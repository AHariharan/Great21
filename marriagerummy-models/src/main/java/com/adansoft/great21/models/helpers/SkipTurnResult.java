package com.adansoft.great21.models.helpers;

public class SkipTurnResult {
	
	private String playerStatus;
	private boolean isGameOver;
	
	
	
	public SkipTurnResult() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SkipTurnResult(String playerStatus, boolean isGameOver) {
		super();
		this.playerStatus = playerStatus;
		this.isGameOver = isGameOver;
	}

	public String getPlayerStatus() {
		return playerStatus;
	}

	public void setPlayerStatus(String playerStatus) {
		this.playerStatus = playerStatus;
	}

	public boolean isGameOver() {
		return isGameOver;
	}

	public void setGameOver(boolean isGameOver) {
		this.isGameOver = isGameOver;
	}
	
	
	

}
