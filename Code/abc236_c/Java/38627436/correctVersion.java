import java.io.*;
import java.math.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.Map.Entry;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

class Main {
	
	public static void solve () {
		
		int n = nextInt(), m = nextInt();
		String[] s = nextArray(n);
		
		Set<String> set = new HashSet<>();
		for (int i=0; i<m; i++) set.add(next());
		
		for (int i=0; i<n; i++) {
			println(set.contains(s[i])? "Yes" : "No");
		}
		
	}
	
	
	/////////////////////////////////////////////////////////////////////////////////////////////////
	// useful methods, useful fields, useful static inner class
	/////////////////////////////////////////////////////////////////////////////////////////////////
	public static String yesno(boolean b) {return b ? "Yes" : "No";}
	public static class CharTable {
		int h, w;
		Point[][] table;
		CharTable(int h, int w) {
			table = new Point[h][w];
			this.h = h;
			this.w = w;
			for (int i=0; i<h; i++) {
				String s = next();
				for (int j=0; j<w; j++) {
					table[i][j] = new Point(i, j, s.charAt(j));
				}
			}
		}
		public Point getPoint(int y, int x) {
			return this.table[y][x];
		}
		public List<Point> getAround4(int y, int x) {
			List<Point> a = new ArrayList<>();
			if (y >= 1) a.add(new Point(y-1, x));
			if (y <= h-2) a.add(new Point(y+1, x));
			if (x >= 1) a.add(new Point(y, x-1));
			if (x <= w-2) a.add(new Point(y, x+1));
			return a;
		}
		public List<Point> getAround8(int y, int x) {
			List<Point> a = new ArrayList<>();
			if (y >= 1) {
				a.add(new Point(y-1, x));
				if (x >= 1) a.add(new Point(y-1, x-1));
				if (x <= w-2) a.add(new Point(y-1, x+1));
			}
			if (y <= h-2) {
				a.add(new Point(y+1, x));
				if (x >= 1) a.add(new Point(y+1, x-1));
				if (x <= w-2) a.add(new Point(y+1, x+1));
			}
			if (x >= 1) a.add(new Point(y, x-1));
			if (x <= w-2) a.add(new Point(y, x+1));
			return a;
		}
	}
	public static class Point {
		int y, x;
		char c;
		Point (int y, int x) {
			this.y = y;
			this.x = x;
		}
		Point (int y, int x, char c) {
			this.y = y;
			this.x = x;
			this.c = c;
		}
		public boolean isEqual(Point p) {
			if (this.y == p.y && this.x == p.x) return true;
			return false;
		}
		public int getY() {return this.y;}
		public int getX() {return this.x;}
		public char getC() {return this.c;}
	}
	public static class Edge {
		int a, b;
		Edge(int a, int b) {
			this.a = a;
			this.b = b;
		}
		public int getA() {return this.a;}
		public int getB() {return this.b;}
	}
	public static class Section {
		int s, e;
		Section(int s, int e) {
			this.s = s;
			this.e = e;
		}
		public int getS() {return this.s;}
		public int getE() {return this.e;}
	}
	
	public static class Triple {
		int a, b, c;
		Triple(int a, int b, int c) {
			this.a = a;
			this.b = b;
			this.c = c;
		}
		public int getA() {return this.a;}
		public int getB() {return this.b;}
		public int getC() {return this.c;}
	}
	
	
	/////////////////////////////////////////////////////////////////////////////////////////////////
	// input 
	/////////////////////////////////////////////////////////////////////////////////////////////////
	public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in), 32768);
	public static StringTokenizer tokenizer = null;
	public static String next() {
		while (tokenizer == null || !tokenizer.hasMoreTokens()) {
			try {
				tokenizer = new StringTokenizer(reader.readLine());
			}
			catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		return tokenizer.nextToken();
	}
	public static String[] nextArray(int n) {
		String[] a = new String[n];
		for (int i=0; i<n; i++) a[i] = next();
		return a;
	}
	public static int nextInt() {return Integer.parseInt(next());};
	public static int[] nextIntArray(int n) {
		int[] a = new int[n];
		for (int i=0; i<n; i++) a[i] = nextInt();
		return a;
	}
	public static int[][] nextIntTable(int n, int m) {
		int[][] a = new int[n][m];
		for (int i=0; i<n; i++) {
			for (int j=0; j<m; j++) a[i][j] = nextInt();
		}
		return a;
	}
	public static long nextLong() {return Long.parseLong(next());}
	public static long[] nextLongArray(int n) {
		long[] a = new long[n];
		for (int i=0; i<n; i++) a[i] = nextLong();
		return a;
	}
	public static double nextDouble() {return Double.parseDouble(next());}
	public static char nextChar() {return next().charAt(0);}
	public static char[][] nextCharTable(int n, int m) {
		char[][] a = new char[n][m];
		for (int i=0; i<n; i++) {
			a[i] = next().toCharArray();
		}
		return a;
	}
	public static List<List<Integer>> nextDirectedGraph(int n, int m) {
		List<List<Integer>> g = new ArrayList<>();
		for (int i=0; i<n; i++) {
			g.add(new ArrayList<>());
		}
		for (int i=0; i<m; i++) {
			int a = nextInt()-1, b = nextInt()-1;
			g.get(a).add(b);
			g.get(b).add(a);
		}
		return g;
	}
	public static List<List<Integer>> nextUndirectedGraph(int n, int m) {
		List<List<Integer>> g = new ArrayList<>();
		for (int i=0; i<n; i++) {
			g.add(new ArrayList<>());
		}
		for (int i=0; i<m; i++) {
			int a = nextInt()-1, b = nextInt()-1;
			g.get(a).add(b);
		}
		return g;
	}
	
	
	/////////////////////////////////////////////////////////////////////////////////////////////////
	// output
	/////////////////////////////////////////////////////////////////////////////////////////////////
	static PrintWriter out = new PrintWriter(System.out);
	public static void print(Object o) {out.print(o);}
	public static void println(Object o) {out.println(o);}
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
	
	
	
	/////////////////////////////////////////////////////////////////////////////////////////////////
	// main method
	/////////////////////////////////////////////////////////////////////////////////////////////////
	public static void main(String[] args) {
		solve();
		out.close();
	}
}