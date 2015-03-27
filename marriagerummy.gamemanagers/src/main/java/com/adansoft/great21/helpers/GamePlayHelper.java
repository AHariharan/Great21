package com.adansoft.great21.helpers;

import java.util.ArrayList;




import java.util.HashMap;

import com.adansoft.great21.games.GameLobby;
import com.adansoft.great21.games.RummyArena;
import com.adansoft.great21.models.Card;
import com.adansoft.great21.models.Game;
import com.adansoft.great21.models.GameRound;
import com.adansoft.great21.models.Player;
import com.adansoft.great21.restschemas.AddCardToHandRequest;
import com.adansoft.great21.restschemas.DeclareGameRequest;
import com.adansoft.great21.restschemas.DeclareGameResult;
import com.adansoft.great21.restschemas.DeclareGameUIRequest;
import com.adansoft.great21.restschemas.DropCardFromHandRequest;
import com.adansoft.great21.restschemas.FinishGameRoundRequest;
import com.adansoft.great21.restschemas.GetCardsRequest;
import com.adansoft.great21.restschemas.GetJokerRequest;
import com.adansoft.great21.restschemas.GetNextCardFromDeckRequest;
import com.adansoft.great21.restschemas.GetOpenCardRequest;
import com.adansoft.great21.restschemas.GetPlayerPointsRequest;
import com.adansoft.great21.restschemas.GetPlayerPointsResponse;
import com.adansoft.great21.restschemas.GetPlayerTurnRequest;
import com.adansoft.great21.restschemas.PlayerShowStatusRequest;
import com.adansoft.great21.restschemas.PlayerShowStatusResponse;
import com.adansoft.great21.restschemas.ShowGameRequest;
import com.adansoft.great21.restschemas.ShowGameResult;
import com.adansoft.great21.restschemas.ShowGameUIRequest;
import com.adansoft.great21.restschemas.ShowJokerRequest;
import com.adansoft.great21.restschemas.SkipTurnRequest;
import com.adansoft.great21.restschemas.SortCardinHandRequest;
import com.adansoft.great21.uischemas.GetSingleCardResponse;
import com.adansoft.great21.ulitity.CardUtility;

public class GamePlayHelper {

	public static ArrayList<Card> getCards(GetCardsRequest request)
	{
		ArrayList<Card> cardlist = new  ArrayList<Card>();
		GameLobby lobby = RummyArena.getInstance().getLobby(request.getLobbyName());
		Game game = UtilityHelper.getGamefromLobby(lobby, request.getGameInstanceID(), request.getGameType());
		for(Player player : game.getPlayers())
		{
			if(player.getNickName().equals(request.getNickName()))
			    cardlist = player.getPlayerCards();
		}
		return cardlist;
	}
	
	public static GetSingleCardResponse getNextCardFromDeck(GetNextCardFromDeckRequest request)
	{
		GetSingleCardResponse response = new GetSingleCardResponse();
		GameLobby lobby = RummyArena.getInstance().getLobby(request.getLobbyName());
		Game game = UtilityHelper.getGamefromLobby(lobby, request.getGameInstanceID(), request.getGameType());
		Card card = game.getCurrentGameRound().getNextCardFromDeck();
		response.setCard(card);response.setAvaialble(true);
	    response.setCardtype(GetSingleCardResponse.CARDTYPE_NEXTFROMDECK);
		return response;
	}
	
	public static GetSingleCardResponse getJokerForGame(GetJokerRequest request)
	{
		GetSingleCardResponse response = new GetSingleCardResponse();
		Card card = null;
		GameLobby lobby = RummyArena.getInstance().getLobby(request.getLobbyName());
		Game game = UtilityHelper.getGamefromLobby(lobby, request.getGameInstanceID(), request.getGameType());
		GameRound round = game.getCurrentGameRound();
	    for(Player player : game.getPlayers())
	    {
	        if(player.getNickName().equals(request.getNickName()))
	        {
	        	if(player.isJokerKnown())
	        	{
	        		response.setAvaialble(true);
	        		card = round.getJoker();
	        	}
	        }
	    }
	    response.setCard(card);
	    response.setCardtype(GetSingleCardResponse.CARDTYPE_JOKER);
	    return response;
	}
	
