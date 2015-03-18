var MarriageRummy = MarriageRummy || {};

// Ulities Name space
MarriageRummy.Utilities = MarriageRummy.Utilities || {};

// UIUtilites Namespace
MarriageRummy.Utilities.CommunicationUtilities = MarriageRummy.Utilities.CommunicationUtilities
		|| {};

MarriageRummy.Utilities.CommunicationUtilities.HttpCommunicator = function() {
	var self = this;
	self.invokeAsyncRequest = function(url, formdata, onSuccessCallbackfn,
			onFailureCallbackfn, requestObj) {
		event.preventDefault();
		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");
		$.ajax({
			type : "POST",
			url : url,
			contentType : "application/json",
			data : JSON.stringify(formdata),
			consumes : "application/json",
			beforeSend : function(request) {
				request.setRequestHeader(header, token);
			},
			success : function(data, textStatus, jqXHR) {
				onSuccessCallbackfn(data, textStatus, jqXHR, requestObj);
			},
			error : function(data) {
				onFailureCallbackfn(data, requestObj);
			}

		});

	};
};

MarriageRummy.Utilities.CommunicationUtilities.URLS = function() {
	var self = this;

	self.getProfileInformation = "/marriagerummy/DataAccess/Data/BasicUserDetails/ProfileInfo/get";
	self.updateProfileInformation = "/marriagerummy/DataAccess/Data/BasicUserDetails/ProfileInfo/Update";

	self.createGame = "/marriagerummy/IndexerServices/GameBrowser/createGame";
	self.joinGame = "/marriagerummy/IndexerServices/GameBrowser/Player/Add";
	self.unjoinGame = "/marriagerummy/IndexerServices/GameBrowser/Player/Remove";
	self.deleteGame = "/marriagerummy/IndexerServices/GameBrowser/removeGame";

	self.getPlayersinGame = "/marriagerummy/IndexerServices/GameLauncher/Game/GetPlayers";
	self.addChatMessage = "/marriagerummy/IndexerServices/GameLauncher/ChatMessages/Add";
	self.getChatMessage = "/marriagerummy/IndexerServices/GameLauncher/ChatMessages/Get";
	self.launchGame = "/marriagerummy/IndexerServices/GameLauncher/Game/Start";

	self.getCards = "/marriagerummy/IndexerServices/GamePlay/Cards/Get";
	self.getJoker = "/marriagerummy/IndexerServices/GamePlay/JokerCard/Get";
	self.getOpenCard = "/marriagerummy/IndexerServices/GamePlay/OpenCard/Get";
	self.getNextCardFromDeck = "/marriagerummy/IndexerServices/GamePlay/NextCardFromDeck/Get";
	self.addCardToHand = "/marriagerummy/IndexerServices/GamePlay/HandCard/Add";
	self.dropCardFromHand = "/marriagerummy/IndexerServices/GamePlay/HandCard/Remove";
	self.showJoker = "/marriagerummy/IndexerServices/GamePlay/JokerCard/Show";
	self.getWhoseTurn = "/marriagerummy/IndexerServices/GamePlay/WhoseTurn/Get";
	self.skipPlayerTurn = "/marriagerummy/IndexerServices/GamePlay/PlayerTurn/Skip";
	self.declareGame = "/marriagerummy/IndexerServices/GamePlay/CurrentGame/Declare";
	self.sortCardsInHand = "/marriagerummy/IndexerServices/GamePlay/Player/Cards/Sort";
	self.showCards = "/marriagerummy/IndexerServices/GamePlay/CurrentGame/Cards/Show";
	self.showStatusPlayer = "/marriagerummy/IndexerServices/GamePlay/CurrentGame/ShowStatus/Get";
	self.getPlayerPoints = "/marriagerummy/IndexerServices/GamePlay/CurrentGame/Points/Get";

};

