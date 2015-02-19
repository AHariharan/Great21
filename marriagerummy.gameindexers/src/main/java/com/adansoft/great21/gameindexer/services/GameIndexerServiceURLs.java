package com.adansoft.great21.gameindexer.services;

public class GameIndexerServiceURLs {

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
	
	
}
