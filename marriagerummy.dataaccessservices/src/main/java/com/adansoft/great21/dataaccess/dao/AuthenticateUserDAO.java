package com.adansoft.great21.dataaccess.dao;

import com.adansoft.great21.dataaccess.entities.UserAccounts;
import com.adansoft.great21.dataaccess.schemas.SignupRequest;
import com.adansoft.great21.dataaccess.schemas.SignupResponse;

public interface AuthenticateUserDAO {
	
	public UserAccounts finduserbyEmail(String emailid);
	
	public UserAccounts findUserbyNickName(String nickname);
	
	public SignupResponse signupRequest(SignupRequest request);

}
