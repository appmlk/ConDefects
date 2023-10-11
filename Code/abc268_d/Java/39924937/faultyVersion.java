import java.io.*;
import java.math.*;
import java.time.*;
import java.util.*;
import java.util.Map.Entry;

class Main implements Runnable {

	public static void solve () {
		
		n = nextInt();
		m = nextInt();
		s = new String[n];
		for (int i=0; i<n; i++) {
			s[i] = next();
			underbarLimit -= s[i].length();
		}
		
		for (int i=0; i<m; i++) {
			String s = next();
			set.add(s);
		}
		
		
		int[] p = new int[n];
		Arrays.setAll(p, i -> i);
		
		do {
			
			rec(p, 1, s[p[0]], s[p[0]].length(), 0);
			
		} while (nextPermutation(p));
		
		println(ans);
		
	}
	
	public static int n, m;
	public static String[] s;
	public static HashSet<String> set = new HashSet<>();
	public static int underbarLimit = 16;
	public static String ans = "-1";
	
	public static void rec (int[] p, int i, String now, int nowLength, int nowUnderbarNum) {
		
		if (i == n) {
			if (set.contains(now) == false && 3 <= now.length() && now.length() <= 16) {
				ans = now;
			}
			return;
		}
		
		for (int j=1; j<underbarLimit-nowUnderbarNum; j++) {
			StringBuilder sb = new StringBuilder(now);
			for (int k=0; k<j; k++) {
				sb.append("_");
			}
			rec(p, i+1, sb.append(s[p[i]]).toString(), now.length()+s[p[i]].length(), nowUnderbarNum + j);
		}
		
	}
	
	public static boolean nextPermutation(int[] a) {
		int len = a.length;
		int l = len-2;
		while(l >= 0 && a[l] >= a[l+1]) l--;
		if(l < 0) return false;
		int r = len-1;
		while(a[l] >= a[r]) r--;
		{int t = a[l]; a[l] = a[r]; a[r] = t;}
		l++;
		r = len - 1;
		while (l < r) {
			{int t = a[l]; a[l] = a[r]; a[r] = t;}
			l++;
			r--;
		}
		return true;
	}
	
	
	
	/*
	 * ############################################################################################
	 * #  useful fields, useful methods, useful class
	 * ##############################################################################################
	 */
	
	// fields
	
	public static final int infi = (int)1e9;
	public static final long infl = (long)1e18;
	public static final int modi = (int)1e9 + 7;
	public static final long modl = (long)1e18 + 7;
	public static int[] dy = {-1, 0, 1, 0};
	public static int[] dx = {0, 1, 0, -1};
	//	public static int[] dy = {-1,  0, -1,  1,  0,  1};
	//	public static int[] dx = {-1, -1,  0,  0,  1,  1};
	//	public static int[] dy = {-1, -1, -1,  0,  1,  1,  1,  0};
	//	public static int[] dx = {-1,  0,  1,  1,  1,  0, -1, -1};
	
	// methods
	
	public static int min (int... a) {Arrays.sort(a); return a[0];}
	public static int max (int... a) {Arrays.sort(a); return a[a.length-1];}
	public static long min (long... a) {Arrays.sort(a); return a[0];}
	public static long max (long... a) {Arrays.sort(a); return a[a.length-1];}
	public static long pow (long c, long b) {
		long res = 1;
		for (int i=0; i<b; i++) {
			res *= c;
		}
		return res;
	}
	
	// class
	
	public static class Edge implements Comparable<Edge> {
		int id, from, to, cost;
		Edge(int to, int cost) { //基本コレ
			this.to = to;
			this.cost = cost;
		}
		Edge(int from, int to, int cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}
		Edge(int id, int from, int to, int cost) {
			this.id = id;
			this.from = from;
			this.to = to;
			this.cost = cost;
		}
		@Override public int compareTo (Edge e) {
			return this.cost - e.cost;
		}
	}
	
	public static class Point implements Comparable<Point> {
		int x, y;
		Point (int x, int y) {
			this.x = x;
			this.y = y;
		}
		@Override public int compareTo (Point p) {
			return this.y - p.y;
		}
	}
	
	/*   
	 * ##############################################################################################
	 * #  input
	 * ##############################################################################################
	 */
	
	// input - fields
	
	public static final InputStream in = System.in;
	public static final byte[] buffer = new byte[1024];
	public static int ptr = 0;
	public static int bufferLength = 0;
	
	// input - basic methods
	
	public static boolean hasNextByte() {
		if (ptr < bufferLength) {
			return true;
		}
		else {
			ptr = 0;
			try {
				bufferLength = in.read(buffer);
			}
			catch (IOException e) {
				e.printStackTrace();
			}
			if (bufferLength <= 0) {
				return false;
			}
		}
		return true;
	}
	
	public static int readByte() {
		if (hasNextByte()) return buffer[ptr++];
		else return -1;
	}

