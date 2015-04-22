package com.adansoft.great21.dataaccess.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.adansoft.great21.dataaccess.dao.BasicDataAccessDAOImpl;
import com.adansoft.great21.dataaccess.schemas.GetNotificationCountRequest;
import com.adansoft.great21.dataaccess.schemas.GetNotificationCountResponse;
import com.adansoft.great21.dataaccess.schemas.GetProfileInformationRequest;
import com.adansoft.great21.dataaccess.schemas.GetProfileInformationResponse;
import com.adansoft.great21.dataaccess.schemas.GetUserBasicDetailsRequest;
import com.adansoft.great21.dataaccess.schemas.GetUserBasicDetailsResponse;
import com.adansoft.great21.dataaccess.schemas.UpdateProfileInformationRequest;
import com.adansoft.great21.dataaccess.schemas.UserAuditRequest;

@RestController
@RequestMapping(DataAccessServiceURLs.DATAACCESS_BASE)
public class DataServiceController {
	
	@Autowired
	private BasicDataAccessDAOImpl basicdatadao;

	@Transactional
	@RequestMapping( value = DataAccessServiceURLs.BASIC_DETAILS, method = RequestMethod.POST)
	public @ResponseBody GetUserBasicDetailsResponse getBasicUserDetails(@RequestBody GetUserBasicDetailsRequest request)
	{
		return basicdatadao.getBasicDetails(request);
	}
	
	@Transactional
	@RequestMapping( value = DataAccessServiceURLs.USER_AUDIT, method = RequestMethod.POST)
	public @ResponseBody String insertAuditInformation(@RequestBody UserAuditRequest request)
	{
		return basicdatadao.addAudit(request);
	}
	
	@Transactional
	@RequestMapping( value = DataAccessServiceURLs.USER_PROFILE_GET, method = RequestMethod.POST)
	public @ResponseBody GetProfileInformationResponse getProfileInformation(@RequestBody GetProfileInformationRequest request)
	{
		return basicdatadao.getProfileInformation(request);
	}

	
	@Transactional
	@RequestMapping( value = DataAccessServiceURLs.USER_PROFILE_UPDATE, method = RequestMethod.POST)
	public @ResponseBody String updateProfileInformation(@RequestBody UpdateProfileInformationRequest request)
	{
		return basicdatadao.updateProfileInformation(request);
	}
	
	@Transactional
	@RequestMapping( value = DataAccessServiceURLs.GET_NOTIFICATION_COUNT, method = RequestMethod.POST)
	public @ResponseBody GetNotificationCountResponse getNotificationCount(@RequestBody GetNotificationCountRequest request)
	{
		return basicdatadao.getNotificationCount(request);
	}

}
