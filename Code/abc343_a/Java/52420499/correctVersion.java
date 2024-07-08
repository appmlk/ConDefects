import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.IntUnaryOperator;
import java.util.function.LongUnaryOperator;
import java.util.stream.Collectors;

public class Main {
	public static final long MOD = ((long) 1e9 + 7L);

	public static void solve() {
		var a = sc.nextInt();
		var b = sc.nextInt();
		var ac = a + b;
		for (var i = 0; i <= 9; i++) {
			if (i != ac) {
				pw.println(i);
				return;
			}

		}

	}

	/**
	 * 10進数からX進数の文字列に変換する。
	 * 
	 * @MEMO 逆向きの変換はLong.parseUnsignedLong(String s, int radix)を利用する！
	 * 
	 * @param n       変換元（１０進数）
	 * @param newBase 変換後の基数
	 * @return newBase変換後のString
	 */
	public static String convertBase10ToX(long n, int radix) {
		if (n == 0L) return "0";
		var sb = new StringBuilder();
		while (n > 0) {
			long r = n % (long) radix;
			sb.insert(0, r);
			n /= (long) radix;
		}
		return sb.toString();
	}

	public static int[] array(int size, int init) {
		var array = new int[size];
		fill(array, init);
		return array;
	}

	public static char[] array(int size, char init) {
		var array = new char[size];
		fill(array, init);
		return array;
	}

	public static char[][] array(int h, int w, char init) {
		var array = new char[h][w];
		for (char[] c : array) fill(c, init);
		return array;
	}

	public static boolean[] array(int size, boolean init) {
		var array = new boolean[size];
		fill(array, init);
		return array;
	}

	public static long[] array(int size, long init) {
		var array = new long[size];
		fill(array, init);
		return array;
	}

	public static int[] dpArray(int size, int init) {
		int[] array = new int[size];
		fill(array, init);
		array[0] = 0;
		return array;
	}

	public static long[] dpArray(int size, long init) {
		var array = new long[size];
		fill(array, init);
		array[0] = 0L;
		return array;
	}

	public static long gcd(long a, long b) {
		if (b > a) {
			long temp = a;
			a = b;
			b = temp;
		}
		if (a % b == 0) {
			return b;
		}
		return gcd(b, a % b);
	}

	public static long lcm(long a, long b) {
		// NOTE オーバーフローに注意すること。
		// オーバーフローのおそれがある場合は以下のような対応をとる
		// if (a / g > INF / b) return INF;
		// @see https://drken1215.hatenablog.com/entry/2023/11/21/020301
		if (b > a) {
			return b / gcd(a, b) * a;
		} else {
			return a / gcd(a, b) * b;
		}
	}

	/**
	 * 負の数にも対応した%演算
	 * 
	 * @param val
	 * @param mod
	 * @return
	 */
	public static long mod(long val, long mod) {
		long res = val % mod;
		if (res < 0) res += mod;
		return res;
	}

	/**
	 * べき乗の計算
	 */
	public static final long pow(int a, int b) {
		if (b == 0) return 1;
		if (b % 2 == 1) return a * pow(a, (b - 1));
		var half = pow(a, b / 2);
		return half * half;
	}

	/**
	 * べき乗の計算
	 */
	public static final long pow(long a, long b) {
		if (b == 0L) return 1L;
		if (b % 2L == 1L) return a * pow(a, (b - 1L));
		var half = pow(a, b / 2L);
		return half * half;
	}

	/**
	 * べき乗 MOD 計算量 O(logN)
	 * 
	 * @param val
	 * @param mod
	 * @return
	 */
	public static long modpow(long a, long b, long mod) {
		if (b == 0L) return 1L;
		if (b == 1L) return a % mod;
		if (b % 2 == 1) return (a * modpow(a, b - 1L, mod)) % mod;
		long temp = modpow(a, b / 2L, mod);
		return (temp * temp) % mod;

	}

	public static int getKeata(int x) {
		return String.valueOf(x).length();
	}

