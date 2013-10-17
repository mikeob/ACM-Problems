import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class ShoemakerProblem {

	static int N;

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		int T = in.nextInt();
		in.nextLine(); // Consume empty lines
		in.nextLine();

		for (int i = 0; i < T; i++) {

			N = in.nextInt();
			int[] time = new int[N];
			int[] cost = new int[N];
			int initCostPerDay = 0;

			for (int j = 0; j < N; j++) {
				time[j] = in.nextInt();
				cost[j] = in.nextInt();
				initCostPerDay += cost[j];
			}

			int[] ans = new int[N];

			solve(time, cost, initCostPerDay);
			
			if (i != T - 1)
			{
				System.out.println();
			}
		}
	}

	public static void solve(int[] time, int[] cost, int costPerDay) {

		ArrayList<Integer> orders = new ArrayList<Integer>();
		for (int i = 0; i < N; i++) {
			orders.add(i);
		}

		Collections.sort(orders, new OrderComparator(time, cost));

		for (int i = 0; i < N - 1; i++) {
			System.out.print((orders.get(i) + 1) + " ");
		}
		System.out.println(orders.get(N - 1) + 1);

	}

	static class OrderComparator implements Comparator<Integer> {
		int[] time;
		int[] cost;

		public OrderComparator(int[] time, int[] cost) {
			this.time = time;
			this.cost = cost;
		}

		@Override
		public int compare(Integer a, Integer b) {

			double weightA = ((double) cost[a]) / time[a];
			double weightB = ((double) cost[b]) / time[b];

			int ans = Double.compare(weightB, weightA);

			// Go with lexicographical ordering if they're equal
			if (ans == 0) {
				return a - b;
			}

			return ans;
		}

	}

}
