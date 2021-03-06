package com.adansoft.great21.restschemas;

import java.io.Serializable;
import java.util.HashMap;

import com.adansoft.great21.models.Card;

public class ShowGameUIRequest implements Serializable{

	private static final long serialVersionUID = 7211059932022619671L;
	private String gameInstanceID;
	private String lobbyName;
	private String gameType;
	private String nickName;
	private HashMap<String,String[]> meldlist;

	public ShowGameUIRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ShowGameUIRequest(String gameInstanceID, String lobbyName,
			String gameType, String nickName, HashMap<String, String[]> meldlist) {
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

	public HashMap<String, String[]> getMeldlist() {
		return meldlist;
	}

	public void setMeldlist(HashMap<String, String[]> meldlist) {
		this.meldlist = meldlist;
	}



	
	
	
}
