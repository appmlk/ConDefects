import static java.lang.Math.*;

import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.Stack;
import java.util.TreeMap;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.DoublePredicate;
import java.util.function.IntPredicate;
import java.util.function.IntUnaryOperator;
import java.util.function.LongBinaryOperator;
import java.util.function.LongPredicate;
import java.util.function.LongUnaryOperator;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

final class Main {
	public static void main(final String[] args) {
		final long begin = System.currentTimeMillis(), end;
		IntStream.range(0, VvyLw.MULTI ? VvyLw.io.ni() : 1).forEach(i -> VvyLw.solve());
		end = System.currentTimeMillis();
		VvyLw.io.dump(end - begin + "ms");
		VvyLw.io.close();
	}
}

final class VvyLw extends Utility {
	static final IO io = new IO(System.in, System.out, System.err, false);
	static final Random rd = new Random();
	static final boolean MULTI = false;
	static final int INF = 1 << 30;
	static final long LINF = (1L << 61) - 1;
	static final double EPS = 1e-18;
	static final int MOD = 998244353;
	static final int M0D = (int) 1e9 + 7;
	static final int[] dx = {0, -1, 1, 0, 0, -1, -1, 1, 1};
	static final int[] dy = {0, 0, 0, -1, 1, -1, 1, -1, 1};
	static final void solve() {
		final int n = io.ni();
		final var s = io.ns();
		final var c = io.ni(n);
		final long[] ne = new long[n + 1], no = new long[n + 1], re = new long[n + 1], ro = new long[n + 1];
		for(int i = 0; i < n; ++i) {
			ne[i + 1] = ne[i];
			no[i + 1] = no[i];
			if(i % 2 == 0) {
				if(s.charAt(i) == '0') {
					no[i + 1] += c[i];
				} else {
					ne[i + 1] += c[i];
				}
			} else {
				if(s.charAt(i) == '0') {
					ne[i + 1] += c[i];
				} else {
					no[i + 1] += c[i];
				}
			}
		}
		for(int i = n; --i >= 0;) {
			re[i] = re[i + 1];
			ro[i] = ro[i + 1];
			if(i % 2 == 0) {
				if(s.charAt(i) == '0') {
					re[i] += c[i];
				} else {
					ro[i] += c[i];
				}
			} else {
				if(s.charAt(i) == '0') {
					ro[i] += c[i];
				} else {
					re[i] += c[i];
				}
			}
		}
		long ans = LINF;
		for(int i = 0; ++i < n;) {
			ans = min(ans, ne[i] + re[i]);
			ans = min(ans, no[i] + ro[i]);
		}
		io.out(ans);
	}
}
class Utility {
	protected static final String yes(final boolean ok){ return ok ? "Yes" : "No"; }
	protected static final String no(final boolean ok){ return yes(!ok); }
	protected static final long sqr(final long x){ return x * x; }
	protected static final long cub(final long x){ return x * x * x; }
	protected static final int mod(long n, final int m) {
		n %= m;
		return (int) (n < 0 ? n + m : n);
	}
	protected static final long mod(long n, final long m) {
		n %= m;
		return n < 0 ? n + m : n;
	}
	protected static final double log(final double x, final long base){ return Math.log(x) / Math.log(base); }
	protected static final long intCeil(final long a, final long b){ return a == 0 ? 0 : (a - 1) / b + 1; }
	protected static final double intRound(final double a, final long b, final int c) {
		final long d = intPow(10, c);
		return rint((a * d) / b) / d;
	}
	protected static final long intPow(long a, int b) {
		long res = 1;
		while(b > 0) {
			if(b % 2 == 1) {
				res *= a;
			}
			a *= a;
			b >>= 1;
		}
		return res;
	}
	protected static final long intPow(long a, long b, final long m) {
		long res = 1;
		while(b > 0) {
			if(b % 2 == 1) {
				res *= a;
				res = mod(res, m);
			}
			a *= a;
			a = mod(a, m);
			b >>= 1;
		}
		return res;
	}
	protected static final long inv(long a, final long m) {
		long b = m, u = 1, v = 0;
		while(b > 0) {
			final long t = a / b;
			a -= t * b;
			a ^= b;
			b ^= a;
			a ^= b;
			u -= t * v;
			u ^= v;
			v ^= u;
			u ^= v;
		}
		return mod(u, m);
	}
	protected static final long lcm(final long a, final long b){ return a / gcd(a, b) * b; }
	protected static final long lcm(final int... a){ return Arrays.stream(a).asLongStream().reduce(1, (x, y) -> lcm(x, y)); }
	protected static final long lcm(final long... a){ return Arrays.stream(a).reduce(1, (x, y) -> lcm(x, y)); }
	protected static final long gcd(final long a, final long b){ return b > 0 ? gcd(b, a % b) : a; }
	protected static final int gcd(final int... a){ return Arrays.stream(a).reduce(0, (x, y) -> (int) gcd(x, y)); }
	protected static final long gcd(final long... a){ return Arrays.stream(a).reduce(0, (x, y) -> gcd(x, y)); }
	protected static final int min(final int... a){ return Arrays.stream(a).min().getAsInt(); }
	protected static final long min(final long... a){ return Arrays.stream(a).min().getAsLong(); }
	protected static final double min(final double... a){ return Arrays.stream(a).min().getAsDouble(); }
	protected static final int max(final int... a){ return Arrays.stream(a).max().getAsInt(); }
	protected static final long max(final long... a){ return Arrays.stream(a).max().getAsLong(); }
	protected static final double max(final double... a){ return Arrays.stream(a).max().getAsDouble(); }
	protected static final long sum(final int... a){ return Arrays.stream(a).asLongStream().sum(); }
	protected static final long sum(final long... a){ return Arrays.stream(a).sum(); }
	protected static final double sum(final double... a){ return Arrays.stream(a).sum(); }
	protected static final long prod(final int... a){ return Arrays.stream(a).asLongStream().reduce(1, (x, y) -> x * y); }
	protected static final long prod(final long... a){ return Arrays.stream(a).reduce(1, (x, y) -> x * y); }
	protected static final double prod(final double... a){ return Arrays.stream(a).reduce(1, (x, y) -> x * y); }
	protected static final double ave(final int... a){ return Arrays.stream(a).average().getAsDouble(); }
	protected static final double ave(final long... a){ return Arrays.stream(a).average().getAsDouble(); }
	protected static final double ave(final double... a){ return Arrays.stream(a).average().getAsDouble(); }
	protected static final double median(final int[] a) {
		assert isSorted(a);
		final int m = a.length / 2;
		return a.length % 2 != 0 ? a[m] : (a[m - 1] + a[m]) / 2.0;
	}
	protected static final double median(final long[] a) {
		assert isSorted(a);
		final int m = a.length / 2;
		return a.length % 2 != 0 ? a[m] : (a[m - 1] + a[m]) / 2.0;
	}
	protected static final double median(final double[] a) {
		assert isSorted(a);
		final int m = a.length / 2;
		return a.length % 2 != 0 ? a[m] : (a[m - 1] + a[m]) / 2;
	}
	protected static final long[] div(final long n) {
		final ArrayList<Long> d = new ArrayList<>();
		for(long i = 1; i * i <= n; ++i) {
			if(n % i == 0) {
				d.add(i);
				if(i * i != n) {
					d.add(n / i);
				}
			}
		}
		return d.stream().mapToLong(i -> i).sorted().toArray();
	}
	protected static final IntPair[] primeFactor(long n) {
		final ArrayList<IntPair> pf = new ArrayList<>();
		for(long i = 2; i * i <= n; ++i) {
			if(n % i != 0) {
				continue;
			}
			int cnt = 0;
			while(n % i == 0) {
				cnt++;
				n /= i;
			}
			pf.add(IntPair.of(i, cnt));
		}
		if(n != 1) {
			pf.add(IntPair.of(n, 1));
		}
		return pf.toArray(IntPair[]::new);
	}
	protected static final long eulerPhi(long n) {
		long res = n;
		for(long i = 2; i * i <= n; ++i) {
			if(n % i == 0) {
				res -= res / i;
				while(n % i == 0) {
					n /= i;
				}
			}
		}
		if(n > 1) {
			res -= res / n;
		}
		return res;
	}
	protected static final long sigma(final long n){ return n * (n + 1) / 2; }
	protected static final long sigma(final long a, final long b) {
		assert a <= b;
		return sigma(b) - sigma(a - 1);
	}
	protected static final long fact(int n) {
		long res = 1;
		while(n > 0) {
			res *= n--;
		}
		return res;
	}
	protected static final long fact(int n, final long mod) {
		long res = 1;
		while(n > 0) {
			res *= n--;
			res %= mod;
		}
		return res;
	}
	protected static final long perm(final int n, final int r) {
		int m = n;
		long res = 1;
		while(m > n - r) {
			res *= m--;
		}
		return res;
	}
	protected static final long perm(final int n, final int r, final long mod) {
		int m = n;
		long res = 1;
		while(m > n - r) {
			res *= m--;
			res %= mod; 
		}
		return res;
	}
	protected static final long binom(final int n, final int r) {
		if(r < 0 || n < r) {
			return 0;
		}
		int tmp = n;
		long res = 1;
		for(int i = 1; i <= min(n - r, r); ++i) {
			res *= tmp--;
			res /= i;
		}
		return res;
	}
	protected static final long binom(final int n, final int r, final long mod) {
		if(r < 0 || n < r) {
			return 0;
		}
		int tmp = n;
		long res = 1;
		for(int i = 1; i <= min(n - r, r); ++i) {
			res *= tmp--;
			res = mod;
			res /= i;
			res %= mod;
		}
		return res;
	}
	protected static final boolean isInt(final double n){ return n == (long) floor(n); }
	protected static final boolean isSqr(final long n){ return isInt(sqrt(n)); }
	protected static final boolean isPrime(final long n) {
		if(n == 1) {
			return false;
		}
		for(long i = 2; i * i <= n; ++i) {
			if(n % i == 0) {
				return false;
			}
		}
		return true;
	}
	protected static final boolean scope(final int l, final int x, final int r){ return l <= x && x <= r; }
	protected static final boolean scope(final long l, final long x, final long r){ return l <= x && x <= r; }
	protected static final boolean scope(final double l, final double x, final double r){ return l <= x && x <= r; }
	protected static final int clamp(final int l, final int x, final int r){ return x < l ? l : x > r ? r : x; }
	protected static final long clamp(final long l, final long x, final long r){ return x < l ? l : x > r ? r : x; }
	protected static final double clamp(final double l, final double x, final double r){ return x < l ? l : x > r ? r : x; }
	protected static final boolean isBit(final long i, final long j){ return (i >> j & 1) == 1; }
	protected static final boolean nextPerm(final int[] a) {
		try {
			final int[] res = nextPermutation(a);
			System.arraycopy(res, 0, a, 0, a.length);
			return true;
		} catch(final NullPointerException e) {
			Arrays.sort(a);
			return false;
		}
	}
	protected static final boolean nextPerm(final long[] a) {
		try {
			final long[] res = nextPermutation(a);
			System.arraycopy(res, 0, a, 0, a.length);
			return true;
		} catch(final NullPointerException e) {
			Arrays.sort(a);
			return false;
		}
	}
	protected static final boolean nextPerm(final double[] a) {
		try {
			final double[] res = nextPermutation(a);
			System.arraycopy(res, 0, a, 0, a.length);
			return true;
		} catch(final NullPointerException e) {
			Arrays.sort(a);
			return false;
		}
	}
	protected static final boolean nextPerm(final char[] a) {
		try {
			final char[] res = nextPermutation(a);
			System.arraycopy(res, 0, a, 0, a.length);
			return true;
		} catch(final NullPointerException e) {
			Arrays.sort(a);
			return false;
		}
	}
	protected static final boolean prevPerm(final int[] a) {
		try {
			final int[] res = prevPermutation(a);
			System.arraycopy(res, 0, a, 0, a.length);
			return true;
		} catch(final NullPointerException e) {
			final int[] res = reverse(sorted(a));
			System.arraycopy(res, 0, a, 0, a.length);
			return false;
		}
	}
	protected static final boolean prevPerm(final long[] a) {
		try {
			final long[] res = prevPermutation(a);
			System.arraycopy(res, 0, a, 0, a.length);
			return true;
		} catch(final NullPointerException e) {
			final long[] res = reverse(sorted(a));
			System.arraycopy(res, 0, a, 0, a.length);
			return false;
		}
	}
	protected static final boolean prevPerm(final double[] a) {
		try {
			final double[] res = prevPermutation(a);
			System.arraycopy(res, 0, a, 0, a.length);
			return true;
		} catch(final NullPointerException e) {
			final double[] res = reverse(sorted(a));
			System.arraycopy(res, 0, a, 0, a.length);
			return false;
		}
	}
	protected static final boolean prevPerm(final char[] a) {
		try {
			final char[] res = prevPermutation(a);
			System.arraycopy(res, 0, a, 0, a.length);
			return true;
		} catch(final NullPointerException e) {
			final char[] res = reverse(sorted(a));
			System.arraycopy(res, 0, a, 0, a.length);
			return false;
		}
	}
	private static final int[] nextPermutation(final int[] a) {
		for(int i = a.length; --i > 0;) {
			if(a[i - 1] < a[i]) {
				final int j = find(a[i - 1], a, i, a.length - 1);
				swap(a, i - 1, j);
				Arrays.sort(a, i, a.length);
				return a;
			}
		}
		return null;
	}
	private static final long[] nextPermutation(final long[] a) {
		for(int i = a.length; --i > 0;) {
			if(a[i - 1] < a[i]) {
				final int j = find(a[i - 1], a, i, a.length - 1);
				swap(a, i - 1, j);
				Arrays.sort(a, i, a.length);
				return a;
			}
		}
		return null;
	}
	private static final double[] nextPermutation(final double[] a) {
		for(int i = a.length; --i > 0;) {
			if(a[i - 1] < a[i]) {
				final int j = find(a[i - 1], a, i, a.length - 1);
				swap(a, i - 1, j);
				Arrays.sort(a, i, a.length);
				return a;
			}
		}
		return null;
	}
	private static final char[] nextPermutation(final char[] a) {
		for(int i = a.length; --i > 0;) {
			if(a[i - 1] < a[i]) {
				final int j = find(a[i - 1], a, i, a.length - 1);
				swap(a, i - 1, j);
				Arrays.sort(a, i, a.length);
				return a;
			}
		}
		return null;
	}
	private static final int[] prevPermutation(final int[] a) {
		for(int i = a.length; --i > 0;) {
			if(a[i - 1] > a[i]) {
				final int j = findRev(a[i - 1], a, i, a.length - 1);
				swap(a, i - 1, j);
				Arrays.sort(a, i, a.length);
				reverse(a, i, a.length - 1);
				return a;
			}
		}
		return null;
	}
	private static final long[] prevPermutation(final long[] a) {
		for(int i = a.length; --i > 0;) {
			if(a[i - 1] > a[i]) {
				final int j = findRev(a[i - 1], a, i, a.length - 1);
				swap(a, i - 1, j);
				Arrays.sort(a, i, a.length);
				reverse(a, i, a.length - 1);
				return a;
			}
		}
		return null;
	}
	private static final double[] prevPermutation(final double[] a) {
		for(int i = a.length; --i > 0;) {
			if(a[i - 1] > a[i]) {
				final int j = findRev(a[i - 1], a, i, a.length - 1);
				swap(a, i - 1, j);
				Arrays.sort(a, i, a.length);
				reverse(a, i, a.length - 1);
				return a;
			}
		}
		return null;
	}
	private static final char[] prevPermutation(final char[] a) {
		for(int i = a.length; --i > 0;) {
			if(a[i - 1] > a[i]) {
				final int j = findRev(a[i - 1], a, i, a.length - 1);
				swap(a, i - 1, j);
				Arrays.sort(a, i, a.length);
				reverse(a, i, a.length - 1);
				return a;
			}
		}
		return null;
	}
	private static final int find(final int dest, final int[] a, final int s, final int e) {
		if(s == e) {
			return s;
		}
		final int m = (s + e + 1) / 2;
		return a[m] <= dest ? find(dest, a, s, m - 1) : find(dest, a, m, e);
	}
	private static final int find(final long dest, final long[] a, final int s, final int e) {
		if(s == e) {
			return s;
		}
		final int m = (s + e + 1) / 2;
		return a[m] <= dest ? find(dest, a, s, m - 1) : find(dest, a, m, e);
	}
	private static final int find(final double dest, final double[] a, final int s, final int e) {
		if(s == e) {
			return s;
		}
		final int m = (s + e + 1) / 2;
		return a[m] <= dest ? find(dest, a, s, m - 1) : find(dest, a, m, e);
	}
	private static final int find(final char dest, final char[] a, final int s, final int e) {
		if(s == e) {
			return s;
		}
		final int m = (s + e + 1) / 2;
		return a[m] <= dest ? find(dest, a, s, m - 1) : find(dest, a, m, e);
	}
	private static final int findRev(final int dest, final int[] a, final int s, final int e) {
		if(s == e) {
			return s;
		}
		final int m = (s + e + 1) / 2;
		return a[m] > dest ? findRev(dest, a, s, m - 1) : findRev(dest, a, m, e);
	}
	private static final int findRev(final long dest, final long[] a, final int s, final int e) {
		if(s == e) {
			return s;
		}
		final int m = (s + e + 1) / 2;
		return a[m] > dest ? findRev(dest, a, s, m - 1) : findRev(dest, a, m, e);
	}
	private static final int findRev(final double dest, final double[] a, final int s, final int e) {
		if(s == e) {
			return s;
		}
		final int m = (s + e + 1) / 2;
		return a[m] > dest ? findRev(dest, a, s, m - 1) : findRev(dest, a, m, e);
	}
	private static final int findRev(final char dest, final char[] a, final int s, final int e) {
		if(s == e) {
			return s;
		}
		final int m = (s + e + 1) / 2;
		return a[m] > dest ? findRev(dest, a, s, m - 1) : findRev(dest, a, m, e);
	}
	private static void reverse(final int[] arr, int start, int end) {
		while(start < end) {
			swap(arr, start, end);
			start++;
			end--;
		}
	}
	private static void reverse(final long[] arr, int start, int end) {
		while(start < end) {
			swap(arr, start, end);
			start++;
			end--;
		}
	}
	private static void reverse(final double[] arr, int start, int end) {
		while(start < end) {
			swap(arr, start, end);
			start++;
			end--;
		}
	}
	private static void reverse(final char[] arr, int start, int end) {
		while(start < end) {
			swap(arr, start, end);
			start++;
			end--;
		}
	}
	protected static final int find(final int[] a, final int x) {
		for(int i = 0; i < a.length; ++i) {
			if(a[i] == x) {
				return i;
			}
		}
		return -1;
	}
	protected static final int find(final long[] a, final long x) {
		for(int i = 0; i < a.length; ++i) {
			if(a[i] == x) {
				return i;
			}
		}
		return -1;
	}
	protected static final int find(final double[] a, final double x) {
		for(int i = 0; i < a.length; ++i) {
			if(a[i] == x) {
				return i;
			}
		}
		return -1;
	}
	protected static final int find(final char[] s, final char c) {
		for(int i = 0; i < s.length; ++i) {
			if(s[i] == c) {
				return i;
			}
		}
		return -1;
	}
	protected static final int find(final Object[] a, final Object x) {
		for(int i = 0; i < a.length; ++i) {
			if(a[i].equals(x)) {
				return i;
			}
		}
		return -1;
	}
	protected static final int findRev(final int[] a, final int x) {
		for(int i = a.length; --i >= 0;) {
			if(a[i] == x) {
				return i;
			}
		}
		return -1;
	}
	protected static final int findRev(final long[] a, final long x) {
		for(int i = a.length; --i >= 0;) {
			if(a[i] == x) {
				return i;
			}
		}
		return -1;
	}
	protected static final int findRev(final double[] a, final double x) {
		for(int i = a.length; --i >= 0;) {
			if(a[i] == x) {
				return i;
			}
		}
		return -1;
	}
	protected static final int findRev(final char[] s, final char c) {
		for(int i = s.length; --i >= 0;) {
			if(s[i] == c) {
				return i;
			}
		}
		return -1;
	}
	protected static final int findRev(final Object[] a, final Object x) {
		for(int i = a.length; --i >= 0;) {
			if(a[i].equals(x)) {
				return i;
			}
		}
		return -1;
	}
	protected static final boolean binarySearch(final int[] a, final int x){ return Arrays.binarySearch(a, x) >= 0; }
	protected static final boolean binarySearch(final long[] a, final long x){ return Arrays.binarySearch(a, x) >= 0; }
	protected static final <T extends Comparable<? super T>> boolean binarySearch(final T[] a, final T x){ return Arrays.binarySearch(a, x) >= 0; }
	protected static final <T extends Comparable<? super T>> boolean binarySearch(final List<T> a, final T x){ return Collections.binarySearch(a, x, null) >= 0; }
	protected static final int lowerBound(final int[] a, final int x){ return bins(a.length, -1, (IntPredicate) y -> a[y] >= x); }
	protected static final int lowerBound(final long[] a, final long x){ return bins(a.length, -1, (IntPredicate) y -> a[y] >= x); }
	protected static final <T extends Comparable<? super T>> int lowerBound(final T[] a, final T x){ return lowerBound(Arrays.asList(a), x); }
	protected static final <T extends Comparable<? super T>> int lowerBound(final List<T> a, final T x){ return ~Collections.binarySearch(a, x, (p, q) -> p.compareTo(q) >= 0 ? 1 : -1); }
	protected static final int upperBound(final int[] a, final int x){ return bins(a.length, -1, (IntPredicate) y -> a[y] > x); }
	protected static final int upperBound(final long[] a, final long x){ return bins(a.length, -1, (IntPredicate) y -> a[y] > x); }
	protected static final <T extends Comparable<? super T>> int upperBound(final T[] a, final T x){ return upperBound(Arrays.asList(a), x); }
	protected static final <T extends Comparable<? super T>> int upperBound(final List<T> a, final T x){ return ~Collections.binarySearch(a, x, (p, q) -> p.compareTo(q) > 0 ? 1 : -1); }
	protected static final String sorted(final String s){ return s.chars().sorted().mapToObj(Character::toString).collect(Collectors.joining()); }
	protected static final int[] sorted(final int[] a){ return Arrays.stream(a).sorted().toArray(); }
	protected static final long[] sorted(final long[] a){ return Arrays.stream(a).sorted().toArray(); }
	protected static final double[] sorted(final double[] a){ return Arrays.stream(a).sorted().toArray(); }
	protected static final char[] sorted(final char[] a){ return sorted(new String(a)).toCharArray(); }
	protected static final <T extends Comparable<? super T>> T[] sorted(final T[] a){ return Arrays.stream(a).sorted().toArray(n -> Arrays.copyOf(a, n)); }
	protected static final boolean isSorted(final String s){ return s.equals(sorted(s)); }
	protected static final boolean isSorted(final int[] a){ return Arrays.equals(a, sorted(a)); }
	protected static final boolean isSorted(final long[] a){ return Arrays.equals(a, sorted(a)); }
	protected static final boolean isSorted(final double[] a){ return Arrays.equals(a, sorted(a)); }
	protected static final boolean isSorted(final char[] a){ return Arrays.equals(a, sorted(a)); }
	protected static final <T extends Comparable<? super T>> boolean isSorted(final T[] a){ return Arrays.equals(a, sorted(a)); }
	protected static final String reverse(final String s){ return new StringBuilder(s).reverse().toString(); }
	protected static final int[] reverse(final int[] a) {
		final int n = a.length;
		final int[] b = new int[n];
		for(int i = 0; i <= n / 2; ++i) {
			b[i] = a[n - 1 - i];
			b[n - 1 - i] = a[i];
		}
		return b;
	}
	protected static final long[] reverse(final long[] a) {
		final int n = a.length;
		final long[] b = new long[n];
		for(int i = 0; i <= n / 2; ++i) {
			b[i] = a[n - 1 - i];
			b[n - 1 - i] = a[i];
		}
		return b;
	}
	protected static final double[] reverse(final double[] a) {
		final int n = a.length;
		final double[] b = new double[n];
		for(int i = 0; i <= n / 2; ++i) {
			b[i] = a[n - 1 - i];
			b[n - 1 - i] = a[i];
		}
		return b;
	}
	protected static final char[] reverse(final char[] a) {
		final int n = a.length;
		final char[] b = new char[n];
		for(int i = 0; i <= n / 2; ++i) {
			b[i] = a[n - 1 - i];
			b[n - 1 - i] = a[i];
		}
		return b;
	}
	protected static final Object[] reverse(final Object[] a) {
		final int n = a.length;
		final Object[] b = new Object[n];
		for(int i = 0; i <= n / 2; ++i) {
			b[i] = a[n - 1 - i];
			b[n - 1 - i] = a[i];
		}
		return b;
	}
	protected static final int[] rotate(final int[] a, final int id) {
		final int n = a.length, k = (int) mod(id, n);
		final int[] res = new int[n];
		System.arraycopy(a, k, res, 0, n - k);
		System.arraycopy(a, 0, res, n - k, k);
		return res;
	}
	protected static final long[] rotate(final long[] a, final int id) {
		final int n = a.length, k = (int) mod(id, n);
		final long[] res = new long[n];
		System.arraycopy(a, k, res, 0, n - k);
		System.arraycopy(a, 0, res, n - k, k);
		return res;
	}
	protected static final double[] rotate(final double[] a, final int id) {
		final int n = a.length, k = (int) mod(id, n);
		final double[] res = new double[n];
		System.arraycopy(a, k, res, 0, n - k);
		System.arraycopy(a, 0, res, n - k, k);
		return res;
	}
	protected static final char[] rotate(final char[] a, final int id) {
		final int n = a.length, k = (int) mod(id, n);
		final char[] res = new char[n];
		System.arraycopy(a, k, res, 0, n - k);
		System.arraycopy(a, 0, res, n - k, k);
		return res;
	}
	protected static final boolean[] rotate(final boolean[] a, final int id) {
		final int n = a.length, k = (int) mod(id, n);
		final boolean[] res = new boolean[n];
		System.arraycopy(a, k, res, 0, n - k);
		System.arraycopy(a, 0, res, n - k, k);
		return res;
	}
	protected static final Object[] rotate(final Object[] a, final int id) {
		final int n = a.length, k = (int) mod(id, n);
		final Object[] res = new Object[n];
		System.arraycopy(a, k, res, 0, n - k);
		System.arraycopy(a, 0, res, n - k, k);
		return res;
	}
	protected static final String rotate(final String s, final int id) {
		final List<Character> t = s.chars().mapToObj(i -> (char) i).collect(Collectors.toList());
		Collections.rotate(t, -id);
		return t.stream().map(String::valueOf).collect(Collectors.joining());
	}
	protected static final int[][] rotateR(final int[][] a) {
		final int h = a.length, w = a[0].length;
		final int[][] b = new int[w][h];
		IntStream.range(0, h).forEach(i -> {
			Arrays.setAll(b[i], j -> a[j][i]);
		});
		IntStream.range(0, w).forEach(i -> b[i] = reverse(b[i]));
		return b;
	}
	protected static final long[][] rotateR(final long[][] a) {
		final int h = a.length, w = a[0].length;
		final long[][] b = new long[w][h];
		IntStream.range(0, h).forEach(i -> {
			Arrays.setAll(b[i], j -> a[j][i]);
		});
		IntStream.range(0, w).forEach(i -> b[i] = reverse(b[i]));
		return b;
	}
	protected static final double[][] rotateR(final double[][] a) {
		final int h = a.length, w = a[0].length;
		final double[][] b = new double[w][h];
		IntStream.range(0, h).forEach(i -> {
			Arrays.setAll(b[i], j -> a[j][i]);
		});
		IntStream.range(0, w).forEach(i -> b[i] = reverse(b[i]));
		return b;
	}
	protected static final char[][] rotateR(final char[][] a) {
		final int h = a.length, w = a[0].length;
		final char[][] b = new char[w][h];
		IntStream.range(0, h).forEach(i -> {
			IntStream.range(0, w).forEach(j -> b[j][i] = a[i][j]);
		});
		IntStream.range(0, w).forEach(i -> b[i] = reverse(b[i]));
		return b;
	}
	protected static final int[][] rotateL(final int[][] a) {
		final int h = a.length, w = a[0].length;
		final int[][] b = new int[w][h];
		IntStream.range(0, h).forEach(i -> {
			Arrays.setAll(b[i], j -> a[j][w - i - 1]);
		});
		return b;
	}
	protected static final long[][] rotateL(final long[][] a) {
		final int h = a.length, w = a[0].length;
		final long[][] b = new long[w][h];
		IntStream.range(0, h).forEach(i -> {
			Arrays.setAll(b[i], j -> a[j][w - i - 1]);
		});
		return b;
	}
	protected static final double[][] rotateL(final double[][] a) {
		final int h = a.length, w = a[0].length;
		final double[][] b = new double[w][h];
		IntStream.range(0, h).forEach(i -> {
			Arrays.setAll(b[i], j -> a[j][w - i - 1]);
		});
		return b;
	}
	protected static final char[][] rotateL(final char[][] a) {
		final int h = a.length, w = a[0].length;
		final char[][] b = new char[w][h];
		IntStream.range(0, h).forEach(i -> {
			IntStream.range(0, w).forEach(j -> b[w - j - 1][i] = a[i][j]);
		});
		return b;
	}
	protected static final void swap(final int[] a, final int i, final int j) {
		a[i] ^= a[j];
		a[j] ^= a[i];
		a[i] ^= a[j];
	}
	protected static final void swap(final long[] a, final int i, final int j) {
		a[i] ^= a[j];
		a[j] ^= a[i];
		a[i] ^= a[j];
	}
	protected static final void swap(final double[] a, final int i, final int j) {
		final double tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}
	protected static final void swap(final char[] a, final int i, final int j) {
		a[i] ^= a[j];
		a[j] ^= a[i];
		a[i] ^= a[j];
	}
	protected static final void swap(final boolean[] a, final int i, final int j) {
		a[i] ^= a[j];
		a[j] ^= a[i];
		a[i] ^= a[j];
	}
	protected static final void swap(final Object[] a, final int i, final int j) {
		final Object tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}
	protected static final void swap(final int[] a, final int[] b) {
		assert a.length == b.length;
		final int n = a.length;
		final int[] c = a.clone();
		System.arraycopy(b, 0, a, 0, n);
		System.arraycopy(c, 0, b, 0, n);
	}
	protected static final void swap(final long[] a, final long[] b) {
		assert a.length == b.length;
		final int n = a.length;
		final long[] c = a.clone();
		System.arraycopy(b, 0, a, 0, n);
		System.arraycopy(c, 0, b, 0, n);
	}
	protected static final void swap(final double[] a, final double[] b) {
		assert a.length == b.length;
		final int n = a.length;
		final double[] c = a.clone();
		System.arraycopy(b, 0, a, 0, n);
		System.arraycopy(c, 0, b, 0, n);
	}
	protected static final void swap(final char[] a, final char[] b) {
		assert a.length == b.length;
		final int n = a.length;
		final char[] c = a.clone();
		System.arraycopy(b, 0, a, 0, n);
		System.arraycopy(c, 0, b, 0, n);
	}
	protected static final void swap(final boolean[] a, final boolean[] b) {
		assert a.length == b.length;
		final int n = a.length;
		final boolean[] c = a.clone();
		System.arraycopy(b, 0, a, 0, n);
		System.arraycopy(c, 0, b, 0, n);
	}
	protected static final void swap(final Object[] a, final Object[] b) {
		assert a.length == b.length;
		final int n = a.length;
		final Object[] c = a.clone();
		System.arraycopy(b, 0, a, 0, n);
		System.arraycopy(c, 0, b, 0, n);
	}
	protected static final <F extends Comparable<? super F>, S extends Comparable<? super S>> Pair<S, F>[] swap(final Pair<F, S>[] p) {
		@SuppressWarnings("unchecked")
		final Pair<S, F>[] q = new Pair[p.length];
		Arrays.setAll(q, i -> p[i].swap());
		return q;
	}
	protected static final IntPair[] swap(final IntPair[] p) {
		final IntPair[] q = new IntPair[p.length];
		Arrays.setAll(q, i -> p[i].swap());
		return q;
	}
	protected static final FloatPair[] swap(final FloatPair[] p) {
		final FloatPair[] q = new FloatPair[p.length];
		Arrays.setAll(q, i -> p[i].swap());
		return q;
	}
	@SuppressWarnings("unchecked")
	protected static final <F extends Comparable<? super F>, S extends Comparable<? super S>> F[] first(final Pair<F, S>[] p){ return (F[]) Arrays.stream(p).map(i -> i.first).toArray(); }
	protected static final long[] first(final IntPair[] p){ return Arrays.stream(p).mapToLong(i -> i.first).toArray(); }
	protected static final double[] first(final FloatPair[] p){ return Arrays.stream(p).mapToDouble(i -> i.first).toArray(); }
	@SuppressWarnings("unchecked")
	protected static final <F extends Comparable<? super F>, S extends Comparable<? super S>> S[] second(final Pair<F, S>[] p){ return (S[]) Arrays.stream(p).map(i -> i.second).toArray(); }
	protected static final long[] second(final IntPair[] p){ return Arrays.stream(p).mapToLong(i -> i.second).toArray(); }
	protected static final double[] second(final FloatPair[] p){ return Arrays.stream(p).mapToDouble(i -> i.second).toArray(); }
	protected static final IntStream iota(final int n){ return IntStream.range(0, n); }
	protected static final IntStream iota(final int n, final int init){ return IntStream.range(0 + init, n + init); }
	protected static final int bins(int ok, int ng, final IntPredicate fn) {
		while(abs(ok - ng) > 1) {
			final int mid = (ok + ng) / 2;
			if(fn.test(mid)) {
				ok = mid;
			}
			else {
				ng = mid;
			}
		}
		return ok;
	}
	protected static final long bins(long ok, long ng, final LongPredicate fn) {
		while(abs(ok - ng) > 1) {
			final long mid = (ok + ng) / 2;
			if(fn.test(mid)) {
				ok = mid;
			}
			else {
				ng = mid;
			}
		}
		return ok;
	}
	protected static final double bins(double ok, double ng, final DoublePredicate fn) {
		while(abs(ok - ng) > VvyLw.EPS) {
			final double mid = (ok + ng) / 2;
			if(fn.test(mid)) {
				ok = mid;
			}
			else {
				ng = mid;
			}
		}
		return ok;
	}
	protected static final Map<Integer, Integer> counter(final int[] a) {
		final Map<Integer, Integer> res = new HashMap<>();
		for(final int i: a) {
			res.merge(i, 1, (x, y) -> x + y);
		}
		return res;
	}
	protected static final Map<Long, Integer> counter(final long[] a) {
		final Map<Long, Integer> res = new HashMap<>();
		for(final long i: a) {
			res.merge(i, 1, (x, y) -> x + y);
		}
		return res;
	}
	protected static final long innerProd(final IntPair... p){ return iota(p.length).mapToLong(i -> p[i].first.longValue() * p[i].second.longValue()).sum(); }
	protected static final double innerProd(final FloatPair... p){ return iota(p.length).mapToDouble(i -> p[i].first.doubleValue() * p[i].second.doubleValue()).sum(); }
	protected static final FloatPair intersection(final IntPair a, final long sec1, final IntPair b, final long sec2) {
		double m1, m2, b1, b2;
		if(a.second.longValue() == 0 && b.second.longValue() == 0) {
			return null;
		} else if(a.second.longValue() == 0) {
			m2 = -b.first.doubleValue() / b.second.longValue();
			b2 = -sec2 / b.second.doubleValue();
			final double x = -sec1 / a.first.doubleValue(), y = b2 + m2 * x; 
			return FloatPair.of(x, y);
		} else if(b.second.longValue() == 0) {
			m1 = -a.first.doubleValue() / a.second.longValue();
			b1 = -sec1 / a.second.doubleValue();
			final double x = -sec2 / b.first.doubleValue(), y = b1 + m1 * x;
			return FloatPair.of(x, y);
		}
		m1 = -a.first.doubleValue() / a.second.longValue();
		m2 = -b.first.doubleValue() / b.second.longValue();
		b1 = -sec1 / a.second.doubleValue();
		b2 = -sec2 / b.second.doubleValue();
		assert m1 != m2;
		final double x = (b1 - b2) / (m2 - m1), y = m1 * x + b1;
		return FloatPair.of(x, y);
	}
	protected static final FloatPair intersection(final FloatPair a, final double sec1, final FloatPair b, final double sec2) {
		double m1, m2, b1, b2;
		if(a.second.doubleValue() == 0 && b.second.doubleValue() == 0) {
			return null;
		} else if(a.second.doubleValue() == 0) {
			m2 = -b.first.doubleValue() / b.second.doubleValue();
			b2 = -sec2 / b.second.doubleValue();
			final double x = -sec1 / a.first.doubleValue(), y = b2 + m2 * x; 
			return FloatPair.of(x, y);
		} else if(b.second.doubleValue() == 0) {
			m1 = -a.first.doubleValue() / a.second.doubleValue();
			b1 = -sec1 / a.second.doubleValue();
			final double x = -sec2 / b.first.doubleValue(), y = b1 + m1 * x;
			return FloatPair.of(x, y);
		}
		m1 = -a.first.doubleValue() / a.second.doubleValue();
		m2 = -b.first.doubleValue() / b.second.doubleValue();
		b1 = -sec1 / a.second.doubleValue();
		b2 = -sec2 / b.second.doubleValue();
		assert m1 != m2;
		final double x = (b1 - b2) / (m2 - m1), y = m1 * x + b1;
		return FloatPair.of(x, y);
	}
	protected static final int[] corPress(final int[] a) {
		final int[] res = new int[a.length];
		final int[] x = Arrays.stream(a).sorted().distinct().toArray();
		Arrays.setAll(res, i -> lowerBound(x, a[i]));
		return res;
	}
	protected static final int[] corPress(final long[] a) {
		final int[] res = new int[a.length];
		final long[] x = Arrays.stream(a).sorted().distinct().toArray();
		Arrays.setAll(res, i -> lowerBound(x, a[i]));
		return res;
	}
	protected static final String runLenPress(final String s) {
		final int n = s.length();
		final StringBuilder sb = new StringBuilder();
		for(int l = 0; l < n;) {
			int r = l + 1;
			for(; r < n && s.charAt(l) == s.charAt(r); ++r){}
			sb.append(s.charAt(l));
			sb.append(r - l);
			l = r;
		}
		return sb.toString();
	}
	protected static final String runLenRev(final String s) {
		final int n = s.length();
		final StringBuilder sb = new StringBuilder();
		for(int l = 0; l < n;) {
			int r = l + 1;
			for(; r < n && scope('0', s.charAt(r), '9'); ++r){}
			sb.append(String.valueOf(s.charAt(l)).repeat(Integer.parseInt(s.substring(l + 1, r))));
			l = r;
		}
		return sb.toString();
	}
	protected static final int[] zAlgorithm(final String s) {
		final int n = s.length();
		int j = 0;
		final int[] pre = new int[n];
		for(int i = 0; ++i < n;) {
			if(i + pre[i - j] < j + pre[j]) {
				pre[i] = pre[i - j];
			}
			else {
				int k = max(0, j + pre[j] - i);
				while(i + k < n && s.charAt(k) == s.charAt(i + k)) {
					++k;
				}
				pre[i] = k;
				j = i;
			}
		}
		pre[0] = n;
		return pre;
	}
	protected static final int[] manacher(final String s_, final boolean calcEven) {
		int n = s_.length();
		final char[] s;
		if(calcEven) {
			s = new char[2 * n - 1];
			IntStream.range(0, n).forEach(i -> s[i] = s_.charAt(i));
			for(int i = n; --i >= 0;) {
				s[2 * i] = s_.charAt(i);
			}
			final char d = Collections.min(s_.chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
			for(int i = 0; i < n - 1; ++i) {
				s[2 * i + 1] = d;
			}
		} else {
			s = new char[n];
			IntStream.range(0, n).forEach(i -> s[i] = s_.charAt(i));
		}
		n = s.length;
		final int[] rad = new int[n];
		for(int i = 0, j = 0; i < n;) {
			while(i - j >= 0 && i + j < n && s[i - j] == s[i + j]) {
				++j;
			}
			rad[i] = j;
			int k = 1;
			while(i - k >= 0 && i + k < n && k + rad[i - k] < j) {
				rad[i + k] = rad[i - k];
				++k;
			}
			i += k;
			j -= k;
		}
		if(calcEven) {
			for(int i = 0; i < n; ++i) {
				if(((i ^ rad[i]) & 1) == 0) {
					rad[i]--;
				}
			}
		} else {
			for(int x: rad) {
				x = 2 * x - 1;
			}
		}
		return rad;
	}
	protected static final long kthRoot(final long n, final int k) {
		if(k == 1) {
			return n;
		}
		final LongPredicate chk = x -> {
			long mul = 1;
			for(int j = 0; j < k; ++j) {
				try {
					mul = multiplyExact(mul, x);
				} catch(final ArithmeticException e) {
					return false;
				}
			}
			return mul <= n;
		};
		long ret = 0;
		for(int i = 32; --i >= 0;) {
			if(chk.test(ret | (1L << i))) {
				ret |= 1L << i;
			}
		}
		return ret;
	}
	protected static final long tetration(final long a, final long b, final long m) {
		if(m == 1) {
			return 0;
		}
		if(a == 0) {
			return (b & 1) == 0 ? 1 : 0;
		}
		if(b == 0) {
			return 1;
		}
		if(b == 1) {
			return a % m;
		}
		if(b == 2) {
			return intPow(a, a, m);
		}
		final long phi = eulerPhi(m);
		long tmp = tetration(a, b - 1, phi);
		if(tmp == 0) {
			tmp += phi;
		}
		return intPow(a, tmp, m);
	}
	protected static final long floorSum(final long n, final long m, long a, long b) {
		long ans = 0;
		if(a >= m) {
			ans += (n - 1) * n * (a / m) / 2;
			a %= m;
		}
		if(b >= m) {
			ans += n * (b / m);
			b %= m;
		}
		final long ym = (a * n + b) / m, xm = (ym * m - b);
		if(ym == 0) {
			return ans;
		}
		ans += (n - (xm + a - 1) / a) * ym;
		ans += floorSum(ym, a, m, (a - xm % a) % a);
		return ans;
	}
}

interface TriFunction<T, U, V, R> {
	R apply(final T a, final U b, final V c);
}
interface QuadFunction<A, B, C, D, R> {
	R apply(final A a, final B b, final C c, final D d);
}
interface TriConsumer<T, U, V> {
	void accept(final T a, final U b, final V c);
}
interface TriPredicate<T, U, V> {
	boolean test(final T a, final U b, final V c);
}
interface RecursiveFunction<T, R> {
	R apply(final RecursiveFunction<T, R> rec, final T n);
}
interface RecursiveBiFunction<T, U, R> {
	R apply(final RecursiveBiFunction<T, U, R> rec, final T n, final U m);
}
interface RecursiveTriFunction<T, U, V, R> {
	R apply(final RecursiveTriFunction<T, U, V, R> rec, final T p, final U q, final V r);
}
interface RecursiveUnaryOperator<T> {
	T apply(final RecursiveUnaryOperator<T> rec, final T n);
}
interface RecursiveBinaryOperator<T> {
	T apply(final RecursiveBinaryOperator<T> rec, final T a, final T b);
}
interface RecursiveConsumer<T> {
	void accept(final RecursiveConsumer<T> rec, final T x);
}
interface RecursiveBiConsumer<T, U> {
	void accept(final RecursiveBiConsumer<T, U> rec, final T x, final U y);
}
interface RecursiveTriConsumer<T, U, V> {
	void accept(final RecursiveTriConsumer<T, U, V> rec, final T x, final U y, final V z);
}
interface RecursivePredicate<T> {
	boolean test(final RecursivePredicate<T> rec, final T n);
}
interface RecursiveBiPredicate<T, U> {
	boolean test(final RecursiveBiPredicate<T, U> rec, final T x, final U y);
}
interface RecursiveTriPredicate<T, U, V> {
	boolean test(final RecursiveTriPredicate<T, U, V> rec, final T x, final U y, final V z);
}
interface RecursiveIntFunction<R> {
	R apply(final RecursiveIntFunction<R> rec, final int n);
}
interface RecursiveLongFunction<R> {
	R apply(final RecursiveLongFunction<R> rec, final long n);
}
interface RecursiveDoubleFunction<R> {
	R apply(final RecursiveDoubleFunction<R> rec, final double n);
}
interface RecursiveIntUnaryOperator {
	int apply(final RecursiveIntUnaryOperator rec, final int n);
}
interface RecursiveLongUnaryOperator {
	long apply(final RecursiveLongUnaryOperator rec, final long n);
}
interface RecursiveDoubleUnaryOperator {
	double apply(final RecursiveDoubleUnaryOperator rec, final double n);
}
interface RecursiveIntBinaryOperator {
	int apply(final RecursiveIntBinaryOperator rec, final int a, final int b);
}
interface RecursiveLongBinaryOperator {
	long apply(final RecursiveLongBinaryOperator rec, final long a, final long b);
}
interface RecursiveDoubleBinaryOperator {
	double apply(final RecursiveDoubleBinaryOperator rec, final double a, final double b);
}
interface RecursiveIntConsumer {
	void accept(final RecursiveIntConsumer rec, final int n);
}
interface RecursiveLongConsumer {
	void accept(final RecursiveLongConsumer rec, final long n);
}
interface RecursiveDoubleConsumer {
	void accept(final RecursiveDoubleConsumer rec, final double n);
}
interface RecursiveIntPredicate {
	boolean test(final RecursiveIntPredicate rec, final int n);
}
interface RecursiveLongPredicate {
	boolean test(final RecursiveLongPredicate rec, final long n);
}
interface RecursiveDoublePredicate {
	boolean test(final RecursiveDoublePredicate rec, final double n);
}

final class IO implements Closeable, AutoCloseable {
	private final MyScanner in;
	private final MyPrinter out, err;
	private final boolean autoFlush;
	IO(final InputStream in, final OutputStream out, final OutputStream err, final boolean autoFlush) {
		this.in = new MyScanner(in);
		this.out = new MyPrinter(out, this.autoFlush = autoFlush);
		this.err = new MyPrinter(err, true);
	}
	final int ni(){ return in.ni(); }
	final long nl(){ return in.nl(); }
	final double nd(){ return in.nd(); }
	final char nc(){ return in.nc(); }
	final String ns(){ return in.ns(); }
	final char[] nt(){ return in.nt(); }
	final BigInteger nb(){ return in.nb(); }
	final IntPair pi(){ return in.pi(); }
	final FloatPair pf(){ return in.pf(); }
	final int[] ni(final int n) {
		final int[] a = new int[n];
		Arrays.setAll(a, i -> ni());
		return a;
	}
	final int[] ni(final int n, final IntUnaryOperator f){ return Arrays.stream(ni(n)).map(f).toArray(); }
	final long[] nl(final int n) {
		final long[] a = new long[n];
		Arrays.setAll(a, i -> nl());
		return a;
	}
	final long[] nl(final int n, final LongUnaryOperator f){ return Arrays.stream(nl(n)).map(f).toArray(); }
	final double[] nd(final int n) {
		final double[] a = new double[n];
		Arrays.setAll(a, i -> nd());
		return a;
	}
	final char[] nc(final int n) {
		final char[] a = new char[n];
		IntStream.range(0, n).forEach(i -> a[i] = nc());
		return a;
	}
	final String[] ns(final int n) {
		final String[] a = new String[n];
		Arrays.setAll(a, i -> ns());
		return a;
	}
	final char[][] nt(final int n) {
		final char[][] a = new char[n][];
		Arrays.setAll(a, i -> nt());
		return a;
	}
	final BigInteger[] nb(final int n) {
		final BigInteger[] a = new BigInteger[n];
		Arrays.setAll(a, i -> nb());
		return a;
	}
	final IntPair[] pi(final int n) {
		final IntPair[] a = new IntPair[n];
		Arrays.setAll(a, i -> pi());
		return a;
	}
	final IntPair[] pi(final int n, final UnaryOperator<IntPair> f){ return Arrays.stream(pi(n)).map(f).toArray(IntPair[]::new); }
	final FloatPair[] pf(final int n) {
		final FloatPair[] a = new FloatPair[n];
		Arrays.setAll(a, i -> pf());
		return a;
	}
	final int[][] ni(final int h, final int w) {
		final int[][] a = new int[h][w];
		Arrays.setAll(a, i -> ni(w));
		return a;
	}
	final long[][] nl(final int h, final int w) {
		final long[][] a = new long[h][w];
		Arrays.setAll(a, i -> nl(w));
		return a;
	}
	final double[][] nd(final int h, final int w) {
		final double[][] a = new double[h][w];
		Arrays.setAll(a, i -> nd(w));
		return a;
	}
	final char[][] nc(final int h, final int w) {
		final char[][] a = new char[h][w];
		Arrays.setAll(a, i -> nc(w));
		return a;
	}
	final String[][] ns(final int h, final int w) {
		final String[][] a = new String[h][w];
		Arrays.setAll(a, i -> ns(w));
		return a;
	}
	final BigInteger[][] nb(final int h, final int w) {
		final BigInteger[][] a = new BigInteger[h][w];
		Arrays.setAll(a, i -> nb(w));
		return a;
	}
	final String line(){ return in.line(); }
	final void print(final Object arg){ out.print(arg); }
	final void printf(final String fmt, final Object... args){ out.printf(fmt, args); }
	final void out(){ out.out(); }
	final void out(final Object head, final Object... tail){ out.out(head, tail); }
	final <F extends Comparable<? super F>, S extends Comparable<? super S>> void out(final Pair<F, S> p){ out.out(p); }
	final <E> void out(final Collection<E> a){ out.out(a); }
	final void out(final int[] head, final int[]...tail){ out.out(head, tail); }
	final void out(final long[] head, final long[]...tail){ out.out(head, tail); }
	final void out(final double[] head, final double[]...tail){ out.out(head, tail); }
	final void out(final boolean[] head, final boolean[]...tail){ out.out(head, tail); }
	final void out(final char[] head, final char[]...tail){ out.out(head, tail); }
	final void out(final Object[] head, final Object[]...tail){ out.out(head, tail); }
	final <F extends Comparable<? super F>, S extends Comparable<? super S>> void out(final Pair<F, S>[] args){ Arrays.stream(args).forEach(this::out); }
	final void out(final int[][] args){ IntStream.range(0, args.length).forEach(i -> out(args[i])); }
	final void out(final long[][] args){ IntStream.range(0, args.length).forEach(i -> out(args[i])); }
	final void out(final double[][] args){ IntStream.range(0, args.length).forEach(i -> out(args[i])); }
	final void out(final boolean[][] args){ IntStream.range(0, args.length).forEach(i -> out(args[i])); }
	final void out(final char[][] args){ IntStream.range(0, args.length).forEach(i -> out(args[i])); }
	final void out(final Object[][] args){ IntStream.range(0, args.length).forEach(i -> out(args[i])); }
	final void outl(final Object head, final Object... tail) {
		out(head);
		Arrays.stream(tail).forEach(this::out);
	}
	final void fin(final Object head, final Object... tail) {
		out(head, tail);
		if(!autoFlush) {
			out.flush();
		}
		System.exit(0);
	}
	final <F extends Comparable<? super F>, S extends Comparable<? super S>> void fin(final Pair<F, S> arg) {
		out(arg);
		if(!autoFlush) {
			out.flush();
		}
		System.exit(0);
	}
	final <E> void fin(final Collection<E> args) {
		out(args);
		if(!autoFlush) {
			out.flush();
		}
		System.exit(0);
	}
	final void fin(final int[] args) {
		out(args);
		if(!autoFlush) {
			out.flush();
		}
		System.exit(0);
	}
	final void fin(final long[] args) {
		out(args);
		if(!autoFlush) {
			out.flush();
		}
		System.exit(0);
	}
	final void fin(final double[] args) {
		out(args);
		if(!autoFlush) {
			out.flush();
		}
		System.exit(0);
	}
	final void fin(final boolean[] args) {
		out(args);
		if(!autoFlush) {
			out.flush();
		}
		System.exit(0);
	}
	final void fin(final char[] args) {
		out(args);
		if(!autoFlush) {
			out.flush();
		}
		System.exit(0);
	}
	final void fin(final Object[] args) {
		out(args);
		if(!autoFlush) {
			out.flush();
		}
		System.exit(0);
	}
	final <F extends Comparable<? super F>, S extends Comparable<? super S>> void fin(final Pair<F, S>[] args) {
		out(args);
		if(!autoFlush) {
			out.flush();
		}
		System.exit(0);
	}
	final void fin(final int[][] args) {
		out(args);
		if(!autoFlush) {
			out.flush();
		}
		System.exit(0);
	}
	final void fin(final long[][] args) {
		out(args);
		if(!autoFlush) {
			out.flush();
		}
		System.exit(0);
	}
	final void fin(final double[][] args) {
		out(args);
		if(!autoFlush) {
			out.flush();
		}
		System.exit(0);
	}
	final void fin(final boolean[][] args) {
		out(args);
		if(!autoFlush) {
			out.flush();
		}
		System.exit(0);
	}
	final void fin(final char[][] args) {
		out(args);
		if(!autoFlush) {
			out.flush();
		}
		System.exit(0);
	}
	final void fin(final Object[][] args) {
		out(args);
		if(!autoFlush) {
			out.flush();
		}
		System.exit(0);
	}
	final void ende(final Object head, final Object... tail) {
		outl(head, tail);
		if(!autoFlush) {
			out.flush();
		}
		System.exit(0);
	}
	final void dump(final Object head, final Object... tail){ err.out(head, tail); }
	final void dump(final int[] head, final int[]...tail){ err.out(head, tail); }
	final void dump(final long[] head, final long[]...tail){ err.out(head, tail); }
	final void dump(final double[] head, final double[]...tail){ err.out(head, tail); }
	final void dump(final boolean[] head, final boolean[]...tail){ err.out(head, tail); }
	final void dump(final char[] head, final char[]...tail){ err.out(head, tail); }
	final void dump(final Object[] head, final Object[]...tail){ err.out(head, tail); }
	final <F extends Comparable<? super F>, S extends Comparable<? super S>> void dump(final Pair<F, S>[] args){ Arrays.stream(args).forEach(this::dump); }
	final void dump(final int[][] args){ IntStream.range(0, args.length).forEach(i -> dump(args[i])); }
	final void dump(final long[][] args){ IntStream.range(0, args.length).forEach(i -> dump(args[i])); }
	final void dump(final double[][] args){ IntStream.range(0, args.length).forEach(i -> dump(args[i])); }
	final void dump(final boolean[][] args){ IntStream.range(0, args.length).forEach(i -> dump(args[i])); }
	final void dump(final char[][] args){ IntStream.range(0, args.length).forEach(i -> dump(args[i])); }
	final void dump(final Object[][] args){ IntStream.range(0, args.length).forEach(i -> dump(args[i])); }
	@Override
	public final void close() {
		out.flush();
		in.close();
		out.close();
		err.close();
	}
	private final class MyScanner implements Closeable, AutoCloseable {
		private int pos, lim;
		private final byte[] buf;
		private final InputStream is;
		private boolean check;
		MyScanner(final InputStream is) {
			this.is = is;
			pos = lim = 0;
			buf = new byte[1 << 17];
			check = false;
		}
		private final boolean isPunct(final byte bt){ return !Utility.scope(33, bt, 126); }
		private final boolean isNum(final byte bt){ return Utility.scope('0', bt, '9'); }
		private final byte read() {
			if(pos == lim && lim != -1) {
				try {
					lim = is.read(buf);
					pos = 0;
				} catch(final IOException e) {
					e.printStackTrace();
				}
			}
			return buf[pos++];
		}
		private final byte next() {
			byte bt;
			if(check) {
				check = false;
				bt = buf[pos - 1];
				if(!isPunct(bt)) {
					return bt;
				}
			}
			while(isPunct(bt = read())){}
			return bt;
		}
		final int ni(){ return toIntExact(nl()); }
		final long nl() {
			byte c = next();
			final boolean neg = c == '-';
			if(neg) {
				c = next();
			}
			assert isNum(c);
			long res = c - '0';
			while(isNum(c = read())) {
				res = 10 * res + c - '0';
			}
			check = !isNum(c);
			return neg ? -res : res;
		}
		final double nd() {
			byte c = next();
			final boolean neg = c == '-';
			if(neg) {
				c = next();
			}
			assert isNum(c);
			double res = c - '0';
			while(isNum(c = read())) {
				res = 10 * res + c - '0';
			}
			if(c != '.') {
				check = true;
				return res;
			}
			int i;
			for(i = 0; isNum(c = read()); ++i) {
				res = res * 10 + c - '0';
			}
			res /= pow(10, i);
			check = true;
			return neg ? -res : res;
		}
		final char nc(){ return (char) next(); }
		final String ns() {
			final StringBuilder sb = new StringBuilder();
			byte c = next();
			while(!isPunct(c)) {
				sb.append((char) c);
				c = read();
			}
			return sb.toString();
		}
		final char[] nt(){ return ns().toCharArray(); }
		final BigInteger nb(){ return new BigInteger(ns()); }
		final IntPair pi(){ return IntPair.of(nl(), nl()); }
		final FloatPair pf(){ return FloatPair.of(nd(), nd()); }
		final String line() {
			final StringBuilder sb = new StringBuilder();
			byte c;
			while((c = read()) != '\n') {
				sb.append((char) c);
			}
			return sb.toString();
		}
		@Override
		public final void close() {
			try {
				is.close();
			} catch(final IOException e) {
				e.printStackTrace();
			}
		}
	}
	private final class MyPrinter implements Closeable, Flushable, AutoCloseable {
		private OutputStream os;
		private final boolean autoFlush;
		private final byte[] buf;
		private int pos;
		private final boolean debug;
		MyPrinter(final OutputStream os, final boolean autoFlush){
			this.os = os;
			this.autoFlush = autoFlush;
			buf = new byte[1 << 17];
			pos = 0;
			debug = os == System.err;
		}
		private final void write(final byte bt) {
			buf[pos++] = bt;
			if(pos == buf.length) {
				flush();
			}
		}
		private final void newLine() {
			write((byte) '\n');
			if(autoFlush) {
				flush();
			}
		}
		final void print(final Object arg) {
			if(arg instanceof final String s) {
				for(final char c: s.toCharArray()) {
					write((byte) c);
				}
			} else {
				print(String.valueOf(arg));
			}
			if(autoFlush) {
				flush();
			}
		}
		final void printf(final String fmt, final Object... args) {
			print(new Formatter().format(fmt, args));
			if(autoFlush) {
				flush();
			}
		}
		final void out(){ newLine(); }
		final void out(final Object head, final Object... tail) {
			print(head);
			for(final var el: tail) {
				print(" " + el);
			}
			newLine();
		}
		final <F extends Comparable<? super F>, S extends Comparable<? super S>> void out(final Pair<F, S> arg) {
			if(debug) {
				print(arg.toString());
			} else {
				print(arg.first + " " + arg.second);
			}
			newLine();
		}
		final <E> void out(final Collection<E> args) {
			if(debug) {
				print(args.toString());
			} else {
				int i = 0;
				for(final var el: args) {
					print(el);
					if(++i != args.size()) {
						print(" ");
					}
				}
			}
			newLine();
		}
		private final void out(final int[] args) {
			if(debug) {
				print(Arrays.toString(args));
			} else if(args.length > 0) {
				print(args[0]);
				for(int i = 0; ++i < args.length;) {
					print(" " + args[i]);
				}
			}
			newLine();
		}
		final void out(final int[] head, final int[]... tail) {
			out(head);
			for(final int[] a: tail) {
				out(a);
			}
		}
		private final void out(final long[] args) {
			if(debug) {
				print(Arrays.toString(args));
			} else if(args.length > 0) {
				print(args[0]);
				for(int i = 0; ++i < args.length;) {
					print(" " + args[i]);
				}
			}
			newLine();
		}
		final void out(final long[] head, final long[]... tail) {
			out(head);
			for(final long[] a: tail) {
				out(a);
			}
		}
		private final void out(final double[] args) {
			if(debug) {
				print(Arrays.toString(args));
			} else if(args.length > 0) {
				print(args[0]);
				for(int i = 0; ++i < args.length;) {
					print(" " + args[i]);
				}
			}
			newLine();
		}
		final void out(final double[] head, final double[]... tail) {
			out(head);
			for(final double[] a: tail) {
				out(a);
			}
		}
		private final void out(final boolean[] args) {
			if(debug) {
				print(Arrays.toString(args));
			} else if(args.length > 0) {
				print(args[0]);
				for(int i = 0; ++i < args.length;) {
					print(" " + args[i]);
				}
			}
			newLine();
		}
		final void out(final boolean[] head, final boolean[]... tail) {
			out(head);
			for(final boolean[] a: tail) {
				out(a);
			}
		}
		private final void out(final char[] args) {
			if(args.length > 0) {
				print(args[0]);
				for(int i = 0; ++i < args.length;) {
					print(" " + args[i]);
				}
			}
			newLine();
		}
		final void out(final char[] head, final char[]... tail) {
			out(head);
			for(final char[] a: tail) {
				out(a);
			}
		}
		private final void out(final Object[] args) {
			if(debug) {
				print(Arrays.toString(args));
			} else if(args.length > 0) {
				print(args[0]);
				for(int i = 0; ++i < args.length;) {
					print(" " + args[i]);
				}
			}
			newLine();
		}
		final void out(final Object[] head, final Object[]... tail) {
			out(head);
			for(final Object[] a: tail) {
				out(a);
			}
		}
		@Override
		public final void flush() {
			try {
				os.write(buf, 0, pos);
				pos = 0;
			} catch(final IOException e) {
				e.printStackTrace();
			}
		}
		@Override
		public final void close() {
			if(os == null) {
				return;
			}
			try {
				os.close();
				os = null;
			} catch(final IOException e) {
				e.printStackTrace();
			}
		}
	}
}

class Pair<F extends Comparable<? super F>, S extends Comparable<? super S>> implements Comparable<Pair<F, S>>, Cloneable {
	public F first;
	public S second;
	protected Pair(final F first, final S second) {
		this.first = first;
		this.second = second;
	}
	static final <F extends Comparable<? super F>, S extends Comparable<? super S>> Pair<F, S> of(final F a, final S b){ return new Pair<>(a, b); }
	Pair<S, F> swap(){ return Pair.of(second, first); }
	@Override
	public final boolean equals(final Object o) {
		if(this == o) {
			return true;
		}
		if(o == null || getClass() != o.getClass()) {
			return false;
		}
		final Pair<?, ?> p = (Pair<?, ?>) o;
		return first.equals(p.first) && second.equals(p.second);
	}
	@Override
	public final int hashCode(){ return Objects.hash(first, second); }
	@Override
	public final String toString(){ return "(" + first + ", " + second + ")"; }
	@SuppressWarnings("unchecked")
	@Override
	public final Pair<F, S> clone() {
		try {
			return (Pair<F, S>) super.clone();
		} catch(final CloneNotSupportedException e){
			e.printStackTrace();
		}
		throw new Error();
	}
	@Override
	public final int compareTo(final Pair<F, S> p) {
		if(first.compareTo(p.first) == 0) {
			return second.compareTo(p.second);
		}
		return first.compareTo(p.first);
	}
}
final class IntPair extends Pair<Long, Long> {
	private IntPair(final long first, final long second){ super(first, second); }
	static final IntPair ZERO = new IntPair(0, 0);
	static final IntPair ONE = new IntPair(1, 1);
	static final IntPair of(final long a, final long b){ return new IntPair(a, b); }
	@Override
	final IntPair swap(){ return new IntPair(second, first); }
	final IntPair add(final IntPair p){ return new IntPair(first + p.first, second + p.second); }
	final IntPair sub(final IntPair p){ return new IntPair(first - p.first, second - p.second); }
	final IntPair mul(final IntPair p){ return new IntPair(first * p.first, second * p.second); }
	final IntPair div(final IntPair p){ return new IntPair(first / p.first, second / p.second); }
	final IntPair mod(final IntPair p){ return new IntPair(first % p.first, second % p.second); }
	final IntPair rotate(){ return new IntPair(-second, first); } 
	final FloatPair rotate(final int ang) {
		final double rad = toRadians(Utility.mod(ang, 360));
		return FloatPair.of(first * cos(rad) - second * sin(rad), first * sin(rad) + second * cos(rad));
	}
	final long dot(final IntPair p){ return first * p.first + second * p.second; }
	final long cross(final IntPair p){ return rotate().dot(p); }
	final long sqr(){ return dot(this); }
	final double grad() {
		try {
			return 1.0 * second / first;
		} catch(final ArithmeticException e) {
			e.printStackTrace();
		}
		throw new Error();
	}
	final double abs(){ return hypot(first, second); }
	final long lcm(){ return Utility.lcm(first, second); }
	final long gcd(){ return Utility.gcd(first, second); }
	final IntPair extgcd() {
		long x = 1, y = 0, t1 = 0, t2 = 0, t3 = 1, a = first, b = second;
		while(b > 0) {
			t1 = a / b;
			a -= t1 * b;
			a ^= b;
			b ^= a;
			a ^= b;
			x -= t1 * t2;
			x ^= t2;
			t2 ^= x;
			x ^= t2;
			y -= t1 * t3;
			y ^= t3;
			t3 ^= y;
			y ^= t3;
		}
		return new IntPair(x, y);
	}
}
final class FloatPair extends Pair<Double, Double> {
	private FloatPair(final double first, final double second){ super(first, second); }
	static final FloatPair of(final double a, final double b){ return new FloatPair(a, b); }
	@Override
	final FloatPair swap(){ return new FloatPair(second, first); }
	final FloatPair add(final FloatPair p){ return new FloatPair(first + p.first, second + p.second); }
	final FloatPair sub(final FloatPair p){ return new FloatPair(first - p.first, second - p.second); }
	final FloatPair mul(final FloatPair p){ return new FloatPair(first * p.first, second * p.second); }
	final FloatPair div(final FloatPair p){ return new FloatPair(first / p.first, second / p.second); }
	final FloatPair rotate(){ return new FloatPair(-second, first); } 
	final FloatPair rotate(final int ang) {
		final double rad = toRadians(Utility.mod(ang, 360));
		return FloatPair.of(first * cos(rad) - second * sin(rad), first * sin(rad) + second * cos(rad));
	}
	final double dot(final FloatPair p){ return first * p.first + second * p.second; }
	final double cross(final FloatPair p){ return rotate().dot(p); }
	final double sqr(){ return dot(this); }
	final double grad() { 
		try {
			return second / first;
		} catch(final ArithmeticException e) {
			e.printStackTrace();
		}
		throw new Error();
	}
	final double abs(){ return hypot(first, second); }
}

final class Why {
	static final boolean isBipartite(final UnionFind uf) {
		assert uf.size() % 2 == 0;
		final int n = uf.size() / 2;
		boolean ok = true;
		for(int i = 0; i < n; ++i) {
			ok &= !uf.same(i, i + n);
		}
		return ok;
	}
	static final long invNum(final int[] a) {
		final int[] b = Utility.sorted(a);
		final Map<Integer, Integer> id = new HashMap<>();
		for(int i = 0; i < a.length; ++i) {
			id.put(b[i], i);
		}
		final FenwickTree bit = new FenwickTree(a.length);
		long res = 0;
		for(int i = 0; i < a.length; ++i) {
			res += i - bit.sum(id.get(a[i]));
			bit.add(id.get(a[i]), 1);
		}
		return res;
	}
	static final long invNum(final long[] a) {
		final long[] b = Utility.sorted(a);
		final Map<Long, Integer> id = new HashMap<>();
		for(int i = 0; i < a.length; ++i) {
			id.put(b[i], i);
		}
		final FenwickTree bit = new FenwickTree(a.length);
		long res = 0;
		for(int i = 0; i < a.length; ++i) {
			res += i - bit.sum(id.get(a[i]));
			bit.add(id.get(a[i]), 1);
		}
		return res;
	}
}

final class Edge {
	public int src, to, id;
	public long cost;
	Edge(final int src, final int to, final int id) {
		this.src = src;
		this.to = to;
		this.id = id;
	}
	Edge(final int src, final int to, final long cost, final int id) {
		this.src = src;
		this.to = to;
		this.cost = cost;
		this.id = id;
	}
	@Override
	public final boolean equals(final Object o) {
		if(this == o) {
			return true;
		}
		if(o == null || getClass() != o.getClass()) {
			return false;
		}
		final Edge e = (Edge) o;
		return src == e.src && to == e.to && cost == e.cost;
	}
	@Override
	public final int hashCode(){ return Objects.hash(src, to, cost, id); }
	@Override
	public final String toString(){ return String.valueOf(to); }
}
class Graph extends ArrayList<ArrayList<Edge>> {
	protected final boolean undirected;
	protected final int n, indexed;
	protected int id;
	protected final ArrayList<Edge> edge;
	Graph(final int n, final boolean undirected){ this(n, 1, undirected); }
	Graph(final int n, final int indexed, final boolean undirected) {
		this.n = n;
		this.indexed = indexed;
		this.undirected = undirected;
		id = 0;
		edge = new ArrayList<>();
		IntStream.range(0, n).forEach(i -> add(new ArrayList<>()));
	}
	static Graph of(final List<ArrayList<Edge>> g, final boolean undirected) {
		int max = 0, min = Integer.MAX_VALUE;
		for(int i = 0; i < g.size(); ++i) {
			for(final Edge e: g.get(i)) {
				max = max(e.src, e.to);
				min = min(e.src, e.to);
			}
		}
		final Graph gp = new Graph(max, min, undirected);
		for(int i = 0; i < g.size(); ++i) {
			for(final Edge e: g.get(i)) {
				gp.addEdge(e.src, e.to);
			}
		}
		return gp;
	}
	final void addEdge(int a, int b) {
		a -= indexed;
		b -= indexed;
		this.get(a).add(new Edge(a, b, id));
		edge.add(new Edge(a, b, id++));
		if(undirected) {
			this.get(b).add(new Edge(b, a, --id));
			edge.add(new Edge(b, a, id++));
		}
	}
	void input(final int m){ IntStream.range(0, m).forEach(i -> addEdge(VvyLw.io.ni(), VvyLw.io.ni())); }
	protected final ArrayList<Edge> getEdge(){ return edge; }
	protected final int[] allDist(final int v) {
		final int[] d = new int[n];
		Arrays.fill(d, -1);
		final Queue<Integer> q = new ArrayDeque<>();
		d[v] = 0;
		q.add(v);
		while(!q.isEmpty()) {
			final int tmp = q.poll();
			for(final Edge el: this.get(tmp)) {
				if(d[el.to] != -1) {
					continue;
				}
				d[el.to] = d[tmp] + 1;
				q.add(el.to);
			}
		}
		return d;
	}
	protected final int dist(final int u, final int v){ return allDist(u)[v]; }
	protected final ArrayList<Integer> topologicalSort() {
		final int[] deg = new int[n];
		for(int i = 0; i < n; ++i) {
			for(final Edge ed: this.get(i)) {
				deg[ed.to]++;
			}
		}
		final Stack<Integer> sk = new Stack<>();
		for(int i = 0; i < n; ++i) {
			if(deg[i] == 0) {
				sk.add(i);
			}
		}
		final ArrayList<Integer> ord = new ArrayList<>();
		while(!sk.isEmpty()) {
			final int tmp = sk.pop();
			ord.add(tmp);
			for(final Edge ed: this.get(tmp)) {
				if(--deg[ed.to] == 0) {
					sk.add(ed.to);
				}
			}
		}
		return n == ord.size() ? ord : null;
	}
	protected final int[] cycleDetector() {
		final int[] used = new int[n];
		final Edge[] pre = new Edge[n];
		final ArrayList<Edge> cycle = new ArrayList<>();
		final RecursiveIntPredicate dfs = (rec, i) -> {
			used[i] = 1;
			for(final Edge e: get(i)) {
				if(used[e.to] == 0) {
					pre[e.to] = e;
					if(rec.test(rec, e.to)) {
						return true;
					}
				} else if(used[e.to] == 1) {
					int now = i;
					while(now != e.to) {
						cycle.add(pre[now]);
						now = pre[now].src;
					}
					cycle.add(e);
					return true;
				}
			}
			used[i] = 2;
			return false;
		};
		for(int i = 0; i < n; ++i) {
			if(used[i] == 0 && dfs.test(dfs, i)) {
				Collections.reverse(cycle);
				return cycle.stream().mapToInt(e -> e.to).toArray();
			}
		}
		return null;
	}
	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		for(int i = 0; i < n; ++i) {
			final int m = get(i).size();
			sb.append(i + ": [");
			for(int j = 0; j < m; ++j) {
				sb.append(get(i).get(j).to);
				if(j + 1 < m) {
					sb.append(", ");
				}
			}
			sb.append(']');
			if(i + 1 < n) {
				sb.append('\n');
			}
		}
		return sb.toString();
	}
}

