package com.adansoft.great21.restschemas;

import java.io.Serializable;

public class ShowJokerRequest implements Serializable {

	private static final long serialVersionUID = -2702768702787862355L;
	
	private String gameInstanceID;
	private String lobbyName;
	private String gameType;
	private String nickName;
	private String cardInstanceList[];
	public ShowJokerRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ShowJokerRequest(String gameInstanceID, String lobbyName,
			String gameType, String nickName, String[] cardInstanceList) {
		super();
		this.gameInstanceID = gameInstanceID;
		this.lobbyName = lobbyName;
		this.gameType = gameType;
		this.nickName = nickName;
		this.cardInstanceList = cardInstanceList;
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
	public String[] getCardInstanceList() {
		return cardInstanceList;
	}
	public void setCardInstanceList(String[] cardInstanceList) {
		this.cardInstanceList = cardInstanceList;
	}
	
	
	
	

}
