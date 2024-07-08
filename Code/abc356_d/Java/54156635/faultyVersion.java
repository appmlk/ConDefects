import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		try(Scanner sc = new Scanner(System.in);) {
			long n = sc.nextLong();
			long m = sc.nextLong();
			long res = 0;
			final long mod = 998244353;
			for(long i = 0; i < 60; i++) {
				if((m & 1 << i) > 0) {
					res += f(i, n);
					res %= mod;
				}
			}
			
			System.out.println(res);
		}
	}
	
	static long f(long j, long n) {
		long p2 = 1L << j;
		long k = n /(2 * p2);
		long res = k * p2;
		long l = n % (2 * p2);
		if(l >= p2) {
			res += (l - p2 + 1);
		}
		return res;
	}
	
}

