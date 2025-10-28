$().ready(function() {
    $(".role-box").on("click", function() {
        var role = $(this).data("role")
        window.location.href = "/terms/" + role;
    });

    $("#check-all").on("click", function() {
        if ($(this).is(':checked')) {
            $("#personal").prop('checked', true);
            $("#term").prop('checked', true);
        }
        else {
            $("#personal").prop('checked', false);
            $("#term").prop('checked', false);
        }
    });
    $("input[type='checkbox'][name='check']").on("change", function() {
        var length = $("input[type='checkbox'][name='check']:not(:checked)").length;
        if (length === 0) {
            $("#check-all").prop("checked", true);
        } else {
            $("#check-all").prop("checked", false);
        }
    });
    $("#fileInput").on("change", function() {
            var files = this.files;
            var $fileList = $("#fileList");
            $fileList.empty(); // 초기화

            $.each(files, function(index, file) {
                $fileList.append("<div>" + file.name + "</div>");
            });
        });
    $(".regist-btn").on("click", function() {
        if($("#email-confirm").val() === "OK") {
            $(".user-regist-form").submit();
        }
    });

    $(".next-btn").on("click", function() {
        var role = $(this).data("role");
        if ($("#personal").is(':checked') && $("#term").is(':checked')) {
            console.log($("#personal").val())
            window.location.href = "/regist/" + role;
        }
        else {
            alert("모두 동의해야합니다!!!");
        }
    });

    $(".email-send").on("click", function() {
        var email = $("#email").val();
        var timerHtml = $(".email-check-timer");
        if (email !== "" && /[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?/g.test(email)) {
            var totalSeconds = 180; //3분
            var timerInterval;
            function startTimer() {
                timerInterval = setInterval(function() {
                    var minutes = Math.floor(totalSeconds / 60);
                    var seconds = totalSeconds % 60;

                    // 2자리 숫자 포맷
                    var display =
                        (minutes < 10 ? "0" + minutes : minutes) + ":" +
                        (seconds < 10 ? "0" + seconds : seconds);

                    timerHtml.text(display);

                    totalSeconds--;

                    if (totalSeconds < 0) {
                        clearInterval(timerInterval);
                        timerHtml.text("시간 만료");
                    }
                }, 1000);
            }
            // 타이머 시작
            startTimer();
        }
        else {
            timerHtml.text("적절하지 않은 이메일입니다.")
        }
        $.post("/email/send?email=" + email, function() {});
    });

    $(".email-verify").on("click", function() {
        var email = $("#email").val();
        var code = $("#email-confirm").val();
        $.post("/email/verify?email=" + email + "&code=" + code, function(response) {
            console.log(response);
            var isSuccess = response.body.success;
            var message = response.body.message;
            if (isSuccess === true) {
                $(".email-check-timer").remove();
                $(".email-confirm-message").text("인증완료");
                $("#email-confirm").val("OK");
                $("#email-confirm").closest(".right-flex").hide();
            }
            else {
                $(".email-confirm-message").text(message);
            }
        });
    });
    
    $(".find-btn").on("click", function() {
        var name = $("#name").val();
        window.location.href = "/find/id/" + ((name !== "") ? name : "non");
    });
    
    $(".reset-password-btn").on("click", function() {
        window.location.href = "/reset/password"
    });
    
    $(".go-login-btn").on("click", function() {
        window.location.href = "/login";
    });
    
    $(".do-reset-btn").on("click", function() {
        if($("#email-confirm").val() === "OK" 
        && $("#password").val() === $("#password-confirm").val()) {
            alert($("#password").val(), $("#password-confirm").val())
              $(".user-regist-form").submit();
        }
    });
});