final class ShortestPath {
	private final long[] cost;
	private final int[] src;
	ShortestPath(final long[] cost, final int[] src) {
		this.cost = cost;
		this.src = src;
	}
	final boolean isThru(final int i){ return src[i] != -1; }
	final int[] path(int i) {
		final List<Integer> res = new ArrayList<>();
		for(; i != -1; i = src[i]) {
			res.add(i);
		}
		Collections.reverse(res);
		return res.stream().mapToInt(k -> k).toArray();
	}
	final long[] get(){ return cost; }
}
final class MST {
	public final ArrayList<Edge> tree;
	public final long cost;
	MST(final ArrayList<Edge> tree, final long cost) {
		this.tree = tree;
		this.cost = cost;
	}
}
final class WeightedGraph extends Graph {
	WeightedGraph(final int n, final boolean undirected){ super(n, undirected); }
	WeightedGraph(final int n, final int indexed, final boolean undirected){ super(n, indexed, undirected); }
	static final WeightedGraph of(final List<ArrayList<Edge>> g, final boolean undirected) {
		int max = 0, min = Integer.MAX_VALUE;
		for(int i = 0; i < g.size(); ++i) {
			for(final Edge e: g.get(i)) {
				max = max(e.src, e.to);
				min = min(e.src, e.to);
			}
		}
		final WeightedGraph gp = new WeightedGraph(max, min, undirected);
		for(int i = 0; i < g.size(); ++i) {
			for(final Edge e: g.get(i)) {
				gp.addEdge(e.src, e.to, e.cost);
			}
		}
		return gp;
	}
	final void addEdge(int a, int b, final long cost) {
		a -= indexed;
		b -= indexed;
		this.get(a).add(new Edge(a, b, cost, id));
		edge.add(new Edge(a, b, cost, id++));
		if(undirected) {
			this.get(b).add(new Edge(b, a, cost, --id));
			edge.add(new Edge(b, a, cost, id++));
		}
	}
	final void input(final int m){ IntStream.range(0, m).forEach(i -> addEdge(VvyLw.io.ni(), VvyLw.io.ni(), VvyLw.io.nl())); }
	final ShortestPath dijkstra(final int v) {
		final long[] cost = new long[n];
		final int[] src = new int[n];
		Arrays.fill(cost, Long.MAX_VALUE);
		Arrays.fill(src, -1);
		final Queue<IntPair> dj = new PriorityQueue<>();
		cost[v] = 0;
		dj.add(IntPair.of(cost[v], v));
		while(!dj.isEmpty()) {
			final IntPair tmp = dj.poll();
			if(cost[tmp.second.intValue()] < tmp.first.longValue()) {
				continue;
			}
			for(final Edge ed: this.get(tmp.second.intValue())) {
				final long next = tmp.first.longValue() + ed.cost;
				if(cost[ed.to] <= next) {
					continue;
				}
				cost[ed.to] = next;
				src[ed.to] = tmp.second.intValue();
				dj.add(IntPair.of(cost[ed.to], ed.to));
			}
		}
		return new ShortestPath(cost, src);
	}
	final long[] spfa(final int v) {
		final long[] cost = new long[n];
		Arrays.fill(cost, Long.MAX_VALUE);
		final boolean[] pend = new boolean[n];
		final int[] cnt = new int[n];
		final Queue<Integer> q = new ArrayDeque<>();
		q.add(v);
		pend[v] = true;
		cnt[v]++;
		cost[v] = 0;
		while(!q.isEmpty()) {
			final int p = q.poll();
			pend[p] = false;
			for(final Edge e: this.get(p)) {
				final long next = cost[p] + e.cost;
				if(next >= cost[e.to]) {
					continue;
				}
				cost[e.to] = next;
				if(!pend[e.to]) {
					if(++cnt[e.to] >= n) {
						return null;
					}
					pend[e.to] = true;
					q.add(e.to);
				}
			}
		}
		return cost;
	}
	final long[][] floydWarshall() {
		final long[][] cost = new long[n][n];
		IntStream.range(0, n).forEach(i -> Arrays.fill(cost[i], VvyLw.LINF));
		IntStream.range(0, n).forEach(i -> cost[i][i] = 0);
		for(int i = 0; i < n; ++i) {
			for(final Edge j: this.get(i)) {
				cost[i][j.to] = j.cost;
			}
		}
		for(int k = 0; k < n; ++k) {
			for(int i = 0; i < n; ++i) {
				for(int j = 0; j < n; ++j) {
					if(cost[i][k] == VvyLw.LINF || cost[k][j] == VvyLw.LINF) {
						continue;
					}
					if(cost[i][j] > cost[i][k] + cost[k][j]) {
						cost[i][j] = cost[i][k] + cost[k][j];
					}
				}
			}
		}
		return cost;
	}
	final MST kruskal() {
		final UnionFind uf = new UnionFind(n);
		final ArrayList<Edge> e = new ArrayList<>();
		long res = 0;
		for(final Edge ed: edge.stream().sorted(Comparator.comparing(ed -> ed.cost)).collect(Collectors.toList())) {
			if(uf.unite(ed.src, ed.to)) {
				e.add(ed);
				res += ed.cost;
			}
		}
		return new MST(e, res);
	}
	final MST directed(final int v) {
		@SuppressWarnings("unchecked")
		final ArrayList<Edge> ed = (ArrayList<Edge>) edge.clone();
		for(int i = 0; i < n; ++i) {
			if(i != v) {
				ed.add(new Edge(i, v, 0));
			}
		}
		int x = 0;
		final int[] par = new int[2 * n], vis = new int[2 * n], link = new int[2 * n];
		Arrays.fill(par, -1);
		Arrays.fill(vis, -1);
		Arrays.fill(link, -1);
		final SkewHeap heap = new SkewHeap(true);
		final SkewHeap.Node[] ins = new SkewHeap.Node[2 * n];
		Arrays.fill(ins, null);
		for(int i = 0; i < ed.size(); i++) {
			final Edge e = ed.get(i);
			ins[e.to] = heap.push(ins[e.to], e.cost, i);
		}
		final ArrayList<Integer> st = new ArrayList<>();
		final IntUnaryOperator go = z -> {
			z = ed.get(ins[z].idx).src;
			while(link[z] != -1) {
				st.add(z);
				z = link[z];
			}
			for(final int p: st) {
				link[p] = z;
			}
			st.clear();
			return z;
		};
		for(int i = n; ins[x] != null; ++i) {
			while(vis[x] == -1) {
				vis[x] = 0;
				x = go.applyAsInt(x);
			}
			while(x != i) {
				final long w = ins[x].key;
				SkewHeap.Node z = heap.pop(ins[x]);
				z = heap.add(z, -w);
				ins[i] = heap.meld(ins[i], z);
				par[x] = i;
				link[x] = i;
				x = go.applyAsInt(x);
			}
			while(ins[x] != null && go.applyAsInt(x) == x) {
				ins[x] = heap.pop(ins[x]);
			}
		}
		for(int i = v; i != -1; i = par[i]) {
			vis[i] = 1;
		}
		long cost = 0;
		final ArrayList<Edge> e = new ArrayList<>();
		for(int i = x; i >= 0; i--) {
			if(vis[i] == 1) {
				continue;
			}
			cost += ed.get(ins[i].idx).cost;
			e.add(ed.get(ins[i].idx));
			for(int j = ed.get(ins[i].idx).to; j != -1 && vis[j] == 0; j = par[j]) {
				vis[j] = 1;
			}
		}
		return new MST(e, cost);
	}
	@Override
	public final String toString() {
		final StringBuilder sb = new StringBuilder();
		for(int i = 0; i < n; ++i) {
			final int m = get(i).size();
			sb.append(i + ": [");
			for(int j = 0; j < m; ++j) {
				sb.append("(to: " + get(i).get(j).to + ", cost: " + get(i).get(j).cost + ')');
				if(j + 1 < m) {
					sb.append(", ");
				}
			}
			sb.append(']');
			if(i + 1 < n) {
				sb.append('\n');
			}
		}
		return sb.toString();
	}
}
final class SkewHeap {
	static final class Node {
		long key, lazy;
		Node l, r;
		final int idx;
		Node(final long key, final int idx) {
			this.key = key;
			this.idx = idx;
			lazy = 0;
			l = null;
			r = null;
		}
	}
	private final boolean isMin;
	SkewHeap(final boolean isMin){ this.isMin = isMin; }
	private final Node alloc(final long key, final int idx){ return new Node(key, idx); }
	private final Node propagate(final Node t) {
		if(t != null && t.lazy != 0) {
			if(t.l != null) {
				t.l.lazy += t.lazy;
			}
			if(t.r != null) {
				t.r.lazy += t.lazy;
			}
			t.key += t.lazy;
			t.lazy = 0;
		}
		return t;
	}
	final Node meld(Node x, Node y) {
		propagate(x);
		propagate(y);
		if(x == null || y == null) {
			return x != null ? x : y;
		}
		if((x.key < y.key) ^ isMin) {
			final Node tmp = x;
			x = y;
			y = tmp;
		}
		x.r = meld(y, x.r);
		final Node tmp = x.l;
		x.l = x.r;
		x.r = tmp;
		return x;
	}
	final Node push(final Node t, final long key, final int idx){ return meld(t, alloc(key, idx)); }
	final Node pop(final Node t) {
		if(t == null) {
			throw new NullPointerException();
		}
		return meld(t.l, t.r);
	}
	final Node add(final Node t, final long lazy) {
		if(t != null) {
			t.lazy += lazy;
			propagate(t);
		}
		return t;
	}
}

