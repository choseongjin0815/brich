<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>회원 관리 - 정보 수정</title>
		<script type="text/javascript" src="/js/jquery-3.7.1.min.js"></script>
		<script type="text/javascript" src="/js/user/admin_user_info_modify.js"></script>
		<link type="text/css" rel="stylesheet" href="/css/user/admin_user_detail.css"/>
	</head>
	<body>
	    <jsp:include page="../layout/menu.jsp">
           <jsp:param name='css' value="
        <link type='text/css' rel='stylesheet' href='/css/campaignmain.css' />
            " />
       </jsp:include>
	    <div class="wrapper">
           <h1>회원 관리</h1>
           
           <div class="container">
              <input type="hidden" id="login_usrId" value="${sessionScope.__LOGIN_USER__.usrId}"/>
			   <input type="hidden" id="usrId" value="${userInfo.usrId}"/>
			   <input type="hidden" id="autr" value="${userInfo.autr}"/>
			   <input type="hidden" id="flGrpId" value="${userInfo.flGrpId}"/>
           <table>
              <c:choose>
                <c:when test="${not empty userInfo}">
                <tbody>
                   <tr>
                      <th>아이디</th>
                      <td>
                        <input type="text" id="logId" name="logId" class="modify-values" value="${userInfo.logId}"/>
                      </td>
                   </tr>
                   
                   <tr>
                      <th>이메일</th>
                      <td>
                          <input type="text" id="eml" name="eml" class="modify-values" value="${userInfo.eml}"/>
                      </td>
                   </tr>
                   
                   <tr>
                      <th>이름</th>
                      <td>
                          <input type="text" id="nm" name="nm" class="modify-values" value="${userInfo.nm}"/>
                      </td>
                   </tr>
                   
                   <c:if test="${classType eq 'AdminAdvertiserDetailVO'}">
                   <tr>
                      <th>상호명</th>
                      <td>
                          <c:choose>
                              <c:when test="${not empty pathInfo && fn:contains(pathInfo, '/admin/user_modify') }">
                                  <input type="text" id="cmpny" name="cmpny" class="modify-values" value="${userInfo.cmpny}" />
                              </c:when>
                              
                              <c:when test="${not empty userInfo.cmpny}">
                                  ${userInfo.cmpny}
                              </c:when>
                              
                              <c:otherwise>
                                  -
                              </c:otherwise>
                          </c:choose>
                      </td>
				   </tr>
                   </c:if>
                   
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
                          <input type="text" id="updtRsn" name="cmpny" class="modify-values"/>
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
                  <button type="button" class="save-btn">완료</button>
              </div>
           </div>
       </div>
	</body>
</html>