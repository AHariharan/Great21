package com.adansoft.great21.dataaccess.entities;

// Generated May 13, 2015 3:59:03 PM by Hibernate Tools 3.4.0.CR1

/**
 * GameRoundResultsId generated by hbm2java
 */
public class GameRoundResultsId implements java.io.Serializable {

	private String gameRoundId;
	private String gameInstanceId;
	private long userId;

	public GameRoundResultsId() {
	}

	public GameRoundResultsId(String gameRoundId, String gameInstanceId,
			long userId) {
		this.gameRoundId = gameRoundId;
		this.gameInstanceId = gameInstanceId;
		this.userId = userId;
	}

	public String getGameRoundId() {
		return this.gameRoundId;
	}

	public void setGameRoundId(String gameRoundId) {
		this.gameRoundId = gameRoundId;
	}

	public String getGameInstanceId() {
		return this.gameInstanceId;
	}

	public void setGameInstanceId(String gameInstanceId) {
		this.gameInstanceId = gameInstanceId;
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
		if (!(other instanceof GameRoundResultsId))
			return false;
		GameRoundResultsId castOther = (GameRoundResultsId) other;

		return ((this.getGameRoundId() == castOther.getGameRoundId()) || (this
				.getGameRoundId() != null && castOther.getGameRoundId() != null && this
				.getGameRoundId().equals(castOther.getGameRoundId())))
				&& ((this.getGameInstanceId() == castOther.getGameInstanceId()) || (this
						.getGameInstanceId() != null
						&& castOther.getGameInstanceId() != null && this
						.getGameInstanceId().equals(
								castOther.getGameInstanceId())))
				&& (this.getUserId() == castOther.getUserId());
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getGameRoundId() == null ? 0 : this.getGameRoundId()
						.hashCode());
		result = 37
				* result
				+ (getGameInstanceId() == null ? 0 : this.getGameInstanceId()
						.hashCode());
		result = 37 * result + (int) this.getUserId();
		return result;
	}

}
