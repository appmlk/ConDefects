import java.io.*;
import java.math.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.Map.Entry;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

class Main implements Runnable {
	
	public static void solve () {
		
		int n = nextInt(), m = nextInt();
		int[] x = new int[n+1];
		for (int i=1; i<=n; i++) x[i] = nextInt();
		int[] b = new int[n+1];
		for (int i=1; i<=n; i++) {
			b[nextInt()] = nextInt();
		}
		
		//x[i] i回目のコイントスで表が出た場合、貰える金額
		//b[i] カウンタの数値がiになったときに貰えるボーナス金額
		
		
		//dp[i][j] i回目のコイントスが終わってカウンタがjであるときの最大値
		long[][] dp = new long[n+1][n+1];
		for (int i=0; i<n+1; i++) Arrays.fill(dp[i], -1);
		dp[0][0] = 0;
		
		for (int i=1; i<=n; i++) {
			
			//表が出てカウンタを増やす場合
			//カウンタの値はi以上にはならないので
			for (int j=1; j<=i; j++) {
				//x[i] 表が出た場合に貰える金額を足す
				//b[j] 今の数字がボーナス対象であれば、それも足す
				dp[i][j] = dp[i-1][j-1] + x[i] + b[j];
			}
			
			//裏が出てカウンタを0にする場合
			dp[i][0] = 0;
			for (int j=0; j<i; j++) {
				dp[i][0] = Math.max(dp[i][0], dp[i-1][j]);
			}
			
		}
		
		long ans = 0;
		for (int i=0; i<n+1; i++) {
			ans = Math.max(ans, dp[n][i]);
		}
		
		println(ans);
		
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////
	// useful methods, useful fields, useful static inner class
	/////////////////////////////////////////////////////////////////////////////////////////////////
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
	
	public static class Edge {
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
		int getCost() {return this.cost;}
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
				print(a[i][j]==-infl? "# " : a[i][j]+" ");
			}
			println("");
		}
	}



	/////////////////////////////////////////////////////////////////////////////////////////////////
	// main method
	/////////////////////////////////////////////////////////////////////////////////////////////////
	public static void main(String[] args) {
		new Thread(null, new Main(), "", 64 * 1024 * 1024).start();
	}
	public void run() {
		solve();
		out.close();
	}
}