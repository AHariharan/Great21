package com.adansoft.great21.dataaccess.schemas;

import java.io.Serializable;
import java.util.ArrayList;

public class GetActiveGameInviteList implements Serializable {

	private static final long serialVersionUID = 3634485576835209580L;
	private ArrayList<GetActiveGameInviteResponse> gameinviteList;
	private String nickname;
	private String emailaddr;

	public GetActiveGameInviteList() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GetActiveGameInviteList(
			ArrayList<GetActiveGameInviteResponse> gameinviteList,
			String nickname, String emailaddr) {
		super();
		this.gameinviteList = gameinviteList;
		this.nickname = nickname;
		this.emailaddr = emailaddr;
	}

	public ArrayList<GetActiveGameInviteResponse> getGameinviteList() {
		return gameinviteList;
	}

	public void setGameinviteList(
			ArrayList<GetActiveGameInviteResponse> gameinviteList) {
		this.gameinviteList = gameinviteList;
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
