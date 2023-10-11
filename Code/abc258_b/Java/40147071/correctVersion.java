import java.io.PrintWriter;
import java.lang.Character.Subset;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

class Main {
	public static int nextInt(Scanner sc) {
		return Integer.parseInt(sc.next());
	}

	public static long nextLong(Scanner sc) {
		return Long.parseLong(sc.next());
	}

	static char[] abcArray = "abcdefghijklmnopqrstuvwxyz".toCharArray();
	static char[] ABCArray = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
	static HashMap<Integer, int[]> map = new HashMap<>();
	static int H;
	static int W;
	static int N;
	static char[][] figure;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		N = nextInt(sc);
		figure = new char[N][N];
		for (int i = 0; i < N; i++) {
			String line = sc.next();
			figure[i] = line.toCharArray();
		}

		long ans = 0;
		for (int i = 0; i < N; i++) {
			for (int ii = 0; ii < N; ii++) {
				int[] now = { i, ii };
				long[] longArray = new long[8];
				longArray[0] = solve(now, 1, 0, new StringBuffer());
				longArray[1] = solve(now, -1, 0, new StringBuffer());
				longArray[2] = solve(now, 0, 1, new StringBuffer());
				longArray[3] = solve(now, 0, -1, new StringBuffer());
				longArray[4] = solve(now, 1, 1, new StringBuffer());
				longArray[5] = solve(now, -1, -1, new StringBuffer());
				longArray[6] = solve(now, 1, -1, new StringBuffer());
				longArray[7] = solve(now, -1, 1, new StringBuffer());
				Arrays.sort(longArray);
				ans = Math.max(ans, longArray[7]);
			}
		}
		System.out.println(ans);
	}

	public static long solve(int[] now, int h, int w, StringBuffer sb) {
		if (now[0] < 0) {
			now[0] = N - 1;
		}
		if (N <= now[0]) {
			now[0] = 0;
		}
		if (now[1] < 0) {
			now[1] = N - 1;
		}
		if (N <= now[1]) {
			now[1] = 0;
		}
		sb.append(figure[now[0]][now[1]]);
		if (sb.length() < N) {
			int[] next = { now[0] + h, now[1] + w };
			solve(next, h, w, sb);
		}
		return Long.parseLong(sb.toString());
	}
}
