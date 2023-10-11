import java.util.*;
import java.io.*;
import java.math.*;
import java.util.function.*;
public class Main implements Runnable {
	static boolean DEBUG;
	public static void main(String[] args) {
		DEBUG = args.length > 0 && args[0].equals("-DEBUG");
		Thread.setDefaultUncaughtExceptionHandler((t, e) -> { e.printStackTrace(); System.exit(1); });
		new Thread(null, new Main(), "", 1 << 31).start();
	}

	public void run() { Solver solver = new Solver(); solver.solve(); solver.exit(); }

	static class FastScanner {
		private final InputStream in = System.in;
		private final byte[] buffer = new byte[1024];
		private int pointer = 0;
		private int buflen = 0;
		private boolean hasNextByte() {
			if(pointer < buflen) return true;
			else {
				pointer = 0;
				try { buflen = in.read(buffer);
				}catch (IOException e) { e.printStackTrace(); }
				return buflen > 0;
			}
		}
		private int readByte() { if(hasNextByte()) return buffer[pointer ++]; else return -1; }
		private boolean isPrintableChar(int c) { return isPrintableChar(c, false); }
		private boolean isPrintableChar(int c, boolean includingSpace) { return (includingSpace ? 32 : 33) <= c && c <= 126; }
		private void skipUnprintable() { skipUnprintable(false); }
		private void skipUnprintable(boolean includingSpace) { while(hasNextByte() && !isPrintableChar(buffer[pointer], includingSpace)) pointer ++; }
		private boolean hasNext() { return hasNext(false); }
		private boolean hasNext(boolean includingSpace) { skipUnprintable(includingSpace); return hasNextByte(); }
		private StringBuilder sb = new StringBuilder();
		public String next() { return next(false); }
		public String next(boolean includingSpace) {
			if(!hasNext(includingSpace)) throw new NoSuchElementException();
			sb.setLength(0);
			int b = readByte();
			while(isPrintableChar(b, includingSpace)) { sb.appendCodePoint(b); b = readByte(); }
			return sb.toString();
		}
		public long nextLong() {
			if(!hasNext()) throw new NoSuchElementException();
			long n = 0;
			boolean minus = false;
			int b = readByte();
			if(b == '-') { minus = true; b = readByte(); }
			if(b < '0' || '9' < b) throw new NumberFormatException();
			while(true) {
				if('0' <= b && b <= '9') n = n * 10 + b - '0';
				else if(b == -1 || !isPrintableChar(b)) return minus ? - n : n;
				else throw new NumberFormatException();
				b = readByte();
			}
		}
	}

	static class Solver {
		final FastScanner sc = new FastScanner();
		public Solver() { }

		final String ns() { return ns(false); }
		final String ns(boolean includingSpace) { return sc.next(includingSpace); }
		final String[] ns(int n) { return ns(n, false); }
		final String[] ns(int n, boolean includingSpace) { String a[] = new String[n]; for(int i = 0; i < n; i ++) a[i] = ns(includingSpace); return a; }
		final String[][] ns(int n, int m) { return ns(n, m, false); }
		final String[][] ns(int n, int m, boolean includingSpace) { String a[][] = new String[n][m]; for(int i = 0; i < n; i ++) a[i] = ns(m, includingSpace); return a; }
		final char nc() { return ns().charAt(0); }
		final char[] nc(int n) {
			String str = ns();
			if(n < 0) n = str.length();
			char a[] = new char[n];
			for(int i = 0; i < n; i ++) a[i] = str.charAt(i);
			return a;
		}
		final char[][] nc(int n, int m) { char a[][] = new char[n][m]; for(int i = 0; i < n; i ++) a[i] = nc(m); return a; }
		final boolean[] nb(int n, char t) {
			char c[] = nc(-1);
			if(n < 0) n = c.length;
			boolean a[] = new boolean[n];
			for(int i = 0; i < n; i ++) a[i] = c[i] == t;
			return a;
		}
		final boolean[][] nb(int n, int m, char t) { boolean a[][] = new boolean[n][m]; for(int i = 0; i < n; i ++) a[i] = nb(m, t); return a; }
		final int ni() { return Math.toIntExact(sc.nextLong()); }
		final int[] ni(int n) { int a[] = new int[n]; for(int i = 0; i < n; i ++) a[i] = ni(); return a; }
		final int[][] ni(int n, int m) { int a[][] = new int[n][m]; for(int i = 0; i < n; i ++) a[i] = ni(m); return a; }
		final long nl() { return sc.nextLong(); }
		final long[] nl(int n) { long a[] = new long[n]; for(int i = 0; i < n; i ++) a[i] = nl(); return a; }
		final long[][] nl(int n, int m) { long a[][] = new long[n][m]; for(int i = 0; i < n; i ++) a[i] = nl(m); return a; }
		final double nd() { return Double.parseDouble(sc.next()); }
		final double[] nd(int n) { double a[] = new double[n]; for(int i = 0; i < n; i ++) a[i] = nd(); return a; }
		final double[][] nd(int n, int m) { double a[][] = new double[n][m]; for(int i = 0; i < n; i ++) a[i] = nd(m); return a; }

		final String booleanToString(boolean b) { return b ? "#" : "."; }

		final PrintWriter out = new PrintWriter(System.out);
		final PrintWriter err = new PrintWriter(System.err);
		final StringBuilder sb4prtln = new StringBuilder();
		final void prt() { out.print(""); }
		final <T> void prt(T a) { out.print(a); }
		final void prtln() { out.println(""); }
		final <T> void prtln(T a) { out.println(a); }
		final void prtln(int... a) { sb4prtln.setLength(0); for(int ele : a) { sb4prtln.append(ele); sb4prtln.append(" "); } if(sb4prtln.length() != 0) sb4prtln.deleteCharAt(sb4prtln.length() - 1); prtln(sb4prtln); }
		final void prtln(long... a) { sb4prtln.setLength(0); for(long ele : a) { sb4prtln.append(ele); sb4prtln.append(" "); } if(sb4prtln.length() != 0) sb4prtln.deleteCharAt(sb4prtln.length() - 1); prtln(sb4prtln); }
		final void prtln(double... a) { sb4prtln.setLength(0); for(double ele : a) { sb4prtln.append(ele); sb4prtln.append(" "); } if(sb4prtln.length() != 0) sb4prtln.deleteCharAt(sb4prtln.length() - 1); prtln(sb4prtln); }
		final void prtln(String... a) { sb4prtln.setLength(0); for(String ele : a) { sb4prtln.append(ele); sb4prtln.append(" "); } if(sb4prtln.length() != 0) sb4prtln.deleteCharAt(sb4prtln.length() - 1); prtln(sb4prtln); }
		final void prtln(char... a) { sb4prtln.setLength(0); for(char ele : a) sb4prtln.append(ele); prtln(sb4prtln); }
		final void prtln(boolean... a) { sb4prtln.setLength(0); for(boolean ele : a) sb4prtln.append(booleanToString(ele)); prtln(sb4prtln); }
		final void prtlns(int... a) { sb4prtln.setLength(0); for(int ele : a) { sb4prtln.append(ele); sb4prtln.append("\n"); } prt(sb4prtln); }
		final void prtlns(long... a) { sb4prtln.setLength(0); for(long ele : a) { sb4prtln.append(ele); sb4prtln.append("\n"); } prt(sb4prtln); }
		final void prtlns(double... a) { sb4prtln.setLength(0); for(double ele : a) { sb4prtln.append(ele); sb4prtln.append("\n"); } prt(sb4prtln); }
		final void prtlns(String... a) { sb4prtln.setLength(0); for(String ele : a) { sb4prtln.append(ele); sb4prtln.append("\n"); } prt(sb4prtln); }
		final void prtlns(char... a) { sb4prtln.setLength(0); for(char ele : a) { sb4prtln.append(ele); sb4prtln.append("\n"); } prt(sb4prtln); }
		final void prtlns(boolean... a) { sb4prtln.setLength(0); for(boolean ele : a) { sb4prtln.append(booleanToString(ele)); sb4prtln.append("\n"); } prt(sb4prtln); }
		final void prtln(int[][] a) { for(int[] ele : a) prtln(ele); }
		final void prtln(long[][] a) { for(long[] ele : a) prtln(ele); }
		final void prtln(double[][] a) { for(double[] ele : a) prtln(ele); }
		final void prtln(String[][] a) { for(String[] ele : a) prtln(ele); }
		final void prtln(char[][] a) { for(char[] ele : a) prtln(ele); }
		final void prtln(boolean[][] a) { for(boolean[] ele : a) prtln(ele); }

		final String errconvert(int a) { return isINF(a) ? "_" : String.valueOf(a); }
		final String errconvert(long a) { return isINF(a) ? "_" : String.valueOf(a); }
		final void errprt(int a) { if(DEBUG) err.print(errconvert(a)); }
		final void errprt(long a) { if(DEBUG) err.print(errconvert(a)); }
		final void errprt() { if(DEBUG) err.print(""); }
		final <T> void errprt(T a) { if(DEBUG) err.print(a); }
		final void errprt(boolean a) { if(DEBUG) errprt(booleanToString(a)); }
		final void errprtln() { if(DEBUG) err.println(""); }
		final void errprtln(int a) { if(DEBUG) err.println(errconvert(a)); }
		final void errprtln(long a) { if(DEBUG) err.println(errconvert(a)); }
		final <T> void errprtln(T a) { if(DEBUG) err.println(a); }
		final void errprtln(boolean a) { if(DEBUG) errprtln(booleanToString(a)); }
		final void errprtln(int... a) { if(DEBUG) { sb4prtln.setLength(0); for(int ele : a) { sb4prtln.append(errconvert(ele)); sb4prtln.append(" "); } errprtln(sb4prtln.toString().trim()); } }
		final void errprtln(long... a) { if(DEBUG) { sb4prtln.setLength(0); for(long ele : a) { sb4prtln.append(errconvert(ele)); sb4prtln.append(" "); } errprtln(sb4prtln.toString().trim()); } }
		final void errprtln(double... a) { if(DEBUG) { sb4prtln.setLength(0); for(double ele : a) { sb4prtln.append(ele); sb4prtln.append(" "); } errprtln(sb4prtln.toString().trim()); } }
		final void errprtln(String... a) { if(DEBUG) { sb4prtln.setLength(0); for(String ele : a) { sb4prtln.append(ele); sb4prtln.append(" "); } errprtln(sb4prtln.toString().trim()); } }
		final void errprtln(char... a) { if(DEBUG) { sb4prtln.setLength(0); for(char ele : a) sb4prtln.append(ele); errprtln(sb4prtln.toString()); } }
		final void errprtln(boolean... a) { if(DEBUG) { sb4prtln.setLength(0); for(boolean ele : a) sb4prtln.append(booleanToString(ele)); errprtln(sb4prtln.toString()); } }
		final void errprtlns(int... a) { if(DEBUG) { sb4prtln.setLength(0); for(int ele : a) { sb4prtln.append(errconvert(ele)); sb4prtln.append("\n"); } errprt(sb4prtln.toString()); } }
		final void errprtlns(long... a) { if(DEBUG) { sb4prtln.setLength(0); for(long ele : a) { sb4prtln.append(errconvert(ele)); sb4prtln.append("\n"); } errprt(sb4prtln.toString()); } }
		final void errprtlns(double... a) { if(DEBUG) { sb4prtln.setLength(0); for(double ele : a) { sb4prtln.append(ele); sb4prtln.append("\n"); } errprt(sb4prtln.toString()); } }
		final void errprtlns(String... a) { if(DEBUG) { sb4prtln.setLength(0); for(String ele : a) { sb4prtln.append(ele); sb4prtln.append("\n"); } errprt(sb4prtln.toString()); } }
		final void errprtlns(char... a) { if(DEBUG) { sb4prtln.setLength(0); for(char ele : a) { sb4prtln.append(ele); sb4prtln.append("\n"); } errprt(sb4prtln.toString()); } }
		final void errprtlns(boolean... a) { if(DEBUG) { sb4prtln.setLength(0); for(boolean ele : a) { sb4prtln.append(booleanToString(ele)); sb4prtln.append("\n"); } errprt(sb4prtln.toString()); } }
		final void errprtln(Object[] a) { if(DEBUG) for(Object ele : a) errprtln(ele); }
		final void errprtln(int[][] a) { if(DEBUG) for(int[] ele : a) errprtln(ele); }
		final void errprtln(long[][] a) { if(DEBUG) for(long[] ele : a) errprtln(ele); }
		final void errprtln(double[][] a) { if(DEBUG) for(double[] ele : a) errprtln(ele); }
		final void errprtln(String[][] a) { if(DEBUG) for(String[] ele : a) errprtln(ele); }
		final void errprtln(char[][] a) { if(DEBUG) for(char[] ele : a) errprtln(ele); }
		final void errprtln(boolean[][] a) { if(DEBUG) for(boolean[] ele : a) errprtln(ele); }
		final void errprtln(Object[][] a) { if(DEBUG) for(Object ele : a) { errprtln(ele); errprtln(); } }

		final void reply(boolean b) { prtln(b ? "Yes" : "No"); }
		final void REPLY(boolean b) { prtln(b ? "YES" : "NO"); }

