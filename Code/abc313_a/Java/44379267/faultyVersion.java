import java.util.Scanner;

public class Main {
	public static void main(final String[] args) {
		try (Scanner sc = new Scanner(System.in)) {
			final int N = sc.nextInt();
			final int P = sc.nextInt();
			int saikyo = 0;

			for (int i = 1; i < N; i++) {
				final int P2 = sc.nextInt();
				if (P < P2 && saikyo < P2) {
					saikyo = P2;
				}
			}

			System.out.println(Math.max(saikyo - P + 1, 0));

		}
	}
}
