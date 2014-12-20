package com.adansoft.great21.helpers;


import com.adansoft.great21.games.GameListConstants;
import com.adansoft.great21.games.GameLobby;
import com.adansoft.great21.games.RummyArena;
import com.adansoft.great21.games.SevenCardRummy;
import com.adansoft.great21.models.Game;
import com.adansoft.great21.restschemas.CreateGameRequest;
import com.adansoft.great21.restschemas.GetGameListinLobbyRequest;

public class GameBrowserHelper {

	public static Game createGame(CreateGameRequest request)
	{
		 Game game = null;
		 System.out.println(" GameType Request :- " + request.getGameType());
		 if(request.getGameType().equals(GameListConstants.GAMELIST_SEVENCARD_CLOSED_TYPE) || request.getGameType().equals(GameListConstants.GAMELIST_SEVENCARD_OPEN_TYPE))
			 game =  new SevenCardRummy(1, request.getMaxPlayers(),
				 request.getMaxRounds(), request.getMaxRounds(),
				 request.getCreatedBy(), request.isGamePointsBased(),
				 request.getMaxPoints(), request.isGamePerCardBase(),
				 request.getPerCardAmount(), request.getLobbyType(), request.getGameType(),
				request.getGameDescription());
		 
		 RummyArena.getInstance().displayArena();      
		 RummyArena.getInstance().getLobby(request.getLobbyType()).addGame(game, request.getGameType());
		 return game;
	}
	
	public static GameLobby getGameList(GetGameListinLobbyRequest request)
	{
		System.out.println("Request game for getGameList: " + request.getLobbyName());
		if(request.getLobbyName() == null)
			return null;
		GameLobby lobby = RummyArena.getInstance().getLobby(request.getLobbyName());	
		System.out.println("Returning Lobby: " + lobby);
		return lobby;
		
	}
}
