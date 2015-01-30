package com.adansoft.great21.ulitity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import com.adansoft.great21.games.GameListConstants;
import com.adansoft.great21.models.Card;
import com.adansoft.great21.models.ClubCard;
import com.adansoft.great21.models.Deck;
import com.adansoft.great21.models.DiamondCard;
import com.adansoft.great21.models.Game;
import com.adansoft.great21.models.HeartCard;
import com.adansoft.great21.models.HumanPlayer;
import com.adansoft.great21.models.Player;
import com.adansoft.great21.models.SpadeCard;


public class CardUtility {
	
	private static SpadeCard[] createSpadeCardChunk(int Deckid)
	{
		SpadeCard[] cardchunk = new SpadeCard[13];
		cardchunk[0] = new SpadeCard(Card.DISPLAY_ONE, 10, Deckid, Card.STATUS_UNASSIGNED);
		cardchunk[1] = new SpadeCard(Card.DISPLAY_TWO, 2, Deckid, Card.STATUS_UNASSIGNED);
		cardchunk[2] = new SpadeCard(Card.DISPLAY_THREE, 3, Deckid, Card.STATUS_UNASSIGNED);
		cardchunk[3] = new SpadeCard(Card.DISPLAY_FOUR, 4, Deckid, Card.STATUS_UNASSIGNED);
		cardchunk[4] = new SpadeCard(Card.DISPLAY_FIVE, 5, Deckid, Card.STATUS_UNASSIGNED);
		cardchunk[5] = new SpadeCard(Card.DISPLAY_SIX, 6, Deckid, Card.STATUS_UNASSIGNED);
		cardchunk[6] = new SpadeCard(Card.DISPLAY_SEVEN, 7, Deckid, Card.STATUS_UNASSIGNED);
		cardchunk[7] = new SpadeCard(Card.DISPLAY_EIGHT, 8, Deckid, Card.STATUS_UNASSIGNED);
		cardchunk[8] = new SpadeCard(Card.DISPLAY_NINE, 9, Deckid, Card.STATUS_UNASSIGNED);
		cardchunk[9] = new SpadeCard(Card.DISPLAY_TEN, 10, Deckid, Card.STATUS_UNASSIGNED);
		cardchunk[10] = new SpadeCard(Card.DISPLAY_ELEVEN, 10, Deckid, Card.STATUS_UNASSIGNED);
		cardchunk[11] = new SpadeCard(Card.DISPLAY_TWELVE, 10, Deckid, Card.STATUS_UNASSIGNED);
		cardchunk[12] = new SpadeCard(Card.DISPLAY_THIRTEEN, 10, Deckid, Card.STATUS_UNASSIGNED);
		
		
		return cardchunk;
	}
	
	private static HeartCard[] createHeartCardChunk(int Deckid)
	{
		HeartCard[] cardchunk = new HeartCard[13];
		cardchunk[0] = new HeartCard(Card.DISPLAY_ONE, 10, Deckid, Card.STATUS_UNASSIGNED);
		cardchunk[1] = new HeartCard(Card.DISPLAY_TWO, 2, Deckid, Card.STATUS_UNASSIGNED);
		cardchunk[2] = new HeartCard(Card.DISPLAY_THREE, 3, Deckid, Card.STATUS_UNASSIGNED);
		cardchunk[3] = new HeartCard(Card.DISPLAY_FOUR, 4, Deckid, Card.STATUS_UNASSIGNED);
		cardchunk[4] = new HeartCard(Card.DISPLAY_FIVE, 5, Deckid, Card.STATUS_UNASSIGNED);
		cardchunk[5] = new HeartCard(Card.DISPLAY_SIX, 6, Deckid, Card.STATUS_UNASSIGNED);
		cardchunk[6] = new HeartCard(Card.DISPLAY_SEVEN, 7, Deckid, Card.STATUS_UNASSIGNED);
		cardchunk[7] = new HeartCard(Card.DISPLAY_EIGHT, 8, Deckid, Card.STATUS_UNASSIGNED);
		cardchunk[8] = new HeartCard(Card.DISPLAY_NINE, 9, Deckid, Card.STATUS_UNASSIGNED);
		cardchunk[9] = new HeartCard(Card.DISPLAY_TEN, 10, Deckid, Card.STATUS_UNASSIGNED);
		cardchunk[10] = new HeartCard(Card.DISPLAY_ELEVEN, 10, Deckid, Card.STATUS_UNASSIGNED);
		cardchunk[11] = new HeartCard(Card.DISPLAY_TWELVE, 10, Deckid, Card.STATUS_UNASSIGNED);
		cardchunk[12] = new HeartCard(Card.DISPLAY_THIRTEEN, 10, Deckid, Card.STATUS_UNASSIGNED);
		
		
		return cardchunk;
	}
	
