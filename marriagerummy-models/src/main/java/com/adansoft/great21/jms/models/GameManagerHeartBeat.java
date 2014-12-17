package com.adansoft.great21.jms.models;

public class GameManagerHeartBeat {

	public static final String STATUS_ALIVE = "Alive";
	
	private String gameManagerInstanceID;
	private String requestQueue;
	private int noofgamesHandled;
	private String status = GameManagerHeartBeat.STATUS_ALIVE;
	
	
	public GameManagerHeartBeat() {
		super();
	}


	public GameManagerHeartBeat(String gameManagerInstanceID,
			String requestQueue, int noofgamesHandled, String status) {
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


	public static String getStatusAlive() {
		return STATUS_ALIVE;
	}
	
	
	@Override
	public String toString() {
	    
		return " [gameManagerInstanceID : " + 
		        getGameManagerInstanceID()+
		        " ] , requestQueue : "+ getRequestQueue()+
		        " ] , status : " + getStatus();
	}
	
	
	
	
	
}
