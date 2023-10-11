import java.util.*;
import java.io.*;
import java.math.*;
import java.util.function.*;
public class Main implements Runnable {
	private static boolean DEBUG;
	public static void main(final String[] args) {
		DEBUG = args.length > 0 && args[0].equals("-DEBUG");
		Thread.setDefaultUncaughtExceptionHandler((t, e) -> { e.printStackTrace(); System.exit(1); });
		new Thread(null, new Main(), "", 1 << 31).start();
	}

	@Override
	public void run() { Solver solver = new Solver(); solver.solve(); solver.exit(); }

	public static final class FastInputStream {
		private static final int BUF_SIZE = 1 << 14;
		private final InputStream in;
		private final byte buf[] = new byte[BUF_SIZE];
		private int pos = 0;
		private int count = 0;
		private static final int TOKEN_SIZE = 1 << 20;
		private final byte tokenBuf[] = new byte[TOKEN_SIZE];

		public FastInputStream(final InputStream in) {
			this.in = in;
		}
		private final void readBuf() {
			pos = 0;
			try { count = in.read(buf); }
			catch(IOException e) { e.printStackTrace(); }
		}
		private final boolean hasNextByte() {
			if(pos < count) return true;
			readBuf();
			return count > 0;
		}
		private final byte read() { if(hasNextByte()) return buf[pos ++]; else throw new NoSuchElementException(); }
		private final boolean isPrintableChar(final byte c) { return 33 <= c && c <= 126; }
		private final boolean isNumber(final byte c) { return 48 <= c && c <= 57; }
		private final void skipUnprintable() {
			while(true) {
				for(int i = pos; i < count; i ++) {
					if(isPrintableChar(buf[i])) { pos = i; return; }
				}
				readBuf();
				if(count <= 0) throw new NoSuchElementException();
			}
		}
		private final boolean readEOL() {
			if(!hasNextByte()) return true;
			if(buf[pos] == 13) {
				pos ++;
				if(hasNextByte() && buf[pos] == 10) pos ++;
				return true;
			}
			if(buf[pos] == 10) {
				pos ++;
				return true;
			}
			return false;
		}

		public final char nextChar() {
			skipUnprintable();
			return (char)buf[pos ++];
		}
		public final String next() {
			skipUnprintable();
			int tokenCount = 0;
			outer: while(count > 0) {
				for(int i = pos; i < count; i ++) {
					final byte b = buf[i];
					if(!isPrintableChar(b)) { pos = i; break outer; }
					tokenBuf[tokenCount ++] = b;
				}
				readBuf();
			}
			return new String(tokenBuf, 0, tokenCount);
		}
		public final String nextLine() {
			readEOL();
			if(!hasNextByte()) throw new NoSuchElementException();
			int tokenCount = 0;
			while(!readEOL()) tokenBuf[tokenCount ++] = read();
			return new String(tokenBuf, 0, tokenCount);
		}
		public final int nextInt() {
			skipUnprintable();
			int n = 0;
			boolean minus = false;
			if(buf[pos] == 45) {
				minus = true;
				pos ++;
				if(!hasNextByte() || !isNumber(buf[pos])) throw new InputMismatchException();
			}
			outer: while(count > 0) {
				for(int i = pos; i < count; i ++) {
					final byte b = buf[i];
					if(!isPrintableChar(b)) { pos = i; break outer; }
					if(!isNumber(b)) throw new InputMismatchException();
					if(minus) {
						if(n < - 214748364) throw new ArithmeticException("int overflow");
						if(n == - 214748364 && b > 56) throw new ArithmeticException("int overflow");
						n = (n << 3) + (n << 1) + 48 - b;
					}else {
						if(n > 214748364) throw new ArithmeticException("int overflow");
						if(n == 214748364 && b >= 56) throw new ArithmeticException("int overflow");
						n = (n << 3) + (n << 1) - 48 + b;
					}
				}
				readBuf();
			}
			return n;
		}
		public final long nextLong() {
			skipUnprintable();
			long n = 0;
			boolean minus = false;
			if(buf[pos] == 45) {
				minus = true;
				pos ++;
				if(!hasNextByte() || !isNumber(buf[pos])) throw new InputMismatchException();
			}
			outer: while(count > 0) {
				for(int i = pos; i < count; i ++) {
					final byte b = buf[i];
					if(!isPrintableChar(b)) { pos = i; break outer; }
					if(!isNumber(b)) throw new InputMismatchException();
					if(minus) {
						if(n < - 922337203685477580l) throw new ArithmeticException("long overflow");
						if(n == - 922337203685477580l && b > 56) throw new ArithmeticException("long overflow");
						n = (n << 3) + (n << 1) + 48 - b;
					}else {
						if(n > 922337203685477580l) throw new ArithmeticException("long overflow");
						if(n == 922337203685477580l && b >= 56) throw new ArithmeticException("long overflow");
						n = (n << 3) + (n << 1) - 48 + b;
					}
				}
				readBuf();
			}
			return n;
		}
		public final double nextDouble() { return Double.parseDouble(next()); }

		public final void close() {
			try { in.close(); }
			catch(IOException e) { e.printStackTrace(); }
		}
	}

	public static final class FastOutputStream {
		private static final int BUF_SIZE = 1 << 13;
		private final byte buf[] = new byte[BUF_SIZE];
		private final OutputStream out;
		private int count = 0;
		private static final byte TRUE_BYTES[] = {116, 114, 117, 101};
		private static final byte FALSE_BYTES[] = {102, 97, 108, 115, 101};
		private static final byte INT_MIN_BYTES[] = {45, 50, 49, 52, 55, 52, 56, 51, 54, 52, 56};
		private static final byte LONG_MIN_BYTES[] = {45, 57, 50, 50, 51, 51, 55, 50, 48, 51,
													54, 56, 53, 52, 55, 55, 53, 56, 48, 56};
		private static final int TOKEN_SIZE = 20;
		private final byte tokenBuf[] = new byte[TOKEN_SIZE];
		private static final int PRECISION = 10;
		public FastOutputStream(OutputStream out) {
			this.out = out;
		}
		public final void print() {  }
		public final void write(final byte b) {
			if(count == BUF_SIZE) internalFlush();
			buf[count ++] = b;
		}
		public final void print(final char c) { write((byte) c); }
		public final void print(final boolean b) {
			if(b) {
				if(count + 4 > BUF_SIZE) internalFlush();
				System.arraycopy(TRUE_BYTES, 0, buf, count, TRUE_BYTES.length);
				count += TRUE_BYTES.length;
			}else {
				if(count + 5 > BUF_SIZE) internalFlush();
				System.arraycopy(FALSE_BYTES, 0, buf, count, FALSE_BYTES.length);
				count += FALSE_BYTES.length;
			}
		}
		public final void print(int x) {
			if(count + 11 > BUF_SIZE) internalFlush();
			if(x == Integer.MIN_VALUE) {
				System.arraycopy(INT_MIN_BYTES, 0, buf, count, INT_MIN_BYTES.length);
				count += INT_MIN_BYTES.length;
				return;
			}
			if(x == 0) {
				buf[count ++] = 48;
				return;
			}
			if(x < 0) {
				buf[count ++] = 45;
				x = - x;
			}
			int tokenCount = 11;
			while(x > 0) {
				final int y = x / 10;
				tokenBuf[-- tokenCount] = (byte) (x - (y << 3) - (y << 1) + 48);
				x = y;
			}
			System.arraycopy(tokenBuf, tokenCount, buf, count, 11 - tokenCount);
			count += 11 - tokenCount;
		}
		public final void print(long x) {
			if(count + 20 > BUF_SIZE) internalFlush();
			if(x == Long.MIN_VALUE) {
				System.arraycopy(LONG_MIN_BYTES, 0, buf, count, LONG_MIN_BYTES.length);
				count += LONG_MIN_BYTES.length;
				return;
			}
			if(x == 0) {
				buf[count ++] = 48;
				return;
			}
			if(x < 0) {
				buf[count ++] = 45;
				x = - x;
			}
			int tokenCount = 20;
			while(x > 0) {
				final long y = x / 10;
				tokenBuf[-- tokenCount] = (byte) (x - (y << 3) - (y << 1) + 48);
				x = y;
			}
			System.arraycopy(tokenBuf, tokenCount, buf, count, 20 - tokenCount);
			count += 20 - tokenCount;
		}
		public final void print(final double d) { print(d, PRECISION); }
		public final void print(double d, final int precision) {
			if(count == BUF_SIZE) internalFlush();
			if(d < 0) {
				buf[count ++] = 45;
				d = - d;
			}
			d += Math.pow(10, - precision) / 2;
			print((long)d);
			if(precision == 0) return;
			if(count + precision + 1 > BUF_SIZE) internalFlush();
			buf[count ++] = 46;
			d -= (long)d;
			for(int i = 0; i < precision; i ++) {
				d *= 10;
				buf[count ++] = (byte)((int)d + 48);
				d -= (int) d;
			}
		}
		public final void print(final String s) { print(s.getBytes()); }
		public final void print(final Object o) { print(o.toString()); }
		public final void print(final byte[] a) {
			if(count + a.length > BUF_SIZE) internalFlush();
			System.arraycopy(a, 0, buf, count, a.length);
			count += a.length;
		}
		public final void print(final char[] a) {
			if(count + a.length > BUF_SIZE) internalFlush();
			for(int i = 0; i < a.length; i ++) buf[count + i] = (byte)a[i];
			count += a.length;
		}
		public final void println() { print('\n'); }
		public final void println(final char c) { print(c); println(); }
		public final void println(final boolean b) { print(b); println(); }
		public final void println(final int x) { print(x); println(); }
		public final void println(final long x) { print(x); println(); }
		public final void println(final double d) { print(d); println(); }
		public final void println(final double d, final int precision) { print(d, precision); println(); }
		public final void println(final String s) { print(s); println(); }
		public final void println(final Object o) { print(o); println(); }
		public final void println(final char[] a) { print(a); println(); }
		public final void println(final int[] a) {
			for(int i = 0; i < a.length; i ++) {
				print(a[i]);
				print(i == a.length - 1 ? '\n' : ' ');
			}
		}
		public final void println(final long[] a) {
			for(int i = 0; i < a.length; i ++) {
				print(a[i]);
				print(i == a.length - 1 ? '\n' : ' ');
			}
		}
		public final void println(final double[] a) {
			for(int i = 0; i < a.length; i ++) {
				print(a[i]);
				print(i == a.length - 1 ? '\n' : ' ');
			}
		}
		public final void println(final double[] a, final int precision) {
			for(int i = 0; i < a.length; i ++) {
				print(a[i], precision);
				print(i == a.length - 1 ? '\n' : ' ');
			}
		}
		public final void println(final String[] a) {
			for(int i = 0; i < a.length; i ++) {
				print(a[i]);
				print(i == a.length - 1 ? '\n' : ' ');
			}
		}
		public final void println(final Object[] a) {
			for(int i = 0; i < a.length; i ++) {
				print(a[i]);
				print(i == a.length - 1 ? '\n' : ' ');
			}
		}
		private final void internalFlush() {
			try {
				out.write(buf, 0, count);
				count = 0;
			}
			catch(IOException e) { e.printStackTrace(); }
		}
		public final void flush() {
			try {
				out.write(buf, 0, count);
				out.flush();
				count = 0;
			}
			catch(IOException e) { e.printStackTrace(); }
		}
		public final void close() {
			try { out.close(); }
			catch(IOException e) { e.printStackTrace(); }
		}
	}

	public static final class Solver {
		private static final FastInputStream in = new FastInputStream(System.in);
		public Solver() {  }

		private static final String nline() { return in.nextLine(); }
		private static final String[] nline(final int n) { final String a[] = new String[n]; for(int i = 0; i < n; i ++) a[i] = nline(); return a; }
		private static final char nc() { return in.nextChar(); }
		private static final char[] nc(int n) {
			final String str = nline();
			if(n < 0) n = str.length();
			final char a[] = new char[n];
			for(int i = 0; i < n; i ++) a[i] = str.charAt(i);
			return a;
		}
		private static final char[][] nc(final int n, final int m) { final char a[][] = new char[n][m]; for(int i = 0; i < n; i ++) a[i] = nc(m); return a; }
		private static final boolean[] nb(int n, final char t) {
			final char c[] = nc(-1);
			if(n < 0) n = c.length;
			final boolean a[] = new boolean[n];
			for(int i = 0; i < n; i ++) a[i] = c[i] == t;
			return a;
		}
		private static final boolean[][] nb(final int n, final int m, final char t) { final boolean a[][] = new boolean[n][]; for(int i = 0; i < n; i ++) a[i] = nb(m, t); return a; }
		private static final int ni() { return in.nextInt(); }
		private static final int[] ni(final int n) { final int a[] = new int[n]; for(int i = 0; i < n; i ++) a[i] = ni(); return a; }
		private static final int[][] ni(final int n, final int m) { final int a[][] = new int[n][]; for(int i = 0; i < n; i ++) a[i] = ni(m); return a; }
		private static final long nl() { return in.nextLong(); }
		private static final long[] nl(final int n) { final long a[] = new long[n]; for(int i = 0; i < n; i ++) a[i] = nl(); return a; }
		private static final long[][] nl(final int n, final int m) { final long a[][] = new long[n][]; for(int i = 0; i < n; i ++) a[i] = nl(m); return a; }
		private static final double nd() { return in.nextDouble(); }
		private static final double[] nd(final int n) { final double a[] = new double[n]; for(int i = 0; i < n; i ++) a[i] = nd(); return a; }
		private static final double[][] nd(final int n, final int m) { final double a[][] = new double[n][]; for(int i = 0; i < n; i ++) a[i] = nd(m); return a; }
		private static final String ns() { return in.next(); }
		private static final String[] ns(final int n) { final String a[] = new String[n]; for(int i = 0; i < n; i ++) a[i] = ns(); return a; }
		private static final String[][] ns(final int n, final int m) { final String a[][] = new String[n][]; for(int i = 0; i < n; i ++) a[i] = ns(m); return a; }

		private static final char booleanToChar(final boolean b) { return b ? '#' : '.'; }
		private static final char[] booleanToChar(final boolean... a) {
			final char c[] = new char[a.length];
			for(int i = 0; i < a.length; i ++) c[i] = booleanToChar(a[i]);
			return c;
		}

		private static final FastOutputStream out = new FastOutputStream(System.out);
		private static final FastOutputStream err = new FastOutputStream(System.err);
		private static final void prt() { out.print(); }
		private static final void prt(final char c) { out.print(c); }
		private static final void prt(final boolean b) { out.print(b); }
		private static final void prt(final int x) { out.print(x); }
		private static final void prt(final long x) { out.print(x); }
		private static final void prt(final double d) { out.print(d); }
		private static final void prt(final String s) { out.print(s); }
		private static final void prt(final Object o) { out.print(o); }
		private static final void prtln() { out.println(); }
		private static final void prtln(final char c) { out.println(c); }
		private static final void prtln(final boolean b) { out.println(b); }
		private static final void prtln(final int x) { out.println(x); }
		private static final void prtln(final long x) { out.println(x); }
		private static final void prtln(final double d) { out.println(d); }
		private static final void prtln(final String s) { out.println(s); }
		private static final void prtln(final Object o) { out.println(o); }
		private static final void prtln(final char... a) { out.println(a); }
		private static final void prtln(final boolean... a) { out.println(booleanToChar(a)); }
		private static final void prtln(final int... a) { out.println(a); }
		private static final void prtln(final long... a) { out.println(a); }
		private static final void prtln(final double... a) { out.println(a); }
		private static final void prtln(final double[] a, int precision) { out.println(a, precision); }
		private static final void prtln(final String... a) { out.println(a); }
		private static final void prtln(final Object[] a) { out.println(a); }
		private static final void prtlns(final char... a) { for(char ele : a) prtln(ele); }
		private static final void prtlns(final boolean... a) { for(boolean ele : a) prtln(ele); }
		private static final void prtlns(final int... a) { for(int ele : a) prtln(ele); }
		private static final void prtlns(final long... a) { for(long ele : a) prtln(ele); }
		private static final void prtlns(final double... a) { for(double ele : a) prtln(ele); }
		private static final void prtlns(final Object[] a) { for(Object ele : a) prtln(ele); }
		private static final void prtln(final char[][] a) { for(char[] ele : a) prtln(ele); }
		private static final void prtln(final boolean[][] a) { for(boolean[] ele : a) prtln(ele); }
		private static final void prtln(final int[][] a) { for(int[] ele : a) prtln(ele); }
		private static final void prtln(final long[][] a) { for(long[] ele : a) prtln(ele); }
		private static final void prtln(final double[][] a) { for(double[] ele : a) prtln(ele); }
		private static final void prtln(final double[][] a, int precision) { for(double[] ele : a) prtln(ele, precision); }
		private static final void prtln(final String[][] a) { for(String[] ele : a) prtln(ele); }
		private static final void prtln(final Object[][] a) { for(Object[] ele : a) prtln(ele); }

