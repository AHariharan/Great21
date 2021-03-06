package com.adansoft.great21.controllers;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.adansoft.great21.controller.helpers.RestServiceHelper;
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
import com.adansoft.great21.dataaccess.schemas.SendGameInviteRequest;
import com.adansoft.great21.dataaccess.schemas.UpdateProfileInformationRequest;
import com.adansoft.great21.exceptions.DataAccessConfigException;
import com.adansoft.great21.router.FacadetoDataAccessMapper;
import com.adansoft.great21.security.RummyUser;
import com.adansoft.great21.uischemas.NotificationEvent;
import com.adansoft.great21.uischemas.data.UIUpdateProfileInformationRequest;

@RestController
@RequestMapping(FacadeControllerURLs.DATAACCESS_BASE)
@EnableWebMvcSecurity
public class FacadeDataAccessController {

	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	FacadetoDataAccessMapper mapper;
	
	@Autowired
	WebSocketController notifier;
	
	@PostConstruct
	public void verifyGameIndexerConfig()
	{
		try
		{
		if(mapper.getDataAccessURI() == null)
		{
			System.out.println("Failed to get DataAccess config .. exiting");
			System.exit(0);
		}
		System.out.println("Data Access from Authentication Service: " + mapper.getDataAccessURI());
		}catch(DataAccessConfigException ex)
		{
			ex.printStackTrace();
			System.exit(0);
		} catch (Exception e) {
		   e.printStackTrace();
		   System.exit(0);
		}
	}
	
	@Secured("ROLE_USER")
	@RequestMapping( value = FacadeControllerURLs.USER_PROFILE_GET, method = RequestMethod.POST)
	public GetProfileInformationResponse getProfileInformation(@AuthenticationPrincipal Authentication authentication)
	{
		GetProfileInformationRequest request = new GetProfileInformationRequest();
		RummyUser user = (RummyUser)authentication.getPrincipal();
		request.setEmailaddress(user.getEmailaddr());
		request.setNickname(user.getNickname());
		request.setUserid(user.getUserid());
		return RestServiceHelper.getProfileInformation(mapper, restTemplate, request);
	}
	
	@Secured("ROLE_USER")
	@RequestMapping( value = FacadeControllerURLs.USER_PROFILE_UPDATE, method = RequestMethod.POST)
	public String updateProfileInformation(@RequestBody UIUpdateProfileInformationRequest incomingrequest,@AuthenticationPrincipal Authentication authentication)
	{
		UpdateProfileInformationRequest request = new UpdateProfileInformationRequest();
		RummyUser user = (RummyUser)authentication.getPrincipal();
		request.setEmailaddress(user.getEmailaddr());request.setNickname(user.getNickname());
		request.setUserid(user.getUserid());request.setCountry(incomingrequest.getCountry());
		request.setFirstname(incomingrequest.getFirstname());request.setLastname(incomingrequest.getLastname());
		return RestServiceHelper.updateProfileInformation(mapper, restTemplate, request);
	}
	
	@Secured("ROLE_USER")
	@RequestMapping( value = FacadeControllerURLs.GET_NOTIFICATION_COUNT, method = RequestMethod.POST)
	public GetNotificationCountResponse getNotificationCount(@RequestBody GetNotificationCountRequest incomingrequest,@AuthenticationPrincipal Authentication authentication)
	{
		RummyUser user = (RummyUser)authentication.getPrincipal();
		incomingrequest.setEmailaddress(user.getEmailaddr());incomingrequest.setNickname(user.getNickname());
		incomingrequest.setUserid(user.getUserid());
		return RestServiceHelper.getNotificationCount(mapper, restTemplate, incomingrequest);
	}
	
	@Secured("ROLE_USER")
	@RequestMapping( value = FacadeControllerURLs.GET_ACTIVE_ADDFRIEND, method = RequestMethod.POST)
	public GetActiveAddFriendList getActiveAddFriends(@RequestBody GetActiveFriendRequest incomingrequest,@AuthenticationPrincipal Authentication authentication)
	{
		RummyUser user = (RummyUser)authentication.getPrincipal();
		incomingrequest.setEmailaddress(user.getEmailaddr());incomingrequest.setNickname(user.getNickname());
		incomingrequest.setUserid(user.getUserid());
		return RestServiceHelper.getActiveAddFriendList(mapper, restTemplate, incomingrequest);
	}
	
