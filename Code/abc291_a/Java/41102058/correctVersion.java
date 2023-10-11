import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public class Main {
	static final boolean debug = false;

	public static void main(String[] args) {

		FastScanner sc = new FastScanner();
		var s = sc.next().toCharArray();
		for (int i = 0; i < s.length; i++) {
			if (s[i] >= 'A' && s[i] <= 'Z') System.out.println(i + 1);
		}

	}

	/*
	 * エラトステネスの篩 nまでの素数の配列を返却する
	 * 
	 */
	public static boolean[] getEratostheses(int n) {
		boolean[] isPrimes = new boolean[n + 1];
		Arrays.fill(isPrimes, true);
		isPrimes[0] = isPrimes[1] = false;

		var root = Math.sqrt(isPrimes.length);
		for (int i = 0; i < root; i++) {
			if (!isPrimes[i]) continue;
			for (int j = i * i; j < isPrimes.length; j += i) {
				isPrimes[j] = false;
			}

		}
		return isPrimes;

	}

	public static boolean isPrime(long a) {
		if (a == 1) return false;
		if (a == 2) return true;
		if (a % 2 == 0) return false;

		var rootA = (int) Math.sqrt(a);
		for (int i = 3; i <= rootA; i += 2) {
			if (a % i == 0) return false;
		}
		return true;
	}

	static void swap(int[] array, int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

	static boolean nextPermutation(int[] array) {
		// Find longest non-increasing suffix
		int i = array.length - 1;
		while (i > 0 && array[i - 1] >= array[i])
			i--;
		// Now i is the head index of the suffix

		// Are we at the last permutation already?
		if (i <= 0) return false;

		// Let array[i - 1] be the pivot
		// Find rightmost element greater than the pivot
		int j = array.length - 1;
		while (array[j] <= array[i - 1])
			j--;
		// Now the value array[j] will become the new pivot
		// Assertion: j >= i

		// Swap the pivot with j
		int temp = array[i - 1];
		array[i - 1] = array[j];
		array[j] = temp;

		// Reverse the suffix
		j = array.length - 1;
		while (i < j) {
			temp = array[i];
			array[i] = array[j];
			array[j] = temp;
			i++;
			j--;
		}

		// Successfully computed the next permutation
		return true;
	}

	/*---       ---*/
	/*--- debug ---*/
	/*---       ---*/
	public static void debug(int[] x) {
		out(Arrays.toString(x));
	}

	public static void debug(boolean[] x) {
		out(Arrays.toString(x));
	}

	public static void debug(long[] x) {
		out(Arrays.toString(x));
	}

	public static void debug(int[][] x) {
		out(Arrays.deepToString(x));
	}

	public static void debug(boolean[][] x) {
		out(Arrays.deepToString(x));
	}

	public static void debug(char[][] x) {
		out(Arrays.deepToString(x));
	}

	public static void debug(Object[] x) {
		out(Arrays.toString(x));
	}

	public static void debug(Object[][] x) {
		out(Arrays.deepToString(x));
	}

	public static void debug(String x) {
		out(x);
	}

	public static void debug(Object a) {
		System.err.println(a);
	}

	public static void out(String x) {
		System.err.println(x);
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
		if (hasNextByte())
			return buffer[ptr++];
		else
			return -1;
	}

	private static boolean isPrintableChar(int c) {
		return 33 <= c && c <= 126;
	}

	public boolean hasNext() {
		while (hasNextByte() && !isPrintableChar(buffer[ptr]))
			ptr++;
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

	public int[] readIntArray(final int size) {
		int[] intArray = new int[size];
		Arrays.setAll(intArray, i -> nextInt());
		return intArray;
	}

	public long[] readLongArray(final int size) {
		long[] longArray = new long[size];
		Arrays.setAll(longArray, i -> nextLong());
		return longArray;
	}

	public String[] readStringArray(final int size) {
		String[] stringArray = new String[size];
		Arrays.setAll(stringArray, i -> next());
		return stringArray;
	}

	public List<String> readStringList(final int size) {
		var list = new ArrayList<String>();
		for (int i = 0; i < size; i++) {
			list.add(next());
		}
		return list;
	}

	public char[][] twoDimensionalCharArray(final int h, final int w) {
		char[][] array = new char[h][w];
		for (int i = 0; i < h; i++) {
			array[i] = next().toCharArray();
		}
		return array;
	}

	public int[][] twoDimensionalIntArray(final int h, final int w) {
		int[][] array = new int[h][w];
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				array[i][j] = nextInt();
			}
		}
		return array;
	}

}
