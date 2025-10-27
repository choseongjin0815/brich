<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c" uri="jakarta.tags.core" %>

<jsp:include page="/WEB-INF/views/layout/menu.jsp">
    <jsp:param name='css' value="
        <link type='text/css' rel='stylesheet' href='/css/campaignmain.css' />
    " />
</jsp:include>
        <div class="main">
          <div class="campaign-detail-wrapper">
              <div class="cmpn-top-area">
                <div class="cmpn-title flex-space-between">
                    <div class="cmpn-title-content">신청한 캠페인</div>
                </div>
              </div>
              <div class="cmpn-bottom-area">
                <div class="cmpn-profile-area flex-row flex-space-between text-align height-center"> 
                    <div class="px24 profile-start">${sessionScope.__LOGIN_USER__.logId}</div>
                    <div class="profile-middle">
				      <c:choose>
				         <c:when test = "${sessionScope.__LOGIN_USER__.autr == 1002}">
				            <div class="green-24px  ">멤버쉽 ON</div>
				         </c:when>
				         <c:when test = "${sessionScope.__LOGIN_USER__.autr == 1003}">
				            <div class="red-24px ">멤버쉽 OFF</div>
				         </c:when>
				      </c:choose>
				    </div>
                    <div class="profile-end">블로그이름 들어갈 자리</div>
                </div>
               
              </div>
          </div>
        </div>
        
<jsp:include page="/WEB-INF/views/layout/footer.jsp"/>
  


