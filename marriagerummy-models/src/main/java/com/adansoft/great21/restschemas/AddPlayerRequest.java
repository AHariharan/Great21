package com.adansoft.great21.restschemas;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AddPlayerRequest implements Serializable{
	

	private static final long serialVersionUID = 4182950291531101449L;

	private AuthData authdata;
	private String playerType;
	private String nickname;
	private String gameInstanceID;
	private String lobbyName;
	private String gameType;
	
	public AddPlayerRequest()
	{
		super();
	}
	
	

	public AddPlayerRequest(AuthData authdata, String playerType, String nickname,
			String gameInstanceID, String lobbyName, String gameType) {
		super();
		this.authdata = authdata;
		this.playerType = playerType;
		this.nickname = nickname;
		this.gameInstanceID = gameInstanceID;
		this.lobbyName = lobbyName;
		this.gameType = gameType;
	}



	public String getPlayerType() {
		return playerType;
	}

	public void setPlayerType(String playerType) {
		this.playerType = playerType;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
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



	public AuthData getAuthdata() {
		return authdata;
	}



	public void setAuthdata(AuthData authdata) {
		this.authdata = authdata;
	}



	
		
}
