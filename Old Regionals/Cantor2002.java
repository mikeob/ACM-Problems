import java.util.Arrays;
import java.util.Scanner;

public class Cantor2002 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		while (in.hasNext()) {
			int N = in.nextInt();

			solve(N);
		}
	}

	public static void solve(int N) {
		int L = (int) Math.pow(3, N);
		char[] s = new char[L];
		Arrays.fill(s, '-');

		String ans = cantor(new String(s));

		System.out.println(ans.substring(ans.indexOf('-'),
				ans.lastIndexOf('-') + 1));
	}

	public static String cantor(String s) {
		if (s.length() == 1)
			return s;

		int third = s.length() / 3;

		String end = cantor(s.substring(0, third));
		StringBuilder whitespace = new StringBuilder();
		for (int i = 0; i < third; i++) {
			whitespace.append(' ');
		}

		return end + whitespace.toString() + end;
	}

}
