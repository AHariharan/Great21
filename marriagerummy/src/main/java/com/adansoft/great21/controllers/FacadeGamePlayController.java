package com.adansoft.great21.controllers;

import java.net.URI;
import java.util.ArrayList;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.adansoft.great21.exceptions.GameIndexerConfigException;
import com.adansoft.great21.models.Card;
import com.adansoft.great21.restschemas.AddCardToHandRequest;
import com.adansoft.great21.restschemas.DeclareGameRequest;
import com.adansoft.great21.restschemas.DeclareGameResult;
import com.adansoft.great21.restschemas.DropCardFromHandRequest;
import com.adansoft.great21.restschemas.GetCardsRequest;
import com.adansoft.great21.restschemas.GetJokerRequest;
import com.adansoft.great21.restschemas.GetNextCardFromDeckRequest;
import com.adansoft.great21.restschemas.GetOpenCardRequest;
import com.adansoft.great21.restschemas.GetPlayerTurnRequest;
import com.adansoft.great21.restschemas.ShowJokerRequest;
import com.adansoft.great21.restschemas.SkipTurnRequest;
import com.adansoft.great21.uimediation.UIMediationMapper;
import com.adansoft.great21.uischemas.AddGameChatRequest;
import com.adansoft.great21.uischemas.GetCardResponse;
import com.adansoft.great21.uischemas.GetSingleCardResponse;

