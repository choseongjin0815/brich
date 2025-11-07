package com.ktdsuniversity.edu.domain.pay.vo.request;

import com.ktdsuniversity.edu.domain.campaign.vo.CampaignVO;

public class RequestPaymentCampaignVO extends CampaignVO{
	
	public String usrId;

	public String getUsrId() {
		return this.usrId;
	}
	public void setUsrId(String usrId) {
		this.usrId = usrId;
	}
	@Override
	public String toString() {
		return super.toString()+ "RequestPaymentCampaignVO [ usrId=" + usrId + "]";
	}
	
	
}