	public static int getKeata(long x) {
		return String.valueOf(x).length();
	}

	/**
	 * Arras#binarySearchでは重複した場合の返却値が安定しないので、LowerBound版を作成
	 * 
	 * 値が重複しないことが保証されている場合は速度的にArrays#binarySearchを使うこと。
	 * 
	 * )* @param array
	 * 
	 * @param key
	 * @return 存在する場合はインデックス 存在しない場合は-(挿入するポイント)-1(ビット反転[~]すればもどる)
	 */
	public static int binarySearchLowerBound(int[] array, int key) {
		var ng = -1;
		var ok = array.length;
		var lastIndex = array.length - 1;
		while (ok - ng > 1) {
			var mid = (ok + ng) >>> 1;
			if (mid <= lastIndex && array[mid] >= key) {
				ok = mid;
			} else {
				ng = mid;
			}
		}

		if (ok <= lastIndex && ok >= 0 && array[ok] == key)
			return ok;
		else
			return ~ok;
	}

	public static int binarySearchLowerBound(long[] array, long key) {
		var ng = -1;
		var ok = array.length;
		var lastIndex = array.length - 1;
		while (ok - ng > 1) {
			var mid = (ok + ng) >>> 1;
			if (mid <= lastIndex && array[mid] >= key) {
				ok = mid;
			} else {
				ng = mid;
			}
		}

		if (ok <= lastIndex && ok >= 0 && array[ok] == key)
			return ok;
		else
			return ~ok;
	}

	public static int binarySearchUpperBound(int[] array, int key) {
		var ng = -1;
		var ok = array.length;
		var lastIndex = array.length - 1;
		while ((ok - ng) > 1) {
			var mid = (ok + ng) >>> 1;
			if (mid <= lastIndex && array[mid] > key) {
				ok = mid;
			} else {
				ng = mid;
			}
		}
		if (ng == -1) return -1;// 探索内で大きいものが見つからなかったとき
		if (array[ng] == key) return ng;// 最後の値がkeyと同じ値の場合は最後の値を利用する

		if (ok <= lastIndex && ok >= 0 && array[ok] == key)
			return ok;
		else
			return ~ok;
	}

	public static int binarySearchUpperBound(long[] array, long key) {
		var ng = -1;
		var ok = array.length;
		var lastIndex = array.length - 1;
		while ((ok - ng) > 1) {
			var mid = (ok + ng) >>> 1;
			if (mid <= lastIndex && array[mid] > key) {
				ok = mid;
			} else {
				ng = mid;
			}
		}
		if (ng == -1) return -1;// 探索内で大きいものが見つからなかったとき
		if (array[ng] == key) return ng;// 最後の値がkeyと同じ値の場合は最後の値を利用する

		if (ok <= lastIndex && ok >= 0 && array[ok] == key)
			return ok;
		else
			return ~ok;
	}

	/*---       ---*/
	/*--- Util  ---*/
	/*---       ---*/
	public static List<Integer> toList(int[] a) {
		return Arrays.stream(a).boxed().collect(Collectors.toList());
	}

	public static List<Long> toList(long[] a) {
		return Arrays.stream(a).boxed().collect(Collectors.toList());
	}

	public static String reverse(String str) {
		return new StringBuilder().append(str).reverse().toString();
	}

	public static boolean isKaibun(String str) {
		return str.equals(reverse(str));
	}

	public static String toString(int a) {
		return String.valueOf(a);
	}

	public static String toString(long a) {
		return String.valueOf(a);
	}

	public static int toInt(String a) {
		return Integer.valueOf(a);
	}

	public static long toLong(String a) {
		return Long.valueOf(a);
	}

	public static char[] toCharArray(int a) {
		return String.valueOf(a).toCharArray();
	}

	public static char[] toCharArray(long a) {
		return String.valueOf(a).toCharArray();
	}

	public static char[] toCharArray(String a) {
		return a.toCharArray();
	}

	public static boolean isAllTrue(boolean[] boo) {
		for (boolean b : boo) {
			if (!b) return false;
		}
		return true;
	}

