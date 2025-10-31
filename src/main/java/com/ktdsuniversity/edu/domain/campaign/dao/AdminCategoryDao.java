package com.ktdsuniversity.edu.domain.campaign.dao;

import java.util.List;

import com.ktdsuniversity.edu.domain.campaign.vo.AdminCampaignCategoryVO;

public interface AdminCategoryDao {

	List<AdminCampaignCategoryVO> selectCampaignCategoryList();

}
