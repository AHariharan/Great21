package com.adansoft.great21.bot;

import java.util.ArrayList;

import com.adansoft.great21.models.Card;
import com.adansoft.great21.models.JokerCard;

public class CardGroupScoreCalculator {

	private static double threecard_sequence_weight = 5.0;
	private static double fourcard_sequence_weight = 6.5;
	private static double fivecard_sequence_weight = 7.0;
	private static double threecard_triplet_weight = 4.0;
	private static double fourcard_triplet_weight = 4.5;
	private static double loosecard_weight = -4.0;
	
	private static double threecard_sequence_weight_with_joker1 = 3.5;
	private static double threecard_sequence_weight_with_joker2 = 1.5;
	private static double fourcard_sequence_weight_with_joker1 = 4.0;
	private static double fourcard_sequence_weight_with_joker2 = 2.5;
	private static double fourcard_sequence_weight_with_joker3 = 1.5;
	private static double fivecard_sequence_weight_with_joker3 = 1.5;
	
	private static double threecard_triplet_with_joker1 = 2.5;
	private static double threecard_triplet_with_joker2 = 1.0;
	//private static double fivecard_sequence_weight = 3.0;
	
	
	
	
	private static double seq_distance_one = 5.0;
	private static double seq_distance_two = 3.0;
	private static double seq_distance_three = 1.0;
	private static double seq_distance_four = 0;
	private static double seq_distance_greater_than_four = -2.0;
	private static double seq_distance_zero = -5.0;
	
	private static double tripcount_score_zero = -1.0;
	private static double tripcount_score_greater_zero = 3.0;
	
	public static double calculateScore(GroupCardSet set)
	{
		double score = 0;
		for(String keys : set.getGroupedCardMap().keySet())
		{
			CardSetNode node =  set.getGroupedCardMap().get(keys);
			if(node.getType().equals(CardSetNode.TYPE_SEQUENCE))
			{
				int currentSize = node.getCardList().size();
				if(currentSize == 5)
					score = score + currentSize * fivecard_sequence_weight;
				if(currentSize == 4)
					score = score + currentSize * fourcard_sequence_weight;
				if(currentSize == 3)
					score = score + currentSize * threecard_sequence_weight;
			}
			if(node.getType().equals(CardSetNode.TYPE_TRIPLET))
			{
				int currentSize = node.getCardList().size();
				if(currentSize == 4)
					score = score + currentSize * fourcard_triplet_weight;
				if(currentSize == 3)
					score = score + currentSize * threecard_triplet_weight;
			}
			if(node.getType().equals(CardSetNode.TYPE_LOOSE))
			{
				int currentSize = node.getCardList().size();
					score = score + currentSize * loosecard_weight;				
			}
			if(node.getType().equals(CardSetNode.TYPE_SEQUENCE_JOKER_1))
			{
				int currentSize = node.getCardList().size();
				if(currentSize == 3)
				       score = score + currentSize * threecard_sequence_weight_with_joker1;
				if(currentSize == 4 )
					   score = score + currentSize * fourcard_sequence_weight_with_joker1;
			}
			if(node.getType().equals(CardSetNode.TYPE_SEQUENCE_JOKER_2))
			{
				int currentSize = node.getCardList().size();
				if(currentSize == 3)
				       score = score + currentSize * threecard_sequence_weight_with_joker2;
				if(currentSize == 4 )
					   score = score + currentSize * fourcard_sequence_weight_with_joker2;	
			}
			if(node.getType().equals(CardSetNode.TYPE_SEQUENCE_JOKER_3))
			{
				int currentSize = node.getCardList().size();
				if(currentSize == 4)
				     score = score + currentSize * fourcard_sequence_weight_with_joker3;
				if(currentSize == 5)
				     score = score + currentSize * fivecard_sequence_weight_with_joker3;
			}
			if(node.getType().equals(CardSetNode.TYPE_TRIPLET_JOKER_1))
			{
				int currentSize = node.getCardList().size();
				score = score + currentSize * threecard_triplet_with_joker1;		
			}
			if(node.getType().equals(CardSetNode.TYPE_TRIPLET_JOKER_2))
			{
				int currentSize = node.getCardList().size();
				score = score + currentSize * threecard_triplet_with_joker2;		
			}
		}
		
		return score;
	}
	
