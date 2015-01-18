package com.adansoft.great21.ulitity;

import java.util.ArrayList;

import com.adansoft.great21.models.Card;
import com.adansoft.great21.models.ClubCard;
import com.adansoft.great21.models.Deck;
import com.adansoft.great21.models.DiamondCard;
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
			if(inputcard.getCardInstanceID().equals(cardlist[i].getCardInstanceID()))
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

}
