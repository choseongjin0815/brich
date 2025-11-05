package com.ktdsuniversity.edu.domain.pay.vo.request;

import com.ktdsuniversity.edu.domain.user.vo.SubscriptionPaymentVO;

public class RequestPaymentVO {
	
	String clientCdId;		// 상품 id
	String clientOrderName;  //상품 이름
	String clientUsrId ;	// 주문자 id
	String clientOrderId;	// 주문번호
	String clientPrice;		// 가격
	String clientCmpnId;	// 주문 캠페인
	public String getClientCmpnId() {
		return this.clientCmpnId;
	}
	public void setClientCmpnId(String clientCmpnId) {
		this.clientCmpnId = clientCmpnId;
	}
	public String getClientCdId() {
		return this.clientCdId;
	}
	public void setClientCdId(String clientCdId) {
		this.clientCdId = clientCdId;
	}
	@Override
	public String toString() {
		return "RequestPaymentVO [clientCdId=" + clientCdId + ", clientOrderName=" + clientOrderName + ", clientUsrId="
				+ clientUsrId + ", clientOrderId=" + clientOrderId + ", clientPrice=" + clientPrice + ", clientCmpnId="
				+ clientCmpnId + "]";
	}
	public String getClientOrderName() {
		return this.clientOrderName;
	}
	public void setClientOrderName(String clientOrderName) {
		this.clientOrderName = clientOrderName;
	}
	public String getClientUsrId() {
		return this.clientUsrId;
	}
	public void setClientUsrId(String clientUsrId) {
		this.clientUsrId = clientUsrId;
	}
	public String getClientOrderId() {
		return this.clientOrderId;
	}
	public void setClientOrderId(String clientOrderId) {
		this.clientOrderId = clientOrderId;
	}
	public String getClientPrice() {
		return this.clientPrice;
	}
	public void setClientPrice(String clientPrice) {
		this.clientPrice = clientPrice;
	}
	
	
	

}
