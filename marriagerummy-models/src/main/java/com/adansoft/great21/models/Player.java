package com.adansoft.great21.models;


import java.io.Serializable;
import java.util.ArrayList;

import com.adansoft.great21.gameindexers.deserializers.PlayerDeserializer;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;


@JsonTypeInfo(use=JsonTypeInfo.Id.NAME, include=JsonTypeInfo.As.WRAPPER_OBJECT, property="type")
@JsonSubTypes({
      @JsonSubTypes.Type(value=HumanPlayer.class, name="HumanPlayer")
  }) 
public interface Player extends Serializable {

	public static final String PLAYER_TYPE_AI = "AI";
	public static final String PLAYER_TYPE_HUMAN = "HUMAN";
	
	public static final String PLAYER_STATUS_ELIMINATED = "ELIMINATED";
	public static final String PLAYER_STATUS_INGAMEROUND = "INGAMEROUND";
	public static final String PLAYER_STATUS_INGAME = "INGAME";
	public static final String PLAYER_STATUS_WINGAME = "WINNER";
	
	public static final String PLAYER_ROLE_HOST = "HOST";
	public static final String PLAYER_ROLE_GUEST = "GUEST";
	
	public static final String PLAYER_PLAY_STATUS_WAIT = "WAIT";
	public static final String PLAYER_PLAY_STATUS_TURN = "TURN";
	
	public void showCards();
	public void assignCard(Card card);
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
	
	/*gets player position */
	public int getPlayerPosition();
	
	/*get check if he is host */
	public boolean isHost();
	
	public String getPlayerInstanceID();
	
	public ArrayList<Card> getPlayerCards();
	
	public void turnPlayed(Card pickupcard,Card droppedcard);
	
	public void setJokerKnown(boolean result);
	
	public void resetCards();
	
	public double getCurrentCash();
	
	public long getUserID();
	
	public void setCurrentCash(double cash);
	
	
	
}
