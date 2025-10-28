package com.ktdsuniversity.edu.domain.user.service;

import java.util.List;

import com.ktdsuniversity.edu.domain.user.vo.BlogCategoryVO;
import com.ktdsuniversity.edu.domain.user.vo.UserVO;
import com.ktdsuniversity.edu.domain.user.vo.request.RequestUserFindIdVO;
import com.ktdsuniversity.edu.domain.user.vo.request.RequestUserLoginVO;
import com.ktdsuniversity.edu.domain.user.vo.request.RequestUserRegistVO;
import com.ktdsuniversity.edu.domain.user.vo.request.RequestUserResetPasswordVO;
import com.ktdsuniversity.edu.global.common.CommonCodeVO;

public interface UserService {

	public UserVO readUser(RequestUserLoginVO requestUserLoginVO);

	public boolean createNewUser(RequestUserRegistVO requestUserRegistVO);

	public int readUserIdByLogId(String logId);

	public String readLogIdByNameAndEmail(RequestUserFindIdVO requestUserFindIdVO);

	public boolean updatePswrdByLogIdAndPswrd(RequestUserResetPasswordVO resetPasswordInfo);

	public List<CommonCodeVO> readCategoryList();


}