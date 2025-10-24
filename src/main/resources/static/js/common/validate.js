$().ready(function() {

    $("label.require").next().on("keyup", function() {
        var value = $(this).val();
        $(this).next(".validate-require").remove();
        if (value === "") {
            var require = $(this).after($("<span class='validate-require'>필수 입력입니다!</span>"));
            $(this).next(".validate-require").css({
                "color": "#FF0000",   // 빨강
                "font-size": "14px"   // 14px
            });
        }

    });
});