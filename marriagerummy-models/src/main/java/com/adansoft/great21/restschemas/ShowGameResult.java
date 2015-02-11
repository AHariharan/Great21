package com.adansoft.great21.restschemas;

import java.io.Serializable;

public class ShowGameResult implements Serializable{

	private static final long serialVersionUID = -9197824238994099115L;

	private String gameMode;
	private float Money;
	private int points;
	
	public ShowGameResult() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ShowGameResult(String gameMode, float money, int points) {
		super();
		this.gameMode = gameMode;
		Money = money;
		this.points = points;
	}

	public String getGameMode() {
		return gameMode;
	}

	public void setGameMode(String gameMode) {
		this.gameMode = gameMode;
	}

	public float getMoney() {
		return Money;
	}

	public void setMoney(float money) {
		Money = money;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}
	
	
	
	
	
}
