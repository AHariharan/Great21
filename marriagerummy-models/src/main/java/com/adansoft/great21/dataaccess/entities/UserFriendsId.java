package com.adansoft.great21.dataaccess.entities;

// Generated May 13, 2015 3:59:03 PM by Hibernate Tools 3.4.0.CR1

/**
 * UserFriendsId generated by hbm2java
 */
public class UserFriendsId implements java.io.Serializable {

	private long linkId;
	private long userId;

	public UserFriendsId() {
	}

	public UserFriendsId(long linkId, long userId) {
		this.linkId = linkId;
		this.userId = userId;
	}

	public long getLinkId() {
		return this.linkId;
	}

	public void setLinkId(long linkId) {
		this.linkId = linkId;
	}

	public long getUserId() {
		return this.userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof UserFriendsId))
			return false;
		UserFriendsId castOther = (UserFriendsId) other;

		return (this.getLinkId() == castOther.getLinkId())
				&& (this.getUserId() == castOther.getUserId());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (int) this.getLinkId();
		result = 37 * result + (int) this.getUserId();
		return result;
	}

}
