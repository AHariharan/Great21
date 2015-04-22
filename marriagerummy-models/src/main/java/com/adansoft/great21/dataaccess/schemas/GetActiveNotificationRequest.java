package com.adansoft.great21.dataaccess.schemas;

import java.io.Serializable;

public class GetActiveNotificationRequest implements Serializable {

	private static final long serialVersionUID = 7086927381846547555L;
	private String emailaddress;
	private long userid;
	private String nickname;

	public GetActiveNotificationRequest(String emailaddress, long userid,
			String nickname) {
		super();
		this.emailaddress = emailaddress;
		this.userid = userid;
		this.nickname = nickname;
	}

	public GetActiveNotificationRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getEmailaddress() {
		return emailaddress;
	}

	public void setEmailaddress(String emailaddress) {
		this.emailaddress = emailaddress;
	}

	public long getUserid() {
		return userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

}
