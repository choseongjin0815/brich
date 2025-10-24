$().ready(function() {

    $("label.require").next().on("keyup", function() {
        var value = $(this).val();
        $(this).next(".validate-require").remove();
        if (value === "") {
            $(".duplicate-id-check").remove();
            $(this).after($("<span class='validate-require'>필수 입력입니다!</span>"));
            $(this).next(".validate-require").css({
                "color": "#FF0000",   // 빨강
                "font-size": "14px"   // 14px
            });
        }
    });

    $("#password-confirm").on("keyup", function() {

        $(this).next(".validate-password-confirm").remove();
        if ($(this).closest(".input-flex").children("#password").val() !== $(this).val()) {
            $(this).after($("<span class='validate-password-confirm'>비밀번호가 일치하지 않습니다.</span>"));
            $(this).next(".validate-password-confirm").css({
                "color": "#FF0000",   // 빨강
                "font-size": "14px"   // 14px
            });
        }
        else {
            $(this).after($("<span class='validate-password-confirm'>비밀번호가 일치합니다.</span>"));
            $(this).next(".validate-password-confirm").css({
                "color": "#FF0000",   // 빨강
                "font-size": "14px"   // 14px
            });
        }
    });
    $(".duplicate-id").on("click", function() {
        var url = $(".logId").val();
        var btn = $(this);
        $.get("/duplicate-id/check?logId=" + url, function(response) {
            btn.closest(".right-flex").next(".duplicate-id-check").remove();
            console.log(response.body);
            if (response.body === 1) {
                btn.closest(".right-flex").after("<span class='duplicate-id-check'>이미 존재하는 아이디입니다.</span>");
                btn.closest(".right-flex").next(".duplicate-id-check").css({
                    "color": "#FF0000",   // 빨강
                    "font-size": "14px"   // 14px
                });
            }
            else if (response.body === 0) {
                btn.closest(".right-flex").after("<span class='duplicate-id-check'>사용 가능한 아이디입니다.</span>");
                btn.closest(".right-flex").next(".duplicate-id-check").css({
                    "color": "#FF0000",   // 빨강
                    "font-size": "14px"   // 14px
                });
            }
            else if (response.body === 2) {
                btn.closest(".right-flex").after("<span class='duplicate-id-check'>아이디는 8~16자까지 가능하며 영문자와 숫자를 혼합해야합니다.</span>");
                btn.closest(".right-flex").next(".duplicate-id-check").css({
                    "color": "#FF0000",  
                    "font-size": "14px"   
                });
            }
        });
    });
});