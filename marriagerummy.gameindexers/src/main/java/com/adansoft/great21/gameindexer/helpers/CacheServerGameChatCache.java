package com.adansoft.great21.gameindexer.helpers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import com.adansoft.great21.gameplay.GameChatMessageManager;
import com.adansoft.great21.gameplay.GameMessage;

public class CacheServerGameChatCache {

	@Autowired
	RedisTemplate<String, GameChatMessageManager> gameChatRedisTemplate;
	
	public void addChatMessagetoGame(String gameinstanceid,GameChatMessageManager requestQueue)
	{
		this.gameChatRedisTemplate.opsForHash().put(gameinstanceid+"-Chat", gameinstanceid.hashCode(), requestQueue);		
	}
	
	public GameChatMessageManager lookupChatMessage(String gameinstanceid)
	{
		System.out.println("gameChat Template: " + gameChatRedisTemplate);
		GameChatMessageManager manager = null; 
		try
		{
		if(this.gameChatRedisTemplate.opsForHash().get(gameinstanceid+"-Chat", gameinstanceid.hashCode()) != null)
		   manager =  (GameChatMessageManager) this.gameChatRedisTemplate.opsForHash().get(gameinstanceid+"-Chat", gameinstanceid.hashCode());
		}catch(Exception e) { e.printStackTrace();}
		return manager;
	}
	
	public void deleteGameChat(String gameinstanceid)
	{
		this.gameChatRedisTemplate.delete(gameinstanceid);
	}
	
	public void updateCache(String gameinstanceid,GameChatMessageManager requestQueue)
	{
		this.gameChatRedisTemplate.opsForHash().put(gameinstanceid+"-Chat", gameinstanceid.hashCode(), requestQueue);		
	}
	
}
