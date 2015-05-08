package com.adansoft.great21.dataaccess.schemas;

import java.io.Serializable;
import java.util.Date;

public class GetUserAcheivementResponse implements Serializable{

	private static final long serialVersionUID = -3681692096113583077L;
	private String divid;
	private String AcheivementAbbr;
	private String AcheivementDesc;
	private Date unlockedDate;
	
	public GetUserAcheivementResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GetUserAcheivementResponse(String divid, String acheivementAbbr,
			String acheivementDesc, Date unlockedDate) {
		super();
		this.divid = divid;
		AcheivementAbbr = acheivementAbbr;
		AcheivementDesc = acheivementDesc;
		this.unlockedDate = unlockedDate;
	}

	public String getDivid() {
		return divid;
	}

	public void setDivid(String divid) {
		this.divid = divid;
	}

	public String getAcheivementAbbr() {
		return AcheivementAbbr;
	}

	public void setAcheivementAbbr(String acheivementAbbr) {
		AcheivementAbbr = acheivementAbbr;
	}

	public String getAcheivementDesc() {
		return AcheivementDesc;
	}

	public void setAcheivementDesc(String acheivementDesc) {
		AcheivementDesc = acheivementDesc;
	}

	public Date getUnlockedDate() {
		return unlockedDate;
	}

	public void setUnlockedDate(Date unlockedDate) {
		this.unlockedDate = unlockedDate;
	}
}
