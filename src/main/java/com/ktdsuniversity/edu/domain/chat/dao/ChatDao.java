package com.ktdsuniversity.edu.domain.chat.dao;

import java.util.List;

import com.ktdsuniversity.edu.domain.campaign.vo.CampaignVO;
import com.ktdsuniversity.edu.domain.chat.vo.ChatParticipantVO;
import com.ktdsuniversity.edu.domain.chat.vo.ChatRoomVO;
import com.ktdsuniversity.edu.domain.chat.vo.response.ResponseChatCampaignListVO;
import com.ktdsuniversity.edu.domain.chat.vo.response.ResponseChatRoomInfoVO;

public interface ChatDao {
	/**
    * 사용자가 참여 중인 채팅방 목록 조회
    */
   public List<ResponseChatRoomInfoVO> selectUserChatRooms(String usrId);

   /**
    * 광고주용 채팅방 캠페인 목록 전체
    */
   public List<ResponseChatCampaignListVO> selectAllCampaignList(String usrId);

   /**
    * 광고주용 채팅방 캠페인 목록 종료
    */
   public List<ResponseChatCampaignListVO> selectEndedCampaignList(String usrId);

   /**
    * 광고주용 채팅방 캠페인 목록 진행중
    */
   public List<ResponseChatCampaignListVO> selectOngoingCampaignList(String usrId);

   /**
    * 채팅방 나가기
    */
   public int updateChatRoomLeave(ChatParticipantVO participant);
   
   /**
    * 사용자 이름 조회
    */
   public String selectUserName(String usrId);
}