		final void flush() { out.flush(); if(DEBUG) err.flush(); }
		final void assertion(boolean b) { if(!b) { flush(); throw new AssertionError(); } }
		final <T> void assertion(boolean b, T a) { if(!b) { errprtln(a); flush(); throw new AssertionError(); } }
		final void assertion(boolean b, int... a) { if(!b) { errprtln(a); flush(); throw new AssertionError(); } }
		final void assertion(boolean b, long... a) { if(!b) { errprtln(a); flush(); throw new AssertionError(); } }
		final void assertion(boolean b, double... a) { if(!b) { errprtln(a); flush(); throw new AssertionError(); } }
		final void assertion(boolean b, String... a) { if(!b) { errprtln(a); flush(); throw new AssertionError(); } }
		final void assertion(boolean b, char... a) { if(!b) { errprtln(a); flush(); throw new AssertionError(); } }
		final void assertion(boolean b, boolean... a) { if(!b) { errprtln(a); flush(); throw new AssertionError(); } }
		final void assertion(boolean b, int[][] a) { if(!b) { errprtln(a); flush(); throw new AssertionError(); } }
		final void assertion(boolean b, long[][] a) { if(!b) { errprtln(a); flush(); throw new AssertionError(); } }
		final void assertion(boolean b, double[][] a) { if(!b) { errprtln(a); flush(); throw new AssertionError(); } }
		final void assertion(boolean b, String[][] a) { if(!b) { errprtln(a); flush(); throw new AssertionError(); } }
		final void assertion(boolean b, char[][] a) { if(!b) { errprtln(a); flush(); throw new AssertionError(); } }
		final void assertion(boolean b, boolean[][] a) { if(!b) { errprtln(a); flush(); throw new AssertionError(); } }
		final void inclusiveRangeCheck(int i, int max) { inclusiveRangeCheck(i, 0, max); }
		final void inclusiveRangeCheck(int i, int min, int max) { rangeCheck(i, min, max + 1); }
		final void inclusiveRangeCheck(long i, long max) { inclusiveRangeCheck(i, 0, max); }
		final void inclusiveRangeCheck(long i, long min, long max) { rangeCheck(i, min, max + 1); }
		final void rangeCheck(int i, int max) { rangeCheck(i, 0, max); }
		final void rangeCheck(int i, int min, int max) { if(i < min || i >= max) throw new IndexOutOfBoundsException(String.format("Index %d out of bounds for length %d", i, max)); }
		final void rangeCheck(long i, long max) { rangeCheck(i, 0, max); }
		final void rangeCheck(long i, long min, long max) { if(i < min || i >= max) throw new IndexOutOfBoundsException(String.format("Index %d out of bounds for length %d", i, max)); }
		final void nonNegativeCheck(long x) { nonNegativeCheck(x, "the argument"); }
		final void nonNegativeCheck(long x, String attribute) { if(x < 0) throw new IllegalArgumentException(String.format("%s %d is negative", attribute, x)); }
		final void positiveCheck(long x) { positiveCheck(x, "the argument"); }
		final void positiveCheck(long x, String attribute) { if(x <= 0) throw new IllegalArgumentException(String.format("%s %d is negative", attribute, x)); }

		final void exit() { flush(); System.exit(0); }
		final <T> void exit(T a) { prtln(a); exit(); }
		final void exit(int... a) { prtln(a); exit(); }
		final void exit(long... a) { prtln(a); exit(); }
		final void exit(double... a) { prtln(a); exit(); }
		final void exit(String... a) { prtln(a); exit(); }
		final void exit(char... a) { prtln(a); exit(); }
		final void exit(boolean... a) { prtln(a); exit(); }
		final void exit(int[][] a) { prtln(a); exit(); }
		final void exit(long[][] a) { prtln(a); exit(); }
		final void exit(double[][] a) { prtln(a); exit(); }
		final void exit(String[][] a) { prtln(a); exit(); }
		final void exit(char[][] a) { prtln(a); exit(); }
		final void exit(boolean[][] a) { prtln(a); exit(); }


		final long INF = (long)1e18 + 7;
		final boolean isPlusINF(long a) { return a > INF / 10; }
		final boolean isMinusINF(long a) { return isPlusINF(- a); }
		final boolean isINF(long a) { return isPlusINF(a) || isMinusINF(a); }
		final int I_INF = (int)1e9 + 7;
		final boolean isPlusINF(int a) { return a > I_INF / 10; }
		final boolean isMinusINF(int a) { return isPlusINF(- a); }
		final boolean isINF(int a) { return isPlusINF(a) || isMinusINF(a); }


		final int min(int a, int b) { return Math.min(a, b); }
		final long min(long a, long b) { return Math.min(a, b); }
		final double min(double a, double b) { return Math.min(a, b); }
		final <T extends Comparable<T>> T min(T a, T b) { return a.compareTo(b) <= 0 ? a : b; }
		final int min(int... x) { int min = x[0]; for(int val : x) min = min(min, val); return min; }
		final long min(long... x) { long min = x[0]; for(long val : x) min = min(min, val); return min; }
		final double min(double... x) { double min = x[0]; for(double val : x) min = min(min, val); return min; }
		final int max(int a, int b) { return Math.max(a, b); }
		final long max(long a, long b) { return Math.max(a, b); }
		final double max(double a, double b) { return Math.max(a, b); }
		final <T extends Comparable<T>> T max(T a, T b) { return a.compareTo(b) >= 0 ? a : b; }
		final int max(int... x) { int max = x[0]; for(int val : x) max = max(max, val); return max; }
		final long max(long... x) { long max = x[0]; for(long val : x) max = max(max, val); return max; }
		final double max(double... x) { double max = x[0]; for(double val : x) max = max(max, val); return max; }
		final <T extends Comparable<T>> T max(T[] x) { T max = x[0]; for(T val : x) max = max(max, val); return max; }
		final int max(int[][] a) { int max = a[0][0]; for(int[] ele : a) max = max(max, max(ele)); return max; }
		final long max(long[][] a) { long max = a[0][0]; for(long[] ele : a) max = max(max, max(ele)); return max; }
		final double max(double[][] a) { double max = a[0][0]; for(double[] ele : a) max = max(max, max(ele)); return max; }
		final <T extends Comparable<T>> T max(T[][] a) { T max = a[0][0]; for(T[] ele : a) max = max(max, max(ele)); return max; }
		final long sum(int... a) { long sum = 0; for(int ele : a) sum += ele; return sum; }
		final long sum(long... a) { long sum = 0; for(long ele : a) sum += ele; return sum; }
		final double sum(double... a) { double sum = 0; for(double ele : a) sum += ele; return sum; }
		final long sum(boolean... a) { long sum = 0; for(boolean ele : a) sum += ele ? 1 : 0; return sum; }
		final long[] sums(int[] a) { long sum[] = new long[a.length + 1]; sum[0] = 0; for(int i = 0; i < a.length; i ++) sum[i + 1] = sum[i] + a[i]; return sum; }
		final long[] sums(long[] a) { long sum[] = new long[a.length + 1]; sum[0] = 0; for(int i = 0; i < a.length; i ++) sum[i + 1] = sum[i] + a[i]; return sum; }
		final double[] sums(double[] a) { double sum[] = new double[a.length + 1]; sum[0] = 0; for(int i = 0; i < a.length; i ++) sum[i + 1] = sum[i] + a[i]; return sum; }
		final long[] sums(boolean[] a) { long sum[] = new long[a.length + 1]; sum[0] = 0; for(int i = 0; i < a.length; i ++) sum[i + 1] = sum[i] + (a[i] ? 1 : 0); return sum; }
		final long[][] sums(int[][] a) {
			long sum[][] = new long[a.length + 1][a[0].length + 1];
			for(int i = 0; i < a.length; i ++) {
				for(int j = 0; j < a[i].length; j ++) sum[i + 1][j + 1] = sum[i + 1][j] + sum[i][j + 1] - sum[i][j] + a[i][j];
			}
			return sum;
		}
		final long[][] sums(long[][] a) {
			long sum[][] = new long[a.length + 1][a[0].length + 1];
			for(int i = 0; i < a.length; i ++) {
				for(int j = 0; j < a[i].length; j ++) sum[i + 1][j + 1] = sum[i + 1][j] + sum[i][j + 1] - sum[i][j] + a[i][j];
			}
			return sum;
		}
		final double[][] sums(double[][] a) {
			double sum[][] = new double[a.length + 1][a[0].length + 1];
			for(int i = 0; i < a.length; i ++) {
				for(int j = 0; j < a[i].length; j ++) sum[i + 1][j + 1] = sum[i + 1][j] + sum[i][j + 1] - sum[i][j] + a[i][j];
			}
			return sum;
		}
		final long[][] sums(boolean[][] a) {
			long sum[][] = new long[a.length + 1][a[0].length + 1];
			for(int i = 0; i < a.length; i ++) {
				for(int j = 0; j < a[i].length; j ++) sum[i + 1][j + 1] = sum[i + 1][j] + sum[i][j + 1] - sum[i][j] + (a[i][j] ? 1 : 0);
			}
			return sum;
		}
		final int constrain(int x, int l, int r) { return min(max(x, min(l, r)), max(l, r)); }
		final long constrain(long x, long l, long r) { return min(max(x, min(l, r)), max(l, r)); }
		final double constrain(double x, double l, double r) { return min(max(x, min(l, r)), max(l, r)); }
		final int abs(int x) { return x >= 0 ? x : - x; }
		final long abs(long x) { return x >= 0 ? x : - x; }
		final double abs(double x) { return x >= 0 ? x : - x; }
		final int signum(int x) { return x > 0 ? 1 : x < 0 ? -1 : 0; }
		final int signum(long x) { return x > 0 ? 1 : x < 0 ? -1 : 0; }
		final int signum(double x) { return x > 0 ? 1 : x < 0 ? -1 : 0; }
		final long round(double x) { return Math.round(x); }
		final long floor(double x) { return round(Math.floor(x)); }
		final int divfloor(int a, int b) { return signum(a) == signum(b) ? a / b : - divceil(abs(a), abs(b)); }
		final long divfloor(long a, long b) { return signum(a) == signum(b) ? a / b : - divceil(abs(a), abs(b)); }
		final long ceil(double x) { return round(Math.ceil(x)); }
		final int divceil(int a, int b) { return a >= 0 && b > 0 ? (a + b - 1) / b : a < 0 && b < 0 ? divceil(abs(a), abs(b)) : - divfloor(abs(a), abs(b)); }
		final long divceil(long a, long b) { return a >= 0 && b > 0 ? (a + b - 1) / b : a < 0 && b < 0 ? divceil(abs(a), abs(b)) : - divfloor(abs(a), abs(b)); }
		final boolean mulGreater(long a, long b, long c) { return b == 0 ? c < 0 : b < 0 ? mulLess(a, - b, - c) : a > divfloor(c, b); } // a * b > c
		final boolean mulGreaterEquals(long a, long b, long c) { return b == 0 ? c <= 0 : b < 0 ? mulLessEquals(a, - b, - c) : a >= divceil(c, b); } // a * b >= c
		final boolean mulLess(long a, long b, long c) { return !mulGreaterEquals(a, b, c); } // a * b < c
		final boolean mulLessEquals(long a, long b, long c) { return !mulGreater(a, b, c); } // a * b <= c
		final double sqrt(int x) { return Math.sqrt((double)x); }
		final double sqrt(long x) { return Math.sqrt((double)x); }
		final double sqrt(double x) { return Math.sqrt(x); }
		final int floorsqrt(int x) { int s = (int)floor(sqrt(x)) + 1; while(s * s > x) s --; return s; }
		final long floorsqrt(long x) { long s = floor(sqrt(x)) + 1; while(s * s > x) s --; return s; }
		final int ceilsqrt(int x) { int s = (int)ceil(sqrt(x)); while(s * s >= x) s --; s ++; return s; }
		final long ceilsqrt(long x) { long s = ceil(sqrt(x)); while(s * s >= x) s --; s ++; return s; }
		final long fact(int n) { long ans = 1; for(int i = 1; i <= n; i ++) ans = Math.multiplyExact(ans, i); return ans; }
		final long naiveP(long n, long r) { long ans = 1; for(int i = 0; i < r; i ++) ans = Math.multiplyExact(ans, n - i); return ans; }
		final long naiveC(long n, long r) { long ans = 1; for(int i = 0; i < r; i ++) { ans = Math.multiplyExact(ans, n - i); ans /= (i + 1); } return ans; }
		final double pow(double x, double y) { return Math.pow(x, y); }
		final long pow(long x, long y) {
			long ans = 1;
			while(true) {
				if((y & 1) != 0) ans = Math.multiplyExact(ans, x);
				y >>= 1;
				if(y <= 0) return ans;
				x = Math.multiplyExact(x, x);
			}
		}
		final double pow(double x, long y) {
			double ans = 1;
			while(true) {
				if((y & 1) != 0) ans *= x;
				y >>= 1;
				if(y <= 0) return ans;
				x *= x;
			}
		}
		final int gcd(int a, int b) { while(true) { if(b == 0) return a; int tmp = a; a = b; b = tmp % b; } }
		final long gcd(long a, long b) { while(true) { if(b == 0) return a; long tmp = a; a = b; b = tmp % b; } }
		final long lcm(long a, long b) { return a / gcd(a, b) * b; }
		final int gcd(int... a) { int gcd = 0; for(int i = 0; i < a.length; i ++) gcd = gcd(gcd, a[i]); return gcd; }
		final long gcd(long... a) { long gcd = 0; for(int i = 0; i < a.length; i ++) gcd = gcd(gcd, a[i]); return gcd; }
		final double random() { return Math.random(); }
		final int random(int max) { return (int)floor(random() * max); }
		final long random(long max) { return floor(random() * max); }
		final double random(double max) { return random() * max; }
		final int random(int min, int max) { return random(max - min) + min; }
		final long random(long min, long max) { return random(max - min) + min; }
		final double random(double min, double max) { return random(max - min) + min; }

		final boolean isUpper(char a) { return a >= 'A' && a <= 'Z'; }
		final boolean isLower(char a) { return a >= 'a' && a <= 'z'; }
		final int upperToInt(char a) { return a - 'A'; }
		final int lowerToInt(char a) { return a - 'a'; }
		final int numToInt(char a) { return a - '0'; }
		final int charToInt(char a) { return isLower(a) ? lowerToInt(a) : isUpper(a) ? upperToInt(a) : numToInt(a); }
		final int alphabetToInt(char a) { return isLower(a) ? lowerToInt(a) : isUpper(a) ? upperToInt(a) + 26 : 52; }
		final char intToUpper(int a) { return (char)(a + 'A'); }
		final char intToLower(int a) { return (char)(a + 'a'); }
		final char intToNum(int a) { return (char)(a + '0'); }
		final int[] charToInt(char[] a) { int array[] = new int[a.length]; for(int i = 0; i < a.length; i ++) array[i] = charToInt(a[i]); return array; }

		final long[] div(long a) {
			nonNegativeCheck(a);
			List<Long> divList = new ArrayList<>();
			for(long i = 1; i * i <= a; i ++) if(a % i == 0) { divList.add(i); if(i * i != a) divList.add(a / i); }
			long div[] = new long[divList.size()];
			for(int i = 0; i < divList.size(); i ++) div[i] = divList.get(i);
			Arrays.sort(div);
			return div;
		}

		final PairLL[] factor(long a) {
			nonNegativeCheck(a);
			List<PairLL> factorList = new ArrayList<>();
			for(long i = 2; i * i <= a; i ++) {
				if(a % i == 0) {
					long cnt = 0;
					while(a % i == 0) { a /= i; cnt ++; }
					factorList.add(new PairLL(i, cnt));
				}
			}
			if(a > 1) factorList.add(new PairLL(a, 1));
			PairLL factor[] = new PairLL[factorList.size()];
			for(int i = 0; i < factorList.size(); i ++) factor[i] = factorList.get(i);
			Arrays.sort(factor);
			return factor;
		}

