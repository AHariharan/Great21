package com.adansoft.great21.dataaccess.gamedata.schemas;

import java.io.Serializable;

public class PersistNewGame implements Serializable {

	private static final long serialVersionUID = -1459338113134522334L;

	private String gameInstanceID;
	private String lobbyName;
	private String gameType;
	private String hostnick;
	private boolean perCard;
	private boolean pointBased;
	private long hostuserid;

	public PersistNewGame() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public PersistNewGame(String gameInstanceID, String lobbyName,
			String gameType, String hostnick, boolean perCard,
			boolean pointBased, long hostuserid) {
		super();
		this.gameInstanceID = gameInstanceID;
		this.lobbyName = lobbyName;
		this.gameType = gameType;
		this.hostnick = hostnick;
		this.perCard = perCard;
		this.pointBased = pointBased;
		this.hostuserid = hostuserid;
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

	public String getHostnick() {
		return hostnick;
	}

	public void setHostnick(String hostnick) {
		this.hostnick = hostnick;
	}

	public boolean isPerCard() {
		return perCard;
	}

	public void setPerCard(boolean perCard) {
		this.perCard = perCard;
	}

	public boolean isPointBased() {
		return pointBased;
	}

	public void setPointBased(boolean pointBased) {
		this.pointBased = pointBased;
	}



	public long getHostuserid() {
		return hostuserid;
	}



	public void setHostuserid(long hostuserid) {
		this.hostuserid = hostuserid;
	}

	
	
}
