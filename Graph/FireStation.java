import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class FireStation {

	static int[] initDist;

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		int T = in.nextInt();
		for (int t = 0; t < T; t++) {

			initDist = null;

			int F = in.nextInt();
			int I = in.nextInt();

			boolean[] stations = new boolean[I];

			for (int i = 0; i < F; i++) {
				stations[in.nextInt() - 1] = true;
			}

			// Initialize the Adjacency matrix
			int[][] e = new int[I][I];
			for (int i = 0; i < I; i++) {
				for (int j = 0; j < I; j++) {
					if (i == j)
						e[i][j] = 0;
					else
						e[i][j] = Integer.MAX_VALUE / 10;
				}
			}

			in.nextLine();
			String line = in.nextLine();
			while (!line.isEmpty()) {
				String[] a = line.split(" ");
				int to = Integer.parseInt(a[0]) - 1;
				int from = Integer.parseInt(a[1]) - 1;
				int dist = Integer.parseInt(a[2]);

				e[to][from] = dist;
				e[from][to] = dist;
				
				if (!in.hasNextLine())
					break;
				
				line = in.nextLine();
			}

			int min = Integer.MAX_VALUE;
			int minStation = 1;

			// Attempt to place a station at each vertex
			for (int i = 0; i < I; i++) {
				if (i == 0 || !stations[i]) {

					boolean[] clone = stations.clone();
					clone[i] = true;
					int maxVal = dijkstra(clone, e);

					if (maxVal < min) {
						min = maxVal;
						minStation = i + 1;
					}
				}
			}

			System.out.println(minStation);

			if (t != T - 1)
				System.out.println();
		}
	}

	/*
	 * Performs a modified dijkstra's, where every vertex with a station at it
	 * has a distance of 0.
	 */
	public static int dijkstra(boolean[] stations, int[][] e) {
		int E = e.length;

		int[] dist;

		// Initialize the default distances
		if (initDist == null) {
			dist = new int[E];

			for (int i = 0; i < E; i++) {
				if (stations[i])
					dist[i] = 0;
				else
					dist[i] = Integer.MAX_VALUE / 10;
			}
		} else {
			dist = initDist.clone();
		}

		Set<Integer> q = new HashSet<Integer>();
		for (int i = 0; i < E; i++) {
			q.add(i);
		}

		while (!q.isEmpty()) {
			// Get shortest
			int shortest = q.iterator().next();
			for (Integer i : q) {
				if (dist[i] < dist[shortest])
					shortest = i;
			}

			q.remove(shortest);

			for (int i = 0; i < E; i++) {
				if (dist[shortest] + e[shortest][i] < dist[i])
					dist[i] = dist[shortest] + e[shortest][i];
			}

		}

		int maxDist = 0;
		for (int i = 0; i < E; i++)
			maxDist = Math.max(maxDist, dist[i]);

		if (initDist == null)
			initDist = dist;

		return maxDist;
	}

}
