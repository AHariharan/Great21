package com.adansoft.great21.bot;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;



import java.util.Random;
import java.util.UUID;

import com.adansoft.great21.games.GameListConstants;
import com.adansoft.great21.models.Card;
import com.adansoft.great21.models.ClubCard;
import com.adansoft.great21.models.DiamondCard;
import com.adansoft.great21.models.GameStrategy;
import com.adansoft.great21.models.HeartCard;
import com.adansoft.great21.models.JokerCard;
import com.adansoft.great21.models.SpadeCard;
import com.adansoft.great21.ulitity.CardUtility;
import com.adansoft.great21.ulitity.NumberUtility;

public class EasyBotStrategy implements GameStrategy {
	
	private ArrayList<Card> cardsinHand;
	private ArrayList<Card> cardsinHandWithoutJokers;	
	private Card[] totalCards;
	private int currentIndicator;
	private ArrayList<Card> jokerlist;
	//private HashMap<String,ArrayList<PossibleSetCards>> possiblesealedCardMap;
	private HashMap<String,ArrayList<Card>> sealedCardMap;
	private ArrayList<Card> looseCards;
	private int noOfWildCards;
	private int countofRemainingCards = 0,instanceid = 0;
	private CardSetNode treeroot = new CardSetNode(false); 
	private ArrayList<CardSetNode> allNodes;
	private ArrayList<GroupCardSet> allGroups;
	private ArrayList<GroupCardSet> uniqueGroups;
	private GroupCardSet activeGroup;
	private ArrayList<CardAttribute> cardAttrList;
    private boolean isOneRealSequencePresent;
	
	private boolean canSeeJoker;
	private boolean jokerKnown;
	private boolean abortRecursiveFunction = false;
	//private boolean isOneRealSequencePresent;
	
	private Card roundJokerCard;
	private String gameType;
	//private GroupCardSet set = null;
	
	private boolean attemptedAtleastOnce;
	
	private ArrayList<Card> pickableCards;
	private int pickableCardscounter = 0;
	private int threshold;
	
	
	public EasyBotStrategy(ArrayList<Card> cardinhand,boolean jokerknown,Card jokercard,String gameType,Card[] cards,int initIndicator)
	{
		this.cardsinHand = cardinhand;
		this.jokerKnown = jokerknown;
		this.roundJokerCard = jokercard;
		this.gameType = gameType;
		this.totalCards = cards;
		this.currentIndicator = initIndicator;
		if(gameType.equals(GameListConstants.GAMELIST_SEVENCARD_CLOSED_TYPE) ||
		   gameType.equals(GameListConstants.GAMELIST_SEVENCARD_OPEN_TYPE))
			threshold = 8;
		else
			threshold = 14;
			
		init();
	//	getCardsinHand();
		initPickableCard();
        
		//playGame();
	}
	
	
	
	public void playTurn()
	{
		Card cardPicked = getNextCardFromDeck();
	//	System.out.println("\n\n--------------------------------------------------------------");
		cardsinHand.add(cardPicked);
	//	System.out.println("Picked Card :- " + cardPicked.getFlower()+"-"+cardPicked.getDisplayValue());
	//	getCardsinHand();
		detectJokers(cardsinHand);
		if(jokerKnown)
		{
			cardsinHandWithoutJokers = CardUtility.excludeJokers(cardsinHand, roundJokerCard.getDisplayValue());
			noOfWildCards = cardsinHand.size() - cardsinHandWithoutJokers.size();
		//	System.out.println("No of Joker : " + noOfWildCards);
		}
		treeroot = new CardSetNode(false); 
		allNodes = new ArrayList<CardSetNode>();
		attemptedAtleastOnce = false;	
		if(noOfWildCards > 0)
		{		   
		   analyzeCards(cardsinHandWithoutJokers,0,treeroot,isOneRealSequencePresent,true,noOfWildCards,0,jokerlist);
		}
		else
		{
		  
		   analyzeCards(cardsinHand,0,treeroot,false,false,0,0,jokerlist);
		}
		collectAllGroupedCards();
		performUniqueGroupedCards();
		decideActiveGroup();
		//printActiveGroup();
		assignScoreForLooseCards();
	 //   System.out.println(" \n\nMost likely to Drop : "  );
	//	getCardsinList(getAllDesiredDroppedCards()); System.out.println(" \n\n");
	}
	
	public int playGame()
	{
	 int count = 0;	
	// getCardsinHand();isOneRealSequencePresent
	 
	
	  while(true)
	  {
		    count++;
		    countofRemainingCards = 0;instanceid = 0;attemptedAtleastOnce = false; isOneRealSequencePresent = false;	
			playTurn();
			ArrayList<Card> desiredDropList = getAllDesiredDroppedCards();
			if(cardsinHand.size() == threshold && getLooseCardListfromGroup(activeGroup).size() <= 1 && isOneRealSequencePresent)
			{
				Card foldCard = chooseFoldCard();
			//	System.out.println("{{ FOLD CARD :- " + foldCard.getFlower() +" - " + foldCard.getDisplayValue() + " }}");
				//printActiveGroup();
				//System.out.println("\nGame Ends ....." + count);
				
				break;
			}
			
			if(desiredDropList.size() != 0)
			{
				int dropcard  = NumberUtility.generateRandomNumber(0,desiredDropList.size()-1);
				//int dropcard  = 1;
			//	System.out.println("\nDropped Card : " + desiredDropList.get(dropcard).getFlower() + "-" + desiredDropList.get(dropcard).getDisplayValue());
				//System.out.println("--------------------------------------------------------------");
				//getCardsinHand();
				boolean result = cardsinHand.remove(desiredDropList.get(dropcard));
		//		System.out.println("Card Really Dropped : " + result);
			}
			else
			{
				CardSetNode looseNode = getLooseCardNodeFromGroup(activeGroup);
				if(looseNode.getCardList().size() == 0 && !isOneRealSequencePresent)
				{
					if(!looseNode.getParent().getType().equals(CardSetNode.TYPE_JOKERONLY))
					{
						ArrayList<Card> dropCardlist = CardUtility.excludeJokers(looseNode.getParent().getCardList(), roundJokerCard.getDisplayValue());
						Card droppableCard = dropCardlist.get(0);
						cardsinHand.remove(droppableCard);
				//		System.out.println("\nDropped Card by alternation : " + droppableCard.getFlower() + "-" + droppableCard.getDisplayValue());
						
					}
					else
					{						
						ArrayList<Card> dropCardlist = CardUtility.excludeJokers(looseNode.getParent().getParent().getCardList(), roundJokerCard.getDisplayValue());
						Card droppableCard = dropCardlist.get(0);
						cardsinHand.remove(droppableCard);
				//		System.out.println("\nDropped Card by parent alternation : " + droppableCard.getFlower() + "-" + droppableCard.getDisplayValue());
					}
				}
					
			}
	  }
	  return count;
	}
	
	
	
	
	
	
	
	
	private ArrayList<Card> getAllDesiredDroppedCards()
	{
		ArrayList<Card> desiredDropList = new ArrayList<Card>();
		String jokerValue = null;
		if(jokerKnown)
			jokerValue = roundJokerCard.getDisplayValue();
		double minimumScore = CardGroupScoreCalculator.getMinimumScore(cardAttrList,jokerValue);
		for(CardAttribute cardAttr : cardAttrList)
		{
			 double currentScore = CardGroupScoreCalculator.calculateDropCardScore(cardAttr,jokerValue);
			 if(currentScore == minimumScore)
			 {
				 desiredDropList.add(cardAttr.getCard());
			 }
		}
		
		return desiredDropList;
	}
	
	
	private void decideActiveGroup()
	{
		double maxscore = -100;
		for(GroupCardSet set : uniqueGroups)
		{
			if(set.getScore() > maxscore)
			{
				maxscore = set.getScore();
				activeGroup = set;
			}
		}
		//System.out.println("Active Group :- \n\n");
		/*for(String key : activeGroup.getGroupedCardMap().keySet())
		{
			// getCardsinList(activeGroup.getGroupedCardMap().get(key).getCardList());
		}*/
		//System.out.println("\n\nActive Group END:- \n\n");
	}
	
	
	private void performUniqueGroupedCards()
	{
		uniqueGroups = new ArrayList<GroupCardSet>();
		int i = 0;
		for(GroupCardSet cardset : allGroups)
		{
			if(i == 0)
			{
				uniqueGroups.add(cardset);
				i++;
				continue;
			}			
			else
			{
				ArrayList<Card> inputCardlist1 = getLooseCardListfromGroup(cardset);
				
				boolean isSame = false;
				for(GroupCardSet loopset : uniqueGroups)
				{
					ArrayList<Card> inputCardlist2 = getLooseCardListfromGroup(loopset);
					isSame = compareArrayListofCardifSame(inputCardlist1, inputCardlist2);
					if(isSame)
						break;
				}
				if(!isSame)
				{
					uniqueGroups.add(cardset);
				}
				
			}
			
		}
		//System.out.println("Unique Group no :- " + uniqueGroups.size() + " ,AllGroup size : " + allGroups.size());
		if(allGroups.size() == 0)
		{
			System.out.println("NO PATTERNS SEVERE MESS UP");
			for(CardSetNode setNode : allNodes)
			{
				getCardsinList(setNode.getCardList());
			}
			System.exit(1);
		}
		for(GroupCardSet set : uniqueGroups)
		{
			double score = CardGroupScoreCalculator.calculateScore(set);
			set.setScore(score);
			/*System.out.print("{{Score :- " + score + "}},");
			for(String key : set.getGroupedCardMap().keySet())
			{
				
				getCardsinList(set.getGroupedCardMap().get(key).getCardList());
			}
			System.out.println("");*/
			//System.out.println("----------------------------------------------\n\n");
		}
		
	}
	
	
	
