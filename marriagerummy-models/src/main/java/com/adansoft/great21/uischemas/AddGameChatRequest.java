package com.adansoft.great21.uischemas;

import java.io.Serializable;

import com.adansoft.great21.gameplay.GameMessage;

public class AddGameChatRequest implements Serializable {

	private static final long serialVersionUID = -6962075818024479436L;
	
	private String gameInstanceID;
	private GameMessage message;
	
	public AddGameChatRequest() {
		super();
		}

	
	public AddGameChatRequest(String gameInstanceID, GameMessage message) {
		super();
		this.gameInstanceID = gameInstanceID;
		this.message = message;
	}


	public String getGameInstanceID() {
		return gameInstanceID;
	}


	public void setGameInstanceID(String gameInstanceID) {
		this.gameInstanceID = gameInstanceID;
	}


	public GameMessage getMessage() {
		return message;
	}


	public void setMessage(GameMessage message) {
		this.message = message;
	}
	
	

	
	
}
