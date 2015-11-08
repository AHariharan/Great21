package com.adansoft.great21.gamemanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.adansoft.great21.gamemanager.JMSHandlers.PulseProducer;

@SpringBootApplication
public class GameManagerApplication {
	
	public static void main(String[] args) {
		System.out.println(" Arguements :- " + args.length + "," + args[0]);
		SpringApplication.run(GameManagerApplication.class, args);			
	}

}
