package com.adansoft.great21.dataaccess.entities;

// Generated May 13, 2015 3:59:03 PM by Hibernate Tools 3.4.0.CR1

/**
 * UserAccountsId generated by hbm2java
 */
public class UserAccountsId implements java.io.Serializable {

	private long userId;
	private String emailAddr;

	public UserAccountsId() {
	}

	public UserAccountsId(long userId, String emailAddr) {
		this.userId = userId;
		this.emailAddr = emailAddr;
	}

	public long getUserId() {
		return this.userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getEmailAddr() {
		return this.emailAddr;
	}

	public void setEmailAddr(String emailAddr) {
		this.emailAddr = emailAddr;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof UserAccountsId))
			return false;
		UserAccountsId castOther = (UserAccountsId) other;

		return (this.getUserId() == castOther.getUserId())
				&& ((this.getEmailAddr() == castOther.getEmailAddr()) || (this
						.getEmailAddr() != null
						&& castOther.getEmailAddr() != null && this
						.getEmailAddr().equals(castOther.getEmailAddr())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (int) this.getUserId();
		result = 37 * result
				+ (getEmailAddr() == null ? 0 : this.getEmailAddr().hashCode());
		return result;
	}

}
