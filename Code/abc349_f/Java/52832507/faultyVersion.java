import java.util.*;

public class Main {

    static int k = 0;
    static long[] p = new long [20];
    static long[] c = new long [20];
    static long P = 998244353;
    static long[] dp = new long [1 << 21];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        long m = sc.nextLong();
        long base_m = m;

        for (long i = 2; i * i <= m; i++) {
            if (m % i == 0) {
                p[k] = i;
                while(m % i == 0) {
                    m /= i;
                    c[k] += 1;
                }
                k++;
            }
        }
        if (m > 1) {
            p[k] = m;
            c[k] = 1;
            k++;
        }

        for (int i = 0; i < n; i++) {
            var a = sc.nextLong();
            if (a == gcd(base_m, a)) {
                dp[encode(a)] += 1;
            }
        }

        for (int i = 0; i < k; i++) {
            for (int j = 0; j < 1 << k; j++) {
                if ((j >> i & 1) == 1) {
                    dp[j] += dp[j ^ (1 << i)];
                }
            }
        }

        long ans = 0;
        for (int i = 0; i < 1 << k; i++) {
            int count = 0;
            for(int j = 0; j < k; j++) {
                if ((i >> j & 1) == 1) count += 1;
            }
            if ((k & 1) == (count & 1)) ans = (ans + qpow(2, dp[i])) % P;
            else ans = (ans + P - qpow(2, dp[i])) % P;
        }

        System.out.println(m == 1 ? (ans + P - 1) % P : ans);
    }

    public static long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public static int encode(long x) {
        int code = 0;
        for (int i = 0; i < k; i++) {
            int temp = 0;
            while (x % p[i] == 0) {
                x /= p[i];
                temp += 1;
            }
            if (temp == c[i]) code |= 1 << i;
        }
        return code;
    }

    public static long qpow(long a, long n) {
        long res = 1;
        while (n > 0) {
            if ((n & 1) == 1) res = res * a % P;
            a = a * a % P;
            n >>= 1;
        }
        return res;
    }

}