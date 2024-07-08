import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.StringJoiner;
import java.util.function.IntUnaryOperator;
import java.util.function.LongUnaryOperator;
import java.util.stream.Collectors;

public class Main {

	static void solve() {

		var a = sc.nextLong();
		var m = sc.nextLong();
		var l = sc.nextLong();
		var r = sc.nextLong();

		l -= a;
		r -= a;
		var X = (((long) 1e18) / m) + 100L;
		l += m * X;
		r += m * X;

		pw.println((r / m - (l - 1) / m));
	}

	static void swap(int[] array, int i, int j) {
		int temp = array[i];
		array[j] = array[i];
		array[i] = temp;
	}

	static void swap(long[] array, int i, int j) {
		long temp = array[i];
		array[j] = array[i];
		array[i] = temp;
	}

	static void swap(char[] array, int i, int j) {
		char temp = array[i];
		array[j] = array[i];
		array[i] = temp;
	}

	static String swap(String str, int i, int j) {
		var temp = str.toCharArray();
		var taihi = temp[i];
		temp[i] = temp[j];
		temp[j] = taihi;
		return toString(temp);
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
			for (int j = i * i; j < isPrimes.length; j += i) {// iでインクリメントし)ているのポイント
				isPrimes[j] = false;
			}
		}
		return isPrimes;

	}

	public static List<Integer> getPrimeList(int n) {
		var list = new ArrayList<Integer>();
		var primes = getEratostheses(n);
		for (var i = 0; i < n; i++) {
			if (primes[i]) list.add(i);
		}

		return list;
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

	/**
	 * 素因数分解の結果を返却する １は含まれないことに注意
	 * 
	 * @param x
	 * @return 素因数分解の結果
	 */
	public static List<Integer> primeFactorize(int x) {
		var ret = new ArrayList<Integer>();

		for (int i = 2; i <= (int) Math.sqrt(x); i++) {
			if (x % i == 0) {
				while (x % i == 0) {
					ret.add(i);
					x /= i;
				}
			}
		}
		if (x != 1) {
			ret.add(x);
		}

		return ret;
	}

	/**
	 * 次の順列にする。 MEMO: 利用の場合はもとが昇順でソートしているか確認すること
	 * 
	 * ) @param array )*
	 * 
	 * @return boolean 次の順列がある
	 */
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

//	public static final int MOD = ((int) 1e9 + 7);
//	public static final long MOD = 998244353L;
	public static final long MOD = (long) 1e8;
	public static final int INF = (int) 1e9;

	/**
	 * ランレングス圧縮
	 * 
	 * @param str
	 * @return Pair
	 */
	public static List<Pair<Character, Integer>> runLength(String str) {
		var ret = new ArrayList<Pair<Character, Integer>>();
		for (int i = 0; i < str.length(); i++) {

			var cnt = 1;
			while (i < str.length() - 1 && str.charAt(i) == str.charAt(i + 1)) {
				i++;
				cnt++;
			}
			ret.add(new Pair<Character, Integer>(str.charAt(i), cnt));
		}
		return ret;
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

	public static int[] array(int size, IntUnaryOperator generator) {
		var array = new int[size];
		Arrays.setAll(array, generator);
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

	/**
	 * 先頭が0でそれ以外は初期値の配列を返却する
	 * 
	 * @param size
	 * @param init
	 * @return
	 */
	public static int[] zeroArray(int size, int init) {
		int[] array = new int[size];
		fill(array, init);
		array[0] = 0;
		return array;
	}

	public static long[] zeropArray(int size, long init) {
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

	public static String toString(char[] a) {
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

	public static int cntBoolean(boolean[] boo) {
		int cnt = 0;
		for (boolean b : boo) {
			if (b) cnt++;
		}
		return cnt;
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

	public static void debug(char[] x) {
		out(String.valueOf(x));
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
		StringJoiner joiner = new StringJoiner(" ");
		for (String str : a) {
			joiner.add(str);
		}
		System.out.println(joiner);
	}

	public void println(String[] a) {
		StringJoiner joiner = new StringJoiner("\r\n");
		for (String str : a) {
			joiner.add(str);
		}
		System.out.println(joiner);
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

	public void printZeroToOneIndex(long[] a) {
		print(a, i -> i + 1);
	}

	public void printZeroToOneIndex(List<Integer> a) {
		print(a.stream().map(i -> i + 1).collect(Collectors.toList()));
	}

	public void printlnZeroToOneIndex(List<Integer> a) {
		println(a.stream().map(i -> i + 1).collect(Collectors.toList()));
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

/**
 * ｘｙの二次元の座標クラス ユークリッド距離が算出可能
 */
class Point implements Comparable<Point> {
	long x;
	long y;

	Point(long x, long y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * 距離を求める。ただし値は平方根を付ける前
	 * 
	 * @param Point
	 * @return long 平方根を付ける前の距離
	 */
	public long dist(Point target) {
		return (long) (x - target.x) * (x - target.x) + (y - target.y) * (y - target.y);

	}

	@Override
	public int compareTo(Point o) {
		return (int) (x == o.x ? y - o.y : x - o.x);
	}

	@Override
	public int hashCode() {
		return (int) (x + y) * 31;
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Point)) return false;
		Point p = (Point) o;

		return x == p.x && y == p.y;
	}

	@Override
	public String toString() {
		return String.format("(%s  %s)", x, y);
	}
}

/**
 * グリッド上のカーソルを扱うクラス
 * 
 */
class GridCursor implements Comparable<GridCursor> {
	int h;
	int w;
	static char[][] input = null;
	static int maxH = -1;
	static int maxW = -1;
	static char OBSTACLE = '#';

	static final int[][] directions8 = { { 1, 0 }, { 1, -1 }, { 1, 1 }, { -1, 0 }, { -1, 1 }, { -1, -1 }, { 0, 1 },
			{ 0, -1 } };

	static final int[][] directions4 = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

	public boolean isInnner() {
		if (maxH < 0 || maxW < 0) {
			throw new IllegalStateException("h/w is not initialized!");
		}
		return h >= 0 && h < maxH && w >= 0 && w < maxW;
	}

	public GridCursor(int h, int w) {
		this.h = h;
		this.w = w;
	}

	public GridCursor(int h, int w, char[][] input) {
		this.h = h;
		this.w = w;
		GridCursor.input = input;
		maxH = input.length;
		maxW = input[0].length;
	}

	public GridCursor(int h, int w, int maxH, int maxW) {
		this.h = h;
		this.w = w;
		// MEMO staticのものを設定するのも微妙な気がする
		GridCursor.maxH = maxH;
		GridCursor.maxW = maxW;
	}

	public boolean move(char ch) {
		// MEMO enumにするべききもするが自分しか利用しないのでそのままとする
		if (ch != 'R' && ch != 'L' && ch != 'D' && ch != 'U') {
			throw new IllegalArgumentException("move method only allows R/L/D/U");
		}
		if (maxH < 0 || maxW < 0) {
			throw new IllegalStateException("h/w is not initialized!");
		}

		var nh = h;
		var nw = w;
		if (ch == 'R') nw++;
		if (ch == 'L') nw--;
		if (ch == 'D') nh++;
		if (ch == 'U') nh--;
		if (!(nh >= 0 && nw >= 0 && nh < maxH && nw < maxW)) return false;
		if (input != null && input[nh][nw] == OBSTACLE) return false;
		h = nh;
		w = nw;

		return true;
	}

	@Override
	public int compareTo(GridCursor o) {
		return (int) (h == o.h ? w - o.w : h - o.h);
	}

	@Override
	public int hashCode() {
		return (h + w) * 31;
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof GridCursor)) return false;
		GridCursor g = (GridCursor) o;

		return h == g.h && w == g.w;
	}

	@Override
	public String toString() {
		return String.format("(%s  %s)", h, w);
	}
}

class Pair<S extends Comparable<S>, T extends Comparable<T>> implements Comparable<Pair<S, T>> {
	S first;
	T second;

	public Pair(S s, T t) {
		first = s;
		second = t;
	}

	public S getFirst() {
		return first;
	}

	public T getSecond() {
		return second;
	}

	public boolean equals(Object another) {
		if (this == another) return true;
		if (!(another instanceof Pair)) return false;
		Pair otherPair = (Pair) another;
		return this.first.equals(otherPair.first) && this.second.equals(otherPair.second);
	}

	public int compareTo(Pair<S, T> another) {
		java.util.Comparator<Pair<S, T>> comp1 = java.util.Comparator.comparing(Pair::getFirst);
		java.util.Comparator<Pair<S, T>> comp2 = comp1.thenComparing(Pair::getSecond);
		return comp2.compare(this, another);
	}

	public int hashCode() {
		return first.hashCode() * 10007 + second.hashCode();
	}

	public String toString() {
		return String.format("%s %s", first, second);
	}
}

/**
 * 辺のクラス
 * 
 * @author ritoAtCoder
 *
 */
class Edge implements Comparable<Edge> {
	int from;
	int to;
	int cost;

	Edge(int from, int to, int cost) {
		this.from = from;
		this.to = to;
		this.cost = cost;
	}

	@Override
	public int compareTo(Edge that) {
//		java.util.Comparator<Pair<S, T>> comp1 = java.util.Comparator.comparing(Pair::getFirst);
//		java.util.Comparator<Pair<S, T>> comp2 = comp1.thenComparing(Pair::getSecond);
//		return comp2.compare(this, another);
//		Integer.compare(cost, e.cost).th
		int c = Integer.compare(this.cost, that.cost);
		if (c == 0) c = Integer.compare(this.to, that.to);
		if (c == 0) c = Integer.compare(this.from, that.from);

		return c;
//		return cost - e.cost != 0 ? cost - e.cost : (to - e.to != 0 ? to - e.to : from - e.from);
	}

	@Override
	public int hashCode() {
		return (from + to + cost) * 17;
	}

	@Override
	public String toString() {
		return String.format("%s -> %s (%s) ", from, to, cost);
	}

	@Override
	public boolean equals(Object another) {
		if (!(another instanceof Edge)) return false;
		if (this == another) return true;
		Edge o = (Edge) another;
		return from == o.from && to == o.to && cost == o.cost;
	}

}

class UnionFind {
	int[] parent, rank, size, minNode;
	int componentSize;// 連結成分の個数
	int[] value;// ノードごとに値を持ちたいとき。直接設定する

	public UnionFind(int n) {
		parent = new int[n];
		rank = new int[n];
		size = new int[n];// ノード内に含まれる数
		minNode = new int[n];
		value = new int[n];

		for (int i = 0; i < n; i++) {
			parent[i] = i;
			rank[i] = 0;// 0始まりとする
			size[i] = 1;
			minNode[i] = i;
		}
		componentSize = n;
	}

	public void clear() {
		int n = parent.length;
		parent = new int[n];
		rank = new int[n];
		size = new int[n];// ノード内に含まれる数
		minNode = new int[n];
		value = new int[n];

		for (int i = 0; i < n; i++) {
			parent[i] = i;
			rank[i] = 0;// 0始まりとする
			size[i] = 1;
			minNode[i] = i;
		}
		componentSize = n;
	}

	public int root(int x) {
		if (parent[x] == x) return x;
		return parent[x] = root(parent[x]);// 経路圧縮
	}

	public boolean isConneted(int x, int y) {
		return root(x) == root(y);
	}

	public boolean union(int x, int y) {
		x = root(x);
		y = root(y);
		if (x == y) return false;

		// ランクが高い方を常にx側で持つ
		if (rank[x] < rank[y]) {
			int temp = x;
			x = y;
			y = temp;
		}

		parent[y] = x;
		if (rank[x] == rank[y]) rank[x]++;
		size[x] += size[y];
		minNode[x] = Math.min(minNode[x], minNode[y]);

		value[x] += value[y];

		componentSize--;

		return true;
	}

	public int getSize(int x) {
		return size[root(x)];
	}

	public int getMinNode(int x) {
		return minNode[root(x)];
	}

	public int getComponentSize() {
		return componentSize;
	}

	public int getValue(int x) {
		return value[root(x)];
	}

	@Override
	public String toString() {
		return Arrays.toString(parent);
	}
}
