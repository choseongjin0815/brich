

$().ready(function() {
    var auth = $(".content-box").data("auth");
    var myId = $(".content-box").data("usr-id");
    console.log(myId);
    var startUrl = auth === 1004 ? '/adv' : '/blgr';
    chtRmId = $(".chat-main").data("chtrm-id");

    // 신고하기/나가기 메뉴 열기
    $(".chat-leave-btn-rm").on("click", function(event) {
        event.stopPropagation();
        $(".report-btn-rm, .leave-chat-btn-rm").show();
    });

    // 메뉴 외부 클릭시 메뉴 숨김
    $(document).on("click", function() {
        $(".report-btn-rm, .leave-chat-btn-rm").hide();
    });

    //채팅방 나가기
    $(".leave-chat-btn").on("click", function() {
        $.post(startUrl + "/chat/room/leave?chtRmId=" + chtRmId, function(response) {
            alert(response.body.message);
            window.history.back();
        });
    });
    //페이지 로딩 시 채팅 메시지 내역 출력
    $.get(startUrl + "/chat/room/" + chtRmId + "/messages", function(response) {
        var item = response.body.data
        var chatArea = $(".message-container");
        for (var i = 0; i < item.length; i++) {
            item[i].crtDt = formatChatDate(item[i].crtDt);
            //나의 메시지 일때
            if (myId === item[i].usrId) {
                chatArea.append(
                    "<div class='my-message'>" +
                        "<div class='message-time'>" + item[i].crtDt + "</div>" +
                        "<div class='my-message-text'>" + item[i].msgCn + "</div>" +
                    "</div>"
                );
            }
            //상대방 메시지이면서 내가 광고주가 아닐 때
            else if (auth !== 1004) {
                chatArea.append(
                    "<div class='other-message'>" +
                        "<div class='other-message-text-box'>" +
                            "<div class='other-name'>" + item[i].cmpny + "</div>" +
                            "<div class='other-message-text'>" + item[i].msgCn + "</div>" +
                        "</div>" +
                        "<div class='message-time'>" + item[i].crtDt + "</div>" +
                    "</div>"

                );
            }
            //상대방 메시지이면서 내가 광고주 일 때
            else if (auth === 1004) {
                console.log("DDD", item[i]);
                chatArea.append(
                    "<div class='other-message'>" +
                        "<div class='other-message-text-box'>" +
                            "<div class='other-name'>" + item[i].usrNm + "</div>" +
                            "<div class='other-message-text'>" + item[i].msgCn + "</div>" +
                        "</div>" +
                        "<div class='message-time'>" + item[i].crtDt + "</div>" +
                        "</div>" +
                    "</div>"
                );
            }
        }
        setTimeout(function() {
            $(".message-container").scrollTop($(".message-container")[0].scrollHeight);
        }, 0);
         
    });

});
// 채팅 메시지 시간 변환
function formatChatDate(isoString) {
  if (!isoString) return "";

  // 입력 UTC -> Date
  const utcDate = new Date(isoString);
  if (isNaN(utcDate)) return "";

  // 서울 offset (분) — 한국은 고정 +9
  const seoulOffsetMin = 9 * 60;

  // 서울 시간으로 변환된 Date 객체
  const seoulDate = new Date(utcDate.getTime() + seoulOffsetMin * 60 * 1000);
  const seoulNow  = new Date(Date.now() + seoulOffsetMin * 60 * 1000);

  const y = seoulDate.getFullYear();
  const m = seoulDate.getMonth() + 1; // 0-based
  const d = seoulDate.getDate();
  const hh = seoulDate.getHours();
  const mm = seoulDate.getMinutes();

  const nowY = seoulNow.getFullYear();
  const nowM = seoulNow.getMonth() + 1;
  const nowD = seoulNow.getDate();

  // helper: 두자리 포맷
  const two = n => String(n).padStart(2, "0");

  // 오늘 체크
  if (y === nowY && m === nowM && d === nowD) {
    const ampm = hh < 12 ? "오전" : "오후";
    let hour12 = hh % 12;
    if (hour12 === 0) hour12 = 12;
    return `${ampm} ${hour12}:${String(mm).padStart(2, "0")}`;
  }

  // 올해(하지만 오늘 아님)
  if (y === nowY) {
    return `${two(m)}.${two(d)}`;
  }

  // 작년 이전
  return `${y}.${two(m)}.${two(d)}`;
}