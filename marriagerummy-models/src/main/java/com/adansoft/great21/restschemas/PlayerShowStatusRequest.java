package com.adansoft.great21.restschemas;

import java.io.Serializable;

public class PlayerShowStatusRequest implements Serializable {

	private static final long serialVersionUID = -3507892661868103062L;
	private String gameInstanceID;
	private String lobbyName;
	private String gameType;
	private String nickName;

	public PlayerShowStatusRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PlayerShowStatusRequest(String gameInstanceID, String lobbyName,
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
