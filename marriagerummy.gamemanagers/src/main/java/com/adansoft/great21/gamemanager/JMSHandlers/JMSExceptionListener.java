package com.adansoft.great21.gamemanager.JMSHandlers;

import javax.jms.ExceptionListener;
import javax.jms.JMSException;

import org.springframework.stereotype.Component;



@Component
public class JMSExceptionListener implements ExceptionListener {
	public void onException(final JMSException e) {
	
		System.out.println("JMS Exception has occured :- " + e.getMessage());
		e.printStackTrace();
	}
}
