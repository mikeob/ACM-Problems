import java.util.HashMap;
import java.util.Scanner;

public class TowersOfHanoiOnCrack {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int[] cache = populateCache(50);

		int T = in.nextInt();
		for (int t = 0; t < T; t++) {
			int N = in.nextInt();
			System.out.println(cache[N]);
		}

	}

	public static int[] populateCache(int N) {
		int[] squares = new int[1000];
		for (int i = 1; i < squares.length; i++) {
			squares[i] = i * i;
		}

		int[] cache = new int[N + 1];
		int cacheidx = 0;
		int cur = 1;
		HashMap<Integer, Integer> inEdge = new HashMap<Integer, Integer>();

		// TODO Some way to stop once we've cached a -1

		while (cacheidx < N + 1) {

			if (!inEdge.containsKey(cur)) {
//				System.out.println(cacheidx + ": " + (cur - 1));
				cache[cacheidx] = cur - 1;
				cacheidx++;
			}
			else
			{
//				System.out.println(cur + " " + inEdge.get(cur));
			}

			// Find next edge
			for (int i = 0; i < squares.length; i++) {
				if (squares[i] - cur > cur) {
					inEdge.put(squares[i] - cur, cur);
					break;
				}
			}

			cur++;
		}

		return cache;
	}

}
