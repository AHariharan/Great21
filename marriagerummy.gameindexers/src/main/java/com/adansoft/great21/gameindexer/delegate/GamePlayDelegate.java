package com.adansoft.great21.gameindexer.delegate;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import com.adansoft.great21.gameindexer.helpers.CacheServerGameIndexCache;
import com.adansoft.great21.models.Card;
import com.adansoft.great21.restschemas.AddCardToHandRequest;
import com.adansoft.great21.restschemas.DropCardFromHandRequest;
import com.adansoft.great21.restschemas.GetCardsRequest;
import com.adansoft.great21.restschemas.GetJokerRequest;
import com.adansoft.great21.restschemas.GetNextCardFromDeckRequest;
import com.adansoft.great21.restschemas.GetOpenCardRequest;
import com.adansoft.great21.restschemas.ShowJokerRequest;
import com.adansoft.great21.uischemas.GetCardResponse;
import com.adansoft.great21.uischemas.GetSingleCardResponse;


public class GamePlayDelegate {
	
	@Autowired
	private JmsMessagingTemplate messageTemplate;
	

	@Autowired
	private CacheServerGameIndexCache cacheserverinstance;
	
	
	public GetCardResponse getCards(GetCardsRequest request)
	{
		GetCardResponse result = new GetCardResponse();
		try
		{
		String gameinstanceid = request.getGameInstanceID();
		String destination = cacheserverinstance.lookupGameInstanceID(gameinstanceid);
		Message<GetCardsRequest> requestjmsmessage = MessageBuilder.withPayload(request).build();
		@SuppressWarnings("unchecked")
		Message<ArrayList<Card>> reply =  (Message<ArrayList<Card>>) messageTemplate.sendAndReceive(destination, requestjmsmessage);
		result.setNickname(request.getNickName());
		result.setCardlist(reply.getPayload());		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return result;
	}
	
	public GetSingleCardResponse getNextCardFromDeck(GetNextCardFromDeckRequest request)
	{
		GetSingleCardResponse result = null;
		try
		{
			String gameinstanceid = request.getGameInstanceID();
			String destination = cacheserverinstance.lookupGameInstanceID(gameinstanceid);
			Message<GetNextCardFromDeckRequest> requestjmsmessage = MessageBuilder.withPayload(request).build();
			@SuppressWarnings("unchecked")
			Message<GetSingleCardResponse> reply =  (Message<GetSingleCardResponse>) messageTemplate.sendAndReceive(destination, requestjmsmessage);
			result = reply.getPayload();
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}
	
	public GetSingleCardResponse getJokerForGame(GetJokerRequest request)
	{
		GetSingleCardResponse result = null;
		try
		{
			String gameinstanceid = request.getGameInstanceID();
			String destination = cacheserverinstance.lookupGameInstanceID(gameinstanceid);
			Message<GetJokerRequest> requestjmsmessage = MessageBuilder.withPayload(request).build();
			@SuppressWarnings("unchecked")
			Message<GetSingleCardResponse> reply =  (Message<GetSingleCardResponse>) messageTemplate.sendAndReceive(destination, requestjmsmessage);
			result = reply.getPayload();
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}
	
	public GetSingleCardResponse getOpenCardGame(GetOpenCardRequest request)
	{
		GetSingleCardResponse result = null;
		try
		{
			String gameinstanceid = request.getGameInstanceID();
			String destination = cacheserverinstance.lookupGameInstanceID(gameinstanceid);
			Message<GetOpenCardRequest> requestjmsmessage = MessageBuilder.withPayload(request).build();
			@SuppressWarnings("unchecked")
			Message<GetSingleCardResponse> reply =  (Message<GetSingleCardResponse>) messageTemplate.sendAndReceive(destination, requestjmsmessage);
			result = reply.getPayload();
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}
	
	
	public String addCardToHand(AddCardToHandRequest request)
	{
		String result = null;
		try
		{
			String gameinstanceid = request.getCard().getGameInstanceID();
			String destination = cacheserverinstance.lookupGameInstanceID(gameinstanceid);
			Message<AddCardToHandRequest> requestjmsmessage = MessageBuilder.withPayload(request).build();
			@SuppressWarnings("unchecked")
			Message<String> reply =  (Message<String>) messageTemplate.sendAndReceive(destination, requestjmsmessage);
			result = reply.getPayload();
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}
	
	public String dropCardFromHand(DropCardFromHandRequest request)
	{
		String result = null;
		try
		{
			String gameinstanceid = request.getCard().getGameInstanceID();
			String destination = cacheserverinstance.lookupGameInstanceID(gameinstanceid);
			Message<DropCardFromHandRequest> requestjmsmessage = MessageBuilder.withPayload(request).build();
			@SuppressWarnings("unchecked")
			Message<String> reply =  (Message<String>) messageTemplate.sendAndReceive(destination, requestjmsmessage);
			result = reply.getPayload();
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}
	
	public GetSingleCardResponse showJoker(ShowJokerRequest request)
	{
		GetSingleCardResponse result = null;
		try
		{
			String gameinstanceid = request.getGameInstanceID();
			String destination = cacheserverinstance.lookupGameInstanceID(gameinstanceid);
			Message<ShowJokerRequest> requestjmsmessage = MessageBuilder.withPayload(request).build();
			@SuppressWarnings("unchecked")
			Message<GetSingleCardResponse> reply =  (Message<GetSingleCardResponse>) messageTemplate.sendAndReceive(destination, requestjmsmessage);
			result = reply.getPayload();
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}
	

	
	
	

}
