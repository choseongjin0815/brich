package com.ktdsuniversity.edu.domain.user.service;

import java.util.List;

import com.ktdsuniversity.edu.domain.blog.vo.RequestBlogInfoVO;
import com.ktdsuniversity.edu.domain.blog.vo.RequestBlogTitleVO;
import com.ktdsuniversity.edu.domain.user.vo.BlogCategoryVO;
import com.ktdsuniversity.edu.domain.user.vo.UserVO;
import com.ktdsuniversity.edu.domain.user.vo.request.RequestUserAccountPasswordVO;
import com.ktdsuniversity.edu.domain.user.vo.request.RequestUserFindIdVO;
import com.ktdsuniversity.edu.domain.user.vo.request.RequestUserInfoModifyVO;
import com.ktdsuniversity.edu.domain.user.vo.request.RequestUserLoginVO;
import com.ktdsuniversity.edu.domain.user.vo.request.RequestUserRegistVO;
import com.ktdsuniversity.edu.domain.user.vo.request.RequestUserResetPasswordVO;
import com.ktdsuniversity.edu.domain.user.vo.response.ResponseUserInfoVO;
import com.ktdsuniversity.edu.domain.user.vo.response.ResponseUserSubscriptionInfoVO;
import com.ktdsuniversity.edu.global.common.CommonCodeVO;

public interface UserService {

	public UserVO readUser(RequestUserLoginVO requestUserLoginVO);

	public boolean createNewUser(RequestUserRegistVO requestUserRegistVO);

	public int readUserIdByLogId(String logId);

	public String readLogIdByNameAndEmail(RequestUserFindIdVO requestUserFindIdVO);

	public boolean updatePswrdByLogIdAndPswrd(RequestUserResetPasswordVO resetPasswordInfo);

	public List<CommonCodeVO> readCategoryList();

	public UserVO readUserByLogId(String id);
	public ResponseUserInfoVO readUserByUserId(String usrId);

<<<<<<< HEAD
	public ResponseUserSubscriptionInfoVO readSubscriptionInfoByUserId(String usrId);

	public boolean updatePswrdByUsrId(RequestUserAccountPasswordVO requestUserAccountPasswordVO);

	public boolean updateUserInfoByUsrId(RequestUserInfoModifyVO requestUserInfoModifyVO);

=======
>>>>>>> d4232c45d637ccfb95a9109c73c5af652040cfbd
	public boolean updateBlogInfo(RequestBlogInfoVO request);

	public boolean updateBlogTitle(RequestBlogTitleVO request);

<<<<<<< HEAD

=======
>>>>>>> d4232c45d637ccfb95a9109c73c5af652040cfbd

}