	public static double findProxmityforSequence(Card[] listofCards,int currentCardPos)
	{
		double score = 0;
		int distance;
		if(listofCards.length == 1)
		{
			distance = 1000;
		}
		else if(currentCardPos == listofCards.length - 1) // If its Last Card
		{
			distance = listofCards[currentCardPos].getInstrinsicValue() - listofCards[currentCardPos-1].getInstrinsicValue();
			if(listofCards[0].getDisplayValue().equals("A"))
			{
				int distanceA = 14 - listofCards[currentCardPos].getInstrinsicValue();
				if(distanceA < distance)
					distance = distanceA;
			}
		}
		else if(currentCardPos == 0) // If its First Card
		{
			distance = listofCards[currentCardPos+1].getInstrinsicValue() - listofCards[currentCardPos].getInstrinsicValue();
			if(listofCards[currentCardPos].getDisplayValue().equals("A"))
			{
				if(listofCards[listofCards.length-1].getDisplayValue().equals("J") ||
				   listofCards[listofCards.length-1].getDisplayValue().equals("Q") ||
				   listofCards[listofCards.length-1].getDisplayValue().equals("K") ||
				   listofCards[listofCards.length-1].getDisplayValue().equals("10"))
				{
					int distanceA = 14 - listofCards[listofCards.length-1].getInstrinsicValue();
					if(distanceA < distance)
						distance = distanceA;
					 
				}
			}
		}
		else
		{
		    int distance1 = listofCards[currentCardPos+1].getInstrinsicValue() - listofCards[currentCardPos].getInstrinsicValue();
		    int distance2 = listofCards[currentCardPos].getInstrinsicValue() - listofCards[currentCardPos-1].getInstrinsicValue();
		    if(distance1<=distance2)
		    	distance = distance1;
		    else
		    	distance = distance2;
		    
		    if(listofCards[currentCardPos].getDisplayValue().equals("A"))
			{
				if(listofCards[listofCards.length-1].getDisplayValue().equals("J") ||
				   listofCards[listofCards.length-1].getDisplayValue().equals("Q") ||
				   listofCards[listofCards.length-1].getDisplayValue().equals("K") ||
				   listofCards[listofCards.length-1].getDisplayValue().equals("10"))
				{
					int distanceA = 14 - listofCards[listofCards.length-1].getInstrinsicValue();
					if(distanceA < distance)
						distance = distanceA;
					 
				}
			}
		    
		}
		if(distance < 0 )
		{
			System.out.println( " Error !!!! This should never happen");
		}
		if(distance == 0)
			score = seq_distance_zero;
		else if(distance == 1)
			score = seq_distance_one;
		else if(distance == 2)
			score = seq_distance_two;
		else if(distance == 3)
			score = seq_distance_three;
		else if(distance == 4)
			score = seq_distance_four;
		else
			score = seq_distance_greater_than_four;
		
		return score;
	}
	
	public static double findTripPossibilityScore(Card[] listofCards,Card currentCard)
	{
		int count = 0;
		double score = 0;
		for(int i=0;i<listofCards.length;i++)
		{
			if(listofCards[i].getInstanceID().equals(currentCard.getInstanceID()))
				continue;
			else
			{
				if(listofCards[i].getDisplayValue().equals(currentCard.getDisplayValue()) &&
					!(listofCards[i].getFlower().equals(currentCard.getFlower())))
					{
					   count++;
					}
			}
		}
		if(count == 0)
			score = tripcount_score_zero;
		else
			score = tripcount_score_greater_zero;
	
		return score;
		
	}

    public static double calculateDropCardScore(CardAttribute cardAttr,String jokerValue)
    {
    	double score = 0;
    	if(cardAttr.getCard().getDisplayValue().equals(jokerValue) || cardAttr.getCard() instanceof JokerCard)
    	{
    		score = 100; // Because its a joker card never discard
    	}
    	else if(cardAttr.isDuplicate())
    	{
    		score = -20;
    	}
    	else
    	{
    		score = cardAttr.getSeqProximityScore() + cardAttr.getTripProximityScore(); 
    	}
    	return score;
    }
 
