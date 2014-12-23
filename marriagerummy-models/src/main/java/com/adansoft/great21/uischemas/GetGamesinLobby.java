package com.adansoft.great21.uischemas;

import java.io.Serializable;

public class GetGamesinLobby implements Serializable{
	private static final long serialVersionUID = 3946904566022672427L;

	private String gameInstanceId;
	private String id;
	private String type;
	private String host;
	private String desc;
	private String playedbet;
	private int players;
	private String gameStatus;
	private String action;
	
	public GetGamesinLobby() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GetGamesinLobby(String gameInstanceId, String id, String type,
			String host, String playedbet, int players, String gameStatus,String desc) {
		super();
		this.gameInstanceId = gameInstanceId;
		this.id = id;
		this.type = type;
		this.host = host;
		this.playedbet = playedbet;
		this.players = players;
		this.gameStatus = gameStatus;
		this.desc = desc;
	}

	public String getGameInstanceId() {
		return gameInstanceId;
	}

	public void setGameInstanceId(String gameInstanceId) {
		this.gameInstanceId = gameInstanceId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPlayedbet() {
		return playedbet;
	}

	public void setPlayedbet(String playedbet) {
		this.playedbet = playedbet;
	}

	public int getPlayers() {
		return players;
	}

	public void setPlayers(int players) {
		this.players = players;
	}

	public String getGameStatus() {
		return gameStatus;
	}

	public void setGameStatus(String gameStatus) {
		this.gameStatus = gameStatus;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
	
   
	
}
