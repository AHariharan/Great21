package com.adansoft.great21.gamemanager.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;



import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.connection.SingleConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;


import com.adansoft.great21.gamemanager.JMSHandlers.JMSExceptionListener;
import com.adansoft.great21.gamemanager.JMSHandlers.QueueListener;

@Configuration
@PropertySource("file:GameManager.properties")
public class JMSConfiguration {

	

	
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
	
	@Value("${InstanceID}")
	private String gameManagerInstanceID;
	

	
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
	
	

	
	/* Queue Related Configurations */
	@Bean
	public ActiveMQQueue createReceivingQueue()
	{	
		return new ActiveMQQueue(requestQueue.replace("INSTANCEID", gameManagerInstanceID));
	}
	
	@Bean
	public MappingJackson2MessageConverter createQueueMessageConvertor()
	{
		MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
		converter.setTargetType(MessageType.TEXT);
		converter.setTypeIdPropertyName("Operation");		
		return converter;
	}
	
	private CachingConnectionFactory createCacheFactory()
	{
		CachingConnectionFactory factory = new CachingConnectionFactory(createAMQConnectionFactory());
		factory.setSessionCacheSize(100);
		factory.setExceptionListener(createExceptionListener());
		return factory;
	}
	
	@Bean QueueListener createQueueListener()
	{
		QueueListener queuelistner = new QueueListener();
		return queuelistner;
	}
	
	@Bean DefaultMessageListenerContainer createContainer()
	{
		DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
		container.setConcurrency("10");
		container.setConnectionFactory(createCacheFactory());
		container.setDestination(createReceivingQueue());
		container.setMessageListener(createQueueListener());
		return container;
	}
	
	
}
