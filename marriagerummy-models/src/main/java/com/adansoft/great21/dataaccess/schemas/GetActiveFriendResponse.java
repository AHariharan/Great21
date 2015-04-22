package com.adansoft.great21.dataaccess.schemas;

import java.io.Serializable;
import java.util.Date;

public class GetActiveFriendResponse implements Serializable {

	private static final long serialVersionUID = -6584849092829570419L;
	private String requestedBY;
	private Date requestedDate;

	public GetActiveFriendResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GetActiveFriendResponse(String requestedBY, Date requestedDate) {
		super();
		this.requestedBY = requestedBY;
		this.requestedDate = requestedDate;
	}

	public String getRequestedBY() {
		return requestedBY;
	}

	public void setRequestedBY(String requestedBY) {
		this.requestedBY = requestedBY;
	}

	public Date getRequestedDate() {
		return requestedDate;
	}

	public void setRequestedDate(Date requestedDate) {
		this.requestedDate = requestedDate;
	}
}
