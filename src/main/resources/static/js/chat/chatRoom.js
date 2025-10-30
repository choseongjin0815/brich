var stompClient = null;
var chtRmId = null;
var myId = null;
var myName = null;
var myCmpny = null;
var auth = null;
var startUrl = null;

$().ready(function() {
    // 사용자 정보 및 채팅방 정보 초기화
    auth = $(".content-box").data("auth");
    myId = $(".content-box").data("usr-id");
    myName = $(".content-box").data("nm");
    myCmpny = $(".content-box").data("cmpny");
    startUrl = auth === 1004 ? '/adv' : '/blgr';
    chtRmId = $(".chat-main").data("chtrm-id");

    console.log("채팅방 ID:", chtRmId);
    console.log("내 ID:", myId);
    console.log("권한:", auth);
    console.log("사업자명:", myCmpny);

    // WebSocket 연결 및 채팅방 구독
    connect();

    // 신고하기/나가기 메뉴 열기
    $(".chat-leave-btn-rm").on("click", function(event) {
        event.stopPropagation();
        $(".report-btn-rm, .leave-chat-btn-rm").show();
    });

    // 메뉴 외부 클릭시 메뉴 숨김
    $(document).on("click", function() {
        $(".report-btn-rm, .leave-chat-btn-rm").hide();
    });

    // 채팅방 나가기
    $(".leave-chat-btn-rm").on("click", function(event) {
        event.stopPropagation();
        if (confirm("채팅방을 나가시겠습니까?")) {
            $.post(startUrl + "/chat/room/leave", { chtRmId: chtRmId }, function(response) {
                if (response.body.success) {
                    alert(response.body.message);
                    disconnect();
                    window.location.href = startUrl + "/chat/rooms";
                } else {
                    alert(response.body.message);
                }
            }).fail(function() {
                alert("채팅방 나가기에 실패했습니다.");
            });
        }
    });

    // 페이지 로딩 시 채팅 메시지 내역 출력
    loadChatMessages();

    // 메시지 전송 버튼 클릭 (중복 방지를 위해 off 후 on)
    $(".chat-send-btn").off("click").on("click", function() {
        sendMessage();
    });

    // Enter 키로 메시지 전송 (줄바꿈은 shift + enter로 해야함)
    // 한글 입력 중복 방지를 위해 isComposing을 체크해야함 <== 문제상황 "안녕"같은 한글 입력하면 "안" "안녕" 두개 입력되는 현상이 있었음
    $(".chat-text-input").off("keydown").on("keydown", function(e) {
        // 한글 조합 중이면 무시 (안녕 -> 안녕, 녕 중복 입력 방지)
        if (e.isComposing || e.keyCode === 229) {
            return;
        }
        
        if (e.key === "Enter" && !e.shiftKey) {
            e.preventDefault();
            sendMessage();
        }
    });

    // 파일 첨부 아이콘 클릭
    $(".file-insert").off("click").on("click", function() {
        $("#file-input").click();
    });

    // 파일 선택 시 바로 전송
    $("#file-input").off("change").on("change", function() {
        var files = this.files;
        if (files && files.length > 0) {
            sendFileMessage(files);
            // 선택 초기화
            $(this).val("");
        }
    });

    // 페이지 벗어날 때 WebSocket 연결 해제
    $(window).on("beforeunload", function() {
        disconnect();
    });
});

/**
 * WebSocket 연결
 */
