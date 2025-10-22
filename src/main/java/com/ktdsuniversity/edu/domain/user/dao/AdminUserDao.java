package com.ktdsuniversity.edu.domain.user.dao;

import java.util.List;

import com.ktdsuniversity.edu.domain.campaign.vo.CampaignVO;
import com.ktdsuniversity.edu.domain.file.vo.FileGroupVO;
import com.ktdsuniversity.edu.domain.user.vo.AdminAdvertiserDetailVO;
import com.ktdsuniversity.edu.domain.user.vo.AdminBloggerDetailVO;
import com.ktdsuniversity.edu.domain.user.vo.AdminUserListVO;
import com.ktdsuniversity.edu.domain.user.vo.BlogCategoryVO;
import com.ktdsuniversity.edu.domain.user.vo.UserAreaVO;

public interface AdminUserDao {

	List<AdminUserListVO> selectAdminUserList();

	String selectAdminUserAutrById(String usrId);

	AdminBloggerDetailVO selectAdminBloggerDetailById(String usrId);

	AdminAdvertiserDetailVO selectAdminAdvertiserDetailById(String usrId);

	List<CampaignVO> selectBloggerCmpnProgressList(String usrId);

	List<CampaignVO> selectBloggerCmpnCompletedList(String usrId);

	List<UserAreaVO> selectBloggerAreaList(String usrId);

	List<BlogCategoryVO> selectBloggerCategoryList(String usrId);

	List<CampaignVO> selectAdvertiserCmpnProgressList(String usrId);

	List<CampaignVO> selectAdvertiserCmpnCompletedList(String usrId);

	List<FileGroupVO> selectAdminUserFileList(String usrId);

}
