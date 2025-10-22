package com.ktdsuniversity.edu.domain.campaign.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.edu.domain.campaign.dao.CampaignDao;
import com.ktdsuniversity.edu.domain.campaign.vo.ApplicantVO;
import com.ktdsuniversity.edu.domain.campaign.vo.CampaignVO;
import com.ktdsuniversity.edu.domain.campaign.vo.request.RequestSearchCampaignVO;
import com.ktdsuniversity.edu.domain.campaign.vo.response.ResponseCampaignVO;
import com.ktdsuniversity.edu.global.common.CommonCodeVO;


@Repository
public class CampaignDaoImpl extends SqlSessionDaoSupport implements CampaignDao {

    private final String NAME_SPACE = "com.ktdsuniversity.edu.domain.campaign.dao.impl.CampaignDaoImpl.";

    @Autowired
    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

	@Override
	public CampaignVO selectCampaignDetailById(String campaignId) {
		return super.getSqlSession().selectOne(this.NAME_SPACE + "selectCampaignDetailById", campaignId);
	}

	@Override
	public List<CommonCodeVO> selectCategoryList() {
		return super.getSqlSession().selectList(this.NAME_SPACE + "selectCategoryList");
	}
	
	@Override
	public List<ApplicantVO> selectApplicantListByCmpnId(String cmpnId) {
		return super.getSqlSession().selectList(this.NAME_SPACE + "selectApplicantListByCmpnId", cmpnId);
	}

	@Override
	public String selectCmpnStateByCmpnId(String cmpnId) {
		return super.getSqlSession().selectOne(this.NAME_SPACE + "selectCmpnStateByCmpnId", cmpnId);
	}

	@Override
	public int updateAdptYnBycmpnApplyId(ApplicantVO applicantVO) {
		return super.getSqlSession().update(this.NAME_SPACE + "updateAdptYnBycmpnApplyId", applicantVO);
	}

	@Override
	public List<ResponseCampaignVO> selectCampaignListCategoryAndSortBy(RequestSearchCampaignVO requestSearchCampaignVO) {
		return super.getSqlSession().selectList(this.NAME_SPACE + "selectCampaignListCategoryAndSortBy", requestSearchCampaignVO);
	}

	@Override
	public String selectCategoryParent(String selectCategroy) {
		return super.getSqlSession().selectOne(this.NAME_SPACE + "selectCategoryParent", selectCategroy);
	}


}