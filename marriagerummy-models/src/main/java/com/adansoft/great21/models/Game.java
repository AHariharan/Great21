package com.adansoft.great21.models;

import java.util.ArrayList;

import com.adansoft.great21.games.SevenCardRummy;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use=JsonTypeInfo.Id.NAME, include=JsonTypeInfo.As.WRAPPER_OBJECT, property="type")
@JsonSubTypes({
      @JsonSubTypes.Type(value=SevenCardRummy.class, name="SevenCardRummy")
  }) 
public interface Game {

		
	public static final String GAME_STATUS_INPROGRESS = "INPROGRESS";
	public static final String GAME_STATUS_COMPLETED = "COMPLETED";	
	public static final String GAME_STATUS_ABANDEND = "ABANDEND";
	
	public static final int SEVENCARD_RUMMY_MAXPLAYERS = 4;
	public static final int THIRTEENCARD_RUMMY_MAXPLAYERS = 8;
	public static final int INDEFINITE_RUMMY_ROUNDS = -1;

	/* gets Which GameLobby this belongs to */
	
	public String getGameLobbyName();
	
	/* gets Game InstanceID */
	public String getGameInstanceId();
	
	/* gets who Created this game */
	public String getGameOwnedBy();
	
	/* gets Maximum number of Players allowed in a game */
	public int getMaxPlayers();
	
	/* gets Players in the current game */
	public ArrayList<Player> getPlayerList();
	

	
	/* get maximum number of rounds allowed in the game */	
	public int getMaxRounds();
	
	/* gets Current round details */
	public GameRound getCurrentGameRound();	
	
	/* checks if game is played on points */
	public boolean isGamePointsBased();
	
	/* indicates maximum game points to reach to decide player gets eliminated from game */
	public int getMaxPoints();
	
	/* checks if game is played on Money */	
	public boolean isGameCardMoneyBased();
	
	/* Gets percard value if game is played for Money */
	public float getPerCardMoneyValue();
	
	
	
}