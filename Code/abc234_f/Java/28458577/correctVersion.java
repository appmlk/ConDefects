
import java.io.*;
import java.util.*;

public class Main {


	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static final int MM = 5005, MOD = 998244353;
	static long[] freq = new long[26], dp[] = new long[26][MM], fact = new long[MM], inv = new long[MM];
	static long ans;
	
	static long pow(long n, int t) {
		if (t == 0) return 1;
		long res = pow(n, t/2);
		res = res * res % MOD;
		if (t % 2 == 1) res = res * n % MOD;
		return res % MOD;
	}
	
	static long c(int n, int k) {
		if (n < k || n < 0 || k < 0) return 0;
		return fact[n] * inv[k] % MOD * inv[n-k] % MOD;
	}
	
	public static void main(String[] args) throws IOException {

		String s = readLine();
		
		for (char c: s.toCharArray()) {
			freq[c - 'a'] ++;
		}
		
		fact[0] = 1; inv[0] = 1;
		for (int i=1; i<MM; i++) {
			fact[i] = i * fact[i-1] % MOD;
			inv[i] = pow(fact[i], MOD-2) % MOD;
			if (i <= freq[0]) dp[0][i] = 1;
		}
		
		dp[0][0] = 1;
		
		for (int i=1; i<26; i++) {
			for (int j=0; j<=s.length(); j++) {
				for (int k=0; k<=Math.min(j, freq[i]); k++) {
					dp[i][j] += dp[i-1][j-k] * c(j, k);
					dp[i][j] %= MOD;
				}
			}
		}
		
		for (int j=1; j<=s.length(); j++) {
			ans = (ans + dp[26-1][j]) % MOD;
		}
		
		
		System.out.println(ans % MOD);
		
		
	}
	static String next () throws IOException {
		while (st == null || !st.hasMoreTokens())
			st = new StringTokenizer(br.readLine().trim());
		return st.nextToken();
	}
	static String readLine () throws IOException {
		return br.readLine().trim();
	}
	static int readInt () throws IOException {
		return Integer.parseInt(next());
	}
	
}


	
	
