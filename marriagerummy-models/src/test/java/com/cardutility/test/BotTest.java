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
import com.adansoft.great21.ulitity.CardUtility;

public class BotTest {

	public static void main(String[] args) {
		
		ArrayList<Card> cardsinHand = new ArrayList<Card>();
	/*	HeartCard card1 = new HeartCard("6", 6, 2, Card.STATUS_ASSIGNED);
		HeartCard card2 = new HeartCard("7", 7, 2, Card.STATUS_ASSIGNED);
		HeartCard card3 = new HeartCard("8", 8, 2, Card.STATUS_ASSIGNED);
		HeartCard card4 = new HeartCard("9", 9, 2, Card.STATUS_ASSIGNED);
		HeartCard card5 = new HeartCard("10", 10, 2, Card.STATUS_ASSIGNED);
		
		ClubCard card6 = new ClubCard("10", 10, 2, Card.STATUS_ASSIGNED);
		ClubCard card7 = new ClubCard("J", 10, 2, Card.STATUS_ASSIGNED);
		ClubCard card8 = new ClubCard("Q",10, 2, Card.STATUS_ASSIGNED);
		
		ClubCard card9 = new ClubCard("2", 2, 2, Card.STATUS_ASSIGNED);
		ClubCard card10 = new ClubCard("3", 3, 2, Card.STATUS_ASSIGNED);
		//ClubCard card11 = new ClubCard("7",7, 2, Card.STATUS_ASSIGNED);
		
		SpadeCard card12 = new SpadeCard("7", 7, 2, Card.STATUS_ASSIGNED);
		SpadeCard card13 = new SpadeCard("8", 8, 2, Card.STATUS_ASSIGNED);
		SpadeCard card14 = new SpadeCard("9", 9, 2, Card.STATUS_ASSIGNED);*/
		
		
		/*DiamondCard card5 = new DiamondCard("10", 10, 2, Card.STATUS_ASSIGNED);
		ClubCard card6 = new ClubCard("10", 10, 2, Card.STATUS_ASSIGNED);
		SpadeCard card7 = new SpadeCard("8", 8, 2, Card.STATUS_ASSIGNED);
		ClubCard card8 = new ClubCard("Q",10, 2, Card.STATUS_ASSIGNED);
		SpadeCard card9 = new SpadeCard("7", 7, 2, Card.STATUS_ASSIGNED);
		
		HeartCard card10 = new HeartCard("6", 6, 2, Card.STATUS_ASSIGNED);
		SpadeCard card11 = new SpadeCard("9", 9, 2, Card.STATUS_ASSIGNED);
		ClubCard card12 = new ClubCard("J", 10, 2, Card.STATUS_ASSIGNED);
		ClubCard card13 = new ClubCard("2", 2, 2, Card.STATUS_ASSIGNED);*/
		//ClubCard card14 = new ClubCard("8", 8, 2, Card.STATUS_ASSIGNED);
		
	/*	cardsinHand.add(card1);cardsinHand.add(card2);cardsinHand.add(card3);cardsinHand.add(card4);cardsinHand.add(card5);
		cardsinHand.add(card6);cardsinHand.add(card7);cardsinHand.add(card8);cardsinHand.add(card9);cardsinHand.add(card10);
		//cardsinHand.add(card11);
		cardsinHand.add(card12);
		cardsinHand.add(card13);
		cardsinHand.add(card14);*/
		
		Card deckCards[] = CardUtility.shuffleCards(3,false,0);
		for(int i=0;i<13;i++)
		{
			cardsinHand.add(deckCards[i]);
		}
		//System.out.println("DateStarted :- " + Calendar.getInstance().getTime());
		EasyBotStrategy strategy = new EasyBotStrategy(cardsinHand, false, null, GameListConstants.GAMELIST_THIRTEENCARD_CLOSED_TYPE,deckCards,12);
		//System.out.println("DateEnded :- " + Calendar.getInstance().getTime());
		

	}

}