final class SCC {
	private final int n, indexed;
	private int m;
	private final ArrayList<Edge> edge;
	private final int[] start, ids;
	private int[][] groups;
	private boolean notBuilt;
	SCC(final int n){ this(n, 1); }
	SCC(final int n, final int indexed) {
		this.n = n;
		this.indexed = indexed;
		edge = new ArrayList<>();
		start = new int[n + 1];
		ids = new int[n];
		m = 0;
		notBuilt = true;
	}
	final void addEdge(int from, int to) {
		from -= indexed;
		to -= indexed;
		rangeCheck(from);
		rangeCheck(to);
		edge.add(new Edge(from, to, m++));
		start[from + 1]++;
	}
	final void input(final int m){ IntStream.range(0, m).forEach(i -> addEdge(VvyLw.io.ni(), VvyLw.io.ni())); }
	final int id(final int i) {
		if(notBuilt) {
			throw new UnsupportedOperationException("Graph hasn't been built.");
		}
		rangeCheck(i);
		return ids[i];
	}
	final void build() {
		for(int i = 1; i <= n; i++) {
			start[i] += start[i - 1];
		}
		final Edge[] ed = new Edge[m];
		final int[] count = new int[n + 1];
		System.arraycopy(start, 0, count, 0, n + 1);
		for(final Edge e: edge) {
			ed[count[e.src]++] = e;
		}
		int nowOrd = 0, groupNum = 0, k = 0, ptr = 0;
		final int[] par = new int[n], vis = new int[n], low = new int[n], ord = new int[n];
		Arrays.fill(ord, -1);
		final long[] stack = new long[n];
		for(int i = 0; i < n; i++) {
			if(ord[i] >= 0) {
				continue;
			}
			par[i] = -1;
			stack[ptr++] = 0L << 32 | i;
			while(ptr > 0) {
				long p = stack[--ptr];
				int u = (int) (p & 0xffff_ffffl);
				int j = (int) (p >>> 32);
				if(j == 0) {
					low[u] = ord[u] = nowOrd++;
					vis[k++] = u;
				}
				if(start[u] + j < count[u]) {
					int to = ed[start[u] + j].to;
					stack[ptr++] += 1l << 32;
					if(ord[to] == -1) {
						stack[ptr++] = 0l << 32 | to;
						par[to] = u;
					} else {
						low[u] = min(low[u], ord[to]);
					}
				} else {
					while(j --> 0) {
						final int to = ed[start[u] + j].to;
						if(par[to] == u) {
							low[u] = min(low[u], low[to]);
						}
					}
					if(low[u] == ord[u]) {
						while(true) {
							final int v = vis[--k];
							ord[v] = n;
							ids[v] = groupNum;
							if(v == u) {
								break;
							}
						}
						groupNum++;
					}
				}
			}
		}
		for(int i = 0; i < n; i++) {
			ids[i] = groupNum - 1 - ids[i];
		}
		final int[] counts = new int[groupNum];
		for(final int x: ids) {
			counts[x]++;
		}
		groups = new int[groupNum][];
		for(int i = 0; i < groupNum; i++) {
			groups[i] = new int[counts[i]];
		}
		for(int i = 0; i < n; i++) {
			int cmp = ids[i];
			groups[cmp][--counts[cmp]] = i;
		}
		notBuilt = false;
	}
	final int[][] groups() {
		if(notBuilt) {
			throw new UnsupportedOperationException("Graph hasn't been built.");
		}
		return groups;
	}
	private final void rangeCheck(final int i) {
		if(!Utility.scope(0, i, n - 1)) {
			throw new IndexOutOfBoundsException(String.format("Index %d out of bounds for length %d", i, n));
		}
	}
}

