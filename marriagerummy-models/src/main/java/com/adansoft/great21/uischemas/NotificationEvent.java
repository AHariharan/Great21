package com.adansoft.great21.uischemas;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class NotificationEvent implements Serializable {

	private static final long serialVersionUID = -7860044125384329133L;

	private String notificationType;
	private String notificationSource;
	private Object notificationObject;
	private String notifiedBy;
	
	public NotificationEvent() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NotificationEvent(String notificationType,
			String notificationSource, Object notificationObject,String notifiedBy) {
		super();
		this.notificationType = notificationType;
		this.notificationSource = notificationSource;
		this.notificationObject = notificationObject;
		this.notifiedBy = notifiedBy;
	}

	public String getNotificationType() {
		return notificationType;
	}

	public void setNotificationType(String notificationType) {
		this.notificationType = notificationType;
	}

	public String getNotificationSource() {
		return notificationSource;
	}

	public void setNotificationSource(String notificationSource) {
		this.notificationSource = notificationSource;
	}

	public Object getNotificationObject() {
		return notificationObject;
	}

	public void setNotificationObject(Object notificationObject) {
		this.notificationObject = notificationObject;
	}

	public String getNotifiedBy() {
		return notifiedBy;
	}

	public void setNotifiedBy(String notifiedBy) {
		this.notifiedBy = notifiedBy;
	}
	
	
	
}
