package com.adansoft.great21.gameplay;

import java.io.Serializable;
import java.util.ArrayList;



public class GameChatMessageManager implements Serializable{

	private static final long serialVersionUID = 1276061878459424410L;
	private String gameInstanceID;
	private int bufferSize = 15;
	private int currentChat = 0;
	private GameMessage[] messagelist;
	
	public GameChatMessageManager() {
		super();
		
		
	}

	public GameChatMessageManager(String gameInstanceID, int bufferSize,
			int currentChat, GameMessage[] messagelist) {
		super();
		this.gameInstanceID = gameInstanceID;
		this.bufferSize = bufferSize;
		this.currentChat = currentChat;
		this.messagelist = messagelist;
	}

	public String getGameInstanceID() {
		return gameInstanceID;
	}

	public void setGameInstanceID(String gameInstanceID) {
		this.gameInstanceID = gameInstanceID;
	}

	public int getBufferSize() {
		return bufferSize;
	}

	public void setBufferSize(int bufferSize) {
		this.bufferSize = bufferSize;
	}

	public int getCurrentChat() {
		return currentChat;
	}

	public void setCurrentChat(int currentChat) {
		this.currentChat = currentChat;
	}

	public GameMessage[] getMessagelist() {
		return messagelist;
	}

	public void setMessagelist(GameMessage[] messagelist) {
		this.messagelist = messagelist;
	}
	
	public void addChatMessage(GameMessage message)
	{
		if(currentChat >= bufferSize - 1)
			currentChat = 0;
		messagelist[currentChat] = message;
		currentChat++;
	} 
	
	public GameMessage[] getMessages(int localchatno)
	{
		ArrayList<GameMessage> locallist = new ArrayList<GameMessage>();
		
		int j=0;
		if(localchatno > currentChat)
		{
			
			for(int i=bufferSize-localchatno;i<bufferSize;i++,j++)
			{
				if(messagelist[i] != null)
				    locallist.add(messagelist[i]);
			}
			for(int i=0;i<=currentChat;i++,j++)
			{	
				if(messagelist[i] != null)
				locallist.add(messagelist[i]);
			}
		}
		else if(localchatno < currentChat)
		{
			for(int i=localchatno;i<currentChat;i++,j++)
			{
				if(messagelist[i] != null)
				   locallist.add(messagelist[i]);
			}
		}
		
		return locallist.toArray(new GameMessage[locallist.size()]);
	}
	
}
