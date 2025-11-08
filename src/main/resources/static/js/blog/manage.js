$(document).ready(function() {
    $('table').DataTable({
		responsive: {
		  details: false // 👈 하위 행으로 절대 이동하지 않게
		},
		columnDefs: [
		  { className: 'all', targets: '_all' } // 👈 모든 컬럼 항상 표시
		],
		lengthChange: false, 
		info:false,
		searching:false,
		language: {
		  search: "검색:",
		  lengthMenu: "_MENU_개씩 보기",
		  info: "총 _TOTAL_개 중 _START_~_END_",
		  infoEmpty: "표시할 데이터가 없습니다.",
		  zeroRecords: "검색 결과가 없습니다.",
		  paginate: {
		    first: "처음",
		    last: "마지막",
		    next: "다음",
		    previous: "이전"
		  }
		},
		autoWidth: false, // ✅ 수동 width 적용 가능하게 함
		columnDefs: [
		  { width: "80px", targets: 0 },   // 첫 번째 열 80px
		  { width: "400px", targets: 1 },  // 두 번째 열 200px
		  { width: "300px", targets: 2 },  // 세 번째 열 150px
		]
    });
	
	$(document).on("click", ".btn-reason", function () {
	  const postId = $(this).data("id");
	  $.ajax({
	    url: `/api/user/${postId}/return-reason`,
	    type: "GET",
		success: function (data) {
		  let html = "";
		  if (typeof data === "string") {
		    html = `<p>${data}</p>`;
		  } else if (Array.isArray(data)) {
		    data.forEach(r => {
		      html += `<p><strong>${r.crtDt}</strong><br>${r.postRetnRsn}</p><hr>`;
		    });
		  }
		  $("#reason-detail").html(html);
		  $("#reason-modal").fadeIn(200);
		}
	  });
	});

	$(".close, #reason-modal").on("click", function (e) {
	  if ($(e.target).is(".modal, .close")) $("#reason-modal").fadeOut(200);
	});
});

