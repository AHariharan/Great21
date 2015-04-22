package com.adansoft.great21.dataaccess.schemas;

import java.io.Serializable;

public class GetNotificationCountResponse implements Serializable{

	private static final long serialVersionUID = -1515135310190697123L;
	private int friendRequestCount;
	private int gameInviteCount;
	private int notificationCount;
	private String nickname;
	private String emailaddress;
	
	
	
	public GetNotificationCountResponse() {
		super();
		// TODO Auto-generated constructor stub
	}



	public GetNotificationCountResponse(int friendRequestCount,
			int gameInviteCount, int notificationCount, String nickname,
			String emailaddress) {
		super();
		this.friendRequestCount = friendRequestCount;
		this.gameInviteCount = gameInviteCount;
		this.notificationCount = notificationCount;
		this.nickname = nickname;
		this.emailaddress = emailaddress;
	}



	public int getFriendRequestCount() {
		return friendRequestCount;
	}



	public void setFriendRequestCount(int friendRequestCount) {
		this.friendRequestCount = friendRequestCount;
	}



	public int getGameInviteCount() {
		return gameInviteCount;
	}



	public void setGameInviteCount(int gameInviteCount) {
		this.gameInviteCount = gameInviteCount;
	}



	public int getNotificationCount() {
		return notificationCount;
	}



	public void setNotificationCount(int notificationCount) {
		this.notificationCount = notificationCount;
	}



	public String getNickname() {
		return nickname;
	}



	public void setNickname(String nickname) {
		this.nickname = nickname;
	}



	public String getEmailaddress() {
		return emailaddress;
	}



	public void setEmailaddress(String emailaddress) {
		this.emailaddress = emailaddress;
	}
	
	
	
	

}
