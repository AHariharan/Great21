package com.adansoft.great21.dataaccess.controllers;

public class DataAccessServiceURLs {

	public final static String DATAACCESS_AUTHBASE = "/DataAccess/Authenticate";
	public final static String FINDUSER = "/UserDetails/Get";
	public final static String SIGNUP = "/User/Signup";
	public final static String ACTIVATE_ACCOUNT = "/User/Activate";
	public final static String RESEND_ACTIVATION = "/User/activation/resend";
	
	public final static String DATAACCESS_BASE = "/DataAccess/Data";	
	public final static String BASIC_DETAILS = "/BasicUserDetails/Get";
	public final static String USER_AUDIT = "/BasicUserDetails/Audit/create";
	public final static String USER_PROFILE_GET = "/BasicUserDetails/ProfileInfo/get";
	public final static String USER_PROFILE_UPDATE = "/BasicUserDetails/ProfileInfo/Update";
	public final static String GET_NOTIFICATION_COUNT = "/BasicUserDetails/GetNotificationCount";
	public final static String GET_ACTIVE_ADDFRIEND = "/BasicUserDetails/GetActiveFriend";
	public final static String GET_ACTIVE_GAMEINVITE = "/BasicUserDetails/GetActiveGameInvite";
	public final static String GET_ACTIVE_NOTIFICATION = "/BasicUserDetails/GetActiveNotifications";
	public final static String GET_FRIEND_LIST = "/BasicUserDetails/Friends/Get";
	public final static String ADD_FRIEND_REQUEST = "/BasicUserDetails/Friends/Add";
	public final static String CONFIRM_IGNORE_FRIEND_REQUEST = "/BasicUserDetails/Friend/ConfirmorIgnore";
	public final static String SEND_GAME_INVITE = "/BasicUserDetails/GameInvite/Send";
	public final static String CONFIRM_IGNORE_GAMEJOIN_REQUEST = "/BasicUserDetails/GameInvite/ConfirmorIgnore";
	public final static String ADDNOTIFICATION_FRONTEND = "/BasicUserDetails/Player/Notification/Add";
	public final static String GET_USER_ACHEIVEMENTS = "/BasicUserDetails/Player/Acheivement/get";
	public final static String GET_USER_MESSAGES = "/BasicUserDetails/Player/Messages/get";
	public final static String GET_USER_MESSAGES_COUNT = "/BasicUserDetails/Player/MessageCount/get";
	public final static String SEND_USERMESSAGE = "/BasicUserDetails/Player/Message/Send";
	public final static String DELETE_USERMESSAGE = "/BasicUserDetails/Player/Message/Delete";
	public final static String REPLY_USERMESSAGE = "/BasicUserDetails/Player/Message/Reply";
	
	public final static String DELAYED_GAMEDATA_BASE = "/DelayedWrite/GameData";
	public final static String CREATED_GAME = "/CreateGame";	
	public final static String LAUNCH_GAME = "/Launch";
	public final static String DELETE_GAME = "/Delete";
	public final static String CREATE_GAME_ROUND = "/CreateRound";
	public final static String FINISH_GAME_ROUND = "/FinishRound";
	public final static String UPDATE_PLAYER_STATUS = "/PlayerCashPoints/persist";
	public final static String UPDATE_PLAYER_RUMMYSTAT = "/Player/RummyStat/Update";
	public final static String ADDNOTIFICATION_BACKEND = "/Player/Notification/Add";
	public final static String UNLOCK_ACHIEVEMENT = "/Player/Achievement/unlock";
}
