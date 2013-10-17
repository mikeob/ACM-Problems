import java.util.Scanner;

/*
 * O(N) streaming solution. 
 */
public class JollyJumpers {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		while (in.hasNext()) {
			int N = in.nextInt();

			boolean[] present = new boolean[N];
			boolean jolly = true;

			int first;
			int second = in.nextInt();

			for (int i = 0; i < N - 1; i++) {
				first = second;
				second = in.nextInt();

				int diff = Math.abs(first - second);

				// If we've encountered it already
				// or it's not within the bounds [1, N)
				if (diff < 1 || diff >= N || present[diff]) {
					jolly = false;
					in.nextLine(); // Throw out rest of the line
					break;
				} else {
					present[diff] = true;
				}
			}

			String output = jolly ? "Jolly" : "Not jolly";
			System.out.println(output);

		}
		in.close();

	}

}
