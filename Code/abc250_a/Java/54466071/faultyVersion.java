import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		MyScanner sc = new MyScanner();
		
		int H = sc.nextInt(), W = sc.nextInt();
		int R = sc.nextInt(), C = sc.nextInt();
		
		int ans = 4;
		if(R==H) ans--;
		if(R==0) ans--;
		if(C==W) ans--;
		if(C==1) ans--;
		
		System.out.println(ans);
	}
}

class MyScanner {
	public Scanner sc = new Scanner(System.in);
	public int nextInt() {
		return Integer.parseInt(sc.next());
	}
	public long nextLong() {
		return Long.parseLong(sc.next());
	}
	public double nextDouble() {
		return Double.parseDouble(sc.next());
	}
	public String next() {
		return sc.next();
	}
}