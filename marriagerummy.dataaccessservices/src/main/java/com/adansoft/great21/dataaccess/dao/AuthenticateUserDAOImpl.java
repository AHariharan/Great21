package com.adansoft.great21.dataaccess.dao;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.adansoft.great21.dataaccess.entities.UserAccounts;
import com.adansoft.great21.dataaccess.entities.UserRoles;
import com.adansoft.great21.dataaccess.schemas.SignupRequest;
import com.adansoft.great21.dataaccess.schemas.SignupResponse;
import com.adansoft.great21.email.EmailHelper;
import com.adansoft.great21.email.EmailManagerDao;
import com.adansoft.great21.ulitity.HashingUtility;

public class AuthenticateUserDAOImpl implements AuthenticateUserDAO {
	
	
	@Autowired
	private EmailManagerDao emailManager;
	
	@Autowired
	private EmailHelper emailHelper;
	
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
				        sendEmail(request.getEmailAddress(),request.getNickName());				        
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
	
	
	
	private void sendEmail(String emailaddress,String nickName)
	{
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("activationurl", generateActivationURL(emailaddress) );
		map.put("fname", nickName);
		 emailManager.SendEmail(emailaddress, emailHelper.CreateSignUpEmail(map), "Signup Confirmation !!!");
	}
	
	
	private String generateActivationURL(String emailaddress)
	{
		String baseActivationUrl = "http://localhost:48080";
		String message = UUID.randomUUID().toString() + UUID.randomUUID().toString();
		String activationCode = HashingUtility.encodeMessage(message);
		String stringActivationUrl = MessageFormat.format(baseActivationUrl
				+ "/marriagerummy/activate/account?id={0}&authtoken={1}", emailaddress,
				activationCode);
		return stringActivationUrl;
	}

}
