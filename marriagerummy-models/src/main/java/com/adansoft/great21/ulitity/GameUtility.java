package com.adansoft.great21.ulitity;

import java.util.HashMap;
import java.util.UUID;

import com.adansoft.great21.models.Game;

public class GameUtility {

	public static String generateGameInstanceID() {
		return UUID.randomUUID().toString();
	}

	public static String generatePlayerInstanceID() {
		return UUID.randomUUID().toString();
	}

	public static int getTotalPointsforPlayerinGame(String nickname, Game game) {
		int currentPoints = 0;
		for (String round : game.getGameContent().getPlayerPointsMap().keySet()) {
			HashMap<String, Integer> mapdata = game.getGameContent()
					.getPlayerPointsMap().get(round);
			if (mapdata != null) {
				for (String curnickname : mapdata.keySet()) {
					if (curnickname.equals(nickname)) {
						currentPoints = currentPoints
								+ mapdata.get(curnickname);
					}
				}
			}
		}

		return currentPoints;
	}

}
