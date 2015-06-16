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
		//oneGameSevenRun();
		//regularrun(testrun, 500,GameListConstants.GAMELIST_SEVENCARD_OPEN_TYPE);
		regularrun(testrun, 15000,GameListConstants.GAMELIST_THIRTEENCARD_OPEN_TYPE);
		//oneGameThirteenRun();
	}
	
	
	private static void oneGameThirteenRun()
	{
		ArrayList<Card> cardsinHand = new ArrayList<Card>();
		createCards("{WILD-Joker,SPADE-A,HEART-4,CLUB-5,HEART-5,DIAMOND-8,SPADE-10,CLUB-Q,SPADE-Q,HEART-K,HEART-K,SPADE-K,CLUB-K,}", cardsinHand);
        Card jokerCard = new SpadeCard("3",3, 100, Card.STATUS_ASSIGNED);
        Card deckCards[] = CardUtility.shuffleCards(3,true,2);
        Date dateStarted = Calendar.getInstance().getTime();
		EasyBotStrategy strategy = new EasyBotStrategy(cardsinHand, true, jokerCard, GameListConstants.GAMELIST_THIRTEENCARD_CLOSED_TYPE,deckCards,13);
		strategy.playGame();
		System.out.println(" DataStarted : " + dateStarted + " , DateEnded :- " + Calendar.getInstance().getTime());
	}
	
	
	private static void oneGameSevenRun()
	{
		ArrayList<Card> cardsinHand = new ArrayList<Card>();
		createCards("{WILD-Joker,WILD-Joker,WILD-Joker,SPADE-A,WILD-Joker,WILD-Joker,SPADE-2}" , cardsinHand);
        Card jokerCard = new DiamondCard("3",3, 100, Card.STATUS_ASSIGNED);
        Card deckCards[] = CardUtility.shuffleCards(1,false,0);
        Date dateStarted = Calendar.getInstance().getTime();
		EasyBotStrategy strategy = new EasyBotStrategy(cardsinHand, true, jokerCard, GameListConstants.GAMELIST_SEVENCARD_OPEN_TYPE,deckCards,7);
		strategy.playGame();
		System.out.println(" DataStarted : " + dateStarted + " , DateEnded :- " + Calendar.getInstance().getTime());
	}
	
	
	private static void regularrun(int testrun,int nooftesttoRun,String gameType)
	{
		int noofcards = 0, noofdecks = 3;
		if(gameType.equals(GameListConstants.GAMELIST_SEVENCARD_CLOSED_TYPE) || 
		   gameType.equals(GameListConstants.GAMELIST_SEVENCARD_OPEN_TYPE))
		{
			noofcards = 7;noofdecks = 3;
		}
		else
		{
			noofcards = 13;noofdecks = 3;
		}
		int sum = 0;int turnfrom0_10 = 0,turnfrom10_15=0,turnfrom15_20 = 0,turnfromgt20 = 0;
		while(testrun < nooftesttoRun)
		{
        ArrayList<Card> cardsinHand = new ArrayList<Card>();
        
        /* Pattern Testing */
        /*createCards("{WILD-Joker,WILD-Joker,CLUB-A,DIAMOND-2,CLUB-5,CLUB-6,CLUB-6,CLUB-7,HEART-7,SPADE-7,SPADE-8,CLUB-8,CLUB-K,}", cardsinHand);
        Card jokerCard = new HeartCard("8", 8, 100, Card.STATUS_ASSIGNED);*/
      		
		Card deckCards[] = CardUtility.shuffleCards(noofdecks,true,2);
		/* Regular tesing ... */
		for(int i=0;i<noofcards;i++)
		{
			cardsinHand.add(deckCards[i]);
		}
		Card jokerCard = deckCards[noofcards];
		Date dateStarted = Calendar.getInstance().getTime();
		EasyBotStrategy strategy = new EasyBotStrategy(cardsinHand, true, jokerCard, gameType,deckCards,noofcards);
		int turnstoFinishGame = strategy.playGame();
		System.out.println("Test Run : " + testrun + "  , DataStarted : " + dateStarted + " , DateEnded :- " + Calendar.getInstance().getTime() + " ,numofTurns : " + turnstoFinishGame);
		testrun++;
		if(turnstoFinishGame > 0 && turnfrom0_10 < 10)
			turnfrom0_10++;
		if(turnstoFinishGame >= 10 && turnfrom0_10 < 15)
			turnfrom10_15++;
		if(turnstoFinishGame >= 15 && turnfrom0_10 < 20)
			turnfrom15_20++;
		if(turnstoFinishGame >= 20)
			turnfromgt20++;
		sum = sum +turnstoFinishGame;
		}
		
		System.out.println("Total Runs : " + nooftesttoRun + " , Avg no of turns : " + (float)sum/nooftesttoRun + " ,Turn Distributions :" + turnfrom0_10 + " , " + turnfrom10_15 + " , " + turnfrom15_20 + " , " + turnfromgt20);
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

