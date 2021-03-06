package com.adansoft.great21.dataaccess.entities;

// Generated May 13, 2015 3:59:03 PM by Hibernate Tools 3.4.0.CR1

import java.util.Date;

/**
 * RummyTransactions generated by hbm2java
 */
public class RummyTransactions implements java.io.Serializable {

	private Long transactionIdn;
	private String gameInstanceId;
	private Long userid;
	private Double cash;
	private Date createdDate;
	private String transType;

	public RummyTransactions() {
	}

	public RummyTransactions(String gameInstanceId, Long userid, Double cash,
			Date createdDate, String transType) {
		this.gameInstanceId = gameInstanceId;
		this.userid = userid;
		this.cash = cash;
		this.createdDate = createdDate;
		this.transType = transType;
	}

	public Long getTransactionIdn() {
		return this.transactionIdn;
	}

	public void setTransactionIdn(Long transactionIdn) {
		this.transactionIdn = transactionIdn;
	}

	public String getGameInstanceId() {
		return this.gameInstanceId;
	}

	public void setGameInstanceId(String gameInstanceId) {
		this.gameInstanceId = gameInstanceId;
	}

	public Long getUserid() {
		return this.userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public Double getCash() {
		return this.cash;
	}

	public void setCash(Double cash) {
		this.cash = cash;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getTransType() {
		return this.transType;
	}

	public void setTransType(String transType) {
		this.transType = transType;
	}

}
