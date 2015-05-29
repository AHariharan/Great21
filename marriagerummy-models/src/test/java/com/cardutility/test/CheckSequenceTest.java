package com.cardutility.test;

import com.adansoft.great21.models.Card;
import com.adansoft.great21.models.DiamondCard;
import com.adansoft.great21.models.SpadeCard;
import com.adansoft.great21.ulitity.CardUtility;

public class CheckSequenceTest {

	public static void main(String[] args) {
		SpadeCard card1 = new SpadeCard("A", 1, 2, Card.STATUS_ASSIGNED);
		SpadeCard card2 = new SpadeCard("2", 2, 2, Card.STATUS_ASSIGNED);
		SpadeCard card3 = new SpadeCard("5", 5, 2, Card.STATUS_ASSIGNED);
		SpadeCard card4 = new SpadeCard("4", 4, 2, Card.STATUS_ASSIGNED);
		Card[] cardlist = {card1,card2,card3,card4};
		System.out.println("isSequence : " + CardUtility.checkSequence(cardlist));
		

	}

}
