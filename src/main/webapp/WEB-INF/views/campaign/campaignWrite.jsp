<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<jsp:include page="/WEB-INF/views/layout/menu.jsp">
    <jsp:param name='css' value="
        <link type='text/css' rel='stylesheet' href='/css/brich.css' />
    " />
    <jsp:param name="scripts" value="
        
    " />    
</jsp:include>


<div class="campaign-wrapper">
    <div class="campaign-title">캠페인 만들기</div>
    <div class="write-content">
        <div class="campaign-sub-title">캠페인 제목</div>
        <input type="text" class="text-input" name="cmpnTitle" />
        
        <div class="campaign-sub-title">카테고리</div>
        <div>
            <c:forEach items="${common.categoryList}" var="category">
                <input type="radio" name="category" id="category-${category.cdId}" />
                <label for="category-${category.cdId}" >${category.cdNm}</label>
            </c:forEach>
        </div>
        
        <div class="area">
            <div>
                <span class="campaign-sub-title">지역</span>
                <button type="button">선택</button>
            </div>
            <div>지역 선택 공간</div>
        </div>
        
        <div class="address">
            <span class="campaign-sub-title">주소</span>
            <input type="radio" name="address-check" id="address-check" />
            <label for="address-check">입력</label>
            <input type="radio" name="address-check" id="address-uncheck" checked />
            <label for="address-uncheck">입력 안함</label>
            <br>
            <input type="text" placeholder="도로명 주소" class="text-input" />
            <input type="text" placeholder="상세 주소" class="text-input" />
        </div>

        <div>
            <span class="campaign-sub-title">이미지</span>
            <input type="file" />
        </div>
        
        <div>
            <span class="campaign-sub-title">신청 인원</span>
            <input type="number" />
            <span>가격 : <span>0</span>원</span>
        </div>
        
        <div class="campaign-sub-title">설명</div>
        <textarea class="text-input write-input require-input require-empty"></textarea>
        <div>
            <span class="campaign-sub-title">제공</span>
            <span>금액</span>
            <input type="number" />
            <span>원</span>
        </div>
        <textarea class="text-input write-input require-input require-empty"></textarea>
        <div class="campaign-sub-title">미션</div>
        <textarea class="text-input write-input require-input require-empty"></textarea>
        <div class="campaign-sub-title">해시태그</div>
        <textarea class="text-input write-input require-input require-empty"></textarea>
        <div class="campaign-sub-title">안내사항</div>
        <textarea class="text-input write-input require-input require-empty"></textarea>
    </div>
</div>

<div class="modal">
    <div class="modal-area">
        <div class="grid-item">지역</div>
        <div class="do-city grid-item">
            <c:forEach items="${common.doAndCityList}" var="doCity">
                <input type="radio" name="do-city" id="${doCity.cdId}" />
                <label for="${doCity.cdId}" class="button_120_50">${doCity.cdNm}</label>
            </c:forEach>
        </div>
        
        <div class="city-gu-gun grid-item">
            <div>금천구</div>
        </div>
        
        <div class="grid-item">
            <div>서울 금천구</div>
        </div>
        
        <div class="modal-button-list grid-item">
	        <button type="button" class="modal-close">닫기</button>
	        <button type="button" class="modal-submit deny-submit auto-active">제출</button>
	    </div>
    </div>
</div>

<jsp:include page="/WEB-INF/views/layout/footer.jsp" />