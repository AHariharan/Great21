package com.adansoft.great21.dataaccess.dao;

import java.lang.reflect.Field;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.adansoft.great21.dataaccess.entities.FriendRequest;
import com.adansoft.great21.dataaccess.entities.FriendRequestId;
import com.adansoft.great21.dataaccess.entities.GamejoinRequest;
import com.adansoft.great21.dataaccess.entities.GamejoinRequestId;
import com.adansoft.great21.dataaccess.entities.RummyStats;
import com.adansoft.great21.dataaccess.entities.UserAccounts;
import com.adansoft.great21.dataaccess.entities.UserAudit;
import com.adansoft.great21.dataaccess.entities.UserFriends;
import com.adansoft.great21.dataaccess.entities.UserFriendsId;
import com.adansoft.great21.dataaccess.entities.UserNotifications;
import com.adansoft.great21.dataaccess.entities.UserNotificationsId;
import com.adansoft.great21.dataaccess.entities.UserProfile;
import com.adansoft.great21.dataaccess.schemas.AddFriendRequest;
import com.adansoft.great21.dataaccess.schemas.AddNotificationRequest;
import com.adansoft.great21.dataaccess.schemas.ConfirmIgnoreFriendRequest;
import com.adansoft.great21.dataaccess.schemas.ConfirmIgnoreGameInviteRequest;
import com.adansoft.great21.dataaccess.schemas.FriendResponse;
import com.adansoft.great21.dataaccess.schemas.GetActiveAddFriendList;
import com.adansoft.great21.dataaccess.schemas.GetActiveFriendRequest;
import com.adansoft.great21.dataaccess.schemas.GetActiveFriendResponse;
import com.adansoft.great21.dataaccess.schemas.GetActiveGameInviteList;
import com.adansoft.great21.dataaccess.schemas.GetActiveGameInviteRequest;
import com.adansoft.great21.dataaccess.schemas.GetActiveGameInviteResponse;
import com.adansoft.great21.dataaccess.schemas.GetActiveNotificationList;
import com.adansoft.great21.dataaccess.schemas.GetActiveNotificationRequest;
import com.adansoft.great21.dataaccess.schemas.GetActiveNotificationResponse;
import com.adansoft.great21.dataaccess.schemas.GetFriendListResponse;
import com.adansoft.great21.dataaccess.schemas.GetFriendsListRequest;
import com.adansoft.great21.dataaccess.schemas.GetNotificationCountRequest;
import com.adansoft.great21.dataaccess.schemas.GetNotificationCountResponse;
import com.adansoft.great21.dataaccess.schemas.GetProfileInformationRequest;
import com.adansoft.great21.dataaccess.schemas.GetProfileInformationResponse;
import com.adansoft.great21.dataaccess.schemas.GetUserAcheivementRequest;
import com.adansoft.great21.dataaccess.schemas.GetUserAcheivementResponse;
import com.adansoft.great21.dataaccess.schemas.GetUserAchivementList;
import com.adansoft.great21.dataaccess.schemas.GetUserBasicDetailsRequest;
import com.adansoft.great21.dataaccess.schemas.GetUserBasicDetailsResponse;
import com.adansoft.great21.dataaccess.schemas.SendGameInviteRequest;
import com.adansoft.great21.dataaccess.schemas.UpdateProfileInformationRequest;
import com.adansoft.great21.dataaccess.schemas.UserAuditRequest;
import com.adansoft.great21.exceptions.GameIndexerConfigException;

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
		GetUserBasicDetailsResponse response = new GetUserBasicDetailsResponse();
		response.setEmailaddress(request.getEmailadd());
		UserAccounts account = authdao.finduserbyEmail(request.getEmailadd());
		response.setNickname(account.getNickName());
		List<RummyStats> list = sessionFactory
				.getCurrentSession()
				.createQuery("from RummyStats where userId = :var_userId")
				.setBigInteger("var_userId",
						BigInteger.valueOf(request.getUserid())).list();

		if (list.size() == 1) {
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
	public GetProfileInformationResponse getProfileInformation(
			GetProfileInformationRequest request) {
		GetProfileInformationResponse response = new GetProfileInformationResponse();
		List<UserProfile> list = sessionFactory
				.getCurrentSession()
				.createQuery("from UserProfile where userId = :userid")
				.setBigInteger("userid",
						BigInteger.valueOf(request.getUserid())).list();
		if (list.size() == 1) {
			response.setEmailaddress(request.getEmailaddress());
			response.setNickname(request.getNickname());
			response.setCountry(list.get(0).getCountry());
			response.setFirstname(list.get(0).getFirstname());
			response.setLastname(list.get(0).getLastname());
			return response;
		} else {
			response.setCountry(null);
			response.setEmailaddress(request.getEmailaddress());
			response.setFirstname(null);
			response.setLastname(null);
			response.setNickname(request.getNickname());
			return response;
		}

	};

	@Override
	@SuppressWarnings("unchecked")
	public String updateProfileInformation(
			UpdateProfileInformationRequest request) {
		String result = "Success";
		try {
			List<UserProfile> list = sessionFactory
					.getCurrentSession()
					.createQuery("from UserProfile where userId = :userid")
					.setBigInteger("userid",
							BigInteger.valueOf(request.getUserid())).list();
			if (list.size() == 1) {
				UserProfile profile = list.get(0);
				profile.setCountry(request.getCountry());
				profile.setFirstname(request.getFirstname());
				profile.setLastname(request.getLastname());
				sessionFactory.getCurrentSession().merge(profile);
			} else {
				// Insert records...
				UserProfile profile = new UserProfile(request.getUserid(),
						request.getFirstname(), request.getLastname(),
						request.getCountry(), null);
				sessionFactory.getCurrentSession().persist(profile);
			}

		} catch (Exception e) {
			result = "failure";
			e.printStackTrace();
		}
		return result;
	}

	@Override
	@SuppressWarnings("unchecked")
	public GetNotificationCountResponse getNotificationCount(
			GetNotificationCountRequest request) {
		GetNotificationCountResponse response = new GetNotificationCountResponse();
		response.setEmailaddress(request.getEmailaddress());
		response.setNickname(request.getNickname());

		List<UserNotifications> notificationlist = sessionFactory
				.getCurrentSession()
				.createQuery(
						"from UserNotifications t where t.id.userid = :userid and t.notificationStatus = :notificationstatus")
				.setBigInteger("userid",BigInteger.valueOf(request.getUserid()))
				.setParameter("notificationstatus",
						DatabaseValueConstants.NOTIFICATION_UNREAD).list();
		if (notificationlist != null)
			response.setNotificationCount(notificationlist.size());

		List<FriendRequest> addfriendlist = sessionFactory
				.getCurrentSession()
				.createQuery(
						"from FriendRequest t where t.id.userId = :userid and t.status = :status")
				.setBigInteger("userid",BigInteger.valueOf(request.getUserid()))
				.setParameter("status",
						DatabaseValueConstants.FRIEND_REQUEST_PENDING).list();
		if (addfriendlist != null)
			response.setFriendRequestCount(addfriendlist.size());

		List<GamejoinRequest> gameinvitelist = sessionFactory
				.getCurrentSession()
				.createQuery(
						"from GamejoinRequest t where t.id.userId = :userid and t.status = :status")
				.setBigInteger("userid",BigInteger.valueOf(request.getUserid()))
				.setParameter("status",
						DatabaseValueConstants.GAMEJOIN_REQUEST_UNREAD).list();
		if (gameinvitelist != null)
			response.setGameInviteCount(gameinvitelist.size());

		return response;

	}

	@Override
	@SuppressWarnings("unchecked")
	public GetActiveAddFriendList getPendingAddFriends(
			GetActiveFriendRequest request) {
		GetActiveAddFriendList response = new GetActiveAddFriendList();
		response.setNickname(request.getNickname());
		response.setEmailaddr(request.getEmailaddress());
	
        ArrayList<GetActiveFriendResponse> friendslist = new ArrayList<GetActiveFriendResponse>();
		List<FriendRequest> addfriendlist = sessionFactory
				.getCurrentSession()
				.createQuery(
						"from FriendRequest t where t.id.userId = :userid and t.status = :status")
				.setBigInteger("userid",BigInteger.valueOf(request.getUserid()))
				.setParameter("status",
						DatabaseValueConstants.FRIEND_REQUEST_PENDING).list();
		if (addfriendlist != null)
		{
		    for(FriendRequest frequest : addfriendlist)
		    {
		    	String nickname = authdao.findUserbyID(frequest.getRequestorIdn()).getNickName();
		    	GetActiveFriendResponse friendinfo = new GetActiveFriendResponse(nickname, frequest.getRequestedDate());
		    	friendslist.add(friendinfo);
		    }
		}
		response.setActiveFriendlist(friendslist);
		return response;
	}

	@Override
	@SuppressWarnings("unchecked")
	public GetActiveGameInviteList getActiveGameInvites(
			GetActiveGameInviteRequest request) {
		
		GetActiveGameInviteList response = new GetActiveGameInviteList();
		response.setNickname(request.getNickname());
		response.setEmailaddr(request.getEmailaddress());
	
        ArrayList<GetActiveGameInviteResponse> gamelist = new ArrayList<GetActiveGameInviteResponse>();
        List<GamejoinRequest> gameinvitelist = sessionFactory
				.getCurrentSession()
				.createQuery(
						"from GamejoinRequest t where t.id.userId = :userid and t.status = :status")
				.setBigInteger("userid",BigInteger.valueOf(request.getUserid()))
				.setParameter("status",
						DatabaseValueConstants.GAMEJOIN_REQUEST_UNREAD).list();
		if (gameinvitelist != null)
		{
		    for(GamejoinRequest gjrequest : gameinvitelist)
		    {
		    	String nickname = authdao.findUserbyID(gjrequest.getRequestorIdn()).getNickName();
		    	GetActiveGameInviteResponse gameinfo = new GetActiveGameInviteResponse(nickname, gjrequest.getGameInstanceId(), gjrequest.getGameLobby(), gjrequest.getGameType(),null);
		    	gamelist.add(gameinfo);
		    }
		}
		response.setGameinviteList(gamelist);
		return response;

	}

	@Override
	@SuppressWarnings("unchecked")
	public GetActiveNotificationList getActiveNotifications(
			GetActiveNotificationRequest request) {
		GetActiveNotificationList response = new GetActiveNotificationList();
		response.setNickname(request.getNickname());
		response.setEmailaddr(request.getEmailaddress());
	
        ArrayList<GetActiveNotificationResponse> activenotificationlist = new ArrayList<GetActiveNotificationResponse>();
        List<UserNotifications> notificationlist = sessionFactory
				.getCurrentSession()
				.createQuery(
						"from UserNotifications t where t.id.userid = :userid and t.notificationStatus = :notificationstatus")
				.setBigInteger("userid",BigInteger.valueOf(request.getUserid()))
				.setParameter("notificationstatus",
						DatabaseValueConstants.NOTIFICATION_UNREAD).list();
		if (notificationlist != null)
		{
		    for(UserNotifications unrequest : notificationlist)
		    {
		    	GetActiveNotificationResponse notifyinfo = new GetActiveNotificationResponse(unrequest.getNotificationType(), unrequest.getNotificationDesc(), unrequest.getNotifiedBy(), unrequest.getCreatedDate());
		    	activenotificationlist.add(notifyinfo);
		    	unrequest.setNotificationStatus(DatabaseValueConstants.NOTIFICATION_READ);
		    	sessionFactory.getCurrentSession().merge(unrequest);
		    }
		}
		response.setNotificationList(activenotificationlist);
		return response;

	}
	
	@Override
	@SuppressWarnings("unchecked")
	public GetFriendListResponse getUserFriends(GetFriendsListRequest request) {
		GetFriendListResponse response = new GetFriendListResponse();
		response.setMynickname(request.getNickname());
		ArrayList<FriendResponse> friendList = new ArrayList<FriendResponse>();
		
		List<UserFriends> listofFriends = sessionFactory.getCurrentSession().createQuery("from UserFriends where id.userId = :var_userId")
		.setBigInteger("var_userId",BigInteger.valueOf(request.getUserid())).list();
		
		if(listofFriends.size() > 0)
		{
			for(int i=0;i<listofFriends.size();i++)
			{
			   UserFriends friend = listofFriends.get(i);
			   UserAccounts account = authdao.findUserbyID(friend.getFriendsIdn());
			   List<RummyStats> list = sessionFactory
						.getCurrentSession()
						.createQuery("from RummyStats where userId = :var_userId")
						.setBigInteger("var_userId",
								BigInteger.valueOf(friend.getFriendsIdn())).list();
			   FriendResponse fresponse = new FriendResponse(account.getNickName(), account.getId().getEmailAddr(), list.get(0).getRating());
			   friendList.add(fresponse);
			}
		}
		response.setFriendlist(friendList);		
		return response;
	}
	
	@Override
	@SuppressWarnings("unchecked")
    public  String addFriend(AddFriendRequest request)
    {
    	String result = "Success";
    	try
    	{
        long useridn = authdao.findUserbyNickName(request.getDesinationNickname()).getId().getUserId();
        long requestoridn = authdao.findUserbyNickName(request.getNickName()).getId().getUserId();
    	List<FriendRequest> list = sessionFactory.getCurrentSession().
    	createQuery("from FriendRequest where id.userId = :varuserid and requestorIdn = :requestoridn")
    	.setBigInteger("varuserid", BigInteger.valueOf(useridn))
    	.setBigInteger("requestoridn",BigInteger.valueOf(requestoridn)).list();
        if(list.size() > 0)
        {
        	System.out.println("Friend Request Already exists");
        	return result;
        }
    	FriendRequest frequest = new FriendRequest();
    	FriendRequestId id = new FriendRequestId();   	
    	id.setUserId(useridn);
    	frequest.setId(id);
    	frequest.setRequestorIdn(requestoridn);
    	frequest.setRequestedDate(Calendar.getInstance().getTime());
    	frequest.setStatus(DatabaseValueConstants.FRIEND_REQUEST_PENDING);
    	sessionFactory.getCurrentSession().persist(frequest);
    	}catch(Exception e)
    	{
    		e.printStackTrace();
    		result = "Failure";
    	}
    	return result;
    }

	@Override
	public String sendGameInvite(SendGameInviteRequest request) {
		 String result = "Success";
		 try
		 {  
		    for(String nickname : request.getNicknames())
		    {
		    	GamejoinRequestId id = new GamejoinRequestId();		    	
		    	long userid = authdao.findUserbyNickName(nickname).getId().getUserId();
		    	long requestorIdn = authdao.findUserbyNickName(request.getInvitornickname()).getId().getUserId();
		    	id.setUserId(userid);
		    	GamejoinRequest gjrequest = new GamejoinRequest(id, requestorIdn, request.getGameInstanceID(), request.getGameType(), request.getLobbyName(), DatabaseValueConstants.GAMEJOIN_REQUEST_UNREAD);
		    	sessionFactory.getCurrentSession().persist(gjrequest);
		    }
		 }catch(Exception e)
		 {
			 e.printStackTrace();
			 result = "Failure";
		 }		
		 return result;
	}

	@Override
	@SuppressWarnings("unchecked")
	public String confirmFriendRequest(ConfirmIgnoreFriendRequest request) {
		String result = "Success";
		try
		{
			long useridn = request.getMyuserid();
			long requestoridn = authdao.findUserbyNickName(request.getRequestorNickName()).getId().getUserId();
			List<FriendRequest> list = sessionFactory.getCurrentSession().createQuery("from FriendRequest where id.userId = :varuserid and requestorIdn = :varrequestoridn and status = :varstatus")
			                                  .setBigInteger("varuserid", BigInteger.valueOf(useridn))
			                                  .setBigInteger("varrequestoridn", BigInteger.valueOf(requestoridn))
			                                  .setString("varstatus", DatabaseValueConstants.FRIEND_REQUEST_PENDING).list();
			if(list !=null && list.size() > 0)
			{
				FriendRequest frequest = list.get(0);
				if(request.getStatus().equals(DatabaseValueConstants.FRIEND_REQUEST_ACCEPTED))
				{
				           frequest.setStatus(DatabaseValueConstants.FRIEND_REQUEST_ACCEPTED);
				           
				           UserFriendsId idoneway = new UserFriendsId();
				           idoneway.setUserId(useridn);
				           UserFriends friendoneway = new UserFriends(idoneway, requestoridn,Calendar.getInstance().getTime(), null);
				           
				           
				           UserFriendsId idrevereseway = new UserFriendsId();
				           idrevereseway.setUserId(requestoridn);
				           UserFriends friendreverseway = new UserFriends(idrevereseway, useridn,Calendar.getInstance().getTime(), null);
				           
				           
				           sessionFactory.getCurrentSession().persist(friendoneway);
				           sessionFactory.getCurrentSession().persist(friendreverseway);
				           
				}
				else
					       frequest.setStatus(DatabaseValueConstants.FRIEND_REQUEST_DENIED);
				
				frequest.setActionedDate(Calendar.getInstance().getTime());
				sessionFactory.getCurrentSession().merge(frequest);
			}
			else
			{
				result = "Failure : Can't find friend request bad request";
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	@SuppressWarnings("unchecked")
	public String confirmorIgnoreGameInviteRequest(ConfirmIgnoreGameInviteRequest request) {
		String result = "Success";		
		try
		{
		    long requestoridn = authdao.findUserbyNickName(request.getRequestorNickName()).getId().getUserId();
			List<GamejoinRequest> list = sessionFactory.getCurrentSession().createQuery("from GamejoinRequest where id.userId = :varuserid and requestorIdn = :varrequestoridn and gameInstanceId = :varGameinstanceid and gameType = :vargameType")
			                                  .setBigInteger("varuserid", BigInteger.valueOf(request.getUserid()))
			                                  .setBigInteger("varrequestoridn", BigInteger.valueOf(requestoridn))
			                                  .setString("varGameinstanceid", request.getGameInstanceID())
			                                  .setString("vargameType", request.getGameType()).list();
			if(list != null && list.size() > 0)
			{
				for(int i=0;i<list.size();i++)
				{
					GamejoinRequest gjrequest = list.get(i);
					gjrequest.setStatus(DatabaseValueConstants.GAMEJOIN_REQUEST_READ);
					sessionFactory.getCurrentSession().merge(gjrequest);
				}
			}
			
		}catch(Exception e)
		{
			result = "Failure";
			e.printStackTrace();
		}		
		return result;
	}
	

	@Override
	public String addNotification(AddNotificationRequest request) {
		String result = "Success";		
		try
		{
	   UserNotificationsId id = new UserNotificationsId();
	   id.setUserid(authdao.findUserbyNickName(request.getNotificationfor()).getId().getUserId());
	   UserNotifications notification = new UserNotifications(id, request.getNotificationType(), 
			                                                 request.getNotificationDesc(),request.getNotifiedby(), 
			                                                 DatabaseValueConstants.NOTIFICATION_UNREAD, 
			                                                 Calendar.getInstance().getTime());
	   sessionFactory.getCurrentSession().persist(notification);
		}catch(Exception e)
		{
			result = "Failure";
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public GetUserAchivementList getUserAcheivements(GetUserAcheivementRequest request) {
		GetUserAchivementList resultlist = new GetUserAchivementList();
		try
		{
		ArrayList<GetUserAcheivementResponse> responselist = new ArrayList<GetUserAcheivementResponse>();
		resultlist.setNickname(request.getNickname());
		@SuppressWarnings("unchecked")
		List<Object[]> list = sessionFactory.getCurrentSession().createQuery("select b.uidivid,b.achievementAbbr,b.achivementDesc,a.status,a.unlockedDate " 
				                                      + "from UserAcheivements a,AchievementConfig b  "
				                                      + "where a.id.userId = :varuserid and "
				                                      + " a.id.achivementId = b.acheivementId and "
				                                      + " a.status = :varstatus")
				                                      .setBigInteger("varuserid", BigInteger.valueOf(request.getUserid()))
				                                      .setString("varstatus", DatabaseValueConstants.ACHIEVEMENT_STATUS_UNLOCKED)
				                                      .list();
	   
       if(list !=null && list.size() > 0)
       {
    	  for(Object[] row : list)
    	  {
    		  String divid = (String)row[0]; String achievementabbr = (String)row[1]; String achivementDesc = (String)row[2];
    		  String status = (String) row[3]; Date date = (Date) row[4]; 
    		  System.out.println(row.length+ " ,Div id : " + divid + ", " + achievementabbr + "," + achivementDesc + "," + status + ", " +  date);
    		  
    		  GetUserAcheivementResponse response = new GetUserAcheivementResponse(divid, achievementabbr, achivementDesc, date);
    		  responselist.add(response);
    	  }
       }
		
		resultlist.setAcheivementlist(responselist);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return resultlist;
	}
	
	
	
	
	
}
