package com.adansoft.great21.restschemas;

import java.io.Serializable;
import java.util.ArrayList;

import com.adansoft.great21.models.Player;

public class GetPlayersinGameResponse implements Serializable {

	private static final long serialVersionUID = 2253662104358284066L;

	private ArrayList<Player> playerlist;
	private String gameInstanceID;
	
	public GetPlayersinGameResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public GetPlayersinGameResponse(ArrayList<Player> playerlist,
			String gameInstanceID) {
		super();
		this.playerlist = playerlist;
		this.gameInstanceID = gameInstanceID;
	}
	
	public ArrayList<Player> getPlayerlist() {
		return playerlist;
	}
	
	public void setPlayerlist(ArrayList<Player> playerlist) {
		this.playerlist = playerlist;
	}
	
	public String getGameInstanceID() {
		return gameInstanceID;
	}
	
	public void setGameInstanceID(String gameInstanceID) {
		this.gameInstanceID = gameInstanceID;
	}
	
	
	
	
}
