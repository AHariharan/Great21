package com.adansoft.great21.dataaccess.gamedata.schemas;

import java.io.Serializable;

public class UpdateRummyStat implements Serializable {

	private static final long serialVersionUID = -5856325086186332575L;
	private long userId;
	private double cash;
	private String rating;
	private Integer winPercent;
	
	public UpdateRummyStat() {
	}

	public UpdateRummyStat(long userId, double cash, String rating,
			Integer winPercent) {
		super();
		this.userId = userId;
		this.cash = cash;
		this.rating = rating;
		this.winPercent = winPercent;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public double getCash() {
		return cash;
	}

	public void setCash(double cash) {
		this.cash = cash;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public Integer getWinPercent() {
		return winPercent;
	}

	public void setWinPercent(Integer winPercent) {
		this.winPercent = winPercent;
	}

	
}
