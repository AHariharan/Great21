package com.adansoft.great21.controllers;

import java.net.URI;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.adansoft.great21.exceptions.GameIndexerConfigException;
import com.adansoft.great21.models.Game;
import com.adansoft.great21.restschemas.AddPlayerRequest;
import com.adansoft.great21.restschemas.CreateGameRequest;
import com.adansoft.great21.restschemas.DeleteGameRequest;
import com.adansoft.great21.restschemas.GetGameListinLobbyResponse;
import com.adansoft.great21.restschemas.RemovePlayerRequest;
import com.adansoft.great21.uimediation.UIMediationMapper;
import com.adansoft.great21.uischemas.GetGamesinLobby;

@RestController
@RequestMapping(FacadeControllerURLs.GAMEBROWSER_BASE)
@EnableWebMvcSecurity
public class FacadeGameBrowserController {

	@Autowired
	UIMediationMapper uimapper;
	
	@Autowired
	FacadetoIndexerMapper mapper;
	
	@Autowired
	RestTemplate restTemplate;
	
	@PostConstruct
	public void verifyGameIndexerConfig()
	{
		try
		{
		if(mapper.getIndexerURI() == null)
		{
			System.out.println("Failed to game indexer config .. exiting");
			System.exit(0);
		}
		System.out.println("GameIndexer configured : " + mapper.getIndexerURI());
		}catch(GameIndexerConfigException ex)
		{
			ex.printStackTrace();
			System.exit(0);
		} catch (Exception e) {
		   e.printStackTrace();
		   System.exit(0);
		}
	}
	
	@Secured("ROLE_USER")
	@RequestMapping( value = FacadeControllerURLs.CREATEGAME, method = RequestMethod.POST)
	public @ResponseBody Game createGame(@RequestBody CreateGameRequest request,@AuthenticationPrincipal Authentication authentication)
	{
		String user = authentication.getName();
		request.setCreatedBy(user);
		Game game = null;
		try
		{
				
		System.out.println("Request came in for create game from host :- " + request.getGameDescription());
		URI url = new URI(mapper.getIndexerURI()+"/"+FacadeControllerURLs.GAMEBROWSER_BASE+"/"+FacadeControllerURLs.CREATEGAME);
		game = restTemplate.postForEntity(url, request, Game.class).getBody();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return game;
	}
	
	@Secured("ROLE_USER")
	@RequestMapping( value = FacadeControllerURLs.GETGAMELIST, method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody GetGamesinLobby[] getGameList(@PathVariable("lobbyName") String lobbyName,@AuthenticationPrincipal Authentication authentication)
	{
		System.out.println("Principal obejct" + authentication.getName());
		GetGameListinLobbyResponse lobby = null;
		System.out.println("Request came in for getGameList from lobby :- " + lobbyName);
		try {
			URI url = new URI(mapper.getIndexerURI() + "/"
					+ FacadeControllerURLs.GAMEBROWSER_BASE + "/"
					+ FacadeControllerURLs.GETGAMELIST.replace("{lobbyName}", lobbyName));
			lobby = restTemplate.postForEntity(url, null, GetGameListinLobbyResponse.class).getBody();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return uimapper.map(lobby);
	}
	
	@Secured("ROLE_USER")
	@RequestMapping( value = FacadeControllerURLs.DELETEGAME, method = RequestMethod.POST)
	public @ResponseBody String deleteGame(@RequestBody DeleteGameRequest request)
	{
		String result = null;
		try {
			URI url = new URI(mapper.getIndexerURI() + "/"
					+ FacadeControllerURLs.GAMEBROWSER_BASE + "/"
					+ FacadeControllerURLs.DELETEGAME);
			result = restTemplate.postForEntity(url, request, String.class).getBody();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@Secured("ROLE_USER")
	@RequestMapping( value = FacadeControllerURLs.ADD_PLAYER, method = RequestMethod.POST)
	public @ResponseBody String addPlayertoGame(@RequestBody AddPlayerRequest request)
	{
		String result = null;
		try {
			URI url = new URI(mapper.getIndexerURI() + "/"
					+ FacadeControllerURLs.GAMEBROWSER_BASE + "/"
					+ FacadeControllerURLs.ADD_PLAYER);
			result = restTemplate.postForEntity(url, request, String.class).getBody();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}
	
	@Secured("ROLE_USER")
	@RequestMapping( value = FacadeControllerURLs.REMOVE_PLAYER, method = RequestMethod.POST)
	public @ResponseBody String removePlayerFromGame(@RequestBody RemovePlayerRequest request)
	{
		String result = null;
		try {
			URI url = new URI(mapper.getIndexerURI() + "/"
					+ FacadeControllerURLs.GAMEBROWSER_BASE + "/"
					+ FacadeControllerURLs.REMOVE_PLAYER);
			result = restTemplate.postForEntity(url, request, String.class).getBody();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
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
