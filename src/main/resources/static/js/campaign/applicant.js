$().ready(function() {
    $(".disabled").on("click", function() {
        alert("선정 단계가 아닙니다.");
    });
    
    $("button[name='adopt']").on("click", function() {
        var cmpnPstAdptId = $(this).closest(".apllicant").children(".user").children(".logId").data("cmpn-apply-id");
        var adpt = $(this).attr("class");
        that = $(this);
        
        if (!adpt.includes("disabled")) {
            adpt = adpt.includes("unadopted") ? "unadopted" : "adopted";
            if (adpt === "unadopted" && 
                    $(".adopt-count").text() === $(".total-adopt-count").text()){
                            alert("더 이상 채택할 수 없습니다.");
                    }
            
            else {
                $.get("/adv/adoptChange?cmpnPstAdptId=" + cmpnPstAdptId + "&adptYn=" + adpt, 
                            function(response) {
                                if (response) {
                                    if (adpt === "adopted") {
                                        $(that).addClass("unadopted").removeClass("adopted");
                                        $(".adopt-count").text(parseInt($(".adopt-count").text()) - 1);
                                    }
                                    
                                    else if (adpt === "unadopted") {
                                        $(that).addClass("adopted").removeClass("unadopted");
                                        $(".adopt-count").text(parseInt($(".adopt-count").text()) + 1);
                                    }
                                }
                           });
                  }
            }
            
        })
        .css('pointer-events', 'none');
    
    var urlParams = new URLSearchParams(window.location.search);
    if (urlParams.get("sortCol") !== null) {
        column = urlParams.get("sortCol");
        order = urlParams.get("order");
        
        if (order === 'asc') {
                $(`[data-sort-type="${column}"]`).addClass("desc").removeClass("asc");
            }
            else if (order === 'desc') {
                $(`[data-sort-type="${column}"]`).addClass("asc").removeClass("desc");
            }
    }

    $(".sort").on("click", function() {
        order = $(this).attr("class").split(" ")[1];
        col = $(this).data("sort-type");
        
        if (window.location.search === "") {
            url = window.location.pathname + "?order=" + order
                                           + "&sortCol=" + col;
         }
         else {
             url = new URL(window.location.href);
             searchParam = url.searchParams;
             searchParam.set("order", order);
             searchParam.set("sortCol", col);
             url = url.toString();
         }
        
        window.location.href = url;
    });
    
    $(".id-search").children("img").on("click", function() {
         id = $(this).closest(".id-search").children("input[type=text]").val();
         url = "";
         if (window.location.search === "") {
            url = window.location.pathname + "?searchId=" + id;
         }
         else {
             url = new URL(window.location.href);
             searchParam = url.searchParams;
             searchParam.set("searchId", id);
             searchParam.set("pageNo", 0);
             url = url.toString();
         }
         window.location.href = url;
    })
});