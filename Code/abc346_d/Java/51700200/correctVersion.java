
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        char[] S = sc.next().toCharArray();
        int[] C = new int[N];
        for (int i = 0; i < N; i++) {
            C[i] = sc.nextInt();
        }
        long[][][] dp = new long[N][2][2];
        dp[0][0][0] = 0;
        dp[0][0][1] = C[0];
        dp[0][1][0] = Integer.MAX_VALUE;
        dp[0][1][1] = Integer.MAX_VALUE;

        dp[1][1][1] = S[0] == S[1] ? C[0]+C[1]: C[1];
        dp[1][0][1] = S[0] == S[1] ? C[1] : C[0] + C[1];
        dp[1][0][0] = S[0] == S[1] ? C[0] : 0;
        dp[1][1][0] = S[0] == S[1] ? 0 : C[0];

        for (int i = 2; i < S.length; i++) {
            dp[i][0][0] = S[i] == S[i - 1] ? dp[i - 1][0][1] : dp[i - 1][0][0];
            dp[i][1][0] = S[i] == S[i - 1] ? Math.min(dp[i - 1][1][1], dp[i - 1][0][0]) : Math.min(dp[i - 1][0][1], dp[i - 1][1][0]);
            dp[i][0][1] = S[i] == S[i - 1] ? dp[i - 1][0][0] + C[i] : dp[i - 1][0][1] + C[i];
            dp[i][1][1] = S[i] == S[i - 1] ? Math.min(dp[i - 1][1][0], dp[i - 1][0][1]) + C[i] : Math.min(dp[i - 1][1][1], dp[i - 1][0][0]) + C[i];
        }

        System.out.println(Math.min(dp[S.length - 1][1][0], dp[S.length - 1][1][1]));
    }
}