function connect() {
    // 이미 연결되어 있으면 중복 연결 방지
    if (stompClient !== null && stompClient.connected) {
        console.log("이미 WebSocket이 연결되어 있습니다.");
        return;
    }
    
    console.log("=== WebSocket 연결 시작 ===");
    
    var socket = new SockJS('/ws-chat');
    stompClient = Stomp.over(socket);
    
    
    stompClient.connect({}, function(frame) {
        console.log('WebSocket 연결 성공:', frame);
        
        // 채팅방 메시지 구독
        stompClient.subscribe('/topic/chat/' + chtRmId, function(message) {
            var chatMessage = JSON.parse(message.body);
            console.log('메시지 수신:', chatMessage);
            displayMessage(chatMessage);
            
            // 다른 사람의 메시지면 읽음 처리
            if (chatMessage.usrId !== myId) {
                markMessagesAsRead();
            }
        });

        // 읽음 처리 알림 구독
        stompClient.subscribe('/topic/chat/' + chtRmId + '/read', function(message) {
            var payload = JSON.parse(message.body);
            console.log('읽음 처리 알림:', payload);
            
            // 내가 보낸 메시지의 읽음 표시 업데이트
            if (payload.usrId !== myId) {
                updateReadStatus();
            }
        });

        // 채팅방 입장 시 읽음 처리
        markMessagesAsRead();
        
    }, function(error) {
        console.error('WebSocket 연결 실패:', error);
        // 5초 후 재연결 시도
        setTimeout(function() {
            console.log('WebSocket 재연결 시도...');
            connect();
        }, 5000);
    });
}

/**
 * WebSocket 연결 해제
 */
function disconnect() {
    if (stompClient !== null && stompClient.connected) {
        stompClient.disconnect(function() {
            console.log('WebSocket 연결 해제');
        });
    }
}

/**
 * 채팅 메시지 내역 로드
 */
function loadChatMessages() {
    $.get(startUrl + "/chat/room/" + chtRmId + "/messages", function(response) {
        if (response.body.success) {
            var messages = response.body.data;
            var chatArea = $(".message-container");
            chatArea.empty(); // 기존 메시지 삭제

            if (messages && messages.length > 0) {
                messages.forEach(function(message) {
                    displayMessage(message, false); // 스크롤 안함
                });
            }
            // 스크롤을 최하단으로 이동
            scrollToBottom();
        }
    }).fail(function(error) {
        console.error("메시지 로드 실패:", error);
    });
}

/**
 * 메시지 전송
 */
function sendMessage() {
    console.log("=== sendMessage 호출 시작 ===");
    
    var messageContent = $(".chat-text-input").val().trim();
    
    if (!messageContent) {
        alert("메시지를 입력해주세요.");
        return;
    }

    if (!stompClient || !stompClient.connected) {
        alert("채팅 서버와 연결되지 않았습니다. 잠시 후 다시 시도해주세요.");
        connect(); // 재연결 시도
        return;
    }

    // WebSocket으로 메시지 전송
    var chatMessage = {
        chtRmId: chtRmId,
        usrId: myId,
        msgCn: messageContent,
        nm: myName,
        cmpny: myCmpny
    };

    console.log("메시지 전송 데이터:", chatMessage);
    console.log("전송 시간:", new Date().toISOString());

    stompClient.send("/app/chat/send", {}, JSON.stringify(chatMessage));

    console.log("=== sendMessage 전송 완료 ===");

    // 입력창 초기화
    $(".chat-text-input").val("");
    $(".chat-text-input").focus();
}

/**
 * 파일 메시지 전송
 */
function sendFileMessage(files) {
    console.log("=== sendFileMessage 호출 ===", files);
    
    if (!files || files.length === 0) {
        return;
    }

    // FormData 생성
    var formData = new FormData();
    
    // 파일 추가
    for (var i = 0; i < files.length; i++) {
        formData.append("files", files[i]);
    }
    
    // 메시지 정보 추가
    formData.append("chtRmId", chtRmId);
    formData.append("usrId", myId);
    formData.append("msgCn", ""); // 파일만 전송
    formData.append("nm", myName);
    formData.append("cmpny", myCmpny);

    // REST API로 전송 (Controller의 @PostMapping("/message/send"))
    $.ajax({
        url: startUrl + "/chat/message/send",
        type: "POST",
        data: formData,
        processData: false,  // FormData 사용 시 필수
        contentType: false,  // FormData 사용 시 필수
        success: function(response) {
            console.log("파일 전송 성공:", response);
            // 서버에서 WebSocket으로 브로드캐스트하므로 별도 처리 불필요
        },
        error: function(error) {
            console.error("파일 전송 실패:", error);
            alert("파일 전송에 실패했습니다.");
        }
    });
}

