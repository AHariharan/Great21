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
import com.adansoft.great21.restschemas.GetCardsRequest;
import com.adansoft.great21.uimediation.UIMediationMapper;
import com.adansoft.great21.uischemas.AddGameChatRequest;
import com.adansoft.great21.uischemas.GetCardResponse;

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
					+ FacadeControllerURLs.GETCHATMESSAGES);
			result = restTemplate.postForEntity(url, request, GetCardResponse.class).getBody();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}

}
