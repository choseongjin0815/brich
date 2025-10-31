<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<jsp:include page="/WEB-INF/views/layout/menu.jsp">
    <jsp:param name='css' value="
        <link type='text/css' rel='stylesheet' href='/css/brich.css' />
    " />
     <jsp:param name='accountCss' value="
        <link type='text/css' rel='stylesheet' href='/css/account/account.css' />
    " />
    <jsp:param name="accountJs"
        value="
        <script type='text/javascript' src='/js/account/account.js'></script>
    " />
</jsp:include>

<div class="campaign-tab">
    <div class="account-menu-box" data-auth="${sessionScope.__LOGIN_USER__.autr}">
        <div class="account-top-menu account-info">계정 정보</div>
        <div class="account-top-menu reset-password point">비밀번호 재설정</div>
        <c:if test="${sessionScope.__LOGIN_USER__.autr ne 1004}">
                <div class="account-top-menu sub-info">구독 정보</div>
        </c:if>
    </div>
</div>
</div>
</div>
</body>
</html>