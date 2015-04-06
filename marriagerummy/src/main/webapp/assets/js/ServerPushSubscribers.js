/**
 * 
 */

// Ulities Name space
MarriageRummy.Utilities = MarriageRummy.Utilities || {};

// UIUtilites Namespace
MarriageRummy.Utilities.PushServerSubscriber = MarriageRummy.Utilities.PushServerSubscriber || {};

MarriageRummy.Utilities.PushServerSubscriber.ChatSubscriber = function()
{
	var stompClient = null;
//	var playerStompClient = null;
	
	var self = this;
	

		
	self.connect = function(gameInstanceID)
	{
		var socket = new SockJS('/marriagerummy/WebSocketChatMessages/Add/');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function(frame) {
            console.log('Connected: ' + frame);
            stompClient.subscribe('/WebSocketChatMessages/getMessages/'+gameInstanceID, function(formdata){
                console.log ("Websocket subscribe received " + formdata.body);
                var gamelauncher =  jQuery.data( $("#GameLauncher")[0], "LauncherObj");
     		    gamelauncher.onReceiveMessage(formdata.body);	    
            });
           /* stompClient.subscribe('/WebSocketGameLauncher/Player/Add/'+gameInstanceID, function(formdata){
                console.log ("Websocket subscribe ** ADD Player *** received " + formdata.body); 
                var gamelauncher =  jQuery.data( $("#GameLauncher")[0], "LauncherObj");
     		    gamelauncher.onPlayerJoin();
            });    */ 
           
        });  
      
	};
	
	self.disconnect = function() {
        stompClient.disconnect();
      //  playerStompClient.disconnect();
        console.log("Disconnected");
    };
    
    self.sendMessage = function(gameInstanceID,formdata)
    {    	
    	stompClient.send("/marriagerummy/WebSocketChatMessages/Add/"+gameInstanceID, {},JSON.stringify(formdata));
    };
    
    self.sendAddPlayer = function(formdata)
    {    	
    	stompClient.send("/marriagerummy/WebSocketGameLauncher/Player/Add", {},JSON.stringify(formdata));
    };
};


MarriageRummy.Utilities.PushServerSubscriber.NotificationManager = function(gid)
{
	 var gameInstanceID = gid;
	 var stompClient = null;
	 var callback = new MarriageRummy.Utilities.PushServerSubscriber.NotificationCallback();
	 var notificationConnectDestination = '/marriagerummy/WebSockets/Notifications/';
	 var subscribeNotification = "/WebSockets/Notifications/";
	
	 var self = this;
	 
	var autoConnect = function()
	 {
		var socket = new SockJS(notificationConnectDestination);
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function(frame) {
            console.log('Notification Manager Connected: ' + frame);
            stompClient.subscribe(subscribeNotification+gameInstanceID, function(formdata){
                console.log ("Notification subscribed " + formdata.body);
                var stompBody = formdata.body; 
                handleNotifications(stompBody);
            });          
           
        });  

	 };
	 
	 autoConnect();
	 
	 var handleNotifications = function(data)
	 {
		 var jsonobj = JSON.parse(data);
		 var type = jsonobj.notificationType;
		 var source = jsonobj.notificationSource;
		 var object = jsonobj.notificationObject;
		 var username = jsonobj.notifiedBy;
		 if(type == "ADDPLAYER")
			 callback.handleAddPlayer(jsonobj);
		 if(type == "CANCELGAME")
			 callback.handleCancelGame(jsonobj);
		 if(type == "REMOVEPLAYER")
			 callback.handleRemovePlayer(jsonobj);
		 if(type == "KICKPLAYER")
			 callback.handleKickPlayer(jsonobj);
		 if(type == "LAUNCHGAME")
			 callback.handleLaunchGame(jsonobj);
		 if(type == "CARDDROPPED")
		     callback.handleCardDropped(jsonobj);
		 if(type == "PLAYERFOLD")
			 callback.handleFoldPlayer(jsonobj);
		 if(type == "DECLARESUCCESS")
			 callback.handleDeclareSuccess(jsonobj);
		 if(type == "SHOWCARDSUCCESS")
			 callback.handleShowCardPlayerNotification(jsonobj);
		 if(type == "NEWGAMENOTIFY")
			 callback.handleNewGameRoundNotification(jsonobj);
		 if(type == "GAMEOVER")
			 callback.handleGameOverNotification(jsonobj);
	 }; 
	 
	 self.sendNotificationEvent = function(data)
	 {
		 stompClient.send(notificationConnectDestination+gameInstanceID,{},JSON.stringify(data));
	 };
	 
	 self.shutdown = function()
	 {
		  stompClient.disconnect();
	 };
	 
	 
};

