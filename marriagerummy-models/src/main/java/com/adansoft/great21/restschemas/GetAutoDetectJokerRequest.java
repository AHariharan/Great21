package com.adansoft.great21.restschemas;

import java.io.Serializable;

public class GetAutoDetectJokerRequest implements Serializable{

	private static final long serialVersionUID = 8351840643164563516L;

	private String gameInstanceID;
	private String nickName;
	private String lobbyName;
	private String gameType;
	
	public GetAutoDetectJokerRequest(String gameInstanceID, String nickName,
			String lobbyName, String gameType) {
		super();
		this.gameInstanceID = gameInstanceID;
		this.nickName = nickName;
		this.lobbyName = lobbyName;
		this.gameType = gameType;
	}
	public GetAutoDetectJokerRequest() {
		super();
	}
	
	public String getGameInstanceID() {
		return gameInstanceID;
	}
	public void setGameInstanceID(String gameInstanceID) {
		this.gameInstanceID = gameInstanceID;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
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
