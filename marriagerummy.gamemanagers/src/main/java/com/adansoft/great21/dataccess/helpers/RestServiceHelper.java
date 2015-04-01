package com.adansoft.great21.dataccess.helpers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import com.adansoft.great21.dataaccess.schemas.GetUserBasicDetailsRequest;
import com.adansoft.great21.dataaccess.schemas.GetUserBasicDetailsResponse;


public class RestServiceHelper {


	
	public RestServiceHelper() {

	}

	public static GetUserBasicDetailsResponse getBasicDetails(GameManagertoDataAccessMapper mapper,RestTemplate template,GetUserBasicDetailsRequest request)
	{
		GetUserBasicDetailsResponse response = null;
		try
		{
		URI url = new URI(mapper.getDataAccessURI() + "/"
				+ DataAccessURLs.DATAACCESS_BASE + "/"
				+ DataAccessURLs.BASIC_DETAILS);
		response = template.postForEntity(url, request, GetUserBasicDetailsResponse.class).getBody();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return response;
	}
	
}
