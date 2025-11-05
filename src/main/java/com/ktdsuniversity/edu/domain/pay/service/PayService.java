package com.ktdsuniversity.edu.domain.pay.service;

import java.util.List;

import com.ktdsuniversity.edu.domain.pay.vo.request.RequestPaymentVO;
import com.ktdsuniversity.edu.global.common.CommonCodeVO;

public interface PayService {

	List<CommonCodeVO> payInfoServiceList();

	boolean paymentValidationCheck(RequestPaymentVO requestPaymentVO);

	int paymentSuccessUpdate(RequestPaymentVO requestPaymentVO);

	CommonCodeVO payInfoService(String cdId);

	String beforePaymentInfoSave(RequestPaymentVO requestPaymentVO);


}
