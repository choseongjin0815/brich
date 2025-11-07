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
		}
    });
});

