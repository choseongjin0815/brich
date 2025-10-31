package com.ktdsuniversity.edu.domain.campaign.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ktdsuniversity.edu.domain.campaign.dao.CampaignDao;
import com.ktdsuniversity.edu.domain.campaign.service.CampaignService;
import com.ktdsuniversity.edu.domain.campaign.vo.CampaignVO;
import com.ktdsuniversity.edu.domain.campaign.vo.request.RequestApplicantVO;
import com.ktdsuniversity.edu.domain.campaign.vo.request.RequestCampaignAreaVO;
import com.ktdsuniversity.edu.domain.campaign.vo.request.RequestCreateCmpnVO;
import com.ktdsuniversity.edu.domain.campaign.vo.request.RequestDenyVO;
import com.ktdsuniversity.edu.domain.campaign.vo.request.RequestSearchCampaignVO;
import com.ktdsuniversity.edu.domain.campaign.vo.request.RequestUpdatePstSttsVO;
import com.ktdsuniversity.edu.domain.campaign.vo.response.ResponseAdoptListVO;
import com.ktdsuniversity.edu.domain.campaign.vo.response.ResponseAdoptVO;
import com.ktdsuniversity.edu.domain.campaign.vo.response.ResponseApplicantListVO;
import com.ktdsuniversity.edu.domain.campaign.vo.response.ResponseApplicantVO;
import com.ktdsuniversity.edu.domain.campaign.vo.response.ResponseCampaignListVO;
import com.ktdsuniversity.edu.domain.campaign.vo.response.ResponseCampaignVO;
import com.ktdsuniversity.edu.domain.campaign.vo.response.ResponseCampaignwriteVO;
import com.ktdsuniversity.edu.domain.file.dao.FileDao;
import com.ktdsuniversity.edu.domain.file.dao.FileGroupDao;
import com.ktdsuniversity.edu.domain.file.util.MultipartFileHandler;
import com.ktdsuniversity.edu.domain.file.vo.FileGroupVO;
import com.ktdsuniversity.edu.domain.file.vo.FileVO;
import com.ktdsuniversity.edu.global.common.CommonCodeVO;
import com.ktdsuniversity.edu.global.config.WebSocketConfig;

@Service
public class CampaignServiceImpl implements CampaignService {

    private final WebSocketConfig webSocketConfig;
	
	private static final Logger log = LoggerFactory.getLogger(CampaignServiceImpl.class);

    @Autowired
    private CampaignDao campaignDao;
    
    @Autowired
    private FileGroupDao fileGroupDao;
    
    @Autowired
    private FileDao fileDao;
    
    @Autowired
    private MultipartFileHandler multipartFileHandler;

    CampaignServiceImpl(WebSocketConfig webSocketConfig) {
        this.webSocketConfig = webSocketConfig;
    }
    
    /**
     * 캠페인 메인
     * 켐페인 상세 조회
     * 조회조건 : 캠페인 아이디
     */
	@Override
	public ResponseCampaignVO readCampaignDetail(String campaignId) {
		// 상세조회
		ResponseCampaignVO detail = campaignDao.selectCampaignDetailById(campaignId);
		
		// 공통코드 이름 출력
		String changeSttsCd = campaignDao.selectCampaignChangeSttsCd(detail.getSttsCd());
		detail.setSttsCd(changeSttsCd);
		
    	// 부모지역명 자르기 // 서울특별시 -> 서울
    	if(detail.getParentArea() != null) {
    		detail.setParentArea(detail.getParentArea().substring(0, 2));
    	}
		
		return detail;
	}
	/**
	 * 캠페인 메인 
	 * 캠페인 목록 조회
	 * 조회조건 : 카테고리, 검색어, 정렬순
	 */
	@Override
	public ResponseCampaignListVO readCampaignListAndCategory (RequestSearchCampaignVO requestSearchCampaignVO) {
		
		ResponseCampaignListVO responseCampaignListVO = new ResponseCampaignListVO();
		
		// Level 1 카테고리 목록 구하기
		List<CommonCodeVO> CategoryList = campaignDao.selectCategoryList();
		responseCampaignListVO.setCategoryList(CategoryList);
		log.info("카테고리 목록 : "+ CategoryList.toString());
		
		// Level 2 조회조건 세팅
		if(requestSearchCampaignVO.getCategory() != null ) {
			// 부모 카테고리 조회
			String searchCatagory = campaignDao.selectCategoryParent(requestSearchCampaignVO.getCategory());
			requestSearchCampaignVO.setCategory(searchCatagory);
			log.info("조회할 카테고리 번호 : " + searchCatagory);
		}
		if(requestSearchCampaignVO.getSortBy() == null) {
			// 초기 정렬 세팅
			requestSearchCampaignVO.setSortBy("latest");
		}
		// Level 3 조회
		responseCampaignListVO.setResponseCampaignList(campaignDao.selectCampaignListCategoryAndSortBy(requestSearchCampaignVO));
			
    	// 부모지역명 자르기 // 서울특별시 -> 서울
		List<ResponseCampaignVO> list = responseCampaignListVO.getResponseCampaignList();
		for(ResponseCampaignVO vo : list) {
			if(vo.getParentArea() != null) {
				vo.setParentArea(vo.getParentArea().substring(0, 2));				
			}
		}
		
		return responseCampaignListVO;
	}
	
