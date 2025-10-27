<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<jsp:include page="/WEB-INF/views/layout/menu.jsp">
    <jsp:param name='css' value="
        <link type='text/css' rel='stylesheet' href='/css/campaignmain.css' />
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
	            </div>
	            <div class="cmpn-rightbar-area">
	              <div class="right-bar">
	              
	              <!-- 여기 스티키바 자를부분 -->
	              
	              <div class="right-bar-top">
					<div class="right-bor-top-wrapper">
						<ul class="right-bar-detail-table">
						  <li>
						    <label for="">캠페인 신청기간</label>
						    <div>${detail.rcrtStrtDt } ~ ${detail.pstEndDt }</div>
						  </li>
						  <li>
						    <label for="">선정자 발표일</label>
						    <div>몇일할껀지??</div>
						  </li>
						  <li>
						    <label for="">캠페인 종료일</label>
						    <div>${detail.cmpnEndDt }</div>
						  </li>
						  <li>
						    <label for="">신청</label>
						    <div>${detail.adptCnt } / ${detail.rcrtPrsnn }</div>
						  </li>
						</ul>
					</div>
				  </div>
				  <div class="right-bar-bottom">2</div>
				  <div class="right-bar-bottom">버튼</div>

	              
	              
	              <!-- 여기 스티키바 자를부분 -->
	              
	              </div>
	            </div>
	          </div>
          </div>
        </div>
        
<jsp:include page="/WEB-INF/views/layout/footer.jsp"/>
  

