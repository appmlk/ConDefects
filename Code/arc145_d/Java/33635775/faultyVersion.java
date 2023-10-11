import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] sa = br.readLine().split(" ");
		int n = Integer.parseInt(sa[0]);
		long m = Long.parseLong(sa[1]);
		br.close();

		int[] a = new int[n];
		Set<Integer> set = new HashSet<>();
		set.add(0);
		int x = 1;
		int next = 1;
		int cnt = 0;
		for (int i = 1; i < n; i++) {
			while (true) {
				boolean flg = true;
				for (int j = i - 1; j >= 0; j--) {
					int val = a[j] - (x - a[j]);
					if (val < 0) {
						break;
					}
					if (set.contains(val)) {
						flg = false;
						break;
					}
				}
				if (flg) {
					a[i] = x;
					set.add(a[i]);
					break;
				}
				x++;
			}
			cnt++;
			if (cnt == next) {
				x *= 2;
				cnt = 0;
				next *= 2;
			}
		}
		long sum = 0;
		for (int i = 0; i < n; i++) {
			sum += a[i];
		}
//		System.out.println(Arrays.toString(a));
//		System.out.println(sum);

		long g = m - x * 4;
		long g2 = (g - sum + n - 1) / n;
		long sum2 = 0;
		for (int i = 0; i < n; i++) {
			a[i] -= g2;
			sum2 += a[i];
		}
		long add = m - sum2;
		a[n - 1] += add;

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			sb.append(a[i]).append(' ');
		}
		sb.deleteCharAt(sb.length() - 1);
		System.out.println(sb.toString());
	}
}
