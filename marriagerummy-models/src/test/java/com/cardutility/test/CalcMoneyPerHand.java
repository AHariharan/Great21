package com.cardutility.test;

import java.util.HashMap;

import com.adansoft.great21.models.Card;
import com.adansoft.great21.models.ClubCard;
import com.adansoft.great21.models.DiamondCard;
import com.adansoft.great21.models.Game;
import com.adansoft.great21.models.SpadeCard;
import com.adansoft.great21.restschemas.ShowGameResult;
import com.adansoft.great21.ulitity.CardUtility;

public class CalcMoneyPerHand {

	public static void main(String[] args) {
		SpadeCard grp1_card1 = new SpadeCard("2",2, 4, Card.STATUS_ASSIGNED);
		SpadeCard grp1_card2 = new SpadeCard("3",3, 4, Card.STATUS_ASSIGNED);
		SpadeCard grp1_card3 = new SpadeCard("4",4, 4, Card.STATUS_ASSIGNED);
		
		SpadeCard grp2_card1 = new SpadeCard("K",1, 4, Card.STATUS_ASSIGNED);
		DiamondCard grp2_card2 = new DiamondCard("K",10, 4, Card.STATUS_ASSIGNED);
		ClubCard grp2_card3 = new ClubCard("K",10, 4, Card.STATUS_ASSIGNED);
		SpadeCard grp2_card4 = new SpadeCard("9",9, 4, Card.STATUS_ASSIGNED);
		
		SpadeCard grp3_card1 = new SpadeCard("J",10, 4, Card.STATUS_ASSIGNED);
		SpadeCard grp3_card2 = new SpadeCard("3",3, 4, Card.STATUS_ASSIGNED);
		SpadeCard grp3_card3 = new SpadeCard("9",9, 4, Card.STATUS_ASSIGNED);
		
		SpadeCard grp4_card1 = new SpadeCard("2",2, 4, Card.STATUS_ASSIGNED);
		SpadeCard grp4_card2 = new SpadeCard("3",3, 4, Card.STATUS_ASSIGNED);
		SpadeCard grp4_card3 = new SpadeCard("4",4, 4, Card.STATUS_ASSIGNED);
		
		DiamondCard jokerCard = new DiamondCard("9", 9 , 2, Card.STATUS_JOKER);
		
		Card[] group1 = new Card[] {grp1_card1,grp1_card2,grp1_card3};
		Card[] group2 = new Card[] {grp2_card1,grp2_card2,grp2_card3,grp2_card4};
		Card[] group3 = new Card[] {grp3_card1,grp3_card2,grp3_card3};
		Card[] group4 = new Card[] {grp4_card1,grp4_card2,grp4_card3};
		
		HashMap<String,Card[]> meldlist = new HashMap<String, Card[]>();
		meldlist.put("Group1", group1);meldlist.put("Group2", group2);
		meldlist.put("Group3", group3);meldlist.put("Group4", group4);
		
		ShowGameResult result = CardUtility.showCards(meldlist, Game.GAME_MODE_PERCARD, 2, jokerCard,80);
		if(result != null)
		{
			System.out.println(" Game Mode : " + result.getGameMode());
			System.out.println(" Game Money : " + result.getMoney());
		}
		
		ShowGameResult resultpoints = CardUtility.showCards(meldlist, Game.GAME_MODE_POINTS, 2, jokerCard,80);
		if(resultpoints != null)
		{
			System.out.println(" Game Mode : " + resultpoints.getGameMode());
			System.out.println(" Game Points : " + resultpoints.getPoints());
		}

	}

}
