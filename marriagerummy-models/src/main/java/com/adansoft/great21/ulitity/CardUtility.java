package com.adansoft.great21.ulitity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import com.adansoft.great21.games.GameListConstants;
import com.adansoft.great21.models.Card;
import com.adansoft.great21.models.ClubCard;
import com.adansoft.great21.models.Deck;
import com.adansoft.great21.models.DiamondCard;
import com.adansoft.great21.models.Game;
import com.adansoft.great21.models.HeartCard;
import com.adansoft.great21.models.HumanPlayer;
import com.adansoft.great21.models.JokerCard;
import com.adansoft.great21.models.Player;
import com.adansoft.great21.models.SpadeCard;
import com.adansoft.great21.restschemas.DeclareGameResult;
import com.adansoft.great21.restschemas.ShowGameResult;

public class CardUtility {

	public final static String JOKERINTERPRET_VALIDSEQUENCE = "Valid Sequence";
	public final static String JOKERINTERPRET_INVALIDSEQUENCE = "InValid Sequence";
	public final static String JOKERINTERPRET_VALIDTRIPQUADR = "Valid Triplet/Quadreplet";
	public final static String JOKERINTERPRET_INVALIDTRIPQUADR = "InValid Triplet/Quadreplet";
	public final static String JOKERINTERPRET_INVALIDGENERIC = "Improper Meld Group";

	private static SpadeCard[] createSpadeCardChunk(int Deckid) {
		SpadeCard[] cardchunk = new SpadeCard[13];
		cardchunk[0] = new SpadeCard(Card.DISPLAY_ONE, 10, Deckid,
				Card.STATUS_UNASSIGNED);
		cardchunk[1] = new SpadeCard(Card.DISPLAY_TWO, 2, Deckid,
				Card.STATUS_UNASSIGNED);
		cardchunk[2] = new SpadeCard(Card.DISPLAY_THREE, 3, Deckid,
				Card.STATUS_UNASSIGNED);
		cardchunk[3] = new SpadeCard(Card.DISPLAY_FOUR, 4, Deckid,
				Card.STATUS_UNASSIGNED);
		cardchunk[4] = new SpadeCard(Card.DISPLAY_FIVE, 5, Deckid,
				Card.STATUS_UNASSIGNED);
		cardchunk[5] = new SpadeCard(Card.DISPLAY_SIX, 6, Deckid,
				Card.STATUS_UNASSIGNED);
		cardchunk[6] = new SpadeCard(Card.DISPLAY_SEVEN, 7, Deckid,
				Card.STATUS_UNASSIGNED);
		cardchunk[7] = new SpadeCard(Card.DISPLAY_EIGHT, 8, Deckid,
				Card.STATUS_UNASSIGNED);
		cardchunk[8] = new SpadeCard(Card.DISPLAY_NINE, 9, Deckid,
				Card.STATUS_UNASSIGNED);
		cardchunk[9] = new SpadeCard(Card.DISPLAY_TEN, 10, Deckid,
				Card.STATUS_UNASSIGNED);
		cardchunk[10] = new SpadeCard(Card.DISPLAY_ELEVEN, 10, Deckid,
				Card.STATUS_UNASSIGNED);
		cardchunk[11] = new SpadeCard(Card.DISPLAY_TWELVE, 10, Deckid,
				Card.STATUS_UNASSIGNED);
		cardchunk[12] = new SpadeCard(Card.DISPLAY_THIRTEEN, 10, Deckid,
				Card.STATUS_UNASSIGNED);

		return cardchunk;
	}

