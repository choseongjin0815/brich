<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>카테고리 관리</title>
		</head>
	<body>
	    <jsp:include page="/WEB-INF/views/layout/menu.jsp">
           <jsp:param name='css' value="
        <link type='text/css' rel='stylesheet' href='/css/campaignmain.css' />
            " />
        </jsp:include>
	    <div class="wrapper">
	        <h1>카테고리 관리</h1>
	        
	        <div class="container">
	        <input type="hidden" id="adminId" value="${sessionScope.__LOGIN_USER__.usrId}"/>
	            <div>
	                <button type="button">카테고리 추가</button>
	            </div>
	            
	            <c:forEach items="${campaignCategoryList}" var="cmpnCtgList" varStatus="status">
	                <div> <!-- 카테고리 1개 틀 영역 -->
		                <input type="hidden" id="cdId" value="${cmpnCtgList.cdId}"/>
	                    <input type="hidden" id="prntCdId" value="${cmpnCtgList.prntCdId}"/>
	                    <div> <!-- 카테고리 정보 영역 -->
	                        <c:if test="${cmpnCtgList.level == 1 and cmpnCtgList.prntCdId eq '3000'}">
	                            <div></div> <!-- 올리기/내리기 영역 -->
	                            <span>${cmpnCtgList.cdNm}</span> <!-- 상위 카테고리 이름 -->
	                        </c:if>
                            <c:if test="${cmpnCtgList.level != 1 and cmpnCtgList.prntCdId != '3000'}"> 
                                <span>- ${cmpnCtgList.cdNm}</span> <!-- 하위 카테고리 이름 (있으면) -->
                            </c:if>
                            
                            <c:if test="${cmpnCtgList.useYn eq 'Y'}">
	                            <div>
	                                <button type="button">분할</button>
	                                <button type="button">병합</button>
	                            </div>
                            </c:if>
                            
                            <c:if test="${cmpnCtgList.level == 1 and cmpnCtgList.prntCdId eq '3000'}">
                                <span>순위: ${cmpnCtgList.srt}</span>
                            </c:if>
	                    </div>
	                </div>
	            </c:forEach>
	            
	            <div class="btn-group">
	                <button>저장</button>
	            </div>
	        </div>
	    </div>
	</body>
</html>