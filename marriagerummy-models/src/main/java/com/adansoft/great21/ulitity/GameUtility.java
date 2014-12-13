package com.adansoft.great21.ulitity;

import java.util.UUID;

public class GameUtility {

	public static String generateGameInstanceID()
	{
		return UUID.randomUUID().toString();
	}
	
}