	private  boolean compareArrayListofCardifSame(ArrayList<Card> inputset1,ArrayList<Card> inputset2)
	{
		if(inputset1 == null || inputset2 == null)
			return false;
		if(inputset1.size() != inputset2.size())
			return false;
		for(Card card : inputset1)
		{
			boolean cardFoundFlag = false;
			for(Card loopedCard : inputset2)
			{
				if(card.getInstanceID().equals(loopedCard.getInstanceID()))
					cardFoundFlag = true;
			}
			if(!cardFoundFlag)
				return false;
		}
		
		return true;
	}
	
	private ArrayList<Card> getLooseCardListfromGroup(GroupCardSet set)
	{
		for(String key : set.getGroupedCardMap().keySet())
		{
			if(key.contains("LOOSE"))
			{
				return set.getGroupedCardMap().get(key).getCardList();
			}
		}
		return null;
	}
	
	private String getLooseCardListKey(GroupCardSet set)
	{
		for(String key : set.getGroupedCardMap().keySet())
		{
			if(key.contains("LOOSE"))
			{
				return key;
			}
		}
		return null;
	}
	
	
	private CardSetNode getLooseCardNodeFromGroup(GroupCardSet set)
	{
		for(String key : set.getGroupedCardMap().keySet())
		{
			if(key.contains("LOOSE"))
			{
				return set.getGroupedCardMap().get(key);
			}
		}
		return null;
	}
	
	
	
	
	
	
	
	private void collectAllGroupedCards()
	{
		int count = 0;
		allGroups = new ArrayList<GroupCardSet>();
		//System.out.println("\n____________________________PATTERNS___________________________");
		for(CardSetNode node : allNodes)
		{
			
			if(node.isLeafNode())
			{
				GroupCardSet currentGroup = new GroupCardSet(threshold);
				int seqcount = 0,tripcount = 0,loosecount = 0,jokeronlycount = 0;
				count++;
				while(node.getParent() != null)
				{
					  if(node.getType().equals(CardSetNode.TYPE_JOKERONLY))
					  {
						  jokeronlycount++;
						  currentGroup.getGroupedCardMap().put("JOKERONLY-"+jokeronlycount, node);
					  }
					  if(node.getType().equals(CardSetNode.TYPE_SEQUENCE))
					  {
						  seqcount++;
					      currentGroup.getGroupedCardMap().put("SEQUENCE-"+seqcount, node);
					  }
					  if(node.getType().equals(CardSetNode.TYPE_TRIPLET))
					  {
						  tripcount++;
					      currentGroup.getGroupedCardMap().put("TRIPLET-"+tripcount, node);
					  }
					  if(node.getType().equals(CardSetNode.TYPE_LOOSE))
					  {
						  loosecount++;
					      currentGroup.getGroupedCardMap().put("LOOSE-"+loosecount, node);
					  }
					  if(node.getType().equals(CardSetNode.TYPE_SEQUENCE_JOKER_1) ||
					     node.getType().equals(CardSetNode.TYPE_SEQUENCE_JOKER_2) ||
					     node.getType().equals(CardSetNode.TYPE_SEQUENCE_JOKER_3)
						 )
					  {
						  seqcount++;
						  currentGroup.getGroupedCardMap().put("SEQ_JOKER-"+seqcount, node);
					  }
					  if(node.getType().equals(CardSetNode.TYPE_TRIPLET_JOKER_1) ||
							     node.getType().equals(CardSetNode.TYPE_TRIPLET_JOKER_2) ||
							     node.getType().equals(CardSetNode.TYPE_TRIPLET_JOKER_3)
								 )
							  {
						  tripcount++;
								  currentGroup.getGroupedCardMap().put("TRIP_JOKER-"+tripcount, node);
							  }
				//	  System.out.print(" " + node.getType());
				//	  getCardsinList(node.getCardList());
					  node = node.getParent();
				}
				//System.out.println("");
				if(!currentGroup.verifyGroup())
				{
					System.out.println("\n\nMessed up group ... Exiting...");
					for(String key : currentGroup.getGroupedCardMap().keySet())
					{
						getCardsinList(currentGroup.getGroupedCardMap().get(key).getCardList());
					}
					System.exit(1);
				}
				allGroups.add(currentGroup);
			//	System.out.println("");
				
				
			}
			
		}
		//System.out.println("____________________________PATTERN___END________________________");
		//System.out.println("\n\n Total possible Combinations : " + allGroups.size() + " -- " + count);
	}
	
	
	private void ReArrangeGroupsbyUsingJokerOnlyNodes()
	{
		
	}
	
	
	private void init()
	{
		jokerlist = new ArrayList<Card>();
		looseCards = new ArrayList<Card>();
		sealedCardMap = new HashMap<String, ArrayList<Card>>();
		//possiblesealedCardMap = new HashMap<String, ArrayList<PossibleSetCards>>();
		
	}
	
	// Main method
	private void analyzeCards(ArrayList<Card> cardlist,int round,CardSetNode node,boolean isOneRealSequencePresent,boolean isJokerKnown,int numofWildCards,int jListIndicator,ArrayList<Card> JLIST)
	{
		ArrayList<SpadeCard> newSpadeList = new ArrayList<SpadeCard>();
		ArrayList<HeartCard> newHeartList = new ArrayList<HeartCard>();
		ArrayList<DiamondCard> newDiamondList = new ArrayList<DiamondCard>();
		ArrayList<ClubCard> newClubList = new ArrayList<ClubCard>();
		splitCardsup(cardlist.toArray(new Card[cardlist.size()]),newSpadeList,newHeartList,newDiamondList,newClubList);
		HashMap<String, ArrayList<PossibleSetCards>> mymap = checkForSequences(newSpadeList,newHeartList,newDiamondList,newClubList);
		ArrayList<Card> localjokerList = makeLocalCopy(JLIST);
		createGroupofCards(mymap,cardlist,round,node,isOneRealSequencePresent,jokerKnown,numofWildCards,jListIndicator,localjokerList);
		//System.out.println("Group Created");
		
	}
	
	private void createGroupofCards(HashMap<String, ArrayList<PossibleSetCards>> mymap,ArrayList<Card> inputcardlist,int round,CardSetNode parentNode,boolean isOneRealSequencePresent,boolean isJokerKnown,int numofWildCards,int jListIndicator,ArrayList<Card> JLIST)
	{
		//System.out.println(" JOKER LIST : ");getCardsinList(jokerlist);
		ArrayList<Card> localjokerList = makeLocalCopy(JLIST);
		boolean isRoot = false;
		round++;
		if(round == 1)
			isRoot = true;	
		if(mymap.size() == 0 && !(this.isOneRealSequencePresent) && !attemptedAtleastOnce) // Attempt to form analyze with only unreal jokers
		{
			  ArrayList<Card> extractedJokerList = getUnrealJokers();
	          inputcardlist.addAll(extractedJokerList);
	          int localnumofWildCards = numofWildCards - extractedJokerList.size();
	          localjokerList.removeAll(extractedJokerList);
	          attemptedAtleastOnce = true;
	          analyzeCards(inputcardlist,1,parentNode,false,jokerKnown,localnumofWildCards,jListIndicator,localjokerList);	          
		}
		else if(mymap.size() == 0 && this.isOneRealSequencePresent)
	    {
			
			checkRegularTriplets(mymap,inputcardlist,round,parentNode,isOneRealSequencePresent,isJokerKnown,numofWildCards,isRoot,jListIndicator,localjokerList);
	    }
		else if(mymap.size() == 0 && !this.isOneRealSequencePresent)
	    {
			ArrayList<Card> newlocaljokerList = makeLocalCopy(JLIST);
			ArrayList<Card> newCardList = CardUtility.excludeJokers(inputcardlist, roundJokerCard.getDisplayValue());
			int localnumofwildcards = numofWildCards + (inputcardlist.size() - newCardList.size());
			addUniqueCardstoJokerList(getRemainingCards(newCardList,inputcardlist),newlocaljokerList);
			checkPossibilitywithJokers(mymap, newCardList, round, parentNode, isOneRealSequencePresent, isJokerKnown, localnumofwildcards, isRoot, jListIndicator, newlocaljokerList);
	    }
	    else // For Real Sequences.
	    {
	    	checkRegularSequence(mymap,inputcardlist,round,parentNode,isOneRealSequencePresent,isJokerKnown,numofWildCards,isRoot,jListIndicator,localjokerList);
		
	    }
		return;
	}
	
