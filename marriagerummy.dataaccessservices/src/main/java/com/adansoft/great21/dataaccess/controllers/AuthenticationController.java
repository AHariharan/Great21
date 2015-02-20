package com.adansoft.great21.dataaccess.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.adansoft.great21.dataaccess.dao.AuthenticateUserDAOImpl;
import com.adansoft.great21.dataaccess.entities.UserAccounts;

@RestController
@RequestMapping(DataAccessServiceURLs.DATAACCESS_AUTHBASE)
public class AuthenticationController {
	
	@Autowired
	private AuthenticateUserDAOImpl authdao;
	
	@RequestMapping( value = DataAccessServiceURLs.FINDUSER, method = RequestMethod.POST)
	public @ResponseBody UserAccounts Authenticate(@RequestBody String name)
	{
		System.out.println("Data access Service request receive for user :- " + name);
		UserAccounts account = authdao.finduserbyEmail(name);
		return account;
	}

}
