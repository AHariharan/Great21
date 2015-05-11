package com.adansoft.great21.dataaccess.dao;

import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.adansoft.great21.dataaccess.entities.AchievementConfig;
import com.adansoft.great21.dataaccess.entities.Game;
import com.adansoft.great21.dataaccess.entities.GamePlayers;
import com.adansoft.great21.dataaccess.entities.GameRound;
import com.adansoft.great21.dataaccess.entities.GameRoundId;
import com.adansoft.great21.dataaccess.entities.GameRoundResults;
import com.adansoft.great21.dataaccess.entities.GameRoundResultsId;
import com.adansoft.great21.dataaccess.entities.RummyStats;
import com.adansoft.great21.dataaccess.entities.RummyTransactions;
import com.adansoft.great21.dataaccess.entities.UserAccounts;
import com.adansoft.great21.dataaccess.entities.UserAcheivements;
import com.adansoft.great21.dataaccess.entities.UserAcheivementsId;
import com.adansoft.great21.dataaccess.entities.UserNotifications;
import com.adansoft.great21.dataaccess.entities.UserNotificationsId;
import com.adansoft.great21.dataaccess.gamedata.schemas.PersistNewGame;
import com.adansoft.great21.dataaccess.gamedata.schemas.PersistNewRound;
import com.adansoft.great21.dataaccess.gamedata.schemas.PersistPointsorCashforRound;
import com.adansoft.great21.dataaccess.gamedata.schemas.UpdateGameStatus;
import com.adansoft.great21.dataaccess.gamedata.schemas.UpdatePlayerRummyStat;
import com.adansoft.great21.dataaccess.gamedata.schemas.UpdatePlayerStatusPoints;
import com.adansoft.great21.dataaccess.gamedata.schemas.UpdateRummyStat;
import com.adansoft.great21.dataaccess.schemas.AddNotificationRequest;
import com.adansoft.great21.dataaccess.schemas.UnlockAchievementRequest;

public class GameDataAccessDAOImpl implements GameDataAccessDAO {

	private SessionFactory sessionFactory;

	@Autowired
	private AuthenticateUserDAOImpl authdao;

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
		List<Game> list = sessionFactory
				.getCurrentSession()
				.createQuery("from Game where gameInstanceId = :gameInstanceId")
				.setString("gameInstanceId", request.getGameInstanceID())
				.list();
		if (list.size() > 0) {
			Game game = list.get(0);
			game.setIsActive(true);
			sessionFactory.getCurrentSession().merge(game);
		}

		List<GamePlayers> listgameplayer = sessionFactory
				.getCurrentSession()
				.createQuery(
						"from GamePlayers where gameInstanceId = :gameInstanceId")
				.setString("gameInstanceId", request.getGameInstanceID())
				.list();

		if (listgameplayer.size() > 0) {
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

		GameRoundId gameRoundId = new GameRoundId("1",
				request.getGameInstanceID());
		GameRound gameRound = new GameRound(gameRoundId, "INPROGRESS", Calendar
				.getInstance().getTime(), null);
		sessionFactory.getCurrentSession().persist(gameRound);

	}

	@Override
	@SuppressWarnings("unchecked")
	public void cancelGame(UpdateGameStatus request) {
		List<Game> list = sessionFactory
				.getCurrentSession()
				.createQuery("from Game where gameInstanceId = :gameInstanceId")
				.setString("gameInstanceId", request.getGameInstanceID())
				.list();
		if (list.size() > 0) {
			Game game = list.get(0);
			game.setIsActive(false);
			game.setCancelledDate(Calendar.getInstance().getTime());
			sessionFactory.getCurrentSession().merge(game);
		}

	}

	public void updatePlayerInfoforRound(UpdatePlayerStatusPoints request) {
		for (String nickname : request.getPlayerList().keySet()) {
			PersistPointsorCashforRound round = request.getPlayerList().get(
					nickname);
			UserAccounts account = authdao.findUserbyNickName(nickname);
			GameRoundResultsId id = new GameRoundResultsId(
					request.getGameRoundID(), request.getGameInstanceID(),
					account.getId().getUserId());
			GameRoundResults result = new GameRoundResults(id,
					round.getPoints(), round.getCash(), round.isWonGame());
			sessionFactory.getCurrentSession().persist(result);
		}

	}

	@Override
	public void createNewRound(PersistNewRound request) {
		GameRoundId id = new GameRoundId(request.getGameRoundID(),
				request.getGameInstanceID());
		GameRound round = new GameRound(id, "INPROGRESS", Calendar
				.getInstance().getTime(), null);
		sessionFactory.getCurrentSession().persist(round);
	}

	@Override
	@SuppressWarnings("unchecked")
	public void finishRound(PersistNewRound request) {
		GameRoundId id = new GameRoundId(request.getGameRoundID(),
				request.getGameInstanceID());

		GameRound round = (GameRound) sessionFactory.getCurrentSession().get(
				GameRound.class, id);

		round.setCompletedDate(Calendar.getInstance().getTime());
		round.setStatus("COMPLETED");
		sessionFactory.getCurrentSession().merge(round);

	}