    public static double getMinimumScore(ArrayList<CardAttribute> cardAttrList,String jokerValue)
    {
    	double minimumScore = 100;
    	for(CardAttribute cardAttr : cardAttrList)
    	{
    		double currentScore = CardGroupScoreCalculator.calculateDropCardScore(cardAttr,jokerValue);
    		if(currentScore < minimumScore)
    		{
    			minimumScore = currentScore;
    		}
    	}
    	return minimumScore;
    }

    
    public static ProximityResult findSequenceDistance(Card[] listofCards,int currentCardPos)
	{
    	ArrayList<Card> comparedCardsList;
	
		int distance;
		if(listofCards.length == 1)
		{
			comparedCardsList = new ArrayList<Card>();
			comparedCardsList.add(listofCards[0]);
			distance = 1000;
		}
		else if(currentCardPos == listofCards.length - 1) // If its Last Card
		{
			distance = listofCards[currentCardPos].getInstrinsicValue() - listofCards[currentCardPos-1].getInstrinsicValue();
			comparedCardsList = new ArrayList<Card>();
			comparedCardsList.add(listofCards[currentCardPos]);comparedCardsList.add(listofCards[currentCardPos-1]);
			if(listofCards[0].getDisplayValue().equals("A")) // This wont occur
			{
				int distanceA = 14 - listofCards[currentCardPos].getInstrinsicValue();
				if(distanceA < distance)
				{
					comparedCardsList = new ArrayList<Card>();
					comparedCardsList.add(listofCards[currentCardPos]);comparedCardsList.add(listofCards[0]);
					distance = distanceA;
				}
			}
		}
		else if(currentCardPos == 0) // If its First Card
		{
			distance = listofCards[currentCardPos+1].getInstrinsicValue() - listofCards[currentCardPos].getInstrinsicValue();
			comparedCardsList = new ArrayList<Card>();
			comparedCardsList.add(listofCards[currentCardPos+1]);comparedCardsList.add(listofCards[currentCardPos]);
			if(listofCards[currentCardPos].getDisplayValue().equals("A"))
			{
				if(listofCards[listofCards.length-1].getDisplayValue().equals("J") ||
				   listofCards[listofCards.length-1].getDisplayValue().equals("Q") ||
				   listofCards[listofCards.length-1].getDisplayValue().equals("K") ||
				   listofCards[listofCards.length-1].getDisplayValue().equals("10"))
				{
					int distanceA = 14 - listofCards[listofCards.length-1].getInstrinsicValue();
					if(distanceA < distance)
					{
						comparedCardsList = new ArrayList<Card>();
						comparedCardsList.add(listofCards[currentCardPos]);comparedCardsList.add(listofCards[listofCards.length-1]);
						distance = distanceA;
					}
					 
				}
			}
		}
		else
		{
		    int distance1 = listofCards[currentCardPos+1].getInstrinsicValue() - listofCards[currentCardPos].getInstrinsicValue();
		    int distance2 = listofCards[currentCardPos].getInstrinsicValue() - listofCards[currentCardPos-1].getInstrinsicValue();
		    if(distance1<=distance2)
		    {
		    	comparedCardsList = new ArrayList<Card>();
		    	comparedCardsList.add(listofCards[currentCardPos+1]);comparedCardsList.add(listofCards[currentCardPos]);
		    	distance = distance1;
		    }
		    else
		    {
		    	comparedCardsList = new ArrayList<Card>();
		    	comparedCardsList.add(listofCards[currentCardPos-1]);comparedCardsList.add(listofCards[currentCardPos]);
		    	distance = distance2;
		    }
		    
		    if(listofCards[currentCardPos].getDisplayValue().equals("A"))
			{
				if(listofCards[listofCards.length-1].getDisplayValue().equals("J") ||
				   listofCards[listofCards.length-1].getDisplayValue().equals("Q") ||
				   listofCards[listofCards.length-1].getDisplayValue().equals("K") ||
				   listofCards[listofCards.length-1].getDisplayValue().equals("10"))
				{
					int distanceA = 14 - listofCards[listofCards.length-1].getInstrinsicValue();
					if(distanceA < distance)
					{
						comparedCardsList = new ArrayList<Card>();
						comparedCardsList.add(listofCards[currentCardPos]);comparedCardsList.add(listofCards[listofCards.length-1]);
						distance = distanceA;
					}
					 
				}
			}
		    
		}
		if(distance < 0 )
		{
			System.out.println( " Error !!!! This should never happen");
		}
		
		ProximityResult result = new ProximityResult(comparedCardsList, distance);
		
		return result;
	}
}
