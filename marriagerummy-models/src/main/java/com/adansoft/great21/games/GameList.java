package com.adansoft.great21.games;

import java.io.Serializable;
import java.util.ArrayList;

import com.adansoft.great21.models.Game;

public class GameList implements Serializable{
	

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7746030211534883118L;
	
	private ArrayList<Game> gamelist;
	private String lobbyName;
	private String type;
	
	
	public GameList()
	{
		gamelist = new ArrayList<Game>();
	}

	public GameList(String LobbyName,String GameType)
	{		
		this.lobbyName = LobbyName;
		this.type = GameType;
		gamelist = new ArrayList<Game>();
	}
	
	public Game getGame(String gameinstanceid)
	{
		for(Game currentgame : gamelist)
		{
			if(currentgame.getGameInstanceId().equals(gameinstanceid))
				return currentgame;
		}
		return null;
	}
	
	public void addGame(Game game)
	{
		gamelist.add(game);
	}

	public ArrayList<Game> getGamelist() {
		return gamelist;
	}

	public void setGamelist(ArrayList<Game> gamelist) {
		this.gamelist = gamelist;
	}

	
	
}
