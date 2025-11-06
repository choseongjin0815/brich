$().ready(function(){
	const modal = $("#blog-index-modal");
	// 모달 열기
	$(".dashboard-detail-btn").on("click", function() {
	  modal.fadeIn(200);
	  var usrID = $("#blog-index-detail").data("user-id");
	  
	  $.ajax({
		url: "/api/blog/index/" + usrID + "/detail",
		method: "GET",
		data: { date: date },
		success: function(res){
			
		}
	  })
	});

	// 닫기 버튼
	$(".close").on("click", function() {
	  modal.fadeOut(200);
	});

	// 배경 클릭 시 닫기
	$(window).on("click", function(e) {
	  if ($(e.target).is(modal)) modal.fadeOut(200);
	});

})