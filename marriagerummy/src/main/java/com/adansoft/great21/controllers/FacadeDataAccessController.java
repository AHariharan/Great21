package com.adansoft.great21.controllers;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.adansoft.great21.controller.helpers.RestServiceHelper;
import com.adansoft.great21.dataaccess.schemas.GetProfileInformationRequest;
import com.adansoft.great21.dataaccess.schemas.GetProfileInformationResponse;
import com.adansoft.great21.dataaccess.schemas.UpdateProfileInformationRequest;
import com.adansoft.great21.exceptions.DataAccessConfigException;
import com.adansoft.great21.router.FacadetoDataAccessMapper;
import com.adansoft.great21.security.RummyUser;
import com.adansoft.great21.uischemas.data.UIUpdateProfileInformationRequest;

@RestController
@RequestMapping(FacadeControllerURLs.DATAACCESS_BASE)
@EnableWebMvcSecurity
public class FacadeDataAccessController {

	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	FacadetoDataAccessMapper mapper;
	
	@PostConstruct
	public void verifyGameIndexerConfig()
	{
		try
		{
		if(mapper.getDataAccessURI() == null)
		{
			System.out.println("Failed to get DataAccess config .. exiting");
			System.exit(0);
		}
		System.out.println("Data Access from Authentication Service: " + mapper.getDataAccessURI());
		}catch(DataAccessConfigException ex)
		{
			ex.printStackTrace();
			System.exit(0);
		} catch (Exception e) {
		   e.printStackTrace();
		   System.exit(0);
		}
	}
	
	@Secured("ROLE_USER")
	@RequestMapping( value = FacadeControllerURLs.USER_PROFILE_GET, method = RequestMethod.POST)
	public GetProfileInformationResponse getProfileInformation(@AuthenticationPrincipal Authentication authentication)
	{
		GetProfileInformationRequest request = new GetProfileInformationRequest();
		RummyUser user = (RummyUser)authentication.getPrincipal();
		request.setEmailaddress(user.getEmailaddr());
		request.setNickname(user.getNickname());
		request.setUserid(user.getUserid());
		return RestServiceHelper.getProfileInformation(mapper, restTemplate, request);
	}
	
	@Secured("ROLE_USER")
	@RequestMapping( value = FacadeControllerURLs.USER_PROFILE_UPDATE, method = RequestMethod.POST)
	public String updateProfileInformation(@RequestBody UIUpdateProfileInformationRequest incomingrequest,@AuthenticationPrincipal Authentication authentication)
	{
		UpdateProfileInformationRequest request = new UpdateProfileInformationRequest();
		RummyUser user = (RummyUser)authentication.getPrincipal();
		request.setEmailaddress(user.getEmailaddr());request.setNickname(user.getNickname());
		request.setUserid(user.getUserid());request.setCountry(incomingrequest.getCountry());
		request.setFirstname(incomingrequest.getFirstname());request.setLastname(incomingrequest.getLastname());
		return RestServiceHelper.updateProfileInformation(mapper, restTemplate, request);
	}
	
}
