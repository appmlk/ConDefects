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
        init(2000010);
        int n = sc.nextInt();
        for(int i=0; i<n; i++) {
            solve2();
        }
    }

    void solve2() {
        int n = sc.nextInt();
        if(n==2) {
            out.println(1);
            return;
        }
        long res = (n-1) % mod * n %mod * n %mod * ((n * n %mod + mod - 3) % mod) % mod * fac[2*n-4] % mod * finv[n] % mod * finv[n] % mod;
        res %= mod;
        out.println(res);

    }


    private static long mod = 998244353;
    static long[] fac;
    static long[] finv;
    static long[] inv;
    static void init(int max) {
        fac = new long[max];
        finv = new long[max];
        inv = new long[max];
        fac[0] = fac[1] = 1;
        finv[0] = finv[1] = 1;
        inv[1] = 1;
        for(int i=2; i<max; i++) {
            fac[i] = fac[i-1] * i % mod;
            inv[i] = mod - inv[(int)mod%i] * (mod / i) % mod;
            finv[i] = finv[i-1] * inv[i] % mod;
        }
    }
    static long conv(int n, int k) {
        if(n<k || n<0 || k<0) return 0;
        return fac[n] * (finv[k]*finv[n-k]%mod) % mod;
    }
}
