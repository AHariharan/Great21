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
    var playerCheckInterval = 3000;
    
    var pollingCallback = function(gameInstanceID)
    {
    	console.log("Polling for chat Messages :" ,gameInstanceID, currentChatCount);
    	count++;
    };
    
    var playerCheckCallback = function(gameInstanceID)
    {
    	console.log("Checking for Players list in the game : " ,gameInstanceID , count);
    };
    
    self.startPollingforChatMessages = function(gameInstanceID)
    {
    	chatPollingJob = setInterval(pollingCallback,chatPollinginterval,gameInstanceID);
    };
    
    self.stopPollingforChatMessages = function()
    {
    	clearInterval(chatPollingJob);
    };
    
    self.startPlayerCheckJob = function()
    {
    	playerCheckJob = setInterval(playerCheckCallback, playerCheckInterval);
    };
    
    self.stopPlayerCheckJob = function()
    {
    	clearInterval(playerCheckJob);
    };
    
   
};

var marriageRummy = marriageRummy || {};
marriageRummy.gameBrowserUtilities = new MarriageRummy.Utilities.RummyUtilities.GameBrowserUtilities();
marriageRummy.gameLauncherUtilities = new MarriageRummy.Utilities.RummyUtilities.GameLauncherUtilities();
