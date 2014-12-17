package com.adansoft.great21.JMSHandlers;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;

import com.adansoft.great21.jms.models.GameManagerHeartBeat;


public class PulseListener implements MessageListener {
	
	@Autowired
	private MappingJackson2MessageConverter converter;
	
	public void onMessage(Message msg) {
		
		try {
			msg.acknowledge();
			System.out.println("Msg Received : " + msg);
			if(msg instanceof TextMessage)
			{
				TextMessage receivedmessage = (TextMessage) msg;
				System.out.println("Internal : " + receivedmessage.getText());				
				converter.setTypeIdPropertyName("HEARTBEAT");				
				GameManagerHeartBeat hearbeat = (GameManagerHeartBeat)	converter.fromMessage(msg);
				System.out.println("heartbeat" + hearbeat);
				
			}
		} catch (JMSException e) {
			
			e.printStackTrace();
		}
		
	}

}
