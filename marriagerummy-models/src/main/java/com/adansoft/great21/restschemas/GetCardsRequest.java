package com.adansoft.great21.restschemas;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GetCardsRequest implements Serializable {

	private static final long serialVersionUID = -1639883819559458395L;
	private String gameInstanceID;
	private String nickName;
	private String lobbyName;
	private String gameType;

	
	
	public GetCardsRequest() {
		super();
		}



	public GetCardsRequest(String gameInstanceID, String nickName,
			String lobbyName , String gameType) {
		super();
		this.gameInstanceID = gameInstanceID;
		this.nickName = nickName;
		this.lobbyName = lobbyName;
		this.gameType = gameType;
	}



	public String getGameInstanceID() {
		return gameInstanceID;
	}



	public void setGameInstanceID(String gameInstanceID) {
		this.gameInstanceID = gameInstanceID;
	}



	public String getNickName() {
		return nickName;
	}



	public void setNickName(String nickName) {
		this.nickName = nickName;
	}



	public String getLobbyName() {
		return lobbyName;
	}



	public void setLobbyName(String lobbyName) {
		this.lobbyName = lobbyName;
	}



	public String getGameType() {
		return gameType;
	}



	public void setGameType(String gameType) {
		this.gameType = gameType;
	}
	
	

}
