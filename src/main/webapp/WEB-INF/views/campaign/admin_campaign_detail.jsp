<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:set var="scripts">
    <script type="text/javascript" src="/js/jquery-3.7.1.min.js"></script>
    <script type="text/javascript" src="/js/campaign/admin_campaign_detail.js"></script>
    <script type="text/javascript" src="/js/common/paginator.js"></script>
</c:set>

<c:set var="css">
    <link type='text/css' rel='stylesheet' href='/css/campaignmain.css' />
    <link type='text/css' rel='stylesheet' href='/css/admin/admin_campaign_detail.css' />
    <link type='text/css' rel='stylesheet' href='/css/brich.css' />
</c:set>

<jsp:include page="/WEB-INF/views/layout/menu.jsp">
    <jsp:param name='css' value="${css}"/>
    <jsp:param name="scripts" value="${scripts}"/>
</jsp:include>

        <div class="main">
            <jsp:include page="/WEB-INF/views/campaign/admin_campaign_detail_tab.jsp">
                <jsp:param value="${detail.cmpnId}" name="cmpnId"></jsp:param>
                <jsp:param value="${detail.cmpnTitle}" name="cmpnTitle"></jsp:param>
                <jsp:param value="${detail.sttsCd}" name="sttsCd"></jsp:param>
            </jsp:include>
	        <div class="campaign-detail-wrapper">
                <div class="cmpn-top-area">
                    <div class="cmpn-title flex-space-between">
                        <div class="cmpn-title-content">
                            <c:if test="${not empty detail.parentArea}">
                                [${detail.parentArea}]
                            </c:if> 
                            ${detail.cmpnTitle}
                            
                            <c:choose>
	                            <c:when test="${detail.sttsCdNm eq '승인' || detail.sttsCdNm eq '모집중'
	                                          || detail.sttsCdNm eq '선정중' || detail.sttsCdNm eq '진행중'}">
	                                <span class="font-green">${detail.sttsCdNm}</span>
	                            </c:when>
	                            
	                            <c:when test="${detail.sttsCdNm eq '시작대기' || detail.sttsCdNm eq '검토중' 
	                                                                         || detail.sttsCdNm eq '임시저장'}">
	                                <span class="font-brown">${detail.sttsCdNm}</span>
	                            </c:when>
	                            
	                            <c:when test="${detail.sttsCdNm eq '반려'}">
	                                <span class="font-red">${detail.sttsCdNm}</span>
	                            </c:when>
	                            
	                            <c:when test="${detail.sttsCdNm eq '종료'}">
	                                <span class="font-gray">${detail.sttsCdNm}</span>
	                            </c:when>
                            </c:choose>
                        </div>
                    </div>
	                <div class="cmpn-title-offrCn">${detail.offrCn}</div>
                </div>
                <div class="cmpn-bottom-area">
                    <div class="cmpn-content-area">
                        <ul class="campaign-detail-table">
                            <li>
                                <c:if test="${not empty detail.fileVoList}">
                                <div class="cmpn-images">
                                    <c:forEach var="f" items="${detail.fileVoList}" varStatus="st">
                                    <div class="cmpn-image">
                                        <div class="path">/file/1234/${detail.flGrpId}/${f.flId}</div>
                                   <!-- <img src="/file/1234/${detail.flGrpId}/${f.flId}"/> -->  
                                        <img class="cmpn-detail-image" 
                                             src="/file/1234/FG-20251104-000155/FL-20251104-000279"/>
                                    </div>
                                    </c:forEach>
                                </div>
                                </c:if>
                            </li>
                            <li>
		                        <label for="">설명</label>
		                        <div>${detail.cmpnCn}</div>                  
	                        </li>
		                    <li>
		                        <label for="">제공</label>
		                        <div>${detail.offrCn}</div>                  
		                    </li>
		                    <li>
		                        <label for="">미션</label>
		                        <div>${detail.pstMssn}</div>                 
		                    </li>
		                    <li>
		                        <label for="">해시 태그</label>
		                        <div>${detail.hstg}</div>
		                    </li>
		                    <li>
		                        <label for="">안내 사항</label>
		                        <div>${detail.ntfcn}</div>
		                    </li>
		                    <li>
		                        <label for="">위치</label>
		                        <div>${detail.addrs}</div>
		                    </li>
		                    <li>
		                        <label>지도</label>
		                        <div id="map" style="width:500px;height:400px;" data-location="${detail.addrs }"></div>
		                        <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=75d2ae10ae5482a8868edf8364c88dad&libraries=services"></script>
		                        <script type="text/javascript" src="/js/common/map.js"></script>
		                    </li>
		                    <li>
                                <label for="">등록자</label>
                                <div>${detail.logId}</div>
                            </li>
                            <li>
                                <label for="">결제 정보</label>
                                <div>
		                            <c:choose>
		                                <c:when test="${detail.cmpnPymntId != null}">
		                                <details>
		                                    <summary></summary>
		                                    <div>결제 일시: ${detail.pymntCrtDt}</div>
		                                    
                                            <div>주문 번호: ${detail.orderId}</div>
                                            
                                            <div>결제 일시: ${detail.pymntCrtDt}</div>
                                            
                                            <div>인원당 가격: ${detail.cmpnPrsnnPrc}</div>
                                            
                                            <div>인원 할인율: ${detail.prsnnDscnt}</div>
                                            
                                            <div>기간당 가격: ${detail.cmpnDrtnPrc}</div>
                                            
                                            <div>기간 할인율: ${detail.drtnDscnt}</div>
                                            
                                            <div>총 결제 금액: ${detail.amnt}</div>
                                        </details>
		                                </c:when>
		                                
		                                <c:otherwise>
		                                    미결제
		                                </c:otherwise>
		                            </c:choose>
                                </div>
                            </li>
                        </ul>
                    </div>
                    
                    <div class="cmpn-rightbar-area">
                        <div class="right-bar" style="width: 355px;">
                  
                            <!-- 오른쪽 창 -->
                            <jsp:include page="/WEB-INF/views/campaign/admin_campaign_detail_right_bar.jsp">
                                <jsp:param name="scripts" value="
                                    <script type='text/javascript' src='/js/campaign/campaignmain.js'></script>
                                " />
                                <jsp:param value="${detail.usrId}" name="usrId" />
                                <jsp:param value="${detail.sttsCd}" name="sttsCd" />
                                <jsp:param value="${detail.rtrnRsn}" name="rtrnRsn" />
                            </jsp:include>
                        </div>
                    </div>
                </div>
            </div>
            
            <div class="btn-area">
            <c:choose>
                <c:when test="${detail.sttsCd == '2001'}">
		            <button type="button" class="campaign-yn-btn-group reject-btn" data-action="reject">반려</button>
                      <button type="button" class="campaign-yn-btn-group approve-btn" data-action="approve">승인</button>
                </c:when>
                
                <c:when test="${detail.sttsCd == '2002' || detail.sttsCd == '2004'}">
                    <button type="button" class="modify-btn">수정</button>
                </c:when>
                
                <c:otherwise>
                </c:otherwise>
            </c:choose>
            </div>
        </div>
        
        <!-- 캠페인 반려 모달 -->
        <div id="rejectModal" class="reject-modal-area" style="display: none;">
		    <div class="reject-modalcontent-area">
		        <div class="reject-modal-title-area">
		            <h4 class="reject-modal-title">반려 사유를 작성해 주세요.</h4>
		        </div>
		        
		        <div class="reject-modal-input-area">
		            <input type="text" class="reject-modal-input" placeholder="반려 사유 입력"/>
		        </div>
		        
		        <div class="reject-modal modal-btn-area-one">
		            <button type="button" class="reject-modal-close-btn">닫기</button>
		            <button type="button" class="reject-modal-next-btn">다음</button>
		        </div>
		        
		        <div class="reject-modal modal-btn-area-two">
                    <button type="button" class="reject-modal-back-btn">다시 작성</button>
                    <button type="button" class="reject-modal-save-btn">확인</button>
                </div>
		    </div>
		</div>
        
<jsp:include page="/WEB-INF/views/layout/footer.jsp"/>
