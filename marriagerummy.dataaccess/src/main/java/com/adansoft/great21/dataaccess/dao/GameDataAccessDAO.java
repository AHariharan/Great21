package com.adansoft.great21.dataaccess.dao;


import com.adansoft.great21.dataaccess.gamedata.schemas.PersistNewGame;
import com.adansoft.great21.dataaccess.gamedata.schemas.PersistNewRound;
import com.adansoft.great21.dataaccess.gamedata.schemas.PersistPointsorCashforRound;
import com.adansoft.great21.dataaccess.gamedata.schemas.UpdateGameStatus;
import com.adansoft.great21.dataaccess.gamedata.schemas.UpdatePlayerRummyStat;
import com.adansoft.great21.dataaccess.gamedata.schemas.UpdatePlayerStatusPoints;
import com.adansoft.great21.dataaccess.schemas.AddNotificationRequest;
import com.adansoft.great21.dataaccess.schemas.UnlockAchievementRequest;


public interface GameDataAccessDAO {

	public void createGame(PersistNewGame request);
	
	public void launchGame(UpdateGameStatus request);
	
	public void cancelGame(UpdateGameStatus request);
	
	public void updatePlayerInfoforRound(UpdatePlayerStatusPoints request);
	
	public void createNewRound(PersistNewRound request);
	
	public void finishRound(PersistNewRound request);
	
	public void updateRummyStat(UpdatePlayerRummyStat request);
	
	public void addNotification(AddNotificationRequest request);
	
	public String unlockAchievement(UnlockAchievementRequest request);

	
}
