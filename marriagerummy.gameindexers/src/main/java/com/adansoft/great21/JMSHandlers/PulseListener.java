package com.adansoft.great21.JMSHandlers;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;

import com.adansoft.great21.CacheModels.GameIndexerCache;
import com.adansoft.great21.CacheModels.GameManagerCache;
import com.adansoft.great21.jms.models.GameManagerHeartBeat;


public class PulseListener implements MessageListener {
	
	@Autowired
	private MappingJackson2MessageConverter converter;
	
	public void onMessage(Message msg) {
		
		try {
			msg.acknowledge();
			msg.getJMSTimestamp();
			System.out.println("Msg Received : " + msg);
			if(msg instanceof TextMessage)
			{
				handleHeartBeatMessages(msg);
				printGameIndexerCache();
			}
		} catch (JMSException e) {			
			e.printStackTrace();
		}
		
	}
	
	private void handleHeartBeatMessages(Message msg) throws JMSException
	{
		TextMessage receivedmessage = (TextMessage) msg;
		System.out.println("Internal : " + receivedmessage.getText());				
		converter.setTypeIdPropertyName("Object");				
		GameManagerHeartBeat hearbeat = (GameManagerHeartBeat)	converter.fromMessage(msg);
		GameManagerCache cache = new GameManagerCache(hearbeat,msg.getJMSTimestamp());
		GameIndexerCache.getInstance().addGameManager(cache);
	}
	
	private void printGameIndexerCache()
	{
		System.out.println(" Print Game Indexer Cache Invoked :- ") ;
		for(String cachekey : GameIndexerCache.getInstance().getGamemanagerlist().keySet())
		{
			GameManagerCache cache = GameIndexerCache.getInstance().getGamemanagerlist().get(cachekey);
			System.out.println("Game Manager : - " +cache);
		}
	}

}
