import java.util.*;

class Main {
    public static void main(String[] args) {
        final int mod = 998244353;
        Scanner scan = new Scanner(System.in);
        int Q = scan.nextInt();
        int K = scan.nextInt();

        long[] dp = new long[K + 1];
        dp[0] = 1;
        while (Q-- > 0) {
            String op = scan.next();
            int x = scan.nextInt();
            if (op.equals("+")) {
                for (int i = K;i >= x;i --)
                    dp[i] = (dp[i] + dp[i - x]) % mod;
            } else {
                for (int i = x;i <= K;i ++)
                    dp[i] = (dp[i] - dp[i - x] + mod) % mod;
            }
            System.out.println(dp[K]);
        }

    }
}
