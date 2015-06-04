package com.adansoft.great21.bot;

import java.util.ArrayList;
import java.util.HashMap;

import com.adansoft.great21.models.Card;

public class GroupCardSet {

	private HashMap<String,ArrayList<Card>> groupedCardMap;

	public GroupCardSet() {
		super();
		groupedCardMap = new HashMap<String, ArrayList<Card>>();
	}

	public GroupCardSet(HashMap<String, ArrayList<Card>> groupedCardMap) {
		super();
		this.groupedCardMap = groupedCardMap;
	}

	public HashMap<String, ArrayList<Card>> getGroupedCardMap() {
		return groupedCardMap;
	}

	public void setGroupedCardMap(HashMap<String, ArrayList<Card>> groupedCardMap) {
		this.groupedCardMap = groupedCardMap;
	}
	
	
	
	
}
