import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.TreeMap;

class VvyLw extends Utility {
	protected static final MyScanner sc = new MyScanner();
	protected static final MyPrinter o = new MyPrinter(System.out, false);
	protected static final MyPrinter e = new MyPrinter(System.err, true);
	static final int[] dx = {0, -1, 1, 0, 0, -1, -1, 1, 1};
	static final int[] dy = {0, 0, 0, -1, 1, -1, 1, -1, 1};
	static final int inf = 1 << 30;
	static final long linf = (1L << 61) - 1;
	static final int mod998 = 998244353;
	static final int mod107 = (int)1e9 + 7;
	static final double eps = 1e-18;
	protected static final void solve() {
		final int n = sc.ni(), k = sc.ni();
        final int[] p = Arrays.stream(sc.ni(n)).map(i -> i - 1).toArray();
        if(k == 1) {
            o.ende(Arrays.stream(p).map(i -> i + 1).toArray());
        }
        var m = new TreeMap<Integer, ArrayList<Integer>>();
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        for(int i = 0; i < n; ++i) {
            final var ky = m.ceilingKey(p[i]);
            if(ky != null) {
                m.put(p[i], m.get(ky));
                m.remove(ky);
            }
            m.computeIfAbsent(p[i], x -> new ArrayList<>()).add(p[i]);
            final var nm = m.get(p[i]);
            if(nm.size() == k) {
                for(final var id: nm) {
                    ans[id] = i + 1;
                }
                m.remove(p[i]);
            }
        }
        o.outl(ans);
	}
}
final class Main extends VvyLw {
	public static void main(final String[] args) {
		int t = 1;
		//t = sc.ni();
		while(t-- > 0) {
			solve();
		}
		o.flush();
		sc.close();
		o.close();
		e.close();
	}
}

