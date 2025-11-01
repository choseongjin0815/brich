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
					  <jsp:param name="havePrevPageGroup" value="${search.havePrevPageGroup}" />
					  <jsp:param name="pageNo" value="${search.pageNo}" />
					  <jsp:param name="haveNextPageGroup" value="${search.haveNextPageGroup}" />
					</jsp:include>
				</div>
				<div>추천 캠페인</div>
				<div>핵심 황금 키워드</div>
				<div>내 블로그 지수
					<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

					<div style="width: 100%; max-width: 800px; margin: 0 auto;">
					    <canvas id="blogIndexChart"></canvas>
					</div>

					<script>
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
					                    tension: 0.3,
					                    fill: true,
					                    pointRadius: 3,
					                    pointHoverRadius: 5
					                }
					            ]
					        },
					        options: {
					            responsive: true,
					            plugins: {
					                legend: { position: 'top' },
					                title: {
					                    display: true,
					                    text: '최근 블로그 지수 변화'
					                }
					            },
					            scales: {
					                x: {
					                    title: { display: true, text: '날짜' }
					                },
					                y: {
					                    beginAtZero: true,
					                    title: { display: true, text: '지수' }
					                }
					            }
					        }
					    });
					</script>

				</div>
				<div>신청한 캠페인</div>
				<div>나의 블로그 방문자 수</div>
			
			
		</div>
		</div>
	</body>
</html>