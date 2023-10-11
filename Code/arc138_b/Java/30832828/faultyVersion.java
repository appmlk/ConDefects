import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Scanner;
import java.util.function.IntFunction;

public class Main {
	final static long s = System.currentTimeMillis();

	public static void main(final String[] args) {
		new Main().solve();
		pw.flush();
		//		System.out.println(System.currentTimeMillis() - s);
	}

	final int N = scInt();
	final Integer[] A = scInt(N);

	void solve() {

		final Deque<Integer> deq = new ArrayDeque<>();

		for (int i = 0; i < N; i++)
			deq.add(A[i]);

		boolean rev = false;
		while (!deq.isEmpty()) {
			if (deq.getFirst() == 1 && !rev || rev && deq.getLast() == 0) {
				out("No");
				return;
			}
			while (!deq.isEmpty() && (deq.getLast() == 0 && !rev || rev && deq.getLast() == 1))
				deq.pollLast();
			if (deq.isEmpty())
				break;
			rev = !rev;
			deq.poll();
		}
		out("Yes");
	}

	/* 入力 */
	static Scanner sc = new Scanner(System.in);

	Integer scInt() { return Integer.parseInt(sc.next()); }

	Integer[] scInt(final int N) { return scArray(new Integer[N], i -> scInt()); }

	Integer[][] scInt(final int H, final int W) { return scArray(new Integer[H][], i -> scInt(W)); }

	Integer scIdx() { return scInt() - 1; }

	Integer[] scIdx(final int N) { return scArray(new Integer[N], i -> scIdx()); }

	Long scLong() { return Long.parseLong(sc.next()); }

	Long[] scLong(final int N) { return scArray(new Long[N], i -> scLong()); }

	Double scDbl() { return Double.parseDouble(sc.next()); }

	Double[] scDbl(final int N) { return scArray(new Double[N], i -> scDbl()); }

	String scStr() { return sc.next(); }

	String[] scStr(final int N) { return scArray(new String[N], i -> scStr()); }

	char[] scCh() { return scStr().toCharArray(); }

	<T> T[] scArray(final T[] arr, final IntFunction<T> fnc) {
		Arrays.setAll(arr, fnc);
		return arr;
	}

	/* 出力 */
	static PrintWriter pw = new PrintWriter(System.out);

	void out(final Object... ret) {
		final StringBuilder sb = new StringBuilder();
		sb.append(ret[0]);
		for (int i = 1; i < ret.length; i++)
			sb.append(' ' + ret[i].toString());

		pw.println(sb.toString());
	}

}