import java.util.*;
public class Main {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] dist = new int[n + 1];
		for(int i = 1;i <= n;i++) {
			dist[i] = sc.nextInt();
		}int[] range = new int[2];
		int[] cost = new int[2];
		int[] max = new int[2];
		for(int i = 0;i< 2;i++) {
			range[i]  =sc.nextInt();
			cost[i] = sc.nextInt();
			max[i] = sc.nextInt();
		}int[][] dp = new int[n + 1][max[0] + 1];
		for(int i = 0;i <= n;i++) {
			for(int j = 0;j <= max[0];j++) {
				dp[i][j] = max[1] + 1;
			}
		}dp[0][0] = 0;
		for(int i = 1;i <= n;i++) {
			int maxPlus = Math.min(max[0],(dist[i] + range[0] - 1)/range[0]);
			for(int j = 0;j <= max[0];j++) {
				if(dp[i - 1][j] == -1) {
					continue;
				}
				for(int k = 0;k <= maxPlus;k++) {
					if(j + k > max[0]) {
						continue;
					}int addCencer = (dist[i] - range[0] * k + range[1] - 1)/range[1];
					dp[i][j + k] = Math.min(dp[i][j + k],dp[i - 1][j] + addCencer);
				}
			}
		}long ans = -1;
		for(int i = 0;i <= max[0];i++) {
			if(dp[n][i] <= max[1]) {
				long tmp = (long)i * (long)cost[0] + dp[n][i] * cost[1];
				if(ans == -1 || ans > tmp) {
					ans = tmp;
				}
			}
		}System.out.print(ans);
	}

}