	private static HeartCard[] createHeartCardChunk(int Deckid) {
		HeartCard[] cardchunk = new HeartCard[13];
		cardchunk[0] = new HeartCard(Card.DISPLAY_ONE, 10, Deckid,
				Card.STATUS_UNASSIGNED);
		cardchunk[1] = new HeartCard(Card.DISPLAY_TWO, 2, Deckid,
				Card.STATUS_UNASSIGNED);
		cardchunk[2] = new HeartCard(Card.DISPLAY_THREE, 3, Deckid,
				Card.STATUS_UNASSIGNED);
		cardchunk[3] = new HeartCard(Card.DISPLAY_FOUR, 4, Deckid,
				Card.STATUS_UNASSIGNED);
		cardchunk[4] = new HeartCard(Card.DISPLAY_FIVE, 5, Deckid,
				Card.STATUS_UNASSIGNED);
		cardchunk[5] = new HeartCard(Card.DISPLAY_SIX, 6, Deckid,
				Card.STATUS_UNASSIGNED);
		cardchunk[6] = new HeartCard(Card.DISPLAY_SEVEN, 7, Deckid,
				Card.STATUS_UNASSIGNED);
		cardchunk[7] = new HeartCard(Card.DISPLAY_EIGHT, 8, Deckid,
				Card.STATUS_UNASSIGNED);
		cardchunk[8] = new HeartCard(Card.DISPLAY_NINE, 9, Deckid,
				Card.STATUS_UNASSIGNED);
		cardchunk[9] = new HeartCard(Card.DISPLAY_TEN, 10, Deckid,
				Card.STATUS_UNASSIGNED);
		cardchunk[10] = new HeartCard(Card.DISPLAY_ELEVEN, 10, Deckid,
				Card.STATUS_UNASSIGNED);
		cardchunk[11] = new HeartCard(Card.DISPLAY_TWELVE, 10, Deckid,
				Card.STATUS_UNASSIGNED);
		cardchunk[12] = new HeartCard(Card.DISPLAY_THIRTEEN, 10, Deckid,
				Card.STATUS_UNASSIGNED);

		return cardchunk;
	}

	private static ClubCard[] createClubCardChunk(int Deckid) {
		ClubCard[] cardchunk = new ClubCard[13];
		cardchunk[0] = new ClubCard(Card.DISPLAY_ONE, 10, Deckid,
				Card.STATUS_UNASSIGNED);
		cardchunk[1] = new ClubCard(Card.DISPLAY_TWO, 2, Deckid,
				Card.STATUS_UNASSIGNED);
		cardchunk[2] = new ClubCard(Card.DISPLAY_THREE, 3, Deckid,
				Card.STATUS_UNASSIGNED);
		cardchunk[3] = new ClubCard(Card.DISPLAY_FOUR, 4, Deckid,
				Card.STATUS_UNASSIGNED);
		cardchunk[4] = new ClubCard(Card.DISPLAY_FIVE, 5, Deckid,
				Card.STATUS_UNASSIGNED);
		cardchunk[5] = new ClubCard(Card.DISPLAY_SIX, 6, Deckid,
				Card.STATUS_UNASSIGNED);
		cardchunk[6] = new ClubCard(Card.DISPLAY_SEVEN, 7, Deckid,
				Card.STATUS_UNASSIGNED);
		cardchunk[7] = new ClubCard(Card.DISPLAY_EIGHT, 8, Deckid,
				Card.STATUS_UNASSIGNED);
		cardchunk[8] = new ClubCard(Card.DISPLAY_NINE, 9, Deckid,
				Card.STATUS_UNASSIGNED);
		cardchunk[9] = new ClubCard(Card.DISPLAY_TEN, 10, Deckid,
				Card.STATUS_UNASSIGNED);
		cardchunk[10] = new ClubCard(Card.DISPLAY_ELEVEN, 10, Deckid,
				Card.STATUS_UNASSIGNED);
		cardchunk[11] = new ClubCard(Card.DISPLAY_TWELVE, 10, Deckid,
				Card.STATUS_UNASSIGNED);
		cardchunk[12] = new ClubCard(Card.DISPLAY_THIRTEEN, 10, Deckid,
				Card.STATUS_UNASSIGNED);

		return cardchunk;
	}

