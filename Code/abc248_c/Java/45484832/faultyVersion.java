import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(), m = in.nextInt(), k = in.nextInt();
        int MOD = 998255353;
        long[] f = new long[k + 1];
        f[0] = 1;
        long ans = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = k; j >= 0; j--) {
                f[j] = 0;
                for (int l = 1; l <= j && l <= m; l++) {
                    f[j] = (f[j] + f[j - l]) % MOD;
                }
            }
        }
        for (int j = 0; j <= k; j++) {
            ans = (ans + f[j]) % MOD;
        }
        System.out.println(ans);
    }
}