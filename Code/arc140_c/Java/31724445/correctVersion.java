import java.io.IOException;
import java.io.InputStream;
import java.util.NoSuchElementException;

public class Main {
	public static void main(String[] args) {
		Main o = new Main();
		o.solve();
	}

	public void solve() {
		FastScanner sc = new FastScanner(System.in);
		int N = sc.nextInt();
		int X = sc.nextInt();

		StringBuilder ans = new StringBuilder();
		//if ( X == 1 ) {
		//	solve0(ans, 1, N, -100, 1, -1, N, N);
		//} else if ( X == N ) {
		//	solve0(ans, N, 1, -100, -1, 1, N, N);
		//} else {
			if ( N % 2 == 0 ) {
				if ( X == N / 2 ) {
					solve0(ans, N / 2, N / 2 + 1, -100, -1, 1, N, N);
				} else if ( X == N / 2 + 1 ) {
					solve0(ans, N / 2 + 1, N / 2, -100, 1, -1, N, N);
				} else {
					ans.append(X);
					ans.append(" ");
					if ( X < N / 2 ) {
						solve0(ans, N / 2 + 1, N / 2, X, 1, -1, N, N - 1);
					} else {
						solve0(ans, N / 2, N / 2 + 1, X, -1, 1, N, N - 1);
					}
				}
			} else {
				if ( X == N / 2 + 1 ) {
					solve0(ans, N / 2 + 1, N / 2, -100, 1, -1, N, N);
				} else if ( X <= N / 2 ) {
					ans.append(X);
					ans.append(" ");
					solve0(ans, N / 2 + 1, N / 2 + 2, X, -1, 1, N, N - 1);
				} else {
					ans.append(X);
					ans.append(" ");
					solve0(ans, N / 2 + 1, N / 2, X, 1, -1, N, N - 1);
				}
			}
		//}

		System.out.println(ans.toString());
	}

	void solve0(StringBuilder ans, int a0, int a1, int exc, int d0, int d1, int N, int cnt) {
		for (int i = 0; i < cnt ; i++) {
			if ( i % 2 == 0 ) {
				if ( a0 == exc ) a0 += d0;
				if ( a0 >= 1 && a0 <= N ) {
					ans.append(a0);
					ans.append(" ");
					a0 += d0;
				} else {
					break;
				}
			} else {
				if ( a1 == exc ) a1 += d1;
				if ( a1 >= 1 && a1 <= N ) {
					ans.append(a1);
					ans.append(" ");
					a1 += d1;
				} else {
					break;
				}
			}
		}
		ans.deleteCharAt(ans.length() - 1);
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
