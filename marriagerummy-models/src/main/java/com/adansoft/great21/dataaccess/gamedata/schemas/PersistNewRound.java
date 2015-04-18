package com.adansoft.great21.dataaccess.gamedata.schemas;

import java.io.Serializable;

public class PersistNewRound implements Serializable{

	private static final long serialVersionUID = 149797528948755010L;
	private String gameInstanceID;
	private String gameRoundID;
	
	public PersistNewRound() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public PersistNewRound(String gameInstanceID, String gameRoundID) {
		super();
		this.gameInstanceID = gameInstanceID;
		this.gameRoundID = gameRoundID;
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

	

}
