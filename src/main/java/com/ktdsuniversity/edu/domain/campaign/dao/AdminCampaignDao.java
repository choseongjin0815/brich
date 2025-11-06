package com.ktdsuniversity.edu.domain.campaign.dao;

import java.util.List;

import com.ktdsuniversity.edu.domain.campaign.vo.request.RequestAdminSearchCampaignVO;
import com.ktdsuniversity.edu.domain.campaign.vo.response.ResponseAdminCampaignVO;

public interface AdminCampaignDao {

	String selectAdminCategoryParent(String category);

	List<ResponseAdminCampaignVO> selectAdminCampaignListCategoryAndSortBy(RequestAdminSearchCampaignVO requestAdminSearchCampaignVO);

	ResponseAdminCampaignVO selectAdminCampaignDetailById(String cmpnId);

}