	public static void sort(int[] a) {
		Arrays.sort(a);
	}

	public static void sort(long[] a) {
		Arrays.sort(a);
	}

	public static int abs(int a, int b) {
		return Math.abs(a - b);
	}

	public static long abs(long a, long b) {
		return Math.abs(a - b);
	}

	/**
	 * int配列を降順にソートする
	 */
	public static void sortDes(int[] a) {
		Arrays.sort(a);
		var temp = Arrays.copyOf(a, a.length);

		for (int i = 0; i < temp.length; i++) {
			a[i] = temp[temp.length - i - 1];
		}
	}

	/**
	 * long配列を降順にソートする
	 */
	public static void sortDes(long[] a) {
		Arrays.sort(a);
		var temp = Arrays.copyOf(a, a.length);

		for (int i = 0; i < temp.length; i++) {
			a[i] = temp[temp.length - i - 1];
		}
	}

	public static void fill(int[] array, int val) {
		Arrays.fill(array, val);
	}

	public static void fill(char[] array, char val) {
		Arrays.fill(array, val);
	}

	public static void fill(boolean[] array, boolean val) {
		Arrays.fill(array, val);
	}

	public static void fill(int[][] array, int val) {
		for (var a : array) Arrays.fill(a, val);
	}

	public static void fill(long[] array, long val) {
		Arrays.fill(array, val);
	}

	public static void fill(long[][] array, long val) {
		for (var a : array) Arrays.fill(a, val);
	}

	public static void initalizeDp(int[][] dp, int val) {
		fill(dp, val);
		dp[0][0] = 0;
	}

	public static void initalizeDp(long[][] dp, long val) {
		fill(dp, val);
		dp[0][0] = 0L;
	}

	public static int max(int... array) {
		return Arrays.stream(array).max().getAsInt();
	}

	public static int sum(int... array) {
		return Arrays.stream(array).sum();
	}

	public static int max(int a, int b) {
		return Math.max(a, b);
	}

	public static int min(int... array) {
		return Arrays.stream(array).min().getAsInt();
	}

	public static int min(int a, int b) {
		return Math.min(a, b);
	}

	public static long max(long... array) {
		return Arrays.stream(array).max().getAsLong();
	}

	public static long max(long a, long b) {
		return Math.max(a, b);
	}

	public static long sum(long... array) {
		return Arrays.stream(array).sum();
	}

	public static long min(long a, long b) {
		return Math.min(a, b);
	}

	public static long min(long... array) {
		return Arrays.stream(array).min().getAsLong();
	}

	/*---       ---*/
	/*--- debug ---*/
	/*---       ---*/
	public static void debug(Object o, Object... args) {
		var format = "%s";
		var temp = new Object[args.length + 1];
		temp[0] = o;
		System.arraycopy(args, 0, temp, 1, args.length);
		for (int i = 0; i < temp.length - 1; i++) {
			format += " %s";
		}
		System.out.printf(format, temp);
		System.out.println("");
	}

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

	public static void debugln(int[][] x) {
		for (int[] w : x) {
			for (var i : w) {
				System.err.print(i + " ");
			}
			out("");
		}
		out("///////////////////");
	}

	public static void debug(long[][] x) {
		out(Arrays.deepToString(x));
	}

