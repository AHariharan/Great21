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
	   console.log("Posted chat message successfully");
   };
   
   self.onAddChatMessageFailure = function(data)
   {
	   console.log("Posted chat message Failure");
   };
};

MarriageRummy.Utilities.CommunicationUtilities.GameBrowserCallback = function()
{
	var self = this;
	self.onCreateGameSucess = function(data, textstatus, Jhxr, requestObj) {
		marriageRummy.gameBrowserUtilities.refreshGameLobby(requestObj.gameLobby);
		$("#creategamemodal").modal('hide');
		$("#GameLauncher").css("display", "block");
		console.log(data);
	};

	self.onCreateGameFailure = function(data) {
		console.log("Failed to create game " + data);
	};
	
	self.onJoinGameSuccess = function(data,textstatus,jhxr,requestObj)
	{
		console.log("Join game Successful", data, textstatus);		
		marriageRummy.gameLauncherUtilities.startPollingforChatMessages(requestObj.formdata.gameInstanceID);
		
	};
	
	self.onJoinGameFailure = function(data)
	{
		console.log("Failed to join game : ", data);
	};

	
};

var marriageRummy = marriageRummy || {};
marriageRummy.httpComm = new MarriageRummy.Utilities.CommunicationUtilities.HttpCommunicator();
marriageRummy.urls = new MarriageRummy.Utilities.CommunicationUtilities.URLS();
marriageRummy.request = new MarriageRummy.Utilities.CommunicationUtilities.RequestPreparer();
marriageRummy.callbacks = new MarriageRummy.Utilities.CommunicationUtilities.Callbacks();