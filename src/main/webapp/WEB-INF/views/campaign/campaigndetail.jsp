<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:set var="scripts">
       <script type="text/javascript" src="/js/jquery-3.7.1.min.js"></script>
       <script type="text/javascript" src="/js/campaign/applicantAdopt.js"></script>
       <script type="text/javascript" src="/js/common/paginator.js"></script>
       <script type="text/javascript" src="/js/common/validate.js"></script>
</c:set>

<c:set var="css">
       <link type='text/css' rel='stylesheet' href='/css/campaignmain.css' />
       <link type='text/css' rel='stylesheet' href='/css/brich.css' />
</c:set>

<jsp:include page="/WEB-INF/views/layout/menu.jsp">
    <jsp:param name='css' value="${css}" />
        
    <jsp:param name="scripts" value="${scripts}" />
</jsp:include>
        <div class="main">
          <c:if test="${userInfo.usrId eq detail.usrId}">
            <jsp:include page="/WEB-INF/views/campaign/campaignTab.jsp">
                <jsp:param value="${detail.cmpnId}" name="cmpnId"></jsp:param>
                <jsp:param value="${detail.cmpnTitle}" name="cmpnTitle"></jsp:param>
                <jsp:param value="${detail.sttsCd}" name="sttsCd"></jsp:param>
            </jsp:include>
          </c:if>
          <div class="campaign-detail-wrapper">
	          <div class="cmpn-top-area">
	            <div class="cmpn-title flex-space-between">
					<div class="cmpn-title-content">
						<c:if test="${not empty detail.parentArea}">[ ${detail.parentArea} ]</c:if> 
						 ${detail.cmpnTitle }
					</div>
				    <c:if test="${not empty sessionScope.__LOGIN_USER__ }" > 
						<div class="cmpn-title-love">
							하트
    					</div>
					</c:if>
				</div>
	            <div class="cmpn-title-offrCn">${detail.offrCn}</div>
	            <div class ="cmpn-title-person flex-space-between">
	              <div>신청 ${detail.adptCnt } / ${detail.rcrtPrsnn }</div>
	              <c:if test="${not empty detail.pstSttsCd}">
		              <div class ="pst-stts-cd-${detail.pstSttsCd}">${detail.pstSttsCdNm}</div>
	              </c:if>
	            </div>
	          </div>
	          <div class="cmpn-bottom-area">
	            <div class="cmpn-content-area">
		            <div>이미지</div>
					<ul class="campaign-detail-table">
					  <li>
	                    <label for="">설명</label>
	                    <div>${detail.cmpnCn }</div>				  
	                  </li>
					  <li>
	                    <label for="">제공</label>
	                    <div>${detail.offrCn }</div>				  
	                  </li>
					  <li>
	                    <label for="">미션</label>
	                    <div>${detail.pstMssn }</div>				  
	                  </li>
	                  <li>
	                    <label for="">해시 태그</label>
	                    <div>${detail.hstg }</div>
	                  </li>
	                  <li>
	                    <label for="">안내 사항</label>
	                    <div>${detail.ntfcn }</div>
	                  </li>
	                  <li>
	                    <label for="">위치</label>
	                    <div>${detail.addrs }</div>
	                  </li>
					</ul>
                    <div>지도</div>
                    <div>aaaaaaaaaaa</div>
                    <div>aaaaaaaaaaa</div>
                    <div>aaaaaaaaaaa</div>
                    <div>aaaaaaaaaaa</div>
                    <div>aaaaaaaaaaa</div>
                    <div>aaaaaaaaaaa</div>
                    <div>aaaaaaaaaaa</div>
                    <div>aaaaaaaaaaa</div>
                    <div>aaaaaaaaaaa</div>
                    <div>aaaaaaaaaaa</div>
                    <div>aaaaaaaaaaa</div>
                    <div>aaaaaaaaaaa</div>
                    <div>aaaaaaaaaaa</div>
                    <div>aaaaaaaaaaa</div>
                    <div>aaaaaaaaaaa</div>
                    <div>aaaaaaaaaaa</div>
                    <div>aaaaaaaaaaa</div>
	            </div>
	            <div class="cmpn-rightbar-area">
	              <div class="right-bar">
	              
	              <!-- 오른쪽 창 -->
					<jsp:include page="/WEB-INF/views/campaign/campaigndetailrightbar.jsp">
					    <jsp:param name="scripts" value="
					        <script type='text/javascript' src='/js/campaign/campaignmain.js'></script>
					    " />					   
	                </jsp:include>
	              <!-- 오른쪽 창 -->
	              
	              </div>
	            </div>
	          </div>
          </div>
        </div>
        <div class = "submit-modal-area display-none">
            <div class="submit-modal">
            dd
            
            </div>
        </div>
        
<jsp:include page="/WEB-INF/views/layout/footer.jsp"/>
  

