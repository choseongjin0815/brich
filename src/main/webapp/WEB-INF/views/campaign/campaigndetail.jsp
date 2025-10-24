<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c" uri="jakarta.tags.core" %>

<jsp:include page="/WEB-INF/views/layout/menu.jsp">
    <jsp:param name='css' value="
        <link type='text/css' rel='stylesheet' href='/css/campaignmain.css' />
    " />
</jsp:include>
        <div class="main">
          <div class="cmpn-top-area">
            <div class="title">${detail.cmpnTitle }</div>
            <div>CMPN.OFFR_CN</div>
            <div>
              <span>신청자</span>
              <div class="now">현재신청자</div>
              <div>${detail.rcrtPrsnn }</div>
            </div>
          </div>
          <div class="cmpn-bottom-area">
            <div class="cmpn-content-area">
              <div>이미지</div>
              <label for="">설명</label>
              <div>${detail.cmpnCn }</div>
              <label for="">제공</label>
              <div>${detail.offrCn }</div>
              <label for="">미션</label>
              <div>${detail.pstMssn }</div>
              <label for="">해시 태그</label>
              <div>${detail.hstg }</div>
              <label for="">안내 사항</label>
              <div>${detail.ntfcn }</div>
              <label for="">위치</label>
              <div>${detail.addrs }</div>
              <div>지도</div>
            </div>
            <div class="cmpn-rightbar-area">
              <div class="right-bar">right-bar</div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </body>
</html>
