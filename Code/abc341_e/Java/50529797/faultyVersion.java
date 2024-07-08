import java.io.*;
import java.util.*;

class Main {
	int N, Q;
	String S;
	LazySegtree st;
	
	void calc() {
		int[] ns = nextInts();
		N = ns[0]; Q = ns[1];
		S = next();
		int[] v = S.chars().map(c -> c - '0').toArray();
		st = new LazySegtree(v);

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < Q; i++) {
			int[] q = nextInts();
			int L = q[1] - 1, R = q[2];
			switch (q[0]) {
				case 1:
					st.operateSegment(L, R, 1);
					break;
				case 2:
					sb.append( st.calculate(L, R) == 0 ? "Yes" : "No" );
					sb.append('\n');
					break;
				default:
			}

		}
		print(sb);
	}

	// ---
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	String next() { try { return br.readLine(); } catch (Exception e) { return null; } }
	String[] nexts() { return next().split(" "); }

	static int i(String s) { return Integer.parseInt(s); }
	int nextInt() { return i(next()); }
	int[] nextInts() { return Arrays.stream(nexts()).mapToInt(Main::i).toArray(); }

	void print(Object o) {
		try { System.out.write(o.toString().getBytes()); System.out.flush(); }
		catch (Exception e) { }
	}

	public static void main(String[] args) {
		new Main().calc();
	}
}

class LazySegtree {
	private int m;
	
	private Operator[] lazy;
	private Segment[] seg;
	
	LazySegtree(int[] value) {
		init(value.length);
		construct(value);
	}
	
	private void init(int size) {
		m = (size == 1)? 1 : Integer.highestOneBit(size - 1) << 1;

		lazy = new Operator[2*m - 1];
		seg = new Segment[2*m - 1];
	}

	void construct(int[] v) {
		int n = v.length;
		for (int i = 0; i < n; i++) {
			seg[m-1+i] = new Segment(v[i]);
			lazy[m-1+i] = new Operator();
		}
		for (int i = m-1+n; i < 2*m-1; i++) {
			seg[i] = Segment.E;
			lazy[i] = Operator.I;
		}
		for (int i = m-2; i >= 0; i--) {
			seg[i] = new Segment(seg[i*2+1]);
			seg[i].append(seg[i*2+2]);
			lazy[i] = new Operator();
		}
	}

	private void eval(int n, int l, int r) {
		if (lazy[n].isIdentity()) return;
		lazy[n].map(seg[n], r-l);

		if(n < m-1) {
			lazy[2*n+1].compose(lazy[n]);
			lazy[2*n+2].compose(lazy[n]);
		}
		lazy[n].clear();
	}

	void operateSegment(int s, int eExclusive, int ope) {
		operateSegmentImpl(s, eExclusive, 0, 0, m, ope);
	}

	private void operateSegmentImpl(int s, int eExclusive, int n, int l, int r, int ope) {
		if (s <= l && r <= eExclusive) {
			lazy[n].compose(ope);
			eval(n, l, r);
		} else if (s < r && l < eExclusive) {
			eval(n, l, r);
			int i = (l>>>1)+(r>>>1);
			operateSegmentImpl(s, eExclusive, 2*n + 1, l, i, ope);
			operateSegmentImpl(s, eExclusive, 2*n + 2, i, r, ope);
			seg[n].let(seg[2*n + 1]);
			seg[n].append(seg[2*n + 2]);
		}
	}

	private Segment acc = new Segment();
	int calculate(int s, int eExclusive) {
		acc.clear();
		calcImpl(acc, s, eExclusive, 0, 0, m);
		return acc.val;
	}
	
	private void calcImpl(Segment acc, int s, int eExclusive, int n, int l, int r) {
		eval(n, l, r);
		if (r <= s || eExclusive <= l) return;
		if (s <= l && r <= eExclusive) {
			acc.append(seg[n]);
			return;
		}
		int i = (l>>>1)+(r>>>1);
		calcImpl(acc, s, eExclusive, 2*n + 1, l, i);
		calcImpl(acc, s, eExclusive, 2*n + 2, i, r);
	}
}

class Segment {
	static final Segment E = new Segment();
	static int cnt = 0;
	int val;
	int l, r;

	Segment() { val = 0; l = r = --cnt; }
	Segment(int v) { val = 0; l = r = v; }

	Segment(Segment s) {
		let(s);
	}

	void clear() {
		val = 0; l = r = --cnt;
	}

	void let(Segment s) {
		val = s.val; l = s.l; r = s.r;
	}
	void append(Segment s) {
		val = (val != 0 || s.val != 0)? 1 : ((r != s.l)? 0 : 1);
		r = s.r;
	}
}

class Operator {
	static final Operator I = new Operator();
	int val;

	Operator() { }
	Operator(int v) { val = v; }

	boolean isIdentity() { return val == 0; }

	void clear() { val = 0; }

	void compose(Operator o) {
		val ^= o.val;
	}

	void compose(int o) {
		val ^= o;
	}

	void let(int o) {
		val = o;
	}

	void map(Segment s, int len) {
		s.l ^= val;
		s.r ^= val;
	}
}
