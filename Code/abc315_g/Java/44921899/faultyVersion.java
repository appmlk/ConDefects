import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.NoSuchElementException;

public class Main {
	public static void main(String[] args) {
		Main o = new Main();
		o.solve();
	}

	public void solve() {
		FastScanner sc = new FastScanner(System.in);
		int N = sc.nextInt();
		long A = sc.nextLong();
		long B = sc.nextLong();
		long C = sc.nextLong();
		long X = sc.nextLong();
		long ans = 0;
		long[] d = xgcd(B, C);
		for (int a = 1; a <= N; a++) {
			ans += solve0(N, B, C, X - A * a, d);
		}
		System.out.println(ans);
	}

	long solve0(long N, long B, long C, long X, long[] d) {
		long g = d[0];
		long b = d[1];
		long c = d[2];
		if ( X % g != 0 ) return 0;
		return solve0(N, B / g, C / g, X / g, b, c);
	}

	long solve0(long N, long B, long C, long X, long b, long c) {
		if ( X <= 0 ) return 0;
		// B * b + C * c = 1
		BigInteger bb = BigInteger.valueOf(b);
		BigInteger cc = BigInteger.valueOf(c);
		BigInteger BB = BigInteger.valueOf(B);
		BigInteger CC = BigInteger.valueOf(C);
		BigInteger XX = BigInteger.valueOf(X);
		BigInteger NN = BigInteger.valueOf(N);

		BigInteger b0 = bb.multiply(XX);
		BigInteger c0 = cc.multiply(XX);
		if ( c0.compareTo(BigInteger.ZERO) > 0 ) {
			BigInteger d = c0.divide(BB).add(BigInteger.ONE);
			b0 = b0.add(CC.multiply(d));
			c0 = c0.subtract(BB.multiply(d));
		}
		if ( b0.compareTo(NN) <= 0 ) {
			BigInteger d = NN.subtract(b0).divide(CC).add(BigInteger.ONE);
			b0 = b0.add(CC.multiply(d));
			c0 = c0.subtract(BB.multiply(d));
		}
		BigInteger a0 = NN.subtract(c0).divide(BB).add(BigInteger.ONE);
		BigInteger a1 = b0.subtract(BigInteger.ONE).divide(CC).add(BigInteger.ONE);
		BigInteger a2 = b0.subtract(NN).subtract(BigInteger.ONE).divide(CC).add(BigInteger.ONE);
		BigInteger a3 = BigInteger.ZERO.subtract(c0).divide(BB).add(BigInteger.ONE);
		return ((a0.min(a1)).subtract(a2.max(a3))).longValue();
	}

	static long[] xgcd(long a, long b) {
		long x0 = 1;
		long y0 = 0;
		long x1 = 0;
		long y1 = 1;
		while ( b != 0 ) {
			long q = a / b;
			long tmp = b;
			b = a % b;
			a = tmp;

			tmp = x1;
			x1 = x0 - q * x1;
			x0 = tmp;

			tmp = y1;
			y1 = y0 - q * y1;
			y0 = tmp;
		}
		return new long[] {a, x0, y0};
	}

	long div(long a, long b) {
		if ( a >= 0 ) {
			return a / b;
		} else {
			return -((-a + b - 1) / b);
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
