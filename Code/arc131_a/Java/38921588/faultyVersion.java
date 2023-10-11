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
		
		//98765432
		long a = 500000000;
		long b = 98765431;
		long c = 98765431;
		
		long A = nextLong(), B = nextLong();
		println(a*A*B);
		
		
//		println((a*b+c));
//		println(2*(a*b+c));
//		println((long)Math.pow(10, 18));
		
	}
	
	
	
	
	/////////////////////////////////////////////////////////////////////////////////////////////////
	// useful methods, useful fields, useful static inner class
	/////////////////////////////////////////////////////////////////////////////////////////////////
	public static final int infi = (int)1e9;
	public static final long infl = (long)1e18;
	public static final int modi = (int)1e9 + 7;
	public static final long modl = (long)1e18 + 7;
	public static int[] dy= {-1, 0, 1, 0};
	public static int[] dx = {0, 1, 0, -1};
	
	public static class Edge {
		int id, from, to, cost;
		Edge(int to, int cost) {
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
		int getCost() {return this.cost;}
		
	}
	
	
	public static String yesno(boolean b) {return b ? "Yes" : "No";}
	public static class Grid {
		int h, w;
		Point[][] p;
		boolean[][] seen;

		Grid(int h, int w) {
			p = new Point[h][w];
			this.h = h;
			this.w = w;
			for (int i=0; i<h; i++) {
				String s = next();
				for (int j=0; j<w; j++) {
					p[i][j] = new Point(i, j, s.charAt(j));
				}
			}
		}
		public Point getPoint(int y, int x) {
			return this.p[y][x];
		}
		public List<Point> getAround4(Point p) {
			int y = p.y, x = p.x;
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
		
		
		public int countConnectedComponents() {

			int count = 0;
			seen = new boolean[h][w];

			while(true) {

				Point start = null;

				//全ての連結成分を探索したかチェック
				boolean flag = true;
				for (int i=0; i<h; i++) {
					for (int j=0; j<w; j++) {
						if (seen[i][j] == false && p[i][j].c == '#') {
							flag = false;
							start = new Point(i, j);
						}
					}
				}
				if (flag == true) break;

				Deque<Point> q = new ArrayDeque<>();
				q.add(start);
				seen[start.y][start.x] = true;

				//BFS
				while(q.size() > 0) {
					Point now = q.removeFirst();
					for (Point next : getAround4(now)) {
						if (seen[next.y][next.x] == false && p[next.y][next.x].c == '#') {
							seen[next.y][next.x] = true;
							q.addLast(next);
						}
					}
				}

				count++;

			}
			return count;
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
	public static char[] nextCharArray() {return next().toCharArray();}
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
				print(a[i][j]==infl? "# " : a[i][j]+" ");
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