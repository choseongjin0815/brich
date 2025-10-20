package com.ktdsuniversity.edu.domain.campaign.controller;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;

import com.ktdsuniversity.edu.domain.campaign.service.CampaignService;

@Controller
public class CampaignController {

    @Autowired
    private CampaignService campaignService;

}