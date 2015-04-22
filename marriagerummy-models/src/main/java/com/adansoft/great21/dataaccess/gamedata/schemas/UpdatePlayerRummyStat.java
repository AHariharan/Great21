package com.adansoft.great21.dataaccess.gamedata.schemas;

import java.io.Serializable;
import java.util.HashMap;

public class UpdatePlayerRummyStat implements Serializable {

	private String gameInstanceID;
	private static final long serialVersionUID = 1275868152098502800L;
    private HashMap<String,UpdateRummyStat> playerlist;
	 
	public UpdatePlayerRummyStat() {
		playerlist = new HashMap<String, UpdateRummyStat>();
	}

	
	public UpdatePlayerRummyStat(String gameInstanceID,
			HashMap<String, UpdateRummyStat> playerlist) {
		super();
		this.gameInstanceID = gameInstanceID;
		this.playerlist = playerlist;
	}

	
	

	public String getGameInstanceID() {
		return gameInstanceID;
	}


	public void setGameInstanceID(String gameInstanceID) {
		this.gameInstanceID = gameInstanceID;
	}


	public HashMap<String, UpdateRummyStat> getPlayerlist() {
		return playerlist;
	}

	public void setPlayerlist(HashMap<String, UpdateRummyStat> playerlist) {
		this.playerlist = playerlist;
	}

	
}
