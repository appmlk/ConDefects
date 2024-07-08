import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Main {

    //    private static int[][] dirs = {{-1,-1}, {1, 1}, {-1, 1}, {1, -1}};
    private static int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    private static long inf = (long) 1e13;
    private static long div = 998_244_353L;
//    private static long div = ((long)1e9) + 7;

    private static long pow(long a, long b) {
        if (b == 0) {
            return 1;
        } else if (b == 1) {
            return a;
        }

        long mid = pow(a, b/2);
        long res = mid * mid;
        if (b%2 == 1) {
            res *= a;
        }
        return res;
    }


    public static void main(String[] commands) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] parts = in.readLine().split(" ");
        int N = Integer.parseInt(parts[0]);
        double[][] dp = new double[N][N];
        parts = in.readLine().split(" ");

        int[] vals = new int[N];
        for(int i = 0;i < N; ++i) {
            vals[i] = Integer.parseInt(parts[i]);
        }

        for(int col = 0;col < N; ++col) {
            if (col == 0) {
                dp[0][0] = vals[0];
            } else {
                dp[0][col] = Math.max(dp[0][col - 1], vals[col]);
            }
        }

        for(int row = 1;row < N; ++row) {
            for(int col = row; col < N; ++col) {
                if (row == col) {
                    dp[row][col] = 0.9 * dp[row - 1][col - 1] + vals[col];
                } else {
                    dp[row][col] = Math.max(dp[row][col - 1], vals[col] + 0.9 * dp[row - 1][col - 1]);
                }
            }
        }

        double nom = 1.0;
        double ans = -10000.0;
        for(int i = 0;i < N; ++i) {
            double rating = dp[i][N - 1]/nom - 1200/Math.sqrt(i + 1);
            ans = Math.max(ans, rating);
            nom = 0.9 * nom + 1;
        }
        System.out.println(ans);
    }
}
