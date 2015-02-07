package com.cardutility.test;

import com.adansoft.great21.models.Card;
import com.adansoft.great21.models.ClubCard;
import com.adansoft.great21.models.DiamondCard;
import com.adansoft.great21.models.SpadeCard;
import com.adansoft.great21.ulitity.CardUtility;

public class CheckSortCardsTest {

	public static void main(String[] args) {
	
		SpadeCard grp1_card1 = new SpadeCard("2",2, 4, Card.STATUS_ASSIGNED);
		SpadeCard grp1_card2 = new SpadeCard("5",5, 4, Card.STATUS_ASSIGNED);
		SpadeCard grp1_card3 = new SpadeCard("8",8, 4, Card.STATUS_ASSIGNED);
		
		SpadeCard grp2_card1 = new SpadeCard("K",1, 4, Card.STATUS_ASSIGNED);
		DiamondCard grp2_card2 = new DiamondCard("K",10, 4, Card.STATUS_ASSIGNED);
		ClubCard grp2_card3 = new ClubCard("K",10, 4, Card.STATUS_ASSIGNED);
		SpadeCard grp2_card4 = new SpadeCard("9",9, 4, Card.STATUS_ASSIGNED);
		
		DiamondCard jokerCard = new DiamondCard("9", 9 , 2, Card.STATUS_JOKER);
		
		Card[] group1 = new Card[] {grp1_card1,grp1_card2,grp1_card3,grp2_card1,grp2_card2,grp2_card3,grp2_card4,jokerCard};
		
		Card[] group2 = CardUtility.sortCards(group1);
		
		for(int i=0;i<group2.length;i++)
		{
			System.out.println( group2[i].getInstanceID());
		}
		
		
	
		
		

	}

}
