package com.adansoft.great21.models;

public class JokerCard implements Card {

	private static final long serialVersionUID = -5128288224806002309L;
	private String flower = Card.FLOWER_WILD;
	private String displayValue;
	private int countValue;
	private int deckID;
	private String status;
	private String cardInstanceId;
	private int instrinsicValue;

	public JokerCard() {

	}

	public JokerCard(String displayValue, int countValue, int deckID,
			String status) {
		super();
		this.displayValue = displayValue;
		this.countValue = countValue;
		this.deckID = deckID;
		this.status = status;
		this.cardInstanceId = deckID + " - " + flower + " - " + displayValue+","+countValue;
		assignInstrinsicValue();
	}

	private void assignInstrinsicValue() {
		if(displayValue.equals("Joker"))
			this.instrinsicValue = 0;
		if (displayValue.equals(new String("J")))
			this.instrinsicValue = 11;
		else if (displayValue.equals(new String("Q")))
			this.instrinsicValue = 12;
		else if (displayValue.equals(new String("K")))
			this.instrinsicValue = 13;
		else if (displayValue.equals(new String("A")))
			this.instrinsicValue = 1;
		else
			this.instrinsicValue = countValue;
	}

	public String getFlower() {
		return flower;
	}

	public String getDisplayValue() {
		return displayValue;
	}

	public String getStatus() {
		return status;
	}

	public int getCountValue() {
		return countValue;
	}

	public int getDecKID() {
		return deckID;
	}

	public String getCardInstanceID() {
		return cardInstanceId;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getInstrinsicValue() {

		return this.instrinsicValue;
	}

	public int hashCode() {
		return getInstrinsicValue();
	}

	public int compareTo(Card inputcard) {
		final int BEFORE = -1;
		final int EQUAL = 0;
		final int AFTER = 1;

		if (getInstrinsicValue() > inputcard.getInstrinsicValue())
			return AFTER;
		if (getInstrinsicValue() < inputcard.getInstrinsicValue())
			return BEFORE;
		if (getInstrinsicValue() == inputcard.getInstrinsicValue())
			return EQUAL;

		return 0;
	}

	public int compareTo(Object input) {
		final int BEFORE = -1;
		final int EQUAL = 0;
		final int AFTER = 1;

		if (input instanceof Card) {
			Card inputcard = (Card) input;
			if (getInstrinsicValue() > inputcard.getInstrinsicValue())
				return AFTER;
			if (getInstrinsicValue() < inputcard.getInstrinsicValue())
				return BEFORE;
			if (getInstrinsicValue() == inputcard.getInstrinsicValue())
				return EQUAL;
		}
		return 0;
	}

	public String getCardInstanceId() {
		return cardInstanceId;
	}

	public void setCardInstanceId(String cardInstanceId) {
		this.cardInstanceId = cardInstanceId;
	}

	public String getInstanceID() {
		return this.getCardInstanceId();
	}

}
