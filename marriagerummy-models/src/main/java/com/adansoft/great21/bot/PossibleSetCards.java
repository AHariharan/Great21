package com.adansoft.great21.bot;

import java.util.ArrayList;

import com.adansoft.great21.models.Card;

public class PossibleSetCards {

	public static final String SETTYPE_SEQUENCE = "Sequence";
	public static final String SETTYPE_TRIPLET = "Triplet";
	
	
	private int noofCards;
	private String setType;
	private ArrayList<Card> cardList;
	private int distanceofRemaingCards;
	private String flower;
	private String origin;
	private int howmanySeq;
	private int howmanytrip;

	public PossibleSetCards() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PossibleSetCards(int noofCards, String setType,
			ArrayList<Card> cardList, int distanceofRemaingCards,
			String flower, String origin) {
		super();
		this.noofCards = noofCards;
		this.setType = setType;
		this.cardList = cardList;
		this.distanceofRemaingCards = distanceofRemaingCards;
		this.flower = flower;
		this.origin = origin;
	}
	public int getNoofCards() {
		return noofCards;
	}
	public void setNoofCards(int noofCards) {
		this.noofCards = noofCards;
	}
	public String getSetType() {
		return setType;
	}
	public void setSetType(String setType) {
		this.setType = setType;
	}
	public ArrayList<Card> getCardList() {
		return cardList;
	}
	public void setCardList(ArrayList<Card> cardList) {
		this.cardList = cardList;
	}
	public int getDistanceofRemaingCards() {
		return distanceofRemaingCards;
	}
	public void setDistanceofRemaingCards(int distanceofRemaingCards) {
		this.distanceofRemaingCards = distanceofRemaingCards;
	}
	public String getFlower() {
		return flower;
	}
	public void setFlower(String flower) {
		this.flower = flower;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public int getHowmanySeq() {
		return howmanySeq;
	}
	public void setHowmanySeq(int howmanySeq) {
		this.howmanySeq = howmanySeq;
	}
	public int getHowmanytrip() {
		return howmanytrip;
	}
	public void setHowmanytrip(int howmanytrip) {
		this.howmanytrip = howmanytrip;
	}
	
	
	
}