	@Override
	public ResponseCampaignListVO readSubmittedMyCampaignByBlgId(String blgId) {
		ResponseCampaignListVO responseCampaignListVO = new ResponseCampaignListVO();
		
		// ('2005', '2006')  -- 모집중 캠페인
		List<String> code = List.of("2005","2006");
		Map<String, Object> param = new HashMap<>();
		param.put("blgId", blgId);
		param.put("statuses", code);
		
		responseCampaignListVO.setResponseCampaignList(campaignDao.selectMyCampaignByBlgId(param));
		
		return responseCampaignListVO;
	}
	
	@Override
	public ResponseCampaignListVO readOnGoingMyCampaignByBlgId(String blgId) {
		ResponseCampaignListVO responseCampaignListVO = new ResponseCampaignListVO();
		
		// ('2007')  -- 진행중 캠페인
		List<String> code = List.of("2007");
		Map<String, Object> param = new HashMap<>();
		param.put("blgId", blgId);
		param.put("statuses", code);
		
		responseCampaignListVO.setResponseCampaignList(campaignDao.selectMyCampaignByBlgId(param));
		
		return responseCampaignListVO;
	}
	
	@Override
	public ResponseCampaignListVO readClosedMyCampaignByBlgId(String blgId) {
		ResponseCampaignListVO responseCampaignListVO = new ResponseCampaignListVO();		
		// ('2009')  -- 종료 캠페인
		List<String> code = List.of("2009");
		Map<String, Object> param = new HashMap<>();
		param.put("blgId", blgId);
		param.put("statuses", code);
		
		responseCampaignListVO.setResponseCampaignList(campaignDao.selectMyCampaignByBlgId(param));
		
		return responseCampaignListVO;
	}	
	
	@Override
	public ResponseCampaignListVO readFavMyCampaignByBlgId(String blgId) {
		
		ResponseCampaignListVO responseCampaignListVO = new ResponseCampaignListVO();		
		// ('2005')  -- 모집중
		List<String> code = List.of("2005");
		Map<String, Object> param = new HashMap<>();
		param.put("blgId", blgId);
		param.put("statuses", code);
		
		responseCampaignListVO.setResponseCampaignList(campaignDao.selectMyFavCampaignByBlgId(param));
		
		return responseCampaignListVO;
	}	

	public ResponseApplicantListVO readApplicantListById(RequestApplicantVO requestApplicantVO) {
		int applicantCount = this.campaignDao.selectApplicantCountByCmpnId(requestApplicantVO);
		requestApplicantVO.setPageCount(applicantCount);
		
		List<ResponseApplicantVO> applicant = this.campaignDao.selectApplicantListByCmpnId(requestApplicantVO);
		int adoptCount = this.campaignDao.selectAdoptCountByCmpnId(requestApplicantVO.getCmpnId());
		CampaignVO campaignInfo = this.campaignDao.selectCampaignInfoByCmpnId(requestApplicantVO.getCmpnId());
		
		ResponseApplicantListVO applicantList = new ResponseApplicantListVO();
		applicantList.setApplicantList(applicant);
		applicantList.setAdoptCount(adoptCount);
		applicantList.setCampaignInfo(campaignInfo);
		
		return applicantList;
	}
	
