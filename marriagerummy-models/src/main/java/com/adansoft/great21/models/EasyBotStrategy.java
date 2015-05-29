package com.adansoft.great21.models;

import java.util.ArrayList;
import java.util.HashMap;

import com.adansoft.great21.ulitity.CardUtility;

public class EasyBotStrategy implements GameStrategy {
	
	private ArrayList<Card> cardsinHand;
	
	private ArrayList<SpadeCard> spadelist;
	private ArrayList<DiamondCard> diamondlist;
	private ArrayList<HeartCard> heartlist;
	private ArrayList<ClubCard> clublist;
	private ArrayList<Card> jokerlist;
	private HashMap<String,ArrayList<Card>> sealedCardMap;
	private ArrayList<Card> looseCards;
	private int noOfWildCards;
	
	
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
		
	}
	
	private void analyzeCardsinHand()
	{
		Card cardinHand[] = CardUtility.sortCards(cardsinHand.toArray(new Card[cardsinHand.size()]));
		splitCardsup(cardinHand);
		checkForSequences();
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
		if(spadelist.size() > 3)
		{
			boolean sequencePresent = CardUtility.checkSequence(spadelist.toArray(new SpadeCard[spadelist.size()]));
			if(sequencePresent)
			{
				if(spadelist.size() == 3)
				{
					sealedCardMap.put("ORIG_SEQUENCE", new ArrayList<Card>(spadelist));
				}
			}
		}
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
	
	
	
	
	

}
