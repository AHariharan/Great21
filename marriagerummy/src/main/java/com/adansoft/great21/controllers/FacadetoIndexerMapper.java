package com.adansoft.great21.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.adansoft.great21.exceptions.GameIndexerConfigException;


public class FacadetoIndexerMapper {

	
	private String hostname = null;
		
	private String port = null;
		
	private String baseURL = null;
	
	
	
	
	public FacadetoIndexerMapper(String hostname, String port, String baseURL) {
		super();
		this.hostname = hostname;
		this.port = port;
		this.baseURL = baseURL;
	}
	
	




	public FacadetoIndexerMapper() {
		super();
	
	}


    



	public String getHostname() {
		return hostname;
	}






	public void setHostname(String hostname) {
		this.hostname = hostname;
	}






	public String getPort() {
		return port;
	}






	public void setPort(String port) {
		this.port = port;
	}






	public String getBaseURL() {
		return baseURL;
	}






	public void setBaseURL(String baseURL) {
		this.baseURL = baseURL;
	}






	public String getIndexerURI() throws Exception
	{
		String GI_URL = null;
		if(hostname == null || port == null || baseURL == null)
			throw new GameIndexerConfigException("Invalid Game Indexer Config Exception , hostname = " + hostname + " , port = " + port + " , baseURL : " + baseURL);
		else
			GI_URL = "http://"+hostname+":"+port+"/"+baseURL;
		return GI_URL;
	}
	
	
	
	
}


