<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>회원 관리 - 상세 정보</title>
        <script type="text/javascript" src="/js/jquery-3.7.1.min.js"></script>
        <script type="text/javascript" src="/js/user/admin_user_penalty_process.js"></script>
        <c:if test="${classType eq 'AdminAdvertiserDetailVO' && userInfo.autr eq '1007'}">
            <script type="text/javascript" src="/js/user/admin_advertiser_regist_process.js"></script>
        </c:if>
        <link type="text/css" rel="stylesheet" href="/css/admin/admin_detail.css"/>
    </head>
    <body>
        <jsp:include page="/WEB-INF/views/layout/menu.jsp">
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
		      <c:if test="${classType eq 'AdminBloggerDetailVO'}">
		          <input type="hidden" id="rcntBlgCrtfctnDt" value="${userInfo.rcntBlgCrtfctnDt}"/>
		      </c:if>
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
                   
                   <c:if test="${classType eq 'AdminAdvertiserDetailVO'}">
                   <tr>
                      <th>상호명</th>
                      <td>
                          <c:choose>
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
		                        
		              <label for="warning">경고</label>
                      <input type="radio" name="penalty-option" class="option_item" value="warning"/>
                      
                      <label for="ban">정지</label>
                      <input type="radio" name="penalty-option" class="option_item" value="ban"/>
                      
                      <button class="penalty-btn">처리</button>
                      
                      <a href="/admin/user_modify/${usrId}">
                          <button class="modify-btn">수정</button>
                      </a>
                  </c:if>
		      
		          <c:if test="${classType eq 'AdminAdvertiserDetailVO' && userInfo.autr eq '1007'}">
		              <button class="regist-yn-group-btn" data-action="reject">가입 반려</button>
		              <button class="regist-yn-group-btn" data-action="approve">가입 승인</button>
		          </c:if>
		      </div>
		   </div>
	   </div>
	</body>
</html>