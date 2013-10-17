import java.util.Scanner;

public class SternBrocot {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		while (true) {
			int num = in.nextInt();
			int denom = in.nextInt();
			if (num == 1 && denom == 1)
			{
				break;
			}
			solve(new Fraction(num, denom));
		}
	}

	public static void solve(Fraction toFind) {
		Fraction left = new Fraction(0, 1);
		Fraction right = new Fraction(1, 0);
		Fraction middle = Fraction.add(right, left);

		while (middle.compare(toFind) != 0) {

			int comp = middle.compare(toFind);

			// If to the right (toFind is greater than middle)
			if (comp < 0) {
				System.out.print('R');
				left = middle;
			}
			// If to the left (toFind is less than the middle)
			else {
				System.out.print('L');
				right = middle;
			}
			middle = Fraction.add(left, right);
		}

		System.out.println();

	}

	/*
	 * Immutable fraction class
	 */
	static class Fraction {
		final int n, d;

		public Fraction(int num, int denom) {
			n = num;
			d = denom;
		}

		public static Fraction add(Fraction one, Fraction other) {
			return new Fraction(other.n + one.n, other.d + one.d);
		}

		int compare(Fraction other) {
			return Integer.compare(n * other.d, d * other.n);
		}
	}

}
