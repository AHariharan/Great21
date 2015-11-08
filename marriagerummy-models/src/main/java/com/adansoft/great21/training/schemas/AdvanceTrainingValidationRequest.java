package com.adansoft.great21.training.schemas;

import java.io.Serializable;

public class AdvanceTrainingValidationRequest implements Serializable{


	private static final long serialVersionUID = 2978606269733942686L;

	private String validationTypes;
	
	private String cardInstanceID[];

	public AdvanceTrainingValidationRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AdvanceTrainingValidationRequest(String validationTypes, String[] cardInstanceID) {
		super();
		this.validationTypes = validationTypes;
		this.cardInstanceID = cardInstanceID;
	}

	public String getValidationTypes() {
		return validationTypes;
	}

	public void setValidationTypes(String validationTypes) {
		this.validationTypes = validationTypes;
	}

	public String[] getCardInstanceID() {
		return cardInstanceID;
	}

	public void setCardInstanceID(String[] cardInstanceID) {
		this.cardInstanceID = cardInstanceID;
	}
	
	
	
}
