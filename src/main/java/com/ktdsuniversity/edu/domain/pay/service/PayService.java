package com.ktdsuniversity.edu.domain.pay.service;

import java.util.List;

import com.ktdsuniversity.edu.domain.pay.vo.request.RequestPaymentVO;
import com.ktdsuniversity.edu.global.common.CommonCodeVO;

public interface PayService {

	List<CommonCodeVO> payInfoServiceList();

	boolean paymentValidationCheck(String orderId, String paymentKey, String orderName, Long easyAmount);

	int paymentSuccessUpdate(String orderId, String paymentKey, String orderName, Long easyAmount);

	CommonCodeVO payInfoService(String cdId);

	String beforePaymentInfoSave(RequestPaymentVO requestPaymentVO);


}
