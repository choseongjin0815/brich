$().ready(function() {
    $(".disabled").on("click", function() {
        alert("선정 단계가 아닙니다.");
    });
    
$("button[name='adopt']").on("click", function() {
    var cmpnPstAdptId = $(this).closest(".apllicant").children(".user").children(".logId").data("cmpn-apply-id");
    var adpt = $(this).attr("class");
    that = $(this);
    if (adpt !== "disabled") {
        $.get("/adv/adoptChange?cmpnPstAdptId=" + cmpnPstAdptId + "&adptYn=" + adpt, 
                function(response) {
                    if (response) {
                        if (adpt === "adopted") {
                            $(that).addClass("unadopted").removeClass("adopted");
                        }
                        else if (adpt === "unadopted") {
                            $(that).addClass("adopted").removeClass("unadopted");
                        }
                    }
               });
        }
    });
});