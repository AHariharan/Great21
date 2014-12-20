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
	
	public int numofgamesinCache()
	{
		int totalgames = 0;
		for(GameLobby lobby : this.getGameLobbyList())
		{
			int sevencardclosedtotal  = lobby.getSevencard_closed_gamelist().getGamelist().size();
			int sevencardopentotal  = lobby.getSevencard_open_gamelist().getGamelist().size();
			int thirteencardclosedtotal  = lobby.getThirteencard_closed_gamelist().getGamelist().size();
			int thirteencardopentotal  = lobby.getThirteencard_open_gamelist().getGamelist().size();
			int twentyonecardtotal  = lobby.getTwentyonecard_gamelist().getGamelist().size();
			int lobbytotal = sevencardclosedtotal + sevencardopentotal + thirteencardclosedtotal + thirteencardopentotal + twentyonecardtotal;
			totalgames = totalgames + lobbytotal;
		}
		
		return totalgames;
	}
	
	public void displayArena()
	{
		for(GameLobby lobby : RummyArena.getInstance().getGameLobbyList())
		{
			System.out.println( "Game Lobby : " + lobby.getLobbyName());
			System.out.println("\t lobby.getSevencard_closed_gamelist : " + lobby.getSevencard_closed_gamelist().getGamelist().size());
			System.out.println("\t lobby.getSevencard_open_gamelist : " + lobby.getSevencard_open_gamelist().getGamelist().size());
			System.out.println("\t lobby.getSevencard_closed_gamelist : " + lobby.getThirteencard_closed_gamelist().getGamelist().size());
			System.out.println("\t lobby.getSevencard_open_gamelist : " + lobby.getThirteencard_open_gamelist().getGamelist().size());
		}
	}
	
}
