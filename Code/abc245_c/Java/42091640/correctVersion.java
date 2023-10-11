import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[][] arr = new int[n][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < n; j++) {
                arr[j][i] = sc.nextInt();
            }
        }
        boolean[][] dp = new boolean[n][2];
        dp[0][0] = true;
        dp[0][1] = true;
        for (int i = 1; i < n; i++) {
            dp[i][0] = dp[i-1][0] && Math.abs(arr[i][0] - arr[i-1][0]) <= k || dp[i-1][1] && Math.abs(arr[i][0] - arr[i-1][1]) <= k;
            dp[i][1] = dp[i-1][0] && Math.abs(arr[i][1] - arr[i-1][0]) <= k || dp[i-1][1] && Math.abs(arr[i][1] - arr[i-1][1]) <= k;
        }
        if(dp[n-1][0] || dp[n-1][1]) System.out.println("Yes");
        else System.out.println("No");
    }
}
