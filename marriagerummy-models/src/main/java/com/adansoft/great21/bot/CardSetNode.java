package com.adansoft.great21.bot;

import java.util.ArrayList;

import com.adansoft.great21.models.Card;

public class CardSetNode {

	public final static String TYPE_SEQUENCE = "Sequence";
	public final static String TYPE_TRIPLET = "Triplet";
	public final static String TYPE_LOOSE = "Loose";
	public final static String TYPE_SEQUENCE_JOKER_1 = "JOKER1_Sequence";
	public final static String TYPE_SEQUENCE_JOKER_2 = "JOKER2_Sequence";
	public final static String TYPE_SEQUENCE_JOKER_3 = "JOKER3_Sequence";
	public final static String TYPE_TRIPLET_JOKER_1 = "JOKER1_Triplet";
	public final static String TYPE_TRIPLET_JOKER_2 = "JOKER2_Triplet";
	public final static String TYPE_TRIPLET_JOKER_3 = "JOKER3_Triplet";
	public final static String TYPE_TRIPLET_JOKER_4 = "JOKER4_Triplet";
	
	
	
	private boolean rootNode;	
	private ArrayList<Card> cardList;
	private String type;
	private boolean hasChild;
	private ArrayList<CardSetNode> children;
	private boolean leafNode;
	private CardSetNode parent;
	
	
	
	public CardSetNode(boolean isRoot)
	{
		this.rootNode = isRoot;
		children = new ArrayList<CardSetNode>();
	}

	public boolean isRootNode() {
		return rootNode;
	}

	public void setRootNode(boolean rootNode) {
		this.rootNode = rootNode;
	}

	public ArrayList<Card> getCardList() {
		return cardList;
	}

	public void setCardList(ArrayList<Card> cardList) {
		this.cardList = cardList;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isHasChild() {
		return hasChild;
	}

	public void setHasChild(boolean hasChild) {
		this.hasChild = hasChild;
	}

	public ArrayList<CardSetNode> getChildren() {
		return children;
	}

	public void setChildren(ArrayList<CardSetNode> children) {
		this.children = children;
	}
 
	public void addChild(CardSetNode node)
	{
		this.children.add(node);
		
	}

	public boolean isLeafNode() {
		return leafNode;
	}

	public void setLeafNode(boolean leafNode) {
		this.leafNode = leafNode;
	}

	public CardSetNode getParent() {
		return parent;
	}

	public void setParent(CardSetNode parent) {
		this.parent = parent;
	}
	
	
}