	public static void debugln(long[][] x) {
		for (long[] w : x) {
			for (var i : w) {
				System.err.print(i + " ");
			}
			out("");
		}
		out("///////////////////");
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

	public static void debug(Object a) {
		System.err.println(a);
	}

	public static void out(String x) {
		System.err.println(x);
	}

	final static ContestPrinter pw = new ContestPrinter();
	final static FastScanner sc = new FastScanner();

	public static void main(String[] args) {
		solve();
		pw.close();
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

	public Point nextPoint() {
		return new Point(nextInt(), nextInt());
	}

	public Point[] nextPointArray(int size) {
		Point[] array = new Point[size];
		Arrays.setAll(array, i -> new Point(nextInt(), nextInt()));
		return array;
	}

	public List<Point> nextPointList(int size) {
		List<Point> list = new ArrayList<>();
		for (int i = 0; i < size; i++) list.add(new Point(nextInt(), nextInt()));
		return list;
	}

	public double nextDouble() {
		return Double.parseDouble(next());
	}

	public int[] nextIntArray(int size) {
		int[] intArray = new int[size];
		Arrays.setAll(intArray, i -> nextInt());
		return intArray;
	}

	public int[] nextIntArray(int size, IntUnaryOperator map) {
		int[] intArray = new int[size];
		Arrays.setAll(intArray, i -> map.applyAsInt(nextInt()));
		return intArray;
	}

	public int[] nextIntArrayOneToZeroIndex(int size) {
		return nextIntArray(size, i -> i - 1);
	}

	public long[] nextLongArray(int size) {
		long[] longArray = new long[size];
		Arrays.setAll(longArray, i -> nextLong());
		return longArray;
	}

	public long[] nextLongArray(int size, LongUnaryOperator map) {
		long[] longArray = new long[size];
		Arrays.setAll(longArray, i -> map.applyAsLong(nextLong()));
		return longArray;
	}

	public String[] nextStringArray(int size) {
		String[] stringArray = new String[size];
		Arrays.setAll(stringArray, i -> next());
		return stringArray;
	}

	public List<String> nextStringList(int size) {
		List<String> list = new ArrayList<>();
		for (int i = 0; i < size; i++) list.add(next());
		return list;
	}

	public Integer[] nextIntegerArray(int size) {
		Integer[] ret = new Integer[size];
		for (int i = 0; i < size; i++) ret[i] = nextInt();
		return ret;
	}

	public Integer[] nextIntegerArray(int size, IntUnaryOperator map) {
		Integer[] ret = new Integer[size];
		for (int i = 0; i < size; i++) ret[i] = map.applyAsInt(nextInt());
		return ret;
	}

	public char[][] nextDimensionalCharArray(int h, int w) {
		char[][] array = new char[h][w];
		for (int i = 0; i < h; i++) {
			array[i] = next().toCharArray();
		}
		return array;
	}

	public int[][] nextDimensionalIntArray(int h, int w) {
		int[][] array = new int[h][w];
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				array[i][j] = nextInt();
			}
		}
		return array;
	}

	public long[][] nextDimensionaLongArray(int h, int w) {
		long[][] array = new long[h][w];
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				array[i][j] = nextLong();
			}
		}
		return array;
	}

	public int[][] nextIntArrayFromStr(int h, int w) {
		int[][] array = new int[h][w];

		for (int i = 0; i < h; i++) {
			String temp = next();
			for (int j = 0; j < w; j++) {
				array[i][j] = temp.charAt(j) - '0';
			}
		}

		return array;
	}

	public List<Integer> nextIntgerList(int size) {
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < size; i++) list.add(nextInt());
		return list;
	}

	public List<Integer> nextIntgerList(int size, IntUnaryOperator map) {
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < size; i++) list.add(map.applyAsInt(nextInt()));
		return list;
	}

}

class ContestPrinter extends java.io.PrintWriter {
	public ContestPrinter(java.io.PrintStream stream) {
		super(stream);
	}

	public ContestPrinter(java.io.File file) throws java.io.FileNotFoundException {
		super(new java.io.PrintStream(file));
	}

	public ContestPrinter() {
		super(System.out);
	}

	private static String dtos(double x, int n) {
		StringBuilder sb = new StringBuilder();
		if (x < 0) {
			sb.append('-');
			x = -x;
		}
		x += Math.pow(10, -n) / 2;
		sb.append((long) x);
		sb.append(".");
		x -= (long) x;
		for (int i = 0; i < n; i++) {
			x *= 10;
			sb.append((int) x);
			x -= (int) x;
		}
		return sb.toString();
	}

	@Override
	public void print(float f) {
		super.print(dtos(f, 20));
	}

	@Override
	public void println(float f) {
		super.println(dtos(f, 20));
	}

