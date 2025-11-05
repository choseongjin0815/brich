package com.ktdsuniversity.edu.domain.pay.dao;

import java.util.List;

import com.ktdsuniversity.edu.domain.pay.vo.request.RequestPaymentVO;
import com.ktdsuniversity.edu.global.common.CommonCodeVO;

public interface PayDao {

	List<CommonCodeVO> selectPayInfoList();

	CommonCodeVO selectPayInfo(String cdId);

	int insertBeforeSubscribePaymentInfoSave(RequestPaymentVO requestPaymentVO);

	int insertBeforeCampaignPaymentInfoSave(RequestPaymentVO requestPaymentVO);

}
