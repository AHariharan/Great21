package com.adansoft.great21.dataaccess.schemas;

import java.io.Serializable;

public class GetGameMessageRequest implements Serializable {

	private static final long serialVersionUID = -8919085130336676550L;
	private long userid;
	private int noofrecentmessages = 100;

	public GetGameMessageRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GetGameMessageRequest(long userid, int noofrecentmessages) {
		super();
		this.userid = userid;
		this.noofrecentmessages = noofrecentmessages;
	}

	public long getUserid() {
		return userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
	}

	public int getNoofrecentmessages() {
		return noofrecentmessages;
	}

	public void setNoofrecentmessages(int noofrecentmessages) {
		this.noofrecentmessages = noofrecentmessages;
	}

}
