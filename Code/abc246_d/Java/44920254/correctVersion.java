import java.util.Scanner;

public class Main {
	
	public static long f(long a, long b) {
		return a*a*a + a*a*b + a*b*b + b*b*b;
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		long X = sc.nextLong();
		long i = 0;
		long j = 1000000;
		long ans = Long.MAX_VALUE;
		for(; i <= j; i++) {
			
			while(f(i,j) >= X) {
				ans = Math.min(ans, f(i,j));
				j--;
			}
		}
		
		System.out.println(ans);
	}

}
