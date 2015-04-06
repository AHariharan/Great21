package com.adansoft.great21.gamemanager.JMSHandlers;

import java.util.ArrayList;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.client.RestTemplate;

import com.adansoft.great21.dataccess.helpers.GameManagertoDataAccessMapper;
import com.adansoft.great21.games.GameLobby;
import com.adansoft.great21.helpers.GameBrowserHelper;
import com.adansoft.great21.helpers.GamePlayHelper;
import com.adansoft.great21.models.Card;
import com.adansoft.great21.models.Game;
import com.adansoft.great21.models.Player;
import com.adansoft.great21.restschemas.AddCardToHandRequest;
import com.adansoft.great21.restschemas.AddPlayerRequest;
import com.adansoft.great21.restschemas.AddPlayerResponse;
import com.adansoft.great21.restschemas.CreateGameRequest;
import com.adansoft.great21.restschemas.DeclareGameResult;
import com.adansoft.great21.restschemas.DeclareGameUIRequest;
import com.adansoft.great21.restschemas.DeleteGameRequest;
import com.adansoft.great21.restschemas.DropCardFromHandRequest;
import com.adansoft.great21.restschemas.FinishGameRoundRequest;
import com.adansoft.great21.restschemas.GetActivePlayersinGameRequest;
import com.adansoft.great21.restschemas.GetCardsRequest;
import com.adansoft.great21.restschemas.GetGameListinLobbyRequest;
import com.adansoft.great21.restschemas.GetGameListinLobbyResponse;
import com.adansoft.great21.restschemas.GetInfoBlockRequest;
import com.adansoft.great21.restschemas.GetInfoBlockResponse;
import com.adansoft.great21.restschemas.GetJokerRequest;
import com.adansoft.great21.restschemas.GetNextCardFromDeckRequest;
import com.adansoft.great21.restschemas.GetOpenCardRequest;
import com.adansoft.great21.restschemas.GetPlayerPointsRequest;
import com.adansoft.great21.restschemas.GetPlayerPointsResponse;
import com.adansoft.great21.restschemas.GetPlayerTurnRequest;
import com.adansoft.great21.restschemas.GetPlayersinGameRequest;
import com.adansoft.great21.restschemas.GetPlayersinGameResponse;
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

public class GameManagerDelegateImpl implements GameManagerDelegate {
	
	@Autowired
	GameManagertoDataAccessMapper gametodataaccessmapper;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	private ThreadPoolTaskExecutor taskExecutor;

	
	@Override
	@SendTo("game")
	public Message<Game> handleMessage(CreateGameRequest request) {
		System.out.println(Calendar.getInstance().getTime()+ " Received CreateGame Request : " + request.getGameType() + "-" + request.getLobbyType());
		Game game = GameBrowserHelper.createGame(gametodataaccessmapper,restTemplate,request,taskExecutor);
		Message<Game> reply = MessageBuilder.withPayload(game).build();
		return reply;
	}

