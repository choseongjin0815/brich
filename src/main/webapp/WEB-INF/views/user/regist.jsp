<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<link type="text/css" rel="stylesheet" href="/css/brich.css">
<script type="text/javascript" src="/js/jquery-3.7.1.min.js"></script>
<script type="text/javascript" src="/js/user/regist.js"></script>
</head>
<body>
    <div class="regist-wrapper">
        <div class="regist-header">
            Brich
        </div>
        <div class="regist-main">
            <c:if test="${role eq 'advertiser' }">
	            <form method="post" action="/regist" class="user-regist-form">
	                <div class="right-flex">
		                <div class="input-flex short"> 
			                <label for="id">아이디</label>
			                <input type="text" id="id" name="logId" placeholder="아이디를 입력해주세요"/>
		                </div>
		                <div class="regist-side-btn"><div>중복 확인</div></div>
	                </div>
	                <div class="input-flex">
		                <label for="name">이름</label>   
		                <input type="text" id="name" name="nm" placeholder="이름을 입력해주세요"/>
	                </div>
	                <div class="input-flex">
		                <label for="company">사업자명</label>
		                <input type="text" id="company" name="cmpny" placeholder="사업자명을 입력해주세요"/>
	                </div>
	                <div>사업자 등록증</div>
	                <div class="right-flex">
		                <div class="input-flex short">
			                <label for="email">이메일</label>
			                <input type="text" id="email" name="eml" placeholder="이메일을 입력해주세요"/>
		                </div>
		                <div class="regist-side-btn"><div>인증 번호</div></div>
	                </div>
	                <div class="right-flex">
		                <div class="input-flex short">
		                   <label for="email-confirm">인증번호 입력</label>
		                   <input type="text" id="email-confirm" placeholder="인증번호를 입력하세요"/>
		                </div>
		                <div class="regist-side-btn"><div>인증 확인</div></div>
	                </div>
	                <div class="input-flex">
	                    <label for="password">비밀번호</label>
		                <input type="password" id="password" name="pswrd" placeholder="8~16자리 비밀번호 입력"/>
		                <input type="password" id="password-confirm" placeholder="비밀번호 확인"/> 
	                </div>
	                <input type="hidden" name="autr" value="1004"/>
	                <div class="regist-btn">회원가입</div>
	            </form>
            </c:if>
            <c:if test="${role eq 'blogger'}">
                <form method="post" action="/regist" class="user-regist-form">
                    <div class="right-flex">
                        <div class="input-flex short"> 
                            <label for="id">아이디</label>
                            <input type="text" id="id" placeholder="아이디를 입력해주세요"/>
                        </div>
                        <div class="regist-side-btn"><div>중복 확인</div></div>
                    </div>
                    <div class="input-flex">
                        <label for="name">이름</label>   
                        <input type="text" id="name" placeholder="이름을 입력해주세요"/>
                    </div>
                    <div class="right-flex">
                        <div class="input-flex short">
                            <label for="email">이메일</label>
                            <input type="text" name="eml" id="email" placeholder="이메일을 입력해주세요"/>
                        </div>
                            <div class="regist-side-btn"><div>인증 번호</div>
                        </div>
                    </div>
                    <div class="right-flex">
                        <div class="input-flex short">
                           <label for="email-confirm">인증번호 입력</label>
                           <input type="text" id="email-confirm" placeholder="인증번호를 입력하세요"/>
                        </div>
                        <div class="regist-side-btn"><div>인증 확인</div></div>
                    </div>
                    <div class="input-flex">
                        <label for="password">비밀번호</label>
                        <input type="password" id="password" placeholder="8~16자리 비밀번호 입력"/>
                        <input type="password" id="password-confirm" placeholder="비밀번호 확인"/> 
                    </div>
                    <div class="input-flex">
                        <label>카테고리</label>
                        <div>
                            <input type="checkbox"/>맛집
                            <input type="checkbox"/>여행
                            <input type="checkbox"/>레저
                            <input type="checkbox"/>뷰티
                        </div>
                    </div>
                    <div class="input-flex">
                        <label>지역</label>
                    </div>
                    <input type="hidden" name="autr" value="1002"/>
                    
                    <div class="regist-btn">회원가입</div>
                </form>   
            </c:if>
        </div>
    </div>
</body>
</html>