		private static final void errprt() { if(DEBUG) err.print(); }
		private static final void errprt(final char c) { if(DEBUG) err.print(c); }
		private static final void errprt(final boolean b) { if(DEBUG) err.print(booleanToChar(b)); }
		private static final void errprt(final int x) { if(DEBUG) if(isINF(x)) err.print('_'); else err.print(x); }
		private static final void errprt(final long x) { if(DEBUG) if(isINF(x)) err.print('_'); else err.print(x); }
		private static final void errprt(final double d) { if(DEBUG) err.print(d); }
		private static final void errprt(final String s) { if(DEBUG) err.print(s); }
		private static final void errprt(final Object o) { if(DEBUG) err.print(o); }
		private static final void errprtln() { if(DEBUG) err.println(); }
		private static final void errprtln(final char c) { if(DEBUG) err.println(c); }
		private static final void errprtln(final boolean b) { if(DEBUG) err.println(booleanToChar(b)); }
		private static final void errprtln(final int x) { if(DEBUG) if(isINF(x)) err.println('_'); else err.println(x); }
		private static final void errprtln(final long x) { if(DEBUG) if(isINF(x)) err.println('_'); else err.println(x); }
		private static final void errprtln(final double d) { if(DEBUG) err.println(d); }
		private static final void errprtln(final String s) { if(DEBUG) err.println(s); }
		private static final void errprtln(final Object o) { if(DEBUG) err.println(o); }
		private static final void errprtln(final char... a) { if(DEBUG) err.println(a); }
		private static final void errprtln(final boolean... a) { if(DEBUG) err.println(booleanToChar(a)); }
		private static final void errprtln(final int... a) {
			if(DEBUG) {
				boolean start = false;
				for(int ele : a) {
					errprt(ele);
					if(!start) errprt(' ');
					start = false;
				}
				err.println();
			}
		}
		private static final void errprtln(final long... a) {
			if(DEBUG) {
				boolean start = false;
				for(long ele : a) {
					errprt(ele);
					if(!start) errprt(' ');
					start = false;
				}
				err.println();
			}
		}
		private static final void errprtln(final double... a) { if(DEBUG) err.println(a); }
		private static final void errprtln(final double[] a, final int precision) { if(DEBUG) err.println(a, precision); }
		private static final void errprtln(final String... a) { if(DEBUG) err.println(a); }
		private static final void errprtln(final Object[] a) { if(DEBUG) err.println(a); }
		private static final void errprtlns(final char... a) { if(DEBUG) for(char ele : a) errprtln(ele); }
		private static final void errprtlns(final boolean... a) { if(DEBUG) for(boolean ele : a) errprtln(ele); }
		private static final void errprtlns(final int... a) { if(DEBUG) for(int ele : a) errprtln(ele); }
		private static final void errprtlns(final long... a) { if(DEBUG) for(long ele : a) errprtln(ele); }
		private static final void errprtlns(final double... a) { if(DEBUG) for(double ele : a) errprtln(ele); }
		private static final void errprtlns(final Object[] a) { if(DEBUG) for(Object ele : a) errprtln(ele); }
		private static final void errprtln(final char[][] a) { if(DEBUG) for(char[] ele : a) errprtln(ele); }
		private static final void errprtln(final boolean[][] a) { if(DEBUG) for(boolean[] ele : a) errprtln(ele); }
		private static final void errprtln(final int[][] a) { if(DEBUG) for(int[] ele : a) errprtln(ele); }
		private static final void errprtln(final long[][] a) { if(DEBUG) for(long[] ele : a) errprtln(ele); }
		private static final void errprtln(final double[][] a) { if(DEBUG) for(double[] ele : a) errprtln(ele); }
		private static final void errprtln(final double[][] a, int precision) { if(DEBUG) for(double[] ele : a) errprtln(ele, precision); }
		private static final void errprtln(final String[][] a) { if(DEBUG) for(String[] ele : a) errprtln(ele); }
		private static final void errprtln(final Object[][] a) { if(DEBUG) for(Object[] ele : a) errprtln(ele); }
		private static final void errprtlns(final Object[][] a) { if(DEBUG) for(Object[] ele : a) { errprtlns(ele); errprtln(); } }

		private static final void reply(final boolean b) { prtln(b ? "Yes" : "No"); }
		private static final void REPLY(final boolean b) { prtln(b ? "YES" : "NO"); }

		private static final void flush() { out.flush(); if(DEBUG) err.flush(); }
		private static final void assertion(final boolean b) { if(!b) { flush(); throw new AssertionError(); } }
		private static final void assertion(final boolean b, final char c) { if(!b) { errprtln(c); flush(); throw new AssertionError(); } }
		private static final void assertion(final boolean b, final boolean b2) { if(!b) { errprtln(b2); flush(); throw new AssertionError(); } }
		private static final void assertion(final boolean b, final int x) { if(!b) { errprtln(x); flush(); throw new AssertionError(); } }
		private static final void assertion(final boolean b, final long x) { if(!b) { errprtln(x); flush(); throw new AssertionError(); } }
		private static final void assertion(final boolean b, final double d) { if(!b) { errprtln(d); flush(); throw new AssertionError(); } }
		private static final void assertion(final boolean b, final String s) { if(!b) { errprtln(s); flush(); throw new AssertionError(); } }
		private static final void assertion(final boolean b, final Object o) { if(!b) { errprtln(o); flush(); throw new AssertionError(); } }
		private static final void assertion(final boolean b, final char... a) { if(!b) { errprtln(a); flush(); throw new AssertionError(); } }
		private static final void assertion(final boolean b, final boolean... a) { if(!b) { errprtln(a); flush(); throw new AssertionError(); } }
		private static final void assertion(final boolean b, final int... a) { if(!b) { errprtln(a); flush(); throw new AssertionError(); } }
		private static final void assertion(final boolean b, final long... a) { if(!b) { errprtln(a); flush(); throw new AssertionError(); } }
		private static final void assertion(final boolean b, final double... a) { if(!b) { errprtln(a); flush(); throw new AssertionError(); } }
		private static final void assertion(final boolean b, final String... a) { if(!b) { errprtln(a); flush(); throw new AssertionError(); } }
		private static final void assertion(final boolean b, final Object[] a) { if(!b) { errprtln(a); flush(); throw new AssertionError(); } }
		private static final void assertion(final boolean b, final char[][] a) { if(!b) { errprtln(a); flush(); throw new AssertionError(); } }
		private static final void assertion(final boolean b, final boolean[][] a) { if(!b) { errprtln(a); flush(); throw new AssertionError(); } }
		private static final void assertion(final boolean b, final int[][] a) { if(!b) { errprtln(a); flush(); throw new AssertionError(); } }
		private static final void assertion(final boolean b, final long[][] a) { if(!b) { errprtln(a); flush(); throw new AssertionError(); } }
		private static final void assertion(final boolean b, final double[][] a) { if(!b) { errprtln(a); flush(); throw new AssertionError(); } }
		private static final void assertion(final boolean b, final String[][] a) { if(!b) { errprtln(a); flush(); throw new AssertionError(); } }
		private static final void assertion(final boolean b, final Object[][] a) { if(!b) { errprtln(a); flush(); throw new AssertionError(); } }
		private static final void inclusiveRangeCheck(final int i, final int max) { inclusiveRangeCheck(i, 0, max); }
		private static final void inclusiveRangeCheck(final int i, final int min, final int max) { rangeCheck(i, min, max + 1); }
		private static final void inclusiveRangeCheck(final long i, final long max) { inclusiveRangeCheck(i, 0, max); }
		private static final void inclusiveRangeCheck(final long i, final long min, final long max) { rangeCheck(i, min, max + 1); }
		private static final void rangeCheck(final int i, final int max) { rangeCheck(i, 0, max); }
		private static final void rangeCheck(final int i, final int min, final int max) { if(i < min || i >= max) throw new IndexOutOfBoundsException(String.format("Index %d out of bounds for length %d", i, max)); }
		private static final void rangeCheck(final long i, final long max) { rangeCheck(i, 0, max); }
		private static final void rangeCheck(final long i, final long min, final long max) { if(i < min || i >= max) throw new IndexOutOfBoundsException(String.format("Index %d out of bounds for length %d", i, max)); }
		private static final void nonNegativeCheck(final long x) { nonNegativeCheck(x, "the argument"); }
		private static final void nonNegativeCheck(final long x, final String attribute) { if(x < 0) throw new IllegalArgumentException(String.format("%s %d is negative", attribute, x)); }
		private static final void positiveCheck(final long x) { positiveCheck(x, "the argument"); }
		private static final void positiveCheck(final long x, final String attribute) { if(x <= 0) throw new IllegalArgumentException(String.format("%s %d is negative", attribute, x)); }

		private static final void exit() { flush(); System.exit(0); }
		private static final void exit(final char c) { prtln(c); exit(); }
		private static final void exit(final boolean b) { prtln(b); exit(); }
		private static final void exit(final int x) { prtln(x); exit(); }
		private static final void exit(final long x) { prtln(x); exit(); }
		private static final void exit(final double d) { prtln(d); exit(); }
		private static final void exit(final String s) { prtln(s); exit(); }
		private static final void exit(final Object o) { prtln(o); exit(); }
		private static final void exit(final char... a) { prtln(a); exit(); }
		private static final void exit(final boolean... a) { prtln(a); exit(); }
		private static final void exit(final int... a) { prtln(a); exit(); }
		private static final void exit(final long... a) { prtln(a); exit(); }
		private static final void exit(final double... a) { prtln(a); exit(); }
		private static final void exit(final String... a) { prtln(a); exit(); }
		private static final void exit(final Object[] a) { prtln(a); exit(); }
		private static final void exit(final char[][] a) { prtln(a); exit(); }
		private static final void exit(final boolean[][] a) { prtln(a); exit(); }
		private static final void exit(final int[][] a) { prtln(a); exit(); }
		private static final void exit(final long[][] a) { prtln(a); exit(); }
		private static final void exit(final double[][] a) { prtln(a); exit(); }
		private static final void exit(final String[][] a) { prtln(a); exit(); }
		private static final void exit(final Object[][] a) { prtln(a); exit(); }


		private static final long INF = (long)4e18;
		private static final boolean isPlusINF(final long x) { return x > INF / 10; }
		private static final boolean isMinusINF(final long x) { return isPlusINF(- x); }
		private static final boolean isINF(final long x) { return isPlusINF(x) || isMinusINF(x); }
		private static final int I_INF = (int)1e9 + 1000;
		private static final boolean isPlusINF(final int x) { return x > I_INF / 10; }
		private static final boolean isMinusINF(final int x) { return isPlusINF(- x); }
		private static final boolean isINF(final int x) { return isPlusINF(x) || isMinusINF(x); }


		private static final int min(final int a, final int b) { return Math.min(a, b); }
		private static final long min(final long a, final long b) { return Math.min(a, b); }
		private static final double min(final double a, final double b) { return Math.min(a, b); }
		private static final <T extends Comparable<T>> T min(final T a, final T b) { return a.compareTo(b) <= 0 ? a : b; }
		private static final int min(final int... x) { int min = x[0]; for(int val : x) min = min(min, val); return min; }
		private static final long min(final long... x) { long min = x[0]; for(long val : x) min = min(min, val); return min; }
		private static final double min(final double... x) { double min = x[0]; for(double val : x) min = min(min, val); return min; }
		private static final int max(final int a, final int b) { return Math.max(a, b); }
		private static final long max(final long a, final long b) { return Math.max(a, b); }
		private static final double max(final double a, final double b) { return Math.max(a, b); }
		private static final <T extends Comparable<T>> T max(final T a, final T b) { return a.compareTo(b) >= 0 ? a : b; }
		private static final int max(final int... x) { int max = x[0]; for(int val : x) max = max(max, val); return max; }
		private static final long max(final long... x) { long max = x[0]; for(long val : x) max = max(max, val); return max; }
		private static final double max(final double... x) { double max = x[0]; for(double val : x) max = max(max, val); return max; }
		private static final <T extends Comparable<T>> T max(final T[] x) { T max = x[0]; for(T val : x) max = max(max, val); return max; }
		private static final int max(final int[][] a) { int max = a[0][0]; for(int[] ele : a) max = max(max, max(ele)); return max; }
		private static final long max(final long[][] a) { long max = a[0][0]; for(long[] ele : a) max = max(max, max(ele)); return max; }
		private static final double max(final double[][] a) { double max = a[0][0]; for(double[] ele : a) max = max(max, max(ele)); return max; }
		private static final <T extends Comparable<T>> T max(final T[][] a) { T max = a[0][0]; for(T[] ele : a) max = max(max, max(ele)); return max; }
		private static final long sum(final int... a) { long sum = 0; for(int ele : a) sum += ele; return sum; }
		private static final long sum(final long... a) { long sum = 0; for(long ele : a) sum += ele; return sum; }
		private static final double sum(final double... a) { double sum = 0; for(double ele : a) sum += ele; return sum; }
		private static final int sum(final boolean... a) { int sum = 0; for(boolean ele : a) sum += ele ? 1 : 0; return sum; }
		private static final long[] sums(final int[] a) { long sum[] = new long[a.length + 1]; sum[0] = 0; for(int i = 0; i < a.length; i ++) sum[i + 1] = sum[i] + a[i]; return sum; }
		private static final long[] sums(final long[] a) { long sum[] = new long[a.length + 1]; sum[0] = 0; for(int i = 0; i < a.length; i ++) sum[i + 1] = sum[i] + a[i]; return sum; }
		private static final double[] sums(final double[] a) { double sum[] = new double[a.length + 1]; sum[0] = 0; for(int i = 0; i < a.length; i ++) sum[i + 1] = sum[i] + a[i]; return sum; }
		private static final int[] sums(final boolean[] a) { int sum[] = new int[a.length + 1]; sum[0] = 0; for(int i = 0; i < a.length; i ++) sum[i + 1] = sum[i] + (a[i] ? 1 : 0); return sum; }
		private static final long[][] sums(final int[][] a) {
			final long sum[][] = new long[a.length + 1][a[0].length + 1];
			for(int i = 0; i < a.length; i ++) {
				for(int j = 0; j < a[i].length; j ++) sum[i + 1][j + 1] = sum[i + 1][j] + sum[i][j + 1] - sum[i][j] + a[i][j];
			}
			return sum;
		}
		private static final long[][] sums(final long[][] a) {
			final long sum[][] = new long[a.length + 1][a[0].length + 1];
			for(int i = 0; i < a.length; i ++) {
				for(int j = 0; j < a[i].length; j ++) sum[i + 1][j + 1] = sum[i + 1][j] + sum[i][j + 1] - sum[i][j] + a[i][j];
			}
			return sum;
		}
		private static final double[][] sums(final double[][] a) {
			final double sum[][] = new double[a.length + 1][a[0].length + 1];
			for(int i = 0; i < a.length; i ++) {
				for(int j = 0; j < a[i].length; j ++) sum[i + 1][j + 1] = sum[i + 1][j] + sum[i][j + 1] - sum[i][j] + a[i][j];
			}
			return sum;
		}
		private static final int[][] sums(final boolean[][] a) {
			final int sum[][] = new int[a.length + 1][a[0].length + 1];
			for(int i = 0; i < a.length; i ++) {
				for(int j = 0; j < a[i].length; j ++) sum[i + 1][j + 1] = sum[i + 1][j] + sum[i][j + 1] - sum[i][j] + (a[i][j] ? 1 : 0);
			}
			return sum;
		}
		private static final int constrain(final int x, final int l, final int r) { return min(max(x, min(l, r)), max(l, r)); }
		private static final long constrain(final long x, final long l, final long r) { return min(max(x, min(l, r)), max(l, r)); }
		private static final double constrain(final double x, final double l, final double r) { return min(max(x, min(l, r)), max(l, r)); }
		private static final int abs(final int x) { return x >= 0 ? x : - x; }
		private static final long abs(final long x) { return x >= 0 ? x : - x; }
		private static final double abs(final double x) { return x >= 0 ? x : - x; }
		private static final int signum(final int x) { return x > 0 ? 1 : x < 0 ? -1 : 0; }
		private static final int signum(final long x) { return x > 0 ? 1 : x < 0 ? -1 : 0; }
		private static final int signum(final double x) { return x > 0 ? 1 : x < 0 ? -1 : 0; }
		private static final long round(final double x) { return Math.round(x); }
		private static final long floor(final double x) { return round(Math.floor(x)); }
		private static final int divfloor(final int a, final int b) { return signum(a) == signum(b) ? a / b : - divceil(abs(a), abs(b)); }
		private static final long divfloor(final long a, final long b) { return signum(a) == signum(b) ? a / b : - divceil(abs(a), abs(b)); }
		private static final long ceil(final double x) { return round(Math.ceil(x)); }
		private static final int divceil(final int a, final int b) { return a >= 0 && b > 0 ? (a + b - 1) / b : a < 0 && b < 0 ? divceil(abs(a), abs(b)) : - divfloor(abs(a), abs(b)); }
		private static final long divceil(final long a, final long b) { return a >= 0 && b > 0 ? (a + b - 1) / b : a < 0 && b < 0 ? divceil(abs(a), abs(b)) : - divfloor(abs(a), abs(b)); }
		private static final boolean mulGreater(final long a, final long b, long c) { return b == 0 ? c < 0 : b < 0 ? mulLess(a, - b, - c) : a > divfloor(c, b); } // a * b > c
		private static final boolean mulGreaterEquals(final long a, final long b, final long c) { return b == 0 ? c <= 0 : b < 0 ? mulLessEquals(a, - b, - c) : a >= divceil(c, b); } // a * b >= c
		private static final boolean mulLess(final long a, final long b, final long c) { return !mulGreaterEquals(a, b, c); } // a * b < c
		private static final boolean mulLessEquals(final long a, final long b, final long c) { return !mulGreater(a, b, c); } // a * b <= c
		private static final double sqrt(final int x) { return Math.sqrt((double)x); }
		private static final double sqrt(final long x) { return Math.sqrt((double)x); }
		private static final double sqrt(final double x) { return Math.sqrt(x); }
		private static final int floorsqrt(final int x) { int s = (int)floor(sqrt(x)) + 1; while(s * s > x) s --; return s; }
		private static final long floorsqrt(final long x) { long s = floor(sqrt(x)) + 1; while(s * s > x) s --; return s; }
		private static final int ceilsqrt(final int x) { int s = (int)ceil(sqrt(x)); while(s * s >= x) s --; s ++; return s; }
		private static final long ceilsqrt(final long x) { long s = ceil(sqrt(x)); while(s * s >= x) s --; s ++; return s; }
		private static final long fact(final int n) { long ans = 1; for(int i = 1; i <= n; i ++) ans = Math.multiplyExact(ans, i); return ans; }
		private static final long naiveP(final long n, final long r) { long ans = 1; for(int i = 0; i < r; i ++) ans = Math.multiplyExact(ans, n - i); return ans; }
		private static final long naiveC(final long n, final long r) { long ans = 1; for(int i = 0; i < r; i ++) { ans = Math.multiplyExact(ans, n - i); ans /= (i + 1); } return ans; }
		private static final double pow(final double x, final double y) { return Math.pow(x, y); }
		private static final long pow(long x, long y) {
			long ans = 1;
			while(true) {
				if((y & 1) != 0) ans = Math.multiplyExact(ans, x);
				y >>= 1;
				if(y <= 0) return ans;
				x = Math.multiplyExact(x, x);
			}
		}
		private static final double pow(double x, long y) {
			double ans = 1;
			while(true) {
				if((y & 1) != 0) ans *= x;
				y >>= 1;
				if(y <= 0) return ans;
				x *= x;
			}
		}
		private static final int gcd(int a, int b) { while(true) { if(b == 0) return a; int tmp = a; a = b; b = tmp % b; } }
		private static final long gcd(long a, long b) { while(true) { if(b == 0) return a; long tmp = a; a = b; b = tmp % b; } }
		private static final long lcm(final long a, final long b) { return a / gcd(a, b) * b; }
		private static final int gcd(final int... a) { int gcd = 0; for(int ele : a) gcd = gcd(ele, gcd); return gcd; }
		private static final long gcd(final long... a) { long gcd = 0; for(long ele : a) gcd = gcd(ele, gcd); return gcd; }
		private static final double random() { return Math.random(); }
		private static final int random(final int max) { return (int)floor(random() * max); }
		private static final long random(final long max) { return floor(random() * max); }
		private static final double random(final double max) { return random() * max; }
		private static final int random(final int min, final int max) { return random(max - min) + min; }
		private static final long random(final long min, final long max) { return random(max - min) + min; }
		private static final double random(final double min, final double max) { return random(max - min) + min; }

