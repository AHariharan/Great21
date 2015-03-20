package com.adansoft.great21.models;

public class Deck {

	private int deckInstanceID;
	private SpadeCard spadeCard[];
	private HeartCard heartCard[];
	private ClubCard clubCard[];
	private DiamondCard diamondCard[];
	private JokerCard jokerCard[];
	private boolean jokerAvailable;
	
	public Deck(int deckid,boolean withJoker) {
		deckInstanceID = deckid;
		jokerAvailable = withJoker;
	}

	public int getDeckInstanceID() {
		return deckInstanceID;
	}

	public void setDeckInstanceID(int deckInstanceID) {
		this.deckInstanceID = deckInstanceID;
	}

	public SpadeCard[] getSpadeCard() {
		return spadeCard;
	}

	public void setSpadeCard(SpadeCard[] spadeCard) {
		this.spadeCard = spadeCard;
	}

	public HeartCard[] getHeartCard() {
		return heartCard;
	}

	public void setHeartCard(HeartCard[] heartCard) {
		this.heartCard = heartCard;
	}

	public ClubCard[] getClubCard() {
		return clubCard;
	}

	public void setClubCard(ClubCard[] clubCard) {
		this.clubCard = clubCard;
	}

	public DiamondCard[] getDiamondCard() {
		return diamondCard;
	}

	public void setDiamondCard(DiamondCard[] diamondCard) {
		this.diamondCard = diamondCard;
	}

	public JokerCard[] getJokerCard() {
		return jokerCard;
	}

	public void setJokerCard(JokerCard[] jokerCard) {
		this.jokerCard = jokerCard;
	}

	public boolean isJokerAvailable() {
		return jokerAvailable;
	}

	public void setJokerAvailable(boolean jokerAvailable) {
		this.jokerAvailable = jokerAvailable;
	}
	
	
	
}
