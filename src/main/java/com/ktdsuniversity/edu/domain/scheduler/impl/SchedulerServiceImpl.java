package com.ktdsuniversity.edu.domain.scheduler.impl;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


import com.ktdsuniversity.edu.domain.scheduler.SchedulerService;

@Service
public class SchedulerServiceImpl implements SchedulerService{
	
	@Scheduled(cron = "0 30 23 * * *")
	public boolean blogScheduler() {
		
		
		
		return true;
	}

}
