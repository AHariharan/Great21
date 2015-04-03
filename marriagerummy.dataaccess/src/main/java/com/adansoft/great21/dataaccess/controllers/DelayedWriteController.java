package com.adansoft.great21.dataaccess.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.adansoft.great21.dataaccess.dao.GameDataAccessDAOImpl;
import com.adansoft.great21.dataaccess.gamedata.schemas.PersistNewGame;
import com.adansoft.great21.dataaccess.gamedata.schemas.UpdateGameStatus;
import com.adansoft.great21.dataaccess.schemas.GetUserBasicDetailsRequest;
import com.adansoft.great21.dataaccess.schemas.GetUserBasicDetailsResponse;

@RestController
@RequestMapping(DataAccessServiceURLs.DELAYED_GAMEDATA_BASE)
public class DelayedWriteController {

	@Autowired
	private GameDataAccessDAOImpl gamedataDAO;
	

	@Transactional
	@RequestMapping( value = DataAccessServiceURLs.CREATED_GAME, method = RequestMethod.POST)
	public @ResponseBody String createGame(@RequestBody PersistNewGame request)
	{
		try
		{ 
			gamedataDAO.createGame(request);
		}catch(Exception e)
		{
			e.printStackTrace();return "Failure";
		}
		return "Success";
	}
	
	
	@Transactional
	@RequestMapping( value = DataAccessServiceURLs.LAUNCH_GAME, method = RequestMethod.POST)
	public @ResponseBody String launchGame(@RequestBody UpdateGameStatus request)
	{
		try
		{ 
			gamedataDAO.launchGame(request);
		}catch(Exception e)
		{
			e.printStackTrace();return "Failure";
		}
		return "Success";
	}
	
	
	@Transactional
	@RequestMapping( value = DataAccessServiceURLs.DELETE_GAME, method = RequestMethod.POST)
	public @ResponseBody String deleteGame(@RequestBody UpdateGameStatus request)
	{
		try
		{ 
			gamedataDAO.cancelGame(request);
		}catch(Exception e)
		{
			e.printStackTrace();return "Failure";
		}
		return "Success";
	}
	
	
}
