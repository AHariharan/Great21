package com.adansoft.great21.bot;

import java.util.ArrayList;
import java.util.HashMap;

import com.adansoft.great21.models.Card;

public class GroupCardSet {

	private HashMap<String,CardSetNode> groupedCardMap;
	private double score;
	private boolean realSequencePresent;

	public GroupCardSet() {
		super();
		groupedCardMap = new HashMap<String, CardSetNode>();
	}

	public GroupCardSet(HashMap<String, CardSetNode> groupedCardMap) {
		super();
		this.groupedCardMap = groupedCardMap;
	}

	public HashMap<String, CardSetNode> getGroupedCardMap() {
		return groupedCardMap;
	}

	public void setGroupedCardMap(HashMap<String, CardSetNode> groupedCardMap) {
		this.groupedCardMap = groupedCardMap;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}
	
	public boolean verifyGroup()
	{
		ArrayList<Card> totalCardsinGroup = new ArrayList<Card>();
		for(String key : this.getGroupedCardMap().keySet())
		{
			totalCardsinGroup.addAll(this.getGroupedCardMap().get(key).getCardList());
		}
		if(totalCardsinGroup.size() != 14)
			return false;
		else
			return true;
	}

	public boolean isRealSequencePresent() {
		return realSequencePresent;
	}

	public void setRealSequencePresent(boolean realSequencePresent) {
		this.realSequencePresent = realSequencePresent;
	}
	
	
	
}
