package com.adansoft.great21.restschemas;

import java.io.Serializable;

import com.adansoft.great21.uischemas.UICard;

public class DropCardFromHandRequest implements Serializable {

	private static final long serialVersionUID = 2321818624613390305L;
	private UICard card;
	
	public DropCardFromHandRequest() {
		super();
	}

	public DropCardFromHandRequest(UICard card) {
		super();
		this.card = card;
	}

	public UICard getCard() {
		return card;
	}

	public void setCard(UICard card) {
		this.card = card;
	}
	
	

}
