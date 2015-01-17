package com.adansoft.great21.models;

import java.util.ArrayList;



public interface GameStrategy {
	
	public static final String PICKUP_FROM_UNASSIGNED = "UNASSIGNED";
	public static final String PICKUP_FROM_OPENCARD = "OPENCARD";

	public Card discardCard(ArrayList<Card> cardlist);
	
	public String pickupCard(Card card);
}
