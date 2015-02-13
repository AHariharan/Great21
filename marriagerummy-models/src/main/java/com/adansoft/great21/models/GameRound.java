package com.adansoft.great21.models;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import com.adansoft.great21.exceptions.GameNotFoundException;
import com.adansoft.great21.games.GameListConstants;
import com.adansoft.great21.games.RummyArena;
import com.adansoft.great21.uischemas.UICard;
import com.adansoft.great21.ulitity.CardUtility;

public class GameRound implements Serializable{

	private static final long serialVersionUID = -2269719594608200339L;
	public final static String STATUS_CREATED = "Created";
	public final static String STATUS_STARTED = "Started";
	public final static String STATUS_INPROGRESS = "Inprogress";
	public final static String STATUS_COMPLETED = "Completed";
	
	public final static String PLAYER_STATUS_DECLARED = "Declared";
	public final static String PLAYER_STATUS_INITDROPPED = "InitDropped";
	public final static String PLAYER_STATUS_HALFDROPPED = "HalfDropped";
	public final static String PLAYER_STATUS_SHOWNCARDS = "Shown cards";
	public final static String PLAYER_STATUS_PLAYING = "Playing";
	
	private String parentGameId;
	private boolean isPointsEnabled;
	private boolean isMoneyEnabled;
	private float moneyperCard;
	private ArrayList<Player> playerlist;
    private Player currentPlayerTurn;
    private HashMap<String,Integer> pointsMap;
    private HashMap<String,Float> cashMap;
    private HashMap<String,String> showstatusMap;
    private String lobbyName;
    private String gameType;
    private int noofdecks;
    private String currentStatus;
    private  Card[] deckcards;
    private int currentindexincard;
    private Card Joker;
    private Card OpenCard;
    private int startTurnpos;
    private int currenturn;
    private ArrayList<Integer> skipturnarray = new ArrayList<Integer>();
    private Card prevDroppedCard;

