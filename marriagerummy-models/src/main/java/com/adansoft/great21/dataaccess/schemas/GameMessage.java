package com.adansoft.great21.dataaccess.schemas;

import java.io.Serializable;

public class GameMessage implements Serializable {

	private static final long serialVersionUID = 8113727909357704983L;
	private String from;
	private String[] to;
	private long[] to_userids;
	private String subject;
	private String messageContent;
	private int internal_messageid;
	private int internal_order;
	private String status;

	public GameMessage() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public GameMessage(String from, String[] to, long[] to_userids,
			String subject, String messageContent, int internal_messageid,
			int internal_order, String status) {
		super();
		this.from = from;
		this.to = to;
		this.to_userids = to_userids;
		this.subject = subject;
		this.messageContent = messageContent;
		this.internal_messageid = internal_messageid;
		this.internal_order = internal_order;
		this.status = status;
	}



	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String[] getTo() {
		return to;
	}

	public void setTo(String[] to) {
		this.to = to;
	}

	public long[] getTo_userids() {
		return to_userids;
	}

	public void setTo_userids(long[] to_userids) {
		this.to_userids = to_userids;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessageContent() {
		return messageContent;
	}

	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}

	public int getInternal_messageid() {
		return internal_messageid;
	}

	public void setInternal_messageid(int internal_messageid) {
		this.internal_messageid = internal_messageid;
	}

	public int getInternal_order() {
		return internal_order;
	}

	public void setInternal_order(int internal_order) {
		this.internal_order = internal_order;
	}



	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
