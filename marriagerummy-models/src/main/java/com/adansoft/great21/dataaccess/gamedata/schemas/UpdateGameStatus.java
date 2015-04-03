package com.adansoft.great21.dataaccess.gamedata.schemas;

import java.io.Serializable;

public class UpdateGameStatus implements Serializable {

	private static final long serialVersionUID = 53833204638316423L;
	private String gameInstanceID;
	private int numofplayers;
	private long player1idn;
	private long player2idn;
	private long player3idn;
	private long player4idn;
	private long player5idn;
	private long player6idn;
	private long player7idn;
	private long player8idn;
	
	public UpdateGameStatus() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UpdateGameStatus(String gameInstanceID, int numofplayers,
			long player1idn, long player2idn, long player3idn, long player4idn,
			long player5idn, long player6idn, long player7idn, long player8idn) {
		super();
		this.gameInstanceID = gameInstanceID;
		this.numofplayers = numofplayers;
		this.player1idn = player1idn;
		this.player2idn = player2idn;
		this.player3idn = player3idn;
		this.player4idn = player4idn;
		this.player5idn = player5idn;
		this.player6idn = player6idn;
		this.player7idn = player7idn;
		this.player8idn = player8idn;
	}

	public String getGameInstanceID() {
		return gameInstanceID;
	}

	public void setGameInstanceID(String gameInstanceID) {
		this.gameInstanceID = gameInstanceID;
	}

	public int getNumofplayers() {
		return numofplayers;
	}

	public void setNumofplayers(int numofplayers) {
		this.numofplayers = numofplayers;
	}

	public long getPlayer1idn() {
		return player1idn;
	}

	public void setPlayer1idn(long player1idn) {
		this.player1idn = player1idn;
	}

	public long getPlayer2idn() {
		return player2idn;
	}

	public void setPlayer2idn(long player2idn) {
		this.player2idn = player2idn;
	}

	public long getPlayer3idn() {
		return player3idn;
	}

	public void setPlayer3idn(long player3idn) {
		this.player3idn = player3idn;
	}

	public long getPlayer4idn() {
		return player4idn;
	}

	public void setPlayer4idn(long player4idn) {
		this.player4idn = player4idn;
	}

	public long getPlayer5idn() {
		return player5idn;
	}

	public void setPlayer5idn(long player5idn) {
		this.player5idn = player5idn;
	}

	public long getPlayer6idn() {
		return player6idn;
	}

	public void setPlayer6idn(long player6idn) {
		this.player6idn = player6idn;
	}

	public long getPlayer7idn() {
		return player7idn;
	}

	public void setPlayer7idn(long player7idn) {
		this.player7idn = player7idn;
	}

	public long getPlayer8idn() {
		return player8idn;
	}

	public void setPlayer8idn(long player8idn) {
		this.player8idn = player8idn;
	}
	
	
	
}
