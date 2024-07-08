import java.io.*;
import java.util.*;

class Main {
	int N, M, L;
	int[][] a, b;
	Set<Long> cd;
	static final long X = 1000000001L;
	
	class Elem implements Comparable<Elem> {
		static Comparator<Elem> c = Comparator.comparingInt((Elem e) -> e.getValue()).thenComparingInt(e -> e.a).thenComparingInt(e -> e.b);
		int a, b;
		Elem(int a0, int b0) { this.a = a0; this.b = b0; }
		final int getValue() { return Main.this.a[a][0] + Main.this.b[b][0]; }
		public int compareTo(Elem e) {
			return c.compare(this, e);
		}
		public boolean equals(Object o) {
			if (o instanceof Elem e) return a == e.a && b == e.b;
			return false;
		}
	}

	void calc() {
		int[] ns = nextInts();
		N = ns[0]; M = ns[1]; L = ns[2];
		var A = nextInts();
		a = new int[N][];
		Arrays.setAll(a, i -> new int[] {A[i], i});
		Arrays.sort(a, Comparator.comparingInt(a -> a[0]));
		var B = nextInts();
		b = new int[M][];
		Arrays.setAll(b, i -> new int[] {B[i], i});
		Arrays.sort(b, Comparator.comparingInt(b -> b[0]));

		for (int i = 0; i < N; i++) A[a[i][1]] = i;
		for (int i = 0; i < M; i++) B[b[i][1]] = i;

		cd = new HashSet<>();
		for (int i = 0; i < L; i++) {
			int[] cd0 = nextInts();
			cd.add(A[cd0[0] - 1] * X + B[cd0[1] - 1]);
		}
		System.out.println(solve());
	}

	int solve() {
		TreeSet<Elem> ts = new TreeSet<>();
		ts.add(new Elem(N-1, M-1));
		for (int i = 0; i < 100000; i++) {
			Elem e = ts.pollLast();
			if (!cd.contains(e.a * X + e.b)) return e.getValue();
			if (e.a > 0) ts.add(new Elem(e.a - 1, e.b));
			if (e.b > 0) ts.add(new Elem(e.a, e.b - 1));
		}
		return -1;
	}

	// ---
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	String next() { try { return br.readLine(); } catch (Exception e) { return null; } }
	String[] nexts() { return next().split(" "); }

	static int i(String s) { return Integer.parseInt(s); }
	int nextInt() { return i(next()); }
	int[] nextInts() { return Arrays.stream(nexts()).mapToInt(Main::i).toArray(); }

	public static void main(String[] args) {
		new Main().calc();
	}
}
