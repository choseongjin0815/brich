package com.ktdsuniversity.edu.domain.campaign.vo.request;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ktdsuniversity.edu.domain.campaign.vo.CampaignVO;

public class RequestCreateCmpnVO extends CampaignVO {

	private String loadAddress;
	private String detailAddress;
	private List<String> category;
	private List<String> area;
	private MultipartFile file;
	
	public String getLoadAddress() {
		return this.loadAddress;
	}
	public void setLoadAddress(String loadAddress) {
		this.loadAddress = loadAddress;
	}
	public String getDetailAddress() {
		return this.detailAddress;
	}
	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}
	public List<String> getCategory() {
		return this.category;
	}
	public void setCategory(List<String> category) {
		this.category = category;
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
	
	@Override
	public String toString() {
		return "RequestCreateCmpnVO [loadAddress=" + loadAddress + ", detailAddress=" + detailAddress + ", category="
				+ category + ", area=" + area + ", file=" + file + super.toString() + "]";
	}
}
