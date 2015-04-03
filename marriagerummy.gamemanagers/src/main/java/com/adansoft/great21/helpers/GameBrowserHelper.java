package com.adansoft.great21.helpers;


import java.util.ArrayList;

import org.springframework.core.task.TaskExecutor;
import org.springframework.web.client.RestTemplate;

import com.adansoft.great21.dataaccess.schemas.GetUserBasicDetailsRequest;
import com.adansoft.great21.dataaccess.schemas.GetUserBasicDetailsResponse;
import com.adansoft.great21.dataccess.helpers.GameManagertoDataAccessMapper;
import com.adansoft.great21.dataccess.helpers.RestServiceHelper;
import com.adansoft.great21.delayedwrite.GameDataLazyWriter;
import com.adansoft.great21.games.GameListConstants;
import com.adansoft.great21.games.GameLobby;
import com.adansoft.great21.games.RummyArena;
import com.adansoft.great21.games.SevenCardRummy;

import com.adansoft.great21.models.Game;

import com.adansoft.great21.models.HumanPlayer;
import com.adansoft.great21.models.Player;
import com.adansoft.great21.restschemas.AddPlayerRequest;
import com.adansoft.great21.restschemas.AddPlayerResponse;
import com.adansoft.great21.restschemas.CreateGameRequest;
import com.adansoft.great21.restschemas.DeleteGameRequest;

import com.adansoft.great21.restschemas.GetGameListinLobbyRequest;

import com.adansoft.great21.restschemas.GetPlayersinGameRequest;
import com.adansoft.great21.restschemas.LaunchGameRequest;
import com.adansoft.great21.restschemas.RemovePlayerRequest;


public class GameBrowserHelper {
	
  