MarriageRummy.Utilities.CommunicationUtilities.RequestPreparer = function() {
	var self = this;

	var gamebrowserRequest = new MarriageRummy.Utilities.CommunicationUtilities.GameBrowserRequestPreparer();
	var gameplayRequest = new MarriageRummy.Utilities.CommunicationUtilities.GamePlayRequestPreparer();
	var dataaccessRequest = new MarriageRummy.Utilities.CommunicationUtilities.DataRequestPreparer();
	
	self.getGameBrowserRequest = function()
	{
	    return gamebrowserRequest;	
	};
	
	self.getGamePlayRequest = function()
	{
		return gameplayRequest;
	};
	
	self.getDataRequest = function()
	{
		return dataaccessRequest;
	};
	
};

MarriageRummy.Utilities.CommunicationUtilities.GameBrowserRequestPreparer = function() {
	var self = this;

	self.getCreateGameRequest = function(gameLobby, gameType) {
		var gameDesc = $('#creategamemodal #GameDesc').val();
		var maxplayers = $('#creategamemodal #MaxPlayers').val();
		var isFriendsOnly = $('#creategamemodal #isFriendsOnly')
				.prop("checked");
		var isInviteOnly = $('#creategamemodal #isByInviteonly')
				.prop("checked");
		var gamePointsBased = $('#creategamemodal #optionsRadios1').prop(
				"checked");
		var gamePerCardBase = $('#creategamemodal #optionsRadios2').prop(
				"checked");
		var Points = $('#creategamemodal #MaxPoints').val();
		var BuyinValue = $('#creategamemodal #buyinvalue').val();
		var CardValue = $('#creategamemodal #percardinputvalue').val();
		var formdata = {

			"lobbyType" : gameLobby,
			"gameType" : gameType,
			"gameDescription" : gameDesc,
			"maxPlayers" : maxplayers,
			"isFriendsOnly" : isFriendsOnly,
			"isbyInviteOnly" : isInviteOnly,
			"gameMode" : "Human",
			"createdBy" : "Auto",
			"gamePointsBased" : gamePointsBased,
			"gamePerCardBase" : gamePerCardBase,
			"maxPoints" : Points,
			"buyinValue" : BuyinValue,
			"perCardAmount" : CardValue,
			"maxRounds" : -1
		};

		return formdata;

	};

	self.getJoinGameRequest = function(lobbyType, gameInstanceID, gameType) {
		var formdata = {
			"playerType" : "HUMAN",
			"nickname" : "Auto",
			"gameInstanceID" : gameInstanceID,
			"lobbyName" : lobbyType,
			"gameType" : gameType
		};
		return formdata;
	};

	self.getPlayersinGameRequest = function(gameInstanceID, lobbyType, gameType) {
		var formdata = {
			"gameInstanceID" : gameInstanceID,
			"lobbyName" : lobbyType,
			"gameType" : gameType
		};
		return formdata;
	};

	self.getAddChatMessage = function(gameInstanceID, messageContent,
			PlayerName, PlayerPos) {
		var formdata = {
			"gameInstanceID" : gameInstanceID,
			"message" : {
				"playerName" : PlayerName,
				"message" : messageContent,
				"playerpos" : PlayerPos
			}
		};

		return formdata;
	};

	self.getChatMessage = function(gameInstanceID, currentChatCount) {
		var formdata = {
			"gameInstanceID" : gameInstanceID,
			"currentChatCount" : currentChatCount
		};
		return formdata;
	};

	self.getDeleteGameRequest = function(gameInstanceID, lobbyName, gameType) {
		var formdata = {
			"gameInstanceID" : gameInstanceID,
			"nickName" : "Auto",
			"lobbyName" : lobbyName,
			"gameType" : gameType
		};
		return formdata;
	};

	self.getUnjoinGameRequest = function(lobbyType, gameInstanceID, gameType) {
		var formdata = {
			"nickname" : "Auto",
			"gameInstanceID" : gameInstanceID,
			"lobbyName" : lobbyType,
			"gameType" : gameType,
			"requestedby" : "Auto"
		};
		return formdata;
	};

	self.getKickPlayerRequest = function(lobbyType, gameInstanceID, gameType,
			nickname) {
		var formdata = {
			"nickname" : nickname,
			"gameInstanceID" : gameInstanceID,
			"lobbyName" : lobbyType,
			"gameType" : gameType,
			"requestedby" : "Auto"
		};
		return formdata;
	};

	self.getLauchGameRequest = function(lobbyType, gameInstanceID, gameType) {
		var formdata = {
			"nickname" : "Auto",
			"gameInstanceID" : gameInstanceID,
			"lobbyName" : lobbyType,
			"gameType" : gameType,
		};
		return formdata;
	};

};

