package com.adansoft.great21.helpers;

import java.util.ArrayList;




import java.util.HashMap;

import org.springframework.core.task.TaskExecutor;
import org.springframework.web.client.RestTemplate;

import com.adansoft.great21.dataaccess.gamedata.schemas.PersistNewRound;
import com.adansoft.great21.dataaccess.gamedata.schemas.PersistPointsorCashforRound;
import com.adansoft.great21.dataaccess.gamedata.schemas.UpdatePlayerRummyStat;
import com.adansoft.great21.dataaccess.gamedata.schemas.UpdatePlayerStatusPoints;
import com.adansoft.great21.dataaccess.gamedata.schemas.UpdateRummyStat;
import com.adansoft.great21.dataccess.helpers.GameManagertoDataAccessMapper;
import com.adansoft.great21.delayedwrite.GameDataLazyWriter;
import com.adansoft.great21.games.GameLobby;
import com.adansoft.great21.games.RummyArena;
import com.adansoft.great21.models.Card;
import com.adansoft.great21.models.Game;
import com.adansoft.great21.models.GameRound;
import com.adansoft.great21.models.Player;
import com.adansoft.great21.models.helpers.SkipTurnResult;
import com.adansoft.great21.restschemas.AddCardToHandRequest;
import com.adansoft.great21.restschemas.DeclareGameRequest;
import com.adansoft.great21.restschemas.DeclareGameResult;
import com.adansoft.great21.restschemas.DeclareGameUIRequest;
import com.adansoft.great21.restschemas.DropCardFromHandRequest;
import com.adansoft.great21.restschemas.FinishGameRoundRequest;
import com.adansoft.great21.restschemas.GetActivePlayersinGameRequest;
import com.adansoft.great21.restschemas.GetCardsRequest;
import com.adansoft.great21.restschemas.GetEliminationDetailsRequest;
import com.adansoft.great21.restschemas.GetEliminationDetailsResponse;
import com.adansoft.great21.restschemas.GetInfoBlockRequest;
import com.adansoft.great21.restschemas.GetInfoBlockResponse;
import com.adansoft.great21.restschemas.GetJokerRequest;
import com.adansoft.great21.restschemas.GetNextCardFromDeckRequest;
import com.adansoft.great21.restschemas.GetOpenCardRequest;
import com.adansoft.great21.restschemas.GetPlayerPointsRequest;
import com.adansoft.great21.restschemas.GetPlayerPointsResponse;
import com.adansoft.great21.restschemas.GetPlayerTurnRequest;
import com.adansoft.great21.restschemas.GetPlayersinGameResponse;
import com.adansoft.great21.restschemas.GetWinnerDetailsRequest;
import com.adansoft.great21.restschemas.GetWinnerDetailsResponse;
import com.adansoft.great21.restschemas.LaunchGameRequest;
import com.adansoft.great21.restschemas.PlayerShowStatusRequest;
import com.adansoft.great21.restschemas.PlayerShowStatusResponse;
import com.adansoft.great21.restschemas.PlayerStatusinGameRequest;
import com.adansoft.great21.restschemas.PlayerStatusinGameResponse;
import com.adansoft.great21.restschemas.ShowGameRequest;
import com.adansoft.great21.restschemas.ShowGameResult;
import com.adansoft.great21.restschemas.ShowGameUIRequest;
import com.adansoft.great21.restschemas.ShowJokerRequest;
import com.adansoft.great21.restschemas.SkipTurnRequest;
import com.adansoft.great21.restschemas.SortCardinHandRequest;
import com.adansoft.great21.uischemas.GetSingleCardResponse;
import com.adansoft.great21.uischemas.UICard;
import com.adansoft.great21.ulitity.CardUtility;
import com.adansoft.great21.ulitity.GameUtility;

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
		SkipTurnResult result = game.getCurrentGameRound().addSkipTurn(player.getPlayerPosition());
		if(game.isGameCardMoneyBased())
		{
			if(result.getPlayerStatus() == GameRound.PLAYER_STATUS_INITDROPPED)
			       game.getCurrentGameRound().deductCashFromPlayer(player.getNickName(),game.getPerCardMoneyValue()*3,result.getPlayerStatus());
			if(result.getPlayerStatus() == GameRound.PLAYER_STATUS_HALFDROPPED)
			       game.getCurrentGameRound().deductCashFromPlayer(player.getNickName(),game.getPerCardMoneyValue()*6,result.getPlayerStatus());
			
		}
		if(game.isGamePointsBased())
		{
			if(result.getPlayerStatus() == GameRound.PLAYER_STATUS_INITDROPPED)
			     game.getCurrentGameRound().addPointsToPlayer(player.getNickName(),20,result.getPlayerStatus());
			if(result.getPlayerStatus() == GameRound.PLAYER_STATUS_HALFDROPPED)
				 game.getCurrentGameRound().addPointsToPlayer(player.getNickName(),40,result.getPlayerStatus());
		}
		if(result.isGameOver())
		{
			boolean GameOver = game.completeRound();
			if(GameOver)
			{
				return "Game Over";
			}
			return "FinishGame";
		}
		return "Success";
	}
	
	public static DeclareGameResult declareGame(GameManagertoDataAccessMapper mapper,RestTemplate template,TaskExecutor executor,DeclareGameUIRequest request)
	{
		GameLobby lobby = RummyArena.getInstance().getLobby(request.getLobbyName());
		Game game = UtilityHelper.getGamefromLobby(lobby, request.getGameInstanceID(), request.getGameType());
		Card jokerCard = game.getCurrentGameRound().getJoker();
		Player player = UtilityHelper.getPlayerinGame(game, request.getNickName());
		UICard jokerUICard = new UICard();
		jokerUICard.setGameInstanceID(request.getGameInstanceID());
		jokerUICard.setGameType(request.getGameType());jokerUICard.setLobbyName(request.getLobbyName());
		jokerUICard.setNickName(request.getNickName());jokerUICard.setCardInstanceID(jokerCard.getInstanceID());
		DeclareGameRequest gamerequest = UtilityHelper.convert(request, player);	
		DeclareGameResult result = CardUtility.checkDeclareGame(gamerequest.getMeldlist(), jokerCard, request.getGameType());
		if(result.isValid())
		{
			result.setJoker(jokerUICard);
			PersistNewRound finishRound = new PersistNewRound();
			finishRound.setGameInstanceID(game.getGameInstanceId());
			finishRound.setGameRoundID(game.getCurrentRoundNum()+"");
			executor.execute(new GameDataLazyWriter(GameDataLazyWriter.OP_FINISHGAMEROUND, finishRound , mapper,template));
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
	
	public static ShowGameResult showGame(GameManagertoDataAccessMapper mapper,RestTemplate template,TaskExecutor executor,ShowGameUIRequest request)
	{
		ShowGameResult result = null;
		GameLobby lobby = RummyArena.getInstance().getLobby(request.getLobbyName());
		Game game = UtilityHelper.getGamefromLobby(lobby, request.getGameInstanceID(), request.getGameType());
		Card jokerCard = game.getCurrentGameRound().getJoker();
		Player player = UtilityHelper.getPlayerinGame(game, request.getNickName());
		System.out.println("Player Cards Display :" + player.getNickName() + player.getPlayerCards().size());
		for(Card card : player.getPlayerCards())
		{
			System.out.println(" Card : " + card.getInstanceID());
		}
		ShowGameRequest gamerequest = UtilityHelper.convert(request, player);	
		if(game.isGameCardMoneyBased())
		{
			result = CardUtility.showCards(gamerequest.getMeldlist(), Game.GAME_MODE_PERCARD, game.getPerCardMoneyValue(), jokerCard, 80);
			game.getCurrentGameRound().deductCashFromPlayer(player.getNickName(), result.getMoney(),GameRound.PLAYER_STATUS_SHOWNCARDS);
			HashMap<String,UpdateRummyStat> statinfo = new HashMap<String, UpdateRummyStat>();
			UpdateRummyStat info = new UpdateRummyStat(0, -1 *  result.getMoney(), null, null);
			statinfo.put(player.getNickName(), info);
			for(String nickname : game.getCurrentGameRound().getPlayersShowStatus().keySet())
			{
				if(game.getCurrentGameRound().getPlayersShowStatus().get(nickname).equals(GameRound.PLAYER_STATUS_DECLARED))
				{
					UpdateRummyStat addinfo = new UpdateRummyStat(0, 1 *  result.getMoney(), null, null);
					statinfo.put(nickname, addinfo);
				}
			}
			
			UpdatePlayerRummyStat stat = new UpdatePlayerRummyStat(game.getGameInstanceId(),statinfo);
			executor.execute(new GameDataLazyWriter(GameDataLazyWriter.OP_UPDATERUMMYSTAT, stat , mapper,template));
		}
		if(game.isGamePointsBased())
		{
			result = CardUtility.showCards(gamerequest.getMeldlist(), Game.GAME_MODE_POINTS, game.getPerCardMoneyValue(), jokerCard, 80);
			game.getCurrentGameRound().addPointsToPlayer(player.getNickName(), result.getPoints(),GameRound.PLAYER_STATUS_SHOWNCARDS);
			int totalpoints =   GameUtility.getTotalPointsforPlayerinGame(player.getNickName(), game) + result.getPoints();  			
			if(totalpoints >= game.getMaxPoints())
			{
				HashMap<String,UpdateRummyStat> statinfo = new HashMap<String, UpdateRummyStat>();
				UpdateRummyStat info = new UpdateRummyStat(0, -1 * game.getBuyinValue(), null, null);
				statinfo.put(player.getNickName(), info);
				UpdatePlayerRummyStat stat = new UpdatePlayerRummyStat(game.getGameInstanceId(),statinfo);
				executor.execute(new GameDataLazyWriter(GameDataLazyWriter.OP_UPDATERUMMYSTAT, stat , mapper,template));
			}
		}
		
		return result;
	}
	
	public static PlayerShowStatusResponse showPlayerStatus(PlayerShowStatusRequest request)
	{
		GameLobby lobby = RummyArena.getInstance().getLobby(request.getLobbyName());
		Game game = UtilityHelper.getGamefromLobby(lobby, request.getGameInstanceID(), request.getGameType());
		HashMap<String,String> showplayerstatus = game.getCurrentGameRound().getPlayersShowStatus();
		boolean isNewGame = game.getCurrentGameRound().isNewRoudStarted();
		PlayerShowStatusResponse result = new PlayerShowStatusResponse();
		result.setGameInstanceID(request.getGameInstanceID());
		result.setPlayerShowStatus(showplayerstatus);
		result.setNewGame(isNewGame);
		return result;
	}

	
	public static String finishRound(GameManagertoDataAccessMapper mapper,RestTemplate template,TaskExecutor executor,FinishGameRoundRequest request)
	{
		GameLobby lobby = RummyArena.getInstance().getLobby(request.getLobbyName());
		Game game = UtilityHelper.getGamefromLobby(lobby, request.getGameInstanceID(), request.getGameType());
		int currentround = game.getCurrentRoundNum();
		boolean gameOver = game.completeRound();
		if(!gameOver)
		{
			UpdatePlayerStatusPoints points = createUpdatePlayerPoints(game,currentround);		    
			executor.execute(new GameDataLazyWriter(GameDataLazyWriter.OP_PERSISTPLAYERPOINTS, points , mapper,template)); 
			PersistNewRound newround = new PersistNewRound(game.getGameInstanceId(),game.getCurrentRoundNum()+"");
			executor.execute(new GameDataLazyWriter(GameDataLazyWriter.OP_CREATEGAMEROUND, newround , mapper,template));
		    return "Success";
		}
		else
		{
			UpdatePlayerStatusPoints points = createUpdatePlayerPoints(game,currentround);	
			executor.execute(new GameDataLazyWriter(GameDataLazyWriter.OP_PERSISTPLAYERPOINTS, points , mapper,template));
			for(Player player :  game.getPlayers())
			{
				if(player.getPlayerStatus().equals(Player.PLAYER_STATUS_WINGAME))
				{
					HashMap<String,UpdateRummyStat> statinfo = new HashMap<String, UpdateRummyStat>();
					UpdateRummyStat info = new UpdateRummyStat(0, game.getPrizeMoney(), null, null);
					statinfo.put(player.getNickName(), info);
					UpdatePlayerRummyStat stats =  new UpdatePlayerRummyStat(game.getGameInstanceId(),statinfo);
					executor.execute(new GameDataLazyWriter(GameDataLazyWriter.OP_UPDATERUMMYSTAT, stats , mapper,template));
				}
			}
			return "Game Over";
		}
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
	
	public static GetInfoBlockResponse getInfoBlock(GetInfoBlockRequest request)
	{
		GetInfoBlockResponse result = new GetInfoBlockResponse();
		GameLobby lobby = RummyArena.getInstance().getLobby(request.getLobbyName());
		Game game = UtilityHelper.getGamefromLobby(lobby, request.getGameInstanceID(), request.getGameType());
		GameRound round = game.getCurrentGameRound();
		result.setNickName(request.getNickName());
		result.setCurrentStatus(round.getStatusMap().get(request.getNickName()));
		result.setCurrentRound(game.getCurrentRoundNum());
		Player player = UtilityHelper.getPlayerinGame(game, request.getNickName());
		result.setCurrentCash(player.getCurrentCash());
		int currentPoints = UtilityHelper.getTotalPointsforPlayerinGame(request.getNickName(), game);
		result.setCurrentPoints(currentPoints);
		return result;
	}
	
	
	public static GetPlayersinGameResponse getActivePlayersinGame(GetActivePlayersinGameRequest request)
	{
		GetPlayersinGameResponse response = new GetPlayersinGameResponse();
		GameLobby lobby = RummyArena.getInstance().getLobby(request.getLobbyName());
		Game game = UtilityHelper.getGamefromLobby(lobby, request.getGameInstanceID(), request.getGameType());
		ArrayList<Player> activePlayerList = new ArrayList<Player>();
		for(Player player : game.getPlayers())
		{
			if(!player.getPlayerStatus().equals(Player.PLAYER_STATUS_ELIMINATED))
			{
				activePlayerList.add(player);
			}
		}
		response.setGameInstanceID(request.getGameInstanceID());
		response.setPlayerlist(activePlayerList);
		return response;
	}
	
	public static PlayerStatusinGameResponse getPlayerStatus(PlayerStatusinGameRequest request)
	{
		PlayerStatusinGameResponse response = new PlayerStatusinGameResponse();
		response.setGameInstanceID(request.getGameInstanceID());
		response.setGameType(request.getGameType());
		response.setLobbyName(request.getLobbyName());
		HashMap<String,String> playerstatusmap = new HashMap<String, String>();
		GameLobby lobby = RummyArena.getInstance().getLobby(request.getLobbyName());
		Game game = UtilityHelper.getGamefromLobby(lobby, request.getGameInstanceID(), request.getGameType());
		for(Player player : game.getPlayers())
		{
			playerstatusmap.put(player.getNickName(), player.getPlayerStatus());
		}
		response.setPlayerstatusMap(playerstatusmap);
		return response;
	}
	
	
	public static GetEliminationDetailsResponse getEliminationDetails(GetEliminationDetailsRequest request)
	{
		GetEliminationDetailsResponse response = new GetEliminationDetailsResponse();
		response.setGameInstanceID(request.getGameInstanceID());
		response.setNickName(request.getNickName());
		GameLobby lobby = RummyArena.getInstance().getLobby(request.getLobbyName());
		Game game = UtilityHelper.getGamefromLobby(lobby, request.getGameInstanceID(), request.getGameType());
		Player player = UtilityHelper.getPlayerinGame(game, request.getNickName());
		response.setPlayerPoints(GameUtility.getTotalPointsforPlayerinGame(player.getNickName(), game));
		response.setMoney(game.getBuyinValue());
		response.setGameThreshold(game.getMaxPoints());
		return response;
	}
	
	public static GetWinnerDetailsResponse getWinnerDetails(GetWinnerDetailsRequest request)
	{
		GetWinnerDetailsResponse response = new GetWinnerDetailsResponse();
		response.setGameInstanceID(request.getGameInstanceID());
		response.setNickname(request.getNickName());
		GameLobby lobby = RummyArena.getInstance().getLobby(request.getLobbyName());
		Game game = UtilityHelper.getGamefromLobby(lobby, request.getGameInstanceID(), request.getGameType());
		response.setPrizeMoney(game.getPrizeMoney());
		return response;
	}
	
	
	private static UpdatePlayerStatusPoints createUpdatePlayerPoints(Game game,int roundnum)
	{
		  UpdatePlayerStatusPoints points = new UpdatePlayerStatusPoints();
		  points.setGameInstanceID(game.getGameInstanceId());
		  points.setGameRoundID("Round : "+roundnum);
		  HashMap<String, PersistPointsorCashforRound> pointsMap = new HashMap<String, PersistPointsorCashforRound>();
		  for(String key : game.getGameContent().getPlayerPointsMap().keySet())
		  {
			  if(key.equals("Round : "+roundnum))
			  {
				  
				  for(String nickname : game.getGameContent().getPlayerPointsMap().get(key).keySet())
				  {
					  PersistPointsorCashforRound playerinfo = new PersistPointsorCashforRound();
					  playerinfo.setNickname(nickname);
					  System.out.println("Nickname : " + nickname + " {Points} : " + game.getGameContent().getPlayerPointsMap().get(key).get(nickname));
					  playerinfo.setPoints(game.getGameContent().getPlayerPointsMap().get(key).get(nickname));
					  if(game.getGameContent().getPlayerPointsMap().get(key).get(nickname) == 0)
					         playerinfo.setWonGame(true);
					  else
						    playerinfo.setWonGame(false);
					  
					  pointsMap.put(nickname, playerinfo);
				  }
			  }
		  }
		  
		  points.setPlayerList(pointsMap);
		  return points;
	}
}
