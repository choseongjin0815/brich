package com.ktdsuniversity.edu.domain.chat.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.edu.domain.campaign.vo.CampaignVO;
import com.ktdsuniversity.edu.domain.chat.dao.ChatDao;
import com.ktdsuniversity.edu.domain.chat.vo.ChatParticipantVO;
import com.ktdsuniversity.edu.domain.chat.vo.ChatRoomVO;
import com.ktdsuniversity.edu.domain.chat.vo.response.ResponseChatCampaignListVO;
import com.ktdsuniversity.edu.domain.chat.vo.response.ResponseChatRoomInfoVO;

@Repository
public class ChatDaoImpl extends SqlSessionDaoSupport implements ChatDao {

    private final String NAME_SPACE = "com.ktdsuniversity.edu.domain.chat.dao.impl.ChatDaoImpl.";

	
    @Autowired
    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }
    
	@Override
	public List<ResponseChatRoomInfoVO> selectUserChatRooms(String usrId) {
		return super.getSqlSession().selectList(this.NAME_SPACE + "selectUserChatRooms", usrId);
	}

	@Override
	public List<ResponseChatCampaignListVO> selectAllCampaignList(String usrId) {
		// TODO Auto-generated method stub
		return super.getSqlSession().selectList(this.NAME_SPACE + "selectAllCampaignList", usrId);
	}

	@Override
	public List<ResponseChatCampaignListVO> selectEndedCampaignList(String usrId) {
		return super.getSqlSession().selectList(this.NAME_SPACE + "selectEndedCampaignList", usrId);
	}

	@Override
	public List<ResponseChatCampaignListVO> selectOngoingCampaignList(String usrId) {
		return super.getSqlSession().selectList(this.NAME_SPACE + "selectOngoingCampaignList", usrId);
	}

	@Override
	public int updateChatRoomLeave(ChatParticipantVO participant) {
		return super.getSqlSession().update(this.NAME_SPACE + "updateChatRoomLeave", participant);
	}

	@Override
	public String selectUserName(String usrId) {
		return super.getSqlSession().selectOne(this.NAME_SPACE + "selectUserName", usrId);
	}

}
