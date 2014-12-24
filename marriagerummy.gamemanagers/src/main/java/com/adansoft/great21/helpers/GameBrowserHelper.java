package com.adansoft.great21.helpers;


import com.adansoft.great21.games.GameListConstants;
import com.adansoft.great21.games.GameLobby;
import com.adansoft.great21.games.RummyArena;
import com.adansoft.great21.games.SevenCardRummy;
import com.adansoft.great21.models.Game;
import com.adansoft.great21.models.HumanPlayer;
import com.adansoft.great21.models.Player;
import com.adansoft.great21.restschemas.AddPlayerRequest;
import com.adansoft.great21.restschemas.CreateGameRequest;
import com.adansoft.great21.restschemas.DeleteGameRequest;
import com.adansoft.great21.restschemas.GetGameListinLobbyRequest;
import com.adansoft.great21.restschemas.RemovePlayerRequest;

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
		 
		 HumanPlayer player = new HumanPlayer(request.getCreatedBy());
		 game.getPlayers().add(player);
		 
		// RummyArena.getInstance().displayArena();      
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
  
	public static String deleteGame(DeleteGameRequest request)
	{
		String result = "Success";
		try
		{
		GameLobby lobby = RummyArena.getInstance().getLobby(request.getLobbyName());
		Game game = UtilityHelper.getGamefromLobby(lobby, request.getGameInstanceID(), request.getGameType());
		if(game.getOwner().equals(request.getNickName()))
		     UtilityHelper.deleteGamefromLobby(lobby, game, request.getGameType());
		else
			result = "Failure : Not enough previleges";
		}catch(Exception e)
		{
			e.printStackTrace();
			result = "Failure";
		}
		return result;	
	}
	
	public static String addPlayertoGame(AddPlayerRequest request)
	{
		String result = "Success";
		try
		{
		GameLobby lobby = RummyArena.getInstance().getLobby(request.getLobbyName());
		Game game =UtilityHelper.getGamefromLobby(lobby, request.getGameInstanceID(), request.getGameType());
		if(request.getPlayerType().equals(Player.PLAYER_TYPE_HUMAN))
		{
			HumanPlayer player = new HumanPlayer(request.getNickname());
					
			       game.getPlayers().add(player);
		}
		}catch(Exception e)
		{
			e.printStackTrace();
			result = "Failure";
		}
		return result;			
	}
	
	
	public static String removePlayerFromGame(RemovePlayerRequest request)
	{
		String result = "Success";
		try {
			GameLobby lobby = RummyArena.getInstance().getLobby(request.getLobbyName());
			Game game = UtilityHelper.getGamefromLobby(lobby, request.getGameInstanceID(), request.getGameType());
			
			for(Player player : game.getPlayers())
			{
				if(player.getNickName().equals(request.getNickname()))
				{
					game.getPlayers().remove(player);
					return result;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			result = "Failure";
		}
		return result;
		
	}
	
	

}
