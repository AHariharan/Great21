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
	self.createGame = "/marriagerummy/IndexerServices/GameBrowser/createGame";
	self.joinGame = "/marriagerummy/IndexerServices/GameBrowser/Player/Add";
	self.unjoinGame = "/marriagerummy/IndexerServices/GameBrowser/Player/Remove";
	self.deleteGame = "/marriagerummy/IndexerServices/GameBrowser/removeGame";
	self.getPlayersinGame ="/marriagerummy/IndexerServices/GameLauncher/Game/GetPlayers";
	self.addChatMessage ="/marriagerummy/IndexerServices/GameLauncher/ChatMessages/Add";
	self.getChatMessage = "/marriagerummy/IndexerServices/GameLauncher/ChatMessages/Get";
};

MarriageRummy.Utilities.CommunicationUtilities.RequestPreparer = function() {
	var self = this;

	self.getCreateGameRequest = function(gameLobby, gameType) {
		var gameDesc = $('#creategamemodal #GameDesc').val();
		var maxplayers = $('#creategamemodal #MaxPlayers').val();
		var isFriendsOnly = $('#creategamemodal #isFriendsOnly').prop("checked");
		var isInviteOnly = $('#creategamemodal #isByInviteonly').prop("checked");
		var gamePointsBased = $('#creategamemodal #inlineRadio1').prop("checked");
		var gamePerCardBase = $('#creategamemodal #inlineRadio2').prop("checked");
		var Points = $('#creategamemodal #Points').val();
		var CardValue = $('#creategamemodal #CardVal').val();
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
			"perCardAmount" : CardValue,
			"maxRounds" : -1
		};

		return formdata;

	};

	self.getJoinGameRequest = function(lobbyType, gameInstanceID, gameType)
	{
		var formdata = {
				"playerType" : "HUMAN",
				"nickname" : "Auto",
				"gameInstanceID" : gameInstanceID,
				"lobbyName" : lobbyType,
				"gameType" : gameType
			};
		return formdata;
	};
 
	self.getPlayersinGameRequest = function(gameInstanceID,lobbyType, gameType)
	{
		var formdata = {
				"gameInstanceID":gameInstanceID ,
				"lobbyName":lobbyType ,
				"gameType":gameType
		};
		return formdata;
	};
	
	self.getAddChatMessage = function(gameInstanceID,messageContent,PlayerName,PlayerPos)
	{
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
	
	self.getChatMessage = function(gameInstanceID,currentChatCount)
	{
		var formdata = {
				         "gameInstanceID" : gameInstanceID,
				         "currentChatCount" :  currentChatCount
		               };
		return formdata;
	};
	
	self.getDeleteGameRequest = function(gameInstanceID,lobbyName,gameType)
	{
		var formdata = {
				"gameInstanceID":gameInstanceID,
				"nickName":"Auto",
				"lobbyName":lobbyName,
				"gameType":gameType
		};
		return formdata;
	};
	
	self.getUnjoinGameRequest = function(lobbyType, gameInstanceID, gameType)
	{
		var formdata = {
				"nickname" : "Auto",
				"gameInstanceID" : gameInstanceID,
				"lobbyName" : lobbyType,
				"gameType" : gameType,
				"requestedby":"Auto"
			};
		return formdata;
	};
	
	self.getKickPlayerRequest = function(lobbyType, gameInstanceID, gameType,nickname)
	{
		var formdata = {
				"nickname" : nickname,
				"gameInstanceID" : gameInstanceID,
				"lobbyName" : lobbyType,
				"gameType" : gameType,
				"requestedby":"Auto"
			};
		return formdata;
	};

};

MarriageRummy.Utilities.CommunicationUtilities.Callbacks = function() {
	var self = this;
	
	var gameBrowserCallback = new MarriageRummy.Utilities.CommunicationUtilities.GameBrowserCallback();
	var gameLauncherCallback = new MarriageRummy.Utilities.CommunicationUtilities.GameLauncherCallback();
	
	self.getGameBrowserCallback = function()
	{
	    return gameBrowserCallback;
	};

	self.getGameLauncherCallback = function()
	{
		return gameLauncherCallback;
	};
	
};


