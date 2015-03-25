package com.adansoft.great21.gamemanager.JMSHandlers;

import java.util.ArrayList;

import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.support.MessageBuilder;

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
import com.adansoft.great21.restschemas.GetPlayerPointsRequest;
import com.adansoft.great21.restschemas.GetPlayerPointsResponse;
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
	public Message<GetGameListinLobbyResponse> handleMessage(
			GetGameListinLobbyRequest request) {
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
		Message<String> reply = null;
		try {
			String result = GameBrowserHelper.deleteGame(request);
			reply = MessageBuilder.withPayload(result).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reply;
	}

	@Override
	public Message<AddPlayerResponse> handleMessage(AddPlayerRequest request) {
		Message<AddPlayerResponse> reply = null;
		try {
			AddPlayerResponse result = GameBrowserHelper
					.addPlayertoGame(request);
			reply = MessageBuilder.withPayload(result).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reply;
	}

	@Override
	public Message<String> handleMessage(RemovePlayerRequest request) {
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
		try {
			String result = GameBrowserHelper.launchGame(request);
			reply = MessageBuilder.withPayload(result).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reply;
	}

	@Override
	public Message<ArrayList<Card>> handleMessage(GetCardsRequest request) {
		Message<ArrayList<Card>> reply = null;
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

}
