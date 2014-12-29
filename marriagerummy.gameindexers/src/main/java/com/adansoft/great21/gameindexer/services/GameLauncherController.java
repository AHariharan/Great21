package com.adansoft.great21.gameindexer.services;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.adansoft.great21.gameindexer.delegate.GameLauncherDelegate;
import com.adansoft.great21.uischemas.AddGameChatRequest;
import com.adansoft.great21.uischemas.GetGameChatRequest;
import com.adansoft.great21.uischemas.GetGameChatResponse;


@RestController
@RequestMapping(GameIndexerServiceURLs.GAMELAUNCHER_BASE)
public class GameLauncherController {
	
	@Autowired
	GameLauncherDelegate delegate;
	
	@RequestMapping(value = GameIndexerServiceURLs.ADDCHATMESSAGES, method = RequestMethod.POST)
	public String addChatMessage(@RequestBody AddGameChatRequest request)
	{
		return delegate.addChatMessage(request);
	}
	
	
	@RequestMapping( value = GameIndexerServiceURLs.GETCHATMESSAGES, method = RequestMethod.POST)
	public @ResponseBody GetGameChatResponse getChatMessages(@RequestBody GetGameChatRequest request)
	{
		GetGameChatResponse response = delegate.getChatMessages(request);
		return response;
	}
	
	
	@ExceptionHandler
	public String handleBadRequest(Exception ex,HttpServletRequest request)
	{
		System.out.println("*********** Incoming request : " + request);
		ex.printStackTrace();
		return ex.getMessage();
	}

}
