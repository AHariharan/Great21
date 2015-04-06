package com.adansoft.great21.restschemas;

import java.io.Serializable;

public class GetActivePlayersinGameRequest implements Serializable {


	private static final long serialVersionUID = 5161265105839109211L;

	private String gameInstanceID;
	private String lobbyName;
	private String gameType;

	public GetActivePlayersinGameRequest(String gameInstanceID,String lobbyName,String gameType) {
		super();
		this.gameInstanceID = gameInstanceID;
		this.lobbyName = lobbyName;
		this.gameType = gameType;
				
	}

	public GetActivePlayersinGameRequest() {
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
