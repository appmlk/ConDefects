import java.io.IOException;
import java.io.InputStream;
import java.util.NoSuchElementException;

public class Main {
	public static void main(String[] args) {
		Main o = new Main();
		o.solve();
	}

	static final long MOD = 998244353;
	public void solve() {
		FastScanner sc = new FastScanner(System.in);
		int N = sc.nextInt();
		int[] A = new int[N];
		for (int i = 0; i < N; i++) {
			A[i] = sc.nextInt();
		}

		int MAX = 200200;
		int[] cnt = new int[MAX];
		for (int i = 0; i < N; i++) {
			cnt[A[i]]++;
		}

		long[][] dp = new long[2][];
		dp[0] = new long[1];
		dp[0][0] = 1;
		for (int i = 0; i < MAX; i++) {
			int ci = i % 2;
			int ni = (i + 1) % 2;
			int cn = dp[ci].length;
			int nn = (cnt[i] + cn - 1) / 2;
			dp[ni] = new long[nn + 1];
			long[] sum = new long[cn + 1];
			for (int j = 1; j <= cn; j++) {
				sum[j] = (sum[j - 1] + dp[ci][j - 1]) % MOD;
			}

			for (int j = 0; j <= nn; j++) {
				int j0 = Math.max(2 * j - cnt[i], 0);
				if ( cn < j0 ) break;
				dp[ni][j] = (dp[ni][j] + sum[cn] - sum[j0] + MOD) % MOD;
			}
		}

		long ans = 0;
		for (int i = 0; i < dp[MAX % 2].length; i++) {
			ans = (ans + dp[MAX % 2][i]) % MOD;
		}

		System.out.println(ans);
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
