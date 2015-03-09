package com.adansoft.great21.dataaccess.dao;

import com.adansoft.great21.dataaccess.schemas.GetUserBasicDetailsRequest;
import com.adansoft.great21.dataaccess.schemas.GetUserBasicDetailsResponse;

public interface BasicDataAccessDAO {
	
     public GetUserBasicDetailsResponse getBasicDetails(GetUserBasicDetailsRequest requset);

}
