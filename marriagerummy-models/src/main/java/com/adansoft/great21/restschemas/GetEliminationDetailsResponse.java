package com.adansoft.great21.restschemas;

import java.io.Serializable;

public class GetEliminationDetailsResponse  implements Serializable{

	private static final long serialVersionUID = -2452836093192912107L;
	
	private int gameThreshold;
	private int playerPoints;
	private double money;
	private String nickName;
	private String gameInstanceID;
	
	public GetEliminationDetailsResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GetEliminationDetailsResponse(int gameThreshold, int playerPoints,
			double money, String nickName, String gameInstanceID) {
		super();
		this.gameThreshold = gameThreshold;
		this.playerPoints = playerPoints;
		this.money = money;
		this.nickName = nickName;
		this.gameInstanceID = gameInstanceID;
	}

	public int getGameThreshold() {
		return gameThreshold;
	}

	public void setGameThreshold(int gameThreshold) {
		this.gameThreshold = gameThreshold;
	}

	public int getPlayerPoints() {
		return playerPoints;
	}

	public void setPlayerPoints(int playerPoints) {
		this.playerPoints = playerPoints;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getGameInstanceID() {
		return gameInstanceID;
	}

	public void setGameInstanceID(String gameInstanceID) {
		this.gameInstanceID = gameInstanceID;
	}
	
	
	

}