MarriageRummy.Utilities.PushServerSubscriber.NotificationCallback = function()
{
	var self = this;
	self.handleAddPlayer = function(data)
	{
		   var gamelauncher =  jQuery.data( $("#GameLauncher")[0], "LauncherObj");
		   gamelauncher.onPlayerJoin();
	};
	self.handleRemovePlayer = function(data)
	{
		if($("#GameLauncher").css("display") == "block")
			{
		        var gamelauncher =  jQuery.data( $("#GameLauncher")[0], "LauncherObj");
		        gamelauncher.onPlayerJoin();
			}    
	};
	self.handleKickPlayer = function(data)
	{
		if(data.notificationObject.nickname == marriageRummy.loggedinUser)
		{
			 marriageRummy.generalutility.showRedAlert("Host Kicked !", "Host has kicked you from  game and we returned you to Game Lobby");
             $("#GameLauncher").css("display", "none");
             marriageRummy.gameBrowserUtilities.refreshGameLobby(data.notificationObject.lobbyName);
             marriageRummy.notificationManager.shutdown();
             marriageRummy.chatSubscriber.disconnect();
             var gamelauncher =  jQuery.data( $("#GameLauncher")[0], "LauncherObj");
     	     gamelauncher.shutdownEvents();
             
		}
		if($("#GameLauncher").css("display") == "block")
		{
	        var gamelauncher =  jQuery.data( $("#GameLauncher")[0], "LauncherObj");
	        gamelauncher.onPlayerJoin();
		}  
		
	};
	
	self.handleLaunchGame = function(data)
	{
		   $('#mygame').css("display","block");
		   marriageRummy.gameBrowserUtilities.refreshGameLobby(data.notificationObject.lobbyName);
		   $("#GameLauncher").css("display", "none");		   
		   marriageRummy.navigator.resetNavigation();
		   $('#mygame').addClass("selected");
		   $('#mygame').children().filter("div").css("display", "block");
		   var divid = $('#mygame').attr("data-divid");
		   if ($('#' + divid) != undefined && $('#' + divid) != null)
				$('#' + divid).slideDown();
		   var gameObject = new MarriageRummy.Utilities.GameUtilities.GameStarter(data.notificationObject);
		   jQuery.data( $("#GameArena")[0], "GameObj", gameObject);
		   // Add Card Distribution here
		   $('#gameToolMain').slideDown();
		   gameObject.getCards();
		   gameObject.getJoker();
		   gameObject.getOpenCard();
		   gameObject.getPlayerList();
	};
	
	self.handleCancelGame = function(data)
	{
		   console.log("Game Cancelled " , data);
		   if($("#GameLauncher").css("display") == "block")
			   {
			             marriageRummy.generalutility.showRedAlert("Game Cancelled !", "Host has cancelled the game and we returned you to Game Lobby");
			             $("#GameLauncher").css("display", "none");
    	                 marriageRummy.gameBrowserUtilities.refreshGameLobby(data.notificationObject.lobbyName);
    	                 try
    	                 {
    	                 marriageRummy.notificationManager.shutdown();
    	                 marriageRummy.chatSubscriber.disconnect();
    	                 gamelauncher.shutdownEvents();
    	                 }catch(e)
    	                 {
    	                	 console.log("Cancel Game : " + e);
    	                 }
			   }
	};

    self.handleCardDropped = function(data)
    {
    	var gameObj = jQuery.data( $("#GameArena")[0], "GameObj");
    	gameObj.notifyDroppedCard(data.notificationObject.card);    	
    	gameObj.onDropNotificationSuccess(data);
    };
    
    self.handleFoldPlayer = function(data)
    {
    	var gameObj = jQuery.data( $("#GameArena")[0], "GameObj");
    	gameObj.notifyFoldCard(data.notificationObject);    	
    	gameObj.onFoldNotificationSuccess(data);
    };
    
    self.handleDeclareSuccess = function(data)
    {
    	console.log("Test Data ... " + JSON.stringify(data));
    	if(data.notifiedBy == marriageRummy.loggedinUser)
    		{
    		    marriageRummy.generalutility.setLoadingMask("Please wait for other players to show cards");
    		    var gameObj = jQuery.data( $("#GameArena")[0], "GameObj");
    		    gameObj.getPlayerShowStatus();
    		    return;
    		}
    	else
    		{
    		   marriageRummy.generalutility.showSuccessAlert("Game Declared",data.notifiedBy + " has declared the game. Please show your cards.." );
    		   var gameObj = jQuery.data( $("#GameArena")[0], "GameObj");
    		   gameObj.forceToShowCards(data);
    		}
    	console.log("Sent by ..,, " + data.notificationObject.nickname);
    };
    
    self.handleShowCardPlayerNotification = function(data)
    {
    	console.log("handleShowCardPlayerNotification Data ... " + JSON.stringify(data));
    	var gameObj = jQuery.data( $("#GameArena")[0], "GameObj");
		gameObj.getPlayerShowStatus();
    };
    
    self.handleNewGameRoundNotification = function(data)
    {
    	console.log("handleNewGameRoundNotification Data ... " + JSON.stringify(data));
    	marriageRummy.generalutility.setLoadingMask("Starting new round");
    	var gameObject = jQuery.data( $("#GameArena")[0], "GameObj");
    	gameObject.hideAllGameTools();
        gameObject.onNewRound();
        setTimeout(startnewGameAfterTimeout,5000,gameObject);
        setTimeout(startUpdateTimer,1000,4);
    };
    
    startUpdateTimer = function(count)
    {
         if(count == 0) 
        	 return;
         else
        	 {
        	    count--;
        	    marriageRummy.generalutility.setLoadingMask("Starting new round in " + count + " seconds");
        	    setTimeout(startUpdateTimer,1000,count);
        	 }
    };
    
    startnewGameAfterTimeout = function(gameObject)
    {
    	  var stateobject = gameObject.getStateObject();
          
          var gameObject = new MarriageRummy.Utilities.GameUtilities.GameStarter(stateobject);
  		   jQuery.data( $("#GameArena")[0], "GameObj", gameObject);
  		   gameObject.getCards();
  		   gameObject.getJoker();
  		   gameObject.getOpenCard();
  		   gameObject.getPlayerList();
          
  		// console.log("startnewGameAfterTimeout Data ... " + JSON.stringify(data));
  		  marriageRummy.generalutility.hideLoadingMask("Starting new round");
    };
    
    self.handleGameOverNotification = function(data)
    {
    	console.log("Handle Game Over Notification : " + JSON.stringify(data));
    };
    
};


