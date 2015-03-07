package com.adansoft.great21.dataaccess.home;

// Generated Mar 6, 2015 3:10:36 PM by Hibernate Tools 4.0.0

import java.util.List;

import javax.naming.InitialContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;

import com.adansoft.great21.dataaccess.entities.GamePlayers;

/**
 * Home object for domain model class GamePlayers.
 * @see com.adansoft.great21.dataaccess.home.GamePlayers
 * @author Hibernate Tools
 */
public class GamePlayersHome {

	private static final Log log = LogFactory.getLog(GamePlayersHome.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext()
					.lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException(
					"Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(GamePlayers transientInstance) {
		log.debug("persisting GamePlayers instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(GamePlayers instance) {
		log.debug("attaching dirty GamePlayers instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(GamePlayers instance) {
		log.debug("attaching clean GamePlayers instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(GamePlayers persistentInstance) {
		log.debug("deleting GamePlayers instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public GamePlayers merge(GamePlayers detachedInstance) {
		log.debug("merging GamePlayers instance");
		try {
			GamePlayers result = (GamePlayers) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public GamePlayers findById(java.lang.String id) {
		log.debug("getting GamePlayers instance with id: " + id);
		try {
			GamePlayers instance = (GamePlayers) sessionFactory
					.getCurrentSession().get(
							"com.adansoft.great21.dataaccess.home.GamePlayers",
							id);
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(GamePlayers instance) {
		log.debug("finding GamePlayers instance by example");
		try {
			List results = sessionFactory
					.getCurrentSession()
					.createCriteria(
							"com.adansoft.great21.dataaccess.home.GamePlayers")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
