package com.adansoft.great21.dataaccess.entities;

// Generated May 13, 2015 3:59:03 PM by Hibernate Tools 3.4.0.CR1

/**
 * RummyStats generated by hbm2java
 */
public class RummyStats implements java.io.Serializable {

	private long userId;
	private double cash;
	private String rating;
	private Integer winPercent;

	public RummyStats() {
	}

	public RummyStats(long userId, double cash) {
		this.userId = userId;
		this.cash = cash;
	}

	public RummyStats(long userId, double cash, String rating,
			Integer winPercent) {
		this.userId = userId;
		this.cash = cash;
		this.rating = rating;
		this.winPercent = winPercent;
	}

	public long getUserId() {
		return this.userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public double getCash() {
		return this.cash;
	}

	public void setCash(double cash) {
		this.cash = cash;
	}

	public String getRating() {
		return this.rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public Integer getWinPercent() {
		return this.winPercent;
	}

	public void setWinPercent(Integer winPercent) {
		this.winPercent = winPercent;
	}

}
