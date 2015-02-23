package com.adansoft.great21.dataaccess.schemas;

import java.io.Serializable;

public class SignupRequest implements Serializable {

	private static final long serialVersionUID = -6555766038421405242L;

	private String emailAddress;
	private String nickName;
	private String password;
	
	public SignupRequest() {
		super();
	}

	public SignupRequest(String emailAddress, String nickName, String password) {
		super();
		this.emailAddress = emailAddress;
		this.nickName = nickName;
		this.password = password;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	
	
	
}
