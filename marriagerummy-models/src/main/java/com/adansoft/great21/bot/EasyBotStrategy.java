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

	
	private boolean canSeeJoker;
	private boolean jokerKnown;
	//private boolean isOneRealSequencePresent;
	
	private Card roundJokerCard;
	private String gameType;
	//private GroupCardSet set = null;
	
	private boolean attemptedAtleastOnce;
	
	
	public EasyBotStrategy(ArrayList<Card> cardinhand,boolean jokerknown,Card jokercard,String gameType,Card[] cards,int initIndicator)
	{
		this.cardsinHand = cardinhand;
		this.jokerKnown = jokerknown;
		this.roundJokerCard = jokercard;
		this.gameType = gameType;
		this.totalCards = cards;
		this.currentIndicator = initIndicator;
		
		init();
		getCardsinHand();
		Card cardPicked = getNextCardFromDeck();
		cardinhand.add(cardPicked);
		System.out.println("Picked Card :- " + cardPicked.getFlower()+"-"+cardPicked.getDisplayValue());
		detectJokers(cardinhand);
		if(jokerknown)
		{
			cardsinHandWithoutJokers = CardUtility.excludeJokers(cardinhand, roundJokerCard.getDisplayValue());
			noOfWildCards = cardinhand.size() - cardsinHandWithoutJokers.size();
			System.out.println("No of Joker : " + noOfWildCards);
		}
		treeroot = new CardSetNode(false); 
		allNodes = new ArrayList<CardSetNode>();
		
	
		if(noOfWildCards > 0)
		{		   
		   analyzeCards(cardsinHandWithoutJokers,0,treeroot,false,true,noOfWildCards);
		}
		else
		{
		   cardinhand.add(getNextCardFromDeck());
		   analyzeCards(cardsinHand,0,treeroot,false,false,0);
		}
		collectAllGroupedCards();
		performUniqueGroupedCards();
		decideActiveGroup();
		assignScoreForLooseCards();
	    System.out.println(" \n\nMost likely to Drop : "  );
		getCardsinList(getAllDesiredDroppedCards()); System.out.println(" \n\n");
		//playGame();
	}
	
	
	private void playGame()
	{
	 int count = 0;	
	  while(true)
	  {
		    count++;
		  
		    countofRemainingCards = 0;instanceid = 0;
			Card card = getNextCardFromDeck();
			System.out.println("--------------------------------------------------------------");
			System.out.println("Picked Card :- " + card.getFlower() +"-" + card.getDisplayValue());		
			cardsinHand.add(card);
			//  System.out.println("CARDS IN HAND SIZE : " + cardsinHand.size());
			treeroot = new CardSetNode(false); 
			allNodes = new ArrayList<CardSetNode>();
	//		analyzeCards(cardsinHand,0,treeroot);
			collectAllGroupedCards();
			performUniqueGroupedCards();
			decideActiveGroup();
			assignScoreForLooseCards();
			
		  //  System.out.println("Cards in Hand : " + cardsinHand.size() + "  , " + getLooseCardListfromGroup(activeGroup).size() );
			ArrayList<Card> desiredDropList = getAllDesiredDroppedCards();
			if(cardsinHand.size() == 14 && getLooseCardListfromGroup(activeGroup).size() <= 1)
			{
				System.out.println("\nGame Ends ....." + count);
			    
				break;
			}
			
			if(desiredDropList.size() != 0)
			{
				int dropcard  = NumberUtility.generateRandomNumber(0,desiredDropList.size()-1);
				System.out.println("\nDropped Card : " + desiredDropList.get(dropcard).getFlower() + "-" + desiredDropList.get(dropcard).getDisplayValue());
				System.out.println("--------------------------------------------------------------");
				cardsinHand.remove(desiredDropList.get(dropcard));
			}
	  }
	}
	
	
	
	
	
	
	
	
	private ArrayList<Card> getAllDesiredDroppedCards()
	{
		ArrayList<Card> desiredDropList = new ArrayList<Card>();
		double minimumScore = CardGroupScoreCalculator.getMinimumScore(cardAttrList);
		for(CardAttribute cardAttr : cardAttrList)
		{
			 double currentScore = CardGroupScoreCalculator.calculateDropCardScore(cardAttr);
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
		for(String key : activeGroup.getGroupedCardMap().keySet())
		{
			 getCardsinList(activeGroup.getGroupedCardMap().get(key).getCardList());
		}
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
		//System.out.println("Unique Group no :- " + uniqueGroups.size());
		for(GroupCardSet set : uniqueGroups)
		{
			double score = CardGroupScoreCalculator.calculateScore(set);
			set.setScore(score);
			//System.out.print("{{Score :- " + score + "}},");
			for(String key : set.getGroupedCardMap().keySet())
			{
				
			//	getCardsinList(set.getGroupedCardMap().get(key).getCardList());
			}
			//System.out.println("----------------------------------------------\n\n");
		}
		
	}
	
	
	
	private  boolean compareArrayListofCardifSame(ArrayList<Card> inputset1,ArrayList<Card> inputset2)
	{
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
	
	private void collectAllGroupedCards()
	{
		int count = 0;
		allGroups = new ArrayList<GroupCardSet>();
		//System.out.println("\n____________________________PATTERNS___________________________");
		for(CardSetNode node : allNodes)
		{
			
			if(node.isLeafNode())
			{
				GroupCardSet currentGroup = new GroupCardSet();
				int seqcount = 0,tripcount = 0,loosecount = 0;
				count++;
				while(node.getParent() != null)
				{
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
					//  System.out.print(" " + node.getType());
					 // getCardsinList(node.getCardList());
					  node = node.getParent();
				}
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
				//System.out.println("____________________________PATTERN___END________________________");
				//System.out.println("");
			}
			
		}
		//System.out.println("\n\n Total possible Combinations : " + allGroups.size() + " -- " + count);
	}
	
	
	private void init()
	{
		jokerlist = new ArrayList<Card>();
		looseCards = new ArrayList<Card>();
		sealedCardMap = new HashMap<String, ArrayList<Card>>();
		//possiblesealedCardMap = new HashMap<String, ArrayList<PossibleSetCards>>();
		
	}
	
	// Main method
	private void analyzeCards(ArrayList<Card> cardlist,int round,CardSetNode node,boolean isOneRealSequencePresent,boolean isJokerKnown,int numofWildCards)
	{
		ArrayList<SpadeCard> newSpadeList = new ArrayList<SpadeCard>();
		ArrayList<HeartCard> newHeartList = new ArrayList<HeartCard>();
		ArrayList<DiamondCard> newDiamondList = new ArrayList<DiamondCard>();
		ArrayList<ClubCard> newClubList = new ArrayList<ClubCard>();
		splitCardsup(cardlist.toArray(new Card[cardlist.size()]),newSpadeList,newHeartList,newDiamondList,newClubList);
		HashMap<String, ArrayList<PossibleSetCards>> mymap = checkForSequences(newSpadeList,newHeartList,newDiamondList,newClubList);
		createGroupofCards(mymap,cardlist,round,node,isOneRealSequencePresent,jokerKnown,numofWildCards);
		//System.out.println("Group Created");
		
	}
	
	private void createGroupofCards(HashMap<String, ArrayList<PossibleSetCards>> mymap,ArrayList<Card> inputcardlist,int round,CardSetNode parentNode,boolean isOneRealSequencePresent,boolean isJokerKnown,int numofWildCards)
	{
		boolean isRoot = false;
		round++;
		if(round == 1)
			isRoot = true;	
		if(mymap.size() == 0 && !isOneRealSequencePresent && !attemptedAtleastOnce)
		{
	          inputcardlist.addAll(getUnrealJokers());
	          attemptedAtleastOnce = true;
	          analyzeCards(inputcardlist,1,parentNode,false,jokerKnown,numofWildCards-getUnrealJokers().size());	          
		}
		else if(mymap.size() == 0)
	    {
	    	mymap = checkForTripletsQuadreplets(inputcardlist);
	    	if(mymap.size() == 0)
	    	{
	    	        CardSetNode node = new CardSetNode(isRoot);
					node.setCardList(inputcardlist);
					node.setType(CardSetNode.TYPE_LOOSE);
					node.setParent(parentNode);
					parentNode.setHasChild(false);
					parentNode.setChildren(null);
					node.setLeafNode(true);
					allNodes.add(node);
					node.getCardList().addAll(getRemainingCards(getUnrealJokers(), jokerlist));
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
				       ArrayList<Card> remainingCards = getRemainingCards(cardset.getCardList(),inputcardlist);
					 //  getCardsinList(cardset.getCardList());getCardsinList(remainingCards);
					   allNodes.add(node);
					   if(remainingCards.size() != countofRemainingCards)
						{
							countofRemainingCards = remainingCards.size();				
							analyzeCards(remainingCards,round,node,isOneRealSequencePresent,jokerKnown,numofWildCards);
							
						}
						else
						{
						    return;
						}						
				}
	    	}
	    }
	    else // For Real Sequences.
	    {
		
		for(String key : mymap.keySet()) 
		{
			if(mymap.get(key).size() == 0)
    			continue;
		   // System.out.println("Kwy :" + key);	
			for(PossibleSetCards cardset : mymap.get(key))
			{
				parentNode.setHasChild(true);
				CardSetNode node = new CardSetNode(isRoot);
				node.setCardList(cardset.getCardList());
				node.setType(CardSetNode.TYPE_SEQUENCE);
				node.setParent(parentNode);
				parentNode.addChild(node);			
				ArrayList<Card> remainingCards = getRemainingCards(cardset.getCardList(),inputcardlist);
				//getCardsinList(cardset.getCardList());getCardsinList(remainingCards);
				allNodes.add(node);
				isOneRealSequencePresent = true;
				if(remainingCards.size() != countofRemainingCards)
				{
					countofRemainingCards = remainingCards.size();				
					analyzeCards(remainingCards,round,node,isOneRealSequencePresent,jokerKnown,numofWildCards);
					
				}
				else
				{
					//node.setLeafNode(true);
				    return;
				}
				
				
			} 
		}
	}
		return;
	}
	
	
	private void detectJokers(ArrayList<Card> cardlist)
	{
		for(Card card : cardlist)
		{
			if(jokerKnown)
			{
				if( card.getDisplayValue().equals(roundJokerCard.getDisplayValue()))
				   jokerlist.add(card);
				if(card instanceof JokerCard)
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
    	ArrayList<Card> currentLooseCards = getLooseCardListfromGroup(activeGroup);
    	Card[] cardlist = currentLooseCards.toArray(new Card[currentLooseCards.size()]);
    	splitCardsup(cardlist, splist, ddlist,htlist, cblist);
    	analyzeLooseCards(currentLooseCards, splist, ddlist,htlist, cblist);
    	return;
    }
    
    
    
    private void analyzeLooseCards(ArrayList<Card> looseCardList,ArrayList<SpadeCard> splist,ArrayList<HeartCard> htlist,ArrayList<DiamondCard> ddlist,ArrayList<ClubCard> cblist)
    {
    	analyzeLooseCardsforSeq(new ArrayList<Card>(splist));
    	analyzeLooseCardsforSeq(new ArrayList<Card>(htlist));
    	analyzeLooseCardsforSeq(new ArrayList<Card>(ddlist));
    	analyzeLooseCardsforSeq(new ArrayList<Card>(cblist));
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
	
	
	
	//Get How many possible Triplets from remaining cards
	private int getHowManyTripletscanbeMade(ArrayList<Card> cardlist)
	{
		int count = 0;
		Card[] sortedCards = cardlist.toArray(new Card[cardlist.size()]);
		Arrays.sort(sortedCards);
		
		ArrayList<Card> distinctCards = getDistinctCardsFromListbyDisplayValue(cardlist);
		for(Card card : distinctCards)
		{
			int similarcardcount = 0;
			ArrayList<Card> similarCards = new ArrayList<Card>();
			for(Card loopCard : sortedCards)
			{
				if(card.getDisplayValue().equals(loopCard.getDisplayValue()))
				{
					similarCards.add(loopCard);
					similarcardcount++;
				}
			}
			if( similarcardcount >=3 )
			{
				boolean isTriplet = CardUtility.checkTripletorQuadraplets(similarCards.toArray(new Card[similarCards.size()]));
				if(isTriplet)
					count++;
			}
		}
		
		return count;
	}
	
	//Get How many possible sequences from remaining cards
	private int getHowManySequencescanbeMade(ArrayList<Card> cardlist)
	{
		int count = 0;
		ArrayList<SpadeCard> splist = new ArrayList<SpadeCard>();
		ArrayList<HeartCard> htlist = new ArrayList<HeartCard>();
		ArrayList<DiamondCard> ddlist = new ArrayList<DiamondCard>();
		ArrayList<ClubCard> cblist = new ArrayList<ClubCard>();
		for(Card card:cardlist)
		{
			if(card instanceof SpadeCard)
				splist.add((SpadeCard)card);
			if(card instanceof HeartCard)
				htlist.add((HeartCard)card);
			if(card instanceof DiamondCard)
				ddlist.add((DiamondCard)card);
			if(card instanceof ClubCard)
				cblist.add((ClubCard)card);
		}
        count = count + getCountsofSequenceinList(new ArrayList<Card>(splist));
        count = count + getCountsofSequenceinList(new ArrayList<Card>(htlist));
        count = count + getCountsofSequenceinList(new ArrayList<Card>(ddlist));
        count = count + getCountsofSequenceinList(new ArrayList<Card>(cblist));
		
	  return count;	
	}
	
	// Helper function to get count inside a same flower list of cards used in getHowManySequencescanbeMade
	private int getCountsofSequenceinList(ArrayList<Card> cardlist)
	{
		int count = 0;
		Card[] cardarray = cardlist.toArray(new Card[cardlist.size()]);
		if(cardlist.size() >= 3)
		{
			
			if(cardlist.size() >= 5)
			{
				HashMap<String,ArrayList<Card>> streamedCards = getCardsByStreaming(cardarray, 5);
				for(String key : streamedCards.keySet())
				{
					ArrayList<Card> cardset = streamedCards.get(key);
					if(CardUtility.checkSequence(cardset.toArray(new Card[cardset.size()])))
					{
						  getCardsinList(cardset);
						  count++;
					}
						
				}
			}
			if(cardlist.size() >= 4)
			{
				HashMap<String,ArrayList<Card>> streamedCards = getCardsByStreaming(cardarray, 4);
				for(String key : streamedCards.keySet())
				{
					ArrayList<Card> cardset = streamedCards.get(key);
					if(CardUtility.checkSequence(cardset.toArray(new Card[cardset.size()])))
					{
						  getCardsinList(cardset);
						  count++;
					}
						
				}
			}
			if(cardlist.size() >= 3)
			{
				HashMap<String,ArrayList<Card>> streamedCards = getCardsByStreaming(cardarray, 3);
				for(String key : streamedCards.keySet())
				{
					ArrayList<Card> cardset = streamedCards.get(key);
					if(CardUtility.checkSequence(cardset.toArray(new Card[cardset.size()])))
					{
						  getCardsinList(cardset);
						  count++;
					}
				}
			}
		}
		
		
	    return count;
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
	



// Not need in actuals but used for testing.

private Card getNextCardFromDeck()
{
	currentIndicator++;
	//return totalCards[currentIndicator];
	return (Card)(new DiamondCard("7", 7, 7, Card.STATUS_ASSIGNED));
}


} // End of Class
