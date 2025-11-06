<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>

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
		<div>
			<div class="flex-grow dashboard-title golden-keyword">핵심 황금 키워드</div>
			<div class="flex-grow dashboard-detail golden-keyword">추천 키워드 줄 경쟁률이 낮고, 상위 노출 가능성이 높음</div>
			<button id="golden-keyword-detail" data-user-id="${sessionScope.__LOGIN_USER__.usrId}" class="dashboard-detail-btn">자세히 보기</button>
			<div id="golden-keyword-modal" class="modal">
				<div class="modal-content golden-keyword">
					<span class="close">&times;</span>
					<h3>핵심 황금 키워드</h3>
					<h6>상세보기</h6>

					<button class="btn close-btn">닫기</button>
				</div>
			</div>
			<div style="width: 100%; max-width: 800px; margin: 0 auto;">
				<canvas id="bubbleChart"></canvas>
			</div>

		</div>
		<div>
			<h4 >내 블로그 지수</h4> 
			<div class="flex-row">
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
			<script src="https://cdn.jsdelivr.net/npm/chartjs-plugin-datalabels"></script>
            <div style="width: 100%; max-width: 800px; margin: 0 auto;">
                <canvas id="blogIndexChart"></canvas>
            </div>



        </div>

        <div>신청한 캠페인</div>

        <div>
            <div class="flex-grow dashboard-title daily-visitor">나의 블로그 방문자 수</div>
            <div id="total-visitor" data-user-id="${sessionScope.__LOGIN_USER__.usrId}" >총 방문자 수</div>
            <button id="daily-visitor-detail" data-user-id="${sessionScope.__LOGIN_USER__.usrId}" class="dashboard-detail-btn">자세히 보기</button>
            <div id="daily-visitor-modal" class="modal">
                <div class="modal-content daily-visitor">
                    <span class="close">&times;</span>
                    <h3>핵심 황금 키워드</h3>
                    <h6>상세보기</h6>

                    <button class="btn close-btn">닫기</button>
                </div>
            </div>
            <div style="width: 100%;  max-width: 800px;  margin: 0 30px;">
                <canvas id="dailyVisitorChart"></canvas>
            </div>
        </div>



        <script>

            // 황금키워드
            const goldenKeywordList = JSON.parse(String.raw`${goldenKeywordListJson}`);
            const bubbleCtx = document.getElementById('bubbleChart');

            const colors = [
                'rgba(255, 99, 132, 0.6)',
                'rgba(54, 162, 235, 0.6)',
                'rgba(255, 206, 86, 0.6)',
                'rgba(75, 192, 192, 0.6)',
                'rgba(153, 102, 255, 0.6)'
            ];

            const cols = 5;
            const rows = Math.ceil((goldenKeywordList.length * 3) / cols);
            const cellWidth = 100 / cols;
            const cellHeight = 100 / rows;
            const xPadding = cellWidth / 2;
            const yPadding = cellHeight / 2;

			const canvasWidth = bubbleCtx.clientWidth;
            const canvasHeight = bubbleCtx.clientHeight;

            let row = 0;
            let col = 0;

            const datasets = goldenKeywordList.map((item, index) => {
                const dataPoints = [];
                const baseColor = colors[index % colors.length];

                [item.value1, item.value2, item.value3].forEach((val) => {
                    if (val) {
                        const fontSize = Math.min(16, 10 + val.length * 0.8);
                        const r = Math.max(fontSize * 1.3, 35 + Math.random() * 10);

                        const xMargin = (r / canvasWidth) * 100;
                        const yMargin = (r / canvasHeight) * 100;

                        const x =
                            Math.min(
                                100 - xMargin,
                                Math.max(
                                    xMargin,
                                    col * cellWidth +
                                    xPadding +
                                    (Math.random() - 0.5) * (cellWidth * 0.4)
                                )
                            );
                        const y =
                            Math.min(
                                100 - yMargin,
                                Math.max(
                                    yMargin,
                                    row * cellHeight +
                                    yPadding +
                                    (Math.random() - 0.5) * (cellHeight * 0.4)
                                )
                            );

                        dataPoints.push({ x, y, r, label: val });

                        col++;
                        if (col >= cols) {
                            col = 0;
                            row++;
                        }
                    }
                });

                return {
                    label: item.cdNm,
                    data: dataPoints,
                    backgroundColor: baseColor,
                    borderColor: baseColor.replace('0.6', '1'),
                    borderWidth: 1
                };
            });

            new Chart(bubbleCtx, {
                type: 'bubble',
                data: { datasets },
                options: {
                    responsive: true,
                    layout: { padding: 0 },
                    animation: { duration: 1000, easing: 'easeOutCubic' },
                    plugins: {
                        datalabels: {
                            color: '#fff',
                            font: {
                                weight: 'bold',
                                size: 14
                            },
                            formatter: (v) => v.label,
                            clip: false
                        },
                        legend: {
                            position: 'top',
                            labels: { boxWidth: 20, padding: 15 }
                        },
                        tooltip: {
                            callbacks: {
                                label: (ctx) => `${ctx.dataset.label}: ${ctx.raw.label}`
                            }
                        }
                    },
                    scales: {
                        x: {
                            display: false,
                            min: 0,
                            max: 100
                        },
                        y: {
                            display: false,
                            min: 0,
                            max: 100
                        }
                    }
                },
                plugins: [ChartDataLabels]
            });



            // 방문자 수 그래프

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
                            usePointStyle: true,
                            boxWidth: 10,
                            padding: 10
                        }
                    }
                }
            });

            // 블로그 지수

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