package com.adansoft.great21.models;

import java.util.ArrayList;
import java.util.HashMap;

public class GameRound {

	private String parentGameId;
	private boolean isPointsEnabled;
	private ArrayList<Player> playerlist;
    private Player currentPlayerTurn;
    private HashMap<Player,Integer> pointsMap;
    
    public GameRound(String gameid,boolean isPointsEnabled)
    {
    	this.parentGameId = gameid;
    	this.isPointsEnabled = isPointsEnabled;
    }

	public String getParentGameId() {
		return parentGameId;
	}

	public void setParentGameId(String parentGameId) {
		this.parentGameId = parentGameId;
	}

	public boolean isPointsEnabled() {
		return isPointsEnabled;
	}

	public void setPointsEnabled(boolean isPointsEnabled) {
		this.isPointsEnabled = isPointsEnabled;
	}

	public ArrayList<Player> getPlayerlist() {
		return playerlist;
	}

	public void setPlayerlist(ArrayList<Player> playerlist) {
		this.playerlist = playerlist;
	}

	public Player getCurrentPlayerTurn() {
		return currentPlayerTurn;
	}

	public void setCurrentPlayerTurn(Player currentPlayerTurn) {
		this.currentPlayerTurn = currentPlayerTurn;
	}

	public HashMap<Player, Integer> getPointsMap() {
		return pointsMap;
	}

	public void setPointsMap(HashMap<Player, Integer> pointsMap) {
		this.pointsMap = pointsMap;
	}
    
    
}
