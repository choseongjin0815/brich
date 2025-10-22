package com.ktdsuniversity.edu.domain.user.dao;

import java.util.List;
import java.util.Map;

import com.ktdsuniversity.edu.domain.campaign.vo.CampaignVO;
import com.ktdsuniversity.edu.domain.file.vo.FileGroupVO;
import com.ktdsuniversity.edu.domain.user.vo.AdminAdvertiserDetailVO;
import com.ktdsuniversity.edu.domain.user.vo.AdminBloggerAreaInfoVO;
import com.ktdsuniversity.edu.domain.user.vo.AdminBloggerCatagoryInfoVO;
import com.ktdsuniversity.edu.domain.user.vo.AdminBloggerDetailVO;
import com.ktdsuniversity.edu.domain.user.vo.AdminUserListVO;

public interface AdminUserDao {

	List<AdminUserListVO> selectAdminUserList();

	String selectAdminUserAutrById(String usrId);

	AdminBloggerDetailVO selectAdminBloggerDetailById(String usrId);

	AdminAdvertiserDetailVO selectAdminAdvertiserDetailById(String usrId);

	List<CampaignVO> selectBloggerCmpnProgressList(String usrId);

	List<CampaignVO> selectBloggerCmpnCompletedList(String usrId);

	List<AdminBloggerAreaInfoVO> selectBloggerAreaList(String usrId);

	List<AdminBloggerCatagoryInfoVO> selectBloggerCategoryList(String usrId);

	List<CampaignVO> selectAdvertiserCmpnProgressList(String usrId);

	List<CampaignVO> selectAdvertiserCmpnCompletedList(String usrId);

	List<FileGroupVO> selectAdminUserFileList(String usrId);

	int updateAdvertiserRegistAuthCode(Map<String, String> requestData);

}
