<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 찾기</title>
<link type="text/css" rel="stylesheet" href="/css/brich.css">
<link type="text/css" rel="stylesheet" href="/css/regist.css">
</head>
<body>
<div class="regist-wrapper find-wrapper">
        <div class="find-header">
            Brich
        </div>
        <div class="find-id-main regist-main">
            <form class="user-regist-form">
                    <div class="right-flex">
                        <div class="input-flex"> 
                            <label for="id" class="require">본인확인 이메일로 인증</label>
                            <input type="text" id="id" name="logId" class="logId" value="${registData.logId}" placeholder="아이디를 입력해주세요"/>
                        </div>

                    </div>
                    <div class="right-flex">
                        <div class="input-flex">
                            <label for="email" class="require" >이메일</label>
                            <input type="text" id="email" name="eml" value="${registData.eml}" placeholder="이메일을 입력해주세요"/>
                        </div>
                        <div class="regist-side-btn"><div>인증 번호</div></div>
                    </div>
                    <div class="right-flex">
                        <div class="input-flex">
                           <label for="email-confirm">인증번호 입력</label>
                           <input type="text" id="email-confirm" placeholder="인증번호를 입력하세요"/>
                        </div>
                        <div class="regist-side-btn"><div>인증 확인</div></div>   
                    </div>                 
                </form>
        </div>
    </div>
</body>
</html>