	private void createEmptyNode(CardSetNode parentNode,boolean isRoot)
	{
		CardSetNode emptyLooseNode = new CardSetNode(isRoot);		
		emptyLooseNode.setCardList(new ArrayList<Card>());
		emptyLooseNode.setType(CardSetNode.TYPE_LOOSE);
		emptyLooseNode.setParent(parentNode);emptyLooseNode.setLeafNode(true);
		parentNode.setHasChild(true);
		allNodes.add(emptyLooseNode);
	}
	
	
	private Card chooseFoldCard()
	{
		Card foldCard = null;
		ArrayList<Card> looseCardlist = getLooseCardListfromGroup(activeGroup);
		if(looseCardlist.size() == 1)
		{
			foldCard = looseCardlist.get(0);
			String looseCardKey = getLooseCardListKey(activeGroup);
			activeGroup.getGroupedCardMap().remove(looseCardKey);
		}
		else if(looseCardlist.size() == 0)
		{
			ArrayList<CardSetNode> CardNodes_4 = new ArrayList<CardSetNode>();
			ArrayList<CardSetNode> CardNodes_3 = new ArrayList<CardSetNode>();
			ArrayList<CardSetNode> CardNodes_5 = new ArrayList<CardSetNode>();
			ArrayList<CardSetNode> CardNodes_2 = new ArrayList<CardSetNode>();
			ArrayList<CardSetNode> CardNodes_1 = new ArrayList<CardSetNode>();
			String Card2NodeKey = null;
			int countof4cardsset = 0;
			for(String key : activeGroup.getGroupedCardMap().keySet())
			{
				CardSetNode currentNode = activeGroup.getGroupedCardMap().get(key);
				if(currentNode.getCardList().size() == 2)
				{
					CardNodes_2.add(currentNode);
					Card2NodeKey = key;

				}
				if(currentNode.getCardList().size() == 1)
				{
					CardNodes_1.add(currentNode);

				}
				if(currentNode.getCardList().size() == 4)
				{
					CardNodes_4.add(currentNode);
					countof4cardsset++;
				}
				if(currentNode.getCardList().size() == 3)
				{
					CardNodes_3.add(currentNode);
				}
				if(currentNode.getCardList().size() == 5)
				{
					CardNodes_5.add(currentNode);
				}
			}
			if(countof4cardsset == 2)
			{
				foldCard = CardNodes_4.get(0).getCardList().get(0);
				CardNodes_4.get(0).getCardList().remove(foldCard);
			}
			if(CardNodes_5.size() == 1 && CardNodes_3.size() == 3)
			{
				foldCard = CardNodes_5.get(0).getCardList().get(0);
				CardNodes_5.get(0).getCardList().remove(foldCard);
			}
			if(CardNodes_5.size() == 1 && CardNodes_3.size() == 1) // For Seven Card
			{
				foldCard = CardUtility.excludeJokers(CardNodes_5.get(0).getCardList(),roundJokerCard.getDisplayValue()).get(0);
				CardNodes_5.get(0).getCardList().remove(foldCard);
			}
			if(CardNodes_5.size() == 2 && CardNodes_4.size() == 1)
			{
				foldCard = CardNodes_4.get(0).getCardList().get(0);
				CardNodes_4.get(0).getCardList().remove(foldCard);
			}
			if(CardNodes_2.size() == 1 && CardNodes_2.get(0).getType().equals(CardSetNode.TYPE_JOKERONLY))
			{
				foldCard = CardNodes_2.get(0).getCardList().get(0);
				Card moveCard = CardNodes_2.get(0).getCardList().get(1);
				CardNodes_2.get(0).getParent().getCardList().add(moveCard);
				activeGroup.getGroupedCardMap().remove(Card2NodeKey);
			}
			if(CardNodes_1.size() == 1 && CardNodes_1.get(0).getType().equals(CardSetNode.TYPE_JOKERONLY))
			{
				foldCard = CardNodes_1.get(0).getCardList().get(0);
				CardSetNode parent = CardNodes_1.get(0).getParent();
				getLooseCardNodeFromGroup(activeGroup).setParent(parent);
			}
		}
		if(foldCard == null)
		{
			System.out.println("Unable to determine fold card : ");
			printActiveGroup();
			System.exit(1);
		}
		else
		{
			CardSetNode foldnode = new CardSetNode(false);
			foldnode.setType(CardSetNode.TYPE_FOLD);
			ArrayList<Card> foldcardlist = new ArrayList<Card>();
			foldnode.setCardList(foldcardlist);
			foldcardlist.add(foldCard);
			activeGroup.getGroupedCardMap().put("FOLDCARD-1",foldnode);
		}
		return foldCard;
	}
	
	private void analyzeLooseCardswithJokers(HashMap<String, ArrayList<PossibleSetCards>> mymap,ArrayList<Card> inputcardlist,int round,CardSetNode parentNode,boolean isOneRealSequencePresent,boolean isJokerKnown,int numofWildCards,boolean isRoot,int jListIndicator,ArrayList<Card> JLIST)
	{
		ArrayList<Card> localjokerList = makeLocalCopy(JLIST);
		//System.out.println("localjokerList.size() " + localjokerList.size() + ", " +inputcardlist.size());
		/*if(parentNode.getType() == null)
		{
			System.out.println("Add BreakPoint");
		}
		if(parentNode.getType().equals(CardSetNode.TYPE_TRIPLET_JOKER_1) &&
		   parentNode.getCardList().size() == 3  )
		{
			//getCardsinList(parentNode.getCardList());
			//System.out.println("Add BreakPoint");
		}*/
	    int numofjokersleft = localjokerList.size();
	    if(inputcardlist.size() == 0)
	    {
	    	if(numofjokersleft == 1)
	    	{
				CardSetNode jokerNode = new CardSetNode(isRoot);		
				jokerNode.setCardList(localjokerList);
				jokerNode.setType(CardSetNode.TYPE_JOKERONLY);
				jokerNode.setParent(parentNode);
				jokerNode.setLeafNode(false);
				parentNode.setHasChild(true);
				allNodes.add(jokerNode);
				createEmptyNode(jokerNode, isRoot);
	    		return;
	    	}
	    	if(numofjokersleft == 2)
	    	{
	    		CardSetNode node = new CardSetNode(isRoot);
				node.setCardList(localjokerList);
				//Card card = borrowOneCardFromAnyParentNode(parentNode);
			/*	if(card == null)
				{
				*/	//System.out.println("Couldn't barrow card and so distribute jokers to different parent nodes");
					CardSetNode jokerNode = new CardSetNode(isRoot);		
					jokerNode.setCardList(localjokerList);
					jokerNode.setType(CardSetNode.TYPE_JOKERONLY);
					jokerNode.setParent(parentNode);
					jokerNode.setLeafNode(false);
					parentNode.setHasChild(true);
					allNodes.add(jokerNode);
					createEmptyNode(jokerNode, isRoot);
					/*parentNode.getCardList().add(localjokerList.get(0));
					parentNode.getParent().getCardList().add(localjokerList.get(1));
					createEmptyNode(parentNode,isRoot);
					*/return;
				/*}
				else
				{
				node.getCardList().add(card);
				node.setType(CardSetNode.TYPE_LOOSE);
				node.setParent(parentNode);
				parentNode.setHasChild(true);
				parentNode.setChildren(null);
				node.setLeafNode(true);
				allNodes.add(node);
				return;
				}*/
	    	}
	    	if(numofjokersleft > 2)
	    	{
	    		CardSetNode node = new CardSetNode(isRoot);
				node.setCardList(localjokerList);
				node.setType(CardSetNode.TYPE_LOOSE);
				node.setParent(parentNode);
				parentNode.setHasChild(true);
				parentNode.setChildren(null);
				node.setLeafNode(true);
				allNodes.add(node);
				return;
	    	}
	    	
	    }
		if(numofjokersleft <= 1)
		{
			CardSetNode node = new CardSetNode(isRoot);
			node.setCardList(inputcardlist);
			node.setType(CardSetNode.TYPE_LOOSE);
			node.setParent(parentNode);
			parentNode.setHasChild(false);
			parentNode.setChildren(null);
			if(numofjokersleft == 1)
			{
				if(parentNode.getCardList() != null)
			        parentNode.getCardList().add(localjokerList.get(0));
				else
					node.getCardList().add(localjokerList.get(0));
			} 
			
			node.setLeafNode(true);
			allNodes.add(node);
			return;
		}		
		if(numofjokersleft == 2)
		{
	     	handleMoreJokers(2,inputcardlist,round,parentNode,isOneRealSequencePresent,isJokerKnown,numofWildCards,isRoot,jListIndicator,localjokerList);
		}
		if(numofjokersleft == 3)
		{
	     	handleMoreJokers(3,inputcardlist,round,parentNode,isOneRealSequencePresent,isJokerKnown,numofWildCards,isRoot,jListIndicator,localjokerList);
		}
		if(numofjokersleft > 3)
		{
	     	handleMoreJokers(3,inputcardlist,round,parentNode,isOneRealSequencePresent,isJokerKnown,numofWildCards,isRoot,jListIndicator,localjokerList);
		}
	}
	
