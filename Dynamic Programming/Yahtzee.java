import java.util.Arrays;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/*
 * Accepted on UVA site: 
 * http://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&page=show_problem&problem=1090
 * 
 */
public class Yahtzee {

	static Map<BitSet, int[]> cache = new HashMap<BitSet, int[]>();
	static int[][] rollValues;
	static int numStates = 0;

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);

		while (in.hasNext()) {

			// Input rolls
			int[][] rolls = new int[13][5];
			for (int i = 0; i < 13; i++) {
				for (int j = 0; j < 5; j++) {
					rolls[i][j] = in.nextInt();
				}
				Arrays.sort(rolls[i]);
			}

			// Precompute roll values
			rollValues = new int[13][14];
			for (int i = 0; i < 13; i++) {
				for (int j = 1; j <= 13; j++) {
					rollValues[i][j] = rollValue(rolls[i], j);
				}
			}
			numStates = 0;

			cache.clear();
			int[] solution = solve(new BitSet(13), 13);

			printAnswer(solution);
			// System.out.println("Num states " + numStates);
		}
	}

	// Prints the answer
	static void printAnswer(int[] solution) {
		int sum = 0;

		for (int i = 1; i < solution.length; i++) {
			sum += solution[i];
			System.out.print(solution[i] + " ");
		}
		System.out.println(sum);
	}

	// Debugging function
	static void printArray(int[] roll) {
		for (Integer I : roll) {
			System.out.print(I + " ");
		}
		System.out.println();
	}

	static int[] solve(BitSet used, int round) {

		int[] bestSolution = null;

		if (round == 0) {
			return new int[15];
		}

		// Have we already computed this?
		if (cache.containsKey(used)) {
			return cache.get(used);
		}

		int ans = -1;
		// Try each unused roll
		for (int i = 0; i < 13; i++) {

			// If roll already used, continue
			if (used.get(i)) {
				continue;
			}

			int roll = rollValues[i][round];

			// Clone used bitset
			BitSet clone = (BitSet) used.clone();
			clone.set(i, true);

			/*
			 * Recurse and get optimal solution given the remaining rolls
			 * available, starting at round + 1
			 */
			int[] solution = solve(clone, round - 1).clone();
			solution[round] = roll;
			int candidateValue = sumAll(solution);

			// Account for bonus
			if (round == 6 && candidateValue > 62) {
				solution[14] = 35;
				candidateValue += 35;
			}

			if (candidateValue >= ans) {
				ans = candidateValue;
				bestSolution = solution;
			}
		}
		// Memoize
		cache.put(used, bestSolution);

		return bestSolution;
	}

	// --------- Roll functions -------------

	static int rollValue(int[] roll, int round) {

		switch (round) {
		case 1: // sum 1
		case 2: // sum 2
		case 3: // sum 3
		case 4: // sum 4
		case 5: // sum 5
		case 6: // sum 6
			return sum(roll, round);
		case 7: // Sum All
			return sumAll(roll);
		case 8: // 3 of kind
		case 9: // 4 of kind
		case 10: // 5 of kind
			return nOfAKind(roll, round - 5);
		case 11: // short straight
			return shortStraight(roll);
		case 12: // long straight
			return longStraight(roll);
		case 13: // full house
			return fullHouse(roll);
		}

		// Weird default to catch bugs
		System.err.println("BAD");
		return -100000;
	}

	/*
	 * Given a roll, returns the sum of all n values thrown
	 */
	static int sum(int[] roll, int n) {
		int ans = 0;
		for (Integer i : roll) {
			ans += (i == n) ? n : 0;
		}
		return ans;
	}

	/*
	 * Sums all of the dice in a roll
	 */
	static int sumAll(int[] roll) {
		int ans = 0;
		for (Integer i : roll) {
			ans += i;
		}
		return ans;
	}

	/*
	 * Checks for a 4 length straight - 25 pts
	 */
	static int shortStraight(int[] roll) {

		int ans = 0;
		if ((roll[0] == roll[1] - 1) && (roll[1] == roll[2] - 1)
				&& (roll[2] == roll[3] - 1)) {
			ans = 25;
		}
		if ((roll[1] == roll[2] - 1) && (roll[2] == roll[3] - 1)
				&& (roll[3] == roll[4] - 1)) {
			ans = 25;
		}
		return ans;

	}

	/*
	 * Checks for a 5 length straight - 35 points
	 */
	static int longStraight(int[] roll) {
		int ans = 0;
		if (roll[0] == roll[1] - 1 && roll[1] == roll[2] - 1
				&& roll[2] == roll[3] - 1 && roll[3] == roll[4] - 1) {
			ans = 35;
		}
		return ans;
	}

	/*
	 * If roll is a full house, return 40.
	 */
	static int fullHouse(int[] roll) {
		int ans = 0;
		if ((roll[0] == roll[1])
				&& ((roll[2] == roll[3]) && roll[3] == roll[4])) {
			ans = 40;
		}
		if ((roll[0] == roll[1] && roll[1] == roll[2]) && (roll[3] == roll[4])) {
			ans = 40;
		}
		return ans;
	}

	/*
	 * Searches to see if n of a kind occur. 5 of a kind - 50 points 3 or 4 -
	 * sumOfAll points
	 */
	static int nOfAKind(int[] roll, int n) {
		boolean foundN = false;
		// Get frequency count
		int[] count = new int[7];
		for (Integer i : roll) {
			count[i]++;

			if (count[i] == n) {
				foundN = true;
			}
		}

		int ans = 0;

		if (!foundN) {
			ans = 0;
		} else if (n == 3 || n == 4) {
			ans = sumAll(roll);
		} else if (n == 5) {
			ans = 50;
		}

		return ans;
	}
}