MarriageRummy.Utilities.CommunicationUtilities.GamePlayRequestPreparer = function() {
	var self = this;

	self.getCardRequest = function(lobbyType, gameInstanceID, gameType) {
		var formdata = {
			"nickname" : "Auto",
			"gameInstanceID" : gameInstanceID,
			"lobbyName" : lobbyType,
			"gameType" : gameType,
		};
		return formdata;
	};

	self.cardInHandRequest = function(lobbyType, gameInstanceID, gameType,
			cardInstanceID) {
		var formdata = {
			"card" : {
				"nickname" : "Auto",
				"gameInstanceID" : gameInstanceID,
				"lobbyName" : lobbyType,
				"gameType" : gameType,
				"cardInstanceID" : cardInstanceID
			}
		};
		return formdata;
	};

	self.showJokerRequest = function(lobbyType, gameInstanceID, gameType,
			cardInstanceList) {
		var formdata = {

			"nickname" : "Auto",
			"gameInstanceID" : gameInstanceID,
			"lobbyName" : lobbyType,
			"gameType" : gameType,
			"cardInstanceList" : cardInstanceList
		};
		return formdata;
	};

	self.getWhoseTurnRequest = function(lobbyType, gameInstanceID, gameType) {
		var formdata = {
			"nickname" : "Auto",
			"gameInstanceID" : gameInstanceID,
			"lobbyName" : lobbyType,
			"gameType" : gameType,
		};
		return formdata;
	};

	self.skipPlayerTurnRequest = function(lobbyType, gameInstanceID, gameType) {
		var formdata = {
			"nickname" : "Auto",
			"gameInstanceID" : gameInstanceID,
			"lobbyName" : lobbyType,
			"gameType" : gameType,
		};
		return formdata;
	};

	self.declareGameRequest = function(lobbyType, gameInstanceID, gameType,
			meldlist, closedCardInstanceid) {
		var formdata = {
			"nickname" : "Auto",
			"gameInstanceID" : gameInstanceID,
			"lobbyName" : lobbyType,
			"gameType" : gameType,
			"meldlist" : meldlist,
			"closedCardInstanceid" : closedCardInstanceid
		};
		return formdata;

	};

	self.sortCardsInHandRequest = function(lobbyType, gameInstanceID, gameType) {
		var formdata = {
			"nickname" : "Auto",
			"gameInstanceID" : gameInstanceID,
			"lobbyName" : lobbyType,
			"gameType" : gameType,
		};
		return formdata;
	};

	self.showCards = function(lobbyType, gameInstanceID, gameType, meldlist) {
		var formdata = {
			"nickname" : "Auto",
			"gameInstanceID" : gameInstanceID,
			"lobbyName" : lobbyType,
			"gameType" : gameType,
			"meldlist" : meldlist
		};
		return formdata;

	};

	self.showStatusforPlayers = function(gameInstanceID, lobbyType, gameType) {
		var formdata = {
			"nickname" : "Auto",
			"gameInstanceID" : gameInstanceID,
			"lobbyName" : lobbyType,
			"gameType" : gameType,
		};
		return formdata;
	};

	self.getPlayerPoints = function(lobbyType, gameInstanceID, gameType) {
		var formdata = {
			"nickname" : "Auto",
			"gameInstanceID" : gameInstanceID,
			"lobbyName" : lobbyType,
			"gameType" : gameType,
		};
		return formdata;
	};

};

MarriageRummy.Utilities.CommunicationUtilities.DataRequestPreparer = function() {
	var self = this;

	self.getProfileInformationRequest = function()
	{
		var formdata = {};
		return formdata;
	};
	
	self.updateProfileInformationRequest = function(firstname,lastname,country)
	{
		var formdata = {
				"firstname" : firstname,
				"lastname" : lastname,
				"country" : country
		};
		
		return formdata;
	};
};

