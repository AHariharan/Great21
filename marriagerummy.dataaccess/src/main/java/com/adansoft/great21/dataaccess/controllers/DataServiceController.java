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
import com.adansoft.great21.dataaccess.schemas.GetActiveAddFriendList;
import com.adansoft.great21.dataaccess.schemas.GetActiveFriendRequest;
import com.adansoft.great21.dataaccess.schemas.GetActiveGameInviteList;
import com.adansoft.great21.dataaccess.schemas.GetActiveGameInviteRequest;
import com.adansoft.great21.dataaccess.schemas.GetActiveNotificationList;
import com.adansoft.great21.dataaccess.schemas.GetActiveNotificationRequest;
import com.adansoft.great21.dataaccess.schemas.GetFriendListResponse;
import com.adansoft.great21.dataaccess.schemas.GetFriendsListRequest;
import com.adansoft.great21.dataaccess.schemas.GetNotificationCountRequest;
import com.adansoft.great21.dataaccess.schemas.GetNotificationCountResponse;
import com.adansoft.great21.dataaccess.schemas.GetProfileInformationRequest;
import com.adansoft.great21.dataaccess.schemas.GetProfileInformationResponse;
import com.adansoft.great21.dataaccess.schemas.GetUserBasicDetailsRequest;
import com.adansoft.great21.dataaccess.schemas.GetUserBasicDetailsResponse;
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
	@RequestMapping( value = DataAccessServiceURLs.GET_FRIEND_LIST, method = RequestMethod.POST)
	public @ResponseBody String addFriend(@RequestBody AddFriendRequest request)
	{
		return basicdatadao.addFriend(request);
	}

}
