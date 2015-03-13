package com.adansoft.great21.dataaccess.schemas;

import java.io.Serializable;

public class GetProfileInformationResponse implements Serializable{

	private static final long serialVersionUID = 7241589143420346553L;
	
	private String firstname;
	private String lastname;
	private String emailaddress;
	private String country;
	private String nickname;
	public GetProfileInformationResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public GetProfileInformationResponse(String firstname, String lastname,
			String emailaddress, String country, String nickname) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.emailaddress = emailaddress;
		this.country = country;
		this.nickname = nickname;
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
	public String getEmailaddress() {
		return emailaddress;
	}
	public void setEmailaddress(String emailaddress) {
		this.emailaddress = emailaddress;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	
	

}
