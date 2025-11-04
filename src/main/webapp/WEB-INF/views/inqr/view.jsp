<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:set var="scripts">
    <script type='text/javascript' src='/js/chat/chat.js'></script>
    <script type='text/javascript' src='/js/report/report.js'></script>
    <script type='text/javascript' src='/js/common/validate.js'></script>
    <script type='text/javascript' src='/js/common/paginator.js'></script>
    <script type='text/javascript' src='/js/help/help.js'></script>
</c:set>
<jsp:include page="/WEB-INF/views/layout/menu.jsp">
    <jsp:param name='chatCss'
        value="
        <link type='text/css' rel='stylesheet' href='/css/chat/chat.css' />
    " />
    <jsp:param name='helpCss'
        value="
        <link type='text/css' rel='stylesheet' href='/css/help/help.css' />
    " />
     <jsp:param name='accountCss' value="
        <link type='text/css' rel='stylesheet' href='/css/account/account.css' />
    " />
    <jsp:param name='reportCss'
        value="
        <link type='text/css' rel='stylesheet' href='/css/report/report.css' />
    " />
    <jsp:param name="scripts" value="${scripts}" />
</jsp:include>
<div class="report-list-wrapper">
    <div class="header-title">HELP</div>
    <div class="campaign-tab">
        <div class="account-menu-box help">
            <div class="account-top-menu inqr-list point">1대1 문의 내역</div>
            <div class="account-top-menu inqr-write">1대1 문의하기</div>
            <div class="account-top-menu faq-list">자주 찾는 도움말</div>
        </div>
    </div>
</div>
</body>
</html>