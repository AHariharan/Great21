package com.adansoft.great21.dataaccess.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.adansoft.great21.dataaccess.dao.BasicDataAccessDAOImpl;
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

@RestController
@RequestMapping(DataAccessServiceURLs.DATAACCESS_BASE)
public class DataServiceController {
	
	@Autowired
	private BasicDataAccessDAOImpl basicdatadao;

	@Transactional
	@RequestMapping( value = DataAccessServiceURLs.BASIC_DETAILS, method = RequestMethod.POST)
	public @ResponseBody GetUserBasicDetailsResponse getBasicUserDetails(@RequestBody GetUserBasicDetailsRequest request)
	{
		return basicdatadao.getBasicDetails(request);
	}
	
	@Transactional
	@RequestMapping( value = DataAccessServiceURLs.USER_AUDIT, method = RequestMethod.POST)
	public @ResponseBody String insertAuditInformation(@RequestBody UserAuditRequest request)
	{
		return basicdatadao.addAudit(request);
	}
	
	@Transactional
	@RequestMapping( value = DataAccessServiceURLs.USER_PROFILE_GET, method = RequestMethod.POST)
	public @ResponseBody GetProfileInformationResponse getProfileInformation(@RequestBody GetProfileInformationRequest request)
	{
		return basicdatadao.getProfileInformation(request);
	}

	
	@Transactional
	@RequestMapping( value = DataAccessServiceURLs.USER_PROFILE_UPDATE, method = RequestMethod.POST)
	public @ResponseBody String updateProfileInformation(@RequestBody UpdateProfileInformationRequest request)
	{
		return basicdatadao.updateProfileInformation(request);
	}
	
	@Transactional
	@RequestMapping( value = DataAccessServiceURLs.GET_NOTIFICATION_COUNT, method = RequestMethod.POST)
	public @ResponseBody GetNotificationCountResponse getNotificationCount(@RequestBody GetNotificationCountRequest request)
	{
		return basicdatadao.getNotificationCount(request);
	}
	
	@Transactional
	@RequestMapping( value = DataAccessServiceURLs.GET_ACTIVE_ADDFRIEND, method = RequestMethod.POST)
	public @ResponseBody GetActiveAddFriendList getAddFriendRequest(@RequestBody GetActiveFriendRequest request)
	{
		return basicdatadao.getPendingAddFriends(request);
	}
	
	@Transactional
	@RequestMapping( value = DataAccessServiceURLs.GET_ACTIVE_GAMEINVITE, method = RequestMethod.POST)
	public @ResponseBody GetActiveGameInviteList getGameInvites(@RequestBody GetActiveGameInviteRequest request)
	{
		return basicdatadao.getActiveGameInvites(request);
	}
	
	@Transactional
	@RequestMapping( value = DataAccessServiceURLs.GET_ACTIVE_NOTIFICATION, method = RequestMethod.POST)
	public @ResponseBody GetActiveNotificationList getActiveNotifications(@RequestBody GetActiveNotificationRequest request)
	{
		return basicdatadao.getActiveNotifications(request);
	}
	
	@Transactional
	@RequestMapping( value = DataAccessServiceURLs.GET_FRIEND_LIST, method = RequestMethod.POST)
	public @ResponseBody GetFriendListResponse getUserFriends(@RequestBody GetFriendsListRequest request)
	{
		return basicdatadao.getUserFriends(request);
	}

	
	@Transactional
	@RequestMapping( value = DataAccessServiceURLs.ADD_FRIEND_REQUEST, method = RequestMethod.POST)
	public @ResponseBody String addFriend(@RequestBody AddFriendRequest request)
	{
		return basicdatadao.addFriend(request);
	}
	
	
	@Transactional
	@RequestMapping( value = DataAccessServiceURLs.SEND_GAME_INVITE, method = RequestMethod.POST)
	public @ResponseBody String addFriend(@RequestBody SendGameInviteRequest request)
	{
		return basicdatadao.sendGameInvite(request);
	}

	@Transactional
	@RequestMapping( value = DataAccessServiceURLs.CONFIRM_IGNORE_FRIEND_REQUEST, method = RequestMethod.POST)
	public @ResponseBody String confirmorIgnoreFriend(@RequestBody ConfirmIgnoreFriendRequest request)
	{
		return basicdatadao.confirmFriendRequest(request);
	}
	
	@Transactional
	@RequestMapping( value = DataAccessServiceURLs.CONFIRM_IGNORE_GAMEJOIN_REQUEST, method = RequestMethod.POST)
	public @ResponseBody String confirmorIgnoreFriend(@RequestBody ConfirmIgnoreGameInviteRequest request)
	{
		return basicdatadao.confirmorIgnoreGameInviteRequest(request);
		
	}
	
	@Transactional
	@RequestMapping( value = DataAccessServiceURLs.ADDNOTIFICATION_FRONTEND, method = RequestMethod.POST)
	public @ResponseBody String addNotification(@RequestBody AddNotificationRequest request)
	{
		return basicdatadao.addNotification(request);
		
	}
	
	@Transactional
	@RequestMapping( value = DataAccessServiceURLs.GET_USER_ACHEIVEMENTS, method = RequestMethod.POST)
	public @ResponseBody GetUserAchivementList getUserAcheivements(@RequestBody GetUserAcheivementRequest request)
	{
		return basicdatadao.getUserAcheivements(request);
		
	}
	
	@Transactional
	@RequestMapping( value = DataAccessServiceURLs.GET_USER_MESSAGES, method = RequestMethod.POST)
	public @ResponseBody GetGameMessageResponse getUserMessages(@RequestBody GetGameMessageRequest request)
	{
		return basicdatadao.getUserMessages(request);
		
	}
	
	@Transactional
	@RequestMapping( value = DataAccessServiceURLs.SEND_USERMESSAGE, method = RequestMethod.POST)
	public @ResponseBody String sendGameMessage(@RequestBody GameMessage request)
	{
		return basicdatadao.sendGameMessage(request);
		
	}
	
	@Transactional
	@RequestMapping( value = DataAccessServiceURLs.GET_USER_MESSAGES_COUNT, method = RequestMethod.POST)
	public @ResponseBody int getUnreadMessages(@RequestBody GetGameMessageRequest request)
	{
		return basicdatadao.getUnreadMessageCount(request);
		
	}
	
	
	@Transactional
	@RequestMapping( value = DataAccessServiceURLs.DELETE_USERMESSAGE, method = RequestMethod.POST)
	public @ResponseBody String deleteMessages(@RequestBody GameMessage request)
	{
		return basicdatadao.deleteGameMessage(request);
		
	}
	
	@Transactional
	@RequestMapping( value = DataAccessServiceURLs.READ_USERMESSAGE, method = RequestMethod.POST)
	public @ResponseBody String readMessage(@RequestBody GameMessage request)
	{
		return basicdatadao.readMessage(request);
		
	}
	
	@Transactional
	@RequestMapping( value = DataAccessServiceURLs.REPLY_USERMESSAGE, method = RequestMethod.POST)
	public @ResponseBody String replyToMessages(@RequestBody GameMessage request)
	{
		return basicdatadao.replyToGameMessage(request);
		
	}
	
}
