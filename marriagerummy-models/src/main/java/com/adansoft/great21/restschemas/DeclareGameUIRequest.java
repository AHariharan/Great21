package com.adansoft.great21.restschemas;

import java.io.Serializable;
import java.util.HashMap;


public class DeclareGameUIRequest implements Serializable{


	private static final long serialVersionUID = -7030054919938826262L;

	private String gameInstanceID;
	private String lobbyName;
	private String gameType;
	private String nickName;
	private String jokerInstanceID;
	private HashMap<String,String[]> meldlist;
	private String closedCardInstanceid;
	
	public DeclareGameUIRequest() {
		super();
	}

	

	public DeclareGameUIRequest(String gameInstanceID, String lobbyName,
			String gameType, String nickName, String jokerInstanceID,
			HashMap<String, String[]> meldlist, String closedCardInstanceid) {
		super();
		this.gameInstanceID = gameInstanceID;
		this.lobbyName = lobbyName;
		this.gameType = gameType;
		this.nickName = nickName;
		this.jokerInstanceID = jokerInstanceID;
		this.meldlist = meldlist;
		this.closedCardInstanceid = closedCardInstanceid;
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

	public String getClosedCardInstanceid() {
		return closedCardInstanceid;
	}

	public void setClosedCardInstanceid(String closedCardInstanceid) {
		this.closedCardInstanceid = closedCardInstanceid;
	}



	public String getJokerInstanceID() {
		return jokerInstanceID;
	}



	public void setJokerInstanceID(String jokerInstanceID) {
		this.jokerInstanceID = jokerInstanceID;
	}

	

}
