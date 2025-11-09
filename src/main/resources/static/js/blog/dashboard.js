$().ready(function(){
	const modal = $("#blog-index-modal");
	const table = $("#blog-detail-table");
	const thead = table.find("thead");
	const tbody = table.find("tbody");
	// 모달 열기
	$("#blog-index-detail").on("click", function () {
		const usrId = $(this).data("user-id");

		$.ajax({
			url: `/api/blog/index/${usrId}/detail`,
			method: "GET",
			success: function (data) {
				if (!data || data.length === 0) {
					thead.html("");
					tbody.html("<tr><td>데이터가 없습니다.</td></tr>");
					modal.fadeIn(200);
					return;
				}

				// 날짜 추출 (dt 기준)
				const dates = data.map(d => d.dt);

				// 행(row)으로 표시할 지표 정의
				const metrics = [
					{ key: "avgLk", label: "좋아요" },
					{ key: "avgCmmt", label: "댓글" },
					{ key: "vstCnt", label: "방문자" }
				];

				// 헤더 생성
				let headerHtml = "<tr><th>지표</th>";
				dates.forEach(date => {
					headerHtml += `<th>${date}</th>`;
				});
				headerHtml += "</tr>";
				thead.html(headerHtml);

				// 본문 생성
				let bodyHtml = "";
				metrics.forEach(metric => {
					bodyHtml += `<tr><td>${metric.label}</td>`;
					data.forEach(day => {
						const value = day[metric.key] ?? "-";
						bodyHtml += `<td>${value}</td>`;
					});
					bodyHtml += "</tr>";
				});
				tbody.html(bodyHtml);

				modal.fadeIn(200);
			},
			error: function (err) {
				console.error("데이터 로드 실패:", err);
				alert("데이터를 불러오는 중 오류가 발생했습니다.");
			}
		});
	});

	$(".close").on("click", function() {
		modal.fadeOut(200);
	});

	$(window).on("click", function(e) {
		if ($(e.target).is(modal)) modal.fadeOut(200);
	});
    
    var url = new URL(window.location.href);
    var searchParam = url.searchParams;
    var userName = searchParam.get("userName");
    $(".user-name-space").text(userName);
    //
	
	const $wrap = document.querySelector('.applied-list');
	  if(!$wrap) return;

	  const PAGE_SIZE = parseInt($wrap.dataset.pageSize || '4', 10);
	  const MAX_PAGES = parseInt($wrap.dataset.maxPages || '4', 10);

	  const $container = $wrap.querySelector('.js-applied-items');
	  // ✅ 플렉스 아이템은 자식 <a>
	  const $cards = Array.from($container.querySelectorAll(':scope > a'));
	  const $prev = $wrap.querySelector('.applied-prev');
	  const $next = $wrap.querySelector('.applied-next');
	  const $now  = $wrap.querySelector('.applied-page-now');
	  const $tot  = $wrap.querySelector('.applied-page-total');

	  if ($cards.length === 0) {
	    $wrap.querySelector('.applied-page-nav').style.display = 'none';
	    return;
	  }

	  let totalPages = Math.ceil($cards.length / PAGE_SIZE);
	  totalPages = Math.min(totalPages, MAX_PAGES);
	  let page = 1;

	  function render(){
	    $cards.forEach(a => a.style.display = 'none');

	    const start = (page - 1) * PAGE_SIZE;
	    const end   = Math.min(start + PAGE_SIZE, $cards.length);
	    for (let i = start; i < end; i++){
	      $cards[i].style.display = 'block';  // ✅ flex 아이템이므로 block이면 충분
	    }

	    $now.textContent = String(page);
	    $tot.textContent = String(totalPages);
	    $prev.disabled = page <= 1;
	    $next.disabled = page >= totalPages;
	  }

	  $prev.addEventListener('click', () => { if(page > 1){ page--; render(); } });
	  $next.addEventListener('click', () => { if(page < totalPages){ page++; render(); } });

	  render();
})