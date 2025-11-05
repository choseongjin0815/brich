package com.ktdsuniversity.edu.domain.campaign.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.ktdsuniversity.edu.domain.campaign.service.CampaignService;
import com.ktdsuniversity.edu.domain.campaign.vo.request.RequestApplicantVO;
import com.ktdsuniversity.edu.domain.campaign.vo.request.RequestCreateCmpnVO;
import com.ktdsuniversity.edu.domain.campaign.vo.request.RequestDenyVO;
import com.ktdsuniversity.edu.domain.campaign.vo.request.RequestPostSubmitVO;
import com.ktdsuniversity.edu.domain.campaign.vo.request.RequestSearchCampaignVO;
import com.ktdsuniversity.edu.domain.campaign.vo.response.ResponseAdoptListVO;
import com.ktdsuniversity.edu.domain.campaign.vo.response.ResponseApplicantListVO;
import com.ktdsuniversity.edu.domain.campaign.vo.response.ResponseCampaignListVO;
import com.ktdsuniversity.edu.domain.campaign.vo.response.ResponseCampaignVO;
import com.ktdsuniversity.edu.domain.campaign.vo.response.ResponseCampaignwriteVO;
import com.ktdsuniversity.edu.domain.file.dao.FileDao;
import com.ktdsuniversity.edu.domain.file.dao.FileGroupDao;
import com.ktdsuniversity.edu.domain.file.util.MultipartFileHandler;
import com.ktdsuniversity.edu.domain.file.vo.FileGroupVO;
import com.ktdsuniversity.edu.domain.file.vo.FileVO;
import com.ktdsuniversity.edu.domain.report.vo.request.RequestReportCreateVO;
import com.ktdsuniversity.edu.domain.user.vo.UserVO;
import com.ktdsuniversity.edu.global.common.AjaxResponse;
import com.ktdsuniversity.edu.global.common.CommonCodeVO;

@Controller
public class CampaignController {
	
	private static final Logger log = LoggerFactory.getLogger(CampaignController.class);

    @Autowired
    private CampaignService campaignService;
    
    @GetMapping("/campaigndetail/{campaignId}")
    public String campaignDetailPage(@PathVariable String campaignId, Model model,
    							 @SessionAttribute(value = "__LOGIN_USER__", required = false) UserVO loginUser ) {
    	ResponseCampaignVO detail = new ResponseCampaignVO(); 
    	if(loginUser != null) {
    		if(loginUser.getAutr().equals("1002") || loginUser.getAutr().equals("1003") ) {
        		detail = campaignService.readCampaignDetail(campaignId, loginUser.getUsrId());    	
        		String returnReason =  campaignService.postReturnReason(campaignId, loginUser.getUsrId());
        		model.addAttribute("returnReason", returnReason);
        	}else {    		
        		detail = campaignService.readCampaignDetail(campaignId);
        	}
    	}else {    		
    		detail = campaignService.readCampaignDetail(campaignId);
    	}
    	
    	
    	log.info( "캠페인 상세조회 결과 : " + detail.toString());
    	model.addAttribute("detail", detail);
    	model.addAttribute("userInfo", loginUser);
    	return "campaign/campaigndetail";
    }
    
    @GetMapping("/campaignmain")
    public String campaignMainPage(RequestSearchCampaignVO requestSearchCampaignVO, Model model,
    						   @SessionAttribute(value = "__LOGIN_USER__", required = false) UserVO loginUser){
    	if(loginUser != null) {
    		requestSearchCampaignVO.setLoginId(loginUser.getUsrId());
    	}
    	log.info( "입력 파라미터 값 : " + requestSearchCampaignVO.toString());
    	ResponseCampaignListVO CampaignListAndCategory = campaignService.readCampaignListAndCategory(requestSearchCampaignVO);
    	
    	model.addAttribute("category", CampaignListAndCategory.getCategoryList());
    	model.addAttribute("campaignList", CampaignListAndCategory.getResponseCampaignList());
    	model.addAttribute("search", requestSearchCampaignVO);
    	
    	log.info( "캠페인 리스트 조회결과 : " + CampaignListAndCategory.getResponseCampaignList().toString());
    	return "campaign/campaignmain";
    }  
    
    
    @GetMapping("/blgr/submittedmycampaign")
    public String submittedmycampaign(Model model,@SessionAttribute(value = "__LOGIN_USER__") 
    						UserVO loginUser) {
    	String blgId = loginUser.getUsrId();
    	ResponseCampaignListVO CampaignListAndCategory = campaignService.readSubmittedMyCampaignByBlgId(blgId);
    	model.addAttribute("campaignList", CampaignListAndCategory.getResponseCampaignList());
    	
    	log.info( "캠페인 리스트 조회결과 : " + CampaignListAndCategory.getResponseCampaignList().toString());
    	return "campaign/submittedmycampaign";
    }
    