/**
 * 메시지 화면에 표시
 */
function displayMessage(message, shouldScroll) {
    console.log("=== displayMessage 호출 ===", message);
    
    var chatArea = $(".message-container");
    var formattedTime = formatChatDate(message.crtDt);
    
    // 내 메시지인 경우
    if (message.usrId === myId) {
        // 읽음 표시 아이콘 (rdYn이 Y면 표시)
        var readCheckImg = "";
        if (message.rdYn === "Y") {
            readCheckImg = "<img class='read-check' src='/img/read_receipt.png' />";
        }
        
        // 첨부 파일 처리
        var fileImagesHtml = "";
        if (message.attchGrpId && message.fileList && message.fileList.length > 0) {
            message.fileList.forEach(function(file) {
                // 확장자 추출
                var ext = '';
                if (file.flNm && file.flNm.lastIndexOf('.') > -1) {
                    ext = file.flNm.substring(file.flNm.lastIndexOf('.') + 1).toLowerCase();
                }
                
                // 파일 다운로드 URL
                var fileUrl = '/file/' + myId + '/' + message.attchGrpId + '/' + file.flId;
                
                // 이미지 파일이면 미리보기 표시 (클릭하면 다운로드)
                if (['jpg', 'jpeg', 'png', 'gif', 'webp', 'bmp'].includes(ext)) {
                    fileImagesHtml += "<a href='" + fileUrl + "' download='" + escapeHtml(file.flNm) + "'>" +
                        "<img class='chat-img' src='" + fileUrl + "' />" +
                        "</a>";
                } 
                // 기타 파일은 파일명 + 다운로드 링크로 표시
                else {
                    fileImagesHtml += "<div class='chat-file'>" +
                        "<a href='" + fileUrl + "' download='" + escapeHtml(file.flNm) + "'>" +
                        escapeHtml(file.flNm) +
                        "</a>" +
                        "</div>";
                }
            });
        }
        
        var myMessageHtml = 
            "<div class='my-message'>" +
                readCheckImg +
                "<div class='message-time'>" + formattedTime + "</div>";
        
        // 파일과 텍스트를 묶는 컨테이너
        myMessageHtml += "<div class='message-content-wrapper'>";
        
        // 파일이 있으면 표시
        if (fileImagesHtml) {
            myMessageHtml += fileImagesHtml;
        }
        
        // 텍스트 메시지가 있으면 표시
        if (message.msgCn && message.msgCn.trim()) {
            myMessageHtml += "<div class='my-message-text'>" + escapeHtml(message.msgCn) + "</div>";
        }
        
        myMessageHtml += "</div></div>";
        
        chatArea.append(myMessageHtml);
    } 
    // 상대방 메시지인 경우
    else {
        var senderName = "";
        
        // 내가 광고주가 아닐 때 (블로거) - 회사명 표시
        if (message.cmpny && message.cmpny.trim()) {
            senderName = message.cmpny;  // 보낸 사람이 사업자면 회사명
        } else {
            senderName = message.usrNm;  // 보낸 사람이 블로거면 이름
        }
        
        // 첨부 파일 처리
        var fileImagesHtml = "";
        if (message.attchGrpId && message.fileList && message.fileList.length > 0) {
            message.fileList.forEach(function(file) {
                // 확장자 추출
                var ext = '';
                if (file.flNm && file.flNm.lastIndexOf('.') > -1) {
                    ext = file.flNm.substring(file.flNm.lastIndexOf('.') + 1).toLowerCase();
                }
                
                // 파일 다운로드 URL
                var fileUrl = '/file/' + message.usrId + '/' + message.attchGrpId + '/' + file.flId;
                
                // 이미지 파일이면 미리보기 표시 (클릭하면 다운로드)
                if (['jpg', 'jpeg', 'png', 'gif', 'webp', 'bmp'].includes(ext)) {
                    fileImagesHtml += "<a href='" + fileUrl + "' download='" + escapeHtml(file.flNm) + "'>" +
                        "<img class='chat-img' src='" + fileUrl + "' />" +
                        "</a>";
                } 
                // 기타 파일은 파일명 + 다운로드 링크로 표시
                else {
                    fileImagesHtml += "<div class='chat-file'>" +
                        "<a href='" + fileUrl + "' download='" + escapeHtml(file.flNm) + "'>" +
                         escapeHtml(file.flNm) +
                        "</a>" +
                        "</div>";
                }
            });
        }
        
        var otherMessageHtml = 
            "<div class='other-message'>" +
                "<div class='other-message-text-box'>" +
                    "<div class='other-name'>" + escapeHtml(senderName) + "</div>" +
                    "<div class='message-content-wrapper'>";
        
        // 파일이 있으면 표시
        if (fileImagesHtml) {
            otherMessageHtml += fileImagesHtml;
        }
        
        // 텍스트 메시지가 있으면 표시
        if (message.msgCn && message.msgCn.trim()) {
            otherMessageHtml += "<div class='other-message-text'>" + escapeHtml(message.msgCn) + "</div>";
        }
        
        otherMessageHtml +=
                    "</div>" +
                "</div>" +
                "<div class='message-time'>" + formattedTime + "</div>" +
            "</div>";
        
        chatArea.append(otherMessageHtml);
    }

    // 스크롤 처리 (기본값 true)
    if (shouldScroll !== false) {
        scrollToBottom();
    }
}

