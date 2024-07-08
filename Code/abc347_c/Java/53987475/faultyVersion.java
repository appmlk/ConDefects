import java.util.*;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = Integer.parseInt(sc.next());
		int A = Integer.parseInt(sc.next());
		int B = Integer.parseInt(sc.next());
		TreeSet<Integer> days = new TreeSet<>();
		for (int i = 0; i < N; i++) {
			int d = Integer.parseInt(sc.next());
			days.add(d % (A + B));
			if (d % (A + B) == 0) {
				days.add(A + B);
			}
		}
		TreeSet<Integer> interval = new TreeSet<>();
		int i = 0;
		int y = 0;
		for (int x: days) {
			if (i == 0) {
				y = x;
				i++;
			} else {
				interval.add((x - y) % (A + B));
				y = x;
			}
		}
		interval.add((A + B) - days.last() + days.first() - 1);
		if (interval.last() >= B) {
			System.out.println("Yes");
		} else {
			System.out.println("No");
		}
	}
}