		private static final boolean isUpper(final char c) { return c >= 'A' && c <= 'Z'; }
		private static final boolean isLower(final char c) { return c >= 'a' && c <= 'z'; }
		private static final int upperToInt(final char c) { return c - 'A'; }
		private static final int lowerToInt(final char c) { return c - 'a'; }
		private static final int numToInt(final char c) { return c - '0'; }
		private static final int charToInt(final char c) { return isLower(c) ? lowerToInt(c) : isUpper(c) ? upperToInt(c) : numToInt(c); }
		private static final int alphabetToInt(final char c) { return isLower(c) ? lowerToInt(c) : isUpper(c) ? upperToInt(c) + 26 : 52; }
		private static final char intToUpper(final int x) { return (char)(x + 'A'); }
		private static final char intToLower(final int x) { return (char)(x + 'a'); }
		private static final char intToNum(final int x) { return (char)(x + '0'); }
		private static final int[] charToInt(final char[] a) { final int toint[] = new int[a.length]; for(int i = 0; i < a.length; i ++) toint[i] = charToInt(a[i]); return toint; }
		private static final int[][] charToInt(final char[][] a) { final int toint[][] = new int[a.length][]; for(int i = 0; i < a.length; i ++) toint[i] = charToInt(a[i]); return toint; }

		private static final long[] div(final long x) {
			nonNegativeCheck(x);
			final List<Long> divList = new ArrayList<>();
			for(long i = 1; i * i <= x; i ++) if(x % i == 0) { divList.add(i); if(i * i != x) divList.add(x / i); }
			final long div[] = new long[divList.size()];
			for(int i = 0; i < divList.size(); i ++) div[i] = divList.get(i);
			Arrays.sort(div);
			return div;
		}

		private static final PairLL[] factor(long x) {
			nonNegativeCheck(x);
			final List<PairLL> factorList = new ArrayList<>();
			for(long i = 2; i * i <= x; i ++) {
				if(x % i == 0) {
					long cnt = 0;
					while(x % i == 0) { x /= i; cnt ++; }
					factorList.add(new PairLL(i, cnt));
				}
			}
			if(x > 1) factorList.add(new PairLL(x, 1));
			final PairLL factor[] = new PairLL[factorList.size()];
			for(int i = 0; i < factorList.size(); i ++) factor[i] = factorList.get(i);
			Arrays.sort(factor);
			return factor;
		}

		private static final boolean isPrime(final long x) { if(x <= 1) return false; for(long i = 2; i * i <= x; i ++) if(x % i == 0) return false; return true; }
		private static final boolean[] prime(final int n) {
			nonNegativeCheck(n);
			final boolean prime[] = new boolean[n];
			fill(prime, true);
			if(n > 0) prime[0] = false;
			if(n > 1) prime[1] = false;
			for(int i = 2; i < n; i ++) if(prime[i]) for(int j = 2; i * j < n; j ++) prime[i * j] = false;
			return prime;
		}

		private static final PairIL[] countElements(final int[] a, final boolean sort) {
			final int len = a.length;
			final int array[] = new int[len];
			for(int i = 0; i < len; i ++) array[i] = a[i];
			if(sort) Arrays.sort(array);
			final List<PairIL> cntsList = new ArrayList<>();
			long tmp = 1;
			for(int i = 1; i <= len; i ++) {
				if(i == len || array[i] != array[i - 1]) {
					cntsList.add(new PairIL(array[i - 1], tmp));
					tmp = 1;
				}else tmp ++;
			}
			final PairIL cnts[] = new PairIL[cntsList.size()];
			for(int i = 0; i < cntsList.size(); i ++) cnts[i] = cntsList.get(i);
			return cnts;
		}
		private static final PairLL[] countElements(final long[] a, final boolean sort) {
			final int len = a.length;
			final long array[] = new long[len];
			for(int i = 0; i < len; i ++) array[i] = a[i];
			if(sort) Arrays.sort(array);
			final List<PairLL> cntsList = new ArrayList<>();
			long tmp = 1;
			for(int i = 1; i <= len; i ++) {
				if(i == len || array[i] != array[i - 1]) {
					cntsList.add(new PairLL(array[i - 1], tmp));
					tmp = 1;
				}else tmp ++;
			}
			final PairLL cnts[] = new PairLL[cntsList.size()];
			for(int i = 0; i < cntsList.size(); i ++) cnts[i] = cntsList.get(i);
			return cnts;
		}
		private static final PairIL[] countElements(final String s, final boolean sort) {
			final int len = s.length();
			final char array[] = s.toCharArray();
			if(sort) Arrays.sort(array);
			final List<PairIL> cntsList = new ArrayList<>();
			long tmp = 1;
			for(int i = 1; i <= len; i ++) {
				if(i == len || array[i] != array[i - 1]) {
					cntsList.add(new PairIL((int)array[i - 1], tmp));
					tmp = 1;
				}else tmp ++;
			}
			final PairIL cnts[] = new PairIL[cntsList.size()];
			for(int i = 0; i < cntsList.size(); i ++) cnts[i] = cntsList.get(i);
			return cnts;
		}

		private static final long triangular(final long n) { return n * (n + 1) / 2; }
		private static final long arctriangularfloor(final long m) {
			long n = (floor(sqrt(m * 8 + 1)) - 1) / 2 + 1;
			while(triangular(n) > m) n --;
			return n;
		}
		private static final long arctriangularceil(final long m) {
			long n = max(0, (ceil(sqrt(m * 8 + 1)) + 1) / 2 - 1);
			while(triangular(n) < m) n ++;
			return n;
		}

		private static final int[] baseConvert(long x, final int n, final int len) {
			nonNegativeCheck(x);
			nonNegativeCheck(n);
			nonNegativeCheck(len);
			final int digit[] = new int[len];
			int i = 0;
			while(x > 0 && i < len) { digit[i ++] = (int)(x % n); x /= n; }
			return digit;
		}
		private static final int[] baseConvert(final long x, final int n) {
			nonNegativeCheck(x);
			nonNegativeCheck(n);
			long tmp = x;
			int len = 0;
			while(tmp > 0) { tmp /= n; len ++; }
			return baseConvert(x, n, len);
		}
		private static final int[] baseConvert(int x, final int n, final int len) {
			nonNegativeCheck(x);
			nonNegativeCheck(n);
			nonNegativeCheck(len);
			final int digit[] = new int[len];
			int i = 0;
			while(x > 0 && i < len) { digit[i ++] = (int)(x % n); x /= n; }
			return digit;
		}
		private static final int[] baseConvert(final int x, final int n) {
			nonNegativeCheck(x);
			nonNegativeCheck(n);
			int tmp = x;
			int len = 0;
			while(tmp > 0) { tmp /= n; len ++; }
			return baseConvert(x, n, len);
		}
		private static final long[] baseConvert(long x, final long n, final int len) {
			nonNegativeCheck(x);
			nonNegativeCheck(n);
			nonNegativeCheck(len);
			final long digit[] = new long[len];
			int i = 0;
			while(x > 0 && i < len) { digit[i ++] = x % n; x /= n; }
			return digit;
		}
		private static final long[] baseConvert(final long x, final long n) {
			nonNegativeCheck(x);
			nonNegativeCheck(n);
			long tmp = x;
			int len = 0;
			while(tmp > 0) { tmp /= n; len ++; }
			return baseConvert(x, n, len);
		}

		private static final int numDigits(final long x) { nonNegativeCheck(x); return Long.toString(x).length(); }
		private static final long bitFlag(final int i) { nonNegativeCheck(i); return 1L << i; }
		private static final boolean isFlagged(final long x, final int i) { nonNegativeCheck(x); nonNegativeCheck(i); return (x >> i & 1) != 0; }

		private static final long countString(final String s, final String t) { return (s.length() - s.replace(t, "").length()) / t.length(); }
		private static final long countStringAll(final String s, final String t) { return s.length() - s.replaceAll(t, "").length(); }


		private static final String reverse(final String s) { return (new StringBuilder(s)).reverse().toString(); }
		private static final char[] reverse(final char[] a) { for(int i = 0; i < a.length / 2; i ++) swap(a, i, a.length - i - 1); return a; }
		private static final boolean[] reverse(final boolean[] a) { for(int i = 0; i < a.length / 2; i ++) swap(a, i, a.length - i - 1); return a; }
		private static final int[] reverse(final int[] a) { for(int i = 0; i < a.length / 2; i ++) swap(a, i, a.length - i - 1); return a; }
		private static final long[] reverse(final long[] a) { for(int i = 0; i < a.length / 2; i ++) swap(a, i, a.length - i - 1); return a; }
		private static final double[] reverse(final double[] a) { for(int i = 0; i < a.length / 2; i ++) swap(a, i, a.length - i - 1); return a; }
		private static final String[] reverse(final String[] a) { for(int i = 0; i < a.length / 2; i ++) swap(a, i, a.length - i - 1); return a; }
		private static final Object[] reverse(final Object[] a) { for(int i = 0; i < a.length / 2; i ++) swap(a, i, a.length - i - 1); return a; }
		private static final void fill(final char[] a, final char x) { Arrays.fill(a, x); }
		private static final void fill(final boolean[] a, final boolean x) { Arrays.fill(a, x); }
		private static final void fill(final int[] a, final int x) { Arrays.fill(a, x); }
		private static final void fill(final long[] a, final long x) { Arrays.fill(a, x); }
		private static final void fill(final double[] a, final double x) { Arrays.fill(a, x); }
		private static final void fill(final char[][] a, final char x) { for(char[] ele : a) fill(ele, x); }
		private static final void fill(final boolean[][] a, final boolean x) { for(boolean[] ele : a) fill(ele, x); }
		private static final void fill(final int[][] a, final int x) { for(int[] ele : a) fill(ele, x); }
		private static final void fill(final long[][] a, final long x) { for(long[] ele : a) fill(ele, x); }
		private static final void fill(final double[][] a, final double x) { for(double[] ele : a) fill(ele, x); }
		private static final void fill(final char[][][] a, final char x) { for(char[][] ele : a) fill(ele, x); }
		private static final void fill(final boolean[][][] a, final boolean x) { for(boolean[][] ele : a) fill(ele, x); }
		private static final void fill(final int[][][] a, final int x) { for(int[][] ele : a) fill(ele, x); }
		private static final void fill(final long[][][] a, final long x) { for(long[][] ele : a) fill(ele, x); }
		private static final void fill(final double[][][] a, final double x) { for(double[][] ele : a) fill(ele, x); }

		private static final char[] resize(final char[] a, final int m, final int x) {
			nonNegativeCheck(m);
			final char resized[] = new char[m];
			if(x <= - a.length || x >= m) return resized;
			if(x >= 0) System.arraycopy(a, 0, resized, x, min(a.length, m - x));
			else System.arraycopy(a, - x, resized, 0, min(a.length + x, m));
			return resized;
		}
		private static final boolean[] resize(final boolean[] a, final int m, final int x) {
			nonNegativeCheck(m);
			final boolean resized[] = new boolean[m];
			if(x <= - a.length || x >= m) return resized;
			if(x >= 0) System.arraycopy(a, 0, resized, x, min(a.length, m - x));
			else System.arraycopy(a, - x, resized, 0, min(a.length + x, m));
			return resized;
		}
		private static final int[] resize(final int[] a, final int m, final int x) {
			nonNegativeCheck(m);
			final int resized[] = new int[m];
			if(x <= - a.length || x >= m) return resized;
			if(x >= 0) System.arraycopy(a, 0, resized, x, min(a.length, m - x));
			else System.arraycopy(a, - x, resized, 0, min(a.length + x, m));
			return resized;
		}
		private static final long[] resize(final long[] a, final int m, final int x) {
			nonNegativeCheck(m);
			final long resized[] = new long[m];
			if(x <= - a.length || x >= m) return resized;
			if(x >= 0) System.arraycopy(a, 0, resized, x, min(a.length, m - x));
			else System.arraycopy(a, - x, resized, 0, min(a.length + x, m));
			return resized;
		}
		private static final double[] resize(final double[] a, final int m, final int x) {
			nonNegativeCheck(m);
			final double resized[] = new double[m];
			if(x <= - a.length || x >= m) return resized;
			if(x >= 0) System.arraycopy(a, 0, resized, x, min(a.length, m - x));
			else System.arraycopy(a, - x, resized, 0, min(a.length + x, m));
			return resized;
		}
		private static final String[] resize(final String[] a, final int m, final int x) {
			nonNegativeCheck(m);
			final String resized[] = new String[m];
			if(x <= - a.length || x >= m) return resized;
			if(x >= 0) System.arraycopy(a, 0, resized, x, min(a.length, m - x));
			else System.arraycopy(a, - x, resized, 0, min(a.length + x, m));
			return resized;
		}
		private static final Object[] resize(final Object[] a, final int m, final int x) {
			nonNegativeCheck(m);
			final Object resized[] = new Object[m];
			if(x <= - a.length || x >= m) return resized;
			if(x >= 0) System.arraycopy(a, 0, resized, x, min(a.length, m - x));
			else System.arraycopy(a, - x, resized, 0, min(a.length + x, m));
			return resized;
		}

		private static final int[] toIntArray(final List<Integer> list) { final int a[] = new int[list.size()]; int idx = 0; for(int ele : list) a[idx ++] = ele; return a; }
		private static final long[] toLongArray(final List<Long> list) { final long a[] = new long[list.size()]; int idx = 0; for(long ele : list) a[idx ++] = ele; return a; }
		private static final double[] toDoubleArray(final List<Double> list) { final double a[] = new double[list.size()]; int idx = 0; for(double ele : list) a[idx ++] = ele; return a; }
		private static final char[] toCharArray(final List<Character> list) { final char a[] = new char[list.size()]; int idx = 0; for(char ele : list) a[idx ++] = ele; return a; }
		private static final boolean[] toBooleanArray(final List<Boolean> list) { final boolean a[] = new boolean[list.size()]; int idx = 0; for(boolean ele : list) a[idx ++] = ele; return a; }
		private static final String[] toStringArray(final List<String> list) { final String a[] = new String[list.size()]; int idx = 0; for(String ele : list) a[idx ++] = ele; return a; }
		private static final <T> void toArray(final List<T> list, final T a[]) { int idx = 0; for(T ele : list) a[idx ++] = ele; }

		private static final void shuffleArray(final int[] a) { for(int i = 0; i < a.length; i ++) swap(a, i, random(i, a.length)); }
		private static final void shuffleArray(final long[] a) { for(int i = 0; i < a.length; i ++) swap(a, i, random(i, a.length)); }
		private static final void shuffleArray(final double[] a) { for(int i = 0; i < a.length; i ++) swap(a, i, random(i, a.length)); }
		private static final int[] randomi(final int n, final int max) { nonNegativeCheck(n); final int a[] = new int[n]; for(int i = 0; i < n; i ++) a[i] = random(max); return a; }
		private static final long[] randoml(final int n, final long max) { nonNegativeCheck(n); final long a[] = new long[n]; for(int i = 0; i < n; i ++) a[i] = random(max); return a; }
		private static final double[] randomd(final int n, final double max) { nonNegativeCheck(n); final double a[] = new double[n]; for(int i = 0; i < n; i ++) a[i] = random(max); return a; }
		private static final int[] randomi(final int n, final int min, final int max) { nonNegativeCheck(n); final int a[] = new int[n]; for(int i = 0; i < n; i ++) a[i] = random(min, max); return a; }
		private static final long[] randoml(final int n, final long min, final long max) { nonNegativeCheck(n); final long a[] = new long[n]; for(int i = 0; i < n; i ++) a[i] = random(min, max); return a; }
		private static final double[] randomd(final int n, final double min, final double max) { nonNegativeCheck(n); final double a[] = new double[n]; for(int i = 0; i < n; i ++) a[i] = random(min, max); return a; }

