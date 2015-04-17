package com.adansoft.great21.restschemas;

import java.io.Serializable;

public class GetWinnerDetailsResponse implements Serializable {

	private static final long serialVersionUID = -5049373807084293324L;
	
	private String nickname;
	private String gameInstanceID;
	private int prizeMoney;
	
	public GetWinnerDetailsResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GetWinnerDetailsResponse(String nickname, String gameInstanceID,
			int prizeMoney) {
		super();
		this.nickname = nickname;
		this.gameInstanceID = gameInstanceID;
		this.prizeMoney = prizeMoney;
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

	public int getPrizeMoney() {
		return prizeMoney;
	}

	public void setPrizeMoney(int prizeMoney) {
		this.prizeMoney = prizeMoney;
	}
	
	

}