/**
 * 스크롤을 최하단으로 이동
 */
function scrollToBottom() {
    var container = $(".message-container");
    setTimeout(function() {
        container.scrollTop(container[0].scrollHeight);
    }, 100);
}

/**
 * 읽음 처리
 */
function markMessagesAsRead() {
    if (stompClient && stompClient.connected) {
        var payload = {
            chtRmId: chtRmId,
            usrId: myId
        };
        
        stompClient.send("/app/chat/read", {}, JSON.stringify(payload));
        console.log("읽음 처리 전송:", payload);
    }
}

/**
 * 읽음 상태 업데이트 (UI 업데이트용)
 */
function updateReadStatus() {
    // 상대방이 메시지를 읽었을 때 내가 보낸 메시지에 읽음 표시 추가
    console.log("읽음 상태 업데이트");
    
    // 아직 읽음 표시가 없는 내 메시지들에 읽음 아이콘 추가
    $(".my-message").each(function() {
        if ($(this).find(".read-check").length === 0) {
            $(this).prepend("<img class='read-check' src='/img/read_receipt.png' />");
        }
    });
}

/**
 * 채팅 메시지 시간 변환
 */
function formatChatDate(isoString) {
    if (!isoString) return "";

    const date = new Date(isoString);
    const now = new Date();

    const y = date.getFullYear();
    const m = date.getMonth() + 1;
    const d = date.getDate();
    const hh = date.getHours();
    const mm = date.getMinutes();

    const nowY = now.getFullYear();
    const nowM = now.getMonth() + 1;
    const nowD = now.getDate();

    const two = n => String(n).padStart(2, "0");

    // 오늘인지 체크
    if (y === nowY && m === nowM && d === nowD) {
        const ampm = hh < 12 ? "오전" : "오후";
        let hour12 = hh % 12;
        if (hour12 === 0) hour12 = 12;
        return `${ampm} ${hour12}:${two(mm)}`;
    }

    // 올해
    if (y === nowY) {
        return `${two(m)}.${two(d)}`;
    }

    // 작년 이전
    return `${y}.${two(m)}.${two(d)}`;
}

/**
 * XSS 방지를 위한 HTML 이스케이프 <-- SQL인젝션 같은 느낌 생각하면 됨
 */
function escapeHtml(text) {
    if (!text) return "";
    
    var map = {
        '&': '&amp;',
        '<': '&lt;',
        '>': '&gt;',
        '"': '&quot;',
        "'": '&#039;',
        '\n': '<br>'
    };
    
    return text.replace(/[&<>"'\n]/g, function(m) { return map[m]; });
}