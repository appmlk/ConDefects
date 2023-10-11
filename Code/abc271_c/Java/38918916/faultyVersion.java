import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.*;
import java.util.Map.Entry;

public class Main {

	static Queue<String> queue = new ArrayDeque<>();

	public static void main(String[] args) {
		FastScanner scan = new FastScanner();
		int N = scan.nextInt();
		boolean[] A = new boolean[N + 2];

		for (int i = 0; i < N; i++) {
			int a = scan.nextInt();
			if (a <= N) A[a] = true;
		}

		int current = 1;
		while (true) {
			N -= A[current] ? 1 : 2;
			if (N <= 0) break;
			current++;
		}

		print(current - 1);
	}

	public static void write(Object... objs) {
		try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("", true)))) {
			for (Object o : objs) {
				pw.println(o);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static BigInteger gcd(BigInteger l, BigInteger r) {
		return l.gcd(r);
	}

	public static BigInteger lcm(BigInteger l, BigInteger r) {
		return l.multiply(r).divide(gcd(l, r));
	}

	@SafeVarargs
	public static <T extends Comparable<T>> T max(T... values) {
		return Collections.max(Arrays.asList(values));
	}

	@SafeVarargs
	public static <T extends Comparable<T>> T min(T... values) {
		return Collections.min(Arrays.asList(values));
	}

	public static <T extends Comparable<T>> int lowerBound(List<T> list, T key) {
		return ~Collections.binarySearch(list, key, (x, y) -> x.compareTo(y) > 0 ? 1 : -1);
	}

	public static <T extends Comparable<T>> int upperBound(List<T> list, T key) {
		return ~Collections.binarySearch(list, key, (x, y) -> x.compareTo(y) >= 0 ? -1 : 1);
	}

	public static void permutation(String s) {
		permutation(s, "");
	}

	public static void permutation(String q, String ans) {
		if (q.length() <= 1) {
			queue.add(ans + q);
		} else {
			for (int i = 0; i < q.length(); i++) {
				permutation(q.substring(0, i) + q.substring(i + 1), ans + q.charAt(i));
			}
		}
	}

	public static <T1 extends Comparable<T1>, T2 extends Comparable<T2>> LinkedHashMap<T1, T2> sortMapByKey(Map<T1, T2> map) {
		return sortMapByKey(map, false);
	}

	public static <T1 extends Comparable<T1>, T2 extends Comparable<T2>> LinkedHashMap<T1, T2> sortMapByKey(Map<T1, T2> map, boolean isReverse) {
		List<Entry<T1, T2>> entries = new LinkedList<>(map.entrySet());
		if (isReverse) entries.sort(Entry.comparingByKey(Collections.reverseOrder()));
		else entries.sort(Entry.comparingByKey());

		LinkedHashMap<T1, T2> result = new LinkedHashMap<>();
		for (Entry<T1, T2> entry : entries) {
			result.put(entry.getKey(), entry.getValue());
		}
		return result;
	}

	public static <T1 extends Comparable<T1>, T2 extends Comparable<T2>> LinkedHashMap<T1, T2> sortMapByValue(Map<T1, T2> map) {
		return sortMapByValue(map, false);
	}

	public static <T1 extends Comparable<T1>, T2 extends Comparable<T2>> LinkedHashMap<T1, T2> sortMapByValue(Map<T1, T2> map, boolean isReverse) {
		List<Entry<T1, T2>> entries = new LinkedList<>(map.entrySet());
		if (isReverse) entries.sort(Entry.comparingByValue(Collections.reverseOrder()));
		else entries.sort(Entry.comparingByValue());

		LinkedHashMap<T1, T2> result = new LinkedHashMap<>();
		for (Entry<T1, T2> entry : entries) {
			result.put(entry.getKey(), entry.getValue());
		}
		return result;
	}

	public static long nCr(long n, long r) {
		long result = 1;
		for (int i = 1; i <= r; i++) {
			result = result * (n - i + 1) / i;
		}

		return result;
	}

	public static void print() {
		print("");
	}

	public static void print(Object o) {
		System.out.println(o);
	}

	public static void print(Object... objs) {
		for (Object o : objs) {
			System.out.print(o + " ");
		}
		print();
	}
}

class IndexedObject<T extends Comparable<T>> implements Comparable<IndexedObject>{
	int i;
	T obj;

	public IndexedObject(int i, T obj) {
		this.i = i;
		this.obj = obj;
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof IndexedObject)) return false;
		return this.i == ((IndexedObject<?>)o).i && this.obj.equals(((IndexedObject<?>)o).obj);
	}

	@Override
	public int compareTo(IndexedObject p) {
		if (p.obj.getClass() != this.obj.getClass()) throw new IllegalArgumentException();
		return obj.compareTo((T) p.obj);
	}

	@Override
	public int hashCode() {
		return this.i + this.obj.hashCode();
	}
}

class FastScanner {
	private final InputStream in = System.in;
	private final byte[] buffer = new byte[1024];
	private int ptr = 0;
	private int buflen = 0;

	private boolean hasNextByte() {
		if (ptr < buflen) {
			return true;
		} else {
			ptr = 0;
			try {
				buflen = in.read(buffer);
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (buflen <= 0) {
				return false;
			}
		}
		return true;
	}

	private int readByte() {
		if (hasNextByte()) return buffer[ptr++];
		else return -1;
	}

	private static boolean isPrintableChar(int c) {
		return 33 <= c && c <= 126;
	}

	public boolean hasNext() {
		while (hasNextByte() && !isPrintableChar(buffer[ptr])) ptr++;
		return hasNextByte();
	}

	public String next() {
		if (!hasNext()) throw new NoSuchElementException();
		StringBuilder sb = new StringBuilder();
		int b = readByte();
		while (isPrintableChar(b)) {
			sb.appendCodePoint(b);
			b = readByte();
		}
		return sb.toString();
	}

	public long nextLong() {
		if (!hasNext()) throw new NoSuchElementException();
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
		if (nl < Integer.MIN_VALUE || nl > Integer.MAX_VALUE) throw new NumberFormatException();
		return (int) nl;
	}

	public double nextDouble() {
		return Double.parseDouble(next());
	}
}

