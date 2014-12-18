package com.adansoft.great21.CacheModels;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import com.adansoft.great21.jms.models.GameManagerHeartBeat;

public class GameManagerCache {

	private String gameManagerInstanceID;
	private String requestQueue;
	private int noofgamesHandled;
	private String status;
	private Calendar lastHeartbeatReceived;
	
	
	public GameManagerCache() {
		super();
	}

	
	public GameManagerCache(GameManagerHeartBeat heartbeat,long jmstimestamp)
	{
		this.gameManagerInstanceID = heartbeat.getGameManagerInstanceID();
		this.noofgamesHandled = heartbeat.getNoofgamesHandled();
		this.requestQueue = heartbeat.getRequestQueue();
		this.status = heartbeat.getStatus();
		this.lastHeartbeatReceived = new GregorianCalendar();
		this.lastHeartbeatReceived.setTimeInMillis(jmstimestamp);
		
	}

	public GameManagerCache(String gameManagerInstanceID, String requestQueue,
			int noofgamesHandled, String status) {
		super();
		this.gameManagerInstanceID = gameManagerInstanceID;
		this.requestQueue = requestQueue;
		this.noofgamesHandled = noofgamesHandled;
		this.status = status;
	}


	public String getGameManagerInstanceID() {
		return gameManagerInstanceID;
	}


	public void setGameManagerInstanceID(String gameManagerInstanceID) {
		this.gameManagerInstanceID = gameManagerInstanceID;
	}


	public String getRequestQueue() {
		return requestQueue;
	}


	public void setRequestQueue(String requestQueue) {
		this.requestQueue = requestQueue;
	}


	public int getNoofgamesHandled() {
		return noofgamesHandled;
	}


	public void setNoofgamesHandled(int noofgamesHandled) {
		this.noofgamesHandled = noofgamesHandled;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}
	
	
	public String getFormattedLastHeartbeatReceived()
	{
		DateFormat format = SimpleDateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL);
		return format.format(this.lastHeartbeatReceived.getTime());		
	}
	
	public void setLastHeartbeatReceived(long jmstimestamp)
	{
		this.lastHeartbeatReceived.setTimeInMillis(jmstimestamp);
	}


	public void setLastHeartbeatReceived(Calendar lastHeartbeatReceived) {
		this.lastHeartbeatReceived = lastHeartbeatReceived;
	}


	public Calendar getLastHeartbeatReceived() {
		return lastHeartbeatReceived;
	}
	
	@Override
	public String toString() {
	
		String content = "[ GameInstanceID : - " + this.getGameManagerInstanceID() + " ] :: " +
				         "[ Request Queue  : - " + this.getRequestQueue() + " ] :: " +
				         "[ No of Game Handled : - " + this.getNoofgamesHandled() + " ] :: " +
				         "[ Status : - " + this.getStatus() + " ] :: " +
				         "[ Last Heart beat :- " + this.getFormattedLastHeartbeatReceived();
				         
		
		return content;
	}
	
	
	
}
