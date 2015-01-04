package com.adansoft.great21.controllers;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
			super.configureMessageBroker(registry);
			registry.enableSimpleBroker("/WebSocketGameLauncher/","/WebSocketChatMessages/");
			registry.setApplicationDestinationPrefixes("/marriagerummy");
			
	}
	

	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/WebSocketChatMessages/Add/*","/WebSocketGameLauncher/Player/Add/*").withSockJS();
	}
	
	

}
