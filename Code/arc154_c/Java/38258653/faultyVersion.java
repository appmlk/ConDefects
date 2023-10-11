import java.io.IOException;
import java.io.InputStream;
import java.util.NoSuchElementException;

public class Main {
	public static void main(String[] args) {
		Main o = new Main();
		o.solve();
	}

	static final String YES = "Yes";
	static final String NO = "No";
	public void solve() {
		FastScanner sc = new FastScanner(System.in);
		int T = sc.nextInt();
		StringBuilder ans = new StringBuilder();
		for (int t = 0; t < T; t++) {
			int N = sc.nextInt();
			int[] A = new int[N];
			int[] B = new int[N];
			for (int i = 0; i < N; i++) A[i] = sc.nextInt();
			for (int i = 0; i < N; i++) B[i] = sc.nextInt();
			ans.append(solve0(N, A, B) ? YES : NO);
			ans.append("\n");
		}

		System.out.print(ans.toString());
	}

	boolean solve0(int N, int[] A, int[] B) {
		for (int i = 0; i < N; i++) {
			if ( solve1(N, A, B, i) ) {
				return true;
			}
		}
		return false;
	}

	boolean solve1(int N, int[] A, int[] B, int si) {
		int ai = 0;
		int bi = si;
		boolean skip = false;
		while ( true ) {
			int cnt = 0;
			while ( A[ai] != B[bi] ) {
				ai++;
				if ( ai >= N ) {
					return false;
				}
				cnt++;
			}
			if ( cnt >= 2 || (bi == si && cnt > 0)) {
				skip = true;
			}
			bi = (bi + 1) % N;
			if ( bi == si ) break;
		}
		if ( si != 0 && skip == false ) {
			return false;
		} else {
			return true;
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
