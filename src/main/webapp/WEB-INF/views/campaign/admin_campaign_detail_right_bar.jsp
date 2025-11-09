<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

                <div class="right-bar-top">
                    <div class="right-bor-top-wrapper">
                        <ul class="right-bar-detail-table">
                            <c:if test="${not empty detail.rcrtStrtDt}">
                                <li>
                                    <label for="">캠페인 신청기간</label>
                                    <div>${detail.rcrtStrtDt} ~ ${detail.pstEndDt}</div>
                                </li>
                            </c:if>
                            <c:if test="${not empty detail.rcrtEndDt}">
                                <li>
                                    <label for="">선정자 발표일</label>
                                    <div>${detail.rcrtEndDt}</div>
                                </li>
                            </c:if>
                            <c:if test="${not empty detail.cmpnEndDt}">
                                <li>
                                    <label for="">캠페인 종료일</label>
                                    <div>${detail.cmpnEndDt}</div>
                                </li>
                            </c:if>
                            <c:if test="${not empty detail.rcrtPrsnn}">
                                <li>
                                    <label for="">모집 인원</label>
                                    <div>${detail.rcrtPrsnn}명</div>
                                </li>
                                <li>
                                    <label for="">신청 인원</label>
                                    <div>${detail.adptCnt}명</div>
                                </li>
                            </c:if>
                        </ul>
                    </div>
                </div>
                  
                <c:if test="${param.sttsCd eq 2003}">
                     <div class="deny-reason">
                         <div class="font-red">반려 사유</div>
                         <div class="rtrnRsn">: ${detail.rtrnRsn}</div>
                     </div>
                </c:if>
