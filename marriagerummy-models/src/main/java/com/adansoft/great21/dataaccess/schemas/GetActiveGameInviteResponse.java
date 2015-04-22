package com.adansoft.great21.dataaccess.schemas;

import java.io.Serializable;
import java.util.Date;

public class GetActiveGameInviteResponse implements Serializable {

	private static final long serialVersionUID = -2267541777380365119L;
	private String requestedBY;
	private String gameInstanceID;
	private String gameLobby;
	private String gameType;
	private Date requestedDate;

	public GetActiveGameInviteResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GetActiveGameInviteResponse(String requestedBY,
			String gameInstanceID, String gameLobby, String gameType,
			Date requestedDate) {
		super();
		this.requestedBY = requestedBY;
		this.gameInstanceID = gameInstanceID;
		this.gameLobby = gameLobby;
		this.gameType = gameType;
		this.requestedDate = requestedDate;
	}

	public String getRequestedBY() {
		return requestedBY;
	}

	public void setRequestedBY(String requestedBY) {
		this.requestedBY = requestedBY;
	}

	public String getGameInstanceID() {
		return gameInstanceID;
	}

	public void setGameInstanceID(String gameInstanceID) {
		this.gameInstanceID = gameInstanceID;
	}

	public String getGameLobby() {
		return gameLobby;
	}

	public void setGameLobby(String gameLobby) {
		this.gameLobby = gameLobby;
	}

	public String getGameType() {
		return gameType;
	}

	public void setGameType(String gameType) {
		this.gameType = gameType;
	}

	public Date getRequestedDate() {
		return requestedDate;
	}

	public void setRequestedDate(Date requestedDate) {
		this.requestedDate = requestedDate;
	}

}
