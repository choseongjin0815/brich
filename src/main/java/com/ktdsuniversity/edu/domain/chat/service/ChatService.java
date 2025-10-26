package com.ktdsuniversity.edu.domain.chat.service;

import java.util.List;

import com.ktdsuniversity.edu.domain.campaign.vo.CampaignVO;
import com.ktdsuniversity.edu.domain.chat.vo.ChatMessageVO;
import com.ktdsuniversity.edu.domain.chat.vo.ChatParticipantVO;
import com.ktdsuniversity.edu.domain.chat.vo.ChatRoomVO;
import com.ktdsuniversity.edu.domain.chat.vo.request.RequestChatMessageVO;
import com.ktdsuniversity.edu.domain.chat.vo.response.ResponseChatCampaignListVO;
import com.ktdsuniversity.edu.domain.chat.vo.response.ResponseChatRoomInfoVO;

public interface ChatService {
	
	/**
	 * 테스트용 추후 삭제
	 */
	public List<ChatMessageVO> readChatMessageByRmId(String chtrmId);
	
	/**
     * 채팅방 목록 전체 조회
     */
	public List<ResponseChatRoomInfoVO> readAllChatRoomList(String usrId);

    /**
     * 채팅방 목록 안읽은 것만 조회
     */
	public List<ResponseChatRoomInfoVO> readUnreadChatRoomList(String usrId);

    /**
     * 광고주용 채팅방 캠페인 목록 전체
     */
	public List<ResponseChatCampaignListVO> readAllCampaignList(String usrId);

    /**
     * 광고주용 채팅방 캠페인 목록 종료
     */
	public List<ResponseChatCampaignListVO> readEndedCampaignList(String usrId);

    /**
     * 광고주용 채팅방 캠페인 목록 진행중
     */
	public List<ResponseChatCampaignListVO> readOngoingCampaignList(String usrId);

    /**
     * 채팅방 나가기
     */
	public boolean leaveChatRoom(ChatParticipantVO participant);

    /**
     * 채팅방 입장 시 메시지 목록 조회
     */
	public List<ChatMessageVO> readChatMessageList(String chtRmId, String usrId);

    /**
     * 메시지 전송 (파일 포함)
     */
	public ChatMessageVO sendMessage(RequestChatMessageVO requestChatMessageVO);

    /**
     * 읽음 처리
     */
	public void updateMessagesAsRead(String chtRmId, String usrId);
}
