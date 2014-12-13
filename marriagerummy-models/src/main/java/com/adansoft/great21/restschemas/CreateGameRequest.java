package com.adansoft.great21.restschemas;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateGameRequest implements Serializable{

	private static final long serialVersionUID = -9161854892669569490L;
	
	private String gameType;
	private String gameDescription;
	private int maxPlayers;
	private boolean isFriendsOnly;
	private boolean isbyInviteOnly;
	private String gameMode;
	
	
	public CreateGameRequest()
	{
		
	}
	
		
	public CreateGameRequest(String gameType, String gameDescription,
			int maxPlayers, boolean isFriendsOnly, boolean isbyInviteOnly,
			String gameMode) {
		super();
		this.gameType = gameType;
		this.gameDescription = gameDescription;
		this.maxPlayers = maxPlayers;
		this.isFriendsOnly = isFriendsOnly;
		this.isbyInviteOnly = isbyInviteOnly;
		this.gameMode = gameMode;
	}




	public String getGameType() {
		return gameType;
	}

	public void setGameType(String gameType) {
		this.gameType = gameType;
	}

	public String getGameDescription() {
		return gameDescription;
	}

	public void setGameDescription(String gameDescription) {
		this.gameDescription = gameDescription;
	}

	public int getMaxPlayers() {
		return maxPlayers;
	}

	public void setMaxPlayers(int maxPlayers) {
		this.maxPlayers = maxPlayers;
	}

	public boolean isFriendsOnly() {
		return isFriendsOnly;
	}

	public void setFriendsOnly(boolean isFriendsOnly) {
		this.isFriendsOnly = isFriendsOnly;
	}

	public boolean isIsbyInviteOnly() {
		return isbyInviteOnly;
	}

	public void setIsbyInviteOnly(boolean isbyInviteOnly) {
		this.isbyInviteOnly = isbyInviteOnly;
	}

	public String getGameMode() {
		return gameMode;
	}

	public void setGameMode(String gameMode) {
		this.gameMode = gameMode;
	}
	
	
	
}
