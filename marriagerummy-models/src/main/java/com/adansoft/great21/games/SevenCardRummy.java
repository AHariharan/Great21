package com.adansoft.great21.games;

import java.util.ArrayList;

import com.adansoft.great21.models.Game;
import com.adansoft.great21.models.GameRound;
import com.adansoft.great21.models.GameStrategy;
import com.adansoft.great21.models.Player;
import com.adansoft.great21.ulitity.GameUtility;

public class SevenCardRummy implements Game {
	
	private int noofdecks = 1;
	private int maxplayers = 4;
	private ArrayList<Player> playerlist;
	private int numofrounds;
	private int maxrounds = Game.INDEFINITE_RUMMY_ROUNDS;
	private ArrayList<GameRound> gameroundlist;
	private String gameInstanceId;
	private String gameOwnedBy;
	private boolean isGamePointsBased;
	private int maxpointtoEliminate;
	private boolean isGameMoneyBased;
	private float moneyPerCard;
	private String lobbyName;
	private String gameType;
	private String gameName;
	
	public SevenCardRummy(String gameName,String createdBy,String lobbyName,String gametype)
	{
		gameInstanceId = GameUtility.generateGameInstanceID();
		gameOwnedBy = createdBy;
		this.lobbyName = lobbyName;
		this.gameType = gametype;
		this.gameName = gameName;
	
	}
	
	public SevenCardRummy(String gameName,String lobbyName,String nickname,boolean isPointsBased,int maxPoints,boolean isGameMoneyBased,float perCardMoneyValue,int maxnoofPlayers,int maxrounds)
	{
		this.gameName = gameName;
		this.lobbyName = lobbyName;
		this.gameInstanceId = GameUtility.generateGameInstanceID();
		this.gameOwnedBy = nickname;
	    this.isGamePointsBased = isPointsBased;
	    this.maxpointtoEliminate = maxPoints;
	    this.isGameMoneyBased = isGameMoneyBased;
	    this.moneyPerCard = perCardMoneyValue;
	    this.maxplayers = maxnoofPlayers;
	    this.maxrounds = maxrounds;
	    
	    
	}

	public String getGameInstanceId() {
		return this.gameInstanceId;
	}

	public String getGameOwnedBy() {
		return this.gameOwnedBy;
	}

	public int getMaxPlayers() {
		return this.maxplayers;
	}

	public ArrayList<Player> getPlayerList() {
		
		return this.playerlist;
	}


	public int getMaxRounds() {
		
		return this.maxrounds;
	}

	public GameRound getCurrentGameRound() {
		return null;
	}

	public boolean isGamePointsBased() {		
		return isGamePointsBased;
	}

	public int getMaxPoints() {
		return maxpointtoEliminate;
	}

	public boolean isGameCardMoneyBased() {
		return isGameMoneyBased;
	}

	public float getPerCardMoneyValue() {
		return moneyPerCard;
	}
	
		
	public String getLobbyName() {
		return lobbyName;
	}

	public void setLobbyName(String lobbyName) {
		this.lobbyName = lobbyName;
	}

	private void createNewGameRound()
	{
		GameRound round = new GameRound(lobbyName,gameType,gameInstanceId, isGamePointsBased , isGameMoneyBased,moneyPerCard);	
		
	}

	public String getGameLobbyName() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getGameType() {
		return gameType;
	}

	public void setGameType(String gameType) {
		this.gameType = gameType;
	}

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}
	
	
	
	
	
}