package com.ktdsuniversity.edu.domain.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ktdsuniversity.edu.domain.user.service.AdminUserService;
import com.ktdsuniversity.edu.domain.user.vo.AdminUserBaseInfoVO;
import com.ktdsuniversity.edu.domain.user.vo.AdminUserListVO;

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
	
	@GetMapping("/admin/user_detail/{usrId}")
	public String viewAdminUserDetailPage(@PathVariable String usrId, Model model) {
		
		AdminUserBaseInfoVO userInfo = this.adminUserService.readAdminUserDetailById(usrId);
		
		model.addAttribute("userInfo", userInfo);
		
		return "/user/admin_user_detail";
	}
	
	
	
}
