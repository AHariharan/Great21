package com.adansoft.great21.gamemanager.JMSHandlers;

import java.util.ArrayList;

import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.support.MessageBuilder;

import com.adansoft.great21.games.GameLobby;
import com.adansoft.great21.helpers.GameBrowserHelper;
import com.adansoft.great21.models.Card;
import com.adansoft.great21.models.Game;
import com.adansoft.great21.models.Player;
import com.adansoft.great21.restschemas.AddPlayerRequest;
import com.adansoft.great21.restschemas.AddPlayerResponse;
import com.adansoft.great21.restschemas.CreateGameRequest;
import com.adansoft.great21.restschemas.DeleteGameRequest;
import com.adansoft.great21.restschemas.GetCardsRequest;
import com.adansoft.great21.restschemas.GetGameListinLobbyRequest;
import com.adansoft.great21.restschemas.GetGameListinLobbyResponse;
import com.adansoft.great21.restschemas.GetJokerRequest;
import com.adansoft.great21.restschemas.GetNextCardFromDeckRequest;
import com.adansoft.great21.restschemas.GetOpenCardRequest;
import com.adansoft.great21.restschemas.GetPlayersinGameRequest;
import com.adansoft.great21.restschemas.LaunchGameRequest;
import com.adansoft.great21.restschemas.RemovePlayerRequest;
import com.adansoft.great21.uischemas.GetSingleCardResponse;

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
	public Message<AddPlayerResponse> handleMessage(AddPlayerRequest request) {
		AddPlayerResponse result = GameBrowserHelper.addPlayertoGame(request);
		Message<AddPlayerResponse> reply = MessageBuilder.withPayload(result).build();
		return reply;
	}

	@Override
	public Message<String> handleMessage(RemovePlayerRequest request)
	{
		String result = GameBrowserHelper.removePlayerFromGame(request);
		Message<String> reply = MessageBuilder.withPayload(result).build();
		return reply;
	}

	@Override
	public Message<ArrayList<Player>> handleMessage(GetPlayersinGameRequest request)
	{
		ArrayList<Player> result = GameBrowserHelper.getPlayersinGame(request);
		Message<ArrayList<Player>> reply = MessageBuilder.withPayload(result).build();
		return reply;
	}
	
	@Override
	public Message<String> handleMessage(LaunchGameRequest request)
	{
		String result = GameBrowserHelper.launchGame(request);
		Message<String> reply = MessageBuilder.withPayload(result).build();
		return reply;
	}
	
	
	@Override
	public Message<ArrayList<Card>> handleMessage(GetCardsRequest request)
	{
		ArrayList<Card> result = GameBrowserHelper.getCards(request);
		Message<ArrayList<Card>> reply = MessageBuilder.withPayload(result).build();
		return reply;
	}
	
	@Override
	public Message<GetSingleCardResponse> handleMessage(GetNextCardFromDeckRequest request)
	{
		
		GetSingleCardResponse response = GameBrowserHelper.getNextCardFromDeck(request);
		Message<GetSingleCardResponse> reply = MessageBuilder.withPayload(response).build();
		return reply;
	}
	
	@Override
	public Message<GetSingleCardResponse> handleMessage(GetJokerRequest request)
	{
		GetSingleCardResponse response = GameBrowserHelper.getJokerForGame(request);
		Message<GetSingleCardResponse> reply = MessageBuilder.withPayload(response).build();
		return reply;
	}
	
	@Override
	public Message<GetSingleCardResponse> handleMessage(GetOpenCardRequest request)
	{
		GetSingleCardResponse response = GameBrowserHelper.getOpenCard(request);
		Message<GetSingleCardResponse> reply = MessageBuilder.withPayload(response).build();
		return reply;
	}
	

}
