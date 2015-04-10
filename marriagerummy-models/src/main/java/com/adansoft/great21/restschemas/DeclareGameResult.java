package com.adansoft.great21.restschemas;

import java.io.Serializable;
import java.util.HashMap;

import com.adansoft.great21.uischemas.UICard;

public class DeclareGameResult implements Serializable{

	private static final long serialVersionUID = -5941088049325508180L;
	private boolean valid = true;
	private String message;
	private UICard joker;
	private HashMap<String,String> groupresults;
	
	public DeclareGameResult() {
		super();
		groupresults = new HashMap<String, String>();
	}

    

	public DeclareGameResult(boolean valid, String message, UICard joker,
			HashMap<String, String> groupresults) {
		super();
		this.valid = valid;
		this.message = message;
		this.joker = joker;
		this.groupresults = groupresults;
	}



	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public HashMap<String, String> getGroupresults() {
		return groupresults;
	}

	public void setGroupresults(HashMap<String, String> groupresults) {
		this.groupresults = groupresults;
	}



	public UICard getJoker() {
		return joker;
	}



	public void setJoker(UICard joker) {
		this.joker = joker;
	}
	
	
	
}