	@Secured("ROLE_USER")
	@RequestMapping( value = FacadeControllerURLs.GET_ACTIVE_GAMEINVITE, method = RequestMethod.POST)
	public GetActiveGameInviteList getActiveGameInvites(@RequestBody GetActiveGameInviteRequest incomingrequest,@AuthenticationPrincipal Authentication authentication)
	{
		RummyUser user = (RummyUser)authentication.getPrincipal();
		incomingrequest.setEmailaddress(user.getEmailaddr());incomingrequest.setNickname(user.getNickname());
		incomingrequest.setUserid(user.getUserid());
		return RestServiceHelper.getActiveGameInvite(mapper, restTemplate, incomingrequest);
	}
	
	@Secured("ROLE_USER")
	@RequestMapping( value = FacadeControllerURLs.GET_ACTIVE_NOTIFICATION, method = RequestMethod.POST)
	public GetActiveNotificationList getActiveNotifications(@RequestBody GetActiveNotificationRequest incomingrequest,@AuthenticationPrincipal Authentication authentication)
	{
		RummyUser user = (RummyUser)authentication.getPrincipal();
		incomingrequest.setEmailaddress(user.getEmailaddr());incomingrequest.setNickname(user.getNickname());
		incomingrequest.setUserid(user.getUserid());
		return RestServiceHelper.getActiveNotifications(mapper, restTemplate, incomingrequest);
	}
	
	@Secured("ROLE_USER")
	@RequestMapping( value = FacadeControllerURLs.GET_FRIEND_LIST, method = RequestMethod.POST)
	public GetFriendListResponse getFriendList(@RequestBody GetFriendsListRequest incomingrequest,@AuthenticationPrincipal Authentication authentication)
	{
		RummyUser user = (RummyUser)authentication.getPrincipal();
		incomingrequest.setEmailaddress(user.getEmailaddr());incomingrequest.setNickname(user.getNickname());
		incomingrequest.setUserid(user.getUserid());
		return RestServiceHelper.getFriendsList(mapper, restTemplate, incomingrequest);
	}
	
	
	@Secured("ROLE_USER")
	@RequestMapping( value = FacadeControllerURLs.ADD_FRIEND_REQUEST, method = RequestMethod.POST)
	public String addFriend(@RequestBody AddFriendRequest incomingrequest,@AuthenticationPrincipal Authentication authentication)
	{
		RummyUser user = (RummyUser)authentication.getPrincipal();
		incomingrequest.setNickName(user.getNickname());	
		return RestServiceHelper.addFriend(notifier, user.getNickname(), mapper, restTemplate, incomingrequest);
	}
	
	
	@Secured("ROLE_USER")
	@RequestMapping( value = FacadeControllerURLs.SEND_GAME_INVITE, method = RequestMethod.POST)
	public String sendGameInvite(@RequestBody SendGameInviteRequest incomingrequest,@AuthenticationPrincipal Authentication authentication)
	{
		RummyUser user = (RummyUser)authentication.getPrincipal();
		incomingrequest.setInvitornickname(user.getNickname());		
		return RestServiceHelper.sendGameInvite(notifier,user.getNickname(),mapper, restTemplate, incomingrequest);
	}
	
	@Secured("ROLE_USER")
	@RequestMapping( value = FacadeControllerURLs.CONFIRM_IGNORE_FRIEND_REQUEST, method = RequestMethod.POST)
	public String confirmorIgnoreFriendRequest(@RequestBody ConfirmIgnoreFriendRequest incomingrequest,@AuthenticationPrincipal Authentication authentication)
	{
		RummyUser user = (RummyUser)authentication.getPrincipal();
		incomingrequest.setMyuserid(user.getUserid());
		return RestServiceHelper.confirmorIgnoreFriend(mapper, restTemplate, incomingrequest);
	}
	
	@Secured("ROLE_USER")
	@RequestMapping( value = FacadeControllerURLs.CONFIRM_IGNORE_GAMEJOIN_REQUEST, method = RequestMethod.POST)
	public String confirmorIgnoreFriendRequest(@RequestBody ConfirmIgnoreGameInviteRequest incomingrequest,@AuthenticationPrincipal Authentication authentication)
	{
		RummyUser user = (RummyUser)authentication.getPrincipal();
		incomingrequest.setUserid(user.getUserid());
		return RestServiceHelper.confirmorIgnoreGameInviteRequest(mapper, restTemplate, incomingrequest);
	}
	
