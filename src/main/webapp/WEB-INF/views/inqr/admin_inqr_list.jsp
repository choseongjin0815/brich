<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>문의 관리 - 목록</title>
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
	        <h1>문의 관리</h1>
	        
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
		                    <th>문의 회원</th>
		                    <th>문의 제목</th>
		                    <th>카테고리</th>
		                    <th>문의 일시</th>
		                    <th>답변 여부</th>
		                    <th>답변 등록 일시</th>
		                </tr>
		            </thead>
			       
		            <tbody>
		                <c:choose>
		                    <c:when test="${not empty inqrList}">
		                        <c:forEach items="${inqrList}" var="inqrInfo">
		                            <tr>
		                                <td>${inqrInfo.logId}</td>
		                                <td>
		                                    <a href="/admin/inqr_detail/${inqrInfo.inqrId}">
		                                        <c:if test="${not empty inqrInfo.inqrFlGrpId}">&#128196;</c:if>
		                                        ${inqrInfo.inqrTitle}
		                                    </a>
		                                </td>
		                                <td>${inqrInfo.cdNm}</td>
		                                <td>${inqrInfo.crtDt}</td>
		                                <td>${inqrInfo.ansrYn}</td>
		                                <td>${inqrInfo.ansrDt}</td>
		                            </tr>
		                        </c:forEach>
		                    </c:when>
			                <c:otherwise>
			                    <tr>
			                        <td colspan="6" class="no-data">
			                            등록된 문의가 존재하지 않습니다.
			                        </td>
			                    </tr>
			                </c:otherwise>
			            </c:choose>
		            </tbody>
		        </table>
		    </div>
        </div>
    </body>
</html>