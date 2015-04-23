package com.adansoft.great21.dataaccess.schemas;

import java.io.Serializable;
import java.util.ArrayList;

public class GetFriendListResponse implements Serializable {

	private static final long serialVersionUID = -8171774366221597201L;
	private String mynickname;
	private ArrayList<FriendResponse> friendlist;

	public String getMynickname() {
		return mynickname;
	}

	public void setMynickname(String mynickname) {
		this.mynickname = mynickname;
	}

	public ArrayList<FriendResponse> getFriendlist() {
		return friendlist;
	}

	public void setFriendlist(ArrayList<FriendResponse> friendlist) {
		this.friendlist = friendlist;
	}

	public GetFriendListResponse(String mynickname,
			ArrayList<FriendResponse> friendlist) {
		super();
		this.mynickname = mynickname;
		this.friendlist = friendlist;
	}

	public GetFriendListResponse() {
		super();
	}

}
