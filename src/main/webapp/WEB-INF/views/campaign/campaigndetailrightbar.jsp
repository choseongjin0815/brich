<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

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
                  <div class="right-bar-bottom"> 여기는 통계 영역입니다 </div>
                  
                  
                  <!-- 신청 버튼 영역 -->
                  <c:if test="${sessionScope.__LOGIN_USER__ ne null 
			               and (sessionScope.__LOGIN_USER__.autr == 1002 
			                or  sessionScope.__LOGIN_USER__.autr == 1003)
			               and detail.pstSttsCd eq null
			                }">
			            <c:set var="isApplied" value="${detail.adptYn eq 'Y'}" />
                        <div class="right-bar-bottom apply-cancel-blg middle-center ${isApplied ? 'display-none' : ''}" data-campaign-id="${detail.cmpnId}">
                        신청취소
                        </div>                        
                        <div class="right-bar-bottom apply-blg middle-center ${isApplied ? '' : 'display-none'}" data-campaign-id="${detail.cmpnId}">
                        신청하기
                        </div>
                  </c:if>
                  <c:if test="${sessionScope.__LOGIN_USER__ eq null}" >
                        <a class="right-bar-bottom required-login middle-center" href="/login">로그인 후 이용해주세요</a>
                  </c:if>
                  <c:if test="${sessionScope.__LOGIN_USER__ ne null 
                           and (sessionScope.__LOGIN_USER__.autr == 1002 
                            or  sessionScope.__LOGIN_USER__.autr == 1003)
                           and detail.pstSttsCd ne null
                            }">
                            <!-- 제출전 -->
                            <c:if test="${detail.pstSttsCd == 6001}" >
		                        <div class="right-bar-bottom middle-center status--draft status--" data-campaign-id="${detail.cmpnId}">
		                          포스팅 제출하기
		                        </div>
                            </c:if>                            
                            <!-- 검토중 -->
                            <c:if test="${detail.pstSttsCd == 6002}" >
		                        <div class="right-bar-bottom middle-center status--review status--" data-campaign-id="${detail.cmpnId}">
		                          검토중
		                        </div>
                            </c:if>      
                            
                            <!-- 반려됨 -->                      
                            <c:if test="${detail.pstSttsCd == 6003}" >
		                        <div class="right-bar-bottom middle-center status--rejected status--" data-campaign-id="${detail.cmpnId}">
		                          다시 제출하기
		                        </div>
                            </c:if>      
                            
                            <!-- 승인됨 -->                      
                            <c:if test="${detail.pstSttsCd == 6004}" >
		                        <div class="right-bar-bottom middle-center status--approved status--" data-campaign-id="${detail.cmpnId}">
		                          승인 완료
		                        </div>
                            </c:if>
                            
                            <!-- 종료됨 -->                      
                            <c:if test="${detail.sttsCd == 2009}" >
		                        <div class="right-bar-bottom middle-center status--end status--" data-campaign-id="${detail.cmpnId}">
		                          종료됨
		                        </div>
                            </c:if>
                  </c:if>
                  
                  
                  
                  
                  
                  
                  
                  
                  