	public void updateRummyStat(UpdatePlayerRummyStat request) {
		for (String nickname : request.getPlayerlist().keySet()) {
			UpdateRummyStat stat = request.getPlayerlist().get(nickname);
			long userid = authdao.findUserbyNickName(nickname).getId()
					.getUserId();
			stat.setUserId(userid);
			RummyTransactions transaction = new RummyTransactions();
			transaction.setCash(stat.getCash());
			transaction.setCreatedDate(Calendar.getInstance().getTime());
			transaction.setGameInstanceId(request.getGameInstanceID());
			transaction.setUserid(userid);
			if (stat.getCash() > 0)
				transaction.setTransType("CREDIT");
			else
				transaction.setTransType("DEBIT");
			sessionFactory.getCurrentSession().persist(transaction);
			RummyStats existingstat = (RummyStats) sessionFactory
					.getCurrentSession().get(RummyStats.class, userid);
			existingstat.setCash(existingstat.getCash() + stat.getCash());
			sessionFactory.getCurrentSession().merge(existingstat);
		}
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void addNotification(AddNotificationRequest request) {
		UserNotificationsId id = new UserNotificationsId();
		id.setUserid(authdao.findUserbyNickName(request.getNotificationfor())
				.getId().getUserId());
		UserNotifications notification = new UserNotifications(id,
				request.getNotificationType(), request.getNotificationDesc(),
				request.getNotifiedby(),
				DatabaseValueConstants.NOTIFICATION_UNREAD, Calendar
						.getInstance().getTime());
		sessionFactory.getCurrentSession().persist(notification);

	}

	@Override
	@SuppressWarnings("unchecked")
	public String unlockAchievement(UnlockAchievementRequest request) {
        String result = DatabaseValueConstants.ACHIEVEMENT_STATUS_UNLOCKED;
        long userid = authdao.findUserbyNickName(request.getNickname()).getId().getUserId();
		try {
			List<UserAcheivements> list = sessionFactory
					.getCurrentSession()
					.createQuery(
							"from UserAcheivements where id.userId = :varuserid "
									+ "and id.achivementId = :varachievementid")
					.setBigInteger("varuserid",
							BigInteger.valueOf(userid))
					.setInteger("varachievementid", request.getAchievementid())
					.list();
			if (list.size() == 0) // New Record insert if none exists.
			{
				List<AchievementConfig> achconfiglist = sessionFactory
						.getCurrentSession()
						.createQuery(
								"from AchievementConfig where acheivementId = :varachievementid")
						.setInteger("varachievementid",
								request.getAchievementid()).list();
				String status = DatabaseValueConstants.ACHIEVEMENT_STATUS_UNLOCKED;
				Date unlockedDate = Calendar.getInstance().getTime();

				if (achconfiglist != null && achconfiglist.size() > 0) {
					AchievementConfig configdata = achconfiglist.get(0);
					if (configdata.getGoal() > 1) {
						status = DatabaseValueConstants.ACHIEVEMENT_STATUS_PROGRESS;
						result = DatabaseValueConstants.ACHIEVEMENT_STATUS_PROGRESS;
						unlockedDate = null;						
					}
				}

				UserAcheivementsId id = new UserAcheivementsId(
						userid, request.getAchievementid());
				UserAcheivements acheivements = new UserAcheivements(id,
						status, unlockedDate, 1);
				sessionFactory.getCurrentSession().persist(acheivements);
				if (status
						.equals(DatabaseValueConstants.ACHIEVEMENT_STATUS_UNLOCKED)) {
					String notificationfor = request.getNickname();
					AddNotificationRequest notificationrequest = new AddNotificationRequest(
							notificationfor, "System",
							DatabaseValueConstants.NOTIFICATION_TYPE_ACHEIVE,
							"New Achievement Unlocked : "+ achconfiglist.get(0).getAchivementDesc() + "!!!");
					addNotification(notificationrequest);

				}
			} else // handle existing progress
			{
				UserAcheivements existingRec = list.get(0);
				if (existingRec.getStatus().equals(
						DatabaseValueConstants.ACHIEVEMENT_STATUS_UNLOCKED)) {
					System.out.println("Achievement already unlocked Skipping");
					return "IGNORED";
				}
				result = DatabaseValueConstants.ACHIEVEMENT_STATUS_PROGRESS;
				int currentprogress = existingRec.getProgress() + 1;
				List<AchievementConfig> achconfiglist = sessionFactory
						.getCurrentSession()
						.createQuery(
								"from AchievementConfig where acheivementId = :varachievementid")
						.setInteger("varachievementid",
								request.getAchievementid()).list();
				if (achconfiglist != null && achconfiglist.size() > 0) {
					AchievementConfig configdata = achconfiglist.get(0);
					if (currentprogress >= configdata.getGoal()) {
						existingRec
								.setStatus(DatabaseValueConstants.ACHIEVEMENT_STATUS_UNLOCKED);
						existingRec.setUnlockedDate(Calendar.getInstance()
								.getTime());
						String notificationfor = request.getNickname();
						AddNotificationRequest notificationrequest = new AddNotificationRequest(
								notificationfor,
								"System",
								DatabaseValueConstants.NOTIFICATION_TYPE_ACHEIVE,
								"New Achievement Unlocked : "+ achconfiglist.get(0).getAchivementDesc() + "!!!");
						addNotification(notificationrequest);
						result = DatabaseValueConstants.ACHIEVEMENT_STATUS_UNLOCKED;

					}
				}
				existingRec.setProgress(currentprogress);
				sessionFactory.getCurrentSession().merge(existingRec);

			}
		} catch (Exception e) {
			System.out.println("Failed to add Achievement");
			result = "ERROR";
		}
		return result;

	}

}
