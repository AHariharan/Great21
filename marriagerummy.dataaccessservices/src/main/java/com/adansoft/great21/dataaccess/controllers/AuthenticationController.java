package com.adansoft.great21.dataaccess.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.adansoft.great21.dataaccess.dao.AuthenticateUserDAOImpl;
import com.adansoft.great21.dataaccess.entities.UserAccounts;
import com.adansoft.great21.dataaccess.schemas.SignupRequest;
import com.adansoft.great21.dataaccess.schemas.SignupResponse;

@RestController
@RequestMapping(DataAccessServiceURLs.DATAACCESS_AUTHBASE)
public class AuthenticationController {
	
	@Autowired
	private AuthenticateUserDAOImpl authdao;
	
	@Transactional
	@RequestMapping( value = DataAccessServiceURLs.FINDUSER, method = RequestMethod.POST)
	public @ResponseBody UserAccounts Authenticate(@RequestBody String name)
	{
		System.out.println("Data access Service request receive for user :- " + name);
		UserAccounts account = authdao.finduserbyEmail(name);
		return account;
	}
	
	@Transactional
	@RequestMapping( value = DataAccessServiceURLs.SIGNUP, method = RequestMethod.POST)
	public @ResponseBody SignupResponse signUp(@RequestBody SignupRequest request)
	{
		System.out.println("Signup Request Received :- " + request.getEmailAddress());
		SignupResponse response = authdao.signupRequest(request);
		return response;
	}

}
