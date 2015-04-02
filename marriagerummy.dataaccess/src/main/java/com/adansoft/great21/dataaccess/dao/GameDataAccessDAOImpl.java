package com.adansoft.great21.dataaccess.dao;

import java.util.Calendar;

import org.hibernate.SessionFactory;

import com.adansoft.great21.dataaccess.entities.Game;
import com.adansoft.great21.dataaccess.entities.GamePlayers;
import com.adansoft.great21.dataaccess.gamedata.schemas.PersistNewGame;
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
	public void PersistNewPlayer(PersistNewGame request) {
		// TODO Auto-generated method stub

	}

	@Override
	public void LaunchGame(UpdateGameStatus request) {
		// TODO Auto-generated method stub

	}

	@Override
	public void CancelGame(UpdateGameStatus request) {
		// TODO Auto-generated method stub

	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	
	
	
	
	
	
	
	
}