	@Override
	@Transactional
	public boolean updateAdptYnByCmpnPstAdptId(RequestApplicantVO requestApplicantVO) {
		String cmpnState = this.campaignDao.selectCampaignStateByCmpnPstAdptId(requestApplicantVO.getCmpnPstAdptId());
		if (!cmpnState.equals("2006")) {
			// TODO : Ajax 에러 처리 하기
			throw new IllegalArgumentException("선정 단계가 아닙니다.");
		}
		
		int updateCount = this.campaignDao.updateAdptYnByCmpnPstAdptId(requestApplicantVO);
		
		return updateCount > 0;
	}

	@Override
	public ResponseAdoptListVO readResponseAdoptListByCmpnId(RequestApplicantVO requestApplicantVO) {
		List<ResponseAdoptVO> adopt = this.campaignDao.selectAdoptListByCmpnId(requestApplicantVO);
		int adoptCount = this.campaignDao.selectAdoptPaginationCount(requestApplicantVO);
		requestApplicantVO.setPageCount(adoptCount);
		
		CampaignVO campaign = this.campaignDao.selectCampaignInfoByCmpnId(requestApplicantVO.getCmpnId());
		
		ResponseAdoptListVO adoptList = new ResponseAdoptListVO();
		adoptList.setAdoptList(adopt);
		adoptList.setCampaignInfo(campaign);
		adoptList.setCmpnCdNm(this.campaignDao.selectStateNameByStateCode(adoptList.getCampaignInfo().getSttsCd()));
		
		return adoptList;
	}
	/**
	 * 사랑해요
	 */
	@Override
	public boolean favCampaignDo(String blgId, String campaignId) {
		int updateCount = 0 ;
		Map<String, String> param = new HashMap<>();
		param.put("blgId",blgId);
		param.put("campaignId",campaignId);
		
		// 최초 생성인지 확인
		String favExists = this.campaignDao.selectFavCamapignExists(param);
		if(favExists.equals("0")) { 
			// 없다면 최초 생성
			updateCount = this.campaignDao.insertFavCamapign(param);			
		} else {
			// 하트가 제거되었는지 확인
			String favDltYn = this.campaignDao.selectFavDltYn(param);
			if(favDltYn.equals("N")) {
				// 있다면 하트제거
				updateCount = this.campaignDao.updateFavCamapignOff(param);				
			}else {
				// 있는데 삭제라면 하트생성
				updateCount = this.campaignDao.updateFavCamapignOn(param);							
			}
		}
		
		
		
		return updateCount > 0 ;
	}
	
	/**
	 * 캠페인 신청하기
	 */
	@Override
	public int applyCampaign(String campaignId, String blgId) {
		
		Map<String, String> param = new HashMap<>();
		param.put("blgId",blgId);
		param.put("campaignId",campaignId);
		
		//캠페인 모집중 여부 확인	
		ResponseCampaignVO detail = campaignDao.selectCampaignDetailById(campaignId);
		if(detail.getSttsCd().equals("2005")) {
			// 캠페인 신청 이력 여부 확인
				// 없다면 캠페인 생성 (insert)
			
				int count = this.campaignDao.insertApplyCampaign(param);
			
				// 있다면 신청상태(삭제여부 확인)
					// 신청
					// 신청취소
		} else {
		}
			
		return 0;
		
	}

	@Transactional
	@Override
	public boolean updatePstSttsApproveByCmpnPstAdoptId(RequestApplicantVO requestApplicantVO) {
		RequestUpdatePstSttsVO requestUpdatePstSttsVO = new RequestUpdatePstSttsVO();
		requestUpdatePstSttsVO.setCmpnPstAdptId(requestApplicantVO.getCmpnPstAdptId());
		requestUpdatePstSttsVO.setStts("6004");
		requestUpdatePstSttsVO.setAdvId(requestApplicantVO.getUsrId());
		int updateCount = this.campaignDao.updatePstSttsByCmpnPstAdoptId(requestUpdatePstSttsVO);
		return updateCount == 1;
	}
	
