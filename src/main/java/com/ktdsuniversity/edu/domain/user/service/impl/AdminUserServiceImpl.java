package com.ktdsuniversity.edu.domain.user.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ktdsuniversity.edu.domain.campaign.vo.CampaignVO;
import com.ktdsuniversity.edu.domain.user.dao.AdminUserDao;
import com.ktdsuniversity.edu.domain.user.service.AdminUserService;
import com.ktdsuniversity.edu.domain.user.vo.AdminAdvertiserDetailVO;
import com.ktdsuniversity.edu.domain.user.vo.AdminBloggerAreaInfoVO;
import com.ktdsuniversity.edu.domain.user.vo.AdminBloggerCategoryInfoVO;
import com.ktdsuniversity.edu.domain.user.vo.AdminBloggerDetailVO;
import com.ktdsuniversity.edu.domain.user.vo.AdminUserBaseInfoVO;
import com.ktdsuniversity.edu.domain.user.vo.AdminUserListVO;
import com.ktdsuniversity.edu.global.common.CommonCodeVO;

@Service
public class AdminUserServiceImpl implements AdminUserService {

	@Autowired
	private AdminUserDao adminUserDao;
	
	@Override
	public List<AdminUserListVO> readAdminUserList(String tab) {
		
		if(tab.equals("all")) {
			return this.adminUserDao.selectAdminUserList();
		}
		else if(tab.equals("blogger")) {
			return this.adminUserDao.selectAdminBloggerList();
		}
		else if(tab.equals("advertiser")) {
			return this.adminUserDao.selectAdminAdvertiserList();
		}
		else {
			throw new IllegalArgumentException("error");
		}
		
	}

	@Override
	public AdminUserBaseInfoVO readAdminUserDetailById(String usrId) {
		
		String userAutr = this.adminUserDao.selectAdminUserAutrById(usrId);
		
		if(userAutr.equals("1002") || userAutr.equals("1003")) {
			AdminBloggerDetailVO info = adminUserDao.selectAdminBloggerDetailById(usrId);
			
			List<CampaignVO> progressList = adminUserDao.selectBloggerCmpnProgressList(usrId);
		    List<CampaignVO> completedList = adminUserDao.selectBloggerCmpnCompletedList(usrId);
		    List<AdminBloggerAreaInfoVO> usrAr = adminUserDao.selectBloggerAreaList(usrId);
		    List<AdminBloggerCategoryInfoVO> usrBlgCtg = adminUserDao.selectBloggerCategoryList(usrId);
		    
		    info.setCmpnProgressList(progressList);
		    info.setCmpnCompletedList(completedList);
		    info.setUsrAr(usrAr);
		    info.setUsrBlgCtg(usrBlgCtg);
		    
		    /*
	        if (usrAr != null) {
	            List<String> checkedAreaIds = usrAr.stream()
	                .map(AdminBloggerAreaInfoVO::getArId)
	                .collect(Collectors.toList());
	            info.setCheckedAreaIds(checkedAreaIds);
	        }
	        */
		    
	        if (usrBlgCtg != null) {
	            List<String> checkedCategory = 
	            		usrBlgCtg.stream()
	            				 .map(AdminBloggerCategoryInfoVO::getCdId)
	            				 .collect(Collectors.toList());
	            info.setCheckedBlgCtg(checkedCategory);
	            
	            // System.out.println("기존 카테고리 " + info.getCheckedBlgCtg());
	        }
	        
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

	@Transactional
	@Override
	public boolean updateAdvertiserRegistAuthCode(Map<String, String> requestData) {
		
		String changeAutr = requestData.get("autr");
		
		if(changeAutr.equals("1004")) {
			return this.adminUserDao.updateAdvertiserAuthCodeByApprove(requestData) > 0;
		}
		else if(changeAutr.equals("1008")) {
			return this.adminUserDao.updateAdvertiserAuthCodeByReject(requestData) > 0;
		}
		else {
			// 임시 코드
			throw new IllegalArgumentException("error");
		}
	}

	@Override
	public List<CommonCodeVO> readBlogCategoryList() {
		return this.adminUserDao.selectBlogCategoryList();
	}

}
