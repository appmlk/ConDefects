import java.util.Scanner;

public class Main {
	public static int n;
	public static long[][] d;
	public static boolean[] isUsed;
	public static long ans = 0;

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in)) {
			n = sc.nextInt();
			d = new long[n][n];
			isUsed = new boolean[n];
			for (int i = 0; i < n; i++) {
				for (int j = i + 1; j < n; j++) {
					d[i][j] = sc.nextLong();
				}
			}

			if (n % 2 == 0) {
				for (int i = 1; i < n; i++) {
					dfs(0, i, d[0][i]);
				}
			} else {
				// 奇数の場合ひとつ使わない頂点がある
				for (int i = 1; i < n; i++) {
					isUsed[i] = true;
					for (int j = 1; j < n; j++) {
						if (j == i) {
							continue;
						}
						dfs(0, j, d[0][j]);
					}
					isUsed[i] = false;
				}
				isUsed[0]=true;
				for (int i = 2; i < args.length; i++) {
					dfs(1,i,d[1][i]);
				}

			}

			System.out.println(ans);
		}
	}

	// 選ばれていないうちもっとも小さい頂点を次に選ぶとして深さ優先探索

	public static void dfs(int smallerVertice, int largerVertice, long totalWeight) {
		isUsed[smallerVertice] = true;
		isUsed[largerVertice] = true;
		int nextSmaller = -1;
		for (int i = smallerVertice + 1; i < n; i++) {
			if (!isUsed[i]) {
				nextSmaller = i;
				break;
			}
		}
		if (nextSmaller == -1) {
			ans = Math.max(totalWeight, ans);
			isUsed[smallerVertice] = false;
			isUsed[largerVertice] = false;
			return;
		}
		for (int j = nextSmaller + 1; j < n; j++) {
			if (isUsed[j]) {
				continue;
			}
			dfs(nextSmaller, j, totalWeight + d[nextSmaller][j]);
		}
		ans = Math.max(totalWeight, ans);
		isUsed[smallerVertice] = false;
		isUsed[largerVertice] = false;
	}
}