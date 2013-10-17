import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class H {

	static HashMap<Integer, String> cache = new HashMap<Integer, String>();
	static final int ADD = 0;
	static final int SUB = 1;
	static final int DIV = 2;
	static final int MULT = 3;

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		populateCache();
		int M = in.nextInt();
		for (int i = 0; i < M; i++) {
			int next = in.nextInt();
			if (cache.containsKey(next))
				System.out.println(cache.get(next));
			else
				System.out.println("no solution");

			// solve(in.nextInt());
		}
	}

	public static void populateCache() {

		int[] state = new int[3];

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				for (int k = 0; k < 4; k++) {
					state[0] = i;
					state[1] = j;
					state[2] = k;
//					System.out.println(getString(state));
					cache.put(eval(state), getString(state));
				}

			}
		}

	}

	public static String getString(int[] state) {
		String ans = "4";
		for (Integer i : state) {
			switch (i) {
			case ADD:
				ans += " + ";
				break;
			case MULT:
				ans += " * ";
				break;
			case DIV:
				ans += " / ";
				break;
			case SUB:
				ans += " - ";
				break;
			}
			ans += "4";
		}
		ans += " = " + eval(state);
		return ans;
	}

	public static int eval(int[] state) {

		// ArrayList<Integer> ops = new ArrayList<Integer>();

		int[] values = new int[4];
		int ans = 0;
		Arrays.fill(values, 4);

		for (int i = 0; i < state.length; i++) {
			switch (state[i]) {
			case ADD:
				break;
			case SUB:
				values[i + 1] *= -1;
				break;
			case MULT:
				values[i + 1] = values[i] * values[i + 1];
				values[i] = 0;
				break;
			case DIV:
				values[i + 1] = values[i] / values[i + 1];
				values[i] = 0;
				break;
			}

		}

		for (Integer i : values) {
			ans += i;
		}

		return ans;
	}

}
