package com.adansoft.great21.controller.helpers;

import java.net.URI;

import org.springframework.web.client.RestTemplate;

import com.adansoft.great21.controllers.FacadeControllerURLs;
import com.adansoft.great21.dataaccess.schemas.GetProfileInformationRequest;
import com.adansoft.great21.dataaccess.schemas.GetProfileInformationResponse;
import com.adansoft.great21.dataaccess.schemas.GetUserBasicDetailsRequest;
import com.adansoft.great21.dataaccess.schemas.GetUserBasicDetailsResponse;
import com.adansoft.great21.dataaccess.schemas.UpdateProfileInformationRequest;
import com.adansoft.great21.dataaccess.schemas.UserAuditRequest;
import com.adansoft.great21.router.FacadetoDataAccessMapper;

public class RestServiceHelper {

	public RestServiceHelper() {
		
	}

	public static GetUserBasicDetailsResponse getBasicDetails(FacadetoDataAccessMapper mapper,RestTemplate template,GetUserBasicDetailsRequest request)
	{
		GetUserBasicDetailsResponse response = null;
		try
		{
		URI url = new URI(mapper.getDataAccessURI() + "/"
				+ FacadeControllerURLs.DATAACCESS_BASE + "/"
				+ FacadeControllerURLs.BASIC_DETAILS);
		response = template.postForEntity(url, request, GetUserBasicDetailsResponse.class).getBody();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return response;
	}
	
	public static String insertAudit(FacadetoDataAccessMapper mapper,RestTemplate template,UserAuditRequest request)
	{
		String response = null;
		try
		{
		URI url = new URI(mapper.getDataAccessURI() + "/"
				+ FacadeControllerURLs.DATAACCESS_BASE + "/"
				+ FacadeControllerURLs.USER_AUDIT);
		response = template.postForEntity(url, request, String.class).getBody();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return response;
	}
	
	public static GetProfileInformationResponse getProfileInformation(FacadetoDataAccessMapper mapper,RestTemplate template,GetProfileInformationRequest request)
	{
		GetProfileInformationResponse response = null;
		try
		{
		URI url = new URI(mapper.getDataAccessURI() + "/"
				+ FacadeControllerURLs.DATAACCESS_BASE + "/"
				+ FacadeControllerURLs.USER_PROFILE_GET);
		response = template.postForEntity(url, request, GetProfileInformationResponse.class).getBody();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return response;
	}
	
	
	public static String updateProfileInformation(FacadetoDataAccessMapper mapper,RestTemplate template,UpdateProfileInformationRequest request)
	{
		String response = null;
		try
		{
		URI url = new URI(mapper.getDataAccessURI() + "/"
				+ FacadeControllerURLs.DATAACCESS_BASE + "/"
				+ FacadeControllerURLs.USER_PROFILE_UPDATE);
		response = template.postForEntity(url, request, String.class).getBody();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return response;
	}
	
}
