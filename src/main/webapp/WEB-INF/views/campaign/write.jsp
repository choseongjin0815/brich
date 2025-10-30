<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="scripts">
    <script type="text/javascript" src="/js/campaign/write.js"></script>
    <script type="text/javascript" src="/js/common/validate.js"></script>
</c:set>
<jsp:include page="/WEB-INF/views/layout/menu.jsp">
    <jsp:param name='css' value="
        <link type='text/css' rel='stylesheet' href='/css/brich.css' />
    " />
    <jsp:param name="scripts" value="${scripts}" />
</jsp:include>

<div class="campaign-wrapper">
    <div class="campaign-title">캠페인 만들기</div>
    <div class="write-content">
        <form:form modelAttribute="RequestCreateCmpnVO"
                   method="post"
                   action="/adv/campaign/write"
                   enctype="multipart/form-data"
                   id="campaign-submit">
            <div class="campaign-sub-title">캠페인 제목</div>
	        <input type="text" class="text-input" name="cmpnTitle" />
	        
	        <div class="campaign-sub-title">카테고리</div>
	        <div>
	            <c:forEach items="${common.categoryList}" var="category">
	                <input type="radio" name="category" value="${category.cdId}" />${category.cdNm}
	            </c:forEach>
	        </div>
	        
	        <div class="area">
	            <div>
	                <span class="campaign-sub-title">지역</span>
	                <button type="button">선택</button>
	            </div>
	            <div class="area-list"></div>
	            <div class="hidden-area-list"></div>
	        </div>
	        
	        <div class="address">
	            <span class="campaign-sub-title">주소</span>
	            <input type="radio" name="address-check" id="address-check" />
	            <label for="address-check">입력</label>
	            <input type="radio" name="address-check" id="address-uncheck" checked />
	            <label for="address-uncheck">입력 안함</label>
	            <br>
	            <input type="text" placeholder="도로명 주소" name="loadAddress" class="text-input" readonly disabled="disabled" />
	            <input type="text" placeholder="상세 주소" name="detailAddress" class="text-input" disabled="disabled" />
	        </div>
	
	        <div>
	            <span class="campaign-sub-title">이미지</span>
	            <input type="file" name="file" />
	        </div>
	        
	        <div>
	            <span class="campaign-sub-title">모집 인원</span>
	            <input type="number" name="rcrtPrsnn" data-person-price="${common.personPrice}" value=0 />
	            <span>예상 인원 가격 : <span class="rcrtPrsnn-price">0</span>원</span>
	        </div>
	        
	        <div class="campaign-sub-title">설명</div>
	        <textarea class="text-input write-input require-input require-empty" name="cmpnCn"></textarea>
	        <div>
	            <span class="campaign-sub-title">제공</span>
	            <span>금액</span>
	            <input type="number" name="offrPrc" value=0 />
	            <span>원</span>
	        </div>
	        <textarea class="text-input write-input require-input require-empty" name="offrCn"></textarea>
	        <div class="campaign-sub-title">미션</div>
	        <textarea class="text-input write-input require-input require-empty" name="pstMssn"></textarea>
	        <div class="campaign-sub-title">해시태그</div>
	        <textarea class="text-input write-input require-input require-empty" name="hstg"></textarea>
	        <div class="campaign-sub-title">안내사항</div>
	        <textarea class="text-input write-input require-input require-empty" name="ntfcn"></textarea>
	        
	        <div class="button-list grid-item">
                <button type="button" class="">미리보기</button>
                <button type="button" class="">임시저장</button>
                <button type="button" class="cancel-button">취소</button>
                <button type="button" class="submit-button auto-active">신청</button>
            </div>
	    </form:form>
	</div>
</div>

<div class="modal">
    <div class="modal-area">
        <div class="grid-item">지역</div>
        <div class="do-city grid-item">
            <c:forEach items="${common.doAndCityList}" var="doCity">
                <input type="radio" name="do-city" id="${doCity.cdId}" data-do-name="${doCity.cdNm}" />
                <label for="${doCity.cdId}" class="button_120_50">${doCity.cdNm}</label>
            </c:forEach>
        </div>
        
        <div class="city-gu-gun grid-item">
        </div>
        
        <div class="checked-cities grid-item">
        </div>
        
        <div class="modal-button-list grid-item">
	        <button type="button" class="modal-close">닫기</button>
	        <button type="button" class="modal-submit deny-submit auto-active">선택</button>
	    </div>
    </div>
</div>

<jsp:include page="/WEB-INF/views/layout/footer.jsp" />