MarriageRummy.Utilities.PushServerSubscriber.RequestPreparer = function()
{
     var self = this;
     
     self.createAddPlayerNotification = function(source,object)
     {
    	 var formdata = {
    			 notificationType : "ADDPLAYER",
    			 notificationSource : source,
    			 notificationObject : object,
    			 notifiedBy : "Auto"
    	 };
    	 return formdata;
     };
     
     self.createCancelGameNotification = function(source,object)
     {
    	 var formdata = {
    			 notificationType : "CANCELGAME",
    			 notificationSource : source,
    			 notificationObject : object,
    			 notifiedBy : "Auto"
    	 };
    	 return formdata;
     };
     
     self.createRemovePlayerNotification = function(source,object)
     {
    	 var formdata = {
    			 notificationType : "REMOVEPLAYER",
    			 notificationSource : source,
    			 notificationObject : object,
    			 notifiedBy : "Auto"
    	 };
    	 return formdata;
     };
     self.kickPlayerNotification = function(source,object)
     {
    	 var formdata = {
    			 notificationType : "KICKPLAYER",
    			 notificationSource : source,
    			 notificationObject : object,
    			 notifiedBy : "Auto"
    	 };
    	 return formdata;
     };
     
     self.launchGameNotification = function(source,object)
     {
    	 var formdata = {
    			 notificationType : "LAUNCHGAME",
    			 notificationSource : source,
    			 notificationObject : object,
    			 notifiedBy : "Auto"
    	 };
    	 return formdata;
     };
     
     self.dropCardNotification = function(source,object)
     {
    	 var formdata = {
    			 notificationType : "CARDDROPPED",
    			 notificationSource : source,
    			 notificationObject : object,
    			 notifiedBy : "Auto"
    	 };
    	 return formdata;
     };
     
     self.foldHandNotification = function(source,object)
     {
    	 var formdata = {
    			 notificationType : "PLAYERFOLD",
    			 notificationSource : source,
    			 notificationObject : object,
    			 notifiedBy : "Auto"
    	 };
    	 return formdata;
     };
     
     self.declareSuccessNotification = function(source,object)
     {
    	 var formdata = {
    			 notificationType : "DECLARESUCCESS",
    			 notificationSource : source,
    			 notificationObject : object,
    			 notifiedBy : "Auto"
    	
    	 };
    	 return formdata;
     };
     
     self.showCardPlayerNotification = function(source,object)
     {
    	 var formdata = {
    			 notificationType : "SHOWCARDSUCCESS",
    			 notificationSource : source,
    			 notificationObject : object,
    			 notifiedBy : "Auto"
    	
    	 };
    	 return formdata;
     };
    
};

marriageRummy = marriageRummy || {};
marriageRummy.chatSubscriber = new  MarriageRummy.Utilities.PushServerSubscriber.ChatSubscriber();
marriageRummy.notificationManager =  {};
marriageRummy.notificationRequest = new MarriageRummy.Utilities.PushServerSubscriber.RequestPreparer();
/*try
{
marriageRummy.chatSubscriber.connect("GAMEINSTANCE123");
marriageRummy.notificationManager =  new MarriageRummy.Utilities.PushServerSubscriber.NotificationManager("TEST123");
}catch(e)
{
   console.log(e);
}*/