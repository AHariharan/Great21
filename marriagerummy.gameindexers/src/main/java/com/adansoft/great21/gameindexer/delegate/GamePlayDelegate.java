package com.adansoft.great21.gameindexer.delegate;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import com.adansoft.great21.gameindexer.helpers.CacheServerGameIndexCache;
import com.adansoft.great21.models.Card;
import com.adansoft.great21.restschemas.GetCardsRequest;
import com.adansoft.great21.uischemas.GetCardResponse;


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

}
