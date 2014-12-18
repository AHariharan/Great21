package com.adansoft.great21.gamemanager.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.adansoft.great21.games.GameLobby;
import com.adansoft.great21.games.RummyArena;
import com.adansoft.great21.jms.models.GameManagerHeartBeat;


@Configuration
@PropertySource("file:GameManager.properties")
public class DataConfiguration {
	
	@Value("${InstanceID}")
	private String gameManagerInstanceID;
	
	@Value("${JMS.GAMEMANAGER.REQ}")
	private String requestQueue;
	
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

}
