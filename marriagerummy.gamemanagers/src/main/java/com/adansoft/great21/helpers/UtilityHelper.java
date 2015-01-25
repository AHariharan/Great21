package com.adansoft.great21.helpers;

import com.adansoft.great21.games.GameListConstants;
import com.adansoft.great21.games.GameLobby;
import com.adansoft.great21.models.Card;
import com.adansoft.great21.models.Game;
import com.adansoft.great21.models.Player;

public class UtilityHelper {

	
	public static Game getGamefromLobby(GameLobby lobby,String gameInstanceID,String gameType)
	{
		Game game = null;
		if(gameType.equals(GameListConstants.GAMELIST_SEVENCARD_CLOSED_TYPE))
			 game = lobby.getSevencard_closed_gamelist().getGame(gameInstanceID);
		else if(gameType.equals(GameListConstants.GAMELIST_SEVENCARD_OPEN_TYPE))
			 game = lobby.getSevencard_open_gamelist().getGame(gameInstanceID);
		else if(gameType.equals(GameListConstants.GAMELIST_THIRTEENCARD_CLOSED_TYPE))
			 game = lobby.getThirteencard_closed_gamelist().getGame(gameInstanceID);
		else if(gameType.equals(GameListConstants.GAMELIST_THIRTEENCARD_OPEN_TYPE))
			 game = lobby.getThirteencard_open_gamelist().getGame(gameInstanceID);
		else if(gameType.equals(GameListConstants.GAMELIST_TWENTYONECARD_TYPE))
			game = lobby.getTwentyonecard_gamelist().getGame(gameInstanceID);		
		
		return game;
	}
	
	public static Game deleteGamefromLobby(GameLobby lobby,Game game,String gameType)
	{
	
		if(gameType.equals(GameListConstants.GAMELIST_SEVENCARD_CLOSED_TYPE))
			 lobby.getSevencard_closed_gamelist().getGamelist().remove(game);
		else if(gameType.equals(GameListConstants.GAMELIST_SEVENCARD_OPEN_TYPE))
			 lobby.getSevencard_open_gamelist().getGamelist().remove(game);
		else if(gameType.equals(GameListConstants.GAMELIST_THIRTEENCARD_CLOSED_TYPE))
			 lobby.getThirteencard_closed_gamelist().getGamelist().remove(game);
		else if(gameType.equals(GameListConstants.GAMELIST_THIRTEENCARD_OPEN_TYPE))
			 lobby.getThirteencard_open_gamelist().getGamelist().remove(game);
		else if(gameType.equals(GameListConstants.GAMELIST_TWENTYONECARD_TYPE))
			 lobby.getTwentyonecard_gamelist().getGamelist().remove(game);
		
		return game;
	}
	
	public static Player getPlayerinGame(Game game,String nickname)
	{
		for(Player player : game.getCurrentGameRound().getPlayerlist())
		{
			if(player.getNickName().equals(nickname))
				return player;
		}
		return null;
	}
	
	
	public static Card getCardforPlayerFromUICard(Player player , String cardinstanceid)
	{
		Card card = null;
		for(Card cucard : player.getPlayerCards())
		{
			if(cucard.getInstanceID().equals(cardinstanceid))
			{
				card = cucard;
				return card;
			}
		}
		return card;
	}
}
