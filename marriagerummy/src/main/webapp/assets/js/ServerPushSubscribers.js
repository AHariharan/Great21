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

marriageRummy = marriageRummy || {};
marriageRummy.chatSubscriber = new  MarriageRummy.Utilities.PushServerSubscriber.ChatSubscriber();
/*try
{
marriageRummy.chatSubscriber.connect("GAMEINSTANCE123");
}catch(e)
{
   console.log(e);
}*/