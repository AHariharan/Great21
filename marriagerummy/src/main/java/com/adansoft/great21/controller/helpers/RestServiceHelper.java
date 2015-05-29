package com.adansoft.great21.controller.helpers;

import java.net.URI;

import org.springframework.web.client.RestTemplate;

import com.adansoft.great21.controllers.FacadeControllerURLs;
import com.adansoft.great21.controllers.WebSocketController;
import com.adansoft.great21.dataaccess.schemas.AddFriendRequest;
import com.adansoft.great21.dataaccess.schemas.AddNotificationRequest;
import com.adansoft.great21.dataaccess.schemas.ConfirmIgnoreFriendRequest;
import com.adansoft.great21.dataaccess.schemas.ConfirmIgnoreGameInviteRequest;
import com.adansoft.great21.dataaccess.schemas.GameMessage;
import com.adansoft.great21.dataaccess.schemas.GetActiveAddFriendList;
import com.adansoft.great21.dataaccess.schemas.GetActiveFriendRequest;
import com.adansoft.great21.dataaccess.schemas.GetActiveGameInviteList;
import com.adansoft.great21.dataaccess.schemas.GetActiveGameInviteRequest;
import com.adansoft.great21.dataaccess.schemas.GetActiveNotificationList;
import com.adansoft.great21.dataaccess.schemas.GetActiveNotificationRequest;
import com.adansoft.great21.dataaccess.schemas.GetFriendListResponse;
import com.adansoft.great21.dataaccess.schemas.GetFriendsListRequest;
import com.adansoft.great21.dataaccess.schemas.GetGameMessageRequest;
import com.adansoft.great21.dataaccess.schemas.GetGameMessageResponse;
import com.adansoft.great21.dataaccess.schemas.GetNotificationCountRequest;
import com.adansoft.great21.dataaccess.schemas.GetNotificationCountResponse;
import com.adansoft.great21.dataaccess.schemas.GetProfileInformationRequest;
import com.adansoft.great21.dataaccess.schemas.GetProfileInformationResponse;
import com.adansoft.great21.dataaccess.schemas.GetUserAcheivementRequest;
import com.adansoft.great21.dataaccess.schemas.GetUserAchivementList;
import com.adansoft.great21.dataaccess.schemas.GetUserBasicDetailsRequest;
import com.adansoft.great21.dataaccess.schemas.GetUserBasicDetailsResponse;
import com.adansoft.great21.dataaccess.schemas.SendGameInviteRequest;
import com.adansoft.great21.dataaccess.schemas.UpdateProfileInformationRequest;
import com.adansoft.great21.dataaccess.schemas.UserAuditRequest;
import com.adansoft.great21.router.FacadetoDataAccessMapper;
import com.adansoft.great21.uischemas.NotificationEvent;

public class RestServiceHelper {

	public RestServiceHelper() {
		
	}

