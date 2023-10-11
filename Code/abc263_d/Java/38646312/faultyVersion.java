import java.io.*;
import java.util.*;

class Main {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	int N, L, R;
	int[] a;
	BinaryIndexedTreeLight bit;
	
	static int i(String is) {
		return Integer.parseInt(is);
	}
	void calc() throws Exception {
		String[] nlr = br.readLine().split(" ");
		N = i(nlr[0]); L = i(nlr[1]); R = i(nlr[2]);
		String[] as = br.readLine().split(" ");
		a = Arrays.stream(as).mapToInt(s -> i(s)).toArray();

		bit = new BinaryIndexedTreeLight(N+1);
		long decL = 0;
		bit.update(0, 0);
		for (int i = 1; i <= N; i++) {
			decL += (L - a[i-1]);
			bit.update(i, decL);
		}

		long decR = 0;
		long min = bit.calculate(N);
		for (int i = 1; i <= N; i++) {
			decR += (R - a[N-i]);
			long val = bit.calculate(N-i) + decR;
			min = Math.min(min, val);
		}
		System.out.println(L*N - decL + min);
	}

	public static void main(String[] args) throws Exception {
		new Main().calc();
	}
}

class BinaryIndexedTreeLight {
	private int size;
	private long[] st;
	private static final long ID = Long.MAX_VALUE / 2;
	
	public BinaryIndexedTreeLight(int size) {
		st = new long[size];
		this.size = size;
		Arrays.fill(st, ID);
	}

	public void update(int index, long element) {
		index++;
		while (index <= size) {
			st[index-1] = Math.min(st[index-1], element);
			index += index & -index;
		}
	}
	
	public long calculate(int e) {
		if (e < 0) return ID;
		if (e >= size) e = size - 1;
		long s = ID;
		e++;
		while (e > 0) {
			s = Math.min(s, st[e-1]);
			e -= e & -e;
		}
		return s;
	}
}
