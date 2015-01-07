package com.adansoft.great21.restschemas;

import java.io.Serializable;

public class RemovePlayerRequest implements Serializable{

	private static final long serialVersionUID = -3786630433253138524L;
	private String gameInstanceID;
	private String nickname;
	private String gameType;
	private String lobbyName;
	private String requestedby;
	
	public RemovePlayerRequest() {
		super();
		}

	public RemovePlayerRequest(String gameInstanceID, String nickname,String gameType,String lobbyName,String requestedby) {
		super();
		this.gameInstanceID = gameInstanceID;
		this.nickname = nickname;
		this.gameType = gameType;
		this.lobbyName = lobbyName;
		this.requestedby = requestedby;
	}

	public String getGameInstanceID() {
		return gameInstanceID;
	}

	public void setGameInstanceID(String gameInstanceID) {
		this.gameInstanceID = gameInstanceID;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getGameType() {
		return gameType;
	}

	public void setGameType(String gameType) {
		this.gameType = gameType;
	}

	public String getLobbyName() {
		return lobbyName;
	}

	public void setLobbyName(String lobbyName) {
		this.lobbyName = lobbyName;
	}

	public String getRequestedby() {
		return requestedby;
	}

	public void setRequestedby(String requestedby) {
		this.requestedby = requestedby;
	}
	

}