class Utility {
	protected static final String yes(final boolean ok){ return ok ? "Yes" : "No"; }
	protected static final String no(final boolean ok){ return yes(!ok); }
	protected static final long sqr(final long x){ return x * x; }
	protected static final int mod(final long n, final int m){ return (int) ((n + m) % m); }
	protected static final long intCeil(long a, long b){ return (long) Math.ceil((double)a / b); }
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
	protected static final long intPow(long a, long b, final int m) {
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
	protected static final ArrayList<Long> div(final long n) {
		ArrayList<Long> d = new ArrayList<>();
		for(long i = 1; i * i <= n; ++i) {
			if(n % i == 0) {
				d.add(i);
				if(i * i != n) {
					d.add(n / i);
				}
			}
		}
		Collections.sort(d);
		return d;
	}
	protected static final ArrayList<Pair<Long, Integer>> primeFactor(long n) {
		ArrayList<Pair<Long, Integer>> pf = new ArrayList<>();
		for(long i = 2; i * i <= n; ++i) {
			if(n % i != 0) {
				continue;
			}
			int cnt = 0;
			while(n % i == 0) {
				cnt++;
				n /= i;
			}
			pf.add(Pair.of(i, cnt));
		}
		if(n != 1) {
			pf.add(Pair.of(n, 1));
		}
		return pf;
	}
	protected static final long binom(int a, final int b) {
		long res = 1;
		for(int i = 1; i <= b; ++i) {
			res *= a--;
			res /= i;
		}
		return res;
	}
	protected static final boolean isInt(final double n){ long r = (long) Math.floor(n); return r == n; }
	protected static final boolean isSqr(final long n){ return isInt(Math.sqrt(n)); }
	protected static final boolean isPrime(final long n) {
		if(n == 1) return false;
		for(long i = 2; i * i <= n; ++i) {
			if(n % i == 0) return false;
		}
		return true;
	}
	protected static final boolean scope(final int l, final int x, final int r){ return l <= x && x <= r; }
	protected static final boolean nextPerm(ArrayList<? extends Number> a) {
		for(int i = a.size() - 1; i > 0; i--) {
			if(a.get(i - 1).longValue() < a.get(i).longValue()) {
				final int j = find(a.get(i - 1).longValue(), a, i, a.size() - 1);
				Collections.swap(a, i - 1, j);
				Collections.sort(a.subList(i, a.size()), (x, y) -> Long.compare(x.longValue(), y.longValue()));
				return true;
			}
		}
		return false;
	}
	protected static final String nextPerm(final String s) {
		var a = s.chars().mapToObj(i -> (char)i).collect(Collectors.toList());
		for(int i = a.size() - 1; i > 0; i--) {
			if(a.get(i - 1).compareTo(a.get(i)) < 0) {
				final int j = find(a.get(i - 1), a, i, a.size() - 1);
				Collections.swap(a, i - 1, j);
				Collections.sort(a.subList(i, a.size()));
				return a.stream().map(String::valueOf).collect(Collectors.joining());
			}
		}
		return null;
	}
	protected static final boolean prevPerm(ArrayList<? extends Number> a) {
		for(int i = a.size() - 1; i > 0; i--) {
			if(a.get(i - 1).longValue() > a.get(i).longValue()) {
				final int j = findRev(a.get(i - 1).longValue(), a, i, a.size() - 1);
				Collections.swap(a, i - 1, j);
				Collections.sort(a.subList(i, a.size()), Collections.reverseOrder());
				return true;
			}
		}
		return false;
	}
	protected static final String prevPerm(final String s) {
		var a = s.chars().mapToObj(i -> (char)i).collect(Collectors.toList());
		for(int i = a.size() - 1; i > 0; i--) {
			if(a.get(i - 1).compareTo(a.get(i)) > 0) {
				final int j = findRev(a.get(i - 1), a, i, a.size() - 1);
				Collections.swap(a, i - 1, j);
				Collections.sort(a.subList(i, a.size()), Collections.reverseOrder());
				return a.stream().map(String::valueOf).collect(Collectors.joining());
			}
		}
		return null;
	}
	private static final int find(final long dest, final List<? extends Number> a, final int s, final int e) {
		if(s == e) {
			return s;
		}
		final int m = (s + e + 1) / 2;
		return a.get(m).longValue() <= dest ? find(dest, a, s, m - 1) : find(dest, a, m, e);
	}
	private static final int find(final char dest, final List<Character> a, final int s, final int e) {
		if(s == e) {
			return s;
		}
		final int m = (s + e + 1) / 2;
		return a.get(m).compareTo(dest) <= 0 ? find(dest, a, s, m - 1) : find(dest, a, m, e);
	}
	private static final int findRev(final long dest, final List<? extends Number> a, final int s, final int e) {
		if(s == e) {
			return s;
		}
		final int m = (s + e + 1) / 2;
		return a.get(m).longValue() > dest ? findRev(dest, a, s, m - 1) : findRev(dest, a, m, e);
	}
	private static final int findRev(final char dest, final List<Character> a, final int s, final int e) {
		if(s == e) {
			return s;
		}
		final int m = (s + e + 1) / 2;
		return a.get(m).compareTo(dest) > 0 ? find(dest, a, s, m - 1) : find(dest, a, m, e);
	}
	protected static final boolean binarySearch(final int[] a, final int x) {
		return Arrays.binarySearch(a, x) >= 0;
	}
	protected static final boolean binarySearch(final long[] a, final long x) {
		return Arrays.binarySearch(a, x) >= 0;
	}
    protected static final boolean binarySearch(final Object[] a, final Object x) {
        return binarySearch(Arrays.stream(a).collect(Collectors.toList()), x);
    }
	protected static final int lowerBound(final int[] a, final int x) {
		return lowerBound(Arrays.stream(a).boxed().collect(Collectors.toList()), x);
	}
	protected static final int lowerBound(final long[] a, final long x) {
		return lowerBound(Arrays.stream(a).boxed().collect(Collectors.toList()), x);
	}
    protected static final <T extends Comparable<? super T>> int lowerBound(final T[] a, final T x) {
		return lowerBound(Arrays.asList(a), x);
	}
	protected static final int upperBound(final int[] a, final int x) {
		return upperBound(Arrays.stream(a).boxed().collect(Collectors.toList()), x);
	}
	protected static final int upperBound(final long[] a, final long x) {
		return upperBound(Arrays.stream(a).boxed().collect(Collectors.toList()), x);
	}
    protected static final <T extends Comparable<? super T>> int upperBound(final T[] a, final T x) {
		return upperBound(Arrays.asList(a), x);
	}
	protected static final <T> boolean binarySearch(final List<T> a, final T x) {
		return Collections.binarySearch(a, x, null) >= 0;
	}
	protected static final <T extends Comparable<? super T>> int lowerBound(final List<T> a, final T x) {
		return ~Collections.binarySearch(a, x, (p, q) -> p.compareTo(q) >= 0 ? 1 : -1);
	}
	protected static final <T extends Comparable<? super T>> int upperBound(final List<T> a, final T x) {
		return ~Collections.binarySearch(a, x, (p, q) -> p.compareTo(q) > 0 ? 1 : -1);
	}
	protected static final int[] reverse(final int[] a) {
		final int n = a.length;
		int[] b = new int[n];
		for(int i = 0; i <= n / 2; ++i) {
			b[i] = a[n - 1 - i];
			b[n - 1 - i] = a[i];
		}
		return b;
	}
	protected static final long[] reverse(final long[] a) {
		final int n = a.length;
		long[] b = new long[n];
		for(int i = 0; i <= n / 2; ++i) {
			b[i] = a[n - 1 - i];
			b[n - 1 - i] = a[i];
		}
		return b;
	}
	protected static final double[] reverse(final double[] a) {
		final int n = a.length;
		double[] b = new double[n];
		for(int i = 0; i <= n / 2; ++i) {
			b[i] = a[n - 1 - i];
			b[n - 1 - i] = a[i];
		}
		return b;
	}
	protected static final Object[] reverse(final Object[] a) {
		final int n = a.length;
		Object[] b = new Object[n];
		for(int i = 0; i <= n / 2; ++i) {
			b[i] = a[n - 1 - i];
			b[n - 1 - i] = a[i];
		}
		return b;
	}
	protected static final int[] rotate(final int[] a, final int id) {
		ArrayList<Integer> t = new ArrayList<>(a.length);
		for(final var el: a) {
			t.add(el);
		}
		Collections.rotate(t, id);
		int[] res = new int[t.size()];
		for(int i = 0; i < t.size(); ++i) {
			res[i] = t.get(i);
		}
		return res;
	}
	protected static final long[] rotate(final long[] a, final int id) {
		ArrayList<Long> t = new ArrayList<>(a.length);
		for(final var el: a) {
			t.add(el);
		}
		Collections.rotate(t, id);
		long[] res = new long[t.size()];
		for(int i = 0; i < t.size(); ++i) {
			res[i] = t.get(i);
		}
		return res;
	}
	protected static final double[] rotate(final double[] a, final int id) {
		ArrayList<Double> t = new ArrayList<>(a.length);
		for(final var el: a) {
			t.add(el);
		}
		Collections.rotate(t, id);
		double[] res = new double[t.size()];
		for(int i = 0; i < t.size(); ++i) {
			res[i] = t.get(i);
		}
		return res;
	}
	protected static final String rotate(final String s, final int id) {
		ArrayList<Character> t = new ArrayList<>();
		for(final char c: s.toCharArray()) {
			t.add(c);
		}
		Collections.rotate(t, id);
		StringBuilder sb = new StringBuilder();
		for(final var c: t) {
			sb.append(c);
		}
		return sb.toString(); 
	}
	protected static final int[][] rotate(final int[][] a) {
		final int h = a.length, w = a[0].length;
		int[][] b = new int[w][h];
		IntStream.range(0, h).forEach(i -> {
			IntStream.range(0, w).forEach(j -> b[j][i] = a[i][j]);
		});
		IntStream.range(0, w).forEach(i -> b[i] = reverse(b[i]));
		return b;
	}
	protected static final long[][] rotate(final long[][] a) {
		final int h = a.length, w = a[0].length;
		long[][] b = new long[w][h];
		IntStream.range(0, h).forEach(i -> {
			IntStream.range(0, w).forEach(j -> b[j][i] = a[i][j]);
		});
		IntStream.range(0, w).forEach(i -> b[i] = reverse(b[i]));
		return b;
	}
	protected static final double[][] rotate(final double[][] a) {
		final int h = a.length, w = a[0].length;
		double[][] b = new double[w][h];
		IntStream.range(0, h).forEach(i -> {
			IntStream.range(0, w).forEach(j -> b[j][i] = a[i][j]);
		});
		IntStream.range(0, w).forEach(i -> b[i] = reverse(b[i]));
		return b;
	}
	protected static final String[] rotate(final String[] s) {
		final int h = s.length, w = s[0].length();
		char[][] t = new char[w][h];
		IntStream.range(0, h).forEach(i -> {
			IntStream.range(0, w).forEach(j -> t[j][i] = s[i].charAt(j));
		});
		IntStream.range(0, w).forEach(i -> t[i] = new StringBuilder(new String(t[i])).reverse().toString().toCharArray());
		String[] res = new String[w];
		IntStream.range(0, w).forEach(i -> res[i] = new String(t[i]));
		return res;
	}
	protected static final long lcm(final long a, final long b){ return a * b / gcd(a, b); }
	protected static final long gcd(final long a, final long b){ return b > 0 ? gcd(b, a % b) : a; }
	protected static final long lcm(final long... a){ return Arrays.stream(a).reduce(1, (x, y) -> lcm(x, y)); }
	protected static final long gcd(final long... a){ return Arrays.stream(a).reduce(0, (x, y) -> gcd(x, y)); }
	protected static final long min(final long... a){ return Arrays.stream(a).reduce(Long.MAX_VALUE, (x, y) -> Math.min(x, y)); }
	protected static final long max(final long... a){ return Arrays.stream(a).reduce(Long.MIN_VALUE, (x, y) -> Math.max(x, y)); }
	protected static final <F, S> ArrayList<F> first(final List<Pair<F, S>> p) {
		ArrayList<F> f = new ArrayList<>();
		for(final var el: p) {
			f.add(el.first);
		}
		return f;
	}
	protected static final <F, S> ArrayList<S> second(final List<Pair<F, S>> p) {
		ArrayList<S> s = new ArrayList<>();
		for(final var el: p) {
			s.add(el.second);
		}
		return s;
	}
	protected static final int[] iota(final int n){ return IntStream.range(0, n).toArray(); }
	protected static final int[] iota(final int n, final int init){ return IntStream.range(0 + init, n + init).toArray(); }
	protected static final long bins(long ok, long ng, final Predicate<Long> fn) {
		while(Math.abs(ok - ng) > 1) {
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
	protected static final double bins(double ok, double ng, final Predicate<Double> fn) {
		while(Math.abs(ok - ng) > VvyLw.eps) {
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
	protected static final ArrayList<Integer> press(final ArrayList<Long> a) {
		ArrayList<Integer> res = new ArrayList<>();
		final var cp = a.stream().sorted().distinct().collect(Collectors.toList());
		for(final var el: a) {
			res.add(lowerBound(cp, el));
		}
		return res;
	}
	protected static final int[] zAlgorithm(final String s) {
		final int n = s.length();
		int j = 0;
		int[] pre = new int[n];
		for(int i = 0; ++i < n;) {
			if(i + pre[i - j] < j + pre[j]) {
				pre[i] = pre[i - j];
			}
			else {
				int k = Math.max(0, j + pre[j] - i);
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
		char[] s;
		if(calcEven) {
			s = new char[2 * n - 1];
			IntStream.range(0, n).forEach(i -> s[i] = s_.charAt(i));
			for(int i = n; --i >= 0;) {
				s[2 * i] = s_.charAt(i);
			}
			final var d = Collections.min(s_.chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
			for(int i = 0; i < n - 1; ++i) {
				s[2 * i + 1] = d;
			}
		} else {
			s = new char[n];
			IntStream.range(0, n).forEach(i -> s[i] = s_.charAt(i));
		}
		n = s.length;
		int[] rad = new int[n];
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
			for(var x: rad) {
				x = 2 * x - 1;
			}
		}
		return rad;
	}
	protected static final long kthRoot(final long n, final int k) {
		if(k == 1) {
			return n;
		}
		final Predicate<Long> chk = (x) -> {
			long mul = 1;
			for(int j = 0; j < k; ++j) {
				try {
					mul = Math.multiplyExact(mul, x);
				} catch(ArithmeticException e) {
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
}

final class MyScanner {
	private final Scanner sc = new Scanner(System.in);
	final int ni(){ return sc.nextInt(); }
	final long nl(){ return sc.nextLong(); }
	final double nd(){ return sc.nextDouble(); }
	final String ns(){ return sc.next(); }
	final int[] ni(final int n){
		int[] a = new int[n];
		IntStream.range(0, n).forEach(i -> a[i] = ni());
		return a;
	}
	final long[] nl(final int n){
		long[] a = new long[n];
		IntStream.range(0, n).forEach(i -> a[i] = nl());
		return a;
	}
	final double[] nd(final int n){
		double[] a = new double[n];
		IntStream.range(0, n).forEach(i -> a[i] = nd());
		return a;
	}
	final String[] ns(final int n){
		String[] a = new String[n];
		IntStream.range(0, n).forEach(i -> a[i] = ns());
		return a;
	}
	final ArrayList<Integer> nia(final int n) {
		var a = new ArrayList<Integer>(n);
		IntStream.range(0, n).forEach(i -> a.add(i, ni()));
		return a;
	}
	final ArrayList<Long> nla(final int n) {
		var a = new ArrayList<Long>(n);
		IntStream.range(0, n).forEach(i -> a.add(i, nl()));
		return a;
	}
	final ArrayList<Double> nda(final int n) {
		var a = new ArrayList<Double>(n);
		IntStream.range(0, n).forEach(i -> a.add(i, nd()));
		return a;
	}
	final ArrayList<String> nsa(final int n) {
		var a = new ArrayList<String>(n);
		IntStream.range(0, n).forEach(i -> a.add(i, ns()));
		return a;
	}
	final void close(){ sc.close(); }
}

final class MyPrinter {
	private final PrintWriter pw;
	MyPrinter(final OutputStream os, final boolean flush){ pw = new PrintWriter(os, flush); }
	final void print(final Object arg){ pw.print(arg); }
	final void out(){ pw.println(); }
	final void out(final Object head, final Object... tail) {
		pw.print(head);
		for(final var el: tail) {
			pw.print(" " + el);
		}
		out();
	}
	final <F, S> void out(final Pair<F, S> arg){ pw.println(arg.first + " " + arg.second); }
	final void out(final int[] args){ IntStream.range(0, args.length).forEach(i -> pw.print(args[i] + (i + 1 < args.length ? " " : "\n"))); }
	final void out(final long[] args){ IntStream.range(0, args.length).forEach(i -> pw.print(args[i] + (i + 1 < args.length ? " " : "\n"))); }
	final void out(final double[] args){ IntStream.range(0, args.length).forEach(i -> pw.print(args[i] + (i + 1 < args.length ? " " : "\n"))); }
	final void out(final boolean[] args){ IntStream.range(0, args.length).forEach(i -> pw.print(args[i] + (i + 1 < args.length ? " " : "\n"))); }
	final void out(final char[] args){ IntStream.range(0, args.length).forEach(i -> pw.print(args[i] + (i + 1 < args.length ? " " : "\n"))); }
	final void out(final Object[] args){ IntStream.range(0, args.length).forEach(i -> pw.print(args[i] + (i + 1 < args.length ? " " : "\n"))); }
	final <T> void out(final List<T> args){ IntStream.range(0, args.size()).forEach(i -> pw.print(args.get(i) + (i + 1 < args.size() ? " " : "\n"))); }
	final void outl(final Object head, final Object... tail) {
		out(head);
		Arrays.stream(tail).forEach(pw::println);
	}
	final void outl(final int[] args){ Arrays.stream(args).forEach(pw::println); }
	final void outl(final int[][] args){ IntStream.range(0, args.length).forEach(i -> out(args[i])); }
	final void outl(final long[] args){ Arrays.stream(args).forEach(pw::println); }
	final void outl(final long[][] args){ IntStream.range(0, args.length).forEach(i -> out(args[i])); }
	final void outl(final double[] args){ Arrays.stream(args).forEach(pw::println); }
	final void outl(final double[][] args){ IntStream.range(0, args.length).forEach(i -> out(args[i])); }
	final void outl(final boolean[] args){ IntStream.range(0, args.length).forEach(i -> out(args[i])); }
	final void outl(final boolean[][] args){ IntStream.range(0, args.length).forEach(i -> out(args[i])); }
	final void outl(final char[] args){ IntStream.range(0, args.length).forEach(i -> out(args[i])); }
	final void outl(final Object[] args){ Arrays.stream(args).forEach(pw::println); }
	final <E> void outl(final Collection<E> args){ args.stream().forEach(pw::println); }
	final void fin(final Object head, final Object... tail) {
		out(head, tail);
		flush();
		System.exit(0);
	}
    final void fin(final int[] args) {
        out(args);
        flush();
        System.exit(0);
    }
	final void fin(final long[] args) {
        out(args);
        flush();
        System.exit(0);
    }
	final void fin(final double[] args) {
        out(args);
        flush();
        System.exit(0);
    }
	final void fin(final boolean[] args) {
        out(args);
        flush();
        System.exit(0);
    }
	final void fin(final char[] args) {
        out(args);
        flush();
        System.exit(0);
    }
	final void fin(final Object[] args) {
        out(args);
        flush();
        System.exit(0);
    }
	final <T> void fin(final List<T> args) {
		out(args);
		flush();
		System.exit(0);
	}
    final void ende(final int[] args) {
        outl(args);
        flush();
        System.exit(0);
    }
	final void ende(final long[] args) {
        outl(args);
        flush();
        System.exit(0);
    }
	final void ende(final double[] args) {
        outl(args);
        flush();
        System.exit(0);
    }
	final void ende(final boolean[] args) {
        outl(args);
        flush();
        System.exit(0);
    }
	final void ende(final char[] args) {
        outl(args);
        flush();
        System.exit(0);
    }
	final void ende(final Object[] args) {
        outl(args);
        flush();
        System.exit(0);
    }
	final <E> void ende(final Collection<E> args) {
		outl(args);
		flush();
		System.exit(0);
	}
	final void flush(){ pw.flush(); }
	final void close(){ pw.close(); }
}

class Pair<F, S> {
	protected final F first;
	protected final S second;
	Pair(final F first, final S second) {
		this.first = first;
		this.second = second;
	}
	@Override
	public final boolean equals(final Object o) {
		if(this == o) {
			return true;
		}
		if(o == null || getClass() != o.getClass()) {
			return false;
		}
		final Pair<?, ?> p = (Pair<?, ?>) o;
		if(!first.equals(p.first)) {
			return false;
		}
		return second.equals(p.second);
	}
	@Override
	public final int hashCode(){ return 31 * first.hashCode() + second.hashCode(); }
	@Override
	public final String toString(){ return "(" + first + ", " + second + ")"; }
	public static final <F, S> Pair<F, S> of(final F a, final S b){ return new Pair<>(a, b); }
	final Pair<S, F> swap(){ return Pair.of(second, first); }
}
final class NumPair extends Pair<Number, Number> implements Comparable<NumPair>  {
	NumPair(final Number first, final Number second){ super(first, second); }
	final NumPair rotate(){ return new NumPair(-second.doubleValue(), first.doubleValue()); } 
	final NumPair rotate(final int ang) {
		final double rad = Math.toRadians(Utility.mod(ang, 360));
		return new NumPair(first.doubleValue() * Math.cos(rad) - second.doubleValue() * Math.sin(rad),
							first.doubleValue() * Math.sin(rad) + second.doubleValue() * Math.cos(rad));
	}
	final long dot(final NumPair p){ return first.longValue() * p.first.longValue() + second.longValue() + p.second.longValue(); }
	final double dotf(final NumPair p){ return first.doubleValue() * p.first.doubleValue() + second.doubleValue() + p.second.doubleValue(); }
	final long cross(final NumPair p){ return this.rotate().dot(p); }
	final double crossf(final NumPair p){ return this.rotate().dotf(p); }
	final long sqr(){ return this.dot(this); }
	final double sqrf(){ return this.dotf(this); }
	final double grad() { 
		try {
			return second.doubleValue() / first.doubleValue();
		} catch(ArithmeticException e) {
			e.printStackTrace();
		}
		return Double.NaN;
	}
	final double abs(){ return Math.hypot(first.doubleValue(), second.doubleValue()); }
	final long lcm(){ return Utility.lcm(first.longValue(), second.longValue()); }
	final long gcd(){ return Utility.gcd(first.longValue(), second.longValue()); }
	final NumPair extgcd() {
		long x = 1, y = 0, t1 = 0, t2 = 0, t3 = 1, a = first.longValue(), b = second.longValue();
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
		return new NumPair(x, y);
	}
	@Override
	final public int compareTo(final NumPair o) {
		if(first.doubleValue() == o.first.doubleValue()) {
			return Double.compare(second.doubleValue(), o.second.doubleValue());
		}
		return Double.compare(first.doubleValue(), o.first.doubleValue());
	}
}