package com.adansoft.great21.gameindexer.delegate;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import com.adansoft.great21.gameindexer.helpers.CacheServerGameChatCache;
import com.adansoft.great21.gameindexer.helpers.CacheServerGameIndexCache;
import com.adansoft.great21.gameplay.GameChatMessageManager;
import com.adansoft.great21.gameplay.GameMessage;
import com.adansoft.great21.models.Player;
import com.adansoft.great21.restschemas.DeleteGameRequest;
import com.adansoft.great21.restschemas.GetPlayersinGameRequest;
import com.adansoft.great21.uischemas.AddGameChatRequest;
import com.adansoft.great21.uischemas.GetGameChatRequest;
import com.adansoft.great21.uischemas.GetGameChatResponse;



public class GameLauncherDelegate {

	@Autowired
	private JmsMessagingTemplate messageTemplate;
	

	@Autowired
	private CacheServerGameChatCache chatCache;
	
	@Autowired
	private CacheServerGameIndexCache cacheserverinstance;
	
	
	public String addChatMessage(AddGameChatRequest request)
	{
		String result = "Success";
		try
		{
			GameChatMessageManager manager = chatCache.lookupChatMessage(request.getGameInstanceID());
			if(manager == null)
			{
				
				GameChatMessageManager newmanager = new GameChatMessageManager(request.getGameInstanceID(),15,0,new GameMessage[15]);
				newmanager.addChatMessage(request.getMessage());
				chatCache.addChatMessagetoGame(request.getGameInstanceID(), newmanager);
			}
			else
			{
				manager.addChatMessage(request.getMessage());
				chatCache.updateCache(request.getGameInstanceID(), manager);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			result = "Failure";
		}
		
		return result;
	}
	
	
	public GetGameChatResponse getChatMessages(GetGameChatRequest request)
	{
		GetGameChatResponse response = new GetGameChatResponse();
		String gameinstanceid = request.getGameInstanceID();
		GameChatMessageManager manager = chatCache.lookupChatMessage(gameinstanceid);
		if(manager == null)
			return null;
		response.setCurrentChatCount(manager.getCurrentChat());	
		response.setMessages(manager.getMessages(request.getCurrentChatCount()));			
		return response;
	}
	
	public ArrayList<Player> getPlayersinGame(GetPlayersinGameRequest request)
	{
		ArrayList<Player> playerlist = new ArrayList<Player>();
		try
		{
		String gameinstanceid = request.getGameInstanceID();
		String destination = cacheserverinstance.lookupGameInstanceID(gameinstanceid);
		Message<GetPlayersinGameRequest> requestjmsmessage = MessageBuilder.withPayload(request).build();
		@SuppressWarnings("unchecked")
		Message<ArrayList<Player>> reply =  (Message<ArrayList<Player>>) messageTemplate.sendAndReceive(destination, requestjmsmessage);
		playerlist = reply.getPayload();		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return playerlist;
	}
	
	
	
}
