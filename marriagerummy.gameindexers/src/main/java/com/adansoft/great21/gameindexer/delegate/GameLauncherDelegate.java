package com.adansoft.great21.gameindexer.delegate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;

import com.adansoft.great21.gameindexer.helpers.CacheServerGameChatCache;
import com.adansoft.great21.gameplay.GameChatMessageManager;
import com.adansoft.great21.gameplay.GameMessage;
import com.adansoft.great21.uischemas.AddGameChatRequest;
import com.adansoft.great21.uischemas.GetGameChatRequest;
import com.adansoft.great21.uischemas.GetGameChatResponse;



public class GameLauncherDelegate {

	@Autowired
	private JmsMessagingTemplate messageTemplate;
	

	@Autowired
	private CacheServerGameChatCache chatCache;
	
	
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
	
	
	
}
