package com.ktdsuniversity.edu.domain.report.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ktdsuniversity.edu.domain.report.service.ReportService;
import com.ktdsuniversity.edu.domain.report.vo.ReportVO;
import com.ktdsuniversity.edu.domain.report.vo.request.RequestReportCreateVO;
import com.ktdsuniversity.edu.domain.report.vo.response.ResponseReportVO;
import com.ktdsuniversity.edu.domain.user.service.UserService;
import com.ktdsuniversity.edu.domain.user.vo.UserVO;
import com.ktdsuniversity.edu.global.common.AjaxResponse;

/**
 * 신고작성
 * 단순 조회만 있으므로 auth prefix 제외함
 */
@Controller
@RequestMapping("/report")
public class ReportController {

	private static final Logger log = LoggerFactory.getLogger(ReportController.class);

	
    @Autowired
    private ReportService reportService;
    
    @Autowired
    private UserService userService;
    
    @GetMapping("/list")
    public String viewMyReportListPage(@SessionAttribute(name = "__LOGIN_USER__") UserVO loginUser) {
    	String usrId = loginUser.getUsrId();
    	return "report/list";
    }
    
    /**
     * 신고글 작성 페이지
     */
    @GetMapping("/write/{targetUsrId}")
    public String viewReportWritePage(@PathVariable String targetUsrId
    								, Model model
    								, ReportVO reportVO) { 
    	ResponseReportVO report = this.reportService.readTargetInfoByTargetUsrId(targetUsrId);
        	
    	model.addAttribute("reportInfo", report);
    	
    	return "report/write";
    }
    
    /*
     * 신고글 작성
     */
    @PostMapping("/write")
    @ResponseBody
    public AjaxResponse doWriteReportAction(RequestReportCreateVO requestReportCreateVO
    									  , @SessionAttribute(name = "__LOGIN_USER__") UserVO loginUser) {
    	String usrId = loginUser.getUsrId();
    	requestReportCreateVO.setRptrUsrId(usrId);
    	
    	boolean reportResult = this.reportService.createNewReport(requestReportCreateVO);
    	
    	AjaxResponse ajaxResponse = new AjaxResponse();
    	
    	if(reportResult) {
    		ajaxResponse.setBody("신고가 성공적으로 접수되었습니다!");
    	}
    	else {
    		ajaxResponse.setBody("신고 접수에 실패했습니다.");
    	}
    	
    	return ajaxResponse;
    }
    
   

}