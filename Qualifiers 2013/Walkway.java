import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class Walkway {

	static ArrayList<Stone> stones;
	static HashMap<Integer, List<Stone>> adj;
	static HashSet<Integer> used;
	static double cheapest;

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		while (true) {
			int N = in.nextInt();
			if (N == 0) {
				return;
			}

			stones = new ArrayList<Stone>();
			adj = new HashMap<Integer, List<Stone>>();
			cheapest = Double.MAX_VALUE;
			used = new HashSet<Integer>();

			for (int i = 0; i < N; i++) {
				int a = in.nextInt();
				int b = in.nextInt();
				int h = in.nextInt();
				Stone newstone = new Stone(a, b, h);
				stones.add(newstone);
				// if (adj.containsKey(a))
				// {
				//
				// }
				// else
				// {
				//
				// }
				// if (adj.containsKey(b))
			}

			int start = in.nextInt();
			int end = in.nextInt();

			solve(start, end);
		}

	}

	public static void solve(int start, int end) {

		backtrack(start, end, 0);

		System.out.printf("%.2f%n", cheapest * 2);

	}

	public static void backtrack(int cur, int end, double cost) {

		if (cur == end) {
			if (cost < cheapest)
				cheapest = cost;
		}

		if (cost > cheapest) {
			return;
		}

		for (int i = 0; i < stones.size(); i++) {

			if (used.contains(i))
				continue;

			Stone s = stones.get(i);

			used.add(i);
			if (s.a == cur) {
				backtrack(s.b, end, cost + s.area);
			} else if (s.b == cur) {
				backtrack(s.a, end, cost + s.area);
			}
			used.remove(i);

		}

	}

	static class Stone {
		int a, b, h;
		double area;

		public Stone(int a, int b, int h) {
			this.a = Math.min(a, b);
			this.b = Math.max(a, b);
			this.h = h;
			area = .5 * (a + b) * h;
			area = area / 100;
		}

	}

}
