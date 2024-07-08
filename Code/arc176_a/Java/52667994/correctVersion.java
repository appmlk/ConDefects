import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] sa = br.readLine().split(" ");
		int n = Integer.parseInt(sa[0]);
		int m = Integer.parseInt(sa[1]);
		int[] a = new int[m];
		int[] b = new int[m];
		for (int i = 0; i < m; i++) {
			sa = br.readLine().split(" ");
			a[i] = Integer.parseInt(sa[0]) - 1;
			b[i] = Integer.parseInt(sa[1]) - 1;
		}
		br.close();

		Set<Integer> set = new HashSet<>();
		for (int i = 0; i < m; i++) {
			set.add((b[i] - a[i] + n) % n);
		}
		for (int i = 0; i < n; i++) {
			if (set.size() == m) {
				break;
			}
			if (!set.contains(i)) {
				set.add(i);
			}
		}

		PrintWriter pw = new PrintWriter(System.out);
		pw.println(n * m);
		for (int p : set) {
			for (int i = 0; i < n; i++) {
				pw.println((i + 1) + " " + ((i + p) % n + 1));
			}
		}
		pw.flush();
	}
}
