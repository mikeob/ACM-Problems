import java.util.Scanner;

public class Chemistry {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		double prev = in.nextDouble();
		double next = in.nextDouble();
		
		while (Double.compare(next, 999) != 0) {

			System.out.printf("%.2f%n", (next - prev));
			prev = next;
			next = in.nextDouble();
		}

		System.out.println("End of Output");
	}
}
