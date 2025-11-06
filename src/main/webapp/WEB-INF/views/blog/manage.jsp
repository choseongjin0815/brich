<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<jsp:include page="/WEB-INF/views/layout/menu.jsp">
    <jsp:param name='css' value="
		<link rel='stylesheet' type='text/css'
	      	href='https://cdn.datatables.net/1.13.7/css/jquery.dataTables.min.css'/>
		<link rel='stylesheet' type='text/css'
	      	href='https://cdn.datatables.net/responsive/2.5.0/css/responsive.dataTables.min.css'/>
       	<link type='text/css' rel='stylesheet' href='/css/campaignmain.css' />
		<link type='text/css' rel='stylesheet' href='/css/blog/manage.css' />
		
       	
   	" />
   	<jsp:param name="scripts" value="
		<script src='https://cdn.datatables.net/1.13.7/js/jquery.dataTables.min.js'></script>
		<script src='https://cdn.datatables.net/responsive/2.5.0/js/dataTables.responsive.min.js'></script>
       	<script type='text/javascript' src='/js/blog/manage.js'></script>
	" />    
</jsp:include>
		<div class="header-title">블로그 관리</div>
		<div class="dashboard-container">
			<table class="table table-bordered table-hover dt-responsive">
			        
			        <thead>
			          <tr>
			            <th>1111</th>
			            <th>Languages</th>
			            <th>Population</th>
			            <th>Median Age</th>
			            <th>Area (Km²)</th>
			          </tr>
			        </thead>
			        <tbody>
			          <tr>
			            <td>A11</td>
			            <td>Spanish (official), English, Italian, German, French</td>
			            <td>41,803,125</td>
			            <td>31.3</td>
			            <td>2,780,387</td>
			          </tr>
			          <tr>
			            <td>Australia</td>
			            <td>English 79%, native and other languages</td>
			            <td>23,630,169</td>
			            <td>37.3</td>
			            <td>7,739,983</td>
			          </tr>
			          <tr>
			            <td>Greece</td>
			            <td>Greek 99% (official), English, French</td>
			            <td>11,128,404</td>
			            <td>43.2</td>
			            <td>131,956</td>
			          </tr>
			          <tr>
			            <td>Luxembourg</td>
			            <td>Luxermbourgish (national) French, German (both administrative)</td>
			            <td>536,761</td>
			            <td>39.1</td>
			            <td>2,586</td>
			          </tr>
			          <tr>
			            <td>Russia</td>
			            <td>Russian, others</td>
			            <td>142,467,651</td>
			            <td>38.4</td>
			            <td>17,076,310</td>
			          </tr>
			          <tr>
			            <td>Sweden</td>
			            <td>Swedish, small Sami- and Finnish-speaking minorities</td>
			            <td>9,631,261</td>
			            <td>41.1</td>
			            <td>449,954</td>
			          </tr>
			        </tbody>
			        <tfoot>
			          <tr>
			            <td colspan="5" class="text-center">Data retrieved from <a href="http://www.infoplease.com/ipa/A0855611.html" target="_blank">infoplease</a> and <a href="http://www.worldometers.info/world-population/population-by-country/" target="_blank">worldometers</a>.</td>
			          </tr>
			        </tfoot>
			      </table>
			
		</div>
	</body>
</html>