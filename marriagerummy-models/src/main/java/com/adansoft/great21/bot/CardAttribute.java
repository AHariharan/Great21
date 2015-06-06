package com.adansoft.great21.bot;

import com.adansoft.great21.models.Card;

public class CardAttribute {
	
	private double seqProximityScore;
	private double tripProximityScore;
	private boolean isDuplicate;
	private Card card;
	
	public CardAttribute() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CardAttribute(double seqProximityScore, double tripProximityScore,
			boolean isDuplicate, Card card) {
		super();
		this.seqProximityScore = seqProximityScore;
		this.tripProximityScore = tripProximityScore;
		this.isDuplicate = isDuplicate;
		this.card = card;
	}

	public double getSeqProximityScore() {
		return seqProximityScore;
	}

	public void setSeqProximityScore(double seqProximityScore) {
		this.seqProximityScore = seqProximityScore;
	}

	public double getTripProximityScore() {
		return tripProximityScore;
	}

	public void setTripProximityScore(double tripProximityScore) {
		this.tripProximityScore = tripProximityScore;
	}

	public boolean isDuplicate() {
		return isDuplicate;
	}

	public void setDuplicate(boolean isDuplicate) {
		this.isDuplicate = isDuplicate;
	}

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}
	
	

}
