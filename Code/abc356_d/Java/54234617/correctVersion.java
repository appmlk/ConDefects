import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		try(Scanner sc = new Scanner(System.in);) {
			long n = sc.nextLong();
			long m = sc.nextLong();
			long l = 60;
			long mod = 998244353;
			long ans = 0;
			for(long i = 0; i < l; i++) {
				if((m >> i & 1) > 0) {
					long k = (n + 1) / (2L << i);
					long r = (n + 1) % (2L << i);
					ans += k << i;
					ans += Math.max(r - (1L << i), 0);
					ans %= mod;
				}
			}
			System.out.println(ans);
		}
	}
}

