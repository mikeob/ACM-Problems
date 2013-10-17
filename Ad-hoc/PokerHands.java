import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class PokerHands {

	enum HandType {
		HIGH_CARD(1), PAIR(2), TWO_PAIR(3), THREE_OF_KIND(4), STRAIGHT(5), FLUSH(
				6), FULL_HOUSE(7), FOUR_OF_KIND(8), STRAIGHT_FLUSH(9);

		final int value;

		private HandType(int value) {
			this.value = value;
		}

	}

	enum Suit {
		HEART, DIAMOND, SPADE, CLUB;
	}

	static final int ACE = 14;
	static final int KING = 13;
	static final int QUEEN = 12;
	static final int JACK = 11;

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		while (in.hasNext()) {

			Card[][] hands = new Card[2][5];

			// Input cards
			for (int handNum = 0; handNum < 2; handNum++) {

				for (int i = 0; i < 5; i++) {
					String c = in.next();

					// Determine Value
					int value = determineValue(c.charAt(0));

					// Determine Suit
					Suit suit = determineSuit(c.charAt(1));

					hands[handNum][i] = new Card(value, suit);
				}
			}

			determineWinner(hands[0], hands[1]);

		}

	}

	public static void determineWinner(Card[] black, Card[] white) {
		Hand blackHand = determineHandType(black);
		Hand whiteHand = determineHandType(white);

		String ans = "";

		if (blackHand.type == whiteHand.type) {
			int compareResult = compareSameType(blackHand, whiteHand);
			if (compareResult == 0) {
				ans = "Tie.";
			} else if (compareResult < 0) {
				ans = "White wins.";
			} else {
				ans = "Black wins.";
			}
		} else if (blackHand.type.value > whiteHand.type.value) {
			ans = "Black wins.";
		} else {
			ans = "White wins.";
		}

		System.out.println(ans);

	}

	public static int compareSameType(Hand black, Hand white) {

		HandType type = black.type;

		switch (type) {
		case PAIR:
			if (black.pairs[0] != white.pairs[0]) {
				return black.pairs[0] - white.pairs[0];
			}
			for (int i = 0; i < 3; i++) {
				int dif = black.nonPairs.get(i) - white.nonPairs.get(i);
				if (dif != 0) {
					return dif;
				}
			}
			return 0;
		case TWO_PAIR:
			for (int i = 0; i < 2; i++) {
				int dif = black.pairs[i] - white.pairs[i];
				if (dif != 0)
					return dif;

			}
			return black.nonPairs.get(0) - white.nonPairs.get(0);

			// Ranked by the value of the triple/quad
		case THREE_OF_KIND:
		case FOUR_OF_KIND:
		case FULL_HOUSE:
			return black.threeOrFour - white.threeOrFour;
		case STRAIGHT_FLUSH:
		case STRAIGHT:
			return black.nonPairs.get(0) - white.nonPairs.get(0);
		case HIGH_CARD:
		case FLUSH:
			for (int i = 0; i < 5; i++) {
				int dif = black.nonPairs.get(i) - white.nonPairs.get(i);
				if (dif != 0) {
					return dif;
				}
			}
			return 0;
		}

		// System.err.println("ERROR");
		return 0;
	}

	public static int determineValue(char c) {
		int value;
		switch (c) {
		case 'A':
			value = ACE;
			break;
		case 'K':
			value = KING;
			break;
		case 'Q':
			value = QUEEN;
			break;
		case 'J':
			value = JACK;
			break;
		case 'T':
			value = 10;
			break;
		default:
			value = c - '0';
			break;
		}

		return value;
	}

	public static Suit determineSuit(char c) {
		Suit suit = null;
		switch (c) {
		case 'H':
			suit = Suit.HEART;
			break;
		case 'D':
			suit = Suit.DIAMOND;
			break;
		case 'S':
			suit = Suit.SPADE;
			break;
		case 'C':
			suit = Suit.CLUB;
			break;
		}

		return suit;
	}

	public static Hand determineHandType(Card[] cards) {
//		System.out.print("determineType: ");

		// Force to be increasing
		Arrays.sort(cards);

		Suit suit = cards[0].suit;
		boolean allSameSuit = true;
		int[] counts = new int[15];
		int prevValue = cards[0].value - 1;
		boolean increasing = true;

		// Walk the cards, collecting information
		// about suits and counts
		for (int i = 0; i < cards.length; i++) {
			int val = cards[i].value;
			if (suit != cards[i].suit) {
				allSameSuit = false;
			}
			if (prevValue + 1 != val) {
				increasing = false;
			}
			counts[val]++;
			prevValue = val;

		}

		// Examine counts
		int fourOfKind = 0;
		int threeOfKind = 0;
		int pair1 = 0;
		int pair2 = 0;
		ArrayList<Integer> singleCards = new ArrayList<Integer>();

		for (int i = 0; i < counts.length; i++) {
			switch (counts[i]) {
			case 4:
				fourOfKind = i;
				break;
			case 3:
				threeOfKind = i;
				break;
			case 2:
				if (pair1 == 0) {
					pair1 = i;
				} else {
					pair2 = i;
				}
				break;
			case 1:
				singleCards.add(i);
				break;
			}
		}

		// Make sure they're decreasing
		Collections.sort(singleCards);
		Collections.reverse(singleCards);

		// --- Now use info to determine hand! ---
		HandType type = null;

		if (increasing && allSameSuit)
			type = HandType.STRAIGHT_FLUSH;
		else if (allSameSuit)
			type = HandType.FLUSH;
		else if (fourOfKind > 0)
			type = HandType.FOUR_OF_KIND;
		else if (threeOfKind > 0 && pair1 > 0)
			type = HandType.FULL_HOUSE;
		else if (increasing)
			type = HandType.STRAIGHT;
		else if (threeOfKind > 0)
			type = HandType.THREE_OF_KIND;
		else if (pair2 > 0)
			type = HandType.TWO_PAIR;
		else if (pair1 > 0)
			type = HandType.PAIR;
		else
			type = HandType.HIGH_CARD;
		
//		System.out.println(type.value);

		int[] pairs = new int[2];
		pairs[0] = Math.max(pair1, pair2);
		pairs[1] = Math.min(pair1, pair2);
		int threeOrFour = Math.max(threeOfKind, fourOfKind);

		return new Hand(cards, type, singleCards, pairs, threeOrFour);
	}

	static class Card implements Comparable<Card> {
		int value;
		Suit suit;

		public Card(int value, Suit suit) {
			this.value = value;
			this.suit = suit;
		}

		@Override
		public int compareTo(Card o) {
			return Integer.compare(value, o.value);
		}

	}

	static class Hand {
		Card[] cards;
		ArrayList<Integer> nonPairs;
		int[] pairs;
		int threeOrFour; // The number of the card that occurs 3 or 4 times
		HandType type;

		public Hand(Card[] cards, HandType type, ArrayList<Integer> nonPairs,
				int[] pairs, int threeOrFour) {
			this.type = type;
			this.cards = cards;
			this.nonPairs = nonPairs;
			this.pairs = pairs;
			this.threeOrFour = threeOrFour;
		}

	}
}
