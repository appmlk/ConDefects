import java.util.*;
import java.io.*;

public class Main {

	static final FastReader sc = new FastReader();
	static final PrintWriter out = new PrintWriter(System.out, true);

	private static boolean debug = System.getProperty("ONLINE_JUDGE") == null;

	static void trace(Object... o) {
		if (debug) {
			System.err.println(Arrays.deepToString(o));
		}
	}

	public static void main(String[] args) {
		int N = sc.nextInt(), M = sc.nextInt();
		long[] L = new long[N];
		for (int i = 0; i < N; i++)
			L[i] = sc.nextInt() + 1;
		long l = Arrays.stream(L).max().getAsLong(), r = Arrays.stream(L).sum();
		while (l < r - 1) {
			long mid = (l + r) / 2;
			long curr = 0;
			int nRows = 1;
			for (int i = 0; i < N; i++) {
				curr += L[i];
				if (curr > mid) {
					nRows++;
					curr = L[i];
				}
			}
			if (nRows > M)
				l = mid;
			else	
				r = mid;
		}
		out.println(r - 1);
	}

	static class FastReader {
		BufferedReader br;
		StringTokenizer st;

		public FastReader() {
			br = new BufferedReader(new InputStreamReader(System.in));
		}

		String next() {
			while (st == null || !st.hasMoreElements()) {
				try {
					st = new StringTokenizer(br.readLine());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return st.nextToken();
		}

		int nextInt() {
			return Integer.parseInt(next());
		}

		long nextLong() {
			return Long.parseLong(next());
		}

		double nextDouble() {
			return Double.parseDouble(next());
		}

		String nextLine() {
			String str = "";
			try {
				str = br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return str;
		}
	}
}
