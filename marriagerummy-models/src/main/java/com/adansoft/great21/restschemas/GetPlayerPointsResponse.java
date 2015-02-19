package com.adansoft.great21.restschemas;

import java.io.Serializable;
import java.util.HashMap;

public class GetPlayerPointsResponse implements Serializable {

	private static final long serialVersionUID = 7903438008105891428L;
	
	private String gameInstanceID;
	private HashMap<String,HashMap<String,Integer>> pointsTable;
	
	public GetPlayerPointsResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GetPlayerPointsResponse(String gameInstanceID,
			HashMap<String, HashMap<String, Integer>> pointsTable) {
		super();
		this.gameInstanceID = gameInstanceID;
		this.pointsTable = pointsTable;
	}

	public String getGameInstanceID() {
		return gameInstanceID;
	}

	public void setGameInstanceID(String gameInstanceID) {
		this.gameInstanceID = gameInstanceID;
	}

	public HashMap<String, HashMap<String, Integer>> getPointsTable() {
		return pointsTable;
	}

	public void setPointsTable(HashMap<String, HashMap<String, Integer>> pointsTable) {
		this.pointsTable = pointsTable;
	}
	
	

}
