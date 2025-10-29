/**
 * 
 */

$().ready(function(){
	
	var activeColor = "#6A52E8";
	var defaultColor= "#7B61FF";
	$("#verify-btn").on("mousedown touchstart", function() {
	  $(this).css("background-color", activeColor);
	});

	$("#verify-btn").on("mouseup mouseleave touchend", function() {
	  $(this).css("background-color", defaultColor);
	  $()
	});
	
	const modal = $("#verify-modal");

	  // 모달 열기
	  $("#verify-btn").on("click", function() {
	    modal.fadeIn(200);
	  });

	  // 닫기 버튼
	  $(".close").on("click", function() {
	    modal.fadeOut(200);
	  });

	  // 배경 클릭 시 닫기
	  $(window).on("click", function(e) {
	    if ($(e.target).is(modal)) modal.fadeOut(200);
	  });

	  $("#generateCode").on("click", function() {
	    $.ajax({
	      url: "/api/verify-code",
	      method: "POST",
	      success: function(res) {
	        $("#verificationCode").html(
	          `<strong style='color:#6A52E8;'>${res.code}</strong>`
	        );
	        $("#verifyResult").text("이 코드를 블로그 소개글에 넣고 인증을 진행하세요.");
			$("#generateCode").attr("disabled", true);
			$("#generateCode").css("background-color","#aaa");
	      }
	    });
	  });
	  
	  $("#run-verification").on("mousedown touchstart", function() {
	    $(this).css("background-color", "#079634");
	  });

	  $("#run-verification").on("mouseup mouseleave touchend", function() {
	    $(this).css("background-color", "#28a745");
	    $()
	  });
	  // 인증 시작
	  $("#run-verification").on("click", function() {
	    $("#verify-result").text("인증 진행 중...");

	    // ✅ input의 value 가져오기
	    var blogURL = $("#blog-url").val().trim();
		var usrID = $("#run-verification").data("user-id");


	    // ✅ 비어 있는지 확인
	    if (blogURL === "") {
	      $("#verify-result").html("<span style='color:red'>블로그 주소를 입력해주세요.</span>");
	      return; // 더 이상 진행하지 않음
	    }

	    $.ajax({
	      url: "/api/verify",
	      method: "POST",
	      contentType: "application/json",
	      data: JSON.stringify({
	        blogUrl: blogURL,
	        usrId: usrID
	      }),
	      success: function(res) {
	        if (res.success) {
	          $("#verify-result").html("<span style='color:green'>인증에 성공하였습니다. 블로그 관리 화면으로 이동합니다.</span>");
	          setTimeout(function() {
	            window.location.href = "/blog/" + usrID + "/manage";
	          }, 3000);
	        } else {
	          $("#verify-result").html("<span style='color:red'>인증 실패 ❌</span>");
	        }
	      },
	      error: function() {
	        $("#verify-result").html("<span style='color:red'>서버 오류 발생 ❌</span>");
	      }
	    });
	  });

})