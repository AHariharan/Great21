package com.adansoft.great21.bot;

import java.util.ArrayList;

import com.adansoft.great21.models.Card;

public class ProximityResult {

	private ArrayList<Card> comparedCardsList;
	private int distance;

	public ArrayList<Card> getComparedCardsList() {
		return comparedCardsList;
	}

	public void setComparedCardsList(ArrayList<Card> comparedCardsList) {
		this.comparedCardsList = comparedCardsList;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public ProximityResult(ArrayList<Card> comparedCardsList, int distance) {
		super();
		this.comparedCardsList = comparedCardsList;
		this.distance = distance;
	}

	public ProximityResult() {
		super();
		// TODO Auto-generated constructor stub
	}

}
