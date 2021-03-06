package com.adansoft.great21.dataaccess.entities;

// Generated May 13, 2015 3:59:03 PM by Hibernate Tools 3.4.0.CR1

import java.util.Date;

/**
 * UserMessages generated by hbm2java
 */
public class UserMessages implements java.io.Serializable {

	private UserMessagesId id;
	private String msgStatus;
	private String msgFrom;
	private byte[] msgContent;
	private String subject;
	private Date createdDate;

	public UserMessages() {
	}

	public UserMessages(UserMessagesId id) {
		this.id = id;
	}

	public UserMessages(UserMessagesId id, String msgStatus, String msgFrom,
			byte[] msgContent, String subject, Date createdDate) {
		this.id = id;
		this.msgStatus = msgStatus;
		this.msgFrom = msgFrom;
		this.msgContent = msgContent;
		this.subject = subject;
		this.createdDate = createdDate;
	}

	public UserMessagesId getId() {
		return this.id;
	}

	public void setId(UserMessagesId id) {
		this.id = id;
	}

	public String getMsgStatus() {
		return this.msgStatus;
	}

	public void setMsgStatus(String msgStatus) {
		this.msgStatus = msgStatus;
	}

	public String getMsgFrom() {
		return this.msgFrom;
	}

	public void setMsgFrom(String msgFrom) {
		this.msgFrom = msgFrom;
	}

	public byte[] getMsgContent() {
		return this.msgContent;
	}

	public void setMsgContent(byte[] msgContent) {
		this.msgContent = msgContent;
	}

	public String getSubject() {
		return this.subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

}