	private void handleMoreJokers(int numofjokersleft,ArrayList<Card> inputcardlist,int round,CardSetNode parentNode,boolean isOneRealSequencePresent,boolean isJokerKnown,int numofWildCards,boolean isRoot,int jListIndicator,ArrayList<Card> JLIST)
	{
		ArrayList<Card> localjokerList = makeLocalCopy(JLIST);
		CardSetNode cardnode = new CardSetNode(isRoot);
		cardnode.setParent(parentNode);
		cardnode.setCardList(new ArrayList<Card>());
		
		parentNode.setHasChild(true);
		cardnode.getCardList().add(inputcardlist.get(0));
		allNodes.add(cardnode);
		for(int i=0;i<numofjokersleft;i++)
		{
			cardnode.getCardList().add(localjokerList.get(i));
			jListIndicator++;
		}
		if(numofjokersleft == 2)
			cardnode.setType(CardSetNode.TYPE_SEQUENCE_JOKER_2);
		if(numofjokersleft >= 3)
			cardnode.setType(CardSetNode.TYPE_SEQUENCE_JOKER_3);
		ArrayList<Card> remainingCards = getRemainingCards(cardnode.getCardList(), inputcardlist);
		analyzeLooseCardswithJokers(null,remainingCards,round,cardnode,isOneRealSequencePresent,isJokerKnown,numofWildCards,isRoot,jListIndicator,localjokerList);
		
	}
	
	
	private void leafNodeWithoutJokers(HashMap<String, ArrayList<PossibleSetCards>> mymap,ArrayList<Card> inputcardlist,int round,CardSetNode parentNode,boolean isOneRealSequencePresent,boolean isJokerKnown,int numofWildCards,boolean isRoot,int jListIndicator,ArrayList<Card> JLIST)
	{
		checkPossibilitywithJokers(mymap, inputcardlist, round, parentNode, isOneRealSequencePresent, isJokerKnown, numofWildCards, isRoot,jListIndicator,JLIST);
	}
	
	private void leafNodeWithJokers(HashMap<String, ArrayList<PossibleSetCards>> mymap,ArrayList<Card> inputcardlist,int round,CardSetNode parentNode,boolean isOneRealSequencePresent,boolean isJokerKnown,int numofWildCards,boolean isRoot,int jListIndicator,ArrayList<Card> JLIST)
	{
       /* CardSetNode node = new CardSetNode(isRoot);
		node.setCardList(inputcardlist);
		node.setType(CardSetNode.TYPE_LOOSE);
		node.setParent(parentNode);
		parentNode.setHasChild(false);
		parentNode.setChildren(null);
		node.setLeafNode(true);
		allNodes.add(node);*/
		ArrayList<Card> localjokerList = makeLocalCopy(JLIST);
		if(!attemptedAtleastOnce)
		{							
		    analyzeLooseCardswithJokers(mymap, inputcardlist, round, parentNode, isOneRealSequencePresent, isJokerKnown, numofWildCards, isRoot, jListIndicator,localjokerList);
		}
		else
		{
			ArrayList<Card> excludedJokerlist = CardUtility.excludeJokers(inputcardlist, roundJokerCard.getDisplayValue());
			numofWildCards = numofWildCards + inputcardlist.size() - excludedJokerlist.size();		
			//System.out.println("No of Wild CARDS : - " + numofWildCards);
		//	getCardsinList(jokerlist);getCardsinList(getRemainingCards(excludedJokerlist, inputcardlist));
			localjokerList.addAll(getRemainingCards(excludedJokerlist, inputcardlist));
			inputcardlist = excludedJokerlist; 
			analyzeLooseCardswithJokers(mymap, inputcardlist, round, parentNode, isOneRealSequencePresent, isJokerKnown, numofWildCards, isRoot, jListIndicator,localjokerList);
		}
		/*else
			node.getCardList().addAll(getRemainingCards(getUnrealJokers(), jokerlist));
		*///System.out.println("\nLoose cards before with Joker Interpretation ");
	//	getCardsinList(node.getCardList());System.out.println(" {{ JLISTIND : " + jListIndicator + " , Round : " + round + "}}\n");
	//	System.out.println("\n");
	}
	
