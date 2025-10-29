<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>회원 관리 - 리스트</title>
		<script type="text/javascript" src="/js/jquery-3.7.1.min.js"></script>
		<script type="text/javascript" src="/js/user/admin_user_list.js"></script>
		<link type="text/css" rel="stylesheet" href="/css/admin/admin_list.css"/>
	</head>
	<body>
	   <jsp:include page="/WEB-INF/views/layout/menu.jsp">
	       <jsp:param name='css' value="
        <link type='text/css' rel='stylesheet' href='/css/campaignmain.css' />
            " />
	   </jsp:include>
	   <div class="wrapper">
		   <h1>회원 관리</h1>
		      <input type="hidden" id="current-tab" value="${currentTab}"/>
		   
            <div class="tab">
			    <label for="all-user">전체</label>
			    <input type="radio" id="all" data-tab-value="all" name="admin-user-tab" class="tab-item"
			        <c:if test="${currentTab == 'all' || empty currentTab}">checked="checked"</c:if>
			    />
	    
			    <label for="blogger">블로거</label>
			    <input type="radio" id="blogger" data-tab-value="blogger" name="admin-user-tab" class="tab-item"
			        <c:if test="${currentTab == 'blogger'}">checked="checked"</c:if>
			    />
			    <label for="advertiser">광고주</label>
			    <input type="radio" id="advertiser" data-tab-value="advertiser" name="admin-user-tab" class="tab-item"
			        <c:if test="${currentTab == 'advertiser'}">checked="checked"</c:if>
		    />
			</div>
		   <div class="container">
           
		       <!-- 전체 회원 -->
		       <table id="all-tbl" class="list-tbl">
		           <colgroup>
		               <col/>
		               <col/>
		               <col/>
		               <col/>
		               <col/>
		               <col/>
		               <col/>
		               <col/>
		               <col/>
		               <col/>
		               <col/>
		           </colgroup>
		           <thead>
		               <tr>
		                   <th>아이디</th>
		                   <th>이름</th>
		                   <th>현재 진행 중인 캠페인</th>
		                   <th>총 진행한 캠페인</th>
		                   <th>가입 승인</th>
		                   <th>블로그 주소</th>
		                   <th>최근 블로그 인증일</th>
		                   <th>구독 만료일</th>
		                   <th>최근 로그인</th>
		                   <th>징계 횟수</th>
		                   <th>가입 일시</th>
		               </tr>
		           </thead>
		           
		           <tbody>
		           <c:choose>
		               <c:when test="${not empty userList}">
		                   <c:forEach items="${userList}" var="items">
		                       <tr>
		                           <td>
		                               <a href="/admin/user_detail/${items.usrId}">${items.logId}</a>
		                           </td>
		                           <td>${items.nm}</td>
		                           <td>${items.cmpnPrgrssCnt}</td>
		                           <td>${items.cmpnAllCnt}</td>
		                           
		                           <td>${items.registAcpt}</td>
		                           
		                           <td>${empty items.blgAddrs ? '-' : items.blgAddrs}</td>
		                           <td>${empty items.rcntBlgCrtfctnDt ? '-' : items.rcntBlgCrtfctnDt}</td>
		                           <td>${empty items.sbscrptnExprsDt ? '-' : items.sbscrptnExprsDt}</td>
		                           
		                           <td>${items.rcntLgnScsDt}</td>
		                           <td>${items.pnltCnt}</td>
		                           <td>${items.crtDt}</td>
	                           </tr>
		                   </c:forEach>
		               </c:when>
		           </c:choose>    
		           </tbody>
		       </table>
		       
		       <!-- 블로거 -->
		       <table id="blogger-tbl" class="list-tbl">
		           <colgroup>
	                   <col/>
	                   <col/>
	                   <col/>
	                   <col/>
	                   <col/>
	                   <col/>
	                   <col/>
	                   <col/>
	                   <col/>
	                   <col/>
	               </colgroup>
	               <thead>
	                   <tr>
	                       <th>아이디</th>
	                       <th>이름</th>
	                       <th>블로그 주소</th>
	                       <th>최근 블로그 인증일</th>
	                       <th>구독 만료일</th>
	                       <th>현재 진행 중인 캠페인</th>
	                       <th>총 진행한 캠페인</th>
	                       <th>최근 로그인</th>
	                       <th>징계 횟수</th>
	                       <th>가입 일시</th>
	                   </tr>
	               </thead>
	               
	               <tbody>
	               <c:choose>
	                   <c:when test="${not empty userList}">
	                       <c:forEach items="${userList}" var="items">
	                       <c:if test="${items.autr == '1002' || items.autr == '1003'}">
	                            <tr>
	                                <c:set var="isAdvertiser" value="false" scope="request"/>
	                                <c:set var="isBlogger" value="true" scope="request"/>
	                                <c:set var="rowItem" value="${items}" scope="request"/>
	                                <jsp:include page="../user/admin_user_list_row.jsp"></jsp:include>
	                            </tr>
	                        </c:if>
	                        </c:forEach>
	                   </c:when>
	               </c:choose>
	               </tbody>
		       </table>
		       
		       <!-- 광고주 -->
		       <table id="advertiser-tbl" class="list-tbl">
		           <colgroup>
	                   <col/>
	                   <col/>
	                   <col/>
	                   <col/>
	                   <col/>
	                   <col/>
	                   <col/>
	                   <col/>
	               </colgroup>
	               <thead>
	                   <tr>
	                       <th>아이디</th>
	                       <th>이름</th>
	                       <th>가입 승인</th>
	                       <th>현재 진행 중인 캠페인</th>
	                       <th>총 진행한 캠페인</th>
	                       <th>최근 로그인</th>
	                       <th>징계 횟수</th>
	                       <th>가입 일시</th>
	                   </tr>
	               </thead>
	               
	               <tbody>
	               <c:choose>
	                   <c:when test="${not empty userList}">
	                       <c:forEach items="${userList}" var="items">
	                       <c:if test="${items.autr == '1004' || items.autr == '1007'}">
	                            <tr>
	                                <c:set var="isBlogger" value="false" scope="request"/>
	                                <c:set var="isAdvertiser" value="true" scope="request"/>
	                                <c:set var="rowItem" value="${items}" scope="request"/>
	                                <jsp:include page="../user/admin_user_list_row.jsp"></jsp:include>
	                            </tr>
	                       </c:if>
	                       </c:forEach>
	                   </c:when>
	               </c:choose>
	               </tbody>
		       </table>
		       
		   </div>
	   </div>
	</body>
</html>