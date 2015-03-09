package com.adansoft.great21.dataaccess.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.adansoft.great21.dataaccess.dao.BasicDataAccessDAOImpl;
import com.adansoft.great21.dataaccess.schemas.GetUserBasicDetailsRequest;
import com.adansoft.great21.dataaccess.schemas.GetUserBasicDetailsResponse;

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

}
