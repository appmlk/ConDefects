import java.io.PrintWriter;
import java.util.Scanner;

public class Main {

    static Scanner sc;
    static PrintWriter out;

    public static void main(String[] args) {
        sc = new Scanner(System.in);
        out = new PrintWriter(System.out);
        new Main().solve();
        out.flush();
    }

    static long mod = 998244353;
    public void solve() {
        int n = sc.nextInt();
        int m = sc.nextInt();

        init(Math.max(n, n + (m-n) / 3) + 10);

        long res = 0;
        for(int s=0; s<=2; s++) {
            for(int b=0; b<=n-1; b++) {
                int last = s + (n-1) + b;
                if(last > m) break;
                int t = (m-last) / 3;
                res += conv(n-1, b) * conv(n-1+t, t) % mod;
                res %= mod;

                // res += n-1 C b * n-1+c C t


            }


        }
        out.println(res);

    }

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
