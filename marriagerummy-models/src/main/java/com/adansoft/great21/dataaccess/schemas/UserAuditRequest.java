package com.adansoft.great21.dataaccess.schemas;

import java.io.Serializable;

public class UserAuditRequest implements Serializable {

	private static final long serialVersionUID = -1073744572242816741L;
	private long userid;
	private String devicetype;
	
	public UserAuditRequest() {
		super();
	}

	public UserAuditRequest(long userid, String devicetype) {
		super();
		this.userid = userid;
		this.devicetype = devicetype;
	}

	public long getUserid() {
		return userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
	}

	public String getDevicetype() {
		return devicetype;
	}

	public void setDevicetype(String devicetype) {
		this.devicetype = devicetype;
	}
	
}
