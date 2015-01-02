package com.adansoft.great21.restschemas;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AddPlayerResponse implements Serializable {

	private static final long serialVersionUID = 1812597161673272786L;
	
	private String playerType;
	private String nickname;
	private String gameInstanceID;
	private String lobbyName;
	private String gameType;
	private int playerPosition;
	private String gameName;
	private String owner;
	private int playersize;
	private int maxplayers;
	private boolean gameMoneyBased;
	private boolean gamePointsBased;
	private int moneyPerCard;
	private int maxPoints;
	
	
	public AddPlayerResponse() {
		super();
	}

	

	public AddPlayerResponse(String playerType, String nickname,
			String gameInstanceID, String lobbyName, String gameType,
			int playerPosition, String gameName, String owner, int playersize,
			int maxplayers, boolean gameMoneyBased, boolean gamePointsBased,
			int moneyPerCard, int maxPoints) {
		super();
		this.playerType = playerType;
		this.nickname = nickname;
		this.gameInstanceID = gameInstanceID;
		this.lobbyName = lobbyName;
		this.gameType = gameType;
		this.playerPosition = playerPosition;
		this.gameName = gameName;
		this.owner = owner;
		this.playersize = playersize;
		this.maxplayers = maxplayers;
		this.gameMoneyBased = gameMoneyBased;
		this.gamePointsBased = gamePointsBased;
		this.moneyPerCard = moneyPerCard;
		this.maxPoints = maxPoints;
	}



	public String getPlayerType() {
		return playerType;
	}



	public void setPlayerType(String playerType) {
		this.playerType = playerType;
	}



	public String getNickname() {
		return nickname;
	}



	public void setNickname(String nickname) {
		this.nickname = nickname;
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



	public int getPlayerPosition() {
		return playerPosition;
	}



	public void setPlayerPosition(int playerPosition) {
		this.playerPosition = playerPosition;
	}



	public String getGameName() {
		return gameName;
	}



	public void setGameName(String gameName) {
		this.gameName = gameName;
	}



	public String getOwner() {
		return owner;
	}



	public void setOwner(String owner) {
		this.owner = owner;
	}



	public int getPlayersize() {
		return playersize;
	}



	public void setPlayersize(int playersize) {
		this.playersize = playersize;
	}



	public int getMaxplayers() {
		return maxplayers;
	}



	public void setMaxplayers(int maxplayers) {
		this.maxplayers = maxplayers;
	}



	public boolean isGameMoneyBased() {
		return gameMoneyBased;
	}



	public void setGameMoneyBased(boolean gameMoneyBased) {
		this.gameMoneyBased = gameMoneyBased;
	}



	public boolean isGamePointsBased() {
		return gamePointsBased;
	}



	public void setGamePointsBased(boolean gamePointsBased) {
		this.gamePointsBased = gamePointsBased;
	}



	public int getMoneyPerCard() {
		return moneyPerCard;
	}



	public void setMoneyPerCard(int moneyPerCard) {
		this.moneyPerCard = moneyPerCard;
	}



	public int getMaxPoints() {
		return maxPoints;
	}



	public void setMaxPoints(int maxPoints) {
		this.maxPoints = maxPoints;
	}



	
	

}
