package com.adansoft.great21.controller.helpers;

import java.util.ArrayList;

import com.adansoft.great21.models.Card;
import com.adansoft.great21.models.ClubCard;
import com.adansoft.great21.models.DiamondCard;
import com.adansoft.great21.models.HeartCard;
import com.adansoft.great21.models.JokerCard;
import com.adansoft.great21.models.SpadeCard;
import com.adansoft.great21.ulitity.CardUtility;

public class TrainingHelper {

	public static boolean validateSequence(String[] cardInstanceID)
	{
		return CardUtility.checkSequence(convertoCardArray(cardInstanceID));	
	}
	
	public static boolean validateTriplets(String[] cardInstanceID)
	{
		return CardUtility.checkTripletorQuadraplets(convertoCardArray(cardInstanceID));
	}
	
	public static boolean validateSequenceWithJoker(String[] cardInstanceID)
	{
		Card jokerCard = new JokerCard("Joker", 0, 1, Card.STATUS_UNASSIGNED);
		String result = CardUtility.interpretJokerandValidate(convertoCardArray(cardInstanceID), jokerCard);
	    if(result.equals(CardUtility.JOKERINTERPRET_VALIDSEQUENCE))
	    	return true;
	    else
	    	return false;
	}
	
	public static boolean validateTripletWithJoker(String[] cardInstanceID)
	{
		Card jokerCard = new JokerCard("Joker", 0, 1, Card.STATUS_UNASSIGNED);
		String result = CardUtility.interpretJokerandValidate(convertoCardArray(cardInstanceID), jokerCard);
	    if(result.equals(CardUtility.JOKERINTERPRET_VALIDTRIPQUADR))
	    	return true;
	    else
	    	return false;
	}
	
	
	private static Card[] convertoCardArray(String[] cardInstanceID)
	{
		ArrayList<Card> cardlist = new ArrayList<Card>();
		
		for(String currentId : cardInstanceID)
		{
			Card card = null; int countValue = 0;
			String flower = currentId.split("-")[0];
			String displayValue = currentId.split("-")[2];
			if(displayValue.equals("A"))
				countValue = 1;
			else if(displayValue.equals("J"))
				countValue = 11;
			else if(displayValue.equals("Q"))
				countValue = 12;
			else if(displayValue.equals("K"))
				countValue = 13;
			else if(displayValue.equals("Joker"))
				countValue = 0;
			else
				countValue = Integer.parseInt(displayValue);
			
			if(flower.equalsIgnoreCase(Card.FLOWER_CLUBS))
				card = new ClubCard(displayValue, countValue, 1, Card.STATUS_UNASSIGNED);
			if(flower.equalsIgnoreCase(Card.FLOWER_SPADE))
				card = new SpadeCard(displayValue, countValue, 1, Card.STATUS_UNASSIGNED);
			if(flower.equalsIgnoreCase(Card.FLOWER_DIAMOND))
				card = new DiamondCard(displayValue, countValue, 1, Card.STATUS_UNASSIGNED);
			if(flower.equalsIgnoreCase(Card.FLOWER_HEART))
				card = new HeartCard(displayValue, countValue, 1, Card.STATUS_UNASSIGNED);
			if(flower.equalsIgnoreCase(Card.FLOWER_WILD))
				card = new JokerCard(displayValue, countValue, 1, Card.STATUS_UNASSIGNED);
				
			cardlist.add(card);
		}
		
		return cardlist.toArray(new Card[cardlist.size()]);
	}
	
}
