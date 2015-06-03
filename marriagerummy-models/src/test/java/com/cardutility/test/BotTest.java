package com.cardutility.test;

import java.util.ArrayList;
import java.util.Calendar;

import com.adansoft.great21.bot.EasyBotStrategy;
import com.adansoft.great21.games.GameListConstants;
import com.adansoft.great21.models.Card;
import com.adansoft.great21.models.ClubCard;
import com.adansoft.great21.models.DiamondCard;
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
		DiamondCard card6 = new DiamondCard("A", 1, 2, Card.STATUS_ASSIGNED);
		HeartCard card7 = new HeartCard("A", 1, 2, Card.STATUS_ASSIGNED);
		DiamondCard card8 = new DiamondCard("J",11, 2, Card.STATUS_ASSIGNED);
		DiamondCard card9 = new DiamondCard("Q", 12, 2, Card.STATUS_ASSIGNED);
		DiamondCard card10 = new DiamondCard("K", 13, 2, Card.STATUS_ASSIGNED);
		ClubCard card11 = new ClubCard("5", 5, 2, Card.STATUS_ASSIGNED);
		ClubCard card12 = new ClubCard("6", 6, 2, Card.STATUS_ASSIGNED);
		ClubCard card13 = new ClubCard("7", 7, 2, Card.STATUS_ASSIGNED);
		
		cardsinHand.add(card1);cardsinHand.add(card2);cardsinHand.add(card3);cardsinHand.add(card4);
		cardsinHand.add(card5);cardsinHand.add(card6);cardsinHand.add(card7);
		cardsinHand.add(card8);cardsinHand.add(card9);cardsinHand.add(card10);cardsinHand.add(card11);
		cardsinHand.add(card12);cardsinHand.add(card13);
		
		System.out.println("DateStarted :- " + Calendar.getInstance().getTime());
		EasyBotStrategy strategy = new EasyBotStrategy(cardsinHand, false, null, GameListConstants.GAMELIST_SEVENCARD_CLOSED_TYPE);
		System.out.println("DateEnded :- " + Calendar.getInstance().getTime());
		

	}

}