		private static final void swap(final char[] a, final int i, final int j) { rangeCheck(i, a.length); rangeCheck(j, a.length); char tmp = a[i]; a[i] = a[j]; a[j] = tmp; }
		private static final void swap(final boolean[] a, final int i, final int j) { rangeCheck(i, a.length); rangeCheck(j, a.length); boolean tmp = a[i]; a[i] = a[j]; a[j] = tmp; }
		private static final void swap(final int[] a, final int i, final int j) { rangeCheck(i, a.length); rangeCheck(j, a.length); int tmp = a[i]; a[i] = a[j]; a[j] = tmp; }
		private static final void swap(final long[] a, final int i, final int j) { rangeCheck(i, a.length); rangeCheck(j, a.length); long tmp = a[i]; a[i] = a[j]; a[j] = tmp; }
		private static final void swap(final double[] a, final int i, final int j) { rangeCheck(i, a.length); rangeCheck(j, a.length); double tmp = a[i]; a[i] = a[j]; a[j] = tmp; }
		private static final void swap(final String[] a, final int i, final int j) { rangeCheck(i, a.length); rangeCheck(j, a.length); String tmp = a[i]; a[i] = a[j]; a[j] = tmp; }
		private static final void swap(final Object[] a, final int i, final int j) { rangeCheck(i, a.length); rangeCheck(j, a.length); Object tmp = a[i]; a[i] = a[j]; a[j] = tmp; }

		private static final char[][] rotate(final char[][] a) {
			final char[][] ans = new char[a[0].length][a.length];
			for(int i = 0; i < a.length; i ++) for(int j = 0; j < a[i].length; j ++) ans[j][a.length - i - 1] = a[i][j];
			return ans;
		}
		private static final boolean[][] rotate(final boolean[][] a) {
			final boolean[][] ans = new boolean[a[0].length][a.length];
			for(int i = 0; i < a.length; i ++) for(int j = 0; j < a[i].length; j ++) ans[j][a.length - i - 1] = a[i][j];
			return ans;
		}
		private static final int[][] rotate(final int[][] a) {
			final int[][] ans = new int[a[0].length][a.length];
			for(int i = 0; i < a.length; i ++) for(int j = 0; j < a[i].length; j ++) ans[j][a.length - i - 1] = a[i][j];
			return ans;
		}
		private static final long[][] rotate(final long[][] a) {
			final long[][] ans = new long[a[0].length][a.length];
			for(int i = 0; i < a.length; i ++) for(int j = 0; j < a[i].length; j ++) ans[j][a.length - i - 1] = a[i][j];
			return ans;
		}
		private static final double[][] rotate(final double[][] a) {
			final double[][] ans = new double[a[0].length][a.length];
			for(int i = 0; i < a.length; i ++) for(int j = 0; j < a[i].length; j ++) ans[j][a.length - i - 1] = a[i][j];
			return ans;
		}
		private static final Object[][] rotate(final Object[][] a) {
			final Object[][] ans = new Object[a[0].length][a.length];
			for(int i = 0; i < a.length; i ++) for(int j = 0; j < a[i].length; j ++) ans[j][a.length - i - 1] = a[i][j];
			return ans;
		}

		private static final int[] compress(final int[] a) {
			final int n = a.length;
			final Set<Integer> ts = new TreeSet<>();
			for(int i = 0; i < n; i ++) ts.add(a[i]);
			final int compressed[] = new int[ts.size()];
			int j = 0;
			for(int x : ts) compressed[j ++] = x;
			for(int i = 0; i < n; i ++) a[i] = lowerBound(compressed, a[i]);
			return compressed;
		}
		private static final long[] compress(final long[] a) {
			final int n = a.length;
			final Set<Long> ts = new TreeSet<>();
			for(int i = 0; i < n; i ++) ts.add(a[i]);
			final long compressed[] = new long[ts.size()];
			int j = 0;
			for(long x : ts) compressed[j ++] = x;
			for(int i = 0; i < n; i ++) a[i] = lowerBound(compressed, a[i]);
			return compressed;
		}
		private static final double[] compress(final double[] a) {
			final int n = a.length;
			final Set<Double> ts = new TreeSet<>();
			for(int i = 0; i < n; i ++) ts.add(a[i]);
			final double compressed[] = new double[ts.size()];
			int j = 0;
			for(double x : ts) compressed[j ++] = x;
			for(int i = 0; i < n; i ++) a[i] = lowerBound(compressed, a[i]);
			return compressed;
		}

		// binary search
		private static final int lowerBound(final int[] a, final int key) { return BS(a, key, true, true, true); }
		private static final int lowerBound(final int[] a, final int key, final int ng, final int ok) { return BS(a, key, true, true, true, ng, ok); }
		private static final int upperBound(final int[] a, final int key) { return BS(a, key, true, true, false); }
		private static final int upperBound(final int[] a, final int key, final int ng, final int ok) { return BS(a, key, true, true, false, ng, ok); }
		private static final int cntBS(final int[] a, final int key, final boolean asc, final boolean gt, final boolean eq) { return BS(a, key, asc, gt, eq, true); }
		private static final int cntBS(final int[] a, final int key,
				final boolean asc, final boolean gt, final boolean eq, final int ng, final int ok) { return BS(a, key, asc, gt, eq, true, ng, ok); }
		private static final int BS(final int[] a, final int key,
				final boolean asc, final boolean gt, final boolean eq) { return BS(a, key, asc, gt, eq, false); }
		private static final int BS(final int[] a, final int key,
				final boolean asc, final boolean gt, final boolean eq, final int ng, final int ok) { return BS(a, key, asc, gt, eq, false, ng, ok); }
		private static final int BS(final int[] a, final int key,
				final boolean asc, final boolean gt, final boolean eq, final boolean cnt) { return BS(a, key, asc, gt, eq, cnt, asc ^ gt ? a.length : -1, asc ^ gt ? -1 : a.length); }
		private static final int BS(final int[] a, final int key, final boolean asc, final boolean gt, final boolean eq, final boolean cnt, final int ng, final int ok) {
			int index = binarySearch(a, key, gt, eq, ng, ok);
			return cnt ? (int)abs(ok - index) : index;
		}
		private static final int binarySearch(final int[] a, final int key, final boolean gt, final boolean eq, int ng, int ok) {
			while(abs(ok - ng) > 1) { int mid = (ok + ng) >> 1; if(isOKforBS(a, mid, key, gt, eq)) ok = mid; else ng = mid; }
			return ok;
		}
		private static final boolean isOKforBS(final int[] a, final int index, final int key, final boolean gt, final boolean eq) {
			return (a[index] > key && gt) || (a[index] < key && !gt) || (a[index] == key && eq);
		}
		private static final int lowerBound(final long[] a, final long key) { return BS(a, key, true, true, true); }
		private static final int lowerBound(final long[] a, final long key, final int ng, final int ok) { return BS(a, key, true, true, true, ng, ok); }
		private static final int upperBound(final long[] a, final long key) { return BS(a, key, true, true, false); }
		private static final int upperBound(final long[] a, final long key, final int ng, final int ok) { return BS(a, key, true, true, false, ng, ok); }
		private static final int cntBS(final long[] a, final long key, final boolean asc, final boolean gt, final boolean eq) { return BS(a, key, asc, gt, eq, true); }
		private static final int cntBS(final long[] a, final long key,
				final boolean asc, final boolean gt, final boolean eq, final int ng, final int ok) { return BS(a, key, asc, gt, eq, true, ng, ok); }
		private static final int BS(final long[] a, final long key,
				final boolean asc, final boolean gt, final boolean eq) { return BS(a, key, asc, gt, eq, false); }
		private static final int BS(final long[] a, final long key,
				final boolean asc, final boolean gt, final boolean eq, final int ng, final int ok) { return BS(a, key, asc, gt, eq, false, ng, ok); }
		private static final int BS(final long[] a, final long key,
				final boolean asc, final boolean gt, final boolean eq, final boolean cnt) { return BS(a, key, asc, gt, eq, cnt, asc ^ gt ? a.length : -1, asc ^ gt ? -1 : a.length); }
		private static final int BS(final long[] a, final long key, final boolean asc, final boolean gt, final boolean eq, final boolean cnt, final int ng, final int ok) {
			int index = binarySearch(a, key, gt, eq, ng, ok);
			return cnt ? (int)abs(ok - index) : index;
		}
		private static final int binarySearch(final long[] a, final long key, final boolean gt, final boolean eq, int ng, int ok) {
			while(abs(ok - ng) > 1) { int mid = (ok + ng) >> 1; if(isOKforBS(a, mid, key, gt, eq)) ok = mid; else ng = mid; }
			return ok;
		}
		private static final boolean isOKforBS(final long[] a, final int index, final long key, final boolean gt, final boolean eq) {
			return (a[index] > key && gt) || (a[index] < key && !gt) || (a[index] == key && eq);
		}
		private static final int lowerBound(final double[] a, final double key) { return BS(a, key, true, true, true); }
		private static final int lowerBound(final double[] a, final double key, final int ng, final int ok) { return BS(a, key, true, true, true, ng, ok); }
		private static final int upperBound(final double[] a, final double key) { return BS(a, key, true, true, false); }
		private static final int upperBound(final double[] a, final double key, final int ng, final int ok) { return BS(a, key, true, true, false, ng, ok); }
		private static final int cntBS(final double[] a, final double key, final boolean asc, final boolean gt, final boolean eq) { return BS(a, key, asc, gt, eq, true); }
		private static final int cntBS(final double[] a, final double key,
				final boolean asc, final boolean gt, final boolean eq, final int ng, final int ok) { return BS(a, key, asc, gt, eq, true, ng, ok); }
		private static final int BS(final double[] a, final double key,
				final boolean asc, final boolean gt, final boolean eq) { return BS(a, key, asc, gt, eq, false); }
		private static final int BS(final double[] a, final double key,
				final boolean asc, final boolean gt, final boolean eq, final int ng, final int ok) { return BS(a, key, asc, gt, eq, false, ng, ok); }
		private static final int BS(final double[] a, final double key,
				final boolean asc, final boolean gt, final boolean eq, final boolean cnt) { return BS(a, key, asc, gt, eq, cnt, asc ^ gt ? a.length : -1, asc ^ gt ? -1 : a.length); }
		private static final int BS(final double[] a, final double key, final boolean asc, final boolean gt, final boolean eq, final boolean cnt, final int ng, final int ok) {
			int index = binarySearch(a, key, gt, eq, ng, ok);
			return cnt ? (int)abs(ok - index) : index;
		}
		private static final int binarySearch(final double[] a, final double key, final boolean gt, final boolean eq, int ng, int ok) {
			while(abs(ok - ng) > 1) { int mid = (ok + ng) >> 1; if(isOKforBS(a, mid, key, gt, eq)) ok = mid; else ng = mid; }
			return ok;
		}
		private static final boolean isOKforBS(final double[] a, final int index, final double key, final boolean gt, final boolean eq) {
			return (a[index] > key && gt) || (a[index] < key && !gt) || (a[index] == key && eq);
		}
		private static final <T extends Comparable<? super T>> int lowerBound(final T[] a, final T key) { return BS(a, key, true, true, true); }
		private static final <T extends Comparable<? super T>> int lowerBound(final T[] a, final T key, final int ng, final int ok) { return BS(a, key, true, true, true, ng, ok); }
		private static final <T extends Comparable<? super T>> int upperBound(final T[] a, final T key) { return BS(a, key, true, true, false); }
		private static final <T extends Comparable<? super T>> int upperBound(final T[] a, final T key, final int ng, final int ok) { return BS(a, key, true, true, false, ng, ok); }
		private static final <T extends Comparable<? super T>> int cntBS(final T[] a, final T key, final boolean asc, final boolean gt, final boolean eq) { return BS(a, key, asc, gt, eq, true); }
		private static final <T extends Comparable<? super T>> int cntBS(final T[] a, final T key,
				final boolean asc, final boolean gt, final boolean eq, final int ng, final int ok) { return BS(a, key, asc, gt, eq, true, ng, ok); }
		private static final <T extends Comparable<? super T>> int BS(final T[] a, final T key,
				final boolean asc, final boolean gt, final boolean eq) { return BS(a, key, asc, gt, eq, false); }
		private static final <T extends Comparable<? super T>> int BS(final T[] a, final T key,
				final boolean asc, final boolean gt, final boolean eq, final int ng, final int ok) { return BS(a, key, asc, gt, eq, false, ng, ok); }
		private static final <T extends Comparable<? super T>> int BS(final T[] a, final T key,
				final boolean asc, final boolean gt, final boolean eq, final boolean cnt) { return BS(a, key, asc, gt, eq, cnt, asc ^ gt ? a.length : -1, asc ^ gt ? -1 : a.length); }
		private static final <T extends Comparable<? super T>> int BS(final T[] a, final T key, final boolean asc, final boolean gt, final boolean eq, final boolean cnt, final int ng, final int ok) {
			int index = binarySearch(a, key, gt, eq, ng, ok);
			return cnt ? (int)abs(ok - index) : index;
		}
		private static final <T extends Comparable<? super T>> int binarySearch(final T[] a, final T key, final boolean gt, final boolean eq, int ng, int ok) {
			while(abs(ok - ng) > 1) { int mid = (ok + ng) >> 1; if(isOKforBS(a, mid, key, gt, eq)) ok = mid; else ng = mid; }
			return ok;
		}
		private static final <T extends Comparable<? super T>> boolean isOKforBS(final T[] a, final int index, final T key, final boolean gt, final boolean eq) {
			int compare = a[index].compareTo(key);
			return (compare > 0 && gt) || (compare < 0 && !gt) || (compare == 0 && eq);
		}
		private static final <T> int lowerBound(final T[] a, final T key, final Comparator<? super T> c) { return BS(a, key, true, true, true, c); }
		private static final <T> int lowerBound(final T[] a, final T key, final int ng, final int ok, final Comparator<? super T> c) { return BS(a, key, true, true, true, ng, ok, c); }
		private static final <T> int upperBound(final T[] a, final T key, final Comparator<? super T> c) { return BS(a, key, true, true, false, c); }
		private static final <T> int upperBound(final T[] a, final T key, final int ng, final int ok, final Comparator<? super T> c) { return BS(a, key, true, true, false, ng, ok, c); }
		private static final <T> int cntBS(final T[] a, final T key, final boolean asc, final boolean gt, final boolean eq, final Comparator<? super T> c) { return BS(a, key, asc, gt, eq, true, c); }
		private static final <T> int cntBS(final T[] a, final T key,
				final boolean asc, final boolean gt, final boolean eq, final int ng, final int ok, final Comparator<? super T> c) { return BS(a, key, asc, gt, eq, true, ng, ok, c); }
		private static final <T> int BS(final T[] a, final T key,
				final boolean asc, final boolean gt, final boolean eq, final Comparator<? super T> c) { return BS(a, key, asc, gt, eq, false, c); }
		private static final <T> int BS(final T[] a, final T key,
				final boolean asc, final boolean gt, final boolean eq, final int ng, final int ok, final Comparator<? super T> c) { return BS(a, key, asc, gt, eq, false, ng, ok, c); }
		private static final <T> int BS(final T[] a, final T key,
				final boolean asc, final boolean gt, final boolean eq, final boolean cnt, final Comparator<? super T> c) { return BS(a, key, asc, gt, eq, cnt, asc ^ gt ? a.length : -1, asc ^ gt ? -1 : a.length, c); }
		private static final <T> int BS(final T[] a, final T key, final boolean asc, final boolean gt, final boolean eq, final boolean cnt, final int ng, final int ok, final Comparator<? super T> c) {
			int index = binarySearch(a, key, gt, eq, ng, ok, c);
			return cnt ? (int)abs(ok - index) : index;
		}
		private static final <T> int binarySearch(final T[] a, final T key, final boolean gt, final boolean eq, int ng, int ok, final Comparator<? super T> c) {
			while(abs(ok - ng) > 1) { int mid = (ok + ng) >> 1; if(isOKforBS(a, mid, key, gt, eq, c)) ok = mid; else ng = mid; }
			return ok;
		}
		private static final <T> boolean isOKforBS(final T[] a, final int index, T key, final boolean gt, final boolean eq, final Comparator<? super T> c) {
			int compare = c.compare(a[index], key);
			return (compare > 0 && gt) || (compare < 0 && !gt) || (compare == 0 && eq);
		}
		private static final <T extends Comparable<? super T>> int lowerBound(final List<T> a, final T key) { return BS(a, key, true, true, true); }
		private static final <T extends Comparable<? super T>> int lowerBound(final List<T> a, final T key, final int ng, final int ok) { return BS(a, key, true, true, true, ng, ok); }
		private static final <T extends Comparable<? super T>> int upperBound(final List<T> a, final T key) { return BS(a, key, true, true, false); }
		private static final <T extends Comparable<? super T>> int upperBound(final List<T> a, final T key, final int ng, final int ok) { return BS(a, key, true, true, false, ng, ok); }
		private static final <T extends Comparable<? super T>> int cntBS(final List<T> a, final T key, final boolean asc, final boolean gt, final boolean eq) { return BS(a, key, asc, gt, eq, true); }
		private static final <T extends Comparable<? super T>> int cntBS(final List<T> a, final T key,
				final boolean asc, final boolean gt, final boolean eq, final int ng, final int ok) { return BS(a, key, asc, gt, eq, true, ng, ok); }
		private static final <T extends Comparable<? super T>> int BS(final List<T> a, final T key,
				final boolean asc, final boolean gt, final boolean eq) { return BS(a, key, asc, gt, eq, false); }
		private static final <T extends Comparable<? super T>> int BS(final List<T> a, final T key,
				final boolean asc, final boolean gt, final boolean eq, final int ng, final int ok) { return BS(a, key, asc, gt, eq, false, ng, ok); }
		private static final <T extends Comparable<? super T>> int BS(final List<T> a, final T key,
				final boolean asc, final boolean gt, final boolean eq, final boolean cnt) { return BS(a, key, asc, gt, eq, cnt, asc ^ gt ? a.size() : -1, asc ^ gt ? -1 : a.size()); }
		private static final <T extends Comparable<? super T>> int BS(final List<T> a, final T key, final boolean asc, final boolean gt, final boolean eq, final boolean cnt, final int ng, final int ok) {
			int index = binarySearch(a, key, gt, eq, ng, ok);
			return cnt ? (int)abs(ok - index) : index;
		}
		private static final <T extends Comparable<? super T>> int binarySearch(final List<T> a, final T key, final boolean gt, final boolean eq, int ng, int ok) {
			while(abs(ok - ng) > 1) { int mid = (ok + ng) >> 1; if(isOKforBS(a, mid, key, gt, eq)) ok = mid; else ng = mid; }
			return ok;
		}
		private static final <T extends Comparable<? super T>> boolean isOKforBS(final List<T> a, final int index, T key, final boolean gt, final boolean eq) {
			int compare = a.get(index).compareTo(key);
			return (compare > 0 && gt) || (compare < 0 && !gt) || (compare == 0 && eq);
		}
		private static final <T> int lowerBound(final List<T> a, final T key, final Comparator<? super T> c) { return BS(a, key, true, true, true, c); }
		private static final <T> int lowerBound(final List<T> a, final T key, final int ng, final int ok, final Comparator<? super T> c) { return BS(a, key, true, true, true, ng, ok, c); }
		private static final <T> int upperBound(final List<T> a, final T key, final Comparator<? super T> c) { return BS(a, key, true, true, false, c); }
		private static final <T> int upperBound(final List<T> a, final T key, final int ng, final int ok, final Comparator<? super T> c) { return BS(a, key, true, true, false, ng, ok, c); }
		private static final <T> int cntBS(final List<T> a, final T key, final boolean asc, final boolean gt, final boolean eq, final Comparator<? super T> c) { return BS(a, key, asc, gt, eq, true, c); }
		private static final <T> int cntBS(final List<T> a, final T key,
				final boolean asc, final boolean gt, final boolean eq, final int ng, final int ok, final Comparator<? super T> c) { return BS(a, key, asc, gt, eq, true, ng, ok, c); }
		private static final <T> int BS(final List<T> a, final T key,
				final boolean asc, final boolean gt, final boolean eq, final Comparator<? super T> c) { return BS(a, key, asc, gt, eq, false, c); }
		private static final <T> int BS(final List<T> a, final T key,
				final boolean asc, final boolean gt, final boolean eq, final int ng, final int ok, final Comparator<? super T> c) { return BS(a, key, asc, gt, eq, false, ng, ok, c); }
		private static final <T> int BS(final List<T> a, final T key,
				final boolean asc, final boolean gt, final boolean eq, final boolean cnt, final Comparator<? super T> c) { return BS(a, key, asc, gt, eq, cnt, asc ^ gt ? a.size() : -1, asc ^ gt ? -1 : a.size(), c); }
		private static final <T> int BS(final List<T> a, final T key, final boolean asc, final boolean gt, final boolean eq, final boolean cnt, final int ng, final int ok, final Comparator<? super T> c) {
			int index = binarySearch(a, key, gt, eq, ng, ok, c);
			return cnt ? (int)abs(ok - index) : index;
		}
		private static final <T> int binarySearch(final List<T> a, final T key, final boolean gt, final boolean eq, int ng, int ok, final Comparator<? super T> c) {
			while(abs(ok - ng) > 1) { int mid = (ok + ng) >> 1; if(isOKforBS(a, mid, key, gt, eq, c)) ok = mid; else ng = mid; }
			return ok;
		}
		private static final <T> boolean isOKforBS(final List<T> a, final int index, final T key, final boolean gt, final boolean eq, final Comparator<? super T> c) {
			int compare = c.compare(a.get(index), key);
			return (compare > 0 && gt) || (compare < 0 && !gt) || (compare == 0 && eq);
		}

