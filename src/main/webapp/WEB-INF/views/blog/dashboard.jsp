<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<jsp:include page="/WEB-INF/views/layout/menu.jsp">
	<jsp:param name='css' value="
       	<link type='text/css' rel='stylesheet' href='/css/campaignmain.css' />
    	<link type='text/css' rel='stylesheet' href='/css/blog/dashboard.css' />
       	<link typr='text/css' rel='stylesheet' href='/css/paginator-simple.css'/>

   	" />
	<jsp:param name="scripts" value="
       	<script type='text/javascript' src='/js/blog/dashboard.js'></script>
	" />
</jsp:include>
<div class="dashboard-container">
	<div class="title">Dashboard</div>
	<div class="grid">
		<div>마감 임박 캠페인
			<table>
				<tbody>
				<c:choose>
					<c:when test="${not empty list.list}">
						<c:forEach items="${list.list}" var="recommend">
							<tr>
								<td>${recommend.cmpnId}</td>
								<td>${recommend.cmpnTitle}</td>
								<td>${recommend.rcrtEndDt}</td>
								<td>${recommend.offrCn}</td>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr>
							<td colspan="6" class="no-data">표시할 캠페인 없음</td>
						</tr>
					</c:otherwise>
				</c:choose>
				</tbody>
			</table>
			<jsp:include page="/WEB-INF/views/layout/paginator-simple.jsp">
				<jsp:param name="havePrevPageGroup" value="${paginator.havePrevPageGroup}" />
				<jsp:param name="pageNo" value="${paginator.pageNo}" />
				<jsp:param name="haveNextPageGroup" value="${paginator.haveNextPageGroup}" />
			</jsp:include>
		</div>
		<div>추천 캠페인</div>
		<div>핵심 황금 키워드</div>
		<div>
			<h4 >내 블로그 지수</h4> 
			<div class="flex-row-index">
				<div>현재 블로그 지수: <span class="blog-index">${currentIndex}</span></div>
				<button id="blog-index-detail" data-user-id="${sessionScope.__LOGIN_USER__.usrId}" class="blog-index-detail-btn">자세히 보기</button>
			</div>
			
			<div id="blog-index-modal" class="modal">
				<div class="modal-content">
    				<span class="close">&times;</span>
    				<h3>블로그 상세 통계</h3>
    				<div class="table-container">
    					<table id="blog-detail-table" border="1">
      						<thead></thead>
      						<tbody></tbody>
    					</table>
    				</div>
    				
  				</div>
			</div>
			<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

			<div style="width: 100%; max-width: 800px; margin: 0 auto;">
				<canvas id="blogIndexChart"></canvas>
			</div>



		</div>

		<div>신청한 캠페인</div>

		<div><div>나의 블로그 방문자 수</div>
			<div style="width: 100%;  max-width: 800px;  margin: 0 30px;">
				<canvas id="dailyVisitorChart"></canvas>
			</div>
		</div>

		<!-- 방문자 수 그래프 -->


		<script>
			// 전체 데이터 (14일)
			const allLabels = [
				<c:forEach items="${dailyVisitorsResult}" var="row" varStatus="st">
				"${row.vstDt}"<c:if test="${!st.last}">,</c:if>
				</c:forEach>
			];

			const allCounts = [
				<c:forEach items="${dailyVisitorsResult}" var="row" varStatus="st">
				${row.vstrCnt}<c:if test="${!st.last}">,</c:if>
				</c:forEach>
			];

			// 지난 주 (앞 7개)
			const lastWeekLabels = allLabels.slice(0, 7);
			const lastWeekCounts = allCounts.slice(0, 7);

			// 최근 1주 (뒤 7개)
			const recentWeekLabels = allLabels.slice(7, 14);
			const recentWeekCounts = allCounts.slice(7, 14);

			// X축은 최근 1주의 날짜 기준 (둘 다 같은 길이니까)
			const ctxVisitor = document.getElementById('dailyVisitorChart').getContext('2d');
			const visitorChart = new Chart(ctxVisitor, {
				type: 'bar',
				data: {
					labels: recentWeekLabels,  // X축 라벨
					datasets: [
						{
							label: '지난 주 방문자 수',
							data: lastWeekCounts,
							backgroundColor: 'lightgray',  // 회색
							borderColor: 'white',
							borderWidth: 1
						},
						{
							label: '최근 1주 방문자 수',
							data: recentWeekCounts,
							backgroundColor: '#00C7E2',   // 파란색
							borderColor: 'white',
							borderWidth: 1
						}
					]
				},
				options: {
					responsive: true,
					scales: {
						x: {
							grid: { display: false }
						},
						y: {
							beginAtZero: true,
							ticks: {
								display: false
							}
						}
					},
					legend: { // 범례
						position: 'bottom',
						labels: {
							usePointStyle: true, // 지정된 포인트 모양에 따라 범례 아이콘 생성
							boxWidth: 10,
							padding: 10
						}
					}
				}
			});

			const labels = [
				<c:forEach items="${index}" var="row" varStatus="st">
					"${row.statDt}"<c:if test="${!st.last}">,</c:if>
				</c:forEach>
			];

			const avg5d = [
				<c:forEach items="${index}" var="row" varStatus="st">
					${row.indxValAvg5d}<c:if test="${!st.last}">,</c:if>
				</c:forEach>
			];

			const ovll = [
				<c:forEach items="${index}" var="row" varStatus="st">
					${row.ovllBlgIndx}<c:if test="${!st.last}">,</c:if>
				</c:forEach>
			];

			const ctx = document.getElementById('blogIndexChart').getContext('2d');
			const chart = new Chart(ctx, {
				type: 'line',
				data: {
					labels: labels,
					datasets: [
						{
							label: '5일 평균 지수',
							data: avg5d,
							borderColor: '#7B61FF',
							backgroundColor: 'rgba(123, 97, 255, 0.1)',
							tension: 0.4,
							fill: true,
							pointRadius: 3,
							pointHoverRadius: 5,
							borderWidth: 2
						},
						{
							label: '전체 평균 지수',
							data: ovll,
							borderColor: '#00B8A9',
							backgroundColor: 'rgba(0, 184, 169, 0.1)',
							tension: 0.4,
							fill: true,
							pointRadius: 3,
							pointHoverRadius: 5,
							borderWidth: 2
						}
					]
				},
				options: {
					responsive: true,
					plugins: {
						legend: {
							position: 'top',
							labels: {
								color: '#333',
								font: { size: 13 }
							}
						},
						title: {
							display: true,
							text: '블로그 지수 추이 (5일 평균 | 전체 평균)',
							color: '#333',
							font: { size: 15, weight: 'bold' }
						},
						tooltip: {
							mode: 'index',
							intersect: false
						}
					},
					scales: {
						x: {
							ticks: { color: '#555' },
							grid: { color: 'rgba(0,0,0,0.05)' }
						},
						y: {
							beginAtZero: false,
							ticks: { color: '#555' },
							grid: { color: 'rgba(0,0,0,0.05)' }
						}
					}
				}
			});


		</script>
	</div>
</div>
</body>
</html>