@RestController
@RequestMapping(FacadeControllerURLs.GAMEPLAY_BASE)
@EnableWebMvcSecurity
public class FacadeGamePlayController {
	

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
	@RequestMapping( value = FacadeControllerURLs.GETCARDS, method = RequestMethod.POST)
	public @ResponseBody GetCardResponse getCards(@RequestBody  GetCardsRequest request,@AuthenticationPrincipal Authentication authentication)	
	{
		GetCardResponse result = null;
		String nickname = authentication.getName();
		request.setNickName(nickname);
		try {
			URI url = new URI(mapper.getIndexerURI() + "/"
					+ FacadeControllerURLs.GAMEPLAY_BASE + "/"
					+ FacadeControllerURLs.GETCARDS);
			result = restTemplate.postForEntity(url, request, GetCardResponse.class).getBody();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}
	
	@Secured("ROLE_USER")
	@RequestMapping( value = FacadeControllerURLs.GETNEXTCARDFROMDECK, method = RequestMethod.POST)
	public @ResponseBody GetSingleCardResponse getNextCardFromDeck(@RequestBody  GetNextCardFromDeckRequest request,@AuthenticationPrincipal Authentication authentication)	
	{
		GetSingleCardResponse result = null;
		String nickname = authentication.getName();
		request.setNickName(nickname);
		try {
			URI url = new URI(mapper.getIndexerURI() + "/"
					+ FacadeControllerURLs.GAMEPLAY_BASE + "/"
					+ FacadeControllerURLs.GETNEXTCARDFROMDECK);
			result = restTemplate.postForEntity(url, request, GetSingleCardResponse.class).getBody();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}
	
	@Secured("ROLE_USER")
	@RequestMapping( value = FacadeControllerURLs.GETJOKER, method = RequestMethod.POST)
	public @ResponseBody GetSingleCardResponse getJokerForGame(@RequestBody  GetJokerRequest request,@AuthenticationPrincipal Authentication authentication)	
	{
		GetSingleCardResponse result = null;
		String nickname = authentication.getName();
		request.setNickName(nickname);
		try {
			URI url = new URI(mapper.getIndexerURI() + "/"
					+ FacadeControllerURLs.GAMEPLAY_BASE + "/"
					+ FacadeControllerURLs.GETJOKER);
			result = restTemplate.postForEntity(url, request, GetSingleCardResponse.class).getBody();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}
	
	@Secured("ROLE_USER")
	@RequestMapping( value = FacadeControllerURLs.GETOPENCARD, method = RequestMethod.POST)
	public @ResponseBody GetSingleCardResponse getOpenCard(@RequestBody GetOpenCardRequest request,@AuthenticationPrincipal Authentication authentication)	
	{
		GetSingleCardResponse result = null;
		String nickname = authentication.getName();
		request.setNickName(nickname);
		try {
			URI url = new URI(mapper.getIndexerURI() + "/"
					+ FacadeControllerURLs.GAMEPLAY_BASE + "/"
					+ FacadeControllerURLs.GETOPENCARD);
			result = restTemplate.postForEntity(url, request, GetSingleCardResponse.class).getBody();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}
	
	@Secured("ROLE_USER")
	@RequestMapping( value = FacadeControllerURLs.ADDCARDTOHAND, method = RequestMethod.POST)
	public @ResponseBody String addCardToHand(@RequestBody AddCardToHandRequest request,@AuthenticationPrincipal Authentication authentication)	
	{
		String result = null;
		String nickname = authentication.getName();
		request.getCard().setNickName(nickname);
		try {
			URI url = new URI(mapper.getIndexerURI() + "/"
					+ FacadeControllerURLs.GAMEPLAY_BASE + "/"
					+ FacadeControllerURLs.ADDCARDTOHAND);
			result = restTemplate.postForEntity(url, request, String.class).getBody();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}
	
	@Secured("ROLE_USER")
	@RequestMapping( value = FacadeControllerURLs.DROPCARDFROMHAND, method = RequestMethod.POST)
	public @ResponseBody String addCardToHand(@RequestBody DropCardFromHandRequest request,@AuthenticationPrincipal Authentication authentication)	
	{
		String result = null;
		String nickname = authentication.getName();
		request.getCard().setNickName(nickname);
		try {
			URI url = new URI(mapper.getIndexerURI() + "/"
					+ FacadeControllerURLs.GAMEPLAY_BASE + "/"
					+ FacadeControllerURLs.DROPCARDFROMHAND);
			result = restTemplate.postForEntity(url, request, String.class).getBody();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}
	
	@Secured("ROLE_USER")
	@RequestMapping( value = FacadeControllerURLs.SHOWJOKER, method = RequestMethod.POST)
	public @ResponseBody GetSingleCardResponse showJoker(@RequestBody ShowJokerRequest request,@AuthenticationPrincipal Authentication authentication)	
	{
		GetSingleCardResponse result = null;
		String nickname = authentication.getName();
		request.setNickName(nickname);
		try {
			URI url = new URI(mapper.getIndexerURI() + "/"
					+ FacadeControllerURLs.GAMEPLAY_BASE + "/"
					+ FacadeControllerURLs.SHOWJOKER);
			result = restTemplate.postForEntity(url, request, GetSingleCardResponse.class).getBody();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}
	
	@Secured("ROLE_USER")
	@RequestMapping( value = FacadeControllerURLs.PLAYERTURN, method = RequestMethod.POST)
	public @ResponseBody Integer getPlayerTurn(@RequestBody GetPlayerTurnRequest request,@AuthenticationPrincipal Authentication authentication)	
	{
		Integer result = null;
		String nickname = authentication.getName();
		request.setNickName(nickname);
		try {
			URI url = new URI(mapper.getIndexerURI() + "/"
					+ FacadeControllerURLs.GAMEPLAY_BASE + "/"
					+ FacadeControllerURLs.PLAYERTURN);
			result = restTemplate.postForEntity(url, request, Integer.class).getBody();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}
	
	@Secured("ROLE_USER")
	@RequestMapping( value = FacadeControllerURLs.DECLAREGAME, method = RequestMethod.POST)
	public @ResponseBody DeclareGameResult declareGame(@RequestBody DeclareGameRequest request,@AuthenticationPrincipal Authentication authentication)	
	{
		DeclareGameResult result = null;
		String nickname = authentication.getName();
		request.setNickName(nickname);
		try {
			URI url = new URI(mapper.getIndexerURI() + "/"
					+ FacadeControllerURLs.GAMEPLAY_BASE + "/"
					+ FacadeControllerURLs.DECLAREGAME);
			result = restTemplate.postForEntity(url, request, DeclareGameResult.class).getBody();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}
	
	@Secured("ROLE_USER")
	@RequestMapping( value = FacadeControllerURLs.SKIPPLAYERTURN, method = RequestMethod.POST)
	public @ResponseBody String skipPlayerTurn(@RequestBody SkipTurnRequest request,@AuthenticationPrincipal Authentication authentication)	
	{
		String result = null;
		String nickname = authentication.getName();
		request.setNickName(nickname);
		try {
			URI url = new URI(mapper.getIndexerURI() + "/"
					+ FacadeControllerURLs.GAMEPLAY_BASE + "/"
					+ FacadeControllerURLs.SKIPPLAYERTURN);
			result = restTemplate.postForEntity(url, request, String.class).getBody();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}
	

}
