package com.adansoft.great21.games;

import java.io.Serializable;

import com.adansoft.great21.models.Game;

// GameLobby's Can be only created by admin's

public class GameLobby implements Serializable{


	private static final long serialVersionUID = -678712022637481004L;

	private String lobbyName;
	
	private GameList sevencard_closed_gamelist;
	private GameList sevencard_open_gamelist;
	private GameList thirteencard_closed_gamelist;
	private GameList thirteencard_open_gamelist;
	private GameList twentyonecard_gamelist;
	

	protected GameLobby(String lobbyName)
	{
		this.lobbyName = lobbyName;
		sevencard_closed_gamelist = new GameList(lobbyName,GameListConstants.GAMELIST_SEVENCARD_CLOSED_TYPE);
		sevencard_open_gamelist = new GameList(lobbyName,GameListConstants.GAMELIST_SEVENCARD_OPEN_TYPE);
		thirteencard_closed_gamelist = new GameList(lobbyName,GameListConstants.GAMELIST_THIRTEENCARD_CLOSED_TYPE);
		thirteencard_open_gamelist = new GameList(lobbyName,GameListConstants.GAMELIST_THIRTEENCARD_OPEN_TYPE);
		twentyonecard_gamelist = new GameList(lobbyName,GameListConstants.GAMELIST_TWENTYONECARD_TYPE);
	}
	
	
	public static GameLobby createGameLobby(String lobbyName)
	{
		GameLobby lobby = RummyArena.getInstance().getLobby(lobbyName);
		if(lobby == null)
		{	
		   GameLobby newlobby =   new GameLobby(lobbyName);
		   RummyArena.getInstance().getGameLobbyList().add(newlobby);
		   return newlobby;
		}
		else
			return lobby;
	}
	
	public static GameLobby createLocalGameLobby(String lobbyName)
	{
		GameLobby lobby = new GameLobby(lobbyName);
		return lobby;
	}
	
/*	//System Created Game;
	public Game createGame(String gameName,String gameType)
	{
		Game game = null;
		if(gameType.equals(GameListConstants.GAMELIST_SEVENCARD_TYPE))
		{
			game = new SevenCardRummy(gameName,"SYSTEM",lobbyName, gameType);
			this.getSevencard_gamelist().addGame(game);
		}

		return game;
		// implement rest later;
	}
	
	// Override for UserCreatedgame;	
	public Game createGame(String gameName,String createdBy,String gameType)
	{
		Game game = null;
		if(gameType.equals(GameListConstants.GAMELIST_SEVENCARD_TYPE))
		{
			game = new SevenCardRummy(gameName,createdBy,lobbyName, gameType);
			this.getSevencard_gamelist().addGame(game);
		}
		
		return game;
		// implement rest later;
	}
*/
	
	public void addGameList()
	{
		
	}

	public String getLobbyName() {
		return lobbyName;
	}


	
	public GameList getTwentyonecard_gamelist() {
		return twentyonecard_gamelist;
	}


	public GameList getSevencard_closed_gamelist() {
		return sevencard_closed_gamelist;
	}


	public void setSevencard_closed_gamelist(GameList sevencard_closed_gamelist) {
		this.sevencard_closed_gamelist = sevencard_closed_gamelist;
	}


	public GameList getSevencard_open_gamelist() {
		return sevencard_open_gamelist;
	}


	public void setSevencard_open_gamelist(GameList sevencard_open_gamelist) {
		this.sevencard_open_gamelist = sevencard_open_gamelist;
	}


	public GameList getThirteencard_closed_gamelist() {
		return thirteencard_closed_gamelist;
	}


	public void setThirteencard_closed_gamelist(
			GameList thirteencard_closed_gamelist) {
		this.thirteencard_closed_gamelist = thirteencard_closed_gamelist;
	}


	public GameList getThirteencard_open_gamelist() {
		return thirteencard_open_gamelist;
	}


	public void setThirteencard_open_gamelist(GameList thirteencard_open_gamelist) {
		this.thirteencard_open_gamelist = thirteencard_open_gamelist;
	}


	public void setTwentyonecard_gamelist(GameList twentyonecard_gamelist) {
		this.twentyonecard_gamelist = twentyonecard_gamelist;
	}
	
	
	
    public void addGame(Game game,String gameType)
    {
    	if(gameType.equals(GameListConstants.GAMELIST_SEVENCARD_CLOSED_TYPE) && game instanceof SevenCardRummy)
    		this.getSevencard_closed_gamelist().addGame(game);
    	if(gameType.equals(GameListConstants.GAMELIST_SEVENCARD_OPEN_TYPE) && game instanceof SevenCardRummy)
    		this.getSevencard_open_gamelist().addGame(game);
    	/*if(gameType.equals(GameListConstants.GAMELIST_SEVENCARD_CLOSED_TYPE) && game instanceof )
    		this.getSevencard_closed_gamelist().addGame(game);
    	if(gameType.equals(GameListConstants.GAMELIST_SEVENCARD_CLOSED_TYPE) && game instanceof SevenCardRummy)
    		this.getSevencard_closed_gamelist().addGame(game);*/
    }
	
	
	
	
}
