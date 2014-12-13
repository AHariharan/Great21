package com.adansoft.great21.gameindexer.services;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.adansoft.great21.models.Game;
import com.adansoft.great21.restschemas.CreateGameRequest;

@RestController
@RequestMapping(GameIndexerServiceURLs.GAMEBROWSER_BASE)

public class GameBrowserController {


	@RequestMapping( value = GameIndexerServiceURLs.CREATEGAME, method = RequestMethod.POST)
	public @ResponseBody Game createGame(@RequestBody CreateGameRequest request)
	{
		System.out.println("Request came in for create game from host :- " + request.getGameDescription());		
		return null;
	}
	
	
	@ExceptionHandler
	public String handleBadRequest(Exception ex,HttpServletRequest request)
	{
		System.out.println("*********** Incoming request : " + request);
		ex.printStackTrace();
		return ex.getMessage();
	}
}