	private void checkRegularTriplets(HashMap<String, ArrayList<PossibleSetCards>> mymap,ArrayList<Card> inputcardlist,int round,CardSetNode parentNode,boolean isOneRealSequencePresent,boolean isJokerKnown,int numofWildCards,boolean isRoot,int jListIndicator,ArrayList<Card> JLIST)
	{
		ArrayList<Card> localjokerList = makeLocalCopy(JLIST);
		ArrayList<Card> newCardList = CardUtility.excludeJokers(inputcardlist, roundJokerCard.getDisplayValue());
		int localnumofwildcards = numofWildCards + (inputcardlist.size() - newCardList.size());
		addUniqueCardstoJokerList(getRemainingCards(newCardList,inputcardlist),localjokerList);
	//	localjokerList.addAll(getRemainingCards(newCardList,inputcardlist));
		mymap = checkForTripletsQuadreplets(newCardList);
    	if(mymap.size() == 0)
    	{
    		   leafNodeWithoutJokers(mymap, newCardList,round,parentNode,isOneRealSequencePresent,isJokerKnown,localnumofwildcards,isRoot,jListIndicator,localjokerList);
			   return;
    	}
    	for(String key : mymap.keySet())
    	{
    		for(PossibleSetCards cardset : mymap.get(key))
			{
    		     //  System.out.println("\n\nTrip Kwy :" + key);	
    		       parentNode.setHasChild(true);
			       CardSetNode node = new CardSetNode(isRoot);
			       node.setCardList(cardset.getCardList());
			       node.setType(CardSetNode.TYPE_TRIPLET);
			       node.setParent(parentNode);
			       parentNode.addChild(node);
			       ArrayList<Card> remainingCards = getRemainingCards(cardset.getCardList(),newCardList);
			       
			      // remainingCards.addAll(getUnrealJokers());
				//  getCardsinList(remainingCards);System.out.println(" {{ JLISTIND : " + jListIndicator + " , Round : " + round + " , NumofWildCards : " + numofWildCards + " }}\n");
				   allNodes.add(node);
				   analyzeCards(remainingCards,round,node,isOneRealSequencePresent,jokerKnown,localnumofwildcards,jListIndicator,localjokerList);
				   /*if(remainingCards.size() != countofRemainingCards)
					{
						countofRemainingCards = remainingCards.size();				
						analyzeCards(remainingCards,round,node,isOneRealSequencePresent,jokerKnown,localnumofwildcards,jListIndicator);
						
					}
					else
					{
					    return;
					}		*/				
			}
    	}
	}
	
	
	private void checkRegularSequence(HashMap<String, ArrayList<PossibleSetCards>> mymap,ArrayList<Card> inputcardlist,int round,CardSetNode parentNode,boolean isOneRealSequencePresent,boolean isJokerKnown,int numofWildCards,boolean isRoot,int jListIndicator,ArrayList<Card> JLIST)
	{
		ArrayList<Card> localjokerList = makeLocalCopy(JLIST);
		for(String key : mymap.keySet()) 
		{
			if(mymap.get(key).size() == 0)
    			continue;
		   // System.out.println("Kwy :" + key);	
			for(PossibleSetCards cardset : mymap.get(key))
			{
				/*if(cardset.getCardList().size() == 3)
				{
					System.out.println("Add Debug");
				}*/
				parentNode.setHasChild(true);
				CardSetNode node = new CardSetNode(isRoot);
				node.setCardList(cardset.getCardList());
				node.setType(CardSetNode.TYPE_SEQUENCE);
				node.setParent(parentNode);
				parentNode.addChild(node);			
				ArrayList<Card> remainingCards = getRemainingCards(cardset.getCardList(),inputcardlist);				
			//	getCardsinList(cardset.getCardList());getCardsinList(remainingCards);System.out.println(" {{ JLISTIND : " + jListIndicator + " , Round : " + round + " , NumofWildCards : " + numofWildCards + " }}\n");
				allNodes.add(node);
				this.isOneRealSequencePresent = true;
				analyzeCards(remainingCards,round,node,isOneRealSequencePresent,jokerKnown,numofWildCards,jListIndicator,localjokerList);
				/*if(remainingCards.size() != countofRemainingCards)
				{
					countofRemainingCards = remainingCards.size();				
					analyzeCards(remainingCards,round,node,isOneRealSequencePresent,jokerKnown,numofWildCards,jListIndicator);
					
				}
				else
				{
					//node.setLeafNode(true);
				    return;
				}*/
				
				
			} 
		}
	}
	
	
	private boolean checkJokerSequences(ArrayList<Card> inputcardlist,int round,CardSetNode parentNode,boolean isOneRealSequencePresent,boolean isJokerKnown,int numofWildCards,boolean isRoot,int jListIndicator,ArrayList<Card> JLIST)
	{
		ArrayList<Card> localjokerList = makeLocalCopy(JLIST);
		ArrayList<PossibleSetCards> seqSetWithJokers = getTotalListofAllPossibleSequencesWithJoker(inputcardlist, round, parentNode, isOneRealSequencePresent, isJokerKnown, numofWildCards, isRoot, jListIndicator);
		if(seqSetWithJokers.size() == 0)
			return true;
		//System.out.println("InputCard List:- " ); getCardsinList(inputcardlist);System.out.println();
		for(PossibleSetCards cardset : seqSetWithJokers)
		{
		//	System.out.println("Round :- " + round  + " , seqSetWithJokers : " + seqSetWithJokers.size());
			
			int localIndicator = jListIndicator;
		
			ArrayList<Card> newlocaljokerList = makeLocalCopy(localjokerList);
		/*	System.out.println("No of jokers : " + numofWildCards + " , " +localjokerList.size() + " , " + newlocaljokerList.size());
			getCardsinList(cardset.getCardList());
			System.out.println("");*/
			CardSetNode node = new CardSetNode(isRoot);
			for(int i=0;i<cardset.getNoofjokerused();i++)
			{
				Card card = newlocaljokerList.get(0);
				cardset.getCardList().add(card);
				newlocaljokerList.remove(card);
				localIndicator++;
			}
			node.setCardList(cardset.getCardList());
			if(cardset.getNoofjokerused() == 1)
			    node.setType(CardSetNode.TYPE_SEQUENCE_JOKER_1);
			else if (cardset.getNoofjokerused() == 2)
			    node.setType(CardSetNode.TYPE_SEQUENCE_JOKER_2);
			else if (cardset.getNoofjokerused() >= 3)
				node.setType(CardSetNode.TYPE_SEQUENCE_JOKER_3);
			else
			{
				System.out.println("Should not occur...");
			}
			node.setParent(parentNode);
			parentNode.addChild(node);			
			ArrayList<Card> remainingCards = getRemainingCards(cardset.getCardList(),inputcardlist);
			int localnumofWildCards = numofWildCards - cardset.getNoofjokerused();
			allNodes.add(node);
			//getCardsinList(cardset.getCardList());getCardsinList(remainingCards);System.out.println(" {{ JLISTIND : " + jListIndicator + " , Round : " + round + " , NumofWildCards : " + numofWildCards + " }}\n");
			analyzeCards(remainingCards,round,node,isOneRealSequencePresent,jokerKnown,localnumofWildCards,localIndicator,newlocaljokerList);
		
			
			/*if(remainingCards.size() != countofRemainingCards)
			{
				int localnumofWildCards = numofWildCards - cardset.getNoofjokerused();
				countofRemainingCards = remainingCards.size();				
				analyzeCards(remainingCards,round,node,isOneRealSequencePresent,jokerKnown,localnumofWildCards,localIndicator);					
			}
			else
			{
				//node.setLeafNode(true);
			    return false;
			}*/
		}
		return false;
	}
	
	
	private void checkJokerTriplets(ArrayList<Card> inputcardlist,int round,CardSetNode parentNode,boolean isOneRealSequencePresent,boolean isJokerKnown,int numofWildCards,boolean isRoot,int jListIndicator,ArrayList<Card> JLIST)
	{
		ArrayList<Card> localjokerList = makeLocalCopy(JLIST);
		ArrayList<PossibleSetCards> setWithJoker = new ArrayList<PossibleSetCards>();
		getAllPossibleTripletswithJoker(inputcardlist, setWithJoker,numofWildCards,jListIndicator);
		if(setWithJoker.size() > 0)
		{
			for(PossibleSetCards cardset : setWithJoker)
			{
				int localIndicator = jListIndicator;
				CardSetNode node = new CardSetNode(isRoot);
				ArrayList<Card> newlocaljokerList = makeLocalCopy(localjokerList);
				for(int i=0;i<cardset.getNoofjokerused();i++)
				{
					Card card = newlocaljokerList.get(0);
					cardset.getCardList().add(card);
					newlocaljokerList.remove(card);
					localIndicator++;
				}
				node.setCardList(cardset.getCardList());
				if(cardset.getNoofjokerused() == 1)
				    node.setType(CardSetNode.TYPE_TRIPLET_JOKER_1);
				else if (cardset.getNoofjokerused() == 2)
				    node.setType(CardSetNode.TYPE_TRIPLET_JOKER_2);
				else if(cardset.getNoofjokerused() == 0)
					node.setType(CardSetNode.TYPE_TRIPLET);
				node.setParent(parentNode);
				parentNode.addChild(node);			
				ArrayList<Card> remainingCards = getRemainingCards(cardset.getCardList(),inputcardlist);
				int localnumofWildCards = numofWildCards - cardset.getNoofjokerused();
				//getCardsinList(cardset.getCardList());getCardsinList(remainingCards);System.out.println(" {{ JLISTIND : " + jListIndicator + " , Round : " + round + " , NumofWildCards : " + numofWildCards + " }}\n");
				allNodes.add(node);
				analyzeCards(remainingCards,round,node,isOneRealSequencePresent,jokerKnown,localnumofWildCards,localIndicator,newlocaljokerList);
				/*if(remainingCards.size() != countofRemainingCards)
				{
					int localnumofWildCards = numofWildCards - cardset.getNoofjokerused();
					countofRemainingCards = remainingCards.size();				
					analyzeCards(remainingCards,round,node,isOneRealSequencePresent,jokerKnown,localnumofWildCards,localIndicator);					
				}
				else
				{
					//node.setLeafNode(true);
				    return;
				}*/
			}
			
		}
		else // Final Leaf Node
		{
			leafNodeWithJokers(null, inputcardlist, round, parentNode, isOneRealSequencePresent, isJokerKnown, numofWildCards, isRoot,jListIndicator,localjokerList);
		}
	}
	
	
	
	
	private void checkPossibilitywithJokers(HashMap<String, ArrayList<PossibleSetCards>> mymap,ArrayList<Card> inputcardlist,int round,CardSetNode parentNode,boolean isOneRealSequencePresent,boolean isJokerKnown,int numofWildCards,boolean isRoot,int jListIndicator,ArrayList<Card> JLIST)
	{
		    ArrayList<Card> localjokerList = makeLocalCopy(JLIST);
			boolean result = checkJokerSequences(inputcardlist,round,parentNode,isOneRealSequencePresent,isJokerKnown,numofWildCards,isRoot,jListIndicator,localjokerList);
		    if(result)
		    {
		         checkJokerTriplets(inputcardlist, round, parentNode, isOneRealSequencePresent, isJokerKnown, numofWildCards, isRoot,jListIndicator,localjokerList);
		    }
		
		
	}
	
	
	private ArrayList<PossibleSetCards> getTotalListofAllPossibleSequencesWithJoker(ArrayList<Card> inputcardlist,int round,CardSetNode parentNode,boolean isOneRealSequencePresent,boolean isJokerKnown,int numofWildCards,boolean isRoot,int jListIndicator)
	{
		ArrayList<PossibleSetCards> seqSetWithJokers = new ArrayList<PossibleSetCards>();
		ArrayList<Card> CardWithoutJoker = CardUtility.excludeJokers(inputcardlist, roundJokerCard.getDisplayValue());
		ArrayList<SpadeCard> splist = new ArrayList<SpadeCard>();
		ArrayList<DiamondCard> ddlist = new ArrayList<DiamondCard>();
		ArrayList<HeartCard> htlist = new ArrayList<HeartCard>();
		ArrayList<ClubCard> cblist = new ArrayList<ClubCard>();
		Card[] splitableCardList = CardWithoutJoker.toArray(new Card[CardWithoutJoker.size()]);
		splitCardsup(splitableCardList, splist, htlist, ddlist, cblist);
		getAllPossibleSequenceswithJoker(new ArrayList<Card>(splist),seqSetWithJokers,numofWildCards,jListIndicator);
		getAllPossibleSequenceswithJoker(new ArrayList<Card>(ddlist),seqSetWithJokers,numofWildCards,jListIndicator);
		getAllPossibleSequenceswithJoker(new ArrayList<Card>(htlist),seqSetWithJokers,numofWildCards,jListIndicator);
		getAllPossibleSequenceswithJoker(new ArrayList<Card>(cblist),seqSetWithJokers,numofWildCards,jListIndicator);
		return seqSetWithJokers;
	}
	
	private void getAllPossibleTripletswithJoker(ArrayList<Card> cardlist,ArrayList<PossibleSetCards> setWithJoker,int numofJokers,int jListIndicator)
	{
		if(numofJokers == 0)
			return;
		ArrayList<Card> uniqueCardList = getDistinctCardsFromListbyDisplayValue(cardlist);
		ArrayList<Card> distinctCardList = getDistinctCardsFromListbyFlowerandDisplayValue(cardlist);
		for(Card card : uniqueCardList)
		{
			int count = 1;
			ArrayList<Card> setCardList = new ArrayList<Card>();
			setCardList.add(card);
			for(Card loopCard : distinctCardList)
			{
				if( loopCard.getDisplayValue().equals(card.getDisplayValue()) &&
				   !(loopCard.getFlower().equals(card.getFlower())))
				  {
					count++;
					setCardList.add(loopCard);
				  }
			}
			    if(count == 0) // No Match found
			    	continue;
			    
				int numofJokeruserd = 0;
				if(count == 2)
				{
					numofJokeruserd = 1;
					if(numofJokeruserd > numofJokers)
						continue;
					//Card jokerCard = jList.get(0);
					//setCardList.add(jokerCard);
					//jList.remove(jokerCard);
					
				}
				else if(count == 1)
				{
					numofJokeruserd = 2;
					if(numofJokeruserd > numofJokers)
						continue;
					
					//setCardList.add(jokerCard2);
					//jList.remove(jokerCard1);jList.remove(jokerCard2);
				}
				else if(count == 3)
					numofJokers = 0;
				
				if(numofJokeruserd > numofJokers)
					continue;
				
				PossibleSetCards setCard = new PossibleSetCards();
				setCard.setCardList(setCardList);setCard.setNoofCards(count+numofJokeruserd);
				setCard.setNoofjokerused(numofJokeruserd);
				setCard.setSetType(PossibleSetCards.SETTYPE_TRIPLET_JOKER);
				setWithJoker.add(setCard);
				
			
		}
	}
	
