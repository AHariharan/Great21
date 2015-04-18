package com.adansoft.great21.dataaccess.gamedata.schemas;

import java.io.Serializable;

public class PersistPointsorCashforRound implements Serializable {

	private static final long serialVersionUID = 8143599615409348763L;
	private String gameInstanceID;
	private String gameRoundID;
	private long userID;
	private int points;
	private double cash;
	private boolean wonGame;
	
	public PersistPointsorCashforRound() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PersistPointsorCashforRound(String gameInstanceID,
			String gameRoundID, long userID, int points, double cash,
			boolean wonGame) {
		super();
		this.gameInstanceID = gameInstanceID;
		this.gameRoundID = gameRoundID;
		this.userID = userID;
		this.points = points;
		this.cash = cash;
		this.wonGame = wonGame;
	}

	public String getGameInstanceID() {
		return gameInstanceID;
	}

	public void setGameInstanceID(String gameInstanceID) {
		this.gameInstanceID = gameInstanceID;
	}

	public String getGameRoundID() {
		return gameRoundID;
	}

	public void setGameRoundID(String gameRoundID) {
		this.gameRoundID = gameRoundID;
	}

	public long getUserID() {
		return userID;
	}

	public void setUserID(long userID) {
		this.userID = userID;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public double getCash() {
		return cash;
	}

	public void setCash(double cash) {
		this.cash = cash;
	}

	public boolean isWonGame() {
		return wonGame;
	}

	public void setWonGame(boolean wonGame) {
		this.wonGame = wonGame;
	}
	
	

}
