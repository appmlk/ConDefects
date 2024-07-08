import java.io.*;
import java.util.*;

public class Main {

    static Scanner sc;
    static PrintWriter out;

    public static void main(String[] args) {
        sc = new Scanner(System.in);
        out = new PrintWriter(System.out);
        new Main().solve();
        out.flush();
    }

    public void solve() {
        long n = sc.nextLong();
        int a0 = sc.nextInt();
        int a1 = sc.nextInt();
        int a2 = sc.nextInt();
        long mod = 998244353;
        long[][][][][][][] dp = new long[63][a0][a1][a2][2][2][2];
        long[][] pow = new long[11][63];
        for(int i=1; i<=10; i++) {
            pow[i][0] = 1;
            for(int j=1; j<63; j++) {
                pow[i][j] = pow[i][j-1] * i;
            }
        }
        dp[62][0][0][0][0][0][0] = 1;
        for(int i=61; i>=0; i--) {
            int b = (int)((n>>i)&1);
            for(int i0 = 0 ; i0 < a0; i0++) {
                for(int i1 = 0; i1 < a1; i1++) {
                    for (int i2 = 0; i2 < a2; i2++) {
                        for (int j0 = 0; j0 < 2; j0++) {
                            for (int j1 = 0; j1 < 2; j1++) {
                                int j2 = j0 ^ j1;
                                int ni0 = (int) ((i0 + (j0 * pow[2][i])) % a0);
                                int ni1 = (int) ((i1 + (j1 * pow[2][i])) % a1);
                                int ni2 = (int) ((i2 + (j2 * pow[2][i])) % a2);
                                if (b == 0) {
                                    if (j0 == 1 && j1 == 1) {
                                        dp[i][ni0][ni1][ni2][1][1][1] += dp[i + 1][i0][i1][i2][1][1][1];
                                        dp[i][ni0][ni1][ni2][1][1][0] += dp[i + 1][i0][i1][i2][1][1][0];
                                    } else if (j0 == 1 && j2 == 1) {
                                        dp[i][ni0][ni1][ni2][1][1][1] += dp[i + 1][i0][i1][i2][1][1][1];
                                        dp[i][ni0][ni1][ni2][1][0][1] += dp[i + 1][i0][i1][i2][1][0][1];
                                    } else if (j1 == 1 && j2 == 1) {
                                        dp[i][ni0][ni1][ni2][1][1][1] += dp[i + 1][i0][i1][i2][1][1][1];
                                        dp[i][ni0][ni1][ni2][0][1][1] += dp[i + 1][i0][i1][i2][0][1][1];
                                    } else {
                                        dp[i][ni0][ni1][ni2][1][1][1] += dp[i + 1][i0][i1][i2][1][1][1];
                                        dp[i][ni0][ni1][ni2][1][1][0] += dp[i + 1][i0][i1][i2][1][1][0];
                                        dp[i][ni0][ni1][ni2][1][0][1] += dp[i + 1][i0][i1][i2][1][0][1];
                                        dp[i][ni0][ni1][ni2][1][0][0] += dp[i + 1][i0][i1][i2][1][0][0];
                                        dp[i][ni0][ni1][ni2][0][1][1] += dp[i + 1][i0][i1][i2][0][1][1];
                                        dp[i][ni0][ni1][ni2][0][1][0] += dp[i + 1][i0][i1][i2][0][1][0];
                                        dp[i][ni0][ni1][ni2][0][0][1] += dp[i + 1][i0][i1][i2][0][0][1];
                                        dp[i][ni0][ni1][ni2][0][0][0] += dp[i + 1][i0][i1][i2][0][0][0];
                                    }
                                } else {
                                    dp[i][ni0][ni1][ni2][1][1][1]                += dp[i + 1][i0][i1][i2][1][1][1];
                                    dp[i][ni0][ni1][ni2][1][1][1 - j2]           += dp[i + 1][i0][i1][i2][1][1][0];
                                    dp[i][ni0][ni1][ni2][1][1 - j1][1]           += dp[i + 1][i0][i1][i2][1][0][1];
                                    dp[i][ni0][ni1][ni2][1][1 - j1][1 - j2]      += dp[i + 1][i0][i1][i2][1][0][0];
                                    dp[i][ni0][ni1][ni2][1 - j0][1][1]           += dp[i + 1][i0][i1][i2][0][1][1];
                                    dp[i][ni0][ni1][ni2][1 - j0][1][1 - j2]      += dp[i + 1][i0][i1][i2][0][1][0];
                                    dp[i][ni0][ni1][ni2][1 - j0][1 - j1][1]      += dp[i + 1][i0][i1][i2][0][0][1];
                                    dp[i][ni0][ni1][ni2][1 - j0][1 - j1][1 - j2] += dp[i + 1][i0][i1][i2][0][0][0];
                                }
                                dp[i][ni0][ni1][ni2][1][1][1] %= mod;
                                dp[i][ni0][ni1][ni2][1][1][0] %= mod;
                                dp[i][ni0][ni1][ni2][1][0][1] %= mod;
                                dp[i][ni0][ni1][ni2][1][0][0] %= mod;
                                dp[i][ni0][ni1][ni2][0][1][1] %= mod;
                                dp[i][ni0][ni1][ni2][0][1][0] %= mod;
                                dp[i][ni0][ni1][ni2][0][0][1] %= mod;
                                dp[i][ni0][ni1][ni2][0][0][0] %= mod;
                            }
                        }
                    }
                }
            }
        }
        long res = 0;
        res += dp[0][0][0][0][1][1][1];
        res += dp[0][0][0][0][1][1][0];
        res += dp[0][0][0][0][1][0][1];
        res += dp[0][0][0][0][1][0][0];
        res += dp[0][0][0][0][0][1][1];
        res += dp[0][0][0][0][0][1][0];
        res += dp[0][0][0][0][0][0][1];
        res += dp[0][0][0][0][0][0][0];
        res += mod - n / lcm(a0, a1) % mod;
        res += mod - n / lcm(a0, a2) % mod;
        res += mod - n / lcm(a1, a2) % mod;
        res += mod - 1;
        res %= mod;
        out.println(res);
    }
    static int lcm(int m, int n) {
        return m / gcd(m, n) * n;
    }
    static int gcd(int m, int n) {
        if(m < n) return gcd(n, m);
        if(n == 0) return m;
        return gcd(n, m % n);
    }
}
