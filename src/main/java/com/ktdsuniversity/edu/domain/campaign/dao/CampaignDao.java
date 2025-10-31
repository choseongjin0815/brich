package com.ktdsuniversity.edu.domain.campaign.dao;


import java.util.List;
import java.util.Map;

import com.ktdsuniversity.edu.domain.blog.vo.RequestExpireSoonCampaignVO;
import com.ktdsuniversity.edu.domain.campaign.vo.CampaignVO;
import com.ktdsuniversity.edu.domain.campaign.vo.request.RequestApplicantVO;
import com.ktdsuniversity.edu.domain.campaign.vo.request.RequestCampaignAreaVO;
import com.ktdsuniversity.edu.domain.campaign.vo.request.RequestCreateCmpnVO;
import com.ktdsuniversity.edu.domain.campaign.vo.request.RequestDenyVO;
import com.ktdsuniversity.edu.domain.campaign.vo.request.RequestSearchCampaignVO;
import com.ktdsuniversity.edu.domain.campaign.vo.request.RequestUpdatePstSttsVO;
import com.ktdsuniversity.edu.domain.campaign.vo.response.ResponseAdoptVO;
import com.ktdsuniversity.edu.domain.campaign.vo.response.ResponseApplicantVO;
import com.ktdsuniversity.edu.domain.campaign.vo.response.ResponseCampaignVO;
import com.ktdsuniversity.edu.global.common.CommonCodeVO;

public interface CampaignDao {

	ResponseCampaignVO selectCampaignDetailById(String campaignId);

	List<CommonCodeVO> selectCategoryList();

	List<ResponseApplicantVO> selectApplicantListByCmpnId(RequestApplicantVO requestApplicantVO);

	int updateAdptYnByCmpnPstAdptId(RequestApplicantVO requestApplicantVO);

	int selectAdoptCountByCmpnId(String cmpnId);

	CampaignVO selectCampaignInfoByCmpnId(String cmpnId);

	int selectApplicantCountByCmpnId(RequestApplicantVO requestApplicantVO);

	String selectCampaignStateByCmpnPstAdptId(String cmpnPstAdptId);

	List<ResponseAdoptVO> selectAdoptListByCmpnId(RequestApplicantVO requestApplicantVO);

	List<ResponseCampaignVO> selectCampaignListCategoryAndSortBy(RequestSearchCampaignVO requestSearchCampaignVO);

	String selectCategoryParent(String selectCategroy);

	List<CampaignVO> selectExpireSoonCampaign(RequestExpireSoonCampaignVO requestExpireSoonCampaignVO);

	String selectCampaignChangeSttsCd(String sttsCd);

	List<ResponseCampaignVO> selectMyCampaignByBlgId(Map<String, Object> param);

	String selectFavCamapignExists(Map<String, String> param);

	int insertFavCamapign(Map<String, String> param);

	int updateFavCamapignOn(Map<String, String> param);

	int updateFavCamapignOff(Map<String, String> param);

	String selectFavDltYn(Map<String, String> param);

	List<ResponseCampaignVO> selectMyFavCampaignByBlgId(Map<String, Object> param);

	int insertApplyCampaign(Map<String, String> param);

	int updatePstSttsByCmpnPstAdoptId(RequestUpdatePstSttsVO requestUpdatePstSttsVO);

	int selectAdoptPaginationCount(RequestApplicantVO requestApplicantVO);

	String selectStateNameByStateCode(String sttsCd);

	int insertDenyByCmpnPstAdoptId(RequestDenyVO requestDenyVO);

	List<CommonCodeVO> selectDoAndCityList();

	List<CommonCodeVO> selectDistrictByCdId(String cdId);

	String selectPersonPrice();

	int insertNewCampaign(RequestCreateCmpnVO requestCreateCmpnVO);

	int insertCampaignCategory(RequestCampaignAreaVO requestCampaignAreaVO);

	int updateDdlnByCmpnPstAdoptId(RequestDenyVO requestDenyVO);

	int udpateCmpnDateByCmpnId(RequestDenyVO requestDenyVO);
}