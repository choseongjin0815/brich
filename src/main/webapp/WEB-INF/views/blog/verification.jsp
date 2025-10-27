<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<jsp:include page="/WEB-INF/views/layout/menu.jsp">
    <jsp:param name='css' value="
       	<link type='text/css' rel='stylesheet' href='/css/campaignmain.css' />
    	<link type='text/css' rel='stylesheet' href='/css/blog/verification.css' />
       	
   	" />
   	<jsp:param name="scripts" value="
       	
	" />    
</jsp:include>
		<div class="">블로그 인증</div>
		<div class="verification-container">
			<div>
			블로그가 등록되지 않았어요! 블로그 등록을 진행해주세요.
			</div>
			<button> 등록하기</button>
			
		</div>
	</body>
</html>