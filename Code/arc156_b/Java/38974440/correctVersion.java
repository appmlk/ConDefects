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
        init(400010);
        int n = sc.nextInt();
        int k = sc.nextInt();
        boolean[] b = new boolean[400010];
        for(int i=0; i<n; i++) {
            b[sc.nextInt()]=true;
        }
        long res = 0;
        for(int i=0; i<400010 && k>=0; i++) {
            if(!b[i]) {
                res += conv(k+i-1, i-1);
                res %= mod;
                k--;
            }
        }
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
    static long perm(int n, int k) {
        if(n<k || n<0 || k<0) return 0;
        return fac[n] * finv[n-k] % mod;
    }
}
