package com.cardutility.test;

import com.adansoft.great21.models.Card;
import com.adansoft.great21.models.DiamondCard;
import com.adansoft.great21.models.SpadeCard;
import com.adansoft.great21.ulitity.CardUtility;

public class CheckJokerInterpreter {
	public static void main(String[] args) {
		SpadeCard card1 = new SpadeCard("4", 4, 4, Card.STATUS_ASSIGNED);
		SpadeCard card2 = new SpadeCard("5", 5, 0, Card.STATUS_ASSIGNED);
		SpadeCard card3 = new SpadeCard("3", 3, 2, Card.STATUS_ASSIGNED);
		SpadeCard card4 = new SpadeCard("7", 7, 2, Card.STATUS_ASSIGNED);
		
		DiamondCard jokerCard = new DiamondCard("3", 3, 2, Card.STATUS_JOKER);
		
		Card[] cardlist = {card1,card2,card3,card4};
		CardUtility.interpretJoker(cardlist,jokerCard);
		

	}
}
