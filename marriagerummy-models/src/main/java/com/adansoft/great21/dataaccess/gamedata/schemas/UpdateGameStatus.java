package com.adansoft.great21.dataaccess.gamedata.schemas;

import java.io.Serializable;

public class UpdateGameStatus implements Serializable {

	private static final long serialVersionUID = 53833204638316423L;
	private String gameInstanceID;
	private String status;

	public UpdateGameStatus() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UpdateGameStatus(String gameInstanceID, String status) {
		super();
		this.gameInstanceID = gameInstanceID;
		this.status = status;
	}

	public String getGameInstanceID() {
		return gameInstanceID;
	}

	public void setGameInstanceID(String gameInstanceID) {
		this.gameInstanceID = gameInstanceID;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