	@Override
	public void print(double d) {
		super.print(dtos(d, 20));
	}

	@Override
	public void println(double d) {
		super.println(dtos(d, 20));
	}

	@Override
	public void print(boolean boo) {
		super.print(boo ? "Yes" : "No");
	}

	public void print(int[] array, String separator) {
		int n = array.length;
		if (n == 0) {
			super.println();
			return;
		}
		for (int i = 0; i < n - 1; i++) {
			super.print(array[i]);
			super.print(separator);
		}
		super.println(array[n - 1]);
	}

	public void print(int[] array) {
		this.print(array, " ");
	}

	public void print(int[] array, String separator, java.util.function.IntUnaryOperator map) {
		int n = array.length;
		if (n == 0) {
			super.println();
			return;
		}
		for (int i = 0; i < n - 1; i++) {
			super.print(map.applyAsInt(array[i]));
			super.print(separator);
		}
		super.println(map.applyAsInt(array[n - 1]));
	}

	public void print(int[] array, java.util.function.IntUnaryOperator map) {
		this.print(array, " ", map);
	}

	public void print(long[] array, String separator) {
		int n = array.length;
		if (n == 0) {
			super.println();
			return;
		}
		for (int i = 0; i < n - 1; i++) {
			super.print(array[i]);
			super.print(separator);
		}
		super.println(array[n - 1]);
	}

	public void print(long[] array) {
		this.print(array, " ");
	}

	public void print(long[] array, String separator, java.util.function.LongUnaryOperator map) {
		int n = array.length;
		if (n == 0) {
			super.println();
			return;
		}
		for (int i = 0; i < n - 1; i++) {
			super.print(map.applyAsLong(array[i]));
			super.print(separator);
		}
		super.println(map.applyAsLong(array[n - 1]));
	}

	public void print(long[] array, java.util.function.LongUnaryOperator map) {
		this.print(array, " ", map);
	}

	public <T> void print(T[] array, String separator) {
		int n = array.length;
		if (n == 0) {
			super.println();
			return;
		}
		for (int i = 0; i < n - 1; i++) {
			super.print(array[i]);
			super.print(separator);
		}
		super.println(array[n - 1]);
	}

	public <T> void print(T[] array) {
		this.print(array, " ");
	}

	public <T> void print(T[] array, String separator, java.util.function.UnaryOperator<T> map) {
		int n = array.length;
		if (n == 0) {
			super.println();
			return;
		}
		for (int i = 0; i < n - 1; i++) {
			super.print(map.apply(array[i]));
			super.print(separator);
		}
		super.println(map.apply(array[n - 1]));
	}

	public <T> void print(T[] array, java.util.function.UnaryOperator<T> map) {
		this.print(array, " ", map);
	}

	public String getOutputCollection(Collection<?> c) {
		return String.join(" ", c.stream().map(String::valueOf).collect(Collectors.toList()));
	}

	public void print(Collection<?> c) {
		super.print(getOutputCollection(c));
	}

	public void println(Collection<?> c) {
		StringBuilder sb = new StringBuilder();
		for (Object object : c) {
			// java 15以下の環境だとisEmptyがコンパイルしない
//			if (!sb.isEmpty()) sb.append("\r\n");
			if (sb.length() != 0) sb.append("\r\n");
			sb.append(object);
		}
		System.out.println(sb);
	}

	public void println(int[] array) {
		print(array, "\r\n");
	}

	public void println(long[] array) {
		print(array, "\r\n");
	}

