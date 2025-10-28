<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
	<html>
	<head>
		<meta charset="UTF-8">
		<title>Inqr View</title>
	</head>
	<body>
	   <div class="wrapper">
	       <h1>문의 상세보기</h1>
	       <div>
	           <!-- session id 받아오기, 값 수정 -->
	           <input type="hidden" value="${inqr_view.inqrId}" />
	           <input type="hidden" value="${inqr_view.inqrUsrId}" />
	           
	           <label for="inqrUsrId">문의자</label>
	           <div>${inqr_view.usrVO.logId}</div><br>
	           
	           <label for="inqrTitle">문의 제목</label>
               <div>${inqr_view.inqrTitle}</div><br>
               
               <label for="inqrCtg">문의 카테고리</label>
               <div>${inqr_view.inqrCtg}</div><br>
               
               <label for="crtDt">문의 일시</label>
               <div>${inqr_view.crtDt}</div><br>
               
               <label for="inqrCn">문의 내용</label>
               <div>${inqr_view.inqrCn}</div><br>
               
               <label for="ansrYn">답변 여부</label>
               <div>${inqr_view.ansrYn}</div><br>
               
               <c:choose>
                   <c:when test="${inqr_view.ansrYn eq 'Y'}">
		               <label for="ansrUsrId">답변 등록 관리자</label>
		               <div>${inqr_view.ansrUsrId}</div><br>
		               
		               <label for="ansrCn">답변 내용</label>
		               <div>${inqr_view.ansrCn}</div><br>
		               
		               <label for="updtDt">답변 등록 일시</label>
		               <div>${inqr_view.updtDt}</div>
	               </c:when>
	               
	               <c:otherwise>
	                   <div>답변</div>
	                   <label for="ansrCn">답변 내용</label>
	                   <textarea name="ansrCn"></textarea>
	                   
	                   <button type="button" id="registAnsr" class="save-btn">등록</button>
	               </c:otherwise>
               </c:choose>
	       </div>
	   </div>
	</body>
</html>