final class LowestCommonAncestor {
	private final int log;
	private final int[] dep, sum;
	private final Graph g;
	private final int[][] table;
	LowestCommonAncestor(final Graph g) {
		this.g = g;
		final int n = g.size();
		dep = new int[n];
		sum = new int[n];
		log = Integer.toBinaryString(n).length();
		table = new int[log][n];
		IntStream.range(0, log).forEach(i -> Arrays.fill(table[i], -1));
		build();
	}
	private final void dfs(final int idx, final int par, final int d) {
		table[0][idx] = par;
		dep[idx] = d;
		for(final Edge el: g.get(idx)) {
			if(el.to != par) {
				sum[el.to] = (int) (sum[idx] + el.cost); 
				dfs(el.to, idx, d + 1);
			}
		}
	}
	private final void build() {
		dfs(0, -1, 0);
		for(int k = 0; k < log - 1; ++k) {
			for(int i = 0; i < table[k].length; ++i) {
				if(table[k][i] == -1) {
					table[k + 1][i] = -1;
				} else {
					table[k + 1][i] = table[k][table[k][i]];
				}
			}
		}
	}
	final int query(int u, int v) {
		if(dep[u] > dep[v]) {
			u ^= v;
			v ^= u;
			u ^= v;
		}
		v = climb(v, dep[v] - dep[u]);
		if(u == v) {
			return u;
		}
		for(int i = log; --i >= 0;) {
			if(table[i][u] != table[i][v]) {
				u = table[i][u];
				v = table[i][v];
			}
		}
		return table[0][u];
	}
	final int climb(int u, final int k) {
		if(dep[u] < k) {
			return -1;
		}
		for(int i = log; --i >= 0;) {
			if(((k >> i) % 2) == 1) {
				u = table[i][u];
			}
		}
		return u;
	}
	final int dist(final int u, final int v){ return sum[u] + sum[v] - 2 * sum[query(u, v)]; }
}

