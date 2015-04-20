package com.adansoft.great21.delayedwrite;

import java.net.URI;

import org.springframework.web.client.RestTemplate;

import com.adansoft.great21.dataaccess.gamedata.schemas.PersistNewGame;
import com.adansoft.great21.dataaccess.gamedata.schemas.PersistNewRound;
import com.adansoft.great21.dataaccess.gamedata.schemas.PersistPointsorCashforRound;
import com.adansoft.great21.dataaccess.gamedata.schemas.UpdateGameStatus;
import com.adansoft.great21.dataaccess.gamedata.schemas.UpdatePlayerStatusPoints;
import com.adansoft.great21.dataaccess.gamedata.schemas.UpdateRummyStat;
import com.adansoft.great21.dataccess.helpers.DataAccessURLs;
import com.adansoft.great21.dataccess.helpers.GameManagertoDataAccessMapper;
import com.adansoft.great21.models.Game;
import com.adansoft.great21.models.Player;




public class GameDataLazyWriteHelper {
	
	public static String createGame(Object obj,GameManagertoDataAccessMapper mapper,RestTemplate template)
	{
		String result = "Failure";
		try
		{
		if(obj instanceof Game)
		{
			PersistNewGame request = convertCreateGameRequest((Game)obj);
			URI url = new URI(mapper.getDataAccessURI() + "/"
					+ DataAccessURLs.DELAYED_GAMEDATA_BASE + "/"
					+ DataAccessURLs.CREATED_GAME);
			result = template.postForEntity(url, request, String.class).getBody();
			System.out.println("Successfully wrote Game Content to DB");
		}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}
	
	
	
	public static String launchGame(Object obj,GameManagertoDataAccessMapper mapper,RestTemplate template)
	{
		String result = "Failure";
		try
		{
		if(obj instanceof Game)
		{
			UpdateGameStatus request = convertUpdateGameRequest((Game)obj);
			URI url = new URI(mapper.getDataAccessURI() + "/"
					+ DataAccessURLs.DELAYED_GAMEDATA_BASE + "/"
					+ DataAccessURLs.LAUNCH_GAME);
			result = template.postForEntity(url, request, String.class).getBody();
			System.out.println("Successfully wrote Game Content to DB");
		}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}
	
	
	public static String deleteGame(Object obj,GameManagertoDataAccessMapper mapper,RestTemplate template)
	{
		String result = "Failure";
		try
		{
		if(obj instanceof Game)
		{
			UpdateGameStatus request = convertUpdateGameRequest((Game)obj);
			URI url = new URI(mapper.getDataAccessURI() + "/"
					+ DataAccessURLs.DELAYED_GAMEDATA_BASE + "/"
					+ DataAccessURLs.DELETE_GAME);
			result = template.postForEntity(url, request, String.class).getBody();
			System.out.println("Successfully wrote Game Content to DB");
		}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}
	
	
	
	public static String createNewGameRound(Object obj,GameManagertoDataAccessMapper mapper,RestTemplate template)
	{
		String result = "Failure";
		try
		{
		if(obj instanceof PersistNewRound)
		{
			PersistNewRound request = (PersistNewRound) obj;
			URI url = new URI(mapper.getDataAccessURI() + "/"
					+ DataAccessURLs.DELAYED_GAMEDATA_BASE + "/"
					+ DataAccessURLs.CREATE_GAME_ROUND);
			result = template.postForEntity(url, request, String.class).getBody();
			System.out.println("Successfully wrote Game Content to DB");
		}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}
	
	public static String finishGameRound(Object obj,GameManagertoDataAccessMapper mapper,RestTemplate template)
	{
		String result = "Failure";
		try
		{
		if(obj instanceof PersistNewRound)
		{
			PersistNewRound request = (PersistNewRound) obj;
			URI url = new URI(mapper.getDataAccessURI() + "/"
					+ DataAccessURLs.DELAYED_GAMEDATA_BASE + "/"
					+ DataAccessURLs.FINISH_GAME_ROUND);
			result = template.postForEntity(url, request, String.class).getBody();
			System.out.println("Successfully wrote Game Content to DB");
		}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}
	
	
	public static String persistPlayerCashorPoints(Object obj,GameManagertoDataAccessMapper mapper,RestTemplate template)
	{
		String result = "Failure";
		try
		{
		if(obj instanceof UpdatePlayerStatusPoints)
		{
			UpdatePlayerStatusPoints request = (UpdatePlayerStatusPoints) obj;
			URI url = new URI(mapper.getDataAccessURI() + "/"
					+ DataAccessURLs.DELAYED_GAMEDATA_BASE + "/"
					+ DataAccessURLs.UPDATE_PLAYER_STATUS);
			result = template.postForEntity(url, request, String.class).getBody();
			System.out.println("Successfully wrote Game Content to DB");
		}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}
	
	public static String updateRummyStat(Object obj,GameManagertoDataAccessMapper mapper,RestTemplate template)
	{
		String result = "Failure";
		try
		{
		if(obj instanceof UpdateRummyStat)
		{
			UpdateRummyStat request = (UpdateRummyStat) obj;
			URI url = new URI(mapper.getDataAccessURI() + "/"
					+ DataAccessURLs.DELAYED_GAMEDATA_BASE + "/"
					+ DataAccessURLs.UPDATE_PLAYER_RUMMYSTAT);
			result = template.postForEntity(url, request, String.class).getBody();
			System.out.println("Successfully wrote Game Content to DB");
		}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}
	
	
	
	private static PersistNewGame convertCreateGameRequest(Game request)
	{
		PersistNewGame response = new PersistNewGame();
		response.setGameInstanceID(request.getGameInstanceId());
		response.setGameType(request.getRummyType());
		response.setHostnick(request.getOwner());
		response.setHostuserid(request.getPlayers().get(0).getUserID());
		response.setLobbyName(request.getGameLobbyName());
		response.setPerCard(request.isGameCardMoneyBased());
		response.setPointBased(request.isGamePointsBased());
		return response;
	}
	
	private static UpdateGameStatus convertUpdateGameRequest(Game request)
	{
		UpdateGameStatus response = new UpdateGameStatus();
		response.setGameInstanceID(request.getGameInstanceId());
		int count = 0,pos = 0;
		for(Player player : request.getPlayers())
		{
			count++;pos++;
			if(pos == 1)
			    response.setPlayer1idn(player.getUserID());
			if(pos == 2)
			    response.setPlayer2idn(player.getUserID());
			if(pos == 3)
			    response.setPlayer3idn(player.getUserID());
			if(pos == 4)
			    response.setPlayer4idn(player.getUserID());
			if(pos == 5)
			    response.setPlayer5idn(player.getUserID());
			if(pos == 6)
			    response.setPlayer6idn(player.getUserID());
			if(pos == 7)
			    response.setPlayer7idn(player.getUserID());
			if(pos == 8)
			    response.setPlayer8idn(player.getUserID());
		}
		response.setNumofplayers(count);
		return response;
	}
	
	
	
}




