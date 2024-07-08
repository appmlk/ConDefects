import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		int t = scan.nextInt();
		int[] numbers = new int[t];
		for (int i = 0; i < t; i++) {
			numbers[i] = scan.nextInt();
		}

		scan.close();

		int[] cols = new int[n];
		int[] rows = new int[n];
		int[] diag = new int[2];
		for (int i = 0; i < t; i++) {
			int num = numbers[i] - 1;
			cols[num % n]++;
			rows[num / n]++;
			if (num % (n + 1) == 0) {
				diag[0]++;
			}
			if (num % (n - 1) == 0 && num != n * n - 1) {
				diag[1]++;
			}

			if (i >= n - 1) {
				if (diag[0] == n || diag[1] == n) {
					System.out.println(i + 1);
					return;
				}
				for (int j = 0; j < n; j++) {
					if (cols[j] == n || rows[j] == n) {
						System.out.println(i + 1);
						return;
					}
				}
			}
		}
		System.out.println(-1);

	}

}