	private static DiamondCard[] createDiamondCardChunk(int Deckid) {
		DiamondCard[] cardchunk = new DiamondCard[13];
		cardchunk[0] = new DiamondCard(Card.DISPLAY_ONE, 10, Deckid,
				Card.STATUS_UNASSIGNED);
		cardchunk[1] = new DiamondCard(Card.DISPLAY_TWO, 2, Deckid,
				Card.STATUS_UNASSIGNED);
		cardchunk[2] = new DiamondCard(Card.DISPLAY_THREE, 3, Deckid,
				Card.STATUS_UNASSIGNED);
		cardchunk[3] = new DiamondCard(Card.DISPLAY_FOUR, 4, Deckid,
				Card.STATUS_UNASSIGNED);
		cardchunk[4] = new DiamondCard(Card.DISPLAY_FIVE, 5, Deckid,
				Card.STATUS_UNASSIGNED);
		cardchunk[5] = new DiamondCard(Card.DISPLAY_SIX, 6, Deckid,
				Card.STATUS_UNASSIGNED);
		cardchunk[6] = new DiamondCard(Card.DISPLAY_SEVEN, 7, Deckid,
				Card.STATUS_UNASSIGNED);
		cardchunk[7] = new DiamondCard(Card.DISPLAY_EIGHT, 8, Deckid,
				Card.STATUS_UNASSIGNED);
		cardchunk[8] = new DiamondCard(Card.DISPLAY_NINE, 9, Deckid,
				Card.STATUS_UNASSIGNED);
		cardchunk[9] = new DiamondCard(Card.DISPLAY_TEN, 10, Deckid,
				Card.STATUS_UNASSIGNED);
		cardchunk[10] = new DiamondCard(Card.DISPLAY_ELEVEN, 10, Deckid,
				Card.STATUS_UNASSIGNED);
		cardchunk[11] = new DiamondCard(Card.DISPLAY_TWELVE, 10, Deckid,
				Card.STATUS_UNASSIGNED);
		cardchunk[12] = new DiamondCard(Card.DISPLAY_THIRTEEN, 10, Deckid,
				Card.STATUS_UNASSIGNED);

		return cardchunk;
	}

	private static JokerCard[] createJokers(int Deckid, int numofJokers) {
		JokerCard[] cardchunk = new JokerCard[numofJokers];
		for (int i = 0; i < numofJokers; i++) {
			cardchunk[i] = new JokerCard("Joker", i, Deckid,
					Card.STATUS_UNASSIGNED);
		}
		return cardchunk;
	};

	public static Deck createDeck(int Deckid, boolean withJoker, int numofJokers) {
		Deck deck = new Deck(Deckid, withJoker);

		deck.setClubCard(createClubCardChunk(Deckid));
		deck.setDiamondCard(createDiamondCardChunk(Deckid));
		deck.setHeartCard(createHeartCardChunk(Deckid));
		deck.setSpadeCard(createSpadeCardChunk(Deckid));
		if (withJoker) {
			deck.setJokerCard(createJokers(Deckid, numofJokers));
			deck.setJokerAvailable(true);
		}

		return deck;
	}

	public static Card[] shuffleCards(int numberofDecks, boolean withJokers,
			int numofJokers) {
		Deck[] decklist = new Deck[numberofDecks];

		Card[] cardlist = null;
		if (withJokers)
			cardlist = new Card[52 * numberofDecks + numberofDecks * numofJokers];
		else
			cardlist = new Card[52 * numberofDecks];

		for (int i = 0; i < numberofDecks; i++) {
			decklist[i] = createDeck(i, withJokers, numofJokers);
		}

		
		
		if (withJokers) {
			int jokerposarr[] = new int[numberofDecks * numofJokers];
			for(int i=0;i<numberofDecks * numofJokers;i++)
			{
				jokerposarr[i] = NumberUtility.generateRandomNumber(1,52 * numberofDecks + numofJokers);
			}
			
			int jokercount = 1;
			for (int i = 0; i < 52 * numberofDecks + numberofDecks * numofJokers; i++) {
				while (true) {
					Card inputcard = null;
					int deckid = NumberUtility.generateRandomNumber(1,numberofDecks);
					int flowerid = NumberUtility.generateRandomNumber(1, 4);
					int valueid = NumberUtility.generateRandomNumber(1, 13);
					if (flowerid == 1)
						inputcard = decklist[deckid - 1].getSpadeCard()[valueid - 1];
					if (flowerid == 2)
						inputcard = decklist[deckid - 1].getHeartCard()[valueid - 1];
					if (flowerid == 3)
						inputcard = decklist[deckid - 1].getClubCard()[valueid - 1];
					if (flowerid == 4)
						inputcard = decklist[deckid - 1].getDiamondCard()[valueid - 1];
					for(int j=0;j<jokerposarr.length;j++)
					{
						if(i == jokerposarr[j])
						{
							//int newdeckid = jokercount % numberofDecks;
								int newdeckid = jokercount/numofJokers;
								int index = jokercount%numofJokers;
							   inputcard = decklist[deckid - 1].getJokerCard()[index];
							  jokercount++;
							
							
						}
					}
					/*if (flowerid == 5)
					{ 
						if(jokercount > 0 && i <=26 )
							continue;
						if(jokercount < numofJokers)
						{
						   inputcard = decklist[deckid - 1].getJokerCard()[jokercount];
						   jokercount++;
						}
						else
							continue;
					}*/

					if (checkCardExists(cardlist, inputcard, i))
						continue;
					else {
						cardlist[i] = inputcard;
						break;
					}
				}
			}
		} else {
			for (int i = 0; i < 52 * numberofDecks; i++) {
				while (true) {
					Card inputcard = null;
					int deckid = NumberUtility.generateRandomNumber(1,
							numberofDecks);
					int flowerid = NumberUtility.generateRandomNumber(1, 4);
					int valueid = NumberUtility.generateRandomNumber(1, 13);
					if (flowerid == 1)
						inputcard = decklist[deckid - 1].getSpadeCard()[valueid - 1];
					if (flowerid == 2)
						inputcard = decklist[deckid - 1].getHeartCard()[valueid - 1];
					if (flowerid == 3)
						inputcard = decklist[deckid - 1].getClubCard()[valueid - 1];
					if (flowerid == 4)
						inputcard = decklist[deckid - 1].getDiamondCard()[valueid - 1];

					if (checkCardExists(cardlist, inputcard, i))
						continue;
					else {
						cardlist[i] = inputcard;
						break;
					}
				}
			}
		}
		return cardlist;

	}