	public static boolean isPrintableChar(int c) {
		return 33 <= c && c <= 126;
	}

	public static void skipUnprintable() {
		while (hasNextByte() && !isPrintableChar(buffer[ptr])) ptr++;
	}

	public static boolean hasNext() {
		skipUnprintable();
		return hasNextByte();
	}
	
	// input - single
	
	public static String next() {
		if (!hasNext()) throw new NoSuchElementException();
		StringBuilder sb = new StringBuilder();
		int b = readByte();
		while (isPrintableChar(b)) {
			sb.appendCodePoint(b);
			b = readByte();
		}
		return sb.toString();
	}
	
	public static int nextInt() {
		return (int) nextLong();
	}
	
	public static long nextLong() {
		if (!hasNext()) throw new NoSuchElementException();
		long n = 0;
		boolean minus = false;
		int b = readByte();
		if (b == '-') {
			minus = true;
			b = readByte();
		}
		if (b < '0' || '9' < b) {
			throw new NumberFormatException();
		}
		while (true) {
			if ('0' <= b && b <= '9') {
				n *= 10;
				n += b - '0';
			} else if (b == -1 || !isPrintableChar(b)) {
				return minus ? -n : n;
			} else {
				throw new NumberFormatException();
			}
			b = readByte();
		}
	}

	public static double nextDouble() {
		return Double.parseDouble(next());
	}
	
	// input - array
	
	public static String[] nextStringArray(int n) {
		String[] array = new String[n];
		for (int i = 0; i < n; i++) array[i] = next();
		return array;
	}
	
	public static int[] nextIntArray(int n) {
		int[] array = new int[n];
		for (int i = 0; i < n; i++) array[i] = nextInt();
		return array;
	}
	
	public static long[] nextLongArray(int n) {
		long[] array = new long[n];
		for (int i = 0; i < n; i++) array[i] = nextLong();
		return array;
	}
	
	public static double[] nextDoubleArray(int n) {
		double[] array = new double[n];
		for (int i = 0; i < n; i++) {
			array[i] = nextDouble();
		}
		return array;
	}
	
	// input - table
	
	public static char[][] nextCharTable(int h, int w) {
		char[][] array = new char[h][w];
		for (int i = 0; i < h; i++) array[i] = next().toCharArray();
		return array;
	}
	
	public static int[][] nextIntMap(int h, int w) {
		int[][] a = new int[h][];
		for (int i=0; i<h; i++) {
			for (int j=0; j<w; j++) a[i][j] = nextInt();
		}
		return a;
	}
	
	/*   
	 * ##############################################################################################
	 * #  output
	 * ##############################################################################################
	 */
	
	// output - fields
	
	static PrintWriter out = new PrintWriter(System.out);
	
	//output - single
	
	public static void print(Object o) {out.print(o);}
	public static void println(Object o) {out.println(o);}
	
	//output - array
	
	public static void printStringArray(String[] a) {
		for (int i=0; i<a.length; i++) {
			if (i != 0) print(" ");
			print(a[i]);
		}
		println("");
	}
	
	public static void printIntArray(int[] a) {
		for (int i=0; i<a.length; i++) {
			if (i != 0) print(" ");
			print(a[i]);
		}
		println("");
	}
	
	public static void printLongArray(long[] a) {
		for (int i=0; i<a.length; i++) {
			if (i != 0) print(" ");
			print(a[i]);
		}
		println("");
	}
	
	public static void printBooleanArray (boolean[] a) {
		for (int i=0; i<a.length; i++) {
			char c = a[i]==true? 'o' : 'x';
			print(c);
		}
		println("");
	}
	
	public static void printCharTable(char[][] a) {
		for (int i=0; i<a.length; i++) {
			for (int j=0; j<a[0].length; j++) {
				print(a[i][j]);
			}
			println("");
		}
	}
	
	public static void printIntTable(int[][] a) {
		for (int i=0; i<a.length; i++) {
			for (int j=0; j<a[0].length; j++) {
				if (j != 0) print(" ");
				print(a[i][j]);
			}
			println("");
		}
	}
	
	public static void printBooleanTable(boolean[][] b) {
		for (int i=0; i<b.length; i++) {
			for (int j=0; j<b[0].length; j++) {
				print(b[i][j]? "o" : "x");
			}
			println("");
		}
	}
	
	public static void printLongTable(long[][] a) {
		for (int i=0; i<a.length; i++) {
			for (int j=0; j<a[0].length; j++) {
				if (j != 0) print(" ");
				print(a[i][j]);
			}
			println("");
		}
	}
	
	/*   
	 * ##############################################################################################
	 * #  main
	 * ##############################################################################################
	 */
	
	public static void main(String[] args) {
		new Thread(null, new Main(), "", 64 * 1024 * 1024).start();
	}
	
	public void run() {
		solve();
		out.close();
	}
	
}