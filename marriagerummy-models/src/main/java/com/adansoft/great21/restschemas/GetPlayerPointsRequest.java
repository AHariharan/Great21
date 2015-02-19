package com.adansoft.great21.restschemas;

import java.io.Serializable;

public class GetPlayerPointsRequest implements Serializable {

	private static final long serialVersionUID = -3435851388015518119L;
	private String gameInstanceID;
	private String lobbyName;
	private String gameType;
	private String nickName;
	
	public GetPlayerPointsRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GetPlayerPointsRequest(String gameInstanceID, String lobbyName,
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
