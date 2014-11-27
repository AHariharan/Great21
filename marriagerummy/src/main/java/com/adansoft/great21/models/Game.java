package com.adansoft.great21.models;

import java.util.ArrayList;

public interface Game {

	public static final String GAME_STATUS_INPROGRESS = "INPROGRESS";
	public static final String GAME_STATUS_COMPLETED = "COMPLETED";	
	public static final String GAME_STATUS_ABANDEND = "ABANDEND";
	
	public int getMaxPlayers();
	
	public ArrayList<Player> getPlayerList();
	
	public Player getCurrentPlayer();
	
	public int getMaxRounds();
	
	public void setAIGameStrategy(GameStrategy strategy);
	
	public GameRound getCurrentGameRound();	
	
	
	
}