    @GetMapping("/blgr/campaignongoing")
    public String campaignongoing(Model model,@SessionAttribute(value = "__LOGIN_USER__") 
    							UserVO loginUser) {
    	String blgId = loginUser.getUsrId();
    	ResponseCampaignListVO CampaignListAndCategory = campaignService.readOnGoingMyCampaignByBlgId(blgId);
    	model.addAttribute("campaignList", CampaignListAndCategory.getResponseCampaignList());
    	
    	log.info( "캠페인 리스트 조회결과 : " + CampaignListAndCategory.getResponseCampaignList().toString());
    	return "campaign/campaignongoing";
    }
    
    @GetMapping("/blgr/closedcampaign")
    public String closedcampaign(Model model,@SessionAttribute(value = "__LOGIN_USER__") 
    							UserVO loginUser) {
    	String blgId = loginUser.getUsrId();
    	ResponseCampaignListVO CampaignListAndCategory = campaignService.readClosedMyCampaignByBlgId(blgId);
    	model.addAttribute("campaignList", CampaignListAndCategory.getResponseCampaignList());
    	
    	log.info( "캠페인 리스트 조회결과 : " + CampaignListAndCategory.getResponseCampaignList().toString());
    	return "campaign/closedcampaign";
    }
    
    @GetMapping("/blgr/favcampaign")
    public String favcampaign(Model model,@SessionAttribute(value = "__LOGIN_USER__") 
    							UserVO loginUser) {
    	String blgId = loginUser.getUsrId();
    	ResponseCampaignListVO CampaignListAndCategory = campaignService.readFavMyCampaignByBlgId(blgId);
    	model.addAttribute("campaignList", CampaignListAndCategory.getResponseCampaignList());
    	
    	log.info( "캠페인 리스트 조회결과 : " + CampaignListAndCategory.getResponseCampaignList().toString());
    	return "campaign/favcampaign";
    }
    
    /**
     * 좋아요 
     * @param loginUser
     * @param campaignId
     * @return
     */
    @ResponseBody
    @PostMapping("/blgr/love/{campaignId}")
    public AjaxResponse favCampaignDo(@SessionAttribute(value = "__LOGIN_USER__") UserVO loginUser, 
    						@PathVariable String campaignId) {
    	String blgId = loginUser.getUsrId();
    	int count = campaignService.favCampaignDo(blgId, campaignId);
    	
    	AjaxResponse ajaxResponse = new AjaxResponse();
    	ajaxResponse.setBody(count);
    	return ajaxResponse;
    }
    
    /**
     * 캠페인 신청, 취소 하기
     * @param loginUser
     * @param campaignId
     * @return
     */
    @ResponseBody
    @PostMapping("/blgr/apply/{campaignId}")
    public AjaxResponse applyCampaign(@SessionAttribute(value = "__LOGIN_USER__") UserVO loginUser, 
    						@PathVariable String campaignId) {
    	String blgId = loginUser.getUsrId();
    	int count = this.campaignService.applyCampaign(campaignId, blgId);
    	
    	AjaxResponse ajaxResponse = new AjaxResponse();
    	ajaxResponse.setBody(count);
    	
    	return ajaxResponse;
    }
    
    /**
     * 포스팅 작성
     * @param requestPostSubmitVO
     * @param loginUser
     * @param campaignId
     * @return
     */
    @ResponseBody
    @PostMapping("/blgr/pstsubmit/{campaignId}")
    public AjaxResponse postSubmit(RequestPostSubmitVO requestPostSubmitVO ,@SessionAttribute(value = "__LOGIN_USER__") UserVO loginUser, 
    						@PathVariable String campaignId) {
    	requestPostSubmitVO.setBlgId(loginUser.getUsrId());
    	requestPostSubmitVO.setCmpnId(campaignId);
    	int count = this.campaignService.postSubmit(requestPostSubmitVO);
    	log.info( "포스팅 input 정보 : " + requestPostSubmitVO.toString());
    
    	
    	AjaxResponse ajaxResponse = new AjaxResponse();
    	ajaxResponse.setBody(count);
    	
    	return ajaxResponse;
    }
    
