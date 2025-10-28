<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<jsp:include page="/WEB-INF/views/layout/menu.jsp">
	<jsp:param name='chatCss'
		value="
        <link type='text/css' rel='stylesheet' href='/css/chat/chat.css' />
    " />
	<jsp:param name='chatroomscripts'
		value="
        <script type='text/javascript' src='/js/chat/chatRoom.js'></script>
    " />
	<jsp:param name="sockjs"
		value='<script src="https://cdn.jsdelivr.net/npm/sockjs-client@1.6.1/dist/sockjs.min.js"></script>' />
	<jsp:param name="stompjs"
		value='<script src="https://cdn.jsdelivr.net/npm/stompjs@2.3.3/lib/stomp.min.js"></script>' />
</jsp:include>
<div class="chat-main" data-chtrm-id="${chtRmId}">
	<div class="header-title">메시지</div>
	<div class="content-box"
		data-auth="${sessionScope.__LOGIN_USER__.autr}">
		<div class="content-title">
			<div class="content-title-text">캠페인 제목</div>
			<div class="chatroom-extra">
				<img src="/img/more-horizontal.png" class="chat-leave-btn-rm">
				<div class="report-btn-rm">신고하기</div>
				<div class="leave-chat-btn-rm">채팅방 나가기</div>
			</div>
		</div>
		<div class="message-container"></div>
		<div class="chat-message-input">
		  <textarea rows="" cols=""></textarea>
		</div>
	</div>
</div>
</div>
</div>
</body>
</html>