MarriageRummy.Utilities.CommunicationUtilities.GameLauncherCallback = function()
{
   var self = this;
   
   self.onAddChatMessageSuccess = function(data, textstatus, Jhxr, requestObj)
   {
	   var gamelauncher =  jQuery.data( $("#GameLauncher")[0], "LauncherObj");
	   gamelauncher.sendMessageSuccess();
	   
   };
   
   self.onAddChatMessageFailure = function(data)
   {
	   console.log("Posted chat message Failure");
   };
   
   self.onGetChatMessageSuccess = function(data, textstatus, Jhxr, requestObj)
   {
	   console.log("Got chat message successfully" , data);
	   if(data === undefined || data == null)
		   return;	   
	   var gamelauncher =  jQuery.data( $("#GameLauncher")[0], "LauncherObj");
	   gamelauncher.getMessageSuccess(data.messages,data.currentChatCount);
   };
   
   self.onGetChatMessageFailure = function(data)
   {
	   console.log("get chat message Failure");
   };
   
   self.onGetPlayersinGameSuccess = function(data, textstatus, Jhxr, requestObj)
   {
	 console.log("Players List : " + JSON.stringify(data)); 
	 var gamelauncher =  jQuery.data( $("#GameLauncher")[0], "LauncherObj");
	 gamelauncher.updatePlayerList(data);
   };
   
   self.onGetPlayersinGameFailure = function(data)
   {
	 console.log("Failed to fetch players in game ");  
   };
   
};

