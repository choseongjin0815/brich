<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!-- 이거 빼 말어... ㅠ -->
<c:if test="${not empty pathInfo && fn:contains(pathInfo, '/admin/user_modify')}">
    <script type="text/javascript" src="/js/user/admin_user_info_modify.js"></script>
</c:if>

<tr>
	<th>진행 중인 캠페인 수</th>
	<td>${userInfo.cmpnProgressCnt}</td>
</tr>

<tr>
    <th>진행 중인 캠페인</th>
    <td>
        <c:choose>
            <c:when test="${not empty userInfo.cmpnProgressList}">
                <c:forEach items="${userInfo.cmpnProgressList}" var="campaginInfo">
                    <a href="/">[${campaginInfo.cmpnTitle}]</a>
                </c:forEach>
            </c:when>
            
            <c:otherwise>
              -
            </c:otherwise>
        </c:choose>
    </td>
</tr>

<tr>
	<th>완료한 캠페인 수</th>
	<td>${userInfo.cmpnCompletedCnt}</td>
</tr>

<tr>
	<th>완료한 캠페인</th>
	<td>
	   <c:choose>
	       <c:when test="${not empty userInfo.cmpnCompletedList}">
			    <c:forEach items="${userInfo.cmpnCompletedList}" var="campaginInfo">
			        <a href="/">[${campaginInfo.cmpnTitle}]</a>
			    </c:forEach>
		   </c:when>
		   
		   <c:otherwise>
		      -
		   </c:otherwise>
	   </c:choose>
	</td>
</tr>

<tr>
	<th>구독 만료일</th>
	<td>${userInfo.sbscrptnExprsDt}</td>
</tr>

<tr>
	<th>블로그 주소</th>
	<td>
	    <!-- 수동 인증 시에만 수정 가능하게, 조건 수정 필요함 -->
	    <c:choose>
	        <c:when test="${not empty pathInfo && fn:contains(pathInfo, '/admin/user_modify') }">
	            <input type="text" class="modify-values" name="blogAddress" 
	                               value="${userInfo.blgAddrs}"
	                               readonly="readonly"/>
	        </c:when>
	        
	        <c:otherwise>
	            ${userInfo.blgAddrs}
	            <button type="button" class="blog_crtfctn">수동 인증</button>
	        </c:otherwise>
	    </c:choose>
	</td>
</tr>

<tr>
	<th>최근 인증일</th>
	<td>${userInfo.rcntBlgCrtfctnDt}</td>
</tr>

<tr>
	<th>활동 지역</th>
	<td>
	   <c:choose>
	       <c:when test="${not empty pathInfo && fn:contains(pathInfo, '/admin/user_modify') }">
               <!-- 석진 모달 끝나면 가져와서 수정 필요 -->
           </c:when>
           
	       <c:when test="${not empty userInfo.usrAr}">
			    <c:forEach items="${userInfo.usrAr}" var="bloggerInfo">
			        <a href="/">[${bloggerInfo.cdNm}]</a>
			    </c:forEach>
		   </c:when>
		   
		   <c:otherwise>
		      -
		   </c:otherwise>
	   </c:choose>
	</td>
</tr>

<tr>
	<th>블로그 카테고리</th>
	<td>
	   <c:choose>
	       <c:when test="${not empty pathInfo && fn:contains(pathInfo, '/admin/user_modify') }">
               <c:forEach items="${BlogcategoryList}" var="categoryInfo">
                   <input type="checkbox" class="modify-values category-checkbox" name="blogCategory" 
                          value="${categoryInfo.cdId}" 
                       <c:if test="${userInfo.checkedBlgCtg.contains(categoryInfo.cdId)}">
                           checked="checked"
                       </c:if>
                   />${categoryInfo.cdNm}
               </c:forEach>
           </c:when>
           
	       <c:when test="${not empty userInfo.usrBlgCtg}">
			    <c:forEach items="${userInfo.usrBlgCtg}" var="bloggerInfo">
			        <a href="/">[${bloggerInfo.cdNm}]</a>
			    </c:forEach>
		   </c:when>
		   
		   <c:otherwise>
		      -
		   </c:otherwise>
	   </c:choose>
	</td>
</tr>

<tr>
	<th>평균 방문자 수</th>
	<td>${userInfo.avrgVstrCnt}</td>
</tr>

<tr>
	<th>이웃 수</th>
	<td>${userInfo.blgNghbrCnt}</td>
</tr>

<tr>
	<th>스크랩 수</th>
	<td>${userInfo.scrpCnt}</td>
</tr>