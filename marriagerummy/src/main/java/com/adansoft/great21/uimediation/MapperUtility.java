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
		uiGame.setPlayers(game.getPlayers().size()+"/"+game.getMaxPlayers());
		uiGame.setDesc(game.getDescription());
		String playedbet = null;
		if(game.isGameCardMoneyBased())
			playedbet = "Per Card : " + game.getPerCardMoneyValue();
		if(game.isGamePointsBased())
			playedbet = "Points ( Max points : " + game.getMaxPoints()+")";
		uiGame.setPlayedbet(playedbet);
		
		uiGame.setType(getGameTypeDesc(gameType));
		uiGame.setGameStatus(getGameStatusDesc(game.getGameStatus()));
		String action = null;
		if(game.getGameStatus().equals(Game.GAME_STATUS_OPEN) && game.getPlayers().size() < game.getMaxPlayers())
			action = "Join";
		else
			action = "Watch";
		uiGame.setAction(action);
		return uiGame;
	}
	
	private static String getGameTypeDesc(String gameType)
	{
		if(gameType.equals(GameListConstants.GAMELIST_SEVENCARD_CLOSED_TYPE))
			return "7 Card closed joker rummy";
		else if(gameType.equals(GameListConstants.GAMELIST_SEVENCARD_OPEN_TYPE))
			return "7 Card open joker rummy";
		else if(gameType.equals(GameListConstants.GAMELIST_THIRTEENCARD_CLOSED_TYPE))
			return "13 Card closed joker rummy";
		else if(gameType.equals(GameListConstants.GAMELIST_THIRTEENCARD_OPEN_TYPE))
			return "13 Card open joker rummy";
		else if(gameType.equals(GameListConstants.GAMELIST_TWENTYONECARD_TYPE))
			return "21 Card marriage rummy";
		else
			return null;
	}
	
	private static String getGameStatusDesc(String gameStatus)
	{
		if(gameStatus.equals(Game.GAME_STATUS_OPEN))
			return "Open";
		else if(gameStatus.equals(Game.GAME_STATUS_INPROGRESS))
			return "In Progress";
		else
			return null;
	}
	
}
