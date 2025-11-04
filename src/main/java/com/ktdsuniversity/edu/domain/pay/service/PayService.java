package com.ktdsuniversity.edu.domain.pay.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ktdsuniversity.edu.global.common.CommonCodeVO;

public interface PayService {

	List<CommonCodeVO> payInfoService();

	boolean ValidationPaySuccess(String paymentKey, String orderId, String orderName, Long easyAmount);

	int PaySuccess(String paymentKey, String orderId, String orderName, Long easyAmount);

}
