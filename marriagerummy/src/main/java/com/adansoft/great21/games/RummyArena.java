package com.adansoft.great21.games;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlRootElement;

// Follows Singleton Pattern
@XmlRootElement
public class RummyArena {
	
	private static RummyArena instance = null;
	
	private static ArrayList<GameLobby> lobbylist;
	
	protected RummyArena()
	{
		// Cannot Instansiate
		lobbylist = new ArrayList<GameLobby>();
	}
	

	
	
	public static RummyArena getInstance()
	{
		 if(instance == null) {
	         instance = new RummyArena();
	      }
	      return instance;
	}
	
	
	
	public  ArrayList<GameLobby> getGameLobbyList()
	{
		return lobbylist;
	}
	
	public  void setGameLobbyList(ArrayList<GameLobby> list)
	{
		lobbylist = list;
	}
	
	public  GameLobby getLobby(String lobbyName)
	{
	
	   for(GameLobby lobby : lobbylist)
	   {
		   if(lobby.getLobbyName().equals(lobbyName))
			   return lobby;
	   }
	   return null;
	}
}