		final boolean isPrime(long x) { boolean ok = x > 1; for(long i = 2; i * i <= x; i ++) { ok &= x % i != 0; if(!ok) return ok; } return ok; }
		final boolean[] prime(int num) {
			nonNegativeCheck(num);
			boolean prime[] = new boolean[num];
			fill(prime, true);
			if(num > 0) prime[0] = false;
			if(num > 1) prime[1] = false;
			for(int i = 2; i < num; i ++) if(prime[i]) for(int j = 2; i * j < num; j ++) prime[i * j] = false;
			return prime;
		}

		final PairIL[] countElements(int[] a, boolean sort) {
			int len = a.length;
			int array[] = new int[len];
			for(int i = 0; i < len; i ++) array[i] = a[i];
			if(sort) Arrays.sort(array);
			List<PairIL> cntsList = new ArrayList<>();
			long tmp = 1;
			for(int i = 1; i <= len; i ++) {
				if(i == len || array[i] != array[i - 1]) {
					cntsList.add(new PairIL(array[i - 1], tmp));
					tmp = 1;
				}else tmp ++;
			}
			PairIL cnts[] = new PairIL[cntsList.size()];
			for(int i = 0; i < cntsList.size(); i ++) cnts[i] = cntsList.get(i);
			return cnts;
		}
		final PairLL[] countElements(long[] a, boolean sort) {
			int len = a.length;
			long array[] = new long[len];
			for(int i = 0; i < len; i ++) array[i] = a[i];
			if(sort) Arrays.sort(array);
			List<PairLL> cntsList = new ArrayList<>();
			long tmp = 1;
			for(int i = 1; i <= len; i ++) {
				if(i == len || array[i] != array[i - 1]) {
					cntsList.add(new PairLL(array[i - 1], tmp));
					tmp = 1;
				}else tmp ++;
			}
			PairLL cnts[] = new PairLL[cntsList.size()];
			for(int i = 0; i < cntsList.size(); i ++) cnts[i] = cntsList.get(i);
			return cnts;
		}
		final PairIL[] countElements(String s, boolean sort) {
			int len = s.length();
			char array[] = s.toCharArray();
			if(sort) Arrays.sort(array);
			List<PairIL> cntsList = new ArrayList<>();
			long tmp = 1;
			for(int i = 1; i <= len; i ++) {
				if(i == len || array[i] != array[i - 1]) {
					cntsList.add(new PairIL((int)array[i - 1], tmp));
					tmp = 1;
				}else tmp ++;
			}
			PairIL cnts[] = new PairIL[cntsList.size()];
			for(int i = 0; i < cntsList.size(); i ++) cnts[i] = cntsList.get(i);
			return cnts;
		}

		long triangular(long n) { return n * (n + 1) / 2; }
		long arctriangularfloor(long m) {
			long n = (floor(sqrt(m * 8 + 1)) - 1) / 2 + 1;
			while(triangular(n) > m) n --;
			return n;
		}
		long arctriangularceil(long m) {
			long n = max(0, (ceil(sqrt(m * 8 + 1)) + 1) / 2 - 1);
			while(triangular(n) < m) n ++;
			return n;
		}

		final int[] baseConvert(long x, int n, int len) { nonNegativeCheck(x); nonNegativeCheck(n); nonNegativeCheck(len); int digit[] = new int[len]; int i = 0; long tmp = x; while(tmp > 0 && i < len) { digit[i ++] = (int)(tmp % n); tmp /= n; } return digit; }
		final int[] baseConvert(long x, int n) { nonNegativeCheck(x); nonNegativeCheck(n); long tmp = x; int len = 0; while(tmp > 0) { tmp /= n; len ++; } return baseConvert(x, n, len); }
		final int[] baseConvert(int x, int n, int len) { nonNegativeCheck(x); nonNegativeCheck(n); nonNegativeCheck(len); int digit[] = new int[len]; int i = 0; int tmp = x; while(tmp > 0 && i < len) { digit[i ++] = (int)(tmp % n); tmp /= n; } return digit; }
		final int[] baseConvert(int x, int n) { nonNegativeCheck(x); nonNegativeCheck(n); int tmp = x; int len = 0; while(tmp > 0) { tmp /= n; len ++; } return baseConvert(x, n, len); }
		final long[] baseConvert(long x, long n, int len) { nonNegativeCheck(x); nonNegativeCheck(n); nonNegativeCheck(len); long digit[] = new long[len]; int i = 0; long tmp = x; while(tmp > 0 && i < len) { digit[i ++] = tmp % n; tmp /= n; } return digit; }
		final long[] baseConvert(long x, long n) { nonNegativeCheck(x); nonNegativeCheck(n); long tmp = x; int len = 0; while(tmp > 0) { tmp /= n; len ++; } return baseConvert(x, n, len); }

		final int numDigits(long a) { nonNegativeCheck(a); return Long.toString(a).length(); }
		final long bitFlag(int a) { nonNegativeCheck(a); return 1L << (long)a; }
		final boolean isFlagged(long x, int a) { nonNegativeCheck(x); nonNegativeCheck(a); return (x & bitFlag(a)) != 0; }

		final long countString(String s, String a) { return (s.length() - s.replace(a, "").length()) / a.length(); }
		final long countStringAll(String s, String a) { return s.length() - s.replaceAll(a, "").length(); }


		final String reverse(String s) { return (new StringBuilder(s)).reverse().toString(); }
		final String[] reverse(String[] a) { for(int i = 0; i < a.length / 2; i ++) swap(a, i, a.length - i - 1); return a; }
		final int[] reverse(int[] a) { for(int i = 0; i < a.length / 2; i ++) swap(a, i, a.length - i - 1); return a; }
		final long[] reverse(long[] a) { for(int i = 0; i < a.length / 2; i ++) swap(a, i, a.length - i - 1); return a; }
		final double[] reverse(double[] a) { for(int i = 0; i < a.length / 2; i ++) swap(a, i, a.length - i - 1); return a; }
		final char[] reverse(char[] a) { for(int i = 0; i < a.length / 2; i ++) swap(a, i, a.length - i - 1); return a; }
		final boolean[] reverse(boolean[] a) { for(int i = 0; i < a.length / 2; i ++) swap(a, i, a.length - i - 1); return a; }
		final <T> T[] reverse(T[] a) { for(int i = 0; i < a.length / 2; i ++) swap(a, i, a.length - i - 1); return a; }
		final void fill(int[] a, int x) { Arrays.fill(a, x); }
		final void fill(long[] a, long x) { Arrays.fill(a, x); }
		final void fill(double[] a, double x) { Arrays.fill(a, x); }
		final void fill(char[] a, char x) { Arrays.fill(a, x); }
		final void fill(boolean[] a, boolean x) { Arrays.fill(a, x); }
		final void fill(int[][] a, int x) { for(int[] ele : a) fill(ele, x); }
		final void fill(long[][] a, long x) { for(long[] ele : a) fill(ele, x); }
		final void fill(double[][] a, double x) { for(double[] ele : a) fill(ele, x); }
		final void fill(char[][] a, char x) { for(char[] ele : a) fill(ele, x); }
		final void fill(boolean[][] a, boolean x) { for(boolean[] ele : a) fill(ele, x); }
		final void fill(int[][][] a, int x) { for(int[][] ele : a) fill(ele, x); }
		final void fill(long[][][] a, long x) { for(long[][] ele : a) fill(ele, x); }
		final void fill(double[][][] a, double x) { for(double[][] ele : a) fill(ele, x); }
		final void fill(char[][][] a, char x) { for(char[][] ele : a) fill(ele, x); }
		final void fill(boolean[][][] a, boolean x) { for(boolean[][] ele : a) fill(ele, x); }

		final int[] resize(int[] a, int m, int x) { nonNegativeCheck(m); int resized[] = new int[m]; for(int i = max(0, - x); i < a.length && i + x < m; i ++) resized[i + x] = a[i]; return resized; }
		final long[] resize(long[] a, int m, int x) { nonNegativeCheck(m); long resized[] = new long[m]; for(int i = max(0, - x); i < a.length && i + x < m; i ++) resized[i + x] = a[i]; return resized; }
		final double[] resize(double[] a, int m, int x) { nonNegativeCheck(m); double resized[] = new double[m]; for(int i = max(0, - x); i < a.length && i + x < m; i ++) resized[i + x] = a[i]; return resized; }
		final char[] resize(char[] a, int m, int x) { nonNegativeCheck(m); char resized[] = new char[m]; for(int i = max(0, - x); i < a.length && i + x < m; i ++) resized[i + x] = a[i]; return resized; }
		final boolean[] resize(boolean[] a, int m, int x) { nonNegativeCheck(m); boolean resized[] = new boolean[m]; for(int i = max(0, - x); i < a.length && i + x < m; i ++) resized[i + x] = a[i]; return resized; }
		final Object[] resize(Object[] a, int m, int x) { nonNegativeCheck(m); Object resized[] = new Object[m]; for(int i = max(0, - x); i < a.length && i + x < m; i ++) resized[i + x] = a[i]; return resized; }

		final int[] toIntArray(List<Integer> list) { int a[] = new int[list.size()]; int idx = 0; for(int ele : list) a[idx ++] = ele; return a; }
		final long[] toLongArray(List<Long> list) { long a[] = new long[list.size()]; int idx = 0; for(long ele : list) a[idx ++] = ele; return a; }
		final double[] toDoubleArray(List<Double> list) { double a[] = new double[list.size()]; int idx = 0; for(double ele : list) a[idx ++] = ele; return a; }
		final char[] toCharArray(List<Character> list) { char a[] = new char[list.size()]; int idx = 0; for(char ele : list) a[idx ++] = ele; return a; }
		final boolean[] toBooleanArray(List<Boolean> list) { boolean a[] = new boolean[list.size()]; int idx = 0; for(boolean ele : list) a[idx ++] = ele; return a; }
		final String[] toStringArray(List<String> list) { String a[] = new String[list.size()]; int idx = 0; for(String ele : list) a[idx ++] = ele; return a; }
		final <T> void toArray(List<T> list, T a[]) { int idx = 0; for(T ele : list) a[idx ++] = ele; }

		final void shuffleArray(int[] a) { for(int i = 0; i < a.length; i ++){ int tmp = a[i]; int idx = random(i, a.length); a[i] = a[idx]; a[idx] = tmp; } }
		final void shuffleArray(long[] a) { for(int i = 0; i < a.length; i ++){ long tmp = a[i]; int idx = random(i, a.length); a[i] = a[idx]; a[idx] = tmp; } }
		final void shuffleArray(double[] a) { for(int i = 0; i < a.length; i ++){ double tmp = a[i]; int idx = random(i, a.length); a[i] = a[idx]; a[idx] = tmp; } }
		final int[] randomi(int n, int max) { nonNegativeCheck(n); int a[] = new int[n]; for(int i = 0; i < n; i ++) a[i] = random(max); return a; }
		final long[] randoml(int n, long max) { nonNegativeCheck(n); long a[] = new long[n]; for(int i = 0; i < n; i ++) a[i] = random(max); return a; }
		final double[] randomd(int n, double max) { nonNegativeCheck(n); double a[] = new double[n]; for(int i = 0; i < n; i ++) a[i] = random(max); return a; }
		final int[] randomi(int n, int min, int max) { nonNegativeCheck(n); int a[] = new int[n]; for(int i = 0; i < n; i ++) a[i] = random(min, max); return a; }
		final long[] randoml(int n, long min, long max) { nonNegativeCheck(n); long a[] = new long[n]; for(int i = 0; i < n; i ++) a[i] = random(min, max); return a; }
		final double[] randomd(int n, double min, double max) { nonNegativeCheck(n); double a[] = new double[n]; for(int i = 0; i < n; i ++) a[i] = random(min, max); return a; }

		final void swap(String[] a, int i, int j) { rangeCheck(i, a.length); rangeCheck(j, a.length); String tmp = a[i]; a[i] = a[j]; a[j] = tmp; }
		final void swap(int[] a, int i, int j) { rangeCheck(i, a.length); rangeCheck(j, a.length); int tmp = a[i]; a[i] = a[j]; a[j] = tmp; }
		final void swap(long[] a, int i, int j) { rangeCheck(i, a.length); rangeCheck(j, a.length); long tmp = a[i]; a[i] = a[j]; a[j] = tmp; }
		final void swap(double[] a, int i, int j) { rangeCheck(i, a.length); rangeCheck(j, a.length); double tmp = a[i]; a[i] = a[j]; a[j] = tmp; }
		final void swap(char[] a, int i, int j) { rangeCheck(i, a.length); rangeCheck(j, a.length); char tmp = a[i]; a[i] = a[j]; a[j] = tmp; }
		final void swap(boolean[] a, int i, int j) { rangeCheck(i, a.length); rangeCheck(j, a.length); boolean tmp = a[i]; a[i] = a[j]; a[j] = tmp; }
		final <T> void swap(T[] a, int i, int j) { rangeCheck(i, a.length); rangeCheck(j, a.length); T tmp = a[i]; a[i] = a[j]; a[j] = tmp; }

		final int[][] rotate(int[][] a) {
			int[][] ans = new int[a[0].length][a.length];
			for(int i = 0; i < a.length; i ++) for(int j = 0; j < a[i].length; j ++) ans[j][a.length - i - 1] = a[i][j];
			return ans;
		}
		final long[][] rotate(long[][] a) {
			long[][] ans = new long[a[0].length][a.length];
			for(int i = 0; i < a.length; i ++) for(int j = 0; j < a[i].length; j ++) ans[j][a.length - i - 1] = a[i][j];
			return ans;
		}
		final double[][] rotate(double[][] a) {
			double[][] ans = new double[a[0].length][a.length];
			for(int i = 0; i < a.length; i ++) for(int j = 0; j < a[i].length; j ++) ans[j][a.length - i - 1] = a[i][j];
			return ans;
		}
		final char[][] rotate(char[][] a) {
			char[][] ans = new char[a[0].length][a.length];
			for(int i = 0; i < a.length; i ++) for(int j = 0; j < a[i].length; j ++) ans[j][a.length - i - 1] = a[i][j];
			return ans;
		}
		final boolean[][] rotate(boolean[][] a) {
			boolean[][] ans = new boolean[a[0].length][a.length];
			for(int i = 0; i < a.length; i ++) for(int j = 0; j < a[i].length; j ++) ans[j][a.length - i - 1] = a[i][j];
			return ans;
		}
		final Object[][] rotate(Object[][] a) {
			Object[][] ans = new Object[a[0].length][a.length];
			for(int i = 0; i < a.length; i ++) for(int j = 0; j < a[i].length; j ++) ans[j][a.length - i - 1] = a[i][j];
			return ans;
		}