MarriageRummy.Utilities.CommunicationUtilities.Callbacks = function() {
	var self = this;

	var gameBrowserCallback = new MarriageRummy.Utilities.CommunicationUtilities.GameBrowserCallback();
	var gameLauncherCallback = new MarriageRummy.Utilities.CommunicationUtilities.GameLauncherCallback();
	var gamePlayCallback = new MarriageRummy.Utilities.CommunicationUtilities.GamePlayCallback();
	var dataaccessCallback = new MarriageRummy.Utilities.CommunicationUtilities.DataAccessCallback();

	self.getGameBrowserCallback = function() {
		return gameBrowserCallback;
	};

	self.getGameLauncherCallback = function() {
		return gameLauncherCallback;
	};

	self.getGamePlayCallback = function() {
		return gamePlayCallback;
	};
	
	self.getDataAccessCallback = function()
	{
		return dataaccessCallback;
	};

};

MarriageRummy.Utilities.CommunicationUtilities.GamePlayCallback = function() {
	var self = this;

	self.onGetCardSuccess = function(data, textstatus, Jhxr, requestObj) {
		var gameObj = jQuery.data($("#GameArena")[0], "GameObj");
		gameObj.renderCards(data);
	};

	self.onGetCardFailure = function(data) {
		console.log("Failed to get Card : " + data);
	};

	self.onGetJokerSuccess = function(data, textstatus, Jhxr, requestObj) {
		var gameObj = jQuery.data($("#GameArena")[0], "GameObj");
		gameObj.renderJokerCard(data);
	};

	self.onGetJokerFailure = function(data) {
		console.log("Failed to get Joker Card : " + data);
	};

	self.onGetOpenCardSuccess = function(data, textstatus, Jhxr, requestObj) {
		var gameObj = jQuery.data($("#GameArena")[0], "GameObj");
		gameObj.renderOpenCard(data);
	};

	self.onGetOpenCardFailure = function(data) {
		console.log("Failed to get Open Card : " + data);
	};

	self.onGetNextCardFromDeckSuccess = function(data, textstatus, Jhxr,
			requestObj) {
		var gameObj = jQuery.data($("#GameArena")[0], "GameObj");
		gameObj.renderNextCardFromDeck(data);
	};

	self.onGetNextCardFromDeckFailure = function(data) {
		console.log("Failed to get Open Card : " + data);
	};

	self.onAddCardToHandSuccess = function(data, textstatus, Jhxr, requestObj) {
		console.log("Success on Card to Hand : " + data);
	};

	self.onAddCardToHandFailure = function(data, textstatus, Jhxr, requestObj) {
		console.log("Failed on Card to Hand : " + data);
	};

	self.onDropCardFromHandSuccess = function(data, textstatus, Jhxr,
			requestObj) {
		console.log("Success on Drop Card From Hand : " + data);
		var gameObj = jQuery.data($("#GameArena")[0], "GameObj");
		gameObj.onDropHandSuccess(data, requestObj);
	};

	self.onDropCardFromHandFailure = function(data, textstatus, Jhxr,
			requestObj) {
		//alert("Failed on Drop Card From Hand" + JSON.stringify(data));
		console.log("Failed on Drop Card From Hand : " + data);
	};

	self.onShowJokerSuccess = function(data, textstatus, Jhxr, requestObj) {
		console.log("Success on showJoker : " + JSON.stringify(data));
		var gameObj = jQuery.data($("#GameArena")[0], "GameObj");
		gameObj.renderJokerCard(data);
	};

	self.onShowJokerFailure = function(data) {
		console.log("Failure on showJoker : " + data);
	};

	self.onGetPlayerListSuccess = function(data, textstatus, Jhxr, requestObj) {
		console.log("Player List success : " + JSON.stringify(data));
		var gameObj = jQuery.data($("#GameArena")[0], "GameObj");
		gameObj.renderGameParticipants(data);
	};

	self.onGetPlayerListFailure = function(data) {
		console.log("Player List Failure : " + data);
	};

	self.onGetWhoseTurnSuccess = function(data, textstatus, Jhxr, requestObj) {
		console.log("Success on onGetWhoseTurn: " + JSON.stringify(data));
		var gameObj = jQuery.data($("#GameArena")[0], "GameObj");
		gameObj.renderTurns(data);
	};

	self.onGetWhoseTurnFailure = function(data) {
		console.log("Failure on onGetWhoseTurn : " + data);
	};

	self.onSkipPlayerTurnSuccess = function(data, textstatus, Jhxr, requestObj) {
		console.log("Success onSkipPlayerTurnSuccess : " + data);
		var gameObj = jQuery.data($("#GameArena")[0], "GameObj");
		gameObj.onFoldHandSuccess(data, requestObj);
	};

	self.onSkipPlayerTurnFailure = function(data) {
		console.log("Failure  onSkipPlayerTurnFailure : " + data);
	};

	self.onDeclareGameSuccess = function(data, textstatus, Jhxr, requestObj) {
		console.log("Declare Game Success : " + JSON.stringify(data));
		var gameObj = jQuery.data($("#GameArena")[0], "GameObj");
		gameObj.onDeclareSuccess(data, requestObj);
	};

	self.onDeclareGameFailure = function(data) {
		console.log("Declare Game Failure : " + data);
	};

	self.onSortCardSuccess = function(data, textstatus, Jhxr, requestObj) {
		console.log("On Card Sort Success : " + JSON.stringify(data));
		var card = {
			"cardlist" : data
		};
		var gameObj = jQuery.data($("#GameArena")[0], "GameObj");
		gameObj.renderCards(card);
	};

	self.onSortCardFailure = function(data) {
		console.log("Failed to get Card : " + data);
	};

	self.onShowCardSuccess = function(data, textstatus, Jhxr, requestObj) {
		console.log("Declare Game Success : " + JSON.stringify(data));
		var gameObj = jQuery.data($("#GameArena")[0], "GameObj");
		gameObj.onShowCardSuccess(data, requestObj);
	};

	self.onShowCardFailure = function(data) {
		console.log("Declare Game Failure : " + data);
	};

	self.onShowStatusforPlayerSuccess = function(data, textstatus, Jhxr,
			requestObj) {
		console.log("onShowStatusforPlayerSuccess : " + JSON.stringify(data));
		/*
		 * var card = {"cardlist":data}; var gameObj = jQuery.data(
		 * $("#GameArena")[0], "GameObj"); gameObj.renderCards(card);
		 */
	};

	self.onShowStatusforPlayerFailure = function(data) {
		console.log("onShowStatusforPlayerSuccess : " + data);
	};

	self.getPlayerPointsSuccess = function(data, textstatus, Jhxr, requestObj) {
		console.log("getPlayerPointsSuccess : " + JSON.stringify(data));
		var gameObj = jQuery.data($("#GameArena")[0], "GameObj");
		gameObj.renderPointsTable(data);
	};

	self.getPlayerPointsFailure = function(data) {
		console.log("Failed to getPlayerPointsFailure : " + data);
	};

};

