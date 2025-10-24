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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ktdsuniversity.edu.domain.user.service.AdminUserService;
import com.ktdsuniversity.edu.domain.user.vo.AdminUserBaseInfoVO;
import com.ktdsuniversity.edu.domain.user.vo.AdminUserListVO;
import com.ktdsuniversity.edu.global.common.AjaxResponse;
import com.ktdsuniversity.edu.global.common.CommonCodeVO;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class AdminUserController {

	@Autowired
	private AdminUserService adminUserService;
	
	/**
	 * 회원 관리 - 회원(전체/블로거/광고주) 목록
	 * @param tab
	 * @param model
	 * @return
	 */
	@GetMapping("/admin/user_list")
	public String viewAdminUserListPage(
			@RequestParam(required = false, defaultValue = "all") String tab, Model model) {
		
		if (tab == null || tab.isEmpty()) {
	        tab = "all";
	    }
	    
	    List<AdminUserListVO> userList = this.adminUserService.readAdminUserList(tab);
	    
	    model.addAttribute("userList", userList);
	    model.addAttribute("currentTab", tab); 
	    
	    return "/user/admin_user_list";
	}
	
	/**
	 * 회원 관리 - 상세 정보 페이지
	 * @param usrId
	 * @param model
	 * @return 
	 */
	@GetMapping("/admin/user_detail/{usrId}")
	public String viewAdminUserDetailPage(@PathVariable String usrId, Model model, 
														HttpServletRequest request) {
		
		AdminUserBaseInfoVO userInfo = this.adminUserService.readAdminUserDetailById(usrId);
		
		model.addAttribute("userInfo", userInfo);
		model.addAttribute("classType", userInfo.getClass().getSimpleName());
		model.addAttribute("pathInfo", request.getServletPath());
		
		return "/user/admin_user_detail";
	}
	
	/**
	 * 회원 관리 
	 * @param requestData
	 * @return Map<usrId, autr>
	 */
	@ResponseBody
	@PostMapping("/admin/advertiser_regist_process")
    public AjaxResponse doAdminUpdateAdvertiserRegistProcessAction(@RequestBody Map<String, String> requestData) {
		
    	boolean isSuccess = this.adminUserService.updateAdvertiserRegistAuthCode(requestData);
    	
    	AjaxResponse ajaxResponse = new AjaxResponse();
    	ajaxResponse.setBody(isSuccess);
    	
    	return ajaxResponse;
    }
	
	/**
	 * 회원 관리 - 정보 수정 페이지
	 * @return
	 */
	@GetMapping("/admin/user_modify/{usrId}")
	public String viewAdminUserInfoModifyPage(@PathVariable String usrId, Model model, 
															HttpServletRequest request) {
		
		AdminUserBaseInfoVO userInfo = this.adminUserService.readAdminUserDetailById(usrId);
		
		model.addAttribute("userInfo", userInfo);
		model.addAttribute("classType", userInfo.getClass().getSimpleName());
		model.addAttribute("pathInfo", request.getServletPath());

		List<CommonCodeVO> BlogcategoryList = this.adminUserService.readBlogCategoryList();
		model.addAttribute("BlogcategoryList", BlogcategoryList);
		
		return "/user/admin_user_info_modify";
	}
	
}
