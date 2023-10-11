import java.util.Scanner;

public class Main {

    public static void main( String[] args) {
        Scanner scn = new Scanner( System.in);
        int H = scn.nextInt();
        int W = scn.nextInt();
        char[][] grid = new char[H][W];
        for ( int i = 0; i < H; i++) {
            grid[i] = scn.next().toCharArray();
        }
        int[][] dp = new int[H][W];
        int ans = 0;
        for ( int j = 0; j < H; j++) {
            if ( grid[j][0] == '.') {
                dp[j][0] = j + 1;
                ans = Math.max( ans, dp[j][0]);
            }
            else {
                ans = Math.max( dp[j - 1][0], ans);
                break;
            }
        }
        for ( int k = 0; k < H; k++) {
            if ( grid[0][k] == '.') {
                dp[0][k] = k + 1;
                ans = Math.max( ans, dp[0][k]);
            }
            else {
                ans = Math.max( dp[0][k - 1], ans);
                break;
            }
        }
        for ( int l = 1; l < H; l++) {
            for ( int m = 1; m < W; m++) {
                if ( grid[l][m] == '.' && (dp[l - 1][m] != 0 || dp[l][m - 1] != 0)) {
                    dp[l][m] = Math.max( dp[l - 1][m], dp[l][m - 1]) + 1;
                    ans = Math.max( ans, dp[l][m]);
                }
            }
        }
        System.out.println( ans);
    }
}