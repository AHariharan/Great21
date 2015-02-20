package com.adansoft.great21.controllers;

import java.net.URI;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.adansoft.great21.exceptions.GameIndexerConfigException;
import com.adansoft.great21.models.Player;
import com.adansoft.great21.restschemas.GetPlayersinGameRequest;
import com.adansoft.great21.restschemas.GetPlayersinGameResponse;
import com.adansoft.great21.restschemas.LaunchGameRequest;
import com.adansoft.great21.router.FacadetoIndexerMapper;
import com.adansoft.great21.uimediation.UIMediationMapper;
import com.adansoft.great21.uischemas.AddGameChatRequest;
import com.adansoft.great21.uischemas.GetGameChatRequest;
import com.adansoft.great21.uischemas.GetGameChatResponse;
import com.adansoft.great21.gameplay.GameMessage;

@RestController
@RequestMapping(FacadeControllerURLs.GAMELAUNCHER_BASE)
@EnableWebMvcSecurity
public class FacadeGameLauncherController {
	


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
		System.out.println("GameIndexer configured for FacadeGameLauncher: " + mapper.getIndexerURI());
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
	@RequestMapping( value = FacadeControllerURLs.ADDCHATMESSAGES, method = RequestMethod.POST)
	public @ResponseBody String addChatMessage(@RequestBody  AddGameChatRequest request,@AuthenticationPrincipal Authentication authentication)	
	{
		String result = "Success";
		String nickname = authentication.getName();
		request.getMessage().setPlayerName(nickname);
		try {
			URI url = new URI(mapper.getIndexerURI() + "/"
					+ FacadeControllerURLs.GAMELAUNCHER_BASE + "/"
					+ FacadeControllerURLs.ADDCHATMESSAGES);
			result = restTemplate.postForEntity(url, request, String.class).getBody();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}
	
	
	@Secured("ROLE_USER")
	@RequestMapping( value = FacadeControllerURLs.GETCHATMESSAGES, method = RequestMethod.POST)
	public @ResponseBody GetGameChatResponse getChatMessages(@RequestBody  GetGameChatRequest request,@AuthenticationPrincipal Authentication authentication)	
	{
		GetGameChatResponse result = null;		
		try {
			URI url = new URI(mapper.getIndexerURI() + "/"
					+ FacadeControllerURLs.GAMELAUNCHER_BASE + "/"
					+ FacadeControllerURLs.GETCHATMESSAGES);
			result = restTemplate.postForEntity(url, request, GetGameChatResponse.class).getBody();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;		
	}
	
	@Secured("ROLE_USER")
	@RequestMapping( value = FacadeControllerURLs.GETPLAYERSINGAME, method = RequestMethod.POST)
	public @ResponseBody GetPlayersinGameResponse getPlayersinGame(@RequestBody  GetPlayersinGameRequest request,@AuthenticationPrincipal Authentication authentication)	
	{
		GetPlayersinGameResponse result = null;		
		try {
			URI url = new URI(mapper.getIndexerURI() + "/"
					+ FacadeControllerURLs.GAMELAUNCHER_BASE + "/"
					+ FacadeControllerURLs.GETPLAYERSINGAME);
			result = restTemplate.postForEntity(url, request, GetPlayersinGameResponse.class ).getBody();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;		
	}
	
	@Secured("ROLE_USER")
	@RequestMapping( value = FacadeControllerURLs.LAUNCHAME, method = RequestMethod.POST)
	public @ResponseBody String launchGame(@RequestBody  LaunchGameRequest request,@AuthenticationPrincipal Authentication authentication)	
	{
		String result = null;	
		String nickname = authentication.getName();
		request.setNickName(nickname);
		try {
			URI url = new URI(mapper.getIndexerURI() + "/"
					+ FacadeControllerURLs.GAMELAUNCHER_BASE + "/"
					+ FacadeControllerURLs.LAUNCHAME);
			result = restTemplate.postForEntity(url, request, String.class ).getBody();
		
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
