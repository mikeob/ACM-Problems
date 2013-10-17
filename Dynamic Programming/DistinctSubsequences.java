import java.math.BigInteger;
import java.util.Scanner;

public class DistinctSubsequences {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		int T = Integer.parseInt(in.nextLine());
		for (int t = 0; t < T; t++) {
			String X = in.nextLine();
			String Z = in.nextLine();

			solve(X, Z);
		}
	}

	public static void solve(String X, String Z) {

		X = ' ' + X;
		char[] word = X.toCharArray();
		char[] sub = Z.toCharArray();

		int ROW = sub.length;
		int COL = word.length;

		BigInteger[][] dp = new BigInteger[ROW][COL];

		for (int i = 0; i < ROW; i++) {
			dp[i][0] = BigInteger.ZERO;
		}
		for (int j = 1; j < COL; j++) {

			if (word[j] == sub[0]) {
				dp[0][j] = dp[0][j - 1].add(BigInteger.ONE);
			} else {
				dp[0][j] = dp[0][j - 1];
			}
		}

		for (int i = 1; i < ROW; i++) {
			for (int j = 1; j < COL; j++) {

				if (sub[i] == word[j]) {
					dp[i][j] = dp[i][j - 1].add(dp[i - 1][j - 1]);
				} else {
					dp[i][j] = dp[i][j - 1];
				}
			}
		}

		System.out.println(dp[ROW - 1][COL - 1]);
	}

}
