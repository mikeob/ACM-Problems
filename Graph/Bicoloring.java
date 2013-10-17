import java.util.Arrays;
import java.util.Scanner;

public class Bicoloring {

	static int[] colors;
	static int N;
	static boolean[][] e;

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		while (true) {
			N = in.nextInt();

			if (N == 0)
				break;

			int L = in.nextInt();

			e = new boolean[N][N];

			for (int i = 0; i < L; i++) {
				int to = in.nextInt();
				int from = in.nextInt();
				e[to][from] = true;
				e[from][to] = true;
			}

			colors = new int[N];
			Arrays.fill(colors, -1);
			if (DFS(0, new boolean[N], 0)) {
				System.out.println("BICOLORABLE.");
			} else {
				System.out.println("NOT BICOLORABLE.");
			}

		}
	}

	public static boolean DFS(int color, boolean[] seen, int cur) {

		colors[cur] = color;
		seen[cur] = true;

		for (int i = 0; i < N; i++) {

			// If connected to an edge of the same color
			if (e[cur][i] && colors[i] == colors[cur]) {
				// FAILURE
				return false;
			}

			if (e[cur][i] && !seen[i]) {
				if (!DFS((color + 1) % 2, seen, i))
					return false;
			}
		}

		return true;
	}

}
