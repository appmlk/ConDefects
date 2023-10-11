import java.io.PrintWriter;
import java.math.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.Map.Entry;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

class Main {
	static void solve () {
		
		int n = nextInt();
		char[][] a = new char[n*3][n*3];

		for (int i=0; i<n; i++) {
			String s = next();
			for (int j=0; j<n; j++) {
				a[i][j] = s.charAt(j);
				a[i][j+n] = a[i][j];
				a[i][j+n*2] = a[i][j];
				a[i+n][j] = a[i][j];
				a[i+n][j+n] = a[i][j];
				a[i+n][j+n*2] = a[i][j];
				a[i+n*2][j] = a[i][j];
				a[i+n*2][j+n] = a[i][j];
				a[i+n*2][j+n*2] = a[i][j];
			}
		}
		
		
		int[] sy = {-1, -1, -1, 0, 1, 1,  1,  0};
		int[] sx = {-1,  0,  1, 1, 1, 0, -1, -1};
		
		
		long max = Integer.MIN_VALUE;
		
		for (int i=n; i<2*n; i++) {
			for (int j=n; j<2*n; j++) {

				for (int k=0; k<8; k++) {
					
					Point p = new Point(i, j);
					StringBuilder sb = new StringBuilder();
					sb.append(a[p.y][p.x]);
					
					for (int l=0; l<n; l++) {
						p.y += sy[k];
						p.x += sx[k];
						sb.append(a[p.y][p.x]);
					}
					max = Math.max(max, Long.parseLong(sb.toString()));
				}
				
			}
		}
		
		println(max);
		
	}
	
	public static String yesno(boolean b) {return b?"Yes":"No";}
	public static void print(Object o) {out.print(o);}
	public static void println(Object o) {out.println(o);}
	
	public static String next() {return in.next();}
	public static char nextChar() {return next().charAt(0);}
	public static int nextInt() {return in.nextInt();}
	public static Double nextDouble() {return in.nextDouble();}
	public static Long nextLong() {return in.nextLong();}
	public static int[] nextIntArray(int n) {
		int[] a = new int[n];
		for (int i=0; i<n; i++) a[i] = nextInt();
		return a;
	}
	public static String[] nextStringArray(int n) {
		String[] a = new String[n];
		for (int i=0; i<n; i++) a[i] = next();
		return a;
	}
	public static char[] nextCharArray() {
		return next().toCharArray();
	}
	public static char[][] nextCharTable(int h, int w) {
		char[][] a = new char[h][w];
		for (int i=0; i<h; i++) {
			a[i] = next().toCharArray();
		}
		return a;
	}
	public static void printCharTable(char[][] a) {
		for (int i=0; i<a.length; i++) {
			for (int j=0; j<a[0].length; j++) {
				print(a[i][j]);
			}
			println("");
		}
	}
	
	static Scanner in = new Scanner(System.in);
	static PrintWriter out = new PrintWriter(System.out);
	public static void main(String[] args) {
		solve();
		in.close();
		out.close();
	}
}

class Point {
	int y, x;
	Point (int y, int x) {
		this.y = y;
		this.x = x;
	}
	public int getY () {return y;}
	public int getX () {return x;}
}