	@Override
	@SendTo("lobby")
	public Message<GetGameListinLobbyResponse> handleMessage(
			GetGameListinLobbyRequest request) {
		System.out.println(Calendar.getInstance().getTime()+ " Received Games in Lobby Request : " + request.getLobbyName());
		Message<GetGameListinLobbyResponse> reply = null;
		try {
			GameLobby lobby = GameBrowserHelper.getGameList(request);
			GetGameListinLobbyResponse response = new GetGameListinLobbyResponse(
					request.getLobbyName(), lobby);
			reply = MessageBuilder.withPayload(response).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reply;
	}

	@Override
	public Message<String> handleMessage(DeleteGameRequest request) {
		System.out.println(Calendar.getInstance().getTime()+ " Received Delete Game Request : " + request.getGameInstanceID());
		Message<String> reply = null;
		try {
			String result = GameBrowserHelper.deleteGame(gametodataaccessmapper,restTemplate,request,taskExecutor);
			reply = MessageBuilder.withPayload(result).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reply;
	}

	@Override
	public Message<AddPlayerResponse> handleMessage(AddPlayerRequest request) {
		Message<AddPlayerResponse> reply = null;
		System.out.println(Calendar.getInstance().getTime()+ " Received Add Player to Game Request : " + request.getGameInstanceID());
		try {
			AddPlayerResponse result = GameBrowserHelper
					.addPlayertoGame(gametodataaccessmapper,restTemplate,request);
			reply = MessageBuilder.withPayload(result).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reply;
	}

	@Override
	public Message<String> handleMessage(RemovePlayerRequest request) {
		System.out.println(Calendar.getInstance().getTime()+ " Received Remove Player from  Game Request : " + request.getGameInstanceID());
		Message<String> reply = null;
		try {
			String result = GameBrowserHelper.removePlayerFromGame(request);
			reply = MessageBuilder.withPayload(result).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reply;
	}

	@Override
	public Message<ArrayList<Player>> handleMessage(
			GetPlayersinGameRequest request) {
		System.out.println(Calendar.getInstance().getTime()+ " Received Get Players in Game Request : " + request.getGameInstanceID());
		Message<ArrayList<Player>> reply = null;
		try {
			ArrayList<Player> result = GameBrowserHelper
					.getPlayersinGame(request);
			reply = MessageBuilder.withPayload(result).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reply;
	}

	@Override
	public Message<String> handleMessage(LaunchGameRequest request) {
		Message<String> reply = null;
		System.out.println(Calendar.getInstance().getTime()+ " Received Launch Game Request : " + request.getGameInstanceID());
		try {
			String result = GameBrowserHelper.launchGame(gametodataaccessmapper,restTemplate,request,taskExecutor);
			reply = MessageBuilder.withPayload(result).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reply;
	}

	@Override
	public Message<ArrayList<Card>> handleMessage(GetCardsRequest request) {
		Message<ArrayList<Card>> reply = null;
		System.out.println(Calendar.getInstance().getTime()+ " Received getCards   Request : " + request.getGameInstanceID());
		try {
			ArrayList<Card> result = GamePlayHelper.getCards(request);
			reply = MessageBuilder.withPayload(result).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reply;
	}

	@Override
	public Message<GetSingleCardResponse> handleMessage(
			GetNextCardFromDeckRequest request) {
		System.out.println(Calendar.getInstance().getTime()+ " Received Get Next Card from Deck   Request : " + request.getGameInstanceID() + " Player " + request.getNickName());
		Message<GetSingleCardResponse> reply = null;
		try {
			GetSingleCardResponse response = GamePlayHelper
					.getNextCardFromDeck(request);
			reply = MessageBuilder.withPayload(response).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reply;
	}

	@Override
	public Message<GetSingleCardResponse> handleMessage(GetJokerRequest request) {
		Message<GetSingleCardResponse> reply = null;
		System.out.println(Calendar.getInstance().getTime()+ " Received Get Joker Card from Deck   Request : " + request.getGameInstanceID() + " Player " + request.getNickName());
		try {
			GetSingleCardResponse response = GamePlayHelper
					.getJokerForGame(request);
			reply = MessageBuilder.withPayload(response).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reply;
	}

	@Override
	public Message<GetSingleCardResponse> handleMessage(
			GetOpenCardRequest request) {
		System.out.println(Calendar.getInstance().getTime()+ " Received Get Open Card from Deck   Request : " + request.getGameInstanceID() + " Player " + request.getNickName());
		Message<GetSingleCardResponse> reply = null;
		try {
			GetSingleCardResponse response = GamePlayHelper
					.getOpenCard(request);
			reply = MessageBuilder.withPayload(response).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reply;
	}

	@Override
	public Message<String> handleMessage(AddCardToHandRequest request) {
		System.out.println(Calendar.getInstance().getTime()+ " Received Add Card to Hand Request : " + request.getCard());
		Message<String> reply = null;
		try {
			String response = GamePlayHelper.addCardToHand(request);
			reply = MessageBuilder.withPayload(response).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reply;
	}

	@Override
	public Message<String> handleMessage(DropCardFromHandRequest request) {
		Message<String> reply = null;
		System.out.println(Calendar.getInstance().getTime()+ " Received Drop Card from Hand Request : " + request.getCard());
		try {
			String response = GamePlayHelper.dropCardFromHand(request);
			reply = MessageBuilder.withPayload(response).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reply;
	}

	@Override
	public Message<GetSingleCardResponse> handleMessage(ShowJokerRequest request) {
		Message<GetSingleCardResponse> reply = null;
		System.out.println(Calendar.getInstance().getTime()+ " Get Show Joker Request : " + request.getGameInstanceID());
		try {
			GetSingleCardResponse response = GamePlayHelper.showJoker(request);
			reply = MessageBuilder.withPayload(response).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reply;
	}

	@Override
	public Message<Integer> handleMessage(GetPlayerTurnRequest request) {
		System.out.println(Calendar.getInstance().getTime()+ " Received Whose Turn Request : " + request.getGameInstanceID());
		Message<Integer> reply = null;
		try {
			int response = GamePlayHelper.getTurn(request);
			reply = MessageBuilder.withPayload(response).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reply;
	}

	@Override
	public Message<String> handleMessage(SkipTurnRequest request) {
		System.out.println(Calendar.getInstance().getTime()+ " Received Skip Turn Request : " + request.getGameInstanceID() + " Player : " + request.getNickName());
		Message<String> reply = null;
		try {
			String response = GamePlayHelper.skipPlayerTurn(request);
			reply = MessageBuilder.withPayload(response).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reply;
	}

	@Override
	public Message<DeclareGameResult> handleMessage(DeclareGameUIRequest request) {
		System.out.println(Calendar.getInstance().getTime()+ " Received Declare Game Request : " + request.getGameInstanceID());
		Message<DeclareGameResult> reply = null;
		try {
			DeclareGameResult response = GamePlayHelper.declareGame(request);
			reply = MessageBuilder.withPayload(response).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reply;
	}

	@Override
	public Message<Card[]> handleMessage(SortCardinHandRequest request) {
		System.out.println(Calendar.getInstance().getTime()+ " Received Sort Card in Hand Request : " + request.getGameInstanceID() + "Player : " + request.getNickName());
		Message<Card[]> reply = null;
		try {
			Card[] response = GamePlayHelper.sortCards(request);
			reply = MessageBuilder.withPayload(response).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reply;
	}

	@Override
	public Message<ShowGameResult> handleMessage(ShowGameUIRequest request) {
		System.out.println(Calendar.getInstance().getTime()+ " Received forced to show game Request : " + request.getGameInstanceID() + " Player : " + request.getNickName());
		Message<ShowGameResult> reply = null;
		try {
			ShowGameResult response = GamePlayHelper.showGame(request);
			reply = MessageBuilder.withPayload(response).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reply;
	}

	@Override
	public Message<PlayerShowStatusResponse> handleMessage(
			PlayerShowStatusRequest request) {
		System.out.println(Calendar.getInstance().getTime()+ " Received PlayerShowStatus Request : " + request.getGameInstanceID() + " Player : " + request.getNickName());
		Message<PlayerShowStatusResponse> reply = null;
		try {
			PlayerShowStatusResponse response = GamePlayHelper
					.showPlayerStatus(request);
			reply = MessageBuilder.withPayload(response).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reply;
	}

	@Override
	public Message<String> handleMessage(FinishGameRoundRequest request) {
		System.out.println(Calendar.getInstance().getTime()+ " Received FinishGameRoundRequest Request : " + request.getGameInstanceID());
		Message<String> reply = null;
		try {
			String response = GamePlayHelper.finishRound(request);
			reply = MessageBuilder.withPayload(response).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reply;
	}

	@Override
	public Message<GetPlayerPointsResponse> handleMessage(
			GetPlayerPointsRequest request) {
		System.out.println(Calendar.getInstance().getTime()+ " Received Show Points Request : " + request.getGameInstanceID());
		Message<GetPlayerPointsResponse> reply = null;
		try {
			GetPlayerPointsResponse response = GamePlayHelper
					.getPointsTable(request);
			reply = MessageBuilder.withPayload(response).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reply;
	}

	@Override
	public Message<GetInfoBlockResponse> handleMessage(
			GetInfoBlockRequest request) {
		System.out.println(Calendar.getInstance().getTime()+ " Received GetBlock Info : " + request.getGameInstanceID());
		Message<GetInfoBlockResponse> reply = null;
		try {
			GetInfoBlockResponse response = GamePlayHelper.getInfoBlock(request);
			reply = MessageBuilder.withPayload(response).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reply;
	}
	
	@Override
	public Message<GetPlayersinGameResponse> handleMessage(
			GetActivePlayersinGameRequest request) {
		System.out.println(Calendar.getInstance().getTime()+ " Received GetActivePlayersinGameRequest Info : " + request.getGameInstanceID());
		Message<GetPlayersinGameResponse> reply = null;
		try {
			GetPlayersinGameResponse response = GamePlayHelper.getActivePlayersinGame(request);
			reply = MessageBuilder.withPayload(response).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reply;
	}
	
	
}
