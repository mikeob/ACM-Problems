import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

/*
 * Dynamic Programming Problem
 * see problem spec: http://www.programming-challenges.com/pg.php?page=downloadproblem&probid=111101&format=html
 * http://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&page=show_problem&problem=1072
 */
public class IsBiggerSmarter {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		ArrayList<Elephant> e = new ArrayList<Elephant>();
		int index = 0;

		while (in.hasNext()) {
			int size = in.nextInt();
			int iq = in.nextInt();
			e.add(new Elephant(index + 1, size, iq));
			index++;
		}

		Collections.sort(e);
		solve(e);

	}

	/*
	 * Views it as longest increasing subsequence with slightly different
	 * constraints.
	 */
	public static void solve(ArrayList<Elephant> e) {
		int N = e.size();

		int[] DP = new int[N];
		int[] prev = new int[N];
		int bestEnd = -1;
		int longest = 0;

		Arrays.fill(DP, 1);
		Arrays.fill(prev, -1);

		DP[0] = 0;

		for (int i = 1; i < N; i++) {
			for (int j = 0; j < i; j++) {
				// Constraints: To add must have lower IQ and higher weight
				if (DP[j] + 1 > DP[i] && e.get(j).iq > e.get(i).iq
						&& e.get(j).w < e.get(i).w) {
					DP[i] = DP[j] + 1;
					prev[i] = j;
				}
			}

			if (longest < DP[i]) {
				longest = DP[i];
				bestEnd = i;
			}
		}

		// Construct the answer output
		StringBuilder ans = new StringBuilder();
		while (bestEnd != -1) {
			ans.insert(0, e.get(bestEnd).index + "\n");
			bestEnd = prev[bestEnd];
		}
		System.out.println(longest);
		System.out.print(ans.toString());
	}

	public static class Elephant implements Comparable<Elephant> {
		int w, iq, index;

		public Elephant(int index, int w, int iq) {
			this.index = index;
			this.w = w;
			this.iq = iq;
		}

		/*
		 * Ordered by ascending weights
		 */
		public int compareTo(Elephant other) {
			return Integer.compare(w, other.w);
		}
	}
}
