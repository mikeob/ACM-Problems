import java.util.Arrays;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Scanner;

/*
 * Solution to 2012 Rocky Mountain - E Approximate Sorting
 * Dynamic Programming
 */
public class Sort {

	static int N;
	static char[][] rules;
	static HashMap<BitSet, int[]> cache = new HashMap<BitSet, int[]>();

	public static void main(String[] arg) {
		Scanner in = new Scanner(System.in);

		while (true) {
			N = in.nextInt();
			if (N == 0)
				return;
			in.nextLine();
			cache.clear();

			rules = new char[N][];

			for (int i = 0; i < N; i++) {
				rules[i] = in.nextLine().toCharArray();
			}

			int[] sol = dp(0, new BitSet(N));

			for (int i = 0; i < N; i++) {
				System.out.printf("%d", sol[i]);
				if (i != N - 1)
					System.out.print(" ");
			}
			System.out.println();
			System.out.println(calcCost(0, sol));
		}
	}

	public static int[] dp(int cur, BitSet used) {

		if (cache.containsKey(used)) {
			return cache.get(used).clone();
		}

		if (cur == N) {
			int[] blank = new int[N];
			Arrays.fill(blank, -1);
			return blank;
		}

		int[] best = null;
		int min = Integer.MAX_VALUE;

		// Attempt to place each non-used
		for (int i = 0; i < N; i++) {

			if (used.get(i))
				continue;

			used.set(i);
			int[] cand = dp(cur + 1, used);

			cand[cur] = i;
			int cost = calcCost(cur, cand);

			if (cost < min) {
				min = cost;
				best = cand;
			}
			used.set(i, false);
		}

		cache.put(used, best);
		return best.clone();
	}

	public static void printArr(int[] arr) {
		for (Integer i : arr) {
			System.out.print(i + " ");
		}
	}

	public static int calcCost(int cur, int[] cand) {
		int ans = 0;
		for (int i = cur; i < N; i++) {
			for (int j = i + 1; j < N; j++) {
				if (less(cand[j], cand[i]))
					ans++;
			}
		}
		return ans;
	}

	public static boolean less(int i, int j) {
		return rules[i][j] == '1';
	}
}