MarriageRummy.Utilities.CommunicationUtilities.GameLauncherCallback = function() {
	var self = this;

	self.onAddChatMessageSuccess = function(data, textstatus, Jhxr, requestObj) {
		var gamelauncher = jQuery.data($("#GameLauncher")[0], "LauncherObj");
		gamelauncher.sendMessageSuccess();

	};

	self.onAddChatMessageFailure = function(data) {
		console.log("Posted chat message Failure");
	};

	self.onGetChatMessageSuccess = function(data, textstatus, Jhxr, requestObj) {
		console.log("Got chat message successfully", data);
		if (data === undefined || data == null)
			return;
		var gamelauncher = jQuery.data($("#GameLauncher")[0], "LauncherObj");
		gamelauncher.getMessageSuccess(data.messages, data.currentChatCount);
	};

	self.onGetChatMessageFailure = function(data) {
		console.log("get chat message Failure");
	};

	self.onGetPlayersinGameSuccess = function(data, textstatus, Jhxr,
			requestObj) {
		console.log("Players List : " + JSON.stringify(data));
		var gamelauncher = jQuery.data($("#GameLauncher")[0], "LauncherObj");
		gamelauncher.updatePlayerList(data);
	};

	self.onGetPlayersinGameFailure = function(data) {
		console.log("Failed to fetch players in game ");
	};

	self.onLaunchGameSuccess = function(data, textstatus, Jhxr, requestObj) {
		var notificationdata = marriageRummy.notificationRequest
				.launchGameNotification("onLaunchGameSuccess",
						requestObj.formdata);
		marriageRummy.notificationManager
				.sendNotificationEvent(notificationdata);
	};

	self.onLaunchGameFailure = function(data) {
		alert("Launch Game Failure" + JSON.stringify(data));
	};

};

