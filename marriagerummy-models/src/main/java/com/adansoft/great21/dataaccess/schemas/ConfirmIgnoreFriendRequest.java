package com.adansoft.great21.dataaccess.schemas;

import java.io.Serializable;

public class ConfirmIgnoreFriendRequest implements Serializable {

	private static final long serialVersionUID = 812337461453650395L;
	private long myuserid;
	private String requestorNickName;
	private String status;

	public ConfirmIgnoreFriendRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ConfirmIgnoreFriendRequest(long myuserid, String requestorNickName,
			String status) {
		super();
		this.myuserid = myuserid;
		this.requestorNickName = requestorNickName;
		this.status = status;
	}

	public long getMyuserid() {
		return myuserid;
	}

	public void setMyuserid(long myuserid) {
		this.myuserid = myuserid;
	}

	public String getRequestorNickName() {
		return requestorNickName;
	}

	public void setRequestorNickName(String requestorNickName) {
		this.requestorNickName = requestorNickName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
