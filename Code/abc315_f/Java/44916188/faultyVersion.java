import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class Main {
	public static void main(String[] args) {
		Main o = new Main();
		o.solve();
	}

	int K = 14;
	public void solve() {
		FastScanner sc = new FastScanner(System.in);
		int N = sc.nextInt();
		int[] X = new int[N];
		int[] Y = new int[N];
		for (int i = 0; i < N; i++) {
			X[i] = sc.nextInt();
			Y[i] = sc.nextInt();
		}

		double[][] dp = new double[N][K];
		for (int i = 0; i < N; i++) {
			Arrays.fill(dp[i], Long.MAX_VALUE);
		}
		dp[0][0] = 0;
		for (int i = 0; i < N; i++) {
			for (int k = 0; k < K; k++) {
				if ( dp[i][k] >= Long.MAX_VALUE ) continue;
				for (int kn = 0; k + kn < K && i + kn + 1 < N ; kn++) {
					int n = i + kn + 1;
					dp[n][k + kn] = Math.min(dp[n][k + kn], dp[i][k] + dist(i, n, X, Y));
				}
			}
		}

		double ans = dp[N - 1][0];
		for (int k = 1; k < K; k++) {
			if ( dp[N - 1][k] >= Long.MAX_VALUE ) continue;
			ans = Math.min(ans, dp[N - 1][k] + (1 << (k - 1)));
		}

		System.out.println(String.format("%.20f", ans));
	}

	double dist(int i, int j, int[] X, int[] Y) {
		int dx = X[j] - X[i];
		int dy = Y[j] - Y[i];
		return Math.sqrt(dx * dx + dy * dy);
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
