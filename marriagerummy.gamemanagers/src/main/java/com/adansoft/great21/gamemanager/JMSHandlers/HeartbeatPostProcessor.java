package com.adansoft.great21.gamemanager.JMSHandlers;

import java.util.Enumeration;

import javax.jms.JMSException;
import javax.jms.Message;

import org.springframework.jms.core.MessagePostProcessor;

public class HeartbeatPostProcessor implements MessagePostProcessor {

	@Override
	public Message postProcessMessage(Message message) throws JMSException {		
		Enumeration properties = message.getPropertyNames();
		while(properties.hasMoreElements())
		{
			String propertyname = (String) properties.nextElement();
			System.out.println("Message properties : - " + propertyname + " : " +message.getObjectProperty(propertyname));
		}
		return message;
	}

}
