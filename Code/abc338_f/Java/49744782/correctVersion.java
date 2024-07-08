import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] sa = br.readLine().split(" ");
		int n = Integer.parseInt(sa[0]);
		int m = Integer.parseInt(sa[1]);
		int inf = 1000000000;
		int[][] d = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (i != j) {
					d[i][j] = inf;
				}
			}
		}
		for (int i = 0; i < m; i++) {
			sa = br.readLine().split(" ");
			int a = Integer.parseInt(sa[0]) - 1;
			int b = Integer.parseInt(sa[1]) - 1;
			int c = Integer.parseInt(sa[2]);
			d[a][b] = c;
		}
		br.close();

		for (int k = 0; k < n; k++) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (d[i][k] != inf && d[k][j] != inf) {
						d[i][j] = Math.min(d[i][j], d[i][k] + d[k][j]);
					}
				}
			}
		}

		long inf2 = 10000000000000000L;
		int n2 = 1 << n;
		long[][] dp = new long[n2][n];
		for (int i = 0; i < n2; i++) {
			Arrays.fill(dp[i], inf2);
		}
		for (int i = 0; i < n; i++) {
			dp[1 << i][i] = 0;
		}

		for (int i = 1; i < n2; i++) {
			for (int j = 0; j < n; j++) {
				if ((i >> j & 1) == 1 && dp[i][j] != inf2) {
					for (int j2 = 0; j2 < n; j2++) {
						int i2 = i | (1 << j2);
						if (i2 != i && d[j][j2] != inf) {
							dp[i2][j2] = Math.min(dp[i2][j2] , dp[i][j] + d[j][j2]);
						}
					}
				}
			}
		}

		long ans = inf2;
		int n21 = n2 - 1;
		for (int i = 0; i < n; i++) {
			ans = Math.min(ans, dp[n21][i]);
		}
		if (ans == inf2) {
			System.out.println("No");
		} else {
			System.out.println(ans);
		}
	}
}
