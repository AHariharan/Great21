package com.adansoft.great21.uischemas;

import java.io.Serializable;

public class UICard implements Serializable{

	private static final long serialVersionUID = 6366104932210631067L;
	private String gameInstanceID;
	private String lobbyName;
	private String gameType;
	private String nickName;
	private String cardInstanceID;
	
	public UICard() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UICard(String gameInstanceID, String lobbyName, String gameType,
			String nickName, String cardInstanceID) {
		super();
		this.gameInstanceID = gameInstanceID;
		this.lobbyName = lobbyName;
		this.gameType = gameType;
		this.nickName = nickName;
		this.cardInstanceID = cardInstanceID;
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

	public String getCardInstanceID() {
		return cardInstanceID;
	}

	public void setCardInstanceID(String cardInstanceID) {
		this.cardInstanceID = cardInstanceID;
	}
	
	
	
}
