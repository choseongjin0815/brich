$().ready(function() {
    
    $.get("/adv/chat/campaigns/all", function(response) {
        if(window.href === "//adv/chat/campaigns"){
            $(".content-list").children().remove();
        }
        var items = response.body.data;
        console.log(items);

        for (var i = 0; i < items.length; i++) {
            var item = items[i];
            var template = $("#chat-campaign-list").html();

            template = template.replace("#campaigntitle#", item.cmpnTitle)
                .replace("#campaignid#", item.cmpnId)
                .replace("#campaign#", item.cmpnId);

            // 상태 라벨 처리
            var status = item.sttsCd === "2009" ? "종료" : "";
            template = template.replace("#campaignstatus#", status);

            var listItem = $(template);

            // 상태가 비어있으면 해당 요소만 제거
            if (status === "") {
                listItem.find(".campaign-status").remove();
            }

            $(".content-list").append(listItem);
        }
    });
    $(".progress.all").on("click", function() {
        $(".content-list").children().remove();
        $(".progress.all, .progress.on, .progress.end").css({
            "border-bottom": "0"
        })
        $(this).css({
            "border-bottom": "3px solid #000"
        })
        $.get("/adv/chat/campaigns/all", function(response) {
            var items = response.body.data;
            console.log(items);

            for (var i = 0; i < items.length; i++) {
                var item = items[i];
                var template = $("#chat-campaign-list").html();

                template = template.replace("#campaigntitle#", item.cmpnTitle)
                    .replace("#campaignid#", item.cmpnId)
                    .replace("#campaign#", item.cmpnId);;

                // 상태 라벨 처리
                var status = item.sttsCd === "2009" ? "종료" : "";
                template = template.replace("#campaignstatus#", status);

                var listItem = $(template);

                // 상태가 비어있으면 해당 요소만 제거
                if (status === "") {
                    listItem.find(".campaign-status").remove();
                }

                $(".content-list").append(listItem);
            }
        });
    });

    $(".progress.on").on("click", function() {
        $(".content-list").children().remove();
        $(".progress.all, .progress.on, .progress.end").css({
            "border-bottom": "0"
        })
        $(this).css({
            "border-bottom": "3px solid #000"
        })
        $.get("/adv/chat/campaigns/ongoing", function(response) {
            var items = response.body.data;
            console.log(items);

            for (var i = 0; i < items.length; i++) {
                var item = items[i];
                var template = $("#chat-campaign-list").html();

                template = template.replace("#campaigntitle#", item.cmpnTitle)
                    .replace("#campaignid#", item.cmpnId)
                    .replace("#campaign#", item.cmpnId);;

                // 상태 라벨 처리
                var status = item.sttsCd === "2009" ? "종료" : "";
                template = template.replace("#campaignstatus#", status);

                var listItem = $(template);

                // 상태가 비어있으면 해당 요소만 제거
                if (status === "") {
                    listItem.find(".campaign-status").remove();
                }

                $(".content-list").append(listItem);
            }
        });
    });

    $(".progress.end").on("click", function() {
        $(".content-list").children().remove();
        $(".progress.all, .progress.on, .progress.end").css({
            "border-bottom": "0"
        })
        $(this).css({
            "border-bottom": "3px solid #000"
        })
        $.get("/adv/chat/campaigns/ended", function(response) {
            var items = response.body.data;
            console.log(items);

            for (var i = 0; i < items.length; i++) {
                var item = items[i];
                var template = $("#chat-campaign-list").html();

                template = template.replace("#campaigntitle#", item.cmpnTitle)
                    .replace("#campaignid#", item.cmpnId)
                    .replace("#campaign#", item.cmpnId);

                // 상태 라벨 처리
                var status = item.sttsCd === "2009" ? "종료" : "";
                template = template.replace("#campaignstatus#", status);

                var listItem = $(template);

                // 상태가 비어있으면 해당 요소만 제거
                if (status === "") {
                    listItem.find(".campaign-status").remove();
                }

                $(".content-list").append(listItem);
            }
        });
    });

    $(document).on("click", ".campaign-content-item", function() {
        window.location.href = "/adv/chat/rooms?cmpnId=" 
                             + $(this).data("campaign-id");
    });

});