import java.io.PrintWriter;
import java.math.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

class Main {
	static void solve () {
		
		int n = nextInt();
		String s = next();
		s = s.replace("A", "BB");
		while (true) {
			if (s.contains("BB") == true) {
				s = s.replace("BB", "A");
			}
			else {
				break;
			}
		}
		println(s);
		
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
