import java.io.*;
import java.util.*;

class Main {
	int N, M;
	int[][] AB;
	int[] V;
	int b;
	Set<Integer> f1 = new HashSet<>();
	Set<Pair> f2 = new HashSet<>();
	
	@SuppressWarnings("unchecked")
	void calc() {
		int[] ns = nextInts();
		N = ns[0]; M = ns[1];
		AB = new int[N][2];
		for (int i = 0; i < N; i++) AB[i] = nextInts();
		for (int i = 0; i < M; i++) {
			int[] xy = nextInts();
			if (xy[0] == xy[1]) f1.add(xy[0]-1);
			else f2.add(new Pair(xy[0]-1, xy[1]-1));
		}
		for (int f: f1) {
			int[] ab = AB[f];
			if (ab[0] < ab[1]) {
				int t = ab[0]; ab[0] = ab[1]; ab[1] = t;
			}
		}
		V = new int[N];
		for (int i = 0; i < N; i++) {
			V[i] = AB[i][1] - AB[i][0]; b += 2 * AB[i][0];
		}
		for (Iterator<Pair> iter = f2.iterator(); iter.hasNext();) {
			Pair f = iter.next();
			if (V[f.a] <= 0 && V[f.b] <= 0) {
				iter.remove(); continue;
			}
			if (V[f.a] < 0 || V[f.b] < 0) continue;
			b += V[f.a] + V[f.b];
			V[f.a] = V[f.b] = 0;
			iter.remove();
		}
		var stars = new HashMap<Integer,Long>();
		for (Pair p: f2) {
			if (V[p.a] < 0) {
				long b = stars.getOrDefault(p.a, 0L);
				stars.put(p.a, b | (1L << p.b));
			} else {
				long b = stars.getOrDefault(p.b, 0L);
				stars.put(p.b, b | (1L << p.a));
			}
		}
		var dp = new HashMap<Long, Integer>();
		int max = 0;
		dp.put(0L, 0);
		for (int c: stars.keySet()) {
			var nextDp = (HashMap<Long, Integer>)(dp.clone());
			long s = stars.get(c);
			for (long b: dp.keySet()) {
				long nb = s | b;
				long db = s & ~b;
				int v = dp.getOrDefault(nb, 0) + calcVal(db) + V[c];
				nextDp.put(nb, Math.max(nextDp.getOrDefault(nb, Integer.MIN_VALUE), v));
				max = Math.max(max, v);
			}
			dp = nextDp;
		}
		System.out.println( (double)(b+max)/2 );
	}

	private int calcVal(long bp) {
		int s = 0, i = 0;
		for (; bp > 0; bp >>= 1) {
			if ((bp & 1) != 0) s += V[i];
			i++;
		}
		return s;
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

class Pair {
	int a, b;
	Pair(int a0, int b0) {
		if (a0 > b0) { a = b0; b = a0; }
		else { a = a0; b = b0; }
	}
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Pair)) return false;
		Pair p = (Pair)o;
		return a == p.a && b == p.b;
	}
	@Override
	public int hashCode() {
		long h = 100001L * b + a;
		return (int)(h ^ (h >> 32));
	}
}