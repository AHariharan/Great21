package com.adansoft.great21.dataaccess.schemas;

import java.io.Serializable;
import java.util.Date;

public class GetActiveNotificationResponse implements Serializable {

	private static final long serialVersionUID = -5837666547277237765L;
	private String NotificationType;
	private String NotificationDesc;
	private String NotifiedBy;
	private Date createdDate;

	public GetActiveNotificationResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GetActiveNotificationResponse(String notificationType,
			String notificationDesc, String notifiedBy, Date createdDate) {
		super();
		NotificationType = notificationType;
		NotificationDesc = notificationDesc;
		NotifiedBy = notifiedBy;
		this.createdDate = createdDate;
	}

	public String getNotificationType() {
		return NotificationType;
	}

	public void setNotificationType(String notificationType) {
		NotificationType = notificationType;
	}

	public String getNotifiedBy() {
		return NotifiedBy;
	}

	public void setNotifiedBy(String notifiedBy) {
		NotifiedBy = notifiedBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getNotificationDesc() {
		return NotificationDesc;
	}

	public void setNotificationDesc(String notificationDesc) {
		NotificationDesc = notificationDesc;
	}
	
	

}