MarriageRummy.Utilities.CommunicationUtilities.GameBrowserCallback = function() {
	var self = this;
	self.onCreateGameSucess = function(data, textstatus, Jhxr, requestObj) {
		marriageRummy.gameBrowserUtilities
				.refreshGameLobby(requestObj.gameLobby);
		$("#creategamemodal").modal('hide');
		var createGameResponse = null;
		if (data.hasOwnProperty("SevenCardRummy")) {
			createGameResponse = data.SevenCardRummy;
		}
		var gamelauncher = new MarriageRummy.Utilities.RummyUtilities.GameLauncherUtilities(
				createGameResponse,
				createGameResponse.playerlist[0].HumanPlayer.playerPosition,
				"CREATEMODE");
		$("#GameLauncher").css("display", "block");
		jQuery.data($("#GameLauncher")[0], "LauncherObj", gamelauncher);
		gamelauncher.onPlayerJoin();
		marriageRummy.chatSubscriber.connect(createGameResponse.gameInstanceId);
		marriageRummy.notificationManager = new MarriageRummy.Utilities.PushServerSubscriber.NotificationManager(
				createGameResponse.gameInstanceId);
		console.log(data);
	};

	self.onCreateGameFailure = function(data) {
		console.log("Failed to create game " + JSON.stringify(data));
		if (data.status == 403) {
			alert("You must login again access forbidden");
			location.reload(true);
		}

	};

	self.onKickPlayerSuccess = function(data, textstatus, jhxr, requestObj) {
		var notificationdata = marriageRummy.notificationRequest
				.kickPlayerNotification("onKickPlayerSuccess",
						requestObj.formdata);
		marriageRummy.notificationManager
				.sendNotificationEvent(notificationdata);
	};

	self.onKickPlayerFailure = function(data) {
		console.log("Failed to onKickPlayerFailure " + data);
		/*
		 * var notificationdata =
		 * marriageRummy.notificationRequest.createRemovePlayerNotification("onKickPlayerSuccess",
		 * requestObj.formdata);
		 * marriageRummy.notificationManager.sendNotificationEvent(notificationdata);
		 */
	};

	self.onUnJoinGameSuccess = function(data, textstatus, jhxr, requestObj) {

		$("#GameLauncher").css("display", "none");
		marriageRummy.gameBrowserUtilities
				.refreshGameLobby(requestObj.formdata.lobbyName);
		var notificationdata = marriageRummy.notificationRequest
				.createRemovePlayerNotification("onUnJoinGameSuccess",
						requestObj.formdata);
		marriageRummy.notificationManager
				.sendNotificationEvent(notificationdata);
		marriageRummy.notificationManager.shutdown();
		marriageRummy.chatSubscriber.disconnect();
		var gamelauncher = jQuery.data($("#GameLauncher")[0], "LauncherObj");
		gamelauncher.shutdownEvents();

	};

	self.onUnJoinGameFailure = function(data, textstatus, jhxr, requestObj) {
		console.log("Failed to unjoin game" + requestObj);
		$("#GameLauncher").css("display", "none");
		marriageRummy.gameBrowserUtilities
				.refreshGameLobby(requestObj.formdata.lobbyName);
	};

	self.onJoinGameSuccess = function(data, textstatus, jhxr, requestObj) {

		marriageRummy.gameBrowserUtilities
				.refreshGameLobby(requestObj.gameLobby);
		var joinGameResponse = {
			"gameInstanceId" : data.gameInstanceID,
			"gameName" : data.gameName,
			"owner" : data.owner,
			"gameType" : data.gameType,
			"currentplayers" : data.playersize,
			"playerlist" : {
				"length" : data.playersize
			},
			"maxplayers" : data.maxplayers,
			"gameMoneyBased" : data.gameMoneyBased,
			"gamePointsBased" : data.gamePointsBased,
			"moneyPerCard" : data.moneyPerCard,
			"maxPoints" : data.maxPoints,
			"lobbyName" : data.lobbyName,
			"nickName" : data.nickname

		};
		console.log("Join game Successful", joinGameResponse);
		var gamelauncher = new MarriageRummy.Utilities.RummyUtilities.GameLauncherUtilities(
				joinGameResponse, data.playerPosition, "JOINMODE");
		$("#GameLauncher").css("display", "block");
		jQuery.data($("#GameLauncher")[0], "LauncherObj", gamelauncher);
		gamelauncher.onPlayerJoin();
		marriageRummy.chatSubscriber.connect(joinGameResponse.gameInstanceId);
		marriageRummy.notificationManager = new MarriageRummy.Utilities.PushServerSubscriber.NotificationManager(
				joinGameResponse.gameInstanceId);
		var notificationdata = marriageRummy.notificationRequest
				.createAddPlayerNotification("onJoinGameSuccess",
						requestObj.formdata);
		setTimeout(marriageRummy.notificationManager.sendNotificationEvent,
				3000, notificationdata);
		console.log(data);

	};

	self.onJoinGameFailure = function(data) {
		console.log("Failed to join game : ", data);
	};

	self.onDeleteGameSuccess = function(data, textstatus, jhxr, requestObj) {
		console.log("Delete game Successful", data, textstatus);
		marriageRummy.gameBrowserUtilities
				.refreshGameLobby(requestObj.formdata.lobbyName);
		var notificationdata = marriageRummy.notificationRequest
				.createCancelGameNotification("onDeleteGameSuccess",
						requestObj.formdata);
		marriageRummy.notificationManager
				.sendNotificationEvent(notificationdata);
		setTimeout(marriageRummy.notificationManager.shutdown, 2000);
		marriageRummy.chatSubscriber.disconnect();
		var gamelauncher = jQuery.data($("#GameLauncher")[0], "LauncherObj");
		gamelauncher.shutdownEvents();
		// delete gamelauncher;
	};

	self.onDeleteGameFailure = function(data) {
		console.log("Failed to delete game : ", data);
	};

};


