package com.adansoft.great21.dataaccess.dao;

import java.util.Calendar;
import java.util.List;

import org.hibernate.SessionFactory;

import com.adansoft.great21.dataaccess.entities.Game;
import com.adansoft.great21.dataaccess.entities.GamePlayers;
import com.adansoft.great21.dataaccess.entities.GameRound;
import com.adansoft.great21.dataaccess.entities.GameRoundId;
import com.adansoft.great21.dataaccess.entities.GameRoundResults;
import com.adansoft.great21.dataaccess.entities.GameRoundResultsId;
import com.adansoft.great21.dataaccess.gamedata.schemas.PersistNewGame;
import com.adansoft.great21.dataaccess.gamedata.schemas.PersistNewRound;
import com.adansoft.great21.dataaccess.gamedata.schemas.PersistPointsorCashforRound;
import com.adansoft.great21.dataaccess.gamedata.schemas.UpdateGameStatus;

public class GameDataAccessDAOImpl implements GameDataAccessDAO {

	private SessionFactory sessionFactory;
	
	@Override
	public void createGame(PersistNewGame request) {
		Game game = new Game();
		game.setGameInstanceId(request.getGameInstanceID());
		game.setGameLobby(request.getLobbyName());
		game.setGameType(request.getGameType());
		game.setHostedBy(request.getHostnick());
		game.setCreatedDate(Calendar.getInstance().getTime());
		game.setPercardBased(request.isPerCard());
		game.setPointsBased(request.isPointBased());
		sessionFactory.getCurrentSession().persist(game);
		
		GamePlayers players = new GamePlayers();
		players.setGameInstanceId(request.getGameInstanceID());
		players.setNumOfPlayers(1);
		players.setPlayer1Idn(request.getHostuserid());
		sessionFactory.getCurrentSession().persist(players);
	}

	@Override
	@SuppressWarnings("unchecked")
	public void launchGame(UpdateGameStatus request) {
	    List<Game> list =  sessionFactory.getCurrentSession().
	     createQuery("from Game where gameInstanceId = :gameInstanceId").
	     setString("gameInstanceId", request.getGameInstanceID()).list();
	    if(list.size() > 0)
	    {
	    	Game game = list.get(0);
	    	game.setIsActive(true);
	    	sessionFactory.getCurrentSession().merge(game);
	    }
	    
	    List<GamePlayers> listgameplayer =  sessionFactory.getCurrentSession().
	   	     createQuery("from GamePlayers where gameInstanceId = :gameInstanceId").
	   	     setString("gameInstanceId", request.getGameInstanceID()).list();

	    if(listgameplayer.size() > 0)
	    {
	    	GamePlayers gameplayer = listgameplayer.get(0);
	    	gameplayer.setPlayer1Idn(request.getPlayer1idn());
	    	gameplayer.setPlayer2Idn(request.getPlayer2idn());
	    	gameplayer.setPlayer3Idn(request.getPlayer3idn());
	    	gameplayer.setPlayer4Idn(request.getPlayer4idn());
	    	gameplayer.setPlayer5Idn(request.getPlayer5idn());
	    	gameplayer.setPlayer6Idn(request.getPlayer6idn());
	    	gameplayer.setPlayer7Idn(request.getPlayer7idn());
	    	gameplayer.setPlayer8Idn(request.getPlayer8idn());
	    	gameplayer.setNumOfPlayers(request.getNumofplayers());
	    	
	    }
	    
	    GameRoundId gameRoundId = new GameRoundId("1", request.getGameInstanceID());
	    GameRound gameRound = new GameRound(gameRoundId, "INPROGRESS", Calendar.getInstance().getTime(),null);
	    sessionFactory.getCurrentSession().persist(gameRound);
	    
	}

	@Override
	@SuppressWarnings("unchecked")
	public void cancelGame(UpdateGameStatus request) {
		List<Game> list = sessionFactory.getCurrentSession().
		createQuery("from Game where gameInstanceId = :gameInstanceId").
		setString("gameInstanceId", request.getGameInstanceID()).list();
		if(list.size() > 0 )
		{
			Game game = list.get(0);
			game.setIsActive(false);
			game.setCancelledDate(Calendar.getInstance().getTime());
			sessionFactory.getCurrentSession().merge(game);
		}

	}

	
	public void updatePlayerInfoforRound(PersistPointsorCashforRound request)
	{
		GameRoundResultsId id = new GameRoundResultsId(request.getGameRoundID(), request.getGameInstanceID(), request.getUserID());
		GameRoundResults result = new GameRoundResults(id,request.getPoints(),request.getCash(),request.isWonGame());
		sessionFactory.getCurrentSession().persist(result);
	}
	
	
	@Override
	public void createNewRound(PersistNewRound request)
	{
		GameRoundId id = new GameRoundId(request.getGameRoundID(), request.getGameInstanceID());
		GameRound round = new GameRound(id, "INPROGRESS", Calendar.getInstance().getTime(),null);
		sessionFactory.getCurrentSession().persist(round);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public void finishRound(PersistNewRound request)
	{
		GameRoundId id = new GameRoundId(request.getGameRoundID(), request.getGameInstanceID());
		List<GameRound> list = sessionFactory.getCurrentSession().
		createQuery("from GameRound where GameRoundId.gameRoundId = :gameRndID and GameRoundId.gameInstanceId = :gameInstID")
		.setString("gameRndID", id.getGameRoundId())
		.setString("gameInstID", id.getGameInstanceId()).list();
		
		if(list.size() > 0)
		{
			GameRound round = list.get(0);
			round.setCompletedDate(Calendar.getInstance().getTime());
			round.setStatus("COMPLETED");
			sessionFactory.getCurrentSession().merge(round);
		}
	}
	
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	
	
	
	
	
	
	
	
}
