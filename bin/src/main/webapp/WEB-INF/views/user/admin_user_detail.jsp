<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>회원 관리 - 회원 상세 정보</title>
        <script type="text/javascript" src="/js/jquery-3.7.1.min.js"></script>
        <script type="text/javascript" src="/js/user/admin_user_list.js"></script>
        <link type="text/css" rel="stylesheet" href="/css/user/admin_user_list.css"/>
    </head>
    <body>
       <div class="wrapper">
           <h1>회원 관리</h1>
           <div class="tab">
               <label for="all_user">전체</label>
               <input type="radio" id="all_user" name="admin_user_tab" class="tab_item" checked="checked"/>
               <label for="blogger">블로거</label>
               <input type="radio" id="blogger" name="admin_user_tab" class="tab_item"/>
               <label for="adtr">광고주</label>
               <input type="radio" id="adtr" name="admin_user_tab" class="tab_item"/>
           </div>
		   <div>
		      <table>
		        <tbody>
		           <tr>
		              <th>아이디</th>
		              <td></td>
		              
		              <th>이메일</th>
                      <td></td>
                      
                      <th>이름</th>
                      <td></td>
                      
                      <!-- include -->
                      
                      <th>최근 로그인</th>
                      <td></td>
                      
                      <th>징계 횟수</th>
                      <td></td>
                      
                      <th>가입 일시</th>
                      <td></td>
		           </tr>
		        </tbody>
		      </table>
		   </div>
	   </div>
	</body>
</html>