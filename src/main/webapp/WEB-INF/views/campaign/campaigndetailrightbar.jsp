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
                  <div class="right-bar-bottom">버튼</div>