package com.adansoft.great21.gameplay;

import java.io.Serializable;

public class GameMessage implements Serializable{

	private static final long serialVersionUID = -8434806749720411687L;
	
	private String playerName;
	private String message;
	private String playerpos;
	
	
	
	public GameMessage() {
		super();
		
	}



	public GameMessage(String playerName, String message, String playerpos) {
		super();
		this.playerName = playerName;
		this.message = message;
		this.playerpos = playerpos;
	}



	public String getPlayerName() {
		return playerName;
	}



	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}



	public String getMessage() {
		return message;
	}



	public void setMessage(String message) {
		this.message = message;
	}



	public String getPlayerpos() {
		return playerpos;
	}



	public void setPlayerpos(String playerpos) {
		this.playerpos = playerpos;
	}
	
	

}
