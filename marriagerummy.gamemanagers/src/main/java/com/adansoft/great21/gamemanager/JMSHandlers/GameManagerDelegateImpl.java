package com.adansoft.great21.gamemanager.JMSHandlers;

import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.support.MessageBuilder;

import com.adansoft.great21.games.GameLobby;
import com.adansoft.great21.helpers.GameBrowserHelper;
import com.adansoft.great21.models.Game;
import com.adansoft.great21.restschemas.CreateGameRequest;
import com.adansoft.great21.restschemas.GetGameListinLobbyRequest;

public class GameManagerDelegateImpl implements GameManagerDelegate {

	@Override
	@SendTo("game")
	public Message<Game> handleMessage(CreateGameRequest request) {
		Game game = GameBrowserHelper.createGame(request);
		Message<Game> reply = MessageBuilder.withPayload(game).build();
		return reply;
	}

	@Override
	@SendTo("lobby")
	public Message<GameLobby> handleMessage(GetGameListinLobbyRequest request) {
		GameLobby lobby = GameBrowserHelper.getGameList(request);
		Message<GameLobby> reply = MessageBuilder.withPayload(lobby).build();
		return reply;
	}

	

	
	
	
	
	

}
