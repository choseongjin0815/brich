package com.ktdsuniversity.edu.domain.user.service;

import java.util.List;
import java.util.Map;

import com.ktdsuniversity.edu.domain.user.vo.AdminUserBaseInfoVO;
import com.ktdsuniversity.edu.domain.user.vo.AdminUserListVO;
import com.ktdsuniversity.edu.global.common.CommonCodeVO;

public interface AdminUserService {


	AdminUserBaseInfoVO readAdminUserDetailById(String usrId);

	boolean updateAdvertiserRegistAuthCode(Map<String, String> requestData);

	List<AdminUserListVO> readAdminUserList(String tab);

	List<CommonCodeVO> readBlogCategoryList();

}
