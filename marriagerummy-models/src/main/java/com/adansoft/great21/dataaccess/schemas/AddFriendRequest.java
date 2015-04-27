package com.adansoft.great21.dataaccess.schemas;

import java.io.Serializable;

public class AddFriendRequest implements Serializable {

	/**
	 * 
	 */
	private String nickName;
	private String desinationNickname;

	private static final long serialVersionUID = 6585821350252500796L;

	public AddFriendRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AddFriendRequest(String nickName, String desinationNickname) {
		super();
		this.nickName = nickName;
		this.desinationNickname = desinationNickname;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getDesinationNickname() {
		return desinationNickname;
	}

	public void setDesinationNickname(String desinationNickname) {
		this.desinationNickname = desinationNickname;
	}

	
	
	
	
}
