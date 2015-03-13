package com.adansoft.great21.dataaccess.dao;

import com.adansoft.great21.dataaccess.schemas.GetProfileInformationRequest;
import com.adansoft.great21.dataaccess.schemas.GetProfileInformationResponse;
import com.adansoft.great21.dataaccess.schemas.GetUserBasicDetailsRequest;
import com.adansoft.great21.dataaccess.schemas.GetUserBasicDetailsResponse;
import com.adansoft.great21.dataaccess.schemas.UpdateProfileInformationRequest;
import com.adansoft.great21.dataaccess.schemas.UserAuditRequest;

public interface BasicDataAccessDAO {
	
	 public String addAudit(UserAuditRequest request);
	
     public GetUserBasicDetailsResponse getBasicDetails(GetUserBasicDetailsRequest request);
     
     public GetProfileInformationResponse getProfileInformation(GetProfileInformationRequest request);

     public String updateProfileInformation(UpdateProfileInformationRequest request);
}
