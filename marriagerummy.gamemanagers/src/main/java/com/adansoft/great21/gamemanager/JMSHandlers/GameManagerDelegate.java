package com.adansoft.great21.gamemanager.JMSHandlers;

import java.util.ArrayList;

import org.springframework.messaging.Message;

import com.adansoft.great21.models.Card;
import com.adansoft.great21.models.Game;
import com.adansoft.great21.models.Player;
import com.adansoft.great21.restschemas.AddCardToHandRequest;
import com.adansoft.great21.restschemas.AddPlayerRequest;
import com.adansoft.great21.restschemas.AddPlayerResponse;
import com.adansoft.great21.restschemas.CreateGameRequest;
import com.adansoft.great21.restschemas.DeclareGameRequest;
import com.adansoft.great21.restschemas.DeclareGameResult;
import com.adansoft.great21.restschemas.DeclareGameUIRequest;
import com.adansoft.great21.restschemas.DeleteGameRequest;
import com.adansoft.great21.restschemas.DropCardFromHandRequest;
import com.adansoft.great21.restschemas.FinishGameRoundRequest;
import com.adansoft.great21.restschemas.GetCardsRequest;
import com.adansoft.great21.restschemas.GetGameListinLobbyRequest;
import com.adansoft.great21.restschemas.GetGameListinLobbyResponse;
import com.adansoft.great21.restschemas.GetJokerRequest;
import com.adansoft.great21.restschemas.GetNextCardFromDeckRequest;
import com.adansoft.great21.restschemas.GetOpenCardRequest;
import com.adansoft.great21.restschemas.GetPlayerTurnRequest;
import com.adansoft.great21.restschemas.GetPlayersinGameRequest;
import com.adansoft.great21.restschemas.LaunchGameRequest;
import com.adansoft.great21.restschemas.PlayerShowStatusRequest;
import com.adansoft.great21.restschemas.PlayerShowStatusResponse;
import com.adansoft.great21.restschemas.RemovePlayerRequest;
import com.adansoft.great21.restschemas.ShowGameResult;
import com.adansoft.great21.restschemas.ShowGameUIRequest;
import com.adansoft.great21.restschemas.ShowJokerRequest;
import com.adansoft.great21.restschemas.SkipTurnRequest;
import com.adansoft.great21.restschemas.SortCardinHandRequest;
import com.adansoft.great21.uischemas.GetSingleCardResponse;

public interface GameManagerDelegate {

	public Message<Game> handleMessage(CreateGameRequest request);
	
	public Message<GetGameListinLobbyResponse> handleMessage(GetGameListinLobbyRequest request);
	
	public Message<String> handleMessage(DeleteGameRequest request);
	
	public Message<AddPlayerResponse> handleMessage(AddPlayerRequest request);
	
	public Message<String> handleMessage(RemovePlayerRequest request);
	
	public Message<ArrayList<Player>> handleMessage(GetPlayersinGameRequest request);
	
	public Message<String> handleMessage(LaunchGameRequest request);
	
	public Message<ArrayList<Card>> handleMessage(GetCardsRequest request);
	
	public Message<GetSingleCardResponse> handleMessage(GetNextCardFromDeckRequest request);
	
	public Message<GetSingleCardResponse> handleMessage(GetJokerRequest request);
	
	public Message<GetSingleCardResponse> handleMessage(GetOpenCardRequest request);
	
	public Message<String> handleMessage(AddCardToHandRequest request);
	
	public Message<String> handleMessage(DropCardFromHandRequest request);	
	
	public Message<GetSingleCardResponse> handleMessage(ShowJokerRequest request);
	
	public Message<Integer> handleMessage(GetPlayerTurnRequest request);
	
	public Message<String> handleMessage(SkipTurnRequest request);
	
	public Message<DeclareGameResult> handleMessage(DeclareGameUIRequest request);
	
	public Message<Card[]> handleMessage(SortCardinHandRequest request);
	
	public Message<ShowGameResult> handleMessage(ShowGameUIRequest request);
	
	public Message<PlayerShowStatusResponse> handleMessage(PlayerShowStatusRequest request);
	
	public Message<String> handleMessage(FinishGameRoundRequest request);
}
