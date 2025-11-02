$().ready(function(){
	const modal = $("#blog-index-modal");
	// 모달 열기
	$(".blog-index-detail-btn").on("click", function() {
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

})