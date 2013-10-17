import java.util.Arrays;
import java.util.Scanner;

public class ProblemC {

	static boolean[] isPrime = new boolean[32001];

	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in);
		
		populatePrimes();
		
		int N = in.nextInt();
		for (int i = 0; i < N; i++)
		{
			int c = in.nextInt();
			
			solve(c);
			if (i != N - 1)
				System.out.println();
		}
	}

	public static void populatePrimes() {
		Arrays.fill(isPrime, true);
		isPrime[1] = false;
		isPrime[0] = false;
		isPrime[2] = true;
		for (int i = 2; i*i <= isPrime.length; i++) {

			if (isPrime[i]) {
				for (int j = i*i; j < isPrime.length; j += i) {
					isPrime[j] = false;
				}
			}
		}
	}

	public static void solve(int c) {
		
		int numReps = 0;
		String reps = "";
		for (int i = 0; i <= c/2; i++)
		{
			if (isPrime[i] && isPrime[c - i])
			{
				numReps++;
				String lol = i + "+" + (c - i);
				reps += lol + "\n";
			}
		}

		System.out.println(c + " has " + numReps + " representation(s)");
		System.out.print(reps);
	}

}
