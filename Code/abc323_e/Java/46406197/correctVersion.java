
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        /**
         * 要求第 x + 0.5 秒播放第一首歌的概率
         * ans = 1 / n * (p[x - t1 + 1] + ... + p[x - 1] + p[x])
         * 上式表示在第 x,x - 1 ,..., x - t1 + 1 秒发生切歌
         * 乘 1 / n 是因为切到第一首歌的概率为 1 / n
         *
         *
         */
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), x = sc.nextInt();
        int[] t = new int[n];
        for (int i = 0; i < n; i++) {
            t[i] = sc.nextInt();
        }
        long v = pow(n, mod - 2);
        long[] p = new long[x + 1];
        //p[i] 表示第 i 秒切歌的几率
        p[0] = 1;
        for (int i = 1; i <= x; i++) {
            for (int j = 0; j < n; j++) {
                if (i >= t[j]) {
                    p[i] += p[i - t[j]] * v;
                    p[i] %= mod;
                }
            }


        }
        long ans = 0;
        for (int i = x; i >= 0 && i >= x - t[0] + 1; i--) {
            ans += p[i] * v;
            ans %= mod;

        }
        System.out.println(ans);

    }

    static int mod = 998244353;

    static long pow(long a, long n) {
        if (n == 0) return 1;
        if (n % 2 == 0) return pow(a * a % mod, n / 2);
        else return pow(a * a % mod, n / 2) * a % mod;
    }

}
