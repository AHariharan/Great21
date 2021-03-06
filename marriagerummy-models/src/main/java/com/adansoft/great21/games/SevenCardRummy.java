package com.adansoft.great21.games;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import com.adansoft.great21.models.Game;
import com.adansoft.great21.models.GameContentHolder;
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
	private GameContentHolder gameContentHolder;
	private boolean includeJokerinDeck;
	private int numofJokers;
	private int prizemoney;
	
	
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
		this.gameContentHolder = new GameContentHolder(this.gameInstanceId,this.gameType,getCurrentGameMode());
		
	}
	
	
	
	private String getCurrentGameMode()
	{
		if(isGameMoneyBased())
			return Game.GAME_MODE_PERCARD;
		if(isGamePointsBased())
			return Game.GAME_MODE_POINTS;
		else
			return null;
	}
	

	public SevenCardRummy(int noofdecks,boolean jokerAvailable,int numofJokers, int maxplayers,
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
		this.gameContentHolder = new GameContentHolder(this.gameInstanceId,this.gameType,getCurrentGameMode());
		this.includeJokerinDeck = jokerAvailable;
		this.numofJokers = numofJokers;
		
	}

	
	
	private void initAvailablePosition()
	{
		positionAvailabiltyMap = new HashMap<Integer, String>();
		for(int i=1;i<=this.getMaxplayers();i++)
		{
			positionAvailabiltyMap.put(new Integer(i), Game.POSITION_AVAILABLE);
		}
	}
	
	@Override
	@JsonIgnore
    public int getPrizeMoney()
	{
	    return prizemoney;	
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
		
		return getLobbyName();
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
	
	
	@JsonIgnore
	public GameContentHolder getGameContentHolder() {
		return gameContentHolder;
	}

	public void setGameContentHolder(GameContentHolder gameContentHolder) {
		this.gameContentHolder = gameContentHolder;
	}
	
	public int getBuyinValue() {
		return buyinValue;
	}

	public void setBuyinValue(int buyinValue) {
		this.buyinValue = buyinValue;
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

	

	
	private ArrayList<Player> getActivePlayersinGame()
	{
		ArrayList<Player> activePlayerList = new ArrayList<Player>();
		
		for(Player player : getPlayers())
		{
			if(!player.getPlayerStatus().equals(Player.PLAYER_STATUS_ELIMINATED))
			{
				activePlayerList.add(player);
			}
		}
		
		return activePlayerList;
	}
	
	private void createNewGameRound(int startturnpos)
	{
		int whoseturn = startturnpos%getActivePlayersinGame().size();
		if(whoseturn == 0)
			whoseturn = getActivePlayersinGame().size();
			
		GameRound round = new GameRound(lobbyName,gameType,gameInstanceId, gamePointsBased, gameMoneyBased,moneyPerCard,noofdecks,whoseturn,includeJokerinDeck,numofJokers);
		System.out.println("Game Round Created...");
		round.setPlayerlist(getActivePlayersinGame());
		round.initshowStatusMap();
		System.out.println("Game Round starting...");
		round.startRound();
		System.out.println("Game Round completed...");
		currentGameRound = round;
	}
	
	public synchronized void startGame()
	{
		prizemoney = buyinValue * ( getPlayers().size() - 1 );
		if(status.equals(Game.GAME_STATUS_INPROGRESS))
		{
			System.out.println("Launch Game Request suspended for Game instance ID :" + gameInstanceId);
			return;
		}
		status = Game.GAME_STATUS_INPROGRESS;
		createNewGameRound(currentRoundnum);
		
	}

	private void nextRound()
	{
		currentRoundnum++;
		createNewGameRound(currentRoundnum);
	}
	
	private boolean finishRound()
	{
		
		String roundname = "Round : " + this.currentRoundnum;
		if(getCurrentGameMode().equals(Game.GAME_MODE_POINTS))
 	          this.getGameContentHolder().getPlayerPointsMap().put(roundname,this.getCurrentGameRound().getPointsMap());
		if(getCurrentGameMode().equals(Game.GAME_MODE_PERCARD))
			this.getGameContentHolder().getPlayerCashMap().put(roundname, this.getCurrentGameRound().getCashMap());
		
		boolean gameOver = checkEliminationCriteria();
		
		if(!gameOver)
		     nextRound();
		
		return gameOver;
	}
	
	
	
	
	
	private synchronized boolean checkEliminationCriteria()
    {
		boolean gameOver = false;
    	try
    	{
    	Game currentgame = this;
    	// Check if points enabled;
    	if(this.gamePointsBased)
    	{
    		for(Player currentplayer : getPlayers())
    		{
    			int totalpoints =   GameUtility.getTotalPointsforPlayerinGame(currentplayer.getNickName(), this);  			
    			if(totalpoints >= currentgame.getMaxPoints())
    			{
    				currentplayer.setPlayerStatus(Player.PLAYER_STATUS_ELIMINATED);
    				//playerlist.remove(currentplayer);
    				currentplayer.setCurrentCash(currentplayer.getCurrentCash() - this.getBuyinValue());
    				this.getCurrentGameRound().getStatusMap().put(currentplayer.getNickName(), Player.PLAYER_STATUS_ELIMINATED);
    			}
    		}
    		
    		if(getActivePlayersinGame().size() == 1)
    		{
    			System.out.println("******************************************************");
    			System.out.println("************** GAME COMPLETED ************************");
    			System.out.println("******************************************************");
    			for(Player player : getActivePlayersinGame())
    			{
    				player.setPlayerStatus(Player.PLAYER_STATUS_WINGAME);
    				System.out.println("The Winner of the game is : - " + player.getNickName());
    			}
    			gameOver = true;
    		}
    	}
    	if(this.gameMoneyBased)
    	{
    		/*for(Player currentplayer : playerlist)
    		{
    			// To be implemented after DB data was brought inside the player;
    		}*/
    	}
    	}catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    	return gameOver;
    }
    
	
	
	

	@JsonIgnore
	public GameContentHolder getGameContent() {
		return this.getGameContentHolder();		
	}
	
	
	public boolean completeRound()
	{
		return finishRound();
	}

	
	@Override
	@JsonIgnore
	public int getCurrentRoundNum() {
	
		return currentRoundnum;
	}

	@Override
	@JsonIgnore
	public String getRummyType()
	{
		return getGameType();
	}
	
	public void exitGame(Player player)
	{
          this.getCurrentGameRound().addSkipTurn(player);
          playerlist.remove(player);
          if(isGamePointsBased())
               player.setCurrentCash(player.getCurrentCash() - this.getBuyinValue());
          
	}
	
}