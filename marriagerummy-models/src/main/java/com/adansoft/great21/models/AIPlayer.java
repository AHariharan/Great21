package com.adansoft.great21.models;

import java.util.ArrayList;
import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("AIPlayer")
@JsonIgnoreProperties(ignoreUnknown = true)
public class AIPlayer implements Player {

private static final long serialVersionUID = -83813341695789602L;
	
	private String instanceID;
	private ArrayList<Card> cards;
	private boolean isTurn,isJokerKnown;
	private String nickName;
	private static final String player_type = Player.PLAYER_TYPE_AI;
	private String status;
    private int playerpos;
    private String playerrole;
    private double cashInHand;
    private long myuserid;
    private GameStrategy botStrategy;
	

	public AIPlayer(String nickname,int playerpos,GameStrategy gameStrategy)
	{
		this.nickName = nickname;
		this.playerpos = playerpos;
		status = PLAYER_STATUS_INGAME;
		botStrategy = gameStrategy;
		cards = new ArrayList<Card>();
	}

	
	public AIPlayer()
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
	
	@JsonIgnore
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

	@JsonIgnore
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
		return status;
	}



	public String getPlayerRoundStatus() {
		// TODO Auto-generated method stub
		return this.status;
	}



	public void setPlayerStatus(String status) {
		this.status = status;
		
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
   
	/* Game Play Code */
	
	public void turnPlayed(Card pickedupcard,Card droppedCard)
	{
		if(pickedupcard.getInstanceID().equals(droppedCard.getInstanceID()))
		{
			return;
		}		
		droppedCard.setStatus(Card.STATUS_DROPPED);
		for(Card card : this.getCards())
		{
			if(card.getInstanceID().equals(droppedCard.getInstanceID()))
			{
				card = pickedupcard;
			}
		}
	}


	public void resetCards() {
		System.out.println("Reset Cards Invoked ... ");
		cards = new ArrayList<Card>();		
	}


	@Override
	@JsonIgnore
	public double getCurrentCash() {
		return cashInHand;
	}

	@JsonIgnore
	public double getCashInHand() {
		return cashInHand;
	}


	public void setCashInHand(double cashInHand) {
		this.cashInHand = cashInHand;
	}
	
	@Override
	@JsonIgnore
	public long getUserID()
	{
		return myuserid;
	}

	@JsonIgnore
	public long getMyuserid() {
		return myuserid;
	}

	@JsonIgnore
	public void setMyuserid(long myuserid) {
		this.myuserid = myuserid;
	}
	
	@JsonIgnore
	public void setCurrentCash(double money) {
		 setCashInHand(money);
	}

}
