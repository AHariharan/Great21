package com.adansoft.great21.gamemanager.JMSHandlers;

import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jca.cci.connection.SingleConnectionFactory;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;





import com.adansoft.great21.gamemanager.config.JMSConfiguration;
import com.adansoft.great21.games.GameListConstants;
import com.adansoft.great21.games.SevenCardRummy;
import com.adansoft.great21.models.Game;

@Import({JMSConfiguration.class})
@Configuration
public class QueueListener implements MessageListener {

	@Autowired
	private  MappingJackson2MessageConverter converter;
	
	@Autowired
	private JmsTemplate template;
	
	@Override
	public void onMessage(Message msg) {
		try
		{
		System.out.println("Message Received :- " + msg);
	    if(msg instanceof TextMessage)
	    {
	    	converter.setTargetType(MessageType.TEXT);
	    	converter.setTypeIdPropertyName("Object");
	    	TextMessage receviedmsg = (TextMessage) msg;
	    	
	    	System.out.println("Received Game Request : " + receviedmsg.getText());
	    	
	    	//Send Reply
	   
		    Destination destination = receviedmsg.getJMSReplyTo();
		    Game game = new SevenCardRummy("Test", "test", "Beginner", GameListConstants.GAMELIST_SEVENCARD_TYPE);
		    System.out.println ("Reply Game :- " + game);
		    template.setMessageConverter(converter);
		    template.convertAndSend(destination, game);
	    }
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
