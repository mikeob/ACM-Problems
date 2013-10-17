import java.util.ArrayList;
import java.util.BitSet;
import java.util.Scanner;

public class Cards {

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);

		int N = in.nextInt();
		int ans = N;
		int[] cards = new int[N];

		int idx = -1;

		for (int i = 0; i < N; i++) {

			int next = in.nextInt();

			if (idx == -1) {
				idx++;
				cards[idx] = next;
			} else if ((cards[idx] + next) % 2 == 0) {
				ans -= 2;
				idx--;
			} else {
				idx++;
				cards[idx] = next;
			}

		}

		System.out.println(ans);
	}

}
