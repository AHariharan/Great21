package com.adansoft.great21.ulitity;

import java.util.HashMap;
import java.util.UUID;

import com.adansoft.great21.models.Game;

public class GameUtility {

	public static String generateGameInstanceID()
	{
		return UUID.randomUUID().toString();
	}
	
	public static String generatePlayerInstanceID()
	{
		return UUID.randomUUID().toString();
	}
	
	public static int getTotalPointsforPlayerinGame(String nickname,Game game)
	{
	
				HashMap<String,Integer> mapdata = game.getGameContent().getPlayerPointsMap().get(nickname);
				int currentPoints = 0;
				for(String key : mapdata.keySet())
				{
					currentPoints = currentPoints + mapdata.get(key);
				}
				
				return currentPoints;
	}
	
	
}
