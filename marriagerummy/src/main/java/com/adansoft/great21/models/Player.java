package com.adansoft.great21.models;



public interface Player {

	public static final String PLAYER_TYPE_AI = "AI";
	public static final String PLAYER_TYPE_HUMAN = "HUMAN";
	
	public static final String PLAYER_STATUS_ELIMINATED = "ELIMINATED";
	public static final String PLAYER_STATUS_INGAMEROUND = "INGAMEROUND";
	
	public static final String PLAYER_PLAY_STATUS_WAIT = "WAIT";
	public static final String PLAYER_PLAY_STATUS_TURN = "TURN";
	
	public void showCards();
	public void assignCard(Card<?> card);
	public String getNickName();
	public void autoArrangeCards();
	public boolean isJokerKnown();
	public String getPlayerType();
	public void playTurn();
	
	public int getCurrentPoints();
	
	/* gets the player's status to show he is in game or eliminated */
	public String getPlayerStatus();
	
	/* sets the player's status to show he is in game or eliminated */
	public void setPlayerStatus(String Status);
	
	/* gets the player's status who is in game with round status */
	public String getPlayerRoundStatus();
	
	
}
