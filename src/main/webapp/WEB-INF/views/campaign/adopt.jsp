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
		<script type="text/javascript" src="/js/common/paginator.js"></script>
	</head>
	<body>
	    <div class="campaign-wrapper">
		    <jsp:include page="/WEB-INF/views/campaign/campaignTab.jsp">
		       <jsp:param value="${adoptList.campaignInfo.cmpnTitle}" name="cmpnTitle"/>
		       <jsp:param value="${adoptList.campaignInfo.sttsCd}" name="sttsCd"/>
		    </jsp:include>
		    <div class="campaign-title">
	            <div>캠페인 신청자</div>
	            <span class="enddate">~${adoptList.campaignInfo.cmpnEndDt}</span>
	        </div>
	        
	        <div class="id-search">
	           <input type="text" placeholder="ID를 입력하세요." />
	           <img src="/img/Search.png" />
	        </div>
	        
	        <div class="list-container">
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
		            <div class="grid-list-header list-adopt applicant">
		                <div class="user">
		                    <div class="logId" data-cmpn-apply-id="${adopt.cmpnPstAdptId}">${adopt.userInfo.logId}</div>
		                    <div><a href="${adopt.userInfo.blgAddrs}" target="_blank">${adopt.userInfo.blgAddrs}</a></div>
		                </div>
		                <c:choose>
		                    <c:when test="${empty adopt.pstUrl}">
		                        <button class="button_50_30 disabled">보기</button>
		                    </c:when>
		                    <c:otherwise>
		                        <button class="button_50_30 abled view-post" data-post-url="${adopt.pstUrl}">보기</button>
		                    </c:otherwise>
		                </c:choose>
		                
		                <c:choose>
		                    <c:when test="${adopt.pstSttsCd eq '6001'}">
		                        <button type="button" name="postState" class="button_50_30 submit-before">${adopt.postCdNm}</button>
		                    </c:when>
		                    <c:when test="${adopt.pstSttsCd eq '6002'}">
		                        <button type="button" name="postState" class="button_50_30 submit-check">${adopt.postCdNm}</button>
		                    </c:when>
		                    <c:when test="${adopt.pstSttsCd eq '6003'}">
		                        <button type="button" name="postState" class="button_50_30 submit-deny">${adopt.postCdNm}</button>
		                    </c:when>
		                    <c:when test="${adopt.pstSttsCd eq '6004'}">
		                        <button type="button" name="postState" class="button_50_30 submit-approve">${adopt.postCdNm}</button>
		                    </c:when>
                        </c:choose>
		                <div>${adopt.pstDdln}</div>
		                <div><a class="button_50_30 abled button_a_50_30" href="">채팅</a></div>
		                <div><a class="button_50_30 button-report button_a_50_30" href="">신고</a></div>
		                
		            </div>
		        </c:forEach>
		        <jsp:include page="/WEB-INF/views/layout/paginator.jsp">
		            <jsp:param value="${search.listSize}" name="listSize"/>
		            <jsp:param value="${search.havePrevPageGroup}" name="havePrevPageGroup"/>
		            <jsp:param value="${search.prevGroupStartPageNo}" name="prevGroupStartPageNo"/>
		            <jsp:param value="${search.groupStartPageNo}" name="groupStartPageNo"/>
		            <jsp:param value="${search.groupEndPageNo}" name="groupEndPageNo"/>
		            <jsp:param value="${search.pageNo}" name="pageNo"/>
		            <jsp:param value="${search.haveNextPageGroup}" name="haveNextPageGroup"/>
		            <jsp:param value="${search.nextGroupStartPageNo}" name="nextGroupStartPageNo"/>
		            <jsp:param value="${search.pageCount}" name="pageCount"/>
		        </jsp:include>
	        </div>
	        
	        <div class="modal">
	             <div class="modal-post">
		            <jsp:include page="/WEB-INF/views/campaign/campaignBlock.jsp">
		              <jsp:param value="${adoptList.campaignInfo.cmpnTitle}" name="cmpnTitle" />
		              <jsp:param value="${adoptList.cmpnCdNm}" name="cmpnCdNm" />
		              <jsp:param value="${adoptList.campaignInfo.cnfmDt}" name="cnfmDt" />
		              <jsp:param value="${adoptList.campaignInfo.rcrtStrtDt}" name="rcrtStrtDt" />
		              <jsp:param value="${adoptList.campaignInfo.rcrtEndDt}" name="rcrtEndDt" />
		              <jsp:param value="${adoptList.campaignInfo.pstEndDt}" name="pstEndDt" />
		              <jsp:param value="${adoptList.campaignInfo.cmpnEndDt}" name="cmpnEndDt" />
		            </jsp:include>
		            
		            <div>포스팅 보기</div>
		            <div class="post-url">포스팅 URL : </div>
				    <div class="button-list">
				    </div>
		            
		            <div class="deny-container">
                        <textarea name="reason" placeholder="반려 사유를 입력하세요."></textarea>
                        <div>
                            <button type="button" class="add-file">첨부파일 +</button>
                        </div>
                        <div class="modal-button-list">
                            <button type="button" class="modal-close">닫기</button>
                            <button type="button" class="modal-submit">제출</button>
                        </div>
                    </div>
                    <button type="button" class="modal-close close-mark">X</button>
	            </div>
	        </div>
        </div>
	</body>
</html>