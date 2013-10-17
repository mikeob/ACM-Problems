import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class FileFragmentation {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		int T = in.nextInt();
		in.nextLine();
		in.nextLine();

		for (int i = 0; i < T; i++) {
			ArrayList<String> input = new ArrayList<String>();

			while (in.hasNextLine()) {
				String line = in.nextLine();
				// if (!line.matches("\\S+")) {
				if (line.isEmpty()) {
					break;
				}
				input.add(line);
			}
			System.out.println(solve(input));
			if (i != T - 1)
				System.out.println();
		}

	}

	public static String solve(ArrayList<String> input) {
		// Bucket input strings by size and use sets to remove duplicates
		ArrayList<ArrayList<String>> chunks = new ArrayList<ArrayList<String>>();
		for (int i = 0; i < 258; i++) {
			chunks.add(new ArrayList<String>());
		}

		// Biggest and smallest chunks
		int biggest = 0;
		int smallest = 257;

		for (String s : input) {
			biggest = Math.max(biggest, s.length());
			smallest = Math.min(smallest, s.length());
			chunks.get(s.length()).add(s);
		}

		int finalSize = biggest + smallest;

		Set<String> ans = new HashSet<String>();

		for (int i = 0; i <= 128; i++) {
			// Ignore empty buckets
			if (chunks.get(i).size() == 0) {
				continue;
			}

			int complement = finalSize - i;

			// For each bucket
			for (String s : chunks.get(i)) {
				Set<String> solutions = new HashSet<String>();

				for (String other : chunks.get(complement)) {
					solutions.add(s + other);
					solutions.add(other + s);
				}

				if (ans.size() == 0) {
					// Initialize our ans
					ans = solutions;
				} else {
					// Intersect the sets
					ans.retainAll(solutions);
				}

			}

		}
		// Return any of the remaining strings in the set
		return ans.iterator().next();

	}

}
