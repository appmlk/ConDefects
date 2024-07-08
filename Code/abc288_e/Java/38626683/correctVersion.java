import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
	public static void main(String[] args) {
		var sc = new Scanner(System.in);
		var n = sc.nextInt();
		var m = sc.nextInt();
		var a = IntStream.range(0, n).map(i -> sc.nextInt()).toArray();
		var c = IntStream.range(0, n).map(i -> sc.nextInt()).toArray();
		var x = IntStream.range(0, m).map(i -> sc.nextInt()).toArray();
		sc.close();

		var seg = new Main().new SegmentTree(n);
		for (var i = 0; i < n; i++) {
			seg.update(i, c[i]);
		}
		var set = Arrays.stream(x).boxed().collect(Collectors.toSet());

		var dp = new long[n + 1][n + 1];
		for (var i = 0; i <= n; i++) {
			for (var j = 0; j <= n; j++) {
				dp[i][j] = Long.MAX_VALUE;
			}
		}
		dp[0][0] = 0L;
		for (var i = 0; i < n; i++) {
			for (var j = 0; j <= i; j++) {
				if (dp[i][j] == Long.MAX_VALUE) {
					continue;
				}
				var v = dp[i][j] + a[i] + seg.get(i - j, i + 1);
				//System.out.printf("%d %d %d\r\n", i, j, v);
				if (set.contains(i + 1)) {
					dp[i + 1][j + 1] = Math.min(dp[i + 1][j + 1], v);
				} else {
					dp[i + 1][j] = Math.min(dp[i + 1][j], dp[i][j]);
					dp[i + 1][j + 1] = Math.min(dp[i + 1][j + 1], v);
				}
			}
		}

		var result = Long.MAX_VALUE;
		for (var i = 0; i <= n; i++) {
			result = Math.min(result, dp[n][i]);
		}
		System.out.print(result);
	}

	public class SegmentTree {
		private int n;
		private long[] values;

		public SegmentTree(int n) {
			this.n = (int) Math.pow(2, Math.ceil(Math.log10(n) / Math.log10(2)));
			this.values = new long[2 * this.n];
		}

		public void update(int i, long v) {
			var index = i + n - 1;
			values[index] = v;
			while (index > 0) {
				index = (index - 1) / 2;
				values[index] = func(values[index * 2 + 1], values[index * 2 + 2]);
			}
		}

		public long get(int i) {
			return get(i, i + 1, 0, 0, n);
		}

		public long get(int l, int r) {
			return get(l, r, 0, 0, n);
		}

		private long get(int l, int r, int k, int l2, int r2) {
			if (r2 <= l || r <= l2) {
				return init();
			} else if (l <= l2 && r2 <= r) {
				return values[k];
			} else {
				var v1 = get(l, r, k * 2 + 1, l2, (l2 + r2) / 2);
				var v2 = get(l, r, k * 2 + 2, (l2 + r2) / 2, r2);
				return func(v1, v2);
			}
		}

		private long func(long a, long b) {
			return Math.min(a, b);
		}

		private long init() {
			return Long.MAX_VALUE;
		}
	}
}
