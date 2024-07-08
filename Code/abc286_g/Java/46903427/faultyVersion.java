import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.function.IntFunction;
import java.util.function.LongFunction;

public class Main {
	public static void main(String[] args) {
		Main o = new Main();
		o.solve();
	}

	public void solve() {
		FastScanner sc = new FastScanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		ArrayList<Edge>[] edge = new ArrayList[N];
		Edge[] earr = new Edge[M];
		for (int i = 0; i < N; i++) edge[i] = new ArrayList<>();
		for (int i = 0; i < M; i++) {
			int U = sc.nextInt() - 1;
			int V = sc.nextInt() - 1;

			earr[i] = new Edge(U, V, i);
			edge[U].add(new Edge(V, U, i));
			edge[V].add(earr[i]);
		}
		int K = sc.nextInt();
		int[] x = new int[K];
		HashSet<Integer> S = new HashSet<>();
		for (int i = 0; i < K; i++) {
			x[i] = sc.nextInt() - 1;
			S.add(x[i]);
		}

		UnionFind uf = new UnionFind(N);
		for (int i = 0; i < N; i++) {
			if ( uf.size(i) != 1 ) continue;
			ArrayDeque<Integer> queue = new ArrayDeque<>();
			queue.add(i);
			while ( queue.size() > 0 ) {
				int pos = queue.pollFirst();
				for ( Edge e : edge[i] ) {
					if ( S.contains(e.id) ) continue;
					if ( uf.isSame(pos, e.to) ) continue;
					uf.unite(pos, e.to);
					queue.add(e.to);
				}
			} 
		}

		int[] cnt = new int[N];
		for (int i = 0; i < K; i++) {
			cnt[uf.root(earr[x[i]].from)]++;
			cnt[uf.root(earr[x[i]].to)]++;
		}
		int ocnt = 0;
		for (int i = 0; i < N; i++) {
			if ( cnt[i] % 2 == 1 ) ocnt++;
		}

		printYesNo(ocnt <= 2);
	}

	class Edge {
		int id = 0;
		int to = 0;
		int from = 0;
		Edge(int to, int from, int id) {
			this.to = to;
			this.from = from;
			this.id = id;
		}
	}

	class UnionFind {
		int[] parent = null;
		int[] size = null;
		
		UnionFind(int N) {
			parent = new int[N];
			size = new int[N];
			for ( int i = 0 ; i < N ; i++ ) {
				parent[i] = i;
				size[i] = 1;
			}
		}
	
		int root(int i) {
			return parent[i] == i ? i : (parent[i] = root(parent[i]));
		}
		int size(int i) {
			return size[root(i)];
		}
	
		void unite(int i, int j) {
			int ri = root(i);
			int rj = root(j);
			if ( ri == rj ) {
				return;
			} else {
				if ( size[ri] < size[rj] ) {
					parent[ri] = rj;
					size[rj] += size[ri];
				} else {
					parent[rj] = ri;
					size[ri] += size[rj];
				}
			}
		}
		
		boolean isSame(int i, int j) {
			return root(i) == root(j);
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
