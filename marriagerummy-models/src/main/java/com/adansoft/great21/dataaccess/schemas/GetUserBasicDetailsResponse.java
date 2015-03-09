package com.adansoft.great21.dataaccess.schemas;

import java.io.Serializable;

public class GetUserBasicDetailsResponse implements Serializable{

	private static final long serialVersionUID = 1537635666380479765L;
   
	private String nickname;
	private String emailaddress;
	private String rating;
	private double cash;
	private int winpercent;
	private int nooffriends;
	
	public GetUserBasicDetailsResponse() {
		super();
	}

	public GetUserBasicDetailsResponse(String nickname, String emailaddress,
			String rating, double cash, int winpercent, int nooffriends) {
		super();
		this.nickname = nickname;
		this.emailaddress = emailaddress;
		this.rating = rating;
		this.cash = cash;
		this.winpercent = winpercent;
		this.nooffriends = nooffriends;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getEmailaddress() {
		return emailaddress;
	}

	public void setEmailaddress(String emailaddress) {
		this.emailaddress = emailaddress;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public double getCash() {
		return cash;
	}

	public void setCash(double cash) {
		this.cash = cash;
	}

	public int getWinpercent() {
		return winpercent;
	}

	public void setWinpercent(int winpercent) {
		this.winpercent = winpercent;
	}

	public int getNooffriends() {
		return nooffriends;
	}

	public void setNooffriends(int nooffriends) {
		this.nooffriends = nooffriends;
	}

	
	
	

}
