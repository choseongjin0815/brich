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
		<script type="text/javascript" src="/js/campaign/applicantAdopt.js"></script>
	</head>
	<body>
	    <jsp:include page="/WEB-INF/views/campaign/campaignTab.jsp">
	       <jsp:param value="${adoptList.campaignInfo.cmpnTitle}" name="cmpnTitle"/>
	    </jsp:include>
	    <div class="title">
            <div>캠페인 신청자</div>
            <span>~${adoptList.campaignInfo.cmpnEndDt}</span>
        </div>
        
        <div class="id-search">
           <input type="text" placeholder="ID를 입력하세요." />
           <img src="/img/Search.png" />
        </div>
        
        <div class="list-header grid-list-header list-adopt">
            <div>블로거</div>
            <div>포스팅</div>
            <div>상태
                <img class="sort desc" data-sort-type="PST_STTS_CD" src="/img/arrow-bottom.png" />
            </div>
            <div>마감일</div>
            <div>1:1 채팅</div>
            <div>신고</div>
        </div>
        
        <c:forEach items="${adoptList.adoptList}" var="adopt">
            <div class="grid-list-header list-adopt apllicant">
                <div class="user">
                    <div class="logId" data-cmpn-apply-id="${adopt.cmpnPstAdptId}">${adopt.userInfo.logId}</div>
                    <div><a href="${adopt.userInfo.blgAddrs}" target="_blank">${adopt.userInfo.blgAddrs}</a></div>
                </div>
                <c:choose>
                    <c:when test="${empty adopt.pstUrl}">
                        <div><a class="button_50_30 disabled button_a_50_30" href="">보기</a></div>
                    </c:when>
                    <c:otherwise>
                        <div><a class="button_50_30 abled button_a_50_30"  href="">보기</a></div>
                    </c:otherwise>
                </c:choose>
                
                <c:choose>
                    <c:when test="${adopt.cdNm eq '제출전'}">
                        <button type="button" class="button_50_30 submit-before">${adopt.cdNm}</button>
                    </c:when>
                    <c:when test="${adopt.cdNm eq '검토중'}">
                        <button type="button" class="button_50_30 submit-check">${adopt.cdNm}</button>
                    </c:when>
                    <c:when test="${adopt.cdNm eq '반려'}">
                        <button type="button" class="button_50_30 submit-deny">${adopt.cdNm}</button>
                    </c:when>
                    <c:when test="${adopt.cdNm eq '승인'}">
                        <button type="button" class="button_50_30 submit-approve">${adopt.cdNm}</button>
                    </c:when>
                </c:choose>
                
                <div>${adopt.pstDdln}</div>
                <div><a class="button_50_30 abled button_a_50_30" href="">채팅</a></div>
                <div><a class="button_50_30 button-report button_a_50_30" href="">신고</a></div>
                
            </div>
        </c:forEach>
	</body>
</html>