	private void getAllPossibleSequenceswithJoker(ArrayList<Card> cardlist,ArrayList<PossibleSetCards> SeqWithJokers,int noofwildcards,int jListIndicator)
	{
		if(noofwildcards == 0)
			return;
		ArrayList<Card> distinctlist = getDistinctCardsFromListbyFlowerandDisplayValue(cardlist);
		Card[] distinctCards = distinctlist.toArray(new Card[distinctlist.size()]);
		Arrays.sort(distinctCards);
		for(int i=0;i<distinctCards.length;i++)
		{
			ProximityResult result = CardGroupScoreCalculator.findSequenceDistance(distinctCards, i);
			int distance = result.getDistance();
			if(distance - 1 > noofwildcards )
				continue;
			if(distance > 4) // Dont create sequence more than 5 card length
				continue;
			else
			{
				int numofjokerused = 0;
				ArrayList<Card> setcardlist = new ArrayList<Card>();
				setcardlist.addAll(result.getComparedCardsList());
				if(distance == 1 || distance == 2)
				{
				//	setcardlist.add(jList.get(0));
				//	jList.remove(0);					
					numofjokerused = 1;
					
				}
				else
				{
					/*for(int j=0;j<distance-1;j++)
					{
					//	setcardlist.add(jList.get(j));
						//jList.remove(j);
					}*/
					numofjokerused = distance - 1;
				}
			    	
				PossibleSetCards setCard = new PossibleSetCards();
				setCard.setCardList(setcardlist);setCard.setNoofCards(2+numofjokerused);
				setCard.setNoofjokerused(numofjokerused);
				setCard.setSetType(PossibleSetCards.SETTYPE_SEQUENCE_JOKER);
				SeqWithJokers.add(setCard);
				
			}
			
		}
		
	}
	
	
	
	private void detectJokers(ArrayList<Card> cardlist)
	{
		jokerlist = new ArrayList<Card>();
		for(Card card : cardlist)
		{
			//System.out.println( " Current Card " + card.getInstanceID());
			if(jokerKnown)
			{
				if(roundJokerCard.getDisplayValue().equals("Joker") && card.getDisplayValue().equals("A"))
					jokerlist.add(card);					
				else if(card.getDisplayValue().equals(roundJokerCard.getDisplayValue()))
				   jokerlist.add(card);
				else if(card instanceof JokerCard)
				   jokerlist.add((JokerCard) card);
				
			}
		}
	}
	
	private ArrayList<Card> getUnrealJokers()
	{
		ArrayList<Card> cardlist = new ArrayList<Card>();
		for(Card card : jokerlist)
		{
			if(!(card instanceof JokerCard))
				cardlist.add(card);
		}
		return cardlist;
	}
	
	
	private void splitCardsup(Card[] cardlist,ArrayList<SpadeCard> splist,ArrayList<HeartCard> htlist,ArrayList<DiamondCard> ddlist,ArrayList<ClubCard> cblist)
	{
		for(Card card : cardlist)
		{
			/*if(jokerKnown)
			{
				if( card.getDisplayValue().equals(roundJokerCard.getDisplayValue()))
						{
					           jokerlist.add(card);
					           return;
						}
			}*/
			if(card instanceof SpadeCard)
				splist.add((SpadeCard) card);
			if(card instanceof DiamondCard)
				ddlist.add((DiamondCard) card);
			if(card instanceof HeartCard)
				htlist.add((HeartCard) card);
			if(card instanceof ClubCard)
				cblist.add((ClubCard) card);
			/*if(card instanceof JokerCard)
				jokerlist.add((JokerCard) card);*/
				
		}
		noOfWildCards = jokerlist.size();
	}
	
	
	private void splitCardsupwithJoker(Card[] cardlist,ArrayList<SpadeCard> splist,ArrayList<HeartCard> htlist,ArrayList<DiamondCard> ddlist,ArrayList<ClubCard> cblist,ArrayList<JokerCard> jlist)
	{
		for(Card card : cardlist)
		{
			/*if(jokerKnown)
			{
				if( card.getDisplayValue().equals(roundJokerCard.getDisplayValue()))
						{
					           jokerlist.add(card);
					           return;
						}
			}*/
			if(card instanceof SpadeCard)
				splist.add((SpadeCard) card);
			if(card instanceof DiamondCard)
				ddlist.add((DiamondCard) card);
			if(card instanceof HeartCard)
				htlist.add((HeartCard) card);
			if(card instanceof ClubCard)
				cblist.add((ClubCard) card);
			if(card instanceof JokerCard)
				jlist.add((JokerCard) card);
				
		}
		noOfWildCards = jokerlist.size();
	}
	
	
	private HashMap<String, ArrayList<PossibleSetCards>> checkForTripletsQuadreplets(ArrayList<Card> cardlist)
	{
		/*System.out.println("\nInside Triplets Section " ); getCardsinList(cardlist);
		System.out.println("\n " );*/
		HashMap<String, ArrayList<PossibleSetCards>> myMap = new HashMap<String, ArrayList<PossibleSetCards>>();
		ArrayList<PossibleSetCards> pcardset = new ArrayList<PossibleSetCards>();
		if(cardlist.size() >= 3 )
		{
			Card[] sortedCards = cardlist.toArray(new Card[cardlist.size()]);
			Arrays.sort(sortedCards);
			ArrayList<Card> distinctCards = getDistinctCardsFromListbyDisplayValue(cardlist);
			for(Card card : distinctCards)
			{
				int similarcardcount = 0;
				ArrayList<Card> similarCards = new ArrayList<Card>();
				for(Card loopCard : sortedCards)
				{
					//System.out.println("card.getDisplayValue() : " + card.getDisplayValue() + " loopCard.getDisplayValue() : " + loopCard.getFlower() + "-"+loopCard.getDisplayValue());
					if(card.getDisplayValue().equals(loopCard.getDisplayValue()))
					{
						similarCards.add(loopCard);
						similarcardcount++;
					}
				}
				//System.out.println("Similar Count : - "  + similarcardcount + " , CurrentCard " + card.getFlower()+"-"+card.getDisplayValue());
				if( similarcardcount >=3 )
				{
					similarCards =  getDistinctCardsFromListbyFlowerandDisplayValue(similarCards);
					boolean isTriplet = CardUtility.checkTripletorQuadraplets(similarCards.toArray(new Card[similarCards.size()]));
					if(isTriplet)
					{
						PossibleSetCards cardset = new PossibleSetCards();
						cardset.setSetType(PossibleSetCards.SETTYPE_TRIPLET);
						cardset.setCardList(similarCards);
						pcardset.add(cardset);
						
					}
						
				}
			}
			if(pcardset.size() > 0 )
			   myMap.put("ALL_TRIPLETS", pcardset);
		}
		
		return myMap;
	}
	
	
	
	
	private HashMap<String, ArrayList<PossibleSetCards>> checkForSequences(ArrayList<SpadeCard> splist,ArrayList<HeartCard> htlist,ArrayList<DiamondCard> ddlist,ArrayList<ClubCard> cblist)
	{
		boolean isSealedSequnce = false;
		HashMap<String,ArrayList<PossibleSetCards>> MyMap = new HashMap<String, ArrayList<PossibleSetCards>>();
		
		isSealedSequnce = attemptToSealviaSequences(new ArrayList<Card>(splist), Card.FLOWER_SPADE,"spadelist",MyMap);
		if(isSealedSequnce)
			canSeeJoker = true;		
		isSealedSequnce = attemptToSealviaSequences(new ArrayList<Card>(htlist), Card.FLOWER_HEART,"heartlist",MyMap);
		if(isSealedSequnce)
			canSeeJoker = true;		
		isSealedSequnce = attemptToSealviaSequences(new ArrayList<Card>(ddlist), Card.FLOWER_DIAMOND,"diamondlist",MyMap);
		if(isSealedSequnce)
			canSeeJoker = true;	
		isSealedSequnce = attemptToSealviaSequences(new ArrayList<Card>(cblist), Card.FLOWER_CLUBS,"clublist",MyMap);
		if(isSealedSequnce)
			canSeeJoker = true;	

		return MyMap;
	}
	
	
    private void assignScoreForLooseCards()
    {
    	cardAttrList = new ArrayList<CardAttribute>();
    	ArrayList<SpadeCard> splist = new ArrayList<SpadeCard>();
    	ArrayList<DiamondCard> htlist = new ArrayList<DiamondCard>();
    	ArrayList<HeartCard> ddlist = new ArrayList<HeartCard>();
    	ArrayList<ClubCard> cblist = new ArrayList<ClubCard>();
    	ArrayList<JokerCard> jokerlist = new ArrayList<JokerCard>();
    	ArrayList<Card> currentLooseCards = getLooseCardListfromGroup(activeGroup);
    	if(currentLooseCards != null)
    	{
    	    Card[] cardlist = currentLooseCards.toArray(new Card[currentLooseCards.size()]);
    	    splitCardsupwithJoker(cardlist, splist, ddlist,htlist, cblist,jokerlist);
         	analyzeLooseCards(currentLooseCards, splist, ddlist,htlist, cblist,jokerlist);
    	}
    	return;
    }
    
    
    
