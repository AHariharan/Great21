package com.adansoft.great21.dataaccess.schemas;

import java.io.Serializable;
import java.util.ArrayList;

public class GetUserAchivementList implements Serializable {

	private static final long serialVersionUID = -3345313925276762578L;
	private String nickname;
	private ArrayList<GetUserAcheivementResponse> acheivementlist;
	
	public GetUserAchivementList() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GetUserAchivementList(String nickname,
			ArrayList<GetUserAcheivementResponse> acheivementlist) {
		super();
		this.nickname = nickname;
		this.acheivementlist = acheivementlist;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public ArrayList<GetUserAcheivementResponse> getAcheivementlist() {
		return acheivementlist;
	}

	public void setAcheivementlist(
			ArrayList<GetUserAcheivementResponse> acheivementlist) {
		this.acheivementlist = acheivementlist;
	}
	
	
	
	
 	
	
	

}
