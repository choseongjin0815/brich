<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>문의 관리 - 문의 목록</title>
	</head>
	<body>
	    <div>
	        <h1>문의 관리</h1>
	        
	        <table>
	            <colgroup>
	                <col />
	                <col />
	                <col />
	                <col />
	                <col />
	                <col />
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
	                    <c:when test="${not empty inqr_list}">
	                        <c:forEach items="${inqr_list}" var="items">
	                            <tr>
	                                <td>${items.usrVO.logId}</td>
	                                <td>
	                                    <a href="/admin/inqr_list">
	                                        ${items.inqrTitle}
	                                    </a>
	                                </td>
	                                <td>${items.inqrCtg}</td>
	                                <td>${items.crtDt}</td>
	                                <td>${items.ansrYn}</td>
	                                <td>${items.ansrDt}</td>
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
    </body>
</html>