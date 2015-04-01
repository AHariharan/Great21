package com.adansoft.great21.restschemas;

import java.io.Serializable;
import java.util.HashMap;

public class PlayerShowStatusResponse implements Serializable{

	private static final long serialVersionUID = 8520615345300046017L;

	private String gameInstanceID;
	private HashMap<String,String> playerShowStatus;
	private boolean newGame;
	
	public PlayerShowStatusResponse(String gameInstanceID,
			HashMap<String, String> playerShowStatus, boolean newGame) {
		super();
		this.gameInstanceID = gameInstanceID;
		this.playerShowStatus = playerShowStatus;
		this.newGame = newGame;
	}


	public PlayerShowStatusResponse() {
		super();
	
	}

	public String getGameInstanceID() {
		return gameInstanceID;
	}

	public void setGameInstanceID(String gameInstanceID) {
		this.gameInstanceID = gameInstanceID;
	}

	public HashMap<String, String> getPlayerShowStatus() {
		return playerShowStatus;
	}

	public void setPlayerShowStatus(HashMap<String, String> playerShowStatus) {
		this.playerShowStatus = playerShowStatus;
	}


	public boolean isNewGame() {
		return newGame;
	}


	public void setNewGame(boolean newGame) {
		this.newGame = newGame;
	}
	
	
	
	
}