    private void analyzeLooseCards(ArrayList<Card> looseCardList,ArrayList<SpadeCard> splist,ArrayList<HeartCard> htlist,ArrayList<DiamondCard> ddlist,ArrayList<ClubCard> cblist,ArrayList<JokerCard> jlist)
    {
    	analyzeLooseCardsforSeq(new ArrayList<Card>(splist));
    	analyzeLooseCardsforSeq(new ArrayList<Card>(htlist));
    	analyzeLooseCardsforSeq(new ArrayList<Card>(ddlist));
    	analyzeLooseCardsforSeq(new ArrayList<Card>(cblist));
    	analyzeLooseCardsforSeq(new ArrayList<Card>(jlist));
    	analyzeLooseCardsforTrip(looseCardList);
    }
	
    private void analyzeLooseCardsforSeq(ArrayList<Card> cardlist)
    {
    	 
    	 Card sortedCards[] = cardlist.toArray(new Card[cardlist.size()]);
    	 Arrays.sort(sortedCards);
    	 for(int i=0;i<sortedCards.length;i++)
    	 {
    		 CardAttribute cardattr = new CardAttribute();
    		 cardattr.setCard(sortedCards[i]);
  			 cardattr.setDuplicate(isDuplicateCardinList(sortedCards, sortedCards[i]));
  			 cardattr.setSeqProximityScore(CardGroupScoreCalculator.findProxmityforSequence(sortedCards, i));
  			 cardAttrList.add(cardattr);
  			
    	 }
    	return;
    }
    
    private void analyzeLooseCardsforTrip(ArrayList<Card> cardlist)
    {
    	 Card[] listofCards = cardlist.toArray(new Card[cardlist.size()]);
    	 Arrays.sort(listofCards);
    	
    	 for(CardAttribute cardAttr : cardAttrList)
    	 {
    		  cardAttr.setTripProximityScore(CardGroupScoreCalculator.findTripPossibilityScore(listofCards, cardAttr.getCard()));
    	 }
    	return;
    }
	
    
    private boolean isDuplicateCardinList(Card[] listofCard,Card card)
    {
    	if(card instanceof JokerCard)
    		return false;
    	
    	int count = 0;
    	for(int i=0;i<listofCard.length;i++)
    	{
        	if(card.getDisplayValue().equals(listofCard[i].getDisplayValue()))
    				{
    			      count++;     
    				}
    	}
    	if(count > 1)
    		 return true;
    	else
    		 return false;
    }


   
    
    
	private int getDeclarableCount()
	{
		if( gameType.equals(GameListConstants.GAMELIST_SEVENCARD_CLOSED_TYPE) || 
			gameType.equals(GameListConstants.GAMELIST_SEVENCARD_OPEN_TYPE))
		{
			    return 7;
		}
		else if( gameType.equals(GameListConstants.GAMELIST_THIRTEENCARD_CLOSED_TYPE) || 
				gameType.equals(GameListConstants.GAMELIST_THIRTEENCARD_OPEN_TYPE))
			{
				return 13;
			}
		else
		{
			return 21;
		}
	}
	
	private int getSealedCardCount()
	{
		int count = 0;
		for(String key : sealedCardMap.keySet())
		{
			count = count + sealedCardMap.get(key).size();
		}
		return count;
	}
	
	
	public boolean attemptToSealviaSequences(ArrayList<Card> cardlist,String flower,String origin,HashMap<String,ArrayList<PossibleSetCards>> myMap)
	{
		
		cardlist = getDistinctCardsFromListbyDisplayValue(cardlist);
		ArrayList<PossibleSetCards> pcardsetarr = new ArrayList<PossibleSetCards>();
		boolean result = false;
		if(cardlist.size() >= 3)
		{
			if(cardlist.size() == 5 && ( 
			!(gameType.equals(GameListConstants.GAMELIST_SEVENCARD_CLOSED_TYPE)) &&
			!(gameType.equals(GameListConstants.GAMELIST_SEVENCARD_OPEN_TYPE))
			))
				  addPossibleSealing(cardlist, 5, flower,origin,pcardsetarr);
			if(cardlist.size() >= 4)
				  addPossibleSealing(cardlist, 4, flower,origin,pcardsetarr);
			if(cardlist.size() >= 3)
				  addPossibleSealing(cardlist, 3, flower,origin,pcardsetarr);
			
			if(pcardsetarr.size()>0)
			    myMap.put("ORIGIN-"+origin+instanceid, pcardsetarr);
			instanceid++;
		}
		/*else
		{
			    looseCards.addAll(cardlist);
		}
		*/
		return result;
	}
	
	
	
	
	private void addPossibleSealing(ArrayList<Card> cardlist,int streamlength,String flower,String origin,ArrayList<PossibleSetCards> pcardsetarr)
	{
		HashMap<String,ArrayList<Card>> streamer = getCardsByStreaming(cardlist.toArray(new Card[cardlist.size()]), streamlength);
		for(String key : streamer.keySet())
		{
			 ArrayList<Card> cardset = streamer.get(key);
			 boolean sequencePresent = CardUtility.checkSequence(cardset.toArray(new Card[cardset.size()]));
			 if(sequencePresent)
			 {
				    PossibleSetCards pcardset = new PossibleSetCards(streamlength, PossibleSetCards.SETTYPE_SEQUENCE, cardset, 0, flower, origin);
				   /* System.out.println("-------------------------------------------------------------");
				    System.out.print("Input Current Set : ");
				    getCardsinList(pcardset.getCardList());
				    System.out.println("-------------------------------------------------------------");*/
				  // // AnalyzedCardResult result = analyzeRemainingCards(getRemainingCards(pcardset.getCardList()));
				  //  pcardset.setHowmanySeq(result.getHowmanysequencescanbemade());
				 //   pcardset.setHowmanytrip(result.getHowmanytripletscanbemade());
				 //   getCardsinList(pcardset.getCardList());
				    pcardsetarr.add(pcardset);
			 }
			 else
				looseCards.addAll(cardset);
		}
	}
	
	/*private void attemptSealinginLooseCards()
	{
		Card[] looseCardArray = looseCards.toArray(new Card[looseCards.size()]);
		Arrays.sort(looseCardArray);
		
		if(looseCardArray.length >= 4)
		{
		   HashMap<String,ArrayList<Card>> streamlist = getCardsByStreaming(looseCardArray,4);
		   for(String key : streamlist.keySet())
		   {
			   System.out.println("Loose Cards ...... : ");
			   getCardsinList(streamlist.get(key));
			   
		   }
		}
		if(looseCardArray.length >= 3)
		{
		   HashMap<String,ArrayList<Card>> streamlist = getCardsByStreaming(looseCardArray,3);
		   for(String key : streamlist.keySet())
		   {
			   System.out.println("Loose Cards :3 ...... : " + key);
			   getCardsinList(streamlist.get(key));
			   
		   }
		}
		//isTriple = CardUtility.checkTripletorQuadraplets(looseCards.toArray(new Card[looseCards.size()]));
	}
*/
	
	
	private HashMap<String,ArrayList<Card>> getCardsByStreaming(Card[] cardlist,int streamlength)
	{
		HashMap<String,ArrayList<Card>> cardStream = new HashMap<String, ArrayList<Card>>();
		Arrays.sort(cardlist);
		for(int i=0;i<cardlist.length;i++)
		{
			ArrayList<Card> streamcardlist = new ArrayList<Card>();
			if((i + streamlength) > cardlist.length)
			{
				break;
			}
			for(int j=i;j<streamlength+i;j++)
			{
				streamcardlist.add(cardlist[j]);
			}
			cardStream.put("STREAM-"+i,streamcardlist);	
		}
		if(cardlist[0].getDisplayValue().equals("A"))
	    {
			ArrayList<Card> streamcardlist = new ArrayList<Card>();
			streamcardlist.add(cardlist[0]);
	    	for(int k = cardlist.length-1,j=0;j<streamlength-1;j++,k--)
	    	{
	    		streamcardlist.add(cardlist[k]);
	    	}
	    	cardStream.put("STREAM-"+"A",streamcardlist);		
	    }
		return cardStream;
	}
	
	
	
	
	
	
	public int getNoOfWildCards() {
		return noOfWildCards;
	}