	private static ClubCard[] createClubCardChunk(int Deckid)
	{
		ClubCard[] cardchunk = new ClubCard[13];
		cardchunk[0] = new ClubCard(Card.DISPLAY_ONE, 10, Deckid, Card.STATUS_UNASSIGNED);
		cardchunk[1] = new ClubCard(Card.DISPLAY_TWO, 2, Deckid, Card.STATUS_UNASSIGNED);
		cardchunk[2] = new ClubCard(Card.DISPLAY_THREE, 3, Deckid, Card.STATUS_UNASSIGNED);
		cardchunk[3] = new ClubCard(Card.DISPLAY_FOUR, 4, Deckid, Card.STATUS_UNASSIGNED);
		cardchunk[4] = new ClubCard(Card.DISPLAY_FIVE, 5, Deckid, Card.STATUS_UNASSIGNED);
		cardchunk[5] = new ClubCard(Card.DISPLAY_SIX, 6, Deckid, Card.STATUS_UNASSIGNED);
		cardchunk[6] = new ClubCard(Card.DISPLAY_SEVEN, 7, Deckid, Card.STATUS_UNASSIGNED);
		cardchunk[7] = new ClubCard(Card.DISPLAY_EIGHT, 8, Deckid, Card.STATUS_UNASSIGNED);
		cardchunk[8] = new ClubCard(Card.DISPLAY_NINE, 9, Deckid, Card.STATUS_UNASSIGNED);
		cardchunk[9] = new ClubCard(Card.DISPLAY_TEN, 10, Deckid, Card.STATUS_UNASSIGNED);
		cardchunk[10] = new ClubCard(Card.DISPLAY_ELEVEN, 10, Deckid, Card.STATUS_UNASSIGNED);
		cardchunk[11] = new ClubCard(Card.DISPLAY_TWELVE, 10, Deckid, Card.STATUS_UNASSIGNED);
		cardchunk[12] = new ClubCard(Card.DISPLAY_THIRTEEN, 10, Deckid, Card.STATUS_UNASSIGNED);
		
		
		return cardchunk;
	}
	
	private static DiamondCard[] createDiamondCardChunk(int Deckid)
	{
		DiamondCard[] cardchunk = new DiamondCard[13];
		cardchunk[0] = new DiamondCard(Card.DISPLAY_ONE, 10, Deckid, Card.STATUS_UNASSIGNED);
		cardchunk[1] = new DiamondCard(Card.DISPLAY_TWO, 2, Deckid, Card.STATUS_UNASSIGNED);
		cardchunk[2] = new DiamondCard(Card.DISPLAY_THREE, 3, Deckid, Card.STATUS_UNASSIGNED);
		cardchunk[3] = new DiamondCard(Card.DISPLAY_FOUR, 4, Deckid, Card.STATUS_UNASSIGNED);
		cardchunk[4] = new DiamondCard(Card.DISPLAY_FIVE, 5, Deckid, Card.STATUS_UNASSIGNED);
		cardchunk[5] = new DiamondCard(Card.DISPLAY_SIX, 6, Deckid, Card.STATUS_UNASSIGNED);
		cardchunk[6] = new DiamondCard(Card.DISPLAY_SEVEN, 7, Deckid, Card.STATUS_UNASSIGNED);
		cardchunk[7] = new DiamondCard(Card.DISPLAY_EIGHT, 8, Deckid, Card.STATUS_UNASSIGNED);
		cardchunk[8] = new DiamondCard(Card.DISPLAY_NINE, 9, Deckid, Card.STATUS_UNASSIGNED);
		cardchunk[9] = new DiamondCard(Card.DISPLAY_TEN, 10, Deckid, Card.STATUS_UNASSIGNED);
		cardchunk[10] = new DiamondCard(Card.DISPLAY_ELEVEN, 10, Deckid, Card.STATUS_UNASSIGNED);
		cardchunk[11] = new DiamondCard(Card.DISPLAY_TWELVE, 10, Deckid, Card.STATUS_UNASSIGNED);
		cardchunk[12] = new DiamondCard(Card.DISPLAY_THIRTEEN, 10, Deckid, Card.STATUS_UNASSIGNED);
		
		
		return cardchunk;
	}

	public static Deck createDeck(int Deckid)
	{
		Deck deck = new Deck(Deckid);
		
		deck.setClubCard(createClubCardChunk(Deckid));
		deck.setDiamondCard(createDiamondCardChunk(Deckid));
		deck.setHeartCard(createHeartCardChunk(Deckid));
		deck.setSpadeCard(createSpadeCardChunk(Deckid));
		
		return deck;
	}
	

