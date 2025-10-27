$().ready(function() {
    var auth = $(".content-box").data("auth");
    console.log(auth);
    var cmpnId = $(".chat-main").data("cmpn");
    console.log(cmpnId);
    if (auth === 1004) {
        $.get("/adv/chat/rooms/all?cmpnId=" + cmpnId, function(response) {
            var items = response.body.data;
            for (var i = 0; i < items.length; i++) {
                var item = items[i];
                var template = $("#chat-room-list").html();

                template = template.replace("#campaigntitle#", item.cmpnTitle)
                    .replace("#campaignstatus#", item.cdNm)
                    .replace("#lastmessage#", item.lastMsgCn)
                    .replace("#blogername#", item.nm)
                    .replace("#latesttime#", item.lastMsgCrtDt)
                    .replace("#unreadcount#", item.unreadCnt);
                var listItem = $(template);
                $(".content-list").append(listItem);
                $(".campaign-status").remove();
                $(".campaign-title.camp").remove();

            }
        });
    }
    else {
        $.get("/blgr/chat/rooms/all", function(response) {
            var items = response.body.data;
            for (var i = 0; i < items.length; i++) {
                var item = items[i];
                var template = $("#chat-room-list").html();
                template = template.replace("#campaigntitle#", item.cmpnTitle)
                    .replace("#campaignstatus#", item.cdNm)
                    .replace("#lastmessage#", item.lastMsgCn)
                    .replace("#latesttime#", item.lastMsgCrtDt)
                    .replace("#unreadcount#", item.unreadCnt);
                var listItem = $(template);
                $(".content-list").append(listItem);
                $(".campaign-title.bloger-name").remove();

            }
        });
    }


    $(".progress.readall").on("click", function() {
        $(".progress.readall, .progress.not").css({
            "border-bottom": "0"
        })
        $(this).css({
            "border-bottom": "3px solid #000"
        })
        if (auth === 1004) {
            $.get("/adv/chat/rooms/all?cmpnId=" + cmpnId, function(response) {
                $(".content-list").children().remove();
                var items = response.body.data;
                console.log(items);
                for (var i = 0; i < items.length; i++) {
                    var item = items[i];
                    var template = $("#chat-room-list").html();

                    template = template.replace("#campaigntitle#", item.cmpnTitle)
                        .replace("#campaignstatus#", item.cdNm)
                        .replace("#lastmessage#", item.lastMsgCn)
                        .replace("#blogername#", item.nm)
                        .replace("#latesttime#", item.lastMsgCrtDt)
                        .replace("#unreadcount#", item.unreadCnt);

                    var listItem = $(template);
                    $(".content-list").append(listItem);
                    $(".campaign-status").remove();
                    $(".campaign-title.camp").remove();
                }
            });
        }
        else {
            $.get("/blgr/chat/rooms/all", function(response) {
                $(".content-list").children().remove();
                var items = response.body.data;
                for (var i = 0; i < items.length; i++) {
                    var item = items[i];
                    var template = $("#chat-room-list").html();
                    template = template.replace("#campaigntitle#", item.cmpnTitle)
                        .replace("#campaignstatus#", item.cdNm)
                        .replace("#lastmessage#", item.lastMsgCn)
                        .replace("#latesttime#", item.lastMsgCrtDt)
                        .replace("#unreadcount#", item.unreadCnt);
                    var listItem = $(template);
                    $(".content-list").append(listItem);
                    $(".campaign-title.bloger-name").remove();

                }
            });
        }
    });

    $(".progress.not").on("click", function() {
        $(".progress.readall, .progress.not").css({
            "border-bottom": "0"
        })
        $(this).css({
            "border-bottom": "3px solid #000"
        })
        if (auth === 1004) {
            $.get("/adv/chat/rooms/unread?cmpnId=" + cmpnId, function(response) {
                $(".content-list").children().remove();
                var items = response.body.data;
                for (var i = 0; i < items.length; i++) {
                    var item = items[i];
                    var template = $("#chat-room-list").html();

                    template = template.replace("#campaigntitle#", item.cmpnTitle)
                        .replace("#campaignstatus#", item.cdNm)
                        .replace("#lastmessage#", item.lastMsgCn)
                        .replace("#blogername#", item.nm)
                        .replace("#latesttime#", item.lastMsgCrtDt)
                        .replace("#unreadcount#", item.unreadCnt);

                    var listItem = $(template);
                    $(".content-list").append(listItem);
                    $(".campaign-status").remove();
                    $(".campaign-title.camp").remove();
                }
            });
        }
        else {
            $.get("/blgr/chat/rooms/unread", function(response) {
                $(".content-list").children().remove();
                var items = response.body.data;
                for (var i = 0; i < items.length; i++) {
                    var item = items[i];
                    var template = $("#chat-room-list").html();
                    template = template.replace("#campaigntitle#", item.cmpnTitle)
                        .replace("#campaignstatus#", item.cdNm)
                        .replace("#lastmessage#", item.lastMsgCn)
                        .replace("#latesttime#", item.lastMsgCrtDt)
                        .replace("#unreadcount#", item.unreadCnt);
                    var listItem = $(template);
                    $(".content-list").append(listItem);
                    $(".campaign-title.bloger-name").remove();


                }
            });
        }
    });
});