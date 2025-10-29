<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<jsp:include page="/WEB-INF/views/layout/menu.jsp">
    <jsp:param name='css' value="
        <link type='text/css' rel='stylesheet' href='/css/campaignmain.css' />
    " />
    <jsp:param name="scripts" value="
        <script type='text/javascript' src='/js/campaign/campaignmain.js'></script>
    " />
</jsp:include>
        <div class="main">
          <div class="campaign-detail-wrapper">
	          <div class="cmpn-top-area">
	            <div class="cmpn-title flex-space-between">
					<div class="cmpn-title-content">
						<c:if test="${not empty detail.parentArea}">[ ${detail.parentArea} ]</c:if> 
						 ${detail.cmpnTitle }
					</div>
					<div class="cmpn-title-love">
						하트
					</div>
				</div>
	            <div class="cmpn-title-offrCn">${detail.offrCn}</div>
	            <div class ="cmpn-title-person flex-space-between">
	              <div>신청 ${detail.adptCnt } / ${detail.rcrtPrsnn }</div>
	              <div>${detail.sttsCd}</div>
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
        
<jsp:include page="/WEB-INF/views/layout/footer.jsp"/>
  

