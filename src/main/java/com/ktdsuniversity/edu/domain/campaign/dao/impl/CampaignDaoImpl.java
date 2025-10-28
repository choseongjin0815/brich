package com.ktdsuniversity.edu.domain.campaign.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.edu.domain.campaign.dao.CampaignDao;
import com.ktdsuniversity.edu.domain.campaign.vo.CampaignVO;
import com.ktdsuniversity.edu.domain.campaign.vo.request.RequestApplicantVO;
import com.ktdsuniversity.edu.domain.campaign.vo.request.RequestSearchCampaignVO;
import com.ktdsuniversity.edu.domain.campaign.vo.response.ResponseAdoptVO;
import com.ktdsuniversity.edu.domain.campaign.vo.response.ResponseApplicantVO;
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
	public ResponseCampaignVO selectCampaignDetailById(String campaignId) {
		return super.getSqlSession().selectOne(this.NAME_SPACE + "selectCampaignDetailById", campaignId);
	}

	@Override
	public List<CommonCodeVO> selectCategoryList() {
		return super.getSqlSession().selectList(this.NAME_SPACE + "selectCategoryList");
	}
	
	@Override
	public List<ResponseApplicantVO> selectApplicantListByCmpnId(RequestApplicantVO requestApplicantVO) {
		return super.getSqlSession().selectList(this.NAME_SPACE + "selectApplicantListByCmpnId", requestApplicantVO);
	}

	@Override
	public int updateAdptYnByCmpnPstAdptId(RequestApplicantVO requestApplicantVO) {
		return super.getSqlSession().update(this.NAME_SPACE + "updateAdptYnByCmpnPstAdptId", requestApplicantVO);
	}

	@Override
	public int selectAdoptCountByCmpnId(String cmpnId) {
		return super.getSqlSession().selectOne(this.NAME_SPACE + "selectAdoptCountByCmpnId", cmpnId);
	}

	@Override
	public CampaignVO selectCampaignInfoByCmpnId(String cmpnId) {
		return super.getSqlSession().selectOne(this.NAME_SPACE + "selectCampaignInfoByCmpnId", cmpnId);
	}

	@Override
	public int selectApplicantCountByCmpnId(RequestApplicantVO requestApplicantVO) {
		return super.getSqlSession().selectOne(this.NAME_SPACE + "selectApplicantCountByCmpnId", requestApplicantVO);
	}
	public List<ResponseCampaignVO> selectCampaignListCategoryAndSortBy(RequestSearchCampaignVO requestSearchCampaignVO) {
		return super.getSqlSession().selectList(this.NAME_SPACE + "selectCampaignListCategoryAndSortBy", requestSearchCampaignVO);
	}

	@Override
	public String selectCategoryParent(String selectCategroy) {
		return super.getSqlSession().selectOne(this.NAME_SPACE + "selectCategoryParent", selectCategroy);
	}

	@Override
	public List<CampaignVO> selectExpireSoonCampaign() {
		return super.getSqlSession().selectList(this.NAME_SPACE + "selectExpireSoonCampaign");
	}


	@Override
	public String selectCampaignStateByCmpnPstAdptId(String cmpnPstAdptId) {
		return super.getSqlSession().selectOne(this.NAME_SPACE + "selectCampaignStateByCmpnPstAdptId", cmpnPstAdptId);
	}

	@Override
	public List<ResponseAdoptVO> selectAdoptListByCmpnId(RequestApplicantVO requestApplicantVO) {
		return super.getSqlSession().selectList(this.NAME_SPACE + "selectAdoptListByCmpnId", requestApplicantVO);
	}
	
	/**
	 * 공통코드 이름 출력
	 */
	@Override
	public String selectCampaignChangeSttsCd(String sttsCd) {
		return super.getSqlSession().selectOne(this.NAME_SPACE + "selectCampaignChangeSttsCd", sttsCd);
	}

	@Override
	public List<ResponseCampaignVO> selectSubmittedMyCampaignByBlgId(String blgId) {
		return super.getSqlSession().selectOne(this.NAME_SPACE + "selectSubmittedMyCampaignByBlgId", blgId);
	}
	

}