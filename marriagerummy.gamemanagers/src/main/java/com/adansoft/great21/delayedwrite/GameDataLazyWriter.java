package com.adansoft.great21.delayedwrite;


import org.springframework.web.client.RestTemplate;

import com.adansoft.great21.dataccess.helpers.GameManagertoDataAccessMapper;

public class GameDataLazyWriter implements Runnable {

	public static final String OP_CREATEGAME = "Create Game";
	public static final String OP_DELETEGAME = "Delete Game";
	public static final String OP_LAUNCHGAME = "Launch Game";
	public static final String OP_CREATEGAMEROUND = "Create Game Round";
	public static final String OP_FINISHGAMEROUND = "Finish Game Round";
	public static final String OP_PERSISTPLAYERPOINTS = "Persist Player Points";
	public static final String OP_UPDATERUMMYSTAT = "Update Rummy Stat";
	public static final String OP_UNLOCK_ACHIEVEMENT = "Unlock Achievement";

	GameManagertoDataAccessMapper gametodataaccessmapper;
	

	RestTemplate restTemplate;
	
	private String operationName;
	
	private Object requestObj;
	
	public GameDataLazyWriter(String operation,Object obj,GameManagertoDataAccessMapper mapper,RestTemplate template)
	{
		this.operationName = operation;
		this.requestObj = obj;
		this.gametodataaccessmapper = mapper;
		this.restTemplate = template;
		try
		{
		   System.out.println("Successfully Autowired : " + gametodataaccessmapper.getDataAccessURI());
		}catch(Exception e)
		{
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	
	@Override
	public void run() {
		if(operationName.equals(OP_CREATEGAME))
			GameDataLazyWriteHelper.createGame(requestObj, gametodataaccessmapper, restTemplate);
		if(operationName.equals(OP_LAUNCHGAME))
			GameDataLazyWriteHelper.launchGame(requestObj, gametodataaccessmapper, restTemplate);
		if(operationName.equals(OP_DELETEGAME))
			GameDataLazyWriteHelper.deleteGame(requestObj, gametodataaccessmapper, restTemplate);
		if(operationName.equals(OP_CREATEGAMEROUND))
			GameDataLazyWriteHelper.createNewGameRound(requestObj, gametodataaccessmapper, restTemplate);
		if(operationName.equals(OP_FINISHGAMEROUND))
			GameDataLazyWriteHelper.finishGameRound(requestObj, gametodataaccessmapper, restTemplate);
		if(operationName.equals(OP_PERSISTPLAYERPOINTS))
			GameDataLazyWriteHelper.persistPlayerCashorPoints(requestObj, gametodataaccessmapper, restTemplate);
		if(operationName.equals(OP_UPDATERUMMYSTAT))
		   GameDataLazyWriteHelper.updateRummyStat(requestObj, gametodataaccessmapper, restTemplate);
		if(operationName.equals(OP_UNLOCK_ACHIEVEMENT))
		   GameDataLazyWriteHelper.unlockAchievement(requestObj, gametodataaccessmapper, restTemplate);
		
		
	}

}
