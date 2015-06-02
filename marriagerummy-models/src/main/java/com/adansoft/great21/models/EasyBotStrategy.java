package com.adansoft.great21.models;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


import com.adansoft.great21.games.GameListConstants;
import com.adansoft.great21.ulitity.CardUtility;

public class EasyBotStrategy implements GameStrategy {
	
	private ArrayList<Card> cardsinHand;
	
	private ArrayList<SpadeCard> spadelist;
	private ArrayList<DiamondCard> diamondlist;
	private ArrayList<HeartCard> heartlist;
	private ArrayList<ClubCard> clublist;
	private ArrayList<Card> jokerlist;
	private HashMap<String,ArrayList<Card>> possiblesealedCardMap;
	private HashMap<String,ArrayList<Card>> sealedCardMap;
	private ArrayList<Card> looseCards;
	private int noOfWildCards;

	
	private boolean canSeeJoker;
	private boolean jokerKnown;
	private Card roundJokerCard;
	private String gameType;
	
	
	public EasyBotStrategy(ArrayList<Card> cardinhand,boolean jokerknown,Card jokercard,String gameType)
	{
		this.cardsinHand = cardinhand;
		this.jokerKnown = jokerknown;
		this.roundJokerCard = jokercard;
		this.gameType = gameType;
		
		init();
		getCardsinHand();
		analyzeCardsinHand();
		
	}
	
	
	
	
	private void init()
	{
		spadelist = new ArrayList<SpadeCard>();
		diamondlist = new ArrayList<DiamondCard>();
		heartlist = new ArrayList<HeartCard>();
		clublist = new ArrayList<ClubCard>();
		jokerlist = new ArrayList<Card>();
		looseCards = new ArrayList<Card>();
		sealedCardMap = new HashMap<String, ArrayList<Card>>();
		possiblesealedCardMap = new HashMap<String, ArrayList<Card>>();
		
	}
	
	private void analyzeCardsinHand()
	{
		Card cardinHand[] = CardUtility.sortCards(cardsinHand.toArray(new Card[cardsinHand.size()]));
		splitCardsup(cardinHand);
		checkForSequences();
		attemptSealinginLooseCards();
		postAnalysis();
	}
	
	
	
	
	private void splitCardsup(Card[] cardlist)
	{
		for(Card card : cardlist)
		{
			if(jokerKnown)
			{
				if( card.getDisplayValue().equals(roundJokerCard.getDisplayValue()))
						{
					           jokerlist.add(card);
					           return;
						}
			}
			if(card instanceof SpadeCard)
				spadelist.add((SpadeCard) card);
			if(card instanceof DiamondCard)
				diamondlist.add((DiamondCard) card);
			if(card instanceof HeartCard)
				heartlist.add((HeartCard) card);
			if(card instanceof ClubCard)
				clublist.add((ClubCard) card);
			if(card instanceof JokerCard)
				jokerlist.add((JokerCard) card);
				
		}
		noOfWildCards = jokerlist.size();
	}
	
	private void checkForSequences()
	{
		boolean isSealedSequnce = false;
		isSealedSequnce = attemptToSealviaSequences(new ArrayList<Card>(spadelist), Card.FLOWER_SPADE);
		if(isSealedSequnce)
			canSeeJoker = true;		
		isSealedSequnce = attemptToSealviaSequences(new ArrayList<Card>(heartlist), Card.FLOWER_HEART);
		if(isSealedSequnce)
			canSeeJoker = true;		
		isSealedSequnce = attemptToSealviaSequences(new ArrayList<Card>(diamondlist), Card.FLOWER_DIAMOND);
		if(isSealedSequnce)
			canSeeJoker = true;	
		isSealedSequnce = attemptToSealviaSequences(new ArrayList<Card>(clublist), Card.FLOWER_CLUBS);
		if(isSealedSequnce)
			canSeeJoker = true;	
	}
	
	
	private void postAnalysis()
	{
		if(sealedCardMap.size() == 0)
			System.out.println("Nothing has been sealed.");
		else
		{
			if( getSealedCardCount() == getDeclarableCount())
				System.out.println("Ready to Declare");
			else
			{
				float percent = ((float)getSealedCardCount()/getDeclarableCount()) * 100;
				System.out.println("Sealed % = " + percent + "% , Ready to Play");
			}
		}
	}
	
	
	private int getDeclarableCount()
	{
		if( gameType.equals(GameListConstants.GAMELIST_SEVENCARD_CLOSED_TYPE) || 
			gameType.equals(GameListConstants.GAMELIST_SEVENCARD_OPEN_TYPE))
		{
			    return 7;
		}
		else if( gameType.equals(GameListConstants.GAMELIST_THIRTEENCARD_CLOSED_TYPE) || 
				gameType.equals(GameListConstants.GAMELIST_THIRTEENCARD_OPEN_TYPE))
			{
				return 13;
			}
		else
		{
			return 21;
		}
	}
	
