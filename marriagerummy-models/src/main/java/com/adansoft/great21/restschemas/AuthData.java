package com.adansoft.great21.restschemas;

import java.io.Serializable;

public class AuthData implements Serializable{

	private static final long serialVersionUID = 7360967491087011517L;
	private long userid;
	private String emailadd;

	public AuthData() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AuthData(long userid, String emailadd) {
		super();
		this.userid = userid;
		this.emailadd = emailadd;
	}

	public long getUserid() {
		return userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
	}

	public String getEmailadd() {
		return emailadd;
	}

	public void setEmailadd(String emailadd) {
		this.emailadd = emailadd;
	}
	
	
	
	

}