	private static boolean checkCardExists(Card[] cardlist, Card inputcard,
			int index) {

		boolean result = false;
		for (int i = 0; i < cardlist.length; i++) {
			if (cardlist[i] == null)
				continue;
			if (inputcard.getInstanceID().equals(cardlist[i].getInstanceID())) {
				result = true;
				break;
			}
		}
		return result;
	}

	public static int distributeCards(ArrayList<Player> playerlist,
			Card[] cardlist, int numofcardstodistribute) {
		int countofdistcards = playerlist.size() * numofcardstodistribute;
		int i = 0;
		for (; i < countofdistcards;) {
			for (int j = 0; j < playerlist.size(); j++) {
				cardlist[i].setStatus(Card.STATUS_ASSIGNED);
				playerlist.get(j).assignCard(cardlist[i]);
				i++;
			}
		}
		return i;
	}

	public static Card pickJoker(Card[] cardlist, int numberofDecks) {
		Card card = null;
		int jokerid = NumberUtility.generateRandomNumber(0,
				(numberofDecks * 52) - 1);
		boolean jokerpicked = false;
		while (!jokerpicked) {
			card = cardlist[jokerid];
			if (card.getStatus().equals(Card.STATUS_UNASSIGNED)) {
				jokerpicked = true;
				card.setStatus(Card.STATUS_JOKER);
			} else {
				jokerid = NumberUtility.generateRandomNumber(0,
						(numberofDecks * 52) - 1);
			}
		}
		return card;
	}

	public static boolean checkSequence(Card[] cardlist) {
		boolean result = true;
		if (cardlist == null || cardlist.length < 3)
			return false;
		else {
			if (checkCardwithSameFlower(cardlist)) {
				Arrays.sort(cardlist);
				for (int i = 0; i < cardlist.length - 1; i++) {
					Card curcard = cardlist[i];
					int curvalue = curcard.getInstrinsicValue();
					Card nextcard = cardlist[i + 1];
					int nextvalue = nextcard.getInstrinsicValue();
					if (curvalue + 1 == nextvalue) {
						continue;
					} else {
						if (cardlist.length == 3) {
							if (curcard.getInstrinsicValue() == 1
									&& nextcard.getInstrinsicValue() == 12)
								continue;
						}
						if (cardlist.length == 4) {
							if (curcard.getInstrinsicValue() == 1
									&& nextcard.getInstrinsicValue() == 11)
								continue;
						}
						if (cardlist.length == 5) {
							if (curcard.getInstrinsicValue() == 1
									&& nextcard.getInstrinsicValue() == 10)
								continue;
						}
						result = false;
						break;
					}

				}
			} else {
				result = false;
			}
		}

		return result;
	}

	private static boolean checkCardwithSameFlower(Card[] cardlist) {
		if (cardlist[0] instanceof ClubCard)
			return checkAllClubs(cardlist);
		else if (cardlist[0] instanceof DiamondCard)
			return checkAllDiamond(cardlist);
		else if (cardlist[0] instanceof SpadeCard)
			return checkAllSpade(cardlist);
		else if (cardlist[0] instanceof HeartCard)
			return checkAllHearts(cardlist);
		else
			return false;
	}

