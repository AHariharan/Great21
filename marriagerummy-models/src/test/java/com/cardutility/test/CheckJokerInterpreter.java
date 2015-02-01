package com.cardutility.test;

import com.adansoft.great21.models.Card;
import com.adansoft.great21.models.DiamondCard;
import com.adansoft.great21.models.SpadeCard;
import com.adansoft.great21.ulitity.CardUtility;

public class CheckJokerInterpreter {
	public static void main(String[] args) {
		SpadeCard card1 = new SpadeCard("A", 1, 4, Card.STATUS_ASSIGNED);
		SpadeCard card2 = new SpadeCard("5", 5, 0, Card.STATUS_ASSIGNED);
		SpadeCard card3 = new SpadeCard("6", 6, 0, Card.STATUS_ASSIGNED);
		SpadeCard card4 = new SpadeCard("5", 5, 2, Card.STATUS_ASSIGNED);
		SpadeCard card5 = new SpadeCard("10", 10, 2, Card.STATUS_ASSIGNED);
		
		DiamondCard jokerCard = new DiamondCard("5", 5, 2, Card.STATUS_JOKER);
		Card[] cardlist = {card1,card2,card3,card4};
		//Card[] cardlist = {card1,card2,card3,card4,card5};
		CardUtility.interpretJokerandValidate(cardlist,jokerCard);
		

	}
}
