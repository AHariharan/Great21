package com.adansoft.great21.gamemanager.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestTemplate;

import com.adansoft.great21.dataccess.helpers.GameManagertoDataAccessMapper;
import com.adansoft.great21.games.GameLobby;
import com.adansoft.great21.games.RummyArena;
import com.adansoft.great21.jms.models.GameManagerHeartBeat;


@Configuration
@PropertySource("classpath:GameManager.properties")
public class DataConfiguration {
	
	@Value("${InstanceID}")
	private String gameManagerInstanceID;
	
	@Value("${JMS.GAMEMANAGER.REQ}")
	private String requestQueue;

	@Value("${DataAccess.HOST}")
	private String dataAccessHost;
	
	@Value("${DataAccess.PORT}")
	private String dataAccessPort;
	
	@Value("${DataAccess.BASEURI}")
	private String dataAccessURI;
	
	
	
	@Bean
	public GameManagerHeartBeat createHeartBeat()
	{
		return new GameManagerHeartBeat(gameManagerInstanceID, requestQueue.replace("INSTANCEID", gameManagerInstanceID), RummyArena.getInstance().numofgamesinCache(), GameManagerHeartBeat.STATUS_ALIVE);
		
	}

	@PostConstruct
	public void initRummyArena()
	{
		System.out.println("Initializing Rummy Arena");
		RummyArena.getInstance();
		createGameLobbies();
	   
	}
	
	private void createGameLobbies()
	{
		
		GameLobby.createGameLobby("Beginner");
		GameLobby.createGameLobby("Intermediate");
		GameLobby.createGameLobby("Advanced");

	}
	
	@Bean
	public GameManagertoDataAccessMapper createDataAccessMapper()
	{
		try
		{
		GameManagertoDataAccessMapper mapper = new GameManagertoDataAccessMapper(dataAccessHost, dataAccessPort, dataAccessURI);
		System.out.println("******* Created DataAccess Mapper with URI : " + mapper.getDataAccessURI());		
		return mapper;
		}catch(Exception e)
		{
			e.printStackTrace();
			System.exit(-1);
		}
		return null;
	}

	
	@Bean	
	public RestTemplate createRestTemplate()
	{
		RestTemplate template = new RestTemplate();
		return template;
	}
}
