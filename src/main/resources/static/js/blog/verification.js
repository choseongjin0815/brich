/**
 * 
 */

$().ready(function(){
	
	var activeColor = "#6A52E8";
	var defaultColor= "#7B61FF";
	$("#verify-btn").on("mousedown touchstart", function() {
	  $(this).css("background-color", activeColor);
	});

	// 마우스 뗐을 때 원래 색 복귀
	$("#verify-btn").on("mouseup mouseleave touchend", function() {
	  $(this).css("background-color", defaultColor);
	});
	
	const modal = $("#verifyModal");

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

	  // 인증 시작
	  $("#runVerification").on("click", function() {
	    $("#verifyResult").text("인증 진행 중...");

	    $.ajax({
	      url: "/api/verify",
	      method: "POST",
		  contentType: "application/json",   // ✅ JSON 타입 명시
		      data: JSON.stringify({
		        blogUrl: "https://blog.naver.com/kse4966",   // ✅ 블로그 주소
		        usrId: "USR-20231010-000001"                 // ✅ 사용자 ID
		      }),
	      success: function(res) {
	        if (res.success) {
	          $("#verifyResult").html("<span style='color:green'>인증 성공 ✅</span>");
	        } else {
	          $("#verifyResult").html("<span style='color:red'>인증 실패 ❌</span>");
	        }
	      },
	      error: function() {
	        $("#verifyResult").html("<span style='color:red'>서버 오류 발생 ❌</span>");
	      }
	    });
	  });
})