		private static final PairLL binaryRangeSearch(final long left, final long right, final UnaryOperator<Long> op, final boolean minimize) {
			long ok1 = right, ng1 = left;
			while(abs(ok1 - ng1) > 1) {
				long mid = (ok1 + ng1) >> 1;
				boolean isOK = (op.apply(mid + 1) - op.apply(mid)) * (minimize ? 1 : -1) >= 0;
				if(isOK) ok1 = mid; else ng1 = mid;
			}
			long ok2 = left, ng2 = right;
			while(abs(ok2 - ng2) > 1) {
				long mid = (ok2 + ng2) >> 1;
				boolean isOK = (op.apply(mid - 1) - op.apply(mid)) * (minimize ? 1 : -1) >= 0;
				if(isOK) ok2 = mid; else ng2 = mid;
			}
			return new PairLL(ok1, ok2); //[l, r]
		}

		private static final double ternarySearch(double left, double right, final UnaryOperator<Double> op, final boolean minimize, final int loop) {
			for(int cnt = 0; cnt < loop; cnt ++) {
				double m1 = (left * 2 + right) / 3.0;
				double m2 = (left + right * 2) / 3.0;
				if(op.apply(m1) > op.apply(m2) ^ minimize) right = m2; else left = m1;
			}
			return (left + right) / 2.0;
		}


		// mods
		private static final class Mod107 extends Mod {
			public static final Mod107 md = new Mod107();
			public static final long MOD = 1_000_000_007;
			private Mod107() { super(MOD); }

			@Override
			public final long mod(long x) {
				if(0 <= x && x < MOD) return x;
				if(- MOD <= x && x < 0) return x + MOD;
				return (x %= MOD) < 0 ? x + MOD : x;
			}
			@Override
			public final long mul(long x, long y) {
				if(x >= 0 && x < MOD && y >= 0 && y < MOD) return (x * y) % MOD;
				x = mod(x);
				y = mod(y);
				return (x * y) % MOD;
			}
		}
		private static final class Mod998 extends Mod {
			public static final Mod998 md = new Mod998();
			public static final long MOD = 998_244_353;
			private Mod998() { super(MOD); }

			@Override
			public final long mod(long x) {
				if(0 <= x && x < MOD) return x;
				if(- MOD <= x && x < 0) return x + MOD;
				return (x %= MOD) < 0 ? x + MOD : x;
			}
			@Override
			public final long mul(long x, long y) {
				if(x >= 0 && x < MOD && y >= 0 && y < MOD) return (x * y) % MOD;
				x = mod(x);
				y = mod(y);
				return (x * y) % MOD;
			}
		}
		private static final class Mod754974721 extends Mod {
			public static final Mod754974721 md = new Mod754974721();
			public static final long MOD = 754_974_721;
			private Mod754974721() { super(MOD); }

			@Override
			public final long mod(long x) {
				if(0 <= x && x < MOD) return x;
				if(- MOD <= x && x < 0) return x + MOD;
				return (x %= MOD) < 0 ? x + MOD : x;
			}
			@Override
			public final long mul(long x, long y) {
				if(x >= 0 && x < MOD && y >= 0 && y < MOD) return (x * y) % MOD;
				x = mod(x);
				y = mod(y);
				return (x * y) % MOD;
			}
		}
		private static final class Mod167772161 extends Mod {
			public static final Mod167772161 md = new Mod167772161();
			public static final long MOD = 167_772_161;
			private Mod167772161() { super(MOD); }

			@Override
			public final long mod(long x) {
				if(0 <= x && x < MOD) return x;
				if(- MOD <= x && x < 0) return x + MOD;
				return (x %= MOD) < 0 ? x + MOD : x;
			}
			@Override
			public final long mul(long x, long y) {
				if(x >= 0 && x < MOD && y >= 0 && y < MOD) return (x * y) % MOD;
				x = mod(x);
				y = mod(y);
				return (x * y) % MOD;
			}
		}
		private static final class Mod469762049 extends Mod {
			public static final Mod469762049 md = new Mod469762049();
			public static final long MOD = 469_762_049;
			private Mod469762049() { super(MOD); }

			@Override
			public final long mod(long x) {
				if(0 <= x && x < MOD) return x;
				if(- MOD <= x && x < 0) return x + MOD;
				return (x %= MOD) < 0 ? x + MOD : x;
			}
			@Override
			public final long mul(long x, long y) {
				if(x >= 0 && x < MOD && y >= 0 && y < MOD) return (x * y) % MOD;
				x = mod(x);
				y = mod(y);
				return (x * y) % MOD;
			}
		}
		private static final class ArbitraryMod extends Mod {
			private static final long MASK = 0xffff_ffffl;
			private final long MH;
			private final long ML;
			public ArbitraryMod(long mod) { super(mod); long a = (1l << 32) / MOD; long b = (1l << 32) % MOD; long m = a * a * MOD + 2 * a * b + (b * b) / MOD; MH = m >>> 32; ML = m & MASK; }

			private final long reduce(long x) {
				if(MOD == 1) return 0;
				if(x < 0) return (x = reduce(- x)) == 0 ? 0 : MOD - x;
				long z = (x & MASK) * ML;
				z = (x & MASK) * MH + (x >>> 32) * ML + (z >>> 32);
				z = (x >>> 32) * MH + (z >>> 32);
				x -= z * MOD;
				return x < MOD ? x : x - MOD;
			}
			@Override
			public long mod(long x) {
				if(0 <= x && x < MOD) return x;
				if(- MOD <= x && x < 0) return x + MOD;
				return reduce(x);
			}
			@Override
			public long mul(long x, long y) {
				if(x >= 0 && x < MOD && y >= 0 && y < MOD) return reduce(x * y);
				x = mod(x);
				y = mod(y);
				return reduce(x * y);
			}
		}

		private abstract static class Mod {
			public final long MOD;
			public Mod(long mod) { MOD = mod; }

			public abstract long mod(long x);
			public final long[] mod(final long[] a) { for(int i = 0; i < a.length; i ++) a[i] = mod(a[i]); return a; }
			public final long[][] mod(final long[][] a) { for(int i = 0; i < a.length; i ++) mod(a[i]); return a; }
			public final long[][][] mod(final long[][][] a) { for(int i = 0; i < a.length; i ++) mod(a[i]); return a; }

			public long add(long x, final long y) { return (x += y) >= MOD * 2 || x < 0 ? mod(x) : x >= MOD ? x - MOD : x; }
			public final long sum(final long... x) { long sum = 0; for(long ele : x) sum = add(sum, ele); return sum; }
			public long sub(long x, final long y) { return (x -= y) < - MOD || x >= MOD ? mod(x) : x < 0 ? x + MOD : x; }
			public final long pow(long x, long y) {
				nonNegativeCheck(y);
				x = mod(x);
				long ans = 1;
				for(; y > 0; y >>= 1) {
					if((y & 1) != 0) ans = mul(ans, x);
					x = mul(x, x);
				}
				return ans;
			}
			public abstract long mul(long x, long y);
			public final long mul(final long... x) { long ans = 1; for(long ele : x) ans = mul(ans, ele); return ans; }
			public final long div(final long x, final long y) { return mul(x, inv(y)); }

			public final long[] pows(long x, final int n) {
				x = mod(x);
				long pow[] = new long[n + 1];
				pow[0] = 1;
				for(int i = 0; i < n; i ++) pow[i + 1] = mul(pow[i], x);
				return pow;
			}
			public final long fact(final int n) {
				nonNegativeCheck(n);
				prepareFact();
				if(n < MAX_FACT1) return fact[n];
				else {
					long ans = fact[MAX_FACT1 - 1];
					for(int i = MAX_FACT1; i <= n; i ++) ans = mul(ans, i);
					return ans;
				}
			}
			public final long invFact(final int n) {
				nonNegativeCheck(n);
				prepareFact();
				if(n < MAX_FACT1) return invFact[n];
				else return inv(fact(n));
			}

			private static final int MAX_INV_SIZE = 100_100;
			public final Map<Long, Long> invMap = new HashMap<>();
			public final long inv(long x) {
				x = mod(x);
				if(invMap.containsKey(x)) return invMap.get(x);
				if(invMap.size() >= MAX_INV_SIZE) return calInv(x);
				invMap.put(x, calInv(x));
				return invMap.get(x);
			}
			private final long calInv(final long x) { // O(logM)
				PairLL s = new PairLL(MOD, 0);
				PairLL t = new PairLL(mod(x), 1);
				while(t.a > 0) {
					long tmp = s.a / t.a;
					PairLL u = new PairLL(s.a - t.a * tmp, s.b - t.b * tmp);
					s = t;
					t = u;
				}
				if(s.b < 0) s.b += MOD / s.a;
				return s.b;
			}
			public final long[] invs(final int n) { // O(N)
				positiveCheck(n);
				long inv[] = new long[n + 1];
				inv[1] = 1;
				for(int i = 2; i <= n; i ++) inv[i] = mul(inv[(int)(MOD % i)], (MOD - MOD / i));
				return inv;
			}

			private long g;
			public final long primitiveRoot() { // O(1) or O(M^(1/2))
				if(MOD == 2) return 1;
				if(MOD == 167772161) return 3;
				if(MOD == 469762049) return 3;
				if(MOD == 754974721) return 11;
				if(MOD == 998244353) return 3;
				if(g != 0) return g;

				PairLL factor[] = factor(MOD - 1);
				outer: for(g = 2; ; g ++) {
					for(PairLL p : factor) if(pow(g, (MOD - 1) / p.a) == 1) continue outer;
					return g;
				}
			}

			private static final int MAX_FACT1 = 5_000_100;
			private static final int MAX_FACT2 = 500_100;
			private static final int MAX_FACT_MAP_SIZE = 100;
			private long fact[];
			private long invFact[];
			private boolean isFactPrepared = false;
			private final Map<Long, long[]> factMap = new HashMap<>();
			private final void prepareFact() {
				if(isFactPrepared) return;
				fact = new long[MAX_FACT1];
				invFact = new long[MAX_FACT1];
				fill(fact, 0);
				fill(invFact, 0);
				fact[0] = 1;
				int maxIndex = min(MAX_FACT1, (int)MOD);
				for(int i = 1; i < maxIndex; i ++) fact[i] = mul(fact[i - 1], i);
				invFact[maxIndex - 1] = inv(fact[maxIndex - 1]);
				for(int i = maxIndex - 1; i > 0; i --) invFact[i - 1] = mul(invFact[i], i);

				isFactPrepared = true;
			}

			public final long P(final long n, final long r) {
				if(!isFactPrepared) prepareFact();
				if(n < 0 || r < 0 || n < r) return 0;
				if(n < MAX_FACT1 && n < MOD) return mul(fact[(int)n], invFact[(int)(n - r)]);
				if(!factMap.containsKey(n)) {
					long largeFact[] = new long[MAX_FACT2];
					factMap.put(n, largeFact);
					fill(largeFact, -1);
					largeFact[0] = 1;
				}
				long largeFact[] = factMap.get(n);
				if(r >= MAX_FACT2) {
					long ans = 1;
					for(long i = n - r + 1; i <= n; i ++) ans = mul(ans, i);
					return ans;
				}else {
					int i = (int)r;
					while(largeFact[i] < 0) i --;
					for(; i < r; i ++) largeFact[i + 1] = mul(largeFact[i], n - i);
					if(factMap.size() > MAX_FACT_MAP_SIZE) factMap.remove(n);
					return largeFact[(int)r];
				}
			}
			public final long C(final long n, long r) {
				if(!isFactPrepared) prepareFact();
				if(n < 0) return mod(C(- n + r - 1, - n - 1) * ((r & 1) == 0 ? 1 : -1));
				if(r < 0 || n < r) return 0;
				r = min(r, n - r);
				if(n < MOD) return mul(P(n, r), r < MAX_FACT1 ? invFact[(int)r] : inv(fact((int)r)));

				long digitN[] = baseConvert(n, MOD);
				long digitR[] = baseConvert(r, MOD);
				final int len = digitN.length;
				digitR = resize(digitR, len, 0);
				long ans = 1;
				for(int i = 0; i < len; i ++) ans = mul(ans, C(digitN[i], digitR[i]));
				return ans;
			}
			public final long H(final long n, final long r) { return C(n - 1 + r, r); }

			public final long sqrt(long x) {
				x = mod(x);
				long p = (MOD - 1) >> 1;
				if(pow(x, p) != 1) return -1;
				long q = MOD - 1;
				int m = 1;
				while(((q >>= 1) & 1) == 0) m ++;
				long z = 1;
				while(pow(z, p) == 1) z = random(1, MOD);
				long c = pow(z, q);
				long t = pow(x, q);
				long r = pow(x, (q + 1) >> 1);
				if(t == 0) return 0;
				m -= 2;
				while(t != 1) {
					long pows[] = new long[m + 1];
					pows[0] = t;
					for(int i = 0; i < m; i ++) pows[i + 1] = mul(pows[i], pows[i]);
					while(pows[m --] == 1) c = mul(c, c);
					r = mul(r, c);
					c = mul(c, c);
					t = mul(t, c);
				}
				return r;
			}
		}
		private static final long mod(long x, final long mod) {
			if(0 <= x && x < mod) return x;
			if(- mod <= x && x < 0) return x + mod;
			return (x %= mod) < 0 ? x + mod : x;
		}
		private static final long pow(long x, long y, final long mod) {
			nonNegativeCheck(y);
			x = mod(x, mod);
			long ans = 1;
			for(; y > 0; y >>= 1) {
				if((y & 1) != 0) ans = mod(ans * x, mod);
				x = mod(x * x, mod);
			}
			return ans;
		}

