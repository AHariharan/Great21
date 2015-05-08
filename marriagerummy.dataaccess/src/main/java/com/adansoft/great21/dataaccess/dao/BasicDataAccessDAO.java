package com.adansoft.great21.dataaccess.dao;

import com.adansoft.great21.dataaccess.schemas.AddFriendRequest;
import com.adansoft.great21.dataaccess.schemas.AddNotificationRequest;
import com.adansoft.great21.dataaccess.schemas.ConfirmIgnoreFriendRequest;
import com.adansoft.great21.dataaccess.schemas.ConfirmIgnoreGameInviteRequest;
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
import com.adansoft.great21.dataaccess.schemas.GetUserAchivementList;
import com.adansoft.great21.dataaccess.schemas.GetUserBasicDetailsRequest;
import com.adansoft.great21.dataaccess.schemas.GetUserBasicDetailsResponse;
import com.adansoft.great21.dataaccess.schemas.SendGameInviteRequest;
import com.adansoft.great21.dataaccess.schemas.UpdateProfileInformationRequest;
import com.adansoft.great21.dataaccess.schemas.UserAuditRequest;
import com.adansoft.great21.uischemas.NotificationEvent;

public interface BasicDataAccessDAO {
	
	 public String addAudit(UserAuditRequest request);
	
     public GetUserBasicDetailsResponse getBasicDetails(GetUserBasicDetailsRequest request);
     
     public GetProfileInformationResponse getProfileInformation(GetProfileInformationRequest request);

     public String updateProfileInformation(UpdateProfileInformationRequest request);
     
     public GetNotificationCountResponse getNotificationCount(GetNotificationCountRequest request);
     
     public GetActiveAddFriendList getPendingAddFriends(GetActiveFriendRequest request);
     
     public GetActiveGameInviteList getActiveGameInvites(GetActiveGameInviteRequest request);
     
     public GetActiveNotificationList getActiveNotifications(GetActiveNotificationRequest request);
     
     public GetFriendListResponse getUserFriends(GetFriendsListRequest request);
     
     public String addFriend(AddFriendRequest request);
     
     public String sendGameInvite(SendGameInviteRequest request);
     
     public String confirmFriendRequest(ConfirmIgnoreFriendRequest request);
     
     public String confirmorIgnoreGameInviteRequest(ConfirmIgnoreGameInviteRequest request);
     
     public String addNotification(AddNotificationRequest request);
     
     public GetUserAchivementList getUserAcheivements(GetUserAcheivementRequest request);
     
}
