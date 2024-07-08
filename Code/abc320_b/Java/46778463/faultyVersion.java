import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class Main {
	public static boolean debug = true;

	public static void main(String[] args) {

		var sc = FastScanner.getInstance();
		var s = sc.next();

		var ans = 0;
		for (int i = 0; i < s.length(); i++) {
			for (int j = i + 1; j <= s.length(); j++) {
				var str = s.substring(i, j);
				var sb = new StringBuilder();
				sb.append(str);
				var rev = sb.reverse().toString();
				if (s.contains(rev)) {
					ans = max(ans, rev.length());
				}
			}
		}
		System.out.println(ans);

	}

	/*---       ---*/
	/*--- Util  ---*/
	/*---       ---*/
	public static int max(int[] array) {
		return Arrays.stream(array).max().getAsInt();
	}

	public static int max(int a, int b) {
		return Math.max(a, b);
	}

	public static String colToString(Collection<?> col) {
		return col.toString().replaceAll("[,\\[\\]]", "");
	}

	public static void printYesNo(boolean b) {
		System.out.println(b ? "Yes" : "No");
	}

	public static void print(Collection<?> c) {
		System.out.println(String.join(" ", c.stream().map(String::valueOf).collect(Collectors.toList())));
	}

	public static void print(Object obj) {
		System.out.print(obj);
	}

	public static void println(Object obj) {
		System.out.println(obj);
	}

	public static void println(Collection<?> c) {
		StringBuilder sb = new StringBuilder();
		for (Object object : c) {
			// java 15以下の環境だとisEmptyがコンパイルしない
			if (!sb.isEmpty()) sb.append("\r\n");
			sb.append(object);
		}
		System.out.println(sb);
	}

	public static void print(String[] a) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < a.length; i++) {
			if (i != 0) sb.append(" ");
			sb.append(a[i]);
		}
		System.out.println(sb);
	}

	public static void println(String[] a) {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < a.length; i++) {
			if (i != 0) sb.append("\r\n");
			sb.append(a[i]);
		}

		System.out.println(sb);
	}

	public static void print(int[] a) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < a.length; i++) {
			if (i != 0) sb.append(" ");
			sb.append(a[i]);
		}
		System.out.println(sb);
	}

	public static void println(int[] a) {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < a.length; i++) {
			if (i != 0) sb.append("\r\n");
			sb.append(a[i]);
		}

		System.out.println(sb);
	}

	public static void print(boolean[] a) {
		for (int i = 0; i < a.length; i++) {
			System.out.println(a[i] + " ");
		}
		System.out.println("");
	}

	public static void printBooleanTable(boolean[][] b) {
		for (int i = 0; i < b.length; i++) {
			for (int j = 0; j < b[0].length; j++) {
				System.out.println(b[i][j] ? "o" : "x");
			}
			System.out.println("");
		}
	}

	public static void printIntTable(int[][] a) {
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[0].length; j++) {
				System.out.println(a[i][j] + " ");
			}
			System.out.println("");
		}
	}

	/*---       ---*/
	/*--- debug ---*/
	/*---       ---*/
	public static void debug(int[] x) {
		if (debug) out(Arrays.toString(x));
	}

	public static void debug(boolean[] x) {
		if (debug) out(Arrays.toString(x));
	}

	public static void debug(long[] x) {
		if (debug) out(Arrays.toString(x));
	}

	public static void debug(int[][] x) {
		if (debug) out(Arrays.deepToString(x));
	}

	public static void debug(boolean[][] x) {
		if (debug) out(Arrays.deepToString(x));
	}

	public static void debug(char[][] x) {
		if (debug) out(Arrays.deepToString(x));
	}

	public static void debug(Object[] x) {
		if (debug) out(Arrays.toString(x));
	}

	public static void debug(Object[][] x) {
		if (debug) out(Arrays.deepToString(x));
	}

	public static void debug(Object a) {
		if (debug) System.err.println(a);
	}

	public static void out(String x) {
		if (debug) System.err.println(x);
	}

}

class FastScanner {
	private FastScanner() {
	}

	private static final FastScanner instance = new FastScanner();

	public static FastScanner getInstance() {
		return instance;
	}

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

	public int[] readIntArray(int size) {
		int[] intArray = new int[size];
		Arrays.setAll(intArray, i -> nextInt());
		return intArray;
	}

	public long[] readLongArray(int size) {
		long[] longArray = new long[size];
		Arrays.setAll(longArray, i -> nextLong());
		return longArray;
	}

	public String[] readStringArray(int size) {
		String[] stringArray = new String[size];
		Arrays.setAll(stringArray, i -> next());
		return stringArray;
	}

	public List<String> readStringList(int size) {
		List<String> list = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			list.add(next());
		}
		return list;
	}

	public Integer[] readIntegerArray(int size) {
		Integer[] ret = new Integer[size];
		for (int i = 0; i < size; i++) {
			ret[i] = nextInt();
		}
		return ret;
	}

	public char[][] twoDimensionalCharArray(int h, int w) {
		char[][] array = new char[h][w];
		for (int i = 0; i < h; i++) {
			array[i] = next().toCharArray();
		}
		return array;
	}

	public int[][] twoDimensionalIntArray(int h, int w) {
		int[][] array = new int[h][w];
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				array[i][j] = nextInt();
			}
		}
		return array;
	}

	public int[][] getIntArrayFromStr(int h, int w) {
		int[][] array = new int[h][w];

		for (int i = 0; i < h; i++) {
			String temp = next();
			for (int j = 0; j < w; j++) {
				array[i][j] = temp.charAt(j) - '0';
			}
		}

		return array;
	}

	public List<Integer> readIntgerList(int size) {
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			list.add(nextInt());
		}
		return list;
	}
}