		final int[] compress(int[] a) {
			int n = a.length;
			Set<Integer> ts = new TreeSet<>();
			for(int i = 0; i < n; i ++) ts.add(a[i]);
			int compressed[] = new int[ts.size()];
			int j = 0;
			for(int x : ts) compressed[j ++] = x;
			for(int i = 0; i < n; i ++) a[i] = lowerBound(compressed, a[i]);
			return compressed;
		}
		final long[] compress(long[] a) {
			int n = a.length;
			Set<Long> ts = new TreeSet<>();
			for(int i = 0; i < n; i ++) ts.add(a[i]);
			long compressed[] = new long[ts.size()];
			int j = 0;
			for(long x : ts) compressed[j ++] = x;
			for(int i = 0; i < n; i ++) a[i] = lowerBound(compressed, a[i]);
			return compressed;
		}
		final double[] compress(double[] a) {
			int n = a.length;
			Set<Double> ts = new TreeSet<>();
			for(int i = 0; i < n; i ++) ts.add(a[i]);
			double compressed[] = new double[ts.size()];
			int j = 0;
			for(double x : ts) compressed[j ++] = x;
			for(int i = 0; i < n; i ++) a[i] = lowerBound(compressed, a[i]);
			return compressed;
		}


		final int lowerBound(int[] a, int key) { return BS(a, key, true, true, true); }
		final int lowerBound(int[] a, int key, int ng, int ok) { return BS(a, key, true, true, true, ng, ok); }
		final int upperBound(int[] a, int key) { return BS(a, key, true, true, false); }
		final int upperBound(int[] a, int key, int ng, int ok) { return BS(a, key, true, true, false, ng, ok); }
		final int cntBS(int[] a, int key, boolean asc, boolean gt, boolean eq) { return BS(a, key, asc, gt, eq, true); }
		final int cntBS(int[] a, int key, boolean asc, boolean gt, boolean eq, int ng, int ok) { return BS(a, key, asc, gt, eq, true, ng, ok); }
		final int BS(int[] a, int key, boolean asc, boolean gt, boolean eq) { return BS(a, key, asc, gt, eq, false); }
		final int BS(int[] a, int key, boolean asc, boolean gt, boolean eq, int ng, int ok) { return BS(a, key, asc, gt, eq, false, ng, ok); }
		final int BS(int[] a, int key, boolean asc, boolean gt, boolean eq, boolean cnt) { return BS(a, key, asc, gt, eq, cnt, asc ^ gt ? a.length : -1, asc ^ gt ? -1 : a.length); }
		final int BS(int[] a, int key, boolean asc, boolean gt, boolean eq, boolean cnt, int ng, int ok) { int index = binarySearch(a, key, gt, eq, ng, ok); return cnt ? (int)abs(ok - index) : index; }
		final int binarySearch(int[] a, int key, boolean gt, boolean eq, int ng, int ok) { while (abs(ok - ng) > 1) { int mid = (ok + ng) / 2; if(isOKforBS(a, mid, key, gt, eq)) ok = mid; else ng = mid; } return ok; }
		final boolean isOKforBS(int[] a, int index, int key, boolean gt, boolean eq) { return (a[index] > key && gt) || (a[index] < key && !gt) || (a[index] == key && eq); }
		final int lowerBound(long[] a, long key) { return BS(a, key, true, true, true); }
		final int lowerBound(long[] a, long key, int ng, int ok) { return BS(a, key, true, true, true, ng, ok); }
		final int upperBound(long[] a, long key) { return BS(a, key, true, true, false); }
		final int upperBound(long[] a, long key, int ng, int ok) { return BS(a, key, true, true, false, ng, ok); }
		final int cntBS(long[] a, long key, boolean asc, boolean gt, boolean eq) { return BS(a, key, asc, gt, eq, true); }
		final int cntBS(long[] a, long key, boolean asc, boolean gt, boolean eq, int ng, int ok) { return BS(a, key, asc, gt, eq, true, ng, ok); }
		final int BS(long[] a, long key, boolean asc, boolean gt, boolean eq) { return BS(a, key, asc, gt, eq, false); }
		final int BS(long[] a, long key, boolean asc, boolean gt, boolean eq, int ng, int ok) { return BS(a, key, asc, gt, eq, false, ng, ok); }
		final int BS(long[] a, long key, boolean asc, boolean gt, boolean eq, boolean cnt) { return BS(a, key, asc, gt, eq, cnt, asc ^ gt ? a.length : -1, asc ^ gt ? -1 : a.length); }
		final int BS(long[] a, long key, boolean asc, boolean gt, boolean eq, boolean cnt, int ng, int ok) { int index = binarySearch(a, key, gt, eq, ng, ok); return cnt ? (int)abs(ok - index) : index; }
		final int binarySearch(long[] a, long key, boolean gt, boolean eq, int ng, int ok) { while (abs(ok - ng) > 1) { int mid = (ok + ng) / 2; if(isOKforBS(a, mid, key, gt, eq)) ok = mid; else ng = mid; } return ok; }
		final boolean isOKforBS(long[] a, int index, long key, boolean gt, boolean eq) { return (a[index] > key && gt) || (a[index] < key && !gt) || (a[index] == key && eq); }
		final int lowerBound(double[] a, double key) { return BS(a, key, true, true, true); }
		final int lowerBound(double[] a, double key, int ng, int ok) { return BS(a, key, true, true, true, ng, ok); }
		final int upperBound(double[] a, double key) { return BS(a, key, true, true, false); }
		final int upperBound(double[] a, double key, int ng, int ok) { return BS(a, key, true, true, false, ng, ok); }
		final int cntBS(double[] a, double key, boolean asc, boolean gt, boolean eq) { return BS(a, key, asc, gt, eq, true); }
		final int cntBS(double[] a, double key, boolean asc, boolean gt, boolean eq, int ng, int ok) { return BS(a, key, asc, gt, eq, true, ng, ok); }
		final int BS(double[] a, double key, boolean asc, boolean gt, boolean eq) { return BS(a, key, asc, gt, eq, false); }
		final int BS(double[] a, double key, boolean asc, boolean gt, boolean eq, int ng, int ok) { return BS(a, key, asc, gt, eq, false, ng, ok); }
		final int BS(double[] a, double key, boolean asc, boolean gt, boolean eq, boolean cnt) { return BS(a, key, asc, gt, eq, cnt, asc ^ gt ? a.length : -1, asc ^ gt ? -1 : a.length); }
		final int BS(double[] a, double key, boolean asc, boolean gt, boolean eq, boolean cnt, int ng, int ok) { int index = binarySearch(a, key, gt, eq, ng, ok); return cnt ? (int)abs(ok - index) : index; }
		final int binarySearch(double[] a, double key, boolean gt, boolean eq, int ng, int ok) { while (abs(ok - ng) > 1) { int mid = (ok + ng) / 2; if(isOKforBS(a, mid, key, gt, eq)) ok = mid; else ng = mid; } return ok; }
		final boolean isOKforBS(double[] a, int index, double key, boolean gt, boolean eq) { return (a[index] > key && gt) || (a[index] < key && !gt) || (a[index] == key && eq); }
		final <T extends Comparable<? super T>> int lowerBound(T[] a, T key) { return BS(a, key, true, true, true); }
		final <T extends Comparable<? super T>> int lowerBound(T[] a, T key, int ng, int ok) { return BS(a, key, true, true, true, ng, ok); }
		final <T extends Comparable<? super T>> int upperBound(T[] a, T key) { return BS(a, key, true, true, false); }
		final <T extends Comparable<? super T>> int upperBound(T[] a, T key, int ng, int ok) { return BS(a, key, true, true, false, ng, ok); }
		final <T extends Comparable<? super T>> int cntBS(T[] a, T key, boolean asc, boolean gt, boolean eq) { return BS(a, key, asc, gt, eq, true); }
		final <T extends Comparable<? super T>> int cntBS(T[] a, T key, boolean asc, boolean gt, boolean eq, int ng, int ok) { return BS(a, key, asc, gt, eq, true, ng, ok); }
		final <T extends Comparable<? super T>> int BS(T[] a, T key, boolean asc, boolean gt, boolean eq) { return BS(a, key, asc, gt, eq, false); }
		final <T extends Comparable<? super T>> int BS(T[] a, T key, boolean asc, boolean gt, boolean eq, int ng, int ok) { return BS(a, key, asc, gt, eq, false, ng, ok); }
		final <T extends Comparable<? super T>> int BS(T[] a, T key, boolean asc, boolean gt, boolean eq, boolean cnt) { return BS(a, key, asc, gt, eq, cnt, asc ^ gt ? a.length : -1, asc ^ gt ? -1 : a.length); }
		final <T extends Comparable<? super T>> int BS(T[] a, T key, boolean asc, boolean gt, boolean eq, boolean cnt, int ng, int ok) { int index = binarySearch(a, key, gt, eq, ng, ok); return cnt ? (int)abs(ok - index) : index; }
		final <T extends Comparable<? super T>> int binarySearch(T[] a, T key, boolean gt, boolean eq, int ng, int ok) { while (abs(ok - ng) > 1) { int mid = (ok + ng) / 2; if(isOKforBS(a, mid, key, gt, eq)) ok = mid; else ng = mid; } return ok; }
		final <T extends Comparable<? super T>> boolean isOKforBS(T[] a, int index, T key, boolean gt, boolean eq) { int compare = a[index].compareTo(key); return (compare > 0 && gt) || (compare < 0 && !gt) || (compare == 0 && eq); }
		final <T> int lowerBound(T[] a, T key, Comparator<? super T> c) { return BS(a, key, true, true, true, c); }
		final <T> int lowerBound(T[] a, T key, int ng, int ok, Comparator<? super T> c) { return BS(a, key, true, true, true, ng, ok, c); }
		final <T> int upperBound(T[] a, T key, Comparator<? super T> c) { return BS(a, key, true, true, false, c); }
		final <T> int upperBound(T[] a, T key, int ng, int ok, Comparator<? super T> c) { return BS(a, key, true, true, false, ng, ok, c); }
		final <T> int cntBS(T[] a, T key, boolean asc, boolean gt, boolean eq, Comparator<? super T> c) { return BS(a, key, asc, gt, eq, true, c); }
		final <T> int cntBS(T[] a, T key, boolean asc, boolean gt, boolean eq, int ng, int ok, Comparator<? super T> c) { return BS(a, key, asc, gt, eq, true, ng, ok, c); }
		final <T> int BS(T[] a, T key, boolean asc, boolean gt, boolean eq, Comparator<? super T> c) { return BS(a, key, asc, gt, eq, false, c); }
		final <T> int BS(T[] a, T key, boolean asc, boolean gt, boolean eq, int ng, int ok, Comparator<? super T> c) { return BS(a, key, asc, gt, eq, false, ng, ok, c); }
		final <T> int BS(T[] a, T key, boolean asc, boolean gt, boolean eq, boolean cnt, Comparator<? super T> c) { return BS(a, key, asc, gt, eq, cnt, asc ^ gt ? a.length : -1, asc ^ gt ? -1 : a.length, c); }
		final <T> int BS(T[] a, T key, boolean asc, boolean gt, boolean eq, boolean cnt, int ng, int ok, Comparator<? super T> c) { int index = binarySearch(a, key, gt, eq, ng, ok, c); return cnt ? (int)abs(ok - index) : index; }
		final <T> int binarySearch(T[] a, T key, boolean gt, boolean eq, int ng, int ok, Comparator<? super T> c) { while (abs(ok - ng) > 1) { int mid = (ok + ng) / 2; if(isOKforBS(a, mid, key, gt, eq, c)) ok = mid; else ng = mid; } return ok; }
		final <T> boolean isOKforBS(T[] a, int index, T key, boolean gt, boolean eq, Comparator<? super T> c) { int compare = c.compare(a[index], key); return (compare > 0 && gt) || (compare < 0 && !gt) || (compare == 0 && eq); }
		final <T extends Comparable<? super T>> int lowerBound(List<T> a, T key) { return BS(a, key, true, true, true); }
		final <T extends Comparable<? super T>> int lowerBound(List<T> a, T key, int ng, int ok) { return BS(a, key, true, true, true, ng, ok); }
		final <T extends Comparable<? super T>> int upperBound(List<T> a, T key) { return BS(a, key, true, true, false); }
		final <T extends Comparable<? super T>> int upperBound(List<T> a, T key, int ng, int ok) { return BS(a, key, true, true, false, ng, ok); }
		final <T extends Comparable<? super T>> int cntBS(List<T> a, T key, boolean asc, boolean gt, boolean eq) { return BS(a, key, asc, gt, eq, true); }
		final <T extends Comparable<? super T>> int cntBS(List<T> a, T key, boolean asc, boolean gt, boolean eq, int ng, int ok) { return BS(a, key, asc, gt, eq, true, ng, ok); }
		final <T extends Comparable<? super T>> int BS(List<T> a, T key, boolean asc, boolean gt, boolean eq) { return BS(a, key, asc, gt, eq, false); }
		final <T extends Comparable<? super T>> int BS(List<T> a, T key, boolean asc, boolean gt, boolean eq, int ng, int ok) { return BS(a, key, asc, gt, eq, false, ng, ok); }
		final <T extends Comparable<? super T>> int BS(List<T> a, T key, boolean asc, boolean gt, boolean eq, boolean cnt) { return BS(a, key, asc, gt, eq, cnt, asc ^ gt ? a.size() : -1, asc ^ gt ? -1 : a.size()); }
		final <T extends Comparable<? super T>> int BS(List<T> a, T key, boolean asc, boolean gt, boolean eq, boolean cnt, int ng, int ok) { int index = binarySearch(a, key, gt, eq, ng, ok); return cnt ? (int)abs(ok - index) : index; }
		final <T extends Comparable<? super T>> int binarySearch(List<T> a, T key, boolean gt, boolean eq, int ng, int ok) { while (abs(ok - ng) > 1) { int mid = (ok + ng) / 2; if(isOKforBS(a, mid, key, gt, eq)) ok = mid; else ng = mid; } return ok; }
		final <T extends Comparable<? super T>> boolean isOKforBS(List<T> a, int index, T key, boolean gt, boolean eq) { int compare = a.get(index).compareTo(key); return (compare > 0 && gt) || (compare < 0 && !gt) || (compare == 0 && eq); }
		final <T> int lowerBound(List<T> a, T key, Comparator<? super T> c) { return BS(a, key, true, true, true, c); }
		final <T> int lowerBound(List<T> a, T key, int ng, int ok, Comparator<? super T> c) { return BS(a, key, true, true, true, ng, ok, c); }
		final <T> int upperBound(List<T> a, T key, Comparator<? super T> c) { return BS(a, key, true, true, false, c); }
		final <T> int upperBound(List<T> a, T key, int ng, int ok, Comparator<? super T> c) { return BS(a, key, true, true, false, ng, ok, c); }
		final <T> int cntBS(List<T> a, T key, boolean asc, boolean gt, boolean eq, Comparator<? super T> c) { return BS(a, key, asc, gt, eq, true, c); }
		final <T> int cntBS(List<T> a, T key, boolean asc, boolean gt, boolean eq, int ng, int ok, Comparator<? super T> c) { return BS(a, key, asc, gt, eq, true, ng, ok, c); }
		final <T> int BS(List<T> a, T key, boolean asc, boolean gt, boolean eq, Comparator<? super T> c) { return BS(a, key, asc, gt, eq, false, c); }
		final <T> int BS(List<T> a, T key, boolean asc, boolean gt, boolean eq, int ng, int ok, Comparator<? super T> c) { return BS(a, key, asc, gt, eq, false, ng, ok, c); }
		final <T> int BS(List<T> a, T key, boolean asc, boolean gt, boolean eq, boolean cnt, Comparator<? super T> c) { return BS(a, key, asc, gt, eq, cnt, asc ^ gt ? a.size() : -1, asc ^ gt ? -1 : a.size(), c); }
		final <T> int BS(List<T> a, T key, boolean asc, boolean gt, boolean eq, boolean cnt, int ng, int ok, Comparator<? super T> c) { int index = binarySearch(a, key, gt, eq, ng, ok, c); return cnt ? (int)abs(ok - index) : index; }
		final <T> int binarySearch(List<T> a, T key, boolean gt, boolean eq, int ng, int ok, Comparator<? super T> c) { while (abs(ok - ng) > 1) { int mid = (ok + ng) / 2; if(isOKforBS(a, mid, key, gt, eq, c)) ok = mid; else ng = mid; } return ok; }
		final <T> boolean isOKforBS(List<T> a, int index, T key, boolean gt, boolean eq, Comparator<? super T> c) { int compare = c.compare(a.get(index), key); return (compare > 0 && gt) || (compare < 0 && !gt) || (compare == 0 && eq); }

