package com.cardutility.test;

import com.adansoft.great21.models.Card;
import com.adansoft.great21.ulitity.CardUtility;

public class CreateDeckTest {

	public static void main(String[] args) {
		
		Card[] deckcards = CardUtility.shuffleCards(1,true,2);
		for(int i=0;i<deckcards.length;i++)
    	{
		
    		System.out.println("Deck is : " + deckcards[i].getInstanceID());
    	}

	}

}
