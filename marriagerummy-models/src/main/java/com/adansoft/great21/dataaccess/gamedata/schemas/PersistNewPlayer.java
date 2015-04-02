package com.adansoft.great21.dataaccess.gamedata.schemas;

import java.io.Serializable;

public class PersistNewPlayer implements Serializable{

	private static final long serialVersionUID = -7909258872795961339L;
 
    private String gameInstanceID;
	private long userid;
	private int playerpos;
	
	public PersistNewPlayer() {
		super();
	}

	public PersistNewPlayer(String gameInstanceID, long userid, int playerpos) {
		super();
		this.gameInstanceID = gameInstanceID;
		this.userid = userid;
		this.playerpos = playerpos;
	}

	public String getGameinstanceID() {
		return gameInstanceID;
	}

	public void setGameinstanceID(String gameinstanceID) {
		this.gameInstanceID = gameinstanceID;
	}

	public long getUserid() {
		return userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
	}

	public int getPlayerpos() {
		return playerpos;
	}

	public void setPlayerpos(int playerpos) {
		this.playerpos = playerpos;
	}

	
	

}
