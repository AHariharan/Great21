package com.adansoft.great21.gamemanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
public class GameController {
	
	public static void main(String[] args) {
		SpringApplication.run(GameController.class, args);
	}

}
