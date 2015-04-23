package com.adansoft.great21.dataaccess.schemas;

import java.io.Serializable;

public class FriendResponse implements Serializable
{
	private static final long serialVersionUID = -7791958960011064243L;
	private String nickname;
	private String emailAddress;
	private String Rating;
	
	public FriendResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FriendResponse(String nickname, String emailAddress,
			String rating) {
		super();
		this.nickname = nickname;
		this.emailAddress = emailAddress;
		Rating = rating;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getRating() {
		return Rating;
	}

	public void setRating(String rating) {
		Rating = rating;
	}

}
