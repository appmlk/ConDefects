import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		int[] x = new int[] {6, 2, 4, 8};
		PrintWriter pw = new PrintWriter(System.out);
		for (int z = 0; z < t; z++) {
			String[] sa = br.readLine().split(" ");
			long n = Long.parseLong(sa[0]);
			long m = Long.parseLong(sa[1]);
			long k = Long.parseLong(sa[2]);

			if (k + 1 == m && n >= k) {
				pw.println(0);
			} else {
				long a = m - k;
				long b = Math.max(n - k, 0);
				long c = b / a;
				long n2 = n - a * c;
				int p = (int) (n2 % 4);
				pw.println(x[p]);
			}
		}
		pw.flush();
	}
}
