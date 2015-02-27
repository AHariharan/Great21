package com.adansoft.great21.dataaccess.schemas;

import java.io.Serializable;

public class ActivateAccountRequest implements Serializable {

	private static final long serialVersionUID = 9035113373152352175L;
	private String emailAddress;
	private String activationCode;
	
	public ActivateAccountRequest(String emailAddress, String activationCode) {
		super();
		this.emailAddress = emailAddress;
		this.activationCode = activationCode;
	}

	public ActivateAccountRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getActivationCode() {
		return activationCode;
	}

	public void setActivationCode(String activationCode) {
		this.activationCode = activationCode;
	}
	
	
	
	
	
}
