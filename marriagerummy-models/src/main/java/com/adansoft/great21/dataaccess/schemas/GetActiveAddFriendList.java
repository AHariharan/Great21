package com.adansoft.great21.dataaccess.schemas;

import java.io.Serializable;
import java.util.ArrayList;

public class GetActiveAddFriendList implements Serializable {

	private static final long serialVersionUID = -6146469160654140597L;
	private ArrayList<GetActiveFriendResponse> activeFriendlist;
	private String nickname;
	private String emailaddr;

	public GetActiveAddFriendList(
			ArrayList<GetActiveFriendResponse> activeFriendlist,
			String nickname, String emailaddr) {
		super();
		this.activeFriendlist = activeFriendlist;
		this.nickname = nickname;
		this.emailaddr = emailaddr;
	}

	public GetActiveAddFriendList() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ArrayList<GetActiveFriendResponse> getActiveFriendlist() {
		return activeFriendlist;
	}

	public void setActiveFriendlist(
			ArrayList<GetActiveFriendResponse> activeFriendlist) {
		this.activeFriendlist = activeFriendlist;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getEmailaddr() {
		return emailaddr;
	}

	public void setEmailaddr(String emailaddr) {
		this.emailaddr = emailaddr;
	}

}
