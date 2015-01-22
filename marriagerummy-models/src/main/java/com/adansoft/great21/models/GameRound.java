package com.adansoft.great21.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import com.adansoft.great21.exceptions.GameNotFoundException;
import com.adansoft.great21.games.GameListConstants;
import com.adansoft.great21.games.RummyArena;
import com.adansoft.great21.ulitity.CardUtility;

public class GameRound implements Serializable{

	private static final long serialVersionUID = -2269719594608200339L;
	public final static String STATUS_CREATED = "Created";
	public final static String STATUS_STARTED = "Started";
	public final static String STATUS_INPROGRESS = "Inprogress";
	public final static String STATUS_COMPLETED = "Completed";
	
	private String parentGameId;
	private boolean isPointsEnabled;
	private boolean isMoneyEnabled;
	private float moneyperCard;
	private ArrayList<Player> playerlist;
    private Player currentPlayerTurn;
    private HashMap<String,Integer> pointsMap;
    private String lobbyName;
    private String gameType;
    private int noofdecks;
    private String currentStatus;
    private  Card[] deckcards;
    private int currentindexincard;
    private Card Joker;
    private Card OpenCard;

    public GameRound(String lobbyName,String gameType,String gameid,boolean isPointsEnabled,boolean isMoneyEnabled,float moneypercard,int noofdecks)
    {
    	this.parentGameId = gameid;
    	this.isPointsEnabled = isPointsEnabled;
    	this.isMoneyEnabled = isMoneyEnabled;
    	this.moneyperCard = moneypercard;
    	playerlist = new ArrayList<Player>();
    	this.lobbyName = lobbyName;
    	this.gameType = gameType;
    	this.noofdecks = noofdecks;
    	setCurrentStatus(GameRound.STATUS_CREATED);
    	
    }

    // Add Players to Gameround
    public void addPlayerstoRound(ArrayList<Player> players)
    {
    	for(Player currentplayer : players)
    	{
    		if(currentplayer.getPlayerStatus().equals(Player.PLAYER_STATUS_INGAMEROUND))
    		{
    			this.playerlist.add(currentplayer);
    		}
    	}
    }
    
    
    private Game getCurrentGame() throws GameNotFoundException
    {
    	if(gameType.equals(GameListConstants.GAMELIST_SEVENCARD_CLOSED_TYPE))
    	      return RummyArena.getInstance().getLobby(lobbyName).getSevencard_closed_gamelist().getGame(parentGameId);
    	else if(gameType.equals(GameListConstants.GAMELIST_SEVENCARD_OPEN_TYPE))
  	          return RummyArena.getInstance().getLobby(lobbyName).getSevencard_open_gamelist().getGame(parentGameId);
    	else if(gameType.equals(GameListConstants.GAMELIST_THIRTEENCARD_CLOSED_TYPE))
  	          return RummyArena.getInstance().getLobby(lobbyName).getThirteencard_closed_gamelist().getGame(parentGameId);
    	else if(gameType.equals(GameListConstants.GAMELIST_THIRTEENCARD_OPEN_TYPE))
	          return RummyArena.getInstance().getLobby(lobbyName).getThirteencard_open_gamelist().getGame(parentGameId);  
    	else if(gameType.equals(GameListConstants.GAMELIST_TWENTYONECARD_TYPE))
    		  return RummyArena.getInstance().getLobby(lobbyName).getTwentyonecard_gamelist().getGame(parentGameId);
    	else
    		  throw new GameNotFoundException("NoGame Found with search params, GameType : " + gameType + " , GameInstanceId :" + parentGameId + " ,LobbyName :" +  lobbyName);
  	
    }
    
    // Decide to Eliminate Player
    private void checkEliminationCriteria()
    {
    	try
    	{
    	Game currentgame = getCurrentGame();
    	// Check if points enabled;
    	if(this.isPointsEnabled)
    	{
    		for(Player currentplayer : playerlist)
    		{
    			int totalpoints = currentplayer.getCurrentPoints() + pointsMap.get(currentplayer).intValue();    			
    			if(totalpoints >= currentgame.getMaxPoints())
    			{
    				currentplayer.setPlayerStatus(Player.PLAYER_STATUS_ELIMINATED);
    			}
    		}
    	}
    	}catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    }
    
    
	public String getParentGameId() {
		return parentGameId;
	}

	public void setParentGameId(String parentGameId) {
		this.parentGameId = parentGameId;
	}

	public boolean isPointsEnabled() {
		return isPointsEnabled;
	}

	public void setPointsEnabled(boolean isPointsEnabled) {
		this.isPointsEnabled = isPointsEnabled;
	}

	
	
	public boolean isMoneyEnabled() {
		return isMoneyEnabled;
	}




	public void setMoneyEnabled(boolean isMoneyEnabled) {
		this.isMoneyEnabled = isMoneyEnabled;
	}




	public float getMoneyperCard() {
		return moneyperCard;
	}




	public void setMoneyperCard(float moneyperCard) {
		this.moneyperCard = moneyperCard;
	}




	public ArrayList<Player> getPlayerlist() {
		return playerlist;
	}

	public void setPlayerlist(ArrayList<Player> playerlist) {
		this.playerlist = playerlist;
	}

	public Player getCurrentPlayerTurn() {
		return currentPlayerTurn;
	}

	public void setCurrentPlayerTurn(Player currentPlayerTurn) {
		this.currentPlayerTurn = currentPlayerTurn;
	}

	public HashMap<String, Integer> getPointsMap() {
		return pointsMap;
	}

	public void setPointsMap(HashMap<String, Integer> pointsMap) {
		this.pointsMap = pointsMap;
	}

	public int getNoofdecks() {
		return noofdecks;
	}

	public void setNoofdecks(int noofdecks) {
		this.noofdecks = noofdecks;
	}
    
    public void startRound()
    {
    	boolean opencardset = false;
    	deckcards = CardUtility.shuffleCards(noofdecks);
    	currentindexincard = CardUtility.distributeCards(playerlist, deckcards, 7);
    	currentindexincard++;
    	while(!opencardset)
    	{
    	Card card = deckcards[currentindexincard];
    	if(card.getStatus().equals(Card.STATUS_UNASSIGNED))
    	{
    		 card.setStatus(Card.STATUS_OPEN);
    		 opencardset = true;
    		 OpenCard = card;
    	}
    	currentindexincard++;
    	}
    	Joker =  CardUtility.pickJoker(deckcards, noofdecks);
    	
    	setCurrentStatus(GameRound.STATUS_STARTED);
    	
    	for(Player player : playerlist)
    	{
    		System.out.println("************************************************************************");
    		System.out.println("Player Name :- " + player.getNickName());
    		System.out.println("************************************************************************");
    		player.showCards();
    		System.out.println("************************************************************************");
    	}
    	
    	System.out.println("Joker " + Joker);
    	System.out.println("OpenCard " + OpenCard);
    }
    
    
    public Card getNextCardFromDeck()
    {
    	Card card = deckcards[currentindexincard];
    	card.setStatus(Card.STATUS_ASSIGNED);
    	currentindexincard++;
    	return card;
    }
    
    public Card getJoker()
    {
    	
    	return Joker;
    }
    
    public Card getOpenCard()
    {
    	return OpenCard;
    }

	public String getCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}
    
	
	public void updateDeck(Card droppedcard)
	{
		for(Card card : deckcards)
		{
			if(card.getCardInstanceID().equals(droppedcard.getCardInstanceID()))
					{
				      droppedcard.setStatus(Card.STATUS_DROPPED);
				      card = droppedcard;
					}
		}
	}
    
}
