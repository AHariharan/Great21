package com.adansoft.great21.uischemas;

import java.io.Serializable;

import com.adansoft.great21.models.Card;

public class GetSingleCardResponse implements Serializable{

	private static final long serialVersionUID = -6467110311771011298L;

	public final static String CARDTYPE_JOKER = "Joker";
	public final static String CARDTYPE_OPEN = "Open";
	public final static String CARDTYPE_NEXTFROMDECK = "NextCardFromDeck";
	
	private String cardtype;
	private Card card;
	private boolean avaialble;
	public GetSingleCardResponse() {
		super();
	}
	
	public GetSingleCardResponse(String cardtype, Card card, boolean avaialble) {
		super();
		this.cardtype = cardtype;
		this.card = card;
		this.avaialble = avaialble;
	}
	
	public String getCardtype() {
		return cardtype;
	}
	
	public void setCardtype(String cardtype) {
		this.cardtype = cardtype;
	}
	
	public Card getCard() {
		return card;
	}
	
	public void setCard(Card card) {
		this.card = card;
	}
	
	public boolean isAvaialble() {
		return avaialble;
	}
	
	public void setAvaialble(boolean avaialble) {
		this.avaialble = avaialble;
	}
	
	
	
}