	public boolean isCanSeeJoker() {
		return canSeeJoker;
	}


	@Override
	public Card discardCard(ArrayList<Card> cardlist) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String pickupCard(Card card) {
		// TODO Auto-generated method stub
		return null;
	}
	
	//Display function for all cards
	public void getCardsinHand()
	{
	    ArrayList<SpadeCard> splist = new ArrayList<SpadeCard>();
	    ArrayList<HeartCard> htlist = new ArrayList<HeartCard>();
	    ArrayList<DiamondCard> ddlist = new ArrayList<DiamondCard>();
	    ArrayList<ClubCard> cblist = new ArrayList<ClubCard>();
	    Card[] listedCards = cardsinHand.toArray(new Card[cardsinHand.size()]);
	    splitCardsup(listedCards, splist, htlist, ddlist, cblist);
	    
		Card[] sortedArray = cardsinHand.toArray(new Card[cardsinHand.size()]);
		Arrays.sort(sortedArray);
		System.out.println("InputCards :-");
		System.out.print("{");
		for(Card card : sortedArray)
		{
			System.out.print(card.getFlower()+"-"+card.getDisplayValue()+",");
		}
		System.out.print("}");
		System.out.println(" {Joker Card : " + roundJokerCard.getFlower() +"-" +  roundJokerCard.getDisplayValue() + "} \n");
	}
	
	//Display function for all cards in the provided list
	public void getCardsinList(ArrayList<Card> cards)
	{
		Card[] sortedArray = cards.toArray(new Card[cards.size()]);
		Arrays.sort(sortedArray);
		System.out.print("{{");
		for(Card card : sortedArray)
		{
			System.out.print(card.getFlower()+"-"+card.getDisplayValue()+",");
		}
		System.out.print("}},");
	}
	
	
	

	
	//Helper function to get unique cards used in getHowManyTripletscanbeMade
	private ArrayList<Card> getDistinctCardsFromListbyFlowerandDisplayValue(ArrayList<Card> cardlist)
	{
		ArrayList<Card> distinctCards = new ArrayList<Card>();
	    for(int i=0;i<cardlist.size();i++)
	    {
	    	if(i==0)
	    	{
	    		distinctCards.add(cardlist.get(i));
	    		continue;
	    	}
	    	else
	    	{
	    		boolean isUnique = true;
	    		for(Card existingcard : distinctCards)
	    		{
	    			if(cardlist.get(i).getDisplayValue().equals(existingcard.getDisplayValue()) && 
	    			   cardlist.get(i).getFlower().equals(existingcard.getFlower())	)
	    					isUnique = false;	
	    		}
	    		if(isUnique)
	    			distinctCards.add(cardlist.get(i));
	    	}
	    }
		
		return distinctCards;
	}
	
	private ArrayList<Card> getDistinctCardsFromListbyDisplayValue(ArrayList<Card> cardlist)
	{
		ArrayList<Card> distinctCards = new ArrayList<Card>();
	    for(int i=0;i<cardlist.size();i++)
	    {
	    	if(i==0)
	    	{
	    		distinctCards.add(cardlist.get(i));
	    		continue;
	    	}
	    	else
	    	{
	    		boolean isUnique = true;
	    		for(Card existingcard : distinctCards)
	    		{
	    			if(cardlist.get(i).getDisplayValue().equals(existingcard.getDisplayValue()))
	    					isUnique = false;	
	    		}
	    		if(isUnique)
	    			distinctCards.add(cardlist.get(i));
	    	}
	    }
		
		return distinctCards;
	}
	
	
	
	
	
	// Get remaining cards from hand other than list of cards provided as input
	private ArrayList<Card> getRemainingCards(ArrayList<Card> cardlist,ArrayList<Card> fromCardList)
	{
		ArrayList<Card> remainingCards = new ArrayList<Card>();
		for(Card card : fromCardList)
		{
			boolean cardexistsinGroup = false;
			for(Card cardfromset : cardlist)
			{
				if(card.getInstanceID().equals(cardfromset.getInstanceID()))
						cardexistsinGroup = true;	
			}
			if(!cardexistsinGroup)
				remainingCards.add(card);
		}
		
		return remainingCards;
	}
	

	
	private void printActiveGroup()
	{
		
		System.out.println(" Active Group : ");
		for(String key : activeGroup.getGroupedCardMap().keySet())
		{
			System.out.print(activeGroup.getGroupedCardMap().get(key).getType());
			getCardsinList(activeGroup.getGroupedCardMap().get(key).getCardList());
		}
		System.out.print("{{ ROUND Joker : " + roundJokerCard.getFlower()+"-"+roundJokerCard.getCountValue()+ " }}");
		System.out.println("");
	}
	
	private ArrayList<Card> findBiggestsizeArrayList(ArrayList<SpadeCard> splist,ArrayList<HeartCard> htlist,ArrayList<DiamondCard> ddlist,ArrayList<ClubCard> cblist)
	{
		ArrayList<Card> returnlist;
		int biggestsize = splist.size();
		returnlist = new ArrayList<Card>(splist);
		if(htlist.size() > biggestsize)
		{
			biggestsize = htlist.size();
			returnlist = new ArrayList<Card>(htlist);
		}
		if(ddlist.size() > biggestsize)
		{
			biggestsize = ddlist.size();
			returnlist = new ArrayList<Card>(ddlist);
		}
		if(cblist.size() > biggestsize)
		{
			biggestsize = cblist.size();
			returnlist = new ArrayList<Card>(cblist);
		}
		return returnlist;
	}

	
	private void addUniqueCardstoJokerList(ArrayList<Card> cardlist,ArrayList<Card> toList)
	{
		for(Card card : cardlist)
		{
		   boolean exists = false;
		   for(Card jokerCard : toList)
		   {
			   if(jokerCard.getInstanceID().equals(card.getInstanceID()))
				   exists = true;
		   }
		   if(!exists)
			   toList.add(card);
		}
	}

	
    private ArrayList<Card> makeLocalCopy(ArrayList<Card> joker)
    {
    	ArrayList<Card> cardlist = new ArrayList<Card>();
    	cardlist.addAll(joker);
    	return cardlist;
    }
	

    private Card borrowOneCardFromAnyParentNode(CardSetNode parentNode)
    {
    	CardSetNode currentNode = parentNode;
    	Card card = null;
    	while(true)
    	{
             if(currentNode.getCardList().size() == 4)
             {
            	 card = currentNode.getCardList().get(0);
            	 currentNode.getCardList().remove(card);
            	 break;
             }
             else
             {
            	// getCardsinList(currentNode.getCardList());
            	 currentNode = currentNode.getParent();
            	 if(currentNode.getCardList() == null)
            		 break;
             }
            	 
    	}
    	/*if(card == null)
    	{
    		System.out.println("Messed up can't borrow card from parent nodes : ");
    		System.exit(1);
    	}*/
    	return card;
    	
    }
    
    
    
// Not need in actuals but used for testing.

    
    
    
    
private void initPickableCard()
{
	pickableCards = new ArrayList<Card>();
	//pickableCards.add(new HeartCard("3", 3,7, Card.STATUS_ASSIGNED));
	//pickableCards.add(new HeartCard("5", 5,7, Card.STATUS_ASSIGNED));
	//pickableCards.add(new ClubCard("3", 3,7, Card.STATUS_ASSIGNED));
	//pickableCards.add(new DiamondCard("J", 10,7, Card.STATUS_ASSIGNED));
	/*pickableCards.add(new ClubCard("K", 10,7, Card.STATUS_ASSIGNED));
	pickableCards.add(new ClubCard("2", 2,7, Card.STATUS_ASSIGNED));
	pickableCards.add(new ClubCard("J", 10,7, Card.STATUS_ASSIGNED));
	pickableCards.add(new DiamondCard("10", 10,7, Card.STATUS_ASSIGNED));
	pickableCards.add(new DiamondCard("9", 9,7, Card.STATUS_ASSIGNED));
	pickableCards.add(new ClubCard("A", 1,7, Card.STATUS_ASSIGNED));*/
	pickableCards.add(new SpadeCard("3", 3,1000, Card.STATUS_ASSIGNED));
	//pickableCards.add(new DiamondCard("Q", 10, 100, Card.STATUS_ASSIGNED));
	//pickableCards.add(new DiamondCard("5", 5,7, Card.STATUS_ASSIGNED));
	//pickableCards.add(new DiamondCard("A", 1,7, Card.STATUS_ASSIGNED));
}
	
	
private Card getNextCardFromDeck()
{
	if(pickableCardscounter < pickableCards.size())
	{
		Card returnCard = pickableCards.get(pickableCardscounter);
		pickableCardscounter++;
		return returnCard;
		
	}
	currentIndicator++;
	if(currentIndicator == (totalCards.length - 1))
	{
		currentIndicator = 0;
		System.out.println("Card Exhausted");
		//System.exit(1);
	}
	return totalCards[currentIndicator];
	//return (Card)(new DiamondCard("7", 7, 7, Card.STATUS_ASSIGNED));
}




} // End of Class
