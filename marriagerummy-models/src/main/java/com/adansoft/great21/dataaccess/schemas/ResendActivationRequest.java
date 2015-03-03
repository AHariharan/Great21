package com.adansoft.great21.dataaccess.schemas;

import java.io.Serializable;

public class ResendActivationRequest implements Serializable{

	private static final long serialVersionUID = -4049989641912502991L;
	
	private String emailAddress;

	public ResendActivationRequest() {
		super();
	}

	public ResendActivationRequest(String emailAddress) {
		super();
		this.emailAddress = emailAddress;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	

}
