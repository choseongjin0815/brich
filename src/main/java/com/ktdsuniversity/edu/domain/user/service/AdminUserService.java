package com.ktdsuniversity.edu.domain.user.service;

import java.util.List;

import com.ktdsuniversity.edu.domain.user.vo.AdminUserBaseInfoVO;
import com.ktdsuniversity.edu.domain.user.vo.AdminUserListVO;

public interface AdminUserService {

	List<AdminUserListVO> readAdminUserList();

	AdminUserBaseInfoVO readAdminUserDetailById(String usrId);

}
