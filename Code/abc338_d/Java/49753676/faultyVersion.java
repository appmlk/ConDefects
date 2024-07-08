import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		try(Scanner sc = new Scanner(System.in);) {
			int n = Integer.parseInt(sc.next());
			int m = Integer.parseInt(sc.next());
			int[] x = new int[m];
			long[] v = new long[n + 1];
			for(int i = 0; i < m; i++) x[i] = Integer.parseInt(sc.next()) - 1;
			for(int i = 0; i < m - 1; i++) {
				add(x[i], x[i + 1], dist(x[i + 1], x[i], n), v, n);
				add(x[i + 1], x[i], dist(x[i], x[i + 1], n), v, n);
			}
			
			long ans = 1 << 61;
			for(int i = 0; i < n; i++) {
				v[i + 1] += v[i];
				ans = Math.min(ans, v[i]);
			}
			
			System.out.println(ans);
		}
	}
	
	static int dist(int from, int to, int n) {
		if(from <= to) return to - from;
		else return to + n - from;
	}
	
	static void add(int from ,int to, int num, long[] v, int n) {
		if(from <= to) {
			v[from] += num;
			v[to] -= num;
		} else {
			v[from] += num;
			v[n] -= num;
			v[0] += num;
			v[to] -= num;
		}
	}
}
