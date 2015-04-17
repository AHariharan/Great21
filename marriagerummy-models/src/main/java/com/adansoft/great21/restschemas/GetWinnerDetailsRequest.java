package com.adansoft.great21.restschemas;

import java.io.Serializable;

public class GetWinnerDetailsRequest implements Serializable{

	private static final long serialVersionUID = -1030054574310969510L;

	private String gameInstanceID;
	private String lobbyName;
	private String gameType;
	private String nickName;

	public GetWinnerDetailsRequest() {
		super();
	}

	public GetWinnerDetailsRequest(String gameInstanceID, String lobbyName,
			String gameType, String nickName) {
		super();
		this.gameInstanceID = gameInstanceID;
		this.lobbyName = lobbyName;
		this.gameType = gameType;
		this.nickName = nickName;
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

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
	

}
