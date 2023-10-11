import java.io.PrintWriter;
import java.math.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

class Main {
	static void solve () {
		
		//n = 5 の場合、1~5までで必ず構成される
		//スターの場合、いずれかがn-1回、他がそれぞれ1回ずつ登場する
		//個数カウントでいけそうだけどな～
		//1のみ通して、1以外が来たらそれがn-1であることを確認する。以降はフラグで閉鎖する感じで
		int n = nextInt();
		Map<Integer, Integer> map = new HashMap<>();
		for (int i=0; i<n-1; i++) {
			int a = nextInt();
			if (map.get(a) == null) map.put(a, 1);
			else map.put(a, map.get(a) + 1);
			int b = nextInt();
			if (map.get(b) == null) map.put(b, 1);
			else map.put(b, map.get(b) + 1);
		}
		
		if (map.size() != n) {
			println("No");
			return;
		}
		
		boolean flag = false;
		for (Map.Entry<Integer, Integer> e: map.entrySet()) {
			if (e.getValue() != 1) {
				
				if (flag == false && e.getValue() == n - 1) {
					println(e.getKey() + " " + e.getValue());
					flag = true;
				}
				else if (flag == true) {
					println("No");
					return;
				}
			}
        }
		println(yesno(flag));
		
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




