package com.ktdsuniversity.edu.domain.campaign.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.edu.domain.campaign.dao.AdminCampaignDao;
import com.ktdsuniversity.edu.domain.campaign.vo.request.RequestAdminSearchCampaignVO;
import com.ktdsuniversity.edu.domain.campaign.vo.response.ResponseAdminCampaignVO;

@Repository
public class AdminCampaignDaoImpl extends SqlSessionDaoSupport implements AdminCampaignDao {
	
	private final String NAME_SPACE = "com.ktdsuniversity.edu.domain.campaign.dao.impl.AdminCampaignDaoImpl.";
	
	@Autowired
	@Override
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}

	@Override
	public String selectAdminCategoryParent(String category) {
		return super.getSqlSession().selectOne(this.NAME_SPACE + "selectAdminCategoryParent", category);
	}

	@Override
	public List<ResponseAdminCampaignVO> selectAdminCampaignListCategoryAndSortBy(RequestAdminSearchCampaignVO requestAdminSearchCampaignVO) {
		return super.getSqlSession().selectList(this.NAME_SPACE + "selectAdminCampaignListCategoryAndSortBy", requestAdminSearchCampaignVO);
	}

	@Override
	public ResponseAdminCampaignVO selectAdminCampaignDetailById(String cmpnId) {
		return super.getSqlSession().selectOne(this.NAME_SPACE + "selectAdminCampaignDetailById", cmpnId);
	}

}
