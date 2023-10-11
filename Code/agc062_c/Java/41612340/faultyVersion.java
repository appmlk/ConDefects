import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] sa = br.readLine().split(" ");
		int n = Integer.parseInt(sa[0]);
		int k = Integer.parseInt(sa[1]);
		sa = br.readLine().split(" ");
		long[] a = new long[n + 1];
		for (int i = 0; i < n; i++) {
			a[i] = Long.parseLong(sa[i]);
		}
		br.close();

		long inf = 1000000000000000000L;
		a[n] = inf;
		Arrays.sort(a);

		TreeMap<Long, Long> map = new TreeMap<>();
		map.put(0L, 0L);
		for (int i = 0; i < n; i++) {
			Long[] arr = map.keySet().toArray(new Long[0]);
			for (Long e1 : arr) {
				long e2 = e1 + a[i];
				long v1 = map.get(e1);
				map.put(e2, Math.max(v1 + a[i], map.getOrDefault(e2, 0L)));
			}
			long x = 0;
			while (true) {
				Long k1 = map.ceilingKey(x);
				Long k2 = map.higherKey(x);
				if (k2 == null) {
					break;
				}
				if (map.get(k1) + 1 >= k2) {
					map.put(k1, Math.max(map.get(k1), map.get(k2)));
					map.remove(k2);
				} else {
					x = k2;
				}
			}
			List<Long> ans = new ArrayList<>();
			x = 0;
			while (x < a[i + 1] && ans.size() < k) {
				Long k1 = map.ceilingKey(x);
				long v1 = map.get(k1);
				Long k2 = map.higherKey(v1);
				long lim = a[i + 1];
				if (k2 != null) {
					lim = Math.min(lim, k2);
					x = k2;
				} else {
					x = a[i + 1];
				}
				for (long j = v1 + 1; j < lim && ans.size() < k; j++) {
					ans.add(j);
				}
			}
			if (ans.size() >= k) {
				out(ans, k);
				return;
			}
		}
	}

	static void out(List<Long> ans, int k) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < k; i++) {
			sb.append(ans.get(i)).append(' ');
		}
		sb.deleteCharAt(sb.length() - 1);
		System.out.println(sb.toString());
	}
}
