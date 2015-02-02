package com.adansoft.great21.restschemas;

import java.io.Serializable;
import java.util.HashMap;

import com.adansoft.great21.models.Card;

public class DeclareGameRequest implements Serializable{

	private static final long serialVersionUID = 2196929147465720886L;
	
	private String gameInstanceID;
	private String lobbyName;
	private String gameType;
	private String nickName;
	private HashMap<String,Card[]> meldlist;
	private Card closedCard;
	
	public DeclareGameRequest() {
		super();
	}

	public DeclareGameRequest(String gameInstanceID, String lobbyName,
			String gameType, String nickName, HashMap<String, Card[]> meldlist,
			Card closedCard) {
		super();
		this.gameInstanceID = gameInstanceID;
		this.lobbyName = lobbyName;
		this.gameType = gameType;
		this.nickName = nickName;
		this.meldlist = meldlist;
		this.closedCard = closedCard;
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

	public HashMap<String, Card[]> getMeldlist() {
		return meldlist;
	}

	public void setMeldlist(HashMap<String, Card[]> meldlist) {
		this.meldlist = meldlist;
	}

	public Card getClosedCard() {
		return closedCard;
	}

	public void setClosedCard(Card closedCard) {
		this.closedCard = closedCard;
	}
	
	

}