	public void print(String[] a) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < a.length; i++) {
			if (i != 0) sb.append(" ");
			sb.append(a[i]);
		}
		System.out.println(sb);
	}

	public void print(Object obj) {
		System.out.print(obj);
	}

	public void println(Object obj) {
		System.out.println(obj);
	}

	public void printlnDeep(ArrayList<ArrayList<Integer>> c) {
		StringBuilder sb = new StringBuilder();
		for (var ci : c) sb.append(getOutputCollection(ci)).append("\r\n");
		System.out.println(sb);
	}

	public void printZeroToOneIndex(int[] a) {
		print(a, i -> i + 1);
	}

	public void printZeroToOneIndex(List<Integer> a) {
		var temp = a.stream().map(i -> i + 1).collect(Collectors.toList());
		print(temp);
	}

	public void printlnZeroToOneIndex(List<Integer> a) {
		var temp = a.stream().map(i -> i + 1).collect(Collectors.toList());
		println(temp);
	}

	public void print(boolean[] a) {
		for (int i = 0; i < a.length; i++) {
			System.out.println(a[i] + " ");
		}
		System.out.println("");
	}

	public void print(boolean[][] b) {
		for (int i = 0; i < b.length; i++) {
			for (int j = 0; j < b[0].length; j++) {
				System.out.print(b[i][j] ? "o" : "x");
			}
			System.out.println("");
		}
	}

	public void print(int[][] array) {
		for (var i : array) print(i);
	}

	public void print(long[][] array) {
		for (var i : array) print(i);
	}

	public void print(char[][] array) {
		for (char[] cs : array) {
			for (char c : cs) {
				print(c);
			}
			println();
		}
	}

}

class Graph {
	List<ArrayList<Integer>> g;
	int v;
	int e;
	boolean[] visited;

	public Graph(int v) {
		this.v = v;
		for (var i = 0; i < v; i++) g.add(new ArrayList<Integer>());
		visited = new boolean[v];

	}

	public void addEdge(int a, int b) {
		g.get(a).add(b);
		g.get(b).add(a);
		e++;
	}

	public void dfs(int now) {
		visited[now] = true;
		for (var next : g.get(now)) {
			if (visited[next]) continue;
			dfs(next);
		}
	}

	public boolean marked(int now) {
		return visited[now];
	}

	public boolean isAllConected() {
		for (var bo : visited) {
			if (!bo) return false;
		}
		return true;
	}
}

/**
 * 先頭及び末尾への挿入がO（1）かつ、ランダムアクセスもO(1)で可能なデータ構造の自作クラス
 * 
 * @author ritoAtCoder
 */
class ContestDeque<E> implements Iterable<E> {
	private List<E> stack;
	private List<E> que;

	public ContestDeque() {
		stack = new ArrayList<E>();
		que = new ArrayList<E>();
	}

	public E get(int i) {
		if (i < stack.size()) {
			return stack.get(stack.size() - i - 1);
		} else {
			return que.get(i - stack.size());
		}
	}

	public E remove(int i) {
		E e;
		if (i < stack.size()) {
			e = stack.remove(stack.size() - i - 1);
		} else {
			e = que.remove(i - stack.size());
		}
		return e;
	}

	public void addFirst(E e) {
		stack.add(e);
	}

	public void addLast(E e) {
		que.add(e);
	}

	public void set(int i, E e) {
		if (i < stack.size()) {
			e = stack.set(stack.size() - i - 1, e);
		} else {
			e = que.set(i - stack.size(), e);
		}
	}

	public int size() {
		return stack.size() + que.size();
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	@Override
	public String toString() {
		var temp = new ArrayList<E>();
		for (int i = stack.size() - 1; i >= 0; i--) {
			temp.add(stack.get(i));
		}
		temp.addAll(que);
		return String.join(" ", temp.stream().map(String::valueOf).collect(Collectors.toList()));
	}

	public Iterator<E> iterator() {
		return new ContestDequeIterator();
	}

	private class ContestDequeIterator implements Iterator<E> {
		private int i = 0;

		public boolean hasNext() {
			return i < size();
		}

		public E next() {
			i++;
			return get(i);
		}
	}
}

class Point {
	int x;
	int y;

	Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * 距離を求める。ただし平方根を付ける前
	 * 
	 * @param Point
	 * @return 平方根を付ける前の距離
	 */
	public long dist(Point target) {
		return (long) (x - target.x) * (x - target.x) + (y - target.y) * (y - target.y);

	}

	@Override
	public String toString() {
		return x + " " + y;
	}
}
