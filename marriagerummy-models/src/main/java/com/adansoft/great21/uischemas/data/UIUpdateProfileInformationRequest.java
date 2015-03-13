package com.adansoft.great21.uischemas.data;

import java.io.Serializable;

public class UIUpdateProfileInformationRequest implements Serializable {

	private static final long serialVersionUID = 4163219441540923897L;
	
	private String firstname;
	private String lastname;
	private String country;
	
	public UIUpdateProfileInformationRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UIUpdateProfileInformationRequest(String firstname, String lastname,
			String country) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.country = country;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	
	

	
}
