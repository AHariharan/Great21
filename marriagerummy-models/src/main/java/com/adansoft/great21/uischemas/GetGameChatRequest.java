package com.adansoft.great21.uischemas;

import java.io.Serializable;

public class GetGameChatRequest implements Serializable{

	private static final long serialVersionUID = -787020362374918602L;
	
	private String gameInstanceID;
	private int currentChatCount;

	public GetGameChatRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GetGameChatRequest(String gameInstanceID, int currentChatCount) {
		super();
		this.gameInstanceID = gameInstanceID;
		this.currentChatCount = currentChatCount;
	}

	public String getGameInstanceID() {
		return gameInstanceID;
	}

	public void setGameInstanceID(String gameInstanceID) {
		this.gameInstanceID = gameInstanceID;
	}

	public int getCurrentChatCount() {
		return currentChatCount;
	}

	public void setCurrentChatCount(int currentChatCount) {
		this.currentChatCount = currentChatCount;
	}

	
	
}
