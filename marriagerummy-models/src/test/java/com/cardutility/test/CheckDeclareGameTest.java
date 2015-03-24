package com.cardutility.test;

import java.util.HashMap;

import com.adansoft.great21.games.GameListConstants;
import com.adansoft.great21.models.Card;
import com.adansoft.great21.models.ClubCard;
import com.adansoft.great21.models.DiamondCard;
import com.adansoft.great21.models.HeartCard;
import com.adansoft.great21.models.SpadeCard;
import com.adansoft.great21.restschemas.DeclareGameResult;
import com.adansoft.great21.ulitity.CardUtility;

public class CheckDeclareGameTest {

	public static void main(String[] args) {
	
		HeartCard grp1_card1 = new HeartCard("A",1, 4, Card.STATUS_ASSIGNED);
		HeartCard grp1_card2 = new HeartCard("2",2, 4, Card.STATUS_ASSIGNED);
		HeartCard grp1_card3 = new HeartCard("3",3, 4, Card.STATUS_ASSIGNED);
		
		SpadeCard grp2_card1 = new SpadeCard("Q",10, 4, Card.STATUS_ASSIGNED);
		DiamondCard grp2_card2 = new DiamondCard("Q",10, 4, Card.STATUS_ASSIGNED);
		HeartCard grp2_card3 = new HeartCard("Q",10, 4, Card.STATUS_ASSIGNED);
		DiamondCard grp2_card4 = new DiamondCard("A",1, 4, Card.STATUS_ASSIGNED);
		
		ClubCard jokerCard = new ClubCard("A", 1 , 2, Card.STATUS_JOKER);
		
		Card[] group1 = new Card[] {grp1_card1,grp1_card2,grp1_card3};
		Card[] group2 = new Card[] {grp2_card1,grp2_card2,grp2_card3,grp2_card4};
		
		HashMap<String,Card[]> meldlist = new HashMap<String, Card[]>();
		meldlist.put("Group1", group1);
		meldlist.put("Group2", group2);
		
		DeclareGameResult result = CardUtility.checkDeclareGame(meldlist, jokerCard, GameListConstants.GAMELIST_SEVENCARD_CLOSED_TYPE);
		System.out.println("\n\n\n\nOverall result validation status : " + result.isValid());
		System.out.println("Overall result validation Message : " + result.getMessage());
		System.out.println("Overall result Individual Group results :- \n");
		for(String key : result.getGroupresults().keySet())
		{
			System.out.println("Group : " + key + "   , Result : " + result.getGroupresults().get(key));
		}
		
	
		
		

	}

}