MarriageRummy.Utilities.CommunicationUtilities.GameBrowserCallback = function()
{
	var self = this;
	self.onCreateGameSucess = function(data, textstatus, Jhxr, requestObj) {
		marriageRummy.gameBrowserUtilities.refreshGameLobby(requestObj.gameLobby);
		$("#creategamemodal").modal('hide');
		var createGameResponse = null;
	    if(data.hasOwnProperty("SevenCardRummy"))
	    	{
	       	  createGameResponse = data.SevenCardRummy;
	    	}
	    var gamelauncher = new MarriageRummy.Utilities.RummyUtilities.GameLauncherUtilities(createGameResponse,createGameResponse.playerlist[0].HumanPlayer.playerPosition,"CREATEMODE");
	    $("#GameLauncher").css("display", "block");	   
	    jQuery.data( $("#GameLauncher")[0], "LauncherObj", gamelauncher);
	    gamelauncher.onPlayerJoin();
	    marriageRummy.chatSubscriber.connect(createGameResponse.gameInstanceId);
	    marriageRummy.notificationManager =  new MarriageRummy.Utilities.PushServerSubscriber.NotificationManager(createGameResponse.gameInstanceId);
		console.log(data);
	};

	self.onCreateGameFailure = function(data) {
		console.log("Failed to create game " + JSON.stringify(data));
		if(data.status == 403)
			{
			   alert("You must login again access forbidden");
			   location.reload(true);
			}
		
	};
	
	self.onKickPlayerSuccess = function(data,textstatus,jhxr,requestObj)
	{
		var notificationdata = marriageRummy.notificationRequest.kickPlayerNotification("onKickPlayerSuccess", requestObj.formdata);
	    marriageRummy.notificationManager.sendNotificationEvent(notificationdata);
	};
	
	self.onKickPlayerFailure = function(data)
	{
		console.log("Failed to onKickPlayerFailure " + data);	
		/*var notificationdata = marriageRummy.notificationRequest.createRemovePlayerNotification("onKickPlayerSuccess", requestObj.formdata);
	    marriageRummy.notificationManager.sendNotificationEvent(notificationdata);*/
	};
	
	self.onUnJoinGameSuccess = function(data,textstatus,jhxr,requestObj)
	{
		
		$("#GameLauncher").css("display", "none");	  
		marriageRummy.gameBrowserUtilities.refreshGameLobby(requestObj.formdata.lobbyName);
		var notificationdata = marriageRummy.notificationRequest.createRemovePlayerNotification("onUnJoinGameSuccess", requestObj.formdata);
	    marriageRummy.notificationManager.sendNotificationEvent(notificationdata);
	    marriageRummy.notificationManager.shutdown();
	    marriageRummy.chatSubscriber.disconnect();
	    var gamelauncher =  jQuery.data( $("#GameLauncher")[0], "LauncherObj");
	    gamelauncher.shutdownEvents();
	    
	};
	
	self.onUnJoinGameFailure = function(data,textstatus,jhxr,requestObj)
	{
		console.log("Failed to unjoin game" + requestObj);		
		$("#GameLauncher").css("display", "none");	  
		marriageRummy.gameBrowserUtilities.refreshGameLobby(requestObj.formdata.lobbyName);		
	};
	
	self.onJoinGameSuccess = function(data,textstatus,jhxr,requestObj)
	{
		
		marriageRummy.gameBrowserUtilities.refreshGameLobby(requestObj.gameLobby);
		var joinGameResponse = {
				"gameInstanceId":data.gameInstanceID,
				"gameName":data.gameName,
				"owner":data.owner,
				"gameType":data.gameType,
				"currentplayers":data.playersize,
				"playerlist": {
					"length" : data.playersize
				 },
				"maxplayers":data.maxplayers,
				"gameMoneyBased":data.gameMoneyBased,
				"gamePointsBased":data.gamePointsBased,
				"moneyPerCard":data.moneyPerCard,
				"maxPoints":data.maxPoints,
				"lobbyName":data.lobbyName,
				"nickName":data.nickname
				
		};
		console.log("Join game Successful", joinGameResponse);	
	    var gamelauncher = new MarriageRummy.Utilities.RummyUtilities.GameLauncherUtilities(joinGameResponse,data.playerPosition,"JOINMODE");
	    $("#GameLauncher").css("display", "block");	   
	    jQuery.data( $("#GameLauncher")[0], "LauncherObj", gamelauncher);
	    gamelauncher.onPlayerJoin();
	    marriageRummy.chatSubscriber.connect(joinGameResponse.gameInstanceId);
	    marriageRummy.notificationManager = new MarriageRummy.Utilities.PushServerSubscriber.NotificationManager(joinGameResponse.gameInstanceId);
	    var notificationdata = marriageRummy.notificationRequest.createAddPlayerNotification("onJoinGameSuccess", requestObj.formdata);
	    setTimeout(marriageRummy.notificationManager.sendNotificationEvent, 3000,notificationdata);
		console.log(data);
		
	};
	
	self.onJoinGameFailure = function(data)
	{
		console.log("Failed to join game : ", data);
	};
	
	self.onDeleteGameSuccess = function(data,textstatus,jhxr,requestObj)
	{
		console.log("Delete game Successful", data, textstatus);		
		marriageRummy.gameBrowserUtilities.refreshGameLobby(requestObj.formdata.lobbyName);
		var notificationdata = marriageRummy.notificationRequest.createCancelGameNotification("onDeleteGameSuccess", requestObj.formdata);
		marriageRummy.notificationManager.sendNotificationEvent(notificationdata);
		setTimeout(marriageRummy.notificationManager.shutdown, 2000);
		marriageRummy.chatSubscriber.disconnect();
		var gamelauncher =  jQuery.data( $("#GameLauncher")[0], "LauncherObj");
		gamelauncher.shutdownEvents();
		//delete gamelauncher;
	};
	
	self.onDeleteGameFailure = function(data)
	{
		console.log("Failed to delete game : ", data);
	};

	
};

var marriageRummy = marriageRummy || {};
marriageRummy.httpComm = new MarriageRummy.Utilities.CommunicationUtilities.HttpCommunicator();
marriageRummy.urls = new MarriageRummy.Utilities.CommunicationUtilities.URLS();
marriageRummy.request = new MarriageRummy.Utilities.CommunicationUtilities.RequestPreparer();
marriageRummy.callbacks = new MarriageRummy.Utilities.CommunicationUtilities.Callbacks();

var loggedinnickname = "deepika";