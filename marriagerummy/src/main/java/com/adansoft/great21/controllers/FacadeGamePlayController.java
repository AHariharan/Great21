package com.adansoft.great21.controllers;

import java.net.URI;











import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.adansoft.great21.exceptions.GameIndexerConfigException;
import com.adansoft.great21.models.Card;
import com.adansoft.great21.models.GameRound;
import com.adansoft.great21.models.Player;
import com.adansoft.great21.restschemas.AddCardToHandRequest;
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
import com.adansoft.great21.restschemas.GetPlayersinGameRequest;
import com.adansoft.great21.restschemas.GetPlayersinGameResponse;
import com.adansoft.great21.restschemas.GetWinnerDetailsRequest;
import com.adansoft.great21.restschemas.GetWinnerDetailsResponse;
import com.adansoft.great21.restschemas.PlayerShowStatusRequest;
import com.adansoft.great21.restschemas.PlayerShowStatusResponse;
import com.adansoft.great21.restschemas.PlayerStatusinGameRequest;
import com.adansoft.great21.restschemas.PlayerStatusinGameResponse;
import com.adansoft.great21.restschemas.ShowGameResult;
import com.adansoft.great21.restschemas.ShowGameUIRequest;
import com.adansoft.great21.restschemas.ShowJokerRequest;
import com.adansoft.great21.restschemas.SkipTurnRequest;
import com.adansoft.great21.restschemas.SortCardinHandRequest;
import com.adansoft.great21.router.FacadetoIndexerMapper;
import com.adansoft.great21.uimediation.UIMediationMapper;
import com.adansoft.great21.uischemas.GetCardResponse;
import com.adansoft.great21.uischemas.GetSingleCardResponse;
import com.adansoft.great21.uischemas.NotificationEvent;

@RestController
@RequestMapping(FacadeControllerURLs.GAMEPLAY_BASE)
@EnableWebMvcSecurity
public class FacadeGamePlayController {
	

	@Autowired
	UIMediationMapper uimapper;
	
	@Autowired
	FacadetoIndexerMapper mapper;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	WebSocketController notifier;
	
	@PostConstruct
	public void verifyGameIndexerConfig()
	{
		try
		{
		if(mapper.getIndexerURI() == null)
		{
			System.out.println("Failed to game indexer config .. exiting");
			System.exit(0);
		}
		System.out.println("GameIndexer configured for FacadeGameLauncher: " + mapper.getIndexerURI());
		}catch(GameIndexerConfigException ex)
		{
			ex.printStackTrace();
			System.exit(0);
		} catch (Exception e) {
		   e.printStackTrace();
		   System.exit(0);
		}
	}
	