	private int getSealedCardCount()
	{
		int count = 0;
		for(String key : sealedCardMap.keySet())
		{
			count = count + sealedCardMap.get(key).size();
		}
		return count;
	}
	
	
	public boolean attemptToSealviaSequences(ArrayList<Card> cardlist,String flower)
	{
		boolean result = false;
		if(cardlist.size() >= 3)
		{
			if(cardlist.size() == 5)
				  addPossibleSealing(cardlist, 5, flower);
			if(cardlist.size() >= 4)
				  addPossibleSealing(cardlist, 4, flower);
			if(cardlist.size() >= 3)
				  addPossibleSealing(cardlist, 3, flower);
		}
		else
		{
			    looseCards.addAll(cardlist);
		}
		
		return result;
	}
	
	
	private void addPossibleSealing(ArrayList<Card> cardlist,int streamlength,String flower)
	{
		HashMap<String,ArrayList<Card>> streamer = getCardsByStreaming(cardlist.toArray(new Card[cardlist.size()]), streamlength);
		for(String key : streamer.keySet())
		{
			 ArrayList<Card> cardset = streamer.get(key);
			 boolean sequencePresent = CardUtility.checkSequence(cardset.toArray(new Card[cardset.size()]));
			 if(sequencePresent)
			        possiblesealedCardMap.put("ORIG_SEQUENCE"+"-"+ streamlength +"Cards-"+ flower+"-"+key, new ArrayList<Card>(cardset));
			 else
				looseCards.addAll(cardset);
		}
	}
	
	private void attemptSealinginLooseCards()
	{
		Card[] looseCardArray = looseCards.toArray(new Card[looseCards.size()]);
		Arrays.sort(looseCardArray);
		
		if(looseCardArray.length >= 4)
		{
		   HashMap<String,ArrayList<Card>> streamlist = getCardsByStreaming(looseCardArray,4);
		   for(String key : streamlist.keySet())
		   {
			   System.out.println("Loose Cards ...... : ");
			   getCardsinList(streamlist.get(key));
			   
		   }
		}
		if(looseCardArray.length >= 3)
		{
		   HashMap<String,ArrayList<Card>> streamlist = getCardsByStreaming(looseCardArray,3);
		   for(String key : streamlist.keySet())
		   {
			   System.out.println("Loose Cards :3 ...... : " + key);
			   getCardsinList(streamlist.get(key));
			   
		   }
		}
		//isTriple = CardUtility.checkTripletorQuadraplets(looseCards.toArray(new Card[looseCards.size()]));
	}

	private HashMap<String,ArrayList<Card>> getCardsByStreaming(Card[] cardlist,int streamlength)
	{
		HashMap<String,ArrayList<Card>> cardStream = new HashMap<String, ArrayList<Card>>();
		for(int i=0;i<cardlist.length;i++)
		{
			ArrayList<Card> streamcardlist = new ArrayList<Card>();
			if((i + streamlength) > cardlist.length)
			{
				break;
			}
			for(int j=i;j<streamlength+i;j++)
			{
				streamcardlist.add(cardlist[j]);
			}
			cardStream.put("STREAM-"+i,streamcardlist);
			
		}
		return cardStream;
	}
	
	
	
	
	
	
	public int getNoOfWildCards() {
		return noOfWildCards;
	}

	public boolean isCanSeeJoker() {
		return canSeeJoker;
	}


	@Override
	public Card discardCard(ArrayList<Card> cardlist) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String pickupCard(Card card) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void getCardsinHand()
	{
		for(Card card : cardsinHand)
		{
			System.out.println("Card : " + card.getInstanceID());
		}
	}
	
	public void getCardsinList(ArrayList<Card> cards)
	{
		for(Card card : cards)
		{
			System.out.println("Card : " + card.getInstanceID());
		}
	}
	
	private void printAllPossibleHashMaps()
	{
		
	}

}
