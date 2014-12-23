package com.adansoft.great21.gameindexer.services;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.adansoft.great21.CacheModels.GameIndexerCache;
import com.adansoft.great21.gameindexer.delegate.GameBrowserDelegate;
import com.adansoft.great21.games.GameLobby;
import com.adansoft.great21.models.Game;
import com.adansoft.great21.models.Player;
import com.adansoft.great21.restschemas.AddPlayerRequest;
import com.adansoft.great21.restschemas.CreateGameRequest;
import com.adansoft.great21.restschemas.DeleteGameRequest;
import com.adansoft.great21.restschemas.GetGameListinLobbyResponse;
import com.adansoft.great21.restschemas.RemovePlayerRequest;

@RestController
@RequestMapping(GameIndexerServiceURLs.GAMEBROWSER_BASE)

public class GameBrowserController {

	
	@Autowired
	GameBrowserDelegate delegate;
	
	@PostConstruct
	public void onInitController()
	{
		GameIndexerCache.getInstance();
		System.out.println("Creating Game Indexer Cache");
	}

	@RequestMapping( value = GameIndexerServiceURLs.CREATEGAME, method = RequestMethod.POST)
	public @ResponseBody Game createGame(@RequestBody CreateGameRequest request)
	{
		System.out.println("GI: Request came in for create game from host :- " + request.getGameDescription());
		Game createdgame = delegate.createGame(request);
		return createdgame;
	}
	
	
	@RequestMapping( value = GameIndexerServiceURLs.GETGAMELIST, method = RequestMethod.POST)
	public @ResponseBody GetGameListinLobbyResponse getGameList(@PathVariable("lobbyName") String lobbyName)
	{
		System.out.println("Request came in for getGameList from lobby :- " + lobbyName);
		GetGameListinLobbyResponse gameLobby = delegate.getGameList(lobbyName);				
		return gameLobby;
	}
	
	@RequestMapping( value = GameIndexerServiceURLs.DELETEGAME, method = RequestMethod.POST)
	public @ResponseBody String deleteGame(@RequestBody DeleteGameRequest request)
	{
		String result = delegate.deleteGame(request);		
		return result;
	}
	
	@RequestMapping( value = GameIndexerServiceURLs.ADD_PLAYER, method = RequestMethod.POST)
	public @ResponseBody String addPlayertoGame(@RequestBody AddPlayerRequest request)
	{
		String result = delegate.addPlayertoGame(request);
		return result;
		
	}
	
	@RequestMapping( value = GameIndexerServiceURLs.REMOVE_PLAYER, method = RequestMethod.POST)
	public @ResponseBody String removePlayerFromGame(@RequestBody RemovePlayerRequest request)
	{
		String result = delegate.removePlayerfromGame(request);
		return result;
		
	}
	
	@ExceptionHandler
	public String handleBadRequest(Exception ex,HttpServletRequest request)
	{
		System.out.println("*********** Incoming request : " + request);
		ex.printStackTrace();
		return ex.getMessage();
	}
}
