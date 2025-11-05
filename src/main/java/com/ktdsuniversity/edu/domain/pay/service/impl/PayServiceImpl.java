package com.ktdsuniversity.edu.domain.pay.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktdsuniversity.edu.domain.pay.dao.PayDao;
import com.ktdsuniversity.edu.domain.pay.service.PayService;
import com.ktdsuniversity.edu.domain.pay.vo.request.RequestPaymentVO;
import com.ktdsuniversity.edu.global.common.CommonCodeVO;
import com.ktdsuniversity.edu.global.util.SessionUtil;

@Service
public class PayServiceImpl implements PayService{
	
	private static final Logger log = LoggerFactory.getLogger(PayServiceImpl.class);

    @Autowired
    private PayDao payDao;
	@Override
	public List<CommonCodeVO> payInfoServiceList() {
		
		List<CommonCodeVO> commonCodeVoList = this.payDao.selectPayInfoList();
		
		return commonCodeVoList;
	}
	
	
	@Override
	public boolean paymentValidationCheck(String orderId, String paymentKey, String orderName, Long easyAmount) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public int paymentSuccessUpdate(String orderId, String paymentKey, String orderName, Long easyAmount) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public CommonCodeVO payInfoService(String cdId) {
		CommonCodeVO commonCodeVO = this.payDao.selectPayInfo(cdId);		

		return commonCodeVO;
	}


	@Override
	public int beforePaymentInfoSave(RequestPaymentVO requestPaymentVO) {
		
		// 구독인지 캠페인 결제인지 확인
		int count;
		String autr = SessionUtil.getLoginObject().getAutr();
		if(autr.equals("1002") || autr.equals("1003")) {  //구독
			count = this.payDao.insertBeforeSubscribePaymentInfoSave(requestPaymentVO);
		} else if (autr.equals("1004")){
			count = this.payDao.insertBeforeCampaignPaymentInfoSave(requestPaymentVO);
		} else {
			return 0;
		}
		
		return count;
	}
	
	
}
