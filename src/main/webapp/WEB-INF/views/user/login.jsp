<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<link type="text/css" rel="stylesheet" href="/css/brich.css">
<script type="text/javascript" src="/js/jquery-3.7.1.min.js"></script>
<script type="text/javascript" src="/js/common/login.js"></script>
</head>
<body>
    <div class="login-wrapper">
    <form method="post" id="login">
         아이디
        <input type="text" name="logId">
        <br>
        비밀번호 
        <input type="password" name="pswrd">
        <button type="button" class="login-button">로그인</button>
    </form>
    <a href="/">로그인 정보를 잊으셨나요?</a>
    <a href="/choose-role">SIGN UP</a>
    <a href="/">비회원으로 둘러보기</a>
    
    </div>
</body>
</html>