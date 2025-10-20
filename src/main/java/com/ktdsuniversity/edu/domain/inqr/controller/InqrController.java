package com.ktdsuniversity.edu.domain.inqr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;

import com.ktdsuniversity.edu.domain.inqr.service.InqrService;

@Controller
public class InqrController {

    @Autowired
    private InqrService inqrService;

}