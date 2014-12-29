package com.adansoft.great21.uischemas;

import java.io.Serializable;

import com.adansoft.great21.gameplay.GameMessage;

public class GetGameChatResponse implements Serializable{

	private static final long serialVersionUID = -9066139623087387946L;
	
	private int currentChatCount;
	private GameMessage[] messages;
	
	public GetGameChatResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GetGameChatResponse(int currentChatCount, GameMessage[] messages) {
		super();
		this.currentChatCount = currentChatCount;
		this.messages = messages;
	}

	public int getCurrentChatCount() {
		return currentChatCount;
	}

	public void setCurrentChatCount(int currentChatCount) {
		this.currentChatCount = currentChatCount;
	}

	public GameMessage[] getMessages() {
		return messages;
	}

	public void setMessages(GameMessage[] messages) {
		this.messages = messages;
	}
	
	
	
	

}
