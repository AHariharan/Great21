package com.adansoft.great21.dataaccess.schemas;

import java.io.Serializable;

public class SignupResponse implements Serializable{

	private static final long serialVersionUID = -6211252729849245335L;
	
	private String message;
	private boolean valid;
	
	public SignupResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SignupResponse(String message, boolean valid) {
		super();
		this.message = message;
		this.valid = valid;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}
	
	

	
}
