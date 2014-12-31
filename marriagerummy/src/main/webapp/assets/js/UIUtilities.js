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


MarriageRummy.Utilities.RummyUtilities.GameLauncherUtilities = function()
{
    var self = this;
    
    var chatPollingJob = {};
    var chatPollinginterval = 2000; // In Milli seconds
    var currentChatCount = 0;
    
    var playerCheckJob = {};
    var playerCheckInterval = 10000;
    
    var pollingCallback = function(gameInstanceID)
    {
    	console.log("Polling for chat Messages :" ,gameInstanceID, currentChatCount);
    
    };
    
    var playerCheckCallback = function(gameInstanceID,lobbyName,gameType)
    {
    	var url = marriageRummy.urls.getPlayersinGame;
    	var formdata = marriageRummy.request.getPlayersinGameRequest(gameInstanceID, lobbyName, gameType);
    	var successfn = marriageRummy.callbacks.getGameLauncherCallback().onGetPlayersinGameSuccess;
    	var failurefn = marriageRummy.callbacks.getGameLauncherCallback().onGetPlayersinGameFailure;
    	var reqobj = {"formdata":formdata};
    	marriageRummy.httpComm.invokeAsyncRequest(url, formdata, successfn, failurefn, reqobj);
    	console.log("Checking for Players list in the game : " ,url , formdata);
    };
    
    self.updatePlayerList = function(data)
    {
    	$("#gamemembers div:first-child").empty();
    	var membertemplate = '<div class="members"><img src="./assets/images/Cards/ClubCards/A.png" width="35px" height="35px" />' +
					  '<p>MEMBERNAME<i id="add" class="fa fa-plus"></i><i id="remove" class="fa fa-times"></i></p></div>';
    	for(var i=0;i<data.playerlist.length;i++)
    		{
    		    var nickname = data.playerlist[i].HumanPlayer.nickName;
    		    var addMemeberContent = membertemplate.replace("MEMBERNAME", nickname);
    		    $("#gamemembers>div").append(addMemeberContent);
    		}
    };
    
    self.startPollingforChatMessages = function(gameInstanceID)
    {
    	chatPollingJob = setInterval(pollingCallback,chatPollinginterval,gameInstanceID);
    };
    
    self.stopPollingforChatMessages = function()
    {
    	clearInterval(chatPollingJob);
    };
    
    self.startPlayerCheckJob = function(gameInstanceID,lobbyName,gameType)
    {
    	playerCheckJob = setInterval(playerCheckCallback, playerCheckInterval,gameInstanceID,lobbyName,gameType);
    };
    
    self.stopPlayerCheckJob = function()
    {
    	clearInterval(playerCheckJob);
    };
    
   
};

var marriageRummy = marriageRummy || {};
marriageRummy.gameBrowserUtilities = new MarriageRummy.Utilities.RummyUtilities.GameBrowserUtilities();
marriageRummy.gameLauncherUtilities = new MarriageRummy.Utilities.RummyUtilities.GameLauncherUtilities();
