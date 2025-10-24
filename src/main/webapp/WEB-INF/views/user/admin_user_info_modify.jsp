<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>회원 관리 - 정보 수정</title>
		<script type="text/javascript" src="/js/jquery-3.7.1.min.js"></script>
		<link type="text/css" rel="stylesheet" href="/css/user/admin_user_detail.css"/>
	</head>
	<body>
	    <div class="wrapper">
           <h1>회원 관리</h1>
           <div class="container">
              <input type="hidden" id="usrId" value="${userInfo.usrId}"/>
              <input type="hidden" id="autr" value="${userInfo.autr}"/>
           <table>
              <c:choose>
                <c:when test="${not empty userInfo}">
                <tbody>
                   <tr>
                      <th>아이디</th>
                      <td>
                        <input type="text" class="modify-values" value="${userInfo.logId}"/>
                      </td>
                   </tr>
                   
                   <tr>
                      <th>이메일</th>
                      <td>
                          <input type="text" class="modify-values" value="${userInfo.eml}"/>
                      </td>
                   </tr>
                   
                   <tr>
                      <th>이름</th>
                      <td>
                          <input type="text" class="modify-values" value="${userInfo.nm}"/>
                      </td>
                   </tr>
                   
                   <tr>
                      <!-- include -->
                      <c:if test="${classType eq 'AdminBloggerDetailVO'}">
                          <jsp:include page="../user/admin_blogger_detail.jsp"></jsp:include>
                      </c:if>
                       
                       <c:if test="${classType eq 'AdminAdvertiserDetailVO'}">
                           <jsp:include page="../user/admin_advertiser_detail.jsp"></jsp:include>
                       </c:if>
                   </tr>
                   <tr>
                      <th>최근 로그인</th>
                      <td>${userInfo.rcntLgnScsDt}</td>
                   </tr>
                   
                   <tr>
                      <th>징계 횟수</th>
                      <td>${userInfo.pnltCnt}</td>
                   </tr>
                   
                   <tr>
                      <th>가입 일시</th>
                      <td>${userInfo.crtDt}</td>
                   </tr>
                   
                   <tr>
                      <th>수정 사유</th>
                      <td>
                          <input type="text" class="modify-values" name="modifyReason" />
                      </td>
                   </tr>
                </tbody>
                </c:when>
              </c:choose>
              </table>
              
              <div class="btn-group">
                  <a href="/admin/user_detail/${usrId}">
                      <button type="button" class="cancel-btn">취소</button>
                  </a>
                  <button type="button" class="done-btn">완료</button>
              </div>
           </div>
       </div>
	</body>
</html>