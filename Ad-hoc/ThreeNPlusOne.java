import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class ThreeNPlusOne {

	static HashMap<Long, Integer> cache = new HashMap<Long, Integer>();
	static ArrayList<Long> cycle = new ArrayList<Long>(1000);
	static ArrayList<ArrayList<Integer>> max = new ArrayList<ArrayList<Integer>>(526);
	static ArrayList<Integer> maxValues = new ArrayList<Integer>();


	public static void main(String[] args) throws FileNotFoundException {


		// Populate the maxes
		cache.put(1L, 1);
		for (int i = 0; i < 526; i++) {
			max.add(null);
		}
		
		int MAX_AMOUNT = 1000000;

		for (int i = 1; i <= MAX_AMOUNT; i++) {
			int length = cycleLength(i);

			if (max.get(length) == null) {
				max.set(length, new ArrayList<Integer>());
			}

			max.get(length).add(i);
		}
		
		//Scanner in = new Scanner(System.in);
		Scanner in = new Scanner(new FileReader("bigger.in"));

		while (in.hasNext()) {
			int i = in.nextInt();
			int j = in.nextInt();

			int start = Math.min(i, j);
			int end = Math.max(i, j);

			int ans = 0;
			
			
			// If it's a short interval, just walk it
			if (end - start < 1000)
				ans = brute(start, end);
			else
				ans = traverseMax(start, end);

			 System.out.printf("%d %d %d%n", i, j, ans);
		}

		in.close();
	}

	// Walk down our max values
	public static int traverseMax(int start, int finish) {

		for (int cur = max.size() - 1; cur >= 0; cur--) {
			// If no max exists here...
			if (max.get(cur) == null) {
				continue;
			}
			// Check if any of the numbers that this max
			// occurs for is in our interval
			for (Integer I : max.get(cur)) {
				if (I >= start && I <= finish) {
					return cur;
				}
			}
		}

		System.err.println("SOMETHING BAD HAPPENED");
		return 0;
	}

	public static int brute(int start, int finish) {
		int ans = 0;
		for (int i = start; i <= finish; i++) {
			ans = Math.max(ans, cycleLength(i));
		}

		return ans;
	}

	public static int cycleLength(long original) {
		long n = original;

		if (!cache.containsKey(n)) {
			cycle.clear();
			int length = 1;
			while (n != 1) {
				cycle.add(n);
				n = (n % 2 == 0) ? n / 2 : 3 * n + 1;

				// If we come across a cycle we've followed,
				// exit early and cache what we've done
				if (cache.containsKey(n)) {
					length += cache.get(n);
					break;
				}
				length++;
			}

			// Keep track of cycle
			for (int i = 0; i < cycle.size(); i++) {
				cache.put(cycle.get(i), length - i);
			}
		}

		return cache.get(original);
	}

}
