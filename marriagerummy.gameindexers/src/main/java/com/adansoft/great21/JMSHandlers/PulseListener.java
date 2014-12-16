package com.adansoft.great21.JMSHandlers;

import javax.jms.Message;
import javax.jms.MessageListener;

public class PulseListener implements MessageListener {

	public void onMessage(Message msg) {
		
		System.out.println("Message Received : " + msg);
		
	}

}
