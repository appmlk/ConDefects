import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		long[] a = new long[n];
		for (int i = 0; i < n; i++) {
			a[i] = sc.nextInt();
		}
		sc.close();

		Arrays.sort(a);
		int[] d = new int[n];
		PriorityQueue<Obj> que = new PriorityQueue<>((o1, o2) -> Long.compare(o1.v, o2.v));
		for (int i = 0; i < 2; i++) {
			Obj o = new Obj();
			o.i = i;
			o.v = a[i] * 3;
			que.add(o);
			d[i] = 1;
		}

		for (int i = 2; i < n; i++) {
			Obj o = que.poll();
			d[o.i]++;
			long d0 = d[o.i];
			long d1 = d0 + 1;
			long d2 = d1 * d1 - d0 * d0;
			o.v = a[o.i] * d2;
			que.add(o);

			o = new Obj();
			o.i = i;
			o.v = a[i] * 3;
			que.add(o);
			d[i] = 1;
		}

		long ans = 0;
		for (int i = 0; i < n; i++) {
			long di = d[i];
			ans += a[i] * di * di;
		}
		System.out.println(ans);
	}

	static class Obj {
		int i;
		long v;
	}
}
