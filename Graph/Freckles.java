import java.util.PriorityQueue;
import java.util.Scanner;

public class Freckles {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		int T = in.nextInt();

		for (int t = 0; t < T; t++) {
			int N = in.nextInt();
			double[][] coords = new double[N][2];

			// Input coordinates
			for (int i = 0; i < N; i++) {
				coords[i][0] = in.nextDouble();
				coords[i][1] = in.nextDouble();
			}

			PriorityQueue<Edge> q = new PriorityQueue<Edge>();

			// Create edges
			for (int i = 0; i < N; i++) {
				for (int j = i + 1; j < N; j++) {
					q.add(new Edge(i, coords[i][0], coords[i][1], j,
							coords[j][0], coords[j][1]));
				}
			}

			MST(q, N);
			if (t != T - 1)
				System.out.println();
		}
	}

	public static void MST(PriorityQueue<Edge> q, int N) {

		DisjointSet s = new DisjointSet(N);
		double ans = 0;

		while (s.N > 1) {
			Edge e = q.poll();

			/* If already connected, continue */
			if (s.get(e.to) == s.get(e.from))
				continue;

			/* Otherwise, add the edge to our set */
			s.union(e.to, e.from);
			ans += e.distance;

		}

		System.out.printf("%.2f%n", ans);

	}

	public static class DisjointSet {

		int[] s;
		int N;

		public DisjointSet(int N) {
			s = new int[N];
			this.N = N;

			for (int i = 0; i < N; i++) {
				s[i] = -1;
			}
		}

		public void union(int x, int y) {
			int r1 = get(x);
			int r2 = get(y);

			if (s[r1] < s[r2])
				s[r2] = r1;
			else if (s[r2] < s[r1])
				s[r1] = r2;
			else {
				s[r2] = r1;
				s[r1]--;
			}
			N--;
		}

		public int get(int x) {
			if (s[x] < 0)
				return x;

			s[x] = get(s[x]);
			return s[x];
		}

	}

	public static class Edge implements Comparable<Edge> {

		int to, from;
		double distance;

		public Edge(int to, double tx, double ty, int from, double fx, double fy) {
			this.to = to;
			this.from = from;

			distance = (tx - fx) * (tx - fx) + (ty - fy) * (ty - fy);
			distance = Math.sqrt(distance);

		}

		@Override
		public int compareTo(Edge o) {
			return Double.compare(distance, o.distance);
		}

	}

}
