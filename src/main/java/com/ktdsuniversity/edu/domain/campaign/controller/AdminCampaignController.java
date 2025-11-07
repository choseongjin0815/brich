package com.ktdsuniversity.edu.domain.campaign.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ktdsuniversity.edu.domain.campaign.service.AdminCampaignService;
import com.ktdsuniversity.edu.domain.campaign.vo.request.RequestAdminCamapaignRejectVO;
import com.ktdsuniversity.edu.domain.campaign.vo.request.RequestAdminCampaignApplicantVO;
import com.ktdsuniversity.edu.domain.campaign.vo.request.RequestAdminCampaignApproveVO;
import com.ktdsuniversity.edu.domain.campaign.vo.request.RequestAdminSearchCampaignVO;
import com.ktdsuniversity.edu.domain.campaign.vo.response.ResponseAdminCampaignApplicantListVO;
import com.ktdsuniversity.edu.domain.campaign.vo.response.ResponseAdminCampaignListVO;
import com.ktdsuniversity.edu.domain.campaign.vo.response.ResponseAdminCampaignVO;
import com.ktdsuniversity.edu.global.common.AjaxResponse;

@Controller
public class AdminCampaignController {

	private static final Logger log = LoggerFactory.getLogger(AdminCampaignController.class);
	
	@Autowired
	private AdminCampaignService adminCampaignService;
	
	/**
	 * 캠페인 관리 - 목록 페이지
	 * @param requestAdminSearchCampaignVO
	 * @param model
	 * @return
	 */
	@GetMapping("/admin/campaign-list")
	public String viewAdminCampaignListPage(RequestAdminSearchCampaignVO requestAdminSearchCampaignVO, Model model) {
		
		ResponseAdminCampaignListVO campaignListAndCategory = this.adminCampaignService.readAdminCampaignListAndCategory(requestAdminSearchCampaignVO);
		
		model.addAttribute("category", campaignListAndCategory.getCategoryList());
		model.addAttribute("campaignList", campaignListAndCategory.getResponseAdminCampaignList());
		model.addAttribute("search", requestAdminSearchCampaignVO);
		
		return "/campaign/admin_campaign_list";
	}
	
	/**
	 * 캠페인 관리 - 상세 페이지
	 * @param cmpnId
	 * @param model
	 * @return
	 */
	@GetMapping("/admin/campaign-detail/{cmpnId}")
	public String viewAdminCampaignDetailPage(@PathVariable String cmpnId, Model model) {
		
		ResponseAdminCampaignVO detail = this.adminCampaignService.readAdminCampaignDetail(cmpnId);
		
    	log.info("어드민 캠페인 상세 보기 결과: " + detail.toString());
    	model.addAttribute("detail", detail);
    	
		return "/campaign/admin_campaign_detail";
	}
	
	/**
	 * 캠페인 반려
	 * @param rejectInfo
	 * @return
	 */
	@ResponseBody
	@PostMapping("/admin/campaign-reject")
	public AjaxResponse doAdminCampaignRejectAction(@RequestBody RequestAdminCamapaignRejectVO rejectInfo) {
		
		boolean isSuccess = this.adminCampaignService.updateAdminCampaignReject(rejectInfo);
		
		AjaxResponse ajaxResponse = new AjaxResponse();
		ajaxResponse.setBody(isSuccess);
		
		return ajaxResponse;
	}
	
	/**
	 * 캠페인 승인
	 * @param approveInfo
	 * @return
	 */
	@ResponseBody
	@PostMapping("/admin/campaign-approve")
	public AjaxResponse doAdminCampaignApproveAction(@RequestBody RequestAdminCampaignApproveVO approveInfo) {
		
		boolean isSuccess = this.adminCampaignService.updateAdminCampaignApprove(approveInfo);
		
		AjaxResponse ajaxResponse = new AjaxResponse();
		ajaxResponse.setBody(isSuccess);
		
		return ajaxResponse;
	}
	
	@GetMapping("/admin/campaign-applicant/{cmpnId}")
	public String viewAdminCampaignApplicantPage(@PathVariable String cmpnId, RequestAdminCampaignApplicantVO requestAdminApplicantVO, Model model) {
		
		requestAdminApplicantVO.setListSize(10);
		requestAdminApplicantVO.setPageCountInGroup(10);
		requestAdminApplicantVO.setCmpnId(cmpnId);
    	
    	if (requestAdminApplicantVO.getOrder() != null) {
    		requestAdminApplicantVO.setOrder(requestAdminApplicantVO.getOrder().toUpperCase());
    	}
    	
    	ResponseAdminCampaignApplicantListVO applicantListVO = this.adminCampaignService.readAdminCampaignApplicantListById(requestAdminApplicantVO);
    	model.addAttribute("applicantList", applicantListVO);
    	model.addAttribute("search", requestAdminApplicantVO);
		
		return "/campaign/admin_campaign_detail_applicant";
	}
	
	@GetMapping("/admin/campaign-adopters/{cmpnId}")
	public String viewAdminCampaignAdoptersPage(@PathVariable String cmpnId) {
		return "/campaign/admin_campaign_detail_adopters";
	}
}
