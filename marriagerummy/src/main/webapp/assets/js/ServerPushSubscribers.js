/**
 * 
 */

// Ulities Name space
MarriageRummy.Utilities = MarriageRummy.Utilities || {};

// UIUtilites Namespace
MarriageRummy.Utilities.PushServerSubscriber = MarriageRummy.Utilities.PushServerSubscriber || {};

MarriageRummy.Utilities.PushServerSubscriber.ChatSubscriber = function(Gid)
{
	var stompClient = null;
	
	var self = this;
	
	var gameInstanceID = Gid;
	
		
	self.connect = function()
	{
		var socket = new SockJS('/marriagerummy/WebSocketChatMessages/Add/');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function(frame) {
            console.log('Connected: ' + frame);
            stompClient.subscribe('/WebSocketChatMessages/getMessages/'+gameInstanceID, function(greeting){
                console.log ("Websocket subscribe received " + greeting);
            });
        });
	};
	
	self.disconnect = function() {
        stompClient.disconnect();
        console.log("Disconnected");
    };
    
    self.sendMessage = function(message,playerpos)
    {
    	
    	stompClient.send("/marriagerummy/WebSocketChatMessages/Add/"+gameInstanceID, {}, 
    			JSON.stringify({ 'gameInstanceID': gameInstanceID,
    				             'message' : {
    				            	'playerName':'Auto',
    				            	'message':message,
    				            	'playerpos' : playerpos
    				                }    				
    			                }));
    };
};

marriageRummy = marriageRummy || {};
marriageRummy.chatSubscriber = new  MarriageRummy.Utilities.PushServerSubscriber.ChatSubscriber();
try
{
marriageRummy.chatSubscriber.connect("GAMEINSTANCE123");
}catch(e)
{
   console.log(e);
}