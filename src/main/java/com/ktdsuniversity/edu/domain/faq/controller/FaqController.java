package com.ktdsuniversity.edu.domain.faq.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;

import com.ktdsuniversity.edu.domain.faq.service.FaqService;

@Controller
@RequestMapping("/help")
public class FaqController {

    @Autowired
    private FaqService faqService;
    
    @GetMapping("/faq")
    public String viewFaqPage() {
    	
    	
    	
    	return "faq/faq";
    }
    
}