var auth = 0;

$().ready(function() {
    auth = $(".content-box").data("auth");
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
        var startUrl = auth === 1004 ? '/adv' : '/blgr';
        $.post(startUrl + "/chat/room/leave?chtRmId=" + chtRmId, function(response) {
            alert(response.body.message);
            window.history.back();
        });
    });
});