package com.ktdsuniversity.edu.domain.campaign.service;

import com.ktdsuniversity.edu.domain.campaign.vo.request.RequestAdminCamapaignRejectVO;
import com.ktdsuniversity.edu.domain.campaign.vo.request.RequestAdminCampaignApplicantVO;
import com.ktdsuniversity.edu.domain.campaign.vo.request.RequestAdminCampaignApproveVO;
import com.ktdsuniversity.edu.domain.campaign.vo.request.RequestAdminSearchCampaignVO;
import com.ktdsuniversity.edu.domain.campaign.vo.response.ResponseAdminCampaignApplicantListVO;
import com.ktdsuniversity.edu.domain.campaign.vo.response.ResponseAdminCampaignListVO;
import com.ktdsuniversity.edu.domain.campaign.vo.response.ResponseAdminCampaignVO;

public interface AdminCampaignService {

	ResponseAdminCampaignListVO readAdminCampaignListAndCategory(RequestAdminSearchCampaignVO requestAdminSearchCampaignVO);

	ResponseAdminCampaignVO readAdminCampaignDetail(String cmpnId);

	boolean updateAdminCampaignReject(RequestAdminCamapaignRejectVO rejectInfo);

	boolean updateAdminCampaignApprove(RequestAdminCampaignApproveVO approveInfo);

	ResponseAdminCampaignApplicantListVO readAdminCampaignApplicantListById(RequestAdminCampaignApplicantVO requestApplicantVO);

}
