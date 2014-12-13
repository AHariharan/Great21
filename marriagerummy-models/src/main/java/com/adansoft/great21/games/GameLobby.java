package com.adansoft.great21.games;

import com.adansoft.great21.models.Game;

// GameLobby's Can be only created by admin's

public class GameLobby {

	private String lobbyName;
	
	private GameList sevencard_gamelist;
	private GameList thirteencard_gamelist;
	private GameList twentyonecard_gamelist;
	

	protected GameLobby(String lobbyName)
	{
		this.lobbyName = lobbyName;
		sevencard_gamelist = new GameList(lobbyName,GameListConstants.GAMELIST_SEVENCARD_TYPE);
		thirteencard_gamelist = new GameList(lobbyName,GameListConstants.GAMELIST_THIRTEENCARD_TYPE);
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
	
	//System Created Game;
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

	
	public void addGameList()
	{
		
	}

	public String getLobbyName() {
		return lobbyName;
	}


	public GameList getSevencard_gamelist() {
		return sevencard_gamelist;
	}


	public GameList getThirteencard_gamelist() {
		return thirteencard_gamelist;
	}


	public GameList getTwentyonecard_gamelist() {
		return twentyonecard_gamelist;
	}
	

	
	
	
	
}
