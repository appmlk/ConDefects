import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
	public static void main(final String[] args) {
		try (final Scanner sc = new Scanner(System.in)) {
			final int N = Integer.parseInt(sc.next());
			final int M = Integer.parseInt(sc.next());
			final Map<Integer, HashSet<Integer>> canGo = new HashMap<>();
			for (int i = 0; i < M; i++) {
				final int A = Integer.parseInt(sc.next());
				final int B = Integer.parseInt(sc.next());
				if (canGo.containsKey(A)) {
					HashSet<Integer> newList = canGo.get(A);
					newList.add(B);
					canGo.put(A, newList);
					if (canGo.containsKey(B)) {
						newList = canGo.get(B);
						newList.add(A);
						canGo.put(B, newList);
					} else {
						newList = new HashSet<>();
						newList.add(A);
						canGo.put(B, newList);
					}
				} else {
					HashSet<Integer> newList = new HashSet<>();
					newList.add(B);
					canGo.put(A, newList);
					if (canGo.containsKey(B)) {
						newList = canGo.get(B);
						newList.add(A);
						canGo.put(B, newList);
					} else {
						newList = new HashSet<>();
						newList.add(A);
						canGo.put(B, newList);
					}
				}
			}

			for (int i = 1; i <= N; i++) {
				if (canGo.containsKey(i)) {
					final List<Integer> ansArray = new ArrayList<>();
					for (final int j : canGo.get(i)) {
						ansArray.add(j);
					}
					Collections.sort(ansArray);
					final int ansSize = canGo.get(i).size();
					System.out.print(ansSize + " ");
					for (int j = 0; j < ansSize; j++) {
						System.out.print(ansArray.get(j) + " ");
					}
					System.out.println();
				} else {
					System.out.println(0);
				}
			}

		}
	}
}
