import java.util.*;

class Main {
	Scanner sc = new Scanner(System.in);
	long X, Y, A, B, C;
	
	String calc() {
		X = sc.nextLong();
		Y = sc.nextLong();
		A = sc.nextLong();
		B = sc.nextLong();
		C = sc.nextLong();
		
		if (check(X, Y, A, B, C)
			|| check(Y, X, A, B, C)
			|| check(X, Y, B, A, C)
			|| check(Y, X, B, A, C)
			|| check(X, Y, C, A, B)
			|| check(Y, X, C, A, B)) return "Yes";
		return "No";
	}
	
	boolean check(long w, long h, long w1, long r1, long r2) {
		long h1 = (w1+w-1)/w;
		return (last2(w, h-h1, r1, r2) || last2(h-h1, w, r1, r2));
	}
	
	boolean last2(long w, long h, long r1, long r2) {
		if (w == 0) return false;
		long h1 = (r1+w-1)/w;
		return ((r2+w-1)/w <= h-h1);
	}
	
	public static void main(String[] args) {
		System.out.println(new Main().calc());
	}
}
