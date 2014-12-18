package com.adansoft.great21.gameindexer.delegate;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import com.adansoft.great21.CacheModels.GameIndexerCache;
import com.adansoft.great21.CacheModels.GameManagerCache;
import com.adansoft.great21.exceptions.NoGameManagerAvailableException;
import com.adansoft.great21.models.Game;
import com.adansoft.great21.restschemas.CreateGameRequest;


public class GameBrowserDelegate {


	@Autowired
	private JmsMessagingTemplate messageTemplate;
	
	@Autowired
	private MappingJackson2MessageConverter converter;


	@PostConstruct
	public void init()
	{
		converter.setTargetType(MessageType.TEXT);
		converter.setTypeIdPropertyName("Operation");	
	}
		
	
	
	public Game createGame(CreateGameRequest request)
	{
		Game createdGame = null;
		try
		{
			converter.setTargetType(MessageType.TEXT);
			converter.setTypeIdPropertyName("Operation");
			messageTemplate.setJmsMessageConverter(converter);
			
		   GameManagerCache cache = GameIndexerCache.getInstance().getNextAvailableGameManager();
		   String destinationName = cache.getRequestQueue();	
		 
		   createdGame = messageTemplate.convertSendAndReceive(destinationName, request, Game.class);
		   
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
	
	
}
