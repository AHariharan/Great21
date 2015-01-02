/**
 * 
 */

// Ulities Name space
MarriageRummy.Utilities = MarriageRummy.Utilities || {};

// UIUtilites Namespace
MarriageRummy.Utilities.RummyUtilities = MarriageRummy.Utilities.RummyUtilities	|| {};

MarriageRummy.Utilities.RummyUtilities.GameBrowserUtilities = function() {
	
	var self =  this;
	
	self.refreshGameLobby = function(gameLobby)
	{
		$("#gamebrowserBeginnerLobby #" + gameLobby + "lobbytable")
		.bootstrapTable('refresh', {silent : true});
	};
	
};


MarriageRummy.Utilities.RummyUtilities.GameLauncherUtilities = function(createGameResponse,playerpos)
{
    var self = this;
    var stateobject = createGameResponse;
    stateobject.playerpos = 1;
    var chatPollingJob = {};
    var chatPollinginterval = 5000; // In Milli seconds
    var currentChatCount = 0;
    
    var currentChatWindowScrollHeight = $('.chatWindow')[0].scrollHeight;
    var currentChatWindowScrollTop = $('.chatWindow')[0].scrollTop;
    
    var playerCheckJob = {};
    var playerCheckInterval = 10000;
    
    var sendMessage = function(message)
    {
    	var gameInstanceID = stateobject.gameInstanceId;
    	var formdata = marriageRummy.request.getAddChatMessage(gameInstanceID,message,"Auto",stateobject.playerpos);
    	var url = marriageRummy.urls.addChatMessage;
    	var onSuccessCallbackfn = marriageRummy.callbacks.getGameLauncherCallback().onAddChatMessageSuccess;
    	var onFailureCallbackfn = marriageRummy.callbacks.getGameLauncherCallback().onAddChatMessageFailure;    		
    	marriageRummy.httpComm.invokeAsyncRequest(url, formdata, onSuccessCallbackfn, onFailureCallbackfn, null);
    };
    
    var getMessage = function()
    {
    	var gameInstanceID = stateobject.gameInstanceId;
    	var url = marriageRummy.urls.getChatMessage;
    	var formdata = marriageRummy.request.getChatMessage(gameInstanceID,currentChatCount);
    	var onSuccessCallbackfn = marriageRummy.callbacks.getGameLauncherCallback().onGetChatMessageSuccess;
    	var onFailureCallbackfn = marriageRummy.callbacks.getGameLauncherCallback().onGetChatMessageFailure;
    	marriageRummy.httpComm.invokeAsyncRequest(url, formdata, onSuccessCallbackfn, onFailureCallbackfn, null);
    };
    
    
    var init = function()
    {
    	$("#GameLauncherContainer .well dd#gamedesc").html(stateobject.gameName);
    	$("#GameLauncherContainer .well dd#host").html(stateobject.owner);
    	var type = marriageRummy.dataConvertor.convertGameTypetoDisplayText(stateobject.gameType);
    	$("#GameLauncherContainer .well dd#gameType").html(type);
    	$("#GameLauncherContainer .well dd#noplayers").html(stateobject.playerlist.length+"/"+stateobject.maxplayers);
    	if(stateobject.gameMoneyBased == true)
    	       $("#GameLauncherContainer .well dd#gameMode").html("Per Card  ");
    	if(stateobject.gamePointsBased == true)
 	       $("#GameLauncherContainer .well dd#gameMode").html("Points");
    	if(stateobject.gameMoneyBased == true)
 	       $("#GameLauncherContainer .well dd#perCard").html(stateobject.moneyPerCard);
 	    if(stateobject.gamePointsBased == true)
	       $("#GameLauncherContainer .well dd#perCard").html(stateobject.maxPoints);
    	
 	    $(".sendText textarea").keyup(function(e){
 	       if(e.keyCode == 13)
 	      {
 	          var message = $(this).val();
 	          sendMessage(message);
 	      }
 	  });
    	
    };
    
    init();
    
   
    var pollingCallback = function()
    {
    	console.log("Polling for chat Messages :" ,stateobject.gameInstanceId, currentChatCount);
    	getMessage();
    
    };
    
    var playerCheckCallback = function()
    {
    	
    	var url = marriageRummy.urls.getPlayersinGame;
    	var formdata = marriageRummy.request.getPlayersinGameRequest(stateobject.gameInstanceId,stateobject.lobbyName, stateobject.gameType);
    	var successfn = marriageRummy.callbacks.getGameLauncherCallback().onGetPlayersinGameSuccess;
    	var failurefn = marriageRummy.callbacks.getGameLauncherCallback().onGetPlayersinGameFailure;
    	var reqobj = {"formdata":formdata};
    	marriageRummy.httpComm.invokeAsyncRequest(url, formdata, successfn, failurefn, reqobj);
    	console.log("Checking for Players list in the game : " ,url , formdata);
    };
    
    
    self.sendMessageSuccess = function()
    {
    	$('.sendText textarea').val("");
    	getMessage();
    }; 
    
    self.getMessageSuccess = function(arrayofmessages,currentCount)
    {
    	if(currentChatCount == currentCount)
    		return;
    	else
    		currentChatCount = currentCount;
    	var htmlContent = '<div class="chatContent PlayerPOSITIONChat"><span><strong>PLAYERNAME</strong>MESSAGE</span></div>';
    	for(var i=0;i<arrayofmessages.length;i++)
    		{
    		   var position = arrayofmessages[i].playerpos;
    		   var playername = arrayofmessages[i].playerName;
    		   var message = arrayofmessages[i].message;
    		   var finalcontent = htmlContent.replace("POSITION",position).replace("PLAYERNAME",playername + " : ").replace("MESSAGE",message);
    		   $('.chatWindow').append(finalcontent);
    		}    
    	
    	var clienHeight = $('.chatWindow')[0].clientHeight;
    	var chatWindowScrollHeight = $('.chatWindow')[0].scrollHeight;
	    var chatWindowScrollTop = $('.chatWindow')[0].scrollTop;
    	if(chatWindowScrollHeight > clienHeight)
    		{
    		    newscrolltop = chatWindowScrollTop + (chatWindowScrollHeight - currentChatWindowScrollHeight);
    		    $('.chatWindow').scrollTop(newscrolltop);
    		}
    	else
    		{
    		   currentChatWindowScrollHeight = chatWindowScrollHeight;
    		   currentChatWindowScrollTop = chatWindowScrollTop;
    		}
    }; 
    
    self.updatePlayerList = function(data)
    {
    	$("#GameLauncherContainer .well dd#noplayers").html(data.playerlist.length+"/"+stateobject.maxplayers);
    	$("#gamemembers #playersarea").empty();
    	var membertemplate = '<div class="members"><img src="./assets/images/Cards/ClubCards/A.png" width="35px" height="35px" />' +
					  '<p>MEMBERNAME<i id="add" class="fa fa-plus"></i><i id="remove" class="fa fa-times"></i></p></div>';
    	for(var i=0;i<data.playerlist.length;i++)
    		{
    		    var nickname = data.playerlist[i].HumanPlayer.nickName;
    		    var addMemeberContent = membertemplate.replace("MEMBERNAME", nickname);
    		    $("#gamemembers #playersarea").append(addMemeberContent);
    		}
    };
    
    self.startPollingforChatMessages = function()
    {
    	chatPollingJob = setInterval(pollingCallback,chatPollinginterval,stateobject.gameInstanceID);
    };
    
    self.stopPollingforChatMessages = function()
    {
    	clearInterval(chatPollingJob);
    };
    
    self.startPlayerCheckJob = function()
    {
    	playerCheckCallback(stateobject.gameInstanceId,stateobject.lobbyName,stateobject.gameType);
    	playerCheckJob = setInterval(playerCheckCallback, playerCheckInterval,stateobject.gameInstanceId,stateobject.lobbyName,stateobject.gameType);
    };
    
    self.stopPlayerCheckJob = function()
    {
    	clearInterval(playerCheckJob);
    };
    
   
};

var marriageRummy = marriageRummy || {};
marriageRummy.gameBrowserUtilities = new MarriageRummy.Utilities.RummyUtilities.GameBrowserUtilities();
//marriageRummy.gameLauncherUtilities = new MarriageRummy.Utilities.RummyUtilities.GameLauncherUtilities();
