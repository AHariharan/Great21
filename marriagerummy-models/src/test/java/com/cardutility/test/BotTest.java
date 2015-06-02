package com.cardutility.test;

import java.util.ArrayList;

import com.adansoft.great21.games.GameListConstants;
import com.adansoft.great21.models.Card;
import com.adansoft.great21.models.ClubCard;
import com.adansoft.great21.models.DiamondCard;
import com.adansoft.great21.models.EasyBotStrategy;
import com.adansoft.great21.models.HeartCard;
import com.adansoft.great21.models.SpadeCard;

public class BotTest {

	public static void main(String[] args) {
		
		ArrayList<Card> cardsinHand = new ArrayList<Card>();
		SpadeCard card1 = new SpadeCard("A", 1, 2, Card.STATUS_ASSIGNED);
		SpadeCard card2 = new SpadeCard("2", 2, 2, Card.STATUS_ASSIGNED);
		SpadeCard card3 = new SpadeCard("3", 3, 2, Card.STATUS_ASSIGNED);
		SpadeCard card4 = new SpadeCard("4", 4, 2, Card.STATUS_ASSIGNED);
		SpadeCard card5 = new SpadeCard("5", 5, 2, Card.STATUS_ASSIGNED);
		DiamondCard card6 = new DiamondCard("A", 6, 2, Card.STATUS_ASSIGNED);
		HeartCard card7 = new HeartCard("A", 7, 2, Card.STATUS_ASSIGNED);
		cardsinHand.add(card1);cardsinHand.add(card2);cardsinHand.add(card3);cardsinHand.add(card4);
		cardsinHand.add(card5);cardsinHand.add(card6);cardsinHand.add(card7);
		
		EasyBotStrategy strategy = new EasyBotStrategy(cardsinHand, false, null, GameListConstants.GAMELIST_SEVENCARD_CLOSED_TYPE);
		
		

	}

}
