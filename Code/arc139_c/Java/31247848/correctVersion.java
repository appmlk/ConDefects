import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] sa = br.readLine().split(" ");
		int n = Integer.parseInt(sa[0]);
		int m = Integer.parseInt(sa[1]);
		br.close();

		int x = Math.min(n, m);
		int y = Math.max(n, m);
		List<Integer> lx = new ArrayList<>();
		List<Integer> ly = new ArrayList<>();
		for (int i = 1; i < x; i++) {
			int end = Math.min(i + 2, y);
			for (int j = Math.max(i - 1, 1); j <= end; j++) {
				lx.add(i);
				ly.add(j);
			}
		}
		for (int j = Math.max(x - 1, 1); j <= y; j++) {
			lx.add(x);
			ly.add(j);
		}

		if (x == y && x % 2 == 1 && x > 1) {
			lx.add(x);
			ly.add(y - 2);
			int val = x + (y - 2) * 3;
			for (int i = lx.size() - 2; i >= 0; i--) {
				if (lx.get(i) + ly.get(i) * 3 == val) {
					lx.set(i, lx.get(i) + 1);
					ly.set(i, ly.get(i + 1) - 1);
					val = lx.get(i) + ly.get(i) * 3;
				}
			}
		}
		PrintWriter pw = new PrintWriter(System.out);
		pw.println(lx.size());
		if (n > m) {
			List<Integer> tmp = lx;
			lx = ly;
			ly = tmp;
		}
		for (int i = 0; i < lx.size(); i++) {
			pw.println(lx.get(i) + " " + ly.get(i));
		}
		pw.flush();
	}
}
