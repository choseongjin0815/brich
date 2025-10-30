package com.ktdsuniversity.edu.domain.chat.service.impl;


import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
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
import com.ktdsuniversity.edu.domain.chat.vo.response.ResponseChatCampaignListVO;
import com.ktdsuniversity.edu.domain.chat.vo.response.ResponseChatRoomInfoVO;
import com.ktdsuniversity.edu.domain.file.dao.FileDao;
import com.ktdsuniversity.edu.domain.file.dao.FileGroupDao;
import com.ktdsuniversity.edu.domain.file.util.MultipartFileHandler;
import com.ktdsuniversity.edu.domain.file.vo.FileGroupVO;
import com.ktdsuniversity.edu.domain.file.vo.FileVO;
import com.ktdsuniversity.edu.global.util.TimeFormatUtil;

@Service
public class ChatServiceImpl implements ChatService {

	private static final Logger log = LoggerFactory.getLogger(ChatServiceImpl.class);

	@Autowired
	private MultipartFileHandler multipartFileHandler;
	
	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private ChatMessageRepository chatMessageRepository;

	@Autowired
	private ChatDao chatDao;

	@Autowired
	private FileGroupDao fileGroupDao;

	@Autowired
	private FileDao fileDao;

	@Override
	public SearchChatVO readAllChatRoomList(SearchChatVO searchChatVO) {
		String auth = searchChatVO.getAuth();

		// 1. 총 개수 조회
		int totalCount = 0;
		List<ResponseChatRoomInfoVO> chatRooms = null;

		if (!auth.equals("1004")) {
			// 블로거용
			log.info("{}", System.currentTimeMillis());
			totalCount = chatDao.selectUserChatRoomsCount(searchChatVO);
			chatRooms = chatDao.selectUserChatRooms(searchChatVO);
			log.info("{}", System.currentTimeMillis());
			log.info("블로거로 리스트!");
		} else {
			// 광고주용
			log.info("{}", System.currentTimeMillis());
			totalCount = chatDao.selectCampaignChatRoomsCount(searchChatVO);
			chatRooms = chatDao.selectCampaignChatRooms(searchChatVO);
			log.info("{}", System.currentTimeMillis());
		}

		// 2. 페이지 정보 설정
		searchChatVO.setPageCount(totalCount);
		log.info("시간{}", System.currentTimeMillis());
		// 3. MongoDB에서 각 채팅방의 최신 메시지 및 안읽은 수 조회
		for (ResponseChatRoomInfoVO chatRoom : chatRooms) {
			// 최신 메시지 조회
			//Sort sort = Sort.by(Sort.Direction.DESC, "CRT_DT");
			log.info("채팅메시지 조회{}", System.currentTimeMillis());
			//여기서 메시지를 하나만 조회하면 되는거아닌가???????????????
			ChatMessageVO messages = chatMessageRepository.findTop1ByChtRmIdAndDltYnOrderByCrtDtDesc(chatRoom.getChtRmId(), "N");
			log.info("채팅메시지 조회{}", System.currentTimeMillis());
			if (messages != null) {
				ChatMessageVO lastMessage = messages;
				chatRoom.setLastMsgCn(lastMessage.getMsgCn());
				chatRoom.setLastMsgUsrId(lastMessage.getUsrId());
				chatRoom.setLastMsgCrtDt(TimeFormatUtil.format(lastMessage.getCrtDt()));
				chatRoom.setCrtDt(lastMessage.getCrtDt());
			}

			// 안읽은 메시지 수 조회
			log.info("안읽은 메시지 개수{}", System.currentTimeMillis());
			long unreadCount = chatMessageRepository.countUnreadMessages(chatRoom.getChtRmId(),
					searchChatVO.getUsrId());
			log.info("안읽은 메시지 개수{}", System.currentTimeMillis());
			chatRoom.setUnreadCnt((int) unreadCount);
		}
		log.info("시간{}", System.currentTimeMillis());
		//최근 메시지 온 순서대로 정렬
		chatRooms.sort(Comparator.comparing(ResponseChatRoomInfoVO::getCrtDt).reversed());
		
		log.info("chatRooms: {}", chatRooms);

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

		if (!auth.equals("1004")) {
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
			ChatMessageVO messages = chatMessageRepository.findTop1ByChtRmIdAndDltYnOrderByCrtDtDesc(chatRoom.getChtRmId(), "N");

			if (messages != null) {
				ChatMessageVO lastMessage = messages;
				chatRoom.setLastMsgCn(lastMessage.getMsgCn());
				chatRoom.setLastMsgUsrId(lastMessage.getUsrId());
				chatRoom.setLastMsgCrtDt(TimeFormatUtil.format(lastMessage.getCrtDt()));
				chatRoom.setCrtDt(lastMessage.getCrtDt());

			}

			// 안읽은 메시지 수 조회
			long unreadCount = chatMessageRepository.countUnreadMessages(chatRoom.getChtRmId(),
					searchChatVO.getUsrId());
			chatRoom.setUnreadCnt((int) unreadCount);

			// 안읽은 메시지가 있는 채팅방만 추가
			if (unreadCount > 0) {
				unreadRooms.add(chatRoom);
			}
		}
		
		unreadRooms.sort(Comparator.comparing(ResponseChatRoomInfoVO::getCrtDt).reversed());


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
	public Map<String, Object> readChatMessageListPaged(String chtRmId, String usrId, int page, int size) {
	    // 페이징 설정 (최신순 내림차순)
	    Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "crtDt"));
	    
