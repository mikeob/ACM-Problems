import java.util.ArrayList;
import java.util.Scanner;

public class StacksOfFlapjacks {

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);

		// Top pancake provided first
		// bottom pancake provided last
		while (in.hasNext()) {
			String[] numbers = in.nextLine().split(" ");
			int[] order = new int[numbers.length];

			for (int i = 0; i < numbers.length; i++) {
				if (i != 0) {
					System.out.print(" ");
				}
				order[i] = Integer.parseInt(numbers[i]);
				System.out.print(order[i]);
			}
			System.out.println();

			sort(order);
		}

	}

	/*
	 * Kinda ugly due to my initial misunderstanding of
	 * how they provide the input and index things
	 */
	public static void sort(int[] order) {
		ArrayList<Integer> ans = new ArrayList<Integer>();

		for (int i = order.length - 1; i > 0; i--) {

			int maxIndex = findMaxIndex(order, i);

			// If sorted, don't bother flipping/printing
			if (maxIndex == i) {
				continue;
			}

			// Only perform flip if not at top
			if (maxIndex != 0) {
				order = flip(order, maxIndex);
				ans.add(order.length - maxIndex);
			}

			order = flip(order, i);
			ans.add(order.length - i);
		}


		// Print ordering of flips
		for (int i = 0; i < ans.size(); i++)
		{
			System.out.print(ans.get(i) + " ");
		}
		System.out.println(0);

	}

	// Debugging
	public static void printStack(int[] order) {
		System.out.print("STACK: ");
		for (int i = 0; i < order.length; i++) {
			System.out.print(order[i] + " ");
		}
		System.out.println();
	}

	// Flips the ordering from [0, start]
	public static int[] flip(int[] order, int start) {
		int left = 0;
		int right = start;
		while (left <= right) {
			int temp = order[left];
			order[left] = order[right];
			order[right] = temp;
			left++;
			right--;
		}

		return order;
	}

	// Finds index from [0, start] with the max value
	public static int findMaxIndex(int[] order, int start) {
		int max = 0;
		int maxIndex = start;
		for (int i = start; i >= 0; i--) {
			if (order[i] > max) {
				max = order[i];
				maxIndex = i;
			}
		}

		return maxIndex;
	}

}
