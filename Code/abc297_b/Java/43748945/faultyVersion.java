import java.util.Scanner;

public class Main {
	public static void main(final String args[]) {
		try (Scanner sc = new Scanner(System.in)) {
			final String S = sc.next();

			final char[] charS = S.toCharArray();

			int firstB = 0;
			int secondB = 0;
			boolean isFirstBFound = false;
			int RKState = 0;
			for (int i = 0; i < 8; i++) {
				if (charS[i] == 'B' && !isFirstBFound) {
					firstB = i;
					isFirstBFound = true;
				}
				if (charS[i] == 'B' && isFirstBFound) {
					secondB = i;
				}
				if (charS[i] == 'R' && RKState == 0) {
					RKState++;
				}
				if (charS[i] == 'K' && RKState == 1) {
					RKState++;
				}
				if (charS[i] == 'R' && RKState == 2) {
					RKState++;
				}
			}

			if ((firstB % 2 == 0 && secondB % 2 == 1) || (firstB % 2 == 1 && secondB % 2 == 0) && RKState == 3) {
				System.out.println("Yes");
			} else {
				System.out.println("No");
			}
		}
	}
}