		// grid
		private static class Grids {
			public final int h, w;
			public final Grid[][] gs;
			public final Grid[] gi;
			public Grids(final int h, final int w) {
				nonNegativeCheck(h);
				nonNegativeCheck(w);
				this.h = h;
				this.w = w;
				gs = new Grid[h][w];
				gi = new Grid[h * w];
				for(int i = 0; i < h; i ++) {
					for(int j = 0; j < w; j ++) {
						gs[i][j] = new Grid(i, j, h, w);
						gi[gs[i][j].i] = gs[i][j];
					}
				}
			}

			public final void init(final boolean[][] b) { for(int i = 0; i < h; i ++) for(int j = 0; j < w; j ++) gs[i][j].b = b[i][j]; }
			public final void init(final long[][] val) { for(int i = 0; i < h; i ++) for(int j = 0; j < w; j ++) gs[i][j].val = val[i][j]; }

			public final Grid get(final int x, final int y) { return isValid(x, y, h, w) ? gs[x][y] : null; }
			public final Grid get(final int i) { return get(i / w, i % w); }

			public static final int dx[] = {0, -1, 1, 0, 0, -1, 1, -1, 1};
			public static final int dy[] = {0, 0, 0, -1, 1, -1, -1, 1, 1};
			public final Grid next(final int x, final int y, final int i) { return next(gs[x][y], i); }
			public final Grid next(final Grid g, final int i) { return isValid(g.x + dx[i], g.y + dy[i], g.h, g.w) ? gs[g.x + dx[i]][g.y + dy[i]] : null; }
		}
		private static class Grid implements Comparable<Grid> {
			public int x, y, h, w, i; public boolean b; public long val;

			public Grid() {  }
			public Grid(final int x, final int y, final int h, final int w) { init(x, y, h, w, false, 0); }
			public Grid(final int x, final int y, final int h, final int w, final boolean b) { init(x, y, h, w, b, 0); }
			public Grid(final int x, final int y, final int h, final int w, final long val) { init(x, y, h, w, false, val); }
			public Grid(final int x, final int y, final int h, final int w, final boolean b, final long val) { init(x, y, h, w, b, val); }

			public final void init(final int x, final int y, final int h, final int w, final boolean b, final long val) { this.x = x; this.y = y; this.h = h; this.w = w; this.b = b; this.val = val; this.i = x * w + y; }

			@Override public final String toString() { return "("+x+", "+y+")"+" "+booleanToChar(b)+" "+val; }
			@Override public final int hashCode() { return Objects.hash(x, y, h, w, b, val); }
			@Override
			public final boolean equals(final Object obj) {
				if(this == obj) return true;
				if(obj == null) return false;
				if(this.getClass() != obj.getClass()) return false;
				Grid that = (Grid) obj;
				if(this.x != that.x) return false;
				if(this.y != that.y) return false;
				if(this.h != that.h) return false;
				if(this.w != that.w) return false;
				if(this.b != that.b) return false;
				if(this.val != that.val) return false;
				return true;
			}
			@Override
			public final int compareTo(final Grid that) {
				int c = Long.compare(this.val, that.val);
				if(c == 0) c = Integer.compare(this.x, that.x);
				if(c == 0) c = Integer.compare(this.y, that.y);
				return c;
			}
		}

		private static final boolean isValid(final int x, final int y, final int h, final int w) { return x >= 0 && x < h && y >= 0 && y < w; }
		private static final boolean isValid(final Grid g) { return isValid(g.x, g.y, g.h, g.w); }

		// graph
		private static class Graph {
			public int numNode, numEdge;
			public boolean directed;
			public List<Edge> edges = new ArrayList<>();
			public Node nodes[];
			public Node reversedNodes[];

			public Graph(final int numNode, final int numEdge, final boolean directed) {
				nonNegativeCheck(numNode);
				this.numNode = numNode;
				this.numEdge = numEdge;
				this.directed = directed;
				nodes = new Node[numNode];
				reversedNodes = new Node[numNode];
				for(int i = 0; i < numNode; i ++) {
					nodes[i] = new Node(i);
					reversedNodes[i] = new Node(i);
				}
			}

			public void init(final List<Edge> edges) {
				this.edges = edges;
				for(Edge e : edges) add(e);
			}

			public void add(final int source, final int target, final long cost) { add(new Edge(source, target, cost)); }
			public void add(final Edge e) {
				rangeCheck(e.source, numNode);
				rangeCheck(e.target, numNode);
				edges.add(e);
				nodes[e.source].add(e.target, e.cost);
				if(directed) reversedNodes[e.target].add(e.source, e.cost);
				else nodes[e.target].add(e.source, e.cost);
				numEdge = edges.size();
			}

			public void clearNodes() { edges.clear(); numEdge = 0; for(Node n : nodes) n.clear(); for(Node n : reversedNodes) n.clear(); }
		}

		private static class Node extends ArrayList<Edge> {
			public final int id;

			public Node(final int id) { this.id = id; }
			public void add(final int target, final long cost) { add(new Edge(id, target, cost)); }
		}

		private static class Edge implements Comparable<Edge> {
			public int source, target; public long cost;
			public Edge(final int source, final int target, final long cost) { this.source = source; this.target = target; this.cost = cost; }

			@Override public final String toString() { return source+" - "+cost+" -> "+target; }
			@Override public final int hashCode() { return Objects.hash(source, target); }
			@Override
			public final boolean equals(final Object obj) {
				if(this == obj) return true;
				if(obj == null) return false;
				if(this.getClass() != obj.getClass()) return false;
				Edge that = (Edge) obj;
				if(this.source != that.source) return false;
				if(this.target != that.target) return false;
				return true;
			}
			@Override
			public final int compareTo(final Edge that) {
				int c = Long.compare(this.cost, that.cost);
				if(c == 0) c = Integer.compare(this.source, that.source);
				if(c == 0) c = Integer.compare(this.target, that.target);
				return c;
			}
		}

		// Pair
		private static class Pair<T extends Comparable<? super T>, U extends Comparable<? super U>> implements Comparable<Pair<T, U>> {
			public T a; public U b;
			public Pair() { }
			public Pair(final T a, final U b) { this.a = a; this.b = b; }

			@Override public final String toString() { return "("+a.toString()+", "+b.toString()+")"; }
			@Override public final int hashCode() { return Objects.hash(a, b); }
			@Override
			public final boolean equals(final Object obj) {
				if(this == obj) return true;
				if(obj == null) return false;
				if(this.getClass() != obj.getClass()) return false;
				Pair that = (Pair) obj;
				if(this.a.getClass() != that.a.getClass()) return false;
				if(this.b.getClass() != that.b.getClass()) return false;
				if(!this.a.equals(that.a)) return false;
				if(!this.b.equals(that.b)) return false;
				return true;
			}
			@Override public final int compareTo(final Pair<T, U> that) { int c = (this.a).compareTo(that.a); if(c == 0) c = (this.b).compareTo(that.b); return c; }
		}
		private static final PairII npii() { return new PairII(ni(), ni()); }
		private static final PairII[] npii(final int n) { final PairII a[] = new PairII[n]; for(int i = 0; i < n; i ++) a[i] = npii(); return a; }
		private static final PairII[][] npii(final int n, final int m) { final PairII a[][] = new PairII[n][m]; for(int i = 0; i < n; i ++) a[i] = npii(m); return a; }
		private static class PairII implements Comparable<PairII> {
			public int a; public int b;
			public PairII() { }
			public PairII(final int a, final int b) { this.a = a; this.b = b; }
			@Override public final String toString() { return "("+a+", "+b+")"; }
			@Override public final int hashCode() { return Objects.hash(a, b); }
			@Override
			public boolean equals(final Object obj) {
				if(this == obj) return true;
				if(obj == null) return false;
				if(this.getClass() != obj.getClass()) return false;
				PairII that = (PairII) obj;
				if(this.a != that.a || this.b != that.b) return false;
				return true;
			}
			@Override public final int compareTo(final PairII that) { int c = Integer.compare(this.a, that.a); if(c == 0) c = Integer.compare(this.b, that.b); return c; }
		}
		private static final PairIL npil() { return new PairIL(ni(), nl()); }
		private static final PairIL[] npil(final int n) { final PairIL a[] = new PairIL[n]; for(int i = 0; i < n; i ++) a[i] = npil(); return a; }
		private static final PairIL[][] npil(final int n, final int m) { final PairIL a[][] = new PairIL[n][m]; for(int i = 0; i < n; i ++) a[i] = npil(m); return a; }
		private static class PairIL implements Comparable<PairIL> {
			public int a; public long b;
			public PairIL() { }
			public PairIL(final int a, final long b) { this.a = a; this.b = b; }
			@Override public final String toString() { return "("+a+", "+b+")"; }
			@Override public final int hashCode() { return Objects.hash(a, b); }
			@Override
			public boolean equals(final Object obj) {
				if(this == obj) return true;
				if(obj == null) return false;
				if(this.getClass() != obj.getClass()) return false;
				PairIL that = (PairIL) obj;
				if(this.a != that.a || this.b != that.b) return false;
				return true;
			}
			@Override public final int compareTo(final PairIL that) { int c = Integer.compare(this.a, that.a); if(c == 0) c = Long.compare(this.b, that.b); return c; }
		}
		private static final PairID npid() { return new PairID(ni(), nd()); }
		private static final PairID[] npid(final int n) { final PairID a[] = new PairID[n]; for(int i = 0; i < n; i ++) a[i] = npid(); return a; }
		private static final PairID[][] npid(final int n, final int m) { final PairID a[][] = new PairID[n][m]; for(int i = 0; i < n; i ++) a[i] = npid(m); return a; }
		private static class PairID implements Comparable<PairID> {
			public int a; public double b;
			public PairID() { }
			public PairID(final int a, final double b) { this.a = a; this.b = b; }
			@Override public final String toString() { return "("+a+", "+b+")"; }
			@Override public final int hashCode() { return Objects.hash(a, b); }
			@Override
			public boolean equals(final Object obj) {
				if(this == obj) return true;
				if(obj == null) return false;
				if(this.getClass() != obj.getClass()) return false;
				PairID that = (PairID) obj;
				if(this.a != that.a || this.b != that.b) return false;
				return true;
			}
			@Override public final int compareTo(final PairID that) { int c = Integer.compare(this.a, that.a); if(c == 0) c = Double.compare(this.b, that.b); return c; }
		}
		private static final PairLI npli() { return new PairLI(nl(), ni()); }
		private static final PairLI[] npli(final int n) { final PairLI a[] = new PairLI[n]; for(int i = 0; i < n; i ++) a[i] = npli(); return a; }
		private static final PairLI[][] npli(final int n, final int m) { final PairLI a[][] = new PairLI[n][m]; for(int i = 0; i < n; i ++) a[i] = npli(m); return a; }
		private static class PairLI implements Comparable<PairLI> {
			public long a; public int b;
			public PairLI() { }
			public PairLI(final long a, final int b) { this.a = a; this.b = b; }
			@Override public final String toString() { return "("+a+", "+b+")"; }
			@Override public final int hashCode() { return Objects.hash(a, b); }
			@Override
			public boolean equals(final Object obj) {
				if(this == obj) return true;
				if(obj == null) return false;
				if(this.getClass() != obj.getClass()) return false;
				PairLI that = (PairLI) obj;
				if(this.a != that.a || this.b != that.b) return false;
				return true;
			}
			@Override public final int compareTo(final PairLI that) { int c = Long.compare(this.a, that.a); if(c == 0) c = Integer.compare(this.b, that.b); return c; }
		}
		private static final PairLL npll() { return new PairLL(nl(), nl()); }
		private static final PairLL[] npll(final int n) { final PairLL a[] = new PairLL[n]; for(int i = 0; i < n; i ++) a[i] = npll(); return a; }
		private static final PairLL[][] npll(final int n, final int m) { final PairLL a[][] = new PairLL[n][m]; for(int i = 0; i < n; i ++) a[i] = npll(m); return a; }
		private static class PairLL implements Comparable<PairLL> {
			public long a; public long b;
			public PairLL() { }
			public PairLL(final long a, final long b) { this.a = a; this.b = b; }
			@Override public final String toString() { return "("+a+", "+b+")"; }
			@Override public final int hashCode() { return Objects.hash(a, b); }
			@Override
			public boolean equals(final Object obj) {
				if(this == obj) return true;
				if(obj == null) return false;
				if(this.getClass() != obj.getClass()) return false;
				PairLL that = (PairLL) obj;
				if(this.a != that.a || this.b != that.b) return false;
				return true;
			}
			@Override public final int compareTo(final PairLL that) { int c = Long.compare(this.a, that.a); if(c == 0) c = Long.compare(this.b, that.b); return c; }
		}
		private static final PairLD npld() { return new PairLD(nl(), nd()); }
		private static final PairLD[] npld(final int n) { final PairLD a[] = new PairLD[n]; for(int i = 0; i < n; i ++) a[i] = npld(); return a; }
		private static final PairLD[][] npld(final int n, final int m) { final PairLD a[][] = new PairLD[n][m]; for(int i = 0; i < n; i ++) a[i] = npld(m); return a; }
		private static class PairLD implements Comparable<PairLD> {
			public long a; public double b;
			public PairLD() { }
			public PairLD(final long a, final double b) { this.a = a; this.b = b; }
			@Override public final String toString() { return "("+a+", "+b+")"; }
			@Override public final int hashCode() { return Objects.hash(a, b); }
			@Override
			public boolean equals(final Object obj) {
				if(this == obj) return true;
				if(obj == null) return false;
				if(this.getClass() != obj.getClass()) return false;
				PairLD that = (PairLD) obj;
				if(this.a != that.a || this.b != that.b) return false;
				return true;
			}
			@Override public final int compareTo(final PairLD that) { int c = Long.compare(this.a, that.a); if(c == 0) c = Double.compare(this.b, that.b); return c; }
		}
		private static final PairDI npdi() { return new PairDI(nd(), ni()); }
		private static final PairDI[] npdi(final int n) { final PairDI a[] = new PairDI[n]; for(int i = 0; i < n; i ++) a[i] = npdi(); return a; }
		private static final PairDI[][] npdi(final int n, final int m) { final PairDI a[][] = new PairDI[n][m]; for(int i = 0; i < n; i ++) a[i] = npdi(m); return a; }
		private static class PairDI implements Comparable<PairDI> {
			public double a; public int b;
			public PairDI() { }
			public PairDI(final double a, final int b) { this.a = a; this.b = b; }
			@Override public final String toString() { return "("+a+", "+b+")"; }
			@Override public final int hashCode() { return Objects.hash(a, b); }
			@Override
			public boolean equals(final Object obj) {
				if(this == obj) return true;
				if(obj == null) return false;
				if(this.getClass() != obj.getClass()) return false;
				PairDI that = (PairDI) obj;
				if(this.a != that.a || this.b != that.b) return false;
				return true;
			}
			@Override public final int compareTo(final PairDI that) { int c = Double.compare(this.a, that.a); if(c == 0) c = Integer.compare(this.b, that.b); return c; }
		}
		private static final PairDL npdl() { return new PairDL(nd(), nl()); }
		private static final PairDL[] npdl(final int n) { final PairDL a[] = new PairDL[n]; for(int i = 0; i < n; i ++) a[i] = npdl(); return a; }
		private static final PairDL[][] npdl(final int n, final int m) { final PairDL a[][] = new PairDL[n][m]; for(int i = 0; i < n; i ++) a[i] = npdl(m); return a; }
		private static class PairDL implements Comparable<PairDL> {
			public double a; public long b;
			public PairDL() { }
			public PairDL(final double a, final long b) { this.a = a; this.b = b; }
			@Override public final String toString() { return "("+a+", "+b+")"; }
			@Override public final int hashCode() { return Objects.hash(a, b); }
			@Override
			public boolean equals(final Object obj) {
				if(this == obj) return true;
				if(obj == null) return false;
				if(this.getClass() != obj.getClass()) return false;
				PairDL that = (PairDL) obj;
				if(this.a != that.a || this.b != that.b) return false;
				return true;
			}
			@Override public final int compareTo(final PairDL that) { int c = Double.compare(this.a, that.a); if(c == 0) c = Long.compare(this.b, that.b); return c; }
		}
		private static final PairDD npdd() { return new PairDD(nd(), nd()); }
		private static final PairDD[] npdd(final int n) { final PairDD a[] = new PairDD[n]; for(int i = 0; i < n; i ++) a[i] = npdd(); return a; }
		private static final PairDD[][] npdd(final int n, final int m) { final PairDD a[][] = new PairDD[n][m]; for(int i = 0; i < n; i ++) a[i] = npdd(m); return a; }
		private static class PairDD implements Comparable<PairDD> {
			public double a; public double b;
			public PairDD() { }
			public PairDD(final double a, final double b) { this.a = a; this.b = b; }
			@Override public final String toString() { return "("+a+", "+b+")"; }
			@Override public final int hashCode() { return Objects.hash(a, b); }
			@Override
			public boolean equals(final Object obj) {
				if(this == obj) return true;
				if(obj == null) return false;
				if(this.getClass() != obj.getClass()) return false;
				PairDD that = (PairDD) obj;
				if(this.a != that.a || this.b != that.b) return false;
				return true;
			}
			@Override public final int compareTo(final PairDD that) { int c = Double.compare(this.a, that.a); if(c == 0) c = Double.compare(this.b, that.b); return c; }
		}