		final PairLL binaryRangeSearch(long left, long right, UnaryOperator<Long> op, boolean minimize) {
			long ok1 = right, ng1 = left;
			while(abs(ok1 - ng1) > 1) {
				long mid = (ok1 + ng1) / 2;
				boolean isOK = (op.apply(mid + 1) - op.apply(mid)) * (minimize ? 1 : -1) >= 0;
				if(isOK) ok1 = mid; else ng1 = mid;
			}
			long ok2 = left, ng2 = right;
			while(abs(ok2 - ng2) > 1) {
				long mid = (ok2 + ng2) / 2;
				boolean isOK = (op.apply(mid - 1) - op.apply(mid)) * (minimize ? 1 : -1) >= 0;
				if(isOK) ok2 = mid; else ng2 = mid;
			}
			return new PairLL(ok1, ok2); //[l, r]
		}

		final double ternarySearch(double left, double right, UnaryOperator<Double> op, boolean minimize, int loop) {
			for(int cnt = 0; cnt < loop; cnt ++) {
				double m1 = (left * 2 + right) / 3.0;
				double m2 = (left + right * 2) / 3.0;
				if(op.apply(m1) > op.apply(m2) ^ minimize) right = m2; else left = m1;
			}
			return (left + right) / 2.0;
		}


		// mods
		Mod md = new Mod();
		// class Mod107 extends Mod { Mod107() { super(1_000_000_007); } } Mod107 md = new Mod107();
		// class Mod998 extends Mod { Mod998() { super(998244353); } } Mod998 md = new Mod998();
		class Mod {
			final long MOD = 1_000_000_007; Mod() { MH = 0; ML = 0; IS_MOD_CONST = true; }
			// final long MOD = 998244353; Mod() { MH = 0; ML = 0; IS_MOD_CONST = true; }
			// final long MOD; Mod(long mod) { MOD = mod; IS_MOD_CONST = false; long a = (1l << 32) / MOD; long b = (1l << 32) % MOD; long m = a * a * MOD + 2 * a * b + (b * b) / MOD; MH = m >>> 32; ML = m & MASK; }

			static final long MASK = 0xffff_ffffl;
			final long MH;
			final long ML;
			final boolean IS_MOD_CONST;

			final long reduce(long x) {
				if(MOD == 1) return 0;
				if(x < 0) return (x = reduce(- x)) == 0 ? 0 : MOD - x;
				long z = (x & MASK) * ML;
				z = (x & MASK) * MH + (x >>> 32) * ML + (z >>> 32);
				z = (x >>> 32) * MH + (z >>> 32);
				x -= z * MOD;
				return x < MOD ? x : x - MOD;
			}

			final long mod(long x) {
				if(0 <= x && x < MOD) return x;
				if(- MOD <= x && x < 0) return x + MOD;
				return IS_MOD_CONST ? ((x %= MOD) < 0 ? x + MOD : x) : reduce(x);
			}
			final long[] mod(long[] a) { for(int i = 0; i < a.length; i ++) a[i] = mod(a[i]); return a; }
			final long[][] mod(long[][] a) { for(int i = 0; i < a.length; i ++) mod(a[i]); return a; }
			final long[][][] mod(long[][][] a) { for(int i = 0; i < a.length; i ++) mod(a[i]); return a; }

			final long add(long x, long y) { return (x += y) >= MOD * 2 || x < 0 ? mod(x) : x >= MOD ? x - MOD : x; }
			final long sum(long... x) { long sum = 0; for(long ele : x) sum = add(sum, ele); return sum; }
			final long sub(long x, long y) { return (x -= y) < - MOD || x >= MOD ? mod(x) : x < 0 ? x + MOD : x; }
			final long pow(long x, long y) {
				nonNegativeCheck(y);
				x = mod(x);
				long ans = 1;
				for(; y > 0; y >>= 1) {
					if((y & 1) != 0) ans = mul(ans, x);
					x = mul(x, x);
				}
				return ans;
			}
			final long mul(long x, long y) {
				if(x >= 0 && x < MOD && y >= 0 && y < MOD) return IS_MOD_CONST ? (x * y) % MOD : reduce(x * y);
				x = mod(x);
				y = mod(y);
				return IS_MOD_CONST ? (x * y) % MOD : reduce(x * y);
			}
			final long mul(long... x) { long ans = 1; for(long ele : x) ans = mul(ans, ele); return ans; }
			final long div(long x, long y) { return mul(x, inv(y)); }

			final long[] pows(long x, int max) {
				x = mod(x);
				long pow[] = new long[max + 1];
				pow[0] = 1;
				for(int i = 0; i < max; i ++) pow[i + 1] = mul(pow[i], x);
				return pow;
			}
			final long fact(int n) {
				nonNegativeCheck(n);
				prepareFact();
				if(n < MAX_FACT1) return fact[n];
				else {
					long ans = fact[MAX_FACT1 - 1];
					for(int i = MAX_FACT1; i <= n; i ++) ans = mul(ans, i);
					return ans;
				}
			}
			final long invFact(int n) {
				nonNegativeCheck(n);
				prepareFact();
				if(n < MAX_FACT1) return invFact[n];
				else return inv(fact(n));
			}

