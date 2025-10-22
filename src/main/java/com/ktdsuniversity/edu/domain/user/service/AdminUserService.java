package com.ktdsuniversity.edu.domain.user.service;

import java.util.List;
import java.util.Map;

import com.ktdsuniversity.edu.domain.user.vo.AdminUserBaseInfoVO;
import com.ktdsuniversity.edu.domain.user.vo.AdminUserListVO;

public interface AdminUserService {

	List<AdminUserListVO> readAdminUserList();

	AdminUserBaseInfoVO readAdminUserDetailById(String usrId);

	boolean updateAdvertiserRegistAuthCode(Map<String, String> requestData);

}
