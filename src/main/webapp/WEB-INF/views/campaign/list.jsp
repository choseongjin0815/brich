<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<jsp:include page="/WEB-INF/views/layout/menu.jsp">
    <jsp:param name='css' value="
        <link type='text/css' rel='stylesheet' href='/css/brich.css' />
    " />
</jsp:include>

<div class="campaign-wrapper">
    <div class="campaign-content-wrapper">
        <div class="campaign-title">내 캠페인</div>
        <div class="id-search">
           <input type="text" placeholder="ID를 입력하세요." />
           <img src="/img/Search.png" />
        </div>
        
        <div class="campaign-list-content">
            <c:forEach items="${campaignList.responseCampaignList}" var="campaign">
                <jsp:include page="/WEB-INF/views/campaign/campaignBlock.jsp">
                    <jsp:param value="${campaign.cmpnId}" name="cmpnId"/>
                    <jsp:param value="${campaign.cmpnTitle}" name="cmpnTitle"/>
                    <jsp:param value="${campaign.sttsCdNm}" name="cmpnCdNm"/>
                    <jsp:param value="${campaign.cnfmDt}" name="cnfmDt"/>
                    <jsp:param value="${campaign.rcrtEndDt}" name="rcrtEndDt"/>
                    <jsp:param value="${campaign.cmpnEndDt}" name="cmpnEndDt"/>
                    <jsp:param value="${campaign.rcrtStrtDt}" name="rcrtStrtDt"/>
                </jsp:include>
            </c:forEach>
        </div>
    </div>
</div>