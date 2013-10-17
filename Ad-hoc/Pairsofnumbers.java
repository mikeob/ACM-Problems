import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Pairsofnumbers {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNext()) {
			int N = in.nextInt();
			int M = N * (N - 1) / 2;
			int[] sums = new int[M];
			for (int i = 0; i < M; i++) {
				sums[i] = in.nextInt();
			}
			Arrays.sort(sums);
			solve(N, M, sums);
		}
	}

	/*
	 * Solves for all of the values by first solving for x1. Assumes x1 <= x2 <=
	 * ... <= x9 Thus sorted sums array should yield: x1x2, x1x3, ...
	 * 
	 * After the first two, we don't know the ordering, so we can do some cool
	 * stuff...
	 */
	public static void solve(int N, int M, int[] sums) {

		/*
		 * Since we know x1x2 and x1x3's positions, we just need to try at most
		 * N until we are guaranteed to have seen x2x3.
		 */
		for (int i = 2; i < N; i++) {
			// Solve for x1
			int x1x2 = sums[0];
			int x1x3 = sums[1];
			int x2x3 = sums[i];
			int x2 = (x1x2 - x1x3 + x2x3) / 2;
			int x1 = x1x2 - x2;
			int[] answers = solve(x1, sums, N);

			// We found a working set! Print it.
			if (answers != null) {

				for (int j = 0; j < answers.length - 1; j++) {
					System.out.print(answers[j] + " ");
				}
				System.out.println(answers[answers.length - 1]);
				return;
			}
		}

		System.out.println("Impossible");
	}

	public static int[] solve(int x1, int[] sums, int N) {
		int[] answers = new int[N];
		answers[0] = x1;

		Set<Integer> toIgnore = new HashSet<Integer>();
		int index = 0;

		// Ignore any sums other than x1 + xi
		for (int i = 1; i < N; i++) {
			while (toIgnore.contains(sums[index])) {
				toIgnore.remove(sums[index]);
				index++;
			}

			// x1xi - x1 = xi
			answers[i] = sums[index] - answers[0];
			for (int j = 1; j < i; j++) {
				toIgnore.add(answers[i] + answers[j]);
			}
			index++;

		}

		// Validation of our answer
		// Probably could've been done more efficiently, but I got lazy
		int[] toCompare = new int[(N * (N - 1)) / 2];
		int counter = 0;
		for (int i = 0; i < N; i++) {
			for (int j = i + 1; j < N; j++) {
				toCompare[counter] = answers[i] + answers[j];
				counter++;
			}
		}

		Arrays.sort(toCompare);

		for (int i = 0; i < toCompare.length; i++) {
			// Mismatch -- incorrect answer
			if (toCompare[i] != sums[i]) {
				return null;
			}
		}

		return answers;
	}

}