MarriageRummy.Utilities.CommunicationUtilities.DataAccessCallback = function()
{
	var self = this;
	
	self.onGetProfileInformationSuccess = function(data, textstatus, Jhxr, requestObj)
	{
		console.log("******GET PROFILE INFORMATION ********* " + JSON.stringify(data)+ " , DATA :- " + data);
		
		if(data === undefined || data == null)
			{
			   return;
			}
		else
			{
			   requestObj.srcObj.renderProfileInformation(data);
			}
	};
	
	self.onGetProfileInformationFailure = function(data)
	{
		console.log("******GET PROFILE INFORMATION FAILURE********* " + JSON.stringify(data));
	};
	
	self.onUpdateProfileInformationSuccess = function(data, textstatus, Jhxr, requestObj)
	{
		console.log("******UPDATE PROFILE INFORMATION ******** " + JSON.stringify(data));
	};
	
	self.onUpdateProfileInformationFailure = function(data)
	{
		console.log("******UPDATE PROFILE INFORMATION FAILURE ******** " + JSON.stringify(data));
	};
};

var marriageRummy = marriageRummy || {};
marriageRummy.httpComm = new MarriageRummy.Utilities.CommunicationUtilities.HttpCommunicator();
marriageRummy.urls = new MarriageRummy.Utilities.CommunicationUtilities.URLS();
marriageRummy.request = new MarriageRummy.Utilities.CommunicationUtilities.RequestPreparer();
marriageRummy.callbacks = new MarriageRummy.Utilities.CommunicationUtilities.Callbacks();

var loggedinnickname = "deepika";