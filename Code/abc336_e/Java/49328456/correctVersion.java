import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.*;

public class Main {
    static int[] dig = new int[15];
    static long[][][] dp;
    static int p;
    public static void main(String[] args) throws IOException {
         Scanner input = new Scanner(System.in);
         long n = input.nextLong();
         dp = new long[15][127][127];
         p = 0;
         while (n > 0){
             dig[p++] =(int) (n % 10);
             n /= 10;
         }
         long res = 0;
        for (int k = 1; k <= 126; k++) {
            for (int i = 0; i < 15; i++) {
                for (int j = 0; j < 127; j++) {
                    Arrays.fill(dp[i][j], -1);
                }
            }
            res += dfs(p - 1, 0, 0, k, false);
        }
        System.out.println(res);
    }

    public static long dfs(int u, int s, int t, int k, boolean lim){
        if (u == -1 && t == 0 && s == k) return 1;
        if (u == -1) return 0;
        if (lim && dp[u][s][t] != -1) return dp[u][s][t];
        int x = lim ? 9 : dig[u];
        long ans = 0;
        for (int i = 0; i <= x; i++) {
            ans += dfs(u - 1, s+i, (t * 10 + i) % k, k, i != x || lim);
        }
        if (lim) dp[u][s][t] = ans;
        return ans;
    }
}