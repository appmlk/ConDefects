import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();

        int[] sc = new int[n];
        int[] bon = new int[n + 1];

        for (int i = 0; i < n; i++) {
            sc[i] = in.nextInt();
        }

        for (int i = 0; i < m; i++) {
            bon[in.nextInt()] = in.nextInt();
        }

        long[][] dp = new long[n + 1][n + 1];
        long ans = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < n; j++) {
                if (j == 0){
                    dp[i][j] = ans;
                }
                else if (j > i) break;

                else{
                    dp[i][j] = dp[i - 1][j - 1] + sc[i - 1] + bon[j];
                    ans = Math.max(ans, dp[i][j]);
                }
            }
        }
        System.out.println(ans);
    }
}