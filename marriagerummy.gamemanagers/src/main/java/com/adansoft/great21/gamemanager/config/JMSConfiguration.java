package com.adansoft.great21.gamemanager.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;



import org.springframework.jms.connection.SingleConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import com.adansoft.great21.gamemanager.GameManagerApplication;
import com.adansoft.great21.gamemanager.JMSHandlers.JMSExceptionListener;
import com.adansoft.great21.gamemanager.JMSHandlers.PulseProducer;
import com.adansoft.great21.jms.models.GameManagerHeartBeat;

@Configuration
@PropertySource("file:GameManager.properties")
public class JMSConfiguration {

	
	@Value("${InstanceID}")
	private String gameManagerInstanceID;
	
	@Value("${JMS.URL}")
	private String brokerURL;
	
	@Value("${JMS.USER}")
	private String jmsuser;
	

	@Value("${JMS.PASSWORD}")
	private String jmspassword;
	
	@Value("${JMS.DETECT.GAMEMANAGERS}")
	private String pulseDestination;
	
	@Value("${JMS.GAMEMANAGER.REQ}")
	private String requestQueue;
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
	      return new PropertySourcesPlaceholderConfigurer();
	   }
	

	public ActiveMQConnectionFactory createAMQConnectionFactory()
	{		
		System.out.println("AMQ Connection factory called");
		ActiveMQConnectionFactory amqfactory =  new ActiveMQConnectionFactory(jmsuser,jmspassword,brokerURL);
		return  amqfactory;
	}
	
	@Bean
	public JMSExceptionListener createExceptionListener()
	{
		return new JMSExceptionListener();
	}
	
	@Bean 
	public SingleConnectionFactory createSingleConnectionFactory()
	{
		return new SingleConnectionFactory(createAMQConnectionFactory());
	}
	
	@Bean
	public ActiveMQTopic createPulseTopic()
	{
		
		System.out.println("Broker URL :- " + brokerURL);
		System.out.println("JMS User :- " + jmsuser);
		System.out.println("JMS Password :- " + jmspassword);
		System.out.println("Pulse Destination :- " + pulseDestination);
		return new ActiveMQTopic(pulseDestination);
	}
	
	
	public MessageConverter createPulseConvertor()
	{
		MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
		converter.setTargetType(MessageType.TEXT);
		converter.setTypeIdPropertyName("HEARTBEAT");		
		return converter;
	}
	
	@Bean
	public JmsTemplate createPulseJmsTemplate()
	{
		JmsTemplate pulsetemplate = new JmsTemplate(createSingleConnectionFactory());
		pulsetemplate.setDefaultDestination(createPulseTopic());
		pulsetemplate.setPubSubDomain(true);
		pulsetemplate.setMessageConverter(createPulseConvertor());
		return pulsetemplate;
	}	
	
	@Bean
	public GameManagerHeartBeat createHeartBeat()
	{
		return new GameManagerHeartBeat(gameManagerInstanceID, requestQueue.replace("INSTANCEID", gameManagerInstanceID), 0, GameManagerHeartBeat.STATUS_ALIVE);
		
	}

	
	
}
