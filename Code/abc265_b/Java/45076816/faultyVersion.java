import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
	public static void main(final String[] args) {
		try (final Scanner sc = new Scanner(System.in)) {
			final int N = Integer.parseInt(sc.next());
			final int M = Integer.parseInt(sc.next());
			int T = Integer.parseInt(sc.next());
			final int[] A = new int[N];
			for (int i = 0; i < N - 1; i++) {
				A[i] = Integer.parseInt(sc.next());
			}

			final Map<Integer, Integer> XY = new HashMap<>();
			for (int i = 0; i < M; i++) {
				XY.put(Integer.parseInt(sc.next()), Integer.parseInt(sc.next()));
			}

			for (int i = 0; i <= N - 1; i++) {
				T -= A[i];
				if (T <= 0) {
					System.out.println("No");
					return;
				}
				if (XY.containsKey(i + 2)) {
					T += XY.get(i + 2);
				}
			}
			System.out.println("Yes");
		}
	}
}
