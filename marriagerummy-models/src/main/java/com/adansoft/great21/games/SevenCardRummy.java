package com.adansoft.great21.games;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import com.adansoft.great21.models.Game;
import com.adansoft.great21.models.GameRound;
import com.adansoft.great21.models.HumanPlayer;
import com.adansoft.great21.models.Player;
import com.adansoft.great21.ulitity.GameUtility;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeName;



@JsonTypeName("SevenCardRummy")
@JsonIgnoreProperties(ignoreUnknown = true)
public class SevenCardRummy implements Game,Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1657836579007562512L;
	private int noofdecks = 3;
	private int maxplayers = 4;
	private ArrayList<Player> playerlist;
	private int numofrounds;
	private int maxrounds = Game.INDEFINITE_RUMMY_ROUNDS;
	private ArrayList<GameRound> gameroundlist;
	private String gameInstanceId;
	private String gameOwnedBy;
	private boolean gamePointsBased;
	private int maxpointtoEliminate;
	private boolean gameMoneyBased;
	private float moneyPerCard;
	private String lobbyName;
	private String gameType;
	private String gameName;
	private String status;
	private HashMap<Integer, String> positionAvailabiltyMap;
	private int buyinValue;
	private GameRound currentGameRound;
	private int currentRoundnum = 1;
	
	
	public SevenCardRummy()
	{
		super();
		initAvailablePosition();
	}
	
	public SevenCardRummy(String gameName,String createdBy,String lobbyName,String gametype)
	{
		gameInstanceId = GameUtility.generateGameInstanceID();
		gameOwnedBy = createdBy;
		this.lobbyName = lobbyName;
		this.gameType = gametype;
		this.gameName = gameName;
		initAvailablePosition();
	}
	
	

	public SevenCardRummy(int noofdecks, int maxplayers,
			 int numofrounds, int maxrounds,
			String gameOwnedBy, boolean isGamePointsBased,
			int maxpointtoEliminate, boolean isGameMoneyBased,
			float moneyPerCard, String lobbyName, String gameType,
			String gameName,int buyinValue) {
		super();
		this.gameInstanceId = GameUtility.generateGameInstanceID();
		this.gameroundlist = new ArrayList<GameRound>();
		this.playerlist = new ArrayList<Player>();
		this.noofdecks = noofdecks;
		this.maxplayers = maxplayers;
		this.numofrounds = numofrounds;
		this.maxrounds = maxrounds;
		this.gameOwnedBy = gameOwnedBy;
		this.gamePointsBased = isGamePointsBased;
		this.maxpointtoEliminate = maxpointtoEliminate;
		this.gameMoneyBased = isGameMoneyBased;
		this.moneyPerCard = moneyPerCard;
		this.lobbyName = lobbyName;
		this.gameType = gameType;
		this.gameName = gameName;
		this.buyinValue = buyinValue;
		this.status = Game.GAME_STATUS_OPEN;
		initAvailablePosition();
	}

	
	
	private void initAvailablePosition()
	{
		positionAvailabiltyMap = new HashMap<Integer, String>();
		for(int i=1;i<=this.getMaxplayers();i++)
		{
			positionAvailabiltyMap.put(new Integer(i), Game.POSITION_AVAILABLE);
		}
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

	public int getMaxRounds() {
		
		return this.maxrounds;
	}

	@JsonIgnore
	public GameRound getCurrentGameRound() {
		return currentGameRound;
	}

	

	public int getMaxPoints() {
		return maxpointtoEliminate;
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

	private void createNewGameRound(int startturnpos)
	{
		GameRound round = new GameRound(lobbyName,gameType,gameInstanceId, gamePointsBased, gameMoneyBased,moneyPerCard,noofdecks,startturnpos);	
		round.setPlayerlist(getPlayers());
		round.startRound();
		currentGameRound = round;
	}
	
	public void startGame()
	{
		createNewGameRound(currentRoundnum);
	}

	public void nextRound()
	{
		currentRoundnum++;
		createNewGameRound(currentRoundnum);
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	@JsonGetter
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


	
	
	
	public boolean isGamePointsBased() {
		return gamePointsBased;
	}

	public void setGamePointsBased(boolean gamePointsBased) {
		this.gamePointsBased = gamePointsBased;
	}

	public boolean isGameMoneyBased() {
		return gameMoneyBased;
	}

	public void setGameMoneyBased(boolean gameMoneyBased) {
		this.gameMoneyBased = gameMoneyBased;
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
	
	public String getOwner()
	{
		return this.gameOwnedBy;
	}

	@JsonIgnore
	public ArrayList<Player> getPlayers() {		
		return getPlayerlist();
	}

	@JsonIgnore
	public String getDescription() {
		return this.gameName;
	}
 
	@JsonIgnore
	public boolean isGameCardMoneyBased() {
		return gameMoneyBased;
	}

	@JsonIgnore
	public String getGameStatus() {
		return this.status;
	}

	@JsonIgnore
	public int getNextAvailablePlayerPosition() {
		for(Integer key : positionAvailabiltyMap.keySet())
		{
			if(positionAvailabiltyMap.get(key).equals(Game.POSITION_AVAILABLE))
				return key.intValue();
		}
		return 0;
	}

	public void addPlayertoGame(Player player) {
		int nexpost = getNextAvailablePlayerPosition();
		if(player instanceof HumanPlayer)
		{
			HumanPlayer HPlayer = (HumanPlayer)player;
			HPlayer.setInstanceID(GameUtility.generatePlayerInstanceID());
			HPlayer.setPlayerpos(nexpost);
			this.getPlayerlist().add(HPlayer);
			positionAvailabiltyMap.put(new Integer(nexpost),Game.POSITION_NOTAVAILABLE);
		}	
	}
	
	public void removePlayerFromGame(Player player) {
		int position = 0;
		if(player instanceof HumanPlayer)
		{
			HumanPlayer HPlayer = (HumanPlayer) player;
			position = HPlayer.getPlayerpos();
			positionAvailabiltyMap.put(new Integer(position),Game.POSITION_AVAILABLE);
			this.getPlayerlist().remove(HPlayer);
		}
	}

	public int getBuyinValue() {
		return buyinValue;
	}

	public void setBuyinValue(int buyinValue) {
		this.buyinValue = buyinValue;
	}
	
	
	
	
}