interface DSU {
	int root(final int i);
	int size(final int i);
	int size();
	default boolean same(final int i, final int j){ return root(i) == root(j); }
	boolean unite(int i, int j);
	ArrayList<ArrayList<Integer>> groups();
}

class UnionFind implements DSU {
	protected final int[] par;
	UnionFind(final int n) {
		par = new int[n];
		Arrays.fill(par, -1);
	}
	@Override
	public final int root(final int i){ return par[i] >= 0 ? par[i] = root(par[i]) : i; }
	@Override
	public final int size(final int i){ return -par[root(i)]; }
	@Override
	public final int size(){ return par.length; }
	@Override
	public boolean unite(int i, int j) {
		i = root(i);
		j = root(j);
		if(i == j) {
			return false;
		}
		if(i > j) {
			i ^= j;
			j ^= i;
			i ^= j;
		}
		par[i] += par[j];
		par[j] = i;
		return true;
	}
	@Override
	public final ArrayList<ArrayList<Integer>> groups() {
		final int n = par.length;
		final ArrayList<ArrayList<Integer>> res = new ArrayList<>(n);
		IntStream.range(0, n).forEach(i -> res.add(new ArrayList<>()));
		IntStream.range(0, n).forEach(i -> res.get(root(i)).add(i));
		res.removeIf(ArrayList::isEmpty);
		return res;
	}
}

abstract class MergeUnionFind<T> extends UnionFind {
	MergeUnionFind(final int n){ super(n); }
	abstract void merge(final int i, final int j);
	abstract T get(final int i);
	@Override
	public final boolean unite(int i, int j) {
		i = root(i);
		j = root(j);
		if(i == j) {
			return false;
		}
		if(i > j) {
			i ^= j;
			j ^= i;
			i ^= j;
		}
		par[i] += par[j];
		par[j] = i;
		merge(i, j);
		return true;
	}
}

final class WeightedUnionFind implements DSU {
	private final int[] par;
	private final long[] weight;
	WeightedUnionFind(final int n) {
		par = new int[n];
		weight = new long[n];
		Arrays.fill(par, -1);
	}
	@Override
	public final int root(final int i) {
		if(par[i] < 0) {
			return i;
		}
		final int r = root(par[i]);
		weight[i] += weight[par[i]];
		return par[i] = r;
	}
	final long get(final int i) {
		root(i);
		return weight[i];
	}
	final long diff(final int x, final int y){ return get(y) - get(x); }
	final int unite(int x, int y, long w) {
		w += diff(y, x);
		x = root(x);
		y = root(y);
		if(x == y) {
			return w == 0 ? 0 : -1;
		}
		if(par[x] > par[y]) {
			x ^= y;
			y ^= x;
			x ^= y;
			w = -w;
		}
		par[x] += par[y];
		par[y] = x;
		weight[y] = w;
		return 1;
	}
	@Override
	public final int size(final int i){ return -par[root(i)]; }
	@Override
	public final int size(){ return par.length; }
	@Override
	public final ArrayList<ArrayList<Integer>> groups() {
		final int n = par.length;
		final ArrayList<ArrayList<Integer>> res = new ArrayList<>();
		IntStream.range(0, n).forEach(i -> res.add(new ArrayList<>()));
		IntStream.range(0, n).forEach(i -> res.get(root(i)).add(i));
		res.removeIf(ArrayList::isEmpty);
		return res;
	}
	// deprecated
	@Override
	public final boolean unite(final int i, final int j){ return unite(i, j, 0) > 0; }
}

final class UndoUnionFind implements DSU {
	private final int[] par;
	private final Stack<Pair<Integer, Integer>> his;
	UndoUnionFind(final int n) {
	    par = new int[n];
	    Arrays.fill(par, -1);
	    his = new Stack<>();
	}
	@Override
	public final boolean unite(int x, int y) {
		x = root(x);
		y = root(y);
		his.add(Pair.of(x, par[x]));
		his.add(Pair.of(y, par[y]));
		if(x == y) {
			return false;
		}
		if(par[x] > par[y]) {
			x ^= y;
			y ^= x;
			x ^= y;
		}
		par[x] += par[y];
		par[y] = x;
		return true;
	}
	@Override
	public final int root(final int i) {
		if(par[i] < 0) {
			return i;
		}
		return root(par[i]);
	}
	@Override
	public final int size(final int i){ return -par[root(i)]; }
	@Override
	public final int size(){ return par.length; }
	@Override
	public final ArrayList<ArrayList<Integer>> groups() {
		final int n = par.length;
		final ArrayList<ArrayList<Integer>> res = new ArrayList<>();
		IntStream.range(0, n).forEach(i -> res.add(new ArrayList<>()));
		IntStream.range(0, n).forEach(i -> res.get(root(i)).add(i));
		res.removeIf(ArrayList::isEmpty);
		return res;
	}
	final void undo() {
		final Pair<Integer, Integer> pop1 = his.pop(), pop2 = his.pop();
		par[pop1.first] = pop1.second;
		par[pop2.first] = pop2.second;
	}
	final void snapshot() {
		while(!his.empty()) {
			his.pop();
		}
	}
	final void rollback() {
		while(!his.empty()) {
			undo();
		}
	}
}

final class PrimeTable {
	private final int[] p;
	private final boolean[] sieve;
	PrimeTable(final int n) {
		sieve = new boolean[n + 1];
		Arrays.fill(sieve, true);
		sieve[0] = sieve[1] = false;
		for(int i = 2; i <= n; ++i) {
			if(!sieve[i]) {
				continue;
			}
			for(long j = (long) i * i; j <= n; j += i) {
				sieve[(int) j] = false;
			}
		}
		final int size = (int) IntStream.rangeClosed(0, n).filter(i -> sieve[i]).count();
		int j = 0;
		p = new int[size];
		for(int i = 2; i <= n; ++i) {
			if(sieve[i]) {
				p[j++] = i; 
			}
		}
	}
	final boolean[] table(){ return sieve; }
	final int[] get(){ return p; }
}

final class PrimeFactor {
	private final int[] spf;
	PrimeFactor(final int n) {
		spf = Utility.iota(n + 1).toArray();
		for(int i = 2; i * i <= n; ++i) {
			if(spf[i] != i) {
				continue;
			}
			for(int j = i * i; j <= n; j += i) {
				if(spf[j] == j) {
					spf[j] = i;
				}
			}
		}
	}
	final TreeMap<Integer, Integer> get(int n) {
		final TreeMap<Integer, Integer> m = new TreeMap<>();
		while(n != 1) {
			m.merge(spf[n], 1, (a, b) -> (a + b));
			n /= spf[n];
		}
		return m;
	}
}

final class PrimeCounter {
	private final int sq;
	private final boolean[] p;
	private final int[] psum;
	private final ArrayList<Integer> ps;
	PrimeCounter(final long n) {
		sq = (int) kthRooti(n, 2);
		psum = new int[sq + 1];
		p = new PrimeTable(sq).table();
		for(int i = 1; i <= sq; ++i) {
			psum[i] = psum[i - 1] + (p[i] ? 1 : 0);
		}
		ps = new ArrayList<>();
		for(int i = 1; i <= sq; ++i) {
			if(p[i]) {
				ps.add(i);
			}
		}
	}
	private final long kthRooti(final long n, final int k){ return Utility.kthRoot(n, k); }
	private final long p2(final long x, final long y) {
		if(x < 4) {
			return 0;
		}
		final long a = pi(y);
		final long b = pi(kthRooti(x, 2));
		if(a >= b) {
			return 0;
		}
		long sum = (long) (a - 2) * (a + 1) / 2 - (b - 2) * (b + 1) / 2;
		for(long i = a; i < b; ++i) {
			sum += pi(x / ps.get((int) i));
		}
		return sum;
	}
	private final long phi(final long m, final long a) {
		if(m < 1) {
			return 0;
		}
		if(a > m) {
			return 1;
		}
		if(a < 1) {
			return m;
		}
		if(m <= (long) ps.get((int) (a - 1)) * ps.get((int) (a - 1))) {
			return pi(m) - a + 1;
		}
		if(m <= (long) ps.get((int) (a - 1)) * ps.get((int) (a - 1)) * ps.get((int) (a - 1)) && m <= sq) {
			final long sx = pi(kthRooti(m, 2));
			long ans = pi(m) - (long) (sx + a - 2) * (sx - a + 1) / 2;
			for(long i = a; i < sx; ++i) {
				ans += pi(m / ps.get((int) i));
			}
			return ans;
		}
		return phi(m, a - 1) - phi(m / ps.get((int) (a - 1)), a - 1);
	}
	final long pi(final long n) {
		if(n <= sq) {
			return psum[(int) n];
		}
		final long m = kthRooti(n, 3);
		final long a = pi(m);
		return phi(n, a) + a - 1 - p2(n, m);
	}
}

// N <= 1e18;
final class LongPrime {
	private static final int bsf(final long x){ return Long.numberOfTrailingZeros(x); }
	private static final long gcd(long a, long b) {
		a = abs(a);
		b = abs(b);
		if(a == 0) {
			return b;
		}
		if(b == 0) {
			return a;
		}
		final int shift = bsf(a|b);
		a >>= bsf(a);
		do {
			b >>= bsf(b);
			if(a > b) {
				a ^= b;
				b ^= a;
				a ^= b;
			}
			b -= a;
		} while(b > 0);
		return a << shift;
	}
	static final boolean isPrime(final long n) {
		if(n <= 1) {
			return false;
		}
		if(n == 2) {
			return true;
		}
		if(n % 2 == 0) {
			return false;
		}
		long d = n - 1;
		while(d % 2 == 0) {
			d /= 2;
		}
		final long[] sample = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37};
		for(final long a: sample) {
			if(n <= a) {
				break;
			}
			long t = d;
			BigInteger y = BigInteger.valueOf(a).modPow(BigInteger.valueOf(t), BigInteger.valueOf(n));
			while(t != n - 1 && !y.equals(BigInteger.ONE) && !y.equals(BigInteger.valueOf(n).subtract(BigInteger.ONE))) {
				y = y.multiply(y).mod(BigInteger.valueOf(n));
				t <<= 1;
			}
			if(!y.equals(BigInteger.valueOf(n).subtract(BigInteger.ONE)) && t % 2 == 0) {
				return false;
			}
		}
		return true;
	}
	private static final long find(final long n) {
		if(isPrime(n)) {
			return n;
		}
		if(n % 2 == 0) {
			return 2;
		}
		long st = 0;
		final LongBinaryOperator f = (x, y) -> { return BigInteger.valueOf(x).multiply(BigInteger.valueOf(x)).add(BigInteger.valueOf(y)).mod(BigInteger.valueOf(n)).longValue(); };
		while(true) {
			st++;
			long x = st, y = f.applyAsLong(x, st);
			while(true) {
				final long p = gcd(y - x + n, n);
				if(p == 0 || p == n) {
					break;
				}
				if(p != 1) {
					return p;
				}
				x = f.applyAsLong(x, st);
				y = f.applyAsLong(f.applyAsLong(y, st), st);
			}
		}
	}
	static final ArrayList<Long> primeFactor(final long n) {
		if(n == 1) return new ArrayList<>();
		final long x = find(n);
		if(x == n) return new ArrayList<>(Arrays.asList(x));
		final ArrayList<Long> l = primeFactor(x), r = primeFactor(n / x);
		l.addAll(r);
		Collections.sort(l);
		return l;
	}
}
// N > 1e18
final class BigPrime {
	private static final int bsf(final long x){ return Long.numberOfTrailingZeros(x); }
	private static final BigInteger gcd(BigInteger a, BigInteger b) {
		a = a.abs();
		b = b.abs();
		if(a.equals(BigInteger.ZERO)) {
			return b;
		}
		if(b.equals(BigInteger.ZERO)) {
			return a;
		}
		final int shift = bsf(a.or(b).longValue());
		a = a.shiftRight(bsf(a.longValue()));
		do {
			b = b.shiftRight(bsf(b.longValue()));
			if(a.compareTo(b) > 0) {
				final BigInteger tmp = b;
				b = a;
				a = tmp;
			}
			b = b.subtract(a);
		} while(b.compareTo(BigInteger.ZERO) > 0);
		return a.shiftLeft(shift);
	}
	static final boolean isPrime(final BigInteger n) {
		if(n.compareTo(BigInteger.ONE) <= 0) {
			return false;
		}
		if(n.equals(BigInteger.TWO)) {
			return true;
		}
		if(n.and(BigInteger.ONE).equals(BigInteger.valueOf(0))) {
			return false;
		}
		BigInteger d = n.subtract(BigInteger.ONE);
		while(d.and(BigInteger.ONE).equals(BigInteger.valueOf(0))) {
			d = d.shiftRight(1);
		}
		final long[] sample = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37};
		for(final long a: sample) {
			if(n.compareTo(BigInteger.valueOf(a)) <= 0) {
				break;
			}
			BigInteger t = d;
			BigInteger y = BigInteger.valueOf(a).modPow(t, n);
			while(!t.equals(n.subtract(BigInteger.ONE)) && !y.equals(BigInteger.ONE) && !y.equals(n.subtract(BigInteger.ONE))) {
				y = y.multiply(y).mod(n);
				t = t.shiftLeft(1);
			}
			if(!y.equals(n.subtract(BigInteger.ONE)) && t.and(BigInteger.ONE).equals(BigInteger.ZERO)) {
				return false;
			}
		}
		return true;
	}
	private static final BigInteger find(final BigInteger n) {
		if(isPrime(n)) {
			return n;
		}
		if(n.and(BigInteger.ONE).equals(BigInteger.ZERO)) {
			return BigInteger.TWO;
		}
		int st = 0;
		final BiFunction<BigInteger, Integer, BigInteger> f = (x, y) -> { return x.multiply(x).add(BigInteger.valueOf(y)).mod(n); };
		while(true) {
			st++;
			BigInteger x = BigInteger.valueOf(st), y = f.apply(x, st);
			while(true) {
				final BigInteger p = gcd(y.subtract(x).add(n), n);
				if(p.equals(BigInteger.ZERO) || p.equals(n)) {
					break;
				}
				if(!p.equals(BigInteger.ONE)) {
					return p;
				}
				x = f.apply(x, st);
				y = f.apply(f.apply(y, st), st);
			}
		}
	}
	static final ArrayList<BigInteger> primeFactor(final BigInteger n) {
		if(n.equals(BigInteger.ONE)) {
			return new ArrayList<>();
		}
		final BigInteger x = find(n);
		if(x.equals(n)) {
			return new ArrayList<>(Arrays.asList(x));
		}
		final ArrayList<BigInteger> l = primeFactor(x), r = primeFactor(n.divide(x));
		l.addAll(r);
		Collections.sort(l);
		return l;
	}
}

final class ModPrime {
	private final int len, mod;
	private final long[] f, rf;
	ModPrime(final int mod, final int sz) {
		this.mod = mod;
		len = min(sz + 1, mod);
		f = new long[len];
		rf = new long[len];
		init();
	}
	private final long inv(long x) {
		long res = 1, k = mod - 2;
		while(k > 0) {
			if(k % 2 == 1) {
				res = (res * x) % mod;
			}
			x = (x * x) % mod;
			k >>= 1;
		}
		return res;
	}
	private final void init() {
		f[0] = 1;
		for(int i = 0; ++i < len;) {
			f[i] = (f[i - 1] * i) % mod;
		}
		rf[len - 1] = inv(f[len - 1]);
		for(int i = len; --i > 0;) {
			rf[i - 1] = (rf[i] * i) % mod;
		}
	}
	final long C(final int n, final int k) {
		if(k < 0 || n < k) {
			return 0;
		}
		final long a = f[n], b = rf[n - k], c = rf[k], bc = (b * c) % mod;
		return (a * bc) % mod;
	}
	final long P(final int n, final int k) {
		if (k < 0 || n < k) {
			return 0;
		}
		final long a = f[n], b = rf[n - k];
		return (a * b) % mod;
	}
	final long H(final int n, final int k) {
		if (n == 0 && k == 0) {
			return 1;
		}
		return C(n + k - 1, k);
	}
	final long fact(final int n){ return f[n]; }
}

final class EulerPhiTable {
	private final int[] euler;
	EulerPhiTable(final int n) {
		euler = Utility.iota(n + 1).toArray();
		for(int i = 2; i <= n; ++i) {
			if(euler[i] == i) {
				for(int j = i; j <= n; j += i) {
					euler[j] = euler[j] / i * (i - 1);
				}
			}
		}
	}
	final int[] get(){ return euler; }
}

