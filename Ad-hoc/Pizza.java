import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;

public class Pizza {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);

		int M = in.nextInt();
		for (int i = 0; i < M; i++) {
			int r = in.nextInt();
			int n = in.nextInt();
			int theta = in.nextInt();
			int m = in.nextInt();
			int s = in.nextInt();
			solve(r, n, theta, m, s);
		}

	}

	public static void solve(int r, int n, int theta, int m, int s) {
		double circum = Math.PI * r * r;

		if (n == 0 || n == 1) {
			System.out.printf("%.6f%n", circum);
			return;
		}

		double t = theta + m / 60.0 + s / (60 * 60.0);
		ArrayList<Double> list = new ArrayList<Double>();
		
		HashSet<Double> set = new HashSet<Double>();

		double cur = 0;
		while (n > 0) {

			cur += t;
			cur %= 360;
			
			
			if (set.contains(cur))
			{
				break;
			}
			set.add(cur);
			list.add(cur);

			n--;
		}

		Collections.sort(list);

//		 for (Double d : list) {
//		 System.out.print(d + " ");
//		 }
//		 System.out.println();
		 
		 

		double biggest = (list.get(0) + 360) - list.get(list.size() - 1);
		biggest = biggest % 360;

		for (int i = 0; i < list.size() - 1; i++) {
			double candidate = list.get(i + 1) - list.get(i);
			candidate = candidate % 360;

			if (candidate > biggest) {
				biggest = candidate;
			}

		}

		// System.out.println((biggest / 360.0) * circum);
		System.out.printf("%.6f%n", (biggest / 360.0) * circum);
	}
}