		// Tuple
		private interface ITuple {
			public StringBuilder toStringBuilder();
			@Override public String toString();
			@Override public int hashCode();
			@Override public boolean equals(Object obj);
		}
		private static class BasicTuple<T extends ITuple & Comparable<? super T>, V extends Comparable<? super V>> implements Comparable<BasicTuple> {
			public T t; public V a;
			public BasicTuple() {  }

			private final StringBuilder sbTuple = new StringBuilder();
			public final StringBuilder toStringBuilder() {
				sbTuple.setLength(0);
				return sbTuple.append(t.toStringBuilder()).append(", ").append(a);
			}
			@Override public final String toString() { return "("+toStringBuilder().toString()+")"; }
			@Override public final int hashCode() { return Objects.hash(t, a); }
			@Override
			public final boolean equals(final Object obj) {
				if(this == obj) return true;
				if(obj == null) return false;
				if(this.getClass() != obj.getClass()) return false;
				BasicTuple that = (BasicTuple) obj;
				if(this.t.getClass() != that.t.getClass()) return false;
				if(this.a.getClass() != that.a.getClass()) return false;
				if(!this.t.equals(that.t)) return false;
				if(!this.a.equals(that.a)) return false;
				return true;
			}
			@Override @SuppressWarnings("unchecked") public final int compareTo(BasicTuple that) { int c = (this.t).compareTo((T) (Object) that.t); if(c == 0) c = (this.a).compareTo((V) (Object) that.a); return c; }
		}
		private static class UniqueTuple<V extends Comparable<? super V>> implements ITuple, Comparable<UniqueTuple> {
			public V a;
			public UniqueTuple() {  }

			private final StringBuilder sbTuple = new StringBuilder();
			public final StringBuilder toStringBuilder() { sbTuple.setLength(0); return sbTuple.append(a); }
			@Override public final String toString() { return "("+a.toString()+")"; }
			@Override public final int hashCode() { return Objects.hash(a); }
			@Override
			public final boolean equals(final Object obj) {
				if(this == obj) return true;
				if(obj == null) return false;
				if(this.getClass() != obj.getClass()) return false;
				UniqueTuple that = (UniqueTuple) obj;
				if(this.a.getClass() != that.a.getClass()) return false;
				if(!this.a.equals(that.a)) return false;
				return true;
			}
			@Override @SuppressWarnings("unchecked") public final int compareTo(UniqueTuple that) { return (this.a).compareTo((V) (Object) that.a); }
		}

		private static class Tuple1<T0 extends Comparable<? super T0>> extends UniqueTuple<T0> implements ITuple {
			public Tuple1() { super(); }
			public Tuple1(final T0 a0) { super(); this.a = a0; }
			final T0 get0() { return a; }
			final void set0(final T0 x) { a = x; }
		}
		private static class Tuple2<
				T0 extends Comparable<? super T0>,
				T1 extends Comparable<? super T1>>
				extends BasicTuple<Tuple1<T0>, T1> implements ITuple {
			public Tuple2() { super(); }
			public Tuple2(final T0 a0, final T1 a1) { super(); this.t = new Tuple1<>(a0); this.a = a1; }
			final T0 get0() { return t.get0(); }
			final T1 get1() { return a; }
			final void set0(final T0 x) { t.set0(x); }
			final void set1(final T1 x) { a = x; }
		}
		private static class Tuple3<
				T0 extends Comparable<? super T0>,
				T1 extends Comparable<? super T1>,
				T2 extends Comparable<? super T2>>
				extends BasicTuple<Tuple2<T0, T1>, T2> implements ITuple {
			public Tuple3() { super(); }
			public Tuple3(final T0 a0, final T1 a1, final T2 a2) { super(); this.t = new Tuple2<>(a0, a1); this.a = a2; }
			final T0 get0() { return t.get0(); }
			final T1 get1() { return t.get1(); }
			final T2 get2() { return a; }
			final void set0(final T0 x) { t.set0(x); }
			final void set1(final T1 x) { t.set1(x); }
			final void set2(final T2 x) { a = x; }
		}
		private static class Tuple4<
				T0 extends Comparable<? super T0>,
				T1 extends Comparable<? super T1>,
				T2 extends Comparable<? super T2>,
				T3 extends Comparable<? super T3>>
				extends BasicTuple<Tuple3<T0, T1, T2>, T3> implements ITuple {
			public Tuple4() { super(); }
			public Tuple4(final T0 a0, final T1 a1, final T2 a2, final T3 a3) { super(); this.t = new Tuple3<>(a0, a1, a2); this.a = a3; }
			final T0 get0() { return t.get0(); }
			final T1 get1() { return t.get1(); }
			final T2 get2() { return t.get2(); }
			final T3 get3() { return a; }
			final void set0(final T0 x) { t.set0(x); }
			final void set1(final T1 x) { t.set1(x); }
			final void set2(final T2 x) { t.set2(x); }
			final void set3(final T3 x) { a = x; }
		}
		private static class Tuple5<
				T0 extends Comparable<? super T0>,
				T1 extends Comparable<? super T1>,
				T2 extends Comparable<? super T2>,
				T3 extends Comparable<? super T3>,
				T4 extends Comparable<? super T4>>
				extends BasicTuple<Tuple4<T0, T1, T2, T3>, T4> implements ITuple {
			public Tuple5() { super(); }
			public Tuple5(final T0 a0, final T1 a1, final T2 a2, final T3 a3, final T4 a4) { super(); this.t = new Tuple4<>(a0, a1, a2, a3); this.a = a4; }
			final T0 get0() { return t.get0(); }
			final T1 get1() { return t.get1(); }
			final T2 get2() { return t.get2(); }
			final T3 get3() { return t.get3(); }
			final T4 get4() { return a; }
			final void set0(final T0 x) { t.set0(x); }
			final void set1(final T1 x) { t.set1(x); }
			final void set2(final T2 x) { t.set2(x); }
			final void set3(final T3 x) { t.set3(x); }
			final void set4(final T4 x) { a = x; }
		}
		private static class Tuple6<
				T0 extends Comparable<? super T0>,
				T1 extends Comparable<? super T1>,
				T2 extends Comparable<? super T2>,
				T3 extends Comparable<? super T3>,
				T4 extends Comparable<? super T4>,
				T5 extends Comparable<? super T5>>
				extends BasicTuple<Tuple5<T0, T1, T2, T3, T4>, T5> implements ITuple {
			public Tuple6() { super(); }
			public Tuple6(final T0 a0, final T1 a1, final T2 a2, final T3 a3, final T4 a4, final T5 a5) { super(); this.t = new Tuple5<>(a0, a1, a2, a3, a4); this.a = a5; }
			final T0 get0() { return t.get0(); }
			final T1 get1() { return t.get1(); }
			final T2 get2() { return t.get2(); }
			final T3 get3() { return t.get3(); }
			final T4 get4() { return t.get4(); }
			final T5 get5() { return a; }
			final void set0(final T0 x) { t.set0(x); }
			final void set1(final T1 x) { t.set1(x); }
			final void set2(final T2 x) { t.set2(x); }
			final void set3(final T3 x) { t.set3(x); }
			final void set4(final T4 x) { t.set4(x); }
			final void set5(final T5 x) { a = x; }
		}
		private static class Tuple7<
				T0 extends Comparable<? super T0>,
				T1 extends Comparable<? super T1>,
				T2 extends Comparable<? super T2>,
				T3 extends Comparable<? super T3>,
				T4 extends Comparable<? super T4>,
				T5 extends Comparable<? super T5>,
				T6 extends Comparable<? super T6>>
				extends BasicTuple<Tuple6<T0, T1, T2, T3, T4, T5>, T6> implements ITuple {
			public Tuple7() { super(); }
			public Tuple7(final T0 a0, final T1 a1, final T2 a2, final T3 a3, final T4 a4, final T5 a5, final T6 a6) { super(); this.t = new Tuple6<>(a0, a1, a2, a3, a4, a5); this.a = a6; }
			final T0 get0() { return t.get0(); }
			final T1 get1() { return t.get1(); }
			final T2 get2() { return t.get2(); }
			final T3 get3() { return t.get3(); }
			final T4 get4() { return t.get4(); }
			final T5 get5() { return t.get5(); }
			final T6 get6() { return a; }
			final void set0(final T0 x) { t.set0(x); }
			final void set1(final T1 x) { t.set1(x); }
			final void set2(final T2 x) { t.set2(x); }
			final void set3(final T3 x) { t.set3(x); }
			final void set4(final T4 x) { t.set4(x); }
			final void set5(final T5 x) { t.set5(x); }
			final void set6(final T6 x) { a = x; }
		}
		private static class Tuple8<
				T0 extends Comparable<? super T0>,
				T1 extends Comparable<? super T1>,
				T2 extends Comparable<? super T2>,
				T3 extends Comparable<? super T3>,
				T4 extends Comparable<? super T4>,
				T5 extends Comparable<? super T5>,
				T6 extends Comparable<? super T6>,
				T7 extends Comparable<? super T7>>
				extends BasicTuple<Tuple7<T0, T1, T2, T3, T4, T5, T6>, T7> implements ITuple {
			public Tuple8() { super(); }
			public Tuple8(final T0 a0, final T1 a1, final T2 a2, final T3 a3, final T4 a4, final T5 a5, final T6 a6, final T7 a7) { super(); this.t = new Tuple7<>(a0, a1, a2, a3, a4, a5, a6); this.a = a7; }
			final T0 get0() { return t.get0(); }
			final T1 get1() { return t.get1(); }
			final T2 get2() { return t.get2(); }
			final T3 get3() { return t.get3(); }
			final T4 get4() { return t.get4(); }
			final T5 get5() { return t.get5(); }
			final T6 get6() { return t.get6(); }
			final T7 get7() { return a; }
			final void set0(final T0 x) { t.set0(x); }
			final void set1(final T1 x) { t.set1(x); }
			final void set2(final T2 x) { t.set2(x); }
			final void set3(final T3 x) { t.set3(x); }
			final void set4(final T4 x) { t.set4(x); }
			final void set5(final T5 x) { t.set5(x); }
			final void set6(final T6 x) { t.set6(x); }
			final void set7(final T7 x) { a = x; }
		}

