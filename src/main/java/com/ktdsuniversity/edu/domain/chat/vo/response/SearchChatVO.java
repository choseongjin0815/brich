package com.ktdsuniversity.edu.domain.chat.vo.response;

import java.util.List;

import com.ktdsuniversity.edu.global.common.AbstractSearchVO;

/**
 * 채팅 (캠페인, 채팅방) 페이징을 위한 VO
 */
public class SearchChatVO extends AbstractSearchVO {
    
    private String usrId;
    private String auth;
    private String cmpnId;
    
    // 채팅방 목록
    private List<ResponseChatRoomInfoVO> chatRoomList;
    
    // 캠페인 목록
    private List<ResponseChatCampaignListVO> campaignList;
    
    public SearchChatVO() {
        super();
        // 한 페이지에 5개씩
        this.setListSize(5);
        // 페이지 그룹에 5개씩
        this.setPageCountInGroup(5);
    }

    public String getUsrId() {
        return usrId;
    }

    public void setUsrId(String usrId) {
        this.usrId = usrId;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public String getCmpnId() {
        return cmpnId;
    }

    public void setCmpnId(String cmpnId) {
        this.cmpnId = cmpnId;
    }

    public List<ResponseChatRoomInfoVO> getChatRoomList() {
        return chatRoomList;
    }

    public void setChatRoomList(List<ResponseChatRoomInfoVO> chatRoomList) {
        this.chatRoomList = chatRoomList;
    }

    public List<ResponseChatCampaignListVO> getCampaignList() {
        return campaignList;
    }

    public void setCampaignList(List<ResponseChatCampaignListVO> campaignList) {
        this.campaignList = campaignList;
    }

    /**
     * 페이징을 위한 OFFSET 계산
     * @return OFFSET 값
     */
    public int getOffset() {
        return this.getPageNo() * this.getListSize();
    }
}