	private static boolean checkAllClubs(Card[] cardlist) {
		boolean result = true;
		for (Card card : cardlist) {
			if (!(card instanceof ClubCard))
				return false;
		}
		return result;
	}

	private static boolean checkAllSpade(Card[] cardlist) {
		boolean result = true;
		for (Card card : cardlist) {
			if (!(card instanceof SpadeCard))
				return false;
		}
		return result;
	}

	private static boolean checkAllDiamond(Card[] cardlist) {
		boolean result = true;
		for (Card card : cardlist) {
			if (!(card instanceof DiamondCard))
				return false;
		}
		return result;
	}

	private static boolean checkAllHearts(Card[] cardlist) {
		boolean result = true;
		for (Card card : cardlist) {
			if (!(card instanceof HeartCard))
				return false;
		}
		return result;
	}

	public static DeclareGameResult checkDeclareGame(
			HashMap<String, Card[]> meldlist, Card jokerCard, String gameType) {
		DeclareGameResult gameresult = new DeclareGameResult();

		// Check Original Sequence.

		boolean isOrigSeqPresent = false;

		for (String key : meldlist.keySet()) {
			boolean result = CardUtility.checkSequence(meldlist.get(key));
			if (result == true) {
				// origSequenceGroupName = key;
				isOrigSeqPresent = true;
				break;
			}
		}
		if (!isOrigSeqPresent) {
			gameresult.setValid(false);
			gameresult.setMessage("No Original Sequence Present");
			return gameresult;
		}

		if (gameType.equals(GameListConstants.GAMELIST_SEVENCARD_CLOSED_TYPE)
				|| gameType
						.equals(GameListConstants.GAMELIST_SEVENCARD_OPEN_TYPE)) {
			return validateSevenCardRummy(meldlist, jokerCard);

		}
		if (gameType
				.equals(GameListConstants.GAMELIST_THIRTEENCARD_CLOSED_TYPE)
				|| gameType
						.equals(GameListConstants.GAMELIST_THIRTEENCARD_OPEN_TYPE)) {
			return validateThirteenCardRummy(meldlist, jokerCard);

		}

		return gameresult;
	}

	private static DeclareGameResult validateSevenCardRummy(
			HashMap<String, Card[]> meldlist, Card jokerCard) {
		DeclareGameResult gameresult = new DeclareGameResult();
		HashMap<String, String> groupresult = new HashMap<String, String>();
		for (String key : meldlist.keySet()) {
			String validationResult = CardUtility.interpretJokerandValidate(
					meldlist.get(key), jokerCard);
			if (validationResult.equals(JOKERINTERPRET_INVALIDGENERIC)
					|| validationResult.equals(JOKERINTERPRET_INVALIDSEQUENCE)
					|| validationResult.equals(JOKERINTERPRET_INVALIDTRIPQUADR)) {
				gameresult.setValid(false);
				gameresult
						.setMessage("InCorrect Declaration , See individual Meld for specific errors");
			}
			groupresult.put(key, validationResult);
		}
		gameresult.setGroupresults(groupresult);
		if (gameresult.isValid())
			gameresult.setMessage("Successful declaration");
		return gameresult;
	}

	private static DeclareGameResult validateThirteenCardRummy(
			HashMap<String, Card[]> meldlist, Card jokerCard) {
		int sequencecount = 0;
		DeclareGameResult gameresult = new DeclareGameResult();
		HashMap<String, String> groupresult = new HashMap<String, String>();
		for (String key : meldlist.keySet()) {
			String validationResult = CardUtility.interpretJokerandValidate(
					meldlist.get(key), jokerCard);
			if (validationResult.equals(JOKERINTERPRET_VALIDSEQUENCE)) {
				sequencecount++;
			}
			if (validationResult.equals(JOKERINTERPRET_INVALIDGENERIC)
					|| validationResult.equals(JOKERINTERPRET_INVALIDSEQUENCE)
					|| validationResult.equals(JOKERINTERPRET_INVALIDTRIPQUADR)) {
				gameresult.setValid(false);
				gameresult
						.setMessage("InCorrect Declaration , See individual Meld for specific errors");
			}
			groupresult.put(key, validationResult);

		}

		if (sequencecount < 2) {
			gameresult.setValid(false);
			gameresult
					.setMessage("Thirteen Card Rummy requires atleast 2 sequences");
		}
		gameresult.setGroupresults(groupresult);
		if (gameresult.isValid())
			gameresult.setMessage("Successful declaration");
		return gameresult;
	}

