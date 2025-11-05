package com.ktdsuniversity.edu.domain.campaign.vo.request;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ktdsuniversity.edu.domain.campaign.vo.CampaignVO;

public class RequestCreateCmpnVO extends CampaignVO {

	private String roadAddress;
	private String detailAddress;
	private List<String> area;
	private MultipartFile file;
	private String denyCmpnId;
	
	public String getRoadAddress() {
		return this.roadAddress;
	}
	public void setRoadAddress(String roadAddress) {
		this.roadAddress = roadAddress;
	}
	public String getDetailAddress() {
		return this.detailAddress;
	}
	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}
	public List<String> getArea() {
		return this.area;
	}
	public void setArea(List<String> area) {
		this.area = area;
	}
	public MultipartFile getFile() {
		return this.file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	
	public String getDenyCmpnId() {
		return this.denyCmpnId;
	}
	public void setDenyCmpnId(String denyCmpnId) {
		this.denyCmpnId = denyCmpnId;
	}
	@Override
	public String toString() {
		return "RequestCreateCmpnVO [roadAddress=" + roadAddress + ", detailAddress=" + detailAddress + ", area=" + area + ", file=" + file + super.toString() + "]";
	}
}
