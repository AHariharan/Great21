package com.adansoft.great21.dataaccess.dao;

import java.util.List;

import org.hibernate.SessionFactory;

import com.adansoft.great21.dataaccess.entities.UserAccounts;
import com.adansoft.great21.dataaccess.entities.UserRoles;
import com.adansoft.great21.dataaccess.schemas.SignupRequest;
import com.adansoft.great21.dataaccess.schemas.SignupResponse;

public class AuthenticateUserDAOImpl implements AuthenticateUserDAO {
	
	public AuthenticateUserDAOImpl()
	{
		super();
	}
	
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public UserAccounts finduserbyEmail(String emailid) {
		List<UserAccounts> list = sessionFactory.getCurrentSession().
		createQuery("from UserAccounts where emailAddr = ?").
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
				createQuery("from UserAccounts where nickName = ?").
				setParameter(0, nickname)
				.list();
				
				if(list.size() > 0)
				   return list.get(0);
				else
				   return null;

	}

	
	
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public SignupResponse signupRequest(SignupRequest request) {
		
		SignupResponse response = new SignupResponse();
		
		if(finduserbyEmail(request.getEmailAddress()) == null)
		{
			if(findUserbyNickName(request.getNickName()) == null)
					{
				        UserRoles roles = new UserRoles();
				        roles.setNickName(request.getNickName());
				        roles.setGrantedRole("ROLE_USER");
				        UserAccounts account = new UserAccounts();
				        account.setEmailAddr(request.getEmailAddress());
				        account.setNickName(request.getNickName());
				        account.setPassword(request.getPassword());
				        account.setEnabled(true);
				        sessionFactory.getCurrentSession().persist(roles);
				        sessionFactory.getCurrentSession().persist(account);
				        response.setValid(true);
				        response.setMessage("Signup Successful");
				        
					}
			else
			{
			response.setValid(false);
	        response.setMessage("Nick Name already taken");
			}
		}
		else
		{
		response.setValid(false);
        response.setMessage("Email address already exits.");
		}
		
		return response;
	}
	
	

}
