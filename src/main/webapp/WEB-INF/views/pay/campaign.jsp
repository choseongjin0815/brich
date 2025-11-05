<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<jsp:include page="/WEB-INF/views/layout/menu.jsp">
  <jsp:param name='css' value="
        <link type='text/css' rel='stylesheet' href='/css/campaignmain.css' />
    " />
  <jsp:param name="scripts" value="
        <script type='text/javascript' src='/js/campaign/campaignmain.js'></script>
    " />
</jsp:include>

<div class="main flex-column side-padding">
   dddd
</div>


<jsp:include page="/WEB-INF/views/layout/footer.jsp"/>
