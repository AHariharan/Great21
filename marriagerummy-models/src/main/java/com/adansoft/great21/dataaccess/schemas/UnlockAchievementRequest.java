package com.adansoft.great21.dataaccess.schemas;

import java.io.Serializable;

public class UnlockAchievementRequest implements Serializable {

	private static final long serialVersionUID = -299206890619865892L;
	private String nickname;
	private int achievementid;
	private String status;

	public UnlockAchievementRequest() {
		super();

	}

	public UnlockAchievementRequest(String nickname, int achievementid,
			String status) {
		super();
		this.nickname = nickname;
		this.achievementid = achievementid;
		this.status = status;
	}

	
	
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getAchievementid() {
		return achievementid;
	}

	public void setAchievementid(int achievementid) {
		this.achievementid = achievementid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
}
