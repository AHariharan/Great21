package com.adansoft.great21.restschemas;

import java.io.Serializable;

public class GetGameListinLobbyRequest implements Serializable{

	private static final long serialVersionUID = 317222348547283634L;

	private String lobbyName;

	public GetGameListinLobbyRequest(String lobbyName) {
		super();
		this.lobbyName = lobbyName;
	}

	public String getLobbyName() {
		return lobbyName;
	}

	public void setLobbyName(String lobbyName) {
		this.lobbyName = lobbyName;
	}
	
	
	
}
