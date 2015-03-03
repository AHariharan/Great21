package com.adansoft.great21.controllers;

import java.net.URI;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.adansoft.great21.dataaccess.schemas.ActivateAccountRequest;
import com.adansoft.great21.dataaccess.schemas.SignupRequest;
import com.adansoft.great21.dataaccess.schemas.SignupResponse;
import com.adansoft.great21.exceptions.DataAccessConfigException;
import com.adansoft.great21.router.FacadetoDataAccessMapper;
import com.adansoft.great21.uimediation.PasswordUtility;

@RestController
@RequestMapping(FacadeControllerURLs.USERACCESS_BASE)
@EnableWebMvcSecurity
public class LoginController {
	
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
	
	

	@RequestMapping( value = FacadeControllerURLs.SIGNUP, method = RequestMethod.POST)
	public @ResponseBody SignupResponse signUp(@RequestBody SignupRequest request)
	{
	    request.setPassword(PasswordUtility.encodePassword(request.getPassword()));
	    SignupResponse result = null;
		try {
			URI url = new URI(mapper.getDataAccessURI() + "/"
					+ FacadeControllerURLs.DATAACCESS_AUTHBASE + "/"
					+ FacadeControllerURLs.SIGNUP);
			result = restTemplate.postForEntity(url, request, SignupResponse.class).getBody();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@RequestMapping( value = FacadeControllerURLs.ACTIVATE_ACCOUNT, method = RequestMethod.POST)
	public @ResponseBody String activateAccount(@RequestBody ActivateAccountRequest request)
	{
	   
		String result = null;
		try {
			URI url = new URI(mapper.getDataAccessURI() + "/"
					+ FacadeControllerURLs.DATAACCESS_AUTHBASE + "/"
					+ FacadeControllerURLs.ACTIVATE_ACCOUNT);
			result = restTemplate.postForEntity(url, request, String.class).getBody();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@RequestMapping( value = FacadeControllerURLs.RESEND_ACTIVATION, method = RequestMethod.POST)
	public @ResponseBody String resendActivationLink(@RequestBody ActivateAccountRequest request)
	{
	   
		String result = null;
		try {
			URI url = new URI(mapper.getDataAccessURI() + "/"
					+ FacadeControllerURLs.DATAACCESS_AUTHBASE + "/"
					+ FacadeControllerURLs.RESEND_ACTIVATION);
			result = restTemplate.postForEntity(url, request, String.class).getBody();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	@ExceptionHandler
	public String handleBadRequest(Exception ex,HttpServletRequest request)
	{
		System.out.println("*********** Incoming request : " + request);
		ex.printStackTrace();
		return ex.getMessage();
	}

}
