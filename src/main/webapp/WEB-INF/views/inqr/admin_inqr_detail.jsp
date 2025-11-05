<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
	<html>
	<head>
		<meta charset="UTF-8">
		<title>문의 관리 - 상세 정보</title>
		<script type="text/javascript" src="/js/jquery-3.7.1.min.js"></script>
		<script type="text/javascript" src="/js/inqr/admin_ansr_regist_process.js"></script>
        <link type="text/css" rel="stylesheet" href="/css/admin/admin_detail.css"/>
	</head>
	<body>
        <jsp:include page="/WEB-INF/views/layout/menu.jsp">
           <jsp:param name='css' value="
        <link type='text/css' rel='stylesheet' href='/css/campaignmain.css' />
            " />
       </jsp:include>
       
        <div class="wrapper">
        <h1>문의 상세</h1>
	        <div class="container">
	            
	            <input type="hidden" id="login_usrId" value="${sessionScope.__LOGIN_USER__.usrId}"/>
	            <input type="hidden" id="inqrId" value="${inqrInfo.inqrVO.inqrId}" />
	            <input type="hidden" id="inqrUsrId" value="${inqrInfo.inqrVO.inqrUsrId}" />
	            <input type="hidden" id="inqrFlGrpId" value="${inqrInfo.inqrVO.inqrFlGrpId}"/>
	            <input type="hidden" id="ansrFlGrpId" value="${inqrInfo.inqrVO.ansrFlGrpId}"/>
	            
	            <table>
	                <tbody>
	                    <tr>
	                        <th>문의 회원</th>
	                        <td id="inqrUsrId">${inqrInfo.logId}</td>
	                    </tr>
	                    
	                    <tr>
	                        <th>문의 제목</th>
	                        <td id="inqrTitle">${inqrInfo.inqrVO.inqrTitle}</td>
	                    </tr>
	                    
	                    <tr>
	                        <th>문의 카테고리</th>
	                        <td id="inqrCtg">${inqrInfo.cdNm}</td>
	                    </tr>
	                    
	                    <tr>
	                        <th>문의 일시</th>
	                        <td id="crtDt">${inqrInfo.inqrVO.crtDt}</td>
	                    </tr>
	                    
	                    <tr>
	                        <th>문의 내용</th>
	                        <td id="inqrCn">${inqrInfo.inqrVO.inqrCn}</td>
	                    </tr>
	                    
	                    <tr>
	                        <th>첨부 파일</th>
	                        <td id="inqrFlGrpId">
	                        
	                            <c:set var="hasValidInqrFile" value="false" />
	                            
	                            <%-- 파일 첨부 유/무 체크 --%>
	                            <c:if test="${not empty inqrInfo.inqrFileList}">
	                                <c:forEach items="${inqrInfo.inqrFileList}" var="checkInqrFileList">
	                                    <c:if test="${not empty checkInqrFileList.flId}">
	                                        <c:set var="hasValidInqrFile" value="true" />
	                                    </c:if>
	                                </c:forEach>
	                            </c:if>
	                            
	                            <c:choose>
	                                <c:when test="${hasValidInqrFile}">
	                                    <%-- 유효한 파일이 존재할 경우: 링크 출력 --%>
	                                    <c:forEach items="${inqrInfo.inqrFileList}" var="inqrFileList">
	                                        <c:if test="${not empty inqrFileList.flId}">
	                                            <a href="/file/${inqrInfo.inqrVO.inqrUsrId}/${inqrInfo.inqrVO.inqrFlGrpId}/${inqrFileList.flId}">
	                                                &#128196;${inqrFileList.flNm} 
	                                            </a>
	                                        </c:if>
	                                    </c:forEach>
	                                </c:when>
	                                
	                                <%-- 유효한 파일이 없을 경우 --%>
	                                <c:otherwise>
	                                    -
	                                </c:otherwise>
	                            </c:choose>
	                        </td>
	                    </tr>
	                    
	                    <tr>
	                        <th>답변 여부</th>
	                        <td id="ansrYn">${inqrInfo.ansrYn}</td>
	                    </tr>
	                </tbody>
	            </table>
	            
	            <div>답변</div>
	            <c:choose>
	                <c:when test="${inqrInfo.ansrYn eq 'Y'}">
	                    <div class="ansr-area ansr-y">
	                        <div>
	                           <div id="ansrCn">
	                               ${inqrInfo.inqrVO.ansrCn}
	                           </div>
	                        </div>
	                        
	                        <c:set var="hasVaildAnsrFile" value="false" />
	                        
	                        <%-- 파일 유/무 체크 --%>
	                        <c:if test="${not empty inqrInfo.ansrFileList}">
	                            <c:forEach items="${inqrInfo.ansrFileList}" var="checkAnsrFileList">
	                                <c:if test="${not empty checkAnsrFileList.flId}">
                                        <c:set var="hasVaildAnsrFile" value="true" />
                                    </c:if>
                                </c:forEach>
	                        </c:if>
	                        
	                        <%-- 유효한 파일이 있을 경우 출력 --%>
	                        <c:if test="${hasVaildAnsrFile}">
	                            <div id="ansrFlGrpId">
	                                <c:forEach items="${inqrInfo.ansrFileList}" var="ansrFileList">
	                                    <c:if test="${not empty ansrFileList.flId}">
	                                        <a href="/file/${inqrInfo.inqrVO.ansrUsrId}/${inqrInfo.inqrVO.ansrFlGrpId}/${ansrFileList.flId}">
	                                            &#128196;${ansrFileList.flNm}
	                                        </a>
	                                    </c:if>
	                                </c:forEach>
	                            </div>
                            </c:if>
	                        <div id="updtDt">${inqrInfo.ansrDt}</div>
	                    </div>
	                </c:when>
	                
	                <c:otherwise>
	                    <div class="ansr-area ansr-n">
	                        <div>
	                            <textarea id="ansr_input" placeholder="답변 내용을 입력하세요."></textarea>
	                        </div>
	                        
	                        <div id="newAddedFileList"></div> 
                            
                            <button type="button" id="addNewFileBtn" class="add-new-file-btn">첨부 파일 +</button>
                            
                            <div id="fileInputList" style="display: none;"></div>
	                        
	                        <div class="button-area">
	                            <button type="button" class="save-btn">등록</button>
	                        </div>
	                    </div>
	                </c:otherwise>
	            </c:choose>
		    </div>
		</div>
	</body>
</html>