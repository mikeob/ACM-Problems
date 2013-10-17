import java.util.Scanner;

public class TimeForChange2000 {


	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		while (true) {

			if (!in.hasNext())
				break;

			String N = in.next();
			if (N.equals("0"))
				break;

			int value = 100 * (N.charAt(0) - '0') + 10 * (N.charAt(2) - '0')
					+ (N.charAt(3) - '0');

			int ans = solve(value);

			System.out.printf("There are %d ways to make $%s%n", ans, N);
		}
		System.out.println("End of Output");

	}

	public static int solve(int N) {

		int ans = 0;

		for (int h = 0; h < 200 / 50; h++) {
			for (int q = 0; q < 200 / 25; q++) {
				for (int d = 0; d < 200 / 10; d++) {
					for (int n = 0; n < 200 / 5; n++) {

						int val = 50 * h + 25 * q + 10 * d + 5 * n;
						int p = N - val;
						if (p < 0) {

						} else if (val <= N) {
							ans++;
						}

					}
				}
			}
		}

		return ans;
	}

}
