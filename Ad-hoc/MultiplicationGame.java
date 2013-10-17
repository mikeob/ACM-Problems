import java.util.Scanner;

public class MultiplicationGame {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		while (in.hasNext()) {
			long n = in.nextLong();
			solve(n);
		}

	}

	/*
	 * Works off the assumption that there
	 * are certain ranges that each player is guaranteed to 
	 * win in.
	 */
	public static void solve(long n) {
		long stanMax = 1;
		long ollieMax = 1;
		String name = "";
		while (true) {
			// Simulate Stan's turn
			stanMax *= 9;
			ollieMax *= 2;
			if (stanMax >= n) {
				name = "Stan";
				break;
			}

			// Simulate Ollie's turn
			stanMax *= 2;
			ollieMax *= 9;
			if (ollieMax >= n) {
				name = "Ollie";
				break;
			}
		}

		System.out.println(name + " wins.");
	}

}
