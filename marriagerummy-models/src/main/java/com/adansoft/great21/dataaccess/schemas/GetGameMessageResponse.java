package com.adansoft.great21.dataaccess.schemas;

import java.io.Serializable;
import java.util.ArrayList;

public class GetGameMessageResponse implements Serializable {

	private static final long serialVersionUID = 7048723479471137066L;
	private String nickname;
	private ArrayList<GameMessage> messagelist;

	public GetGameMessageResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GetGameMessageResponse(String nickname,
			ArrayList<GameMessage> messagelist) {
		super();
		this.nickname = nickname;
		this.messagelist = messagelist;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public ArrayList<GameMessage> getMessagelist() {
		return messagelist;
	}

	public void setMessagelist(ArrayList<GameMessage> messagelist) {
		this.messagelist = messagelist;
	}

}
