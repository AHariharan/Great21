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
            stompClient.subscribe('/WebSocketGameLauncher/Player/Add/'+gameInstanceID, function(formdata){
                console.log ("Websocket subscribe ** ADD Player *** received " + formdata.body); 
                var gamelauncher =  jQuery.data( $("#GameLauncher")[0], "LauncherObj");
     		    gamelauncher.onPlayerJoin();
            });     
           
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
	 var notificationConnectDestination = '/marriagerummy/WebSockets/Notifications';
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
		
	 }; 
	 
	 self.sendNotificationEvent = function(data)
	 {
		 stompClient.send(subscribeNotification+gameInstanceID,{},JSON.stringify(data));
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
		   var gamelauncher =  jQuery.data( $("#GameLauncher")[0], "LauncherObj");
		   gamelauncher.onPlayerJoin();
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
    			 norifiedBy : "Auto"
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
}catch(e)
{
   console.log(e);
}*/