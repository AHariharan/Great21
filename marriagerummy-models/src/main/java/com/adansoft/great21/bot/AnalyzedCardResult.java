package com.adansoft.great21.bot;

public class AnalyzedCardResult {

	private boolean isSameFlower;
	private boolean isSameValue;
	private int noofspades;
	private int noofhearts;
	private int noofdiamonds;
	private int noofclubs;
	private boolean sequencebeformed;
	private boolean tripletbeformed;
	private int howmanysequencescanbemade;
	private int howmanytripletscanbemade;
	private int distancebetweenCards;

	public AnalyzedCardResult() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AnalyzedCardResult(boolean isSameFlower, boolean isSameValue,
			int noofspades, int noofhearts, int noofdiamonds, int noofclubs,
			boolean sequencebeformed, boolean tripletbeformed,
			int howmanysequencescanbemade, int howmanytripletscanbemade,
			int distancebetweenCards) {
		super();
		this.isSameFlower = isSameFlower;
		this.isSameValue = isSameValue;
		this.noofspades = noofspades;
		this.noofhearts = noofhearts;
		this.noofdiamonds = noofdiamonds;
		this.noofclubs = noofclubs;
		this.sequencebeformed = sequencebeformed;
		this.tripletbeformed = tripletbeformed;
		this.howmanysequencescanbemade = howmanysequencescanbemade;
		this.howmanytripletscanbemade = howmanytripletscanbemade;
		this.distancebetweenCards = distancebetweenCards;
	}

	public boolean isSameFlower() {
		return isSameFlower;
	}

	public void setSameFlower(boolean isSameFlower) {
		this.isSameFlower = isSameFlower;
	}

	public boolean isSameValue() {
		return isSameValue;
	}

	public void setSameValue(boolean isSameValue) {
		this.isSameValue = isSameValue;
	}

	public int getNoofspades() {
		return noofspades;
	}

	public void setNoofspades(int noofspades) {
		this.noofspades = noofspades;
	}

	public int getNoofhearts() {
		return noofhearts;
	}

	public void setNoofhearts(int noofhearts) {
		this.noofhearts = noofhearts;
	}

	public int getNoofdiamonds() {
		return noofdiamonds;
	}

	public void setNoofdiamonds(int noofdiamonds) {
		this.noofdiamonds = noofdiamonds;
	}

	public int getNoofclubs() {
		return noofclubs;
	}

	public void setNoofclubs(int noofclubs) {
		this.noofclubs = noofclubs;
	}

	public boolean isSequencebeformed() {
		return sequencebeformed;
	}

	public void setSequencebeformed(boolean sequencebeformed) {
		this.sequencebeformed = sequencebeformed;
	}

	public boolean isTripletbeformed() {
		return tripletbeformed;
	}

	public void setTripletbeformed(boolean tripletbeformed) {
		this.tripletbeformed = tripletbeformed;
	}

	public int getHowmanysequencescanbemade() {
		return howmanysequencescanbemade;
	}

	public void setHowmanysequencescanbemade(int howmanysequencescanbemade) {
		this.howmanysequencescanbemade = howmanysequencescanbemade;
	}

	public int getHowmanytripletscanbemade() {
		return howmanytripletscanbemade;
	}

	public void setHowmanytripletscanbemade(int howmanytripletscanbemade) {
		this.howmanytripletscanbemade = howmanytripletscanbemade;
	}

	public int getDistancebetweenCards() {
		return distancebetweenCards;
	}

	public void setDistancebetweenCards(int distancebetweenCards) {
		this.distancebetweenCards = distancebetweenCards;
	}

}