final class DP {
	static final long knapsack01(final int[] a, final long[] v, final int w) {
		final int n = a.length;
		final long[] dp = new long[w + 1];
		Arrays.fill(dp, Long.MIN_VALUE);
		dp[0] = 0;
		for(int i = 0; i < n; i++) {
			for(int j = w; j >= a[i]; j--) {
				if(dp[j - a[i]] != Long.MIN_VALUE) {
					if(dp[j - a[i]] + v[i] > dp[j]) {
						dp[j] = dp[j - a[i]] + v[i];
					}
				}
			}
		}
		return Utility.max(dp);
	}
	static final int knapsack01(final long[] a, final int[] v, final long w) {
		final int n = a.length;
		final int s = (int) Utility.sum(v);
		final long[] dp = new long[s + 1];
		Arrays.fill(dp, w + 1);
		dp[0] = 0;
		for(int i = 0; i < n; i++) {
			for(int j = s; j >= v[i]; j--) {
				dp[j] = Math.min(dp[j], dp[j - v[i]] + a[i]);
			}
		}
		int res = 0;
		for(int i = 0; i <= s; i++) {
			if(dp[i] <= w) {
				res = i;
			}
		}
		return res;
	}
	private static final long[] knapsack(final int[] a, final long[] v, final int[] m, final int w, final boolean less) {
		final int n = a.length;
		final long[] dp = new long[w + 1], deqv = new long[w + 1];
		Arrays.fill(dp, Long.MIN_VALUE);
		dp[0] = 0;
		final int[] deq = new int[w + 1];
		for(int i = 0; i < n; ++i) {
			if(a[i] == 0) {
				for(int j = 0; j <= w; ++j) {
					if(dp[j] != Long.MIN_VALUE && (less ? dp[j] + v[i] * m[i] < dp[j] : dp[j] + v[i] * m[i] > dp[j])) {
						dp[j] = dp[j] + v[i] * m[i];
					}
				}
			} else {
				for(int k = 0; k < a[i]; ++k) {
					int s = 0, t = 0;
					for(int j = 0; a[i] * j + k <= w; ++j) {
						if(dp[a[i] * j + k] != Long.MIN_VALUE) {
							final long val = dp[a[i] * j + k] - j * v[i];
							while(s < t && (less ? val < deqv[t - 1] : val > deqv[t - 1])) {
								t--;
							}
							deq[t] = j;
							deqv[t++] = val;
						}
						if(s < t) {
							dp[j * a[i] + k] = deqv[s] + j * v[i];
							if(deq[s] == j - m[i]) {
								s++;
							}
						}
					}
				}
			}
		}
		return dp;
	}
	static final long knapsack(final int[] a, final long[] v, final int[] m, final int w){ return Utility.max(knapsack(a, v, m, w, false)); }
	static final long knapsack(final long[] a, final int[] v, final long[] m, final long w) {
		final int n = a.length;
		final int max = Utility.max(v);
		if(max == 0) {
			return 0;
		}
		final int[] ma = new int[n];
		final long[] mb = new long[n];
		for(int i = 0; i < n; i++) {
			ma[i] = (int) Math.min(m[i], max - 1);
			mb[i] = m[i] - ma[i];
		}
		int sum = 0;
		for(int i = 0; i < n; ++i) {
			sum += ma[i] * v[i];
		}
		final long[] dp = knapsack(v, a, ma, sum, true);
		final int[] id = Utility.iota(n).boxed().sorted((i, j) -> -Long.compare(v[i] * a[j], v[j] * a[i])).mapToInt(i -> i).toArray();
		long res = 0;
		for(int i = 0; i < dp.length; ++i) {
			if(dp[i] > w || dp[i] == Long.MIN_VALUE) {
				continue;
			}
			long rest = w - dp[i], cost = i;
			for(final int j: id) {
				final long get = Math.min(mb[j], rest / a[j]);
				if(get <= 0) {
					continue;
				}
				cost += get * v[j];
				rest -= get * a[j];
			}
			res = Math.max(res, cost);
		}
		return res;
	}
	static final long knapsack(final int[] a, final long[] v, final int w) {
		final int n = a.length;
		final long[] dp = new long[w + 1];
		Arrays.fill(dp, Long.MIN_VALUE);
		dp[0] = 0;
		for(int i = 0; i < n; i++) {
			for(int j = a[i]; j <= w; j++) {
				if(dp[j - a[i]] != Long.MIN_VALUE) {
					if(dp[j - a[i]] + v[i] > dp[j]) {
						dp[j] = dp[j - a[i]] + v[i];
					}
				}
			}
		}
		return Utility.max(dp);
	}
	static final long maxRectangle(final int[] a) {
		final Stack<Integer> sk = new Stack<>();
		final long[] h = new long[a.length + 1];
		for(int i = 0; i < a.length; ++i) {
			h[i] = a[i];
		}
		final int[] l = new int[h.length];
		long res = 0;
		for(int i = 0; i < h.length; i++) {
			while(!sk.isEmpty() && h[sk.peek()] >= h[i]) {
				res = max(res, (i - l[sk.peek()] - 1) * h[sk.pop()]);
			}
			l[i] = sk.isEmpty() ? -1 : sk.peek();
			sk.add(i);
		}
		return res;
	}
	static final long maxRectangle(final long[] a) {
		final Stack<Integer> sk = new Stack<>();
		final long[] h = Arrays.copyOf(a, a.length + 1);
		final int[] l = new int[h.length];
		long res = 0;
		for(int i = 0; i < h.length; i++) {
			while(!sk.isEmpty() && h[sk.peek()] >= h[i]) {
				res = max(res, (i - l[sk.peek()] - 1) * h[sk.pop()]);
			}
			l[i] = sk.isEmpty() ? -1 : sk.peek();
			sk.add(i);
		}
		return res;
	}
	static final int lcs(final String s, final String t) {
		final int n = s.length();
		final int[] dp = new int[n + 1], ndp = new int[n + 1];
		for(int i = 0; i < t.length(); ++i) {
			for(int j = 0; j < n; ++j) {
				if(s.charAt(j) == t.charAt(i)) {
					ndp[j + 1] = dp[j] + 1;
				} else {
					ndp[j + 1] = max(ndp[j], dp[j + 1]);
				}
			}
			Utility.swap(dp, ndp);
		}
		return dp[n];
	}
	static final int[] lis(final int[] a) {
		final int n = a.length;
		List<IntPair> dp = new ArrayList<IntPair>();
		final int[] p = new int[n];
		Arrays.fill(p, -1);
		for(int i = 0; i < n; ++i) {
			final int id = Utility.lowerBound(dp, IntPair.of(a[i], -i));
			if(id != 0) {
				p[i] = -dp.get(id - 1).second.intValue();
			}
			if(id == dp.size()) {
				dp.add(IntPair.of(a[i], -i));
			} else {
				dp.set(id, IntPair.of(a[i], -i));
			}
		}
		final List<Integer> res = new ArrayList<Integer>();
		for(int i = -dp.get(dp.size() - 1).second.intValue(); i != -1; i = p[i]) {
			res.add(i);
		}
		Collections.reverse(res);
		return res.stream().mapToInt(i -> i).toArray();
	}
	static final int[] lis(final long[] a) {
		final int n = a.length;
		List<IntPair> dp = new ArrayList<IntPair>();
		final int[] p = new int[n];
		Arrays.fill(p, -1);
		for(int i = 0; i < n; ++i) {
			final int id = Utility.lowerBound(dp, IntPair.of(a[i], -i));
			if(id != 0) {
				p[i] = -dp.get(id - 1).second.intValue();
			}
			if(id == n) {
				dp.add(IntPair.of(a[i], -i));
			} else {
				dp.set(id, IntPair.of(a[i], -i));
			}
		}
		final List<Integer> res = new ArrayList<Integer>();
		for(int i = -dp.get(dp.size() - 1).second.intValue(); i != -1; i = p[i]) {
			res.add(i);
		}
		Collections.reverse(res);
		return res.stream().mapToInt(i -> i).toArray();
	}
}

final class Matrix implements Cloneable {
	private final int h, w;
	private final long[][] mat;
	Matrix(final int n){ this(n, n); }
	Matrix(final int h, final int w) {
		this.h = h;
		this.w = w;
		mat = new long[h][w];
	}
	Matrix(final int[][] m) {
		this(m.length, m[0].length);
		IntStream.range(0, h).forEach(i -> Arrays.setAll(mat[i], j -> m[i][j]));
	}
	Matrix(final long[][] m) {
		this(m.length, m[0].length);
		IntStream.range(0, h).forEach(i -> Arrays.setAll(mat[i], j -> m[i][j]));
	}
	static final Matrix E(final int n) {
		final Matrix m = new Matrix(n);
		IntStream.range(0, n).forEach(i -> m.set(i, i, 1));
		return m;
	}
	final long[] getH(final int i){ return mat[i]; }
	final long[] getW(final int i){ return IntStream.range(0, h).mapToLong(j -> mat[j][i]).toArray(); }
	final long[][] get(){ return mat; }
	final long get(final int i, final int j){ return mat[i][j]; }
	final void set(final int i, final int j, final long x){ mat[i][j] = x; }
	final Matrix add(final Matrix m) {
		assert h == m.h && w == m.w;
		final Matrix mt = new Matrix(h, w);
		for(int i = 0; i < h; ++i) {
			for(int j = 0; j < w; ++j) {
				mt.set(i, j, mat[i][j] + m.get(i, j));
			}
		}
		return mt;
	}
	final Matrix add(final Matrix m, final long mod) {
		assert h == m.h && w == m.w;
		final Matrix mt = new Matrix(h, w);
		for(int i = 0; i < h; ++i) {
			for(int j = 0; j < w; ++j) {
				mt.set(i, j, Utility.mod(mat[i][j] + m.get(i, j), mod));
			}
		}
		return mt;
	}
	final Matrix sub(final Matrix m) {
		assert h == m.h && w == m.w;
		final Matrix mt = new Matrix(h, w);
		for(int i = 0; i < h; ++i) {
			for(int j = 0; j < w; ++j) {
				mt.set(i, j, mat[i][j] - m.get(i, j));
			}
		}
		return mt;
	}
	final Matrix sub(final Matrix m, final long mod) {
		assert h == m.h && w == m.w;
		final Matrix mt = new Matrix(h, w);
		for(int i = 0; i < h; ++i) {
			for(int j = 0; j < w; ++j) {
				mt.set(i, j, Utility.mod(mat[i][j] - m.get(i, j), mod));
			}
		}
		return mt;
	}
	final Matrix mul(final Matrix m) {
		assert w == m.h;
		final Matrix mt = new Matrix(h, m.w);
		for(int i = 0; i < h; ++i) {
			for(int j = 0; j < m.w; ++j) {
				for(int k = 0; k < w; ++k) {
					mt.set(i, j, mt.get(i, j) + mat[i][k] * m.get(k, j));
				}
			}
		}
		return mt;
	}
	final Matrix mul(final Matrix m, final long mod) {
		assert w == m.h;
		final Matrix mt = new Matrix(h, m.w);
		for(int i = 0; i < h; ++i) {
			for(int j = 0; j < m.w; ++j) {
				for(int k = 0; k < w; ++k) {
					mt.set(i, j, Utility.mod(mt.get(i, j) + mat[i][k] * m.get(k, j), mod));
				}
			}
		}
		return mt;
	}
	final Matrix pow(int k) {
		Matrix n = clone();
		Matrix m = Matrix.E(h);
		while(k > 0) {
			if(k % 2 == 1) {
				m = m.mul(n);
			}
			n = n.mul(n);
			k >>= 1;
		}
		return m;
	}
	final Matrix pow(long k, final long mod) {
		Matrix n = clone();
		Matrix m = Matrix.E(h);
		while(k > 0) {
			if(k % 2 == 1) {
				m = m.mul(n, mod);
			}
			n = n.mul(n, mod);
			k >>= 1L;
		}
		return m;
	}
	@Override
	public final boolean equals(final Object o) {
		if(this == o) {
			return true;
		}
		if(o == null || getClass() != o.getClass()) {
			return false;
		}
		final Matrix m = (Matrix) o;
		if(h != m.h || w != m.w) {
			return false;
		}
		for(int i = 0; i < h; ++i) {
			for(int j = 0; j < w; ++j) {
				if(mat[i][j] != m.get(i, j)) {
					return false;
				}
			}
		}
		return true;
	}
	@Override
	public final Matrix clone() {
		final Matrix m = new Matrix(h, w);
		for(int i = 0; i < h; ++i) {
			m.mat[i] = Arrays.copyOf(mat[i], w);
		}
		return m;
	}
	@Override
	public final String toString() {
		final StringBuilder sb = new StringBuilder();
		final int interval = String.valueOf(IntStream.range(0, h).mapToLong(i -> IntStream.range(0, w).mapToLong(j -> mat[i][j]).max().getAsLong()).max().getAsLong()).length() + 1;
		for(int i = 0; i < h; ++i) {
			sb.append("[");
			for(int j = 0; j < w; ++j) {
				sb.append(String.format("%" + interval + "d", mat[i][j]));
				if(j + 1 == w) {
					sb.append("]");
				}
			}
			if(i + 1 != h) {
				sb.append("\n");
			}
		}
		return sb.toString();
	}
}

class InclusiveScan {
	protected final int n;
	protected long[] s;
	protected InclusiveScan(final int n) {
		this.n = n;
		s = new long[n + 1];
	}
	InclusiveScan(final int[] a, final LongBinaryOperator op) {
		n = a.length;
		s = Arrays.stream(a).asLongStream().toArray();
		Arrays.parallelPrefix(s, op);
	}
	InclusiveScan(final long[] a, final LongBinaryOperator op) {
		n = a.length;
		s = a.clone();
		Arrays.parallelPrefix(s, op);
	}
	protected final long[] get(){ return s; }
}
final class PrefixSum extends InclusiveScan {
	private boolean built;
	PrefixSum(final int n) {
		super(n);
		built = false;
	}
	PrefixSum(final int[] a) {
		super(a, Long::sum);
		s = Utility.rotate(Arrays.copyOf(s, n + 1), -1);
	}
	PrefixSum(final long[] a) {
		super(a, Long::sum);
		s = Utility.rotate(Arrays.copyOf(s, n + 1), -1);
	}
	final long sum(final int l, final int r){ return s[r] - s[l]; }
	final void add(final int l, final int r, final long x) {
		if(built) {
			throw new UnsupportedOperationException("Prefix Sum has been built.");
		}
		s[l] += x;
		s[r] -= x;
	}
	final long[] build() {
		assert !built;
		Arrays.parallelPrefix(s, Long::sum);
		built = true;
		return Arrays.copyOf(s, n);
	}
}
final class PrefixSum2D {
	private final int h, w;
	private final long[][] data;
	private boolean built;
	PrefixSum2D(final int h, final int w) {
		this.h = h + 3;
		this.w = w + 3;
		data = new long[this.h][this.w];
		built = false;
	}
	PrefixSum2D(final int[][] a) {
		this(a.length, a[0].length);
		for(int i = 0; i < a.length; ++i) {
			for(int j = 0; j < a[i].length; ++j) {
				add(i, j, a[i][j]);
			}
		}
	}
	PrefixSum2D(final long[][] a) {
		this(a.length, a[0].length);
		for(int i = 0; i < a.length; ++i) {
			for(int j = 0; j < a[i].length; ++j) {
				add(i, j, a[i][j]);
			}
		}
	}
	final void add(int i, int j, final long x) {
		if(built) {
			throw new UnsupportedOperationException("Prefix Sum 2D has been built.");
		}
		i++;
		j++;
		if(i >= h || j >= w) {
			return;
		}
		data[i][j] += x;
	}
	final void add(final int i1, final int j1, final int i2, final int j2, final long x) {
		add(i1, j1, x);
		add(i1, j2, -x);
		add(i2, j1, -x);
		add(i2, j2, x);
	}
	final void build() {
		assert !built;
		for(int i = 0; ++i < h;) {
			for(int j = 0; ++j < w;) {
				data[i][j] += data[i][j - 1] + data[i - 1][j] - data[i - 1][j - 1];
			}
		}
		built = true;
	}
	final long get(final int i1, final int j1, final int i2, final int j2) {
		if(!built) {
			throw new UnsupportedOperationException("Prefix Sum 2D hasn't been built.");
		}
		return data[i2][j2] - data[i1][j2] - data[i2][j1] + data[i1][j1];
	}
	final long get(final int i, final int j) {
		if(!built) {
			throw new UnsupportedOperationException("Prefix Sum 2D hasn't been built.");
		}
		return data[i + 1][j + 1];
	}
	@Override
	public final String toString() {
		final StringBuilder sb = new StringBuilder();
		for(int i = 0; i < h - 3; ++i) {
			sb.append(get(i, 0));
			for(int j = 0; ++j < w - 3;) {
				sb.append(" " + get(i, j));
			}
			if(i + 1 < h) {
				sb.append('\n');
			}
		}
		return sb.toString();
	}
}

final class SuffixArray extends ArrayList<Integer> {
	private final String vs;
	SuffixArray(final String vs, final boolean compress) {
		this.vs = vs;
		final int[] newVS = new int[vs.length() + 1];
		if(compress) {
			final List<Integer> xs = vs.chars().sorted().distinct().boxed().collect(Collectors.toList());
			for(int i = 0; i < vs.length(); ++i) {
				newVS[i] = Utility.lowerBound(xs, (int) vs.charAt(i)) + 1;
			}
		} else {
			final int d = vs.chars().min().getAsInt();
			for(int i = 0; i < vs.length(); ++i) {
				newVS[i] = vs.charAt(i) - d + 1;
			}
		}
		this.addAll(Arrays.stream(SAIS(newVS)).boxed().collect(Collectors.toList()));
	}
	private final int[] SAIS(final int[] s) {
		final int n = s.length;
		final int[] ret = new int[n];
		final boolean[] isS = new boolean[n], isLMS = new boolean[n];
		int m = 0;
		for(int i = n - 2; i >= 0; i--) {
			isS[i] = (s[i] > s[i + 1]) || (s[i] == s[i + 1] && isS[i + 1]);
			m += (isLMS[i + 1] = isS[i] && !isS[i + 1]) ? 1 : 0;
		}
		final Consumer<ArrayList<Integer>> inducedSort = (lms) -> {
			final int upper = Arrays.stream(s).max().getAsInt();
			final int[] l = new int[upper + 2], r = new int[upper + 2];
			for(final int v: s) {
				++l[v + 1];
				++r[v];
			}
			Arrays.parallelPrefix(l, (x, y) -> x + y);
			Arrays.parallelPrefix(r, (x, y) -> x + y);
			Arrays.fill(ret, -1);
			for(int i = lms.size(); --i >= 0;) {
				ret[--r[s[lms.get(i)]]] = lms.get(i);
			}
			for(final int v: ret) {
				if(v >= 1 && isS[v - 1]) {
					ret[l[s[v - 1]]++] = v - 1;
				}
			}
			Arrays.fill(r, 0);
			for(final int v: s) {
				++r[v];
			}
			Arrays.parallelPrefix(r, (x, y) -> x + y);
			for(int k = ret.length - 1, i = ret[k]; k >= 1; i = ret[--k]) {
				if(i >= 1 && !isS[i - 1]) {
					ret[--r[s[i - 1]]] = i - 1;
				}
			}
		};
		final ArrayList<Integer> lms = new ArrayList<>(), newLMS = new ArrayList<>();
		for(int i = 0; ++i < n;) {
			if(isLMS[i]) {
				lms.add(i);
			}
		}
		inducedSort.accept(lms);
		for(int i = 0; i < n; ++i) {
			if(!isS[ret[i]] && ret[i] > 0 && isS[ret[i] - 1]) {
				newLMS.add(ret[i]);
			}
		}
		final BiPredicate<Integer, Integer> same = (a, b) -> {
			if(s[a++] != s[b++]) {
				return false;
			}
			while(true) {
				if(s[a] != s[b]) {
					return false;
				}
				if(isLMS[a] || isLMS[b]) {
					return isLMS[a] && isLMS[b];
				}
				a++;
				b++;
			}
		};
		int rank = 0;
		ret[n - 1] = 0;
		for(int i = 0; ++i < m;) {
			if(!same.test(newLMS.get(i - 1), newLMS.get(i))) {
				++rank;
			}
			ret[newLMS.get(i)] = rank;
		}
		if(rank + 1 < m) {
			final int[] newS = new int[m];
			for(int i = 0; i < m; ++i) {
				newS[i] = ret[lms.get(i)];
			}
			final var lmsSA = SAIS(newS);
			IntStream.range(0, m).forEach(i -> newLMS.set(i, lms.get(lmsSA[i])));
		}
		inducedSort.accept(newLMS);
		return ret;
	}
	private final boolean ltSubstr(final String t, int si, int ti) {
		final int sn = vs.length(), tn = t.length();
		while(si < sn && ti < tn) {
			if(vs.charAt(si) < t.charAt(ti)) {
				return true;
			}
			if(vs.charAt(si) > t.charAt(ti)) {
				return false;
			}
			++si;
			++ti;
		}
		return si >= sn && ti < tn;
	}
	final int lowerBound(final String t) {
		int ok = this.size(), ng = 0;
		while(ok - ng > 1) {
			final int mid = (ok + ng) / 2;
			if(ltSubstr(t, this.get(mid), 0)) {
				ng = mid;
			} else {
				ok = mid;
			}
		}
		return ok;
	}
	final Pair<Integer, Integer> equalRange(final String t) {
		final int low = lowerBound(t);
		int ng = low - 1, ok = this.size();
		final StringBuilder sb = new StringBuilder(t);
		sb.setCharAt(t.length() - 1, (char)(sb.charAt(sb.length() - 1) - 1));
		final String u = sb.toString();
		while(ok - ng > 1) {
			final int mid = (ok + ng) / 2;
			if(ltSubstr(u, this.get(mid), 0)) {
				ng = mid;
			} else {
				ok = mid;
			}
		}
		final int end = this.size() - 1;
		this.add(end, this.get(end) - 1);
		return Pair.of(low, ok);
	}
	final int[] lcpArray() {
		final int n = this.size() - 1;
		final int[] lcp = new int[n + 1], rank = new int[n + 1];
		for(int i = 0; i <= n; ++i) {
			rank[this.get(i)] = i;
		}
		int h = 0;
		for(int i = 0; i <= n; ++i) {
			if(rank[i] < n) {
				final int j = this.get(rank[i] + 1);
				for(; j + h < n && i + h < n; ++h) {
					if(vs.charAt(j + h) != vs.charAt(i + h)) {
						break;
					}
				}
				lcp[rank[i] + 1] = h;
				if(h > 0) {
					h--;
				}
			}
		}
		return lcp;
	}
	@Override
	public final String toString() { 
		final StringBuilder sb = new StringBuilder();
		for(int i = 0; i < this.size(); ++i) {
			sb.append(i + ":[" + this.get(i) + "]");
			for(int j = this.get(i); j < vs.length(); ++j) {
				sb.append(" " + vs.charAt(j));
			}
			if(i + 1 != this.size()) {
				sb.append("\n");
			}
		}
		return sb.toString();
	}
}

