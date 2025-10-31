$().ready(function() {
    submitAutoActive();
    
    $("button[name='adopt'].disabled").on("click", function() {
        alert("선정 단계가 아닙니다.");
    });
    
    $("button[name='adopt']").on("click", function() {
        var cmpnPstAdptId = $(this).closest(".applicant").children(".user").children(".logId").data("cmpn-apply-id");
        var adpt = $(this).attr("class");
        that = $(this);
        
        if (!adpt.includes("disabled")) {
            adpt = adpt.includes("unadopted") ? "unadopted" : "adopted";
            if (adpt === "unadopted" && 
                    parseInt($(".adopt-count").text()) >= parseInt($(".total-adopt-count").text())){
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
        order = $(this).attr("class").includes("asc") ? "asc" : "desc";
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
    
    $(".view-post").on("click", function() {
        postState = $(this).closest("div.applicant").children("button[name=postState]").text();
        postId = $(this).closest(".applicant").children(".user").children(".logId").data("cmpn-apply-id");
        if (postState === "검토중") {
            approveButton = $("<button type='button'></button>")
            approveButton.addClass("button_120_50");
            approveButton.addClass("post-approve");
            approveButton.text("승인");
            approveButton.on("click", function() {
                    nowUrl = window.location.href;
                    
                    $.get("/adv/postapprove/" + postId, function(response) {
                        if (response) {
                            window.location.href = nowUrl;
                        }
                    });
                });
            
            denyButton = $("<button></button>")
            denyButton.addClass("button_120_50");
            denyButton.addClass("post-deny");
            denyButton.on("click", function() {
                    $(".deny-container").css("display", "block");
                });
            denyButton.text("반려");
            
            $(".button-list").append(approveButton);
            $(".button-list").append(denyButton);
        }
        
        $(".modal").css("display", "flex");
        
        var url = $(this).data("post-url");
        var postUrl = $("<a></a>");
        postUrl.text(url);
        postUrl.attr("href", url);
        postUrl.attr("target", "_blank");
        postUrl.data("cmpn-apply-id", postId);

        $(".post-url").append(postUrl);
    });
    
    $("#add-file").on("change", function() {
        var files = this.files;
        var $fileList = $("#file-list");
        $fileList.empty(); // 초기화
    
        $.each(files, function(index, file) {
            $fileList.append("<span>" + file.name +"\t </span>");
        });
    });
    
    $(".deny-submit").on("click", function() {
        var denyContainer = $(this).closest(".deny-container");
        var reason = denyContainer.children("#reason").val();
        var attachedFile = denyContainer.find("input[type=file]");
        var files = attachedFile[0].files;
        var deadline = denyContainer.children(".pst-ddln").find("input[type=date]").val();
        
        var formData = new FormData();
        formData.append("reason", reason);
        
        if (files.length > 0) {
            for(i = 0; i < files.length; i++) {
                formData.append("file", files[i]);
            }
        }
        formData.append("pstDdln", deadline);
        
        url = window.location.href;
        id = $(".post-url").children("a").data("cmpn-apply-id");
        cmpnId = $(".campaign-block").data("cmpn-id");
        $.ajax({
            url: "/adv/deny/" + id + "/" + cmpnId,
            method: "post",
            enctype: "multipart/form-data",
            data: formData,
            processData: false,
            contentType: false,
            success: function(response) {
                if(response) {
                    $(".modal").css("display", "none");
                    window.location.reload();
                }
            }
        });
        
    });
});