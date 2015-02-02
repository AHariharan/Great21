package com.cardutility.test;

import com.adansoft.great21.models.Card;
import com.adansoft.great21.models.ClubCard;
import com.adansoft.great21.models.DiamondCard;
import com.adansoft.great21.models.HeartCard;
import com.adansoft.great21.models.SpadeCard;
import com.adansoft.great21.ulitity.CardUtility;

public class CheckJokerInterpreter {
	public static void main(String[] args) {
		// ARG 1 is display value : A,2,3.....10,J,Q,K
		// ARG 2 is count value  :  1,2,3.....10,10,10,10
		// ARG3 is deck id so ignore it doesn't matter
		// ARG4 is status doesn't matter
		
		SpadeCard card1 = new SpadeCard("2",2, 4, Card.STATUS_ASSIGNED);
		DiamondCard card2 = new DiamondCard("2", 2, 0, Card.STATUS_ASSIGNED);
		HeartCard card3 = new HeartCard("2", 2, 0, Card.STATUS_ASSIGNED);
		ClubCard card4 = new ClubCard("2", 2, 2, Card.STATUS_ASSIGNED);
		//SpadeCard card5 = new SpadeCard("4", 4, 2, Card.STATUS_ASSIGNED);
		
		DiamondCard jokerCard = new DiamondCard("J", 10, 2, Card.STATUS_JOKER);
		
		
		Card[] cardlist = {card1,card2,card3,card4};
		
		//Card[] cardlist = {card1,card2,card3,card4,card5};
		
		CardUtility.interpretJokerandValidate(cardlist,jokerCard);
		

	}
}
