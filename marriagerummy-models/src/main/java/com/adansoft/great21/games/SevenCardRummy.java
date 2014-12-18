package com.adansoft.great21.games;

import java.io.Serializable;
import java.util.ArrayList;

import com.adansoft.great21.models.Game;
import com.adansoft.great21.models.GameRound;
import com.adansoft.great21.models.Player;
import com.adansoft.great21.ulitity.GameUtility;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("SevenCardRummy")
@JsonIgnoreProperties(ignoreUnknown = true)
public class SevenCardRummy implements Game,Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1657836579007562512L;
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
	
	public SevenCardRummy()
	{
		
	}
	
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

	/*public String getGameLobbyName() {
		// TODO Auto-generated method stub
		return null;
	}*/

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

	public int getNoofdecks() {
		return noofdecks;
	}

	public void setNoofdecks(int noofdecks) {
		this.noofdecks = noofdecks;
	}

	public int getMaxplayers() {
		return maxplayers;
	}

	public void setMaxplayers(int maxplayers) {
		this.maxplayers = maxplayers;
	}

	public ArrayList<Player> getPlayerlist() {
		return playerlist;
	}

	public void setPlayerlist(ArrayList<Player> playerlist) {
		this.playerlist = playerlist;
	}

	public int getNumofrounds() {
		return numofrounds;
	}

	public void setNumofrounds(int numofrounds) {
		this.numofrounds = numofrounds;
	}

	public int getMaxrounds() {
		return maxrounds;
	}

	public void setMaxrounds(int maxrounds) {
		this.maxrounds = maxrounds;
	}

	public ArrayList<GameRound> getGameroundlist() {
		return gameroundlist;
	}

	public void setGameroundlist(ArrayList<GameRound> gameroundlist) {
		this.gameroundlist = gameroundlist;
	}

	public int getMaxpointtoEliminate() {
		return maxpointtoEliminate;
	}

	public void setMaxpointtoEliminate(int maxpointtoEliminate) {
		this.maxpointtoEliminate = maxpointtoEliminate;
	}

	public boolean isGameMoneyBased() {
		return isGameMoneyBased;
	}

	public void setGameMoneyBased(boolean isGameMoneyBased) {
		this.isGameMoneyBased = isGameMoneyBased;
	}

	public float getMoneyPerCard() {
		return moneyPerCard;
	}

	public void setMoneyPerCard(float moneyPerCard) {
		this.moneyPerCard = moneyPerCard;
	}

	public void setGameInstanceId(String gameInstanceId) {
		this.gameInstanceId = gameInstanceId;
	}

	public void setGameOwnedBy(String gameOwnedBy) {
		this.gameOwnedBy = gameOwnedBy;
	}

	public void setGamePointsBased(boolean isGamePointsBased) {
		this.isGamePointsBased = isGamePointsBased;
	}
	
	@Override
	public String toString() {
	
		String content = "Game Instance ID : " + getGameInstanceId() + "\n" +
				         "noofdecks : " + getNoofdecks() + "\n" +
				         "maxplayers : " + getMaxplayers() + "\n"+
				         "playerlist :" + getPlayerlist() + "\n" +
				         "numofrounds : " + getNumofrounds() + "\n" +
				         "maxrounds : " + getMaxrounds() + "\n" +
				         "gameroundlist : " + getGameroundlist() + "\n" +
				         "gameOwnedBy :" + getGameOwnedBy() + "\n"+
				         "isGamePointsBased : " + isGameCardMoneyBased() + "\n" +
				         "moneyPerCard : " + getMoneyPerCard() + "\n" +
				         "lobbyName : " + getLobbyName() + "\n" +
				         "gameType : " + getGameType() + "\n" +
				         "gameName : " + getGameName();
 		return content; 
	}

	public String getGameLobbyName() {
		
		return null;
	}
	
	
	
}