<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
	<html>
		<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<link type="text/css" rel="stylesheet" href="/css/brich.css" />
	</head>
	<body>
	    <div class="campaign-tab">
	        <input type="radio" name="tab" id="campaign-name" checked>
	        <label class="campaign-tab-name" for="campaign-name">${param.cmpnTitle}</label>
	        <input type="radio" name="tab" id="campaign-applicant">
	        <label for="campaign-applicant">신청자</label>
	        <input type="radio" name="tab" id="campaign-return-hist">
	        <label for="campaign-return-hist">반려 기록</label>
	    </div>
	</body>
</html>