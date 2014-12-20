package com.adansoft.great21.gameindexer.helpers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

public class CacheServerGameIndexCache {

	@Autowired
	RedisTemplate<String, String> gameInstanceRedisTemplate;
	
	public void addGametoCache(String gameinstanceid,String requestQueue)
	{
		this.gameInstanceRedisTemplate.opsForHash().put(gameinstanceid, gameinstanceid.hashCode(), requestQueue);		
	}
	
	public String lookupGameInstanceID(String gameinstanceid)
	{
		return (String) this.gameInstanceRedisTemplate.opsForHash().get(gameinstanceid, gameinstanceid.hashCode());
	}
	
	public void deleteGameInstanceID(String gameinstanceid)
	{
		this.gameInstanceRedisTemplate.delete(gameinstanceid);
	}
	
	
}
