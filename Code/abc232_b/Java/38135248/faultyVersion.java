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
		
		char[] s = nextCharArray();
		String t = next();
		
		for (int i=1; i<=25; i++) {
			if (t.equals(slide(s, i))) {
				println("Yes");
				return;
			}
		}
		
		println("No");
		
	}
	
	public static String slide (char[] a, int n) {
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<a.length; i++) {
			int c = a[i] + n;
			if (c > 122) c -= 26;
			sb.append((char)c);
		}
		return sb.toString();
	}

	public static String yesno(boolean b) {return b?"Yes":"No";}
	public static int[] accumulateSum (int[] a) {
		int[] b = new int[a.length+1];
		for (int i=1; i<a.length+1; i++) {
			b[i] = b[i-1] + a[i-1];
		}
		return b;
	}
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