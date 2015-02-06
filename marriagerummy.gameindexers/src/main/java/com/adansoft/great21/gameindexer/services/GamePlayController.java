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
import com.adansoft.great21.restschemas.AddCardToHandRequest;
import com.adansoft.great21.restschemas.DeclareGameRequest;
import com.adansoft.great21.restschemas.DeclareGameResult;
import com.adansoft.great21.restschemas.DeclareGameUIRequest;
import com.adansoft.great21.restschemas.DropCardFromHandRequest;
import com.adansoft.great21.restschemas.GetCardsRequest;
import com.adansoft.great21.restschemas.GetJokerRequest;
import com.adansoft.great21.restschemas.GetNextCardFromDeckRequest;
import com.adansoft.great21.restschemas.GetOpenCardRequest;
import com.adansoft.great21.restschemas.GetPlayerTurnRequest;
import com.adansoft.great21.restschemas.ShowJokerRequest;
import com.adansoft.great21.restschemas.SkipTurnRequest;
import com.adansoft.great21.uischemas.GetCardResponse;
import com.adansoft.great21.uischemas.GetSingleCardResponse;


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
	public GetSingleCardResponse getNextCardFromDeck(@RequestBody GetNextCardFromDeckRequest request)
	{
		return delegate.getNextCardFromDeck(request);
	}
	
	@RequestMapping(value = GameIndexerServiceURLs.GETJOKER, method = RequestMethod.POST)
	public GetSingleCardResponse getJokerForGame(@RequestBody GetJokerRequest request)
	{
		return delegate.getJokerForGame(request);
	}
	
	@RequestMapping(value = GameIndexerServiceURLs.GETOPENCARD, method = RequestMethod.POST)
	public GetSingleCardResponse getOpenCardGame(@RequestBody GetOpenCardRequest request)
	{
		return delegate.getOpenCardGame(request);
	}
	
	@RequestMapping(value = GameIndexerServiceURLs.ADDCARDTOHAND, method = RequestMethod.POST)
	public String addCardToHand(@RequestBody AddCardToHandRequest request)
	{
		return delegate.addCardToHand(request);
	}
	
	@RequestMapping(value = GameIndexerServiceURLs.DROPCARDFROMHAND, method = RequestMethod.POST)
	public String dropCardFromHand(@RequestBody DropCardFromHandRequest request)
	{
		return delegate.dropCardFromHand(request);
	}
	
	
	@RequestMapping(value = GameIndexerServiceURLs.SHOWJOKER, method = RequestMethod.POST)
	public GetSingleCardResponse showJoker(@RequestBody ShowJokerRequest request)
	{
		return delegate.showJoker(request);
	}
	
	@RequestMapping(value = GameIndexerServiceURLs.PLAYERTURN, method = RequestMethod.POST)
	public Integer getPlayerTurn(@RequestBody GetPlayerTurnRequest request)
	{
		return delegate.getPlayerTurn(request);
	}
	
	@RequestMapping(value = GameIndexerServiceURLs.SKIPPLAYERTURN, method = RequestMethod.POST)
	public String skipPlayerTurn(@RequestBody SkipTurnRequest request)
	{
		return delegate.skipPlayerTurn(request);
	}
	
	@RequestMapping(value = GameIndexerServiceURLs.DECLAREGAME, method = RequestMethod.POST)
	public DeclareGameResult declareGame(@RequestBody DeclareGameUIRequest request)
	{
		return delegate.declareGame(request);
	}
	
	@ExceptionHandler
	public String handleBadRequest(Exception ex,HttpServletRequest request)
	{
		System.out.println("*********** Incoming request : " + request);
		ex.printStackTrace();
		return ex.getMessage();
	}

}
