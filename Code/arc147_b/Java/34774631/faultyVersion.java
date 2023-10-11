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
		int[] P = new int[N];
		for (int i = 0; i < N; i++) {
			P[i] = sc.nextInt();
		}

		int cnt = 0;
		int ei = 0;
		int oi = 1;
		StringBuilder ans = new StringBuilder();
		while ( ei < N && oi < N ) {
			while ( ei < N && P[ei] % 2 != 0 ) ei += 2;
			while ( oi < N && P[oi] % 2 == 0 ) oi += 2;
			if ( ei >= N || oi >= N ) break;
			int i0 = 0;
			if ( ei < oi ) {
				for (int i = ei; i <= oi - 3; i += 2) {
					ans.append("B ");
					ans.append(i + 1);
					ans.append("\n");
					swap(P, i, i + 2);
					cnt++;
				}
				i0 = oi - 1;
			} else {
				for (int i = oi; i <= ei - 3; i += 2) {
					ans.append("B ");
					ans.append(i + 1);
					ans.append("\n");
					swap(P, i, i + 2);
					cnt++;
				}
				i0 = ei - 1;
			}
			ans.append("A ");
			ans.append(i0 + 1);
			ans.append("\n");
			swap(P, i0, i0 + 1);
			cnt++;
		}

		for (int p = 0; p < 2; p++) {
			for (int i = p; i < N; i += 2) {
				for (int j = p + 2; j < N - i; j += 2) {
					if ( P[j - 2] > P[j] ) {
						ans.append("B ");
						ans.append(j - 1);
						ans.append("\n");
						swap(P, j - 2, j);
						cnt++;
					}
				}
			}
		}

		System.out.println(cnt);
		System.out.print(ans.toString());
	}

	void swap(int[] P, int i, int j) {
		int tmp = P[i];
		P[i] = P[j];
		P[j] = tmp;
	}

	class Number implements Comparable<Number> {
		int val = 0;
		int ord = 0;
		Number(int val) {
			this.val = val;
		}
		@Override
		public int compareTo(Main.Number o) {
			return val < o.val ? -1 : 1;
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
