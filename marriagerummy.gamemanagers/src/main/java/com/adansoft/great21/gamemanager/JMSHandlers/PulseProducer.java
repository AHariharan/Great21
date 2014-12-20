package com.adansoft.great21.gamemanager.JMSHandlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.adansoft.great21.gamemanager.config.DataConfiguration;
import com.adansoft.great21.gamemanager.config.JMSConfiguration;
import com.adansoft.great21.games.RummyArena;
import com.adansoft.great21.jms.models.GameManagerHeartBeat;

@Import({JMSConfiguration.class , DataConfiguration.class})

@Configuration
@EnableScheduling
public class PulseProducer {
	
	@Value("${InstanceID}")
	private String gameManagerInstanceID;
	
	

	@Autowired
	private JmsTemplate pulseTemplate;
	
	@Autowired
	private GameManagerHeartBeat heartbeat;
	
	private HeartbeatPostProcessor postprocessor = new HeartbeatPostProcessor();

	
	
	public PulseProducer()
	{
		
	}
	
	
	public PulseProducer(JmsTemplate template,String gameInstanceID)
	{
		this.pulseTemplate = template;
		this.gameManagerInstanceID = gameInstanceID;
	}
	
	
	public void sendPulse(String message)
	{	
		System.out.println("Sending pulse ... ");
		pulseTemplate.convertAndSend(message);
	}

	public JmsTemplate getPulseTemplate() {
		return pulseTemplate;
	}

	public void setPulseTemplate(JmsTemplate pulseTemplate) {
		this.pulseTemplate = pulseTemplate;
		
	}
	
	@Scheduled(fixedRate = 25000)
	public void sendAdditionalPulses()
	{
		heartbeat.setNoofgamesHandled(RummyArena.getInstance().numofgamesinCache());
		System.out.println("Sending pulse ... " + gameManagerInstanceID + " : " + heartbeat);
	    pulseTemplate.setMessageIdEnabled(true);
	    System.out.println("RummyArena Game size: " + RummyArena.getInstance().numofgamesinCache());
	    heartbeat.setNoofgamesHandled(RummyArena.getInstance().numofgamesinCache());
		pulseTemplate.convertAndSend(heartbeat,postprocessor);

	}
	
	
	
}
