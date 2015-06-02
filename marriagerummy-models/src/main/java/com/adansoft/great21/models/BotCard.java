package com.adansoft.great21.models;

public class BotCard {
	
	public final static String STATUS_SEALED = "Sealed";
	public final static String STATUS_LOOSE = "Loose";
	public final static String STATUS_UNTOUCHED = "Untouched";

	private Card card;
	private String botStatus;
	
	public BotCard() {
		super();
		this.botStatus = STATUS_UNTOUCHED;		
	}

	public BotCard(Card card, String botStatus) {
		super();
		this.card = card;
		this.botStatus = botStatus;
	}

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public String getBotStatus() {
		return botStatus;
	}

	public void setBotStatus(String botStatus) {
		this.botStatus = botStatus;
	}
	
	
	
	
}
