<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>구독 정보</title>
</head>
<body>
<c:if test="${sessionScope.__LOGIN_USER__.autr eq '1004' }">
    광고주
</c:if>
</body>
</html>