final class Deque<T> implements Iterable<T> {
	private int n, head, tail;
	private Object[] buf;
	Deque(){ this(1 << 17); }
	private Deque(final int n) {
		this.n = n;
		head = tail = 0;
		buf = new Object[n];
	}
	Deque(final T[] a) {
		this(a.length);
		Arrays.stream(a).forEach(i -> add(i));
	}
	private final int next(final int index) {
		final int next = index + 1;
		return next == n ? 0 : next;
	}
	private final int prev(final int index) {
		final int prev = index - 1;
		return prev == -1 ? n - 1 : prev;
	}
	private final int index(final int i) {
		final int size = size();
		assert i < size;
		final int id = head + i;
		return n <= id ? id - n : id;
	}
	private final void arraycopy(final int fromId, final T[] a, final int from, final int len) {
		assert fromId + len <= size();
		final int h = index(fromId);
		if(h + len < n) {
			System.arraycopy(buf, h, a, from, len);
		} else {
			final int back = n - h;
			System.arraycopy(buf, h, a, from, back);
			System.arraycopy(buf, 0, a, from + back, len - back);
		}
	}
	@SuppressWarnings("unchecked")
	private final void extend() {
		final Object[] tmp = new Object[n << 1];
		arraycopy(0, (T[]) tmp, 0, size());
		buf = tmp;
		n = buf.length;
	}
	final boolean isEmpty(){ return size() == 0; }
	final int size() {
		final int size = tail - head;
		return size < 0 ? size + n : size;
	}
	final void addFirst(final T x) {
		if(prev(head) == tail) {
			extend();
		}
		head = prev(head);
		buf[head] = x;
	}
	final void addLast(final T x) {
		if(next(tail) == head) {
			extend();
		}
		buf[tail] = x;
		tail = next(tail);
	}
	final void removeFirst() {
		if(head == tail) {
			throw new NoSuchElementException("Deque is empty");
		}
		head = next(head);
	}
	final void removeLast() {
		if(head == tail) {
			throw new NoSuchElementException("Deque is empty");
		}
		tail = prev(tail);
	}
	@SuppressWarnings("unchecked")
	final T pollFirst() {
		if(head == tail) {
			throw new NoSuchElementException("Deque is empty");
		}
		final T ans = (T) buf[head];
		head = next(head);
		return ans;
	}
	@SuppressWarnings("unchecked")
	final T pollLast() {
		if(head == tail) {
			throw new NoSuchElementException("Deque is empty");
		}
		tail = prev(tail);
		return (T) buf[tail];
	}
	final T peekFirst(){ return get(0); }
	final T peekLast(){ return get(n - 1); }
	@SuppressWarnings("unchecked")
	final T get(final int i){ return (T) buf[index(i)]; }
	final void set(final int i, final T x){ buf[index(i)] = x; }
	final void add(final T x){ addLast(x); }
	final T poll(){ return pollFirst(); }
	final T peek(){ return peekFirst(); }
	@SuppressWarnings("unchecked")
	final void swap(final int a, final int b) {
		final int i = index(a), j = index(b);
		final T num = (T) buf[i];
		buf[i] = buf[j];
		buf[j] = num;
	}
	final void clear(){ head = tail = 0; }
	@SuppressWarnings("unchecked")
	final T[] toArray() {
		final Object[] array = new Object[size()];
		arraycopy(0, (T[]) array, 0, size());
		return (T[]) array;
	}
	@Override
	public final String toString(){ return Arrays.toString(toArray()); }
	@Override
	public final Iterator<T> iterator(){ return new DequeIterator(); }
	private class DequeIterator implements Iterator<T> {
		private int now = head;
		private int rem = size();
		@Override
		public boolean hasNext(){ return rem > 0; }
		@Override
		public final T next() {
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			@SuppressWarnings("unchecked")
			final T res = (T) buf[now];
			now = (now + 1) % n;
			rem--;
			return res;
		}
		@Override
		public final void remove() {
			if(isEmpty()) {
				throw new IllegalStateException();
			}
			now = (now - 1 + n) % n;
			buf[now] = null;
			head = (head + 1) % n;
			rem++;
		}
	}
}
final class IntDeque {
	private int n, head, tail;
	private long[] buf;
	IntDeque(){ this(1 << 17); }
	private IntDeque(final int n) {
		this.n = n;
		head = tail = 0;
		buf = new long[n];
	}
	IntDeque(final int[] a) {
		this(a.length);
		Arrays.stream(a).forEach(i -> add(i));
	}
	IntDeque(final long[] a) {
		this(a.length);
		Arrays.stream(a).forEach(i -> add(i));
	}
	private final int next(final int index) {
		final int next = index + 1;
		return next == n ? 0 : next;
	}
	private final int prev(final int index) {
		final int prev = index - 1;
		return prev == -1 ? n - 1 : prev;
	}
	private final int index(final int i) {
		final int size = size();
		assert i < size;
		final int id = head + i;
		return n <= id ? id - n : id;
	}
	private final void arraycopy(final int fromId, final long[] a, final int from, final int len) {
		assert fromId + len <= size();
		final int h = index(fromId);
		if(h + len < n) {
			System.arraycopy(buf, h, a, from, len);
		} else {
			final int back = n - h;
			System.arraycopy(buf, h, a, from, back);
			System.arraycopy(buf, 0, a, from + back, len - back);
		}
	}
	private final void extend() {
		final long[] tmp = new long[n << 1];
		arraycopy(0, tmp, 0, size());
		buf = tmp;
		n = buf.length;
	}
	final boolean isEmpty(){ return size() == 0; }
	final int size() {
		final int size = tail - head;
		return size < 0 ? size + n : size;
	}
	final void addFirst(final long x) {
		head = prev(head);
		if(head == tail) {
			extend();
		}
		buf[head] = x;
	}
	final void addLast(final long x) {
		if(next(tail) == head) {
			extend();
		}
		buf[tail] = x;
		tail = next(tail);
	}
	final void removeFirst() {
		if(head == tail) {
			throw new NoSuchElementException("Deque is empty");
		}
		head = next(head);
	}
	final void removeLast() {
		if(head == tail) {
			throw new NoSuchElementException("Deque is empty");
		}
		tail = prev(tail);
	}
	final long pollFirst() {
		if(head == tail) {
			throw new NoSuchElementException("Deque is empty");
		}
		final long ans = buf[head];
		head = next(head);
		return ans;
	}
	final long pollLast() {
		if(head == tail) {
			throw new NoSuchElementException("Deque is empty");
		}
		tail = prev(tail);
		return buf[tail];
	}
	final long peekFirst(){ return get(0); }
	final long peekLast(){ return get(n - 1); }
	final long get(final int i){ return buf[index(i)]; }
	final void set(final int i, final long x){ buf[index(i)] = x; }
	final void add(final long x){ addLast(x); }
	final long poll(){ return pollFirst(); }
	final long peek(){ return peekFirst(); }
	final void swap(final int a, final int b) {
		final int i = index(a);
		final int j = index(b);
		final long num = buf[i];
		buf[i] = buf[j];
		buf[j] = num;
	}
	final void clear(){ head = tail = 0; }
	final long[] toArray(){ return Arrays.copyOf(buf, size()); }
	@Override
	public final String toString(){ return Arrays.toString(toArray()); }
}

final class AVLTree<T extends Comparable<? super T>> {
	static final class Node<T extends Comparable<? super T>> {
		T val;
		@SuppressWarnings("unchecked")
		Node<T>[] ch = new Node[2];
		int dep, size;
		Node(final T val, final Node<T> l, final Node<T> r) {
			this.val = val;
			dep = size = 1;
			ch[0] = l;
			ch[1] = r;
		}
	}
	private Node<T> root;
	private final int depth(final Node<T> t){ return t == null ? 0 : t.dep; }
	private final int count(final Node<T> t){ return t == null ? 0 : t.size; }
	private final Node<T> update(final Node<T> t) {
		t.dep = max(depth(t.ch[0]), depth(t.ch[1])) + 1;
		t.size = count(t.ch[0]) + count(t.ch[1]) + 1;
		return t;
	}
	private final Node<T> rotate(Node<T> t, final int b) {
		Node<T> s = t.ch[1 - b];
		t.ch[1 - b] = s.ch[b];
		s.ch[b] = t;
		t = update(t);
		s = update(s);
		return s;
	}
	private final Node<T> fetch(Node<T> t) {
		if(t == null) {
			return t;
		}
		if(depth(t.ch[0]) - depth(t.ch[1]) == 2) {
			if(depth(t.ch[0].ch[1]) > depth(t.ch[0].ch[0])) {
				t.ch[0] = rotate(t.ch[0], 0);
			}
			t = rotate(t, 1);
		}
		else if(depth(t.ch[0]) - depth(t.ch[1]) == -2) {
			if (depth(t.ch[1].ch[0]) > depth(t.ch[1].ch[1])) {
				t.ch[1] = rotate(t.ch[1], 1);
			}
			t = rotate(t, 0);
		}
		return t;
	}
	private final Node<T> insert(final Node<T> t, final int k, final T v) {
		if(t == null) {
			return new Node<T>(v, null, null);
		}
		final int c = count(t.ch[0]), b = (k > c) ? 1 : 0;
		t.ch[b] = insert(t.ch[b], k - (b == 1 ? (c + 1) : 0), v);
		update(t);
		return fetch(t);
	}
	private final Node<T> erase(final Node<T> t) {
		if(t == null || t.ch[0] == null && t.ch[1] == null) {
			return null;
		}
		if(t.ch[0] == null || t.ch[1] == null) {
			return t.ch[t.ch[0] == null ? 1 : 0];
		}
		return fetch(update(new Node<T>(find(t.ch[1], 0).val, t.ch[0], erase(t.ch[1], 0))));
	}
	private final Node<T> erase(Node<T> t, final int k) {
		if(t == null) {
			return null;
		}
		final int c = count(t.ch[0]);
		if(k < c) {
			t.ch[0] = erase(t.ch[0], k);
			t = update(t);
		}
		else if(k > c) {
			t.ch[1] = erase(t.ch[1], k - (c + 1));
			t = update(t);
		}
		else {
			t = erase(t);
		}
		return fetch(t);
	}
	private final Node<T> find(final Node<T> t, final int k) {
		if(t == null) {
			return t;
		}
		final int c = count(t.ch[0]);
		return k < c ? find(t.ch[0], k) : k == c ? t : find(t.ch[1], k - (c + 1));
	}
	private final int cnt(final Node<T> t, final T v) {
		if(t == null) {
			return 0;
		}
		if(t.val.compareTo(v) < 0) {
			return count(t.ch[0]) + 1 + cnt(t.ch[1], v);
		}
		if(t.val.equals(v)) {
			return count(t.ch[0]);
		}
		return cnt(t.ch[0], v);
	}
	AVLTree(){ root = null; }
	final void add(final T val){ root = insert(root, cnt(root, val), val); }
	final void remove(final int k){ root = erase(root, k); }
	final T get(final int k){ return find(root, k).val; }
	final int count(final T val){ return cnt(root, val); }
	final int size(){ return root.size; }
	@Override
	public final String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append(get(0));
		for(int i = 0; ++i < root.size;) {
			sb.append(" ");
			sb.append(get(i));
		}
		return "[" + sb.toString() + "]";
	}
}

final class DoubleEndedPriorityQueue<T extends Number> {
	private final ArrayList<T> d;
	DoubleEndedPriorityQueue(final ArrayList<T> d) {
		this.d = d;
		makeHeap();
	}
	private final void makeHeap() {
		for(int i = d.size(); i-- > 0;) {
			if (i % 2 == 1 && d.get(i - 1).longValue() < d.get(i).longValue()) {
				Collections.swap(d, i - 1, i);
			}
			up(down(i), i);
		}
	}
	private final int down(int k) {
		final int n = d.size();
		if(k % 2 == 1) {
			while(2 * k + 1 < n) {
				int c = 2 * k + 3;
				if(n <= c || d.get(c - 2).longValue() < d.get(c).longValue()) {
					 c -= 2;
				}
				if(c < n && d.get(c).longValue() < d.get(k).longValue()) {
					Collections.swap(d, k, c);
					k = c;
				}
				else {
					break;
				}
			}
		} else {
			while(2 * k + 2 < n) {
				int c = 2 * k + 4;
				if(n <= c || d.get(c).longValue() < d.get(c - 2).longValue()) {
					c -= 2;
				}
				if(c < n && d.get(k).longValue() < d.get(c).longValue()) {
					Collections.swap(d, k, c);
					k = c;
				}
				else {
					break;
				}
			}
		}
		return k;
	}
	private final int up(int k, final int root) {
		if((k | 1) < d.size() && d.get(k & ~1).longValue() < d.get(k | 1).longValue()) {
			Collections.swap(d, k & ~1, k | 1);
			k ^= 1;
		}
		int p;
		while(root < k && d.get(p = parent(k)).longValue() < d.get(k).longValue()) {
			Collections.swap(d, p, k);
			k = p;
		}
		while(root < k && d.get(k).longValue() < d.get(p = parent(k) | 1).longValue()) {
			Collections.swap(d, p, k);
			k = p;
		}
		return k;
	}
	private final int parent(final int k){ return ((k >> 1) - 1) & ~1; }
	private final void popBack(final ArrayList<T> d){ d.remove(d.size() - 1); } 
	final void push(final T x) {
		final int k = d.size();
		d.add(x);
		up(k, 1);
	}
	final T popMin() {
		final T res = getMin();
		if(d.size() < 3) {
			popBack(d); 
		} else {
			Collections.swap(d, 1, d.size() - 1);
			popBack(d);
			up(down(1), 1);
		}
		return res;
	}
	final T popMax() {
		final T res = getMax();
		if(d.size() < 2) { 
			popBack(d);
		} else {
			Collections.swap(d, 0, d.size() - 1);
			popBack(d);
			up(down(0), 1);
		}
		return res;
	}
	final T getMin(){ return d.size() < 2 ? d.get(0) : d.get(1); }
	final T getMax(){ return d.get(0); }
	final int size(){ return d.size(); }
	final boolean isEmpty(){ return d.isEmpty(); }
}

final class FenwickTree {
	private final int n;
	private final long[] data;
	FenwickTree(final int n) {
		this.n = n + 2;
		data = new long[this.n + 1];
	}
	FenwickTree(final int[] a) {
		this(a.length);
		IntStream.range(0, a.length).forEach(i -> add(i, a[i]));
	}
	FenwickTree(final long[] a) {
		this(a.length);
		IntStream.range(0, a.length).forEach(i -> add(i, a[i]));
	}
	final long sum(int k) {
		if(k < 0) {
			return 0;
		}
		long ret = 0;
		for(++k; k > 0; k -= k & -k) {
			ret += data[k];
		}
		return ret;
	}
	final long sum(final int l, final int r){ return sum(r) - sum(l - 1); }
	final long get(final int k){ return sum(k) - sum(k - 1); }
	final void add(int k, final long x) {
		for(++k; k < n; k += k & -k) {
			data[k] += x;
		}
	}
	final void add(final int l, final int r, final long x) {
		add(l, x);
		add(r + 1, -x);
	}
	private final int lg(final int n){ return 31 - Integer.numberOfLeadingZeros(n); }
	final int lowerBound(long w) {
		if(w <= 0) {
			return 0;
		}
		int x = 0;
		for(int k = 1 << lg(n); k > 0; k >>= 1) {
			if(x + k <= n - 1 && data[x + k] < w) {
				w -= data[x + k];
				x += k;
			}
		}
		return x;
	}
	final int upperBound(long w) {
		if(w < 0) {
			return 0;
		}
		int x = 0;
		for(int k = 1 << lg(n); k > 0; k >>= 1) {
			if(x + k <= n - 1 && data[x + k] <= w) {
				w -= data[x + k];
				x += k;
			}
		}
		return x;
	}
	@Override
	public final String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append(sum(0));
		for(int i = 0; ++i < n - 2;) {
			sb.append(" " + sum(i));
		}
		return sb.toString();
	}
}
final class RangeBIT {
	private final int n;
	private final FenwickTree a, b;
	RangeBIT(final int n) {
		this.n = n;
		a = new FenwickTree(n + 1);
		b = new FenwickTree(n + 1);
	}
	RangeBIT(final int[] arr) {
		this(arr.length);
		for(int i = 0; i < arr.length; ++i) {
			add(i, i, arr[i]);
		}
	}
	RangeBIT(final long[] arr) {
		this(arr.length);
		for(int i = 0; i < arr.length; ++i) {
			add(i, i, arr[i]);
		}
	}
	final void add(final int l, final int r, final long x) {
		a.add(l, x);
		a.add(r, -x);
		b.add(l, x * (1 - l));
		b.add(r, x * (r - 1));
	}
	final long get(final int i){ return sum(i, i + 1); }
	final long sum(int l, int r) {
		l--;
		r--;
		return a.sum(r) * r + b.sum(r) - a.sum(l) * l - b.sum(l);
	}
	@Override
	public final String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append(get(0));
		for(int i = 0; ++i < n;) {
			sb.append(" " + get(i));
		}
		return sb.toString();
	}
}

final class SegmentTree<T> {
	private int n = 1, rank = 0;
	private final int fini;
	private final BinaryOperator<T> op;
	private final T e;
	private final Object[] dat;
	SegmentTree(final int fini, final BinaryOperator<T> op, final T e) {
		this.fini = fini;
		this.op = op;
		this.e = e;
		while(this.fini > n) {
			n <<= 1;
			rank++;
		}
		dat = new Object[2 * n];
		Arrays.fill(dat, e);
	}
	SegmentTree(final T[] a, final BinaryOperator<T> op, final T e) {
		this(a.length, op, e);
		IntStream.range(0, a.length).forEach(i -> update(i, a[i]));
	}
	@SuppressWarnings("unchecked")
	final void update(int i, final T x) {
		i += n;
		dat[i] = x;
		do {
			i >>= 1;
			dat[i] = op.apply((T) dat[2 * i], (T) dat[2 * i + 1]);
		} while(i > 0);
	}
	final T get(final int i){ return query(i, i + 1); }
	@SuppressWarnings("unchecked")
	final T query(int a, int b) {
		T l = e, r = e;
		for(a += n, b += n; a < b; a >>= 1, b >>= 1) {
			if(a % 2 == 1) {
				l = op.apply(l, (T) dat[a++]);
			}
			if(b % 2 == 1) {
				r = op.apply((T) dat[--b], r);
			}
		}
		return op.apply(l, r);
	}
	@SuppressWarnings("unchecked")
	final T all(){ return (T) dat[1]; }
	@SuppressWarnings("unchecked")
	final int findLeft(final int r, final Predicate<T> fn) {
		if(r == 0) {
			return 0;
		}
		int h = 0, i = r + n;
		T val = e;
		for(; h <= rank; h++) {
			if(i >> (h & 1) > 0) {
				final T val2 = op.apply(val, (T) dat[i >> (h ^ 1)]);
				if(fn.test(val2)){
					i -= 1 << h;
					if(i == n) {
						return 0;
					}
					val = val2;
				}
				else {
					break;
				}
			}
		}
		for(; h-- > 0;) {
			final T val2 = op.apply(val, (T) dat[(i >> h) - 1]);
			if(fn.test(val2)){
				i -= 1 << h;
				if(i == n) {
					return 0;
				}
				val = val2;
			}
		}
		return i - n;
	}
	@SuppressWarnings("unchecked")
	final int findRight(final int l, final Predicate<T> fn) {
		if(l == fini) {
			return fini;
		}
		int h = 0, i = l + n;
		T val = e;
		for(; h <= rank; h++) {
			if(i >> (h & 1) > 0){
				final T val2 = op.apply(val, (T) dat[i >> h]);
				if(fn.test(val2)){
					i += 1 << h;
					if(i == n * 2) {
						return fini;
					}
					val = val2;
				}
				else {
					break;
				}
			}
		}
		for(; h-- > 0;) {
			final T val2 = op.apply(val, (T) dat[i >> h]);
			if(fn.test(val2)) {
				i += 1 << h;
				if(i == n * 2) {
					return fini;
				}
				val = val2;
			}
		}
		return min(i - n, fini);
	}
	@Override
	public final String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append(get(0));
		for(int i = 0; ++i < fini;) {
			sb.append(" " + get(i));
		}
		return sb.toString();
	}
}

