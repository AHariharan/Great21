package com.adansoft.great21.gameindexer.delegate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.support.converter.SimpleMessageConverter;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import com.adansoft.great21.CacheModels.GameIndexerCache;
import com.adansoft.great21.CacheModels.GameManagerCache;
import com.adansoft.great21.exceptions.NoGameManagerAvailableException;
import com.adansoft.great21.gameindexers.deserializers.PlayerDeserializer;
import com.adansoft.great21.gameindexers.deserializers.PlayerKeyDeserializer;
import com.adansoft.great21.games.SevenCardRummy;
import com.adansoft.great21.models.Game;
import com.adansoft.great21.models.Player;
import com.adansoft.great21.restschemas.CreateGameRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;


public class GameBrowserDelegate {


	@Autowired
	private JmsMessagingTemplate messageTemplate;
	
	//@Autowired
//	private MappingJackson2MessageConverter converter;

	//private org.springframework.messaging.converter.MappingJackson2MessageConverter messageconverter = new org.springframework.messaging.converter.MappingJackson2MessageConverter();

	/*@PostConstruct
	public void init()
	{
		converter.setTargetType(MessageType.TEXT);
		converter.setTypeIdPropertyName("Object");	
	}
		*/
	
	
	public Game createGame(CreateGameRequest request)
	{
		Game createdGame = null;
		try
		{
		//	converter.setTargetType(MessageType.TEXT);
		//	converter.setTypeIdPropertyName("Object");
		
			
		   GameManagerCache cache = GameIndexerCache.getInstance().getNextAvailableGameManager();
		   String destinationName = cache.getRequestQueue();	
		   
		 /*  ObjectMapper mapper = new ObjectMapper();
		   
		   SimpleModule module = new SimpleModule();
		   //module.addSerializer(Player.class, new PlayerSerializer());
		  // module.addKeySerializer(Player.class, new PlayerSerializer());
		   module.addDeserializer(Player.class, new PlayerDeserializer());
		   module.addKeyDeserializer(Player.class, new PlayerKeyDeserializer());
		   mapper.registerModule(module);
		 //  messageconverter.setObjectMapper(mapper);
		  // converter.setObjectMapper(mapper);
		//   messageTemplate.setJmsMessageConverter(converter);
		//   messageTemplate.setMessageConverter(messageconverter);
		   SimpleMessageConverter convertor = new SimpleMessageConverter();
		   messageTemplate.setJmsMessageConverter(convertor);*/
		   Message<CreateGameRequest> requestjmsmessage = MessageBuilder.withPayload(request).build();
		   @SuppressWarnings("unchecked")
		   Message<Game> game =  (Message<Game>) messageTemplate.sendAndReceive(destinationName, requestjmsmessage);
		   System.out.println("Got Reply :- " + game.getPayload());
		   createdGame = game.getPayload();
		   
		}catch(NoGameManagerAvailableException ex)
		{
			ex.printStackTrace();
			// Construct Error Object here
			return null;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return null;
		}
		return createdGame;
	}
	
	
}
