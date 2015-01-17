package com.adansoft.great21.models;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use=JsonTypeInfo.Id.NAME, include=JsonTypeInfo.As.WRAPPER_OBJECT, property="type")
@JsonSubTypes({
      @JsonSubTypes.Type(value=HeartCard.class, name="HeartCard"),
      @JsonSubTypes.Type(value=ClubCard.class, name="ClubCard"),
      @JsonSubTypes.Type(value=DiamondCard.class, name="DiamondCard"),
      @JsonSubTypes.Type(value=SpadeCard.class, name="SpadeCard")
  }) 
public interface Card extends Serializable{
	
	public static final String FLOWER_SPADE = "SPADE";
	public static final String FLOWER_HEART = "HEART";
	public static final String FLOWER_CLUBS = "CLUB";
	public static final String FLOWER_DIAMOND = "DIAMOND";
	
	public static final String STATUS_UNASSIGNED = "UNASSIGNED";
	public static final String STATUS_ASSIGNED = "ASSIGNED";
	public static final String STATUS_JOKER = "JOKER";
	public static final String STATUS_OPEN = "OPEN";
	public static final String STATUS_DEAD = "DEAD";
	public static final String STATUS_CLOSE = "CLOSE";
	
	public static final String DISPLAY_ONE = "A";
	public static final String DISPLAY_TWO = "2";
	public static final String DISPLAY_THREE = "3";
	public static final String DISPLAY_FOUR = "4";
	public static final String DISPLAY_FIVE = "5";
	public static final String DISPLAY_SIX = "6";
	public static final String DISPLAY_SEVEN = "7";
	public static final String DISPLAY_EIGHT = "8";
	public static final String DISPLAY_NINE = "9";
	public static final String DISPLAY_TEN = "10";
	public static final String DISPLAY_ELEVEN = "J";
	public static final String DISPLAY_TWELVE = "Q";
	public static final String DISPLAY_THIRTEEN = "K";
	
	public String getFlower();
	
	public String getDisplayValue();
	
	public String getStatus();
	
	public int getCountValue();
	
	public int getDecKID();
	
	public String getCardInstanceID();
	
	public void setStatus(String status);	
	
	public int getInstrinsicValue();

	int compareTo(Card inputcard);

	int compareTo(Object input);
	
}
