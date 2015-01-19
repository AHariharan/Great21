package com.adansoft.great21.gameindexer.services;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.adansoft.great21.gameindexer.delegate.GamePlayDelegate;
import com.adansoft.great21.models.Card;
import com.adansoft.great21.restschemas.GetCardsRequest;
import com.adansoft.great21.restschemas.GetJokerRequest;
import com.adansoft.great21.restschemas.GetNextCardFromDeckRequest;
import com.adansoft.great21.restschemas.GetOpenCardRequest;
import com.adansoft.great21.uischemas.GetCardResponse;


@RestController
@RequestMapping(GameIndexerServiceURLs.GAMEPLAY_BASE)
public class GamePlayController {
	
	@Autowired
	GamePlayDelegate delegate;
	
	@RequestMapping(value = GameIndexerServiceURLs.GETCARDS, method = RequestMethod.POST)
	public GetCardResponse getCards(@RequestBody GetCardsRequest request)
	{
		return delegate.getCards(request);
	}
	
	@RequestMapping(value = GameIndexerServiceURLs.GETNEXTCARDFROMDECK, method = RequestMethod.POST)
	public Card getNextCardFromDeck(@RequestBody GetNextCardFromDeckRequest request)
	{
		return delegate.getNextCardFromDeck(request);
	}
	
	@RequestMapping(value = GameIndexerServiceURLs.GETJOKER, method = RequestMethod.POST)
	public Card getJokerForGame(@RequestBody GetJokerRequest request)
	{
		return delegate.getJokerForGame(request);
	}
	
	@RequestMapping(value = GameIndexerServiceURLs.GETOPENCARD, method = RequestMethod.POST)
	public Card getOpenCardGame(@RequestBody GetOpenCardRequest request)
	{
		return delegate.getOpenCardGame(request);
	}
	
	@ExceptionHandler
	public String handleBadRequest(Exception ex,HttpServletRequest request)
	{
		System.out.println("*********** Incoming request : " + request);
		ex.printStackTrace();
		return ex.getMessage();
	}

}
