package com.ktdsuniversity.edu.domain.faq.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.ktdsuniversity.edu.domain.faq.dao.FaqDao;
import com.ktdsuniversity.edu.domain.faq.service.FaqService;

@Service
public class FaqServiceImpl implements FaqService {

    @Autowired
    private FaqDao faqDao;

}