	public static GetSingleCardResponse getOpenCard(GetOpenCardRequest request)
	{
		GetSingleCardResponse response = new GetSingleCardResponse();
		Card card = null;
		GameLobby lobby = RummyArena.getInstance().getLobby(request.getLobbyName());
		Game game = UtilityHelper.getGamefromLobby(lobby, request.getGameInstanceID(), request.getGameType());
		card = game.getCurrentGameRound().getOpenCard();
		response.setCard(card);response.setAvaialble(true);
	    response.setCardtype(GetSingleCardResponse.CARDTYPE_OPEN);
		return response;
	}
	
	public static String addCardToHand(AddCardToHandRequest request)
	{
		String result = "Success";
		GameLobby lobby = RummyArena.getInstance().getLobby(request.getCard().getLobbyName());
		Game game = UtilityHelper.getGamefromLobby(lobby, request.getCard().getGameInstanceID(), request.getCard().getGameType());
		result = game.getCurrentGameRound().addCardToHand(request.getCard(), request.getCard().getNickName());		
		return result;
	}
	
	public static String dropCardFromHand(DropCardFromHandRequest request)
	{
		String result = "Success";
		GameLobby lobby = RummyArena.getInstance().getLobby(request.getCard().getLobbyName());
		Game game = UtilityHelper.getGamefromLobby(lobby, request.getCard().getGameInstanceID(), request.getCard().getGameType());
		result = game.getCurrentGameRound().dropCardFromHand(request.getCard(), request.getCard().getNickName());
		return result;
	}
	
	public static GetSingleCardResponse showJoker(ShowJokerRequest request)
	{
		GetSingleCardResponse response = new GetSingleCardResponse();
		Card card = null;
		GameLobby lobby = RummyArena.getInstance().getLobby(request.getLobbyName());
		Game game = UtilityHelper.getGamefromLobby(lobby, request.getGameInstanceID(), request.getGameType());
		ArrayList<Card> cardlist = new ArrayList<Card>();
		Player player = UtilityHelper.getPlayerinGame(game, request.getNickName());
		for(int i=0;i<request.getCardInstanceList().length;i++)
		{
			card = UtilityHelper.getCardforPlayerFromUICard(player,request.getCardInstanceList()[i]);
			cardlist.add(card);
		}
		boolean result = CardUtility.checkSequence(cardlist.toArray(new Card[cardlist.size()]));
		if(result == true)
		{
			player.setJokerKnown(result);
			Card jokercard = game.getCurrentGameRound().getJoker();
			response.setAvaialble(true);
			response.setCard(jokercard);
			response.setCardtype(GetSingleCardResponse.CARDTYPE_JOKER);    		
		}
		return response;
	}
	
	public static Integer getTurn(GetPlayerTurnRequest request)
	{
		GameLobby lobby = RummyArena.getInstance().getLobby(request.getLobbyName());
		Game game = UtilityHelper.getGamefromLobby(lobby, request.getGameInstanceID(), request.getGameType());
		int result = game.getCurrentGameRound().getCurrenturn();
		return new Integer(result);
	}
	
	public static String skipPlayerTurn(SkipTurnRequest request)
	{
		GameLobby lobby = RummyArena.getInstance().getLobby(request.getLobbyName());
		Game game = UtilityHelper.getGamefromLobby(lobby, request.getGameInstanceID(), request.getGameType());
		Player player = UtilityHelper.getPlayerinGame(game, request.getNickName());
		boolean isGameOver = game.getCurrentGameRound().addSkipTurn(player.getPlayerPosition());
		if(game.isGameCardMoneyBased())
		{
			game.getCurrentGameRound().deductCashFromPlayer(player.getNickName(),game.getPerCardMoneyValue()*3,GameRound.PLAYER_STATUS_INITDROPPED);
		}
		if(game.isGamePointsBased())
		{
			game.getCurrentGameRound().addPointsToPlayer(player.getNickName(),20,GameRound.PLAYER_STATUS_INITDROPPED);
		}
		if(isGameOver)
		{
			game.completeRound();
			return "FinishGame";
		}
		return "Success";
	}
	
