<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c" uri="jakarta.tags.core" %>

<jsp:include page="/WEB-INF/views/layout/menu.jsp">
    <jsp:param name='css' value="
        <link type='text/css' rel='stylesheet' href='/css/campaignmain.css' />
    " />
</jsp:include>
        <div class="main">
          <div class="campaign-detail-wrapper">
          
          <!-- 자를부분 -->
          
			<jsp:include page="/WEB-INF/views/campaign/campaignmy.jsp">
			   <jsp:param name="campaignMyName" value="신청한 캠페인"/>
			</jsp:include>
			
          <!-- 자를부분 -->
          
                <div class="campaign-main-list-area">
                     <c:forEach items="${campaignList}" var="campaignList">
                        <a class="campaign-main-block" href="/campaigndetail/${campaignList.cmpnId}">
                            <div class="campaign-thumbnail">${campaignList.attchGrpId}</div>
                            <div class="campaign-one-title padding-10px"> 
                              <c:if test="${not empty campaignList.parentArea}">[ ${campaignList.parentArea} ]</c:if>  
                              ${campaignList.cmpnTitle}</div>
                            <div class="campaign-one-offrcn padding-10px">${campaignList.offrCn}</div>
                            <div class="campaign-one-adptcnt padding-10px flex-row flex-space-between">
                                <div>신청 ${campaignList.adptCnt } / ${campaignList.rcrtPrsnn }</div>
                                <div class="campaign-now-status-${campaignList.sttsCd}">
                                     <c:if test="${campaignList.sttsCd eq 2005}">모집중</c:if>
                                     <c:if test="${campaignList.sttsCd eq 2006}">선정중</c:if>
                                </div>
                            </div>
                        </a>
                     </c:forEach>
                </div>
                
                
                                    
            </div>
          </div>
        </div>
        
<jsp:include page="/WEB-INF/views/layout/footer.jsp"/>
  


