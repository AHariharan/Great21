package com.adansoft.great21.dataaccess.dao;

import java.math.BigInteger;
import java.util.Calendar;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.adansoft.great21.dataaccess.entities.RummyStats;
import com.adansoft.great21.dataaccess.entities.UserAccounts;
import com.adansoft.great21.dataaccess.entities.UserAudit;
import com.adansoft.great21.dataaccess.entities.UserProfile;
import com.adansoft.great21.dataaccess.schemas.GetProfileInformationRequest;
import com.adansoft.great21.dataaccess.schemas.GetProfileInformationResponse;
import com.adansoft.great21.dataaccess.schemas.GetUserBasicDetailsRequest;
import com.adansoft.great21.dataaccess.schemas.GetUserBasicDetailsResponse;
import com.adansoft.great21.dataaccess.schemas.UpdateProfileInformationRequest;
import com.adansoft.great21.dataaccess.schemas.UserAuditRequest;

public class BasicDataAccessDAOImpl implements BasicDataAccessDAO {

	@Autowired
	private AuthenticateUserDAOImpl authdao;
	
	
	private SessionFactory sessionFactory;
	
	public BasicDataAccessDAOImpl() {
		super();
	}

	
	@Override
	@SuppressWarnings("unchecked")
	public GetUserBasicDetailsResponse getBasicDetails(
			GetUserBasicDetailsRequest request) {
		GetUserBasicDetailsResponse response = new  GetUserBasicDetailsResponse();
		response.setEmailaddress(request.getEmailadd());
		UserAccounts account = authdao.finduserbyEmail(request.getEmailadd());
		response.setNickname(account.getNickName());
		List<RummyStats> list =    sessionFactory.getCurrentSession().
				                   createQuery("from RummyStats where userId = :var_userId").
				                   setBigInteger("var_userId", BigInteger.valueOf(request.getUserid())).
				                   list();
				                   
				                   
				                 
		  
		  if(list.size() == 1 )
		  {
			  RummyStats stats = list.get(0);
			  response.setCash(stats.getCash());
			  response.setRating(stats.getRating());
			  response.setWinpercent(stats.getWinPercent());
			  return response;
		  }
		return null;
	}

	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}


	@Override
	public String addAudit(UserAuditRequest request) {
	
		UserAudit audit = new UserAudit();
		audit.setUserId(request.getUserid());
		audit.setLastLoggedinDate(Calendar.getInstance().getTime());
		audit.setDevice(request.getDevicetype());
		sessionFactory.getCurrentSession().persist(audit);
		return "Success";
	}
	
	@Override
	@SuppressWarnings("unchecked")
	 public GetProfileInformationResponse getProfileInformation(GetProfileInformationRequest request)
	 {
		GetProfileInformationResponse response = new GetProfileInformationResponse();
		List<UserProfile> list = sessionFactory.getCurrentSession().
				                  createQuery("from UserProfile where userid = :userid").
				                  setBigInteger("userid", BigInteger.valueOf(request.getUserid())).list();
		if(list.size() == 1)
		{
			response.setEmailaddress(request.getEmailaddress());
			response.setNickname(request.getNickname());
			response.setCountry(list.get(0).getCountry());
			response.setFirstname(list.get(0).getFirstname());
			response.setLastname(list.get(0).getLastname());
			return response;
		}
		else
		{
			response.setCountry(null);response.setEmailaddress(request.getEmailaddress());
			response.setFirstname(null);response.setLastname(null);
			response.setNickname(request.getNickname());
			return response;
		}
		 
	
		 
	 };
	
	 
	 @Override
	 @SuppressWarnings("unchecked")
	 public String updateProfileInformation(UpdateProfileInformationRequest request)
	 {
		String result = "Success";
		try
		{
		List<UserProfile> list = sessionFactory.getCurrentSession().createQuery("from UserProfile where userid = :userid").
				                  setBigInteger("userid", BigInteger.valueOf(request.getUserid())).list();
		if(list.size() == 1)
		{
			UserProfile profile = list.get(0);
			profile.setCountry(request.getCountry());
			profile.setFirstname(request.getFirstname());
			profile.setLastname(request.getLastname());
			sessionFactory.getCurrentSession().merge(profile);
		}
		else
		{
			//Insert records...
			UserProfile profile = new UserProfile(request.getUserid(), request.getFirstname(), request.getLastname(), request.getCountry(), null);
			sessionFactory.getCurrentSession().persist(profile);
		}
		
		}catch(Exception e)
		{
			result = "failure";
			e.printStackTrace();
		}
		return result;		 
	 };
}
