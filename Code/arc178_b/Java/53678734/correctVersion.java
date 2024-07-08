import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		var sc = new Scanner(System.in);
		var t = sc.nextInt();
		var a = new int[t];
		var b = new int[t];
		var c = new int[t];
		for (var i = 0; i < t; i++) {
			a[i] = sc.nextInt();
			b[i] = sc.nextInt();
			c[i] = sc.nextInt();
		}
		sc.close();

		var sb = new StringBuilder();
		for (var i = 0; i < t; i++) {
			var min = Math.min(a[i], b[i]);
			var max = Math.max(a[i], b[i]);
			var result = calc(min, max, c[i]);
			sb.append(result);
			sb.append("\r\n");
		}
		System.out.println(sb.toString());
	}

	private static int calc(int a, int b, int c) {
		var result = 0;
		var mc = new Main().new ModCalc();
		var ca = mc.multiply(9, mc.pow(10, a - 1));
		var cb = mc.multiply(9, mc.pow(10, b - 1));
		var v = 0;
		if (a == b) {
			var v1 = 0;
			var v2 = calc2(mc, mc.subtract(ca, mc.pow(10, a - 1)));
			var v3 = mc.add(v1, v2);
			v = v3;
			//System.out.printf("%d %d %d %d %d\r\n", ca, cb, v1, v2, v3);
		} else {
			var v1 = mc.multiply(mc.subtract(cb, mc.pow(10, a)), ca);
			var v2 = calc2(mc, ca);
			var v3 = mc.add(v1, v2);
			v = v3;
			//System.out.printf("%d %d %d %d %d\r\n", ca, cb, v1, v2, v3);
		}
		if (c == b) {
			result = v;
		} else if (c == b + 1) {
			result = mc.subtract(mc.multiply(ca, cb), v);
		}

		return result;
	}

	private static int calc2(ModCalc mc, int a) {
		return mc.divide(mc.multiply(a, mc.add(a, 1)), 2);
	}

	public class ModCalc {
		private int mod = 998244353;

		public ModCalc() {
		}

		public ModCalc(int mod) {
			this.mod = mod;
		}

		public int add(int a, int b) {
			return (int) (((long) a + b) % mod);
		}

		public int subtract(int a, int b) {
			return add((int) (((long) a - b) % mod), mod);
		}

		public int multiply(int a, int b) {
			return add((int) (((long) a * b) % mod), mod);
		}

		public int multiply(int... a) {
			int result = 1;
			for (var a1 : a) {
				result = multiply(result, a1);
			}
			return result;
		}

		public int divide(int a, int b) {
			return multiply(a, pow(b, mod - 2));
		}

		public int pow(int n, int k) {
			if (k == 0) {
				return 1;
			} else if (k % 2 == 0) {
				int p = pow(n, k / 2);
				return multiply(p, p);
			} else {
				int p = pow(n, (k - 1) / 2);
				return multiply(multiply(p, p), n);
			}
		}

		public int combine(int n, int k) {
			int sum1 = 1;
			int sum2 = 1;
			for (int i = 0; i < k; i++) {
				sum1 = multiply(sum1, n - i);
				sum2 = multiply(sum2, i + 1);
			}
			return divide(sum1, sum2);
		}

		public int factorial(int n) {
			int result = 1;
			for (int i = 0; i < n; i++) {
				result = multiply(result, i + 1);
			}
			return result;
		}
	}
}