	    // 메시지 조회
	    Slice<ChatMessageVO> messageSlice = chatMessageRepository
	        .findByChtRmIdAndDltYnOrderByCrtDtDesc(chtRmId, "N", pageable);
	    
	    //Reverse되게 하기 위해서 List로
	    List<ChatMessageVO> messages = new ArrayList<>(messageSlice.getContent());
	    
	    // 파일이 있다면 파일 정보 추가
	    for (ChatMessageVO message : messages) {
	        if (message.getAttchGrpId() != null && !message.getAttchGrpId().isEmpty()) {
	            List<FileVO> files = fileDao.selectFilesByGroupId(message.getAttchGrpId());
	            message.setFileList(files);
	        }
	    }
	    
	    Collections.reverse(messages);
	    
	    // 첫 페이지일 때만 읽음 처리
	    if (page == 0) {
	        updateMessagesAsRead(chtRmId, usrId);
	    }
	    
	    Map<String, Object> result = new HashMap<>();
	    result.put("messages", messages);
	    result.put("hasNext", messageSlice.hasNext());
	    
	    return result;
	}

	@Transactional
	@Override
	public ChatMessageVO sendMessage(RequestChatMessageVO requestChatMessageVO) {

		ChatMessageVO message = new ChatMessageVO();

		List<FileVO> uploadResult = this.multipartFileHandler.upload(requestChatMessageVO.getFiles());

		if (uploadResult != null && uploadResult.size() > 0) {
			// 1.File Group Insert
			FileGroupVO fileGroupVO = new FileGroupVO();
			fileGroupVO.setFlCnt(uploadResult != null ? uploadResult.size() : 0);
			int insertGroupCount = this.fileGroupDao.insertFileGroup(fileGroupVO);

			// 2.File Insert
			for (FileVO result : uploadResult) {
				result.setFlGrpId(fileGroupVO.getFlGrpId());
				int insertFileCount = this.fileDao.insertFile(result);
			}
			// 게시글에 첨부되어있는 파일 그룹의 아이디가 무엇인지 알수있다.
			requestChatMessageVO.setAttchGrpId(fileGroupVO.getFlGrpId());
			message.setAttchGrpId(requestChatMessageVO.getAttchGrpId());

			message.setFileList(uploadResult);

		}

		// 메시지 ID 생성
		message.setChtMsgId(UUID.randomUUID().toString());
		message.setChtRmId(requestChatMessageVO.getChtRmId());
		message.setMsgCn(requestChatMessageVO.getMsgCn());
		message.setUsrId(requestChatMessageVO.getUsrId());
		message.setCrtr(requestChatMessageVO.getUsrId());
		message.setMttr(requestChatMessageVO.getUsrId());
		message.setUsrNm(requestChatMessageVO.getNm());
		message.setCmpny(requestChatMessageVO.getCmpny());
		String now = DateTimeFormatter.ISO_INSTANT.format(Instant.now());
		message.setCrtDt(now);
		message.setUpdtDt(now);
		message.setRdYn("N");
		message.setDltYn("N");

		// MongoDB에 메시지 저장
		ChatMessageVO savedMessage = chatMessageRepository.save(message);

		// 사용자 이름 추가
		String userName = chatDao.selectUserName(requestChatMessageVO.getUsrId());
		savedMessage.setUsrNm(userName);

		// 보낸 메시지에 파일도 있으면
		if (uploadResult != null && uploadResult.size() > 0) {
			savedMessage.setFileList(uploadResult);
		}

		return savedMessage;
	}

	@Transactional
	@Override
	public void updateMessagesAsRead(String chtRmId, String usrId) {
		// MongoDB에서 안읽은 메시지 조회
		List<ChatMessageVO> unreadMessages = chatMessageRepository.findUnreadMessages(chtRmId, usrId);
		String now = DateTimeFormatter.ISO_INSTANT.format(Instant.now());

		// 읽음 처리
		for (ChatMessageVO msg : unreadMessages) {
			msg.setRdYn("Y");
			msg.setUpdtDt(now);
			chatMessageRepository.save(msg);
		}
	}

	@Override
	public CampaignVO readCampaignByChtRmId(String chtRmId) {
		
		CampaignVO campaignVO = this.chatDao.selectCampaignByChtRmId(chtRmId);
		if(campaignVO == null) {
			throw new IllegalArgumentException(chtRmId + "에 해당하는 캠페인이 없습니다.");
		}
		
		return campaignVO;
	}

}