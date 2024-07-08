import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		try(Scanner sc = new Scanner(System.in);) {
			int n = Integer.parseInt(sc.next());
			int d = Integer.parseInt(sc.next());
			double[] w = new double[15];
			double ave = 0;
			for(int i = 0; i < n; i++) {
				w[i] = Double.parseDouble(sc.next());
				ave += w[i];
			}
			ave /= (double)d;
			
			double[][] dp = new double[1 << 15][16];
			for(int i = 0; i < (1 << n); i++) {
				double y = 0;
				for(int j = 0; j < n; j++) {
					if((i & (1 << j)) != 0)  y += w[j];
				}
				dp[i][1] = Math.pow(y - ave, 2);
				for(int j = 2; j <= d; j++) {
					dp[i][j] = dp[i][j - 1] + dp[0][1];
					int x = i;
					while(x > 0) {
						dp[i][j] = Math.min(dp[i][j], dp[i - x][j - 1] + dp[x][1]);
						x = (x - 1) % i;
					}
				}
			}
			
			System.out.printf("%.10f", dp[(1 << n) - 1][d] / (double)d);
		}
	}
}