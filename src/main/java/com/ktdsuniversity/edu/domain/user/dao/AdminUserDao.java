package com.ktdsuniversity.edu.domain.user.dao;

import java.util.List;
import java.util.Map;

import com.ktdsuniversity.edu.domain.campaign.vo.CampaignVO;
import com.ktdsuniversity.edu.domain.file.vo.FileGroupVO;
import com.ktdsuniversity.edu.domain.user.vo.AdminAdvertiserDetailVO;
import com.ktdsuniversity.edu.domain.user.vo.AdminBloggerAreaInfoVO;
import com.ktdsuniversity.edu.domain.user.vo.AdminBloggerCategoryInfoVO;
import com.ktdsuniversity.edu.domain.user.vo.AdminBloggerDetailVO;
import com.ktdsuniversity.edu.domain.user.vo.AdminUserListVO;
import com.ktdsuniversity.edu.global.common.CommonCodeVO;

public interface AdminUserDao {

	List<AdminUserListVO> selectAdminUserList();
	
	List<AdminUserListVO> selectAdminBloggerList();

	List<AdminUserListVO> selectAdminAdvertiserList();

	String selectAdminUserAutrById(String usrId);

	AdminBloggerDetailVO selectAdminBloggerDetailById(String usrId);

	AdminAdvertiserDetailVO selectAdminAdvertiserDetailById(String usrId);

	List<CampaignVO> selectBloggerCmpnProgressList(String usrId);

	List<CampaignVO> selectBloggerCmpnCompletedList(String usrId);

	List<AdminBloggerAreaInfoVO> selectBloggerAreaList(String usrId);

	List<AdminBloggerCategoryInfoVO> selectBloggerCategoryList(String usrId);

	List<CampaignVO> selectAdvertiserCmpnProgressList(String usrId);

	List<CampaignVO> selectAdvertiserCmpnCompletedList(String usrId);

	List<FileGroupVO> selectAdminUserFileList(String usrId);

	int updateAdvertiserAuthCodeByApprove(Map<String, String> requestData);

	int updateAdvertiserAuthCodeByReject(Map<String, String> requestData);

	List<CommonCodeVO> selectBlogCategoryList();

}
