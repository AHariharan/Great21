package com.adansoft.great21.controllers;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;








import com.adansoft.great21.restschemas.AddPlayerRequest;
import com.adansoft.great21.uischemas.AddGameChatRequest;
import com.adansoft.great21.uischemas.NotificationEvent;

@Controller
public class WebSocketController {
	

	private SimpMessagingTemplate template;
	
	@Autowired
	public WebSocketController(SimpMessagingTemplate temp)
	{
		System.out.println("*********** WEB SOCKET TEMPLATE INVOKED **************");
		this.template = temp;
		
	}
	
	@MessageMapping("/WebSocketChatMessages/Add/{gameInstanceID}")
	public AddGameChatRequest addChatMessage(AddGameChatRequest request,@DestinationVariable("gameInstanceID") String gameInstanceID,@AuthenticationPrincipal Authentication authentication )
	{
		request.getMessage().setPlayerName(authentication.getName());
		System.out.println("Web Socket invoked : Game : " + request.getMessage().getPlayerName() + " - " + request.getMessage().getMessage() + " ::: " + gameInstanceID);
		template.convertAndSend("/WebSocketChatMessages/getMessages/"+gameInstanceID, request);
		return request;
	}
	
	@MessageMapping("/WebSocketGameLauncher/Player/Add")
	public String addPlayerRequest(AddPlayerRequest request,@Payload String payload,@AuthenticationPrincipal Authentication authentication )
	{
		request.setNickname(authentication.getName());
		System.out.println("Web Socket invoked Add Player : Game : " + payload );
		template.convertAndSend("/WebSocketGameLauncher/Player/Add/"+request.getGameInstanceID(), request);
		return payload;
	}
	
	@MessageMapping("/WebSockets/Notifications/{gameInstanceID}")
	public void handleAllNotifications(NotificationEvent event,@DestinationVariable("gameInstanceID") String gameInstanceID,@AuthenticationPrincipal Authentication authentication )
	{
		 System.out.println("Web Socket Notification received :" + event.getNotifiedBy());
		 System.out.println("Web Socket Notification received :" + gameInstanceID);
	     System.out.println("Web Socket Notification received : " + event.getNotificationSource() + "____ " + event.getNotificationType() + "----" + event.getNotifiedBy() + "------" + authentication.getName());
	     event.setNotifiedBy(authentication.getName());
	     template.convertAndSend("/WebSockets/Notifications/"+gameInstanceID, event);
	    
	}
	
	public void sendNotificationFromBackend(NotificationEvent event,String gameInstanceID)
	{
		 System.out.println("Send Notification From Backend");
		template.convertAndSend("/WebSockets/Notifications/"+gameInstanceID, event);
	}
	
	
	@ExceptionHandler
	public String handleBadRequest(Exception ex,HttpServletRequest request)
	{
		System.out.println("*********** Incoming request : " + request);
		ex.printStackTrace();
		return ex.getMessage();
	}
	

}
