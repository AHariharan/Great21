package com.adansoft.great21.uimediation;

import java.util.ArrayList;

import com.adansoft.great21.games.GameListConstants;
import com.adansoft.great21.models.Game;
import com.adansoft.great21.restschemas.GetGameListinLobbyResponse;
import com.adansoft.great21.uischemas.GetGamesinLobby;

public class UIMediationMapper {

	public GetGamesinLobby[] map(GetGameListinLobbyResponse response)
	{
		int id=1;
		if(response == null)
			 return null;
		ArrayList<GetGamesinLobby> gamelist = new ArrayList<GetGamesinLobby>();
		for(Game game : response.getLobby().getSevencard_closed_gamelist().getGamelist())
		{
			GetGamesinLobby uiGame = MapperUtility.mapGametoUIGame(game, id, GameListConstants.GAMELIST_SEVENCARD_CLOSED_TYPE);
			id++;gamelist.add(uiGame);
		}
		for(Game game : response.getLobby().getSevencard_open_gamelist().getGamelist())
		{
			GetGamesinLobby uiGame = MapperUtility.mapGametoUIGame(game, id, GameListConstants.GAMELIST_SEVENCARD_OPEN_TYPE);
			id++;gamelist.add(uiGame);
		}
		for(Game game : response.getLobby().getThirteencard_closed_gamelist().getGamelist())
		{
			GetGamesinLobby uiGame = MapperUtility.mapGametoUIGame(game, id, GameListConstants.GAMELIST_THIRTEENCARD_CLOSED_TYPE);
			id++;gamelist.add(uiGame);
		}
		for(Game game : response.getLobby().getThirteencard_open_gamelist().getGamelist())
		{
			GetGamesinLobby uiGame = MapperUtility.mapGametoUIGame(game, id, GameListConstants.GAMELIST_THIRTEENCARD_OPEN_TYPE);
			id++;gamelist.add(uiGame);
		}
		for(Game game : response.getLobby().getTwentyonecard_gamelist().getGamelist())
		{
			GetGamesinLobby uiGame = MapperUtility.mapGametoUIGame(game, id, GameListConstants.GAMELIST_TWENTYONECARD_TYPE);
			gamelist.add(uiGame);
		}
		GetGamesinLobby[] gamelistarray = new GetGamesinLobby[gamelist.size()];
		int i=0;
		for(GetGamesinLobby objGame : gamelist)
		{
			gamelistarray[i] = objGame;i++;
		}
		
		return gamelistarray;
	}
	
}