	@Secured("ROLE_USER")
	@RequestMapping( value = FacadeControllerURLs.GETCARDS, method = RequestMethod.POST)
	public @ResponseBody GetCardResponse getCards(@RequestBody  GetCardsRequest request,@AuthenticationPrincipal Authentication authentication)	
	{
		GetCardResponse result = null;
		String nickname = authentication.getName();
		request.setNickName(nickname);
		try {
			URI url = new URI(mapper.getIndexerURI() + "/"
					+ FacadeControllerURLs.GAMEPLAY_BASE + "/"
					+ FacadeControllerURLs.GETCARDS);
			result = restTemplate.postForEntity(url, request, GetCardResponse.class).getBody();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}
	
	@Secured("ROLE_USER")
	@RequestMapping( value = FacadeControllerURLs.GETNEXTCARDFROMDECK, method = RequestMethod.POST)
	public @ResponseBody GetSingleCardResponse getNextCardFromDeck(@RequestBody  GetNextCardFromDeckRequest request,@AuthenticationPrincipal Authentication authentication)	
	{
		GetSingleCardResponse result = null;
		String nickname = authentication.getName();
		request.setNickName(nickname);
		try {
			URI url = new URI(mapper.getIndexerURI() + "/"
					+ FacadeControllerURLs.GAMEPLAY_BASE + "/"
					+ FacadeControllerURLs.GETNEXTCARDFROMDECK);
			result = restTemplate.postForEntity(url, request, GetSingleCardResponse.class).getBody();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}
	
	@Secured("ROLE_USER")
	@RequestMapping( value = FacadeControllerURLs.GETJOKER, method = RequestMethod.POST)
	public @ResponseBody GetSingleCardResponse getJokerForGame(@RequestBody  GetJokerRequest request,@AuthenticationPrincipal Authentication authentication)	
	{
		GetSingleCardResponse result = null;
		String nickname = authentication.getName();
		request.setNickName(nickname);
		try {
			URI url = new URI(mapper.getIndexerURI() + "/"
					+ FacadeControllerURLs.GAMEPLAY_BASE + "/"
					+ FacadeControllerURLs.GETJOKER);
			result = restTemplate.postForEntity(url, request, GetSingleCardResponse.class).getBody();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}
	
	@Secured("ROLE_USER")
	@RequestMapping( value = FacadeControllerURLs.GETOPENCARD, method = RequestMethod.POST)
	public @ResponseBody GetSingleCardResponse getOpenCard(@RequestBody GetOpenCardRequest request,@AuthenticationPrincipal Authentication authentication)	
	{
		GetSingleCardResponse result = null;
		String nickname = authentication.getName();
		request.setNickName(nickname);
		try {
			URI url = new URI(mapper.getIndexerURI() + "/"
					+ FacadeControllerURLs.GAMEPLAY_BASE + "/"
					+ FacadeControllerURLs.GETOPENCARD);
			result = restTemplate.postForEntity(url, request, GetSingleCardResponse.class).getBody();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}
	
	@Secured("ROLE_USER")
	@RequestMapping( value = FacadeControllerURLs.ADDCARDTOHAND, method = RequestMethod.POST)
	public @ResponseBody String addCardToHand(@RequestBody AddCardToHandRequest request,@AuthenticationPrincipal Authentication authentication)	
	{
		String result = null;
		String nickname = authentication.getName();
		request.getCard().setNickName(nickname);
		try {
			URI url = new URI(mapper.getIndexerURI() + "/"
					+ FacadeControllerURLs.GAMEPLAY_BASE + "/"
					+ FacadeControllerURLs.ADDCARDTOHAND);
			result = restTemplate.postForEntity(url, request, String.class).getBody();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}
	
	@Secured("ROLE_USER")
	@RequestMapping( value = FacadeControllerURLs.DROPCARDFROMHAND, method = RequestMethod.POST)
	public @ResponseBody String dropCardFromHand(@RequestBody DropCardFromHandRequest request,@AuthenticationPrincipal Authentication authentication)	
	{
		System.out.println("Drop Card Request came :- " + request.getCard() + "------" + authentication.getName());
		String result = null;
		String nickname = authentication.getName();
		request.getCard().setNickName(nickname);
		try {
			URI url = new URI(mapper.getIndexerURI() + "/"
					+ FacadeControllerURLs.GAMEPLAY_BASE + "/"
					+ FacadeControllerURLs.DROPCARDFROMHAND);
			result = restTemplate.postForEntity(url, request, String.class).getBody();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}
	
	@Secured("ROLE_USER")
	@RequestMapping( value = FacadeControllerURLs.SHOWJOKER, method = RequestMethod.POST)
	public @ResponseBody GetSingleCardResponse showJoker(@RequestBody ShowJokerRequest request,@AuthenticationPrincipal Authentication authentication)	
	{
		GetSingleCardResponse result = null;
		String nickname = authentication.getName();
		request.setNickName(nickname);
		try {
			URI url = new URI(mapper.getIndexerURI() + "/"
					+ FacadeControllerURLs.GAMEPLAY_BASE + "/"
					+ FacadeControllerURLs.SHOWJOKER);
			result = restTemplate.postForEntity(url, request, GetSingleCardResponse.class).getBody();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}
	
	@Secured("ROLE_USER")
	@RequestMapping( value = FacadeControllerURLs.PLAYERTURN, method = RequestMethod.POST)
	public @ResponseBody Integer getPlayerTurn(@RequestBody GetPlayerTurnRequest request,@AuthenticationPrincipal Authentication authentication)	
	{
		Integer result = null;
		String nickname = authentication.getName();
		request.setNickName(nickname);
		try {
			URI url = new URI(mapper.getIndexerURI() + "/"
					+ FacadeControllerURLs.GAMEPLAY_BASE + "/"
					+ FacadeControllerURLs.PLAYERTURN);
			result = restTemplate.postForEntity(url, request, Integer.class).getBody();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}
	
	@Secured("ROLE_USER")
	@RequestMapping( value = FacadeControllerURLs.DECLAREGAME, method = RequestMethod.POST)
	public @ResponseBody DeclareGameResult declareGame(@RequestBody DeclareGameUIRequest request,@AuthenticationPrincipal Authentication authentication)	
	{
		DeclareGameResult result = null;
		String nickname = authentication.getName();
		request.setNickName(nickname);
		try {
			URI url = new URI(mapper.getIndexerURI() + "/"
					+ FacadeControllerURLs.GAMEPLAY_BASE + "/"
					+ FacadeControllerURLs.DECLAREGAME);
			result = restTemplate.postForEntity(url, request, DeclareGameResult.class).getBody();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}
	
	@Secured("ROLE_USER")
	@RequestMapping( value = FacadeControllerURLs.SKIPPLAYERTURN, method = RequestMethod.POST)
	public @ResponseBody String skipPlayerTurn(@RequestBody SkipTurnRequest request,@AuthenticationPrincipal Authentication authentication)	
	{
		String result = null;
		String nickname = authentication.getName();
		request.setNickName(nickname);
		try {
			URI url = new URI(mapper.getIndexerURI() + "/"
					+ FacadeControllerURLs.GAMEPLAY_BASE + "/"
					+ FacadeControllerURLs.SKIPPLAYERTURN);
			result = restTemplate.postForEntity(url, request, String.class).getBody();
			if(result.equals("FinishGame"))
				notifyNewRound(request.getGameInstanceID(),null);
			if(result.equals("Game Over"))
				notifyGameOver(request.getGameInstanceID(),null);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}
	
	@Secured("ROLE_USER")
	@RequestMapping( value = FacadeControllerURLs.SORTCARDS, method = RequestMethod.POST)
	public @ResponseBody Card[] sortCards(@RequestBody SortCardinHandRequest request,@AuthenticationPrincipal Authentication authentication)	
	{
		Card[] result = null;
		String nickname = authentication.getName();
		request.setNickName(nickname);
		try {
			URI url = new URI(mapper.getIndexerURI() + "/"
					+ FacadeControllerURLs.GAMEPLAY_BASE + "/"
					+ FacadeControllerURLs.SORTCARDS);
			result = restTemplate.postForEntity(url, request, Card[].class).getBody();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;		
	}
	
	@Secured("ROLE_USER")
	@RequestMapping( value = FacadeControllerURLs.SHOWMYCARDS, method = RequestMethod.POST)
	public @ResponseBody ShowGameResult showCards(@RequestBody ShowGameUIRequest request,@AuthenticationPrincipal Authentication authentication)	
	{
		ShowGameResult result = null;
		String nickname = authentication.getName();
		request.setNickName(nickname);
		try {
			URI url = new URI(mapper.getIndexerURI() + "/"
					+ FacadeControllerURLs.GAMEPLAY_BASE + "/"
					+ FacadeControllerURLs.SHOWMYCARDS);
			result = restTemplate.postForEntity(url, request, ShowGameResult.class).getBody();
			PlayerShowStatusRequest intimateRequest = new PlayerShowStatusRequest();
			intimateRequest.setGameInstanceID(request.getGameInstanceID());
			intimateRequest.setGameType(request.getGameType());
			intimateRequest.setLobbyName(request.getLobbyName());
			intimateRequest.setNickName(request.getNickName());
			PlayerShowStatusResponse response = showPlayerStatus(intimateRequest, authentication);
			NotifyNewRoundStart(response,request,result);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}
	
	@Secured("ROLE_USER")
	@RequestMapping( value = FacadeControllerURLs.PLAYERSHOWSTATUS, method = RequestMethod.POST)
	public @ResponseBody PlayerShowStatusResponse showPlayerStatus(@RequestBody PlayerShowStatusRequest request,@AuthenticationPrincipal Authentication authentication)	
	{
		PlayerShowStatusResponse result = null;
		String nickname = authentication.getName();
		request.setNickName(nickname);
		try {
			URI url = new URI(mapper.getIndexerURI() + "/"
					+ FacadeControllerURLs.GAMEPLAY_BASE + "/"
					+ FacadeControllerURLs.PLAYERSHOWSTATUS);
			result = restTemplate.postForEntity(url, request, PlayerShowStatusResponse.class).getBody();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}
	
	
	@Secured("ROLE_USER")
	@RequestMapping( value = FacadeControllerURLs.GETPOINTS, method = RequestMethod.POST)
	public @ResponseBody GetPlayerPointsResponse getPointsTable(@RequestBody GetPlayerPointsRequest request,@AuthenticationPrincipal Authentication authentication)	
	{
		GetPlayerPointsResponse result = null;
		String nickname = authentication.getName();
		request.setNickName(nickname);
		try {
			URI url = new URI(mapper.getIndexerURI() + "/"
					+ FacadeControllerURLs.GAMEPLAY_BASE + "/"
					+ FacadeControllerURLs.GETPOINTS);
			result = restTemplate.postForEntity(url, request, GetPlayerPointsResponse.class).getBody();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}
	
	
	@Secured("ROLE_USER")
	@RequestMapping( value = FacadeControllerURLs.GETINFOBLOCK, method = RequestMethod.POST)
	public @ResponseBody GetInfoBlockResponse getInfoBlock(@RequestBody GetInfoBlockRequest request,@AuthenticationPrincipal Authentication authentication)	
	{
		GetInfoBlockResponse result = null;
		String nickname = authentication.getName();
		request.setNickName(nickname);
		try {
			URI url = new URI(mapper.getIndexerURI() + "/"
					+ FacadeControllerURLs.GAMEPLAY_BASE + "/"
					+ FacadeControllerURLs.GETINFOBLOCK);
			result = restTemplate.postForEntity(url, request, GetInfoBlockResponse.class).getBody();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}
	
	
	@Secured("ROLE_USER")
	@RequestMapping( value = FacadeControllerURLs.GETACTIVEPLAYERS, method = RequestMethod.POST)
	public @ResponseBody GetPlayersinGameResponse getActivePlayersinGame(@RequestBody  GetActivePlayersinGameRequest request,@AuthenticationPrincipal Authentication authentication)	
	{
		GetPlayersinGameResponse result = null;		
		try {
			URI url = new URI(mapper.getIndexerURI() + "/"
					+ FacadeControllerURLs.GAMEPLAY_BASE + "/"
					+ FacadeControllerURLs.GETACTIVEPLAYERS);
			result = restTemplate.postForEntity(url, request, GetPlayersinGameResponse.class ).getBody();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;		
	}
	
	
	@Secured("ROLE_USER")
	@RequestMapping( value = FacadeControllerURLs.GETELIMINATIONDETAILS, method = RequestMethod.POST)
	public @ResponseBody GetEliminationDetailsResponse getEliminationDetails(@RequestBody  GetEliminationDetailsRequest request,@AuthenticationPrincipal Authentication authentication)	
	{
		String nickname = authentication.getName();
		request.setNickName(nickname);
		GetEliminationDetailsResponse result = null;		
		try {
			URI url = new URI(mapper.getIndexerURI() + "/"
					+ FacadeControllerURLs.GAMEPLAY_BASE + "/"
					+ FacadeControllerURLs.GETELIMINATIONDETAILS);
			result = restTemplate.postForEntity(url, request, GetEliminationDetailsResponse.class ).getBody();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;		
	}
	
	
	@Secured("ROLE_USER")
	@RequestMapping( value = FacadeControllerURLs.GETWINNERDETAILS, method = RequestMethod.POST)
	public @ResponseBody GetWinnerDetailsResponse getEliminationDetails(@RequestBody  GetWinnerDetailsRequest request,@AuthenticationPrincipal Authentication authentication)	
	{
		String nickname = authentication.getName();
		request.setNickName(nickname);
		GetWinnerDetailsResponse result = null;		
		try {
			URI url = new URI(mapper.getIndexerURI() + "/"
					+ FacadeControllerURLs.GAMEPLAY_BASE + "/"
					+ FacadeControllerURLs.GETWINNERDETAILS);
			result = restTemplate.postForEntity(url, request, GetWinnerDetailsResponse.class ).getBody();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;		
	}
	
	private synchronized void NotifyNewRoundStart(PlayerShowStatusResponse response,ShowGameUIRequest request,ShowGameResult showGameresult)
	{
		boolean needtoIntimate = true;
		for(String key : response.getPlayerShowStatus().keySet())
		{
			String status = response.getPlayerShowStatus().get(key);
			if(status.equals(GameRound.PLAYER_STATUS_PLAYING) || status.equals(GameRound.PLAYER_STATUS_WAITFORTURN))
			{
				needtoIntimate = false;
				break;
			}
		}
		
		/* Get Player Status for Elimination and inform  logic should be called here*/
		if(needtoIntimate)
		{
			FinishGameRoundRequest gamecomprequest = new FinishGameRoundRequest();
			gamecomprequest.setGameInstanceID(request.getGameInstanceID());
			gamecomprequest.setGameType(request.getGameType());
			gamecomprequest.setLobbyName(request.getLobbyName());
			String result = finishGameRound(gamecomprequest);
			PlayerStatusinGameRequest playerstatusrequest = new PlayerStatusinGameRequest();
			playerstatusrequest.setGameInstanceID(request.getGameInstanceID());
			playerstatusrequest.setGameType(request.getGameType());
			playerstatusrequest.setLobbyName(request.getLobbyName());
			PlayerStatusinGameResponse playerstatusresponse = getPlayerStatusinGame(playerstatusrequest);
			for(String nickname : playerstatusresponse.getPlayerstatusMap().keySet())
			{
				if(playerstatusresponse.getPlayerstatusMap().get(nickname).equals(Player.PLAYER_STATUS_ELIMINATED))
				{
					notifyPlayerEliminatation(playerstatusresponse);
					System.out.println("Player Elimination Notification sent : " + nickname);
					if(request.getNickName().equals(nickname))
						 showGameresult.setEliminated(true);
				}
			}
			System.out.println("Result from Finish Game Round " + result);
			if(!result.equals("Game Over"))
			{
				notifyNewRound(request.getGameInstanceID(),response);
			}
			else
			{
				notifyGameOver(request.getGameInstanceID(), response);	
			}
		}
	}
	
	private void notifyNewRound(String gameinstanceId,Object notificationObject)
	{
		NotificationEvent event = new NotificationEvent();
		event.setNotificationSource("SERVER");
		event.setNotifiedBy("NotifyGAMEOVER");
		event.setNotificationType("NEWGAMENOTIFY");
		event.setNotificationObject(null);
		notifier.sendNotificationFromBackend(event, gameinstanceId);
	}
	
	private void notifyGameOver(String gameinstanceId,Object notificationObject)
	{
		NotificationEvent event = new NotificationEvent();
		event.setNotificationSource("SERVER");
		event.setNotifiedBy("NotifyGAMEOVER");
		event.setNotificationType("GAMEOVER");
		event.setNotificationObject(null);
		notifier.sendNotificationFromBackend(event, gameinstanceId);
	}
	
	private void notifyPlayerEliminatation(PlayerStatusinGameResponse response)
	{
		NotificationEvent event = new NotificationEvent();
		event.setNotificationSource("SERVER");
		event.setNotifiedBy("NotifyPlayerElimination");
		event.setNotificationType("PLAYERELIMINATED");
		event.setNotificationObject(response);
		notifier.sendNotificationFromBackend(event, response.getGameInstanceID());
	};
	
	
	private String finishGameRound(FinishGameRoundRequest request)	
	{
		String result = null;
		try {
			URI url = new URI(mapper.getIndexerURI() + "/"
					+ FacadeControllerURLs.GAMEPLAY_BASE + "/"
					+ FacadeControllerURLs.FINISHROUND);
			result = restTemplate.postForEntity(url, request, String.class).getBody();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}
	
	private PlayerStatusinGameResponse getPlayerStatusinGame(PlayerStatusinGameRequest request)
	{
		PlayerStatusinGameResponse response = null;
		try
		{
			URI url = new URI(mapper.getIndexerURI() + "/"
					+ FacadeControllerURLs.GAMEPLAY_BASE + "/"
					+ FacadeControllerURLs.GETPLAYERSTATUS);
			response = restTemplate.postForEntity(url, request, PlayerStatusinGameResponse.class).getBody();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return response;
	}
	
	

}