	public static Card[] shuffleCards(int numberofDecks)
	{
		Deck[] decklist = new Deck[numberofDecks];
		
		Card[] cardlist = new Card[52*numberofDecks];
		for(int i=0;i<numberofDecks;i++)
		{
			decklist[i] = createDeck(i);
		}
			
		for(int i=0;i<52*numberofDecks;i++)
		{
			while(true)
			{
				Card inputcard = null;
			    int deckid = NumberUtility.generateRandomNumber(1, numberofDecks);
			    int flowerid = NumberUtility.generateRandomNumber(1, 4);
			    int valueid = NumberUtility.generateRandomNumber(1, 13);
			    if(flowerid == 1)
			    	inputcard = decklist[deckid-1].getSpadeCard()[valueid-1];
			    if(flowerid == 2)
			    	inputcard = decklist[deckid-1].getHeartCard()[valueid-1];
			    if(flowerid == 3)
			    	inputcard = decklist[deckid-1].getClubCard()[valueid-1];
			    if(flowerid == 4)
			    	inputcard = decklist[deckid-1].getDiamondCard()[valueid-1];
			    
			    if(checkCardExists(cardlist, inputcard,i))
			    	continue;
			    else
			    {
			    	cardlist[i] = inputcard;
			    	break;
			    }
			}
		}
		
		return cardlist;
		
	}
	
	
	private static boolean checkCardExists(Card[] cardlist,Card inputcard,int index)
	{
		
		
		boolean result = false;
		for(int i=0;i<cardlist.length;i++)
		{
			if(cardlist[i] == null)
				continue;
			if(inputcard.getInstanceID().equals(cardlist[i].getInstanceID()))
			{
				result = true;
				break;
			}
		}
		return result;
	}

	
	public static int distributeCards(ArrayList<Player> playerlist,Card[] cardlist,int numofcardstodistribute)
	{
		int countofdistcards = playerlist.size()*numofcardstodistribute;
		int i=0;
		for(;i<countofdistcards;)
		{
			for(int j=0;j<playerlist.size();j++)
			{
				cardlist[i].setStatus(Card.STATUS_ASSIGNED);
				playerlist.get(j).assignCard(cardlist[i]);
				i++;
			}
		}
		return i;
	}
	
	public static Card pickJoker(Card[] cardlist,int numberofDecks)
	{
		Card card = null;
	    int jokerid = NumberUtility.generateRandomNumber(0, numberofDecks*52 - 1);
	    boolean jokerpicked = false;
	    while(!jokerpicked)
	    {
	    	card = cardlist[jokerid];
	    	if(card.getStatus().equals(Card.STATUS_UNASSIGNED))
	    	{
	    		jokerpicked = true;
	    		card.setStatus(Card.STATUS_JOKER);	    		
	    	}
	    	else
	    	{
	    		jokerid = NumberUtility.generateRandomNumber(0, numberofDecks*52);
	    	}
	    }
	    return card;
	}
	
	public static boolean checkSequence(Card[] cardlist)
	{
		boolean result = true;
		if(cardlist == null || cardlist.length < 3)
			return false;
		else
		{
			if(checkCardwithSameFlower(cardlist))
			{
				Arrays.sort(cardlist);
				for(int i =0;i<cardlist.length-1;i++)
				{
					Card curcard = cardlist[i];int curvalue = curcard.getInstrinsicValue();
					Card nextcard = cardlist[i+1];int nextvalue = nextcard.getInstrinsicValue();
					if(curvalue+1 == nextvalue)
					{
						continue;
					}
					else
					{
						if(curcard.getInstrinsicValue() == 1 && nextcard.getInstrinsicValue() == 12)
							continue;
						result = false;
						break;
					}
					
				}
			}
			else
			{
				result = false;
			}
		}
		
		return result;
	}
	
	
	private static boolean checkCardwithSameFlower(Card[] cardlist)
	{
		       if(cardlist[0] instanceof ClubCard)
		        	return checkAllClubs(cardlist);
				else if(cardlist[0] instanceof DiamondCard)
					return checkAllDiamond(cardlist);
				else if(cardlist[0] instanceof SpadeCard)
					return checkAllSpade(cardlist);
				else if(cardlist[0] instanceof HeartCard)
					return checkAllHearts(cardlist);
				else
					return false;		
	}
	
	private static boolean checkAllClubs(Card[] cardlist)
	{
		boolean result = true;
		for(Card card : cardlist)
		{
			if(!(card instanceof ClubCard))
				return false;
		}
		return result;
	}
	private static boolean checkAllSpade(Card[] cardlist)
	{
		boolean result = true;
		for(Card card : cardlist)
		{
			if(!(card instanceof SpadeCard))
				return false;
		}
		return result;
	}
	private static boolean checkAllDiamond(Card[] cardlist)
	{
		boolean result = true;
		for(Card card : cardlist)
		{
			if(!(card instanceof DiamondCard))
				return false;
		}
		return result;
	}
	private static boolean checkAllHearts(Card[] cardlist)
	{
		boolean result = true;
		for(Card card : cardlist)
		{
			if(!(card instanceof HeartCard))
				return false;
		}
		return result;
	}

