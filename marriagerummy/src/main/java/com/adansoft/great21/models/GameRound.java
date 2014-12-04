package com.adansoft.great21.models;

import java.util.ArrayList;
import java.util.HashMap;

import com.adansoft.great21.exceptions.GameNotFoundException;
import com.adansoft.great21.games.GameListConstants;
import com.adansoft.great21.games.RummyArena;

public class GameRound {

	private String parentGameId;
	private boolean isPointsEnabled;
	private boolean isMoneyEnabled;
	private float moneyperCard;
	private ArrayList<Player> playerlist;
    private Player currentPlayerTurn;
    private HashMap<Player,Integer> pointsMap;
    private String lobbyName;
    private String gameType;
    
    
    public GameRound(String lobbyName,String gameType,String gameid,boolean isPointsEnabled,boolean isMoneyEnabled,float moneypercard)
    {
    	this.parentGameId = gameid;
    	this.isPointsEnabled = isPointsEnabled;
    	this.isMoneyEnabled = isMoneyEnabled;
    	this.moneyperCard = moneypercard;
    	playerlist = new ArrayList<Player>();
    	this.lobbyName = lobbyName;
    	this.gameType = gameType;
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
    	if(gameType.equals(GameListConstants.GAMELIST_SEVENCARD_TYPE))
    	      return RummyArena.getInstance().getLobby(lobbyName).getSevencard_gamelist().getGame(parentGameId);
    	else if(gameType.equals(GameListConstants.GAMELIST_THIRTEENCARD_TYPE))
  	          return RummyArena.getInstance().getLobby(lobbyName).getThirteencard_gamelist().getGame(parentGameId);
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

	public HashMap<Player, Integer> getPointsMap() {
		return pointsMap;
	}

	public void setPointsMap(HashMap<Player, Integer> pointsMap) {
		this.pointsMap = pointsMap;
	}
    
    
}
