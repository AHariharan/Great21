package com.adansoft.great21.gameindexer.helpers;

import com.adansoft.great21.games.GameLobby;
import com.adansoft.great21.models.Game;

public class GameBrowserHelper {

	public static GameLobby mergeGameLobby(GameLobby holdobj,GameLobby mergedobj)
	{
		if(holdobj.getLobbyName().equals(mergedobj.getLobbyName()))
				{
			        for(Game game : mergedobj.getSevencard_closed_gamelist().getGamelist())
			             holdobj.getSevencard_closed_gamelist().addGame(game);
			        for(Game game : mergedobj.getSevencard_open_gamelist().getGamelist())
	                	holdobj.getSevencard_open_gamelist().addGame(game);
			        for(Game game : mergedobj.getThirteencard_closed_gamelist().getGamelist())
	                	holdobj.getThirteencard_closed_gamelist().addGame(game);
			        for(Game game : mergedobj.getThirteencard_open_gamelist().getGamelist())
	                	holdobj.getThirteencard_open_gamelist().addGame(game);
			        for(Game game : mergedobj.getTwentyonecard_gamelist().getGamelist())
	                	holdobj.getTwentyonecard_gamelist().addGame(game);
	        
			        
				}
	
		
		return holdobj;
		
	}
	
}