class LazySegmentTree<T, U extends Comparable<? super U>> {
	private final int n;
	private int sz, h;
	private final Object[] data, lazy;
	private final BinaryOperator<T> f;
	private final BiFunction<T, U, T> map;
	private final BinaryOperator<U> comp;
	private final T e;
	private final U id;
	@SuppressWarnings("unchecked")
	private final void update(final int k){ data[k] = f.apply((T) data[2 * k], (T) data[2 * k + 1]); }
	@SuppressWarnings("unchecked")
	private final void allApply(final int k, final U x) {
		data[k] = map.apply((T) data[k], x);
		if(k < sz) {
			lazy[k] = comp.apply((U) lazy[k], x);
		}
	}
	@SuppressWarnings("unchecked")
	private final void propagate(final int k) {
		if(!lazy[k].equals(id)) {
			allApply(2 * k, (U) lazy[k]);
			allApply(2 * k + 1, (U) lazy[k]);
			lazy[k] = id;
		}
	}
	LazySegmentTree(final int n, final BinaryOperator<T> f, final BiFunction<T, U, T> map, final BinaryOperator<U> comp, final T e, final U id) {
		this.n = n;
		this.f = f;
		this.map = map;
		this.comp = comp;
		this.e = e;
		this.id = id;
		sz = 1;
		h = 0;
		while(sz < n) {
			sz <<= 1;
			h++;
		}
		data = new Object[2 * sz];
		Arrays.fill(data, e);
		lazy = new Object[2 * sz];
		Arrays.fill(lazy, id);
	}
	LazySegmentTree(final T[] a, final BinaryOperator<T> f, final BiFunction<T, U, T> map, final BinaryOperator<U> comp, final T e, final U id) {
		this(a.length, f, map, comp, e, id);
		build(a);
	}
	final void build(final T[] a) {
		assert n == a.length;
		for(int k = 0; k < n; ++k) {
			data[k + sz] = a[k];
		}
		for(int k = sz; --k > 0;) {
			update(k);
		}
	}
	final void set(int k, final T x) {
		k += sz;
		for(int i = h; i > 0; i--) {
			propagate(k >> i);
		}
		data[k] = x;
		for(int i = 0; ++i <= h;) {
			update(k >> i);
		}
	}
	@SuppressWarnings("unchecked")
	final T get(int k) {
		k += sz;
		for(int i = h; i > 0; i--) {
			propagate(k >> i);
		}
		return (T) data[k];
	}
	@SuppressWarnings("unchecked")
	final T query(int l, int r) {
		if(l >= r) {
			return e;
		}
		l += sz;
		r += sz;
		for(int i = h; i > 0; i--) {
			if(((l >> i) << i) != l) {
				propagate(l >> i);
			}
			if(((r >> i) << i) != r) {
				propagate((r - 1) >> i);
			}
		}
		T l2 = e, r2 = e;
		for(; l < r; l >>= 1, r >>= 1) {
			if(l % 2 == 1) {
				l2 = f.apply(l2, (T) data[l++]);
			}
			if(r % 2 == 1) {
				r2 = f.apply((T) data[--r], r2);
			}
		}
		return f.apply(l2, r2);
	}
	@SuppressWarnings("unchecked")
	final T all(){ return (T) data[1]; }
	@SuppressWarnings("unchecked")
	final void apply(int k, final U x) {
		k += sz;
		for(int i = h; i > 0; i--) {
			propagate(k >> i);
		}
		data[k] = map.apply((T) data[k], x);
		for(int i = 0; ++i <= h;) {
			update(k >> i);
		}
	}
	final void apply(int l, int r, final U x) {
		if(l >= r) {
			return;
		}
		l += sz;
		r += sz;
		for(int i = h; i > 0; i--) {
			if(((l >> i) << i) != l) {
				propagate(l >> i);
			}
			if(((r >> i) << i) != r) {
				propagate((r - 1) >> i);
			}
		}
		int l2 = l, r2 = r;
		for(; l < r; l >>= 1, r >>= 1) {
			if(l % 2 == 1) {
				allApply(l++, x);
			}
			if(r % 2 == 1) {
				allApply(--r, x);
			}
		}
		l = l2;
		r = r2;
		for(int i = 0; ++i <= h;) {
			if(((l >> i) << i) != l) {
				update(l >> i);
			}
			if(((r >> i) << i) != r) {
				update((r - 1) >> i);
			}
		}
	}
	@SuppressWarnings("unchecked")
	final int findFirst(int l, final Predicate<T> fn) {
		if(l >= n) {
			return n;
		}
		l += sz;
		for(int i = h; i > 0; i--) {
			propagate(l >> i);
		}
		T sum = e;
		do {
			while((l & 1) == 0) {
				l >>= 1;
			}
			if(fn.test(f.apply(sum, (T) data[l]))) {
				while(l < sz) {
					propagate(l);
					l <<= 1;
					final T nxt = f.apply(sum, (T) data[l]);
					if(!fn.test(nxt)) {
						sum = nxt;
						l++;
					}
				}
				return l + 1 - sz;
			}
			sum = f.apply(sum, (T) data[l++]);
		} while((l & -l) != l);
		return n;
	}
	@SuppressWarnings("unchecked")
	final int findLast(int r, final Predicate<T> fn) {
		if(r <= 0) {
			return -1;
		}
		r += sz;
		for(int i = h; i > 0; i--) {
			propagate((r - 1) >> i);
		}
		T sum = e;
		do {
			r--;
			while(r > 1 && r % 2 == 1) {
				r >>= 1;
			}
			if(fn.test(f.apply((T) data[r], sum))) {
				while(r < sz) {
					propagate(r);
					r = (r << 1) + 1;
					final T nxt = f.apply((T) data[r], sum);
					if(!fn.test(nxt)) {
						sum = nxt;
						r--;
					}
				}
				return r - sz;
			}
			sum = f.apply((T) data[r], sum);
		} while((r & -r) != r);
		return -1;
	}
	final void clear(){ Arrays.fill(data, e); }
	@Override
	public final String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append(get(0));
		for(int i = 0; ++i < n;) {
			sb.append(" " + get(i));
		}
		return sb.toString();
	}
}
final class Zwei<T> implements Cloneable {
	public T first, second;
	private Zwei(final T first, final T second) {
		this.first = first;
		this.second = second;
	}
	static final <T> Zwei<T> of(final T f, final T s){ return new Zwei<>(f, s); }
	@Override
	public final boolean equals(final Object o) {
		if(this == o) {
			return true;
		}
		if(o == null || getClass() != o.getClass()) {
			return false;
		}
		final Zwei<?> z = (Zwei<?>) o;
		return first.equals(z.first) && second.equals(z.second);
	}
	@Override
	public final int hashCode(){ return Objects.hash(first, second); }
	@Override
	public final String toString(){ return String.valueOf(first); }
	@SuppressWarnings("unchecked")
	@Override
	public final Zwei<T> clone() {
		try {
			return (Zwei<T>) super.clone();
		} catch(final CloneNotSupportedException e){
			e.printStackTrace();
		}
		throw new Error();
	}
}
final class RAMX extends LazySegmentTree<Long, Long> {
	RAMX(final int[] a){ super(Arrays.stream(a).boxed().toArray(Long[]::new), Long::max, Long::sum, Long::sum, Long.valueOf(Long.MIN_VALUE), Long.valueOf(0)); }
	RAMX(final long[] a){ super(Arrays.stream(a).boxed().toArray(Long[]::new), Long::max, Long::sum, Long::sum, Long.valueOf(Long.MIN_VALUE), Long.valueOf(0)); }
}
final class RAMN extends LazySegmentTree<Long, Long> {
	RAMN(final int[] a){ super(Arrays.stream(a).boxed().toArray(Long[]::new), Long::min, Long::sum, Long::sum, Long.valueOf(Long.MAX_VALUE), Long.valueOf(0)); }
	RAMN(final long[] a){ super(Arrays.stream(a).boxed().toArray(Long[]::new), Long::min, Long::sum, Long::sum, Long.valueOf(Long.MAX_VALUE), Long.valueOf(0)); }
}
final class RASM extends LazySegmentTree<Zwei<Long>, Long> {
	private final int n;
	private final Zwei<Long>[] b;
	@SuppressWarnings("unchecked")
	RASM(final int[] a) {
		super(a.length, (x, y) -> Zwei.of(x.first.longValue() + y.first.longValue(), x.second.longValue() + y.second.longValue()), (x, y) -> Zwei.of(x.first.longValue() + x.second.longValue() * y.longValue(), x.second.longValue()), Long::sum, Zwei.of(0L, 0L), Long.valueOf(0));
		n = a.length;
		b = new Zwei[n];
		for(int i = 0; i < n; ++i) {
			b[i] = Zwei.of((long) a[i], 1L);
		}
		build(b);
	}
	@SuppressWarnings("unchecked")
	RASM(final long[] a) {
		super(a.length, (x, y) -> Zwei.of(x.first.longValue() + y.first.longValue(), x.second.longValue() + y.second.longValue()), (x, y) -> Zwei.of(x.first.longValue() + x.second.longValue() * y.longValue(), x.second.longValue()), Long::sum, Zwei.of(0L, 0L), Long.valueOf(0));
		n = a.length;
		b = new Zwei[n];
		for(int i = 0; i < n; ++i) {
			b[i] = Zwei.of(a[i], 1L);
		}
		build(b);
	}
}
final class RUMX extends LazySegmentTree<Long, Long> {
	RUMX(final int[] a){ super(Arrays.stream(a).boxed().toArray(Long[]::new), Long::max, (x, y) -> y, (x, y) -> y, Long.valueOf(Long.MIN_VALUE), Long.valueOf(Long.MIN_VALUE)); }
	RUMX(final long[] a){ super(Arrays.stream(a).boxed().toArray(Long[]::new), Long::max, (x, y) -> y, (x, y) -> y, Long.valueOf(Long.MIN_VALUE), Long.valueOf(Long.MIN_VALUE)); }
}
final class RUMN extends LazySegmentTree<Long, Long> {
	RUMN(final int[] a){ super(Arrays.stream(a).boxed().toArray(Long[]::new), Long::min, (x, y) -> y, (x, y) -> y, Long.valueOf(Long.MAX_VALUE), Long.valueOf(Long.MAX_VALUE)); }
	RUMN(final long[] a){ super(Arrays.stream(a).boxed().toArray(Long[]::new), Long::min, (x, y) -> y, (x, y) -> y, Long.valueOf(Long.MAX_VALUE), Long.valueOf(Long.MAX_VALUE)); }
}
final class RUSM extends LazySegmentTree<Zwei<Long>, Long> {
	private final int n;
	private final Zwei<Long>[] b;
	@SuppressWarnings("unchecked")
	RUSM(final int[] a) {
		super(a.length, (x, y) -> Zwei.of(x.first.longValue() + y.first.longValue(), x.second.longValue() + y.second.longValue()), (x, y) -> Zwei.of(x.second.longValue() * y.longValue(), x.second.longValue()), (x, y) -> y, Zwei.of(0L, 0L), Long.valueOf(Long.MIN_VALUE));
		n = a.length;
		b = new Zwei[n];
		for(int i = 0; i < n; ++i) {
			b[i] = Zwei.of((long) a[i], 1L);
		}
		build(b);
	}
	@SuppressWarnings("unchecked")
	RUSM(final long[] a) {
		super(a.length, (x, y) -> Zwei.of(x.first.longValue() + y.first.longValue(), x.second.longValue() + y.second.longValue()), (x, y) -> Zwei.of(x.second.longValue() * y.longValue(), x.second.longValue()), (x, y) -> y, Zwei.of(0L, 0L), Long.valueOf(Long.MIN_VALUE));
		n = a.length;
		b = new Zwei[n];
		for(int i = 0; i < n; ++i) {
			b[i] = Zwei.of(a[i], 1L);
		}
		build(b);
	}
}

final class DualSegmentTree<T> {
	private final int n;
	private int sz, h;
	private final Object[] lazy;
	private final T id;
	private final BinaryOperator<T> ap;
	@SuppressWarnings("unchecked")
	private final void propagate(final int k) {
		if(lazy[k] != id) {
			lazy[2 * k] = ap.apply((T) lazy[2 * k], (T) lazy[k]);
			lazy[2 * k + 1] = ap.apply((T) lazy[2 * k + 1], (T) lazy[k]);
			lazy[k] = id;
		}
	}
	private final void thrust(final int k) {
		for(int i = h; i > 0; i--) {
			propagate(k >> i);
		}
	}
	DualSegmentTree(final int n, final BinaryOperator<T> ap, final T id) {
		this.n = n;
		this.ap = ap;
		this.id = id;
		sz = 1;
		h = 0;
		while(sz < n) {
			sz <<= 1;
			h++;
		}
		lazy = new Object[2 * sz];
		Arrays.fill(lazy, id);
	}
	@SuppressWarnings("unchecked")
	final void apply(int a, int b, final T x) {
		thrust(a += sz);
		thrust(b += sz - 1);
		for(int l = a, r = b + 1; l < r; l >>= 1, r >>= 1) {
			if(l % 2 == 1) {
				lazy[l] = ap.apply((T) lazy[l], x);
				l++;
			}
			if(r % 2 == 1) {
				r--;
				lazy[r] = ap.apply((T) lazy[r], x);
			}
		}
	}
	@SuppressWarnings("unchecked")
	final T get(int k) {
		thrust(k += sz);
		return (T) lazy[k];
	}
	@Override
	public final String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append(get(0));
		for(int i = 0; ++i < n;) {
			sb.append(" " + get(i));
		}
		return sb.toString();
	}
}

final class SparseTable {
	private final long[][] st;
	private final int[] lookup;
	private final LongBinaryOperator op;
	SparseTable(final int[] a, final LongBinaryOperator op) {
		this.op = op;
		int b = 0;
		while((1 << b) <= a.length) {
			++b;
		}
		st = new long[b][1 << b];
		for(int i = 0; i < a.length; i++) {
			st[0][i] = a[i];
		}
		for(int i = 1; i < b; i++) {
			for(int j = 0; j + (1 << i) <= (1 << b); j++) {
				st[i][j] = op.applyAsLong(st[i - 1][j], st[i - 1][j + (1 << (i - 1))]);
			}
		}
		lookup = new int[a.length + 1];
		for(int i = 2; i < lookup.length; i++) {
			lookup[i] = lookup[i >> 1] + 1;
		}
	}
	SparseTable(final long[] a, final LongBinaryOperator op) {
		this.op = op;
		int b = 0;
		while((1 << b) <= a.length) {
			++b;
		}
		st = new long[b][1 << b];
		for(int i = 0; i < a.length; i++) {
			st[0][i] = a[i];
		}
		for(int i = 1; i < b; i++) {
			for(int j = 0; j + (1 << i) <= (1 << b); j++) {
				st[i][j] = op.applyAsLong(st[i - 1][j], st[i - 1][j + (1 << (i - 1))]);
			}
		}
		lookup = new int[a.length + 1];
		for(int i = 2; i < lookup.length; i++) {
			lookup[i] = lookup[i >> 1] + 1;
		}
	}
	final long query(final int l, final int r) {
		final int b = lookup[r - l];
		return op.applyAsLong(st[b][l], st[b][r - (1 << b)]);
	}
	final int minLeft(final int x, final LongPredicate fn) {
		if(x == 0) {
			return 0;
		}
		int ok = x, ng = -1;
		while(abs(ok - ng) > 1) {
			final int mid = (ok + ng) / 2;
			if(fn.test(query(mid, x) - 1)) {
				ok = mid;
			}
			else {
				ng = mid;
			}
		}
		return ok;
	}
	final int maxRight(final int x, final LongPredicate fn) {
		if(x == lookup.length - 1) {
			return lookup.length - 1;
		}
		int ok = x, ng = lookup.length;
		while(abs(ok - ng) > 1) {
			final int mid = (ok + ng) / 2;
			if(fn.test(query(x, mid))) {
				ok = mid;
			}
			else {
				ng = mid;
			}
		}
		return ok;
	}
}

final class WaveletMatrix {
	private final WaveletMatrixBeta mat;
	private final long[] ys;
	WaveletMatrix(final int[] arr){ this(arr, 20); }
	WaveletMatrix(final long[] arr){ this(arr, 20); }
	WaveletMatrix(final int[] arr, final int log) {
		ys = Arrays.stream(arr).asLongStream().sorted().distinct().toArray();
		final long[] t = new long[arr.length];
		Arrays.setAll(t, i -> index(arr[i]));
		mat = new WaveletMatrixBeta(t, log);
	}
	WaveletMatrix(final long[] arr, final int log) {
		ys = Arrays.stream(arr).sorted().distinct().toArray();
		final long[] t = new long[arr.length];
		Arrays.setAll(t, i -> index(arr[i]));
		mat = new WaveletMatrixBeta(t, log);
	}
	private final int index(final long x){ return Utility.lowerBound(ys, x); }
	final long get(final int k){ return ys[(int) mat.access(k)]; }
	final int rank(final int r, final long x) {
		final int pos = index(x);
		if(pos == ys.length || ys[pos] != x) {
			return 0;
		}
		return mat.rank(pos, r);
	}
	final int rank(final int l, final int r, final long x){ return rank(r, x) - rank(l, x); }
	final long kthMin(final int l, final int r, final int k){ return ys[(int) mat.kthMin(l, r, k)]; }
	final long kthMax(final int l, final int r, final int k){ return ys[(int) mat.kthMax(l, r, k)]; }
	final int rangeFreq(final int l, final int r, final long upper){ return mat.rangeFreq(l, r, index(upper)); }
	final int rangeFreq(final int l, final int r, final long lower, final long upper){ return mat.rangeFreq(l, r, index(lower), index(upper)); }
	final long prev(final int l, final int r, final long upper) {
		final long ret = mat.prev(l, r, index(upper));
		return ret == -1 ? -1 : ys[(int) ret];
	}
	final long next(final int l, final int r, final long lower) {
		final long ret = mat.next(l, r, index(lower));
		return ret == -1 ? -1 : ys[(int) ret];
	}
	private final class WaveletMatrixBeta {
		private final int log;
		private final SuccinctIndexableDictionary[] matrix;
		private final int[] mid;
		WaveletMatrixBeta(final long[] arr, final int log) {
			final int len = arr.length;
			this.log = log;
			matrix = new SuccinctIndexableDictionary[log];
			mid = new int[log];
			final long[] l = new long[len], r = new long[len];
			for(int level = log; --level >= 0;) {
				matrix[level] = new SuccinctIndexableDictionary(len + 1);
				int left = 0, right = 0;
				for(int i = 0; i < len; ++i) {
					if(((arr[i] >> level) & 1) == 1) {
						matrix[level].set(i);
						r[right++] = arr[i];
					} else {
						l[left++] = arr[i];
					}
				}
				mid[level] = left;
				matrix[level].build();
				final long[] tmp = new long[len];
				System.arraycopy(arr, 0, tmp, 0, len);
				System.arraycopy(l, 0, arr, 0, len);
				System.arraycopy(tmp, 0, l, 0, len);
				for(int i = 0; i < right; ++i) {
					arr[left + i] = r[i];
				}
			}
		}
		private final IntPair succ(final boolean f, final int l, final int r, final int level){ return IntPair.of(matrix[level].rank(f, l) + mid[level] * (f ? 1 : 0), matrix[level].rank(f, r) + mid[level] * (f ? 1 : 0)); }
		final long access(int k) {
			long ret = 0;
			for(int level = log; --level >= 0;) {
				final boolean f = matrix[level].get(k);
				if(f) {
					ret |= 1L << level;
				}
				k = matrix[level].rank(f, k) + mid[level] * (f ? 1 : 0);
			}	
			return ret;
		}
		final int rank(final long x, int r) {
			int l = 0;
			for(int level = log; --level >= 0;) {
				final IntPair p = succ(((x >> level) & 1) == 1, l, r, level);
				l = p.first.intValue();
				r = p.second.intValue();
			}
			return r - l;
		}
		final long kthMin(int l, int r, int k) {
			if(!Utility.scope(0, k, r - l - 1)) {
				throw new IndexOutOfBoundsException();
			}
			long ret = 0;
			for(int level = log; --level >= 0;) {
				final int cnt = matrix[level].rank(false, r) - matrix[level].rank(false, l);
				final boolean f = cnt <= k;
				if(f) {
					ret |= 1 << level;
					k -= cnt;
				}
				final IntPair p = succ(f, l, r, level);
				l = p.first.intValue();
				r = p.second.intValue();
			}
			return ret;
		}
		final long kthMax(final int l, final int r, final int k){ return kthMin(l, r, r - l - k - 1); }
		final int rangeFreq(int l, int r, final long upper) {
			int ret = 0;
			for(int level = log; --level >= 0;) {
				final boolean f = ((upper >> level) & 1) == 1;
				if(f) {
					ret += matrix[level].rank(false, r) - matrix[level].rank(false, l);
				}
				final IntPair p = succ(f, l, r, level); 
				l = p.first.intValue();
				r = p.second.intValue();
			}
			return ret;
		}
		final int rangeFreq(final int l, final int r, final long lower, final long upper){ return rangeFreq(l, r, upper) - rangeFreq(l, r, lower); }
		final long prev(final int l, final int r, final long upper) {
			final int cnt = rangeFreq(l, r, upper);
			return cnt == 0 ? -1 : kthMin(l, r, cnt - 1);
		}
		final long next(final int l, final int r, final long lower) {
			final int cnt = rangeFreq(l, r, lower);
			return cnt == r - l ? -1 : kthMin(l, r, cnt);
		}
		private final class SuccinctIndexableDictionary {
			private final int blk;
			private final int[] bit, sum;
			SuccinctIndexableDictionary(final int len) {
				blk = (len + 31) >> 5;
				bit = new int[blk];
				sum = new int[blk];
			}
			final void set(final int k){ bit[k >> 5] |= 1 << (k & 31); }
			final void build() {
				sum[0] = 0;
				for(int i = 0; i + 1 < blk; ++i) {
					sum[i + 1] = sum[i] + Integer.bitCount(bit[i]);
				}
			}
			final boolean get(final int k){ return ((bit[k >> 5] >> (k & 31)) & 1) == 1; }
			final int rank(final int k){ return (sum[k >> 5] + Integer.bitCount(bit[k >> 5] & ((1 << (k & 31)) - 1))); }
			final int rank(final boolean val, final int k){ return val ? rank(k) : k - rank(k); }
		}
	}
}