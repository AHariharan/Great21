package com.adansoft.great21.bot;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;



import com.adansoft.great21.games.GameListConstants;
import com.adansoft.great21.models.Card;
import com.adansoft.great21.models.ClubCard;
import com.adansoft.great21.models.DiamondCard;
import com.adansoft.great21.models.GameStrategy;
import com.adansoft.great21.models.HeartCard;
import com.adansoft.great21.models.JokerCard;
import com.adansoft.great21.models.SpadeCard;
import com.adansoft.great21.ulitity.CardUtility;

public class EasyBotStrategy implements GameStrategy {
	
	private ArrayList<Card> cardsinHand;
	
	private ArrayList<SpadeCard> spadelist;
	private ArrayList<DiamondCard> diamondlist;
	private ArrayList<HeartCard> heartlist;
	private ArrayList<ClubCard> clublist;
	private ArrayList<Card> jokerlist;
	private HashMap<String,ArrayList<PossibleSetCards>> possiblesealedCardMap;
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
		possiblesealedCardMap = new HashMap<String, ArrayList<PossibleSetCards>>();
		
	}
	
	private void analyzeCardsinHand()
	{
		Card cardinHand[] = CardUtility.sortCards(cardsinHand.toArray(new Card[cardsinHand.size()]));
		splitCardsup(cardinHand);
		checkForSequences();
		printAllPossibleHashMaps();
		//attemptSealinginLooseCards();
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
		isSealedSequnce = attemptToSealviaSequences(new ArrayList<Card>(spadelist), Card.FLOWER_SPADE,"spadelist");
		if(isSealedSequnce)
			canSeeJoker = true;		
		isSealedSequnce = attemptToSealviaSequences(new ArrayList<Card>(heartlist), Card.FLOWER_HEART,"heartlist");
		if(isSealedSequnce)
			canSeeJoker = true;		
		isSealedSequnce = attemptToSealviaSequences(new ArrayList<Card>(diamondlist), Card.FLOWER_DIAMOND,"diamondlist");
		if(isSealedSequnce)
			canSeeJoker = true;	
		isSealedSequnce = attemptToSealviaSequences(new ArrayList<Card>(clublist), Card.FLOWER_CLUBS,"clublist");
		if(isSealedSequnce)
			canSeeJoker = true;	
		detectBestPossibleCardSet();
		if( sealedCardMap.size() > 0 )
		{
			ArrayList<Card> sealedCardList = new ArrayList<Card>();
			for(String key : sealedCardMap.keySet())
				sealedCardList.addAll(sealedCardMap.get(key));
			
			looseCards = new ArrayList<Card>();
			looseCards.addAll(getRemainingCards(sealedCardList));
			
		}
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
	
	
	public boolean attemptToSealviaSequences(ArrayList<Card> cardlist,String flower,String origin)
	{
		ArrayList<PossibleSetCards> pcardsetarr = new ArrayList<PossibleSetCards>();
		boolean result = false;
		if(cardlist.size() >= 3)
		{
			if(cardlist.size() == 5 && ( 
			!(gameType.equals(GameListConstants.GAMELIST_SEVENCARD_CLOSED_TYPE)) &&
			!(gameType.equals(GameListConstants.GAMELIST_SEVENCARD_OPEN_TYPE))
			))
				  addPossibleSealing(cardlist, 5, flower,origin,pcardsetarr);
			if(cardlist.size() >= 4)
				  addPossibleSealing(cardlist, 4, flower,origin,pcardsetarr);
			if(cardlist.size() >= 3)
				  addPossibleSealing(cardlist, 3, flower,origin,pcardsetarr);
			
			possiblesealedCardMap.put("ORIGIN-"+origin, pcardsetarr);
		}
		else
		{
			    looseCards.addAll(cardlist);
		}
		
		return result;
	}
	
	
	
	private void detectBestPossibleCardSet()
	{
		PossibleSetCards bestprobableset = null;
		for(String key : possiblesealedCardMap.keySet())
		{
			
			int weight = 0;
			ArrayList<PossibleSetCards> pcardsets = possiblesealedCardMap.get(key);
			for(PossibleSetCards pcardset : pcardsets)
			{
				int currentweight = pcardset.getHowmanySeq() + pcardset.getHowmanytrip();
				if(currentweight > weight)
				{
					weight = currentweight;
					bestprobableset = pcardset;
				}
			}
			System.out.println("-------------------------- BEST PROBABLE --------------------------------------------------");
			getCardsinList(bestprobableset.getCardList());
			sealedCardMap.put(key, bestprobableset.getCardList());
			System.out.println("-------------------------- END OF BEST PROBABLE --------------------------------------------------");
			
		}
		
	
	}
	
	private void addPossibleSealing(ArrayList<Card> cardlist,int streamlength,String flower,String origin,ArrayList<PossibleSetCards> pcardsetarr)
	{
		HashMap<String,ArrayList<Card>> streamer = getCardsByStreaming(cardlist.toArray(new Card[cardlist.size()]), streamlength);
		for(String key : streamer.keySet())
		{
			 ArrayList<Card> cardset = streamer.get(key);
			 boolean sequencePresent = CardUtility.checkSequence(cardset.toArray(new Card[cardset.size()]));
			 if(sequencePresent)
			 {
				    PossibleSetCards pcardset = new PossibleSetCards(streamlength, PossibleSetCards.SETTYPE_SEQUENCE, cardset, 0, flower, origin);
				    AnalyzedCardResult result = analyzeRemainingCards(getRemainingCards(pcardset.getCardList()));
				    pcardset.setHowmanySeq(result.getHowmanysequencescanbemade());
				    pcardset.setHowmanytrip(result.getHowmanytripletscanbemade());
				    pcardsetarr.add(pcardset);
			 }
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
	
	//Display function for all cards
	public void getCardsinHand()
	{
		for(Card card : cardsinHand)
		{
			System.out.println("Card : " + card.getInstanceID());
		}
	}
	
	//Display function for all cards in the provided list
	public void getCardsinList(ArrayList<Card> cards)
	{
		for(Card card : cards)
		{
			System.out.println("Card : " + card.getInstanceID());
		}
	}
	
	private void printAllPossibleHashMaps()
	{
		for(String key : possiblesealedCardMap.keySet())
		{
			ArrayList<PossibleSetCards> cardsetarr = possiblesealedCardMap.get(key);
			for(PossibleSetCards cardset : cardsetarr)
			{
			       System.out.println(cardset.getNoofCards() +"-"+ cardset.getFlower() + "-"+ cardset.getSetType() +"-"+ cardset.getOrigin());
			       System.out.println("-------------------------------------------------------------------------------------------------------");
			       for(Card card : cardset.getCardList())
			       {
			                  	System.out.println("Card :- " + card.getInstanceID());
			       }
			       System.out.println("------------------- Remaining Cards  ----------------------------------------------------------------------------");
			       System.out.println(" SEQ : " + cardset.getHowmanySeq() + " Trip : " + cardset.getHowmanytrip());
			      /* AnalyzedCardResult result = analyzeRemainingCards(getRemainingCards(cardset.getCardList()));
			       cardset.setHowmanySeq(result.getHowmanysequencescanbemade());
			       cardset.setHowmanytrip(result.getHowmanytripletscanbemade());*/
			       System.out.println("-------------------------------------------------------------------------------------------------------");
			}
			
			
		}
	}
	
	
	private AnalyzedCardResult analyzeRemainingCards(ArrayList<Card> cardlist)
	{
		AnalyzedCardResult result = new AnalyzedCardResult();
		int howmanysequencescanbemade = getHowManySequencescanbeMade(cardlist);
		int howmanytripcanbemade = getHowManyTripletscanbeMade(cardlist);
		result.setHowmanysequencescanbemade(howmanysequencescanbemade);
		result.setHowmanytripletscanbemade(howmanytripcanbemade);
		//System.out.println("How many triplets :-  " + howmanytripcanbemade + " , How many Seq :" + howmanysequencescanbemade);
		return result;
	}
	
	
	//Helper function to get unique cards used in getHowManyTripletscanbeMade
	private ArrayList<Card> getDistinctCardsFromListbyDisplayValue(ArrayList<Card> cardlist)
	{
		ArrayList<Card> distinctCards = new ArrayList<Card>();
	    for(int i=0;i<cardlist.size();i++)
	    {
	    	if(i==0)
	    	{
	    		distinctCards.add(cardlist.get(i));
	    		continue;
	    	}
	    	else
	    	{
	    		boolean isUnique = true;
	    		for(Card existingcard : distinctCards)
	    		{
	    			if(cardlist.get(i).getDisplayValue().equals(existingcard.getDisplayValue()))
	    					isUnique = false;	
	    		}
	    		if(isUnique)
	    			distinctCards.add(cardlist.get(i));
	    	}
	    }
		
		return distinctCards;
	}
	
	//Get How many possible Triplets from remaining cards
	private int getHowManyTripletscanbeMade(ArrayList<Card> cardlist)
	{
		int count = 0;
		Card[] sortedCards = cardlist.toArray(new Card[cardlist.size()]);
		Arrays.sort(sortedCards);
		
		ArrayList<Card> distinctCards = getDistinctCardsFromListbyDisplayValue(cardlist);
		for(Card card : distinctCards)
		{
			int similarcardcount = 0;
			ArrayList<Card> similarCards = new ArrayList<Card>();
			for(Card loopCard : sortedCards)
			{
				if(card.getDisplayValue().equals(loopCard.getDisplayValue()))
				{
					similarCards.add(loopCard);
					similarcardcount++;
				}
			}
			if( similarcardcount >=3 )
			{
				boolean isTriplet = CardUtility.checkTripletorQuadraplets(similarCards.toArray(new Card[similarCards.size()]));
				if(isTriplet)
					count++;
			}
		}
		
		return count;
	}
	
	//Get How many possible sequences from remaining cards
	private int getHowManySequencescanbeMade(ArrayList<Card> cardlist)
	{
		int count = 0;
		ArrayList<SpadeCard> splist = new ArrayList<SpadeCard>();
		ArrayList<HeartCard> htlist = new ArrayList<HeartCard>();
		ArrayList<DiamondCard> ddlist = new ArrayList<DiamondCard>();
		ArrayList<ClubCard> cblist = new ArrayList<ClubCard>();
		for(Card card:cardlist)
		{
			if(card instanceof SpadeCard)
				splist.add((SpadeCard)card);
			if(card instanceof HeartCard)
				htlist.add((HeartCard)card);
			if(card instanceof DiamondCard)
				ddlist.add((DiamondCard)card);
			if(card instanceof ClubCard)
				cblist.add((ClubCard)card);
		}
        count = count + getCountsofSequenceinList(new ArrayList<Card>(splist));
        count = count + getCountsofSequenceinList(new ArrayList<Card>(htlist));
        count = count + getCountsofSequenceinList(new ArrayList<Card>(ddlist));
        count = count + getCountsofSequenceinList(new ArrayList<Card>(cblist));
		
	  return count;	
	}
	
	// Helper function to get count inside a same flower list of cards used in getHowManySequencescanbeMade
	private int getCountsofSequenceinList(ArrayList<Card> cardlist)
	{
		int count = 0;
		Card[] cardarray = cardlist.toArray(new Card[cardlist.size()]);
		if(cardlist.size() >= 3)
		{
			if(cardlist.size() >= 4)
			{
				HashMap<String,ArrayList<Card>> streamedCards = getCardsByStreaming(cardarray, 4);
				for(String key : streamedCards.keySet())
				{
					ArrayList<Card> cardset = streamedCards.get(key);
					if(CardUtility.checkSequence(cardset.toArray(new Card[cardset.size()])))
						  count++;
						
				}
			}
			if(cardlist.size() >= 3)
			{
				HashMap<String,ArrayList<Card>> streamedCards = getCardsByStreaming(cardarray, 3);
				for(String key : streamedCards.keySet())
				{
					ArrayList<Card> cardset = streamedCards.get(key);
					if(CardUtility.checkSequence(cardset.toArray(new Card[cardset.size()])))
						  count++;
						
				}
			}
		}
		
		
	    return count;
	}
	
	// Get remaining cards from hand other than list of cards provided as input
	private ArrayList<Card> getRemainingCards(ArrayList<Card> cardlist)
	{
		ArrayList<Card> remainingCards = new ArrayList<Card>();
		for(Card card : cardsinHand)
		{
			boolean cardexistsinGroup = false;
			for(Card cardfromset : cardlist)
			{
				if(card.getInstanceID().equals(cardfromset.getInstanceID()))
						cardexistsinGroup = true;	
			}
			if(!cardexistsinGroup)
				remainingCards.add(card);
		}
		
		return remainingCards;
	}

}