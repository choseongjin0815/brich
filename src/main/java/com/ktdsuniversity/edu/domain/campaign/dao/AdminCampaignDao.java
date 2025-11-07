package com.ktdsuniversity.edu.domain.campaign.dao;

import java.util.List;

import com.ktdsuniversity.edu.domain.campaign.vo.CampaignVO;
import com.ktdsuniversity.edu.domain.campaign.vo.request.RequestAdminCamapaignRejectVO;
import com.ktdsuniversity.edu.domain.campaign.vo.request.RequestAdminCampaignApplicantVO;
import com.ktdsuniversity.edu.domain.campaign.vo.request.RequestAdminCampaignApproveVO;
import com.ktdsuniversity.edu.domain.campaign.vo.request.RequestAdminSearchCampaignVO;
import com.ktdsuniversity.edu.domain.campaign.vo.response.ResponseAdminCampaignApplicantVO;
import com.ktdsuniversity.edu.domain.campaign.vo.response.ResponseAdminCampaignVO;

public interface AdminCampaignDao {

	String selectAdminCategoryParent(String category);

	List<ResponseAdminCampaignVO> selectAdminCampaignListCategoryAndSortBy(RequestAdminSearchCampaignVO requestAdminSearchCampaignVO);

	ResponseAdminCampaignVO selectAdminCampaignDetailById(String cmpnId);

	CampaignVO selectAdminCampaignAllInfoById(String cmpnId);

	int updateAdminCampaignByRejectInfo(RequestAdminCamapaignRejectVO rejectInfo);

	int updateAdminCampaignByApproveInfo(RequestAdminCampaignApproveVO approveInfo);

	int selectAdminCampaignApplicantCountByCmpnId(RequestAdminCampaignApplicantVO requestApplicantVO);

	List<ResponseAdminCampaignApplicantVO> selectAdminCampaignApplicantListByCmpnId(
			RequestAdminCampaignApplicantVO requestApplicantVO);

}
