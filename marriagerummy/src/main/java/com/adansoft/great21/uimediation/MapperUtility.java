package com.adansoft.great21.uimediation;

import com.adansoft.great21.uischemas.GetGamesinLobby;
import com.adansoft.great21.games.GameListConstants;
import com.adansoft.great21.models.Game;

public class MapperUtility {

	public static GetGamesinLobby mapGametoUIGame(Game game,int id,String gameType)
	{
		GetGamesinLobby uiGame = new GetGamesinLobby();
		uiGame.setGameInstanceId(game.getGameInstanceId());
		uiGame.setId(new Integer(id).toString());id++;
		uiGame.setHost(game.getOwner());
		uiGame.setPlayers(game.getPlayers().size());
		uiGame.setDesc(game.getDescription());
		String playedbet = null;
		if(game.isGameCardMoneyBased())
			playedbet = "Per Card : " + game.getPerCardMoneyValue();
		if(game.isGamePointsBased())
			playedbet = "Points ( Max points : " + game.getMaxPoints()+")";
		uiGame.setPlayedbet(playedbet);
		uiGame.setType(gameType);
		uiGame.setGameStatus(game.getGameStatus());
		String action = null;
		if(game.getGameStatus().equals(Game.GAME_STATUS_OPEN))
			action = "Join";
		else
			action = "Watch";
		uiGame.setAction(action);
		return uiGame;
	}
	
}
