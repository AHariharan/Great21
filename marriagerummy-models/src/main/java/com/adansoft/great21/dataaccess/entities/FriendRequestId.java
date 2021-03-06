package com.adansoft.great21.dataaccess.entities;

// Generated May 13, 2015 3:59:03 PM by Hibernate Tools 3.4.0.CR1

/**
 * FriendRequestId generated by hbm2java
 */
public class FriendRequestId implements java.io.Serializable {

	private long friendRequestIdn;
	private long userId;

	public FriendRequestId() {
	}

	public FriendRequestId(long friendRequestIdn, long userId) {
		this.friendRequestIdn = friendRequestIdn;
		this.userId = userId;
	}

	public long getFriendRequestIdn() {
		return this.friendRequestIdn;
	}

	public void setFriendRequestIdn(long friendRequestIdn) {
		this.friendRequestIdn = friendRequestIdn;
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
		if (!(other instanceof FriendRequestId))
			return false;
		FriendRequestId castOther = (FriendRequestId) other;

		return (this.getFriendRequestIdn() == castOther.getFriendRequestIdn())
				&& (this.getUserId() == castOther.getUserId());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (int) this.getFriendRequestIdn();
		result = 37 * result + (int) this.getUserId();
		return result;
	}

}
