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


MarriageRummy.Utilities.RummyUtilities.GeneralDataUtilities = function()
{
   var self = this;
   
   self.getGameDescriptionbyCode = function(GameCode)
   {
	  if(GameCode == "SEVENCARD_CLOSED")
		  {
		    return "7 Card Closed Joker Rummy";
		  }
	  if(GameCode == "SEVENCARD_OPEN")
	  {
	    return "7 Card Open Joker Rummy";
	  }
	  if(GameCode == "THIRTEENCARD_CLOSED")
	  {
	    return "13 Card Closed Joker Rummy";
	  }
	  if(GameCode == "THIRTEENCARD_OPEN")
	  {
	    return "13 Card Closed Joker Rummy";
	  }
	  if(GameCode == "TWENTYONECARD")
	  {
	    return "21 Card marriage Rummy";
	  }
	  
	  return "UNKNOWN GAME TYPE";
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
		var formdata = marriageRummy.request.getGameBrowserRequest().getAddChatMessage(gameInstanceID,
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
		var formdata = marriageRummy.request.getGameBrowserRequest().getChatMessage(gameInstanceID,
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
		
		$('#inviteFriends').unbind();
		$('#inviteFriends').on("click",function(){
			var url = marriageRummy.urls.getFriendList;
			var formdata = marriageRummy.request.getDataRequest().getFriendsList();
			var onSuccessCallbackfn = marriageRummy.callbacks.getDataAccessCallback().onGetFriendsListSuccess;
			var onFailureCallbackfn = marriageRummy.callbacks.getDataAccessCallback().onGetFriendsListFailure;
			var requestObj = {"formdata":formdata,
					          "srcObj":self,
					          "gameData":{
					        	  "gameInstanceId" :  stateobject.gameInstanceId,
					        	  "lobbyName" : stateobject.lobbyName,
					        	  "gameType" : stateobject.gameType
					           }
			                 };	
			marriageRummy.httpComm.invokeAsyncRequest(url,formdata, onSuccessCallbackfn,onFailureCallbackfn, requestObj);			
		});
		
		
		$('#LaunchGame').unbind();
		$('#LaunchGame').on("click",function(){
			var gameInstanceID = stateobject.gameInstanceId;
			var url = marriageRummy.urls.launchGame;
			//alert("URL : " + url);
			var formdata = marriageRummy.request.getGameBrowserRequest().getLauchGameRequest(stateobject.lobbyName,gameInstanceID,stateobject.gameType);
			var onSuccessCallbackfn = marriageRummy.callbacks.getGameLauncherCallback().onLaunchGameSuccess;
			var onFailureCallbackfn = marriageRummy.callbacks.getGameLauncherCallback().onLaunchGameFailure;
			var requestObj = {"formdata":formdata};	
			marriageRummy.httpComm.invokeAsyncRequest(url,
					formdata, onSuccessCallbackfn,
					onFailureCallbackfn, requestObj);

			return;
			
		});
		
		$("#CancelGame")
				.on(
						'click',
						function() {
							if (LauncherType == "JOINMODE") {
								var gameInstanceID = stateobject.gameInstanceId;
								var url = marriageRummy.urls.unjoinGame;
								var formdata = marriageRummy.request.getGameBrowserRequest().getUnjoinGameRequest(stateobject.lobbyName,gameInstanceID,stateobject.gameType);
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
	
	self.renderFriendsList = function(data,gameData)
	{
		console.log("RENDER renderFriendsList : " + JSON.stringify(data));
		$('#FriendsListModal').data('datacontent', gameData);
		$("#FriendsListModal").modal('show');
		if(data.friendlist === undefined || data.friendlist.length == 0)
			{
			   $('#friendList').empty();
			   $('#friendList').html('<h3 style="text-align:center;"> No Friends to Display </h3>');
			}
		else
			{
			    var friendTemplate = '<div class="friend">	<div class="selectcheckbox"> <input type="checkbox" /></div>'+
			                         '<div class="friendImage"><span><i class="fa fa-user fa-5x"></i></div><div class="friendContent">'+
				                     '<h4>NICKNAME</h4><h5>EMAILADDRESS<span style="color: rgb(74, 148, 115); font-size: 11px;">'+ 
			                         '<i class="fa fa-clock-o"></i> 1 day ago</span></h5></div>'+
			                         '<div class="friendRating"><h6>Rating</h6><h4>RATING</h4></div></div>';
			    var htmlcontent = "";
			    for(var i=0;i<data.friendlist.length;i++)
			    	{
			    	   var frienduser = friendTemplate.replace("NICKNAME", data.friendlist[i].nickname).replace("EMAILADDRESS",data.friendlist[i].emailAddress)
			    	   .replace("RATING",data.friendlist[i].rating);
			    	   htmlcontent = htmlcontent + frienduser;
			    	}
			    $('#friendList').empty();
			    $('#friendList').html(htmlcontent);
			}
		
		$('#InviteNow').unbind();
		$('#InviteNow').on("click",function(){
			var gameData = $('#FriendsListModal').data('datacontent');
			var nicknameArray = new Array();
			console.log("On Invite Now Game data " + JSON.stringify(gameData));
			 $('.friend input[type="checkbox"]').filter(':checked').each(
			            function(){
			                    var friendnode = $(this).parent().parent();
			                    var nickname =  friendnode.children().filter('div.friendContent').children().filter('h4').html().trim();
			                    nicknameArray.push(nickname);
			 });
			 sendGameInvite(gameData,nicknameArray);
			 $("#FriendsListModal").modal('hide');
		});
		
	};
	
	
	var sendGameInvite = function(GameData,nicknamearr)
	{
		var url = marriageRummy.urls.sendGameInvite;
		var formdata = marriageRummy.request.getDataRequest().sendGameInvite(GameData.gameInstanceId,GameData.gameType,GameData.lobbyName,nicknamearr);
		var onSuccessCallbackfn = marriageRummy.callbacks.getDataAccessCallback().onSendGameInviteSuccess;
		var onFailureCallbackfn = marriageRummy.callbacks.getDataAccessCallback().onSendGameInviteFailure;
		var requestObj = {"formdata":formdata};	
		marriageRummy.httpComm.invokeAsyncRequest(url,formdata, onSuccessCallbackfn,onFailureCallbackfn, requestObj);
	};
	
	var kickPlayer = function(nickname)
	{
		var gameInstanceID = stateobject.gameInstanceId;
		var url = marriageRummy.urls.unjoinGame;
		var formdata = marriageRummy.request.getGameBrowserRequest().getKickPlayerRequest(stateobject.lobbyName,gameInstanceID,stateobject.gameType,nickname);
		var onSuccessCallbackfn = marriageRummy.callbacks.getGameBrowserCallback().onKickPlayerSuccess;
		var onFailureCallbackfn = marriageRummy.callbacks.getGameBrowserCallback().onKickPlayerFailure;
		var requestObj = {"formdata":formdata};	
		marriageRummy.httpComm.invokeAsyncRequest(url,
				formdata, onSuccessCallbackfn,
				onFailureCallbackfn, requestObj);

	};

	var removeGame = function() {
		var url = marriageRummy.urls.deleteGame;
		var formdata = marriageRummy.request.getGameBrowserRequest().getDeleteGameRequest(
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
		var formdata = marriageRummy.request.getGameBrowserRequest().getPlayersinGameRequest(
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

	var createFriendRequest = function(destinationNickName)
	{
		var url = marriageRummy.urls.addFriendRequest;
		var formdata = marriageRummy.request.getDataRequest().addFriend(destinationNickName);
		var requestObj = {
			"formdata" : formdata
		};
		var successcall = marriageRummy.callbacks.getDataAccessCallback().onAddFriendSuccess;
		var failurecall = marriageRummy.callbacks.getDataAccessCallback().onAddFriendFailure;
		marriageRummy.httpComm.invokeAsyncRequest(url, formdata, successcall,failurecall, requestObj);
		console.log(url, formdata);
	};
	
	self.updatePlayerList = function(data) {
	
		
        var loggedinUser = marriageRummy.loggedinUser; 
		$("#GameLauncherContainer .well dd#noplayers").html(
				data.playerlist.length + "/" + stateobject.maxplayers);
		$("#gamemembers #playersarea").empty();
		var membertemplate = '<div class="members">'
				+ '<img src="./assets/images/Cards/ClubCards/A.png">'
				+ '<button class="close kickPlayer"><i id="add" class="fa fa-times fa-2x"></i></button>'
				+ '<button class="close addFriend"><i id="add" class="fa fa-user-plus fa-2x"></i></button>'
				+ 'MEMBERNAME</div>';
		
		var hosttemplate = '<div class="members">'
			+ '<img src="./assets/images/Cards/ClubCards/A.png">'
			+ 'MEMBERNAME</div>';
		if(LauncherType == "JOINMODE")
			{
			membertemplate = '<div class="members">'
				+ '<img src="./assets/images/Cards/ClubCards/A.png">'
				+'<button class="close addFriend"><i id="add" class="fa fa-user-plus fa-2x"></i></button>'
				+ 'MEMBERNAME</div>';
			}

		for (var i = 0; i < data.playerlist.length; i++) {
			var nickname = data.playerlist[i].HumanPlayer.nickName;
			
				
			var addMemeberContent = membertemplate.replace("MEMBERNAME",
					nickname);

			if(i == 0 && LauncherType != "JOINMODE")
				addMemeberContent = hosttemplate.replace("MEMBERNAME",nickname);
			if(nickname == loggedinUser)
			{
				addMemeberContent = hosttemplate.replace("MEMBERNAME",nickname);
			}
				
			$("#gamemembers #playersarea").append(addMemeberContent);
			
			$('.addFriend').unbind();
			$('.addFriend').on("click",function(){
				var destNickname = $(this).parent().text().trim();
				createFriendRequest(destNickname);
				$(this).attr("disabled","disabled");
				$(this).css("color","rgb(172, 177, 180)");
				marriageRummy.generalutility.showInfo("Friend Request Sent",
				"Friend Request sent successfully to " + destNickname);
			});
		}

		
		var curtopint = 328;
		var desiredtop = curtopint - (data.playerlist.length -1 )*38;
		$('#GameLauncherContainer .actionLauncher').css("top",desiredtop + "px");
		$(".kickPlayer").unbind();
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
marriageRummy.generaldatautility = new MarriageRummy.Utilities.RummyUtilities.GeneralDataUtilities();
// marriageRummy.gameLauncherUtilities = new
// MarriageRummy.Utilities.RummyUtilities.GameLauncherUtilities();
