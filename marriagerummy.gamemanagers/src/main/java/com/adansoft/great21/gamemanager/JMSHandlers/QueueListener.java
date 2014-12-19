package com.adansoft.great21.gamemanager.JMSHandlers;

import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.activemq.command.ActiveMQBytesMessage;
import org.apache.activemq.command.ActiveMQObjectMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jca.cci.connection.SingleConnectionFactory;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;







import org.springframework.jms.support.converter.SimpleMessageConverter;

import com.adansoft.great21.gameindexers.deserializers.PlayerKeyDeserializer;
import com.adansoft.great21.gameindexers.deserializers.PlayerSerializer;
import com.adansoft.great21.gamemanager.config.JMSConfiguration;
import com.adansoft.great21.games.GameListConstants;
import com.adansoft.great21.games.SevenCardRummy;
import com.adansoft.great21.models.Game;
import com.adansoft.great21.models.Player;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

@Import({JMSConfiguration.class})
@Configuration
public class QueueListener implements MessageListener {

//	@Autowired
//	private  MappingJackson2MessageConverter converter;
	
	@Autowired
	private JmsTemplate template;
	
	@Override
	public void onMessage(Message msg) {
		try
		{
		System.out.println("Message Received :- " + msg);
	    if(msg instanceof ActiveMQObjectMessage)
	    {
	    //	converter.setTargetType(MessageType.TEXT);
	    //	converter.setTypeIdPropertyName("Object");
	    	ActiveMQObjectMessage receviedmsg = (ActiveMQObjectMessage) msg;
	    	
	    	System.out.println("Received Game Request : " + receviedmsg.getObject().getClass());
	    	
	    	//Send Reply
	   
		    Destination destination = receviedmsg.getJMSReplyTo();
		    SevenCardRummy game = new SevenCardRummy("Test", "test", "Beginner", GameListConstants.GAMELIST_SEVENCARD_TYPE);
		    System.out.println ("Reply Game :- " + game);
		    System.out.println("Destinatoin name : " + destination);
		    //ObjectMapper mapper = new ObjectMapper();
			   
			   //SimpleModule module = new SimpleModule();
			   //module.addSerializer(Player.class, new PlayerSerializer());
			  // module.addKeySerializer(Player.class, new PlayerSerializer());
			  // module.addDeserializer(Player.class, new PlayerDeserializer());
			   ///module.addKeyDeserializer(Player.class, new PlayerKeyDeserializer());
			   //mapper.registerModule(module);
			 //  converter.setObjectMapper(mapper);
			  
		   // template.setMessageConverter(converter);
		    SimpleMessageConverter convertor = new SimpleMessageConverter();
		
		    template.setMessageConverter(convertor);
		    template.convertAndSend(destination, game);
	    }
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
