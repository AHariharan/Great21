package com.adansoft.great21.dataaccess.home;

// Generated Mar 9, 2015 3:07:08 PM by Hibernate Tools 3.4.0.CR1

import java.util.List;

import javax.naming.InitialContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;

import com.adansoft.great21.dataaccess.entities.UserFriends;
import com.adansoft.great21.dataaccess.entities.UserFriendsId;

/**
 * Home object for domain model class UserFriends.
 * @see com.adansoft.great21.dataaccess.home.UserFriends
 * @author Hibernate Tools
 */
public class UserFriendsHome {

	private static final Log log = LogFactory.getLog(UserFriendsHome.class);

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

	public void persist(UserFriends transientInstance) {
		log.debug("persisting UserFriends instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(UserFriends instance) {
		log.debug("attaching dirty UserFriends instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(UserFriends instance) {
		log.debug("attaching clean UserFriends instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(UserFriends persistentInstance) {
		log.debug("deleting UserFriends instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public UserFriends merge(UserFriends detachedInstance) {
		log.debug("merging UserFriends instance");
		try {
			UserFriends result = (UserFriends) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public UserFriends findById(
			UserFriendsId id) {
		log.debug("getting UserFriends instance with id: " + id);
		try {
			UserFriends instance = (UserFriends) sessionFactory
					.getCurrentSession().get(
							"com.adansoft.great21.dataaccess.home.UserFriends",
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

	public List findByExample(UserFriends instance) {
		log.debug("finding UserFriends instance by example");
		try {
			List results = sessionFactory
					.getCurrentSession()
					.createCriteria(
							"com.adansoft.great21.dataaccess.home.UserFriends")
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
