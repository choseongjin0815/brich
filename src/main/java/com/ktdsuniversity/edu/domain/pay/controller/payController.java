package com.ktdsuniversity.edu.domain.pay.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.ktdsuniversity.edu.domain.pay.service.PayService;
import com.ktdsuniversity.edu.domain.user.vo.UserVO;
import com.ktdsuniversity.edu.global.common.CommonCodeVO;

@Controller
public class payController {
	
	private static final Logger log = LoggerFactory.getLogger(payController.class);
	
	@Autowired
    private PayService payService;
    
    @GetMapping("/blgr/pay/subscribe")
    public String subscribePayPage( Model model, @SessionAttribute(value = "__LOGIN_USER__", required = false) UserVO loginUser) {
    	
    	List<CommonCodeVO> commonCodeVoList = this.payService.payInfoService();
    	model.addAttribute("payInfoList", commonCodeVoList);
    	
    	log.info("결제정보 : " + commonCodeVoList.toString());
    	
    	return "pay/subscribe";
    }

}
