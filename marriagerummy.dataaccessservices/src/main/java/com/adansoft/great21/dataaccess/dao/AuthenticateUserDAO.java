package com.adansoft.great21.dataaccess.dao;

import com.adansoft.great21.dataaccess.entities.UserAccounts;

public interface AuthenticateUserDAO {
	
	public UserAccounts finduserbyEmail(String emailid);
	
	public UserAccounts findUserbyNickName(String nickname);

}
