package com.adansoft.great21.controllers;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.config.StompBrokerRelayRegistration;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
			super.configureMessageBroker(registry);
			/*StompBrokerRelayRegistration reg = registry.enableStompBrokerRelay("/WebSocketGameLauncher/","/WebSocketChatMessages/","/WebSockets");
			reg.setRelayHost("localhost");
			reg.setRelayPort(61613);*/
			
			registry.enableSimpleBroker("/WebSocketGameLauncher/","/WebSocketChatMessages/","/WebSockets");
			registry.setApplicationDestinationPrefixes("/marriagerummy");
			
	}
	

	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/WebSocketChatMessages/Add/*","/WebSocketGameLauncher/Player/Add/*","/WebSockets/Notifications/*").withSockJS();
	}
	
	
}