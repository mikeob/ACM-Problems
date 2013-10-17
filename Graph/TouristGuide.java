import java.util.Scanner;

public class TouristGuide {

	static int N;
	static int[][] e;

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int counter = 1;

		while (true) {
			N = in.nextInt();
			int R = in.nextInt();

			if (N == 0 && R == 0) {
				break;
			}

			e = new int[N][N];

			for (int i = 0; i < R; i++) {
				int to = in.nextInt() - 1;
				int from = in.nextInt() - 1;
				int capacity = in.nextInt();
				e[to][from] = capacity;
				e[from][to] = capacity;
			}

			int start = in.nextInt() - 1;
			int end = in.nextInt() - 1;
			int num = in.nextInt();

			int numTrips = findNumTrips(start, end, num);
			System.out.printf("Scenario #%d%n", counter);
			System.out.printf("Minimum Number of Trips = %d%n", numTrips);
			System.out.println();

			counter++;
		}
	}

	public static int findNumTrips(int start, int end, int num) {
		int maximin = FloydMaximin(start, end);
		maximin--; // Account for driver

		int ans = num / maximin;
		ans += (num % maximin > 0) ? 1 : 0; // If not evenly divided

		return ans;
	}

	public static int FloydMaximin(int start, int end) {

		int[][] d = new int[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				d[i][j] = e[i][j];
			}
		}

		for (int k = 0; k < N; k++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					d[i][j] = Math.max(d[i][j], Math.min(d[i][k], d[k][j]));
				}
			}
		}

		return d[start][end];
	}

}
