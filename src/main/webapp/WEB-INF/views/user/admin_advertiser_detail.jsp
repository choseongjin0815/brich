<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<c:if test="${not empty pathInfo && fn:contains(pathInfo, '/admin/user_modify')}">
    <script type="text/javascript" src="/js/user/admin_user_info_modify.js"></script>
</c:if>
<input type="hidden" id="login_usrId" value="${sessionScope.__LOGIN_USER__.usrId}"/>
<input type="hidden" id="usrId" value="${userInfo.usrId}"/>
<input type="hidden" id="autr" value="${userInfo.autr}"/>
<input type="hidden" id="flGrpId" value="${userInfo.flGrpId}"/>
<tr>
    <th>사업자 등록증</th>
    <td>
        <c:choose>
            <%-- 수정 페이지 --%>
            <c:when test="${not empty pathInfo && fn:contains(pathInfo, '/admin/user_modify') }">
                
                <%-- 파일 선택 영역 --%>
                <div id="existFileList">
                    <c:set var="vaildFileCount" value="0"/>
	                <c:forEach items="${userInfo.fileGroupVO.file}" var="fileList">
	                
	                   <%-- 기존 파일이 있다면, (flId가 있다면) --%>
	                   <c:if test="${not empty fileList.flId}">
	                   
	                       <%-- 기존에 첨부되어 있던 파일들 --%>
	                       <span class="file-item">
		                       ${fileList.flNm} 
		                       
			                   <%-- 기존에 첨부되어 있던 파일 삭제 버튼 --%>
			                   <button type="button" class="file-remove-btn" data-file-id="${fileList.flId}">X</button>
			                   
			                   <%-- 유지시킬 기존 파일 ID 값 --%>
			                   <input type="hidden" name="existFileIds" value="${fileList.flId}" />
		                   </span>
		                   <c:set var="vaildFileCount" value="${vaildFileCount + 1}"/>
	                   </c:if>
	                </c:forEach>
	                
	                
	                <%-- 새로 첨부되는 파일 영역 --%>
	                <div id="newAddedFileList"></div>
	                
	                <%-- 새 파일 첨부 버튼 --%>
	                <button type="button" id="addNewFileBtn" class="add-new-file-btn">첨부 파일 +</button>
	                
	                <%-- 새 파일 첨부 버튼으로 추가되는 첨부 파일을 담아주는 영역 --%>
	                <div id="fileInputList" style="display: none;"></div>
                </div>
            </c:when>
        
            <%-- 상세 정보 페이지 --%>
		    <c:when test="${not empty userInfo.fileGroupVO && not empty userInfo.fileGroupVO.file}">
		        <c:set var="vaildFileCount" value="0"/>
		        <c:forEach items="${userInfo.fileGroupVO.file}" var="fileList">
		           <c:if test="${not empty fileList.flId}">
			            <a href="/file/${userInfo.usrId}/${userInfo.flGrpId}/${fileList.flId}" title="클릭하여 해당 파일 다운로드">
			                 [${fileList.flNm}]
			             </a>
			           <c:set var="vaildFileCount" value="${vaildFileCount + 1}"/>
		           </c:if>
		        </c:forEach>
		        
		        <%-- file 객체 내에 flId 값이 없을 경우 --%>
		        <c:if test="${vaildFileCount eq 0}">
		            -
		        </c:if>
		    </c:when>
		    
		    <%-- file 객체가 비어있을 경우 --%>
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