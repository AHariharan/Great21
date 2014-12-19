package com.adansoft.great21.gamemanager.JMSHandlers;

import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.support.MessageBuilder;

import com.adansoft.great21.games.GameListConstants;
import com.adansoft.great21.games.SevenCardRummy;
import com.adansoft.great21.models.Game;
import com.adansoft.great21.restschemas.CreateGameRequest;

public class GameManagerDelegateImpl implements GameManagerDelegate {
	
	private Game createGame(CreateGameRequest request)
	{
		 System.out.println("Create Game request invoked");
		 Game game = new SevenCardRummy("Test", "test", "Beginner", GameListConstants.GAMELIST_SEVENCARD_TYPE);
		 return game;
	}

	@Override
	@SendTo("game")
	public Message<Game> handleMessage(Message<CreateGameRequest> request) {
		Game game = createGame(request.getPayload());
		Message<Game> reply = MessageBuilder.withPayload(game).setHeader("code", 1234).build();
		return reply;
	}
	
	@Override
	@SendTo("game")
	public Message<Game> handleMessage(CreateGameRequest request) {
		Game game = createGame(request);
		Message<Game> reply = MessageBuilder.withPayload(game).setHeader("code", 1234).build();
		return reply;
	}
	

}
