package com.adansoft.great21.dataaccess.gamedata.schemas;

import java.io.Serializable;
import java.util.HashMap;

public class UpdatePlayerRummyStat implements Serializable {

	private static final long serialVersionUID = 1275868152098502800L;
    private HashMap<String,UpdateRummyStat> playerlist;
	 
	public UpdatePlayerRummyStat() {
		playerlist = new HashMap<String, UpdateRummyStat>();
	}

	public UpdatePlayerRummyStat(HashMap<String, UpdateRummyStat> playerlist) {
		super();
		this.playerlist = playerlist;
	}

	public HashMap<String, UpdateRummyStat> getPlayerlist() {
		return playerlist;
	}

	public void setPlayerlist(HashMap<String, UpdateRummyStat> playerlist) {
		this.playerlist = playerlist;
	}

	
}