    public GameRound(String lobbyName,String gameType,String gameid,boolean isPointsEnabled,boolean isMoneyEnabled,float moneypercard,int noofdecks,int startturn)
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
    	this.startTurnpos = startturn;
    	this.currenturn = this.startTurnpos;
    	pointsMap = new HashMap<String, Integer>();
    	cashMap = new HashMap<String, Float>();
    	showstatusMap = new HashMap<String, String>();
    }

    
    private void initshowStatusMap()
    {
    	for(Player player : this.getPlayerlist())
    	{
    		showstatusMap.put(player.getNickName(), PLAYER_STATUS_PLAYING);
    	}
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
	
	public HashMap<String, Float> getCashMap() {
		return cashMap;
	}

	public void setCashMap(HashMap<String, Float> cashMap) {
		this.cashMap = cashMap;
	}
	
	public HashMap<String, String> getStatusMap() {
		return showstatusMap;
	}

	public void setShowStatusMap(HashMap<String, String> showstatusMap) {
		this.showstatusMap = showstatusMap;
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
    	/* adding debug ...*/
    	
    	for(int i=0;i<deckcards.length;i++)
    	{
    		System.out.println("Deck is : " + deckcards[i].getInstanceID());
    	}
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
    	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    	Calendar cal = Calendar.getInstance();
    	System.out.println("Incoming request next card .. " + dateFormat.format(cal.getTime())); 
    	Card card = null;
        boolean isCardfromDeckSet = false;
        while(!isCardfromDeckSet)
        {
        	System.out.println("Current Index : " + currentindexincard);
        	card = deckcards[currentindexincard];
    	    if(card.getStatus().equals(Card.STATUS_UNASSIGNED))
    	     {
    	      if(currentindexincard >= this.noofdecks*52 - 1)
    	      {
    	    	  for(int i=0;i<this.deckcards.length;i++)
    	    	  {
    	    		  if(deckcards[i].getStatus().equals(Card.STATUS_DEAD))
    	    			  deckcards[i].setStatus(Card.STATUS_UNASSIGNED);
    	    	  }
    	    	  // Reshuffle and resetting the index on picking all cards.
    	    	  currentindexincard = 0;
    	      }
    	      isCardfromDeckSet = true;
    	      card.setStatus(Card.STATUS_PICKED);
    	      
    	     }
    	    currentindexincard++;
        }
        System.out.println("Next Card Request Came : " + card);
        Card precard = getPrevDroppedCard();
        if(precard != null)
        {
            if(precard.getStatus().equals(Card.STATUS_DROPPED))
        	    precard.setStatus(Card.STATUS_DEAD);
        }
       
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
			if(card.getInstanceID().equals(droppedcard.getInstanceID()))
					{
				      droppedcard.setStatus(Card.STATUS_DROPPED);
				      card = droppedcard;
					}
		}
	}
	
	
    public String addCardToHand(UICard inputcard,String nickname)
    {
    	String result = "Failure";
    	for(Card card : deckcards)
		{
    		if(card.getInstanceID().equals(inputcard.getCardInstanceID()))
    		{
    			for(Player player : getPlayerlist())
    			{
    				if(player.getNickName().equals(nickname))
    				{
    					card.setStatus(Card.STATUS_ASSIGNED);
    					player.getPlayerCards().add(card);
    					result = "Success";    					
    					System.out.println("************************************************************************");
    		    		System.out.println("Player Name :- " + player.getNickName());
    		    		System.out.println("************************************************************************");
    		    		player.showCards();
    		    		System.out.println("************************************************************************");
    				}
    			}
    		}
		}
    	return result;
    }
    
    public String dropCardFromHand(UICard inputcard,String nickname)
    {
    	String result = "Failure";
    	for(Card card : deckcards)
		{
    		if(card.getInstanceID().equals(inputcard.getCardInstanceID()))
    		{
    			for(Player player : getPlayerlist())
    			{
    				if(player.getNickName().equals(nickname))
    				{    					
    					player.getPlayerCards().remove(card);
    					card.setStatus(Card.STATUS_DROPPED);
    					setPrevDroppedCard(card);
    					result = "Success";    		
    					System.out.println("************************************************************************");
    		    		System.out.println("Player Name :- " + player.getNickName());
    		    		System.out.println("************************************************************************");
    		    		player.showCards();
    		    		System.out.println("************************************************************************");
    				}
    			}
    			if(result != "Success")
    			{
    				if(card.getStatus().equals(Card.STATUS_DROPPED))
    				{
    				   card.setStatus(Card.STATUS_DROPPED);
    				   setPrevDroppedCard(card);
				 	  result = "Success";
    				}
    			}
    		}
		}
    	updateTurn();
    	return result;
    }
    
    private void updateTurn()
    {
    	currenturn++;
    	if(currenturn > this.getPlayerlist().size())
    		currenturn = 1;
    	
    	while(isPosistionSkippable(currenturn))
    	{
    		currenturn++;
    		if(currenturn > this.getPlayerlist().size())
        		currenturn = 1;
    	}
    	
    }

	public int getCurrenturn() {
		return currenturn;
	}

	public void setCurrenturn(int currenturn) {
		this.currenturn = currenturn;
	}
    
    public void addSkipTurn(int position)
    {
    	System.out.println("Adding skip turn : " + position);
    	skipturnarray.add(position);
    	updateTurn();
    }
    
    private boolean isPosistionSkippable(int posistion)
    {
    	boolean result = false;
    	for(Integer curpos : skipturnarray)
    	{
    		if(curpos.intValue() == posistion)
    			result = true;
    	}
    	
    	return result;
    }

	public Card getPrevDroppedCard() {
		return prevDroppedCard;
	}

	public void setPrevDroppedCard(Card prevDroppedCard) {
		this.prevDroppedCard = prevDroppedCard;
	}
    

    public void addPointsToPlayer(String nickname,int points,String status)
    {
    	pointsMap.put(nickname, points);
    	showstatusMap.put(nickname, status);
    }
    
    public void deductCashFromPlayer(String nickname,Float money,String status)
    {
    	cashMap.put(nickname, money);
    	showstatusMap.put(nickname, status);
    }
    
}
