<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<div class="campaign-block">
    <img src="/img/image13.png" />
    <div class="flex-grow-1">
        <div>${param.cmpnTitle}</div>
        <c:choose>
            <c:when test="${param.cmpnCdNm eq '승인'}">
		        <div class="enddate font-green">~${param.cnfmDt}</div>
		        </div>
		        <div class="font-green">${param.cmpnCdNm}</div>
            </c:when>
            
            <c:when test="${param.cmpnCdNm eq '모집중'}">
		        <div class="enddate">~${param.rcrtEndDt}</div>
		        </div>
		        <div class="font-green">${param.cmpnCdNm}</div>
            </c:when>
            
            <c:when test="${param.cmpnCdNm eq '선정중'}">
            </div>
            <div class="font-green">${param.cmpnCdNm}</div>
            </c:when>
            
            <c:when test="${param.cmpnCdNm eq '진행중'}">
                <div class="enddate">~${param.cmpnEndDt}</div>
                </div>
                <div class="font-green">${param.cmpnCdNm}</div>
            </c:when>
            
            <c:when test="${param.cmpnCdNm eq '시작대기' || param.cmpnCdNm eq '검토중'}">
                <div class="enddate">~${param.rcrtStrtDt}</div>
                </div>
                <div class="font-brown">${param.cmpnCdNm}</div>
            </c:when>
            
            <c:when test="${param.cmpnCdNm eq '임시저장'}">
                </div>
                <div class="font-brown">${param.cmpnCdNm}</div>
            </c:when>
            
            <c:when test="${param.cmpnCdNm eq '반려'}">
                </div>
                <div class="font-red">${param.cmpnCdNm}</div>
            </c:when>
            
            <c:when test="${param.cmpnCdNm eq '종료'}">
                <div class="enddate">~${param.cmpnEndDt}</div>
                </div>
                <div>${param.cmpnCdNm}</div>
            </c:when>
        </c:choose>
</div>