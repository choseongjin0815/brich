<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<tr>
    <th>상호명</th>
    <td>${userInfo.cmpny}</td>
</tr>

<tr>
    <th>사업자 등록증</th>
    <td>
        <c:choose>
		    <c:when test="${not empty userInfo.flGrpId}">
		        
		    </c:when>
		    
		    <c:otherwise>
		      -
		    </c:otherwise>
		</c:choose>
    </td>
</tr>


<tr>
    <th>가입 승인</th>
    <td>${userInfo.registAcpt}</td>
</tr>

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
</tr>>

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

