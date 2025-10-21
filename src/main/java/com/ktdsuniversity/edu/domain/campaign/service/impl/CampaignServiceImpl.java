package com.ktdsuniversity.edu.domain.campaign.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktdsuniversity.edu.domain.campaign.dao.CampaignDao;
import com.ktdsuniversity.edu.domain.campaign.service.CampaignService;

@Service
public class CampaignServiceImpl implements CampaignService {

    @Autowired
    private CampaignDao campaignDao;

}