    /**
     * 포스팅 재 제출
     * @param requestPostSubmitVO
     * @param loginUser
     * @param campaignId
     * @return
     */
    @ResponseBody
    @PostMapping("/blgr/repstsubmit/{campaignId}")
    public AjaxResponse rePostSubmit(RequestPostSubmitVO requestPostSubmitVO ,@SessionAttribute(value = "__LOGIN_USER__") UserVO loginUser, 
    		@PathVariable String campaignId) {
    	
    	requestPostSubmitVO.setBlgId(loginUser.getUsrId());
    	requestPostSubmitVO.setCmpnId(campaignId);
    	int count = this.campaignService.rePostSubmit(requestPostSubmitVO);
    	log.info( "포스팅 input 정보 : " + requestPostSubmitVO.toString());
    	
    	
    	AjaxResponse ajaxResponse = new AjaxResponse();
    	ajaxResponse.setBody(1);
    	
    	return ajaxResponse;
    }
    


    
    @GetMapping("/adv/campaign/applicant/{cmpnId}")
    public String readApplicantList(Model model, @PathVariable String cmpnId,
    								RequestApplicantVO requestApplicantVO) {
    	// TODO 캠페인 주인과 세션이 다를 때 접근 막을 것
//    	if (!board.getEmail().equals(loginUser.getEmail())) {
//			throw new HelloSpringException("잘못된 접근입니다.", "error/403");
//		}
    	requestApplicantVO.setListSize(10);
    	requestApplicantVO.setPageCountInGroup(10);
    	requestApplicantVO.setCmpnId(cmpnId);

    	if (requestApplicantVO.getOrder() != null) {
    		requestApplicantVO.setOrder(requestApplicantVO.getOrder().toUpperCase());
    	}
    	ResponseApplicantListVO applicantList = this.campaignService.readApplicantListById(requestApplicantVO);
    	model.addAttribute("applicantList", applicantList);
    	model.addAttribute("search", requestApplicantVO);
    	
    	return "campaign/applicant";
    }
    
    @GetMapping("/adv/adoptChange")
    @ResponseBody
    public boolean doUpdateAdptYnAction(RequestApplicantVO requestApplicantVO,
    									@SessionAttribute(value="__LOGIN_USER__") UserVO loginUser) {
    	requestApplicantVO.setUsrId(loginUser.getUsrId());
    	boolean update = this.campaignService.updateAdptYnByCmpnPstAdptId(requestApplicantVO);
    	
    	if (update) {
    		return true;
    	}
    	
    	else {
    		return false;
    	}
    }
    
    @GetMapping("/adv/campaign/adopt/{cmpnId}")
    public String readAdoptList(Model model
    							, @PathVariable String cmpnId
    							,RequestApplicantVO requestApplicantVO) {
    	requestApplicantVO.setListSize(10);
    	requestApplicantVO.setPageCountInGroup(10);
		requestApplicantVO.setCmpnId(cmpnId);
    	
    	ResponseAdoptListVO adoptList = this.campaignService.readResponseAdoptListByCmpnId(requestApplicantVO);
    	model.addAttribute("adoptList", adoptList);
    	model.addAttribute("search",requestApplicantVO);
    	log.info("adoptList---:" + adoptList);
    	return "campaign/adopt";
    }

	@GetMapping("/adv/postapprove/{cmpnPstAdoptId}")
    @ResponseBody
    public boolean doUpdatePstSttsApproveAction(@PathVariable String cmpnPstAdoptId,
    											@SessionAttribute(value="__LOGIN_USER__") UserVO loginUser) {
    	RequestApplicantVO requestApplicantVO = new RequestApplicantVO();
    	requestApplicantVO.setCmpnPstAdptId(cmpnPstAdoptId);
    	requestApplicantVO.setUsrId(loginUser.getUsrId());
    	boolean update = this.campaignService.updatePstSttsApproveByCmpnPstAdoptId(requestApplicantVO);
    	
    	if (update) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }

