package com.adansoft.great21.restschemas;

import java.io.Serializable;

public class FinishGameRoundRequest implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4499400542756943269L;
	private String gameInstanceID;
	private String lobbyName;
	private String gameType;
	public FinishGameRoundRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FinishGameRoundRequest(String gameInstanceID, String lobbyName,
			String gameType) {
		super();
		this.gameInstanceID = gameInstanceID;
		this.lobbyName = lobbyName;
		this.gameType = gameType;
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
