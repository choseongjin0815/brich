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
                                    <label for="">신청</label>
                                    <div>${detail.adptCnt} / ${detail.rcrtPrsnn}</div>
                                </li>
                            </c:if>
                        </ul>
                    </div>
                </div>
                  
                <c:if test="${param.sttsCd eq 2003}">
                     <div class="deny-reason">
                         <div class="font-red">반려 사유</div>
                         <div>${param.rtrnRsn}</div>
                     </div>
                     <div class="middle-center">
                         <button type="button" class="button_200_30 button-campaign-modify">수정</button>
                     </div>
                </c:if>
