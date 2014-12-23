package com.adansoft.great21.restschemas;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateGameRequest implements Serializable{

	private static final long serialVersionUID = -9161854892669569490L;
	
	private String lobbyType;
	private String gameType;
	private String gameDescription;
	private int maxPlayers;
	private boolean isFriendsOnly;
	private boolean isbyInviteOnly;
	private String gameMode;
	private String createdBy;
	private boolean gamePointsBased;
	private boolean gamePerCardBase;
	private int maxPoints;
	private int perCardAmount;
	private int maxRounds;
	
	public CreateGameRequest()
	{
		
	}
	
		
	



	public CreateGameRequest(String lobbyType, String gameType,
			String gameDescription, int maxPlayers, boolean isFriendsOnly,
			boolean isbyInviteOnly, String gameMode, String createdBy,
			boolean isGamePointsBased, boolean isGamePerCardBase,
			int maxPoints, int perCardAmount, int maxRounds) {
		super();
		this.lobbyType = lobbyType;
		this.gameType = gameType;
		this.gameDescription = gameDescription;
		this.maxPlayers = maxPlayers;
		this.isFriendsOnly = isFriendsOnly;
		this.isbyInviteOnly = isbyInviteOnly;
		this.gameMode = gameMode;
		this.createdBy = createdBy;
		this.gamePointsBased = isGamePointsBased;
		this.gamePerCardBase = isGamePerCardBase;
		this.maxPoints = maxPoints;
		this.perCardAmount = perCardAmount;
		this.maxRounds = maxRounds;
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


	public String getLobbyType() {
		return lobbyType;
	}


	public void setLobbyType(String lobbyType) {
		this.lobbyType = lobbyType;
	}


	public String getCreatedBy() {
		return createdBy;
	}


	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}


	

	public boolean isGamePointsBased() {
		return gamePointsBased;
	}






	public void setGamePointsBased(boolean gamePointsBased) {
		this.gamePointsBased = gamePointsBased;
	}






	public boolean isGamePerCardBase() {
		return gamePerCardBase;
	}






	public void setGamePerCardBase(boolean gamePerCardBase) {
		this.gamePerCardBase = gamePerCardBase;
	}






	public int getMaxPoints() {
		return maxPoints;
	}


	public void setMaxPoints(int maxPoints) {
		this.maxPoints = maxPoints;
	}


	public int getPerCardAmount() {
		return perCardAmount;
	}


	public void setPerCardAmount(int perCardAmount) {
		this.perCardAmount = perCardAmount;
	}


	public int getMaxRounds() {
		return maxRounds;
	}


	public void setMaxRounds(int maxRounds) {
		this.maxRounds = maxRounds;
	}
	
	
	
}
