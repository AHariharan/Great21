package com.adansoft.great21.models;



public interface Player {

	public static final String PLAYER_TYPE_AI = "AI";
	public static final String PLAYER_TYPE_HUMAN = "HUMAN";
	
	public void showCards();
	public void assignCard(Card<?> card);
	public String getNickName();
	public void autoArrangeCards();
	public boolean isJokerKnown();
	public String getPlayerType();
	public void playTurn();
	
	
}
