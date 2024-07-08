import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.function.IntFunction;
import java.util.function.LongFunction;

public class Main {
	public static void main(String[] args) {
		Main o = new Main();
		o.solve();
	}

	static final int NINF = Integer.MIN_VALUE / 2;
	public void solve() {
		FastScanner sc = new FastScanner(System.in);
		int T = sc.nextInt();
		StringBuilder ans = new StringBuilder();
		for (int t = 0; t < T; t++) {
			int N = sc.nextInt();
			int[] A = new int[N];
			for (int i = 0; i < N; i++) {
				A[i] = sc.nextInt();
			}
			ArrayList<Integer> ret = solve0(N, A);
			ans.append(ret.size());
			ans.append(LF);
			for (int a : ret) {
				ans.append(a + 1);
				ans.append(SPACE);
			}
			ans.deleteCharAt(ans.length() - 1);
			ans.append(LF);
		}

		System.out.print(ans.toString());
	}

	ArrayList<Integer> solve0(int N, int[] A) {
		Number[] num = new Number[N];
		Number[] num_sorted = new Number[N];
		for (int i = 0; i < N; i++) {
			num[i] = new Number(A[i], i);
			num_sorted[i] = num[i];
		}
		Arrays.sort(num_sorted);
		for (int i = 0; i < N; i++) {
			num_sorted[i].ord = i;
		}

		IntSegmentTree tree = new IntSegmentTree(N + 1);
		int[] len = new int[N];
		int max = 0;
		boolean[] ok = new boolean[N];
		for (int i = 0; i < N; i++) {
			Number n = num[i];
			int v = tree.query(0, n.ord);
			v = Math.max(v, 0);
			len[n.id] = v + 1;
			max = Math.max(max, len[n.id]);
			tree.update(n.ord, v + 1);
		}

		tree = new IntSegmentTree(N + 1);
		for (int i = N - 1; i >= 0; i--) {
			Number n = num[i];
			if ( len[i] == max ) {
				ok[i] = true;
			} else {
				int v = -tree.query(n.ord, N);
				
				if ( len[i] + 1 >= v ) {
					ok[i] = true;
				}
			}
			if ( ok[i] ) {
				tree.update(n.ord, -len[i]);
			}
		}
		ArrayList<Integer> ret = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			if ( ok[i] ) ret.add(i);
		}

		return ret;
	}

	class Number implements Comparable<Number> {
		int A = 0;
		int id = 0;
		int ord = 0;
		Number(int A, int id) {
			this.A = A;
			this.id = id;
		}
		@Override
		public int compareTo(Number o) {
			if ( A != o.A ) {
				return A - o.A;
			} else {
				return -id + o.id;
			}
		}
	}

	class IntSegmentTree {
	// 最下段の個数
	int N = 1;
	int[] tree = null;

	// 演算に影響しない値(加減なら0、最大なら-INF等)
	// f(a, e) = f(e, a) = f(a)となるようなe
	int IDENTITY = NINF;

	// 演算
	int f(int a, int b) {
		return Math.max(a, b);
	}
	
	// 要素1〜maxまで
	IntSegmentTree(int max) {
		while ( N <= max ) {
			N *= 2;
		}
		
		tree = new int[2 * N - 1];
		Arrays.fill(tree, IDENTITY);
	}

	// 初期化
	void init(int[] arr) {
		System.arraycopy(arr, 0, tree, N - 1, arr.length);

		// 演算実行
		for ( int i = N - 2 ; i >= 0 ; i-- ) {
			tree[i] = f(tree[2 * i + 1], tree[2 * i + 2]);
		}
	}

	// i番目(0-indexed)の値
	int get(int i) {
		return tree[N - 1 + i];
	}

	// i番目の要素をaに更新
	void update(int i, int a) {
		int idx = N - 1 + i;
		tree[idx] = a;
		while ( idx > 0 ) {
			idx = (idx - 1) / 2;
			tree[idx] = f(tree[2 * idx + 1], tree[2 * idx + 2]);
		}
	}

	// [i, j)の区間(i番目(含む)からj番目(含まない))までの間で演算実行
	int query(int i, int j) {
		return query(i, j, 0, 0, N);
	}
	int query(int i, int j, int cur, int l, int r) {
		// 入っていない場合
		if ( r <= i || j <= l ) {
			return IDENTITY;
		}
		// 完全に入っている場合
		if ( i <= l && r <= j ) {
			return tree[cur];
		}

		int val0 = query(i, j, 2 * cur + 1, l, (l + r) / 2);
		int val1 = query(i, j, 2 * cur + 2, (l + r) / 2, r);
		return f(val0, val1);
	}
}



	static final char LF = '\n';
	static final char SPACE = ' ';
	static final String YES = "Yes";
	static final String NO = "No";
	void print(int[] array, char sep) {
		print(array, sep, n -> n, 0, array.length);
	}
	void print(int[] array, char sep, IntFunction<Integer> conv) {
		print(array, sep, conv, 0, array.length);
	}
	void print(int[] array, char sep, IntFunction<Integer> conv, int start, int end) {
		StringBuilder ans = new StringBuilder();
		for (int i = start; i < end; i++) {
			ans.append(conv.apply(array[i]));
			ans.append(sep);
		}
		ans.deleteCharAt(ans.length() - 1);
		System.out.println(ans.toString());
	}
	void print(long[] array, char sep) {
		print(array, sep, n -> n, 0, array.length);
	}
	void print(long[] array, char sep, LongFunction<Long> conv) {
		print(array, sep, conv, 0, array.length);
	}
	void print(long[] array, char sep, LongFunction<Long> conv, int start, int end) {
		StringBuilder ans = new StringBuilder();
		for (int i = start; i < end; i++) {
			ans.append(conv.apply(array[i]));
			ans.append(sep);
		}
		ans.deleteCharAt(ans.length() - 1);
		System.out.println(ans.toString());
	}
	void printYesNo(boolean yesno) {
		System.out.println(yesno ? YES : NO);
	}
	void printDouble(double val, int digit) {
		System.out.println(String.format("%." + digit + "f", val));
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
