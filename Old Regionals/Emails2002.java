import java.util.HashSet;
import java.util.Scanner;

public class Emails2002 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		int EMAILNUM = 1;
		while (in.hasNext()) {
			HashSet<String> dict = new HashSet<String>();
			int D = Integer.parseInt(in.nextLine());

			// Populate dictionary
			for (int i = 0; i < D; i++)
				dict.add(in.nextLine());

			int E = Integer.parseInt(in.nextLine());
			for (int e = 1; e <= E; e++) {
				boolean correct = true;

				String line = in.nextLine();
				while (!line.equals("-1")) {

					if (!dict.contains(line)) {
						if (correct) {
							correct = false;
							System.out.printf(
									"Email %d is not spelled correctly.%n",
									EMAILNUM);
						}
						System.out.println(line);
					}

					line = in.nextLine();

				}

				if (correct) {
					System.out.printf("Email %d is spelled correctly.%n",
							EMAILNUM);
				}

				EMAILNUM++;

			}

		}

		System.out.println("End of Output");

	}

}
