package com.cardutility.test;

import com.adansoft.great21.models.Card;
import com.adansoft.great21.models.ClubCard;
import com.adansoft.great21.models.DiamondCard;
import com.adansoft.great21.models.HeartCard;
import com.adansoft.great21.models.JokerCard;
import com.adansoft.great21.models.SpadeCard;
import com.adansoft.great21.ulitity.CardUtility;

public class CheckJokerInterpreter {
	public static void main(String[] args) {
		// ARG 1 is display value : A,2,3.....10,J,Q,K
		// ARG 2 is count value  :  1,2,3.....10,10,10,10
		// ARG3 is deck id so ignore it doesn't matter
		// ARG4 is status doesn't matter
		
		DiamondCard card1 = new DiamondCard("2",2, 0, Card.STATUS_ASSIGNED);
		DiamondCard card2 = new DiamondCard("3", 3, 0, Card.STATUS_ASSIGNED);
		JokerCard card3 = new JokerCard("Joker", 0, 0, Card.STATUS_ASSIGNED);
		JokerCard card4 = new JokerCard("Joker", 0, 0, Card.STATUS_ASSIGNED);
		DiamondCard card5 = new DiamondCard("5", 5, 2, Card.STATUS_ASSIGNED);
		
		DiamondCard jokerCard = new DiamondCard("4", 4, 2, Card.STATUS_JOKER);
		
		
		//Card[] cardlist = {card1,card2,card3,card4};
		
		Card[] cardlist = {card1,card2,card3,card4,card5};
		
		System.out.println("Status :  " + CardUtility.interpretJokerandValidate(cardlist,jokerCard));
		

	}
}
