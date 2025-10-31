<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>신고 관리 - 리스트</title>
	 <script type="text/javascript" src="/js/jquery-3.7.1.min.js"></script>
	 <link type="text/css" rel="stylesheet" href="/css/admin/admin_list.css"/>
	</head>
	<body>
	    <jsp:include page="/WEB-INF/views/layout/menu.jsp">
           <jsp:param name='css' value="
        <link type='text/css' rel='stylesheet' href='/css/campaignmain.css' />
            " />
       </jsp:include>
	
	    <div class="wrapper">
	        <h1>신고 관리</h1>
	        
	        <div class="container">
		        <table>
		            <colgroup>
		                <col/>
		                <col/>
		                <col/>
		                <col/>
		                <col/>
		                <col/>
		            </colgroup>
		            <thead>
		                <tr>
			                <th>아이디</th>
			                <th>신고 제목</th>
			                <th>카테고리</th>
			                <th>신고 일시</th>
			                <th>처리 여부</th>
			                <th>처리 일시</th>
		                </tr>
		            </thead>
		            
		            <tbody>
		            <c:if test="${not empty reportList}">
		            <c:forEach items="${reportList}" var="reportInfo">
		                <tr>
		                    <td>${reportInfo.logId}</td>
		                    <td>
			                    <a href="/admin/report_detail/${reportInfo.rptId}">
				                    <c:if test="${reportInfo.rptFlGrpId != null and 
				                                  reportInfo.rptFlGrpId != ''}">
				                        &#128196;
				                    </c:if>
				                        ${reportInfo.rptTitle}
			                    </a>
		                    </td>
		                    <td>${reportInfo.cdNm}</td>
		                    <td>${reportInfo.crtDt}</td>
		                    <td>${reportInfo.prcnYn}</td>
		                    <td>${reportInfo.prcnDt}</td>
		                </tr>
		            </c:forEach>
		            </c:if>
		            </tbody>
		        </table>
	        </div>
	    </div>
	</body>
</html>