	public static DeclareGameResult declareGame(DeclareGameUIRequest request)
	{
		GameLobby lobby = RummyArena.getInstance().getLobby(request.getLobbyName());
		Game game = UtilityHelper.getGamefromLobby(lobby, request.getGameInstanceID(), request.getGameType());
		Card jokerCard = game.getCurrentGameRound().getJoker();
		Player player = UtilityHelper.getPlayerinGame(game, request.getNickName());
		DeclareGameRequest gamerequest = UtilityHelper.convert(request, player);	
		DeclareGameResult result = CardUtility.checkDeclareGame(gamerequest.getMeldlist(), jokerCard, request.getGameType());
		if(result.isValid())
		{
			if(game.isGameCardMoneyBased())
			{
				game.getCurrentGameRound().deductCashFromPlayer(player.getNickName(),0.0F,GameRound.PLAYER_STATUS_DECLARED);
			}
			if(game.isGamePointsBased())
			{
				game.getCurrentGameRound().addPointsToPlayer(player.getNickName(),0,GameRound.PLAYER_STATUS_DECLARED);
			}
		}
		return result;
	}
	
	public static Card[] sortCards(SortCardinHandRequest request)
	{
		GameLobby lobby = RummyArena.getInstance().getLobby(request.getLobbyName());
		Game game = UtilityHelper.getGamefromLobby(lobby, request.getGameInstanceID(), request.getGameType());
		Player player = UtilityHelper.getPlayerinGame(game, request.getNickName());
		Card[] inputlist = player.getPlayerCards().toArray(new Card[player.getPlayerCards().size()]);
		Card[] result = CardUtility.sortCards(inputlist);
		return result;
	}
	
	public static ShowGameResult showGame(ShowGameUIRequest request)
	{
		ShowGameResult result = null;
		GameLobby lobby = RummyArena.getInstance().getLobby(request.getLobbyName());
		Game game = UtilityHelper.getGamefromLobby(lobby, request.getGameInstanceID(), request.getGameType());
		Card jokerCard = game.getCurrentGameRound().getJoker();
		Player player = UtilityHelper.getPlayerinGame(game, request.getNickName());
		ShowGameRequest gamerequest = UtilityHelper.convert(request, player);	
		if(game.isGameCardMoneyBased())
		{
			result = CardUtility.showCards(gamerequest.getMeldlist(), Game.GAME_MODE_PERCARD, game.getPerCardMoneyValue(), jokerCard, 80);
			game.getCurrentGameRound().deductCashFromPlayer(player.getNickName(), result.getMoney(),GameRound.PLAYER_STATUS_SHOWNCARDS);
		}
		if(game.isGamePointsBased())
		{
			result = CardUtility.showCards(gamerequest.getMeldlist(), Game.GAME_MODE_POINTS, game.getPerCardMoneyValue(), jokerCard, 80);
			game.getCurrentGameRound().addPointsToPlayer(player.getNickName(), result.getPoints(),GameRound.PLAYER_STATUS_SHOWNCARDS);
		}
		return result;
	}
	
	public static PlayerShowStatusResponse showPlayerStatus(PlayerShowStatusRequest request)
	{
		GameLobby lobby = RummyArena.getInstance().getLobby(request.getLobbyName());
		Game game = UtilityHelper.getGamefromLobby(lobby, request.getGameInstanceID(), request.getGameType());
		HashMap<String,String> showplayerstatus = game.getCurrentGameRound().getPlayersShowStatus();
		PlayerShowStatusResponse result = new PlayerShowStatusResponse();
		result.setGameInstanceID(request.getGameInstanceID());
		result.setPlayerShowStatus(showplayerstatus);
		return result;
	}

	
	public static String finishRound(FinishGameRoundRequest request)
	{
		GameLobby lobby = RummyArena.getInstance().getLobby(request.getLobbyName());
		Game game = UtilityHelper.getGamefromLobby(lobby, request.getGameInstanceID(), request.getGameType());
		game.completeRound();
		return "Success";
	}
	
	public static GetPlayerPointsResponse getPointsTable(GetPlayerPointsRequest request)
	{
		GetPlayerPointsResponse result = new GetPlayerPointsResponse();
		GameLobby lobby = RummyArena.getInstance().getLobby(request.getLobbyName());
		Game game = UtilityHelper.getGamefromLobby(lobby, request.getGameInstanceID(), request.getGameType());
		result.setGameInstanceID(request.getGameInstanceID());
		result.setPointsTable(game.getGameContent().getPlayerPointsMap());
		return result;
	}
}
