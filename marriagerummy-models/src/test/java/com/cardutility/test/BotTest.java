package com.cardutility.test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.adansoft.great21.bot.EasyBotStrategy;
import com.adansoft.great21.games.GameListConstants;
import com.adansoft.great21.models.Card;
import com.adansoft.great21.models.ClubCard;
import com.adansoft.great21.models.DiamondCard;
import com.adansoft.great21.models.HeartCard;
import com.adansoft.great21.models.JokerCard;
import com.adansoft.great21.models.SpadeCard;
import com.adansoft.great21.ulitity.CardUtility;

public class BotTest {

	public static void main(String[] args) {
		int testrun = 0;
		oneGameRun();
		//regularrun(testrun, 100);
	}
	
	
	private static void oneGameRun()
	{
		ArrayList<Card> cardsinHand = new ArrayList<Card>();
		createCards("{HEART-A,DIAMOND-4,HEART-K,CLUB-2,CLUB-3,CLUB-4,HEART-5,DIAMOND-6,HEART-6,HEART-10,HEART-Q,WILD-Joker,DIAMOND-6,DIAMOND-8}", cardsinHand);
        Card jokerCard = new DiamondCard("4", 4, 100, Card.STATUS_ASSIGNED);
        Card deckCards[] = CardUtility.shuffleCards(3,true,2);
        Date dateStarted = Calendar.getInstance().getTime();
		EasyBotStrategy strategy = new EasyBotStrategy(cardsinHand, true, jokerCard, GameListConstants.GAMELIST_THIRTEENCARD_CLOSED_TYPE,deckCards,13);
		strategy.playGame();
		System.out.println(" DataStarted : " + dateStarted + " , DateEnded :- " + Calendar.getInstance().getTime());
	}
	
	private static void regularrun(int testrun,int nooftesttoRun)
	{
		while(testrun < nooftesttoRun)
		{
        ArrayList<Card> cardsinHand = new ArrayList<Card>();
        
        /* Pattern Testing */
        /*createCards("{WILD-Joker,WILD-Joker,CLUB-A,DIAMOND-2,CLUB-5,CLUB-6,CLUB-6,CLUB-7,HEART-7,SPADE-7,SPADE-8,CLUB-8,CLUB-K,}", cardsinHand);
        Card jokerCard = new HeartCard("8", 8, 100, Card.STATUS_ASSIGNED);*/
      		
		Card deckCards[] = CardUtility.shuffleCards(3,true,2);
		/* Regular tesing ... */
		for(int i=0;i<13;i++)
		{
			cardsinHand.add(deckCards[i]);
		}
		Card jokerCard = deckCards[13];
		Date dateStarted = Calendar.getInstance().getTime();
		EasyBotStrategy strategy = new EasyBotStrategy(cardsinHand, true, jokerCard, GameListConstants.GAMELIST_THIRTEENCARD_CLOSED_TYPE,deckCards,13);
		strategy.playGame();
		System.out.println("Test Run : " + testrun + "  , DataStarted : " + dateStarted + " , DateEnded :- " + Calendar.getInstance().getTime());
		testrun++;
		}
	}

	private static void createCards(String cardlistsepbycommas,ArrayList<Card> cardlist)
	{
        String prepString = cardlistsepbycommas.replace("{","").replace("}", "");
        String arr[] = prepString.split(",");
        System.out.println("Num of Cards to be created is : " + arr.length);
        int deckid = 4;
        for(int i=0;i<arr.length;i++)
        {
        	String flower = arr[i].split("-")[0].trim();
        	String value = arr[i].split("-")[1].trim();
        	
        	int countValue = 0;
        	if(value.equals("A"))
        		countValue = 1;
        	else if(value.equals("J") || value.equals("Q") || value.equals("K") || value.equals("Joker"))
        		countValue = 10;
        	else
        		countValue = new Integer(value).intValue();
        	if(flower.contains("HEART"))
              		cardlist.add(new HeartCard(value, countValue, deckid, Card.STATUS_ASSIGNED));
        	if(flower.contains("DIAMOND"))
          		cardlist.add(new DiamondCard(value, countValue, deckid, Card.STATUS_ASSIGNED));    	
        	if(flower.contains("SPADE"))
          		cardlist.add(new SpadeCard(value, countValue, deckid, Card.STATUS_ASSIGNED));    	
        	if(flower.contains("CLUB"))
          		cardlist.add(new ClubCard(value, countValue, deckid, Card.STATUS_ASSIGNED));
        	if(flower.contains("WILD"))
        		cardlist.add(new JokerCard(value, countValue, deckid, Card.STATUS_ASSIGNED));
        	deckid++;
        }
        		
	}

	
}

