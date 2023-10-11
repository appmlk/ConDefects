import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;
import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.lang.System.exit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static class Token implements Comparable<Token> {
		final long v;
		final int i, j;
		public Token(long v, int i, int j) {
			this.v = v;
			this.i = i;
			this.j = j;
		}

		public int compareTo(Token o) {
			return Long.compare(v, o.v);
		}
	}

	static void solve() throws Exception {
		int n = scanInt();
		long x = scanLong(), a[] = new long[n], min = Long.MAX_VALUE, max = 0;
		PriorityQueue<Token> pq = new PriorityQueue<>();
		for (int i = 0; i < n; i++) {
			a[i] = scanLong();
			max = max(max, a[i]);
			min = min(min, a[i]);
			pq.add(new Token(a[i], i, 0));
		}
		long ans = max - min;
		for (int i = 0; i < 30 * n; i++) {
			Token tok = pq.remove();
			ans = min(ans, max - tok.v);
			Token nt = new Token(2 * tok.v + x, tok.i, tok.j + 1);
			max = max(max, a[tok.i] << (tok.j + 1));
			pq.add(nt);
		}
		out.print(max(ans, 0));
	}

	static int scanInt() throws IOException {
		return parseInt(scanString());
	}

	static long scanLong() throws IOException {
		return parseLong(scanString());
	}

	static String scanString() throws IOException {
		while (tok == null || !tok.hasMoreTokens()) {
			tok = new StringTokenizer(in.readLine());
		}
		return tok.nextToken();
	}

	static BufferedReader in;
	static PrintWriter out;
	static StringTokenizer tok;

	public static void main(String[] args) {
		try {
			in = new BufferedReader(new InputStreamReader(System.in));
			out = new PrintWriter(System.out);
			solve();
			in.close();
			out.close();
		} catch (Throwable e) {
			e.printStackTrace();
			exit(1);
		}
	}
}