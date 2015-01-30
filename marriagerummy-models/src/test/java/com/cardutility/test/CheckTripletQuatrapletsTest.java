package com.cardutility.test;

import com.adansoft.great21.models.Card;
import com.adansoft.great21.models.ClubCard;
import com.adansoft.great21.models.DiamondCard;
import com.adansoft.great21.models.HeartCard;
import com.adansoft.great21.models.SpadeCard;
import com.adansoft.great21.ulitity.CardUtility;

public class CheckTripletQuatrapletsTest {
	
	public static void main(String[] args) {
		SpadeCard card1 = new SpadeCard("A", 1, 2, Card.STATUS_ASSIGNED);
		DiamondCard card2 = new DiamondCard("A", 1, 2, Card.STATUS_ASSIGNED);
		HeartCard card3 = new HeartCard("A", 1, 2, Card.STATUS_ASSIGNED);
		ClubCard card4 = new ClubCard("A", 1, 2, Card.STATUS_ASSIGNED);
		Card[] cardlist = {card1,card2,card3,card4};
		System.out.println("isTriple/Quatraple : " + CardUtility.checkTripletorQuadraplets(cardlist));
	}

}