			static final int MAX_INV_SIZE = 100_100;
			final Map<Long, Long> invMap = new HashMap<>();
			final long inv(long x) {
				x = mod(x);
				if(invMap.containsKey(x)) return invMap.get(x);
				if(invMap.size() >= MAX_INV_SIZE) return calInv(x);
				invMap.put(x, calInv(x));
				return invMap.get(x);
			}
			final long calInv(long x) { // O(logM)
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
			final long[] invs(int n) { // O(N)
				positiveCheck(n);
				long inv[] = new long[n + 1];
				inv[1] = 1;
				for(int i = 2; i <= n; i ++) inv[i] = mul(inv[(int)(MOD % i)], (MOD - MOD / i));
				return inv;
			}

			static final int MAX_FACT1 = 5_000_100;
			static final int MAX_FACT2 = 500_100;
			static final int MAX_FACT_MAP_SIZE = 100;
			long fact[];
			long invFact[];
			boolean isFactPrepared = false;
			final Map<Long, long[]> factMap = new HashMap<>();
			final void prepareFact() {
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

			final long P(long n, long r) {
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
			final long C(long n, long r) {
				if(!isFactPrepared) prepareFact();
				if(n < 0) return mod(C(- n + r - 1, - n - 1) * ((r & 1) == 0 ? 1 : -1));
				if(r < 0 || n < r) return 0;
				r = min(r, n - r);
				if(n < MOD) return mul(P(n, r), r < MAX_FACT1 ? invFact[(int)r] : inv(fact((int)r)));

				long digitN[] = baseConvert(n, MOD);
				long digitR[] = baseConvert(r, MOD);
				int len = digitN.length;
				digitR = resize(digitR, len, 0);
				long ans = 1;
				for(int i = 0; i < len; i ++) ans = mul(ans, C(digitN[i], digitR[i]));
				return ans;
			}
			final long H(long n, long r) { return C(n - 1 + r, r); }

			final long sqrt(long x) {
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
		final long mod(long x, long mod) {
			if(0 <= x && x < mod) return x;
			if(- mod <= x && x < 0) return x + mod;
			return (x %= mod) < 0 ? x + mod : x;
		}
		final long pow(long x, long y, long mod) {
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
		class Grids {
			int h, w;
			Grid[][] gs;
			Grid[] gi;
			Grids(int h, int w) {
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

			void init(boolean[][] b) { for(int i = 0; i < h; i ++) for(int j = 0; j < w; j ++) gs[i][j].b = b[i][j]; }
			void init(long[][] val) { for(int i = 0; i < h; i ++) for(int j = 0; j < w; j ++) gs[i][j].val = val[i][j]; }

			Grid get(int x, int y) { return isValid(x, y, h, w) ? gs[x][y] : null; }
			Grid get(int i) { return get(i / w, i % w); }

			int dx[] = {0, -1, 1, 0, 0, -1, 1, -1, 1};
			int dy[] = {0, 0, 0, -1, 1, -1, -1, 1, 1};
			Grid next(int x, int y, int i) { return next(gs[x][y], i); }
			Grid next(Grid g, int i) { return isValid(g.x + dx[i], g.y + dy[i], g.h, g.w) ? gs[g.x + dx[i]][g.y + dy[i]] : null; }
		}
		class Grid implements Comparable<Grid> {
			int x, y, h, w, i; boolean b; long val;

			Grid() {  }
			Grid(int x, int y, int h, int w) { init(x, y, h, w, false, 0); }
			Grid(int x, int y, int h, int w, boolean b) { init(x, y, h, w, b, 0); }
			Grid(int x, int y, int h, int w, long val) { init(x, y, h, w, false, val); }
			Grid(int x, int y, int h, int w, boolean b, long val) { init(x, y, h, w, b, val); }

			void init(int x, int y, int h, int w, boolean b, long val) { this.x = x; this.y = y; this.h = h; this.w = w; this.b = b; this.val = val; this.i = x * w + y; }

			@Override public String toString() { return "("+x+", "+y+")"+" "+booleanToString(b)+" "+val; }
			@Override public int hashCode() { return Objects.hash(x, y, h, w, b, val); }
			@Override
			public boolean equals(Object obj) {
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
			public int compareTo(Grid that) {
				int c = Long.compare(this.val, that.val);
				if(c == 0) c = Integer.compare(this.x, that.x);
				if(c == 0) c = Integer.compare(this.y, that.y);
				return c;
			}
		}

		final boolean isValid(int x, int y, int h, int w) { return x >= 0 && x < h && y >= 0 && y < w; }
		final boolean isValid(Grid g) { return isValid(g.x, g.y, g.h, g.w); }

		// graph
		class Graph {
			int numNode, numEdge;
			boolean directed;
			List<Edge> edges = new ArrayList<>();
			Node nodes[];
			Node reversedNodes[];

			Graph(int numNode, int numEdge, boolean directed) {
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

			void init(List<Edge> edges) {
				this.edges = edges;
				for(Edge e : edges) add(e);
			}

			void add(int source, int target, long cost) { add(new Edge(source, target, cost)); }
			void add(Edge e) {
				rangeCheck(e.source, numNode);
				rangeCheck(e.target, numNode);
				edges.add(e);
				nodes[e.source].add(e.target, e.cost);
				if(directed) reversedNodes[e.target].add(e.source, e.cost);
				else nodes[e.target].add(e.source, e.cost);
				numEdge = edges.size();
			}

			void clearNodes() { edges.clear(); numEdge = 0; for(Node n : nodes) n.clear(); for(Node n : reversedNodes) n.clear(); }
		}

		class Node extends ArrayList<Edge> {
			int id;

			Node(int id) { this.id = id; }
			void add(int target, long cost) { add(new Edge(id, target, cost)); }
		}

		class Edge implements Comparable<Edge> {
			int source; int target; long cost;
			Edge(int source, int target, long cost) { this.source = source; this.target = target; this.cost = cost; }

			@Override public String toString() { return source+" - "+cost+" -> "+target; }
			@Override public int hashCode() { return Objects.hash(source, target); }
			@Override
			public boolean equals(Object obj) {
				if(this == obj) return true;
				if(obj == null) return false;
				if(this.getClass() != obj.getClass()) return false;
				Edge that = (Edge) obj;
				if(this.source != that.source) return false;
				if(this.target != that.target) return false;
				return true;
			}
			@Override
			public int compareTo(Edge that) {
				int c = Long.compare(this.cost, that.cost);
				if(c == 0) c = Integer.compare(this.source, that.source);
				if(c == 0) c = Integer.compare(this.target, that.target);
				return c;
			}
		}

		// Pair
		class Pair<T extends Comparable<? super T>, U extends Comparable<? super U>> implements Comparable<Pair<T, U>> {
			T a; U b;
			Pair() { }
			Pair(T a, U b) { this.a = a; this.b = b; }

			@Override public String toString() { return "("+a.toString()+", "+b.toString()+")"; }
			@Override public int hashCode() { return Objects.hash(a, b); }
			@Override
			public boolean equals(Object obj) {
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
			@Override public int compareTo(Pair<T, U> that) { int c = (this.a).compareTo(that.a); if(c == 0) c = (this.b).compareTo(that.b); return c; }
		}
		final PairII npii() { return new PairII(ni(), ni()); }
		final PairII[] npii(int n) { PairII a[] = new PairII[n]; for(int i = 0; i < n; i ++) a[i] = npii(); return a; }
		final PairII[][] npii(int n, int m) { PairII a[][] = new PairII[n][m]; for(int i = 0; i < n; i ++) a[i] = npii(m); return a; }
		class PairII implements Comparable<PairII> {
			int a; int b;
			PairII() { }
			PairII(int a, int b) { this.a = a; this.b = b; }
			@Override public String toString() { return "("+a+", "+b+")"; }
			@Override public int hashCode() { return Objects.hash(a, b); }
			@Override
			public boolean equals(Object obj) {
				if(this == obj) return true;
				if(obj == null) return false;
				if(this.getClass() != obj.getClass()) return false;
				PairII that = (PairII) obj;
				if(this.a != that.a || this.b != that.b) return false;
				return true;
			}
			@Override public int compareTo(PairII that) { int c = Integer.compare(this.a, that.a); if(c == 0) c = Integer.compare(this.b, that.b); return c; }
		}
		final PairIL npil() { return new PairIL(ni(), nl()); }
		final PairIL[] npil(int n) { PairIL a[] = new PairIL[n]; for(int i = 0; i < n; i ++) a[i] = npil(); return a; }
		final PairIL[][] npil(int n, int m) { PairIL a[][] = new PairIL[n][m]; for(int i = 0; i < n; i ++) a[i] = npil(m); return a; }
		class PairIL implements Comparable<PairIL> {
			int a; long b;
			PairIL() { }
			PairIL(int a, long b) { this.a = a; this.b = b; }
			@Override public String toString() { return "("+a+", "+b+")"; }
			@Override public int hashCode() { return Objects.hash(a, b); }
			@Override
			public boolean equals(Object obj) {
				if(this == obj) return true;
				if(obj == null) return false;
				if(this.getClass() != obj.getClass()) return false;
				PairIL that = (PairIL) obj;
				if(this.a != that.a || this.b != that.b) return false;
				return true;
			}
			@Override public int compareTo(PairIL that) { int c = Integer.compare(this.a, that.a); if(c == 0) c = Long.compare(this.b, that.b); return c; }
		}
		final PairID npid() { return new PairID(ni(), nd()); }
		final PairID[] npid(int n) { PairID a[] = new PairID[n]; for(int i = 0; i < n; i ++) a[i] = npid(); return a; }
		final PairID[][] npid(int n, int m) { PairID a[][] = new PairID[n][m]; for(int i = 0; i < n; i ++) a[i] = npid(m); return a; }
		class PairID implements Comparable<PairID> {
			int a; double b;
			PairID() { }
			PairID(int a, double b) { this.a = a; this.b = b; }
			@Override public String toString() { return "("+a+", "+b+")"; }
			@Override public int hashCode() { return Objects.hash(a, b); }
			@Override
			public boolean equals(Object obj) {
				if(this == obj) return true;
				if(obj == null) return false;
				if(this.getClass() != obj.getClass()) return false;
				PairID that = (PairID) obj;
				if(this.a != that.a || this.b != that.b) return false;
				return true;
			}
			@Override public int compareTo(PairID that) { int c = Integer.compare(this.a, that.a); if(c == 0) c = Double.compare(this.b, that.b); return c; }
		}
		final PairLI npli() { return new PairLI(nl(), ni()); }
		final PairLI[] npli(int n) { PairLI a[] = new PairLI[n]; for(int i = 0; i < n; i ++) a[i] = npli(); return a; }
		final PairLI[][] npli(int n, int m) { PairLI a[][] = new PairLI[n][m]; for(int i = 0; i < n; i ++) a[i] = npli(m); return a; }
		class PairLI implements Comparable<PairLI> {
			long a; int b;
			PairLI() { }
			PairLI(long a, int b) { this.a = a; this.b = b; }
			@Override public String toString() { return "("+a+", "+b+")"; }
			@Override public int hashCode() { return Objects.hash(a, b); }
			@Override
			public boolean equals(Object obj) {
				if(this == obj) return true;
				if(obj == null) return false;
				if(this.getClass() != obj.getClass()) return false;
				PairLI that = (PairLI) obj;
				if(this.a != that.a || this.b != that.b) return false;
				return true;
			}
			@Override public int compareTo(PairLI that) { int c = Long.compare(this.a, that.a); if(c == 0) c = Integer.compare(this.b, that.b); return c; }
		}
		final PairLL npll() { return new PairLL(nl(), nl()); }
		final PairLL[] npll(int n) { PairLL a[] = new PairLL[n]; for(int i = 0; i < n; i ++) a[i] = npll(); return a; }
		final PairLL[][] npll(int n, int m) { PairLL a[][] = new PairLL[n][m]; for(int i = 0; i < n; i ++) a[i] = npll(m); return a; }
		class PairLL implements Comparable<PairLL> {
			long a; long b;
			PairLL() { }
			PairLL(long a, long b) { this.a = a; this.b = b; }
			@Override public String toString() { return "("+a+", "+b+")"; }
			@Override public int hashCode() { return Objects.hash(a, b); }
			@Override
			public boolean equals(Object obj) {
				if(this == obj) return true;
				if(obj == null) return false;
				if(this.getClass() != obj.getClass()) return false;
				PairLL that = (PairLL) obj;
				if(this.a != that.a || this.b != that.b) return false;
				return true;
			}
			@Override public int compareTo(PairLL that) { int c = Long.compare(this.a, that.a); if(c == 0) c = Long.compare(this.b, that.b); return c; }
		}
		final PairLD npld() { return new PairLD(nl(), nd()); }
		final PairLD[] npld(int n) { PairLD a[] = new PairLD[n]; for(int i = 0; i < n; i ++) a[i] = npld(); return a; }
		final PairLD[][] npld(int n, int m) { PairLD a[][] = new PairLD[n][m]; for(int i = 0; i < n; i ++) a[i] = npld(m); return a; }
		class PairLD implements Comparable<PairLD> {
			long a; double b;
			PairLD() { }
			PairLD(long a, double b) { this.a = a; this.b = b; }
			@Override public String toString() { return "("+a+", "+b+")"; }
			@Override public int hashCode() { return Objects.hash(a, b); }
			@Override
			public boolean equals(Object obj) {
				if(this == obj) return true;
				if(obj == null) return false;
				if(this.getClass() != obj.getClass()) return false;
				PairLD that = (PairLD) obj;
				if(this.a != that.a || this.b != that.b) return false;
				return true;
			}
			@Override public int compareTo(PairLD that) { int c = Long.compare(this.a, that.a); if(c == 0) c = Double.compare(this.b, that.b); return c; }
		}
		final PairDI npdi() { return new PairDI(nd(), ni()); }
		final PairDI[] npdi(int n) { PairDI a[] = new PairDI[n]; for(int i = 0; i < n; i ++) a[i] = npdi(); return a; }
		final PairDI[][] npdi(int n, int m) { PairDI a[][] = new PairDI[n][m]; for(int i = 0; i < n; i ++) a[i] = npdi(m); return a; }
		class PairDI implements Comparable<PairDI> {
			double a; int b;
			PairDI() { }
			PairDI(double a, int b) { this.a = a; this.b = b; }
			@Override public String toString() { return "("+a+", "+b+")"; }
			@Override public int hashCode() { return Objects.hash(a, b); }
			@Override
			public boolean equals(Object obj) {
				if(this == obj) return true;
				if(obj == null) return false;
				if(this.getClass() != obj.getClass()) return false;
				PairDI that = (PairDI) obj;
				if(this.a != that.a || this.b != that.b) return false;
				return true;
			}
			@Override public int compareTo(PairDI that) { int c = Double.compare(this.a, that.a); if(c == 0) c = Integer.compare(this.b, that.b); return c; }
		}
		final PairDL npdl() { return new PairDL(nd(), nl()); }
		final PairDL[] npdl(int n) { PairDL a[] = new PairDL[n]; for(int i = 0; i < n; i ++) a[i] = npdl(); return a; }
		final PairDL[][] npdl(int n, int m) { PairDL a[][] = new PairDL[n][m]; for(int i = 0; i < n; i ++) a[i] = npdl(m); return a; }
		class PairDL implements Comparable<PairDL> {
			double a; long b;
			PairDL() { }
			PairDL(double a, long b) { this.a = a; this.b = b; }
			@Override public String toString() { return "("+a+", "+b+")"; }
			@Override public int hashCode() { return Objects.hash(a, b); }
			@Override
			public boolean equals(Object obj) {
				if(this == obj) return true;
				if(obj == null) return false;
				if(this.getClass() != obj.getClass()) return false;
				PairDL that = (PairDL) obj;
				if(this.a != that.a || this.b != that.b) return false;
				return true;
			}
			@Override public int compareTo(PairDL that) { int c = Double.compare(this.a, that.a); if(c == 0) c = Long.compare(this.b, that.b); return c; }
		}
		final PairDD npdd() { return new PairDD(nd(), nd()); }
		final PairDD[] npdd(int n) { PairDD a[] = new PairDD[n]; for(int i = 0; i < n; i ++) a[i] = npdd(); return a; }
		final PairDD[][] npdd(int n, int m) { PairDD a[][] = new PairDD[n][m]; for(int i = 0; i < n; i ++) a[i] = npdd(m); return a; }
		class PairDD implements Comparable<PairDD> {
			double a; double b;
			PairDD() { }
			PairDD(double a, double b) { this.a = a; this.b = b; }
			@Override public String toString() { return "("+a+", "+b+")"; }
			@Override public int hashCode() { return Objects.hash(a, b); }
			@Override
			public boolean equals(Object obj) {
				if(this == obj) return true;
				if(obj == null) return false;
				if(this.getClass() != obj.getClass()) return false;
				PairDD that = (PairDD) obj;
				if(this.a != that.a || this.b != that.b) return false;
				return true;
			}
			@Override public int compareTo(PairDD that) { int c = Double.compare(this.a, that.a); if(c == 0) c = Double.compare(this.b, that.b); return c; }
		}

		interface ITuple {
			public StringBuilder toStringBuilder();
			@Override public String toString();
			@Override public int hashCode();
			@Override public boolean equals(Object obj);
		}
		class BasicTuple<T extends ITuple & Comparable<? super T>, V extends Comparable<? super V>> implements Comparable<BasicTuple> {
			T t; V a;
			BasicTuple() {  }

			StringBuilder sbTuple = new StringBuilder();
			public StringBuilder toStringBuilder() {
				sbTuple.setLength(0);
				return sbTuple.append(t.toStringBuilder()).append(", ").append(a);
			}
			@Override public String toString() { return "("+toStringBuilder().toString()+")"; }
			@Override public int hashCode() { return Objects.hash(t, a); }
			@Override
			public boolean equals(Object obj) {
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
			@Override @SuppressWarnings("unchecked") public int compareTo(BasicTuple that) { int c = (this.t).compareTo((T) (Object) that.t); if(c == 0) c = (this.a).compareTo((V) (Object) that.a); return c; }
		}
		class UniqueTuple<V extends Comparable<? super V>> implements ITuple, Comparable<UniqueTuple> {
			V a;
			UniqueTuple() {  }

			final StringBuilder sbTuple = new StringBuilder();
			public StringBuilder toStringBuilder() { sbTuple.setLength(0); return sbTuple.append(a); }
			@Override public String toString() { return "("+toStringBuilder().toString()+")"; }
			@Override public int hashCode() { return Objects.hash(a); }
			@Override
			public boolean equals(Object obj) {
				if(this == obj) return true;
				if(obj == null) return false;
				if(this.getClass() != obj.getClass()) return false;
				UniqueTuple that = (UniqueTuple) obj;
				if(this.a.getClass() != that.a.getClass()) return false;
				if(!this.a.equals(that.a)) return false;
				return true;
			}
			@Override @SuppressWarnings("unchecked") public int compareTo(UniqueTuple that) { return (this.a).compareTo((V) (Object) that.a); }
		}

		class Tuple1<T0 extends Comparable<? super T0>> extends UniqueTuple<T0> implements ITuple {
			Tuple1() { super(); }
			Tuple1(T0 a0) { super(); this.a = a0; }
			T0 get0() { return a; }
			void set0(T0 x) { a = x; }
		}
		class Tuple2<
				T0 extends Comparable<? super T0>,
				T1 extends Comparable<? super T1>>
				extends BasicTuple<Tuple1<T0>, T1> implements ITuple {
			Tuple2() { super(); }
			@SuppressWarnings("unchecked") Tuple2(T0 a0, T1 a1) { super(); this.t = new Tuple1(a0); this.a = a1; }
			T0 get0() { return t.get0(); }
			T1 get1() { return a; }
			void set0(T0 x) { t.set0(x); }
			void set1(T1 x) { a = x; }
		}
		class Tuple3<
				T0 extends Comparable<? super T0>,
				T1 extends Comparable<? super T1>,
				T2 extends Comparable<? super T2>>
				extends BasicTuple<Tuple2<T0, T1>, T2> implements ITuple {
			Tuple3() { super(); }
			@SuppressWarnings("unchecked") Tuple3(T0 a0, T1 a1, T2 a2) { super(); this.t = new Tuple2(a0, a1); this.a = a2; }
			T0 get0() { return t.get0(); }
			T1 get1() { return t.get1(); }
			T2 get2() { return a; }
			void set0(T0 x) { t.set0(x); }
			void set1(T1 x) { t.set1(x); }
			void set2(T2 x) { a = x; }
		}
		class Tuple4<
				T0 extends Comparable<? super T0>,
				T1 extends Comparable<? super T1>,
				T2 extends Comparable<? super T2>,
				T3 extends Comparable<? super T3>>
				extends BasicTuple<Tuple3<T0, T1, T2>, T3> implements ITuple {
			Tuple4() { super(); }
			@SuppressWarnings("unchecked") Tuple4(T0 a0, T1 a1, T2 a2, T3 a3) { super(); this.t = new Tuple3(a0, a1, a2); this.a = a3; }
			T0 get0() { return t.get0(); }
			T1 get1() { return t.get1(); }
			T2 get2() { return t.get2(); }
			T3 get3() { return a; }
			void set0(T0 x) { t.set0(x); }
			void set1(T1 x) { t.set1(x); }
			void set2(T2 x) { t.set2(x); }
			void set3(T3 x) { a = x; }
		}
		class Tuple5<
				T0 extends Comparable<? super T0>,
				T1 extends Comparable<? super T1>,
				T2 extends Comparable<? super T2>,
				T3 extends Comparable<? super T3>,
				T4 extends Comparable<? super T4>>
				extends BasicTuple<Tuple4<T0, T1, T2, T3>, T4> implements ITuple {
			Tuple5() { super(); }
			@SuppressWarnings("unchecked") Tuple5(T0 a0, T1 a1, T2 a2, T3 a3, T4 a4) { super(); this.t = new Tuple4(a0, a1, a2, a3); this.a = a4; }
			T0 get0() { return t.get0(); }
			T1 get1() { return t.get1(); }
			T2 get2() { return t.get2(); }
			T3 get3() { return t.get3(); }
			T4 get4() { return a; }
			void set0(T0 x) { t.set0(x); }
			void set1(T1 x) { t.set1(x); }
			void set2(T2 x) { t.set2(x); }
			void set3(T3 x) { t.set3(x); }
			void set4(T4 x) { a = x; }
		}
		class Tuple6<
				T0 extends Comparable<? super T0>,
				T1 extends Comparable<? super T1>,
				T2 extends Comparable<? super T2>,
				T3 extends Comparable<? super T3>,
				T4 extends Comparable<? super T4>,
				T5 extends Comparable<? super T5>>
				extends BasicTuple<Tuple5<T0, T1, T2, T3, T4>, T5> implements ITuple {
			Tuple6() { super(); }
			@SuppressWarnings("unchecked")
			Tuple6(T0 a0, T1 a1, T2 a2, T3 a3, T4 a4, T5 a5) { super(); this.t = new Tuple5(a0, a1, a2, a3, a4); this.a = a5; }
			T0 get0() { return t.get0(); }
			T1 get1() { return t.get1(); }
			T2 get2() { return t.get2(); }
			T3 get3() { return t.get3(); }
			T4 get4() { return t.get4(); }
			T5 get5() { return a; }
			void set0(T0 x) { t.set0(x); }
			void set1(T1 x) { t.set1(x); }
			void set2(T2 x) { t.set2(x); }
			void set3(T3 x) { t.set3(x); }
			void set4(T4 x) { t.set4(x); }
			void set5(T5 x) { a = x; }
		}
		class Tuple7<
				T0 extends Comparable<? super T0>,
				T1 extends Comparable<? super T1>,
				T2 extends Comparable<? super T2>,
				T3 extends Comparable<? super T3>,
				T4 extends Comparable<? super T4>,
				T5 extends Comparable<? super T5>,
				T6 extends Comparable<? super T6>>
				extends BasicTuple<Tuple6<T0, T1, T2, T3, T4, T5>, T6> implements ITuple {
			Tuple7() { super(); }
			@SuppressWarnings("unchecked")
			Tuple7(T0 a0, T1 a1, T2 a2, T3 a3, T4 a4, T5 a5, T6 a6) { super(); this.t = new Tuple6(a0, a1, a2, a3, a4, a5); this.a = a6; }
			T0 get0() { return t.get0(); }
			T1 get1() { return t.get1(); }
			T2 get2() { return t.get2(); }
			T3 get3() { return t.get3(); }
			T4 get4() { return t.get4(); }
			T5 get5() { return t.get5(); }
			T6 get6() { return a; }
			void set0(T0 x) { t.set0(x); }
			void set1(T1 x) { t.set1(x); }
			void set2(T2 x) { t.set2(x); }
			void set3(T3 x) { t.set3(x); }
			void set4(T4 x) { t.set4(x); }
			void set5(T5 x) { t.set5(x); }
			void set6(T6 x) { a = x; }
		}
		class Tuple8<
				T0 extends Comparable<? super T0>,
				T1 extends Comparable<? super T1>,
				T2 extends Comparable<? super T2>,
				T3 extends Comparable<? super T3>,
				T4 extends Comparable<? super T4>,
				T5 extends Comparable<? super T5>,
				T6 extends Comparable<? super T6>,
				T7 extends Comparable<? super T7>>
				extends BasicTuple<Tuple7<T0, T1, T2, T3, T4, T5, T6>, T7> implements ITuple {
			Tuple8() { super(); }
			@SuppressWarnings("unchecked")
			Tuple8(T0 a0, T1 a1, T2 a2, T3 a3, T4 a4, T5 a5, T6 a6, T7 a7) { super(); this.t = new Tuple7(a0, a1, a2, a3, a4, a5, a6); this.a = a7; }
			T0 get0() { return t.get0(); }
			T1 get1() { return t.get1(); }
			T2 get2() { return t.get2(); }
			T3 get3() { return t.get3(); }
			T4 get4() { return t.get4(); }
			T5 get5() { return t.get5(); }
			T6 get6() { return t.get6(); }
			T7 get7() { return a; }
			void set0(T0 x) { t.set0(x); }
			void set1(T1 x) { t.set1(x); }
			void set2(T2 x) { t.set2(x); }
			void set3(T3 x) { t.set3(x); }
			void set4(T4 x) { t.set4(x); }
			void set5(T5 x) { t.set5(x); }
			void set6(T6 x) { t.set6(x); }
			void set7(T7 x) { a = x; }
		}

		class TupleIII implements Comparable<TupleIII> {
			int a; int b; int c;
			TupleIII() {  }
			TupleIII(int a, int b, int c) { this.a = a; this.b = b; this.c = c; }
			@Override public String toString() { return "("+a+", "+b+", "+c+")"; }
			@Override public int hashCode() { return Objects.hash(a, b, c); }
			@Override
			public boolean equals(Object obj) {
				if(this == obj) return true;
				if(obj == null || this.getClass() != obj.getClass()) return false;
				TupleIII that = (TupleIII) obj;
				if(this.a != that.a || this.b != that.b || this.c != that.c) return false;
				return true;
			}
			@Override public int compareTo(TupleIII that) { int c = Integer.compare(this.a, that.a); if(c == 0) c = Integer.compare(this.b, that.b); if(c == 0) c = Integer.compare(this.c, that.c); return c; }
		}
		class TupleIIL implements Comparable<TupleIIL> {
			int a; int b; long c;
			TupleIIL() {  }
			TupleIIL(int a, int b, long c) { this.a = a; this.b = b; this.c = c; }
			@Override public String toString() { return "("+a+", "+b+", "+c+")"; }
			@Override public int hashCode() { return Objects.hash(a, b, c); }
			@Override
			public boolean equals(Object obj) {
				if(this == obj) return true;
				if(obj == null || this.getClass() != obj.getClass()) return false;
				TupleIIL that = (TupleIIL) obj;
				if(this.a != that.a || this.b != that.b || this.c != that.c) return false;
				return true;
			}
			@Override public int compareTo(TupleIIL that) { int c = Integer.compare(this.a, that.a); if(c == 0) c = Integer.compare(this.b, that.b); if(c == 0) c = Long.compare(this.c, that.c); return c;
			}
		}
		class TupleIID implements Comparable<TupleIID> {
			int a; int b; double c;
			TupleIID() {  }
			TupleIID(int a, int b, double c) { this.a = a; this.b = b; this.c = c; }
			@Override public String toString() { return "("+a+", "+b+", "+c+")"; }
			@Override public int hashCode() { return Objects.hash(a, b, c); }
			@Override
			public boolean equals(Object obj) {
				if(this == obj) return true;
				if(obj == null || this.getClass() != obj.getClass()) return false;
				TupleIID that = (TupleIID) obj;
				if(this.a != that.a || this.b != that.b || this.c != that.c) return false;
				return true;
			}
			@Override public int compareTo(TupleIID that) { int c = Integer.compare(this.a, that.a); if(c == 0) c = Integer.compare(this.b, that.b); if(c == 0) c = Double.compare(this.c, that.c); return c; }
		}
		class TupleILI implements Comparable<TupleILI> {
			int a; long b; int c;
			TupleILI() {  }
			TupleILI(int a, long b, int c) { this.a = a; this.b = b; this.c = c; }
			@Override public String toString() { return "("+a+", "+b+", "+c+")"; }
			@Override public int hashCode() { return Objects.hash(a, b, c); }
			@Override
			public boolean equals(Object obj) {
				if(this == obj) return true;
				if(obj == null || this.getClass() != obj.getClass()) return false;
				TupleILI that = (TupleILI) obj;
				if(this.a != that.a || this.b != that.b || this.c != that.c) return false;
				return true;
			}
			@Override public int compareTo(TupleILI that) { int c = Integer.compare(this.a, that.a); if(c == 0) c = Long.compare(this.b, that.b); if(c == 0) c = Integer.compare(this.c, that.c); return c; }
		}
		class TupleILL implements Comparable<TupleILL> {
			int a; long b; long c;
			TupleILL() {  }
			TupleILL(int a, long b, long c) { this.a = a; this.b = b; this.c = c; }
			@Override public String toString() { return "("+a+", "+b+", "+c+")"; }
			@Override public int hashCode() { return Objects.hash(a, b, c); }
			@Override
			public boolean equals(Object obj) {
				if(this == obj) return true;
				if(obj == null || this.getClass() != obj.getClass()) return false;
				TupleILL that = (TupleILL) obj;
				if(this.a != that.a || this.b != that.b || this.c != that.c) return false;
				return true;
			}
			@Override public int compareTo(TupleILL that) { int c = Integer.compare(this.a, that.a); if(c == 0) c = Long.compare(this.b, that.b); if(c == 0) c = Long.compare(this.c, that.c); return c; }
		}
		class TupleILD implements Comparable<TupleILD> {
			int a; long b; double c;
			TupleILD() {  }
			TupleILD(int a, long b, double c) { this.a = a; this.b = b; this.c = c; }
			@Override public String toString() { return "("+a+", "+b+", "+c+")"; }
			@Override public int hashCode() { return Objects.hash(a, b, c); }
			@Override
			public boolean equals(Object obj) {
				if(this == obj) return true;
				if(obj == null || this.getClass() != obj.getClass()) return false;
				TupleILD that = (TupleILD) obj;
				if(this.a != that.a || this.b != that.b || this.c != that.c) return false;
				return true;
			}
			@Override public int compareTo(TupleILD that) { int c = Integer.compare(this.a, that.a); if(c == 0) c = Long.compare(this.b, that.b); if(c == 0) c = Double.compare(this.c, that.c); return c; }
		}
		class TupleIDI implements Comparable<TupleIDI> {
			int a; double b; int c;
			TupleIDI() {  }
			TupleIDI(int a, double b, int c) { this.a = a; this.b = b; this.c = c; }
			@Override public String toString() { return "("+a+", "+b+", "+c+")"; }
			@Override public int hashCode() { return Objects.hash(a, b, c); }
			@Override
			public boolean equals(Object obj) {
				if(this == obj) return true;
				if(obj == null || this.getClass() != obj.getClass()) return false;
				TupleIDI that = (TupleIDI) obj;
				if(this.a != that.a || this.b != that.b || this.c != that.c) return false;
				return true;
			}
			@Override
			public int compareTo(TupleIDI that) { int c = Integer.compare(this.a, that.a); if(c == 0) c = Double.compare(this.b, that.b); if(c == 0) c = Integer.compare(this.c, that.c); return c; }
		}
		class TupleIDL implements Comparable<TupleIDL> {
			int a; double b; long c;
			TupleIDL() {  }
			TupleIDL(int a, double b, long c) { this.a = a; this.b = b; this.c = c; }
			@Override public String toString() { return "("+a+", "+b+", "+c+")"; }
			@Override public int hashCode() { return Objects.hash(a, b, c); }
			@Override
			public boolean equals(Object obj) {
				if(this == obj) return true;
				if(obj == null || this.getClass() != obj.getClass()) return false;
				TupleIDL that = (TupleIDL) obj;
				if(this.a != that.a || this.b != that.b || this.c != that.c) return false;
				return true;
			}
			@Override public int compareTo(TupleIDL that) { int c = Integer.compare(this.a, that.a); if(c == 0) c = Double.compare(this.b, that.b); if(c == 0) c = Long.compare(this.c, that.c); return c; }
		}
		class TupleIDD implements Comparable<TupleIDD> {
			int a; double b; double c;
			TupleIDD() {  }
			TupleIDD(int a, double b, double c) { this.a = a; this.b = b; this.c = c; }
			@Override public String toString() { return "("+a+", "+b+", "+c+")"; }
			@Override public int hashCode() { return Objects.hash(a, b, c); }
			@Override
			public boolean equals(Object obj) {
				if(this == obj) return true;
				if(obj == null || this.getClass() != obj.getClass()) return false;
				TupleIDD that = (TupleIDD) obj;
				if(this.a != that.a || this.b != that.b || this.c != that.c) return false;
				return true;
			}
			@Override public int compareTo(TupleIDD that) { int c = Integer.compare(this.a, that.a); if(c == 0) c = Double.compare(this.b, that.b); if(c == 0) c = Double.compare(this.c, that.c); return c; }
		}
		class TupleLII implements Comparable<TupleLII> {
			long a; int b; int c;
			TupleLII() {  }
			TupleLII(long a, int b, int c) { this.a = a; this.b = b; this.c = c; }
			@Override public String toString() { return "("+a+", "+b+", "+c+")"; }
			@Override public int hashCode() { return Objects.hash(a, b, c); }
			@Override
			public boolean equals(Object obj) {
				if(this == obj) return true;
				if(obj == null || this.getClass() != obj.getClass()) return false;
				TupleLII that = (TupleLII) obj;
				if(this.a != that.a || this.b != that.b || this.c != that.c) return false;
				return true;
			}
			@Override public int compareTo(TupleLII that) { int c = Long.compare(this.a, that.a); if(c == 0) c = Integer.compare(this.b, that.b); if(c == 0) c = Integer.compare(this.c, that.c); return c; }
		}
		class TupleLIL implements Comparable<TupleLIL> {
			long a; int b; long c;
			TupleLIL() {  }
			TupleLIL(long a, int b, long c) { this.a = a; this.b = b; this.c = c; }
			@Override public String toString() { return "("+a+", "+b+", "+c+")"; }
			@Override public int hashCode() { return Objects.hash(a, b, c); }
			@Override
			public boolean equals(Object obj) {
				if(this == obj) return true;
				if(obj == null || this.getClass() != obj.getClass()) return false;
				TupleLIL that = (TupleLIL) obj;
				if(this.a != that.a || this.b != that.b || this.c != that.c) return false;
				return true;
			}
			@Override public int compareTo(TupleLIL that) { int c = Long.compare(this.a, that.a); if(c == 0) c = Integer.compare(this.b, that.b); if(c == 0) c = Long.compare(this.c, that.c); return c; }
		}
		class TupleLID implements Comparable<TupleLID> {
			long a; int b; double c;
			TupleLID() {  }
			TupleLID(long a, int b, double c) { this.a = a; this.b = b; this.c = c; }
			@Override public String toString() { return "("+a+", "+b+", "+c+")"; }
			@Override public int hashCode() { return Objects.hash(a, b, c); }
			@Override
			public boolean equals(Object obj) {
				if(this == obj) return true;
				if(obj == null || this.getClass() != obj.getClass()) return false;
				TupleLID that = (TupleLID) obj;
				if(this.a != that.a || this.b != that.b || this.c != that.c) return false;
				return true;
			}
			@Override public int compareTo(TupleLID that) { int c = Long.compare(this.a, that.a); if(c == 0) c = Integer.compare(this.b, that.b); if(c == 0) c = Double.compare(this.c, that.c); return c; }
		}
		class TupleLLI implements Comparable<TupleLLI> {
			long a; long b; int c;
			TupleLLI() {  }
			TupleLLI(long a, long b, int c) { this.a = a; this.b = b; this.c = c; }
			@Override public String toString() { return "("+a+", "+b+", "+c+")"; }
			@Override public int hashCode() { return Objects.hash(a, b, c); }
			@Override
			public boolean equals(Object obj) {
				if(this == obj) return true;
				if(obj == null || this.getClass() != obj.getClass()) return false;
				TupleLLI that = (TupleLLI) obj;
				if(this.a != that.a || this.b != that.b || this.c != that.c) return false;
				return true;
			}
			@Override public int compareTo(TupleLLI that) { int c = Long.compare(this.a, that.a); if(c == 0) c = Long.compare(this.b, that.b); if(c == 0) c = Integer.compare(this.c, that.c); return c; }
		}
		class TupleLLL implements Comparable<TupleLLL> {
			long a; long b; long c;
			TupleLLL() {  }
			TupleLLL(long a, long b, long c) { this.a = a; this.b = b; this.c = c; }
			@Override public String toString() { return "("+a+", "+b+", "+c+")"; }
			@Override public int hashCode() { return Objects.hash(a, b, c); }
			@Override
			public boolean equals(Object obj) {
				if(this == obj) return true;
				if(obj == null || this.getClass() != obj.getClass()) return false;
				TupleLLL that = (TupleLLL) obj;
				if(this.a != that.a || this.b != that.b || this.c != that.c) return false;
				return true;
			}
			@Override public int compareTo(TupleLLL that) { int c = Long.compare(this.a, that.a); if(c == 0) c = Long.compare(this.b, that.b); if(c == 0) c = Long.compare(this.c, that.c); return c; }
		}
		class TupleLLD implements Comparable<TupleLLD> {
			long a; long b; double c;
			TupleLLD() {  }
			TupleLLD(long a, long b, double c) { this.a = a; this.b = b; this.c = c; }
			@Override public String toString() { return "("+a+", "+b+", "+c+")"; }
			@Override public int hashCode() { return Objects.hash(a, b, c); }
			@Override
			public boolean equals(Object obj) {
				if(this == obj) return true;
				if(obj == null || this.getClass() != obj.getClass()) return false;
				TupleLLD that = (TupleLLD) obj;
				if(this.a != that.a || this.b != that.b || this.c != that.c) return false;
				return true;
			}
			@Override public int compareTo(TupleLLD that) { int c = Long.compare(this.a, that.a); if(c == 0) c = Long.compare(this.b, that.b); if(c == 0) c = Double.compare(this.c, that.c); return c; }
		}
		class TupleLDI implements Comparable<TupleLDI> {
			long a; double b; int c;
			TupleLDI() {  }
			TupleLDI(long a, double b, int c) { this.a = a; this.b = b; this.c = c; }
			@Override public String toString() { return "("+a+", "+b+", "+c+")"; }
			@Override public int hashCode() { return Objects.hash(a, b, c); }
			@Override
			public boolean equals(Object obj) {
				if(this == obj) return true;
				if(obj == null || this.getClass() != obj.getClass()) return false;
				TupleLDI that = (TupleLDI) obj;
				if(this.a != that.a || this.b != that.b || this.c != that.c) return false;
				return true;
			}
			@Override public int compareTo(TupleLDI that) { int c = Long.compare(this.a, that.a); if(c == 0) c = Double.compare(this.b, that.b); if(c == 0) c = Integer.compare(this.c, that.c); return c; }
		}
		class TupleLDL implements Comparable<TupleLDL> {
			long a; double b; long c;
			TupleLDL() {  }
			TupleLDL(long a, double b, long c) { this.a = a; this.b = b; this.c = c; }
			@Override public String toString() { return "("+a+", "+b+", "+c+")"; }
			@Override public int hashCode() { return Objects.hash(a, b, c); }
			@Override
			public boolean equals(Object obj) {
				if(this == obj) return true;
				if(obj == null || this.getClass() != obj.getClass()) return false;
				TupleLDL that = (TupleLDL) obj;
				if(this.a != that.a || this.b != that.b || this.c != that.c) return false;
				return true;
			}
			@Override public int compareTo(TupleLDL that) { int c = Long.compare(this.a, that.a); if(c == 0) c = Double.compare(this.b, that.b); if(c == 0) c = Long.compare(this.c, that.c); return c; }
		}
		class TupleLDD implements Comparable<TupleLDD> {
			long a; double b; double c;
			TupleLDD() {  }
			TupleLDD(long a, double b, double c) { this.a = a; this.b = b; this.c = c; }
			@Override public String toString() { return "("+a+", "+b+", "+c+")"; }
			@Override public int hashCode() { return Objects.hash(a, b, c); }
			@Override
			public boolean equals(Object obj) {
				if(this == obj) return true;
				if(obj == null || this.getClass() != obj.getClass()) return false;
				TupleLDD that = (TupleLDD) obj;
				if(this.a != that.a || this.b != that.b || this.c != that.c) return false;
				return true;
			}
			@Override public int compareTo(TupleLDD that) { int c = Long.compare(this.a, that.a); if(c == 0) c = Double.compare(this.b, that.b); if(c == 0) c = Double.compare(this.c, that.c); return c; }
		}
		class TupleDII implements Comparable<TupleDII> {
			double a; int b; int c;
			TupleDII() {  }
			TupleDII(double a, int b, int c) { this.a = a; this.b = b; this.c = c; }
			@Override public String toString() { return "("+a+", "+b+", "+c+")"; }
			@Override public int hashCode() { return Objects.hash(a, b, c); }
			@Override
			public boolean equals(Object obj) {
				if(this == obj) return true;
				if(obj == null || this.getClass() != obj.getClass()) return false;
				TupleDII that = (TupleDII) obj;
				if(this.a != that.a || this.b != that.b || this.c != that.c) return false;
				return true;
			}
			@Override public int compareTo(TupleDII that) { int c = Double.compare(this.a, that.a); if(c == 0) c = Integer.compare(this.b, that.b); if(c == 0) c = Integer.compare(this.c, that.c); return c; }
		}
		class TupleDIL implements Comparable<TupleDIL> {
			double a; int b; long c;
			TupleDIL() {  }
			TupleDIL(double a, int b, long c) { this.a = a; this.b = b; this.c = c; }
			@Override public String toString() { return "("+a+", "+b+", "+c+")"; }
			@Override public int hashCode() { return Objects.hash(a, b, c); }
			@Override
			public boolean equals(Object obj) {
				if(this == obj) return true;
				if(obj == null || this.getClass() != obj.getClass()) return false;
				TupleDIL that = (TupleDIL) obj;
				if(this.a != that.a || this.b != that.b || this.c != that.c) return false;
				return true;
			}
			@Override public int compareTo(TupleDIL that) { int c = Double.compare(this.a, that.a); if(c == 0) c = Integer.compare(this.b, that.b); if(c == 0) c = Long.compare(this.c, that.c); return c; }
		}
		class TupleDID implements Comparable<TupleDID> {
			double a; int b; double c;
			TupleDID() {  }
			TupleDID(double a, int b, double c) { this.a = a; this.b = b; this.c = c; }
			@Override public String toString() { return "("+a+", "+b+", "+c+")"; }
			@Override public int hashCode() { return Objects.hash(a, b, c); }
			@Override
			public boolean equals(Object obj) {
				if(this == obj) return true;
				if(obj == null || this.getClass() != obj.getClass()) return false;
				TupleDID that = (TupleDID) obj;
				if(this.a != that.a || this.b != that.b || this.c != that.c) return false;
				return true;
			}
			@Override public int compareTo(TupleDID that) { int c = Double.compare(this.a, that.a); if(c == 0) c = Integer.compare(this.b, that.b); if(c == 0) c = Double.compare(this.c, that.c); return c; }
		}
		class TupleDLI implements Comparable<TupleDLI> {
			double a; long b; int c;
			TupleDLI() {  }
			TupleDLI(double a, long b, int c) { this.a = a; this.b = b; this.c = c; }
			@Override public String toString() { return "("+a+", "+b+", "+c+")"; }
			@Override public int hashCode() { return Objects.hash(a, b, c); }
			@Override
			public boolean equals(Object obj) {
				if(this == obj) return true;
				if(obj == null || this.getClass() != obj.getClass()) return false;
				TupleDLI that = (TupleDLI) obj;
				if(this.a != that.a || this.b != that.b || this.c != that.c) return false;
				return true;
			}
			@Override public int compareTo(TupleDLI that) { int c = Double.compare(this.a, that.a); if(c == 0) c = Long.compare(this.b, that.b); if(c == 0) c = Integer.compare(this.c, that.c); return c; }
		}
		class TupleDLL implements Comparable<TupleDLL> {
			double a; long b; long c;
			TupleDLL() {  }
			TupleDLL(double a, long b, long c) { this.a = a; this.b = b; this.c = c; }
			@Override public String toString() { return "("+a+", "+b+", "+c+")"; }
			@Override public int hashCode() { return Objects.hash(a, b, c); }
			@Override
			public boolean equals(Object obj) {
				if(this == obj) return true;
				if(obj == null || this.getClass() != obj.getClass()) return false;
				TupleDLL that = (TupleDLL) obj;
				if(this.a != that.a || this.b != that.b || this.c != that.c) return false;
				return true;
			}
			@Override public int compareTo(TupleDLL that) { int c = Double.compare(this.a, that.a); if(c == 0) c = Long.compare(this.b, that.b); if(c == 0) c = Long.compare(this.c, that.c); return c; }
		}
		class TupleDLD implements Comparable<TupleDLD> {
			double a; long b; double c;
			TupleDLD() {  }
			TupleDLD(double a, long b, double c) { this.a = a; this.b = b; this.c = c; }
			@Override public String toString() { return "("+a+", "+b+", "+c+")"; }
			@Override public int hashCode() { return Objects.hash(a, b, c); }
			@Override
			public boolean equals(Object obj) {
				if(this == obj) return true;
				if(obj == null || this.getClass() != obj.getClass()) return false;
				TupleDLD that = (TupleDLD) obj;
				if(this.a != that.a || this.b != that.b || this.c != that.c) return false;
				return true;
			}
			@Override public int compareTo(TupleDLD that) { int c = Double.compare(this.a, that.a); if(c == 0) c = Long.compare(this.b, that.b); if(c == 0) c = Double.compare(this.c, that.c); return c; }
		}
		class TupleDDI implements Comparable<TupleDDI> {
			double a; double b; int c;
			TupleDDI() {  }
			TupleDDI(double a, double b, int c) { this.a = a; this.b = b; this.c = c; }
			@Override public String toString() { return "("+a+", "+b+", "+c+")"; }
			@Override public int hashCode() { return Objects.hash(a, b, c); }
			@Override
			public boolean equals(Object obj) {
				if(this == obj) return true;
				if(obj == null || this.getClass() != obj.getClass()) return false;
				TupleDDI that = (TupleDDI) obj;
				if(this.a != that.a || this.b != that.b || this.c != that.c) return false;
				return true;
			}
			@Override public int compareTo(TupleDDI that) { int c = Double.compare(this.a, that.a); if(c == 0) c = Double.compare(this.b, that.b); if(c == 0) c = Integer.compare(this.c, that.c); return c; }
		}
		class TupleDDL implements Comparable<TupleDDL> {
			double a; double b; long c;
			TupleDDL() {  }
			TupleDDL(double a, double b, long c) { this.a = a; this.b = b; this.c = c; }
			@Override public String toString() { return "("+a+", "+b+", "+c+")"; }
			@Override public int hashCode() { return Objects.hash(a, b, c); }
			@Override
			public boolean equals(Object obj) {
				if(this == obj) return true;
				if(obj == null || this.getClass() != obj.getClass()) return false;
				TupleDDL that = (TupleDDL) obj;
				if(this.a != that.a || this.b != that.b || this.c != that.c) return false;
				return true;
			}
			@Override public int compareTo(TupleDDL that) { int c = Double.compare(this.a, that.a); if(c == 0) c = Double.compare(this.b, that.b); if(c == 0) c = Long.compare(this.c, that.c); return c; }
		}
		class TupleDDD implements Comparable<TupleDDD> {
			double a; double b; double c;
			TupleDDD() {  }
			TupleDDD(double a, double b, double c) { this.a = a; this.b = b; this.c = c; }
			@Override public String toString() { return "("+a+", "+b+", "+c+")"; }
			@Override public int hashCode() { return Objects.hash(a, b, c); }
			@Override
			public boolean equals(Object obj) {
				if(this == obj) return true;
				if(obj == null || this.getClass() != obj.getClass()) return false;
				TupleDDD that = (TupleDDD) obj;
				if(this.a != that.a || this.b != that.b || this.c != that.c) return false;
				return true;
			}
			@Override public int compareTo(TupleDDD that) { int c = Double.compare(this.a, that.a); if(c == 0) c = Double.compare(this.b, that.b); if(c == 0) c = Double.compare(this.c, that.c); return c; }
		}

public void solve() {
	int n = ni();
	int a[] = ni(n);
	int b[] = ni(n);
	int bucket[] = new int[5010];
	for(int i = 0; i < n; i ++) bucket[a[i]] ++;
	for(int i = 0; i < n; i ++) bucket[b[i]] --;
	boolean ok = true;
	for(int i = 0; i < bucket.length; i ++) ok &= bucket[i] == 0;
	if(!ok) { reply(ok); return; }
	for(int i = 0; i < n; i ++) bucket[a[i]] ++;
	if(max(bucket) > 1) { reply(ok); return; }
	long sum = 0;
	int idx[] = new int[5010];
	for(int i = 0; i < n; i ++) idx[b[i]] = i;
	FenwickTree ft = new FenwickTree(n);
	for(int i = 0; i < n; i ++) { int j = idx[a[i]]; sum += j - ft.sum(j); ft.add(j, 1); }
	reply(sum % 2 == 0);
}

class FenwickTree { // N=size
	int size;
	long nodes[];
	FenwickTree(int size) { init(size); } // O(N)
	FenwickTree(int size, long[] array) { init(size); for(int i = 0; i < size; i ++) add(i, array[i]); }// O(NlogN)
	void init(int size) {
		nonNegativeCheck(size);
		this.size = size;
		nodes = new long[size];
		fill(nodes, 0);
	}

	void add(int a, long x) { // O(logN)
		rangeCheck(a, size);
		a ++;
		while(a <= size) {
			nodes[a - 1] += x;
			a += a & -a;
		}
	}
	long sum(int r) { // return sum of [0, r) // O(logN)
		inclusiveRangeCheck(r, size);
		long sum = 0;
		while(r > 0) {
			sum += nodes[r - 1];
			r -= r & -r;
		}
		return sum;
	}
	long sum(int l, int r) { // return sum of [l, r) // O(logN)
		inclusiveRangeCheck(l, size);
		inclusiveRangeCheck(r, size);
		if(l > r) return 0;
		else return sum(r) - sum(l);
	}
}




	}
}
