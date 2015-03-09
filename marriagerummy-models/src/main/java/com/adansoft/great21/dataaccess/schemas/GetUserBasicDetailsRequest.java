
package com.adansoft.great21.dataaccess.schemas;

import java.io.Serializable;

public class GetUserBasicDetailsRequest implements Serializable{

	private static final long serialVersionUID = 1970396693258512740L;
  
	private long userid;
	private String emailadd;
	
	public GetUserBasicDetailsRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GetUserBasicDetailsRequest(long userid, String emailadd) {
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
