package com.ktdsuniversity.edu.domain.inqr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ktdsuniversity.edu.domain.inqr.service.InqrService;
import com.ktdsuniversity.edu.domain.inqr.vo.request.RequestInqrCreateVO;
import com.ktdsuniversity.edu.domain.user.vo.UserVO;
import com.ktdsuniversity.edu.global.common.AjaxResponse;
import com.ktdsuniversity.edu.global.common.CommonCodeVO;

@Controller
@RequestMapping("/help/inqr")
public class InqrController {

    @Autowired
    private InqrService inqrService;
    
    /**
     * 1대1문의 작성 페이지
     */
    @GetMapping("/write")
    public String viewInqrWritePage(Model model) {
    	
    	List<CommonCodeVO> inqrCatergory = this.inqrService.readInqrCategory();
    	
    	model.addAttribute("category", inqrCatergory);
    	
    	return "inqr/write";
    }
    /**
     * 1대1문의 작성
     */
    @PostMapping("/write")
    @ResponseBody
    public AjaxResponse doCreateInqr(@SessionAttribute(name = "__LOGIN_USER__") UserVO loginUser
    		                       , RequestInqrCreateVO requestInqrCreateVO) {
    	String usrId = loginUser.getUsrId();
    	requestInqrCreateVO.setInqrUsrId(usrId);
    	boolean createResult = this.inqrService.createNewInqr(requestInqrCreateVO);
    	
    	AjaxResponse ajaxResponse = new AjaxResponse();
    	
    	if(createResult) {
    		ajaxResponse.setBody("문의 접수가 성공적으로 접수되었습니다!");
    	}
    	else {
    		ajaxResponse.setBody("문의 접수에 실패했습니다.");
    	}
    	return ajaxResponse;
    }
    
    
    /**
     * 1대1 문의 글 리스트 페이지
     */
    @GetMapping("/list")
    public String viewInqrListPage() {
    	return "inqr/list";
    }
    
    /**
     * 1대1 문의 상세보기 페이지 
     */
    @GetMapping("/view")
    public String viewInqrDetailViewPage() {
    	return "inqr/view";
    }
    
    

}