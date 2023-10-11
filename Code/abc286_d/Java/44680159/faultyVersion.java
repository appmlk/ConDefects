import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
	public static void main(final String[] args) {
		try (final Scanner sc = new Scanner(System.in)) {
			int N = Integer.parseInt(sc.next());
			final int X = Integer.parseInt(sc.next());

			final int[] A = new int[N];
			final int[] B = new int[N];

			for (int i = 0; i < N; i++) {
				final int tempA = Integer.parseInt(sc.next());
				if (tempA > X) {
					N--;
					continue;
				}
				A[i] = tempA;
				B[i] = Integer.parseInt(sc.next());
			}

			final HashSet<Integer> makableAmountSet = new HashSet<>();
			for (int i = 0; i < N; i++) {
				@SuppressWarnings("unchecked")
				final Set<Integer> tempMakableAmountSet = (Set<Integer>) makableAmountSet.clone();
				for (final Integer makableAmount : tempMakableAmountSet) {
					for (int j = 1; j <= B[i]; j++) {
						makableAmountSet.add(makableAmount + A[i] * j);
						if (makableAmountSet.contains(X)) {
							System.out.println("Yes");
							return;
						}
					}
				}
				for (int j = 1; j <= B[i]; j++) {
					makableAmountSet.add(A[i] * j);
					if (makableAmountSet.contains(X)) {
						System.out.println("Yes");
						return;
					}
				}
			}
			System.out.println("No");
		}
	}

}