		// Tuple3
		private static class TupleIII implements Comparable<TupleIII> {
			public int a; public int b; public int c;
			public TupleIII() {  }
			public TupleIII(final int a, final int b, final int c) { this.a = a; this.b = b; this.c = c; }
			@Override public final String toString() { return "("+a+", "+b+", "+c+")"; }
			@Override public final int hashCode() { return Objects.hash(a, b, c); }
			@Override
			public final boolean equals(final Object obj) {
				if(this == obj) return true;
				if(obj == null || this.getClass() != obj.getClass()) return false;
				TupleIII that = (TupleIII) obj;
				if(this.a != that.a || this.b != that.b || this.c != that.c) return false;
				return true;
			}
			@Override public final int compareTo(final TupleIII that) { int c = Integer.compare(this.a, that.a); if(c == 0) c = Integer.compare(this.b, that.b); if(c == 0) c = Integer.compare(this.c, that.c); return c; }
		}
		private static class TupleIIL implements Comparable<TupleIIL> {
			public int a; public int b; public long c;
			public TupleIIL() {  }
			public TupleIIL(final int a, final int b, final long c) { this.a = a; this.b = b; this.c = c; }
			@Override public final String toString() { return "("+a+", "+b+", "+c+")"; }
			@Override public final int hashCode() { return Objects.hash(a, b, c); }
			@Override
			public final boolean equals(final Object obj) {
				if(this == obj) return true;
				if(obj == null || this.getClass() != obj.getClass()) return false;
				TupleIIL that = (TupleIIL) obj;
				if(this.a != that.a || this.b != that.b || this.c != that.c) return false;
				return true;
			}
			@Override public final int compareTo(final TupleIIL that) { int c = Integer.compare(this.a, that.a); if(c == 0) c = Integer.compare(this.b, that.b); if(c == 0) c = Long.compare(this.c, that.c); return c;
			}
		}
		private static class TupleIID implements Comparable<TupleIID> {
			public int a; public int b; public double c;
			public TupleIID() {  }
			public TupleIID(final int a, final int b, final double c) { this.a = a; this.b = b; this.c = c; }
			@Override public final String toString() { return "("+a+", "+b+", "+c+")"; }
			@Override public final int hashCode() { return Objects.hash(a, b, c); }
			@Override
			public final boolean equals(final Object obj) {
				if(this == obj) return true;
				if(obj == null || this.getClass() != obj.getClass()) return false;
				TupleIID that = (TupleIID) obj;
				if(this.a != that.a || this.b != that.b || this.c != that.c) return false;
				return true;
			}
			@Override public final int compareTo(final TupleIID that) { int c = Integer.compare(this.a, that.a); if(c == 0) c = Integer.compare(this.b, that.b); if(c == 0) c = Double.compare(this.c, that.c); return c; }
		}
		private static class TupleILI implements Comparable<TupleILI> {
			public int a; public long b; public int c;
			public TupleILI() {  }
			public TupleILI(final int a, final long b, final int c) { this.a = a; this.b = b; this.c = c; }
			@Override public final String toString() { return "("+a+", "+b+", "+c+")"; }
			@Override public final int hashCode() { return Objects.hash(a, b, c); }
			@Override
			public final boolean equals(final Object obj) {
				if(this == obj) return true;
				if(obj == null || this.getClass() != obj.getClass()) return false;
				TupleILI that = (TupleILI) obj;
				if(this.a != that.a || this.b != that.b || this.c != that.c) return false;
				return true;
			}
			@Override public final int compareTo(final TupleILI that) { int c = Integer.compare(this.a, that.a); if(c == 0) c = Long.compare(this.b, that.b); if(c == 0) c = Integer.compare(this.c, that.c); return c; }
		}
		private static class TupleILL implements Comparable<TupleILL> {
			public int a; public long b; public long c;
			public TupleILL() {  }
			public TupleILL(final int a, final long b, final long c) { this.a = a; this.b = b; this.c = c; }
			@Override public final String toString() { return "("+a+", "+b+", "+c+")"; }
			@Override public final int hashCode() { return Objects.hash(a, b, c); }
			@Override
			public final boolean equals(final Object obj) {
				if(this == obj) return true;
				if(obj == null || this.getClass() != obj.getClass()) return false;
				TupleILL that = (TupleILL) obj;
				if(this.a != that.a || this.b != that.b || this.c != that.c) return false;
				return true;
			}
			@Override public final int compareTo(final TupleILL that) { int c = Integer.compare(this.a, that.a); if(c == 0) c = Long.compare(this.b, that.b); if(c == 0) c = Long.compare(this.c, that.c); return c; }
		}
		private static class TupleILD implements Comparable<TupleILD> {
			public int a; public long b; public double c;
			public TupleILD() {  }
			public TupleILD(final int a, final long b, final double c) { this.a = a; this.b = b; this.c = c; }
			@Override public final String toString() { return "("+a+", "+b+", "+c+")"; }
			@Override public final int hashCode() { return Objects.hash(a, b, c); }
			@Override
			public final boolean equals(final Object obj) {
				if(this == obj) return true;
				if(obj == null || this.getClass() != obj.getClass()) return false;
				TupleILD that = (TupleILD) obj;
				if(this.a != that.a || this.b != that.b || this.c != that.c) return false;
				return true;
			}
			@Override public final int compareTo(final TupleILD that) { int c = Integer.compare(this.a, that.a); if(c == 0) c = Long.compare(this.b, that.b); if(c == 0) c = Double.compare(this.c, that.c); return c; }
		}
		private static class TupleIDI implements Comparable<TupleIDI> {
			public int a; public double b; public int c;
			public TupleIDI() {  }
			public TupleIDI(final int a, final double b, final int c) { this.a = a; this.b = b; this.c = c; }
			@Override public final String toString() { return "("+a+", "+b+", "+c+")"; }
			@Override public final int hashCode() { return Objects.hash(a, b, c); }
			@Override
			public final boolean equals(final Object obj) {
				if(this == obj) return true;
				if(obj == null || this.getClass() != obj.getClass()) return false;
				TupleIDI that = (TupleIDI) obj;
				if(this.a != that.a || this.b != that.b || this.c != that.c) return false;
				return true;
			}
			@Override public final int compareTo(final TupleIDI that) { int c = Integer.compare(this.a, that.a); if(c == 0) c = Double.compare(this.b, that.b); if(c == 0) c = Integer.compare(this.c, that.c); return c; }
		}
		private static class TupleIDL implements Comparable<TupleIDL> {
			public int a; public double b; public long c;
			public TupleIDL() {  }
			public TupleIDL(final int a, final double b, final long c) { this.a = a; this.b = b; this.c = c; }
			@Override public final String toString() { return "("+a+", "+b+", "+c+")"; }
			@Override public final int hashCode() { return Objects.hash(a, b, c); }
			@Override
			public final boolean equals(final Object obj) {
				if(this == obj) return true;
				if(obj == null || this.getClass() != obj.getClass()) return false;
				TupleIDL that = (TupleIDL) obj;
				if(this.a != that.a || this.b != that.b || this.c != that.c) return false;
				return true;
			}
			@Override public final int compareTo(final TupleIDL that) { int c = Integer.compare(this.a, that.a); if(c == 0) c = Double.compare(this.b, that.b); if(c == 0) c = Long.compare(this.c, that.c); return c; }
		}
		private static class TupleIDD implements Comparable<TupleIDD> {
			public int a; public double b; public double c;
			public TupleIDD() {  }
			public TupleIDD(final int a, final double b, final double c) { this.a = a; this.b = b; this.c = c; }
			@Override public final String toString() { return "("+a+", "+b+", "+c+")"; }
			@Override public final int hashCode() { return Objects.hash(a, b, c); }
			@Override
			public final boolean equals(final Object obj) {
				if(this == obj) return true;
				if(obj == null || this.getClass() != obj.getClass()) return false;
				TupleIDD that = (TupleIDD) obj;
				if(this.a != that.a || this.b != that.b || this.c != that.c) return false;
				return true;
			}
			@Override public final int compareTo(final TupleIDD that) { int c = Integer.compare(this.a, that.a); if(c == 0) c = Double.compare(this.b, that.b); if(c == 0) c = Double.compare(this.c, that.c); return c; }
		}
		private static class TupleLII implements Comparable<TupleLII> {
			public long a; public int b; public int c;
			public TupleLII() {  }
			public TupleLII(final long a, final int b, final int c) { this.a = a; this.b = b; this.c = c; }
			@Override public final String toString() { return "("+a+", "+b+", "+c+")"; }
			@Override public final int hashCode() { return Objects.hash(a, b, c); }
			@Override
			public final boolean equals(final Object obj) {
				if(this == obj) return true;
				if(obj == null || this.getClass() != obj.getClass()) return false;
				TupleLII that = (TupleLII) obj;
				if(this.a != that.a || this.b != that.b || this.c != that.c) return false;
				return true;
			}
			@Override public final int compareTo(final TupleLII that) { int c = Long.compare(this.a, that.a); if(c == 0) c = Integer.compare(this.b, that.b); if(c == 0) c = Integer.compare(this.c, that.c); return c; }
		}
		private static class TupleLIL implements Comparable<TupleLIL> {
			public long a; public int b; public long c;
			public TupleLIL() {  }
			public TupleLIL(final long a, final int b, final long c) { this.a = a; this.b = b; this.c = c; }
			@Override public final String toString() { return "("+a+", "+b+", "+c+")"; }
			@Override public final int hashCode() { return Objects.hash(a, b, c); }
			@Override
			public final boolean equals(final Object obj) {
				if(this == obj) return true;
				if(obj == null || this.getClass() != obj.getClass()) return false;
				TupleLIL that = (TupleLIL) obj;
				if(this.a != that.a || this.b != that.b || this.c != that.c) return false;
				return true;
			}
			@Override public final int compareTo(final TupleLIL that) { int c = Long.compare(this.a, that.a); if(c == 0) c = Integer.compare(this.b, that.b); if(c == 0) c = Long.compare(this.c, that.c); return c; }
		}
		private static class TupleLID implements Comparable<TupleLID> {
			public long a; public int b; public double c;
			public TupleLID() {  }
			public TupleLID(final long a, final int b, final double c) { this.a = a; this.b = b; this.c = c; }
			@Override public final String toString() { return "("+a+", "+b+", "+c+")"; }
			@Override public final int hashCode() { return Objects.hash(a, b, c); }
			@Override
			public final boolean equals(final Object obj) {
				if(this == obj) return true;
				if(obj == null || this.getClass() != obj.getClass()) return false;
				TupleLID that = (TupleLID) obj;
				if(this.a != that.a || this.b != that.b || this.c != that.c) return false;
				return true;
			}
			@Override public final int compareTo(final TupleLID that) { int c = Long.compare(this.a, that.a); if(c == 0) c = Integer.compare(this.b, that.b); if(c == 0) c = Double.compare(this.c, that.c); return c; }
		}
		private static class TupleLLI implements Comparable<TupleLLI> {
			public long a; public long b; public int c;
			public TupleLLI() {  }
			public TupleLLI(final long a, final long b, final int c) { this.a = a; this.b = b; this.c = c; }
			@Override public final String toString() { return "("+a+", "+b+", "+c+")"; }
			@Override public final int hashCode() { return Objects.hash(a, b, c); }
			@Override
			public final boolean equals(final Object obj) {
				if(this == obj) return true;
				if(obj == null || this.getClass() != obj.getClass()) return false;
				TupleLLI that = (TupleLLI) obj;
				if(this.a != that.a || this.b != that.b || this.c != that.c) return false;
				return true;
			}
			@Override public final int compareTo(final TupleLLI that) { int c = Long.compare(this.a, that.a); if(c == 0) c = Long.compare(this.b, that.b); if(c == 0) c = Integer.compare(this.c, that.c); return c; }
		}
		private static class TupleLLL implements Comparable<TupleLLL> {
			public long a; public long b; public long c;
			public TupleLLL() {  }
			public TupleLLL(final long a, final long b, final long c) { this.a = a; this.b = b; this.c = c; }
			@Override public final String toString() { return "("+a+", "+b+", "+c+")"; }
			@Override public final int hashCode() { return Objects.hash(a, b, c); }
			@Override
			public final boolean equals(final Object obj) {
				if(this == obj) return true;
				if(obj == null || this.getClass() != obj.getClass()) return false;
				TupleLLL that = (TupleLLL) obj;
				if(this.a != that.a || this.b != that.b || this.c != that.c) return false;
				return true;
			}
			@Override public final int compareTo(final TupleLLL that) { int c = Long.compare(this.a, that.a); if(c == 0) c = Long.compare(this.b, that.b); if(c == 0) c = Long.compare(this.c, that.c); return c; }
		}
		private static class TupleLLD implements Comparable<TupleLLD> {
			public long a; public long b; public double c;
			public TupleLLD() {  }
			public TupleLLD(final long a, final long b, final double c) { this.a = a; this.b = b; this.c = c; }
			@Override public final String toString() { return "("+a+", "+b+", "+c+")"; }
			@Override public final int hashCode() { return Objects.hash(a, b, c); }
			@Override
			public final boolean equals(final Object obj) {
				if(this == obj) return true;
				if(obj == null || this.getClass() != obj.getClass()) return false;
				TupleLLD that = (TupleLLD) obj;
				if(this.a != that.a || this.b != that.b || this.c != that.c) return false;
				return true;
			}
			@Override public final int compareTo(final TupleLLD that) { int c = Long.compare(this.a, that.a); if(c == 0) c = Long.compare(this.b, that.b); if(c == 0) c = Double.compare(this.c, that.c); return c; }
		}
		private static class TupleLDI implements Comparable<TupleLDI> {
			public long a; public double b; public int c;
			public TupleLDI() {  }
			public TupleLDI(final long a, final double b, final int c) { this.a = a; this.b = b; this.c = c; }
			@Override public final String toString() { return "("+a+", "+b+", "+c+")"; }
			@Override public final int hashCode() { return Objects.hash(a, b, c); }
			@Override
			public final boolean equals(final Object obj) {
				if(this == obj) return true;
				if(obj == null || this.getClass() != obj.getClass()) return false;
				TupleLDI that = (TupleLDI) obj;
				if(this.a != that.a || this.b != that.b || this.c != that.c) return false;
				return true;
			}
			@Override public final int compareTo(final TupleLDI that) { int c = Long.compare(this.a, that.a); if(c == 0) c = Double.compare(this.b, that.b); if(c == 0) c = Integer.compare(this.c, that.c); return c; }
		}
		private static class TupleLDL implements Comparable<TupleLDL> {
			public long a; public double b; public long c;
			public TupleLDL() {  }
			public TupleLDL(final long a, final double b, final long c) { this.a = a; this.b = b; this.c = c; }
			@Override public final String toString() { return "("+a+", "+b+", "+c+")"; }
			@Override public final int hashCode() { return Objects.hash(a, b, c); }
			@Override
			public final boolean equals(final Object obj) {
				if(this == obj) return true;
				if(obj == null || this.getClass() != obj.getClass()) return false;
				TupleLDL that = (TupleLDL) obj;
				if(this.a != that.a || this.b != that.b || this.c != that.c) return false;
				return true;
			}
			@Override public final int compareTo(final TupleLDL that) { int c = Long.compare(this.a, that.a); if(c == 0) c = Double.compare(this.b, that.b); if(c == 0) c = Long.compare(this.c, that.c); return c; }
		}
		private static class TupleLDD implements Comparable<TupleLDD> {
			public long a; public double b; public double c;
			public TupleLDD() {  }
			public TupleLDD(final long a, final double b, final double c) { this.a = a; this.b = b; this.c = c; }
			@Override public final String toString() { return "("+a+", "+b+", "+c+")"; }
			@Override public final int hashCode() { return Objects.hash(a, b, c); }
			@Override
			public final boolean equals(final Object obj) {
				if(this == obj) return true;
				if(obj == null || this.getClass() != obj.getClass()) return false;
				TupleLDD that = (TupleLDD) obj;
				if(this.a != that.a || this.b != that.b || this.c != that.c) return false;
				return true;
			}
			@Override public final int compareTo(final TupleLDD that) { int c = Long.compare(this.a, that.a); if(c == 0) c = Double.compare(this.b, that.b); if(c == 0) c = Double.compare(this.c, that.c); return c; }
		}
		private static class TupleDII implements Comparable<TupleDII> {
			public double a; public int b; public int c;
			public TupleDII() {  }
			public TupleDII(final double a, final int b, final int c) { this.a = a; this.b = b; this.c = c; }
			@Override public final String toString() { return "("+a+", "+b+", "+c+")"; }
			@Override public final int hashCode() { return Objects.hash(a, b, c); }
			@Override
			public final boolean equals(final Object obj) {
				if(this == obj) return true;
				if(obj == null || this.getClass() != obj.getClass()) return false;
				TupleDII that = (TupleDII) obj;
				if(this.a != that.a || this.b != that.b || this.c != that.c) return false;
				return true;
			}
			@Override public final int compareTo(final TupleDII that) { int c = Double.compare(this.a, that.a); if(c == 0) c = Integer.compare(this.b, that.b); if(c == 0) c = Integer.compare(this.c, that.c); return c; }
		}
		private static class TupleDIL implements Comparable<TupleDIL> {
			public double a; public int b; public long c;
			public TupleDIL() {  }
			public TupleDIL(final double a, final int b, final long c) { this.a = a; this.b = b; this.c = c; }
			@Override public final String toString() { return "("+a+", "+b+", "+c+")"; }
			@Override public final int hashCode() { return Objects.hash(a, b, c); }
			@Override
			public final boolean equals(final Object obj) {
				if(this == obj) return true;
				if(obj == null || this.getClass() != obj.getClass()) return false;
				TupleDIL that = (TupleDIL) obj;
				if(this.a != that.a || this.b != that.b || this.c != that.c) return false;
				return true;
			}
			@Override public final int compareTo(final TupleDIL that) { int c = Double.compare(this.a, that.a); if(c == 0) c = Integer.compare(this.b, that.b); if(c == 0) c = Long.compare(this.c, that.c); return c; }
		}
		private static class TupleDID implements Comparable<TupleDID> {
			public double a; public int b; public double c;
			public TupleDID() {  }
			public TupleDID(final double a, final int b, final double c) { this.a = a; this.b = b; this.c = c; }
			@Override public final String toString() { return "("+a+", "+b+", "+c+")"; }
			@Override public final int hashCode() { return Objects.hash(a, b, c); }
			@Override
			public final boolean equals(final Object obj) {
				if(this == obj) return true;
				if(obj == null || this.getClass() != obj.getClass()) return false;
				TupleDID that = (TupleDID) obj;
				if(this.a != that.a || this.b != that.b || this.c != that.c) return false;
				return true;
			}
			@Override public final int compareTo(final TupleDID that) { int c = Double.compare(this.a, that.a); if(c == 0) c = Integer.compare(this.b, that.b); if(c == 0) c = Double.compare(this.c, that.c); return c; }
		}
		private static class TupleDLI implements Comparable<TupleDLI> {
			public double a; public long b; public int c;
			public TupleDLI() {  }
			public TupleDLI(final double a, final long b, final int c) { this.a = a; this.b = b; this.c = c; }
			@Override public final String toString() { return "("+a+", "+b+", "+c+")"; }
			@Override public final int hashCode() { return Objects.hash(a, b, c); }
			@Override
			public final boolean equals(final Object obj) {
				if(this == obj) return true;
				if(obj == null || this.getClass() != obj.getClass()) return false;
				TupleDLI that = (TupleDLI) obj;
				if(this.a != that.a || this.b != that.b || this.c != that.c) return false;
				return true;
			}
			@Override public final int compareTo(final TupleDLI that) { int c = Double.compare(this.a, that.a); if(c == 0) c = Long.compare(this.b, that.b); if(c == 0) c = Integer.compare(this.c, that.c); return c; }
		}
		private static class TupleDLL implements Comparable<TupleDLL> {
			public double a; public long b; public long c;
			public TupleDLL() {  }
			public TupleDLL(final double a, final long b, final long c) { this.a = a; this.b = b; this.c = c; }
			@Override public final String toString() { return "("+a+", "+b+", "+c+")"; }
			@Override public final int hashCode() { return Objects.hash(a, b, c); }
			@Override
			public final boolean equals(final Object obj) {
				if(this == obj) return true;
				if(obj == null || this.getClass() != obj.getClass()) return false;
				TupleDLL that = (TupleDLL) obj;
				if(this.a != that.a || this.b != that.b || this.c != that.c) return false;
				return true;
			}
			@Override public final int compareTo(final TupleDLL that) { int c = Double.compare(this.a, that.a); if(c == 0) c = Long.compare(this.b, that.b); if(c == 0) c = Long.compare(this.c, that.c); return c; }
		}
		private static class TupleDLD implements Comparable<TupleDLD> {
			public double a; public long b; public double c;
			public TupleDLD() {  }
			public TupleDLD(final double a, final long b, final double c) { this.a = a; this.b = b; this.c = c; }
			@Override public final String toString() { return "("+a+", "+b+", "+c+")"; }
			@Override public final int hashCode() { return Objects.hash(a, b, c); }
			@Override
			public final boolean equals(final Object obj) {
				if(this == obj) return true;
				if(obj == null || this.getClass() != obj.getClass()) return false;
				TupleDLD that = (TupleDLD) obj;
				if(this.a != that.a || this.b != that.b || this.c != that.c) return false;
				return true;
			}
			@Override public final int compareTo(final TupleDLD that) { int c = Double.compare(this.a, that.a); if(c == 0) c = Long.compare(this.b, that.b); if(c == 0) c = Double.compare(this.c, that.c); return c; }
		}
		private static class TupleDDI implements Comparable<TupleDDI> {
			public double a; public double b; public int c;
			public TupleDDI() {  }
			public TupleDDI(final double a, final double b, final int c) { this.a = a; this.b = b; this.c = c; }
			@Override public final String toString() { return "("+a+", "+b+", "+c+")"; }
			@Override public final int hashCode() { return Objects.hash(a, b, c); }
			@Override
			public final boolean equals(final Object obj) {
				if(this == obj) return true;
				if(obj == null || this.getClass() != obj.getClass()) return false;
				TupleDDI that = (TupleDDI) obj;
				if(this.a != that.a || this.b != that.b || this.c != that.c) return false;
				return true;
			}
			@Override public final int compareTo(final TupleDDI that) { int c = Double.compare(this.a, that.a); if(c == 0) c = Double.compare(this.b, that.b); if(c == 0) c = Integer.compare(this.c, that.c); return c; }
		}
		private static class TupleDDL implements Comparable<TupleDDL> {
			public double a; public double b; public long c;
			public TupleDDL() {  }
			public TupleDDL(final double a, final double b, final long c) { this.a = a; this.b = b; this.c = c; }
			@Override public final String toString() { return "("+a+", "+b+", "+c+")"; }
			@Override public final int hashCode() { return Objects.hash(a, b, c); }
			@Override
			public final boolean equals(final Object obj) {
				if(this == obj) return true;
				if(obj == null || this.getClass() != obj.getClass()) return false;
				TupleDDL that = (TupleDDL) obj;
				if(this.a != that.a || this.b != that.b || this.c != that.c) return false;
				return true;
			}
			@Override public final int compareTo(final TupleDDL that) { int c = Double.compare(this.a, that.a); if(c == 0) c = Double.compare(this.b, that.b); if(c == 0) c = Long.compare(this.c, that.c); return c; }
		}
		private static class TupleDDD implements Comparable<TupleDDD> {
			public double a; public double b; public double c;
			public TupleDDD() {  }
			public TupleDDD(final double a, final double b, final double c) { this.a = a; this.b = b; this.c = c; }
			@Override public final String toString() { return "("+a+", "+b+", "+c+")"; }
			@Override public final int hashCode() { return Objects.hash(a, b, c); }
			@Override
			public final boolean equals(final Object obj) {
				if(this == obj) return true;
				if(obj == null || this.getClass() != obj.getClass()) return false;
				TupleDDD that = (TupleDDD) obj;
				if(this.a != that.a || this.b != that.b || this.c != that.c) return false;
				return true;
			}
			@Override public final int compareTo(final TupleDDD that) { int c = Double.compare(this.a, that.a); if(c == 0) c = Double.compare(this.b, that.b); if(c == 0) c = Double.compare(this.c, that.c); return c; }
		}


public void solve() {
	long n = nl();
	int m = ni();
	PairLI p[] = npli(m);

	if(m == 0) {
		prtln(n % 2 != 0 ? "Takahashi" : "Aoki");
		return;
	}

	for(int i = 0; i < m; i ++) if(p[i].a % 2 == 0) p[i].b = 1 - p[i].b;
	int cnt = 0;
	long x = p[0].a - 1;
	long y = n - p[m - 1].a;
	
	boolean odd = false;
	for(int i = 1; i < m; i ++) {
		if(p[i - 1].a % 2 == p[i].a % 2) odd = !odd;
		if(p[i - 1].b != p[i].b) odd = !odd;
	}
	if(abs(x - y) == 1) odd = !odd;
	prtln(abs(x - y) > 1 || (abs(x - y) == 1 && min(x, y) % 2 != 0) || odd ? "Takahashi" : "Aoki");
}


	}
}

