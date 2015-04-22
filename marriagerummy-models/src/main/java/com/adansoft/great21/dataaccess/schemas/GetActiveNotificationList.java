package com.adansoft.great21.dataaccess.schemas;

import java.io.Serializable;
import java.util.ArrayList;

public class GetActiveNotificationList implements Serializable {

	private static final long serialVersionUID = -241397304893786175L;
	private ArrayList<GetActiveNotificationResponse> notificationList;
	private String nickname;
	private String emailaddr;

	public GetActiveNotificationList() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GetActiveNotificationList(
			ArrayList<GetActiveNotificationResponse> notificationList,
			String nickname, String emailaddr) {
		super();
		this.notificationList = notificationList;
		this.nickname = nickname;
		this.emailaddr = emailaddr;
	}

	public ArrayList<GetActiveNotificationResponse> getNotificationList() {
		return notificationList;
	}

	public void setNotificationList(
			ArrayList<GetActiveNotificationResponse> notificationList) {
		this.notificationList = notificationList;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getEmailaddr() {
		return emailaddr;
	}

	public void setEmailaddr(String emailaddr) {
		this.emailaddr = emailaddr;
	}

}
