package com.adansoft.great21.restschemas;

import java.io.Serializable;
import java.util.HashMap;

public class PlayerStatusinGameResponse implements Serializable{

	private static final long serialVersionUID = -5071544822965661999L;

	private String gameInstanceID;
	private String lobbyName;
	private String gameType;
	private HashMap<String,String> playerstatusMap;
	
	public PlayerStatusinGameResponse() {
		playerstatusMap = new HashMap<String, String>();
	}

	public PlayerStatusinGameResponse(String gameInstanceID, String lobbyName,
			String gameType, HashMap<String, String> playerstatusMap) {
		super();
		this.gameInstanceID = gameInstanceID;
		this.lobbyName = lobbyName;
		this.gameType = gameType;
		this.playerstatusMap = playerstatusMap;
	}

	public String getGameInstanceID() {
		return gameInstanceID;
	}

	public void setGameInstanceID(String gameInstanceID) {
		this.gameInstanceID = gameInstanceID;
	}

	public String getLobbyName() {
		return lobbyName;
	}

	public void setLobbyName(String lobbyName) {
		this.lobbyName = lobbyName;
	}

	public String getGameType() {
		return gameType;
	}

	public void setGameType(String gameType) {
		this.gameType = gameType;
	}

	public HashMap<String, String> getPlayerstatusMap() {
		return playerstatusMap;
	}

	public void setPlayerstatusMap(HashMap<String, String> playerstatusMap) {
		this.playerstatusMap = playerstatusMap;
	}

	
	
}
