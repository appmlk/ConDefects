import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.IntStream;

public final class Main {

	private static final FastScanner sc = new FastScanner();
	private static final PrintWriter pw = new PrintWriter(System.out);

	private static int n;
	private static int m;
	private static char[][] grid;

	private static final int[] dx = { 1, -1, 0, 0 };
	private static final int[] dy = { 0, 0, 1, -1 };

	public static final void main(String[] args) throws Exception {
		n = sc.nextInt();
		m = sc.nextInt();
		grid = new char[n][m];
		for (int i = 0; i < n; ++i) {
			grid[i] = sc.next().toCharArray();
		}

		var yDeque = new ArrayDeque<Integer>(n * m);
		var xDeque = new ArrayDeque<Integer>(n * m);
		yDeque.addLast(1);
		xDeque.addLast(1);
		var targets = new HashMap<String, Boolean>(n * m);
		targets.put(1 + " " + 1, true);

		while (!yDeque.isEmpty()) {
			final int y0 = yDeque.pollFirst();
			final int x0 = xDeque.pollFirst();

			for (int i = 0; i < 4; ++i) {
				int y = y0, x = x0;
				while (true) {
					if (grid[y + dy[i]][x + dx[i]] == '#') {

						if (!targets.containsKey(y + " " + x)) {
							yDeque.addLast(y);
							xDeque.addLast(x);
							targets.put(y + " " + x, true);
						}
						break;
					}

					grid[y][x] = 'v';
					y += dy[i];
					x += dx[i];
				}
			}
		}

		int ans = 0;
		for (char[] row : grid) {
			for (char c : row) {
				if (c == 'v') {
					++ans;
				}
			}
		}

		println(ans);
		pw.flush();
	}

	static final void println(Object obj) {
		pw.println(Objects.toString(obj));
	}

	static final class Permutation {

		private int size;
		private int[] init;
		private int[] perm;
		private boolean[] used;

		public Permutation(int size) {
			if (size < 1) {
				throw new IllegalArgumentException();
			}

			this.size = size;
			init = IntStream.rangeClosed(1, size).toArray();
			perm = new int[size];
			used = new boolean[size];
		}

		private void process() {
		}

		public void buildPermutation(int depth) {
			if (depth == size) {
				process();
				return;
			}

			for (int i = 0; i < size; ++i) {
				if (used[i]) {
					continue;
				}

				perm[depth] = init[i];
				used[i] = true;
				buildPermutation(depth + 1);
				used[i] = false;
			}
		}

	}

}

final class FastScanner {
	private final InputStream in = System.in;
	private final byte[] buffer = new byte[1024];
	private int ptr = 0;
	private int buflen = 0;

	private boolean hasNextByte() {
		if (ptr < buflen) {
			return true;
		}

		ptr = 0;
		try {
			buflen = in.read(buffer);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return buflen > 0;
	}

	private int readByte() {
		return hasNextByte() ? buffer[ptr++] : -1;
	}

	private static boolean isPrintableChar(int c) {
		return 33 <= c && c <= 126;
	}

	private static boolean isPrintableCharForLine(int c) {
		return 32 <= c && c <= 126;
	}

	public boolean hasNext() {
		while (hasNextByte() && !isPrintableChar(buffer[ptr])) {
			ptr++;
		}
		return hasNextByte();
	}

	public String next() {
		if (!hasNext()) {
			throw new NoSuchElementException();
		}
		StringBuilder sb = new StringBuilder();
		int b = readByte();
		while (isPrintableChar(b)) {
			sb.appendCodePoint(b);
			b = readByte();
		}
		return sb.toString();
	}

	public String nextLine() {
		if (!hasNext()) {
			throw new NoSuchElementException();
		}
		StringBuilder sb = new StringBuilder();
		int b = readByte();
		while (isPrintableCharForLine(b)) {
			sb.appendCodePoint(b);
			b = readByte();
		}
		return sb.toString();
	}

	public long nextLong() {
		if (!hasNext()) {
			throw new NoSuchElementException();
		}
		long n = 0;
		boolean minus = false;
		int b = readByte();
		if (b == '-') {
			minus = true;
			b = readByte();
		}
		if (b < '0' || '9' < b) {
			throw new NumberFormatException();
		}
		while (true) {
			if ('0' <= b && b <= '9') {
				n *= 10;
				n += b - '0';
			} else if (b == -1 || !isPrintableChar(b)) {
				return minus ? -n : n;
			} else {
				throw new NumberFormatException();
			}
			b = readByte();
		}
	}

	public int nextInt() {
		long nl = nextLong();
		if (nl < Integer.MIN_VALUE || nl > Integer.MAX_VALUE) {
			throw new NumberFormatException();
		}
		return (int) nl;
	}

	public double nextDouble() {
		return Double.parseDouble(next());
	}
}

final class Pair<F extends Comparable<F>, S extends Comparable<S>>
		implements Comparable<Pair<F, S>> {

	private F first;
	private S second;

	public Pair(F first, S second) {
		this.first = first;
		this.second = second;
	}

	public F getFirst() {
		return first;
	}

	public S getSecond() {
		return second;
	}

	public void setFirst(F first) {
		this.first = first;
	}

	public void setSecond(S second) {
		this.second = second;
	}

	@Override
	public int compareTo(Pair<F, S> o) {
		int t = this.first.compareTo(o.getFirst());

		if (t != 0) {
			return t;
		}

		return this.second.compareTo(o.getSecond());
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		Pair<F, S> another = (Pair<F, S>) obj;
		return Objects.equals(this.getFirst(), another.getFirst())
				&& Objects.equals(this.getSecond(), another.getSecond());

	}

	@Override
	public int hashCode() {
		int h1 = first.hashCode();
		int h2 = second.hashCode();
		String connected = String.valueOf(h1) + ' ' + String.valueOf(h2);
		return connected.hashCode();
	}

	@Override
	public String toString() {
		return Objects.toString(first) + ' ' + Objects.toString(second);
	}

}

final class UnionFind {

	private int[] parents;
	private int[] size;

	public UnionFind(int n) {
		parents = IntStream.range(0, n).toArray();
		size = new int[n];
		Arrays.fill(size, 1);
	}

	public int getRootOf(int x) {
		if (parents[x] == x) {
			return x;
		}

		return parents[x] = getRootOf(parents[x]);
	}

	public boolean hasSameRoot(int x, int y) {
		return getRootOf(x) == getRootOf(y);
	}

	public int getSizeOf(int x) {
		return size[getRootOf(x)];
	}

	public void unite(int x, int y) {
		int rootX = getRootOf(x);
		int rootY = getRootOf(y);

		if (rootX == rootY) {
			return;
		}

		if (getSizeOf(rootX) < getSizeOf(rootY)) {
			int t = rootX;
			rootX = rootY;
			rootY = t;
		}

		parents[rootY] = rootX;
		size[rootX] += size[rootY];
	}

	public boolean allConected() {
		return getSizeOf(0) == size.length;
	}

}