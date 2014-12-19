package com.adansoft.great21.gamemanager.JMSHandlers;

import org.springframework.messaging.Message;

import com.adansoft.great21.models.Game;
import com.adansoft.great21.restschemas.CreateGameRequest;

public interface GameManagerDelegate {

	public Message<Game> handleMessage(Message<CreateGameRequest> request);
	
	public Message<Game> handleMessage(CreateGameRequest request);
	
}
