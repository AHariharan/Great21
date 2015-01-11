/**
 * 
 */

// Ulities Name space
MarriageRummy.Utilities = MarriageRummy.Utilities || {};

// UIUtilites Namespace
MarriageRummy.Utilities.RummyUtilities = MarriageRummy.Utilities.RummyUtilities
		|| {};

MarriageRummy.Utilities.RummyUtilities.GameBrowserUtilities = function() {

	var self = this;

	self.refreshGameLobby = function(gameLobby) {
		$("#gamebrowser"+gameLobby+"Lobby #" + gameLobby + "lobbytable")
				.bootstrapTable('refresh', {
					silent : true
				});
	};

};

MarriageRummy.Utilities.RummyUtilities.GameLauncherUtilities = function(
		createGameResponse, playerpos, LauncherType) {
	
	var unblindallEvents = function()
	{
		$(".sendText textarea").unbind();
		$("#CancelGame").unbind();
		
	};
	
	unblindallEvents();
	var self = this;
	var stateobject = createGameResponse;
	stateobject.playerpos = playerpos;
	if (stateobject.playerlist.length != undefined)
		stateobject.currentplayers = stateobject.playerlist.length;

	var chatPollingJob = {};
	var chatPollinginterval = 7000; // In Milli seconds
	var currentChatCount = 0;

	var currentChatWindowScrollHeight = $('.chatWindow')[0].scrollHeight;

	var playerCheckJob = {};
	var playerCheckInterval = 15000; // In Milli seconds

	var sendMessage = function(message) {
		var gameInstanceID = stateobject.gameInstanceId;
		var formdata = marriageRummy.request.getAddChatMessage(gameInstanceID,
				message, "Auto", stateobject.playerpos);
		marriageRummy.chatSubscriber.sendMessage(gameInstanceID, formdata);
		/*
		 * var url = marriageRummy.urls.addChatMessage; var onSuccessCallbackfn =
		 * marriageRummy.callbacks.getGameLauncherCallback().onAddChatMessageSuccess;
		 * var onFailureCallbackfn =
		 * marriageRummy.callbacks.getGameLauncherCallback().onAddChatMessageFailure;
		 * marriageRummy.httpComm.invokeAsyncRequest(url, formdata,
		 * onSuccessCallbackfn, onFailureCallbackfn, null);
		 */
	};

	var getMessage = function() {
		var gameInstanceID = stateobject.gameInstanceId;
		var url = marriageRummy.urls.getChatMessage;
		var formdata = marriageRummy.request.getChatMessage(gameInstanceID,
				currentChatCount);
		var onSuccessCallbackfn = marriageRummy.callbacks
				.getGameLauncherCallback().onGetChatMessageSuccess;
		var onFailureCallbackfn = marriageRummy.callbacks
				.getGameLauncherCallback().onGetChatMessageFailure;
		marriageRummy.httpComm.invokeAsyncRequest(url, formdata,
				onSuccessCallbackfn, onFailureCallbackfn, null);
	};

	
	var cleanup = function()
	{
		$('#playersarea').empty();
		$('.chatWindow').empty();
		$('#GameLauncherContainer .actionLauncher').css("top","328px");
	};
	
	var init = function() {
		cleanup();
		$("#GameLauncherContainer .well dd#gamedesc")
				.html(stateobject.gameName);
		$("#GameLauncherContainer .well dd#host").html(stateobject.owner);
		var type = marriageRummy.dataConvertor
				.convertGameTypetoDisplayText(stateobject.gameType);
		$("#GameLauncherContainer .well dd#gameType").html(type);
		$("#GameLauncherContainer .well dd#noplayers").html(
				stateobject.currentplayers + "/" + stateobject.maxplayers);
		if (stateobject.gameMoneyBased == true)
			$("#GameLauncherContainer .well dd#gameMode").html("Per Card  ");
		if (stateobject.gamePointsBased == true)
			$("#GameLauncherContainer .well dd#gameMode").html("Points");
		if (stateobject.gameMoneyBased == true)
			$("#GameLauncherContainer .well dd#perCard").html('<i class="fa  fa-inr"></i>&nbsp;&nbsp;'+
					stateobject.moneyPerCard);
		if (stateobject.gamePointsBased == true)
			$("#GameLauncherContainer .well dd#perCard").html("Points " +
					stateobject.maxPoints);
		if(stateobject.gameMoneyBased == true)
			$("#GameLauncherContainer .well dd#buyindisplay").html("N/A");
		else
    		$("#GameLauncherContainer .well dd#buyindisplay").html('<i class="fa  fa-inr"></i>&nbsp;&nbsp;'+stateobject.buyinValue);
		

		$(".sendText textarea").keyup(function(e) {
			if (e.keyCode == 13) {
				var message = $(this).val();
				sendMessage(message);
				self.sendMessageSuccess();
			}
		});

		if (LauncherType == "JOINMODE") {
			$("#CancelGame").text("Leave Game");
			$("#LaunchGame").css("display", "none");

		}
		else
			{
			$("#LaunchGame").css("display", "inline-block");
			$("#CancelGame").text("Cancel Game");
			}
		
		
		$("#CancelGame")
				.on(
						'click',
						function() {
							if (LauncherType == "JOINMODE") {
								var gameInstanceID = stateobject.gameInstanceId;
								var url = marriageRummy.urls.unjoinGame;
								var formdata = marriageRummy.request.getUnjoinGameRequest(stateobject.lobbyName,gameInstanceID,stateobject.gameType);
								var onSuccessCallbackfn = marriageRummy.callbacks.getGameBrowserCallback().onUnJoinGameSuccess;
								var onFailureCallbackfn = marriageRummy.callbacks.getGameBrowserCallback().onUnJoinGameFailure;
								var requestObj = {"formdata":formdata};	
								marriageRummy.httpComm.invokeAsyncRequest(url,
										formdata, onSuccessCallbackfn,
										onFailureCallbackfn, requestObj);

								return;
							}
							$("#GameLauncher").css("display", "none");
							marriageRummy.gameBrowserUtilities
									.refreshGameLobby(stateobject.lobbyName);
							removeGame();
							marriageRummy.chatSubscriber.disconnect();

						});

	};

	init();
	
	self.shutdownEvents = function()
	{
		$(".sendText textarea").unbind();
		$("#CancelGame").unbind();
		
	};
	
	var kickPlayer = function(nickname)
	{
		var gameInstanceID = stateobject.gameInstanceId;
		var url = marriageRummy.urls.unjoinGame;
		var formdata = marriageRummy.request.getKickPlayerRequest(stateobject.lobbyName,gameInstanceID,stateobject.gameType,nickname);
		var onSuccessCallbackfn = marriageRummy.callbacks.getGameBrowserCallback().onKickPlayerSuccess;
		var onFailureCallbackfn = marriageRummy.callbacks.getGameBrowserCallback().onKickPlayerFailure;
		var requestObj = {"formdata":formdata};	
		marriageRummy.httpComm.invokeAsyncRequest(url,
				formdata, onSuccessCallbackfn,
				onFailureCallbackfn, requestObj);

	};

	var removeGame = function() {
		var url = marriageRummy.urls.deleteGame;
		var formdata = marriageRummy.request.getDeleteGameRequest(
				stateobject.gameInstanceId, stateobject.lobbyName,
				stateobject.gameType);
		var successfn = marriageRummy.callbacks.getGameBrowserCallback().onDeleteGameSuccess;
		var failurefn = marriageRummy.callbacks.getGameBrowserCallback().onDeleteGameFailure;
		var reqobj = {
			"formdata" : formdata
		};
		marriageRummy.httpComm.invokeAsyncRequest(url, formdata, successfn,
				failurefn, reqobj);
	};


	var playerCheckCallback = function() {

		var url = marriageRummy.urls.getPlayersinGame;
		var formdata = marriageRummy.request.getPlayersinGameRequest(
				stateobject.gameInstanceId, stateobject.lobbyName,
				stateobject.gameType);
		var successfn = marriageRummy.callbacks.getGameLauncherCallback().onGetPlayersinGameSuccess;
		var failurefn = marriageRummy.callbacks.getGameLauncherCallback().onGetPlayersinGameFailure;
		var reqobj = {
			"formdata" : formdata
		};
		marriageRummy.httpComm.invokeAsyncRequest(url, formdata, successfn,
				failurefn, reqobj);
		console.log("Checking for Players list in the game : ", url, formdata);
	};

	self.sendMessageSuccess = function() {
		$('.sendText textarea').val("");
		// getMessage();
	};

	var handleScroll = function() {
		var clienHeight = $('.chatWindow')[0].clientHeight;
		var chatWindowScrollHeight = $('.chatWindow')[0].scrollHeight;
		var chatWindowScrollTop = $('.chatWindow')[0].scrollTop;
		if (chatWindowScrollHeight > clienHeight) {
			newscrolltop = chatWindowScrollTop
					+ (chatWindowScrollHeight - currentChatWindowScrollHeight);
			$('.chatWindow').scrollTop(newscrolltop);
		} else {
			currentChatWindowScrollHeight = chatWindowScrollHeight;
			currentChatWindowScrollTop = chatWindowScrollTop;
		}
	};

	self.onReceiveMessage = function(formdata) {
		var object = jQuery.parseJSON(formdata);
		var htmlContent = '<div class="chatContent PlayerPOSITIONChat"><span><strong>PLAYERNAME</strong>MESSAGE</span></div>';
		var position = object.message.playerpos;
		var playername = object.message.playerName;
		var message = object.message.message;

		var finalcontent = htmlContent.replace("POSITION", position).replace(
				"PLAYERNAME", playername + " : ").replace("MESSAGE", message);
		$('.chatWindow').append(finalcontent);
		handleScroll();
	};

	self.getMessageSuccess = function(arrayofmessages, currentCount) {
		if (currentChatCount == currentCount)
			return;
		else
			currentChatCount = currentCount;
		var htmlContent = '<div class="chatContent PlayerPOSITIONChat"><span><strong>PLAYERNAME</strong>MESSAGE</span></div>';
		for (var i = 0; i < arrayofmessages.length; i++) {
			var position = arrayofmessages[i].playerpos;
			var playername = arrayofmessages[i].playerName;
			var message = arrayofmessages[i].message;
			var finalcontent = htmlContent.replace("POSITION", position)
					.replace("PLAYERNAME", playername + " : ").replace(
							"MESSAGE", message);
			$('.chatWindow').append(finalcontent);
		}

	};

	self.onPlayerJoin = function() {
		playerCheckCallback();
	};

	self.updatePlayerList = function(data) {
	

		$("#GameLauncherContainer .well dd#noplayers").html(
				data.playerlist.length + "/" + stateobject.maxplayers);
		$("#gamemembers #playersarea").empty();
		var membertemplate = '<div class="members">'
				+ '<img src="./assets/images/Cards/ClubCards/A.png">'
				+ '<button class="close kickPlayer"><i id="add" class="fa fa-times"></i></button>'
				+ '<button class="close addFriend"><i id="add" class="fa fa-plus"></i></button>'
				+ 'MEMBERNAME</div>';
		var hosttemplate = '<div class="members">'
			+ '<img src="./assets/images/Cards/ClubCards/A.png">'
			+ 'MEMBERNAME</div>';
		if(LauncherType == "JOINMODE")
			{
			membertemplate = '<div class="members">'
				+ '<img src="./assets/images/Cards/ClubCards/A.png">'
				+'<button class="close addFriend"><i id="add" class="fa fa-plus"></i></button>'
				+ 'MEMBERNAME</div>';
			
			}

		for (var i = 0; i < data.playerlist.length; i++) {
			var nickname = data.playerlist[i].HumanPlayer.nickName;
			
				
			var addMemeberContent = membertemplate.replace("MEMBERNAME",
					nickname);

			if(i == 0)
				addMemeberContent = hosttemplate.replace("MEMBERNAME",nickname);
				
			$("#gamemembers #playersarea").append(addMemeberContent);
		}

		
		var curtopint = 328;
		var desiredtop = curtopint - (data.playerlist.length -1 )*38;
		$('#GameLauncherContainer .actionLauncher').css("top",desiredtop + "px");
		$(".kickPlayer").on("click",function(){
			nickname = $(this).parent().text();
			kickPlayer(nickname);
		});
	};

/*	self.startPollingforChatMessages = function() {
		chatPollingJob = setInterval(pollingCallback, chatPollinginterval,
				stateobject.gameInstanceID);
	};

	self.stopPollingforChatMessages = function() {
		clearInterval(chatPollingJob);
	};

	self.startPlayerCheckJob = function() {
		playerCheckCallback(stateobject.gameInstanceId, stateobject.lobbyName,
				stateobject.gameType);
		playerCheckJob = setInterval(playerCheckCallback, playerCheckInterval,
				stateobject.gameInstanceId, stateobject.lobbyName,
				stateobject.gameType);
	};

	self.stopPlayerCheckJob = function() {
		clearInterval(playerCheckJob);
	};*/

};

var marriageRummy = marriageRummy || {};
marriageRummy.gameBrowserUtilities = new MarriageRummy.Utilities.RummyUtilities.GameBrowserUtilities();
// marriageRummy.gameLauncherUtilities = new
// MarriageRummy.Utilities.RummyUtilities.GameLauncherUtilities();
