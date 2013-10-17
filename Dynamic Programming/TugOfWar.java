import java.util.BitSet;
import java.util.Scanner;

public class TugOfWar {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		int T = in.nextInt();

		for (int t = 0; t < T; t++) {

			int N = in.nextInt();
			int biggest = 0;
			int[] weights = new int[N];
			for (int i = 0; i < N; i++) {
				weights[i] = in.nextInt();
				biggest = Math.max(weights[i], biggest);
			}

			solve(weights, biggest);
			if (t != T - 1)
				System.out.println();
		}
	}

	public static void solve(int[] weights, int biggest) {

		int N = weights.length;
		BitSet[] dp = new BitSet[biggest * N + 1];

		// Init our structure
		for (int i = 0; i < dp.length; i++) {
			dp[i] = new BitSet(N + 1);
		}

		int totalWeight = 0;
		int maxWeight = weights[0];

		// For each person in the group
		for (int i = 0; i < weights.length; i++) {
			int curWeight = weights[i];
			totalWeight += curWeight;

			// Try to combine with others in the group
			for (int j = dp.length - 1; j >= 0; j--) {

				if (!dp[j].isEmpty()) {
					copyAndShiftByOne(dp[j + curWeight], dp[j]);
					maxWeight = Math.max(maxWeight, j + curWeight);
				}
				if (j == curWeight) {
					dp[j].set(1);
				}
			}
		}

		boolean odd = (N % 2 == 0) ? false : true;

		// Search our DP structure for the closest weight an even division
		for (int i = totalWeight / 2; i >= 0; i--) {
			if (dp[i].get(N / 2)) {
				System.out.println(i + " " + (totalWeight - i));
				return;
			}

			if (odd && dp[i].get(N / 2 + 1)) {
				System.out.println(i + " " + (totalWeight - i));
				return;
			}
		}
	}

	/*
	 * Copies in's BitSet configuration to cur, except shifted to the left by
	 * one.
	 */
	static void copyAndShiftByOne(BitSet cur, BitSet in) {

		for (int i = 0; i < in.size(); i++) {
			if (in.get(i)) {
				cur.set(i + 1);
			}
		}
	}

}