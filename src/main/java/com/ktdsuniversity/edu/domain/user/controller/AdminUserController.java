package com.ktdsuniversity.edu.domain.user.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ktdsuniversity.edu.domain.user.service.AdminUserService;
import com.ktdsuniversity.edu.domain.user.vo.AdminUserBaseInfoVO;
import com.ktdsuniversity.edu.domain.user.vo.AdminUserListVO;
import com.ktdsuniversity.edu.global.common.AjaxResponse;

@Controller
public class AdminUserController {

	@Autowired
	private AdminUserService adminUserService;
	
	/**
	 * 회원 관리 - 회원 목록 페이지
	 * @param model
	 * @return
	 */
	@GetMapping("/admin/user_list")
	public String viewAdminUserListPage(Model model) {
		
		List<AdminUserListVO> userList = this.adminUserService.readAdminUserList();
		
		model.addAttribute("userList", userList);
		
		return "/user/admin_user_list";
	}
	
	/**
	 * 회원 관리 - 회원 상세 정보 페이지
	 * @param usrId
	 * @param model
	 * @return
	 */
	@GetMapping("/admin/user_detail/{usrId}")
	public String viewAdminUserDetailPage(@PathVariable String usrId, Model model) {
		
		AdminUserBaseInfoVO userInfo = this.adminUserService.readAdminUserDetailById(usrId);
		
		model.addAttribute("userInfo", userInfo);
		
		model.addAttribute("classType", userInfo.getClass().getSimpleName());
		
		return "/user/admin_user_detail";
	}
	
	@ResponseBody
	@PostMapping("/admin/advertiser_regist_process")
    public AjaxResponse doAdminUpdateAdvertiserRegistProcessAction(@RequestBody Map<String, String> requestData) {
		
    	boolean isSuccess = this.adminUserService.updateAdvertiserRegistAuthCode(requestData);
    	
    	AjaxResponse ajaxResponse = new AjaxResponse();
    	ajaxResponse.setBody(isSuccess);
    	
    	return ajaxResponse;
    }
	
	@GetMapping("/admin/user_detail/info_modify/{usrId}")
	public String viewAdminUserInfoModifyPage() {
		return "/user/admin_info_modify";
	}
	
}
