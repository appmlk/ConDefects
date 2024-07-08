import java.util.*;

class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = Integer.parseInt(sc.next());

		List<Integer> q = new ArrayList<>();
		List<Integer> a = new ArrayList<>();
		List<Integer> b = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			q.add(Integer.parseInt(sc.next()));
		}
		for (int i = 0; i < n; i++) {
			a.add(Integer.parseInt(sc.next()));
		}
		for (int i = 0; i < n; i++) {
			b.add(Integer.parseInt(sc.next()));
		}

		int answer = 0;
		int x = 0;
		while (true) {
			x++;	
			List<Integer> r = new ArrayList<>();
			for (int i = 0; i < n; i++) {
				r.add(q.get(i) - a.get(i) * x);
			}
			boolean ok = true;
			for (int i = 0; i < n; i++) {
				if (r.get(i) < 0) {
					ok = false;
				}
			}
			if (!ok) {
				break;
			}
			int y = 1000000000;
			for (int i = 0; i < n; i++) {
				if (b.get(i) == 0) {
					continue;
				}
				y = Math.min(y, r.get(i) / b.get(i));
			}
			answer = Math.max(answer, x + y);
		}
		System.out.println(answer);
	}
}
