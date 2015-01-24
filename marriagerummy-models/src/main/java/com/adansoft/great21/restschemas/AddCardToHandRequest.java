package com.adansoft.great21.restschemas;

import java.io.Serializable;

import com.adansoft.great21.uischemas.UICard;

public class AddCardToHandRequest implements Serializable {

	private static final long serialVersionUID = 8146550784443169562L;
	private UICard card;
	
	public AddCardToHandRequest() {
		super();
	}

	public AddCardToHandRequest(UICard card) {
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
