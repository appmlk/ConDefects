import java.io.*;
import java.math.*;
import java.time.*;
import java.util.*;
import java.util.Map.Entry;

class Main implements Runnable {

	public static void solve () {
		
		//同じ要素=良い線を構成できるということ
		
		int n = nextInt();
		int[] a = nextIntArray(n);
		
		long all = 0;
		
		for (int i=1; i<=n; i++) {
			all += 1L * (n+1-i) * (i/2);
		}
		
		//allから良い線を引いてみる
		//ある良い線があることによって、何個の部分文字列で「交換する回数が1回減ったか」を考える
		
		long good = 0;
		
		List<List<Integer>> list = new ArrayList<>();
		for (int i=0; i<=n; i++) list.add(new ArrayList<>());
		for (int i=0; i<n; i++) list.get(a[i]).add(i);
		
		for (int i=0; i<=n; i++) {
			
			int iSize = list.get(i).size();
			if (iSize == 0) continue;
			int l = 0, r = iSize - 1;
			while (l < r) {
				if (list.get(i).get(l) < n+1-list.get(i).get(r)) {
					good += 1L * (r-l) * list.get(i).get(l);
					l += 1;
				}
				else {
					good += 1L * (r-l) * (n+1-list.get(i).get(r));
					r -= 1;
				}
			}
			
		}
		
		println(all - good);
		
	}
	
	public static long nCr (long n, long r) {
		long result = 1;
		for (int i = 1; i <= r; i++) {
			result = result * (n - i + 1) / i;
		}
		return result;
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
	
	public static int[][] nextIntTable(int h, int w) {
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