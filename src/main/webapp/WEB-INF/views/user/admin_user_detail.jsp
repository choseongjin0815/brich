<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>회원 관리 - 회원 상세 정보</title>
        <script type="text/javascript" src="/js/jquery-3.7.1.min.js"></script>
        <c:if test="${classType eq 'AdminAdvertiserDetailVO' && userInfo.autr eq '1007'}">
            <script type="text/javascript" src="/js/user/admin_advertiser_regist_process.js"></script>
        </c:if>
        <!-- <link type="text/css" rel="stylesheet" href="/css/user/admin_user_detail.css"/>  -->
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
		              <td>${userInfo.logId}</td>
		           </tr>
		           
		           <tr>
		              <th>이메일</th>
                      <td>${userInfo.eml}</td>
                   </tr>
                   
                   <tr>
                      <th>이름</th>
                      <td>${userInfo.nm}</td>
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
		        </tbody>
		        </c:when>
		      </c:choose>
		      </table>
		      
		      <div class="btn-group">
		          
		          <c:if test="${(classType eq 'AdminBloggerDetailVO') || 
		                        (classType eq 'AdminAdvertiserDetailVO' && userInfo.autr eq '1004')}">
		                        
		              <label for="warning-process">경고</label>
                      <input type="radio" id="warning" name="penalty-option" class="option_item"/>
                      
                      <label for="ban-process">정지</label>
                      <input type="radio" id="ban" name="penalty-option" class="option_item"/>
                      
                      <button type="button" class="user-info-update-btn">처리</button>
                      <button type="button" class="user-info-update-btn">수정</button>
                  </c:if>
		      
		          <c:if test="${classType eq 'AdminAdvertiserDetailVO' && userInfo.autr eq '1007'}">
		              <button type="button" class="regist-yn-group-btn" data-action="reject">가입 반려</button>
		              <button type="button" class="regist-yn-group-btn" data-action="approve">가입 승인</button>
		          </c:if>
		      </div>
		   </div>
	   </div>
	</body>
</html>