	public static boolean checkTripletorQuadraplets(Card[] cardlist) {
		boolean isclub = false, isheart = false, isdiamond = false, isspade = false;
		int countoftrue = 0;
		if (checkCardwithSameValue(cardlist)) {
			if (cardlist.length < 3 || cardlist.length > 4)
				return false;
			for (Card card : cardlist) {
				if (card.getFlower().equals(Card.FLOWER_CLUBS))
					isclub = true;
				if (card.getFlower().equals(Card.FLOWER_HEART))
					isheart = true;
				if (card.getFlower().equals(Card.FLOWER_DIAMOND))
					isdiamond = true;
				if (card.getFlower().equals(Card.FLOWER_SPADE))
					isspade = true;
			}
			if (isclub)
				countoftrue++;
			if (isheart)
				countoftrue++;
			if (isdiamond)
				countoftrue++;
			if (isspade)
				countoftrue++;
			if (cardlist.length == 3 && countoftrue == 3)
				return true;
			if (cardlist.length == 4 && countoftrue == 4)
				return true;
		}

		return false;
	}

	private static boolean checkCardwithSameValue(Card[] cardlist) {
		if (cardlist == null || cardlist.length == 0)
			return false;
		String firstcardValue = cardlist[0].getDisplayValue();
		for (Card curcard : cardlist) {
			String curcardvalue = curcard.getDisplayValue();
			if (firstcardValue.equals(curcardvalue))
				continue;
			else
				return false;
		}
		return true;
	}

	public static String interpretJokerandValidate(Card[] cardlist,
			Card jokerCard) {
		String jokerValue = jokerCard.getDisplayValue();
		// System.out.println("Excluded Cards : ");
		// showCards(excludeJokerfromCardList(cardlist,jokerValue));
		Card[] excludedCardList = excludeJokerfromCardList(cardlist, jokerValue);
		if (cardlist.length >= 3 && checkCardwithSameFlower(excludedCardList)) {
			System.out.println("Will be interpreted as Sequence number");
			boolean result = isSequenceCantUseJoker(excludedCardList,
					(cardlist.length - excludedCardList.length));
			if (!result)
				return JOKERINTERPRET_VALIDSEQUENCE;
			else
				return JOKERINTERPRET_INVALIDSEQUENCE;
		} else if (cardlist.length >= 3 && cardlist.length <= 4
				&& checkCardwithSameValue(excludedCardList)) {
			if (!checkAnyDuplicateFlower(excludedCardList))
				return JOKERINTERPRET_VALIDTRIPQUADR;
			else
				return JOKERINTERPRET_INVALIDTRIPQUADR;
		} else {
			return JOKERINTERPRET_INVALIDGENERIC;
		}

	}

	private static boolean checkAnyDuplicateFlower(Card[] cardlist) {
		int spadeflowercount = 0, diamondflowerCount = 0, clubflowercount = 0, heartflowercount = 0;
		for (Card card : cardlist) {
			if (card.getFlower().equals(Card.FLOWER_CLUBS))
				clubflowercount++;
			if (card.getFlower().equals(Card.FLOWER_SPADE))
				spadeflowercount++;
			if (card.getFlower().equals(Card.FLOWER_DIAMOND))
				diamondflowerCount++;
			if (card.getFlower().equals(Card.FLOWER_HEART))
				heartflowercount++;
		}
		if (clubflowercount > 1 || spadeflowercount > 1
				|| diamondflowerCount > 1 || heartflowercount > 1)
			return true;
		else
			return false;
	}

	private static boolean isSequenceCantUseJoker(Card[] cardlist,
			int howmanyjokers) {
		int gap = -1;
		boolean result = false;
		Arrays.sort(cardlist);
		PrelimenaryCheckResult output = isDuplicateCardpresent(cardlist);
		if (output.isDuplicate())
			return true;
		for (int i = 0; i < cardlist.length - 1; i++) {

			if (cardlist[i].getInstrinsicValue() == 1)
				continue;
			gap = cardlist[i + 1].getInstrinsicValue()
					- cardlist[i].getInstrinsicValue();

			if (gap >= 2) {
				if (gap > howmanyjokers + 1) {
					result = true;
					return result;
				} else
					howmanyjokers = howmanyjokers - (gap - 1);

			}

		}
		if (output.isAPresent()) {
			if (output.isJQKPresent())
				gap = 14 - cardlist[cardlist.length - 1].getInstrinsicValue();
			else
				gap = cardlist[1].getInstrinsicValue() - 1;
			if (gap > howmanyjokers + 1)
				result = true;
		}

		return result;
	}

