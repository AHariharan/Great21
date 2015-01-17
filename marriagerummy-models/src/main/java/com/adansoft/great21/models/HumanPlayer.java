package com.adansoft.great21.models;

import java.util.ArrayList;
import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeName;



@JsonTypeName("HumanPlayer")
@JsonIgnoreProperties(ignoreUnknown = true)
public class HumanPlayer implements Player{


	private static final long serialVersionUID = -83813341695789602L;
	
	private String instanceID;
	private ArrayList<Card> cards;
	private boolean isTurn,isJokerKnown;
	private String nickName;
	private static final String player_type = Player.PLAYER_TYPE_HUMAN;
	private String status;
    private int playerpos;
    private String playerrole;
	

	public HumanPlayer(String nickname,int playerpos)
	{
		this.nickName = nickname;
		this.playerpos = playerpos;
		status = PLAYER_STATUS_INGAME;
		cards = new ArrayList<Card>();
	}

	
	public HumanPlayer()
	{
		status = PLAYER_STATUS_INGAME;
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
	
	public ArrayList<Card> getPlayerCards()
	{
		return cards;
	}

	public String getNickName() {
		return nickName;
	}


	public void autoArrangeCards() {
		if(cards.size() == 0 )
			return;
		
		ArrayList<Card> spadeCardList = new ArrayList<Card>();
		ArrayList<Card> diamondCardList = new ArrayList<Card>();
		ArrayList<Card> clubCardList = new ArrayList<Card>();
		ArrayList<Card> heartCardList = new ArrayList<Card>();
		
		for(Card card : cards)
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
		cards = new ArrayList<Card>();
		addToList(cards,clubcards);addToList(cards,diamondcards);addToList(cards,heartcards);addToList(cards,spadecards);
	}
	
	private void addToList(ArrayList<Card> sortedcardlist, Object[] list)
	{
		for(int i=0;i<list.length;i++)
		{
			sortedcardlist.add((Card)list[i]);
		}
	}

	
	public ArrayList<Card> getCards() {
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


	public void assignCard(Card card) {
	cards.add(card);		
	}

	public void wipeoutCards()
	{
		cards = new ArrayList<Card>();
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


	public int getPlayerpos() {
		return playerpos;
	}


	public void setPlayerpos(int playerpos) {
		this.playerpos = playerpos;
	}


	public String getPlayerrole() {
		return playerrole;
	}


	public void setPlayerrole(String playerrole) {
		this.playerrole = playerrole;
	}


	
	public int getPlayerPosition() {		
		return getPlayerpos();
	}


	public boolean isHost() {		
		return getPlayerrole().equals(Player.PLAYER_ROLE_HOST);		
	}


	public String getInstanceID() {
		return instanceID;
	}


	public void setInstanceID(String instanceID) {
		this.instanceID = instanceID;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public void setCards(ArrayList<Card> cards) {
		this.cards = cards;
	}

	public String getPlayerInstanceID()
	{
		return this.instanceID;
	}
   
	
	

}
