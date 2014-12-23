package com.adansoft.great21.restschemas;

import java.io.Serializable;

import com.adansoft.great21.games.GameLobby;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GetGameListinLobbyResponse implements Serializable{

	private static final long serialVersionUID = -1775026013259231238L;

	private String lobbyName;
	
	private GameLobby lobby;
	
	public GetGameListinLobbyResponse(String lobbyName)
	{
		this.lobbyName = lobbyName;
		GameLobby.createLocalGameLobby(this.lobbyName);
	}

	public GetGameListinLobbyResponse(String lobbyName, GameLobby lobby) {
		super();
		this.lobbyName = lobbyName;
		this.lobby = lobby;
	}

	public GetGameListinLobbyResponse() {
		super();	
	}

	public String getLobbyName() {
		return lobbyName;
	}

	public void setLobbyName(String lobbyName) {
		this.lobbyName = lobbyName;
	}

	public GameLobby getLobby() {
		return lobby;
	}

	public void setLobby(GameLobby lobby) {
		this.lobby = lobby;
	}
	
	

}
