package com.adansoft.great21.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;


import com.adansoft.great21.uischemas.AddGameChatRequest;

@Controller
public class WebSocketController {
	

	private SimpMessagingTemplate template;
	
	@Autowired
	public WebSocketController(SimpMessagingTemplate temp)
	{
		this.template = temp;
	}
	
	@MessageMapping("/WebSocketChatMessages/Add/{gameInstanceID}")
	/*@SendTo("/WebSocketChatMessages/getMessages/") */
	public String addChatMessage(AddGameChatRequest request,@DestinationVariable("gameInstanceID") String gameInstanceID)
	{
		System.out.println("Web Socket invoked : Game : " + request.getMessage().getPlayerName() + " - " + request.getMessage().getMessage() + " ::: " + gameInstanceID);
		template.convertAndSend("/WebSocketChatMessages/getMessages/"+gameInstanceID, "success for this game");
		return "success";
	}
	
	
	@ExceptionHandler
	public String handleBadRequest(Exception ex,HttpServletRequest request)
	{
		System.out.println("*********** Incoming request : " + request);
		ex.printStackTrace();
		return ex.getMessage();
	}
	

}
