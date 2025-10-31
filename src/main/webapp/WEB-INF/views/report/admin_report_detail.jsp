<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>신고 관리 - 상세 정보</title>
		<script type="text/javascript" src="/js/jquery-3.7.1.min.js"></script>
		<script type="text/javascript" src="/js/report/admin_report_penalty_process.js"></script>
		<link type="text/css" rel="stylesheet" href="/css/admin/admin_detail.css"/>
	</head>
	<body>
	    <jsp:include page="/WEB-INF/views/layout/menu.jsp">
           <jsp:param name='css' value="
        <link type='text/css' rel='stylesheet' href='/css/campaignmain.css' />
            " />
        </jsp:include>
        
        <div class="wrapper">
            <h1>신고 관리</h1>
            <div class="container">
            <input type="hidden" id="adminId" value="${sessionScope.__LOGIN_USER__.usrId}"/>
            <input type="hidden" id="rptId" value="${reportInfo.rptId}"/>
            <input type="hidden" id="rptrUsrId" value="${reportInfo.rptrUsrId}"/>
            <input type="hidden" id="rptedUsrId" value="${reportInfo.rptedUsrId}"/>
               <table>
                    <tbody>
                        <tr>
                            <th>신고한 회원</th>
                            <td>${reportInfo.rptrLogId}</td>
                        </tr>
                        
                        <tr>
                            <th>신고 대상</th>
                            <td>${reportInfo.rptedLogId}</td>
                        </tr>
                        
                        <tr>
                            <th>신고 제목</th>
                            <td>${reportInfo.rptTitle}</td>
                        </tr>
                        
                        <tr>
                            <th>신고 일시</th>
                            <td>${reportInfo.crtDt}</td>
                        </tr>
                        
                        <tr>
                            <th>신고 사유</th>
                            <td>${reportInfo.cdNm}</td>
                        </tr>
                        
                        <tr>
                            <th>신고 내용</th>
                            <td>${reportInfo.rptCn}</td>
                        </tr>
                        
                        <tr>
                            <th>첨부 파일</th>
                            <td>
                            <c:set var="hasValidReportFile" value="false"/>
                            <c:if test="${not empty reportInfo.fileList}">
		                        <c:forEach items="${reportInfo.fileList}" var="checkReportFileList">
			                        <c:if test="${not empty checkReportFileList.flId}">
			                            <c:set var="hasValidReportFile" value="true"/>
			                        </c:if>
		                        </c:forEach>
		                    </c:if>
		                    
		                    <c:choose>
		                        <c:when test="${hasValidReportFile}">
		                            <c:forEach items="${reportInfo.fileList}" var="reportFileList">
		                                <c:if test="${not empty reportFileList.flId}">
		                                    <a href="/file/${reportInfo.rptrUsrId}/${reportInfo.rptFlGrpId}/${reportFileList.flId}">
		                                        &#128196;${fileList.flNm} 
		                                    </a>
		                                </c:if>
		                            </c:forEach>
		                        </c:when>
		                        
		                        <c:otherwise>
		                            -
		                        </c:otherwise>
		                    </c:choose>
                            </td>
                        </tr>
                        
                        <tr>
                            <th>처리 여부</th>
                            <td>${reportInfo.prcnYn}</td>
                        </tr>
                        
                        <c:if test="${reportInfo.prcnYn eq 'Y'}">
                        <tr>
                            <th>처리 일시</th>
                            <td>${reportInfo.prcnDt}</td>
                        </tr>
                        </c:if>
                    </tbody>
                </table>
                
                <c:if test="${reportInfo.prcnYn eq 'N'}">
                <div class="btn-group">
                    <label for="dismiss">기각</label>
                    <input type="radio" name="penalty-option" class="option_item" value="dismiss"/>
                    
                    <label for="warning">경고</label>
                    <input type="radio" name="penalty-option" class="option_item" value="warning"/>
                    
                    <label for="ban">정지</label>
                    <input type="radio" name="penalty-option" class="option_item" value="ban"/>
                    
                    <button type="button" class="penalty-btn">처리</button>
                </div>
                </c:if>
            </div>
        </div>
	</body>
</html>