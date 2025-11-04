package com.ktdsuniversity.edu.domain.pay.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktdsuniversity.edu.domain.pay.dao.PayDao;
import com.ktdsuniversity.edu.domain.pay.service.PayService;
import com.ktdsuniversity.edu.global.common.CommonCodeVO;

@Service
public class PayServiceImpl implements PayService{
	
	private static final Logger log = LoggerFactory.getLogger(PayServiceImpl.class);

    @Autowired
    private PayDao payDao;
	@Override
	public List<CommonCodeVO> payInfoService() {
		
		List<CommonCodeVO> commonCodeVoList = this.payDao.selectPayInfo();
		
		return commonCodeVoList;
	}
	@Override
	public boolean ValidationPaySuccess(String paymentKey, String orderId, String orderName, Long easyAmount) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public int PaySuccess(String paymentKey, String orderId, String orderName, Long easyAmount) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
}
