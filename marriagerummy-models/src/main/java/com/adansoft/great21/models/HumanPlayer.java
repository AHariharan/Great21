package com.adansoft.great21.models;

import java.util.ArrayList;
import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonTypeName;



@JsonTypeName("HumanPlayer")
public class HumanPlayer implements Player{

	private ArrayList<Card<?>> cards;
	private boolean isTurn,isJokerKnown;
	private String nickName;
	private static final String player_type = Player.PLAYER_TYPE_HUMAN;
	private String status;
	

	public HumanPlayer(String nickname)
	{
		this.nickName = nickname;
		status = PLAYER_STATUS_INGAMEROUND;
	}

	

	public boolean isJokerKnown() {
			return isJokerKnown;
	}


	public void showCards() {
		for(int i=0;i<cards.size();i++)
		{			
			System.out.println("Deck Id :-" + cards.get(i).getDecKID() + "-" + cards.get(i).getFlower() + "-" + cards.get(i).getDisplayValue());
		}
		
	}

	public String getNickName() {
		return nickName;
	}


	public void autoArrangeCards() {
		if(cards.size() == 0 )
			return;
		
		ArrayList<Card<?>> spadeCardList = new ArrayList<Card<?>>();
		ArrayList<Card<?>> diamondCardList = new ArrayList<Card<?>>();
		ArrayList<Card<?>> clubCardList = new ArrayList<Card<?>>();
		ArrayList<Card<?>> heartCardList = new ArrayList<Card<?>>();
		
		for(Card<?> card : cards)
		{
			if(card.getFlower().equals(Card.FLOWER_CLUBS))
				clubCardList.add(card);
			if(card.getFlower().equals(Card.FLOWER_DIAMOND))
				diamondCardList.add(card);
			if(card.getFlower().equals(Card.FLOWER_HEART))
				heartCardList.add(card);
			if(card.getFlower().equals(Card.FLOWER_SPADE))
				spadeCardList.add(card);
		}
		
		Object[] clubcards =  clubCardList.toArray();
		Object[] diamondcards =  diamondCardList.toArray();
		Object[] heartcards =  heartCardList.toArray();
		Object[] spadecards =  spadeCardList.toArray();
		
		Arrays.sort(clubcards);
		
		Arrays.sort(diamondcards);Arrays.sort(heartcards);Arrays.sort(spadecards);
		cards = new ArrayList<Card<?>>();
		addToList(cards,clubcards);addToList(cards,diamondcards);addToList(cards,heartcards);addToList(cards,spadecards);
	}
	
	private void addToList(ArrayList<Card<?>> sortedcardlist, Object[] list)
	{
		for(int i=0;i<list.length;i++)
		{
			sortedcardlist.add((Card<?>)list[i]);
		}
	}

	
	public ArrayList<Card<?>> getCards() {
		return cards;
	}

	

	public boolean isTurn() {
		return isTurn;
	}

	public void setTurn(boolean isTurn) {
		this.isTurn = isTurn;
	}

	public void setJokerKnown(boolean isJokerKnown) {
		this.isJokerKnown = isJokerKnown;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	

	public String getPlayerType() {
		return player_type;
	}

	public void playTurn() {
		// TODO Auto-generated method stub
		
	}


	public void assignCard(Card<?> card) {
		// TODO Auto-generated method stub
		
	}



	public int getCurrentPoints() {
		// TODO Auto-generated method stub
		return 0;
	}



	public String getPlayerStatus() {
		// TODO Auto-generated method stub
		return null;
	}



	public String getPlayerRoundStatus() {
		// TODO Auto-generated method stub
		return this.status;
	}



	public void setPlayerStatus(String Status) {
		this.status =status;
		
	}





}
