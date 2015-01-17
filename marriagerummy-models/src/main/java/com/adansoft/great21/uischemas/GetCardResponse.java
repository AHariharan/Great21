package com.adansoft.great21.uischemas;

import java.io.Serializable;
import java.util.ArrayList;

import com.adansoft.great21.models.Card;

public class GetCardResponse implements Serializable {

	private static final long serialVersionUID = 8525232326234071394L;
	private String nickname;
	private ArrayList<Card> cardlist;
	
	public GetCardResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GetCardResponse(String nickname, ArrayList<Card> cardlist) {
		super();
		this.nickname = nickname;
		this.cardlist = cardlist;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public ArrayList<Card> getCardlist() {
		return cardlist;
	}

	public void setCardlist(ArrayList<Card> cardlist) {
		this.cardlist = cardlist;
	}
	
	

	
	
}
