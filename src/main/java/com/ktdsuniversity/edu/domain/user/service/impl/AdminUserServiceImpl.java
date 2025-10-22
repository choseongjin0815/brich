package com.ktdsuniversity.edu.domain.user.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ktdsuniversity.edu.domain.campaign.vo.CampaignVO;
import com.ktdsuniversity.edu.domain.user.dao.AdminUserDao;
import com.ktdsuniversity.edu.domain.user.service.AdminUserService;
import com.ktdsuniversity.edu.domain.user.vo.AdminAdvertiserDetailVO;
import com.ktdsuniversity.edu.domain.user.vo.AdminBloggerAreaInfoVO;
import com.ktdsuniversity.edu.domain.user.vo.AdminBloggerCatagoryInfoVO;
import com.ktdsuniversity.edu.domain.user.vo.AdminBloggerDetailVO;
import com.ktdsuniversity.edu.domain.user.vo.AdminUserBaseInfoVO;
import com.ktdsuniversity.edu.domain.user.vo.AdminUserListVO;

@Service
public class AdminUserServiceImpl implements AdminUserService {

	@Autowired
	private AdminUserDao adminUserDao;

	@Override
	public List<AdminUserListVO> readAdminUserList() {
		
		List<AdminUserListVO> result = this.adminUserDao.selectAdminUserList();
		
		return result;
	}

	@Override
	public AdminUserBaseInfoVO readAdminUserDetailById(String usrId) {
		
		String userAutr = this.adminUserDao.selectAdminUserAutrById(usrId);
		
		if(userAutr.equals("1002") || userAutr.equals("1003")) {
			AdminBloggerDetailVO info = adminUserDao.selectAdminBloggerDetailById(usrId);
			
			List<CampaignVO> progressList = adminUserDao.selectBloggerCmpnProgressList(usrId);
		    List<CampaignVO> completedList = adminUserDao.selectBloggerCmpnCompletedList(usrId);
		    List<AdminBloggerAreaInfoVO> usrAr = adminUserDao.selectBloggerAreaList(usrId);
		    List<AdminBloggerCatagoryInfoVO> usrBlgCtg = adminUserDao.selectBloggerCategoryList(usrId);
		    
		    info.setCmpnProgressList(progressList);
		    info.setCmpnCompletedList(completedList);
		    info.setUsrAr(usrAr);
		    info.setUsrBlgCtg(usrBlgCtg);
			
			return info;
		}
		else if(userAutr.equals("1004") || userAutr.equals("1007")) {
			AdminAdvertiserDetailVO info = adminUserDao.selectAdminAdvertiserDetailById(usrId);
			
			List<CampaignVO> progressList = adminUserDao.selectAdvertiserCmpnProgressList(usrId);
		    List<CampaignVO> completedList = adminUserDao.selectAdvertiserCmpnCompletedList(usrId);
		    // List<FileGroupVO> fileList = adminUserDao.selectAdminUserFileList(usrId);
		    
		    info.setCmpnProgressList(progressList);
		    info.setCmpnCompletedList(completedList);
		    // info.setFlGrpId(fileList);
			
			return info;
		}
		else {
			// 임시 코드
			throw new IllegalArgumentException("error");
		}
		
	}

	@Override
	public boolean updateAdvertiserRegistAuthCode(Map<String, String> requestData) {
		
		return this.adminUserDao.updateAdvertiserRegistAuthCode(requestData) > 0;
	}
	
}
