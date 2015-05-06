package com.adansoft.great21.controllers;

public class FacadeControllerURLs {

	/* URLS for Game Indexers */
	
	public final static String GAMEBROWSER_BASE = "/IndexerServices/GameBrowser";
	public final static String CREATEGAME = "createGame";
	public final static String DELETEGAME = "removeGame";
	public final static String GETGAMELIST = "GetGameListinLobby/{lobbyName}";
	public final static String ADD_PLAYER = "Player/Add";
	public final static String REMOVE_PLAYER = "Player/Remove";
	
	public final static String GAMELAUNCHER_BASE = "/IndexerServices/GameLauncher";
	public final static String ADDCHATMESSAGES = "/ChatMessages/Add";
	public final static String GETCHATMESSAGES = "/ChatMessages/Get";
	public final static String GETPLAYERSINGAME = "/Game/GetPlayers";
	public final static String LAUNCHAME = "/Game/Start";
	
	public final static String GAMEPLAY_BASE = "/IndexerServices/GamePlay";
	public final static String GETACTIVEPLAYERS = "/Game/GetActivePlayers";
	public final static String GETCARDS = "/Cards/Get";
	public final static String GETNEXTCARDFROMDECK = "/NextCardFromDeck/Get";
	public final static String GETJOKER = "/JokerCard/Get";
	public final static String GETOPENCARD = "/OpenCard/Get";
	public final static String ADDCARDTOHAND = "/HandCard/Add";
	public final static String DROPCARDFROMHAND = "/HandCard/Remove";
	public final static String SHOWJOKER = "/JokerCard/Show";
	public final static String PLAYERTURN = "/WhoseTurn/Get";
	public final static String SKIPPLAYERTURN = "/PlayerTurn/Skip";
	public final static String DECLAREGAME = "/CurrentGame/Declare";
	public final static String SORTCARDS = "/Player/Cards/Sort";
	public final static String SHOWMYCARDS = "/CurrentGame/Cards/Show";
	public final static String PLAYERSHOWSTATUS  = "/CurrentGame/ShowStatus/Get";
	public final static String FINISHROUND  = "/CurrentGame/Round/Complete";
	public final static String GETPOINTS  = "/CurrentGame/Points/Get";
	public final static String GETINFOBLOCK  = "/CurrentGame/GamePlay/Info/Get";
	public final static String GETPLAYERSTATUS  = "/CurrentGame/Player/Status/Get";
	public final static String GETELIMINATIONDETAILS = "/CurrentGame/Player/EliminationDetails/Get";
	public final static String GETWINNERDETAILS = "/CurrentGame/Player/WinnerDetails/Get";
	
	/* URLS for DataAccess Services */
	
	public final static String DATAACCESS_AUTHBASE = "/DataAccess/Authenticate";
	public final static String FINDUSER = "/UserDetails/Get";
	public final static String SIGNUP = "/User/Signup";
	public final static String ACTIVATE_ACCOUNT = "/User/Activate";
	public final static String RESEND_ACTIVATION = "/User/activation/resend";
	
	public final static String USERACCESS_BASE = "/UserAccess";
	
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
	public final static String SEND_GAME_INVITE = "/BasicUserDetails/GameInvite/Send";
	
	
}
