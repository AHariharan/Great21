package com.adansoft.great21.dataaccess.gamedata.schemas;

import java.io.Serializable;
import java.util.HashMap;

public class UpdatePlayerStatusPoints implements Serializable {

	private static final long serialVersionUID = 4700191253568036993L;
	private String gameInstanceID;
	private String gameRoundID;
	private HashMap<String,PersistPointsorCashforRound> playerList;
	
	public UpdatePlayerStatusPoints() {
		playerList = new HashMap<String, PersistPointsorCashforRound>();
	}

	public UpdatePlayerStatusPoints(String gameInstanceID, String gameRoundID,
			HashMap<String, PersistPointsorCashforRound> playerList) {
		super();
		this.gameInstanceID = gameInstanceID;
		this.gameRoundID = gameRoundID;
		this.playerList = playerList;
	}

	public String getGameInstanceID() {
		return gameInstanceID;
	}

	public void setGameInstanceID(String gameInstanceID) {
		this.gameInstanceID = gameInstanceID;
	}

	public String getGameRoundID() {
		return gameRoundID;
	}

	public void setGameRoundID(String gameRoundID) {
		this.gameRoundID = gameRoundID;
	}

	public HashMap<String, PersistPointsorCashforRound> getPlayerList() {
		return playerList;
	}

	public void setPlayerList(
			HashMap<String, PersistPointsorCashforRound> playerList) {
		this.playerList = playerList;
	}

	
}