	@Secured("ROLE_USER")
	@RequestMapping( value = FacadeControllerURLs.ADDNOTIFICATION_FRONTEND, method = RequestMethod.POST)
	public String addNotification(@RequestBody AddNotificationRequest incomingrequest,@AuthenticationPrincipal Authentication authentication)
	{
		RummyUser user = (RummyUser)authentication.getPrincipal();
		incomingrequest.setNotifiedby(user.getNickname());
		return RestServiceHelper.addNotification(notifier,user.getNickname(),mapper, restTemplate, incomingrequest);
	}
	
	@Secured("ROLE_USER")
	@RequestMapping( value = FacadeControllerURLs.GET_USER_ACHEIVEMENTS, method = RequestMethod.POST)
	public GetUserAchivementList getUserAcheivements(@RequestBody GetUserAcheivementRequest incomingrequest,@AuthenticationPrincipal Authentication authentication)
	{
		RummyUser user = (RummyUser)authentication.getPrincipal();
		incomingrequest.setUserid(user.getUserid());
		incomingrequest.setNickname(user.getNickname());
		incomingrequest.setEmailaddress(user.getEmailaddr());		
		return RestServiceHelper.getUserAcheivements(mapper, restTemplate, incomingrequest);
	}
	
	
	@Secured("ROLE_USER")
	@RequestMapping( value = FacadeControllerURLs.GET_USER_MESSAGES , method = RequestMethod.POST)
	public GetGameMessageResponse getUserMessages(@RequestBody GetGameMessageRequest incomingrequest,@AuthenticationPrincipal Authentication authentication)
	{
		RummyUser user = (RummyUser)authentication.getPrincipal();
		incomingrequest.setUserid(user.getUserid());
		if(incomingrequest.getNoofrecentmessages() > 100)
			incomingrequest.setNoofrecentmessages(100);
		return RestServiceHelper.getUserMessages(mapper, restTemplate, incomingrequest);
	}
	
	
	@Secured("ROLE_USER")
	@RequestMapping( value = FacadeControllerURLs.SEND_USERMESSAGE , method = RequestMethod.POST)
	public String sendUserMessage(@RequestBody GameMessage incomingrequest,@AuthenticationPrincipal Authentication authentication)
	{
		RummyUser user = (RummyUser)authentication.getPrincipal();
		incomingrequest.setFrom(user.getNickname());
		return RestServiceHelper.sendUserMessage(mapper, restTemplate, incomingrequest);
	}
	
	
	@Secured("ROLE_USER")
	@RequestMapping( value = FacadeControllerURLs.DELETE_USERMESSAGE , method = RequestMethod.POST)
	public String deleteUserMessage(@RequestBody GameMessage incomingrequest,@AuthenticationPrincipal Authentication authentication)
	{
		RummyUser user = (RummyUser)authentication.getPrincipal();
		incomingrequest.setFrom(user.getNickname());
		return RestServiceHelper.deleteUserMessage(mapper, restTemplate, incomingrequest);
	}
	
	@Secured("ROLE_USER")
	@RequestMapping( value = FacadeControllerURLs.READ_USERMESSAGE , method = RequestMethod.POST)
	public String readMessage(@RequestBody GameMessage incomingrequest,@AuthenticationPrincipal Authentication authentication)
	{
		RummyUser user = (RummyUser)authentication.getPrincipal();
		incomingrequest.setFrom(user.getNickname());
		return RestServiceHelper.readUserMessage(mapper, restTemplate, incomingrequest);
	}
	
	
	@Secured("ROLE_USER")
	@RequestMapping( value = FacadeControllerURLs.REPLY_USERMESSAGE , method = RequestMethod.POST)
	public String replyToUserMessage(@RequestBody GameMessage incomingrequest,@AuthenticationPrincipal Authentication authentication)
	{
		RummyUser user = (RummyUser)authentication.getPrincipal();
		incomingrequest.setFrom(user.getNickname());
		return RestServiceHelper.replyToUserMessage(mapper, restTemplate, incomingrequest);
	}
	
	
}
