// 현재 페이지 상태를 저장하는 변수
var currentFilter = 'all'; // 'all', 'unread'
var currentPage = 0;
var auth = 0;
var cmpnId = "";

$().ready(function() {
    auth = $(".content-box").data("auth");
    cmpnId = $(".chat-main").data("cmpn");
    
    console.log("auth:", auth);
    console.log("cmpnId:", cmpnId);
    
    // 초기 로드: 전체 채팅방 목록 첫 페이지 조회
    loadChatRoomList('all', 0);

    // 전체 버튼 클릭
    $(".progress.readall").on("click", function() {
        updateFilterButtons($(this));
        currentFilter = 'all';
        currentPage = 0;
        loadChatRoomList('all', 0);
    });

    // 안읽음 버튼 클릭
    $(".progress.not").on("click", function() {
        updateFilterButtons($(this));
        currentFilter = 'unread';
        currentPage = 0;
        loadChatRoomList('unread', 0);
    });

    // 페이지 번호 클릭 이벤트 (동적 생성된 요소)
    $(document).on("click", ".page-item", function() {
        var pageNo = $(this).data("page");
        currentPage = pageNo;
        loadChatRoomList(currentFilter, pageNo);
    });

    // 이전 그룹 버튼 클릭
    $(document).on("click", ".page-prev-group", function() {
        var pageNo = $(this).data("page");
        currentPage = pageNo;
        loadChatRoomList(currentFilter, pageNo);
    });

    // 다음 그룹 버튼 클릭
    $(document).on("click", ".page-next-group", function() {
        var pageNo = $(this).data("page");
        currentPage = pageNo;
        loadChatRoomList(currentFilter, pageNo);
    });
});

// 필터 버튼 스타일 업데이트
function updateFilterButtons($clickedButton) {
    $(".progress.readall, .progress.not").css({
        "border-bottom": "0"
    });
    $clickedButton.css({
        "border-bottom": "3px solid #000"
    });
}

// 채팅방 목록 로드 함수
function loadChatRoomList(filter, pageNo) {
    var url = "";
    var params = "pageNo=" + pageNo;
    
    if (auth === 1004) {
        // 광고주용
        params += "&cmpnId=" + cmpnId;
    }
    
    if (filter === 'all') {
        url = (auth === 1004 ? "/adv" : "/blgr") + "/chat/rooms/all";
    } else if (filter === 'unread') {
        url = (auth === 1004 ? "/adv" : "/blgr") + "/chat/rooms/unread";
    }
    
    $.get(url + "?" + params, function(response) {
        var result = response.body;
        var items = result.chatRoomList;
        
        // 기존 목록 초기화
        $(".content-list").children().remove();
        
        // 채팅방 아이템 렌더링
        if (items && items.length > 0) {
            for (var i = 0; i < items.length; i++) {
                var item = items[i];
                var template = $("#chat-room-list").html();

                template = template.replace("#campaigntitle#", item.cmpnTitle)
                    .replace("#campaignstatus#", item.cdNm)
                    .replace("#lastmessage#", item.lastMsgCn || "")
                    .replace("#latesttime#", item.lastMsgCrtDt || "")
                    .replace("#unreadcount#", item.unreadCnt || 0);
                
                if (auth === 1004) {
                    // 광고주는 블로거 이름 표시
                    template = template.replace("#blogername#", item.nm || "");
                }

                var listItem = $(template);
                
                // 권한에 따라 불필요한 요소 제거
                if (auth === 1004) {
                    listItem.find(".campaign-status").remove();
                    listItem.find(".campaign-title.camp").remove();
                } else {
                    listItem.find(".campaign-title.bloger-name").remove();
                }

                $(".content-list").append(listItem);
            }
        } else {
            $(".content-list").append("<div class='no-data'>채팅방이 없습니다.</div>");
        }
        
        // 페이지네이션 렌더링
        renderPagination(result);
    });
}

// 페이지네이션 렌더링 함수
function renderPagination(pageInfo) {
    var paginationHtml = "";
    
    // 이전 그룹 버튼
    if (pageInfo.havePrevPageGroup) {
        paginationHtml += '<span class="page-prev-group" data-page="' + 
                         pageInfo.prevGroupStartPageNo + '">&lt;&lt;</span> ';
    }
    
    // 페이지 번호들
    for (var i = pageInfo.groupStartPageNo; i <= pageInfo.groupEndPageNo; i++) {
        if (i === pageInfo.pageNo) {
            paginationHtml += '<span class="page-item active" data-page="' + i + '">' + 
                             (i + 1) + '</span> ';
        } else {
            paginationHtml += '<span class="page-item" data-page="' + i + '">' + 
                             (i + 1) + '</span> ';
        }
    }
    
    // 다음 그룹 버튼
    if (pageInfo.haveNextPageGroup) {
        paginationHtml += '<span class="page-next-group" data-page="' + 
                         pageInfo.nextGroupStartPageNo + '">&gt;&gt;</span>';
    }
    
    $(".page-list").html(paginationHtml);
}