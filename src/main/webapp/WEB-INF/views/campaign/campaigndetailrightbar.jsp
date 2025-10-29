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
                  <div class="right-bar-bottom">2</div>
                  
                  <!-- 신청 버튼 영역 -->
                  <c:if test="${sessionScope.__LOGIN_USER__ ne null 
			               and (sessionScope.__LOGIN_USER__.autr == 1002 
			                or  sessionScope.__LOGIN_USER__.autr == 1003)}">
                    
                        <div class="right-bar-bottom apply-blg" data-campaign-id="${detail.cmpnId }">신청하기</div>
                        <div class="right-bar-bottom apply-cancel-blg display-none" data-campaign-id="${detail.cmpnId }">신청취소</div>
                  </c:if>