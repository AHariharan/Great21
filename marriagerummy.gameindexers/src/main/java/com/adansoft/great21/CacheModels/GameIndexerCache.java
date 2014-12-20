package com.adansoft.great21.CacheModels;


import java.util.HashMap;

import com.adansoft.great21.exceptions.NoGameManagerAvailableException;

public class GameIndexerCache {

	private int gamecreateRequestCount = 0;
	
	private static GameIndexerCache indexercache_instance = null;
	
	private HashMap<String,GameManagerCache> gamemanagerlist;
	
	protected GameIndexerCache()
	{
		gamemanagerlist = new HashMap<String, GameManagerCache>();
	}
	
	public static GameIndexerCache getInstance()
	{
		if(indexercache_instance == null)
			  indexercache_instance = new GameIndexerCache();		
	    return indexercache_instance;
	}

	public HashMap<String, GameManagerCache> getGamemanagerlist() {
		return gamemanagerlist;
	}

	public void setGamemanagerlist(HashMap<String, GameManagerCache> gamemanagerlist) {
		this.gamemanagerlist = gamemanagerlist;
	}

	public void addGameManager(GameManagerCache gamemanager)
	{
		if(!getGamemanagerlist().containsKey(gamemanager.getGameManagerInstanceID()))
		    this.getGamemanagerlist().put(gamemanager.getGameManagerInstanceID(), gamemanager);
		else
		{
			GameManagerCache localcache = this.getGamemanagerlist().get(gamemanager.getGameManagerInstanceID());
			localcache.setLastHeartbeatReceived(gamemanager.getLastHeartbeatReceived());
			localcache.setNoofgamesHandled(gamemanager.getNoofgamesHandled());
		}
	}
	
	
	public GameManagerCache getNextAvailableGameManager() throws NoGameManagerAvailableException
	{
		if(getGamemanagerlist().size() == 0 )
		{
			throw new NoGameManagerAvailableException("Can't create game. Indexer doesn't have any game managers");
		}
		gamecreateRequestCount++;
		// Round robin scheduling
		int index =  gamecreateRequestCount%getGamemanagerlist().size();
		return (GameManagerCache) gamemanagerlist.values().toArray()[index];		
				
	}
	
	
}
