<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
	<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<link type="text/css" rel="stylesheet" href="/css/brich.css" />
		<script type="text/javascript" src="/js/jquery-3.7.1.min.js"></script>
		<script type="text/javascript" src="/js/campaign/applicant.js"></script>
	</head>
	<body>
	    <jsp:include page="/WEB-INF/views/campaign/campaignTab.jsp" />
	    <div class="title">
            <div>캠페인 신청자</div>
            <span>~2025-10-20</span>
        </div>
        
        <div class="id-search">
           <img src="/img/Search.png" />
           <input type="text" placeholder="ID를 입력하세요." />
        </div>
        
        <div class="list-header grid-applicant-columns">
            <div>신뢰도</div>
            <div>블로거</div>
            <div>평균 방문자 수</div>
            <div>이웃 수</div>
            <div>스크랩 수</div>
            <div>전체 방문자 수</div>
            <div>채택 3 / 5</div>
        </div>
           
        <c:forEach items="${applicantList.applicantList}" var="applicant">
            <div class="grid-applicant-columns apllicant">
                <div>
                    <c:choose>
	                    <c:when test="${applicant.userInfo.pnltCnt eq 0}">
	                        <img src="/img/pnt_0.png" />
	                    </c:when>
	                    <c:when test="${applicant.userInfo.pnltCnt eq 1}">
	                        <img src="/img/pnt_1.png" />
	                    </c:when>
	                    <c:otherwise>
	                       <img src="/img/pnt_2.png" />
	                    </c:otherwise>
                    </c:choose>
                </div>
                <div class="user">
                    <div class="logId" data-cmpn-apply-id="${applicant.cmpnPstAdptId}">${applicant.userInfo.logId}</div>
                    <div><a href="${applicant.userInfo.blgAddrs}" target="_blank">${applicant.userInfo.blgAddrs}</a></div>
                </div>
                <div>${applicant.userInfo.avrgVstrCnt}</div>
                <div>${applicant.userInfo.blgNghbrCnt}</div>
                <div>${applicant.userInfo.scrpCnt}</div>
                <div>${applicant.userInfo.ttlVstrCnt}</div>
                <c:choose>
                    <c:when test="${applicantList.cmpnState eq '2005'}">
                        <c:choose>
                            <c:when test="${applicant.adptYn eq 'Y'}">
                                <button type="button" name="adopt" class="adopted">채택</button>
                            </c:when>
                            <c:otherwise>
                                <button type="button" name="adopt" class="unadopted">채택</button>
                            </c:otherwise>
                        </c:choose>
                    </c:when>
                    <c:otherwise>
                        <button type="button" name="adopt" class="disabled">채택</button>
                    </c:otherwise>
                </c:choose>
            </div>
        </c:forEach>
	</body>
</html>