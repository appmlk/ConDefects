import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.NoSuchElementException;

public class Main {
	public static void main(String[] args) {
		Main o = new Main();
		o.solve();
	}

	public void solve() {
		FastScanner sc = new FastScanner(System.in);
		String S = sc.next();
		long K = sc.nextLong();
		ArrayList<Integer> y0 = new ArrayList<>();
		ArrayList<Integer> y1 = new ArrayList<>();
		for (int i = 0; i < S.length(); i++) {
			if ( S.charAt(i) == 'Y' ) {
				y0.add(i);
				y1.add(S.length() - i - 1);
			}
		}
		Collections.reverse(y1);

		if ( y0.size() == 0 ) {
			System.out.println(0);
			return;
		}

		int min = 1;
		int max = y0.size() + 1;
		while ( max - min > 1 ) {
			int mid = (min + max) / 2;
			if ( solve0(y0, y1, K, mid) ) {
				min = mid;
			} else {
				max = mid;
			}
		}

		System.out.println(min);
	}

	boolean solve0(ArrayList<Integer> y0, ArrayList<Integer> y1, long K, int a) {
		if ( a % 2 == 0 ) {
			return solve0(y0, K, a / 2, a / 2);
		} else {
			return solve0(y0, K, a - a / 2, a / 2) || solve0(y1, K, a - a / 2, a / 2);
		}
	}

	boolean solve0(ArrayList<Integer> y, long K, int a0, int a1) {
		long k0 = 0;
		for (int i = 0; i < a0; i++) {
			long p = y.get(i);
			if ( i > 0 ) k0 += (p - y.get(i - 1) - 1) * i;
		}
		long k1 = 0;
		for (int i = a0; i < a0 + a1; i++) {
			k1 += ((long)y.get(i)) - y.get(a0 - 1) - (i - a0 + 1);
		}
		if ( k0 + k1 <= K ) return true;
		for (int i = 1; i <= y.size() - a0 - a1; i++) {
			int i0 = i + a0 - 1;
			int i1 = i + a0;
			int i2 = i + a0 + a1 - 1;
			k0 -= y.get(i0 - 1) - y.get(i - 1) - (a0 - 1);
			k0 += ((long)(a0 - 1)) * (y.get(i0) - y.get(i0 - 1));
			k1 -= ((long)a1) * (y.get(i0) - y.get(i0 - 1) - 1);
			k1 += y.get(i2) - y.get(i0) - 1 - (a1 - 1);
			if ( k0 + k1 <= K ) return true;
		}
		return false;
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
