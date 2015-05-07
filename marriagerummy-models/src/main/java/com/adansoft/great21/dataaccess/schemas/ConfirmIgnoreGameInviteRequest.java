package com.adansoft.great21.dataaccess.schemas;

import java.io.Serializable;

public class ConfirmIgnoreGameInviteRequest implements Serializable{

	private static final long serialVersionUID = -3621359940416701273L;
	
	private String gameInstanceID;
	private String requestorNickName;
	private long userid;
	private String gameType;
	public ConfirmIgnoreGameInviteRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ConfirmIgnoreGameInviteRequest(String gameInstanceID,
			String requestorNickName, long userid, String gameType) {
		super();
		this.gameInstanceID = gameInstanceID;
		this.requestorNickName = requestorNickName;
		this.userid = userid;
		this.gameType = gameType;
	}
	public String getGameInstanceID() {
		return gameInstanceID;
	}
	public void setGameInstanceID(String gameInstanceID) {
		this.gameInstanceID = gameInstanceID;
	}
	public String getRequestorNickName() {
		return requestorNickName;
	}
	public void setRequestorNickName(String requestorNickName) {
		this.requestorNickName = requestorNickName;
	}
	public long getUserid() {
		return userid;
	}
	public void setUserid(long userid) {
		this.userid = userid;
	}
	public String getGameType() {
		return gameType;
	}
	public void setGameType(String gameType) {
		this.gameType = gameType;
	}
	
	

	
	
}
