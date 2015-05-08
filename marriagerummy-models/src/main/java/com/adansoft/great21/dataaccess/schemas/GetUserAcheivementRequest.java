package com.adansoft.great21.dataaccess.schemas;

import java.io.Serializable;

public class GetUserAcheivementRequest implements Serializable{

	private static final long serialVersionUID = 1285877117624147528L;
	private String nickname;
	private long userid;
	private String emailaddress;
	
	public GetUserAcheivementRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GetUserAcheivementRequest(String nickname, long userid,
			String emailaddress) {
		super();
		this.nickname = nickname;
		this.userid = userid;
		this.emailaddress = emailaddress;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public long getUserid() {
		return userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
	}

	public String getEmailaddress() {
		return emailaddress;
	}

	public void setEmailaddress(String emailaddress) {
		this.emailaddress = emailaddress;
	}
	
}
