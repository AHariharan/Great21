package com.adansoft.great21.helpers;

import java.util.ArrayList;
import java.util.HashMap;

import com.adansoft.great21.games.GameListConstants;
import com.adansoft.great21.games.GameLobby;
import com.adansoft.great21.models.Card;
import com.adansoft.great21.models.Game;
import com.adansoft.great21.models.Player;
import com.adansoft.great21.restschemas.DeclareGameRequest;
import com.adansoft.great21.restschemas.DeclareGameUIRequest;
import com.adansoft.great21.restschemas.ShowGameRequest;
import com.adansoft.great21.restschemas.ShowGameResult;
import com.adansoft.great21.restschemas.ShowGameUIRequest;

public class UtilityHelper {

	public static Game getGamefromLobby(GameLobby lobby, String gameInstanceID,
			String gameType) {
		Game game = null;
		if (gameType.equals(GameListConstants.GAMELIST_SEVENCARD_CLOSED_TYPE))
			game = lobby.getSevencard_closed_gamelist().getGame(gameInstanceID);
		else if (gameType
				.equals(GameListConstants.GAMELIST_SEVENCARD_OPEN_TYPE))
			game = lobby.getSevencard_open_gamelist().getGame(gameInstanceID);
		else if (gameType
				.equals(GameListConstants.GAMELIST_THIRTEENCARD_CLOSED_TYPE))
			game = lobby.getThirteencard_closed_gamelist().getGame(
					gameInstanceID);
		else if (gameType
				.equals(GameListConstants.GAMELIST_THIRTEENCARD_OPEN_TYPE))
			game = lobby.getThirteencard_open_gamelist()
					.getGame(gameInstanceID);
		else if (gameType.equals(GameListConstants.GAMELIST_TWENTYONECARD_TYPE))
			game = lobby.getTwentyonecard_gamelist().getGame(gameInstanceID);

		return game;
	}

	public static Game deleteGamefromLobby(GameLobby lobby, Game game,
			String gameType) {

		if (gameType.equals(GameListConstants.GAMELIST_SEVENCARD_CLOSED_TYPE))
			lobby.getSevencard_closed_gamelist().getGamelist().remove(game);
		else if (gameType
				.equals(GameListConstants.GAMELIST_SEVENCARD_OPEN_TYPE))
			lobby.getSevencard_open_gamelist().getGamelist().remove(game);
		else if (gameType
				.equals(GameListConstants.GAMELIST_THIRTEENCARD_CLOSED_TYPE))
			lobby.getThirteencard_closed_gamelist().getGamelist().remove(game);
		else if (gameType
				.equals(GameListConstants.GAMELIST_THIRTEENCARD_OPEN_TYPE))
			lobby.getThirteencard_open_gamelist().getGamelist().remove(game);
		else if (gameType.equals(GameListConstants.GAMELIST_TWENTYONECARD_TYPE))
			lobby.getTwentyonecard_gamelist().getGamelist().remove(game);

		return game;
	}

	public static Player getPlayerinGame(Game game, String nickname) {
		try {
			for (Player player : game.getCurrentGameRound().getPlayerlist()) {
				if (player.getNickName().equals(nickname))
					return player;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Card getCardforPlayerFromUICard(Player player,
			String cardinstanceid) {
		System.out
				.println("getCardforPlayerFromUICard Incoming cardinstanceid "
						+ cardinstanceid);
		Card card = null;
		for (Card cucard : player.getPlayerCards()) {
			if (cucard.getInstanceID().equals(cardinstanceid)) {
				card = cucard;
				return card;
			}
		}
		return card;
	}

	public static DeclareGameRequest convert(DeclareGameUIRequest request,
			Player player) {
		DeclareGameRequest gamerequest = new DeclareGameRequest();
		Card closedcard = getCardforPlayerFromUICard(player,
				request.getClosedCardInstanceid());
		gamerequest.setClosedCard(closedcard);
		gamerequest.setGameInstanceID(request.getGameInstanceID());
		gamerequest.setGameType(request.getGameType());
		gamerequest.setLobbyName(request.getLobbyName());
		gamerequest.setNickName(request.getNickName());
		HashMap<String, Card[]> gamemeldlist = new HashMap<String, Card[]>();
		for (String cardgroup : request.getMeldlist().keySet()) {
			ArrayList<Card> cardlist = new ArrayList<Card>();
			for (String UiCardInstanceID : request.getMeldlist().get(cardgroup)) {
				Card card = getCardforPlayerFromUICard(player, UiCardInstanceID);
				if (card == null) {
					System.out
							.println("******* WARN ****** PLAYER CARD INCONSISTENT STATE *********");
				} else {
					cardlist.add(card);
				}
			}
			Card[] finalarrayofcards = cardlist.toArray(new Card[cardlist
					.size()]);
			gamemeldlist.put(cardgroup, finalarrayofcards);
		}
		gamerequest.setMeldlist(gamemeldlist);
		return gamerequest;
	}

	public static ShowGameRequest convert(ShowGameUIRequest request,
			Player player) {
		ShowGameRequest gamerequest = new ShowGameRequest();
		gamerequest.setGameInstanceID(request.getGameInstanceID());
		gamerequest.setGameType(request.getGameType());
		gamerequest.setLobbyName(request.getLobbyName());
		gamerequest.setNickName(request.getNickName());
		HashMap<String, Card[]> gamemeldlist = new HashMap<String, Card[]>();
		for (String cardgroup : request.getMeldlist().keySet()) {
			ArrayList<Card> cardlist = new ArrayList<Card>();
			for (String UiCardInstanceID : request.getMeldlist().get(cardgroup)) {
				Card card = getCardforPlayerFromUICard(player, UiCardInstanceID);
				if (card == null) {
					System.out
							.println("******* WARN ****** PLAYER CARD INCONSISTENT STATE *********");
				} else {
					cardlist.add(card);
				}
			}
			Card[] finalarrayofcards = cardlist.toArray(new Card[cardlist
					.size()]);
			gamemeldlist.put(cardgroup, finalarrayofcards);
		}
		gamerequest.setMeldlist(gamemeldlist);
		return gamerequest;
	}

	public static int getTotalPointsforPlayerinGame(String nickname, Game game) {
		int currentPoints = 0;
		for (String round : game.getGameContent().getPlayerPointsMap().keySet()) {
			HashMap<String, Integer> mapdata = game.getGameContent()
					.getPlayerPointsMap().get(round);
			if (mapdata != null) {
				for (String curnickname : mapdata.keySet()) {
					if (curnickname.equals(nickname)) {
						currentPoints = currentPoints
								+ mapdata.get(curnickname);
					}
				}
			}
		}

		return currentPoints;
	}

}