	public static GetUserBasicDetailsResponse getBasicDetails(FacadetoDataAccessMapper mapper,RestTemplate template,GetUserBasicDetailsRequest request)
	{
		GetUserBasicDetailsResponse response = null;
		try
		{
		URI url = new URI(mapper.getDataAccessURI() + "/"
				+ FacadeControllerURLs.DATAACCESS_BASE + "/"
				+ FacadeControllerURLs.BASIC_DETAILS);
		response = template.postForEntity(url, request, GetUserBasicDetailsResponse.class).getBody();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return response;
	}
	
	public static String insertAudit(FacadetoDataAccessMapper mapper,RestTemplate template,UserAuditRequest request)
	{
		String response = null;
		try
		{
		URI url = new URI(mapper.getDataAccessURI() + "/"
				+ FacadeControllerURLs.DATAACCESS_BASE + "/"
				+ FacadeControllerURLs.USER_AUDIT);
		response = template.postForEntity(url, request, String.class).getBody();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return response;
	}
	
	public static GetProfileInformationResponse getProfileInformation(FacadetoDataAccessMapper mapper,RestTemplate template,GetProfileInformationRequest request)
	{
		GetProfileInformationResponse response = null;
		try
		{
		URI url = new URI(mapper.getDataAccessURI() + "/"
				+ FacadeControllerURLs.DATAACCESS_BASE + "/"
				+ FacadeControllerURLs.USER_PROFILE_GET);
		response = template.postForEntity(url, request, GetProfileInformationResponse.class).getBody();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return response;
	}
	
	
	public static String updateProfileInformation(FacadetoDataAccessMapper mapper,RestTemplate template,UpdateProfileInformationRequest request)
	{
		String response = null;
		try
		{
		URI url = new URI(mapper.getDataAccessURI() + "/"
				+ FacadeControllerURLs.DATAACCESS_BASE + "/"
				+ FacadeControllerURLs.USER_PROFILE_UPDATE);
		response = template.postForEntity(url, request, String.class).getBody();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return response;
	}
	
	
	public static GetNotificationCountResponse getNotificationCount(FacadetoDataAccessMapper mapper,RestTemplate template,GetNotificationCountRequest request)
	{
		GetNotificationCountResponse response = null;
		try
		{
		URI url = new URI(mapper.getDataAccessURI() + "/"
				+ FacadeControllerURLs.DATAACCESS_BASE + "/"
				+ FacadeControllerURLs.GET_NOTIFICATION_COUNT);
		response = template.postForEntity(url, request, GetNotificationCountResponse.class).getBody();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return response;
	}
	
	public static GetActiveGameInviteList getActiveGameInvite(FacadetoDataAccessMapper mapper,RestTemplate template,GetActiveGameInviteRequest request)
	{
		GetActiveGameInviteList response = null;
		try
		{
		URI url = new URI(mapper.getDataAccessURI() + "/"
				+ FacadeControllerURLs.DATAACCESS_BASE + "/"
				+ FacadeControllerURLs.GET_ACTIVE_GAMEINVITE);
		response = template.postForEntity(url, request, GetActiveGameInviteList.class).getBody();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return response;
	}
	
	public static GetActiveAddFriendList getActiveAddFriendList(FacadetoDataAccessMapper mapper,RestTemplate template,GetActiveFriendRequest request)
	{
		GetActiveAddFriendList response = null;
		try
		{
		URI url = new URI(mapper.getDataAccessURI() + "/"
				+ FacadeControllerURLs.DATAACCESS_BASE + "/"
				+ FacadeControllerURLs.GET_ACTIVE_ADDFRIEND);
		response = template.postForEntity(url, request, GetActiveAddFriendList.class).getBody();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return response;
	}
	
	
	public static GetActiveNotificationList getActiveNotifications(FacadetoDataAccessMapper mapper,RestTemplate template,GetActiveNotificationRequest request)
	{
		GetActiveNotificationList response = null;
		try
		{
		URI url = new URI(mapper.getDataAccessURI() + "/"
				+ FacadeControllerURLs.DATAACCESS_BASE + "/"
				+ FacadeControllerURLs.GET_ACTIVE_NOTIFICATION);
		response = template.postForEntity(url, request, GetActiveNotificationList.class).getBody();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return response;
	}
	
	public static GetFriendListResponse getFriendsList(FacadetoDataAccessMapper mapper,RestTemplate template,GetFriendsListRequest request)
	{
		GetFriendListResponse response = null;
		try
		{
		URI url = new URI(mapper.getDataAccessURI() + "/"
				+ FacadeControllerURLs.DATAACCESS_BASE + "/"
				+ FacadeControllerURLs.GET_FRIEND_LIST);
		response = template.postForEntity(url, request, GetFriendListResponse.class).getBody();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return response;
	}
	
	
	public static String addFriend(WebSocketController notifier,String notifiedBy,FacadetoDataAccessMapper mapper,RestTemplate template,AddFriendRequest request)
	{
		String response = null;
		try
		{
		URI url = new URI(mapper.getDataAccessURI() + "/"
				+ FacadeControllerURLs.DATAACCESS_BASE + "/"
				+ FacadeControllerURLs.ADD_FRIEND_REQUEST);
		response = template.postForEntity(url, request, String.class).getBody();
		notifier.sendNotificationtoSpecificUser(new NotificationEvent("addFriend", "Server", null, notifiedBy), request.getDesinationNickname());
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return response;
	}
	
	public static String confirmorIgnoreFriend(FacadetoDataAccessMapper mapper,RestTemplate template,ConfirmIgnoreFriendRequest request)
	{
		String response = null;
		try
		{
		URI url = new URI(mapper.getDataAccessURI() + "/"
				+ FacadeControllerURLs.DATAACCESS_BASE + "/"
				+ FacadeControllerURLs.CONFIRM_IGNORE_FRIEND_REQUEST);
		response = template.postForEntity(url, request, String.class).getBody();
		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return response;
	}
	
	
	
	public static String sendGameInvite(WebSocketController notifier,String notifiedBy,FacadetoDataAccessMapper mapper,RestTemplate template,SendGameInviteRequest request)
	{
		String response = null;
		try
		{
		URI url = new URI(mapper.getDataAccessURI() + "/"
				+ FacadeControllerURLs.DATAACCESS_BASE + "/"
				+ FacadeControllerURLs.SEND_GAME_INVITE);
		response = template.postForEntity(url, request, String.class).getBody();
		for(String nickname : request.getNicknames())
		    notifier.sendNotificationtoSpecificUser(new NotificationEvent("gameInvite", "Server", null, notifiedBy), nickname);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return response;
	}
	

	public static String confirmorIgnoreGameInviteRequest(FacadetoDataAccessMapper mapper,RestTemplate template,ConfirmIgnoreGameInviteRequest request)
	{
		String response = null;
		try
		{
		URI url = new URI(mapper.getDataAccessURI() + "/"
				+ FacadeControllerURLs.DATAACCESS_BASE + "/"
				+ FacadeControllerURLs.CONFIRM_IGNORE_GAMEJOIN_REQUEST);
		response = template.postForEntity(url, request, String.class).getBody();
		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return response;
	}
	
	
	public static String addNotification(WebSocketController notifier,String notifiedBy,FacadetoDataAccessMapper mapper,RestTemplate template,AddNotificationRequest request)
	{
		String response = null;
		try
		{
		URI url = new URI(mapper.getDataAccessURI() + "/"
				+ FacadeControllerURLs.DATAACCESS_BASE + "/"
				+ FacadeControllerURLs.ADDNOTIFICATION_FRONTEND);
		response = template.postForEntity(url, request, String.class).getBody();
		notifier.sendNotificationtoSpecificUser(new NotificationEvent("GenericNotification", "Server", null, notifiedBy), request.getNotificationfor());
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return response;
	}
	
	
	public static GetUserAchivementList getUserAcheivements(FacadetoDataAccessMapper mapper,RestTemplate template,GetUserAcheivementRequest request)
	{
		GetUserAchivementList response = null;
		try
		{
		URI url = new URI(mapper.getDataAccessURI() + "/"
				+ FacadeControllerURLs.DATAACCESS_BASE + "/"
				+ FacadeControllerURLs.GET_USER_ACHEIVEMENTS);
		response = template.postForEntity(url, request, GetUserAchivementList.class).getBody();
		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return response;
	}
	
	
	public static GetGameMessageResponse getUserMessages(FacadetoDataAccessMapper mapper,RestTemplate template,GetGameMessageRequest request)
	{
		GetGameMessageResponse response = null;
		try
		{
		URI url = new URI(mapper.getDataAccessURI() + "/"
				+ FacadeControllerURLs.DATAACCESS_BASE + "/"
				+ FacadeControllerURLs.GET_USER_MESSAGES);
		response = template.postForEntity(url, request, GetGameMessageResponse.class).getBody();
		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return response;
	}
	
	public static String sendUserMessage(FacadetoDataAccessMapper mapper,RestTemplate template,GameMessage request)
	{
		String response = null;
		try
		{
		URI url = new URI(mapper.getDataAccessURI() + "/"
				+ FacadeControllerURLs.DATAACCESS_BASE + "/"
				+ FacadeControllerURLs.SEND_USERMESSAGE);
		response = template.postForEntity(url, request, String.class).getBody();
		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return response;
	}
	
	
	public static String deleteUserMessage(FacadetoDataAccessMapper mapper,RestTemplate template,GameMessage request)
	{
		String response = null;
		try
		{
		URI url = new URI(mapper.getDataAccessURI() + "/"
				+ FacadeControllerURLs.DATAACCESS_BASE + "/"
				+ FacadeControllerURLs.DELETE_USERMESSAGE);
		response = template.postForEntity(url, request, String.class).getBody();
		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return response;
	}
	
	
	public static String replyToUserMessage(FacadetoDataAccessMapper mapper,RestTemplate template,GameMessage request)
	{
		String response = null;
		try
		{
		URI url = new URI(mapper.getDataAccessURI() + "/"
				+ FacadeControllerURLs.DATAACCESS_BASE + "/"
				+ FacadeControllerURLs.REPLY_USERMESSAGE);
		response = template.postForEntity(url, request, String.class).getBody();
		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return response;
	}
	
	
}
