package com.adansoft.great21.dataaccess.schemas;

import java.io.Serializable;
import java.util.ArrayList;

public class SendGameInviteRequest implements Serializable {

	private static final long serialVersionUID = -8506369114975324046L;
	private String invitornickname;
	private String gameInstanceID;
	private String gameType;
	private String lobbyName;
	private ArrayList<String> nicknames;
	
	public SendGameInviteRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	
	public SendGameInviteRequest(String invitornickname, String gameInstanceID,
			String gameType, String lobbyName, ArrayList<String> nicknames) {
		super();
		this.invitornickname = invitornickname;
		this.gameInstanceID = gameInstanceID;
		this.gameType = gameType;
		this.lobbyName = lobbyName;
		this.nicknames = nicknames;
	}




	public String getGameInstanceID() {
		return gameInstanceID;
	}

	public void setGameInstanceID(String gameInstanceID) {
		this.gameInstanceID = gameInstanceID;
	}

	public String getGameType() {
		return gameType;
	}

	public void setGameType(String gameType) {
		this.gameType = gameType;
	}

	public String getLobbyName() {
		return lobbyName;
	}

	public void setLobbyName(String lobbyName) {
		this.lobbyName = lobbyName;
	}

	public ArrayList<String> getNicknames() {
		return nicknames;
	}

	public void setNicknames(ArrayList<String> nicknames) {
		this.nicknames = nicknames;
	}




	public String getInvitornickname() {
		return invitornickname;
	}




	public void setInvitornickname(String invitornickname) {
		this.invitornickname = invitornickname;
	}

	
	
	
}
