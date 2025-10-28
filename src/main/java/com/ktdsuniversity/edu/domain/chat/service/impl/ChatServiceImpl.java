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
import com.ktdsuniversity.edu.domain.chat.vo.SearchChatVO;
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
	public SearchChatVO readAllChatRoomList(SearchChatVO searchChatVO) {
		String auth = searchChatVO.getAuth();
		
		// 1. 총 개수 조회
		int totalCount = 0;
		List<ResponseChatRoomInfoVO> chatRooms = null;
		
		if(!auth.equals("1004")) {
			// 블로거용
			totalCount = chatDao.selectUserChatRoomsCount(searchChatVO);
			chatRooms = chatDao.selectUserChatRooms(searchChatVO);
			log.info("블로거로 리스트!");
		} else {
			// 광고주용
			log.info("광고주로 리스트!{}", searchChatVO.getCmpnId());
			totalCount = chatDao.selectCampaignChatRoomsCount(searchChatVO);
			chatRooms = chatDao.selectCampaignChatRooms(searchChatVO);
		}
		
		// 2. 페이지 정보 설정
		searchChatVO.setPageCount(totalCount);

		// 3. MongoDB에서 각 채팅방의 최신 메시지 및 안읽은 수 조회
		for (ResponseChatRoomInfoVO chatRoom : chatRooms) {
			// 최신 메시지 조회
			Sort sort = Sort.by(Sort.Direction.DESC, "CRT_DT");
			List<ChatMessageVO> messages = chatMessageRepository.findByChtRmIdOrderByCrtDtDesc(
					chatRoom.getChtRmId(), sort);

			if (messages != null && !messages.isEmpty()) {
				ChatMessageVO lastMessage = messages.get(0);
				chatRoom.setLastMsgCn(lastMessage.getMsgCn());
				chatRoom.setLastMsgUsrId(lastMessage.getUsrId());
				chatRoom.setLastMsgCrtDt(lastMessage.getCrtDt());
			}

			// 안읽은 메시지 수 조회
			long unreadCount = chatMessageRepository.countUnreadMessages(
					chatRoom.getChtRmId(), searchChatVO.getUsrId());
			chatRoom.setUnreadCnt((int) unreadCount);
		}

		// 4. 결과 목록을 SearchChatVO에 설정
		searchChatVO.setChatRoomList(chatRooms);

		return searchChatVO;
	}

	@Override
	public SearchChatVO readUnreadChatRoomList(SearchChatVO searchChatVO) {
		// 안읽은 메시지 조회는 전체 데이터를 가져와서 필터링해야 함
		// MongoDB에서 unreadCnt를 확인한 후 필터링하기 때문
		
		String auth = searchChatVO.getAuth();
		
		// 1. 전체 채팅방 목록 조회 (페이징 없이 전체)
		SearchChatVO tempSearch = new SearchChatVO();
		tempSearch.setUsrId(searchChatVO.getUsrId());
		tempSearch.setAuth(searchChatVO.getAuth());
		tempSearch.setCmpnId(searchChatVO.getCmpnId());
		tempSearch.setPageNo(0);
		tempSearch.setListSize(Integer.MAX_VALUE); // 전체 조회
		
		List<ResponseChatRoomInfoVO> allChatRooms = null;
		
		if(!auth.equals("1004")) {
			// 블로거용
			allChatRooms = chatDao.selectUserChatRooms(tempSearch);
			log.info("블로거로 안읽은 메시지 리스트!");
		} else {
			// 광고주용
			log.info("광고주로 안읽은 메시지 리스트!{}", searchChatVO.getCmpnId());
			allChatRooms = chatDao.selectCampaignChatRooms(tempSearch);
		}

		// 2. MongoDB에서 각 채팅방의 안읽은 메시지 수 조회 및 필터링
		List<ResponseChatRoomInfoVO> unreadRooms = new ArrayList<>();
		
		for (ResponseChatRoomInfoVO chatRoom : allChatRooms) {
			// 최신 메시지 조회
			Sort sort = Sort.by(Sort.Direction.DESC, "CRT_DT");
			List<ChatMessageVO> messages = chatMessageRepository.findByChtRmIdOrderByCrtDtDesc(
					chatRoom.getChtRmId(), sort);

			if (messages != null && !messages.isEmpty()) {
				ChatMessageVO lastMessage = messages.get(0);
				chatRoom.setLastMsgCn(lastMessage.getMsgCn());
				chatRoom.setLastMsgUsrId(lastMessage.getUsrId());
				chatRoom.setLastMsgCrtDt(lastMessage.getCrtDt());
			}

			// 안읽은 메시지 수 조회
			long unreadCount = chatMessageRepository.countUnreadMessages(
					chatRoom.getChtRmId(), searchChatVO.getUsrId());
			chatRoom.setUnreadCnt((int) unreadCount);
			
			// 안읽은 메시지가 있는 채팅방만 추가
			if (unreadCount > 0) {
				unreadRooms.add(chatRoom);
			}
		}

		// 3. 필터링된 결과에서 페이징 적용
		int startIndex = searchChatVO.getPageNo() * searchChatVO.getListSize();
		int endIndex = Math.min(startIndex + searchChatVO.getListSize(), unreadRooms.size());
		
		List<ResponseChatRoomInfoVO> pagedRooms = new ArrayList<>();
		if (startIndex < unreadRooms.size()) {
			pagedRooms = unreadRooms.subList(startIndex, endIndex);
		}
		
		// 4. 결과 설정
		searchChatVO.setChatRoomList(pagedRooms);
		searchChatVO.setPageCount(unreadRooms.size());
		
		return searchChatVO;
	}

	@Override
	public SearchChatVO readAllCampaignList(SearchChatVO searchChatVO) {
		// 1. 총 개수 조회
		int totalCount = chatDao.selectAllCampaignListCount(searchChatVO);
		log.info("TOTALCOUNT {}", totalCount);
		
		// 2. 목록 조회
		List<ResponseChatCampaignListVO> campaigns = chatDao.selectAllCampaignList(searchChatVO);
		
		// 3. 페이지 정보 설정
		searchChatVO.setPageCount(totalCount);
		
		// 4. 결과 목록을 SearchChatVO에 설정
		searchChatVO.setCampaignList(campaigns);
		
		return searchChatVO;
	}

	@Override
	public SearchChatVO readEndedCampaignList(SearchChatVO searchChatVO) {
		// 1. 총 개수 조회
		int totalCount = chatDao.selectEndedCampaignListCount(searchChatVO);
		
		// 2. 목록 조회
		List<ResponseChatCampaignListVO> campaigns = chatDao.selectEndedCampaignList(searchChatVO);
		
		// 3. 페이지 정보 설정
		searchChatVO.setPageCount(totalCount);
		
		// 4. 결과 목록을 SearchChatVO에 설정
		searchChatVO.setCampaignList(campaigns);
		
		return searchChatVO;
	}

	@Override
	public SearchChatVO readOngoingCampaignList(SearchChatVO searchChatVO) {
		// 1. 총 개수 조회
		int totalCount = chatDao.selectOngoingCampaignListCount(searchChatVO);
		
		// 2. 목록 조회
		List<ResponseChatCampaignListVO> campaigns = chatDao.selectOngoingCampaignList(searchChatVO);
		
		// 3. 페이지 정보 설정
		searchChatVO.setPageCount(totalCount);
		
		// 4. 결과 목록을 SearchChatVO에 설정
		searchChatVO.setCampaignList(campaigns);
		
		return searchChatVO;
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

		// 각 메시지에 사용자 이름 추가 (Oracle에서)
		for (ChatMessageVO message : messages) {
			// 사용자 이름 조회
			String userName = chatDao.selectUserName(message.getUsrId());
			message.setUsrNm(userName);
		}

		// 3. 읽음 처리
		updateMessagesAsRead(chtRmId, usrId);

		return messages;
	}

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

	private String generateMessageId() {
		return "CHT_MSG-" + "-";
	}

}
