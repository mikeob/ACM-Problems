import java.util.Scanner;

public class DefenceofGarden {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		int T = in.nextInt();

		for (int t = 0; t < T; t++) {
			System.out.print("Data Set " + (t + 1) + ": ");
			boolean[][] yard = new boolean[101][101];
			int X = in.nextInt();
			int Y = in.nextInt();
			int Z = in.nextInt();

			int curX = X;
			int curY = Y;
			for (int i = 0; i < Z; i++) {
				int dirX = 0;
				int dirY = 0;

				char dir = in.next().charAt(0);
				int dist = in.nextInt();

				switch (dir) {
				case 'N':
					dirX = 1;
					break;
				case 'S':
					dirX = -1;
					break;
				case 'E':
					dirY = 1;
					break;
				case 'W':
					dirY = -1;
					break;
				}

				for (int j = 0; j < dist; j++) {
					yard[curX][curY] = true;
					curX += dirX;
					curY += dirY;
				}

			}
			solve(yard);
			System.out.println(" square feet.");
		}

		System.out.println("End of Output");
	}
	
	public static void solve(boolean[][] yard)
	{
		
		for (int i = 0; i < 101; i++)
		{
			for (int j = 0; j < 101; j++)
			{
				
			}
		}
		
	}

}
