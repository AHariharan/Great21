package com.adansoft.great21.ulitity;

public class PrelimenaryCheckResult {

	private boolean isDuplicate;
	private boolean isJQKPresent;
	private boolean isAPresent;
	
	public PrelimenaryCheckResult()
	{
		isDuplicate = false;
		isJQKPresent = false;
		isAPresent = false;
	}

	public boolean isDuplicate() {
		return isDuplicate;
	}

	public void setDuplicate(boolean isDuplicate) {
		this.isDuplicate = isDuplicate;
	}

	public boolean isJQKPresent() {
		return isJQKPresent;
	}

	public void setJQKPresent(boolean isJQKPresent) {
		this.isJQKPresent = isJQKPresent;
	}

	public boolean isAPresent() {
		return isAPresent;
	}

	public void setAPresent(boolean isAPresent) {
		this.isAPresent = isAPresent;
	}
	
	
	
}
