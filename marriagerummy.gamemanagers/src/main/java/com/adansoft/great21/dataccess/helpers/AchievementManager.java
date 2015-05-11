package com.adansoft.great21.dataccess.helpers;

import org.springframework.core.task.TaskExecutor;
import org.springframework.web.client.RestTemplate;

import com.adansoft.great21.dataaccess.schemas.UnlockAchievementRequest;
import com.adansoft.great21.delayedwrite.GameDataLazyWriter;

public class AchievementManager {
	
	public static void sendAchievement(GameManagertoDataAccessMapper mapper,RestTemplate template,TaskExecutor executor,int achievementtype,String nickname)
	{
		UnlockAchievementRequest request = new UnlockAchievementRequest(nickname, achievementtype, "UNLOCKED");
		executor.execute(new GameDataLazyWriter(GameDataLazyWriter.OP_UNLOCK_ACHIEVEMENT, request , mapper,template));
	}

}
