package com.cardutility.test;

import java.util.Calendar;

import com.adansoft.great21.models.Card;
import com.adansoft.great21.ulitity.CardUtility;

public class CreateDeckTest {

	public static void main(String[] args) {
		
		System.out.println("Shuffle Start :- " + Calendar.getInstance().getTime());
		Card[] deckcards = CardUtility.shuffleCards(1,true,2);
		System.out.println("Shuffle End :- " + Calendar.getInstance().getTime());
		/*for(int i=0;i<deckcards.length;i++)
    	{
		
    		System.out.println("Deck is : " + deckcards[i].getInstanceID());
    	}*/

	}

}
