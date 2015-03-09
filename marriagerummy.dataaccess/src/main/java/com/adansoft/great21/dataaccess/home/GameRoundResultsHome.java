package com.adansoft.great21.dataaccess.home;

// Generated Mar 9, 2015 3:07:08 PM by Hibernate Tools 3.4.0.CR1

import java.util.List;

import javax.naming.InitialContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;

import com.adansoft.great21.dataaccess.entities.GameRoundResults;
import com.adansoft.great21.dataaccess.entities.GameRoundResultsId;

/**
 * Home object for domain model class GameRoundResults.
 * @see com.adansoft.great21.dataaccess.home.GameRoundResults
 * @author Hibernate Tools
 */
public class GameRoundResultsHome {

	private static final Log log = LogFactory
			.getLog(GameRoundResultsHome.class);

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

	public void persist(GameRoundResults transientInstance) {
		log.debug("persisting GameRoundResults instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(GameRoundResults instance) {
		log.debug("attaching dirty GameRoundResults instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(GameRoundResults instance) {
		log.debug("attaching clean GameRoundResults instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(GameRoundResults persistentInstance) {
		log.debug("deleting GameRoundResults instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public GameRoundResults merge(GameRoundResults detachedInstance) {
		log.debug("merging GameRoundResults instance");
		try {
			GameRoundResults result = (GameRoundResults) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public GameRoundResults findById(
			GameRoundResultsId id) {
		log.debug("getting GameRoundResults instance with id: " + id);
		try {
			GameRoundResults instance = (GameRoundResults) sessionFactory
					.getCurrentSession()
					.get("com.adansoft.great21.dataaccess.home.GameRoundResults",
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

	public List findByExample(GameRoundResults instance) {
		log.debug("finding GameRoundResults instance by example");
		try {
			List results = sessionFactory
					.getCurrentSession()
					.createCriteria(
							"com.adansoft.great21.dataaccess.home.GameRoundResults")
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
