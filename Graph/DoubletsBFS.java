import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

/*
 * Works, finally.
 */
public class DoubletsBFS {

	static HashMap<Integer, String> words = new HashMap<Integer, String>();
	static HashMap<String, Integer> dict = new HashMap<String, Integer>();
	static int N;

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		N = 0;

		// Input words
		while (true) {
			String line = in.nextLine();
			if (line.isEmpty()) {
				break;
			}
			words.put(N, line);
			dict.put(line, N);
			N++;
		}

		boolean first = true;
		// Handle test cases
		while (in.hasNextLine()) {
			if (!first) {
				System.out.println();
			}
			first = false;

			String[] input = in.nextLine().split(" ");

			if (dict.containsKey(input[0]) && dict.containsKey(input[1])
					&& input[0].length() == input[1].length()) {
				BFS(input[0], input[1]);
			} else {
				System.out.println("No solution.");
			}

		}
	}

	public static void BFS(String start, String end) {
		boolean[] visited = new boolean[N];
		int[] prev = new int[N];
		Queue<Integer> q = new ArrayDeque<Integer>();

		int startIndex = dict.get(start);
		int endIndex = dict.get(end);

		q.add(startIndex);
		Arrays.fill(prev, -1);

		while (!q.isEmpty()) {
			int curIndex = q.poll();

			visited[curIndex] = true;

			// If at the end, build our answer
			if (curIndex == endIndex) {
				Stack<String> ans = new Stack<String>();

				// Construct answer
				while (curIndex != -1) {
					ans.push(words.get(curIndex));
					curIndex = prev[curIndex];
				}
				StringBuilder b = new StringBuilder();
				// Print answer
				while (!ans.isEmpty()) {
					b.append(ans.pop()).append("\n");
				}
				System.out.print(b.toString());
				return;
			}

			// Generate neighbors
			char[] cur = words.get(curIndex).toCharArray();
			char[] clone = words.get(curIndex).toCharArray();
			for (int i = 0; i < cur.length; i++) {
				for (int j = 0; j < 26; j++) {
					cur[i] = (char) ('a' + j);
					String neighbor = new String(cur);

					// If in our dictionary and unvisited
					if (dict.containsKey(neighbor)
							&& !visited[dict.get(neighbor)]
							&& prev[dict.get(neighbor)] == -1) {

						int index = dict.get(neighbor);
						prev[index] = curIndex;

						q.add(index);
					}

				}
				cur[i] = clone[i];
			}

		}

		// Failure to find a path
		System.out.println("No solution.");
	}
}
