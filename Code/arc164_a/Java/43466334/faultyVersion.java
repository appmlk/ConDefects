import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	private static final boolean DEBUG = false;

	public static class Counts {
		private long multiplier;
		private long counts;
		private long minCounts;

		public Counts(long multiplier, long counts) {
			this.multiplier = multiplier;
			this.counts = counts;
		}

		public long getMultiplier() {
			return multiplier;
		}

		public void setCounts(long counts) {
			this.counts = counts;
		}

		public long getCounts() {
			return counts;
		}

		public void setMinCounts(long minCounts) {
			this.minCounts = minCounts;
		}

		public long getMinCounts() {
			return minCounts;
		}

	}

	public static Counts createCounts(long n, long k, long c) {
		// 3の累乗系列で分割する
		Counts counts = new Counts(c, n / c);
		long sum = 0;
		while (c > 1) {
			long count = n / c;
			sum += count;
			n -= c * count;
			c /= 3;
		}
		sum += n;
		counts.setMinCounts(sum);
		return counts;
	}

	public static boolean test(long n, long k, long c) {
		if (DEBUG) {
			System.err.println("N: " + n + ", K: " + k + ", C: " + c);
		}

		if (c <= 1) {
			return n == k;
		}
		Counts counts = createCounts(n, k, c);
		if (DEBUG) {
			System.err.println("multiplier: " + counts.getMultiplier() + ", counts: " + counts.getCounts()
					+ ", minCounts: " + counts.getMinCounts());
		}

		long minCounts = counts.getMinCounts();
		long count = counts.getCounts();
		if (minCounts > k) {
			// どうにもならない
			return false;
		} else if (minCounts == k) {
			// 現状の構成で成立している
			return true;
		} else {
			if ((k - minCounts) % 2 != 0) {
				return false;
			} else {
				return true;
			}
//			// この階層から減らすべき数を計算する
//			long t = (k - minCounts) / 2;
//			if (t > count) {
//				t = count;
//			}
//			if (DEBUG) {
//				System.err.println("K: " + k + ", T: " + t + ", counts: " + count
//						+ ", minCounts: " + minCounts);
//			}
//			long countMinusT = count - t;
//			return test(n - counts.getMultiplier() * countMinusT,
//					k - countMinusT, c / 3);
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int t = scanner.nextInt();
		long[] n = new long[t];
		long[] k = new long[t];
		for (int i = 0; i < t; ++i) {
			n[i] = scanner.nextLong();
			k[i] = scanner.nextLong();
		}
		scanner.close();
		for (int i = 0; i < t; ++i) {
			long n0 = n[i];
			long k0 = k[i];
			long c0 = 1;
			if (n[i] >= 3) {
				while (c0 < n[i]) {
					c0 *= 3;
				}
				c0 /= 3;
				if (test(n0, k0, c0)) {
					System.out.println("Yes");
				} else {
					System.out.println("No");
				}
			} else {
				System.out.println(n[i] == k[i]? "Yes": "No");
			}
		}
	}

}