	@Transactional
	@Override
	public boolean createDenyByCmpnPstAdoptId(RequestDenyVO requestDenyVO) {
		List<FileVO> uploadResult = this.multipartFileHandler.upload(requestDenyVO.getFile());
		RequestUpdatePstSttsVO requestUpdatePstSttsVO = new RequestUpdatePstSttsVO();
		requestUpdatePstSttsVO.setCmpnPstAdptId(requestDenyVO.getCmpnPstAdptId());
		requestUpdatePstSttsVO.setStts("6003");
		requestUpdatePstSttsVO.setAdvId(requestDenyVO.getAdvId());
		
		RequestDenyVO requestDdlnVO = new RequestDenyVO();
		requestDdlnVO.setCmpnPstAdptId(requestDenyVO.getCmpnPstAdptId());
		requestDdlnVO.setPstDdln(requestDenyVO.getPstDdln());
		requestDdlnVO.setAdvId(requestDenyVO.getAdvId());
		
		if (uploadResult != null && uploadResult.size() > 0) {
			// 1. File Group Insert
			FileGroupVO fileGroupVO = new FileGroupVO();
			fileGroupVO.setFlCnt(uploadResult.size());
			int insertGroupCount = this.fileGroupDao.insertFileGroup(fileGroupVO);
			
			// 2. File Insert
			for(FileVO result: uploadResult) {
				result.setFlGrpId(fileGroupVO.getFlGrpId());
				int insertFileCount = this.fileDao.insertFile(result);
			}
			
			requestDenyVO.setFileGroupId(fileGroupVO.getFlGrpId());
		}
		
		int insertCount = this.campaignDao.insertDenyByCmpnPstAdoptId(requestDenyVO);
		int updateCount = 0;
		int updateDateCount = 0;
		int updateCmpnDateCount = 0;
		
		if (insertCount == 1) {
			updateCount = this.campaignDao.updatePstSttsByCmpnPstAdoptId(requestUpdatePstSttsVO);
			updateDateCount = this.campaignDao.updateDdlnByCmpnPstAdoptId(requestDdlnVO);
		}
		
		if (updateDateCount == 1) {
			updateCmpnDateCount = this.campaignDao.udpateCmpnDateByCmpnId(requestDenyVO);
		}
		
		return insertCount == 1 && updateCount == 1 && updateDateCount == 1 && updateCmpnDateCount == 1;
	}

	@Override
	public ResponseCampaignwriteVO createCampaign() {
		ResponseCampaignwriteVO common = new ResponseCampaignwriteVO();
		common.setDoAndCityList(this.campaignDao.selectDoAndCityList());
		common.setCategoryList(this.campaignDao.selectCategoryList());
		common.setPersonPrice(this.campaignDao.selectPersonPrice());
		return common;
	}

	@Override
	public List<CommonCodeVO> readDistrictByCdId(String cdId) {
		List<CommonCodeVO> districtList = this.campaignDao.selectDistrictByCdId(cdId);
		return districtList;
	}

	@Override
	@Transactional
	public boolean createNewCampaign(RequestCreateCmpnVO requestCreateCmpnVO) {
		String addr = requestCreateCmpnVO.getRoadAddress() + " " + requestCreateCmpnVO.getDetailAddress();
		requestCreateCmpnVO.setAddrs(addr);
		
		FileVO uploadResult = this.multipartFileHandler.upload(requestCreateCmpnVO.getFile());
		
		if (uploadResult != null) {
			// 1. File Group Insert
			FileGroupVO fileGroupVO = new FileGroupVO();
			fileGroupVO.setFlCnt(1);
			int insertGroupCount = this.fileGroupDao.insertFileGroup(fileGroupVO);
			
			// 2. File Insert
			uploadResult.setFlGrpId(fileGroupVO.getFlGrpId());
			int insertFileCount = this.fileDao.insertFile(uploadResult);
			
			requestCreateCmpnVO.setAttchGrpId(fileGroupVO.getFlGrpId());
		}
		
		int insertCmpnCount = this.campaignDao.insertNewCampaign(requestCreateCmpnVO);
		
		if (requestCreateCmpnVO.getArea() != null && insertCmpnCount == 1) {
			RequestCampaignAreaVO requestCampaignAreaVO = new RequestCampaignAreaVO();
			requestCampaignAreaVO.setCmpnId(requestCreateCmpnVO.getCmpnId());
			requestCampaignAreaVO.setUsrId(requestCreateCmpnVO.getUsrId());
			
			for(String arCd : requestCreateCmpnVO.getArea()) {
				requestCampaignAreaVO.setArCd(arCd);
				int insertAreaCount = this.campaignDao.insertCampaignCategory(requestCampaignAreaVO);
			}
		}
		
		return insertCmpnCount == 1;
	}
}
