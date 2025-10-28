package com.ktdsuniversity.edu.domain.chat.service;

import java.util.List;

import com.ktdsuniversity.edu.domain.campaign.vo.CampaignVO;
import com.ktdsuniversity.edu.domain.chat.vo.ChatMessageVO;
import com.ktdsuniversity.edu.domain.chat.vo.ChatParticipantVO;
import com.ktdsuniversity.edu.domain.chat.vo.ChatRoomVO;
import com.ktdsuniversity.edu.domain.chat.vo.SearchChatVO;
import com.ktdsuniversity.edu.domain.chat.vo.request.RequestChatMessageVO;
import com.ktdsuniversity.edu.domain.chat.vo.response.ResponseChatCampaignListVO;
import com.ktdsuniversity.edu.domain.chat.vo.response.ResponseChatRoomInfoVO;

public interface ChatService {
    
    // 채팅방 목록 조회 (페이징)
    public SearchChatVO readAllChatRoomList(SearchChatVO searchChatVO);
    
    public SearchChatVO readUnreadChatRoomList(SearchChatVO searchChatVO);
    
    // 캠페인 목록 조회 (페이징)
    public SearchChatVO readAllCampaignList(SearchChatVO searchChatVO);
    
    public SearchChatVO readEndedCampaignList(SearchChatVO searchChatVO);
    
    public SearchChatVO readOngoingCampaignList(SearchChatVO searchChatVO);
    
    // 채팅방 나가기
    public boolean leaveChatRoom(ChatParticipantVO participant);
    
    // 채팅 메시지 관련
    public List<ChatMessageVO> readChatMessageList(String chtRmId, String usrId);
    
    public ChatMessageVO sendMessage(RequestChatMessageVO requestChatMessageVO);
    
    public void updateMessagesAsRead(String chtRmId, String usrId);
}
