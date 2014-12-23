package com.adansoft.great21.gamemanager.JMSHandlers;

import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.support.MessageBuilder;

import com.adansoft.great21.games.GameLobby;
import com.adansoft.great21.helpers.GameBrowserHelper;
import com.adansoft.great21.models.Game;
import com.adansoft.great21.restschemas.AddPlayerRequest;
import com.adansoft.great21.restschemas.CreateGameRequest;
import com.adansoft.great21.restschemas.DeleteGameRequest;
import com.adansoft.great21.restschemas.GetGameListinLobbyRequest;
import com.adansoft.great21.restschemas.GetGameListinLobbyResponse;
import com.adansoft.great21.restschemas.RemovePlayerRequest;

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
	public Message<GetGameListinLobbyResponse> handleMessage(GetGameListinLobbyRequest request) {
		GameLobby lobby = GameBrowserHelper.getGameList(request);
		GetGameListinLobbyResponse response = new GetGameListinLobbyResponse(request.getLobbyName(),lobby);
		Message<GetGameListinLobbyResponse> reply = MessageBuilder.withPayload(response).build();
		return reply;
	}

	@Override
	public Message<String> handleMessage(DeleteGameRequest request) {
		String result = GameBrowserHelper.deleteGame(request);
		Message<String> reply = MessageBuilder.withPayload(result).build();
		return reply;
	}
	
	
	@Override
	public Message<String> handleMessage(AddPlayerRequest request) {
		String result = GameBrowserHelper.addPlayertoGame(request);
		Message<String> reply = MessageBuilder.withPayload(result).build();
		return reply;
	}

	@Override
	public Message<String> handleMessage(RemovePlayerRequest request)
	{
		String result = GameBrowserHelper.removePlayerFromGame(request);
		Message<String> reply = MessageBuilder.withPayload(result).build();
		return reply;
	}

	
	
	
	
	

}
