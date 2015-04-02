package com.adansoft.great21.delayedwrite;

import java.net.URI;

import org.springframework.web.client.RestTemplate;

import com.adansoft.great21.dataaccess.gamedata.schemas.PersistNewGame;
import com.adansoft.great21.dataccess.helpers.DataAccessURLs;
import com.adansoft.great21.dataccess.helpers.GameManagertoDataAccessMapper;
import com.adansoft.great21.models.Game;




public class GameDataLazyWriteHelper {
	
	public static String createGame(Object obj,GameManagertoDataAccessMapper mapper,RestTemplate template)
	{
		String result = "Failure";
		try
		{
		if(obj instanceof Game)
		{
			PersistNewGame request = convertCreateGameRequest((Game)obj);
			URI url = new URI(mapper.getDataAccessURI() + "/"
					+ DataAccessURLs.DELAYED_GAMEDATA_BASE + "/"
					+ DataAccessURLs.CREATED_GAME);
			result = template.postForEntity(url, request, String.class).getBody();
			System.out.println("Successfully wrote Game Content to DB");
		}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}
	
	
	private static PersistNewGame convertCreateGameRequest(Game request)
	{
		PersistNewGame response = new PersistNewGame();
		response.setGameInstanceID(request.getGameInstanceId());
		response.setGameType(request.getRummyType());
		response.setHostnick(request.getOwner());
		response.setHostuserid(request.getPlayers().get(0).getUserID());
		response.setLobbyName(request.getGameLobbyName());
		response.setPerCard(request.isGameCardMoneyBased());
		response.setPointBased(request.isGamePointsBased());
		return response;
	}
	
	
	
}




