<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <title>Document</title>
    <link type="text/css" rel="stylesheet" href="/css/layoutmenu.css" />
    <script type="text/javascript" src="/js/jquery-3.7.1.min.js"></script>
    
    ${param.css }
    ${param.scripts}
  </head>
  <body>
    <div class="wrapper">
      <div class="top-menu"></div>
      <div class="application-main">
        <div class="side-menu">
          <nav>
            <ul>
              <c:if test="${empty sessionScope.__LOGIN_USER__ }">
                <li class="logout-menu">
                  <span>MENU</span>
                  <ul>
                    <li><a href="">Dashboard</a></li>
                    <li><a href="">캠페인</a></li>
                  </ul>
                </li> 
              </c:if>
              <c:if test="${sessionScope.__LOGIN_USER__ ne null 
                  and (sessionScope.__LOGIN_USER__.autr == 1002 
                    or sessionScope.__LOGIN_USER__.autr == 1003)}">
                <li class="blg-menu">
                  <span>MENU</span>
                  <ul>
                    <li><a href="">Dashboard</a></li>
                    <li><a href="">캠페인</a></li>
                    <li>
                      <button>MY 캠페인</button>
                      <ul>
                        <li><a href="">신청</a></li>
                        <li><a href="">채택</a></li>
                        <li><a href="">마감</a></li>
                        <li><a href="">관심</a></li>
                      </ul>
                    </li>
                    <li><a href="">블로그 관리</a></li>
                    <li><a href="">메세지</a></li>
                  </ul>
                </li>
              </c:if>
              <c:if test="${sessionScope.__LOGIN_USER__ ne null 
                  and sessionScope.__LOGIN_USER__.autr == 1004}">
                <li class="advertiser-menu">
                  <span>MENU</span>
                  <ul>
                    <li><a href="">캠페인</a></li>
                    <li><a href="">MY 캠페인</a></li>
                    <li><a href="">캠페인 만들기</a></li>
                    <li><a href="">메세지</a></li>
                  </ul>
                </li>
              </c:if>
              <c:if test="${sessionScope.__LOGIN_USER__ ne null 
                  and sessionScope.__LOGIN_USER__.autr == 1001}">
                <li class="admin-menu">
                  <span>ADMIN MENU</span>
                  <ul>
                    <li><a href="">캠페인 관리</a></li>
                    <li><a href="">회원 관리</a></li>
                    <li><a href="">문의 관리</a></li>
                    <li><a href="">신고 관리</a></li>
                    <li><a href="">카테고리 관리</a></li>
                    <li><a href="">도움말 관리</a></li>
                  </ul>
                </li>
              </c:if>
                <li>
                  <span>OTHERS</span>
                  <ul>
                    <li><a href="">계정 관리</a></li>
                    <li><a href="">Help</a></li>
                    <li><a href="">신고</a></li>
                  </ul>
                </li>
            </ul>
          </nav>
        </div>