	public static String checkDeclareGame(HashMap<String,Card[]> meldlist,Card jokerCard,String gameType)
	{
		String outputMessage = "";
		boolean isOrigSeqPresent = false;
		for(String key : meldlist.keySet())
		{
			boolean result = CardUtility.checkSequence(meldlist.get(key));
			if(result == true)
				isOrigSeqPresent = true;
		}
		if(!isOrigSeqPresent)
			return "No Original sequence present";
		
		if(gameType.equals(GameListConstants.GAMELIST_SEVENCARD_CLOSED_TYPE) || gameType.equals(GameListConstants.GAMELIST_SEVENCARD_OPEN_TYPE))
		{
			
		}
		
		return outputMessage;
	}
	
	
	public static boolean checkTripletorQuadraplets(Card[] cardlist)
	{
		boolean isclub = false,isheart = false,isdiamond = false,isspade = false;
		int countoftrue = 0;
		if(checkCardwithSameValue(cardlist))
		{
			if(cardlist.length < 3 || cardlist.length > 4)
				return false;
			for(Card card :cardlist)
			{
				if(card.getFlower().equals(Card.FLOWER_CLUBS))
					isclub = true;
				if(card.getFlower().equals(Card.FLOWER_HEART))
					isheart = true;
				if(card.getFlower().equals(Card.FLOWER_DIAMOND))
					isdiamond = true;
				if(card.getFlower().equals(Card.FLOWER_SPADE))
					isspade = true;
			}
			if(isclub)countoftrue++;if(isheart)countoftrue++;if(isdiamond)countoftrue++;if(isspade)countoftrue++;
			if(cardlist.length == 3 && countoftrue == 3 )
				return true;
			if(cardlist.length == 4 && countoftrue == 4 )
				return true;
		 }
		
		return false;
	}
	
	private static boolean checkCardwithSameValue(Card[] cardlist)
	{
		if(cardlist == null || cardlist.length == 0)
			return false;
		String firstcardValue = cardlist[0].getDisplayValue();
		for(Card curcard : cardlist)
		{
			String curcardvalue = curcard.getDisplayValue();
			if(firstcardValue.equals(curcardvalue))
				continue;
			else
				return false;
		}
		return true;
	}
	
	public static void interpretJoker(Card[] cardlist,Card jokerCard)
	{
		String jokerValue =  jokerCard.getDisplayValue();
		System.out.println("Excluded Cards : ");
		showCards(excludeJokerfromCardList(cardlist,jokerValue));
		Card[] excludedCardList = excludeJokerfromCardList(cardlist,jokerValue);
		if (excludedCardList.length > 1 )
		{
			if(checkCardwithSameFlower(excludedCardList))
			{
				System.out.println("Will be interpreted as Sequence number");
				int gap = checkGap(excludedCardList);
				if(gap == 1 || gap == 2)
					System.out.println("valid sequence" + gap);
				else
					System.out.println("Invalid sequence" + gap);
				
				//System.out.println(" Check Gap Result : - " + checkGap(excludedCardList));
			}
			else if(checkCardwithSameValue(excludedCardList))
			{
				System.out.println("Will be interpreted as Triplet/Quatraplet number");
			}
			else
			{
				System.out.println("Invalid Usage");
			}
		}
	}
	
	
	private static int checkGap(Card[] cardlist)
	{
		int gap = -1;
		Arrays.sort(cardlist);
		for(int i=0;i<cardlist.length-1;i++)
		{
			gap = cardlist[i+1].getInstrinsicValue() - cardlist[i].getInstrinsicValue();
			if(gap > 1)
			{
				if(cardlist[i].getInstrinsicValue() == 1 && cardlist[i+1].getInstrinsicValue() == 12)
				{
					gap = 1;
					continue;
				}
				if(cardlist[i].getInstrinsicValue() == 1 && cardlist[i+1].getInstrinsicValue() == 11)
				{
					gap = 2;
					continue;
				}
				return gap;
			}
				
		}
		return gap;
	}
	
	private static Card[] excludeJokerfromCardList(Card[] cardlist,String jokerValue)
	{
		ArrayList<Card> excludedCardList = new ArrayList<Card>();
		for(Card card : cardlist)
		{
			if(!card.getDisplayValue().equals(jokerValue))
			{
				excludedCardList.add(card);
			}
		}
		return excludedCardList.toArray(new Card[excludedCardList.size()]);
	}
	
	private static void showCards(Card[] cardlist)
	{
	   for(Card card : cardlist)
	   {
		   System.out.println("Card : " + card.getInstanceID());
	   }
	}
	
}
