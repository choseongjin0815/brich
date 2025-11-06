<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<div class="campaign-tab">
    <c:choose>
        <c:when test="${not empty param.sttsCd}" >
            <input type="radio" name="campaign-tab" id="campaign-adopt">
            <label for="campaign-adopt">캠페인</label>
        </c:when>
        
        <c:when test="${not empty param.sttsCd && 
                       (param.sttsCd eq '2005' || 
                        param.sttsCd eq '2006' || 
                        param.sttsCd eq '2007' || 
                        param.sttsCd eq '2009')}" >
            <input type="radio" name="campaign-tab" id="campaign-applicant">
            <label for="campaign-applicant">신청자</label>
            
            <input type="radio" name="campaign-tab" id="campaign-return-hist">
            <label for="campaign-return-hist">채택자</label>
        </c:when>
        
        <c:otherwise>
        
        </c:otherwise>
    </c:choose>
</div>