	private static PrelimenaryCheckResult isDuplicateCardpresent(Card[] cardlist) {
		PrelimenaryCheckResult result = new PrelimenaryCheckResult();
		for (int i = 0; i < cardlist.length; i++) {
			int curvalue = cardlist[i].getInstrinsicValue();
			if (i < cardlist.length - 1) {
				if (cardlist[i + 1].getInstrinsicValue()
						- cardlist[i].getInstrinsicValue() == 0)
					result.setDuplicate(true);
			}
			if (curvalue >= 10)
				result.setJQKPresent(true);
			if (curvalue == 1)
				result.setAPresent(true);
		}
		return result;
	}

	private static void updateInstrinsicValeu(Card[] cardlist) {
		boolean cardlisthasA = false;
		boolean anyCardmorethan10 = false;
		for (Card card : cardlist) {
			if (card.getInstrinsicValue() == 1)
				cardlisthasA = true;
			if (card.getInstrinsicValue() > 10)
				anyCardmorethan10 = true;
		}

		if (cardlisthasA && anyCardmorethan10) {
			for (int i = 0; i < cardlist.length; i++) {
				if (cardlist[i].getInstrinsicValue() == 1) {

				}
			}
		}

	}

	private static Card[] excludeJokerfromCardList(Card[] cardlist,
			String jokerValue) {
		ArrayList<Card> excludedCardList = new ArrayList<Card>();
		for (Card card : cardlist) {
			if(card.getFlower().equals(Card.FLOWER_WILD))
					continue;
			if (!card.getDisplayValue().equals(jokerValue)) {
				excludedCardList.add(card);
			}
		}
		return excludedCardList.toArray(new Card[excludedCardList.size()]);
	}

	private static void showCards(Card[] cardlist) {
		for (Card card : cardlist) {
			System.out.println("Card : " + card.getInstanceID());
		}
	}

	public static Card[] sortCards(Card[] inputcardlist) {
		if (inputcardlist == null || inputcardlist.length == 0)
			return inputcardlist;

		ArrayList<Card> spadeCardList = new ArrayList<Card>();
		ArrayList<Card> diamondCardList = new ArrayList<Card>();
		ArrayList<Card> clubCardList = new ArrayList<Card>();
		ArrayList<Card> heartCardList = new ArrayList<Card>();
		ArrayList<Card> jokerCardList = new ArrayList<Card>();

		for (Card card : inputcardlist) {
			if (card.getFlower().equals(Card.FLOWER_CLUBS))
				clubCardList.add(card);
			if (card.getFlower().equals(Card.FLOWER_DIAMOND))
				diamondCardList.add(card);
			if (card.getFlower().equals(Card.FLOWER_HEART))
				heartCardList.add(card);
			if (card.getFlower().equals(Card.FLOWER_SPADE))
				spadeCardList.add(card);
			if (card.getFlower().equals(Card.FLOWER_WILD))
				jokerCardList.add(card);
		}
		Card[] clubcards = clubCardList.toArray(new Card[clubCardList.size()]);
		Card[] diamondcards = diamondCardList.toArray(new Card[diamondCardList
				.size()]);
		Card[] heartcards = heartCardList
				.toArray(new Card[heartCardList.size()]);
		Card[] spadecards = spadeCardList
				.toArray(new Card[spadeCardList.size()]);
		Card[] jokercards = jokerCardList.toArray(new Card[jokerCardList.size()]);

		Arrays.sort(clubcards);
		Arrays.sort(diamondcards);
		Arrays.sort(heartcards);
		Arrays.sort(spadecards);
		Arrays.sort(jokercards);

		ArrayList<Card> outputlist = new ArrayList<Card>();

		outputlist.addAll(Arrays.asList(spadecards));
		outputlist.addAll(Arrays.asList(diamondcards));
		outputlist.addAll(Arrays.asList(clubcards));
		outputlist.addAll(Arrays.asList(heartcards));
		outputlist.addAll(Arrays.asList(jokercards));
		return outputlist.toArray(new Card[outputlist.size()]);
	}

