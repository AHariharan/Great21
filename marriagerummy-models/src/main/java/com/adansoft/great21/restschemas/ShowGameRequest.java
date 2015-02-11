package com.adansoft.great21.restschemas;

import java.io.Serializable;
import java.util.HashMap;

import com.adansoft.great21.models.Card;

public class ShowGameRequest implements Serializable{


	private static final long serialVersionUID = 284586430750284826L;
	
	private String gameInstanceID;
	private String lobbyName;
	private String gameType;
	private String nickName;
	private HashMap<String,Card[]> meldlist;


	public ShowGameRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ShowGameRequest(String gameInstanceID, String lobbyName,
			String gameType, String nickName, HashMap<String, Card[]> meldlist) {
		super();
		this.gameInstanceID = gameInstanceID;
		this.lobbyName = lobbyName;
		this.gameType = gameType;
		this.nickName = nickName;
		this.meldlist = meldlist;
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


}
