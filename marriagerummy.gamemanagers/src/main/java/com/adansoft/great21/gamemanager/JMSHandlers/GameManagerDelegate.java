package com.adansoft.great21.gamemanager.JMSHandlers;

import org.springframework.messaging.Message;

import com.adansoft.great21.games.GameList;
import com.adansoft.great21.games.GameLobby;
import com.adansoft.great21.models.Game;
import com.adansoft.great21.restschemas.AddPlayerRequest;
import com.adansoft.great21.restschemas.CreateGameRequest;
import com.adansoft.great21.restschemas.GetGameListinLobbyRequest;
import com.adansoft.great21.restschemas.RemovePlayerRequest;

public interface GameManagerDelegate {

	public Message<Game> handleMessage(CreateGameRequest request);
	
	public Message<GameLobby> handleMessage(GetGameListinLobbyRequest request);
	
	public Message<String> handleMessage(AddPlayerRequest request);
	
	public Message<String> handleMessage(RemovePlayerRequest request);
	
}
