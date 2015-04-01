package com.adansoft.great21.restschemas;

import java.io.Serializable;

public class GetInfoBlockResponse implements Serializable{

	private static final long serialVersionUID = 7281465102541896031L;
	
	private String nickName;
	private int currentRound;
	private int currentPoints;
	private String currentStatus;
	private double currentCash;
	
	public GetInfoBlockResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	

	public GetInfoBlockResponse(String nickName, int currentRound,
			int currentPoints, String currentStatus, double currentCash) {
		super();
		this.nickName = nickName;
		this.currentRound = currentRound;
		this.currentPoints = currentPoints;
		this.currentStatus = currentStatus;
		this.currentCash = currentCash;
	}




	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public int getCurrentRound() {
		return currentRound;
	}

	public void setCurrentRound(int currentRound) {
		this.currentRound = currentRound;
	}

	public int getCurrentPoints() {
		return currentPoints;
	}

	public void setCurrentPoints(int currentPoints) {
		this.currentPoints = currentPoints;
	}


	public String getCurrentStatus() {
		return currentStatus;
	}


	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}




	public double getCurrentCash() {
		return currentCash;
	}




	public void setCurrentCash(double currentCash) {
		this.currentCash = currentCash;
	}
	
	
	

}
