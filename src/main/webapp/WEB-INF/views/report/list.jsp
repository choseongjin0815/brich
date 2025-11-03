<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:set var="scripts">
    <script type='text/javascript' src='/js/chat/chat.js'></script>
    <script type='text/javascript' src='/js/report/report.js'></script>
    <script type='text/javascript' src='/js/common/validate.js'></script>
</c:set>
<jsp:include page="/WEB-INF/views/layout/menu.jsp">
    <jsp:param name='chatCss'
        value="
        <link type='text/css' rel='stylesheet' href='/css/chat/chat.css' />
    " />
    <jsp:param name='reportCss'
        value="
        <link type='text/css' rel='stylesheet' href='/css/report/report.css' />
    " />
    <jsp:param name="scripts" value="${scripts}" />
</jsp:include>


</div>
</div>
</body>
</html>