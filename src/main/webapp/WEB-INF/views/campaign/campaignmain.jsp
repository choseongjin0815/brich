<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c" uri="jakarta.tags.core" %>

<jsp:include page="/WEB-INF/views/layout/menu.jsp">
    <jsp:param name='css' value="
        <link type='text/css' rel='stylesheet' href='/css/campaignmain.css' />
    " />
    <jsp:param name="scripts" value="
        <script type='text/javascript' src='/js/campaign/campaignmain.js'></script>
    " />    
</jsp:include>
        <div class="main">
            <div>캠페인</div>
            <div>카테고리</div>
            <form class="search_section">
                <label class="category__radio noview">
                    <input type="radio" name="category" value="ALL" checked />
                </label>  
                <c:forEach items="${category}" var="category">
                    <label class="category__radio">
                        <input type="radio" name="category" value="${category.cdNm}"/>
                        <span>${category.cdNm} </span>
                    </label>    
                </c:forEach>
                <div>필터&검색</div>
                <div>목록</div>
                <input type="text" name="searchKeyword" value="${search.searchKeyword}" />
                <button type="button" class="search-button">검색</button>
                <div>SORT BY:</div>
                <select name="sortBy">
                    <option value="latest" ${search.sortBy eq "latest" ? "selected" : ""}>최신순</option>
                    <option value="deadline" ${search.sortBy eq "deadline" ? "selected" : ""}>마감임박순</option>
                    <option value="popular" ${search.sortBy eq "popular" ? "selected" : ""}>인기순</option>
                </select>
            </form> 
            
          <div>
            <a href="/campaigndetail/CMPN-20250919-000004">dd</a>
                <c:forEach items="${campaignList}" var="campaignList">
                    <div> ${campaignList.cmpnTitle} </div>
                </c:forEach>
          </div>
        </div>
      </div>
    </div>
  </body>
</html>