	@PostMapping("/adv/deny/{cmpnPstAdptId}/{cmpnId}")
	@ResponseBody
	public boolean doCreateDenyAction(RequestDenyVO requestDenyVO,
									  @SessionAttribute(value="__LOGIN_USER__") UserVO loginUser) {
		requestDenyVO.setAdvId(loginUser.getUsrId());
		boolean insert = this.campaignService.createDenyByCmpnPstAdoptId(requestDenyVO);
		
		if (insert) {
    		return true;
    	}
    	else {
    		return false;
    	}
	}
	
	@GetMapping("/adv/campaign/write")
	public String createCampaign(Model model) {
		ResponseCampaignwriteVO common = this.campaignService.createCampaign();
		model.addAttribute("common", common);
		return "campaign/write";
	}
	
	@GetMapping("/adv/campaign/write/{cdId}")
	@ResponseBody
	public AjaxResponse doReadDistrictAction(@PathVariable String cdId) {
		AjaxResponse response = new AjaxResponse();
		
		List<CommonCodeVO> districtList = this.campaignService.readDistrictByCdId(cdId);
		response.setBody(districtList);
		return response;
	}
	
	@PostMapping("/adv/campaign/write")
	public String doCreateNewCampaignAction(RequestCreateCmpnVO requestCreateCmpnVO,
											@SessionAttribute(value="__LOGIN_USER__") UserVO loginUser) {
		requestCreateCmpnVO.setUsrId(loginUser.getUsrId());
		boolean insert = this.campaignService.createNewCampaign(requestCreateCmpnVO);
		if (insert) {
			return "redirect:/adv/campaign/list";
		}
		
		else {
			return "";
		}
	}
	
	@GetMapping("/adv/campaign/modify/{cmpnId}")
	public String modifyCampaign(Model model
								 , @PathVariable String cmpnId) {
		ResponseCampaignwriteVO common = this.campaignService.createCampaign();
		ResponseCampaignVO campaign = this.campaignService.readCampaignDetail(cmpnId);
		log.info("campaign read---" + campaign.toString());
		
		model.addAttribute("common", common);
		model.addAttribute("campaign", campaign);
		return "campaign/modify";
	}
	
	@PostMapping("/adv/campaign/modify/{denyCmpnId}")
	public String doModifyCampaignAction(RequestCreateCmpnVO requestCreateCmpnVO,
											@SessionAttribute(value="__LOGIN_USER__") UserVO loginUser) {
		requestCreateCmpnVO.setUsrId(loginUser.getUsrId());
		boolean modify = this.campaignService.modifyNewCampaign(requestCreateCmpnVO);
		if (modify) {
			return "redirect:/adv/campaign/list";
		}
		
		else {
			return "";
		}
	}
	
	@GetMapping("/adv/campaign/list")
	public String readCampaignListByUsrId(@SessionAttribute(value="__LOGIN_USER__") UserVO loginUser
										  , Model model
										  , RequestSearchCampaignVO requestSearchCampaignVO) {
		requestSearchCampaignVO.setListSize(8);
		requestSearchCampaignVO.setPageCountInGroup(5);
		requestSearchCampaignVO.setLoginId(loginUser.getUsrId());
		
		ResponseCampaignListVO campaignList = this.campaignService.readCampaignListByUsrId(requestSearchCampaignVO);
		model.addAttribute("campaignList", campaignList);
		model.addAttribute("search", requestSearchCampaignVO);
		log.info("campaignFilecheck : " + campaignList.getResponseCampaignList().toString());
		return "campaign/list";
	}
	
	@GetMapping("/adv/campaign/deny-history/{cmpnId}")
	public String readDenyHistory(Model model
								  , RequestSearchCampaignVO requestSearchCampaignVO) {
		
		ResponseCampaignListVO denyList = this.campaignService.readDenyHistoryByCmpnId(requestSearchCampaignVO);
		denyList.setIsDeny(true);
		log.info("---denyList:" + denyList.toString());
		model.addAttribute("campaignList", denyList);
		model.addAttribute("search", requestSearchCampaignVO);
		
		return "campaign/list";
	}
}