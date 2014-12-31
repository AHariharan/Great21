package com.adansoft.great21.restschemas;

import java.io.Serializable;

public class GetPlayersinGameRequest implements Serializable {

	private static final long serialVersionUID = 1498828174015153445L;
	
	private String gameInstanceID;
	private String lobbyName;
	private String gameType;

	public GetPlayersinGameRequest(String gameInstanceID,String lobbyName,String gameType) {
		super();
		this.gameInstanceID = gameInstanceID;
		this.lobbyName = lobbyName;
		this.gameType = gameType;
				
	}

	public GetPlayersinGameRequest() {
		super();
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
	
	
	

}
