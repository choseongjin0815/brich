<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이용약관</title>
<link type="text/css" rel="stylesheet" href="/css/brich.css">
<script type="text/javascript" src="/js/jquery-3.7.1.min.js"></script>
<script type="text/javascript" src="/js/user/regist.js"></script>
</head>
<body>
	<div class="regist-wrapper">
		<div class="regist-header">Brich</div>
		<c:choose>
			<c:when test="${role eq 'blogger'}">
				<div class= "regist-main">
					<div class="term-check">
					    <div class="term-check-input">
						    <label for="term">이용약관 </label> 
						    <input type="checkbox" id="term" name="check"/>
						</div>
							<div class="term-content">
						
							~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
								<br>~~~~~~~~~~~~~~~~~~~~~~~~~~~~
								 <br>~~~~~~~~~~~~~~~~~~ 
								 <br>~~~~~~~~~~~ ~~~~~
								<br>~~~~~~~~~~~
								    <br>~~~~~~~~~~~
								        <br>~~~~~~~~~~~
								            <br>~~~~~~~~~~~
						    </div>
					    </div>
					</div>
					<br>
					<div class="personal-check">
					    <div class="term-check-input"> 
							<label for="personal">개인정보 수집 및 이용 </label> 
							<input type="checkbox" id="personal" name="check"/>
						</div>
						<div class="term-content">
							~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ <br>~~~~~~~~~~~~~~~~~~~~~~~~~~~~
							~~~~~~~~~~~~~~~~~~<br> ~~~~~~~~~~~
							 <br>~~~~~~~~~~~
							     <br>~~~~~~~~~~~</div>
					</div>
					<br>
					<div class="term-check-all term-check-input">
						<label for="check-all" >전체 동의 </label> 
						<input type="checkbox" id="check-all" />
					</div>
					<div class="next-btn" data-role="${role}">다음</div>
				</div>
			</c:when>
			<c:when test="${role eq 'advertiser'}">
                <div class="regist-main">
                    <div class="term-check">
                        <div class="term-check-input">
                            <label for="term">광고주 이용약관 </label> 
                            <input type="checkbox" id="term" name="check"/>
                        </div>
                         <div class="term-content">
                        
                            ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                                <br>~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                                 <br>~~~~~~~~~~~~~~~~~~ 
                                 <br>~~~~~~~~~~~ ~~~~~
                                <br>~~~~~~~~~~~
                                    <br>~~~~~~~~~~~
                                        <br>~~~~~~~~~~~
                                            <br>~~~~~~~~~~~
                         </div>
                    </div>
                    </div>
                    <br>
                    <div class="personal-check">
                        <div class="term-check-input"> 
                            <label for="personal">개인정보 수집 및 이용 </label> 
                            <input type="checkbox" id="personal" name="check"/>
                        </div>
                        <div class="term-content">
                            ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ <br>~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                            ~~~~~~~~~~~~~~~~~~<br> ~~~~~~~~~~~
                             <br>~~~~~~~~~~~
                                 <br>~~~~~~~~~~~</div>
                    </div>
                    <br>
                    <div class="term-check-all term-check-input">
                        <label for="check-all" >전체 동의 </label> 
                        <input type="checkbox" id="check-all" />
                    </div>
                    <div class="next-btn"  data-role="${role}">다음</div>
                </div>
			</c:when>
		</c:choose>
	</div>
</body>
</html>