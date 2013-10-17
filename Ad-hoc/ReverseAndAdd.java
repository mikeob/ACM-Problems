import java.util.Scanner;

public class ReverseAndAdd {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		int N = in.nextInt();
		for (int i = 0; i < N; i++) {
			int number = in.nextInt();
			solve(number);
		}
	}

	public static void solve(long cur) {
		int count = 0;
		while (true) {
			long reverse = reverse(cur);
			if (cur == reverse) {
				break;
			}
			cur += reverse;
			count++;
		}
		System.out.println(count + " " + cur);
	}

	public static long reverse(long in) {
		long out = 0;
		while (in != 0) {
			out *= 10;
			out += in % 10;
			in /= 10;
		}
		return out;
	}

}
