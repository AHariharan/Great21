package com.adansoft.great21.gamemanager.JMSHandlers;

import java.util.ArrayList;

import org.springframework.messaging.Message;

import com.adansoft.great21.games.GameList;
import com.adansoft.great21.games.GameLobby;
import com.adansoft.great21.models.Game;
import com.adansoft.great21.models.Player;
import com.adansoft.great21.restschemas.AddPlayerRequest;
import com.adansoft.great21.restschemas.AddPlayerResponse;
import com.adansoft.great21.restschemas.CreateGameRequest;
import com.adansoft.great21.restschemas.DeleteGameRequest;
import com.adansoft.great21.restschemas.GetGameListinLobbyRequest;
import com.adansoft.great21.restschemas.GetGameListinLobbyResponse;
import com.adansoft.great21.restschemas.GetPlayersinGameRequest;
import com.adansoft.great21.restschemas.RemovePlayerRequest;

public interface GameManagerDelegate {

	public Message<Game> handleMessage(CreateGameRequest request);
	
	public Message<GetGameListinLobbyResponse> handleMessage(GetGameListinLobbyRequest request);
	
	public Message<String> handleMessage(DeleteGameRequest request);
	
	public Message<AddPlayerResponse> handleMessage(AddPlayerRequest request);
	
	public Message<String> handleMessage(RemovePlayerRequest request);
	
	public Message<ArrayList<Player>> handleMessage(GetPlayersinGameRequest request);
	
	
	
}
