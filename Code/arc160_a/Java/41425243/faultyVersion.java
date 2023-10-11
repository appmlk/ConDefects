import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.stream.IntStream;

public class Main {
	public static void main(String[] args) {
		var sc = new Scanner(System.in);
		var n = sc.nextInt();
		var k = sc.nextInt();
		var a = IntStream.range(0, n).map(i -> sc.nextInt()).toArray();
		sc.close();

		var list = new ArrayList<Integer>();
		var min = 1;
		var max = n * (n + 1) / 2;
		var st = new Main().new SegmentTree(n + 1);
		for (var i = 1; i <= n; i++) {
			st.update(i, 1);
		}
		for (var i = 0; i < n; i++) {
			var c1 = st.get(1, a[i]);
			var c2 = st.get(a[i] + 1, n + 1);
			var v = -1;
			if (k <= min + c1 - 1) {
				var set = new TreeSet<Integer>();
				for (var j = i + 1; j < n; j++) {
					if (a[j] < a[i]) {
						set.add(a[j]);
					}
				}
				v = new ArrayList<Integer>(set).get(min + set.size() - 1 - k);
			} else if (k >= max - c2 + 1) {
				var set = new TreeSet<Integer>();
				for (var j = i + 1; j < n; j++) {
					if (a[j] > a[i]) {
						set.add(a[j]);
					}
				}
				v = new ArrayList<Integer>(set).get(set.size() - max + k - 1);
			}

			//System.out.printf("%d %d %d %d %d\r\n", min, max, c1, c2, v);

			if (v != -1) {
				var check = false;
				var list1 = new ArrayList<Integer>();
				var list2 = new ArrayList<Integer>();
				var list3 = new ArrayList<Integer>();
				for (var j = 0; j < n; j++) {
					if (j < i) {
						list1.add(a[j]);
					} else if (j >= i && !check) {
						list2.add(a[j]);
						if (a[j] == v) {
							check = true;
						}
					} else {
						list3.add(a[j]);
					}
				}
				Collections.reverse(list2);
				list.addAll(list1);
				list.addAll(list2);
				list.addAll(list3);
				break;
			}
			min += c1;
			max -= c2;
			st.update(a[i], 0);
		}
		if (list.isEmpty()) {
			for (var i = 0; i < n; i++) {
				list.add(a[i]);
			}
		}
		var sb = new StringBuilder();
		for (var i = 0; i < list.size(); i++) {
			var result = list.get(i);
			sb.append(result);
			sb.append(" ");
		}
		System.out.print(sb.toString());
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
			return a + b;
		}

		private long init() {
			return 0L;
		}
	}
}
