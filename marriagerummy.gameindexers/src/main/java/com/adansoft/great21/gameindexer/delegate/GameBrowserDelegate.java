package com.adansoft.great21.gameindexer.delegate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.support.converter.SimpleMessageConverter;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import com.adansoft.great21.CacheModels.GameIndexerCache;
import com.adansoft.great21.CacheModels.GameManagerCache;
import com.adansoft.great21.exceptions.NoGameManagerAvailableException;
import com.adansoft.great21.gameindexer.helpers.CacheServerGameIndexCache;
import com.adansoft.great21.gameindexer.helpers.GameBrowserHelper;
import com.adansoft.great21.gameindexers.deserializers.PlayerDeserializer;
import com.adansoft.great21.gameindexers.deserializers.PlayerKeyDeserializer;
import com.adansoft.great21.games.GameLobby;
import com.adansoft.great21.games.SevenCardRummy;
import com.adansoft.great21.models.Game;
import com.adansoft.great21.models.Player;
import com.adansoft.great21.restschemas.AddPlayerRequest;
import com.adansoft.great21.restschemas.CreateGameRequest;
import com.adansoft.great21.restschemas.DeleteGameRequest;
import com.adansoft.great21.restschemas.GetGameListinLobbyRequest;
import com.adansoft.great21.restschemas.RemovePlayerRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;


public class GameBrowserDelegate {


	@Autowired
	private JmsMessagingTemplate messageTemplate;
	

	@Autowired
	private CacheServerGameIndexCache cacheserverinstance;
	
	
	public Game createGame(CreateGameRequest request)
	{
		Game createdGame = null;
		try
		{
		
		   GameManagerCache cache = GameIndexerCache.getInstance().getNextAvailableGameManager();
		   String destinationName = cache.getRequestQueue();	
		   Message<CreateGameRequest> requestjmsmessage = MessageBuilder.withPayload(request).build();
		   @SuppressWarnings("unchecked")
		   Message<Game> game =  (Message<Game>) messageTemplate.sendAndReceive(destinationName, requestjmsmessage);
		   System.out.println("Got Reply :- " + game.getPayload());
		   createdGame = game.getPayload();
		   cacheserverinstance.addGametoCache(createdGame.getGameInstanceId(), destinationName);
		   
		}catch(NoGameManagerAvailableException ex)
		{
			ex.printStackTrace();
			// Construct Error Object here
			return null;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return null;
		}
		return createdGame;
	}
	
	public GameLobby getGameList(String lobbyName)
	{
		GameLobby replLobby = null;
		try
		{
		replLobby = GameLobby.createLocalGameLobby(lobbyName);
		GetGameListinLobbyRequest request = new GetGameListinLobbyRequest(lobbyName);
		for(String gmcachekey : GameIndexerCache.getInstance().getGamemanagerlist().keySet())
		{
			String destination = GameIndexerCache.getInstance().getGamemanagerlist().get(gmcachekey).getRequestQueue();
			Message<GetGameListinLobbyRequest> requestjmsmessage = MessageBuilder.withPayload(request).build();
			@SuppressWarnings("unchecked")
			Message<GameLobby> gamelobby =  (Message<GameLobby>) messageTemplate.sendAndReceive(destination, requestjmsmessage);
			GameBrowserHelper.mergeGameLobby(replLobby, gamelobby.getPayload());
		}
		}catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
		
		return replLobby;
	}
	
	public String deleteGame(DeleteGameRequest request)
	{
		String result = null;
		try
		{
			String destination = cacheserverinstance.lookupGameInstanceID(request.getGameInstanceID());
			if(destination == null)
			{
				result = "Failure: No such Game Available";
				return result;
			}
			Message<DeleteGameRequest> requestjmsmessage = MessageBuilder.withPayload(request).build();
			@SuppressWarnings("unchecked")
			Message<String> reply =  (Message<String>) messageTemplate.sendAndReceive(destination, requestjmsmessage);
			result = reply.getPayload();
			if(result.equals("Success"))
				cacheserverinstance.deleteGameInstanceID(request.getGameInstanceID());
		}catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
		return result;
	}
	
	
	public String addPlayertoGame(AddPlayerRequest request)
	{
		String result = null;
		try
		{
			String destination = cacheserverinstance.lookupGameInstanceID(request.getGameInstanceID());
			if(destination == null)
			{
				result = "Failure : No such Game Available";
				return result;
			}
			Message<AddPlayerRequest> requestjmsmessage = MessageBuilder.withPayload(request).build();
			@SuppressWarnings("unchecked")
			Message<String> reply =  (Message<String>) messageTemplate.sendAndReceive(destination, requestjmsmessage);
			result = reply.getPayload();
		}catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
		return result;
	}
	
	public String removePlayerfromGame(RemovePlayerRequest request)
	{
		String result = null;
		try
		{
			String destination = cacheserverinstance.lookupGameInstanceID(request.getGameInstanceID());
			if(destination == null)
			{
				result = "Failure : No such Game Available";
				return result;
			}
			Message<RemovePlayerRequest> requestjmsmessage = MessageBuilder.withPayload(request).build();
			@SuppressWarnings("unchecked")
			Message<String> reply =  (Message<String>) messageTemplate.sendAndReceive(destination, requestjmsmessage);
			result = reply.getPayload();
		}catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
		return result;
	}
}
