package com.adansoft.great21.dataaccess.dao;

import java.util.List;

import org.hibernate.SessionFactory;

import com.adansoft.great21.dataaccess.dao.AuthenticateUserDAO;
import com.adansoft.great21.dataaccess.entities.UserAccounts;


public class AuthenticateUserDAOImpl implements AuthenticateUserDAO {
	
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public UserAccounts finduserbyEmail(String emailid) {
		List<UserAccounts> list = sessionFactory.getCurrentSession().
		createQuery("from USER_ACCOUNTS where EMAIL_ADDR = ?").
		setParameter(0, emailid)
		.list();
		
		if(list.size() > 0)
		   return list.get(0);
		else
		   return null;
	}

	@SuppressWarnings("unchecked")
	public UserAccounts findUserbyNickName(String nickname) {
		List<UserAccounts> list = sessionFactory.getCurrentSession().
				createQuery("from USER_ACCOUNTS where NICK_NAME = ?").
				setParameter(0, nickname)
				.list();
				
				if(list.size() > 0)
				   return list.get(0);
				else
				   return null;

	}

}
