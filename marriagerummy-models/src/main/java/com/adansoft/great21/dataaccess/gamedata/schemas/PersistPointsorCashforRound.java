package com.adansoft.great21.dataaccess.gamedata.schemas;

import java.io.Serializable;

public class PersistPointsorCashforRound implements Serializable {

	private static final long serialVersionUID = 8143599615409348763L;
	private String nickname;
	private int points;
	private double cash;
	private boolean wonGame;
	
	public PersistPointsorCashforRound() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PersistPointsorCashforRound(String nickname, int points, double cash,
			boolean wonGame) {
		super();
		this.nickname = nickname;
		this.points = points;
		this.cash = cash;
		this.wonGame = wonGame;
	}

	
	

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
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
