package com.adansoft.great21.dataaccess.dao;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.adansoft.great21.dataaccess.entities.UserAccounts;
import com.adansoft.great21.dataaccess.entities.UserAccountsId;
import com.adansoft.great21.dataaccess.entities.UserRoles;
import com.adansoft.great21.dataaccess.helpers.CacheServerActivateAccountCache;
import com.adansoft.great21.dataaccess.schemas.ActivateAccountRequest;
import com.adansoft.great21.dataaccess.schemas.ResendActivationRequest;
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
	
	@Autowired
	private CacheServerActivateAccountCache cacheInstance;
	
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
				        roles.setGrantedRole("ROLE_USER");
				        UserAccounts account = new UserAccounts();
				        UserAccountsId id = new UserAccountsId();
				        id.setEmailAddr(request.getEmailAddress());
				        account.setId(id);
				        account.setNickName(request.getNickName());
				        account.setPassword(request.getPassword());
				        account.setEnabled(false);
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
		String activationUrl = MessageFormat.format(baseActivationUrl
				+ "/marriagerummy/activate/account?id={0}&authtoken={1}", emailaddress,
				activationCode);
		System.out.println("adding email " + emailaddress + " with activationcode " + activationCode);
		cacheInstance.addEmailActivationtoCache(emailaddress, activationCode);
		System.out.println("activationURL : " + activationUrl);
		return activationUrl;
	}
	
	public String activateAccount(ActivateAccountRequest request)
	{
		String email = request.getEmailAddress();
		UserAccounts account = finduserbyEmail(email);
		if(account == null)
			return "Failure";
		else
		{
			String activationcode = cacheInstance.lookupActivationbyEmail(request.getEmailAddress());
			System.out.println("While Activating : " + activationcode + "  ::: request Activation  :" + request.getActivationCode());
			
			if(!activationcode.equals(request.getActivationCode()))
				return "Failure";
			account.setEnabled(true);
		    getSessionFactory().getCurrentSession().merge(account);
		}
		return "Success";
	}
	
	public String resendActivationLink(ResendActivationRequest request)
	{
		String email = request.getEmailAddress();
		UserAccounts account = finduserbyEmail(email);
		if(account == null)
			 return "Failure";
		if(account.isEnabled())
			 return "Account already active";
		sendEmail(email, account.getNickName());		
		return "Success";
		
	}
	

}
