
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		
		long a[] = new long[n + 1];
		int mod = 998244353;
		
		for(int i = 1; i <= n; i++) {
			a[i] = sc.nextLong();
		}
		
		//dp[i][j][k] : i番目の数字まで使ってmod j = kのパターン数
//		long dp[][][] = new long[n + 1][n + 1][n];
		
		long result = 0;
		for(int i = 1; i <= n; i++) {
			//dp[j][k][l] : j番目の数字までの中からk個使ってmod i = lのパターン数
			long dp[][][] = new long[n + 1][n + 1][n];
			for(int j = 0; j <= n; j++) {
				for(int k = 0; k <= j; k++) {
					if(k == 0) {
						dp[j][0][0] = 1;
						continue;
					}
					for(int l = 0; l < i; l++) {
						int tmp = (int)((a[j] + l)%i);
						
						dp[j][k][l] += dp[j - 1][k][l];
						dp[j][k][tmp] += dp[j - 1][k - 1][l];
						
						dp[j][k][l] %= mod;
						dp[j][k][tmp] %= mod;
					}
				}
			}
			
			result += dp[n][i][0];
//			System.out.println(dp[n][i][0]);
			result %= mod;
		}
		
		System.out.println(result);
//		for(int i = 1; i <= n; i++) {
//			System.out.print(dp[n][i][0] + " ");
//		}
	}
}