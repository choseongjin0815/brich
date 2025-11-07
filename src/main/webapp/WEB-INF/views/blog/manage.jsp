<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<jsp:include page="/WEB-INF/views/layout/menu.jsp">
    <jsp:param name='css' value="
		<link rel='stylesheet' type='text/css'
	      	href='https://cdn.datatables.net/1.13.7/css/jquery.dataTables.min.css'/>
		<link rel='stylesheet' type='text/css'
	      	href='https://cdn.datatables.net/responsive/2.5.0/css/responsive.dataTables.min.css'/>
       	<link type='text/css' rel='stylesheet' href='/css/campaignmain.css' />
		<link type='text/css' rel='stylesheet' href='/css/blog/manage.css' />
		
       	
   	" />
   	<jsp:param name="scripts" value="
		<script src='https://cdn.datatables.net/1.13.7/js/jquery.dataTables.min.js'></script>
		<script src='https://cdn.datatables.net/responsive/2.5.0/js/dataTables.responsive.min.js'></script>
       	<script type='text/javascript' src='/js/blog/manage.js'></script>
	" />    
</jsp:include>
		<div class="manage-container">
			<div class="header-title">블로그 관리</div>
			
			<table class="table table-bordered table-hover dt-responsive">
			        
			        <thead>
			          <tr>
			            <th>제출일</th>
			            <th>제목</th>
			            <th>캠페인 제목</th>
			            <th>상태</th>
			            <th>반려사유</th>
			          </tr>
			        </thead>
				<tbody>
				<c:choose>
					<c:when test="${not empty list.list}">
						<c:forEach items="${list.list}" var="recommend">
							<tr>
								<td>${recommend.cmpnId}</td>
								<td>${recommend.cmpnTitle}</td>
								<td>${recommend.rcrtEndDt}</td>
								<td>${recommend.offrCn}</td>
								<td>${recommend.offrCn}</td>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr>
							<td colspan="5" class="no-data">표시할 캠페인 없음</td>
						</tr>
					</c:otherwise>
				</c:choose>
				</tbody>
	      	</table>
			
		</div>
	</body>
</html>