	public static Game createGame(GameManagertoDataAccessMapper mapper,RestTemplate template,CreateGameRequest request,TaskExecutor executor)
	{
		 Game game = null;
		 System.out.println(" GameType Request :- " + request.getGameType());
		 if(request.getGameType().equals(GameListConstants.GAMELIST_SEVENCARD_CLOSED_TYPE) || request.getGameType().equals(GameListConstants.GAMELIST_SEVENCARD_OPEN_TYPE))
			 game =  new SevenCardRummy(1, true , 2, request.getMaxPlayers(),
				 request.getMaxRounds(), request.getMaxRounds(),
				 request.getCreatedBy(), request.isGamePointsBased(),
				 request.getMaxPoints(), request.isGamePerCardBase(),
				 request.getPerCardAmount(), request.getLobbyType(), request.getGameType(),
				 request.getGameDescription(),request.getBuyinValue());
		 
		    GetUserBasicDetailsRequest basicdetailsrequest = new GetUserBasicDetailsRequest(request.getAuthdata().getUserid(),request.getAuthdata().getEmailadd());			
			GetUserBasicDetailsResponse basicdetailResponse = RestServiceHelper.getBasicDetails(mapper,template,basicdetailsrequest);
		 
		 HumanPlayer player = new HumanPlayer(request.getCreatedBy(),0);
		 player.setPlayerrole(Player.PLAYER_ROLE_HOST);
		 player.setMyuserid(request.getAuthdata().getUserid());
		 player.setCashInHand(basicdetailResponse.getCash());
		 if(request.getGameType().equals(GameListConstants.GAMELIST_SEVENCARD_CLOSED_TYPE))
			 player.setJokerKnown(false);
		 if(request.getGameType().equals(GameListConstants.GAMELIST_SEVENCARD_OPEN_TYPE))
			 player.setJokerKnown(true);
		 game.addPlayertoGame(player);
		 
		// RummyArena.getInstance().displayArena();      
		 RummyArena.getInstance().getLobby(request.getLobbyType()).addGame(game, request.getGameType());
		 executor.execute(new GameDataLazyWriter(GameDataLazyWriter.OP_CREATEGAME, game , mapper,template));
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
  
	public static String deleteGame(GameManagertoDataAccessMapper mapper,RestTemplate template,DeleteGameRequest request,TaskExecutor executor)
	{
		String result = "Success";
		try
		{
		GameLobby lobby = RummyArena.getInstance().getLobby(request.getLobbyName());
		Game game = UtilityHelper.getGamefromLobby(lobby, request.getGameInstanceID(), request.getGameType());
		if(game.getOwner().equals(request.getNickName()))
		{
		     UtilityHelper.deleteGamefromLobby(lobby, game, request.getGameType());
		     executor.execute(new GameDataLazyWriter(GameDataLazyWriter.OP_DELETEGAME, game , mapper,template));
		}
		else
			result = "Failure : Not enough previleges";
		}catch(Exception e)
		{
			e.printStackTrace();
			result = "Failure";
		}
		return result;	
	}
	
	public static AddPlayerResponse addPlayertoGame(GameManagertoDataAccessMapper mapper,RestTemplate template , AddPlayerRequest request)
	{
		AddPlayerResponse result = new AddPlayerResponse();
		try
		{
		GameLobby lobby = RummyArena.getInstance().getLobby(request.getLobbyName());
		Game game = UtilityHelper.getGamefromLobby(lobby, request.getGameInstanceID(), request.getGameType());
		
		GetUserBasicDetailsRequest basicdetailsrequest = new GetUserBasicDetailsRequest(request.getAuthdata().getUserid(),request.getAuthdata().getEmailadd());
		
		GetUserBasicDetailsResponse basicdetailResponse = RestServiceHelper.getBasicDetails(mapper,template,basicdetailsrequest);
		
		
		if(request.getPlayerType().equals(Player.PLAYER_TYPE_HUMAN))
		{
			HumanPlayer player = new HumanPlayer(request.getNickname(),0);
			player.setPlayerrole(Player.PLAYER_ROLE_GUEST);	
			player.setMyuserid(request.getAuthdata().getUserid());
			player.setCashInHand(basicdetailResponse.getCash());
			game.addPlayertoGame(player);
			result.setGameInstanceID(request.getGameInstanceID());
			result.setGameType(request.getGameType());
			result.setLobbyName(request.getLobbyName());
			result.setNickname(request.getNickname());
			result.setPlayerType(request.getPlayerType());
			result.setPlayerPosition(player.getPlayerPosition());
			result.setGameMoneyBased(game.isGameCardMoneyBased());
			result.setGameName(game.getDescription());
			result.setGamePointsBased(game.isGamePointsBased());
			result.setMaxplayers(game.getMaxPlayers());
			result.setMaxPoints(game.getMaxPoints());
			result.setMoneyPerCard((int) game.getPerCardMoneyValue());
			result.setOwner(game.getOwner());
			result.setPlayersize(game.getPlayers().size());
			result.setPlayerType(player.getPlayerType());	
		}
		}catch(Exception e)
		{
			e.printStackTrace();
			result = null;
		}
		return result;			
	}
	
	
	public static String removePlayerFromGame(RemovePlayerRequest request)
	{
		String result = "Success";
		try {
			GameLobby lobby = RummyArena.getInstance().getLobby(request.getLobbyName());
			Game game = UtilityHelper.getGamefromLobby(lobby, request.getGameInstanceID(), request.getGameType());
			if(!(request.getRequestedby().equals(game.getOwner())) &&
			   !(request.getRequestedby().equals(request.getNickname()))
			  )
			{
				result = "Failure not enough previlges";
				return result;
			}
			for(Player player : game.getPlayers())
			{
				if(player.getNickName().equals(request.getNickname()))
				{
					game.removePlayerFromGame(player);
					return result;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			result = "Failure";
		}
		return result;
		
	}
	
	
	public static ArrayList<Player> getPlayersinGame(GetPlayersinGameRequest request)
	{
		GameLobby lobby = RummyArena.getInstance().getLobby(request.getLobbyName());
		Game game = UtilityHelper.getGamefromLobby(lobby, request.getGameInstanceID(), request.getGameType());
		return game.getPlayers();
	}
	
	public static String launchGame(GameManagertoDataAccessMapper mapper,RestTemplate template,LaunchGameRequest request,TaskExecutor executor)
	{
		GameLobby lobby = RummyArena.getInstance().getLobby(request.getLobbyName());
		Game game = UtilityHelper.getGamefromLobby(lobby, request.getGameInstanceID(), request.getGameType());
		if(game == null)
		{
			System.out.println("Unable to find game with game instanceid:- " + request.getGameInstanceID());
		}
		if(game != null && game.getOwner().equals(request.getNickName()))
		{  
			game.startGame();
			 executor.execute(new GameDataLazyWriter(GameDataLazyWriter.OP_LAUNCHGAME, game , mapper,template));
		   return "Success"; 
		}
		return "Failure : Not enough previleges ";
	}
	
	
}
