import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class Main {
	public static void main(String[] args) {
		Main o = new Main();
		o.solve();
	}

	public void solve() {
		FastScanner sc = new FastScanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		long[] B = new long[N];
		long[] C = new long[M];
		for (int i = 0; i < N; i++) {
			B[i] = -sc.nextLong();
		}
		for (int i = 0; i < M; i++) {
			C[i] = sc.nextLong();
		}
		Arrays.sort(B);
		for (int i = 0; i < N; i++) B[i] = -B[i];

		Line[] line = new Line[N];
		int li = 0;
		line[li++] = new Line(1, B[0]);
		for (int i = 1; i < N; i++) {
			Line l = new Line(i + 1, B[i]);
			int min = -1;
			int max = li;
			while ( max - min > 1 ) {
				int mid = (min + max) / 2;
				long c0 = l.intersection(line[mid]);
				if ( c0 < line[mid].c0 ) {
					max = mid;
				} else {
					min = mid;
				}
			}
			if ( max > 0 ) {
				l.c0 = l.intersection(line[max - 1]);
			}
			line[max] = l;
			li = max + 1;
		}

		StringBuilder ans = new StringBuilder();
		for (int i = 0; i < M; i++) {
			int ii = search(line, li, C[i]);
			long a = (B[line[ii].k - 1] + C[i]) * line[ii].k;
			ans.append(a);
			ans.append(" ");
		}
		ans.deleteCharAt(ans.length() - 1);

		System.out.println(ans.toString());
	}

	int search(Line[] line, int li, long c) {
		int min = 0;
		int max = li;
		while ( max - min > 1 ) {
			int mid = (min + max) / 2;
			if ( c >= line[mid].c0 ) {
				min = mid;
			} else {
				max = mid;
			}
		}
		return min;
	}

	class Line {
		int k = 0;
		long b = 0;
		long c0 = 0;
		Line(int k, long b) {
			this.k = k;
			this.b = b;
		}

		long intersection(Line o) {
			return (long)Math.ceil((o.b * o.k - b * k) / (k - o.k));
		}

		long val(long c) {
			return (b + c) * k;
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
