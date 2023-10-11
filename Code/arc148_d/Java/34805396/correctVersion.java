import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] sa = br.readLine().split(" ");
		int n = Integer.parseInt(sa[0]);
		int m = Integer.parseInt(sa[1]);
		int n2 = n * 2;
		sa = br.readLine().split(" ");
		int[] a = new int[n2];
		for (int i = 0; i < n2; i++) {
			a[i] = Integer.parseInt(sa[i]);
		}
		br.close();

		long sum = 0;
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < n2; i++) {
			sum += a[i];
			map.put(a[i], map.getOrDefault(a[i], 0) + 1);
		}
		if (sum % 2 == 1 && m % 2 == 0) {
			System.out.println("Alice");
			return;
		}

		int odd = 0;
		for (int v : map.values()) {
			if (v % 2 == 1) {
				odd++;
			}
		}
		if (odd == 0) {
			System.out.println("Bob");
			return;
		}

		if (m % 2 == 1) {
			System.out.println("Alice");
			return;
		}

		int m2 = m / 2;
		boolean ok = true;
		for (int k : map.keySet()) {
			if (map.get(k) % 2 == 1) {
				Integer v = map.get((k + m2) % m);
				if (v == null || v % 2 == 0) {
					ok = false;
					break;
				}
			}
		}
		if (ok && odd % 4 == 0) {
			System.out.println("Bob");
		} else {
			System.out.println("Alice");
		}
	}
}
