<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:include page="/WEB-INF/views/layout/menu.jsp">
    <jsp:param name='css' value="
       	<link type='text/css' rel='stylesheet' href='/css/campaignmain.css' />
    	<link type='text/css' rel='stylesheet' href='/css/blog/dashboard.css' />
       	
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
                              				<td>${recommend.profile}</td>
                              				<td>${recommend.name}</td>
                              				<td>${recommend.expiredate}</td>
                            				<td>${recommend.reward}</td>
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
				</div>
				<div>추천 캠페인</div>
				<div>핵심 황금 키워드</div>
				<div>내 블로그 지수</div>
				<div>신청한 캠페인</div>
				<div>나의 블로그 방문자 수</div>
			
			
		</div>
		</div>
	</body>
</html>