package com.ktdsuniversity.edu.domain.faq.controller;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;

import com.ktdsuniversity.edu.domain.faq.service.FaqService;

@Controller
public class FaqController {

    @Autowired
    private FaqService faqService;

}