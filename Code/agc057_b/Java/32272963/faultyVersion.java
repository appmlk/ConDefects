import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.function.BinaryOperator;

public class Main {
	public static void main(String[] args) {
		Main o = new Main();
		o.solve();
	}

	long X = 0;
	public void solve() {
		FastScanner sc = new FastScanner(System.in);
		int N = sc.nextInt();
		X = sc.nextLong();
		long[] A = new long[N];
		Elem[] E = new Elem[N];
		for (int i = 0; i < N; i++) {
			A[i] = sc.nextLong();
			E[i] = new Elem(A[i], i);
		}

		Elem INF = new Elem(Long.MAX_VALUE, Integer.MAX_VALUE);

		SegmentTree<Elem> tree = new SegmentTree<>(N, (e0, e1) -> min(e0, e1), INF);
		PriorityQueue<Elem> queue = new PriorityQueue<>();
		for (int i = 0; i < N; i++) {
			queue.add(E[i]);
			tree.update(i, E[i]);
		}
		long ans = Long.MAX_VALUE;
		while ( queue.size() > 0 ) {
			Elem e = queue.poll();
			long max = e.min;
			//long min = Math.min(tree.query(0, e.id).max, tree.query(e.id + 1, N).max);
			long min = tree.query(0, N).max;
			ans = Math.min(ans, max - min);
			if ( ans <= 0 ) { ans = 0; break; }

			Elem n = e.back();
			if ( n == null ) break;
			queue.add(n);
			tree.update(n.id, n);
		}

		if ( ans < X ) {
			ans = 0;
		}
		System.out.println(ans);
	}

	Elem min(Elem e0, Elem e1) {
		if ( e0.max != e1.max ) {
			return e0.max < e1.max ? e0 : e1;
		} else if ( e0.id != e1.id ) {
			return e0.id < e1.id ? e0 : e1;
		} else {
			return e0;
		}
	}

	static final long LIMIT = 1L << 40;
	class Elem implements Comparable<Elem> {
		long A = 0;
		int id = 0;

		long min = 0;
		long max = 0;
		int cnt = 0;
		Elem(long A, int id) {
			this.A = A;
			this.id = id;
			this.min = A;
			this.max = A;
			while ( min < LIMIT ) {
				min = min * 2L;
				max = max * 2L + X;
				cnt++;
			}
		}
		Elem() {}
		Elem back() {
			if ( cnt == 0 ) return null;
			Elem n = new Elem();
			n.A = this.A;
			n.id = this.id;
			n.min = this.min / 2L;
			n.max = (this.max - X) / 2L;
			n.cnt = this.cnt - 1;
			return n;
		}
		@Override
		public int compareTo(Main.Elem o) {
			if ( min != o.min ) {
				return min < o.min ? 1 : -1;
			} else if ( max != o.max ) {
				return max < o.max ? 1 : -1;
			} else {
				return id - o.id;
			}
		}
	}

	class SegmentTree<T> {
		// 最下段の個数
		int N = 1;
		T[] tree = null;
	
		// 演算に影響しない値(加減なら0、最大なら-INF等)
		// f(a, e) = f(e, a) = f(a)となるようなe
		T IDENTITY = null;
		// 演算
		BinaryOperator<T> f = null;
		
		// 要素1〜maxまで
		SegmentTree(int max, BinaryOperator<T> f, T identity) {
			this.f = f;
			this.IDENTITY = identity;
			
			while ( N <= max ) {
				N *= 2;
			}
			
			tree = (T[]) Array.newInstance(identity.getClass(), 2 * N - 1);
			Arrays.fill(tree, identity);
		}
	
		// 初期化
		void init(T[] arr) {
			System.arraycopy(arr, 0, tree, N - 1, arr.length);
	
			// 演算実行
			for ( int i = N - 2 ; i >= 0 ; i-- ) {
				tree[i] = f.apply(tree[2 * i + 1], tree[2 * i + 2]);
			}
		}
	
		// i番目(0-indexed)の値
		T get(int i) {
			return tree[N - 1 + i];
		}
	
		// i番目の要素をaに更新
		void update(int i, T a) {
			int idx = N - 1 + i;
			tree[idx] = a;
			while ( idx > 0 ) {
				idx = (idx - 1) / 2;
				tree[idx] = f.apply(tree[2 * idx + 1], tree[2 * idx + 2]);
			}
		}
	
		// [i, j)の区間(i番目(含む)からj番目(含まない))までの間で演算実行
		T query(int i, int j) {
			return query(i, j, 0, 0, N);
		}
		T query(int i, int j, int cur, int l, int r) {
			// 入っていない場合
			if ( r <= i || j <= l ) {
				return IDENTITY;
			}
			// 完全に入っている場合
			if ( i <= l && r <= j ) {
				return tree[cur];
			}
	
			T val0 = query(i, j, 2 * cur + 1, l, (l + r) / 2);
			T val1 = query(i, j, 2 * cur + 2, (l + r) / 2, r);
			return f.apply(val0, val1);
		}
	}

	class FastScanner {
		private final InputStream in;
		private final byte[] buf = new byte[1024];
		private int ptr = 0;
		private int buflen = 0;
		FastScanner( InputStream source ) { this.in = source; }
		private boolean hasNextByte() {
			if ( ptr < buflen ) return true;
			else {
				ptr = 0;
				try { buflen = in.read(buf); } catch (IOException e) { e.printStackTrace(); }
				if ( buflen <= 0 ) return false;
			}
			return true;
		} 
		private int readByte() { if ( hasNextByte() ) return buf[ptr++]; else return -1; } 
		private boolean isPrintableChar( int c ) { return 33 <= c && c <= 126; }
		private boolean isNumeric( int c ) { return '0' <= c && c <= '9'; }
		private void skipToNextPrintableChar() { while ( hasNextByte() && !isPrintableChar(buf[ptr]) ) ptr++; }
		public boolean hasNext() { skipToNextPrintableChar(); return hasNextByte(); }
		public String next() {
			if ( !hasNext() ) throw new NoSuchElementException();
			StringBuilder ret = new StringBuilder();
			int b = readByte();
			while ( isPrintableChar(b) ) { ret.appendCodePoint(b); b = readByte(); }
			return ret.toString();
		}
		public long nextLong() {
			if ( !hasNext() ) throw new NoSuchElementException();
			long ret = 0;
			int b = readByte();
			boolean negative = false;
			if ( b == '-' ) { negative = true; if ( hasNextByte() ) b = readByte(); }
			if ( !isNumeric(b) ) throw new NumberFormatException();
			while ( true ) {
				if ( isNumeric(b) ) ret = ret * 10 + b - '0';
				else if ( b == -1 || !isPrintableChar(b) ) return negative ? -ret : ret;
				else throw new NumberFormatException();
				b = readByte();
			}
		}
		public int nextInt() { return (int)nextLong(); }
		public double nextDouble() { return Double.parseDouble(next()); }
	}
}
