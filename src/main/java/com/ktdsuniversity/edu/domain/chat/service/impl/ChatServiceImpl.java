package com.ktdsuniversity.edu.domain.chat.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ktdsuniversity.edu.domain.campaign.vo.CampaignVO;
import com.ktdsuniversity.edu.domain.chat.dao.ChatDao;
import com.ktdsuniversity.edu.domain.chat.dao.ChatMessageRepository;
import com.ktdsuniversity.edu.domain.chat.service.ChatService;
import com.ktdsuniversity.edu.domain.chat.vo.ChatMessageVO;
import com.ktdsuniversity.edu.domain.chat.vo.ChatParticipantVO;
import com.ktdsuniversity.edu.domain.chat.vo.request.RequestChatMessageVO;
import com.ktdsuniversity.edu.domain.chat.vo.request.RequestChatRoomFindVO;
import com.ktdsuniversity.edu.domain.chat.vo.response.ResponseChatCampaignListVO;
import com.ktdsuniversity.edu.domain.chat.vo.response.ResponseChatRoomInfoVO;

@Service
public class ChatServiceImpl implements ChatService {

	private static final Logger log = LoggerFactory.getLogger(ChatServiceImpl.class);

	@Autowired
	private ChatMessageRepository chatMessageRepository;

	@Autowired
	private ChatDao chatDao;

	/**
	 * 연결 테스트 : 추후 삭제 예정
	 */
	@Override
	public List<ChatMessageVO> readChatMessageByRmId(String chtrmId) {
		return this.chatMessageRepository.findByChtRmIdOrderByCrtDtAsc(chtrmId);
	}

	@Override
	public List<ResponseChatRoomInfoVO> readAllChatRoomList(String usrId, String auth, String cmpnId) {
		// 1. Oracle에서 사용자가 참여 중인 채팅방 목록 조회
		//블로거일 때 리스트 
		List<ResponseChatRoomInfoVO> chatRooms = null;
		log.info("service {}", auth);
		if(!auth.equals("1004")) {
			 chatRooms = this.chatDao.selectUserChatRooms(usrId);
			 log.info("블로거로 리스트!");
		}
		else if(auth.equals("1004")) {
			 log.info("광고주로 리스트!{}", cmpnId);
			 
			RequestChatRoomFindVO find = new RequestChatRoomFindVO();
			find.setCmpnId(cmpnId);
			find.setUsrId(usrId);
			chatRooms = this.chatDao.selectCampaignChatRooms(find);
		}

		// 2. MongoDB에서 각 채팅방의 최신 메시지 및 안읽은 수 조회
		for (ResponseChatRoomInfoVO chatRoom : chatRooms) {
			// 최신 메시지 조회
			Sort sort = Sort.by(Sort.Direction.DESC, "CRT_DT");
			List<ChatMessageVO> messages = chatMessageRepository.findByChtRmIdOrderByCrtDtDesc(chatRoom.getChtRmId(),
					sort);

			if (messages != null && !messages.isEmpty()) {
				ChatMessageVO lastMessage = messages.get(0);
				chatRoom.setLastMsgCn(lastMessage.getMsgCn());
				chatRoom.setLastMsgUsrId(lastMessage.getUsrId());
				chatRoom.setLastMsgCrtDt(lastMessage.getCrtDt());
			}

			// 안읽은 메시지 수 조회
			long unreadCount = chatMessageRepository.countUnreadMessages(chatRoom.getChtRmId(), usrId);
			chatRoom.setUnreadCnt((int) unreadCount);
		}

		return chatRooms;
	}

	@Override
	public List<ResponseChatRoomInfoVO> readUnreadChatRoomList(String usrId, String auth, String cmpnId) {
		// 일단 전부 읽은 채팅을 불러오고
		List<ResponseChatRoomInfoVO> allRooms = readAllChatRoomList(usrId, auth, cmpnId);

		// 안읽은 메시지가 있는 채팅방만 필터링
		List<ResponseChatRoomInfoVO> unreadRooms = new ArrayList<>();
		for (ResponseChatRoomInfoVO room : allRooms) {
			if (room.getUnreadCnt() > 0) {
				unreadRooms.add(room);
			}
		}

		return unreadRooms;
	}

	/**
	 * TODO 캠페인 목록 뽑기 나중에 페이징 추가 , 중복으로 사용할 수 있는 쿼리 있으면 그거 가져올 예정임
	 * 	    ResponseChatCampaignListVO 만들어야 함
	 */
	@Override
	public List<ResponseChatCampaignListVO> readAllCampaignList(String usrId) {
		return chatDao.selectAllCampaignList(usrId);
	}

	@Override
	public List<ResponseChatCampaignListVO> readEndedCampaignList(String usrId) {
		return chatDao.selectEndedCampaignList(usrId);
	}

	@Override
	public List<ResponseChatCampaignListVO> readOngoingCampaignList(String usrId) {
		return chatDao.selectOngoingCampaignList(usrId);
	}

	@Transactional
	@Override
	public boolean leaveChatRoom(ChatParticipantVO participant) {
		int result = chatDao.updateChatRoomLeave(participant);
		return result > 0;
	}

	@Transactional
	@Override
	public List<ChatMessageVO> readChatMessageList(String chtRmId, String usrId) {
		// MongoDB에서 메시지 목록 조회
		List<ChatMessageVO> messages = chatMessageRepository.findByChtRmIdOrderByCrtDtAsc(chtRmId);

		// 각 메시지에 사용자 이름 및 파일 정보 추가 (Oracle에서)
		for (ChatMessageVO message : messages) {
			// 사용자 이름 조회
			String userName = chatDao.selectUserName(message.getUsrId());
			message.setUsrNm(userName);

		
		}

		// 3. 읽음 처리
		updateMessagesAsRead(chtRmId, usrId);

		return messages;
	}

	
	//TODO 날짜를 형식 변환해서 삽입
	@Transactional
	@Override
	public ChatMessageVO sendMessage(RequestChatMessageVO requestChatMessageVO) {
		// 메시지 ID 생성
		String msgId = generateMessageId();
		ChatMessageVO message = new ChatMessageVO();
		message.setChtMsgId(msgId);
		message.setUsrId(requestChatMessageVO.getUsrId());
		message.setCrtr(requestChatMessageVO.getUsrId());
		message.setMttr(requestChatMessageVO.getUsrId());
		message.setCrtDt("");
		message.setUpdtDt("");
		message.setRdYn("N");
		message.setDltYn("N");

		// MongoDB에 메시지 저장
		ChatMessageVO savedMessage = chatMessageRepository.save(message);

		// 사용자 이름 추가
		String userName = chatDao.selectUserName(requestChatMessageVO.getUsrId());
		savedMessage.setUsrNm(userName);

		return savedMessage;
	}

	@Transactional
	@Override
	public void updateMessagesAsRead(String chtRmId, String usrId) {
		// MongoDB에서 안읽은 메시지 조회
		List<ChatMessageVO> unreadMessages = chatMessageRepository.findUnreadMessages(chtRmId, usrId);

		// 읽음 처리
		for (ChatMessageVO msg : unreadMessages) {
			msg.setRdYn("Y");
			msg.setUpdtDt("");
			chatMessageRepository.save(msg);
		}
	}

	//TODO CHT_MSG_ID, "CHT-MSG-yyyymmdd-000001" 형태 시퀀스 포함된 아이디 생성하기
	private String generateMessageId() {
		
		return "CHT_MSG-" + "-";
	}


}
