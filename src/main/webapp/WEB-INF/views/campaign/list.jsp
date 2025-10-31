<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="/WEB-INF/views/layout/menu.jsp">
    <jsp:param name='css' value="
        <link type='text/css' rel='stylesheet' href='/css/brich.css' />
    " />
</jsp:include>

<div class="campaign-wrapper">
    <div class="campaign-content-wrapper">
        <div class="campaign-title">내 캠페인</div>
        <div class="id-search">
           <input type="text" placeholder="ID를 입력하세요." />
           <img src="/img/Search.png" />
        </div>
    </div>
</div>