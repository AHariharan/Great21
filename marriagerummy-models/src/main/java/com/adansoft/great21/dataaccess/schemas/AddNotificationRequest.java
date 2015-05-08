package com.adansoft.great21.dataaccess.schemas;

import java.io.Serializable;

public class AddNotificationRequest implements Serializable {

	private static final long serialVersionUID = -3681003458652954545L;
	private String notificationfor;
	private String notifiedby;
	private String notificationType;
	private String notificationDesc;
	
	public AddNotificationRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AddNotificationRequest(String notificationfor, String notifiedby,
			String notificationType, String notificationDesc) {
		super();
		this.notificationfor = notificationfor;
		this.notifiedby = notifiedby;
		this.notificationType = notificationType;
		this.notificationDesc = notificationDesc;
	}

	public String getNotificationfor() {
		return notificationfor;
	}

	public void setNotificationfor(String notificationfor) {
		this.notificationfor = notificationfor;
	}

	public String getNotifiedby() {
		return notifiedby;
	}

	public void setNotifiedby(String notifiedby) {
		this.notifiedby = notifiedby;
	}

	public String getNotificationType() {
		return notificationType;
	}

	public void setNotificationType(String notificationType) {
		this.notificationType = notificationType;
	}

	public String getNotificationDesc() {
		return notificationDesc;
	}

	public void setNotificationDesc(String notificationDesc) {
		this.notificationDesc = notificationDesc;
	}
	
	

}
