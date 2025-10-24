<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<link type="text/css" rel="stylesheet" href="/css/brich.css">
<link type="text/css" rel="stylesheet" href="/css/regist.css">
<script type="text/javascript" src="/js/jquery-3.7.1.min.js"></script>
<script type="text/javascript" src="/js/common/login.js"></script>
<script type="text/javascript" src="/js/common/validate.js"></script>
</head>
<body>
    <div class="login-wrapper">
<<<<<<< HEAD
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
    <a href="/campaignmain">비회원으로 둘러보기</a>
=======
	    <div class="login-area">
		    <form method="post" id="login">
		        <div class="login-input-box">
		            <img src="/img/user.png"/>
			        <label for="logId" class="require-login"></label>
			        <input type="text" id="logId" class="login-input" name="logId" placeholder="USERNAME">
		        </div>
		        <div class="login-input-box">
		            <img src="/img/lock.png"/>
			        <label for="password" class="require-login"></label>
			        <input type="password" class="login-input"  id="password" name="pswrd" placeholder="PASSWORD">
		        </div>
		         <button type="button" class="login-button">SIGN IN</button>
		    </form>
		    <a href="/find/id" class="login-page-btn">로그인 정보를 잊으셨나요?</a>
		    <a href="/choose-role" class="login-button">SIGN UP</a>
		    <a href="/campaignmain" class="login-page-btn">비회원으로 둘러보기</a>
    </div>
>>>>>>> aca9deb45842d7c9b0375e6318bc2129a8edab9f
    
    </div>
</body>
</html>