	public static ShowGameResult showCards(HashMap<String, Card[]> meldlist,
			String gameMode, float perCardValue, Card JokerCard, int maxPoints) {
		// Check Original Sequence.
		ShowGameResult output = new ShowGameResult();
		boolean isOrigSeqPresent = false;

		for (String key : meldlist.keySet()) {
			boolean result = CardUtility.checkSequence(meldlist.get(key));
			if (result == true) {
				isOrigSeqPresent = true;
				break;
			}
		}

		if (gameMode.equals(Game.GAME_MODE_PERCARD)) {
			output.setGameMode(Game.GAME_MODE_PERCARD);
			output.setMoney(calcMoney(isOrigSeqPresent, meldlist, perCardValue,
					JokerCard));
		}
		if (gameMode.equals(Game.GAME_MODE_POINTS)) {
			output.setGameMode(Game.GAME_MODE_POINTS);
			output.setPoints(calcPoints(isOrigSeqPresent, meldlist, JokerCard,
					maxPoints));
		}

		return output;
	}

	private static float calcMoney(boolean isOrigSeqPresent,
			HashMap<String, Card[]> meldlist, float perCardValue, Card JokerCard) {
		if (!isOrigSeqPresent) {
			ArrayList<Card> calculatablecardlist = new ArrayList<Card>();
			for (String key : meldlist.keySet()) {
				Card[] list = excludeJokerfromCardList(meldlist.get(key),
						JokerCard.getDisplayValue());
				calculatablecardlist.addAll(Arrays.asList(list));
			}
			return calculatablecardlist.size() * perCardValue;
		} else {
			ArrayList<Card> calculatablecardlist = new ArrayList<Card>();
			for (String key : meldlist.keySet()) {
				Card[] cardlist = meldlist.get(key);
				String validationResult = interpretJokerandValidate(cardlist,
						JokerCard);
				if (validationResult.equals(JOKERINTERPRET_INVALIDGENERIC)
						|| validationResult
								.equals(JOKERINTERPRET_INVALIDSEQUENCE)
						|| validationResult
								.equals(JOKERINTERPRET_INVALIDTRIPQUADR)) {
					calculatablecardlist.addAll(Arrays.asList(cardlist));
				}
			}
			Card[] finalcardlist = excludeJokerfromCardList(
					calculatablecardlist.toArray(new Card[calculatablecardlist
							.size()]), JokerCard.getDisplayValue());
			return finalcardlist.length * perCardValue;
		}

	}

	private static int calcPoints(boolean isOrigSeqPresent,
			HashMap<String, Card[]> meldlist, Card JokerCard, int maxPoints) {
		if (!isOrigSeqPresent) {
			ArrayList<Card> calculatablecardlist = new ArrayList<Card>();
			for (String key : meldlist.keySet()) {
				Card[] list = excludeJokerfromCardList(meldlist.get(key),
						JokerCard.getDisplayValue());
				calculatablecardlist.addAll(Arrays.asList(list));
			}
			int points = 0;
			for (Card card : calculatablecardlist) {
				points = points + card.getCountValue();
			}
			if (points > maxPoints)
				return maxPoints;
			else
				return points;

		} else {
			ArrayList<Card> calculatablecardlist = new ArrayList<Card>();
			for (String key : meldlist.keySet()) {
				Card[] cardlist = meldlist.get(key);
				String validationResult = interpretJokerandValidate(cardlist,
						JokerCard);
				if (validationResult.equals(JOKERINTERPRET_INVALIDGENERIC)
						|| validationResult
								.equals(JOKERINTERPRET_INVALIDSEQUENCE)
						|| validationResult
								.equals(JOKERINTERPRET_INVALIDTRIPQUADR)) {
					calculatablecardlist.addAll(Arrays.asList(cardlist));
				}
			}
			Card[] finalcardlist = excludeJokerfromCardList(
					calculatablecardlist.toArray(new Card[calculatablecardlist
							.size()]), JokerCard.getDisplayValue());
			int points = 0;
			for (int i = 0; i < finalcardlist.length; i++) {
				points = points + finalcardlist[i].getCountValue();
			}
			if (points > maxPoints)
				return maxPoints;
			else
				return points;
		}

	}

}
