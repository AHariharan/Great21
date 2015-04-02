package com.adansoft.great21.models;

import java.io.Serializable;
import java.util.HashMap;

public class GameContentHolder implements Serializable{

	private static final long serialVersionUID = -4352177438713072359L;

	private HashMap<String,HashMap<String,Integer>> playerPointsMap; //format gameround,<PlayerName,points>
	private HashMap<String,HashMap<String,Float>> playerCashMap;
	private String gameInstanceID;
	private String gameType;
	private String gameMode;
	
	
	
	public GameContentHolder() {
		super();
		playerPointsMap = new HashMap<String, HashMap<String,Integer>>();
		playerCashMap = new HashMap<String, HashMap<String,Float>>();
	}



	public GameContentHolder(String gameInstanceID,String gameType,String gameMode)
	{
		this.gameInstanceID = gameInstanceID;
		this.gameType = gameType;
		this.gameMode = gameMode;
		playerPointsMap = new HashMap<String, HashMap<String,Integer>>();
		playerCashMap = new HashMap<String, HashMap<String,Float>>();
	}



	public HashMap<String, HashMap<String, Integer>> getPlayerPointsMap() {
		return playerPointsMap;
	}



	public void setPlayerPointsMap(
			HashMap<String, HashMap<String, Integer>> playerPointsMap) {
		this.playerPointsMap = playerPointsMap;
	}



	public HashMap<String, HashMap<String, Float>> getPlayerCashMap() {
		return playerCashMap;
	}



	public void setPlayerCashMap(
			HashMap<String, HashMap<String, Float>> playerCashMap) {
		this.playerCashMap = playerCashMap;
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



	public String getGameMode() {
		return gameMode;
	}



	public void setGameMode(String gameMode) {
		this.gameMode = gameMode;
	}
	
	
	

	
}
